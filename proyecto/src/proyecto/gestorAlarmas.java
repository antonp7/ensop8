package proyecto;
import java.util.HashMap;

public class gestorAlarmas {
    private HashMap<Integer, Alarma> alarmas;
    private HashMap<Integer, Protocolo> protocolos;
    
    public gestorAlarmas(){
        this.alarmas = new HashMap<>();
        this.protocolos = new HashMap<>();
    }

    public HashMap<Integer, Alarma> getAlarmas() {
        return alarmas;
    }

    public void setAlarmas(HashMap<Integer, Alarma> alarmas) {
        this.alarmas = alarmas;
    }

    public HashMap<Integer, Protocolo> getProtocolos() {
        return protocolos;
    }

    public void setProtocolos(HashMap<Integer, Protocolo> protocolos) {
        this.protocolos = protocolos;
    }
    
    
}
