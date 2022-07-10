package com.example.test_soap;

import com.example.springsoap.gen.GetClientRequest;
import com.example.springsoap.gen.GetClientResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

@Endpoint
public class ClientEndpoint {

    private static final String namespaceUri = "http://www.example.com/springsoap/gen";

    @PayloadRoot(localPart = "getClientRequest", namespace = namespaceUri)
    @ResponsePayload
    public GetClientResponse getClientResponse(@RequestPayload GetClientRequest request) throws TransformerException {
        System.out.println("я тут");
        System.out.println(request.getClient());
        GetClientResponse response = new GetClientResponse();

        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("src/transform.xslt"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(request.getClient());
        StringWriter writer = new StringWriter();
        Writer out = new StringWriter();
        transformer.transform(text, new StreamResult(out));
        transformer.transform(text, new StreamResult(writer));
        response.setResponse(writer.toString());

        return response;
    }

}
