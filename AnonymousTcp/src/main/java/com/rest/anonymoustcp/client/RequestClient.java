package com.rest.anonymoustcp.client;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Jorge
 * Date: 4/17/12
 * Time: 1:53 PM
 */
@Component
public class RequestClient {


    public Response.ResponseBuilder get(String url, HttpHeaders httpHeaders)
            throws IOException {
        HttpGet request = new HttpGet(url);
        setHttpHeaders(httpHeaders, request);
        return executeRequest(request);
    }
    public Response.ResponseBuilder get(String url, org.springframework.http.HttpHeaders headers)
            throws IOException {
        HttpGet request = new HttpGet(url);
        setHeaders(headers, request);
        return executeRequest(request);
    }

    public Response.ResponseBuilder delete(String url, HttpHeaders httpHeaders)
            throws IOException {
        HttpDelete request = new HttpDelete(url);
        setHttpHeaders(httpHeaders, request);
        return executeRequest(request);
    }

    public Response.ResponseBuilder put(String url, HttpHeaders httpHeaders,
                                        String body) throws IOException {
        HttpPut request = new HttpPut(url);
        request.setEntity(getHttpEntity(body));
        setHttpHeaders(httpHeaders, request);
        return executeRequest(request);
    }

    public Response.ResponseBuilder post(String url, HttpHeaders httpHeaders,
                                         String body) throws IOException {
        HttpPost request = new HttpPost(url);
        request.setEntity(getHttpEntity(body));
        setHttpHeaders(httpHeaders, request);
        return executeRequest(request);
    }

    public Response.ResponseBuilder post(String url, org.springframework.http.HttpHeaders headers, String body) throws IOException {
        HttpPost request = new HttpPost(url);
        request.setEntity(getHttpEntity(body));
        setHeaders(headers, request);
        return executeRequest(request);
    }


    private Response.ResponseBuilder executeRequest(HttpRequestBase request) {
        DefaultHttpClient client = getHttpClient();
        String responseBody = null;
        int statusCode = 500;
        HttpResponse response = null;
        try {
            response = client.execute(request);
            statusCode = response.getStatusLine().getStatusCode();
            if (response.getEntity() != null) {
                responseBody = convertStreamToString(response.getEntity().getContent());
                if (responseBody.equals(""))
                    responseBody = null;
            }
        } catch (IOException e) {
            responseBody = e.getMessage();
        }

        Response.ResponseBuilder responseBuilder = Response.status(statusCode).entity(responseBody);
        for (Header header : response.getAllHeaders()) {
            String key = header.getName();
            if (!key.equalsIgnoreCase("content-encoding") && !key.equalsIgnoreCase("content-length")) {
                responseBuilder = responseBuilder.header(key, header.getValue());
            }
        }

        return responseBuilder;
    }

    private BasicHttpEntity getHttpEntity(String body) {
        if (body == null)
            return null;
        BasicHttpEntity entity = new BasicHttpEntity();
        ByteArrayInputStream bs = new ByteArrayInputStream(body.getBytes());
        entity.setContent(bs);
        return entity;
    }

    private DefaultHttpClient getHttpClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        client.addRequestInterceptor(new RequestAcceptEncoding());
        client.addResponseInterceptor(new ResponseContentEncoding());
        return client;
    }
    private void setHeaders(org.springframework.http.HttpHeaders headers, HttpRequestBase request) {
        if (!headers.isEmpty()){
            for (String header: headers.keySet()){
                request.addHeader(header, headers.get(header).toString());
            }
        }
    }

    private void setHttpHeaders(HttpHeaders httpHeaders, HttpRequestBase request) {
        Set<String> keys = httpHeaders.getRequestHeaders().keySet();
        request.setHeaders(new Header[]{});
        for (String key : keys) {
            if (!key.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH) && !key.equals(HttpHeaders.HOST)) {
                if (key.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE)) {
                    request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
                } else {
                    request.setHeader(key, httpHeaders.getRequestHeaders().getFirst(key));
                }
            }
        }
    }

    public static String convertStreamToString(InputStream is)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }


}