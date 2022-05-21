package test;

import modelo.Email;
import negocio.EmailABM;

public class TestTraerListaDeMails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EmailABM eABM = new EmailABM();
		
		
		for (Email e : eABM.traerEmail()) {
			System.out.println(e.toString());
		}
	}

}
