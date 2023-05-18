<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String ruolo = (String) session.getAttribute("ruolo");

	if (ruolo == null) {
		//do nothing
	}
	else {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
%>
 
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=devide-width, initial scale=1.0">
	<title>Accedi</title>
	<link rel="stylesheet" href="style/style.css">
	<link rel="icon" href="../images/icon.png">

</head>
<body>
	
	<header>
		<h2 class="logo">Unigame</h2>
		<nav class="navigation">
			<a href="#">Home</a>
			<a href="#">Shop</a>
            <a href="#">Chi Siamo</a>
			<a href="#">Contatti</a>
            <button class="btnLogin-popup">Login</button>
		</nav>
	</header>

    <div class="wrapper">
        <div class="form-box login">
            <h2>Login</h2>
            
            <form action="/LoginServlet">
                <!-- Campi email e password -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail"></ion-icon></span>
                    <input type="email" required>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
                    <input type="password" required>
                    <label>Password</label>
                </div>
                <!--Checkbox ricordami/password -->
                <div class="ricordami-recupera">
                    <label>
                        <input type="checkbox"> Ricordami
                    </label>
                    <a href="#">Recupera Password</a>
                </div>
                <button type="submit" class="btnLogin">Login</button>

                <div class="login-register">
                    <p>
                        Non hai un account?
                        <a href="#" class="register-link">Registrati!</a>
                    </p>
                </div>
            </form>
        </div>
    </div>


    <!-- ICONE -->
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>