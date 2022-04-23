package proyecto;

import java.util.HashMap;

public interface gestorUsuariosInt {
    
	int registrarUsuario(Usuario usuario);
	int darBajaUsuario(String dni);
	int declararAlarma(Alarma alarma);
	int recibirAccionesUsuario(HashMap<Integer, String> acciones);
	int modificarUsuario(Usuario usuario);
	
}
