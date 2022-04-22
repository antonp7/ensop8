package proyecto;
public class Protocolo {
    private int id;
    private String tipoAlarma;
    private String localizacion;
    private int idXAccion;
    private String rol;
    private boolean verifica;
    private String accion;
    
    public Protocolo(int id, String tipoAlarma, String localizacion){
        this.id = id;
        this.tipoAlarma = tipoAlarma;
        this.localizacion = localizacion;
        this.idXAccion = 0;
        this.rol = "";
        this.verifica = false;
        this.accion = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoAlarma() {
        return tipoAlarma;
    }

    public void setTipoAlarma(String tipoAlarma) {
        this.tipoAlarma = tipoAlarma;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getIdXAccion() {
        return idXAccion;
    }

    public void setIdXAccion(int idXAccion) {
        this.idXAccion = idXAccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isVerifica() {
        return verifica;
    }

    public void setVerifica(boolean verifica) {
        this.verifica = verifica;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
}
