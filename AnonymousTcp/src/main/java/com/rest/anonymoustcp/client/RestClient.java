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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestClient {
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(config);
        WebResource service = client.resource(getBaseURI());
        // Fluent interfaces
        System.out.println(service.path("rest").path("hello").accept(
                MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
        // Get plain text
        System.out.println(service.path("rest").path("hello").accept(
                MediaType.TEXT_PLAIN).get(String.class));
        // Get XML
        System.out.println(service.path("rest").path("hello").accept(
                MediaType.APPLICATION_XML).get(String.class));
        // The HTML
        System.out.println(service.path("rest").path("hello").accept(
                MediaType.APPLICATION_XHTML_XML).get(String.class));

    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "http://localhost:8080/de.vogella.jersey.first").build();
    }
}