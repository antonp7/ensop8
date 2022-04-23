package proyecto;
import java.util.HashMap;


public class gestorUsuarios implements gestorUsuariosInt{
    private HashMap<Integer, Usuario> usuarios;
    
    public gestorUsuarios(){
        this.usuarios = new HashMap<>();
    }

	@Override
	public int registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int darBajaUsuario(String dni) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int declararAlarma(Alarma alarma) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recibirAccionesUsuario(HashMap<Integer, String> acciones) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
    
}
