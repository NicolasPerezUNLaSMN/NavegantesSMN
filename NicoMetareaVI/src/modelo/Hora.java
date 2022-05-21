package modelo;

import java.util.ArrayList;
import java.util.List;

public class Hora {
	
	private int idHora;
	private String descripcion;
	private ArrayList<Valor> valores = new ArrayList<Valor>();
	public int getIdHora() {
		return idHora;
	}
	public void setIdHora(int idHora) {
		this.idHora = idHora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Valor> getValores() {
		return valores;
	}
	public void setValores(ArrayList<Valor> valores) {
		this.valores = valores;
	}
	public Hora(int idHora, String descripcion, ArrayList<Valor> valores) {
		super();
		this.idHora = idHora;
		this.descripcion = descripcion;
		this.valores = valores;
	}
	@Override
	public String toString() {
		return "Hora [idHora=" + idHora + ", descripcion=" + descripcion + "]";
	}
	
	public Hora(){};
	
	
	
	
	
	

}
