package proyecto;


import java.util.HashMap;

public interface gestorEquiposInt {
    
	//Solo para testear
	void setEquipos(HashMap<Integer, HashMap<Integer, Usuario>> equipos);
    
    boolean recibirEstado(int usuario);
    
    int recibirGestion(int e, HashMap<Integer, Usuario> equipo);
    
    int determinarProtocolo(Protocolo p, Alarma a);
    
    void setgU(gestorUsuariosInt gU);
}
