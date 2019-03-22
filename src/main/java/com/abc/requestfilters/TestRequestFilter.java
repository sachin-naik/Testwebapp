package com.abc.requestfilters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.json.JsonSanitizer;

import RestModels.TestResponseModel;

@Provider
public class TestRequestFilter implements ContainerRequestFilter {
	
	public void filter(ContainerRequestContext requestContext)
        throws IOException {

        System.out.println("IN FILTER");
        String requestBody = readEntityStream(requestContext);
        String requestBody1 = requestBody.replaceAll("[^\\w{}:\", ]", "");
        String sanitizedString = JsonSanitizer.sanitize(requestBody1);
        TestResponseModel tst = new ObjectMapper().readValue(sanitizedString, TestResponseModel.class);
        if(requestBody.equals(sanitizedString)) {
        	System.out.println("All good");
        } else {
        	System.out.println("Sanitized json is different than original");
        }
        requestContext.setEntityStream(new ByteArrayInputStream(sanitizedString.getBytes()) );
        System.out.println("END FILTER");
    }
	
	private static String readEntityStream(ContainerRequestContext requestContext)
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final InputStream inputStream = requestContext.getEntityStream();
        final StringBuilder builder = new StringBuilder();
        try
        {
            ReaderWriter.writeTo(inputStream, outStream);
            byte[] requestEntity = outStream.toByteArray();
            String inputString = new String(requestEntity);
            if (requestEntity.length == 0) {
                builder.append("");
            } else {
                builder.append(inputString);
            }
        } catch (IOException ex) {
        	System.out.println(ex);
        }
        return builder.toString();
    }
}
