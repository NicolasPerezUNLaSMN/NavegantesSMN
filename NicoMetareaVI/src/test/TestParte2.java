package test;

import funciones.Funciones;
import negocio.FenomenoABM;

public class TestParte2 {

	public static void main(String[] args) throws Exception {
	
		
		FenomenoABM fABM = new FenomenoABM();
		
		fABM.eliminar(2);
		fABM.eliminar(3);
		
		System.out.println(Funciones.generarParte2());

	}

}
