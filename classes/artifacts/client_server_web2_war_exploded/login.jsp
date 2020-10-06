<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signin</title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
</head>
<body>
<div class="empty"></div>

<%
    // Check if the user logged in
    String userID = null;
    if (session.getAttribute("userID") != null) {
        userID = (String) session.getAttribute("userID");
    }

    if (userID != null) {
        response.sendRedirect("main.jsp");
    }

%>

<div class="cardXcontainer">
	<h1> Sign in! </h1>
	<div class = "cardX">
		<form action="loginAction.jsp" method="post">
		    <input type="text" name="userID"/>
		    <input type="password" name="userPassword"/>
		    
		    <input type="submit" value="Sign in"/>
		    <input type="button" value ="Forgot to Sign up? "onclick="location.href='index.jsp'" />
		</form>	
	</div>
</div>

</body>
</html>