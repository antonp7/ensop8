package proyecto;
import java.util.ArrayList;
import java.util.HashMap;


public class gestorEquipos {
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
    
    
    
}
