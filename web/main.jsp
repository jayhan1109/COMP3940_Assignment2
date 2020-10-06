<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.Db" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Monsrat </title>
    <link rel="stylesheet" type="text/css" href="style/main.css" />
    <link href="https://fonts.googleapis.com/css?family=Archivo+Black&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Archivo:700&display=swap" rel="stylesheet">
    
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
    Db db = new Db();
    int row = db.maxRow((String) session.getAttribute("userID"));
    session.setAttribute("row", row);

%>
<div class="empty"></div>

<div class = "cardContainer">
	<div class="card">
		<a href="upload.jsp">
			<img src="images/upload.png" class ="imageSty"/>
		</a>
	</div>
	<div class="card">
		<a href="search.jsp">
			<img src="images/search.png" class ="imageSty" />
		</a>
	</div>
	<div class="card">
		<a href="view.jsp">
			<img src="images/view.png" class ="imageSty" />
		</a>
	</div>
	
	<div class="card">
		<a href="logout.jsp">
			<img src="images/logouticon.png" class ="imageSty"/>
		</a>
	</div>
</div>



</body>
</html>