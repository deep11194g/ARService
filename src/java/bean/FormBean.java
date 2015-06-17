/*  
 *  Bean to store data from login and register form
 */
package bean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class FormBean extends org.apache.struts.action.ActionForm {

    //username and password fields from index.jsp
    private String username;
    private String password;

    /*  
     *  server-side valiadtion of form values of login and register
     *  error messages can be found at ApplicationResource.properties
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        //validate username
        if (getUsername() == null || getUsername().length() < 1) {
            errors.add("username", new ActionMessage("error.username.required"));
        }

        //validate password
        if (getPassword() == null || getPassword().length() < 1) {
            errors.add("password", new ActionMessage("error.password.required"));
        }
        return errors;
    }

    //setter and getter functions for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //setter and getter functions for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
