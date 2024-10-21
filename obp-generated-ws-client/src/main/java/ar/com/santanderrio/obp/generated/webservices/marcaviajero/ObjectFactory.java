
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.marcaviajero package. 
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

    private final static QName _ObtenerViajesResponse_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerViajesResponse");
    private final static QName _ObtenerViajesRequest_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerViajesRequest");
    private final static QName _ConfirmarViajeRequest_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "confirmarViajeRequest");
    private final static QName _Response_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "response");
    private final static QName _Viaje_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "viaje");
    private final static QName _ObtenerTarjetasDelSocioRequest_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerTarjetasDelSocioRequest");
    private final static QName _Pais_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "pais");
    private final static QName _Request_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "request");
    private final static QName _Fecha_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "fecha");
    private final static QName _ConfirmarViajeResponse_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "confirmarViajeResponse");
    private final static QName _ObtenerPaisesResponse_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerPaisesResponse");
    private final static QName _Tarjeta_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "tarjeta");
    private final static QName _EliminarViajeResponse_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "eliminarViajeResponse");
    private final static QName _ObtenerPaisesRequest_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerPaisesRequest");
    private final static QName _EliminarViajeRequest_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "eliminarViajeRequest");
    private final static QName _TarjetaConFecha_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "tarjetaConFecha");
    private final static QName _Message_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "message");
    private final static QName _ObtenerTarjetasDelSocioResponse_QNAME = new QName("http://visa.com.ar/visahome/integration/ws/mv", "obtenerTarjetasDelSocioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.marcaviajero
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObtenerViajesResponse }
     * 
     */
    public ObtenerViajesResponse createObtenerViajesResponse() {
        return new ObtenerViajesResponse();
    }

    /**
     * Create an instance of {@link ObtenerPaisesResponse }
     * 
     */
    public ObtenerPaisesResponse createObtenerPaisesResponse() {
        return new ObtenerPaisesResponse();
    }

    /**
     * Create an instance of {@link ObtenerTarjetasDelSocioResponse }
     * 
     */
    public ObtenerTarjetasDelSocioResponse createObtenerTarjetasDelSocioResponse() {
        return new ObtenerTarjetasDelSocioResponse();
    }

    /**
     * Create an instance of {@link Viaje }
     * 
     */
    public Viaje createViaje() {
        return new Viaje();
    }

    /**
     * Create an instance of {@link TarjetaConFecha }
     * 
     */
    public TarjetaConFecha createTarjetaConFecha() {
        return new TarjetaConFecha();
    }

    /**
     * Create an instance of {@link Pais }
     * 
     */
    public Pais createPais() {
        return new Pais();
    }

    /**
     * Create an instance of {@link Fecha }
     * 
     */
    public Fecha createFecha() {
        return new Fecha();
    }

    /**
     * Create an instance of {@link Tarjeta }
     * 
     */
    public Tarjeta createTarjeta() {
        return new Tarjeta();
    }

    /**
     * Create an instance of {@link BaseGatewayResponse }
     * 
     */
    public BaseGatewayResponse createBaseGatewayResponse() {
        return new BaseGatewayResponse();
    }

    /**
     * Create an instance of {@link BaseGatewayMessage }
     * 
     */
    public BaseGatewayMessage createBaseGatewayMessage() {
        return new BaseGatewayMessage();
    }

    /**
     * Create an instance of {@link BaseGatewayRequest }
     * 
     */
    public BaseGatewayRequest createBaseGatewayRequest() {
        return new BaseGatewayRequest();
    }

    /**
     * Create an instance of {@link EchoViajeResponse }
     * 
     */
    public EchoViajeResponse createEchoViajeResponse() {
        return new EchoViajeResponse();
    }

    /**
     * Create an instance of {@link EliminarViajeRequest }
     * 
     */
    public EliminarViajeRequest createEliminarViajeRequest() {
        return new EliminarViajeRequest();
    }

    /**
     * Create an instance of {@link EliminarViajeResponse }
     * 
     */
    public EliminarViajeResponse createEliminarViajeResponse() {
        return new EliminarViajeResponse();
    }

    /**
     * Create an instance of {@link EchoViajeRequest }
     * 
     */
    public EchoViajeRequest createEchoViajeRequest() {
        return new EchoViajeRequest();
    }

    /**
     * Create an instance of {@link ObtenerViajesRequest }
     * 
     */
    public ObtenerViajesRequest createObtenerViajesRequest() {
        return new ObtenerViajesRequest();
    }

    /**
     * Create an instance of {@link ObtenerPaisesRequest }
     * 
     */
    public ObtenerPaisesRequest createObtenerPaisesRequest() {
        return new ObtenerPaisesRequest();
    }

    /**
     * Create an instance of {@link ObtenerTarjetasDelSocioRequest }
     * 
     */
    public ObtenerTarjetasDelSocioRequest createObtenerTarjetasDelSocioRequest() {
        return new ObtenerTarjetasDelSocioRequest();
    }

    /**
     * Create an instance of {@link ConfirmarViajeRequest }
     * 
     */
    public ConfirmarViajeRequest createConfirmarViajeRequest() {
        return new ConfirmarViajeRequest();
    }

    /**
     * Create an instance of {@link ConfirmarViajeResponse }
     * 
     */
    public ConfirmarViajeResponse createConfirmarViajeResponse() {
        return new ConfirmarViajeResponse();
    }

    /**
     * Create an instance of {@link ObtenerViajesResponse.ListaViajes }
     * 
     */
    public ObtenerViajesResponse.ListaViajes createObtenerViajesResponseListaViajes() {
        return new ObtenerViajesResponse.ListaViajes();
    }

    /**
     * Create an instance of {@link ObtenerPaisesResponse.Paises }
     * 
     */
    public ObtenerPaisesResponse.Paises createObtenerPaisesResponsePaises() {
        return new ObtenerPaisesResponse.Paises();
    }

    /**
     * Create an instance of {@link ObtenerTarjetasDelSocioResponse.Tarjetas }
     * 
     */
    public ObtenerTarjetasDelSocioResponse.Tarjetas createObtenerTarjetasDelSocioResponseTarjetas() {
        return new ObtenerTarjetasDelSocioResponse.Tarjetas();
    }

    /**
     * Create an instance of {@link Viaje.Paises }
     * 
     */
    public Viaje.Paises createViajePaises() {
        return new Viaje.Paises();
    }

    /**
     * Create an instance of {@link Viaje.Tarjetas }
     * 
     */
    public Viaje.Tarjetas createViajeTarjetas() {
        return new Viaje.Tarjetas();
    }

    /**
     * Create an instance of {@link Viaje.AccionesPermitidas }
     * 
     */
    public Viaje.AccionesPermitidas createViajeAccionesPermitidas() {
        return new Viaje.AccionesPermitidas();
    }

    /**
     * Create an instance of {@link TarjetaConFecha.FechasInhabilitadas }
     * 
     */
    public TarjetaConFecha.FechasInhabilitadas createTarjetaConFechaFechasInhabilitadas() {
        return new TarjetaConFecha.FechasInhabilitadas();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerViajesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerViajesResponse")
    public JAXBElement<ObtenerViajesResponse> createObtenerViajesResponse(ObtenerViajesResponse value) {
        return new JAXBElement<ObtenerViajesResponse>(_ObtenerViajesResponse_QNAME, ObtenerViajesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerViajesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerViajesRequest")
    public JAXBElement<ObtenerViajesRequest> createObtenerViajesRequest(ObtenerViajesRequest value) {
        return new JAXBElement<ObtenerViajesRequest>(_ObtenerViajesRequest_QNAME, ObtenerViajesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarViajeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "confirmarViajeRequest")
    public JAXBElement<ConfirmarViajeRequest> createConfirmarViajeRequest(ConfirmarViajeRequest value) {
        return new JAXBElement<ConfirmarViajeRequest>(_ConfirmarViajeRequest_QNAME, ConfirmarViajeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "response")
    public JAXBElement<Object> createResponse(Object value) {
        return new JAXBElement<Object>(_Response_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Viaje }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "viaje")
    public JAXBElement<Viaje> createViaje(Viaje value) {
        return new JAXBElement<Viaje>(_Viaje_QNAME, Viaje.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTarjetasDelSocioRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerTarjetasDelSocioRequest")
    public JAXBElement<ObtenerTarjetasDelSocioRequest> createObtenerTarjetasDelSocioRequest(ObtenerTarjetasDelSocioRequest value) {
        return new JAXBElement<ObtenerTarjetasDelSocioRequest>(_ObtenerTarjetasDelSocioRequest_QNAME, ObtenerTarjetasDelSocioRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pais }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "pais")
    public JAXBElement<Pais> createPais(Pais value) {
        return new JAXBElement<Pais>(_Pais_QNAME, Pais.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "request")
    public JAXBElement<Object> createRequest(Object value) {
        return new JAXBElement<Object>(_Request_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fecha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "fecha")
    public JAXBElement<Fecha> createFecha(Fecha value) {
        return new JAXBElement<Fecha>(_Fecha_QNAME, Fecha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmarViajeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "confirmarViajeResponse")
    public JAXBElement<ConfirmarViajeResponse> createConfirmarViajeResponse(ConfirmarViajeResponse value) {
        return new JAXBElement<ConfirmarViajeResponse>(_ConfirmarViajeResponse_QNAME, ConfirmarViajeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPaisesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerPaisesResponse")
    public JAXBElement<ObtenerPaisesResponse> createObtenerPaisesResponse(ObtenerPaisesResponse value) {
        return new JAXBElement<ObtenerPaisesResponse>(_ObtenerPaisesResponse_QNAME, ObtenerPaisesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tarjeta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "tarjeta")
    public JAXBElement<Tarjeta> createTarjeta(Tarjeta value) {
        return new JAXBElement<Tarjeta>(_Tarjeta_QNAME, Tarjeta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarViajeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "eliminarViajeResponse")
    public JAXBElement<EliminarViajeResponse> createEliminarViajeResponse(EliminarViajeResponse value) {
        return new JAXBElement<EliminarViajeResponse>(_EliminarViajeResponse_QNAME, EliminarViajeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerPaisesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerPaisesRequest")
    public JAXBElement<ObtenerPaisesRequest> createObtenerPaisesRequest(ObtenerPaisesRequest value) {
        return new JAXBElement<ObtenerPaisesRequest>(_ObtenerPaisesRequest_QNAME, ObtenerPaisesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarViajeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "eliminarViajeRequest")
    public JAXBElement<EliminarViajeRequest> createEliminarViajeRequest(EliminarViajeRequest value) {
        return new JAXBElement<EliminarViajeRequest>(_EliminarViajeRequest_QNAME, EliminarViajeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TarjetaConFecha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "tarjetaConFecha")
    public JAXBElement<TarjetaConFecha> createTarjetaConFecha(TarjetaConFecha value) {
        return new JAXBElement<TarjetaConFecha>(_TarjetaConFecha_QNAME, TarjetaConFecha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseGatewayMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "message")
    public JAXBElement<BaseGatewayMessage> createMessage(BaseGatewayMessage value) {
        return new JAXBElement<BaseGatewayMessage>(_Message_QNAME, BaseGatewayMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTarjetasDelSocioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://visa.com.ar/visahome/integration/ws/mv", name = "obtenerTarjetasDelSocioResponse")
    public JAXBElement<ObtenerTarjetasDelSocioResponse> createObtenerTarjetasDelSocioResponse(ObtenerTarjetasDelSocioResponse value) {
        return new JAXBElement<ObtenerTarjetasDelSocioResponse>(_ObtenerTarjetasDelSocioResponse_QNAME, ObtenerTarjetasDelSocioResponse.class, null, value);
    }

}
