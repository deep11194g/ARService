/*
 *  Action to be performed on clicking the link "Add Devices" from home.jsp
 */
package action;

import bean.DeviceBean;
import etc.DataPackage;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShowDevicesAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //Get username from the session object
        HttpSession hs = request.getSession(true);
        String username = (String) hs.getAttribute("username");

        //get the human-readable string of device details from Data Package class
        DataPackage dp = new DataPackage();
        dp.extract(username);

        //display in an organized manner
        Iterator itor = dp.DEVICE_LIST.iterator();
        String show = "";
        int count = 1;
        while (itor.hasNext()) {
            DeviceBean dev = (DeviceBean) itor.next();
            show += "<h2>DEVICE NO." + count++ + ":<br></h2>";
            show += "Barcode : " + dev.getBarcode() + "<br>";
            show += "Last Maintainence : " + dev.getLastMaintainence() + "<br>";
            show += "Latitude : " + dev.getLatitude() + " Longitude : " + dev.getLongitude()+ "<br>";
            show += "Keywords : " + dev.getKeywords() + "<br>";
            show += "<br><br>";
            
        }

        request.setAttribute("username", username);
        request.setAttribute("show", show);

        //Return back to the page from where the request has been originated
        return mapping.findForward("devices");
    }
}
