package com.example.springsoap.gen;

import com.example.springsoap.gen.newgen.ClientInterface;
import com.example.springsoap.gen.newgen.GetClientRequest;
import com.example.springsoap.gen.newgen.GetClientResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebService;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebService(endpointInterface = "com.example.springsoap.gen.newgen.ClientInterface", targetNamespace = "http://www.example.com/springsoap/gen", serviceName = "ClientWSService", portName = "ClientInterfaceSoap11")
public class ClientServiceImpl implements ClientInterface {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    @Override
    public GetClientResponse getClientRequest(GetClientRequest request) {
        logger.info("Start transforming xml.");
        GetClientResponse response = new GetClientResponse();
        TransformerFactory factory = TransformerFactory.newInstance();

        try(InputStream is = Files.newInputStream(Paths.get("src/transform.xslt"))) {
            Source xslt = new StreamSource(is);
            Transformer transformer = factory.newTransformer(xslt);
            Source source = new StreamSource(new StringReader(request.getRequest()));
            Writer result = new StringWriter();
            transformer.transform(source, new StreamResult(result));
            response.setResponse(result.toString());
            logger.info("Transforming ended successfully!");
        }
        catch (IOException | TransformerException e){
            logger.error("Caught exception!", e);
            throw new RuntimeException(e);
        }
        return response;
    }
}
