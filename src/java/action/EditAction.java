package action;

import bean.DeviceBean;
import etc.DataPackage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EditAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        DeviceBean device = (DeviceBean) form;
        
        HttpSession hs = request.getSession(true);
        String username = (String) hs.getAttribute("username");
        
        DataPackage dp = new DataPackage();
        dp.update(device, username);
        
        request.setAttribute("update_msg", "Device with barcode no. " + device.getBarcode() + "has been successfully updates");
        
        return mapping.getInputForward();
    }
}
