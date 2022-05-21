package test;

import modelo.Warning;
import negocio.WarningABM;

public class TestListaWarningActivos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WarningABM wABM = new WarningABM();
		
		for(Warning w: wABM.traerWarningActivos()) {
			System.out.println(w.getBoletin().getIdBoletin());
			
			
			
		}

		
		
		
		
		
	}

}
