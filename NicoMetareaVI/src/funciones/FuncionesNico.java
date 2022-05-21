package funciones;


//Cosas a importar	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



//Nombre de la clase
public class FuncionesNico {    
	
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Lista de feriados
	private static final ArrayList<GregorianCalendar> lstFeriados = new ArrayList<GregorianCalendar>();	
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Cosigo el a�o
	public static int traerAnio(GregorianCalendar f){
		return f.get(Calendar.YEAR);
	} 
  
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Consigo el mes
	public static int traerMes(GregorianCalendar f){
		return f.get(Calendar.MONTH)+1;
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	//Consigo el dia 
	public static int traerDia(GregorianCalendar f){	
		return f.get(Calendar.DAY_OF_MONTH);
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo si el a�o es bisiesto
	public static boolean esBisiesto (int anio){
		return ((anio % 4 == 0) && ((anio % 100 != 0) || ( anio % 400 == 0)));
	} 
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	public static boolean esFechaValida (int anio, int mes, int dia){
		boolean validacion;
		if ((anio <= 2015) && (mes <= 12) && (mes >= 0) && (dia > 0) && (dia <= 31)){ 
			if ((esBisiesto(anio) == true) && (mes == 2)){
					if (dia <=29){
						validacion = true;
					}else{ 
						validacion = false;
					}	
			}
			
			else{ 
				if ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)){
					 if (dia <= 30){
						validacion = true;
					 }else{ 
						validacion = false;
					 }
				}
				
				else if((mes == 1) || (mes == 3) || (mes == 5) || (mes == 7) || (mes == 8) || (mes == 10)){
					 if (dia <= 31){
						validacion = true;
					 }else{ 
						validacion = false;
					 }
				}
				
				else{ 
					 if (dia <= 28){
						 validacion = true;
					 }else{
						  validacion = false;
					 }
				}
			}
		}else{
			validacion = false; 
		}	
		
	 return validacion;
	  } 
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Genero una instancia a gregorian calendar con fechas enteras
	//OJO poner 1 es poner ENERO ( cero en gregorian)
	public static GregorianCalendar traerFecha (int anio, int mes, int dia){
		GregorianCalendar fecha = new GregorianCalendar(anio, mes-1, dia);
		return fecha;	
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Genero la instancia desde unn strgin de 10 caracteres
	//30/01/1989
	public static GregorianCalendar traerFechaDesdeString10 (String fechaprueba){ 
		GregorianCalendar fecha= new GregorianCalendar ();
		fecha.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(fechaprueba.substring(0,2)));
		fecha.set(GregorianCalendar.MONTH, Integer.parseInt(fechaprueba.substring(3,5))-1);
		fecha.set(GregorianCalendar.YEAR, Integer.parseInt(fechaprueba.substring(6,10)));
		return fecha;

	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//traigo la fecha corta en forma de string
	public static String traerFechaCorta (GregorianCalendar f2){
		String fechaCorta;
		if (traerMes(f2) >= 10){  
			if (traerDia(f2)>= 10)
				fechaCorta = (traerDia(f2)+"/"+traerMes(f2)+"/"+traerAnio(f2));
			else
				fechaCorta = ("0"+traerDia(f2)+"/"+traerMes(f2)+"/"+traerAnio(f2));
		}else{ 
			if (traerDia(f2)>= 10)
				fechaCorta = (traerDia(f2)+"/0"+traerMes(f2)+"/"+traerAnio(f2));
			else 
				fechaCorta = ("0"+traerDia(f2)+"/0"+traerMes(f2)+"/"+traerAnio(f2));
		}
		return fechaCorta;
	}
			
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		
		//traigo la fecha corta en forma de string
		public static String traerFechaEWarning (GregorianCalendar f2){
		String fechaCorta;
		
		fechaCorta = String.valueOf(traerDia(f2)) +" " + traerMesEnLetrasIngles(f2) +" "+traerAnio(f2);
		
		return fechaCorta;
		}

	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo que sea dia de lunes a viernes
	public static boolean esDiaHabil(GregorianCalendar f){
		boolean resultado = false;
		if ((f.get(Calendar.DAY_OF_WEEK) <= 6) && (f.get(Calendar.DAY_OF_WEEK) > 1))
			resultado = true;
		return resultado;		
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	// Traigo en string los dias de la semana
	public static String traerDiaDeLaSemana (GregorianCalendar f){
		String DiaSemanaLet = null;
		switch (f.get(Calendar.DAY_OF_WEEK)){ //Si tomamos al lunes como primer dia, se modificarian los case empezando por el "Lunes".
			case 1: 			  //Ademas, para que funcione, deberiamos tener modificado el metodo anterior con la configuracion explicada en los comentarios del mismo.
				DiaSemanaLet = "Domingo";  	
			break;
			
			case 2:
				DiaSemanaLet = "Lunes";
			break;
			
			case 3: 
				DiaSemanaLet = "Martes";
			break;
			
			case 4: 
				DiaSemanaLet = "Miercoles";
			break;
			
			case 5:
				DiaSemanaLet = "Jueves";
			break;
			
			case 6:
				DiaSemanaLet = "Viernes";
			break;
			
			case 7:
				DiaSemanaLet = "Sabado";
			break;					
		}
		
		return DiaSemanaLet;
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Genero el mes en letras
	public static String traerMesEnLetras (GregorianCalendar f){
		String nombMes = null;
		switch (traerMes(f)-1){    	//Como el metodo traerMes nos devuelve el mes con +1, lo nivelamos otra vez. 
		
		case 0: 
			nombMes = "Enero";
		break;
		
		case 1:
			nombMes = "Febrero";
		break;
		
		case 2: 
			nombMes = "Marzo";
		break;
		
		case 3: 
			nombMes = "Abril";
		break;
		
		case 4:
			nombMes = "Mayo";
		break;
		
		case 5: 
			nombMes = "Junio";
		break;
		
		case 6:
			nombMes = "Julio";
		break;
		
		case 7:
			nombMes = "Agosto"; 
		break;
		
		case 8:
			nombMes = "Septiembre";
		break;
		
		case 9:
			nombMes = "Octubre";
		break;
		
		case 10:
			nombMes = "Noviembre";
		break;
		 
		case 11:
			nombMes = "Diciembre";
		break;
		
		}	
		
		return nombMes;
	}  
	/////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////// 
	
	public static String traerMesEnLetrasIngles (GregorianCalendar f){
		String nombMes = null;
		switch (traerMes(f)-1){    	//Como el metodo traerMes nos devuelve el mes con +1, lo nivelamos otra vez. 
		
	
		case 0: 
			nombMes = "January";
		break;
		
		case 1:
			nombMes = "February";
		break;
		
		case 2: 
			nombMes = "March";
		break;
		
		case 3: 
			nombMes = "April";
		break;
		
		case 4:
			nombMes = "May";
		break;
		
		case 5: 
			nombMes = "June";
		break;
		
		case 6:
			nombMes = "July";
		break;
		
		case 7:
			nombMes = "August"; 
		break;
		
		case 8:
			nombMes = "September";
		break;
		
		case 9:
			nombMes = "October";
		break;
		
		case 10:
			nombMes = "November";
		break;
		 
		case 11:
			nombMes = "December";
		break;
		
		}	
		
		return nombMes;
	}  
	/////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////// 
	
	//Genero la fecha larga
	public static String traerFechaLarga (GregorianCalendar f){
		String FechaLarga;
		if (traerDia(f) < 10) 
			FechaLarga = (traerDiaDeLaSemana(f)+" 0"+traerDia(f)+" de "+traerMesEnLetras(f)+" del "+traerAnio(f));
		else 
			FechaLarga = (traerDiaDeLaSemana(f)+" "+traerDia(f)+" de "+traerMesEnLetras(f)+" del "+traerAnio(f));
		return FechaLarga;
	} 
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veamos si las fechas son las mismas
	public static boolean sonFechasIguales (GregorianCalendar f, GregorianCalendar f2){
		boolean fechaComparar = false;
		if (((traerAnio(f) == traerAnio(f2)) && (traerMes(f)) == traerMes(f2)) && (traerDia(f) == traerDia(f2)))
				fechaComparar = true;
		
		return fechaComparar;	 
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Cuantos dias tiene el mes
	public static int traerCantDiasDeUnMes(int anio, int mes){ 
		int cantidad;
		if ((esBisiesto(anio)== true) && (mes== 2)) 
			cantidad = 29;
		else 
			if ((mes == 4) || (mes== 6) || (mes == 9) || (mes== 11))
				cantidad = 30;
			else if ((mes == 1) || (mes == 3) || (mes == 5 ) || (mes == 7) || (mes == 8) || (mes == 10) || (mes == 12))
				cantidad = 31;
			else 
				cantidad = 28;
		return cantidad;		
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Redondeamo a 2 decimas
	public static double aproximar2Decimal (double valor){
		 return Math.rint(valor*100)/100;     
		 //Rinth (redondea un numero al entero mas proximo).
		 											
		 //Entonces se le pasa numero en decimal (o no) * 10 (elevado a la cantidad de cifras que queremos redondear). Finalmente, dividimos el resultado por lo mismo que multiplicamos el valor.
	}	//el valor = 2.346  -> quedo convertido en 2.35
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//convierte un entero en doble
	public static double convertirADouble(int n){ 	 
		//Convierte un entero a un double pero, como siempre las 
		//conversiones se hacen sobre strings o substrings:
		
	return Double.parseDouble(String.valueOf(n)); 
		//Llama a un metodo de la clase String (de java) que convierte el entero a string 
	}	//Y finalmente convierte ese string a un double
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo si un char es un numero
	public static boolean esNumero(char c){
		String diccionario_num = "0123456789";
		int i = 0;
		char charArray[] = diccionario_num.toCharArray();
		//System.out.println(charArray.length);
		boolean esNum = false;
		for (i = 0; i<charArray.length; i++){  	//recorre el array de chars y compara con el char recibido como parametro
			if (c == charArray[i]){
				esNum = true;
				i = charArray.length;	
			}
		}
		return esNum;
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo si un char es letras
	public static boolean esLetra(char c){
		String diccionario_char = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ";
		int i = 0;
		char charArray[] = diccionario_char.toCharArray();	
		boolean esUnaLetra = false;
		for (i= 0; i<charArray.length; i++){
			if (c == charArray[i]){
				esUnaLetra = true;
				i = charArray.length;
			}
		}
		return esUnaLetra;	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo si es cadena de numeros
	public static boolean esCadenaNumeros(String cadenaX){
	  char arrayAnalizar[] = cadenaX.toCharArray();
	  int contador = 0;
	  boolean esCadenaNumeros = true;
	  while ((contador < arrayAnalizar.length) && (esCadenaNumeros == true)){
		  if (arrayAnalizar[contador] != ' '){
			  if (esNumero(arrayAnalizar[contador]) == false){ //(0, 1, 2, 20)
				  esCadenaNumeros = false;	
			  }
		  } 
		  contador++;
	  }
	 return esCadenaNumeros;	  
	}  
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Veo si es cadena de letras
	public static boolean esCadenaLetras(String cadenaX){
		char arrayAnalizar[] = cadenaX.toCharArray();
		int contador = 0; 
		boolean esCadenaLetras = true; 
		while ((contador < arrayAnalizar.length) && (esCadenaLetras == true)){
			if (arrayAnalizar[contador] != ' '){
				if (esLetra(arrayAnalizar[contador]) == false){ 
					esCadenaLetras = false;
				} 
			}
		contador++;	
		}
		return esCadenaLetras;
	}	
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	//Veo si es sexo valido, fFmM
	public static boolean esSexoValido(char c){ 
		String diccionario_sexo = "FfMm";
		boolean sexoValido = false;
		int i = 0;
		for (i= 0; i<(diccionario_sexo.length()); i++){ 
			if (c == diccionario_sexo.charAt(i)){
				sexoValido = true;
			}
		}
		return sexoValido;  		  
						
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//traer la hora
	public static String traerHora(GregorianCalendar f){ 
		//PARA TRAER LA FECHA CORTA + HORA -> JUNTAR CODIGO DE ESTE METODO CON EL DE FECHACORTA
		int hora = f.get(GregorianCalendar.HOUR);
		int minutos = f.get(GregorianCalendar.MINUTE);
		return (hora+":"+minutos); 
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//genero una lista de gregorians desde un archivo
	//Alli estan los feriados
	public static ArrayList<GregorianCalendar> cargarLstFeriados(){
		try {
			File dirBase = new File("src/modelo/feriados.xml"); 
			String ruta = dirBase.getAbsolutePath();
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			
			String entrada;
			String cadena = "";
			
			while ((entrada = br.readLine()) != null)
				cadena += entrada;
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource archivo = new InputSource();
			archivo.setCharacterStream(new StringReader(cadena));
			
			Document documento = db.parse(archivo);
			documento.getDocumentElement().normalize();
			
			NodeList nodeLista = documento.getElementsByTagName("feriado");
			
			String[] tags = {"anio", "mes", "dia"};
			
			for (int s = 0; s < nodeLista.getLength(); s++) {
				
				Node nodo = nodeLista.item(s);
				String[] valores = new String[tags.length];
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) nodo;
					for (int i = 0; i < tags.length; i++) {
						NodeList nombreElementoLista = elemento.getElementsByTagName(tags[i]);
						Element nombreElemento = (Element) nombreElementoLista.item(0);
						NodeList nombre = nombreElemento.getChildNodes();
						valores[i] = ((Node) nombre.item(0)).getNodeValue().toString();
					}
					
					int anio= Integer.parseInt(valores[0]);
					int mes= Integer.parseInt(valores[1]);
					int dia = Integer.parseInt(valores[2]); 
					
					
					GregorianCalendar p= new GregorianCalendar(anio, mes, dia);
					lstFeriados.add(p);
					}
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return lstFeriados;
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//habiles del 2015
	public static boolean esHabil(GregorianCalendar f){
		boolean esHabil = true; 
		
		int anio = f.get(Calendar.YEAR);
		int mes =  f.get(Calendar.MONTH);
		int dia = f.get(Calendar.DAY_OF_MONTH);
		int i = 0;
		
		for (i= 0; i<lstFeriados.size(); i++){
			if (anio == lstFeriados.get(i).get(Calendar.YEAR)){
				if (mes == lstFeriados.get(i).get(Calendar.MONTH)){
					if (dia == lstFeriados.get(i).get(Calendar.DAY_OF_MONTH)){
						esHabil = false;
						i = lstFeriados.size();
					}
				}
			}
		}
		if ((f.get(Calendar.DAY_OF_WEEK) == 1) || (f.get(Calendar.DAY_OF_WEEK) == 7)){
			esHabil = false;
		}
		return esHabil;	
	}
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	//Traer primer dia habil
	public static GregorianCalendar traerPrimerDiaHabil(GregorianCalendar f){
		int anio = f.get(Calendar.YEAR);
		int mes  = f.get(Calendar.MONTH); 
		int dia = f.get(Calendar.DAY_OF_MONTH);
		
		GregorianCalendar temporal = new GregorianCalendar(anio, mes, dia);
		temporal.add(Calendar.DATE, 1);
		while (esHabil(temporal) == false){
			temporal.add(Calendar.DATE, 1);
		}
		return temporal;
	} 
	
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	public static int restaDeDias(GregorianCalendar inicio, GregorianCalendar fin){
		
		
		long diferenciaEnMs = fin.getTimeInMillis()- inicio.getTimeInMillis();
		
		long diferenciaEnDias = diferenciaEnMs/(1000*60*60);
		
		return (int)diferenciaEnDias;
	}
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	public static GregorianCalendar traerFechaDeHoy(){
		int diaHoy = GregorianCalendar.DAY_OF_MONTH;
		int mesHoy = GregorianCalendar.MONTH;
		int anioHoy = GregorianCalendar.YEAR;
		
		GregorianCalendar fHoy = new GregorianCalendar (anioHoy, mesHoy, diaHoy);
		return fHoy;
	}
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	
	public static boolean testLuhn(String nroCuenta){
		boolean verificarNroCuenta = false;
		
		int tamanioString = nroCuenta.length();
		
		//Genero el array
		int arrayIntDelString[] = new int [tamanioString];
				  
		
		//Recorro mi array y voy guardando caada digito
		for (int i=0; i<tamanioString; i++){
			arrayIntDelString[i] = Integer.parseInt(nroCuenta.substring(i,i+1));
					}//Cierra el For   El array va de [0] a [tamanioString -1]
		
		int numeroverificador = arrayIntDelString[tamanioString -1];
		
		
		int resultado = 0;
		int cuenta1 = 0;
		int resultadoPor9 = 0;
		
		//algoritmo de verificacion Luhn
		for (int i=0; i<tamanioString-1; i++){
			

			
			//Si es posicion par.. o sea i 1, 3, 5, 7
			if ((i%2) == 1 ){
				cuenta1 = arrayIntDelString[i]*2;
				
				if (cuenta1 >= 10){// caso dos digitos
					cuenta1 = 1 + (cuenta1%10);
				} //Cierra caso de dos digitos
				
			} //Cierra caso de i 1, 3, 5, 7 ....
			
			//Si es posicion impar.. o sea i 0, 2, 4, 6...
			if ((i%2) == 0 ){
				cuenta1 = arrayIntDelString[i];
			}//Cierra el caso i 0, 2, 4, 6...
			
			
			resultado = resultado + cuenta1;
			
			
			
		}//Cierra el for que recorre el string
		
		//Multiplico por 9 
		resultadoPor9 = resultado*9;
		
		//Me quedo con el ultimo numero
		String resultadoPor9AString = String.valueOf(resultadoPor9);
		
		
		int  ultimoValorDelString = Integer.parseInt(resultadoPor9AString.substring(resultadoPor9AString.length()-1,resultadoPor9AString.length()));
		System.out.println(ultimoValorDelString);
		
		if (ultimoValorDelString==numeroverificador){
			verificarNroCuenta = true;		}
		
		return verificarNroCuenta;
	}
	
   //////////////////////////////////////////////////////////////////////////
   //////////////////////////////////////////////////////////////////////////
	
	
	public static int pasarStringAInt (String cadenaString){
		int resultado = 0;
		
		resultado= Integer.parseInt(cadenaString);
		
		return resultado;
	}
	
  //////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////
	
	public static int[] pasarStringAVectorInt(String cadenaString){
		
      int tamanioCadenaString = cadenaString.length();
		
		//Genero el array
		int vector[] = new int [tamanioCadenaString];
		
		//Recorro mi array y voy guardando caada digito
		for (int i=0; i<tamanioCadenaString; i++){
		      vector[i] = Integer.parseInt(cadenaString.substring(i,i+1));
		      System.out.println (vector[i]);
			  }//Cierra el For   El array va de [0] a [tamanioString -1]
				  
		
		return vector;
	}
	
  //////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////
	
	public static String pasarIntAString (int numero){
		
		
		String numCadena= String.valueOf(numero);
		
		return numCadena;
		
	}

  //////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
	public  static int calcularEdad( GregorianCalendar nacimiento, GregorianCalendar fhoy){


			int retornoEnEdadEnAnios = 0;
			//int meses;
			//int dias;


			int difAnyos = fhoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
			int difMeses = fhoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
			int difDias = fhoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);
			
			//Ya tenia el metodo con el cual calculaba ademas de a�os, los meses y dias
			//de edad, por eso estan comentadas esas variables
			
			if (difAnyos>=1){
			if (difMeses > 0 && difDias >= 0){
			retornoEnEdadEnAnios = difAnyos;
			//meses = difMeses;
			//dias = difDias;
			}
			
			if (difMeses > 0 && difDias < 0){
			retornoEnEdadEnAnios = difAnyos;
			//meses = difMeses-1;
			//dias = 30+difDias;
			}
			
			if (difMeses ==0){
			if (difDias >=0){ 
			retornoEnEdadEnAnios = difAnyos;
			//meses = difMeses;
			//dias = difDias;
			}
			
			if (difDias <0){
			retornoEnEdadEnAnios = difAnyos-1;
			//meses = 11;
			//dias = 30+difDias;
			}
			
			}
			if (difMeses <0 && difDias>0){
			retornoEnEdadEnAnios = difAnyos-1;
			//meses = 12+difMeses;
			//dias = difDias;
			
			}
			if (difMeses <0 && difDias<=0){
			retornoEnEdadEnAnios = difAnyos-1;
			//meses = 12+difMeses+difDias;
			//dias = 30+difDias;
			}
			
			}
			if (difAnyos==0){
			if (difMeses>0 && difDias >=0){
			retornoEnEdadEnAnios = difAnyos;
			// meses = difMeses+difDias;
			//dias = difDias;
			}
			if (difMeses>0 && difDias <0){
			retornoEnEdadEnAnios = difAnyos;
			// meses = difMeses-1;
			//dias = 30+difDias;
			}
			}
			if (difAnyos==0){
			if (difMeses==0 && difDias >=0){
			retornoEnEdadEnAnios = difAnyos;
			//  meses = difMeses;
			// dias = difDias;
			}  
			
			//if (difMeses==0 && difDias <0){
			//  edad= ("Fecha incorrecta");
			//}   
			
			
			}
			return retornoEnEdadEnAnios;
			}//Cierra calcularEdad
	///////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	
	
	/*	Recibe dos fechas
	Devuelve -1 si fecha1 esta anterior a fecha 2
	Devuelve 0 si es la misma fecha
	Devuelve 1 si fecha1 es posterior a fecha2*/
	public static int compararFechas (GregorianCalendar fecha1 , GregorianCalendar fecha2){
		int resultado;
		
		if (fecha1.before(fecha2)){
			resultado = -1;
		} else if (fecha1.after(fecha2)){
			resultado = 1;
		} else {
			resultado = 0;
		};
				
		return resultado;
	}
	
	/////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	
	public static boolean esPrimo(int numero){
		boolean resultado = true;
		int i = 2;
		if (numero==1){
			resultado = false;
		}else if (numero>0){
			while ((resultado)&&(i<=(int)Math.sqrt(numero))){
				if (numero%i==0){
					resultado = false;
				}
				i++;
			}
		}
		return resultado;
	}
	
	/////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	
	public static boolean esPrimoMellizo(int primo1, int primo2){
		boolean resultado = false;
		if (esPrimo(primo1) && esPrimo(primo2)){
			if (primo1 > primo2){
				if (primo1 - primo2 == 2){
					resultado = true;
				}
			}else if (primo1 < primo2){
				if (primo2 - primo1 == 2){
					resultado = true;
				}
			}else{
				resultado = false;
			}
		}
			return resultado;
	}
	/////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////
	
	
// METODO aGregoriaCalendar ///////////////////////////////////////////////////////////////////
    
    public static GregorianCalendar aGregorianCalendar(int anio, int mes, int dia){
    	GregorianCalendar fecha = new GregorianCalendar(anio, (mes)-1, dia);
    	fecha.setLenient(false);
    	return fecha;
    } // end_public_aGregorianCalendar
    
    // METODO aGregorianCalendar SOBRECARGADO /////////////////////////////////////////////////////
    
    public static GregorianCalendar aGregorianCalendar(int anio, int mes, int dia, int hora, int minutos, int segundos){
    	GregorianCalendar fecha = new GregorianCalendar(anio, (mes)-1, dia);
		fecha.set(Calendar.HOUR_OF_DAY, hora);
		fecha.set(Calendar.MINUTE, minutos);
		fecha.set(Calendar.SECOND, segundos);
    	fecha.setLenient(false);
    	return fecha;
    } // end_public_aGregorianCalendar
    
    
    public static String traerFechaCortaParaHibernate(GregorianCalendar fecha){
    	String mes="";
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1<10){
    		mes = "0"+pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1>=10){
    		mes = pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	String dia="";
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)<10){
    		dia = "0"+pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)>=10){
    		dia = pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	return ("'" +fecha.get(GregorianCalendar.YEAR) +"-" +mes  +"-"  +dia+"'");
	    	} // end_public_fechaCorta
    
    public static String traerFechaCortaParaHibernateConHora(GregorianCalendar fecha){
    	
String mes="";
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1<10){
    		mes = "0"+pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1>=10){
    		mes = pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	String dia="";
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)<10){
    		dia = "0"+pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)>=10){
    		dia = pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	String hora = "";
    	
    	if (fecha.get(GregorianCalendar.HOUR_OF_DAY)<10){
    		hora = "0"+pasarIntAString(fecha.get(GregorianCalendar.HOUR_OF_DAY));
    	}
    	
    	if (fecha.get(GregorianCalendar.HOUR_OF_DAY)>=10){
    		hora = pasarIntAString(fecha.get(GregorianCalendar.HOUR_OF_DAY));
    	}
    	
    	String minutos = "";
    	
    	if (fecha.get(GregorianCalendar.MINUTE)<10){
    		minutos = "0"+pasarIntAString(fecha.get(GregorianCalendar.MINUTE));
    	}
    	
    	String segundos = "";
    	
    	if (fecha.get(GregorianCalendar.SECOND)<10){
    		segundos = "0"+pasarIntAString(fecha.get(GregorianCalendar.SECOND));
    	}
    	
    	return ("'" +fecha.get(GregorianCalendar.YEAR) +"-" +mes  +"-"  +dia+" "+hora+":" +minutos+":"  +segundos+"'");
	    	} // end_public_fechaCorta
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    public static String stringAFechaBoletin (String fecha) {
    	
    	String retorno = "";
    	
    	//recibo algo asi: AAAA-MM-DD
    	                 //012345678901
    	
    	String mes = fecha.substring(5, 7);
    	String dia = fecha.substring(8,10);
    	String anio = fecha.substring(0,4);
    	
    	GregorianCalendar fechaFinal = new GregorianCalendar (Integer.parseInt(anio),Integer.parseInt(mes)-1,Integer.parseInt(dia));
    	
    	retorno = traerFechaEWarning(fechaFinal);
    	
    	
    	
    	return retorno;
    	
    	
    }
    
    
	

	
	/*
	@SuppressWarnings("unchecked")
	public List<Compra> traerCompra(int idCliente, GregorianCalendar fechaDesde, GregorianCalendar fechaHasta) {
		List<Compra> lista=null;
		try {
			iniciaOperacion();
			lista=session.createQuery("from Compra where idCliente = "+idCliente+" and fechaDeIngreso >=" +traerFechaCortaParaHibernate(fechaDesde)+" AND  fechaDeIngreso <="+traerFechaCortaParaHibernate(fechaHasta)).list();
			
		} finally {
			session.close();
		}

	return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Novios> traerNoviosPorFecha(GregorianCalendar c) throws HibernateException{
		
		List<Novios> lista = null;
		
	   String pepePompin = Funciones.traerFechaCortaParaHibernate(c);
		
		try {
			iniciaOperacion();
			; 
			lista = session.createQuery("from Novios  tb where fechaNacimiento=" +pepePompin).list();
		

		}
		catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}
		finally {
			session.close();
		}
		return lista;
	} // end_public_traerNovioss
	
	
	Las funciones de agregaci�n que soporta HQL son:

	AVG(): Calcula el valor medio de todos los datos.
	SUM(): Calcula la suma de todos los datos.
	MIN(): Calcula el valor m�nimo de todos los datos.
	MAX(): Calcula el valor m�ximo de todos los datos.
	COUNT(): Cuanta el n� de datos.
	SELECT AVG(c.horas),SUM(c.horas),MIN(c.horas),MAX(c.horas),COUNT(*) FROM Ciclo c
	
	
	
	
	
	
	
	
	*/
    
    
    
    public static String contenidoDeTXT(String direccion) throws IOException {
    	
    	
    	File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String linea;
	      String resultado = "";

	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (direccion);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         
	         while((linea=br.readLine())!=null)
	            resultado = resultado +linea+"<br/>";
	     
	     
	      System.out.println(resultado);
    	
    	return resultado;
    }
    
  
    
    public static String traerFechaCortaConHora(GregorianCalendar fecha){
    	
String mes="";
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1<10){
    		mes = "0"+pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	if (fecha.get(GregorianCalendar.MONTH)+1>=10){
    		mes = pasarIntAString((fecha.get(GregorianCalendar.MONTH)+1));
    	}
    	
    	String dia="";
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)<10){
    		dia = "0"+pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	if (fecha.get(GregorianCalendar.DAY_OF_MONTH)>=10){
    		dia = pasarIntAString((fecha.get(GregorianCalendar.DAY_OF_MONTH)));
    	}
    	
    	String hora = "";
    	
    	if (fecha.get(GregorianCalendar.HOUR_OF_DAY)<10){
    		hora = "0"+pasarIntAString(fecha.get(GregorianCalendar.HOUR_OF_DAY));
    	}
    	
    	if (fecha.get(GregorianCalendar.HOUR_OF_DAY)>=10){
    		hora = pasarIntAString(fecha.get(GregorianCalendar.HOUR_OF_DAY));
    	}
    	
    	String minutos = "";
    	
    	if (fecha.get(GregorianCalendar.MINUTE)<10){
    		minutos = "0"+pasarIntAString(fecha.get(GregorianCalendar.MINUTE));
    	}
    	
    	String segundos = "";
    	
    	if (fecha.get(GregorianCalendar.SECOND)<10){
    		segundos = "0"+pasarIntAString(fecha.get(GregorianCalendar.SECOND));
    	}
    	
    	return (+fecha.get(GregorianCalendar.YEAR) +"-" +mes  +"-"  +dia+" "+hora+":00") ;
	    	} // end_public_fechaCorta
    



}

