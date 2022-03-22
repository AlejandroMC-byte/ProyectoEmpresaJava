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
public class materiaPrimaDAO {
    public materiaPrimaDAO() {
    }
    /**
     * 
     * @param p Objeto de la clase materiaPrima a grabar
     * @return rtdo resultado de la operación grbar
     */
    public int grabarMateriaPrima(materiaPrima p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "INSERT INTO materiaPrima values (?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, p.getNombre());
            pstm.setInt(2, p.getCantidad());
            
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
     * @param p Objeto de la clase materiaPrima a grabar
     * @return rtdo resultado de la operación modificar
     */
    public int modificarMateriaPrima(materiaPrima p){      
        Connection con = null;
        PreparedStatement pstm;
        pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "UPDATE materiaPrima " +
                         "SET nombre = ?, nivel = ?, num_creditos = ? "
                    +    "WHERE codigo=?";
            pstm = con.prepareStatement(sql);            
            pstm.setString(1, p.getNombre());
            pstm.setInt(2, p.getCantidad());
           
            
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
     * @param codigo código del materiaPrima a borrar
     * @return rtdo resultado de la operación borrar
     */
    public int borrarMateriaPrima(String nombre){      
        Connection con = null;
        PreparedStatement pstm = null;
        int rtdo;
        rtdo = 0;
        try{
            con = fachada.getConnection();
            String sql = "DELETE FROM materiaPrima WHERE codigo = ? ";
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
     * @param codigo codigo del materiaPrima a listar, 0 se listaran todos
     * @return ArrayList, lista de objetos materiaPrima
     */
    public ArrayList<materiaPrima> listadomateriaPrima(String nombre){      
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<materiaPrima> listado = new ArrayList<>();
        try{
            con = fachada.getConnection();
            String sql="";
            if(nombre.equalsIgnoreCase("0")){
                sql = "SELECT * FROM materiaPrima ORDER BY codigo";            
            }else{
                sql = "SELECT * FROM materiaPrima where codigo = ? "
                    + "ORDER BY codigo";      
            }                        
            pstm = con.prepareStatement(sql);
            
            if(nombre != "0"){
                pstm.setString(1, nombre);
            }
            
            rs = pstm.executeQuery();
                        
            materiaPrima materiaPrima = null;
            while(rs.next()){
                materiaPrima = new materiaPrima();
                materiaPrima.setNombre(rs.getString("Nombre"));
                materiaPrima.setCantidad(rs.getInt("cantidad"));
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
