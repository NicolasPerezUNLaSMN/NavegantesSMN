package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pronostico {
	
	private double idPronostico;
	private ArrayList<Area> areas = new ArrayList<Area>();
	
	
	
	public Pronostico(double idPronostico, ArrayList<Area> areas) {
		super();
		this.idPronostico = idPronostico;
		this.areas = areas;
	}



	public double getIdPronostico() {
		return idPronostico;
	}



	public void setIdPronostico(double idPronostico) {
		this.idPronostico = idPronostico;
	}



	public List<Area> getAreas() {
		return areas;
	}



	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}



	@Override
	public String toString() {
		return "Pronostico [idPronostico=" + idPronostico+"]";
	}
	
	
	public Pronostico(){};
	
	
	
	
	
	

}
