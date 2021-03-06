package com.rest.anonymoustcp.jaxb.model;

/**
 * Created with IntelliJ IDEA.
 * User: jorge
 * Date: 4/17/12
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class Request {
    private String title;
    private String message;
    private String description;
    private String endpoint;

    public Request()
    {
    }

    public Request(String title, String message, String description, String endpoint)
    {
        this.title = title;
        this.message = message;
        this.description = description;
        this.endpoint = endpoint;
    }


    @XmlElement
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
