package test;

import java.io.FileWriter;
import java.io.PrintWriter;

import funciones.Funciones;

import modelo.Pronostico;

public class TestPronosMetarea {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pronostico p = funciones.Funciones.cargarXML("/entrada");
		
		String retorno = Funciones.generoCabecera();
		
		
		
		retorno = retorno +Funciones.generarPronosticos(p);
		
		
		
		System.out.println(retorno.toUpperCase());
		
		
		
		
		

	}//cierra el main

}//Cierra el test
