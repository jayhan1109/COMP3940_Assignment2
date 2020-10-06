<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
</head>
<body>

<%
    PrintWriter script = response.getWriter();
    String userID = null;
    if (session.getAttribute("userID") != null) {
        userID = (String) session.getAttribute("userID");
    }

    //l ogged in so send user to main.jsp
    if (userID != null) {
        response.sendRedirect("login.jsp");
    }
%>
<div class="empty"></div>
<div class="cardXcontainer">
	<h1> Sign Up! </h1>
	<div class = "cardX">
		<form action="registerAction.jsp" method="post">
		    <input type="text" name="userID"/>
		    <input type="password" name="userPassword"/>
		    <input type="submit" value="register"/>
		    <a href="login.jsp"><input type="submit" value="Already have an account?"></a>
		</form>
	</div>
</div>

</body>
</html>