package pe.snt.logon.categoria;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class CategoriaRepository {
    private static final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager em;

    public Categoria crear(Categoria categoria) {
        logger.info("Creando categoría: " + categoria.getNombre());
        em.persist(categoria);
        return categoria;
    }

    public List<Categoria> buscarTodos() {
        logger.info("Obteniendo todas las categorías");
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        logger.info("Buscando categoría por id: " + id);
        return Optional.ofNullable(em.find(Categoria.class, id));
    }

    public void eliminar(Long id) {
        logger.info("Eliminando categoría con id: " + id);
        var categoria = buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Id de categoría inválido: " + id));
        em.remove(categoria);
    }

    public Categoria actualizar(Categoria categoria) {
        logger.info("Actualizando categoría con id: " + categoria.getId());
        return em.merge(categoria);
    }
}
