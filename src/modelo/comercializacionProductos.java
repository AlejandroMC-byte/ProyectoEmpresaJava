package modelo;
/**
 *
 * @author Alejo
 */
public class comercializacionProductos {
    private String nombreProducto;
    private int cantidadVenta;
    
    private comercializacionProductos(){
    }
    public comercializacionProductos(String nombreProducto, int cantidadVenta){
        this.nombreProducto=nombreProducto;
        this.cantidadVenta=cantidadVenta;
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    
}
