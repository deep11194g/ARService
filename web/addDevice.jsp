<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<html>
    <head>
        <title>Add Device</title>
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
        
        <h1>Add new device details</h1>
        
        <html:form action="/add">
            <table>
                <tr><td>Barcode</td>            <td><html:text name="DetailsBean" property="barcode" value="" /></td></tr>
                <tr><td>Name</td>               <td><html:text name="DetailsBean" property="name" value="" /></td></tr>
                <tr><td>Location:</td></tr>
                <tr><td>Latitude</td>           <td><html:text name="DetailsBean" property="latitude" value="" /></td></tr>
                <tr><td>Longitude</td>          <td><html:text name="DetailsBean" property="longitude" value="" /></td></tr>
                <tr><td>Keywords</td>           <td><html:text name="DetailsBean" property="keywords" value="" /></td></tr>
                <tr><td>Application</td>        <td><html:text name="DetailsBean" property="application" value="" /></td></tr>
                <tr><td>Upstream</td>           <td><html:text name="DetailsBean" property="upstream" value="" /></td></tr>
                <tr><td>Downstream</td>         <td><html:text name="DetailsBean" property="downstream" value="" /></td></tr>
                <tr><td>Last Maintainence</td>  <td><html:text name="DetailsBean" property="lastMaintainence" value="" /></td></tr>
                <tr><td>Manual Link</td>        <td><html:text name="DetailsBean" property="manual" value="" /></td></tr>
                <tr><td>SLD Link</td>           <td><html:text name="DetailsBean" property="sld" value="" /></td></tr>
                <tr><td><html:submit value="Submit" /></td></tr>
            </table>
        </html:form>
        
    <br><br><b><font color="blue">${requestScope.update_msg}</font></b>    
    
    <div style="color:red">
        <html:errors/>
    </div>
    
    </body>
</html>
