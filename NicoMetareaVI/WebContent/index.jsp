<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 
   
     <%@page import="modelo.Pronostico"%>
     <%@page import="modelo.Area"%>
     <%@page import="modelo.Warning"%>
	<%@page import="funciones.Funciones"%>
	
	 <%@page import="java.util.ArrayList"%>
	 
	 
	 <%@page import= "modelo.Boletin"%>
		<%@page import= "modelo.Warning"%>
		<%@page import= "negocio.BoletinABM"%>
		<%@page import= "negocio.WarningABM"%>
		<%@page import= "java.util.GregorianCalendar"%>
		<%@page import= "java.util.Calendar"%>



 
    
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

		<%   %>


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
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Situación
					</a>
				</li>
				
				<li>
					<a href="indexHielo.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Límite hielos
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
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Estadísticas
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
						<% WarningABM warningABMCantidad = new WarningABM(); 
						int cantidad = warningABMCantidad.traerWarningActivos().size(); %>
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
			  <h1 class="text-titles"><i class="zmdi zmdi-balance zmdi-hc-fw"></i> WARNING <small>administración de los warning.</small></h1>
			</div>
			<p class="lead">Cesar, actualizar  o crear un warning.</p>
			
			
			
			<a href="index.jsp">Refrescar web.</a>
		
			
		</div>
		
		
		
		
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					
					  	<li class="active"><a href="#avisos" data-toggle="tab"><i class="zmdi zmdi-balance"></i> Sus warning</a></li>
					  	<li><a href="#nuevoAviso" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Nuevo Warning HIGH SEA</a></li>
					  	<li><a href="#nuevoAvisoOffShore" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Nuevo Warning OFF SHORE</a></li>
					    <li><a href="#nuevoAvisoCosta" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Nuevo Warning COSTA</a></li>
					  	
					</ul>
					
					
					
					
					
					
					
					
					<div class="tab-content">
					
					
					
					
					
					
						<div class="tab-pane fade active in" id="avisos">
						
						<div class="table-responsive">
						
						
						
								<table id="Tabla1" class="table table-hover text-center">
									<thead>
									
		
					
									
									
										<tr>
										    
											<th class="text-center">Numero:</th>
											<th class="text-center">Actualizacion:</th>
											<th class="text-center">Dominio:</th>
											<th class="text-center">Tipo:</th>
											<th class="text-center">¿Qué es?:</th>
											<th class="text-center">Areas:</th>
											<th class="text-center">X</th>
											<th class="text-center">Actualizar</th>
											<th class="text-center">Cesar</th>
										</tr>
									</thead>
									<tbody>
									
									<%BoletinABM b = new BoletinABM(); %>
									<%WarningABM wABM = new WarningABM();
									int hora = 12;%>
									
									<%Boletin boletin; %>
									
									<% GregorianCalendar fecha = new GregorianCalendar(); 
									if(  fecha.get(Calendar.HOUR_OF_DAY) <19 && fecha.get(Calendar.HOUR_OF_DAY) >6) {
										boletin = b.traerBoletin(fecha,12); 
									}else {fecha.add(Calendar.DAY_OF_MONTH,1); hora = 0;
									boletin = b.traerBoletin(fecha,0);}%>
									
									
									
									<%if (boletin==null) {
							           b.agregar(fecha, hora);
							           boletin = b.traerBoletin(fecha,hora);
									} 
									
									else { %>
									
									<%Boletin boletinConWarning = b.traerBoletinYListaWarning(boletin.getIdBoletin());%>
									<%int id = boletinConWarning.getIdBoletin(); %>
									
									<%for (Warning w : boletinConWarning.getListaWarning()) { if(w.getActivo()==1){%>
									
									
											<tr>
											
											
											
											
											
											
											
											
											<td><%= w.getNumeroWarning() %></td>
											<td><%= w.getActualizacion() %></td>
											<td><%= w.getDominio() %></td>
											<td><%= w.getTipo() %></td>
											<td><%= w.getFenomeno() %></td>
											<td><%= w.getAreas() %></td>
											
											
											<td>
											
											<form method="POST" action=" /NicoMetareaVI/ControladorMatarWarning">
											
											<INPUT type="hidden" name="idWarning" value=<%=w.getIdWarning() %> />
											
											 
											<button  type="submit"  class="btn btn-link btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
										
											
											<td>
											
											<form method="POST" action=" /NicoMetareaVI/ControladorVerWarning">
											
											<INPUT type="hidden" name="idWarning" value=<%=w.getIdWarning() %> />
											
											 
											<button  type="submit"  class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i> </button>
											</form>
											</td>
											
											
											<td>
											<form method="POST" action=" /NicoMetareaVI/ControladorEliminarWarning">
											<INPUT type="hidden" name="idWarning" value=<%=w.getIdWarning() %> />
											<button type="submit"  class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
											
										</tr>
										
										
										<%}//cierra el if %>
												
										<%} //cierra el for%>	
										
										
										
										
										<tr>
											
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											
											</tr>
										
										
										<%for (Warning warning: wABM.traerWarningActivos()){ if(warning.getBoletin().getIdBoletin()!=id){%>
										
										
										<tr>
											
											
											<td><%= warning.getNumeroWarning() %></td>
											<td><%= warning.getActualizacion() %></td>
											<td><%= warning.getDominio() %></td>
											<td><%= warning.getTipo() %></td>
											<td><%= warning.getFenomeno() %></td>
											<td><%= warning.getAreas() %></td>
											
											
											
											<td>
											
											<form method="POST" action=" /NicoMetareaVI/ControladorMatarWarning">
											
											<INPUT type="hidden" name="idWarning" value=<%=warning.getIdWarning() %> />
											
											 
											<button  type="submit"  class="btn btn-link btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
										
											
											<td>
											
											<form method="POST" action=" /NicoMetareaVI/ControladorVerWarning">
											
											<INPUT type="hidden" name="idWarning" value=<%=warning.getIdWarning() %> />
											
											 
											<button  type="submit"  class="btn btn-success btn-raised btn-xs"><i class="zmdi zmdi-refresh"></i> </button>
											</form>
											</td>
											
											
											<td>
											<form method="POST" action=" /NicoMetareaVI/ControladorEliminarWarning">
											<INPUT type="hidden" name="idWarning" value=<%=warning.getIdWarning() %> />
											<button type="submit"  class="btn btn-danger btn-raised btn-xs"><i class="zmdi zmdi-delete"></i> </button>
											</form>
											</td>
											
										</tr>
										
										
										
										
										
										<%}//cierra el if %>
												
										<%} //cierra el for%>	
										
										
										
										
										
										
										
										
										
										
										
										
										
										<%}//cierra el else %>
									
									
										
	
		

										
									</tbody>
								</table>
								
							</div>
							
							
							
							<!--  
							 <form method="POST" action=" /NicoMetareaVI/ControladorGenerarWarningActivos">
									    
									  
	
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Generar Warning para enviar</button>
										    </p>
										    
									    </form>
							-->
							
							
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
												<option value="FRENTE ESTACIONARIO">FRENTE ESTACIONARIO</option>
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
									  
									  
									<option value="NORTH AREA W 020W">NORTH AREA W 020W</option>
									<option value="NORTH AREA W 030W">NORTH AREA W 030W</option>
									<option value="NORTH AREA W 040W">NORTH AREA W 040W</option>    
									<option value="CENTRAL EAST AREA N 45S E 030W">CENTRAL EAST AREA N 45S E 030W</option>
									<option value="CENTRAL EAST AREA N 45S W 030W">CENTRAL EAST AREA N 45S W 030W</option>
									<option value="CENTRAL EAST AREA S 45S E 030W">CENTRAL EAST AREA S 45S E 030W</option>
									<option value="CENTRAL EAST AREA S 45S W 030W">CENTRAL EAST AREA S 45S W 030W</option>
									<option value="CENTRAL WEST AREA N 45S E 050W">CENTRAL WEST AREA N 45S E 050W</option>
									<option value="CENTRAL WEST AREA N 45S W 050W">CENTRAL WEST AREA N 45S W 050W</option>
									<option value="CENTRAL WEST AREA S 45S E 050W">CENTRAL WEST AREA S 45S E 050W</option>
									<option value="CENTRAL WEST AREA S 45S W 050W">CENTRAL WEST AREA S 45S W 050W</option>
									<option value="SOUTH EAST AREA N 55S E 030W">SOUTH EAST AREA N 55S E 030W</option>
									<option value="SOUTH EAST AREA N 55S W 030W">SOUTH EAST AREA N 55S E 030W</option>
									<option value="SOUTH EAST AREA S 55S E 030W">SOUTH EAST AREA S 55S E 030W</option>
									<option value="SOUTH EAST AREA S 55S W 030W">SOUTH EAST AREA S 55S W 030W</option>
									<option value="SOUTH WEST AREA N 55S E 050W">SOUTH WEST AREA N 55S E 050W</option>
									<option value="SOUTH WEST AREA N 55S W 050W">SOUTH WEST AREA N 55S W 050W</option>
									<option value="SOUTH WEST AREA S 55S W 050W">SOUTH WEST AREA S 55S W 050W</option>
									<option value="SOUTH WEST AREA S 55S E 050W">SOUTH WEST AREA S 55S E 050W</option>
									<option value="ZONAMALVINAS">ZONAMALVINAS</option>
									<option value="DRAKE AREA">DRAKE AREA</option>
									<option value="DRAKE SOUTH">DRAKE SOUTH</option>
							
									<option value="NORTHERN WEDDELL AREA N 68S W 020W">NORTHERN WEDDELL AREA N 68S W 020W</option>
									<option value="NORTHERN WEDDELL AREA N 68S W 030W">NORTHERN WEDDELL AREA N 68S W 030W</option>
									<option value="NORTHERN WEDDELL AREA N 68S W 040W">NORTHERN WEDDELL AREA N 68S W 040W</option>
									
									<option value="SOUTHERN WEDDELL AREA S 68S W 020W">SOUTHERN WEDDELL AREA S 68S W 020W</option>
									<option value="SOUTHERN WEDDELL AREA S 68S W 040W">SOUTHERN WEDDELL AREA S 68S W 040W</option>
									<option value="SOUTHERN WEDDELL AREA S 68S W 050W">SOUTHERN WEDDELL AREA S 68S W 050W</option>
									<option value="SOUTHERN WEDDELL AREA S 68S W 030W">SOUTHERN WEDDELL AREA S 68S W 030W</option>
									
									
									<option value="MARDELAFLOTA">MARDELAFLOTA</option>
									<option value="GERLACHE STRAIT">GERLACHE STRAIT</option>
									<option value="EREBUS AND TERROR GULF">EREBUS AND TERROR GULF</option>
								
						        		
									</select>
											
											
											<div class="form-group">
											  <label class="control-label">Desde: </label>
											  <input class="form-control" type="datetime-local" name="fechaInicial" >
											</div>
											
											
											<div class="form-group">
											  <label class="control-label">Hasta: </label>
											  <input class="form-control" type="datetime-local" name="fechaFinal" >
											</div>
											
											
											
											
											
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Generar Warning</button>
										    </p>
										    
									    </form>
									</div>
								</div>
							</div>
						</div>
						
						
						
						
												<div class="tab-pane fade" id="nuevoAvisoOffShore">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12 col-md-10 col-md-offset-1">
									
				
					
				
									
									
									
									   <form method="POST" action=" /NicoMetareaVI/ControladorAgregarWarningOffShore">
									    
									  
									     
									     
									
									     
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
										        
										        <select class="form-control" name="fenomeno" onchange="if(this.value=='DEPRESION'||this.value=='ANTICICLON') {document.getElementById('valorCentral2').disabled = false} else {document.getElementById('valorCentral2').disabled = true}">
										        <option value="DEPRESION">DEPRESION</option>
												<option value="ANTICICLON">ANTICICLON </option>
												<option value="FRENTE">FRENTE</option>
												<option value="FRENTE FRIO">FRENTE FRIO</option> 
												<option value="FRENTE ESTACIONARIO">FRENTE ESTACIONARIO</option>
												<option value="FRENTE CALIENTE">FRENTE CALIENTE</option> 
												<option value="FRENTE OCLUIDO">FRENTE OCLUIDO</option>
												<option value="CICLOGENESIS">CICLOGENESIS</option>
												<option selected value="FUERTE GRADIENTE BARICO">FUERTE GRADIENTE BARICO</option>
											
										    
										        </select>
										    </div>
										    
										    
					
									    	<div class="form-group label-floating">
											  <label class="control-label">Valor central, optativo hpa:</label>
											  <input id = "valorCentral2"class="form-control" type="text" name="valorCentral" disabled>
											</div>
											
											
											
											
											
											<!-- Ver o no coordenadas -->
											
											<input type="button" value="Agregar coordenadas iniciales" onclick="mostrar2()">
											
											<div id='ver2' style="display:none;">
											
											
											<div id="close2"><a href="javascript:cerrar2();">Cerrar coordenadas iniciales</a></div>
											
											
											
											
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
											
											<input type="button" value="Agregar coordenadas finales" onclick="mostrarF2()">
											
											<div id='verF2' style="display:none;">
											
											
											<div id="closeF2"><a href="javascript:cerrarF2();">Cerrar coordenadas finales</a></div>
											
											
											
											
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
									    
							<option value="DESEMBOCADURA RIO DE LA PLATA">DESEMBOCADURA RIO DE LA PLATA</option>
							<option value="OFFSHORE RIO DE LA PLATA">OFFSHORE RIO DE LA PLATA</option>
							<option value="OFFSHORE MAR DEL PLATA">OFFSHORE MAR DEL PLATA</option>
							<option value="OFFSHORE BAHIA BLANCA">OFFSHORE BAHIA BLANCA</option>
							<option value="OFFSHORE VALDES">OFFSHORE VALDES</option>
							<option value="OFFSHORE SAN JORGE">OFFSHORE SAN JORGE</option>
							<option value="OFFSHORE PATAGONIA SUR">OFFSHORE PATAGONIA SUR</option>
							<option value="OFFSHORE FIN DEL MUNDO">OFFSHORE FIN DEL MUNDO</option>
										
									</select>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha inicial</label>
											  <input class="form-control" type="datetime-local" name="fechaInicial" >
											</div>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha final</label>
											  <input class="form-control" type="datetime-local" name="fechaFinal" >
											</div>
											
											
											
											
											
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Generar Warning</button>
										    </p>
										    
									    </form>
									</div>
								</div>
							</div>
						</div>
						
						
						
						
												<div class="tab-pane fade" id="nuevoAvisoCosta">
							<div class="container-fluid">
								<div class="row">
									<div class="col-xs-12 col-md-10 col-md-offset-1">
									
				
					
				
									
									
									
									   <form method="POST" action=" /NicoMetareaVI/ControladorAgregarWarningCosta">
									    
									  
									     
									     
									
									     
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
										        
										        <select class="form-control" name="fenomeno" onchange="if(this.value=='DEPRESION'||this.value=='ANTICICLON') {document.getElementById('valorCentral3').disabled = false} else {document.getElementById('valorCentral3').disabled = true}">
										        <option value="DEPRESION">DEPRESION</option>
												<option value="ANTICICLON">ANTICICLON </option>
												<option value="FRENTE">FRENTE</option>
												<option value="FRENTE FRIO">FRENTE FRIO</option> 
												<option value="FRENTE ESTACIONARIO">FRENTE ESTACIONARIO</option>
												<option value="FRENTE CALIENTE">FRENTE CALIENTE</option> 
												<option value="FRENTE OCLUIDO">FRENTE OCLUIDO</option>
												<option value="CICLOGENESIS">CICLOGENESIS</option>
												<option selected value="FUERTE GRADIENTE BARICO">FUERTE GRADIENTE BARICO</option>
											
										    
										        </select>
										    </div>
										    
										    
					
									    	<div class="form-group label-floating">
											  <label class="control-label">Valor central, optativo hpa:</label>
											  <input id = "valorCentral3"class="form-control" type="text" name="valorCentral" disabled>
											</div>
											
											
											
											
											
											<!-- Ver o no coordenadas -->
											
											<input type="button" value="Agregar coordenadas iniciales" onclick="mostrar3()">
											
											<div id='ver3' style="display:none;">
											
											
											<div id="close3"><a href="javascript:cerrar3();">Cerrar coordenadas iniciales</a></div>
											
											
											
											
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
											
											<input type="button" value="Agregar coordenadas finales" onclick="mostrarF3()">
											
											<div id='verF3' style="display:none;">
											
											
											<div id="closeF3"><a href="javascript:cerrarF3();">Cerrar coordenadas finales</a></div>
											
											
											
											
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
																		    
																		   <option value="RIO DE LA PLATA EXTERIOR">RIO DE LA PLATA EXTERIOR</option>
									 <option value="RIO DE LA PLATA INTERIOR">RIO DE LA PLATA INTERIOR</option>
									 <option value="RIO DE LA PLATA INTERMEDIO">RIO DE LA PLATA INTERMEDIO</option>
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
									 <option value="COSTA RIO GRANDE">COSTA BAHIA BLANCA</option>
									 <option value="COSTA SAN JULIAN">COSTA SAN JULIAN</option>
									 <option value="COSTA SANTA CRUZ">COSTA SANTA CRUZ</option>
									 <option value="COSTA VALDES">COSTA VALDES</option>
									 <option value="COSTA VIEDMA">COSTA VIEDMA</option>
									 <option value="GOLFO NUEVO">GOLFO NUEVO</option>
									 <option value="GOLFO SAN JORGE NORTE">GOLFO SAN JORGE NORTE</option>
									 <option value="GOLFO SAN JORGE SUR">GOLFO SAN JORGE SUR</option>
									 <option value="GOLFO SAN JOSE">GOLFO SAN JOSE</option>
									 <option value="GOLFO SAN MATIAS">GOLFO SAN MATIAS</option>
										
									</select>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha inicial</label>
											  <input class="form-control" type="datetime-local" name="fechaInicial" >
											</div>
											
											
											<div class="form-group">
											  <label class="control-label">Fecha final</label>
											  <input class="form-control" type="datetime-local" name="fechaFinal" ,.,.,.,.,.,>
											</div>
											
											
											
											
											
											
										    <p class="text-center">
										    	<button href="#!" class="btn btn-info btn-raised btn-sm"><i class="zmdi zmdi-floppy"></i> Generar Warning</button>
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
		
		
		
		
			 <div class="card">
    
    
    
    
    <div class="card-heade" role="tab" id="headingThree2">
      <p class="mb-0">
      <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree2" aria-expanded="false" aria-controls="collapseThree2">
          Vientos Escaterometro - Costas
        </a>
      </p>
    </div>

    <div id="collapseThree2" class="collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas105.png" width="80%" alt="Costa Buenos Aires"> 

      </div>
      
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas94.png" width="80%" alt="Costa Patagonia Norte"> 

      </div>
      
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas95.png" width="80%" alt="Costa Patagonia Sur"> 

      </div>
      
      
    </div>
    
    
    
    
    <div class="card-heade" role="tab" id="headingThree3">
      <p class="mb-0">
      <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree3" aria-expanded="false" aria-controls="collapseThree3">
          Vientos Escaterometro - 50S-60S
        </a>
      </p>
    </div>

    <div id="collapseThree3" class="collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas107.png" width="80%" alt="60W-45W"> 

      </div>
      
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas119.png" width="80%" alt="45W-30W"> 

      </div>
      
      <div class="card-block">

<img src="https://manati.star.nesdis.noaa.gov/ascat_images/cur_25km_META/zooms/WMBas131.png" width="80%" alt="30W-15W"> 

      </div>
      
      
    </div>   
    
    
    
    
    
    
    
    
    

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
			    	<h4 class="modal-title">Warning activos: </h4>
			    </div>
			    <div class="modal-body">
			        
			        
			        
			        <% for(Warning warning: wABM.traerWarningActivos()){%>
			
				
			  	<div class="list-group-item" ALIGN="justify">
			  	
			  	
				    
				  
			        	<h6><%= warning.getTexto() %></h6>
			     
				    
				    
				    	
				    
				    
			  	</div>
			  	<div class="list-group-separator"></div>
			
			  	
			  	<%} %>
			        
			        
			        
			        
			        
			        
			        
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


</html>