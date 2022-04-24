package proyecto;

import java.util.HashMap;

public interface gestorUsuariosInt {
    
	/* Método que introduce un usuario en el sistema.
	 * Devolverá 1 cuando se introduzca correctamente,
	 * Devolverá -1 si se le introduce como parámetro null,
	 * Devolverá 0 si el usuario ya está registrado en el sistema.*/
	int registrarUsuario(Usuario usuario);
	
	/* Método que elimina un usuario del sistema.
	 * Devolverá 1 cuando se elimine correctamente,
	 * Devolverá -1 si el sistema no contiene ningún usuario,
	 * Devolverá 0 si el usuario no existe en el sistema.*/
	int darBajaUsuario(String dni);
	
	/* Método que introduce una alarma en el sistema.
	 * Devolverá 1 cuando se introduzca correctamente,
	 * Devolverá -1 si se introduce null como parámetro,
	 * Devolverá 0 si la alarma ya ha sido introducida anteriormente.*/
	int declararAlarma(Alarma alarma);
	
	/* Método que*/
	int recibirAccionesUsuario(HashMap<Integer, String> acciones);
	
	/* Método que modifica un usuario del sistema.
	 * Devolverá 1 cuando se modifique correctamente,
	 * Devolverá -1 si se le introduce null como parámetro,
	 * Devolverá 0 si el usuario no existe en el sistema.*/
	int modificarUsuario(Usuario usuario);
	
}
