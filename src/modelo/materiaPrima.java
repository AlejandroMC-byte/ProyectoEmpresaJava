package modelo;
/**
 *
 * @author Alejo
 */
public class materiaPrima {
    private String nombre;
    private int cantidad;
    
    private materiaPrima(){
        
    }
    public materiaPrima(String nombre,int cantidad){
        this.nombre=nombre;
        this.cantidad=cantidad;
    }

    
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad += (cantidad);
    }
}
