<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.Db" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
</head>
<body>
<%
    String key = request.getParameter("key");
    session.setAttribute("key", key);
    String userid = (String) session.getAttribute("userID");
%>
<div style="height: 15em;"></div>

<div class="cardXcontainer" style="width: 600px;">
	<h1> Search Result </h1>
	<div class ="cardX">	
	<img src="showfile"width="300">
	<input type="submit" value="<%=key %>" readonly="readonly" >
	
	</div>
</div>

</body>
</html>