package proyecto;
import java.util.ArrayList;
import java.util.HashMap;


public class gestorEquipos implements gestorEquiposInt{
    private HashMap<Integer, HashMap<Integer, Usuario>> equipos;
    
    public gestorEquipos(){
        this.equipos = new HashMap<>();
    }

    public HashMap<Integer, HashMap<Integer, Usuario>> getEquipos() {
        return equipos;
    }

    public void setEquipos(HashMap<Integer, HashMap<Integer, Usuario>> equipos) {
        this.equipos = equipos;
    }
    
    public int recibirEstado(int usuario, boolean estado, HashMap<Integer, Usuario> usuarios) {
    	Usuario u=usuarios.get(usuario);
    	u.setEstado(estado);
    	if(!usuarios.replace(usuario, u))
    		return 0;
    	return 1;
    }
    
    public int editarEquipos(int equipo, HashMap<Integer, Usuario> miembros) {
    	for(Usuario u: miembros.values()) 
    		if(!u.estado)
    			return 0;
    	if(!this.equipos.replace(equipo,equipo))
    		return 0;
    	return 1;
    }
    
    public void notificarInfoEquipos() {
    	bool dis=true;
    	for(Integer eq: this.equipos.keySet()) {
    		dis=true;
    		for(Usuario u: this.equipos.get(eq).getvalues())
    			if(!u.isEstado)
    				dis=null;
    		if(dis)
    			System.out.println("El equipo "+eq+" esta disponible al completo.");
    		else
    			System.out.println("El equipo "+eq+" no esta disponible.");
    	}
    }
    
    public int recibirGestion(int e, HashMap<Integer, Usuario> equipo) {
    	//Comprobamos que los usuarios estén disponibles
    	for(Usuario u : equipo.values()) {
    		if(u.isEstado()) {}
    		else {return 1;}
    	}
    	//Si lo están, comprobamos que el equipo exista
    	if(equipos.get(equipo)!=null) {
    		//Si existe, lo modificamos
    		for(Usuario u : equipo.values()) {
        		u.setEquipo(e);
        	}
    		equipos.replace(e, equipo);
    	}
    	else {
    		//Si no existe, se crea
    		for(Usuario u : equipo.values()) {
        		u.setEquipo(e);
        	}
    		equipos.put(e, equipo);	
    	}
    	return 0;
    }
    
    public int determinarProtocolo(Protocolo p) {
    	Scanner s = new Scanner(System.in);
    	int id;
    	String tAlarma, localizacion;
    	
    	//Pedimos los datos al usuario
    	System.out.println("Introduzca los datos del protocolo que desea añadir:");
    	System.out.println("Introduzca su ID:");
    	id = s.nextInt();
    	System.out.println("Introduzca su tipo:");
    	tAlarma = s.nextLine();
    	System.out.println("Introduzca su localización:");
    	localizacion = s.nextLine();
    	
    	//Generamos el protocolo
    	Protocolo prot = new Protocolo(id, tAlarma, localizacion);
    	//QUE COÑO HAGO CON EL PROTOCOLO ESTE
    	System.out.println("Protocolo generado con éxito!");
    	
    	return 0;
    }
    
    //COMO COJONES SACO DE AQUI UN INT Y UN FLOAT HERMANO????
    public void notificarInfoAccion(String accion) {
    	HashMap<Integer, Float> i;
    	
    	//A DONDE CARALLO MANDO YO AHORA ESTO
    	//recibirInfoAccion(i);
    }
    
    public void consutarProtocolo(Protocolo p) {
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
