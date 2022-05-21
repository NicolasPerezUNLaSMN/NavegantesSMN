package test;

import modelo.Fenomeno;
import negocio.FenomenoABM;

public class TestTraerFenomenos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		FenomenoABM fABM = new FenomenoABM();
		for (Fenomeno f:fABM.traerFenomeno()) {
			System.out.println(f.toString());
		}

	}

}
