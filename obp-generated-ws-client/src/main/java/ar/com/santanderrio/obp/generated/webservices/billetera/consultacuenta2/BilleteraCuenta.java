package ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2017-04-12T10:33:15.955-03:00
 * Generated source version: 3.0.0
 * 
 */
@WebServiceClient(name = "BilleteraCuenta", 
                  wsdlLocation = "file:/D:/vistas/projects/OBPMerge/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/BilleteraCuenta.wsdl",
                  targetNamespace = "http://billetera.prismamp.com.ar/billeteraCuenta") 
public class BilleteraCuenta extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://billetera.prismamp.com.ar/billeteraCuenta", "BilleteraCuenta");
    public final static QName BilleteraCuentaHttpsSoap11Endpoint = new QName("http://billetera.prismamp.com.ar/billeteraCuenta", "BilleteraCuentaHttpsSoap11Endpoint");
    public final static QName BilleteraCuentaHttpsSoap12Endpoint = new QName("http://billetera.prismamp.com.ar/billeteraCuenta", "BilleteraCuentaHttpsSoap12Endpoint");
    public final static QName BilleteraCuentaHttpSoap11Endpoint = new QName("http://billetera.prismamp.com.ar/billeteraCuenta", "BilleteraCuentaHttpSoap11Endpoint");
    public final static QName BilleteraCuentaHttpSoap12Endpoint = new QName("http://billetera.prismamp.com.ar/billeteraCuenta", "BilleteraCuentaHttpSoap12Endpoint");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/vistas/projects/OBPMerge/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/BilleteraCuenta.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BilleteraCuenta.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/vistas/projects/OBPMerge/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/BilleteraCuenta.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public BilleteraCuenta(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BilleteraCuenta(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BilleteraCuenta() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpsSoap11Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpsSoap11Endpoint() {
        return super.getPort(BilleteraCuentaHttpsSoap11Endpoint, BilleteraCuentaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpsSoap11Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpsSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(BilleteraCuentaHttpsSoap11Endpoint, BilleteraCuentaPortType.class, features);
    }
    /**
     *
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpsSoap12Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpsSoap12Endpoint() {
        return super.getPort(BilleteraCuentaHttpsSoap12Endpoint, BilleteraCuentaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpsSoap12Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpsSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(BilleteraCuentaHttpsSoap12Endpoint, BilleteraCuentaPortType.class, features);
    }
    /**
     *
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpSoap11Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpSoap11Endpoint() {
        return super.getPort(BilleteraCuentaHttpSoap11Endpoint, BilleteraCuentaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpSoap11Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpSoap11Endpoint(WebServiceFeature... features) {
        return super.getPort(BilleteraCuentaHttpSoap11Endpoint, BilleteraCuentaPortType.class, features);
    }
    /**
     *
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpSoap12Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpSoap12Endpoint() {
        return super.getPort(BilleteraCuentaHttpSoap12Endpoint, BilleteraCuentaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BilleteraCuentaPortType
     */
    @WebEndpoint(name = "BilleteraCuentaHttpSoap12Endpoint")
    public BilleteraCuentaPortType getBilleteraCuentaHttpSoap12Endpoint(WebServiceFeature... features) {
        return super.getPort(BilleteraCuentaHttpSoap12Endpoint, BilleteraCuentaPortType.class, features);
    }

}
