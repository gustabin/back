package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2019-10-22T12:14:32.008-03:00
 * Generated source version: 3.0.1
 * 
 */
@WebServiceClient(name = "Omnicanalidad", 
                  wsdlLocation = "file:/C:/jona/Select/OBP-Git/obp-generated-ws-client/src/main/resources/wsdl/OmnicanalidadService.wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class Omnicanalidad extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "Omnicanalidad");
    public final static QName BasicHttpBindingIOmnicanalidad = new QName("http://tempuri.org/", "BasicHttpBinding_IOmnicanalidad");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/jona/Select/OBP-Git/obp-generated-ws-client/src/main/resources/wsdl/OmnicanalidadService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Omnicanalidad.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/jona/Select/OBP-Git/obp-generated-ws-client/src/main/resources/wsdl/OmnicanalidadService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Omnicanalidad(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Omnicanalidad(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Omnicanalidad() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IOmnicanalidad
     */
    @WebEndpoint(name = "BasicHttpBinding_IOmnicanalidad")
    public IOmnicanalidad getBasicHttpBindingIOmnicanalidad() {
        return super.getPort(BasicHttpBindingIOmnicanalidad, IOmnicanalidad.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IOmnicanalidad
     */
    @WebEndpoint(name = "BasicHttpBinding_IOmnicanalidad")
    public IOmnicanalidad getBasicHttpBindingIOmnicanalidad(WebServiceFeature... features) {
        return super.getPort(BasicHttpBindingIOmnicanalidad, IOmnicanalidad.class, features);
    }

}
