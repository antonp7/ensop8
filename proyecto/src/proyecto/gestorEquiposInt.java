package proyecto;

import java.util.HashMap;

public interface gestorEquiposInt {

    //public gestorEquipos();

    public HashMap<Integer, HashMap<Integer, Usuario>> getEquipos();
	public void setEquipos(HashMap<Integer, HashMap<Integer, Usuario>> equipos);
    
    public int recibirEstado(int usuario, boolean estado, HashMap<Integer, Usuario> usuarios);
    
    public int editarEquipos(int equipo, HashMap<Integer, Usuario> miembros);
    
    public void notificarInfoEquipos() ;
    
    public int recibirGestion(int e, HashMap<Integer, Usuario> equipo);
    
    public int determinarProtocolo(Protocolo p);
    
    public void notificarInfoAccion(String accion);
    
    public void consutarProtocolo(Protocolo p);
    
    public void determinarAccion(String accion, Protocolo p);
}
