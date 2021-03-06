/*
 *  Action to be performed while logging in from index.jsp
 */
package action;

import bean.FormBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import etc.DBConnect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginAction extends org.apache.struts.action.Action {

    public DBConnect DBC = new DBConnect("localhost", 27017);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        FormBean loginBean = (FormBean) form;
        
        boolean search = false;
        
        //Get the collection required; in this case the login collection => db.getCollection("coll_name")
        DBCollection coll = DBC.db.getCollection("login");
        
        DBCursor cursor = coll.find();

        while (cursor.hasNext()) {
            BasicDBObject doc = (BasicDBObject) cursor.next();
            //match each username and password with the one netered at login form fields
            if (doc.getString("password").equals(loginBean.getPassword()) && doc.getString("username").equals(loginBean.getUsername())) {
                search = true;
                break;
            }
        }
        
        //Close DB connection
        //DBC.close();
        
        //if username and password found in DB, create session and redirect to home
        if (search) {
            request.setAttribute("username", loginBean.getUsername());
            HttpSession hs = request.getSession(true);
            hs.setAttribute("username", loginBean.getUsername());
            return mapping.findForward("home");
        } 
        //if not found redirect to the page from where the request originated with an error message
        else {
            request.setAttribute("login_failed", "Username of password mismatch. Re-enter");
            return mapping.getInputForward();
        }
        
    }
}
