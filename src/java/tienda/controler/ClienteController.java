package tienda.controler;

import java.awt.event.ActionEvent;
import java.io.IOException;
import tienda.model.Cliente;
import tienda.model.dao.ClienteDAO;
import tienda.qualifiers.DAOJdbc;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

@Named(value = "clienteCtrl")
@ViewScoped
public class ClienteController implements Serializable {

    private static final long serialVersionUID = 1L;

    //Business logic
    @Inject
    @DAOJdbc
    private ClienteDAO clienteDAO;

    //Model
    private Cliente c;
    private List<Cliente> lc;
    private String editRow = "";      //current client editable

    public ClienteController() {
        //DAO not injected yet
        // facesContext=FacesContext.getCurrentInstance();

    }

    @PostConstruct
    public void init() {
        //Usefull if DAO injection needed
        c = new Cliente();
        lc = null;
    }

    //Model access
    public List<Cliente> getClientes() {
        if (lc == null) {
            lc = clienteDAO.buscaTodos();
        }
        return lc;
    }

    public void setClientes(List<Cliente> lc) {
        this.lc = lc;
    }

    public Cliente getCliente() {
        return c;
    }

    public void setCliente(Cliente c) {
        this.c = c;
    }

    //ACTIONS for visualiza, crea, edit and borra views
    /**
     * Get client from id param
     */
    public void recupera() {
        Cliente clienteActual;
        clienteActual = clienteDAO.buscaId(c.getNick());
        if (clienteActual != null) {
            c = clienteActual;
            setCliente(clienteActual);
        } else {
            c = new Cliente();
        }
    }

    public void identifica() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            System.err.println("===HAY SESION===");
            ExternalContext externalContext = facesContext.getExternalContext();
            if (externalContext.getUserPrincipal() == null) {
                System.err.println("===NO ESTA AUTENTIFICADO===");
            } else {
                System.err.println("===ESTA AUTENTIFICADO===");
                String nick = externalContext.getUserPrincipal().getName();
                System.err.println("===HOLA SOY " + nick + "===");
                c = clienteDAO.buscaId(nick);
            }
        }
    }

    /**
     *
     * @param nombre
     * @param clave
     * @return
     */
    public String login(String nombre, String clave) {
        Cliente clienteActual;
        clienteActual = clienteDAO.logIn(nombre, clave);
        if (clienteActual != null) {
            c = clienteActual;
            return "/index";
        }
        return "index";
    }
    
    public boolean isAdmin(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            ExternalContext externalContext = facesContext.getExternalContext();
            if (externalContext.isUserInRole("admin")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isUser(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            ExternalContext externalContext = facesContext.getExternalContext();
            if (externalContext.isUserInRole("cliente")) {
                return true;
            }
        }
        return false; 
    }
    public boolean isLoged(){
        return (isUser() || isAdmin());
    }
    
    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate(); //Cierre de sesion
        }
        return "/index.xhtml";// indicas a donde quieres direccionar después de cerrar sesión 
    }

    /**
     * Create a new Client from model data
     */
    public String crea() {
        c.setNick("");
        clienteDAO.crea(c);
        //Post-Redirect-Get
        return "visualiza?faces-redirect=true&id=" + c.getNick();
    }

    public String alta() {
        c.setNick("");
        clienteDAO.crea(c);
        //Post-Redirect-Get
        return "/index";
    }

    /**
     * Update current model client to DAO
     */
    public String guarda() {
//      Programatic validation
//        if (valida(c.getDni())==false) {
//            FacesContext fc=FacesContext.getCurrentInstance();
//            fc.addMessage("formCliente:idDNI", new FacesMessage("La letra no coincide con el DNI"));
//            return ""; //Stay on view to correct error
//        } 
        clienteDAO.guarda(c);
        return "visualiza?faces-redirect=true&id=" + c.getNick();
    }

    /**
     * Delete current model data client
     */
    public String borra() {
        clienteDAO.borra(c.getNick());
        return "listado_din";
    }

    //ACTIONS for listado.xhtml view
    public String borra(Cliente cliente) {
        clienteDAO.borra(cliente.getNick());
        return "listado_din";
    }

    //ACTIONS for listado_din.xhtml view
    /**
     * Check if current row has edit mode enabled
     */
    public boolean isEditable(String row) {
        return editRow == row;
    }

    /**
     * Enable row edit model
     */
    public void setEditRow(String row) {
        this.editRow = row;
    }

    /**
     * Update current row from table to DAO
     */
    public void guarda(Cliente cliente) {

        clienteDAO.guarda(cliente);
        editRow = "";
        lc = null;
    }

    public void subirImagen(Part file) throws IOException {
        String img = file.getName();
        FileUpload fichero = new FileUpload();
        if (fichero.upload(file)) {
            c.setImagen(fichero.getNombre());
            clienteDAO.guarda(c);
        } else {
           // FacesMessage message = new FacesMessage("Tipo de imagen inválido");
            //throw new ValidatorException(message);
        }
    }

//        public void validaImg(FacesContext context, UIComponent inputDni,Object value) {
//        String img=(String)value;
//        if (!img.matches("\"/(\\.|\\/)(gif|jpe?g|png)$/\"")) {
//            FacesMessage message = new FacesMessage("Tipo de imagen inválido");
//            throw new ValidatorException(message);               
//        }        
//    }
    //VALIDADORES Faces. Using Bean Validation instead
//    public void validaNombre(FacesContext context, UIComponent inputNombre,
//                                Object value) {
//        String nombre=(String)value;
//        if (nombre.length()<4 || nombre.length()>25) {
//            FacesMessage message = new FacesMessage("La longitud del nombre debe estar entre 4 y 25");
//            throw new ValidatorException(message);        
//        
//        }
//    }
//
//    public void validaDni(FacesContext context, UIComponent inputDni,
//                                Object value) {
//        String dni=(String)value;
//        if (!dni.matches("\\d{7,8}-?[a-zA-Z]")) {
//            FacesMessage message = new FacesMessage("El dni no tiene el formato 12345678-A");
//            throw new ValidatorException(message);        
//        
//        }        
//    }
}
