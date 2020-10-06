<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    PrintWriter script = response.getWriter();
    int count = (int) session.getAttribute("count");
    if (count > 0) {
        session.setAttribute("count", count - 1);
    }
    response.sendRedirect("gallery.jsp");
%>

</body>
</html>