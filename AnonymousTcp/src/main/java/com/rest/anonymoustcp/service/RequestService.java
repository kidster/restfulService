package com.rest.anonymoustcp.service;

import com.rest.anonymoustcp.client.RequestClient;
import com.rest.anonymoustcp.config.JAXBContextResolver;
import com.rest.anonymoustcp.jaxb.model.Request;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/26/12
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */

public class RequestService {

    private RequestClient requestClient = new RequestClient();

    public ResponseBuilder sendRequest(HttpHeaders httpHeaders, Request request)
            throws IOException, JAXBException {
        String url = request.getEndpoint() + "/AnonymousTcp/request";
        String body = marshallObjectToString(request);
        return requestClient.post(url, httpHeaders, body);
    }

    private String marshallObjectToString(Object jaxbObject) throws JAXBException {
        StringWriter sw = new StringWriter();
        Marshaller marshaller = JAXBContextResolver.get().createMarshaller();
        try {
            marshaller.marshal(jaxbObject, sw);
        } catch (Exception e) {
            JAXBException k = new JAXBException(e.getMessage());
            throw k;
        }
        return sw.toString();
    }
}

