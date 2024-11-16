/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.snt.logon.user;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class UsuarioController extends HttpServlet {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    @Inject
    private UsuarioRepository usuarioRepository;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void validateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("Solicitud de validar usuario: " + username + " " + password);
        Optional<Usuario> usuario = usuarioRepository.validateUser(username, password);

        if (usuario.isPresent()) {
            logger.info("Usuario encontrado!");
            List<Usuario> users = usuarioRepository.findAll();
            //if list has some values then you are logged in
            out.print("<h1 align='center'>Congrats!You've SuccessFully Logged In</h1>");
            out.print("<table align ='center' border='1' cellspacing='5' cellpadding='5'><tr><th>ID</th><th>NAME</th><th>Password</th><th>Email</th></tr>");
            for (Usuario u : users) {
                out.print("<tr><td>" + u.getId() + "</td>");
                out.print("<td>" + u.getUsername() + "</td>");
                out.print("<td>" + u.getPassword() + "</td>");
                out.print("<td>" + u.getEmail() + "</td></tr>");
            }
            out.print("</table>");
            //response.sendRedirect(request.getContextPath() + "/panel.html");
        } else {
            logger.info("Usuario no encontrado");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validateUser(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
