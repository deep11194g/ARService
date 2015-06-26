<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devices</title>
    </head>
    
    <body>
        <h1>Devices of ${requestScope.username}</h1>
        
        <br><br>
        <!-- Receiving show variable from ShowDeviceAction.java -->
        <!-- show holds details of all devices -->
        ${requestScope.show}
        <br><br>
    </body>
</html>
