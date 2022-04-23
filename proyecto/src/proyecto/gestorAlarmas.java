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
    
    public void recibirAlarma(Alarma alarma){
    	
    }
    
    public void crearProtocolo(int id, String tipoAlarma, String localizacion, String rol, String accion) {
    	if((Integer)id == null || id < 0) {
    		System.out.println("El id no puede ser nulo o menor que 0");
    	}
    	if(tipoAlarma == null || tipoAlarma == "") {
    		System.out.println("El atributo tipoAlarma no puede ser nulo");
    	}
    	if(localizacion == null || localizacion == "") {
    		System.out.println("El atributo localizacion no puede ser nulo");
    	}
    	if(rol == null || rol == "") {
    		System.out.println("El atributo rol no puede ser nulo");
    	}
    	if(accion == null || accion == "") {
    		System.out.println("El atributo accion no puede ser nulo");
    	}
    	Protocolo prot = new Protocolo(id, tipoAlarma, localizacion);
    	prot.setRol(rol);
    	prot.setAccion(accion);
    	if(!this.getProtocolos().containsKey(prot.getId())) {
    		this.getProtocolos().put(prot.getId(), prot);
    	}
    }
    
    
    
}