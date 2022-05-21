<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 
   
     <%@page import="modelo.Pronostico"%>
     <%@page import="modelo.Area"%>
     <%@page import="modelo.Warning"%>
	<%@page import="funciones.Funciones"%>
	
	 <%@page import="java.util.ArrayList"%>


 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<title>Generar Warning</title>
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
	 </script>
	
	
	<!--  
	 <style>
       /* Set the size of the div element that contains the map */
      #map {
        height: 200px;  /* The height is 400 pixels */
        width: 40%;  /* The width is the width of the web page */
       }
    </style>
	
	
	<script type="text/javascript">
	
	// Inicializa y agrega el mapa
	function initMap () {
	  // La ubicaci�n de Uluru
	  var uluru = {lat: -44, lng: -60.036};
	  // El mapa, centrado en Uluru
	  var map = new google.maps.Map (
	      document.getElementById ('map'), {zoom: 3, center: uluru});
	  // El marcador, posicionado en Uluru
	  //var marker = new google.maps.Marker ({position: uluru, map: map});
	}</script>
	
	
	-->
	
	
	
	
		

	
</head>



<body>
	<!-- SideBar -->
	<section class="full-box cover dashboard-sideBar">
		<div class="full-box dashboard-sideBar-bg btn-menu-dashboard"></div>
		<div class="full-box dashboard-sideBar-ct">
			<!--SideBar Title -->
			<div class="full-box text-uppercase text-center text-titles dashboard-sideBar-title">
				WARNING <i class="zmdi zmdi-close btn-menu-dashboard visible-xs"></i>
			</div>
			<!-- SideBar User info -->
			<div class="full-box dashboard-sideBar-UserInfo">
				<figure class="full-box">
					
					<figcaption class="text-center text-titles">Desarrollado por y para el SMN</figcaption>
				</figure>
				
				<ul class="full-box list-unstyled text-center">
					<li>
						<a href="#!">
							<i class="zmdi zmdi-settings"></i>
						</a>
					</li>
					<li>
						<a href="#!" class="btn-exit-system">
							<i class="zmdi zmdi-power"></i>
						</a>
					</li>
				</ul>
			</div>
			
			
	
			
			
			
			<!-- SideBar Menu -->
			<ul class="list-unstyled full-box dashboard-sideBar-Menu">
				<li>
					<a href="inicioVisual.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Inicio
					</a>
				</li>
				
				
				
			</ul>
		</div>
	</section>
	
	
	
	

		
		
<!-- Content page -->

