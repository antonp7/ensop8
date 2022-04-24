package proyecto;
import java.util.HashMap;

public class gestorAlarmas implements gestorAlarmasInt {
    private HashMap<Integer, Alarma> alarmas;
    private HashMap<Integer, Protocolo> protocolos;
    private gestorUsuariosInt gU;
    private gestorEquiposInt gE;
    private gestorEstadisticasInt gS;
    
    public gestorAlarmas(gestorEstadisticasInt gS){
        this.alarmas = new HashMap<>();
        this.protocolos = new HashMap<>();
        this.gS = gS;
    }

    public gestorUsuariosInt getgU() {
		return gU;
	}

	public void setgU(gestorUsuariosInt gU) {
		this.gU = gU;
	}

	public gestorEquiposInt getgE() {
		return gE;
	}
	
	public void setgE(gestorEquiposInt gE) {
		this.gE = gE;
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
    @Override
    public int recibirAlarma(Alarma alarma){
    	if(!this.getAlarmas().containsKey(alarma.getId())) {
    		this.getAlarmas().put(alarma.getId(), alarma);
    		determinarProtocolo(alarma);
    		String mensaje = "";
    		for(Protocolo p : this.getProtocolos().values()) {
    			mensaje = p.getAccion();
    			enviarProtocolo(p);
    			
    		}
    		enviarSirena(mensaje);
    		
    		
    		return 1;
    		
    	}
    	return 0;
    	
    }
    
    @Override
    public int crearProtocolo(String tipoAlarma, String localizacion, String rol, String accion) {
    	int id = 0;
    	if((Integer)id == null || id < 0 || tipoAlarma == null || tipoAlarma == "" || rol == null || rol == "" || accion ==null || accion == "") {
    		return 0;
    	}

    	if(this.getProtocolos().size()== 0) {
    		id = 1;
    	}
    	else {
    		id = this.getProtocolos().size() +1;
    	}
    	Protocolo prot = new Protocolo(id, tipoAlarma, localizacion);
    	prot.setRol(rol);
    	prot.setAccion(accion);
    	if(!this.getProtocolos().containsKey(prot.getId())) {
    		this.getProtocolos().put(prot.getId(), prot);
    		return 1;
    	}
    	return 0;
    }
    

    public int determinarProtocolo(Alarma alarma) {
    	int valor = 0;
    	if(alarma == null) {
    		valor = 0;
    	}
    	else {
	    	for(Protocolo p : this.getProtocolos().values()) {
	    		switch(alarma.getTipo()) {
		    		case "Incendio":
		    			p.setAccion("Dejar todo lo que se esté haciendo en ese momento y salir al exterior del edificio por la puerta de emergencia");
		    			p.setVerifica(true);
		    			valor = 1;
		    		case "Terremoto":
		    			p.setAccion("Esconderse debajo de una mesa si es posible o salir por la puerta de emergencia más cercana");
		    			p.setVerifica(true);
		    			valor = 1;
		    		case "Meteorito":
		    			p.setAccion("Refugiarse en un lugar subterráneo lo más rápido posible");
		    			p.setVerifica(true);
		    			valor = 1;
		    		case "Volcan":
		    			p.setAccion("Salir del edificio con la mayor rapidez posible y refugiarse en un lugar lejano");
		    			valor = 1;
		    		case "Alerta de bomba":
		    			p.setAccion("Salir del edificio, irse para casa y no salir salvo por causas muy específicas");
		    			p.setVerifica(true);
		    			valor = 1;
		    		case "Tsunami":
		    			p.setAccion("Sal del edificio lo antes posible y aléjate de la costa a un lugar muy lejano de la costa");
		    			p.setVerifica(true);
		    			valor = 1;
		    		default:
		    			valor = 0;
	    		} 
	    	}    		
    	}

    	return valor;    	
    }
    

    public int enviarInfoAlarma(HashMap<Integer, Alarma> alarmasResueltas) {
    	for(Integer idAlarma : this.getAlarmas().keySet()) {
    		Alarma a = this.getAlarmas().get(idAlarma);
    		if(a.getEstado() == 0 && (a.getFechaCierre()!=null || a.getFechaCierre() != "")) {
    			if(gS.recibirInfoAlarmas(alarmasResueltas)== 1) {
    				return 1;
    			}
    		}
    	}
    	return 0;
    	
    }
    
    public int enviarSirena(String notificacion) {
    	if(notificacion == null || notificacion == "") {
    		return 0;
    	}
    	else {
    		for(Alarma a : this.getAlarmas().values()) {
    			gU.declararAlarma(a);
    		}
    		return 1;
    	}
    }
    
    
    public int enviarProtocolo(Protocolo protocolo) {
    	if(protocolo == null) {
    		return 0;
    	}
    	else {
    		gE.consultarProtocolo(protocolo);
    		return 1;
    	}
    }

    public void prueba() {
    	
    }
    
    
    
}