package tienda.controler;

import tienda.model.Producto;
import tienda.model.dao.ProductoDAO;
import tienda.qualifiers.DAOJdbc;
import tienda.qualifiers.DAOList;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Lob;


@Named(value="productoCtrl")
@ViewScoped
public class ProductoController implements Serializable {
     private static final long serialVersionUID = 1L;

    //Business logic
    @Inject @DAOJdbc    //Inject DAO JDBC Implementation version
    //@Inject @DAOList  //Inject DAO ArrayList testing implementation version
    private ProductoDAO productoDAO;
   
    //Model
    private Producto c;
    private List<Producto> lc;
    private int editRow=0;      //current client editable
    
    public ProductoController() {
        //DAO not injected yet
    }
    
    @PostConstruct
    public void init() {
        //Usefull if DAO injection needed
        c=new Producto();
        lc=null;
    }
    
 
    
    //Model access
    public List<Producto> getProductos() {
        if (lc==null)
                lc=productoDAO.buscaTodos();
        return lc;
    }
    public void setProductos (List<Producto> lc) {
        this.lc=lc;
    }

    public Producto getProducto () {
        return c;
    }
    public void setProducto (Producto c) {
        this.c=c;
    }
    
    //ACTIONS for visualiza, crea, edit and borra views
    
    /** Get client from id param*/
    public void recupera() {
        Producto productoActual;
        productoActual=productoDAO.buscaId(c.getId());
        if (productoActual!=null) {
             c=productoActual;
        } else {
            c=new Producto();
        }
    }
    /** Create a new Client from model data*/
    public String crea() {
        c.setId(0);
        productoDAO.crea(c); 
        //Post-Redirect-Get
        return "visualiza?faces-redirect=true&id="+c.getId();
    }
    /** Update current model client to DAO*/    
    public String guarda() {
        productoDAO.guarda(c);
        return "visualiza?faces-redirect=true&id="+c.getId();
    }        
    /** Delete current model data client*/
    public String borra() {        
        productoDAO.borra(c.getId());
        return "listado_din";
    }

    //ACTIONS for listado.xhtml view
    
    public String borra(Producto producto) {
        productoDAO.borra(producto.getId());
        return "listado_din";
    }
    
    //ACTIONS for listado_din.xhtml view

    /**Check if current row has edit mode enabled */
    public boolean isEditable(int row) {
        return editRow==row;
    }

    /**Enable row edit model*/
    public void setEditRow(int row) {
        this.editRow = row;
    }

    /** Update current row from table to DAO*/    
    public void guarda(Producto producto) {

        productoDAO.guarda(producto);
        editRow=0;
        lc=null;
    }        
    
}