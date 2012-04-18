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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/request")
public class RequestResource {
    // This method is called if XMLis request
    @GET
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Request getRequest() {
        Request request = new Request("Request","Test Message","Description");
        return request;
    }

    // This can be used to test the integration with the browser
    @GET
    @Produces( { MediaType.TEXT_XML })
    public Request getHTML() {
        Request request = new Request();
        request.setTitle("Request");
        request.setMessage("Test Message");
        request.setDescription("Test Description");
        return request;
    }

}
