package modelo;

public class Cese {
	
	
	private int idCese;
	private String texto;
	private int activo; //cero es NO 
	
	
	
	
	public Cese(int idCese, String texto, int activo) {
		super();
		this.idCese = idCese;
		this.texto = texto;
		this.activo = activo;
	}
	
	
	
	public Cese( String texto, int activo) {
		super();
	
		this.texto = texto;
		this.activo = activo;
	}

	
	public Cese() {}


	public int getIdCese() {
		return idCese;
	}



	protected void setIdCese(int idCese) {
		this.idCese = idCese;
	}



	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public int getActivo() {
		return activo;
	}



	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
	
	
	
	
	

}
