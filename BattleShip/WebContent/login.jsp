<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<title>Login</title>
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<%  session.invalidate();
//		session.setAttribute("logon.isDone", null);
// 		Object user = session.getAttribute("logon.isDone");
// 		if (user!=null){
// 			System.out.println("usuaro logueado: " + user.toString());
// 			}		
// 		System.out.println("salio de pagina");				
	%> 
    <div class="container">    
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="LoginServlet" method="post" >
                <span id="reauth-user" class="reauth-user"></span>
                <input type="text" name="txtUserName" id="txtUserName" class="form-control" placeholder="Usuario" required autofocus>
                <input type="password" name="txtPass" id="txtPass" class="form-control" placeholder="Contraseña" required>              
              	<input type="hidden" name="action" value="login">
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Ingresar</button>
            </form>            
        </div>
    </div>
   </body>
</html> 