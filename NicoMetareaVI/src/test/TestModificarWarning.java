package test;

import modelo.Warning;
import negocio.WarningABM;

public class TestModificarWarning {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		WarningABM wABM = new WarningABM();
		
		
		Warning w = wABM.traerWarning(1);
		
		w.setActivo(0);
		
		wABM.modificar(w);

	}

}
