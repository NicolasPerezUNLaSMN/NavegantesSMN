<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 
   
     <%@page import="modelo.Pronostico"%>
     <%@page import="modelo.Area"%>
     <%@page import="modelo.Fenomeno"%>
	<%@page import="funciones.Funciones"%>
	
	 <%@page import="java.util.ArrayList"%>
	 
	 
	 <%@page import= "modelo.Boletin"%>
		<%@page import= "modelo.Warning"%>
		<%@page import= "negocio.FenomenoABM"%>
		<%@page import= "negocio.WarningABM"%>
		<%@page import= "java.util.GregorianCalendar"%>



 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>

	<link rel="shortcut icon" type="image/png" href="https://img.icons8.com/cotton/64/000000/cargo-ship--v2.png"/>
<title>Generar Situacion</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="./css/main.css">
	
	<script type="text/javascript" src="jquery.js"></script>
	<script type="text/javascript" src="jquery_plantuml.js"></script>
	
	<script src="http://code.responsivevoice.org/responsivevoice.js"></script>
	
	
	
	
	
	
	<script type="text/javascript">
	 function mostrar() {
            div = document.getElementById('ver');
            div.style.display = '';
        }
	 
	  function cerrar() {
          div = document.getElementById('ver');
          div.style.display = 'none';
      }
	  
	  function mostrarF() {
          div = document.getElementById('verF');
          div.style.display = '';
      }
	 
	  function cerrarF() {
        div = document.getElementById('verF');
        div.style.display = 'none';
    }
	  
	  
	  
	  function mostrar2() {
          div = document.getElementById('ver2');
          div.style.display = '';
      }
	 
	  function cerrar2() {
        div = document.getElementById('ver2');
        div.style.display = 'none';
    }
	  
	  function mostrarF2() {
        div = document.getElementById('verF2');
        div.style.display = '';
    }
	 
	  function cerrarF2() {
      div = document.getElementById('verF2');
      div.style.display = 'none';
  }
	  
	  
	  
	  function mostrar3() {
          div = document.getElementById('ver3');
          div.style.display = '';
      }
	 
	  function cerrar3() {
        div = document.getElementById('ver3');
        div.style.display = 'none';
    }
	  
	  function mostrarF3() {
        div = document.getElementById('verF3');
        div.style.display = '';
    }
	 
	  function cerrarF3() {
      div = document.getElementById('verF3');
      div.style.display = 'none';
  }
	 </script>
	
	

	
	
	
	
		

	
</head>



<body>
	<!-- SideBar -->
	<section class="full-box cover dashboard-sideBar">
		<div class="full-box dashboard-sideBar-bg btn-menu-dashboard"></div>
		<div class="full-box dashboard-sideBar-ct">
			<!--SideBar Title -->
			<div class="full-box text-uppercase text-center text-titles dashboard-sideBar-title">
				SITUACI�N <i class="zmdi zmdi-close btn-menu-dashboard visible-xs"></i>
			</div>
			<!-- SideBar User info -->
			<div class="full-box dashboard-sideBar-UserInfo">
				
				
				
				<ul class="full-box list-unstyled text-center">
					<li>
						<a href="enviar.jsp">
							Enviar  <i class="zmdi zmdi-play"></i>
						</a>
					</li>
					  
					  
					  
				</ul>
			</div>
			
			
			
	
			
			
			
			<!-- SideBar Menu -->
			<ul class="list-unstyled full-box dashboard-sideBar-Menu">
				<li>
					<a href="index.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Warning
					</a>
				</li>
				
				
				<li>
					<a href="indexSituacion.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Situaci�n
					</a>
				</li>
				
				
				<li>
					<a href="indexHielo.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> L�mite hielos
					</a>
				</li>
				
				
				<li>
					<a href="indexVerBoletines.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Ver ultimos boletines
					</a>
				</li>
				
				
					<li>
					<a href="indexSubirPronostico.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Nuevo mail a enviar
					</a>
				</li>
				
				
				<li>
					<a href="indexVerificarXML.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Verificar XML
					</a>
				</li>
				
				
					<li>
					<a href="https://1drv.ms/u/s!AgB0dw0E7wKakMx0OFLXlx5Hnr44RQ?e=dGvOAH">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Manual AudioVisual
					</a>
				</li>
				
					<li>
					<a href="https://1drv.ms/u/s!AgB0dw0E7wKakMx1OYXv74b3cqz35w?e=WM3hyP">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Manual - Imprimible
					</a>
				</li>
				
				<li>
					<a href="estadisticasWarning.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Estad�sticas
					</a>
				</li>
				
				
				
				
				
			</ul>
		</div>
	</section>
	
	
	
	

		
		
