package proyecto;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface gestorEstadisticasInt {
	int recibirInfoEquipos(HashMap<Integer, HashMap<Integer, Boolean>> disponibilidadMiembros);
	
	int recibirInfoAccion(HashMap<Integer, Float> i);
	
	int recibirInfoAlarmas(HashMap<Integer, Alarma> i);
	
	void exponerValores(int flag) throws FileNotFoundException;
}
