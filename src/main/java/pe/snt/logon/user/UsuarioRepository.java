/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.snt.logon.user;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
@Stateless
public class UsuarioRepository {

    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager em;

    public Usuario create(Usuario user) {
        logger.info("Creando usuario: " + user.getUsername());
        em.persist(user);
        return user;
    }

    public List<Usuario> findAll() {
        logger.info("Obteniendo todos los usuarios");
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Optional<Usuario> findById(Long id) {
        logger.info("Buscando Usuario por id: " + id);
        return Optional.ofNullable(em.find(Usuario.class, id));
    }

    public void delete(Long id) {
        logger.info("Borrando usuario con id: " + id);
        var user = findById(id).orElseThrow(() -> new IllegalArgumentException("Id de usuario Invalido: " + id));
        em.remove(user);
    }

    public Usuario update(Usuario user) {
        logger.info("Actualizando usuario con id: " + user.getId());
        return em.merge(user);
    }

    public Optional<Usuario> validateUser(String username, String password) {
        logger.info("Validando usuario: " + username + " passwd: " + password);
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password",
                Usuario.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

}
