package modelo;

import java.util.ArrayList;
import java.util.List;

public class Area {
	
	private int idArea;
	private String latitud;
	private String longitud;
	private String nombre;
	private String dominio;
	private ArrayList<Parametro> parametros = new ArrayList<Parametro>();
	
	
	
	
	
	
	
	public Area(int idArea, String latitud, String longitud, String nombre,
			String dominio, ArrayList<Parametro> parametros) {
		super();
		this.idArea = idArea;
		this.latitud = latitud;
		this.longitud = longitud;
		this.nombre = nombre;
		this.dominio = dominio;
		this.parametros = parametros;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Parametro> getParametros() {
		return parametros;
	}
	public void setParametros(ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}
	public Area(int idArea, String latitud, String longitud, String nombre,
			ArrayList<Parametro> parametros) {
		super();
		this.idArea = idArea;
		this.latitud = latitud;
		this.longitud = longitud;
		this.nombre = nombre;
		this.parametros = parametros;
	}
	@Override
	public String toString() {
		return "Area [idArea=" + idArea + ", latitud=" + latitud
				+ ", longitud=" + longitud + ", nombre=" + nombre
				+ ", dominio=" + dominio  + "]";
	}
	
	public Area(){};
	
	
	
	
	

}
