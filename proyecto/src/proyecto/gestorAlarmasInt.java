package proyecto;

import java.util.HashMap;

public interface gestorAlarmasInt {
	public HashMap<Integer, Alarma> getAlarmas();
	public HashMap<Integer, Protocolo> getProtocolos();;
	int recibirAlarma(Alarma alarma);
	int crearProtocolo(String tipoAlarma, String localizacion, String rol, String accion);
    
}
