package ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2015-10-14T17:18:43.444-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebServiceClient(name = "AdministrarMedioPagoBancos", 
                  wsdlLocation = "AdministrarMedioPagoBancos.xml",
                  targetNamespace = "http://billetera.prismamp.com.ar/administrarMedioPago/") 
public class AdministrarMedioPagoBancos extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://billetera.prismamp.com.ar/administrarMedioPago/", "AdministrarMedioPagoBancos");
    public final static QName AdministrarMedioPagoBancosHttpsSoap11Endpoint = new QName("http://billetera.prismamp.com.ar/administrarMedioPago/", "AdministrarMedioPagoBancosHttpsSoap11Endpoint");
    public final static QName AdministrarMedioPagoBancosHttpSoap11Endpoint = new QName("http://billetera.prismamp.com.ar/administrarMedioPago/", "AdministrarMedioPagoBancosHttpSoap11Endpoint");
    public final static QName AdministrarMedioPagoBancosHttpsSoap12Endpoint = new QName("http://billetera.prismamp.com.ar/administrarMedioPago/", "AdministrarMedioPagoBancosHttpsSoap12Endpoint");
    public final static QName AdministrarMedioPagoBancosHttpSoap12Endpoint = new QName("http://billetera.prismamp.com.ar/administrarMedioPago/", "AdministrarMedioPagoBancosHttpSoap12Endpoint");
    static {
        URL url = AdministrarMedioPagoBancos.class.getResource("AdministrarMedioPagoBancos.xml");
        if (url == null) {
            url = AdministrarMedioPagoBancos.class.getClassLoader().getResource("AdministrarMedioPagoBancos.xml");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(AdministrarMedioPagoBancos.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "AdministrarMedioPagoBancos.xml");
        }       
        WSDL_LOCATION = url;
    }

    public AdministrarMedioPagoBancos(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AdministrarMedioPagoBancos(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AdministrarMedioPagoBancos() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpsSoap11Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpsSoap11Endpoint() {
        return super.getPort(AdministrarMedioPagoBancosHttpsSoap11Endpoint, AdministrarMedioPagoBancosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpsSoap11Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpsSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(AdministrarMedioPagoBancosHttpsSoap11Endpoint, AdministrarMedioPagoBancosPortType.class, features);
    }
    /**
     *
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpSoap11Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpSoap11Endpoint() {
        return super.getPort(AdministrarMedioPagoBancosHttpSoap11Endpoint, AdministrarMedioPagoBancosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpSoap11Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(AdministrarMedioPagoBancosHttpSoap11Endpoint, AdministrarMedioPagoBancosPortType.class, features);
    }
    /**
     *
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpsSoap12Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpsSoap12Endpoint() {
        return super.getPort(AdministrarMedioPagoBancosHttpsSoap12Endpoint, AdministrarMedioPagoBancosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpsSoap12Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpsSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(AdministrarMedioPagoBancosHttpsSoap12Endpoint, AdministrarMedioPagoBancosPortType.class, features);
    }
    /**
     *
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpSoap12Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpSoap12Endpoint() {
        return super.getPort(AdministrarMedioPagoBancosHttpSoap12Endpoint, AdministrarMedioPagoBancosPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AdministrarMedioPagoBancosPortType
     */
    @WebEndpoint(name = "AdministrarMedioPagoBancosHttpSoap12Endpoint")
    public AdministrarMedioPagoBancosPortType getAdministrarMedioPagoBancosHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(AdministrarMedioPagoBancosHttpSoap12Endpoint, AdministrarMedioPagoBancosPortType.class, features);
    }

}
