package proyecto;

import java.util.HashMap;

public interface gestorEstadisticasInt {
	int recibirInfoEquipos(HashMap<Integer, HashMap<Integer, Boolean>> disponibilidadMiembros);
	
	int recibirInfoAccion(HashMap<Integer, Float> i);
	
	int recibirInfoAlarmas(HashMap<Integer, Alarma> i);
}
