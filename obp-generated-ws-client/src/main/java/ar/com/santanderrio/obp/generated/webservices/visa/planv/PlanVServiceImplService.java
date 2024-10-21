package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2016-09-09T15:40:58.540-03:00
 * Generated source version: 3.0.0
 * 
 */
@WebServiceClient(name = "PlanVServiceImplService", 
                  wsdlLocation = "file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/visa-planv.wsdl",
                  targetNamespace = "http://services.planv.visa.com/") 
public class PlanVServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://services.planv.visa.com/", "PlanVServiceImplService");
    public final static QName PlanVServiceImplPort = new QName("http://services.planv.visa.com/", "PlanVServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/visa-planv.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PlanVServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/sergio.e.goldentair/nuevo/Obp-new/obp-generated-ws-client/src/main/resources/wsdl/visa-planv.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PlanVServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PlanVServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PlanVServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PlanVService
     */
    @WebEndpoint(name = "PlanVServiceImplPort")
    public PlanVService getPlanVServiceImplPort() {
        return super.getPort(PlanVServiceImplPort, PlanVService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PlanVService
     */
    @WebEndpoint(name = "PlanVServiceImplPort")
    public PlanVService getPlanVServiceImplPort(WebServiceFeature... features) {
        return super.getPort(PlanVServiceImplPort, PlanVService.class, features);
    }

}
