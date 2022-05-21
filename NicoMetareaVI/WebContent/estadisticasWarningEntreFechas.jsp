<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 
   
     <%@page import="modelo.Pronostico"%>
     <%@page import="modelo.Area"%>
     <%@page import="modelo.Warning"%>
	<%@page import="funciones.Funciones"%>
	
	 <%@page import="java.util.ArrayList"%>
	 
	 
	 <%@page import= "modelo.Boletin"%>
	 	 <%@page import= "modelo.Estadisticas"%>
		<%@page import= "modelo.Warning"%>
		<%@page import= "negocio.BoletinABM"%>
		<%@page import= "negocio.WarningABM"%>
		<%@page import= "java.util.GregorianCalendar"%>
		<%@page import= "java.util.Calendar"%>



 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>


	<link rel="shortcut icon" type="image/png" href="https://img.icons8.com/cotton/64/000000/cargo-ship--v2.png"/>
<title>Estadisticas de los Warning</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="stylesheet" href="./css/main.css">
	
	<script type="text/javascript" src="jquery.js"></script>
	<script type="text/javascript" src="jquery_plantuml.js"></script>
	
	<script src="http://code.responsivevoice.org/responsivevoice.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	
	
	
	
	
	
	
	
	
	
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

				<% Estadisticas e = (Estadisticas)request.getAttribute("e"); %>
		


	<!-- SideBar -->
	<section class="full-box cover dashboard-sideBar">
	
		<div class="full-box dashboard-sideBar-bg btn-menu-dashboard"></div>
		<div class="full-box dashboard-sideBar-ct">
			<!--SideBar Title -->
			<div class="full-box text-uppercase text-center text-titles dashboard-sideBar-title">
				ESTADISTICAS <i class="zmdi zmdi-close btn-menu-dashboard visible-xs"></i>
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
				
				<li>
					<a href="indexEstadisticasWarningEntreFechas.jsp">
						<i class="zmdi zmdi-view-dashboard zmdi-hc-fw"></i> Estadísticas por fechas
					</a>
				</li>
				
				

				
				
				
			</ul>
			
			
			
			
			
			
			
			
			
			
			
			
		</div>
	</section>
	
	
	
	

		
		
<!-- Content page -->

