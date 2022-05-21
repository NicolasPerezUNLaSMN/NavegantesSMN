package test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import funciones.Funciones;

public class RuletaDeLaMuerte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
	
		
	
		
		
		
		String[]  nombres  = new String[3];
		
		
		System.out.println("//////////////Que dominio te toca?????? :O /////////////////\n\n\n");
		
		
		System.out.println("Quien llego primero al turno? : ");
		Scanner scan = new Scanner(System.in);
		nombres[0] = (String) scan.next();
		
		
		System.out.println("Quien llego segundo al turno? : ");
		Scanner scan1 = new Scanner(System.in);
		nombres[1] = (String) scan1.next();
		
		
		System.out.println("Quien llego tercero al turno? : ");
		Scanner scan2 = new Scanner(System.in);
		nombres[2] = (String) scan2.next();
		
		
		
		System.out.flush();
		
		
		int[] numeros = {1, 2, 3};
		
		Random rnd = ThreadLocalRandom.current(); 
        for (int i = numeros.length - 1; i > 0; i--) { 
            int temp = rnd.nextInt(i + 1); 
            // Simple swap 
            int a = numeros[temp]; 
            numeros[temp] = numeros[i]; 
            numeros[i] = a; 
        } 
		
        
        String texto = "";
        String camiones = "";
        for(int i=0 ; i<3; i++) {
        	
        	if(numeros[i]==1) {
        		texto = "LLanura Chaco Pampeana y Alertas, temo lo peor para ti, ";
        		camiones = " camiones";
        	}
        	
        	
        	
        	if(numeros[i]==2) {
        		texto = "Cordillera y Patagonia, demasiado facil... ";
        		camiones = " baldes ";
        	}
        	
        	
        	
        	if(numeros[i]==3) {
        		texto = "Costas y MetareaVI, que Dios te ayude :( ";
        		camiones = " cucharadas ";
        	}
        	
        	
        	System.out.println("A " +nombres[i] +" le toca hacer " +texto  +"  y tu pronostico equivaldra a: " +((int) (Math.random() * 20) + 1) +camiones +" de tierra\n");
        }
		

	
		

	}

}
