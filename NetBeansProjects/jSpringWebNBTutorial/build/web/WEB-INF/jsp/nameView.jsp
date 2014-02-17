<%-- 
    Document   : nameView
    Created on : 24-abr-2011, 18:15:51
    Author     : mike
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Enter your name</h1>
        <spring:nestedPath path="name">
    <form action="" method="post">
        Name:
        <spring:bind path="value">
            <input type="text" name="${status.expression}" value="${status.value}">
        </spring:bind>
        <input type="submit" value="OK">
    </form>
</spring:nestedPath>

        <%--
        spring:bind allows you to bind a bean property. The bind tag provides a bind
status and value, which you use as the name and value of the input field. This way,
when the form is submitted, Spring will know how to extract the submitted value.
Here, our command class (controller.Name) has a value property, therefore you set
the path to value.

spring:nestedPath enables you to prepend a specified path to a bean. So, when used
with spring:bind as shown above, the path to the bean becomes: name.value. As you
recall, the command name of HelloController is name. Therefore, this path refers to
the value property of a bean named name in the page scope.
        --%>
    </body>
</html>
