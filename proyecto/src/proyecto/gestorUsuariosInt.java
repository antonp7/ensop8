package proyecto;

import java.util.HashMap;

public interface gestorUsuariosInt {
    
	/* M�todo que introduce un usuario en el sistema.
	 * Devolver� 1 cuando se introduzca correctamente,
	 * Devolver� -1 si se le introduce como par�metro null,
	 * Devolver� 0 si el usuario ya est� registrado en el sistema.*/
	int registrarUsuario(Usuario usuario);
	
	/* M�todo que elimina un usuario del sistema.
	 * Devolver� 1 cuando se elimine correctamente,
	 * Devolver� -1 si el sistema no contiene ning�n usuario,
	 * Devolver� 0 si el usuario no existe en el sistema.*/
	int darBajaUsuario(String dni);
	
	/* M�todo que introduce una alarma en el sistema.
	 * Devolver� 1 cuando se introduzca correctamente,
	 * Devolver� -1 si se introduce null como par�metro,
	 * Devolver� 0 si la alarma ya ha sido introducida anteriormente.*/
	int declararAlarma(Alarma alarma);
	
	/* M�todo que*/
	int recibirAccionesUsuario(HashMap<Integer, String> acciones);
	
	/* M�todo que modifica un usuario del sistema.
	 * Devolver� 1 cuando se modifique correctamente,
	 * Devolver� -1 si se le introduce null como par�metro,
	 * Devolver� 0 si el usuario no existe en el sistema.*/
	int modificarUsuario(Usuario usuario);
	
}
