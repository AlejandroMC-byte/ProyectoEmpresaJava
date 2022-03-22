package modelo;
/**
 *
 * @author Alejo
 */
public class comercializacionProductos {
    private String nombreProducto;
    private int cantidadVenta;
    
    public comercializacionProductos(){
    }
    public comercializacionProductos(String nombreProducto, int cantidadVenta){
        this.nombreProducto=nombreProducto;
        this.cantidadVenta=cantidadVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    
}
