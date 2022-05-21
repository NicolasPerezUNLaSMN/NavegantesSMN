package test;
import java.io.File;


public class EliminarArchivo {

    public static void main(String args[]){
    	
    	
    	
    	   File dir = new File("C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Boletines");
    	   String cabeceraDir = "C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Boletines\\";
    	   String[] ficheros = dir.list();
    	 
    	   File dir2 = new File("C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Warning");
    	   String cabeceraDir2 = "C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Warning\\";
    	   String[] ficheros2 = dir2.list();
    	   
    	   
    	   File dir3 = new File("C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Navtex");
    	   String cabeceraDir3 = "C:\\Users\\nperez\\Desktop\\InformesMetareaVI\\Navtex\\";
    	   String[] ficheros3 = dir3.list();
    	

    	   
    	   for (String s: ficheros) {
    	   
        try{

            //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");

        	File archivo = new File(cabeceraDir +s);
        
            boolean estatus = archivo.delete();;

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

           }else{

                System.out.println("Se ha eliminado el archivo exitosamente");

           }

        }catch(Exception e){

           System.out.println(e);

        }
        
    	   } //Cierro el for
    	   
    	   
    	   
    	   
    	   for (String s: ficheros2) {
    	   
        try{

            //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");

        	File archivo = new File(cabeceraDir2 +s);
        
            boolean estatus = archivo.delete();;

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

           }else{

                System.out.println("Se ha eliminado el archivo exitosamente");

           }

        }catch(Exception e){

           System.out.println(e);

        }
        
    	   } //Cierro el for
    	   
    	   
    	   
    	   
    	   
    	   for (String s: ficheros3) {
    	   
        try{

            //File archivo = new File("C:\\carpeta1\\ejemplo1.txt");

        	File archivo = new File(cabeceraDir3 +s);
        
            boolean estatus = archivo.delete();;

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

           }else{

                System.out.println("Se ha eliminado el archivo exitosamente");

           }

        }catch(Exception e){

           System.out.println(e);

        }
        
    	   } //Cierro el for
    	   
    	   
    	   
    	   
    	   

    }//Cierra el main

} //cierra la clase