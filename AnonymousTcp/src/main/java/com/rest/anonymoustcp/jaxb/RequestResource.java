package com.rest.anonymoustcp.jaxb;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/17/12
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
import com.rest.anonymoustcp.jaxb.model.Request;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("/request")
public class RequestResource {
    // This method is called if XMLis request
    @GET
    public Request getRequest() {
        Request request = new Request("Request","Test Message","Description","Ex. http://localhost:8080");
        return request;
    }

    @POST
    public Request getSentRequest(Request request){
        request.setDescription(null);
        request.setEndpoint(null);
        //Dont update title
        request.setMessage("Message Received");
        return request;
    }

}
