package test;

import modelo.Boletin;
import negocio.BoletinABM;

public class TestTraerBoletinPorFecha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
			BoletinABM bABM = new BoletinABM();
			
			
			for (Boletin b:bABM.traerBoletin()) {
				
				
				System.out.println(b);
				
			}
			
			
			
		
		
		
		

	}

}
