package pe.snt.logon.categoria;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class CategoriaBean implements Serializable {
    
    @Inject
    private CategoriaRepository categoriaRepository;
    
    @Getter @Setter
    private Categoria categoria = new Categoria();
    
    @Getter
    private List<Categoria> categorias;
    
    public String guardar() {
        if (categoria.getId() == null) {
            categoriaRepository.crear(categoria);
        } else {
            categoriaRepository.actualizar(categoria);
        }
        categoria = new Categoria();
        return "categorias?faces-redirect=true";
    }
    
    public void cargarCategorias() {
        categorias = categoriaRepository.buscarTodos();
    }
    
    public void eliminar(Long id) {
        categoriaRepository.eliminar(id);
        cargarCategorias();
    }
    
    public void editar(Categoria cat) {
        this.categoria = cat;
    }
    
    public void nuevo() {
        this.categoria = new Categoria();
    }
}
