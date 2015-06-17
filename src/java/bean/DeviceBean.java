/*  
 *  Bean to store data from device entry form
 */

package bean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class DeviceBean extends org.apache.struts.action.ActionForm {
    
    //form fields from addDevice.jsp
    private String barcode;
    private String name;
    private String latitude;
    private String longitude;
    private String keywords;
    private String application;
    private String upstream;
    private String downstream;
    private String lastMaintainence;
    private String manual;
    private String sld;
    
    /*  
     *  server-side valiadtion of form values of devices
     *  error messages can be found at ApplicationResource.properties
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        //validate name
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
        }
        
        //validate latitude
        if (getLatitude()== null || getLatitude().length() < 1) {
            errors.add("latitude", new ActionMessage("error.latitude.required"));
        }
        
        //validate longitude
        if (getLongitude() == null || getLongitude().length() < 1) {
            errors.add("longitude", new ActionMessage("error.longitude.required"));
        }
        
        //validate manual link
        if (getManual() == null || getManual().length() < 1) {
            errors.add("manual", new ActionMessage("error.manual.required"));
        }
        return errors;
    }
    
    //setter and getter functions for barcode
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    //setter and getter functions for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ////setter and getter functions for latitude
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    //setter and getter functions for longitude
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    //setter and getter functions for keywords
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    //setter and getter functions for application
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    //setter and getter functions for upstream
    public String getUpstream() {
        return upstream;
    }

    public void setUpstream(String upstream) {
        this.upstream = upstream;
    }

    //setter and getter functions for downstream
    public String getDownstream() {
        return downstream;
    }

    public void setDownstream(String downstream) {
        this.downstream = downstream;
    }

    //setter and getter functions for lastMaintainenece
    public String getLastMaintainence() {
        return lastMaintainence;
    }

    public void setLastMaintainence(String lastMaintainence) {
        this.lastMaintainence = lastMaintainence;
    }

    //setter and getter functions for manual
    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    //setter and getter functions for sld
    public String getSld() {
        return sld;
    }

    public void setSld(String sld) {
        this.sld = sld;
    }
}
