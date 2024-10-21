package ar.com.santanderrio.obp.generated.webservices.mya.v2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2019-12-11T17:09:37.934-03:00
 * Generated source version: 3.0.1
 * 
 */
@WebServiceClient(name = "ServicesService", 
                  wsdlLocation = "file:/C:/tbancoGit/back/obp-generated-ws-client/src/main/resources/wsdl/serviciomya.wsdl",
                  targetNamespace = "http://services.mya.ar.bsch") 
public class ServicesService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://services.mya.ar.bsch", "ServicesService");
    public final static QName Services = new QName("http://services.mya.ar.bsch", "Services");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/tbancoGit/back/obp-generated-ws-client/src/main/resources/wsdl/serviciomya.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ServicesService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/tbancoGit/back/obp-generated-ws-client/src/main/resources/wsdl/serviciomya.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ServicesService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ServicesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServicesService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns Services
     */
    @WebEndpoint(name = "Services")
    public Services getServices() {
        return super.getPort(Services, Services.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Services
     */
    @WebEndpoint(name = "Services")
    public Services getServices(WebServiceFeature... features) {
        return super.getPort(Services, Services.class, features);
    }

}
