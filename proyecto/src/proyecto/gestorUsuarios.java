package proyecto;
import java.util.HashMap;


public class gestorUsuarios implements gestorUsuariosInt{
    private HashMap<Integer, Usuario> usuarios;
    
    public gestorUsuarios(){
        this.usuarios = new HashMap<>();
    }

	@Override
	public int registrarUsuario(Usuario usuario) {
		int result = 0;
		
		if(usuario != null) {
			if(!usuarios.containsKey(usuario.getId())) {
				usuarios.put(usuario.getId(), usuario);
				result = 1;
			}
		}else {
			result = -1;
		}
		
		return result;
	}

	@Override
	public int darBajaUsuario(String dni) {
		int result = 0;
		
		if(usuarios.isEmpty()) {
			result = -1;
		}else {
			for(Usuario u: usuarios.values()) {
				if(u.getDni().equals(dni)) {
					usuarios.remove(u.getId());
					result = 1;
				}
			}
		}
		
		return result;
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
		int result = 0;
				
		if(usuario != null) {
			if(usuarios.containsKey(usuario.getId())) {
				usuarios.put(usuario.getId(), usuario);
				result = 1;
			}
		}else {
			result = -1;
		}
		
		return result;
	}
    
    
    
}
