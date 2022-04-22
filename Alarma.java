
public class Alarma {
    private int id;
    private String fecha;
    private String tipo;
    private String centro;
    private int estado;
    private String fechaCierre;   
    
    public Alarma(int id, String centro){
        this.id = id;
        this.centro = centro;  
        this.fecha = "";
        this.tipo = "";
        this.estado = 0;
        this.fechaCierre = "";
    }
    
    public Alarma(int id, String centro, String fecha, int estado){
        this.id = id;
        this.fecha = fecha;
        this.centro = centro;
        this.estado = estado;
        this.tipo = "";
        this.fechaCierre = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
}

