/*
 *  Service containing GET and POST method to retrieve and add device details
 */
package service;

import java.io.File;
import java.io.FileNotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import etc.DataPackage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.ws.rs.PathParam;

@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

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

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
