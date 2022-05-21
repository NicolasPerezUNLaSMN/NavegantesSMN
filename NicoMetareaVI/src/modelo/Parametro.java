package modelo;

import java.util.ArrayList;
import java.util.List;

public class Parametro {

	private String nombre; 
	private ArrayList<Hora> horas = new ArrayList<Hora>();
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Hora> getHoras() {
		return horas;
	}
	public void setHoras(ArrayList<Hora> horas) {
		this.horas = horas;
	}
	public Parametro(String nombre, ArrayList<Hora> horas) {
		super();
		this.nombre = nombre;
		this.horas = horas;
	}
	@Override
	public String toString() {
		return "Parametro [nombre=" + nombre  + "]";
	}
	
	public Parametro(){};
	
	
	
	
	
	
	
}
