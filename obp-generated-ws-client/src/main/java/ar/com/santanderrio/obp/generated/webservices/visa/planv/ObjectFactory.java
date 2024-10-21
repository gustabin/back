
package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.visa.planv package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetInformacionPlanV_QNAME = new QName("http://services.planv.visa.com/", "getInformacionPlanV");
    private final static QName _GetSolicitudesPlanV_QNAME = new QName("http://services.planv.visa.com/", "getSolicitudesPlanV");
    private final static QName _ConfirmarSolicitudPlanVResponse_QNAME = new QName("http://services.planv.visa.com/", "confirmarSolicitudPlanVResponse");
    private final static QName _GetInformacionPlanVResponse_QNAME = new QName("http://services.planv.visa.com/", "getInformacionPlanVResponse");
    private final static QName _GetSolicitudesPlanVResponse_QNAME = new QName("http://services.planv.visa.com/", "getSolicitudesPlanVResponse");
    private final static QName _ConfirmarSolicitudPlanV_QNAME = new QName("http://services.planv.visa.com/", "confirmarSolicitudPlanV");
    private final static QName _EchoResponse_QNAME = new QName("http://services.planv.visa.com/", "echoResponse");
    private final static QName _Echo_QNAME = new QName("http://services.planv.visa.com/", "echo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.visa.planv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSolicitudesPlanVResponse }
     * 
     */
    public GetSolicitudesPlanVResponse createGetSolicitudesPlanVResponse() {
        return new GetSolicitudesPlanVResponse();
    }

    /**
     * Create an instance of {@link GetInformacionPlanVResponse }
     * 
     */
    public GetInformacionPlanVResponse createGetInformacionPlanVResponse() {
        return new GetInformacionPlanVResponse();
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link ConfirmarSolicitudPlanVResponse }
     * 
     */
    public ConfirmarSolicitudPlanVResponse createConfirmarSolicitudPlanVResponse() {
        return new ConfirmarSolicitudPlanVResponse();
    }

    /**
     * Create an instance of {@link GetSolicitudesPlanV }
     * 
     */
    public GetSolicitudesPlanV createGetSolicitudesPlanV() {
        return new GetSolicitudesPlanV();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link ConfirmarSolicitudPlanV }
     * 
     */
    public ConfirmarSolicitudPlanV createConfirmarSolicitudPlanV() {
        return new ConfirmarSolicitudPlanV();
    }

    /**
     * Create an instance of {@link InformacionPlanV }
     * 
     */
    public InformacionPlanV createInformacionPlanV() {
        return new InformacionPlanV();
    }

    /**
     * Create an instance of {@link GetInformacionPlanV }
     * 
     */
    public GetInformacionPlanV createGetInformacionPlanV() {
        return new GetInformacionPlanV();
    }

    /**
     * Create an instance of {@link ConfirmacionSolicitudPlanV }
     * 
     */
    public ConfirmacionSolicitudPlanV createConfirmacionSolicitudPlanV() {
        return new ConfirmacionSolicitudPlanV();
    }

    /**
     * Create an instance of {@link SolicitudPlanV }
     * 
     */
    public SolicitudPlanV createSolicitudPlanV() {
        return new SolicitudPlanV();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInformacionPlanV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "getInformacionPlanV")
    public JAXBElement<GetInformacionPlanV> createGetInformacionPlanV(GetInformacionPlanV value) {
        return new JAXBElement<GetInformacionPlanV>(_GetInformacionPlanV_QNAME, GetInformacionPlanV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSolicitudesPlanV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "getSolicitudesPlanV")
    public JAXBElement<GetSolicitudesPlanV> createGetSolicitudesPlanV(GetSolicitudesPlanV value) {
        return new JAXBElement<GetSolicitudesPlanV>(_GetSolicitudesPlanV_QNAME, GetSolicitudesPlanV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarSolicitudPlanVResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "confirmarSolicitudPlanVResponse")
    public JAXBElement<ConfirmarSolicitudPlanVResponse> createConfirmarSolicitudPlanVResponse(ConfirmarSolicitudPlanVResponse value) {
        return new JAXBElement<ConfirmarSolicitudPlanVResponse>(_ConfirmarSolicitudPlanVResponse_QNAME, ConfirmarSolicitudPlanVResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInformacionPlanVResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "getInformacionPlanVResponse")
    public JAXBElement<GetInformacionPlanVResponse> createGetInformacionPlanVResponse(GetInformacionPlanVResponse value) {
        return new JAXBElement<GetInformacionPlanVResponse>(_GetInformacionPlanVResponse_QNAME, GetInformacionPlanVResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSolicitudesPlanVResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "getSolicitudesPlanVResponse")
    public JAXBElement<GetSolicitudesPlanVResponse> createGetSolicitudesPlanVResponse(GetSolicitudesPlanVResponse value) {
        return new JAXBElement<GetSolicitudesPlanVResponse>(_GetSolicitudesPlanVResponse_QNAME, GetSolicitudesPlanVResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarSolicitudPlanV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "confirmarSolicitudPlanV")
    public JAXBElement<ConfirmarSolicitudPlanV> createConfirmarSolicitudPlanV(ConfirmarSolicitudPlanV value) {
        return new JAXBElement<ConfirmarSolicitudPlanV>(_ConfirmarSolicitudPlanV_QNAME, ConfirmarSolicitudPlanV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Echo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.planv.visa.com/", name = "echo")
    public JAXBElement<Echo> createEcho(Echo value) {
        return new JAXBElement<Echo>(_Echo_QNAME, Echo.class, null, value);
    }

}
