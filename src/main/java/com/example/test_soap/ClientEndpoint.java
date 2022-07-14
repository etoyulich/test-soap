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
import java.io.*;

@Endpoint
public class ClientEndpoint {

    private static final String namespaceUri = "http://www.example.com/springsoap/gen";

    @PayloadRoot(localPart = "getClientRequest", namespace = namespaceUri)
    @ResponsePayload
    public GetClientResponse getClientResponse(@RequestPayload GetClientRequest request) {

        GetClientResponse response = new GetClientResponse();
        TransformerFactory factory = TransformerFactory.newInstance();

        try(InputStream is = new FileInputStream("src/transform.xslt")) {
            Source xslt = new StreamSource(is);
            Transformer transformer = factory.newTransformer(xslt);
            Source source = new StreamSource(new StringReader(request.getXml()));
            Writer result = new StringWriter();
            transformer.transform(source, new StreamResult(result));
            response.setResponse(result.toString());
        }
        catch (IOException | TransformerException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return response;
    }

}
