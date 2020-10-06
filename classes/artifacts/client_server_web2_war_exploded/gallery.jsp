<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gallery</title>
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




<div style="height:150px;"></div>

<br><br>
<div class="cardXcontainer" style="width: 600px;">
<h1> Gallery </h1>
	<img src="showfiles" width="300">
	<div class ="cardX">
		<div class="formContainer">
			<form method="post" action="viewAction.jsp">
				<div>
					<input type="submit" value="previous">
				</div>
			</form>
			<form method="post" action="viewAction2.jsp">
	    		<div>
	    			<input type="submit" value="next">
	    		</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>