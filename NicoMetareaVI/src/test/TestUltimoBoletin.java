package test;

import modelo.Boletin;
import negocio.BoletinABM;

public class TestUltimoBoletin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		int maximo = 0; 
		BoletinABM bABM = new BoletinABM();
		
		for (Boletin b : bABM.traerBoletin()) {
			
			if(b.getIdBoletin()>=maximo) {
				maximo = b.getIdBoletin();
			}
		}

	}

}
