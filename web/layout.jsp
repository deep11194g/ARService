<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <body>
        <table>
            <tr>
                <td><tiles:insert attribute="menu" /></td>
            </tr>
            <tr>
                <td><tiles:insert attribute="body" /></td>
            </tr>
        </table>
        
    </body>
</html>
