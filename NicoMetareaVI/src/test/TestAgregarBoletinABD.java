package test;

import java.util.GregorianCalendar;

import modelo.Boletin;
import modelo.Warning;
import negocio.BoletinABM;
import negocio.WarningABM;

public class TestAgregarBoletinABD {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		BoletinABM b = new BoletinABM();
		//b.agregar(new GregorianCalendar(), 12);
		
		Boletin boletin = b.traerBoletin(new GregorianCalendar(),0);
		
		if (boletin==null) {
			System.out.println("no se genero ese boletin");
		}
		
		else {
			
			//System.out.println(boletin.getIdBoletin());
			
			Boletin boletinConWarning = b.traerBoletinYListaWarning(boletin.getIdBoletin());
			System.out.println(boletinConWarning.getIdBoletin());
		
			for (Warning w : boletinConWarning.getListaWarning()) {
				System.out.println(w.toString());
			}
			
		}
		
		
		
		//System.out.println(boletin.getIdBoletin());
		
		//WarningABM w = new WarningABM();
		//w.agregar(1, 1, new GregorianCalendar(), "Estas son las areas", "fenomen", "texto", "tipo", "dominio", boletin);
		
		
		

	}

}
