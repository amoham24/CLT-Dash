<%-- 
    Document   : editRiddle
    Created on : Apr 21, 2019, 8:13:04 PM
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
        <form action="Controller" method="POST">
            <input type="text" name="action" value="edit" hidden>
            <input type="text" name="itemcode" value="${theRiddle.getCode()}" hidden>
            <input type="text" name="title" value="${theRiddle.getTitle()}" required>
            <input type="text" name="riddle" value="${theRiddle.getRiddle()}" required>
            <input type="submit" value="Submit changes">
        </form>
    </body>
</html>
