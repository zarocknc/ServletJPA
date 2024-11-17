/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.snt.logon.user;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sebas
 */
@Named
@RequestScoped
public class LoginBean {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    @Inject
    private UsuarioRepository usuarioRepository;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    private List<Usuario> users;

    public List<Usuario> getUsers() {
        return users;
    }

    public String login() {
        Optional<Usuario> usuario = usuarioRepository.validateUser(username, password);
        if (usuario.isPresent()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Inicio de sesion exitoso"));
            // cargamos la lista de usuarios
            users = usuarioRepository.findAll();
            return "panel";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, Usuario o password incorrecto", "Usuario o contraseña incorrectos"));
            return null; // Mantiene en la misma página para mostrar el mensaje de error
        }

    }

}
