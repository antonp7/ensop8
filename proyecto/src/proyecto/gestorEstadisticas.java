package proyecto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        this.flagAccion=1;
        this.flagAlarma=1;
        this.equipoOK=0;
        this.accionOK=0;
        this.alarmaOK=0;
    }

	@Override
	public int recibirInfoEquipos(HashMap<Integer, HashMap<Integer, Boolean>> disponibilidadMiembros) {
		// TODO Auto-generated method stub
		try {
			try (PrintWriter writer = new PrintWriter("./LogEquipo_"+String.valueOf(this.flagEquipo)+".txt", "UTF-8")) {
				for(Integer i: disponibilidadMiembros.keySet()) {
					writer.println("\nEquipo "+Integer.toString(i)+"");
					for(Integer in: disponibilidadMiembros.get(i).keySet()) {
						writer.println("Miembro "+Integer.toString(i)+"");
						if(disponibilidadMiembros.get(i).get(in)== true) {
							writer.println("Miembro disponible");
						}
						else {
							writer.println("Miembro no disponible");
						}
						writer.println();
					}
					writer.println();
					writer.println();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.equipoOK=0;
			return 0;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.equipoOK=0;
			return 0;
		}
		
		this.flagEquipo++;
		this.equipoOK=1;
		
		return 1;
	}

	@Override
	public int recibirInfoAccion(HashMap<Integer, Float> i) {
		// TODO Auto-generated method stub
		
		try {
			try (PrintWriter writer = new PrintWriter("./LogAccion_"+String.valueOf(this.flagAccion)+".txt", "UTF-8")) {
				for(Integer in: i.keySet()) {
					writer.println("Accion "+Integer.toString(in)+"");
					writer.println("Tiempo: "+String.valueOf(i.get(in))+"");
					writer.println();
					writer.println();
				}
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
		
		this.flagAccion++;
		this.accionOK=1;
		
		return 1;
	}

	@Override
	public int recibirInfoAlarmas(HashMap<Integer, Alarma> i) {
		// TODO Auto-generated method stub
		
		try {
			try (PrintWriter writer = new PrintWriter("./LogAlarma_"+String.valueOf(this.flagAlarma)+".txt", "UTF-8")) {
				for(Alarma a: i.values()) {
					writer.println("Alarma "+String.valueOf(a.getId())+"");
					writer.println("Fecha inicio: "+a.getFecha()+"");
					writer.println("Fecha fin: "+a.getFechaCierre()+"");
					writer.println("Tipo: "+a.getTipo()+"");
					writer.println("Centro: "+a.getCentro()+"");
					writer.println("Estado: "+String.valueOf(a.getEstado())+"");
					writer.println();
					writer.println();
				}
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
		System.out.println(this.flagAlarma);
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
	
	private HashMap<String, Integer> distribucionAlarmas(HashMap<Integer, String> alarmas){
		HashMap<String, ArrayList<String>> result= new HashMap<String, ArrayList<String>>();
		HashMap<String, Integer> fin= new HashMap<String, Integer>();
		int i=0;
		
		for(String a: alarmas.values()) {
			String[] fechaIn= a.split("/");
			String mesIn= fechaIn[1];
			String anoIn= fechaIn[2];
			
			if(!result.containsKey(mesIn)) {
				result.put(mesIn, new ArrayList<String>());
				result.get(mesIn).add(String.valueOf(i));
				i++;
			}
			
			else {
				result.get(mesIn).add(String.valueOf(i));
				i++;
			}
			
			if(!result.containsKey(anoIn)) {
				result.put(anoIn, new ArrayList<String>());
				result.get(anoIn).add(String.valueOf(i));
				i++;
			}
			
			else {
				result.get(anoIn).add(String.valueOf(i));
				i++;
			}
		}
		
		for(String d: result.keySet()) {
			int total= result.get(d).size();
			fin.put(d, total);
		}
		
		return fin;
	}
	
	private void calcularEstadisticasEquipos() throws FileNotFoundException {
		int i=1, j=0;
		HashMap<String, ArrayList<Integer>> equipos= new HashMap<String, ArrayList<Integer>>();
		HashMap<String, Integer> fin= new HashMap<String, Integer>();
		for(i=1; i<this.flagEquipo; i++) {
			String nombre="./LogEquipo_"+String.valueOf(i)+".txt";
			File doc= new File(nombre);
			try (Scanner obj = new Scanner(doc)) {
				while(obj.hasNextLine()) {
					String linea= obj.nextLine();
					if(linea.contains("Equipo")) {
						j++;
						equipos.put("Equipo "+String.valueOf(j)+"", new ArrayList<Integer>());
					}
					
					else if(linea.equals("Miembro disponible")) {
						equipos.get("Equipo "+String.valueOf(j)+"").add(1);
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String s: equipos.keySet()) {
			int lon=equipos.get(s).size();
			fin.put(s, lon);
		}
		
		System.out.println("INFORMACION EQUIPOS\n");
		for(String s: fin.keySet()) {
			System.out.println(""+" tiene disponible "+fin.get(s)+" miembros\n");
		}
		System.out.println("\n\n");
	}
	
	private void calcularEstadisticasAcciones() throws FileNotFoundException {
		int total=0;
		float media=0f;
		int i=1;
		for(i=1; i<this.flagAlarma; i++) {
			String nombre="./LogAlarma_"+String.valueOf(i)+".txt";
			File doc= new File(nombre);
			try (Scanner obj = new Scanner(doc)) {
				while(obj.hasNextLine()) {
					String linea=obj.nextLine();
					if(linea.contains("Accion")) {
						total++;
					}
					
					else if(linea.contains("Tiempo: ")) {
						String[] parts= linea.split(": ");
						media+=Float.valueOf(parts[1]);
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		media=media/total;	
		
		System.out.println("INFORMACION ACCIONES Y VERIFICACIONES\n");
		System.out.println("Total: "+String.valueOf(total)+"\n");
		System.out.println("Media de duracion: "+String.valueOf(media)+"\n");
		System.out.println("\n\n");
	}
	
	private void calcularEstadisticasAlarmas() throws FileNotFoundException {
		int total=0;
		float media=0f;
		
		int i=1, k=1;
		HashMap<Integer, String> alarmas= new HashMap<Integer, String>();
		HashMap<String, Integer> fin= new HashMap<String, Integer>();
		for(i=1; i<this.flagAlarma; i++) {
			int j=0;
			String nombre="./LogAlarma_"+String.valueOf(i)+".txt";
			File doc= new File(nombre);
			try (Scanner obj = new Scanner(doc)) {
				ArrayList<String> fechasIn, fechasFin;
				fechasIn= new ArrayList<String>();
				fechasFin= new ArrayList<String>();
				
				while(obj.hasNextLine()) {
					String linea= obj.nextLine();
					if(linea.contains("Alarma")) {
						total++;
					}
					
					else if(linea.contains("Fecha inicio: ")) {
						String[] parts= linea.split(": ");
						fechasIn.add(parts[1]);
						alarmas.put(k, parts[1]);
						k++;
					}
					
					else if(linea.contains("Fecha fin: ")) {
						String[] parts= linea.split(": ");
						fechasFin.add(parts[1]);
					}
				}
				
				for(j=0; j<fechasIn.size(); j++) {
					String fechaIn=fechasIn.get(j);
					String fechaFin=fechasFin.get(j);
					
					Date dIn=convertirFecha(fechaIn);
					Date dFin= convertirFecha(fechaFin);
					
					long diff= dFin.getTime()-dIn.getTime();
					
					TimeUnit time= TimeUnit.DAYS;
					long diferencia= time.convert(diff, TimeUnit.MILLISECONDS);
					long duracion= diferencia*24;
					media+=duracion;
				}
				
			}
		}
		
		media=media/total;
		fin= distribucionAlarmas(alarmas);
		
		System.out.println("INFORMACION ALARMAS\n");
		System.out.println("Total: "+String.valueOf(total)+"\n");
		System.out.println("Media de duracion: "+String.valueOf(media)+"\n");
		for(String s: fin.keySet()) {
			System.out.println("En "+s+" se produjeron "+Integer.toString(fin.get(s))+" alarmas\n");
		}
		System.out.println("\n\n");
		
	}
	
	private void calcularEstadisticas(int flag) throws FileNotFoundException {
		if(flag==0) { //Equipos
			if(this.equipoOK==1) {
				calcularEstadisticasEquipos();
			}
			this.equipoOK=0;
		}
		
		else if(flag==1) { //Acciones
			if(this.accionOK==1) {
				calcularEstadisticasAcciones();
			}
			this.accionOK=0;
		}
		
		else if(flag== 2) { //Alarmas
			if(this.alarmaOK==1) {
				calcularEstadisticasAlarmas();
			}
			this.alarmaOK=0;
		}
	}
	
	public void eliminarArchivos() throws IOException {
		Process process = Runtime.getRuntime().exec("DEL Log*.txt");
		this.flagEquipo=0;
		this.flagAccion=0;
		this.flagAlarma=0;
	}
	
	public void exponerValores(int flag) throws FileNotFoundException {
		ArrayList<Object> res= new ArrayList<Object>();
		
		if(flag== 0) { //Equipos
			calcularEstadisticas(0);
		}
		
		else if(flag== 1) { //Acciones
			calcularEstadisticas(1);
		}
		
		else if(flag== 2) { //Alarmas
			calcularEstadisticas(2);
		}
	}
}