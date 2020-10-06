<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.*" %>
<%@ page import="db.Db" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    String name = request.getParameter("userID");
    String password = request.getParameter("userPassword");
    PrintWriter script = response.getWriter();

    // Check if the user logged in
    String userID = null;
    if (session.getAttribute("userID") != null) {
        userID = (String) session.getAttribute("userID");
    }

    if (userID != null) {
        script.println("<script>");
        script.println("alter('your already logged in')");
        script.println("location.href='main.jsp");
        script.println("<script/>");
    }


    Db db = new Db();
    if (db.register(name, password) != -1) {
        script.println("<script>");
        script.println("location.href = 'login.jsp'");
        script.println("</script>");
    } else {
    	script.println("<script>");
        script.println("location.href = 'login.jsp'");
        script.println("</script>");
    }
%>

</body>
</html>