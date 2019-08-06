<%-- 
    Document   : edit
    Created on : Apr 21, 2019, 6:38:11 PM
    Author     : MrAye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <ol>
        <c:forEach items = "${qrcodes}" var = "i">
                <li><a href ="Controller?page=edit&qr=${i.getCode()}">${i.getTitle()}</a></li>
                </c:forEach>
        </ol>
    </body>
</html>
