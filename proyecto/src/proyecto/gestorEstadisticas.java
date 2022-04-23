package proyecto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class gestorEstadisticas implements gestorEstadisticasInt{
	private int flagEquipo;
	private int flagAlarma;
	private int flagAccion;
	private int equipoOK;
	private int alarmaOK;
	private int accionOK;
	
    public gestorEstadisticas(){
    	this.flagEquipo=1;
        this.flagAccion=1;//
        this.flagAlarma=1;
        this.equipoOK=0;
        this.accionOK=0;
        this.alarmaOK=0;
    }

	@Override
	public int recibirInfoEquipos(HashMap<Integer, HashMap<Integer, Boolean>> disponibilidadMiembros) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int recibirInfoAccion(HashMap<Integer, Float> i) {
		// TODO Auto-generated method stub
		int total=0;
		float media=0f;
		ArrayList<Object> r= new ArrayList<Object>();
		
		try {
			PrintWriter writer= new PrintWriter("./LogAccion_"+String.valueOf(this.flagAccion)+".txt", "UTF-8");
			for(Integer in: i.keySet()) {
				writer.println("Acci�n "+Integer.toString(in)+"");
				writer.println("Tiempo: "+String.valueOf(i.get(in))+"");
				writer.println();
				writer.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.accionOK=0;
			return 0;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.accionOK=0;
			return 0;
		}
		
		total=i.size();
		r.add(total);
		
		for(Float f: i.values()) {
			media+=f;
		}
		media/=total;
		r.add(media);
		
		this.flagAccion++;
		
		this.accionOK=1;
		return 1;
	}

	@Override
	public int recibirInfoAlarmas(HashMap<Integer, Alarma> i) {
		// TODO Auto-generated method stub
		ArrayList<Object> f= new ArrayList<Object>();
		
		try {
			PrintWriter writer= new PrintWriter("./LogAlarma_"+String.valueOf(this.flagAlarma)+".txt", "UTF-8");
			for(Alarma a: i.values()) {
				writer.println("Alarma "+String.valueOf(a.getId())+"");
				writer.println("Fecha inicio: "+a.getFecha()+"");
				writer.println("Fecha fin: "+a.getFechaCierre()+"");
				writer.println("Tipo: "+a.getTipo()+"");
				writer.println("Centro: "+a.getCentro()+"");
				writer.println("Estado "+String.valueOf(a.getEstado())+"");
				writer.println();
				writer.println();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.alarmaOK=0;
			return 0;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.alarmaOK=0;
			return 0;
		}
		
		this.flagAlarma++;
		
		this.alarmaOK=1;
		return 1;
	}
	
	private Date convertirFecha(String f) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date d=null;
		try {
			d= formato.parse(f);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
	}
	
	/*private float mediaAlarmas(HashMap<Integer, Alarma> alarmas) {
		float media=0f;
		long duracionT=0L;
		//21/04/2022
		for(Alarma a: alarmas.values()) {
			Date fechaIn=convertirFecha(a.getFecha());
			Date fechaFin= convertirFecha(a.getFechaCierre());
			
			long diff= fechaFin.getTime()-fechaIn.getTime();
			
			TimeUnit time= TimeUnit.DAYS;
			long diferencia= time.convert(diff, TimeUnit.MILLISECONDS);
			long duracion= diferencia*24;
			duracionT+=duracion;
		}
		
		media=duracionT/alarmas.size();
		
		return media;
	}*/
	
	private HashMap<String, ArrayList<String>> distribucionAlarmas(HashMap<Integer, Alarma> alarmas){
		HashMap<String, ArrayList<String>> result= new HashMap();
		
		for(Alarma a: alarmas.values()) {
			String[] fechaIn= a.getFecha().split("/");
			String mesIn= fechaIn[1];
			String anoIn= fechaIn[2];
			
			if(!result.containsKey(mesIn)) {
				result.put(mesIn, new ArrayList<String>());
				result.get(mesIn).add(String.valueOf(a.getId()));
			}
			
			else {
				result.get(mesIn).add(String.valueOf(a.getId()));
			}
			
			if(!result.containsKey(anoIn)) {
				result.put(anoIn, new ArrayList<String>());
				result.get(anoIn).add(String.valueOf(a.getId()));
			}
			
			else {
				result.get(anoIn).add(String.valueOf(a.getId()));
			}
		}
		
		return result;
	}
	
	public void calcularEstadisticas(int flag) throws FileNotFoundException {
		int total=0;
		float media=0f;
		ArrayList<Object> result= new ArrayList<Object>();
		
		if(flag==0) { //Equipos
			
		}
		
		else if(flag==1) { //Acciones
			if(this.accionOK==1) {
				int i=1;
				for(i=1; i<this.flagAlarma; i++) {
					String nombre="./LogAlarma_"+String.valueOf(i)+".txt";
					File doc= new File(nombre);
					Scanner obj= new Scanner(doc);
					
					while(obj.hasNextLine()) {
						if(obj.nextLine().contains("Accion")) {
							total++;
						}
						
						else if(obj.nextLine().contains("Tiempo: ")) {
							String[] parts= obj.nextLine().split(": ");
							media+=Float.valueOf(parts[1]);
						}
					}
					
					
					
				}
				result.add(total);
				
				media/=total;
				result.add(media);
			}
		}
		
		else if(flag== 2) { //Alarmas
			if(this.alarmaOK==1) {
				int i=1;
				for(i=1; i<this.flagAlarma; i++) {
					int j=0;
					String nombre="./LogAlarma_"+String.valueOf(i)+".txt";
					File doc= new File(nombre);
					Scanner obj= new Scanner(doc);
					ArrayList<String> fechasIn, fechasFin;
					fechasIn= new ArrayList<String>();
					fechasFin= new ArrayList<String>();
					
					while(obj.hasNextLine()) {
						if(obj.nextLine().contains("Alarma")) {
							total++;
						}
						
						else if(obj.nextLine().contains("Fecha inicio: ")) {
							String[] parts= obj.nextLine().split(": ");
							fechasIn.add(parts[1]);
						}
						
						else if(obj.nextLine().contains("Fecha fin: ")) {
							String[] parts= obj.nextLine().split(": ");
							fechasFin.add(parts[1]);
						}
					}
					
					for(j=0; j<fechasIn.size(); j++) {
						String fechaIn=fechasIn.get(j);
						String fechaFin=fechasIn.get(j);
						
						Date dIn=convertirFecha(fechaIn);
						Date dFin= convertirFecha(fechaFin);
						
						long diff= dFin.getTime()-dIn.getTime();
						
						TimeUnit time= TimeUnit.DAYS;
						long diferencia= time.convert(diff, TimeUnit.MILLISECONDS);
						long duracion= diferencia*24;
						media+=duracion;
					}
					
				}
				result.add(total);
				
				media/=total;
				result.add(media);
			}
		}
		
	}
	
	public void exponerValores(int flag) throws FileNotFoundException {
		calcularEstadisticas(flag);
	}
}