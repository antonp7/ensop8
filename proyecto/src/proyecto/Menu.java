package proyecto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class Menu {

	public static void main(String[] args) throws IOException {
		
		gestorEstadisticasInt gS = new gestorEstadisticas();
		gestorEquiposInt gE = new gestorEquipos(gS);
		gestorAlarmasInt gA = new gestorAlarmas(gS, gE);
		gestorUsuariosInt gU = new gestorUsuarios(gA);
		gE.setgU(gU);
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
		gU.darBajaUsuario("9348293N");
		HashMap<Integer,String> acciones = new HashMap<>();
		acciones.put(3, "salir");
		gU.recibirAccionesUsuario(acciones);
		
		us1.setEstado(true);
		System.out.println("NotificarInfoEquipos():");
		for(Integer i : gE.notificarInfoEquipos().keySet()) {
			Collection<HashMap<Integer, Boolean>> notificarCol = gE.notificarInfoEquipos().values();
			System.out.println(i);
			System.out.println(notificarCol);	
		}
		Protocolo p2 = new Protocolo(3, "Tsunami", "Casa");
		p2.setIdXAccion(2);
		gE.determinarProtocolo(p2, a2);
		gE.consultarProtocolo(p2);
		
		HashMap<Integer, HashMap<Integer, Usuario>> hashEquipos = new HashMap<>();
		hashEquipos.put(1, hashUsuarios);
		gE.setEquipos(hashEquipos);
		HashMap<Integer, Usuario> hashUsuarios2 = new HashMap<>();
		hashUsuarios2.put(us1.getId(), us1);
		System.out.println(gE.editarEquipos(1, hashUsuarios2));
		
		HashMap<Integer, HashMap<Integer, Boolean>> hashgestion = new HashMap<>();
		HashMap<Integer, Boolean> hashBool = new HashMap<>();
		hashBool.put(3, true);
		hashBool.put(4, true);
		hashgestion.put(1, hashBool);
		
		gE.recibirGestion(1, hashUsuarios);
		
		gS.exponerValores(0);
		gS.exponerValores(1);
		gS.exponerValores(2);
		
		
		
	}

}