<!-- Content page -->

<section class="full-box dashboard-contentPage">
		<!-- NavBar -->
		<ul class="full-box list-unstyled text-right">
			
			
				
				
				<li>
					<a href="#!" class="btn-modal-help">
						<i class="zmdi zmdi-notifications-none"></i>
						<% FenomenoABM fenomenoABMCantidad = new FenomenoABM(); 
						
						int cantidad = fenomenoABMCantidad.traerFenomeno().size(); %>
						<span class="badge"><%=cantidad %></span>
					</a>
				</li>
				
				
			</ul>
		

		
		<table summary="main" border="0" cellspacing="2" cellpadding="0">
		
		<tr>
		
		<td >
		<img src="https://wmail.smn.gov.ar/skins/outlook/skin/images/login.jpg"  alt="UserIcon"> 
		</td>
		
		<td >
		<a href="http://weather.gmdss.org/" target="_blank">
		<img src="https://www.un.org/ldcportal/wp-content/uploads/2016/12/wmo-1.jpg" alt="WMO" border="0" width="100" height="100">
		</a>
		</td>
		
		<td>
		<a href="http://www.jcomm.info/" target="_blank">
		<img src="http://www.uk-ioc.org/uk-ioc/sites/uk-ioc/files/images/JCOMM-logo.jpg" alt="JCOMM" border="0" width="100" height="100">
		</a>
		</td>
		</tr>
		
		</table>
		
		<div class="container-fluid">
			<div class="page-header">
			  <h1 class="text-titles"><i class="zmdi zmdi-balance zmdi-hc-fw"></i> SITUACI�N <small>administraci�n de los fenomenos.</small></h1>
			</div>
			<p class="lead">Crear o borrar un fenomeno.</p>
				<a href="indexSituacion.jsp">Refrescar web.</a>
		</div>
		
		
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					
					  	<li class="active"><a href="#fenomenos" data-toggle="tab"><i class="zmdi zmdi-balance"></i> Sus fenomenos</a></li>
					  	<li><a href="#nuevoFenomeno" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Nuevo Fenomeno</a></li>
					  	
					  	
					</ul>
					
					
					
					
					
					
					
					
					<div class="tab-content">
					
					
					
					
					
					
						<div class="tab-pane fade active in" id="fenomenos">
						
						<div class="table-responsive">
								<table id="Tabla1" class="table table-hover text-center">
									<thead>
									
		
					
									
									
										<tr>
										    <th class="text-center">#</th>
											<th class="text-center">Texto:</th>
											
											<th class="text-center">Borrar</th>
										</tr>
									</thead>
									<tbody>
									
									
									
									<%FenomenoABM f = new FenomenoABM(); %>
									
									
								
									
									<%for (Fenomeno fenomeno : f.traerFenomeno()) { %>
									
									
											<tr>
											<td><%= fenomeno.getIdFenomeno() %></td>
											<td><%= fenomeno.getTexto() %></td>
											<td>
											<form method="POST" action=" /NicoMetareaVI/ControladorEliminarFenomeno">
											<INPUT type="hidden" name="idFenomeno" value=<%=fenomeno.getIdFenomeno() %> />
											<button type="submit"  class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
						
											
											
											
										</tr>
										
										
									
												
										<%} //cierra el for%>	
										
									
	
		

										
									</tbody>
								</table>
								
							</div>
							
							
							
						
									    
									    
							
						</div>
						
						
									
						
						<div class="tab-pane fade" id="nuevoFenomeno">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12 col-md-10 col-md-offset-1">
									
				
					
				
									
									
									
									   <form method="POST" action=" /NicoMetareaVI/ControladorAgregarFenomeno">
									    
									  
									     
									     
									
									     
									     
										    
										    
										    
										    <div class="form-group">
										        <label class="control-label">Fenomeno que lo causa(*):</label>
										        
										        <select class="form-control" name="fenomeno" onchange="if(this.value=='DEPRESION'||this.value=='ANTICICLON') {document.getElementById('valorCentral').disabled = false} else {document.getElementById('valorCentral').disabled = true}">
										        <option value="DEPRESION">DEPRESION</option>
												<option value="ANTICICLON">ANTICICLON </option>
												<option value="FRENTE">FRENTE</option>
												<option value="FRENTE FRIO">FRENTE FRIO</option> 
												<option value="FRENTE CALIENTE">FRENTE CALIENTE</option> 
												<option value="FRENTE OCLUIDO">FRENTE OCLUIDO</option>
												<option value="CICLOGENESIS">CICLOGENESIS</option>
												<option selected value="FUERTE GRADIENTE BARICO">FUERTE GRADIENTE BARICO</option>
												<option value="EJE DE CU�A">EJE DE CU�A</option>
												<option value="EJE DE VAGUADA">EJE DE VAGUADA</option>
											
										    
										        </select>
										    </div>
										    
										    
					
									    	<div class="form-group label-floating">
											  <label class="control-label">Valor central, optativo hpa:</label>
											  <input id = "valorCentral"class="form-control" type="text" name="valorCentral" disabled>
											</div>
											
											
											
											
											
											  <div class="form-group">
										        <label class="control-label">Movimiento (optativo):</label>
										        
										        <select class="form-control" name="mov" >
										        <option value="N">N</option>
												<option value="NE">NE </option>
												<option value="E">E</option>
												<option value="SE">SE</option> 
												<option value="S">S</option> 
												<option value="SW">SW</option>
												<option value="W">W</option>
												<option value="NO">NW</option>
												<option selected value="SIN MOV">SIN MOV</option>
											
										    
										        </select>
										    </div>
										    
										    
										    
										      <div class="form-group">
										        <label class="control-label">Evoluci�n (optativo):</label>
										        
										        <select class="form-control" name="evo" >
										        <option value="BLDN">Formandose</option>
												<option value="INTSF">Intensificandose </option>
												<option value="DPN">Profundizandose</option>
												<option value="WKN">Debilitandose</option> 
												<option value="NC">Sin cambio</option> 
												
												<option selected value="SIN EVO">SIN EVO</option>
											
										    
										        </select>
										    </div>
											
											
											
											
											
											<!-- Ver o no coordenadas -->
											
											<input type="button" value="Agregar coordenadas iniciales" onclick="mostrar()">
											
											<div id='ver' style="display:none;">
											
											
											<div id="close"><a href="javascript:cerrar();">Cerrar coordenadas iniciales</a></div>
											
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">Latitud1:</label>
											  <input class="form-control" type="number" name="latitud1" >
											  
											</div>
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">Longitud1:</label>
											  <input class="form-control" type="number" name="longitud1" >
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Latitud2:</label>
											  <input class="form-control" type="number" name="latitud2">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Longitud2:</label>
											  <input class="form-control" type="number" name="longitud2">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Latitud3:</label>
											  <input class="form-control" type="number" name="latitud3">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Longitud3:</label>
											  <input class="form-control" type="number" name="longitud3">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Latitud4:</label>
											  <input class="form-control" type="number" name="latitud4">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">Longitud4:</label>
											  <input class="form-control" type="number" name="longitud4">
											</div>
											
											</div>
											
											<!-- Ver o no coordenadas -->
											
											
											<!-- Ver o no coordenadas  finales-->
											
											<input type="button" value="Agregar coordenadas finales" onclick="mostrarF()">
											
											<div id='verF' style="display:none;">
											
											
											<div id="closeF"><a href="javascript:cerrarF();">Cerrar coordenadas finales</a></div>
											
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">LatitudF1:</label>
											  <input class="form-control" type="number" name="latitudF1">
											  
											</div>
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">LongitudF1:</label>
											  <input class="form-control" type="number" name="longitudF1" >
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LatitudF2:</label>
											  <input class="form-control" type="number" name="latitudF2">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LongitudF2:</label>
											  <input class="form-control" type="number" name="longitudF2">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LatitudF3:</label>
											  <input class="form-control" type="number" name="latitudF3">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LongitudF3:</label>
											  <input class="form-control" type="number" name="longitudF3">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LatitudF4:</label>
											  <input class="form-control" type="number" name="latitudF4">
											</div>
											
											<div class="form-group label-floating">
											  <label class="control-label">LongitudF4:</label>
											  <input class="form-control" type="number" name="longitudF4">
											</div>
											
											<!-- 
											<div class="form-group">
											  <label class="control-label">Fecha final de las coordenadas</label>
											  <input class="form-control" type="datetime-local" name="fechaFinalCoordenadas" >
											</div>
											 -->
											
											</div>
											
											<!-- Ver o no coordenadas -->
											
											
								
								
									
									
											
											<div class="form-group">
											  <label class="control-label">Posicion inicial - Fecha y hora:</label>
											  <input class="form-control" type="datetime-local" name="fechaInicial" >
											</div>
											
											
											<div class="form-group">
											  <label class="control-label">Posicion final - Fecha y hora:</label>
											  <input class="form-control" type="datetime-local" name="fechaFinal" >
											</div>
											
											
											
												<div class="form-group label-floating" >
									
									<label class="control-label">Incluir o no al navtex:</label>
								        <label>
								            <input type="radio" name="navtex"  value="Incluir" required> Incluir
								        </label>
								        <label>
								            <input type="radio" name="navtex" value="No incluir"> No incluir
								        </label>
								       
								        
									</div>
											
											
											
											
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Agregar Fenomeno</button>
										    </p>
										    
									    </form>
									</div>
								</div>
							</div>
						</div>
						
						
						
						
											
						
						
						
						
					
					  	
					  	
					</div>
				</div>
			</div>
		</div>
		
		
			
		
		
		
		
	</section>
		
		
		
		
		
		
		
		
		
		
					

		
		
		
	</section>
	
	

	<!-- Notifications area -->
	<section class="full-box Notifications-area">
		<div class="full-box Notifications-bg btn-Notifications-area"></div>
		<div class="full-box Notifications-body">
		
		
			<div class="Notifications-body-title text-titles text-center">
				Notificaciones <i class="zmdi zmdi-close btn-Notifications-area"></i>
			</div>
			
			
			<div class="list-group">
			  	<div class="list-group-item">
			  	
			  	
				    
				  
				    
				    
				    	
				    
				    
			  	</div>
			  	<div class="list-group-separator"></div>
			  	
			  	
			
			  	
			  	

		</div>
	</section>
	
	
	

	<!-- Dialog help -->
	<div class="modal fade" tabindex="-1" role="dialog" id="Dialog-Help">
	  	<div class="modal-dialog" role="document">
		    <div class="modal-content">
		    
		    
		    
		    <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			    	<h4 class="modal-title">Ayuda</h4>
			    </div>
			    <div class="modal-body">
			        <p>
			        	Cualquier error dejelo plasmado en: 
			        </p>
			        
			        <a href="https://docs.google.com/spreadsheets/d/1H__hbZZQBZTzIGQ17--n6yNVnNbNmhtgMt9aTtD5_ag/edit?usp=sharing" target="_blank"> Comentarios </a>
			    </div>
		      	<div class="modal-footer">
		        	
		      	</div>
		    
		    
		    
		    
		    
			    <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			    	<h4 class="modal-title">Situacion sinoptica: </h4>
			    </div>
			    
			    
			    <div class="modal-body">
			    
			    
			    <%for (Fenomeno fenomeno : f.traerFenomeno()) { %>
			    
			    
			    
			    <div class="list-group-item" ALIGN="justify">
			  	
			  	
				    
				  
			        	<h6> <%= fenomeno.getTexto() %> </h6>
			     
				    
				    
				    	
				    
				    
			  	</div>
			  	<div class="list-group-separator"></div>
			    
			    <%}; %>
			    
			    
			     </div>
			    
			    
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-primary btn-raised" data-dismiss="modal"><i class="zmdi zmdi-thumb-up"></i> Ok</button>
		      	</div>
		    </div>
	  	</div>
	</div>
	
	
	<!--====== Scripts -->
	<script src="http://code.responsivevoice.org/responsivevoice.js"></script>
	<script src="./js/jquery-3.1.1.min.js"></script>
	<script src="./js/sweetalert2.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/material.min.js"></script>
	<script src="./js/ripples.min.js"></script>
	<script src="./js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="./js/main.js"></script>
	<script>
		$.material.init();
	</script>
</body>


</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         