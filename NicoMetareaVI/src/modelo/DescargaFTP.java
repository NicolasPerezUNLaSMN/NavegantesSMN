package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DescargaFTP {


public static boolean downloadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
 try {
 URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");
 URLConnection urlc = url.openConnection();
 InputStream is = urlc.getInputStream();
 BufferedWriter bw = new BufferedWriter(new FileWriter(localPath));
 int c;
 while ((c = is.read()) != -1) {
 bw.write(c);
 }
 is.close(); 
 bw.flush();
 bw.close();
 return true;
 } catch (Exception ex) {
 ex.printStackTrace();
 System.out.println(ex.getMessage());
 return false;
 }
 }


}


