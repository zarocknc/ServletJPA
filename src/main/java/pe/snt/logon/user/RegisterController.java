package pe.snt.logon.user;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class RegisterController extends HttpServlet {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    @Inject
    private UsuarioRepository usuarioRepository;

    protected void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        logger.info("Solicitud de registro de usuario: " + username);

        Usuario newUser = new Usuario();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        usuarioRepository.create(newUser);

        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerUser(request, response);
    }
}
