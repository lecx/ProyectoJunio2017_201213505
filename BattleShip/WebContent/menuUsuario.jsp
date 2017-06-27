<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>Menu</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/logo-nav.css" rel="stylesheet">
</head>
<body>	
<%	try{
	Object temp = session.getAttribute("logon.isDone");
	if (temp==null){
		response.sendRedirect("login.jsp");
	}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	 %>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">		
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Navegacion</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="menuUsuario.jsp"> <img
					src="image/inicio.png" alt="">
				</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="funcionesUsuario/GestionJuegosU.jsp">Gestion Juegos</a></li>
					<li><a href="funcionesUsuario/ReportesU.jsp">Reportes</a></li>
					<li><% //session.invalidate();%><a href="login.jsp">  Salir  </a></td></li>
				</ul>
			</div>			
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>Logo Nav by Start Bootstrap</h1>
				<p>Note: You may need to adjust some CSS based on the size of
					your logo. The default logo size is 150x50 pixels.</p>
			</div>
		</div>
	</div>
</body>
</html>
