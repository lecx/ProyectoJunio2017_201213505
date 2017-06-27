<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<title>Menu</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/logo-nav.css" rel="stylesheet">
</head>
<body>
<%	try{
	Object temp = session.getAttribute("logon.isDone");
	if (temp==null){
		response.sendRedirect("../login.jsp");
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
					<span class="sr-only">Navegacion</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="../menuAdmin.jsp"> <img
					src="../image/inicio.png" alt="">
				</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="GestionUsuarios.jsp">Gestion Usuario</a></li>
					<li><a href="GestionJuegos.jsp">Gestion Juegos</a></li>
					<li><a href="CargarDatos.jsp">Carga Datos</a></li>
					<li><a href="Reportes.jsp">Reportes</a></li>
					<li><% //session.invalidate(); %><a href="../login.jsp"> Salir </a>
						</td></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="container-page">
			<div class="col-md-6">
				<form class="form-horizontal" action="../LoginServlet" method="post">
					<fieldset>
						<div id="legend">
							<legend class="">Nuevo Usuario</legend>
						</div>
						<div class="control-group">
							<!-- Username -->
							<label class="control-label" for="username">usuario</label>
							<div class="controls">
								<input type="text" id="username" name="txtNombre" placeholder="" autocomplete="off"
									class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<!-- Password-->
							<label class="control-label" for="password">Contraseña</label>
							<div class="controls">
								<input type="password" id="password" name="txtPass"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<p class="help-block">
									<% String resultado = (String)session.getAttribute("resultado"); %>
									<%= resultado %>	
									<% session.setAttribute("resultado", ""); %>
								</p>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="action" value="agregarUsuario">
								<button class="btn btn-success" type="submit">Registrar</button>
							</div>
						</div>
					</fieldset>
				</form>
				<br>
				<br>
				<br>
				<form class="form-horizontal" action="../LoginServlet" method="post">
					<fieldset>
						<div id="legend">
							<legend class="">Eliminar Usuario</legend>
						</div>
						<div class="control-group">
							<!-- Username -->
							<label class="control-label" for="username">usuario</label>
							<div class="controls">
								<input type="text" id="username" name="txtNombreE" placeholder="" autocomplete="off"
									class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<p class="help-block">
									<% String resultadoE = (String)session.getAttribute("resultadoE"); %>
									<%= resultadoE %>	
									<% session.setAttribute("resultadoE", ""); %>
								</p>
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<input type="hidden" name="action" value="eliminarUsuario">
								<button class="btn btn-success" type="submit">Eliminar</button>
							</div>
						</div>
					</fieldset>
				</form>	
			</div>
			<div class="col-md-6">
				<form class="form-horizontal" action="../LoginServlet" method="post">
					<fieldset>
						<div id="legend">
							<legend class="">Modificar Usuario</legend>
						</div>
						<div class="control-group">
							<%
							String opciones="<option value=\"\">Seleccione usuario</option>";
							try{
								Object listaUsuarios = session.getAttribute("cbxUsuarios");
								
								if (listaUsuarios !=null){
									ArrayList<String> cbxUsuarios = (ArrayList<String>)listaUsuarios;
									for (String usuario: cbxUsuarios){
										opciones+="<option value="+usuario+">"+usuario+"</option>";
									}
								}else{
								opciones="<option value=\"\">Sin usurios</option>";	
								}
							}catch(Exception e){
								opciones="<option value=\"\">Sin usurios</option>";
							}							
							%>
							<label class="control-label" for="username">usuario</label>							
							<div class="controls" style="width: 166px; ">
					            <select class="form-control" name="txtNombreMod" contenteditable="false"  style="width: 177px; ">
					               <%=opciones%>
					            </select>
					        </div>
						</div>

						<div class="control-group">
							<!-- Password-->
							<label class="control-label" for="password">Contraseña Anterior</label>
							<div class="controls">
								<input type="password" id="passwordAnt" name="txtClaveMod"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<!-- Password-->
							<label class="control-label" for="password">Contraseña</label>
							<div class="controls">
								<input type="password" id="password" name="txtPassMod"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<!-- Password-->
							<label class="control-label" for="password">Confirmar Contraseña</label>
							<div class="controls">
								<input type="password" id="confPassword" name="txtConfPassMod"
									placeholder="" class="input-xlarge">
							</div>
						</div>

						<div class="control-group">
							<div class="controls">
								<p class="help-block">
									<% String resultadoMod = (String)session.getAttribute("resultadoMod"); %>
									<%= resultadoMod %>	
									<% session.setAttribute("resultadoMod", ""); %>
								</p>
							</div>
						</div>

						<div class="control-group">						
							<div class="controls">								
								<input type="hidden" name="action" value="modificarUsuario">
								<button class="btn btn-success" type="submit">Modificar</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
