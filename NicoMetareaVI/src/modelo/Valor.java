package modelo;

public class Valor {

	private String unidad;
	private String valor;
	
	
	
	
	
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Valor [unidad=" + unidad + ", valor=" + valor + "]";
	}
	public Valor(String unidad, String valor) {
		super();
		this.unidad = unidad;
		this.valor = valor;
	}
	
	public Valor(){};
	
	
	
	
	
	
}
