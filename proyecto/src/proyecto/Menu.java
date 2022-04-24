package proyecto;

public class Menu {

	public static void main(String[] args) {
		gestorEstadisticasInt gS = new gestorEstadisticas();
		gestorEquiposInt gE = new gestorEquipos(gS);
		gestorAlarmasInt gA = new gestorAlarmas(gS, gE);
		gestorUsuariosInt gU = new gestorUsuarios(gA);
		
		
	}

}
