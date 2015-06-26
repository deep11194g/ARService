/*
 *  Service containing methods to retrieve, add or edit device details
 */
package service;

import bean.DeviceBean;
import java.io.File;
import java.io.FileNotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import etc.DataPackage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Path("generic")
public class GenericResource {

    public GenericResource() {
    }

    /*
     *  GET method to invoke extract function from DataPackage class and create a temp file for download
     */
    @GET
    @Path("{username}")
    @Produces("application/json")
    public Response getJson(@PathParam("username") String username) throws FileNotFoundException, IOException, UnknownHostException {
        DataPackage dp = new DataPackage();
        dp.extract(username);

        File file = File.createTempFile("data", ".json");

        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(dp.OUTPUT);
        out.close();

        //create download response
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=\"data.json\"");
        return response.build();
    }

    /*
     *  PUT method to invoke insert function from DataPackage class and enter device details
     */
    @PUT
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    public Response postDevice(@FormParam("username") String username,
            @FormParam("barcode") String barcode,
            @FormParam("name") String name,
            @FormParam("latitude") String latitude,
            @FormParam("longitude") String longitude,
            @FormParam("keywords") String keywords,
            @FormParam("application") String application,
            @FormParam("upstream") String upstream,
            @FormParam("downstream") String downstream,
            @FormParam("lastMaintainence") String lastMaintainence,
            @FormParam("manual") String manual,
            @FormParam("sld") String sld) {

        DeviceBean device = new DeviceBean();

        device.setName(name);
        device.setBarcode(barcode);
        device.setLatitude(latitude);
        device.setLongitude(longitude);
        device.setKeywords(keywords);
        device.setApplication(application);
        device.setUpstream(upstream);
        device.setDownstream(downstream);
        device.setLastMaintainence(lastMaintainence);
        device.setManual(manual);
        device.setSld(sld);

        DataPackage dp = new DataPackage();
        dp.insert(device, username);

        //return a response with status
        return Response.status(200).entity("Device with barcode no." + barcode + " has been added succesfully").build();
    }

    /*
     *  POST method to invoke update function from DataPackage class and edit device details
     */
    @POST
    @Path("/edit")
    @Consumes("application/x-www-form-urlencoded")
    public Response editDevice(@FormParam("username") String username,
            @FormParam("barcode") String barcode,
            @FormParam("latitude") String latitude,
            @FormParam("longitude") String longitude) {

        DeviceBean device = new DeviceBean();
        device.setBarcode(barcode);
        device.setLatitude(latitude);
        device.setLongitude(longitude);

        DataPackage dp = new DataPackage();
        dp.update(device, username);

        //return a response with status
        return Response.status(200).entity("Location of device with barcode no." + barcode + " has been updated").build();

    }
}
