<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
    </head>
        <title>Index</title>
    </head>

<body>
    <html:form action="/login">
        <h2>Login</h2>
        Username <html:text name="FormBean" property="username" value="" />
        Password <html:password name="FormBean" property="password" value="" />
        <html:submit value="Login"/>
    </html:form>
    <br><font color="red">${requestScope.login_failed}</font>
    
    <html:form action="/register">
        <h2>Register</h2>
        Username <html:text name="FormBean" property="username" value="" />
        Password <html:password name="FormBean" property="password" value="" />
        <html:submit value="Register"/>
    </html:form>
    <br><font color="red">${requestScope.register_msg}</font>
        
    <div style="color:red">
        <html:errors/>
    </div>
    
</body>
</html>