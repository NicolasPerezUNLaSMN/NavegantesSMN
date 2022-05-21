package test;

import modelo.Cese;
import negocio.CeseABM;

public class TestTraerCesesActivos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		CeseABM cABM = new CeseABM();
		
		
		for (Cese c:cABM.traerCesesActivos()) {
			
			
			
			
			System.out.println(c.getTexto());
		}
		
		
		
		

	}

}
