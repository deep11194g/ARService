<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
    <head>
        <title>Edit</title>
    </head>
    <body>
        
        <table border="1" cellspacing="5">
            <tr>
                <td><a href="seeDevices.do">See present devices</a></td>
                <td><a href="addDevice.jsp">Add a device</a></td>
                <td><a href="edit.jsp">Edit</a></td>
                <td><a href="logout.do">Logout</a></td>
            </tr>
        </table>
        
        <h1>Update Location</h1>
        
        <html:form action="/edit">
            <table>
                <tr><td>Barcode</td>            <td><html:text name="DeviceBean" property="barcode" value="" /></td></tr>
                <tr><td>Latitude</td>           <td><html:text name="DeviceBean" property="latitude" value="" /></td></tr>
                <tr><td>Longitude</td>          <td><html:text name="DeviceBean" property="longitude" value="" /></td></tr>
                <tr><td><html:submit value="Submit" /></td></tr>
            </table>
        </html:form>
    
        <br><br><b><font color="blue">${requestScope.update_msg}</font></b>   
        
    </body>
</html>
