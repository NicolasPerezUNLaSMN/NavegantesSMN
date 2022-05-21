
package modelo;

import java.util.GregorianCalendar;
import java.util.Set;



public class Boletin {
	



	private int idBoletin;
	private GregorianCalendar fecha;
	private int horaUTC;
	
	private Set<Warning> listaWarning;
	
	private Set<BoletinEmitido> listaBoletinEmitido;
	
	
	
	public Boletin(int idBoletin, GregorianCalendar fecha, int horaUTC) {
		super();
		this.idBoletin = idBoletin;
		this.fecha = fecha;
		this.horaUTC = horaUTC;
	}
	public int getIdBoletin() {
		return idBoletin;
	}
	public Boletin() {};
	
	public Boletin(GregorianCalendar fecha, int horaUTC) {
		super();
	
		this.fecha = fecha;
		this.horaUTC = horaUTC;
	}
	protected void setIdBoletin(int idBoletin) {
		this.idBoletin = idBoletin;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	public int getHoraUTC() {
		return horaUTC;
	}
	public void setHoraUTC(int horaUTC) {
		this.horaUTC = horaUTC;
	}
	public Set<Warning> getListaWarning() {
		return listaWarning;
	}
	public void setListaWarning(Set<Warning> listaWarning) {
		this.listaWarning = listaWarning;
	}
	public Boletin(int idBoletin, GregorianCalendar fecha, int horaUTC, Set<Warning> listaWarning) {
		super();
		this.idBoletin = idBoletin;
		this.fecha = fecha;
		this.horaUTC = horaUTC;
		this.listaWarning = listaWarning;
	}
	
	
	public Boletin(GregorianCalendar fecha, int horaUTC, Set<Warning> listaWarning) {
		super();
		
		this.fecha = fecha;
		this.horaUTC = horaUTC;
		this.listaWarning = listaWarning;
	}
	@Override
	public String toString() {
		return "Boletin [idBoletin=" + idBoletin + ", fecha=" + fecha + ", horaUTC=" + horaUTC + ", listaWarning="
				+ listaWarning + "]";
	}
	public Set<BoletinEmitido> getListaBoletinEmitido() {
		return listaBoletinEmitido;
	}
	public void setListaBoletinEmitido(Set<BoletinEmitido> listaBoletinEmitido) {
		this.listaBoletinEmitido = listaBoletinEmitido;
	}
	public Boletin(int idBoletin, GregorianCalendar fecha, int horaUTC, Set<Warning> listaWarning,
			Set<BoletinEmitido> listaBoletinEmitido) {
		super();
		this.idBoletin = idBoletin;
		this.fecha = fecha;
		this.horaUTC = horaUTC;
		this.listaWarning = listaWarning;
		this.listaBoletinEmitido = listaBoletinEmitido;
	}
	
	
	
	public Boletin(GregorianCalendar fecha, int horaUTC, Set<Warning> listaWarning,
			Set<BoletinEmitido> listaBoletinEmitido) {
		super();

		this.fecha = fecha;
		this.horaUTC = horaUTC;
		this.listaWarning = listaWarning;
		this.listaBoletinEmitido = listaBoletinEmitido;
	}
	
	
	
	
	
	
	
	
	
	
	

}
