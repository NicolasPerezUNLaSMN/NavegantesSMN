package test;

import modelo.Estadisticas;
import modelo.Warning;
import negocio.WarningABM;

public class TestEstadisticasWarning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WarningABM wABM = new WarningABM();
		
		
		Estadisticas e = new Estadisticas(wABM.traerWarningTodos());
		
		
		
		//VEAMOS LOS RESULTADOS
		
		System.out.println(e.getCantidadWarningEmitidos()+" --- " +e.getCantidadWHighSea() + "---- " +e.getCantidadWOffShore() +" ----- " +e.getCantidadWCoastal());

		System.out.println(e.getCantidadS60()  +" ---- " +e.getCantidadS50() +" ---- " +e.getCantidadS40() +" ---- " +e.getCantidadS35());
		
		
		
		
	}

}
