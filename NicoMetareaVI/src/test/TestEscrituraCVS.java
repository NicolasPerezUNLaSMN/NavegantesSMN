package test;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class TestEscrituraCVS {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		
		//Guardando el Warning en el cvs
		String [] warning = {"1", "232", "sd","12-2", "dsad", "fdaa", "112121", "12121\r\n4234234\r\n\r\n33309", "222"};

		String archCSV = "C:\\Users\\nperez\\Desktop\\listaDeWarning.csv";
		@SuppressWarnings("deprecation")
		CSVWriter writer = new CSVWriter(new FileWriter(archCSV, true),';');
		

		writer.writeNext(warning);

		writer.close();
		//Fin del guardado

	}

}
