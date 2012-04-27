package com.rest.anonymoustcp.config;

import com.rest.anonymoustcp.jaxb.model.Request;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

    private static JAXBContext context;

    public JAXBContextResolver() throws Exception {
        init();
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return context;
    }

    public static JAXBContext get() {
        if (context == null) {
            try {
                init();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return context;
    }

    private static void init() throws Exception {
        context = JAXBContext.newInstance(Request.class);
    }
}
