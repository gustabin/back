package ar.com.santanderrio.obp.generated.webservices.crm;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2016-12-06T14:40:02.576-03:00
 * Generated source version: 3.0.0
 * 
 */
@WebServiceClient(name = "AlertasCrm", 
                  wsdlLocation = "file:/P:/vistas/projects/obpAgileDeploy/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AlertaPorCanal.wsdl",
                  targetNamespace = "http://model.alertas.crm.bancorio.com/") 
public class AlertasCrm extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://model.alertas.crm.bancorio.com/", "AlertasCrm");
    public final static QName AlertasCrmSoap = new QName("http://model.alertas.crm.bancorio.com/", "AlertasCrmSoap");
    public final static QName AlertasCrmSoap12 = new QName("http://model.alertas.crm.bancorio.com/", "AlertasCrmSoap12");
    static {
        URL url = null;
        try {
            url = new URL("file:/P:/vistas/projects/obpAgileDeploy/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AlertaPorCanal.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AlertasCrm.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/P:/vistas/projects/obpAgileDeploy/OBP/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/AlertaPorCanal.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AlertasCrm(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AlertasCrm(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AlertasCrm() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns AlertasCrmSoap
     */
    @WebEndpoint(name = "AlertasCrmSoap")
    public AlertasCrmSoap getAlertasCrmSoap() {
        return super.getPort(AlertasCrmSoap, AlertasCrmSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AlertasCrmSoap
     */
    @WebEndpoint(name = "AlertasCrmSoap")
    public AlertasCrmSoap getAlertasCrmSoap(WebServiceFeature... features) {
        return super.getPort(AlertasCrmSoap, AlertasCrmSoap.class, features);
    }
    /**
     *
     * @return
     *     returns AlertasCrmSoap
     */
    @WebEndpoint(name = "AlertasCrmSoap12")
    public AlertasCrmSoap getAlertasCrmSoap12() {
        return super.getPort(AlertasCrmSoap12, AlertasCrmSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AlertasCrmSoap
     */
    @WebEndpoint(name = "AlertasCrmSoap12")
    public AlertasCrmSoap getAlertasCrmSoap12(WebServiceFeature... features) {
        return super.getPort(AlertasCrmSoap12, AlertasCrmSoap.class, features);
    }

}
