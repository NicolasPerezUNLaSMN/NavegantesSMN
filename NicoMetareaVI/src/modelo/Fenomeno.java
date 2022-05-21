package modelo;

public class Fenomeno {
	
	
	private int idFenomeno;
	private String texto;
	private int incluir; //cero es no
	
	
	
	
	
	
	public int getIncluir() {
		return incluir;
	}
	public void setIncluir(int incluir) {
		this.incluir = incluir;
	}
	public int getIdFenomeno() {
		return idFenomeno;
	}
	protected void setIdFenomeno(int idFenomeno) {
		this.idFenomeno = idFenomeno;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	public Fenomeno(int idFenomeno, String texto) {
		super();
		this.idFenomeno = idFenomeno;
		this.texto = texto;
	}
	
	
	public Fenomeno(String texto) {
		super();
		
		this.texto = texto;
	}
	
	
	@Override
	public String toString() {
		return "Fenomeno [idFenomeno=" + idFenomeno + ", texto=" + texto + "]";
	}
	
	
	public Fenomeno(int idFenomeno, String texto, int incluir) {
		super();
		this.idFenomeno = idFenomeno;
		this.texto = texto;
		this.incluir = incluir;
	}
	
	
	
	public Fenomeno(String texto, int incluir) {
		super();
		
		this.texto = texto;
		this.incluir = incluir;
	}
	
	
	public Fenomeno() {};
	
	
	
	

}
