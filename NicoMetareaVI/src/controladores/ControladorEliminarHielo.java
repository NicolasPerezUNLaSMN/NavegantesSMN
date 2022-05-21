package controladores;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.FenomenoABM;
import negocio.HieloABM;






public class ControladorEliminarHielo extends HttpServlet {
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
procesarPeticion(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
procesarPeticion(request, response);
}



@SuppressWarnings("null")
private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			
			
			Logger.getLogger("org.hibernate").setLevel(Level.OFF);
			
			
			try {
				
				
				int idHielo = Integer.parseInt(request.getParameter("idHielo"));
				
				
				System.out.println("------------------------" +idHielo);
				
				HieloABM w = new HieloABM ();
				
				
				w.eliminar(idHielo);
				
				
				

				//request.setAttribute("w", w);
				request.getRequestDispatcher("/indexHielo.jsp").forward(request , response);
			
			
			} catch (
			Exception e) {
				
			request.getRequestDispatcher("/indexHielo.jsp").forward(request , response);
			//response.sendError(500, "Los datos son erroneos.");
			}
			}
			
}




