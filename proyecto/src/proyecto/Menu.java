package proyecto;

import java.util.HashMap;

public class Menu {

	public static void main(String[] args) {
		gestorEstadisticasInt gS = new gestorEstadisticas();
		gestorEquiposInt gE = new gestorEquipos(gS);
		gestorAlarmasInt gA = new gestorAlarmas(gS, gE);
		gestorUsuariosInt gU = new gestorUsuarios(gA);
		
		gA.crearProtocolo("incendio", "ETSE", "normal", "Salir del edificio");
		Alarma a = new Alarma(1, "ETSE", "21/04/2022", 1);
		gA.recibirAlarma(a);
		Protocolo p = new Protocolo(2, "Incendio", "ETSE");
		gE.determinarAccion("Accion 1", p);
		gE.consultarProtocolo(p);
		Usuario us1 = new Usuario(3, "Pepe", "12345789S");
		Usuario us2 = new Usuario(4, "Carlos", "987654321A");
		gU.registrarUsuario(us1);
		gU.registrarUsuario(us2);
		gU.declararAlarma(a);
		Usuario us3 = new Usuario(4, "Carlos", "9348293N");
		gU.modificarUsuario(us3);
		
		HashMap<Integer, Usuario> hashUsuarios = new HashMap<>();
		hashUsuarios.put(us1.getId(), us1);
		hashUsuarios.put(us2.getId(), us2);
		HashMap<Integer, HashMap<Integer, Usuario>> hashUsuariosgrande = new HashMap<>();
		hashUsuariosgrande.put(1, hashUsuarios);
		gE.setEquipos(hashUsuariosgrande);
	}

}
