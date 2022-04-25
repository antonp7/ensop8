package proyecto;

public interface gestorAlarmasInt {
	int recibirAlarma(Alarma alarma);
	int crearProtocolo(String tipoAlarma, String localizacion, String rol, String accion);
}
