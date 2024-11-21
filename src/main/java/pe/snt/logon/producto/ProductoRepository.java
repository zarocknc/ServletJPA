package pe.snt.logon.producto;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class ProductoRepository {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager em;

    public Producto crear(Producto producto) {
        logger.info("Creando producto: " + producto.getNombre());
        em.persist(producto);
        return producto;
    }

    public List<Producto> buscarTodos() {
        logger.info("Obteniendo todos los productos");
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    public Optional<Producto> buscarPorId(Long id) {
        logger.info("Buscando producto por id: " + id);
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando producto con id: " + id);
        var producto = buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Id de producto inválido: " + id));
        em.remove(producto);
    }

    public Producto actualizar(Producto producto) {
        logger.info("Actualizando producto con id: " + producto.getId());
        return em.merge(producto);
    }
}
