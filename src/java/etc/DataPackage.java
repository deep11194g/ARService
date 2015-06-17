/*
 *  Class which contains code to deal with JSON data from MongoDB and get into a human displayable form
 *  Also create the JSON file which is to sent on GET request
 */
package etc;

import bean.DeviceBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPackage {

    //Hold the JSON document in string format
    public String OUTPUT = "";
    public ArrayList<DeviceBean> DEVICE_LIST;

    public void extract(String username) {
        try {
            //Connection to mongoDB server
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            //Get the DB instance required
            DB db = mongoClient.getDB("AugReality");
            //Get the collection required; in this case the devices collection
            DBCollection coll = db.getCollection("devices");

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
                    OUTPUT += "Device no." + count + ":<br>" + dbObj.toString() + "<br><br>";
                    count++;
                    
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
                    for(String key : keyArr) {
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

            //Close connection to mongoDB server
            mongoClient.close();

        } catch (UnknownHostException ex) {
            Logger.getLogger(DataPackage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
