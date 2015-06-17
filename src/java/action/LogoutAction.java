/*
 *  Action to be performed while logging out from home.jsp
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        //Get the session object
        HttpSession hs = request.getSession();
        
        //destroy the session object
        hs.invalidate();
        
        //return to index.jsp; refer struts-config.xml action mapping
        return mapping.findForward("index");
    }
}
