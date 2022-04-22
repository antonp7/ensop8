package proyecto;
import java.util.HashMap;


public class gestorUsuarios {
    private HashMap<Integer, Usuario> usuarios;
    
    public gestorUsuarios(){
        this.usuarios = new HashMap<>();
    }

    public HashMap<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<Integer, Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
