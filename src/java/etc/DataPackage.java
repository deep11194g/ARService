/*
 *  Class which contains code to deal with JSON data from MongoDB and get into a human displayable form
 *  Also create the JSON file which is to sent on GET request
 */
package etc;

import bean.DeviceBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPackage {

    /*
     *  Receive username and extract device details accordingly
     *  Either by web client(device.jsp) or GET method
     */
    //Hold the JSON document in string format
    public String OUTPUT = "{";
    public ArrayList<DeviceBean> DEVICE_LIST;

    public DBConnect DBC = new DBConnect("localhost", 27017);

    public void extract(String username) {

        //Get the collection required; in this case the devices collection
        DBCollection coll = DBC.db.getCollection("devices");

        //search for the document with the given username; db.devices.find("username" : concerned_username)
        BasicDBObject searchQuery = new BasicDBObject("username", username);
        DBCursor cursor = coll.find(searchQuery);

        //list of device details for the present username
        DEVICE_LIST = new ArrayList<DeviceBean>();

        //populating device detials
        while (cursor.hasNext()) {
            int count = 1;
            BasicDBObject obj = (BasicDBObject) cursor.next();
            BasicDBList productList = (BasicDBList) obj.get("productList");
            BasicDBObject[] prodArr = productList.toArray(new BasicDBObject[0]);

            for (BasicDBObject dbObj : prodArr) {
                if (count++ != 1) {
                    OUTPUT += ",";
                }
                OUTPUT += dbObj.toString();

                //Creating an object for each device document
                DeviceBean dev = new DeviceBean();
                dev.setBarcode(dbObj.getString("barcode"));
                dev.setName(dbObj.getString("name"));

                BasicDBObject loc = (BasicDBObject) dbObj.get("location");
                dev.setLongitude(loc.getString("longitude"));
                dev.setLatitude(loc.getString("latitude"));

                BasicDBList keywords = (BasicDBList) dbObj.get("keywords");
                String[] keyArr = keywords.toArray(new String[0]);
                String keysInString = "";
                for (String key : keyArr) {
                    keysInString += key + " ";
                }
                dev.setKeywords(keysInString);

                dev.setApplication(dbObj.getString("application"));
                dev.setUpstream(dbObj.getString("upstream"));
                dev.setDownstream(dbObj.getString("downstream"));
                dev.setLastMaintainence(dbObj.getString("lastMaintainence"));
                dev.setManual(dbObj.getString("manual"));
                dev.setSld(dbObj.getString("sld"));

                //Adding the device object to device list
                DEVICE_LIST.add(dev);
            }
        }
        OUTPUT += "}";

        //Close connection to mongoDB server
        DBC.close();
    }

    /*
     *  Receive device details and add to DB
     *  Either by web client(addDevice.jsp) or POST method
     */
    //Date format for last maintainence entry
    DateFormat FORMAT = new SimpleDateFormat("mm/DD/yyyy", Locale.ENGLISH);

    public void insert(DeviceBean device, String username) {
        try {
            //Get the collection required; in this case the devices collection => db.getCollection("coll_name")
            DBCollection coll = DBC.db.getCollection("devices");

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

            DBC.close();
        } catch (ParseException ex) {
            Logger.getLogger(DataPackage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     *  Edit value of some given fields of a device
     */
    public void update(DeviceBean device, String username) {

        String latitude = device.getLatitude();
        String longitude = device.getLongitude();
        String barcode = device.getBarcode();

        //Get the collection required; in this case the devices collection => db.getCollection("coll_name")
        DBCollection coll = DBC.db.getCollection("devices");

        //updated location document
        BasicDBObject updateDoc = new BasicDBObject("latitude", latitude);
        updateDoc.append("longitude", longitude);

        BasicDBObject updateCommand = new BasicDBObject("$set", new BasicDBObject("productList.$.location", updateDoc));
        BasicDBObject updateQuery = new BasicDBObject("username", username);
        updateQuery.append("productList.barcode", barcode);
        coll.update(updateQuery, updateCommand);

        //Close MongoDB connection
        DBC.close();
    }
}
