package com.rest.anonymoustcp.jaxb;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/17/12
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
import com.rest.anonymoustcp.jaxb.model.Request;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/request")
@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class RequestResource {
    // This method is called if XMLis request
    @GET
    public Request getRequest() {
        Request request = new Request("Request","Test Message","Description");
        return request;
    }

    @POST
    public Request getSentRequest(Request request){
        request.setDescription("Update Description");
        //Dont update title
        request.setMessage("Update Message");
        return request;
    }

}
