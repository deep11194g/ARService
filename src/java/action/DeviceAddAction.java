/*
 *  Action to be performed while adding a device from addDevice.jsp
 */
package action;

import bean.DeviceBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DeviceAddAction extends org.apache.struts.action.Action {

    //Date format for last maintainence entry
    DateFormat FORMAT = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //clear values from form after evry request is sent
        form.reset(mapping, request);

        //receive the form data as an object of DeviceBean class; details entered at addDevice.jsp
        DeviceBean device = (DeviceBean) form;

        //retrieve username from session
        HttpSession hs = request.getSession(true);
        String username = (String) hs.getAttribute("username");

        //Connection to mongoDB server
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Get the DB instance required => use db
        DB db = mongoClient.getDB("AugReality");
        //Get the collection required; in this case the devices collection => db.getCollection("coll_name")
        DBCollection coll = db.getCollection("devices");

        //Create a document and appened all device details to it
        BasicDBObject doc = new BasicDBObject("barcode", device.getBarcode());
        doc = doc.append("name", device.getName());

        BasicDBObject doc_location = new BasicDBObject("latitude", device.getLatitude());
        doc_location = doc_location.append("longitude", device.getLongitude());

        doc = doc.append("location", doc_location);

        String[] keywordArray = device.getKeywords().split(",");
        doc = doc.append("keywords", keywordArray);

        doc = doc.append("application", device.getApplication());
        doc = doc.append("upstream", device.getUpstream());
        doc = doc.append("downstream", device.getDownstream());

        Date date = FORMAT.parse(device.getLastMaintainence());
        doc = doc.append("lastMaintainence", date);

        doc = doc.append("manual", device.getManual());
        doc = doc.append("sld", device.getSld());

        //update the productList field; insert the created document
        BasicDBObject updateCommand = new BasicDBObject("$push", new BasicDBObject("productList", doc));
        BasicDBObject updateQuery = new BasicDBObject("username", username);
        coll.update(updateQuery, updateCommand, true, true);

        String lastUpdateBarcode = device.getBarcode();
        request.setAttribute("update_msg", "Device with barcode no." + lastUpdateBarcode + " has been entered");

        //Return to the source page
        return mapping.getInputForward();
    }
}
