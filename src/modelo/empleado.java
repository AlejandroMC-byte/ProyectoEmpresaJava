package modelo;

/**
 *
 * @author Alejo
 */
public class empleado {
    private int codigo;
    private String nombre;
    private int salario;
    private int comision;
    
    public empleado(){
    }
    public empleado(int codigo,String nombre,int salario,int comision){
        this.codigo=codigo;
        this.nombre=nombre;
        
        if(comision !=0){
            this.salario=salario+(salario/(comision*100));
        }else{
            this.salario=salario;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }
}
