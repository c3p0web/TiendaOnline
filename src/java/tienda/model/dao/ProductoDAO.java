package tienda.model.dao;

import java.util.List;
import tienda.model.Producto;

/**
 *
 * @author c3p0
 */
public interface ProductoDAO extends GenericDAO<Producto,Integer> {
    public List<Producto> buscaMayorPrecio();
    
}
