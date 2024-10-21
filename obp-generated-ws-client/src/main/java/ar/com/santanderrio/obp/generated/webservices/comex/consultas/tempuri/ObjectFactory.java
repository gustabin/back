
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas.ConsultaBeneficiarioRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas.ObtenerCondicionesVentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaBancosRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaMonedasRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaNomMonedaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaNomPaisRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaPaisesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ObtenerTipoDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.PaisesConDobleConvenioRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.RegimenesGananciasRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales.ConsultaBeneficiarioResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri package. 
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

    private final static QName _ConsultaMonedasRequest_QNAME = new QName("http://tempuri.org/", "request");
    private final static QName _ObtenerTipoDocResponseObtenerTipoDocResult_QNAME = new QName("http://tempuri.org/", "ObtenerTipoDocResult");
    private final static QName _PaisesConDobleConvenioResponsePaisesConDobleConvenioResult_QNAME = new QName("http://tempuri.org/", "PaisesConDobleConvenioResult");
    private final static QName _ConsultaPaisesResponseConsultaPaisesResult_QNAME = new QName("http://tempuri.org/", "ConsultaPaisesResult");
    private final static QName _ConsultaMonedasResponseConsultaMonedasResult_QNAME = new QName("http://tempuri.org/", "ConsultaMonedasResult");
    private final static QName _ObtenerCondicionesVentaResponseObtenerCondicionesVentaResult_QNAME = new QName("http://tempuri.org/", "ObtenerCondicionesVentaResult");
    private final static QName _RegimenesGananciasResponseRegimenesGananciasResult_QNAME = new QName("http://tempuri.org/", "RegimenesGananciasResult");
    private final static QName _ConsultaNomPaisResponseConsultaNomPaisResult_QNAME = new QName("http://tempuri.org/", "ConsultaNomPaisResult");
    private final static QName _ConsultaNomMonedaResponseConsultaNomMonedaResult_QNAME = new QName("http://tempuri.org/", "ConsultaNomMonedaResult");
    private final static QName _ConsultaBeneficiariosResponseConsultaBeneficiariosResult_QNAME = new QName("http://tempuri.org/", "ConsultaBeneficiariosResult");
    private final static QName _ConsultaAgendaResponseConsultaAgendaResult_QNAME = new QName("http://tempuri.org/", "ConsultaAgendaResult");
    private final static QName _ConsultaBancosResponseConsultaBancosResult_QNAME = new QName("http://tempuri.org/", "ConsultaBancosResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaAgenda }
     * 
     */
    public ConsultaAgenda createConsultaAgenda() {
        return new ConsultaAgenda();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaAgendaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaAgendaResponse createConsultaAgendaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaAgendaResponse();
    }

    /**
     * Create an instance of {@link ConsultaBeneficiarios }
     * 
     */
    public ConsultaBeneficiarios createConsultaBeneficiarios() {
        return new ConsultaBeneficiarios();
    }

    /**
     * Create an instance of {@link ConsultaBeneficiariosResponse }
     * 
     */
    public ConsultaBeneficiariosResponse createConsultaBeneficiariosResponse() {
        return new ConsultaBeneficiariosResponse();
    }

    /**
     * Create an instance of {@link ConsultaMonedas }
     * 
     */
    public ConsultaMonedas createConsultaMonedas() {
        return new ConsultaMonedas();
    }

    /**
     * Create an instance of {@link ObtenerCondicionesVenta }
     * 
     */
    public ObtenerCondicionesVenta createObtenerCondicionesVenta() {
        return new ObtenerCondicionesVenta();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaMonedasResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaMonedasResponse createConsultaMonedasResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaMonedasResponse();
    }

    /**
     * Create an instance of {@link RegimenesGanancias }
     * 
     */
    public RegimenesGanancias createRegimenesGanancias() {
        return new RegimenesGanancias();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerCondicionesVentaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerCondicionesVentaResponse createObtenerCondicionesVentaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerCondicionesVentaResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.RegimenesGananciasResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.RegimenesGananciasResponse createRegimenesGananciasResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.RegimenesGananciasResponse();
    }

    /**
     * Create an instance of {@link ObtenerTipoDoc }
     * 
     */
    public ObtenerTipoDoc createObtenerTipoDoc() {
        return new ObtenerTipoDoc();
    }

    /**
     * Create an instance of {@link ConsultaBancos }
     * 
     */
    public ConsultaBancos createConsultaBancos() {
        return new ConsultaBancos();
    }

    /**
     * Create an instance of {@link ConsultaNomMoneda }
     * 
     */
    public ConsultaNomMoneda createConsultaNomMoneda() {
        return new ConsultaNomMoneda();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerTipoDocResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerTipoDocResponse createObtenerTipoDocResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerTipoDocResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaBancosResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaBancosResponse createConsultaBancosResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaBancosResponse();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomMonedaResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomMonedaResponse createConsultaNomMonedaResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomMonedaResponse();
    }

    /**
     * Create an instance of {@link ConsultaNomPais }
     * 
     */
    public ConsultaNomPais createConsultaNomPais() {
        return new ConsultaNomPais();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomPaisResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomPaisResponse createConsultaNomPaisResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomPaisResponse();
    }

    /**
     * Create an instance of {@link PaisesConDobleConvenio }
     * 
     */
    public PaisesConDobleConvenio createPaisesConDobleConvenio() {
        return new PaisesConDobleConvenio();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.PaisesConDobleConvenioResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.PaisesConDobleConvenioResponse createPaisesConDobleConvenioResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.PaisesConDobleConvenioResponse();
    }

    /**
     * Create an instance of {@link ConsultaPaises }
     * 
     */
    public ConsultaPaises createConsultaPaises() {
        return new ConsultaPaises();
    }

    /**
     * Create an instance of {@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaPaisesResponse }
     * 
     */
    public ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaPaisesResponse createConsultaPaisesResponse() {
        return new ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaPaisesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMonedasRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaMonedas.class)
    public JAXBElement<ConsultaMonedasRequest> createConsultaMonedasRequest(ConsultaMonedasRequest value) {
        return new JAXBElement<ConsultaMonedasRequest>(_ConsultaMonedasRequest_QNAME, ConsultaMonedasRequest.class, ConsultaMonedas.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomMonedaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaNomMoneda.class)
    public JAXBElement<ConsultaNomMonedaRequest> createConsultaNomMonedaRequest(ConsultaNomMonedaRequest value) {
        return new JAXBElement<ConsultaNomMonedaRequest>(_ConsultaMonedasRequest_QNAME, ConsultaNomMonedaRequest.class, ConsultaNomMoneda.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaAgenda.class)
    public JAXBElement<ConsultaAgendaRequest> createConsultaAgendaRequest(ConsultaAgendaRequest value) {
        return new JAXBElement<ConsultaAgendaRequest>(_ConsultaMonedasRequest_QNAME, ConsultaAgendaRequest.class, ConsultaAgenda.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTipoDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ObtenerTipoDoc.class)
    public JAXBElement<ObtenerTipoDocRequest> createObtenerTipoDocRequest(ObtenerTipoDocRequest value) {
        return new JAXBElement<ObtenerTipoDocRequest>(_ConsultaMonedasRequest_QNAME, ObtenerTipoDocRequest.class, ObtenerTipoDoc.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerTipoDocResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerTipoDocResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse> createObtenerTipoDocResponseObtenerTipoDocResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse>(_ObtenerTipoDocResponseObtenerTipoDocResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerTipoDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "PaisesConDobleConvenioResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.PaisesConDobleConvenioResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse> createPaisesConDobleConvenioResponsePaisesConDobleConvenioResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse>(_PaisesConDobleConvenioResponsePaisesConDobleConvenioResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.PaisesConDobleConvenioResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaPaisesResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaPaisesResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse> createConsultaPaisesResponseConsultaPaisesResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse>(_ConsultaPaisesResponseConsultaPaisesResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaPaisesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaPaisesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaPaises.class)
    public JAXBElement<ConsultaPaisesRequest> createConsultaPaisesRequest(ConsultaPaisesRequest value) {
        return new JAXBElement<ConsultaPaisesRequest>(_ConsultaMonedasRequest_QNAME, ConsultaPaisesRequest.class, ConsultaPaises.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaBeneficiarios.class)
    public JAXBElement<ConsultaBeneficiarioRequest> createConsultaBeneficiariosRequest(ConsultaBeneficiarioRequest value) {
        return new JAXBElement<ConsultaBeneficiarioRequest>(_ConsultaMonedasRequest_QNAME, ConsultaBeneficiarioRequest.class, ConsultaBeneficiarios.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaMonedasResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaMonedasResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse> createConsultaMonedasResponseConsultaMonedasResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse>(_ConsultaMonedasResponseConsultaMonedasResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaMonedasResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegimenesGananciasRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = RegimenesGanancias.class)
    public JAXBElement<RegimenesGananciasRequest> createRegimenesGananciasRequest(RegimenesGananciasRequest value) {
        return new JAXBElement<RegimenesGananciasRequest>(_ConsultaMonedasRequest_QNAME, RegimenesGananciasRequest.class, RegimenesGanancias.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ObtenerCondicionesVentaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerCondicionesVentaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse> createObtenerCondicionesVentaResponseObtenerCondicionesVentaResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse>(_ObtenerCondicionesVentaResponseObtenerCondicionesVentaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ObtenerCondicionesVentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RegimenesGananciasResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.RegimenesGananciasResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse> createRegimenesGananciasResponseRegimenesGananciasResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse>(_RegimenesGananciasResponseRegimenesGananciasResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.RegimenesGananciasResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaNomPaisResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomPaisResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse> createConsultaNomPaisResponseConsultaNomPaisResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse>(_ConsultaNomPaisResponseConsultaNomPaisResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomPaisResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaNomMonedaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomMonedaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse> createConsultaNomMonedaResponseConsultaNomMonedaResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse>(_ConsultaNomMonedaResponseConsultaNomMonedaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaNomMonedaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaisesConDobleConvenioRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = PaisesConDobleConvenio.class)
    public JAXBElement<PaisesConDobleConvenioRequest> createPaisesConDobleConvenioRequest(PaisesConDobleConvenioRequest value) {
        return new JAXBElement<PaisesConDobleConvenioRequest>(_ConsultaMonedasRequest_QNAME, PaisesConDobleConvenioRequest.class, PaisesConDobleConvenio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomPaisRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaNomPais.class)
    public JAXBElement<ConsultaNomPaisRequest> createConsultaNomPaisRequest(ConsultaNomPaisRequest value) {
        return new JAXBElement<ConsultaNomPaisRequest>(_ConsultaMonedasRequest_QNAME, ConsultaNomPaisRequest.class, ConsultaNomPais.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCondicionesVentaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ObtenerCondicionesVenta.class)
    public JAXBElement<ObtenerCondicionesVentaRequest> createObtenerCondicionesVentaRequest(ObtenerCondicionesVentaRequest value) {
        return new JAXBElement<ObtenerCondicionesVentaRequest>(_ConsultaMonedasRequest_QNAME, ObtenerCondicionesVentaRequest.class, ObtenerCondicionesVenta.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBeneficiarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaBeneficiariosResult", scope = ConsultaBeneficiariosResponse.class)
    public JAXBElement<ConsultaBeneficiarioResponse> createConsultaBeneficiariosResponseConsultaBeneficiariosResult(ConsultaBeneficiarioResponse value) {
        return new JAXBElement<ConsultaBeneficiarioResponse>(_ConsultaBeneficiariosResponseConsultaBeneficiariosResult_QNAME, ConsultaBeneficiarioResponse.class, ConsultaBeneficiariosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBancosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "request", scope = ConsultaBancos.class)
    public JAXBElement<ConsultaBancosRequest> createConsultaBancosRequest(ConsultaBancosRequest value) {
        return new JAXBElement<ConsultaBancosRequest>(_ConsultaMonedasRequest_QNAME, ConsultaBancosRequest.class, ConsultaBancos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaAgendaResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaAgendaResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse> createConsultaAgendaResponseConsultaAgendaResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse>(_ConsultaAgendaResponseConsultaAgendaResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaAgendaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsultaBancosResult", scope = ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaBancosResponse.class)
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse> createConsultaBancosResponseConsultaBancosResult(ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse value) {
        return new JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse>(_ConsultaBancosResponseConsultaBancosResult_QNAME, ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse.class, ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.ConsultaBancosResponse.class, value);
    }

}
