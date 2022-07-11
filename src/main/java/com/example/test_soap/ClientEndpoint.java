package com.example.test_soap;

import com.example.springsoap.gen.GetClientRequest;
import com.example.springsoap.gen.GetClientResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

@Endpoint
public class ClientEndpoint {

    private static final String namespaceUri = "http://www.example.com/springsoap/gen";

    @PayloadRoot(localPart = "getClientRequest", namespace = namespaceUri)
    @ResponsePayload
    public GetClientResponse getClientResponse(@RequestPayload GetClientRequest request) {

        GetClientResponse response = new GetClientResponse();
        TransformerFactory factory = TransformerFactory.newInstance();
        File file = new File("src/transform.xslt");
        Source xslt = new StreamSource(file.getAbsolutePath());

        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        Source source = new StreamSource(new StringReader(request.getXml()));
        Writer result = new StringWriter();
        try {
            transformer.transform(source, new StreamResult(result));
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        response.setResponse(result.toString());

        return response;
    }

}
