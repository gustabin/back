package ar.com.santanderrio.obp.generated.webservices.rsa;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2016-09-09T15:35:38.709-03:00
 * Generated source version: 3.0.0
 * 
 */
@WebServiceClient(name = "AdaptiveAuthentication", 
                  wsdlLocation = "file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AdaptiveAuthentication.wsdl",
                  targetNamespace = "http://ws.csd.rsa.com") 
public class AdaptiveAuthentication extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.csd.rsa.com", "AdaptiveAuthentication");
    public final static QName AdaptiveAuthentication = new QName("http://ws.csd.rsa.com", "AdaptiveAuthentication");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AdaptiveAuthentication.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AdaptiveAuthentication.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AdaptiveAuthentication.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AdaptiveAuthentication(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AdaptiveAuthentication(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AdaptiveAuthentication() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns AdaptiveAuthenticationInterface
     */
    @WebEndpoint(name = "AdaptiveAuthentication")
    public AdaptiveAuthenticationInterface getAdaptiveAuthentication() {
        return super.getPort(AdaptiveAuthentication, AdaptiveAuthenticationInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdaptiveAuthenticationInterface
     */
    @WebEndpoint(name = "AdaptiveAuthentication")
    public AdaptiveAuthenticationInterface getAdaptiveAuthentication(WebServiceFeature... features) {
        return super.getPort(AdaptiveAuthentication, AdaptiveAuthenticationInterface.class, features);
    }

}
