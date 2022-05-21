package modelo;

public class Hielo {
	
	private int idHielo;
	private String espaniol;
	private String ingles;
	
	
	public int getIdHielo() {
		return idHielo;
	}
	
	protected void setIdHielo(int idHielo) {
		this.idHielo = idHielo;
	}
	public String getEspaniol() {
		return espaniol;
	}
	public void setEspaniol(String espaniol) {
		this.espaniol = espaniol;
	}
	public String getIngles() {
		return ingles;
	}
	public void setIngles(String ingles) {
		this.ingles = ingles;
	}
	
	
	public Hielo() {};
	
	
	public Hielo(int idHielo, String espaniol, String ingles) {
		super();
		this.idHielo = idHielo;
		this.espaniol = espaniol;
		this.ingles = ingles;
	}
	
	public Hielo(String espaniol, String ingles) {
		
		this.espaniol = espaniol;
		this.ingles = ingles;
	}
	
	
	
	
	

}
