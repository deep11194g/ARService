/*
 *  Action to be performed on while registering a user from index.jsp
 */
package action;

import bean.FormBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RegisterAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        //get username and password from FormBean entered at index.jsp
        FormBean loginBean = (FormBean) form;
        String username = loginBean.getUsername(), password = loginBean.getPassword();
        
        //Connection to mongoDB server
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Get the DB instance required => use db
        DB db = mongoClient.getDB("AugReality");
        //Get the collection required; in this case the login collection => db.getCollection("coll_name")
        DBCollection coll = db.getCollection("login");
        
        //Create a document containing username and password
        BasicDBObject doc = new BasicDBObject("username", username);
        doc.append("password", password);
        coll.insert(doc);

        //Get the devices collection; create a document for storage of devices later on
        DBCollection coll2 = db.getCollection("devices");
        BasicDBObject doc2 = new BasicDBObject("username", username);
        doc2.append("productList", new ArrayList<>());
        coll2.insert(doc2);

        //Close connection to MongoDB server
        mongoClient.close();

        request.setAttribute("register_msg", username + " has been registered");
        
        //Return back to the page from where the request has been originated
        return mapping.getInputForward();
    }
}
