package pe.snt.logon.categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class CategoriaConverter implements Converter<Categoria> {
    
    @Inject
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return categoriaRepository.buscarPorId(Long.valueOf(value))
                .orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
        if (categoria == null || categoria.getId() == null) {
            return "";
        }
        return categoria.getId().toString();
    }
}
