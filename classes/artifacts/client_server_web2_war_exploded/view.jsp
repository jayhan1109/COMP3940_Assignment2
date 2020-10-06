<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.Db" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View </title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
	<style>
		body {
			width: 300px;
		}
	</style>
	
</head>
<body>
<%
    // Check if the user logged in
    String userID = null;
    if (session.getAttribute("userID") != null) {
        userID = (String) session.getAttribute("userID");
    }

    if (userID == null) {
        response.sendRedirect("login.jsp");
    }

%>


<%
    session.setAttribute("count", 0);
%>
<div class="empty"></div>

<div class="cardXcontainer" style="width: 300px;">
	<h1> Go to Gallery! </h1>
	<div class ="cardX">	
		<a href="gallery.jsp"><input type="submit" value="Gallery"></a>
	</div>
</div>




</body>
</html>