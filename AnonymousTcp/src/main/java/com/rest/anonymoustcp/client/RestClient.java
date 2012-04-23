package com.rest.anonymoustcp.client;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/17/12
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.rest.anonymoustcp.jaxb.model.Request;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClient {
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI(args[0])).path("rest").path("request");
        // Get XML
        System.out.println(service.accept(
                MediaType.APPLICATION_XML).get(String.class));
        // GET JSON
        System.out.println(service.accept(
                MediaType.APPLICATION_JSON).get(String.class));
        // POST XML
        ClientResponse response = service.type(MediaType.APPLICATION_XML).post(ClientResponse.class, new Request("New Title","New Message","New Description"));
        Request request = response.getEntity(Request.class);
        System.out.println(request.getTitle());
        System.out.println(request.getMessage());
        System.out.println(request.getDescription());
    }

    private static URI getBaseURI(String path) {
        return UriBuilder.fromUri(
                path + "/AnonymousTcp").build();
    }
}
