<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload</title>
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
<h2> Upload Pictures!	</h2>
	<div class = "cardX">
		<form action="uploadAction" method="POST" enctype="multipart/form-data">
    		<input type="text" name="caption" placeholder="Caption..."/> <br>
    		<input type="text" name="title" placeholder="Title..."/>
    		
    		<br>
	    		<div class="filebox">
				  <label for="ex_file">Upload!</label>
				  <input type="file" id="ex_file" name="file" />
				  
				</div>
    		<input type="submit" value="Submit">
		</form>
	</div>
</div>

<br>

</body>
</html>