package tienda.model.dao;

import java.util.List;

/**
 *
 * @author c3p0
 */
public interface GenericDAO<T,K> {
    
    public T buscaId(K id);
    public List<T> buscaTodos();
    public boolean crea(T c);
    public boolean guarda(T c);    
    public boolean borra(K id);
}
