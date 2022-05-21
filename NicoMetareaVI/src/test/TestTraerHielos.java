package test;

import modelo.Fenomeno;
import modelo.Hielo;
import negocio.FenomenoABM;
import negocio.HieloABM;

public class TestTraerHielos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		HieloABM fABM = new HieloABM();
		
		
		for (Hielo f:fABM.traerHielo()) {
			System.out.println(f.getIngles());
		}


	}

}
