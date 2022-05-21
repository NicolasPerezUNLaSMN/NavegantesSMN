package modelo;

public class BoletinEmitido {
	
	
	private int idBoletinEmitido;
	private String texto;
	private String tipo;
	
	
	private Boletin boletin;
	
	
	
	public BoletinEmitido() {}



	public int getIdBoletinEmitido() {
		return idBoletinEmitido;
	}



	protected void setIdBoletinEmitido(int idBoletinEmitido) {
		this.idBoletinEmitido = idBoletinEmitido;
	}



	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public BoletinEmitido(int idBoletinEmitido, String texto, String tipo) {
		super();
		this.idBoletinEmitido = idBoletinEmitido;
		this.texto = texto;
		this.tipo = tipo;
	}
	
	
	
	

	public BoletinEmitido( String texto, String tipo) {
		super();

		this.texto = texto;
		this.tipo = tipo;
	}



	public Boletin getBoletin() {
		return boletin;
	}



	public void setBoletin(Boletin boletin) {
		this.boletin = boletin;
	}



	public BoletinEmitido(int idBoletinEmitido, String texto, String tipo, Boletin boletin) {
		super();
		this.idBoletinEmitido = idBoletinEmitido;
		this.texto = texto;
		this.tipo = tipo;
		this.boletin = boletin;
	}
	
	
	
	
	public BoletinEmitido(String texto, String tipo, Boletin boletin) {
		super();
	
		this.texto = texto;
		this.tipo = tipo;
		this.boletin = boletin;
	}



	@Override
	public String toString() {
		return "BoletinEmitido [texto=" + texto + ", tipo=" + tipo + "]";
	}
	
	
	
	
	
	
	
	
	

}
