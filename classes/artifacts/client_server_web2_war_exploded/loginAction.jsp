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
    PrintWriter script = response.getWriter();


    //check log in
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


    String name = request.getParameter("userID");
    String password = request.getParameter("userPassword");
    Db db = new Db();
    int result = db.login(name, password);

    System.out.println(result);
    if (result == 1) {
        session.setAttribute("userID", name);
        int row = db.maxRow((String) session.getAttribute("userID"));
        session.setAttribute("row", row);
        script.println("<script>");
        script.println("location.href = 'main.jsp'");
        script.println("</script>");
    } else if (result == 0) {
%>
<script>
    alert('wrong password')
</script>
<%
    response.sendRedirect("login.jsp");
} else if (result == -1) {
%>
<script>
    alert('wrong user name')
</script>
<%
    response.sendRedirect("login.jsp");
} else if (result == 2) {
%>
<script>
    alert('db not working')
</script>
<%
        script.println("alert('db not working omg')");
    }

%>

</body>
</html>