<section class="full-box dashboard-contentPage">
		<!-- NavBar -->
		
			
		
		
		
		<img src="https://wmail.smn.gov.ar/skins/outlook/skin/images/login.jpg"  alt="UserIcon">  
		
		<div class="container-fluid">
			<div class="page-header">
			  <h1 class="text-titles"><i class="zmdi zmdi-balance zmdi-hc-fw"></i> TIPOS de warning <small>distribucion de los warning.</small></h1>
			</div>
			<p class="lead">High Sea, OffShore o Coastal.</p>
			
			
			
			
		
			
		</div>
		
		
		
		
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					
					  	<li class="active"><a href="#avisos" data-toggle="tab"><i class="zmdi zmdi-balance"></i> Tipos</a></li>
					  	<li><a href="#nuevoAviso" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Por Latitud</a></li>
					  	<li><a href="#nuevoAvisoOffShore" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Distribucion OFF</a></li>
					    <li><a href="#nuevoAvisoCosta" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Intensidad</a></li>
					    <li><a href="#nuevo1" data-toggle="tab" ><i class="zmdi zmdi-calendar-check"></i> Progreso</a></li>
					  	
					</ul>
					
					
					
					
					
					
					
					
					<div class="tab-content">
					
					
					
					
					
					
						<div class="tab-pane fade active in" id="avisos">
						
						<div class="table-responsive">
						
						
						
								<canvas id="bar-chart-horizontal" width="600" height="300"></canvas>
				
								
						<script>
						
						var myChart = new Chart(document.getElementById("bar-chart-horizontal"), {
						    type: 'horizontalBar',
						    data: {
						      labels: ["Warning", "High Sea", "OffShore", "Coastal"],
						      datasets: [
						        {
						          label: "Cantidad (unidades)",
						          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#c45850"],
						          data: [<%=e.getCantidadWarningEmitidos()%>,<%=e.getCantidadWHighSea()%>,<%=e.getCantidadWOffShore()%>,<%=e.getCantidadWCoastal()%>]
						        }
						      ]
						    },
						    options: {
						      legend: { display: false },
						      title: {
						        display: true,
						        text: 'Warning emitidos por el SMN'
						      }
						    }
						});
						</script>
						
						
		
								
							</div>
							
						
							
							
						</div>
						
						
						
						
						
						<div class="tab-pane fade" id="nuevoAviso">
							<div class="table-responsive">
									
				
					
				
									<canvas id="pie-chart" width="600" height="300"></canvas>
									
									
									<script>
						
						          var myChart1 =new Chart(document.getElementById("pie-chart"), {
									    type: 'pie',
									    data: {
									      labels: ["Sur de 60°", "Sur de 50°", "Sur de 40°", "Sur de 35°"],
									      datasets: [{
									        label: "Cantidad (unidades)",
									        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9"],
									        data: [<%=e.getCantidadS60()%>,<%=e.getCantidadS50()%>,<%=e.getCantidadS40()%>,<%=e.getCantidadS35()%>]
									      }]
									    },
									    options: {
									      title: {
									        display: true,
									        text: 'Warning emitidos por el SMN'
									      }
									    }
									});
						          </script>	
						          
						      					
									
									</div>
								</div>
						
						
						
						
						<div class="tab-pane fade" id="nuevoAvisoOffShore">
							<div class="table-responsive">
									
				
		<canvas id="doughnut-chart" width="800" height="450"></canvas>
				
							
									
									
									<script>
						
						          var myChart2 = new Chart(document.getElementById("doughnut-chart"), {
						        	    type: 'doughnut',
						        	    data: {
						        	      labels: ["OffShore Norte", "OffShore Centro", "OffShore Sur"],
						        	      datasets: [
						        	        {
						        	          label: "Cantidad (unidades)",
						        	          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
						        	          data: [<%=e.getCantidadOffNorte()%>,<%=e.getCantidadOffCentro()%>,<%=e.getCantidadOffSur()%>]
						        	        }
						        	      ]
						        	    },
						        	    options: {
						        	      title: {
						        	        display: true,
						        	        text: 'Warning emitidos por el SMN'
						        	      }
						        	    }
						        	});
						          </script>	
						          
						          
											
									
									</div>
								</div>
							
							
						
						
						
						
						
						
						<div class="tab-pane fade" id="nuevoAvisoCosta">
							<div class="table-responsive">
									
				
								<canvas id="bubble-chart" width="800" height="800"></canvas>
							
									
									
									<script>
						
						          var myChart2 = new Chart(document.getElementById("bubble-chart"), {
						        	    type: 'bubble',
						        	    data: {
						        	      labels: "SMN",
						        	      datasets: [
						        	        {
						        	          label: ["Sur de 60°"],
						        	          backgroundColor: "rgba(255,221,50,0.2)",
						        	          borderColor: "rgba(255,221,50,1)",
						        	          data: [{
						        	            x: <%=e.getCantidadS60Gale()%>,
						        	            y: <%=e.getCantidadS60Mas()%>,
						        	            r: <%=e.getCantidadS60()%>
						        	          }]
						        	        }, {
						        	          label: ["Sur de 50°"],
						        	          backgroundColor: "rgba(60,186,159,0.2)",
						        	          borderColor: "rgba(60,186,159,1)",
						        	          data: [{
						        	        	  x: <%=e.getCantidadS50Gale()%>,
							        	            y: <%=e.getCantidadS50Mas()%>,
						        	            r: <%=e.getCantidadS50()%>
						        	          }]
						        	        }, {
						        	          label: ["Sur de 40°"],
						        	          backgroundColor: "rgba(0,0,0,0.2)",
						        	          borderColor: "#000",
						        	          data: [{
						        	        	  x: <%=e.getCantidadS40Gale()%>,
							        	            y: <%=e.getCantidadS40Mas()%>,
						        	            r: <%=e.getCantidadS40()%>
						        	          }]
						        	        }, {
						        	          label: ["Sur de 35°"],
						        	          backgroundColor: "rgba(193,46,12,0.2)",
						        	          borderColor: "rgba(193,46,12,1)",
						        	          data: [{
						        	        	  x: <%=e.getCantidadS35Gale()%>,
							        	            y: <%=e.getCantidadS35Mas()%>,
						        	            r: <%=e.getCantidadS35()%>
						        	          }]
						        	        }
						        	      ]
						        	    },
						        	    options: {
						        	      title: {
						        	        display: true,
						        	        text: 'Distribucion warning High Sea por intensidad (unidades)'
						        	      }, scales: {
						        	        yAxes: [{ 
						        	          scaleLabel: {
						        	            display: true,
						        	            labelString: "#Gale Force"
						        	          }
						        	        }],
						        	        xAxes: [{ 
						        	          scaleLabel: {
						        	            display: true,
						        	            labelString: "#Storm Force"
						        	          }
						        	        }]
						        	      }
						        	    }
						        	});
						          </script>	
						          
						          							
									
									</div>
								</div>
						
							
						
						
						
						
						<div class="tab-pane fade" id="nuevo1">
							<div class="table-responsive">
									
				
								<canvas id="line-chart" width="800" height="450"></canvas>
							
									
									
									<script>
						
						          var myChart6 = new Chart(document.getElementById("line-chart"), {
						        	  type: 'line',
						        	  data: {
						        	    labels: ["RDP","MDP","BHB","VAL","GOL","PAT","FIN"],
						        	    datasets: [{ 
						        	        data: [<%=e.getGaleRio()%>,<%=e.getGaleMar()%>,<%=e.getGaleBah()%>,<%=e.getGaleVal()%>,<%=e.getGaleSan()%>,<%=e.getGalePat()%>,<%=e.getGaleFin()%>],
						        	        label: "Gale Force",
						        	        borderColor: "#3e95cd",
						        	        fill: false
						        	      }, { 
						        	        data: [<%=e.getHurRio()%>,<%=e.getHurMar()%>,<%=e.getHurBah()%>,<%=e.getHurVal()%>,<%=e.getHurSan()%>,<%=e.getHurPat()%>,<%=e.getHurFin()%>],
						        	        label: "Strom Force",
						        	        borderColor: "#8e5ea2",
						        	        fill: false
						        	      }, { 
						        	        data: [<%=e.getHurRio()%>,<%=e.getHurMar()%>,<%=e.getHurBah()%>,<%=e.getHurVal()%>,<%=e.getHurSan()%>,<%=e.getHurPat()%>,<%=e.getHurFin()%>],
						        	        label: "Hurracane Foce",
						        	        borderColor: "#3cba9f",
						        	        fill: false
						        	      }
						        	    ]
						        	  },
						        	  options: {
						        	    title: {
						        	      display: true,
						        	      text: 'Cantidad de Warning por categoria y latitud (unidades)'
						        	    }
						        	  }
						        	});
						          </script>								
									
									
			
									
									
									</div>
								</div>
						
							
						
						
						
						
						
						
						
						
						
						
					
					  	
					  	
					</div>
				</div>
			</div>
		</div>
		
		
		
		
			 <div class="card">
    
    
    
    
    
    
    
    
    
    
    
    
    
    

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