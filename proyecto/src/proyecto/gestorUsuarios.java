package proyecto;
import java.util.HashMap;
import java.util.Iterator;


public class gestorUsuarios implements gestorUsuariosInt{
    private HashMap<Integer, Usuario> usuarios;
    private gestorAlarmasInt ga;
    
    public gestorUsuarios(gestorAlarmasInt ga){
        this.usuarios = new HashMap<>();
        this.ga = ga;
    }

	@Override
	public int registrarUsuario(Usuario usuario) {
		int result = 0;
		
		//Si se introdujo un usuario
		if(usuario != null) {
			//Si el usuario introducido no existe en el sistema
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
		
		//Si usuarios no está vacío
		if(usuarios.isEmpty()) {
			result = -1;
		}else {
			//Se busca el usuario a eliminar por su dni
			Iterator<Usuario> u = usuarios.values().iterator();
			Usuario a;
			while(u.hasNext()) {
				a = u.next();
				if(a.getDni().equals(dni)) {
					usuarios.remove(a.getId());
				}
			}
		}
		
		return result;
	}

	@Override
	public int declararAlarma(Alarma alarma) {
		int result = 0;
		
		//Si se introdujo la alarma
		if(alarma != null) {
			//Si se envía la alarma correctamente al gestor de alarmas
			if(ga.recibirAlarma(alarma) == 1) {
				result = 1;
			}
		}else {
			result = -1;
		}
		
		return result;
	}

	@Override
	public int recibirAccionesUsuario(HashMap<Integer, String> acciones) {
		int result = 0;
		
		//Si se introducen acciones correctamente
		if(acciones != null) {
			if(!acciones.isEmpty()) {
				//Se recorren las acciones introducidas
				for(Integer i: acciones.keySet()) {
					//Se envía a cada usuario su acción a realizar
					result = 1;
				}
			}else {
				result = -1;
			}
		}else {
			result = -1;
		}
		
		return result;
	}

	@Override
	public int modificarUsuario(Usuario usuario) {
		int result = 0;
		
		//Si se introdujo un usuario
		if(usuario != null) {
			//Si el usuario existe en el sistema
			if(usuarios.containsKey(usuario.getId())) {
				//Se modifica el usuario
				usuarios.put(usuario.getId(), usuario);
				result = 1;
			}
		}else {
			result = -1;
		}
		
		return result;
	}
    
    
    
}
