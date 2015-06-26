/*
 *  Action to be performed while adding a device from addDevice.jsp
 */
package action;

import bean.DeviceBean;
import etc.DataPackage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeviceAddAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //Receive the form data as an object of DeviceBean class; details entered at addDevice.jsp
        DeviceBean device = (DeviceBean) form;

        //Retrieve username from session
        HttpSession hs = request.getSession(true);
        String username = (String) hs.getAttribute("username");

        //Call insert fucntion from DataPackage class
        DataPackage dp = new DataPackage();
        dp.insert(device, username);

        String lastUpdateBarcode = device.getBarcode();
        request.setAttribute("update_msg", "Device with barcode no." + lastUpdateBarcode + " has been entered");

        //Return to the source page
        return mapping.getInputForward();
    }
}
