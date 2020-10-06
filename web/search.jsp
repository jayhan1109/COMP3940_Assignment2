<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Search </title>
    <link rel="stylesheet" type="text/css" href="style/home.css" />
    <link rel="stylesheet" type="text/css" href="style/form.css" />
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

%>

<div class="empty"></div>
<div class="cardXcontainer" style="width: 700px;">
<h2> Search Pictures!	</h2>
	<div class = "cardX">
		<form action="searchAction.jsp" method="get">
    		<input type="text" name="key">
    		<input class="btn" type="submit" value="search">
		</form>
	</div>
</div>


</body>
</html>