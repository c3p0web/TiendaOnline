package tienda.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Cliente {

    private String nick;
    
    //@Pattern(regexp = "[a-zA-Z\\s]{3,25}", message = "Nombre")
    @Size(min = 4, max = 25, message = "El nombre debe tener almenos 4 carácteres")
    private String nombre;
    
    @Pattern(regexp = "\\d{7,8}(-?[a-zA-Z])?", message = "DNI no válido (12345678A)")
    private String dni;
    
    //@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "Email erróneo")
    @Pattern(regexp = "^[a-zA-Z0-9+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email erróneo")
    private String correo;
    
    @Pattern(regexp = "[a-zA-Z0-9\\\\s]{5,10}", message = "Contraseña inválida, almenos 5 carácteres")
    private String clave;

    public Cliente() {
        nick = "";
        nombre = "";
        dni="";
        correo = "";
        clave = "";
    }

    public Cliente(String nick, String nombre, String dni, String correo, String clave) {
        this.nick = nick;
        this.nombre = nombre;
        this.dni = dni;
        this.correo = correo;
        this.clave = clave;
    }

    /**
     * Copy constructor
     */
    public Cliente(Cliente c) {
        this.nick = c.nick;
        this.nombre = c.nombre;
        this.dni = c.dni;
        this.correo = c.correo;
        this.clave = c.clave;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    


}
