package etc;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    public String url;
    public int port;
    
    public MongoClient mongoClient;
    public DB db;

    public DBConnect(String url, int port) {
        try {
            this.url = url;
            this.port = port;
            
            mongoClient = new MongoClient(url, port);
            db = mongoClient.getDB("AugReality");
        } catch (UnknownHostException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() {
        mongoClient.close();
    }
    
}
