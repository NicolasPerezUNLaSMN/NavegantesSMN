package modelo;

import java.util.GregorianCalendar;

public class Warning {

	private int idWarning; //autogenerado para base de datos
	private int numeroWarning;
	private int actualizacion;
	private GregorianCalendar fecha;
	private String areas;
	private String fenomeno;
	private String texto;
	private String tipo; //Gale, storm, etc, etc
	private String dominio;  //high, off, costa
	private int activo; //1 activo
	
	
	private Boletin boletin;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Warning(int idWarning, int numeroWarning, int actualizacion, GregorianCalendar fecha, String areas,
			String fenomeno, String texto, String tipo, String dominio, Boletin boletin) {
		super();
		this.idWarning = idWarning;
		this.numeroWarning = numeroWarning;
		this.actualizacion = actualizacion;
		this.fecha = fecha;
		this.areas = areas;
		this.fenomeno = fenomeno;
		this.texto = texto;
		this.tipo = tipo;
		this.dominio = dominio;
		this.boletin = boletin;
	}

	public Warning(int numeroWarning, int actualizacion, GregorianCalendar fecha, String areas, String fenomeno,
			String texto, String tipo, String dominio, Boletin boletin) {
		super();
		this.numeroWarning = numeroWarning;
		this.actualizacion = actualizacion;
		this.fecha = fecha;
		this.areas = areas;
		this.fenomeno = fenomeno;
		this.texto = texto;
		this.tipo = tipo;
		this.dominio = dominio;
		this.boletin = boletin;
	}
	
	public Warning(int numeroWarning, int actualizacion, GregorianCalendar fecha, String areas, String fenomeno,
			String texto, String tipo, String dominio, int activo, Boletin boletin) {
		super();
		this.numeroWarning = numeroWarning;
		this.actualizacion = actualizacion;
		this.fecha = fecha;
		this.areas = areas;
		this.fenomeno = fenomeno;
		this.texto = texto;
		this.tipo = tipo;
		this.dominio = dominio;
		this.activo = activo;
		this.boletin = boletin;
	}

	public Boletin getBoletin() {
		return boletin;
	}

	public void setBoletin(Boletin boletin) {
		this.boletin = boletin;
	}

	public Warning() {};
	
	public Warning(int numeroWarning, int actualizacion, GregorianCalendar fecha,String areas, String fenomeno,  String texto, String tipo, String dominio) {
		super();
		this.numeroWarning = numeroWarning;
		this.actualizacion = actualizacion;
		this.fecha = fecha;
		this.areas = areas;
		this.fenomeno = fenomeno;
		this.texto = texto;
		this.tipo = tipo;
		this.dominio = dominio;
	}
	
	
	
	
	
	
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getFenomeno() {
		return fenomeno;
	}

	public void setFenomeno(String fenomeno) {
		this.fenomeno = fenomeno;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public int getIdWarning() {
		return idWarning;
	}


	protected void setIdWarning(int idWarning) {
		this.idWarning = idWarning;
	}
	
	
	public int getNumeroWarning() {
		return numeroWarning;
	}
	public void setNumeroWarning(int numeroWarning) {
		this.numeroWarning = numeroWarning;
	}
	public int getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(int actualizacion) {
		this.actualizacion = actualizacion;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
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

	@Override
	public String toString() {
		return "Warning [idWarning=" + idWarning + ", numeroWarning=" + numeroWarning + ", actualizacion="
				+ actualizacion + ", fecha=" + fecha + ", areas=" + areas + ", fenomeno=" + fenomeno + ", texto="
				+ texto + ", tipo=" + tipo + ", dominio=" + dominio ;
	}
	
	
	
	
	
	
	
	
	
}
