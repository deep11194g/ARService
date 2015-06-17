/*
 *  Action to be performed on clicking the link "Add Devices" from home.jsp
 */
package action;

import bean.DeviceBean;
import etc.DataPackage;
import java.io.File;
import java.io.FileOutputStream;
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
            show += "<h2>DEVICE NO." + count + ":<br></h2>";
            show += "Barcode : " + dev.getBarcode() + "<br>";
            show += "Last Maintainence : " + dev.getLastMaintainence() + "<br>";
            show += "Name : " + dev.getName() + "<br>";
            show += "Keywords : " + dev.getKeywords()+ "<br>";
            show += "<br><br>";
            count++;
        }

        request.setAttribute("username", username);
        request.setAttribute("show", show);

        //create the JSON file to be sent to mobile application
        String path = this.getServlet().getServletContext().getRealPath("/") + "/DataFiles";
        File uploadFolder = new File(path);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdir();
        }
        FileOutputStream fos = new FileOutputStream(path + "/" + "data.json");
        fos.write(dp.OUTPUT.getBytes());
        fos.close();

        //Return back to the page from where the request has been originated
        return mapping.findForward("devices");
    }
}
