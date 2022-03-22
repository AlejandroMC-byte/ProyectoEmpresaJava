/*
 * Programa	: productosDAO.java
 * Fecha	: 22/03/2022
 * Objetivo	: Modela el acceso a datos de la tabla Productos
 * Programador	: Alejandro Montero Cardona
 */

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.fachada;

/**
 *
 * @author Alejo
 */
public class productosDAO {
public productosDAO() {
    }
    /**
     * 
     * @param p Objeto de la clase productos a grabar
     * @return rtdo resultado de la operación grbar
     */
    public int grabarProductos(productos p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "INSERT INTO productos values (?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, p.getNombre());
            pstm.setInt(2, p.getCantidad());
            pstm.setInt(3, p.getPrecio());
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    
     /**
     * 
     * @param p Objeto de la clase productos a grabar
     * @return rtdo resultado de la operación modificar
     */
    public int modificarProductos(productos p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "UPDATE productos " +
                         "SET nombre = ?, nivel = ?, num_creditos = ? "
                    +    "WHERE codigo=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, p.getNombre());
            pstm.setInt(2, p.getCantidad());
            pstm.setInt(3,p.getPrecio());
            
            rtdo = pstm.executeUpdate();  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
            
    /**
     * 
     * @param codigo código del productos a borrar
     * @return rtdo resultado de la operación borrar
     */
    public int borrarProductos(String nombre){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "DELETE FROM productos WHERE codigo = ? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, nombre);
            rtdo = pstm.executeUpdate(); 
            return rtdo;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        } 
        finally{
            try{
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return rtdo;
    }
    /**
     * 
     * @param codigo codigo del productos a listar, 0 se listaran todos
     * @return ArrayList, lista de objetos productos
     */
    public ArrayList<productos> listadoProductos(String nombre){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<productos> listado = new ArrayList<>();
        try{
            con = fachada.getConnection();
            String sql="";
            if(nombre.equalsIgnoreCase("0")){
                sql = "SELECT * FROM productos ORDER BY codigo";            
            }else{
                sql = "SELECT * FROM productos where codigo = ? "
                    + "ORDER BY codigo";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(nombre != "0"){
                pstm.setString(1, nombre);
            }
            
            rs = pstm.executeQuery();
                        
            productos productos = null;
            while(rs.next()){
                productos = new productos();
                productos.setNombre(rs.getString("Nombre"));
                productos.setCantidad(rs.getInt("cantidad"));
                productos.setPrecio(rs.getInt("Precio"));
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
        }
        finally{
            try{
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();                
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Código : " + 
                        ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listado;
    }
}
