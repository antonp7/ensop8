package proyecto;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;

public class Menu {

	public static void main(String[] args) throws FileNotFoundException {
		gestorEstadisticasInt gS = new gestorEstadisticas();
		gestorEquiposInt gE = new gestorEquipos(gS);
		gestorAlarmasInt gA = new gestorAlarmas(gS, gE);
		gestorUsuariosInt gU = new gestorUsuarios(gA);
		
		gA.crearProtocolo("incendio", "ETSE", "normal", "Salir del edificio");
		Alarma a = new Alarma(1, "ETSE", "21/04/2022", 1);
		Alarma a2 = new Alarma(2, "ETSE", "24/04/2022", 1);
		Alarma a3 = new Alarma(3, "Casa", "30/04/2022", 1);
		gA.recibirAlarma(a);
		gA.recibirAlarma(a2);
		Protocolo p = new Protocolo(2, "Incendio", "ETSE");
		gE.determinarAccion("Accion 1", p);
		gE.consultarProtocolo(p);
		Usuario us1 = new Usuario(3, "Pepe", "12345789S");
		Usuario us2 = new Usuario(4, "Carlos", "987654321A");
		gU.registrarUsuario(us1);
		gU.registrarUsuario(us2);
		gU.declararAlarma(a);
		Usuario us3 = new Usuario(us2.getId(), us2.getNombre(), "9348293N");
		gU.modificarUsuario(us3);
		
		HashMap<Integer, Usuario> hashUsuarios = new HashMap<>();
		hashUsuarios.put(us1.getId(), us1);
		hashUsuarios.put(us2.getId(), us2);
		HashMap<Integer, HashMap<Integer, Usuario>> hashUsuariosgrande = new HashMap<>();
		hashUsuariosgrande.put(1, hashUsuarios);
		gE.setEquipos(hashUsuariosgrande);
		
		HashMap<Integer, Alarma> hashAlarmas = new HashMap<>();
		a.setFechaCierre("30/04/2022");
		a2.setFechaCierre("30/05/2022");
		a3.setFechaCierre("05/05/2022");
		hashAlarmas.put(a.getId(), a);
		hashAlarmas.put(a2.getId(), a2);
		hashAlarmas.put(a3.getId(), a3);
		gS.recibirInfoAlarmas(hashAlarmas);
		//gS.exponerValores(2);
		gU.darBajaUsuario("9348293N");
		HashMap<Integer,String> acciones = new HashMap<>();
		acciones.put(3, "salir");
		gU.recibirAccionesUsuario(acciones);
		
		
		gE.recibirEstado(4, hashUsuarios);
		for(Integer i : gE.notificarInfoEquipos().keySet()) {
			Collection<HashMap<Integer, Boolean>> notificarCol = gE.notificarInfoEquipos().values();
			System.out.println(i);
			System.out.println(notificarCol);
			
			
		}
		
		
	}

}
