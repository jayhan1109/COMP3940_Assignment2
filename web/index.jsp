<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.Db" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
</head>
<body>
<div class="empty"></div>

<div class="cardXcontainer" style="width: 600px;">
	<h1> Welcome to Monsrat </h1>
	<div class ="cardX">	
		<br>
		<a href="login.jsp"><input type="submit" value="Sign in!"></a>
		<a href="register.jsp"><input type="submit" value="Sign up!"></a>
	</div>
</div>


<%-- 	<%
	response.setContentType("text/html");
	Db db = new Db();
	PrintWriter o = response.getWriter();
	o.println(db.check());
	
	%> --%>
</body>
</html>