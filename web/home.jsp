<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
    <head>
        <title>Home</title>
    </head>
    
    <body>
        <!-- Receiving username variable from LoginAction.java -->
        <h1>Hello ${requestScope.username} </h1>
    </body>
</html>
