/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tienda.model.Producto;
import tienda.qualifiers.DAOJdbc;

/**
 *
 * @author c3p0
 */
@Dependent
@DAOJdbc 
public class ProductoDAOJDBC implements ProductoDAO, Serializable{
    
    private static final String dbTable="Productos";
    private static final String[] autoField={"id"}; //Autogenerated field for new records
    //private static final String connPoolName="java:comp/env/jdbc/tiendaOnline";  //Tomcat
    private static String connPoolName="jdbc/tiendaOnline";               //Glassfish
    private static final String SQL_BUSCAID="SELECT * FROM Productos where id=?";
    private static final String SQL_BUSCATODOS="SELECT * FROM Productos";
    private static final String SQL_CREA="INSERT INTO Productos (nombre,descripcion,valoracion,familia,precio,imagen) VALUES (?,?,?,?,?,?)";
    private static final String SQL_ACTUALIZA="UPDATE Productos set NOMBRE=?, DESCRIPCION=?, VALORACION=?, FAMILIA=?, PRECIO=?, IMAGEN=? WHERE id=?";
    private static final String SQL_BORRA="DELETE FROM Productos WHERE id=?";
    private static final String SQL_MAYORPRECIO="select * from PRODUCTOS ORDER BY precio DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY";
    private static final String SQL_FAMILIA="select * from PRODUCTOS WHERE familia=? ORDER BY precio DESC OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY";


     @Resource(lookup = "jdbc/tiendaOnline")
     private DataSource ds;
    
    public ProductoDAOJDBC() {
    }
    
    /**Recupera un Cliente del registro actual del RS (MAPPING)*/
    private static Producto clienteMapper(ResultSet rs) throws SQLException {
        Producto c;
        c=new Producto( rs.getInt("id"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getInt("VALORACION"),
                        rs.getInt("FAMILIA"),
                        rs.getFloat("PRECIO"),
                        rs.getString("IMAGEN"));
        return c;
    }  
   
    @Override
    public Producto buscaId(Integer id) {
        Producto c=null;
        try (Connection conn=ds.getConnection();
             PreparedStatement stmn=conn.prepareStatement(SQL_BUSCAID)) {
            stmn.setInt(1,id);
            try( ResultSet rs=stmn.executeQuery()) {
                rs.next();
                c=clienteMapper(rs);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return c;
    }

    @Override
    public List<Producto> buscaTodos() {
        List<Producto> l=new ArrayList<>();
        try (Connection conn=ds.getConnection();
            Statement stmn=conn.createStatement();

            ResultSet rs=stmn.executeQuery(SQL_BUSCATODOS);
        ){
            while (rs.next()) {
                l.add(clienteMapper(rs));
            }                
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }
    
    @Override
    public List<Producto> buscaMayorPrecio() {
        List<Producto> l=new ArrayList<>();
        try (Connection conn=ds.getConnection();
            Statement stmn=conn.createStatement();
            ResultSet rs=stmn.executeQuery(SQL_MAYORPRECIO);
        ){
            while (rs.next()) {
                l.add(clienteMapper(rs));
            }                
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }
    
    @Override
    public List<Producto> buscaFamilia(Integer f) {
        List<Producto> l=new ArrayList<>();
        try (Connection conn=ds.getConnection();
            PreparedStatement stmn=conn.prepareStatement(SQL_FAMILIA)){
            stmn.setInt(1,(Integer)f);
                 

            try( ResultSet rs=stmn.executeQuery()) {
                while(rs.next()){
                    l.add(clienteMapper(rs));
                }               
            }
        }catch (SQLException ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return l;
    }
              

    @Override
    public boolean crea(Producto c) {
        boolean result=false;
        try (Connection conn=ds.getConnection();
            PreparedStatement stmn=conn.prepareStatement(SQL_CREA,autoField);
        ){
            stmn.setString(1,c.getNombre());
            stmn.setString(2,c.getDescripcion());
            stmn.setInt(3, (Integer) c.getValoracion());
            stmn.setInt(4, (Integer) c.getFamilia());
            stmn.setFloat(5, (float) c.getPrecio());
            stmn.setString(6, c.getImagen());
            stmn.executeUpdate();
            try (ResultSet rs=stmn.getGeneratedKeys()) {
                //Get autogenerated field value
                if (rs!=null && rs.next()) {
                    int nuevoId=rs.getInt(1); //RS has only one field with key value
                    c.setId(nuevoId);
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public boolean guarda(Producto c) {
        boolean result=false;
        try (Connection conn=ds.getConnection();
            PreparedStatement stmn=conn.prepareStatement(SQL_ACTUALIZA);
        ){
            
            stmn.setString(1,c.getNombre());
            stmn.setString(2,c.getDescripcion());
            stmn.setInt(3,c.getValoracion());
            stmn.setInt(4,c.getFamilia());
            stmn.setFloat(5, c.getPrecio());
            stmn.setString(6, c.getImagen());
            stmn.setInt(7, c.getId());
            result=(stmn.executeUpdate()==1);
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
    }
    @Override    
    public boolean borra(Integer id) {
        boolean result=false;
        try (Connection conn=ds.getConnection();
            PreparedStatement stmn=conn.prepareStatement(SQL_BORRA);
        ){
            stmn.setInt(1,id);
            result=(stmn.executeUpdate()==1);
        } catch (Exception ex) {
            Logger.getLogger(ProductoDAOJDBC.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }         
        return result;
    }
    
}
