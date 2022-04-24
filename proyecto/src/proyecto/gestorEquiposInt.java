package proyecto;


import java.util.HashMap;

public interface gestorEquiposInt {

    HashMap<Integer, HashMap<Integer, Usuario>> getEquipos();
    
	void setEquipos(HashMap<Integer, HashMap<Integer, Usuario>> equipos);
    
    boolean recibirEstado(int usuario);
    
    int editarEquipos(int equipo, HashMap<Integer, Usuario> miembros);
    
    HashMap<Integer, HashMap<Integer, Boolean>> notificarInfoEquipos() ;
    
    int recibirGestion(int e, HashMap<Integer, Usuario> equipo);
    
    int determinarProtocolo(Protocolo p, Alarma a);
    
    void notificarInfoAccion(Integer accion, Float tiempo);
    
    void notificarInfoAccion(HashMap<Integer, Float> i, Integer accion, Float tiempo);
    
    void consultarProtocolo(Protocolo p);
    
    void determinarAccion(String accion, Protocolo p);
    
    void setgU(gestorUsuariosInt gU);
}
