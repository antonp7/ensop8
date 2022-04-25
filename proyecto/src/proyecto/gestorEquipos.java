package proyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;


public class gestorEquipos implements gestorEquiposInt{
    private HashMap<Integer, HashMap<Integer, Usuario>> equipos;
    private gestorUsuariosInt gU;
    private gestorEstadisticasInt gS;
    
    public gestorEquipos(gestorEstadisticasInt gS){
        this.equipos = new HashMap<>();
        this.gS = gS;
    }
    
	public void setgU(gestorUsuariosInt gU) {
		this.gU = gU;
	}
	
	public HashMap<Integer, HashMap<Integer, Usuario>> getEquipos() {
        return equipos;
    }

    public void setEquipos(HashMap<Integer, HashMap<Integer, Usuario>> equipos) {
        this.equipos = equipos;
    }
    
    public boolean recibirEstado(int usuario) {
    	boolean result = false;
    	Scanner s=new Scanner(System.in);
		String estado="";
		System.out.println("Esta disponible el usuario " + usuario + "?"+"[S/N]");
		estado=s.nextLine();
		
		
		while(!estado.equals("S") && !estado.equals("N")){
			System.out.println("Repita el estado, el formato no era correcto.");
			estado=s.nextLine();
		}
		if(estado.equals("S")) {
			result = true;
		}
		else{
			result = false;
		}
		
		return result;
    }
    
    public int editarEquipos(int equipo, HashMap<Integer, Usuario> miembros) {
    	
    	for(Usuario u: miembros.values()) 
    		if(!u.isEstado())
    			return 0;
    	if(this.equipos.replace(equipo, miembros) == null)
    		return 0;
    	return 1;
    }
    
    public HashMap<Integer, HashMap<Integer, Boolean>> notificarInfoEquipos() {
    	HashMap<Integer, HashMap<Integer, Boolean>> disponibilidad=new HashMap<>();
    	for(Integer eq: this.equipos.keySet()) {
    		HashMap<Integer, Boolean> equipo=new HashMap<>();
    		for(Usuario u: this.equipos.get(eq).values())
    			equipo.put(u.getId(), u.isEstado());
    		disponibilidad.put(eq, equipo);
    	}
		return disponibilidad;
    }
    
    public int recibirGestion(int e, HashMap<Integer, Usuario> equipo) {
    	int result = 0;
        ArrayList<Boolean> dispo = new ArrayList<>();
        HashMap<Integer,Boolean> d = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Boolean>> d2 = new HashMap<>();

        //Comprobamos que los usuarios están disponibles
        for(Usuario u : equipo.values()) {
	    	boolean aux = recibirEstado(u.getId());
	    	u.setEstado(aux);
	        dispo.add(aux);
        }
        for(Usuario u : equipo.values()) {
            u.setEquipo(e);
        }
        equipos.put(e, equipo);
        
        for(Integer i : equipos.keySet()) {
        	HashMap<Integer, Usuario> h = equipos.get(i);
        	for(Integer id : h.keySet()) {
        		d.put(id, h.get(id).isEstado());        		
        	}
        	
        }
        d2.put(e, d);
        gS.recibirInfoEquipos(d2);
        
        return result;
    }
    
	private Date convertirFecha(String f) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date d=null;
		try {
			d= formato.parse(f);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
    public int determinarProtocolo(Protocolo p, Alarma a) {
    	int result = 0;
    	//Recorremos todos los equipos para ver quienes pueden aplicar el protocolo
    	for(HashMap<Integer, Usuario> e : equipos.values()) {
    		for(Usuario u : e.values()) {
    			//Vemos si el protocolo es aplicable al equipo
    			if(p.getLocalizacion().equals(u.getZona())) {
    				result = 1;
    				//Informamos al equipo
    				HashMap<Integer, String> acciones = new HashMap<>();
    				acciones.put(p.getIdXAccion(),p.getAccion());
    				gU.recibirAccionesUsuario(acciones);
    				//Salimos del bucle
    				break;
    			}
    		}
    	}
    	//Una vez informados, enviamos las estadisticas
    	Date inicio = convertirFecha(a.getFecha());
    	Date fin = convertirFecha(a.getFechaCierre());
    	notificarInfoAccion(p.getIdXAccion(), (float)fin.getTime()-inicio.getTime());
    	
    	return result;
    }
    
    //Opcion 1 (El HashMap no se suministra previamente)
    public void notificarInfoAccion(Integer accion, Float tiempo) {
    	//Creamos un hashmap que gestione nuestros datos
    	HashMap<Integer, Float> i = new HashMap<>();
    	//Añadimos nuestros datos como una nueva entrada
    	i.put(accion, tiempo);
    	//Enviamos los datos
    	gS.recibirInfoAccion(i);
    }
    
    public void consultarProtocolo(Protocolo p) {
    	//Devolvemos mediante un String que debe hacer el equipo en este caso
    	System.out.println("Se deben tomar medidas sobre la siguiente alarma: ");
    	System.out.println("Tipo: " + p.getTipoAlarma());
    	System.out.println("Localización: " + p.getLocalizacion());
    	System.out.println("Acción: " + p.getAccion());
    }
    
    public void determinarAccion(String accion, Protocolo p) {
    	p.setAccion(accion);
    }
}
