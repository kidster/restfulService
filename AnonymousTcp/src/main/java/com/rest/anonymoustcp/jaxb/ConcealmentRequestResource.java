package com.rest.anonymoustcp.jaxb;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/17/12
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */

import com.rest.anonymoustcp.jaxb.model.Request;
import com.rest.anonymoustcp.service.RequestService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;


@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Path("/request/concealment")
public class ConcealmentRequestResource {
    // This method is called if XMLis request
    private RequestService requestService = new RequestService();

    @Context
    private HttpHeaders headers;

    @GET
    public Request getRequest() {
        Request request = new Request("Request Title","Example Message","Add a description in your message.","Add server endpoint eg. http://11.1.111.11:8080");
        return request;
    }

    @POST
    public Response requestSent(Request request) throws IOException, JAXBException {
        return requestService.sendRequest(headers,request).build();
    }

}
