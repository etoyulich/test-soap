
package com.example.springsoap.gen.newgen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ClientWSService", targetNamespace = "http://www.example.com/springsoap/gen", wsdlLocation = "src/main/resources/client.wsdl")
public class ClientWSService
    extends Service
{

    private final static URL CLIENTWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException CLIENTWSSERVICE_EXCEPTION;
    private final static QName CLIENTWSSERVICE_QNAME = new QName("http://www.example.com/springsoap/gen", "ClientWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(null, "src/main/resources/client.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CLIENTWSSERVICE_WSDL_LOCATION = url;
        CLIENTWSSERVICE_EXCEPTION = e;
    }

    public ClientWSService() {
        super(__getWsdlLocation(), CLIENTWSSERVICE_QNAME);
    }

    public ClientWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CLIENTWSSERVICE_QNAME, features);
    }

    public ClientWSService(URL wsdlLocation) {
        super(wsdlLocation, CLIENTWSSERVICE_QNAME);
    }

    public ClientWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CLIENTWSSERVICE_QNAME, features);
    }

    public ClientWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ClientWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ClientInterface
     */
    @WebEndpoint(name = "ClientWSPortBinding")
    public ClientInterface getClientWSPortBinding() {
        return super.getPort(new QName("http://www.example.com/springsoap/gen", "ClientWSPortBinding"), ClientInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns ClientInterface
     */
    @WebEndpoint(name = "ClientWSPortBinding")
    public ClientInterface getClientWSPortBinding(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.example.com/springsoap/gen", "ClientWSPortBinding"), ClientInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CLIENTWSSERVICE_EXCEPTION!= null) {
            throw CLIENTWSSERVICE_EXCEPTION;
        }
        return CLIENTWSSERVICE_WSDL_LOCATION;
    }

}
