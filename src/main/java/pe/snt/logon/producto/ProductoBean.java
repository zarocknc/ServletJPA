package pe.snt.logon.producto;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import pe.snt.logon.categoria.CategoriaRepository;

@Named
@SessionScoped
public class ProductoBean implements Serializable {
    
    @Inject
    private ProductoRepository productoRepository;
    
    @Inject
    private CategoriaRepository categoriaRepository;
    
    @Getter @Setter
    private Producto producto = new Producto();
    
    @Getter
    private List<Producto> productos;
    
    public String guardar() {
        if (producto.getId() == null) {
            productoRepository.crear(producto);
        } else {
            productoRepository.actualizar(producto);
        }
        producto = new Producto();
        return "productos?faces-redirect=true";
    }
    
    public void cargarProductos() {
        productos = productoRepository.buscarTodos();
    }
    
    public void eliminar(Long id) {
        productoRepository.eliminar(id);
        cargarProductos();
    }
    
    public void editar(Producto prod) {
        this.producto = prod;
    }
    
    public void nuevo() {
        this.producto = new Producto();
    }
    
    public List<pe.snt.logon.categoria.Categoria> getCategorias() {
        return categoriaRepository.buscarTodos();
    }
}
