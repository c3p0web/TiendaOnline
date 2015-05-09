/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.model;


/**
 *
 * @author c3p0
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int valoracion;
    private float precio;
    private int familia;
    private String imagen;



    public Producto () {
        nombre="Desconocido";
        descripcion="Sin descripcion";
        valoracion=0;
        familia=1;
        precio=0;
        imagen="pdefecto.png";
    }
    
    public Producto(int _id, String _nombre, String _descripcion, int _valoracion, int _familia, float _precio, String _imagen) {
        this.id=_id;
        this.nombre=_nombre;
        this.descripcion=_descripcion;
        this.valoracion=_valoracion;
        this.familia=_familia;
        this.precio=_precio;
        this.imagen=_imagen;
    }

    /**Copy constructor*/
    public Producto(Producto c) {
        this.id=c.id;
        this.nombre=c.nombre;
        this.descripcion=c.descripcion;
        this.valoracion=c.valoracion;
        this.familia=c.familia;
        this.precio=c.precio;
        this.imagen=c.imagen;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int _id) {
        this.id = _id;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String _descripcion) {
        this.descripcion = _descripcion;
    }

    /**
     * @return the socio
     */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * @param socio the socio to set
     */
    public void setValoracion(int _valoracion) {
        this.valoracion = _valoracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getFamilia() {
        return familia;
    }

    public void setFamilia(int familia) {
        this.familia = familia;
    }
    
    
    
}
