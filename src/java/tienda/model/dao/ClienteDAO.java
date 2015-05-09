package tienda.model.dao;

import tienda.model.Cliente;

/**
 *
 * @author c3p0
 */
public interface ClienteDAO extends GenericDAO<Cliente,String> {
    public boolean creaAdmin(Cliente c);
}