<section class="full-box dashboard-contentPage">
		<!-- NavBar -->
		<nav class="full-box dashboard-Navbar">
			<ul class="full-box list-unstyled text-right">
				<li class="pull-left">
					<a href="#!" class="btn-menu-dashboard"><i class="zmdi zmdi-more-vert"></i></a>
				</li>
				<li>
					<a href="#!" class="btn-Notifications-area">
						<i class="zmdi zmdi-notifications-none"></i>
						<span class="badge">1</span>
					</a>
				</li>
				<li>
					<a href="#!" class="btn-search">
						<i class="zmdi zmdi-search"></i>
					</a>
				</li>
				<li>
					<a href="#!" class="btn-modal-help">
						<i class="zmdi zmdi-help-outline"></i>
					</a>
				</li>
			</ul>
		</nav>
		
		<div class="container-fluid">
			<div class="page-header">
			  <h1 class="text-titles"><i class="zmdi zmdi-balance zmdi-hc-fw"></i> WARNING <small>de su warning.</small></h1>
			</div>
			<p class="lead">Elija con que warning quiere seguir trabajando o cree uno nueva.</p>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					
					  	<li class="active"><a href="#avisos" data-toggle="tab"><i class="zmdi zmdi-balance"></i> Sus warning</a></li>
					  	<li><a href="#nuevoAviso" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Nueva warning</a></li>
					  	
					  
					  	
					</ul>
					
					
					
					
					<% Warning w = (Warning)request.getAttribute("w"); %>
					
					
					
					<div class="tab-content">
					
					
						<div class="tab-pane fade active in" id="avisos">
						
						<div class="table-responsive">
								<table id="Tabla1" class="table table-hover text-center">
									<thead>
										<tr>
											<th class="text-center">#</th>
											<th class="text-center">numero:</th>
											<th class="text-center">actualizacion:</th>
											<th class="text-center">Dominio:</th>
											<th class="text-center">Tipo:</th>
											<th class="text-center">�que es?:</th>
											<th class="text-center">areas:</th>
											<th class="text-center">Seguir...</th>
											<th class="text-center">Borrar</th>
										</tr>
									</thead>
									<tbody>
									
		
		                             
		
										<tr>
											<td><%= w.getIdWarning() %></td>
											<td><%= w.getNumeroWarning() %></td>
											<td><%= w.getActualizacion() %></td>
											<td><%= w.getDominio() %></td>
											<td><%= w.getTipo() %></td>
											<td><%= w.getFenomeno() %></td>
											<td><%= w.getAreas() %></td>
										
											
											
											
											<td>
											
											<form method="POST" action=" /UML-DC/ControladorVerClases">
											
											 <INPUT type="hidden" name="idVersion" value="23232" />
											 <INPUT type="hidden" name="contrasenia" value="ssssdfggg" />
											 <INPUT type="hidden" name="emailCreador" value="sfdf" />
											 
											<button  type="submit"  class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i> </button>
											</form>
											</td>
											
											
											<td>
											<form method="POST" action=" /UML-DC/ControladorBorrarVersion">
											<INPUT type="hidden" name="idVersion" value="55556" />
											<button type="submit"  class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
											
										</tr>
										
								
									
										
	
		

										
									</tbody>
								</table>
								
							</div>
							
						</div>
						
						
						<div class="tab-pane fade" id="nuevoAviso">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12 col-md-10 col-md-offset-1">
									
				
					
				
									
									
									
									   <form method="POST" action=" /NicoMetareaVI/ControladorAgregarWarning">
									    
									    
									     
									     
									
									     
									     	<div class="form-group">
										        <label class="control-label">Tipo de WARNING(*):</label>
										        <select class="form-control" name="tipo">
										        
										        	<option>GALE FORCE - 8 o 9 BEAUFORT - 34 a 47 kt</option>
										          	<option>STORM FORCE - 10 o 11 BEAUFORT - 48 a 63 kt</option>
										          	<option>HURRICANE FORCE - +11 BEAUFORT - +63 kt</option>
										          	
										        </select>
										    </div>
										    
										    
										    
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
											
										    
										        </select>
										    </div>
										    
										    
					
									    	<div class="form-group label-floating">
											  <label class="control-label">Valor central, optativo hpa:</label>
											  <input id = "valorCentral"class="form-control" type="text" name="valorCentral" disabled>
											</div>
											
											
											
											
											
											<!-- Ver o no coordenadas -->
											
											<input type="button" value="Agregar coordenadas iniciales" onclick="mostrar()">
											
											<div id='ver' style="display:none;">
											
											
											<div id="close"><a href="javascript:cerrar();">Cerrar coordenadas iniciales</a></div>
											
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">Latitud1:</label>
											  <input class="form-control" type="number" name="latitud1" required>
											  
											</div>
											
											
											
											<div class="form-group label-floating">
											  <label class="control-label">Longitud1:</label>
											  <input class="form-control" type="number" name="longitud1" required>
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
											  <input class="form-control" type="number" name="longitudF1">
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
											
											
								
									<div class="form-group label-floating" >
									
									<label class="control-label">WARNING - Presente o Futuro(*):</label>
								        <label>
								            <input type="radio" name="queHace"  value="Provoca" required> Provoca
								        </label>
								        <label>
								            <input type="radio" name="queHace" value="Provocara"> Provocara
								        </label>
								       
								        
									</div>
									
									
										<label class="control-label">Areas afectadas(*):</label>								
									<select  multiple class="form-control" name="areas" required>
									    <option value="BAHIA BLANCA">BAHIA BLANCA</option>
										<option value="CARMEN DE PATAGONES">CARMEN DE PATAGONES</option>
										<option value="COMODORO RIVADAVIA">COMODORO RIVADAVIA</option>
										<option value="COSTA BAHIA BLANCA">COSTA BAHIA BLANCA</option>
										<option value="COSTA CARMEN PATAGONES">COSTA CARMEN PATAGONES</option>
										<option value="COSTA DEL TUYU">COSTA DEL TUYU</option>
										<option value="COSTA DESEADO SUR">COSTA DESEADO SUR</option>
										<option value="COSTA GALLEGOS">COSTA GALLEGOS</option>
										<option value="COSTA GESELL">COSTA GESELL</option>
										<option value="COSTA ISLA DE LOS ESTADOS">COSTA ISLA DE LOS ESTADOS</option>
										<option value="COSTA MAR DEL PLATA">COSTA MAR DEL PLATA</option>
										<option value="COSTA NECOCHEA">COSTA NECOCHEA</option>
										<option value="COSTA RAWSON">COSTA RAWSON</option>
										<option value="COSTA RIO GRANDE">COSTA RIO GRANDE</option>
										<option value="COSTA SAN JULIAN">COSTA SAN JULIAN</option>
										<option value="COSTA SANTA CRUZ">COSTA SANTA CRUZ</option>
										<option value="COSTA VALDES">COSTA VALDES</option>
										<option value="COSTA VIEDMA">COSTA VIEDMA</option>
										<option value="DESEMBOCADURA RIO DE LA PLATA">DESEMBOCADURA RIO DE LA PLATA</option>
										<option value="GOLFO NUEVO">GOLFO NUEVO</option>
										<option value="GOLFO SAN JORGE NORTE">GOLFO SAN JORGE NORTE</option>
										<option value="GOLFO SAN JORGE SUR">GOLFO SAN JORGE SUR</option>
										<option value="GOLFO SAN JOSE">GOLFO SAN JOSE</option>
										<option value=" GOLFO SAN MATIAS"> GOLFO SAN MATIAS</option>
										<option value="MALVINAS">MALVINAS</option>
										<option value="MAR DEL PLATA">MAR DEL PLATA</option>
										<option value="NECOCHEA">NECOCHEA</option>
										<option value="OFFSHORE BAHIA BLANCA">OFFSHORE BAHIA BLANCA</option>
										<option value="OFFSHORE FIN DEL MUNDO">OFFSHORE FIN DEL MUNDO</option>
										<option value="OFFSHORE MAR DEL PLATA">OFFSHORE MAR DEL PLATA</option>
										<option value="OFFSHORE PATAGONIA SUR">OFFSHORE PATAGONIA SUR</option>
										<option value="OFFSHORE RIO DE LA PLATA">OFFSHORE RIO DE LA PLATA</option>
										<option value="OFFSHORE SAN JORGE">OFFSHORE SAN JORGE</option>
										<option value="OFFSHORE VALDES">OFFSHORE VALDES</option>
										<option value="PUERTO DESEADO">PUERTO DESEADO</option>
										<option value="PUERTO MADRYN">PUERTO MADRYN</option>
										<option value="PUERTO SAN JULIAN">PUERTO SAN JULIAN</option>
										<option value="PUERTO SAN JULIAN">PUERTO SAN JULIAN</option>
										<option value="PUERTO SANTA CRUZ">PUERTO SANTA CRUZ</option>
										<option value="PUNTA INDIO">PUNTA INDIO</option>
										<option value="RAWSON">RAWSON</option>
										<option value="RIO DE LA PLATA EXTERIOR">RIO DE LA PLATA EXTERIOR</option>
										<option value="RIO DE LA PLATA INTERIOR">RIO DE LA PLATA INTERIOR</option>
										<option value="RIO DE LA PLATA INTERMEDIO">RIO DE LA PLATA INTERMEDIO</option>
										<option value="RIO GALLEGOS">RIO GALLEGOS</option>
										<option value="RIO GRANDE">RIO GRANDE</option>
										<option value="SAN ANTONIO OESTE">SAN ANTONIO OESTE</option>
										<option value="SANTA TERESITA">SANTA TERESITA</option>
										<option value="TOLHUIN">TOLHUIN</option>
										<option value="USHUAIA">USHUAIA</option>
										<option value="VIEDMA">VIEDMA</option>
										<option value="VILLA GESELL">VILLA GESELL</option>
										<option value="Area Centro Este (N 45 E 30)">Area Centro Este (N 45 E 30)</option>
										<option value="Area Centro Este (N 45 W 30)">Area Centro Este (N 45 W 30)</option>
										<option value="Area Centro Este (S 45 E 30)">Area Centro Este (S 45 E 30)</option>
										<option value="Area Centro Este (S 45 W 30)">Area Centro Este (S 45 W 30)</option>
										<option value="Area Centro Oeste (N 45 E 50)">Area Centro Oeste (N 45 E 50)</option>
										<option value="Area Centro Oeste (N 45 W 50)">Area Centro Oeste (N 45 W 50)</option>
										<option value="Area Centro Oeste (S 45 E 50)">Area Centro Oeste (S 45 E 50)</option>
										<option value="Area Centro Oeste (S 45 W 50)">Area Centro Oeste (S 45 W 50)</option>
										<option value="Area Norte (E 30)">Area Norte (E 30)</option>
										<option value="Area Norte (E 40 W 30)">Area Norte (E 40 W 30)</option>
										<option value="Area Norte (W 40)">Area Norte (W 40)</option>
										<option value="Area Sudeste (N 55 E 30)">Area Sudeste (N 55 E 30)</option>
										<option value="Area Sudeste (N 55 W 30)">Area Sudeste (N 55 W 30)</option>
										<option value="Area Sudeste (S 55 E 30)">Area Sudeste (S 55 E 30)</option>
										<option value="Area Sudeste (S 55 W 30)">Area Sudeste (S 55 W 30)</option>
										<option value="Area Sudoeste (N 55 E 50)">Area Sudoeste (N 55 E 50)</option>
										<option value="Area Sudoeste (N 55 W 50)">Area Sudoeste (N 55 W 50)</option>
										<option value="Area Sudoeste (S 55 E 50)">Area Sudoeste (S 55 E 50)</option>
										<option value="Area Sudoeste (S 55 W 50)">Area Sudoeste (S 55 W 50)</option>
										<option value="ZonaDrake">ZonaDrake</option>
									</select>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha inicial</label>
											  <input class="form-control" type="datetime-local" name="fechaInicial" required>
											</div>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha final</label>
											  <input class="form-control" type="datetime-local" name="fechaFinal" required>
											</div>
											
											
											
											
											
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Generar Warning</button>
										    </p>
										    
									    </form>
									</div>
								</div>
							</div>
						</div>
						
						
						
					  	<div class="tab-pane fade" id="listYear">
							<div class="table-responsive">
								<table class="table table-hover text-center">
									<thead>
										<tr>
											<th class="text-center">#</th>
											<th class="text-center">Year</th>
											<th class="text-center">Start Date</th>
											<th class="text-center">End Date</th>
											<th class="text-center">Update</th>
											<th class="text-center">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>2017</td>
											<td>23/01/2017</td>
											<td>07/11/2017</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
										<tr>
											<td>2</td>
											<td>2016</td>
											<td>23/01/2016</td>
											<td>07/11/2016</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
										<tr>
											<td>3</td>
											<td>2015</td>
											<td>23/01/2015</td>
											<td>07/11/2015</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
									</tbody>
								</table>
								<ul class="pagination pagination-sm">
								  	<li class="disabled"><a href="#!">«</a></li>
								  	<li class="active"><a href="#!">1</a></li>
								  	<li><a href="#!">2</a></li>
								  	<li><a href="#!">3</a></li>
								  	<li><a href="#!">4</a></li>
								  	<li><a href="#!">5</a></li>
								  	<li><a href="#!">»</a></li>
								</ul>
							</div>
					  	</div>
					  	<div class="tab-pane fade" id="newPeriod">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12 col-md-10 col-md-offset-1">
									    <form action="">
									    	<div class="form-group label-floating">
											  <label class="control-label">Name</label>
											  <input class="form-control" type="text">
											</div>
											<div class="form-group label-floating">
											  <label class="control-label">Status</label>
											  <input class="form-control" type="text">
											</div>
											<div class="form-group">
											  <label class="control-label">Start Date</label>
											  <input class="form-control" type="date">
											</div>
											<div class="form-group">
											  <label class="control-label">End Date</label>
											  <input class="form-control" type="date">
											</div>
											<div class="form-group label-floating">
											  <label class="control-label">Amount</label>
											  <input class="form-control" type="text">
											</div>
											<div class="form-group">
										        <label class="control-label">Year</label>
										        <select class="form-control">
										          	<option>2017</option>
										          	<option>2016</option>
										          	<option>2015</option>
										        </select>
										    </div>
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Save</button>
										    </p>
									    </form>
									</div>
								</div>
							</div>
						</div>
					  	<div class="tab-pane fade" id="listPeriod">
							<div class="table-responsive">
								<table class="table table-hover text-center">
									<thead>
										<tr>
											<th class="text-center">#</th>
											<th class="text-center">Name</th>
											<th class="text-center">Status</th>
											<th class="text-center">Start Date</th>
											<th class="text-center">End Date</th>
											<th class="text-center">Amount</th>
											<th class="text-center">Year</th>
											<th class="text-center">Update</th>
											<th class="text-center">Delete</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Period 1</td>
											<td>Active</td>
											<td>23/01/2017</td>
											<td>23/03/2017</td>
											<td>$40</td>
											<td>2017</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
										<tr>
											<td>2</td>
											<td>Period 2</td>
											<td>Active</td>
											<td>24/03/2017</td>
											<td>23/06/2017</td>
											<td>$40</td>
											<td>2017</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
										<tr>
											<td>3</td>
											<td>Period 3</td>
											<td>Active</td>
											<td>24/06/2017</td>
											<td>23/09/2017</td>
											<td>$40</td>
											<td>2017</td>
											<td><a href="#!" class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i></a></td>
											<td><a href="#!" class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i></a></td>
										</tr>
									</tbody>
								</table>
								<ul class="pagination pagination-sm">
								  	<li class="disabled"><a href="#!">«</a></li>
								  	<li class="active"><a href="#!">1</a></li>
								  	<li><a href="#!">2</a></li>
								  	<li><a href="#!">3</a></li>
								  	<li><a href="#!">4</a></li>
								  	<li><a href="#!">5</a></li>
								  	<li><a href="#!">»</a></li>
								</ul>
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
		        	<button type="button" class="btn btn-primary btn-raised" data-dismiss="modal"><i class="zmdi zmdi-thumb-up"></i> Entendido</button>
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


</html>