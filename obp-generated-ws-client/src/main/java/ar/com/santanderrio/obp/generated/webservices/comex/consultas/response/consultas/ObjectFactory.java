
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfAgenda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBanco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfCondicionVenta;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfMoneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfNomMoneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfNomPais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfPais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfPaisesDobleConv;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfRegimenGanancia;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfTipoDoc;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas package. 
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

    private final static QName _ConsultaNomPaisResponse_QNAME = new QName("Response/Consultas", "ConsultaNomPaisResponse");
    private final static QName _PaisesConDobleConvenioResponse_QNAME = new QName("Response/Consultas", "PaisesConDobleConvenioResponse");
    private final static QName _ObtenerTipoDocResponse_QNAME = new QName("Response/Consultas", "ObtenerTipoDocResponse");
    private final static QName _ConsultaNomMonedaResponse_QNAME = new QName("Response/Consultas", "ConsultaNomMonedaResponse");
    private final static QName _ConsultaAgendaResponse_QNAME = new QName("Response/Consultas", "ConsultaAgendaResponse");
    private final static QName _ObtenerCondicionesVentaResponse_QNAME = new QName("Response/Consultas", "ObtenerCondicionesVentaResponse");
    private final static QName _ConsultaBancosResponse_QNAME = new QName("Response/Consultas", "ConsultaBancosResponse");
    private final static QName _ConsultaPaisesResponse_QNAME = new QName("Response/Consultas", "ConsultaPaisesResponse");
    private final static QName _RegimenesGananciasResponse_QNAME = new QName("Response/Consultas", "RegimenesGananciasResponse");
    private final static QName _ConsultaMonedasResponse_QNAME = new QName("Response/Consultas", "ConsultaMonedasResponse");
    private final static QName _ConsultaBancosResponseRegistros_QNAME = new QName("Response/Consultas", "Registros");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaAgendaResponse }
     * 
     */
    public ConsultaAgendaResponse createConsultaAgendaResponse() {
        return new ConsultaAgendaResponse();
    }

    /**
     * Create an instance of {@link ConsultaNomPaisResponse }
     * 
     */
    public ConsultaNomPaisResponse createConsultaNomPaisResponse() {
        return new ConsultaNomPaisResponse();
    }

    /**
     * Create an instance of {@link ConsultaMonedasResponse }
     * 
     */
    public ConsultaMonedasResponse createConsultaMonedasResponse() {
        return new ConsultaMonedasResponse();
    }

    /**
     * Create an instance of {@link ObtenerCondicionesVentaResponse }
     * 
     */
    public ObtenerCondicionesVentaResponse createObtenerCondicionesVentaResponse() {
        return new ObtenerCondicionesVentaResponse();
    }

    /**
     * Create an instance of {@link RegimenesGananciasResponse }
     * 
     */
    public RegimenesGananciasResponse createRegimenesGananciasResponse() {
        return new RegimenesGananciasResponse();
    }

    /**
     * Create an instance of {@link PaisesConDobleConvenioResponse }
     * 
     */
    public PaisesConDobleConvenioResponse createPaisesConDobleConvenioResponse() {
        return new PaisesConDobleConvenioResponse();
    }

    /**
     * Create an instance of {@link ObtenerTipoDocResponse }
     * 
     */
    public ObtenerTipoDocResponse createObtenerTipoDocResponse() {
        return new ObtenerTipoDocResponse();
    }

    /**
     * Create an instance of {@link ConsultaNomMonedaResponse }
     * 
     */
    public ConsultaNomMonedaResponse createConsultaNomMonedaResponse() {
        return new ConsultaNomMonedaResponse();
    }

    /**
     * Create an instance of {@link ConsultaBancosResponse }
     * 
     */
    public ConsultaBancosResponse createConsultaBancosResponse() {
        return new ConsultaBancosResponse();
    }

    /**
     * Create an instance of {@link ConsultaPaisesResponse }
     * 
     */
    public ConsultaPaisesResponse createConsultaPaisesResponse() {
        return new ConsultaPaisesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomPaisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaNomPaisResponse")
    public JAXBElement<ConsultaNomPaisResponse> createConsultaNomPaisResponse(ConsultaNomPaisResponse value) {
        return new JAXBElement<ConsultaNomPaisResponse>(_ConsultaNomPaisResponse_QNAME, ConsultaNomPaisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaisesConDobleConvenioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "PaisesConDobleConvenioResponse")
    public JAXBElement<PaisesConDobleConvenioResponse> createPaisesConDobleConvenioResponse(PaisesConDobleConvenioResponse value) {
        return new JAXBElement<PaisesConDobleConvenioResponse>(_PaisesConDobleConvenioResponse_QNAME, PaisesConDobleConvenioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTipoDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ObtenerTipoDocResponse")
    public JAXBElement<ObtenerTipoDocResponse> createObtenerTipoDocResponse(ObtenerTipoDocResponse value) {
        return new JAXBElement<ObtenerTipoDocResponse>(_ObtenerTipoDocResponse_QNAME, ObtenerTipoDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomMonedaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaNomMonedaResponse")
    public JAXBElement<ConsultaNomMonedaResponse> createConsultaNomMonedaResponse(ConsultaNomMonedaResponse value) {
        return new JAXBElement<ConsultaNomMonedaResponse>(_ConsultaNomMonedaResponse_QNAME, ConsultaNomMonedaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaAgendaResponse")
    public JAXBElement<ConsultaAgendaResponse> createConsultaAgendaResponse(ConsultaAgendaResponse value) {
        return new JAXBElement<ConsultaAgendaResponse>(_ConsultaAgendaResponse_QNAME, ConsultaAgendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCondicionesVentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ObtenerCondicionesVentaResponse")
    public JAXBElement<ObtenerCondicionesVentaResponse> createObtenerCondicionesVentaResponse(ObtenerCondicionesVentaResponse value) {
        return new JAXBElement<ObtenerCondicionesVentaResponse>(_ObtenerCondicionesVentaResponse_QNAME, ObtenerCondicionesVentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBancosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaBancosResponse")
    public JAXBElement<ConsultaBancosResponse> createConsultaBancosResponse(ConsultaBancosResponse value) {
        return new JAXBElement<ConsultaBancosResponse>(_ConsultaBancosResponse_QNAME, ConsultaBancosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaPaisesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaPaisesResponse")
    public JAXBElement<ConsultaPaisesResponse> createConsultaPaisesResponse(ConsultaPaisesResponse value) {
        return new JAXBElement<ConsultaPaisesResponse>(_ConsultaPaisesResponse_QNAME, ConsultaPaisesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegimenesGananciasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "RegimenesGananciasResponse")
    public JAXBElement<RegimenesGananciasResponse> createRegimenesGananciasResponse(RegimenesGananciasResponse value) {
        return new JAXBElement<RegimenesGananciasResponse>(_RegimenesGananciasResponse_QNAME, RegimenesGananciasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMonedasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "ConsultaMonedasResponse")
    public JAXBElement<ConsultaMonedasResponse> createConsultaMonedasResponse(ConsultaMonedasResponse value) {
        return new JAXBElement<ConsultaMonedasResponse>(_ConsultaMonedasResponse_QNAME, ConsultaMonedasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBanco }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaBancosResponse.class)
    public JAXBElement<ArrayOfBanco> createConsultaBancosResponseRegistros(ArrayOfBanco value) {
        return new JAXBElement<ArrayOfBanco>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfBanco.class, ConsultaBancosResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPais }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaPaisesResponse.class)
    public JAXBElement<ArrayOfPais> createConsultaPaisesResponseRegistros(ArrayOfPais value) {
        return new JAXBElement<ArrayOfPais>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfPais.class, ConsultaPaisesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfNomPais }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaNomPaisResponse.class)
    public JAXBElement<ArrayOfNomPais> createConsultaNomPaisResponseRegistros(ArrayOfNomPais value) {
        return new JAXBElement<ArrayOfNomPais>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfNomPais.class, ConsultaNomPaisResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTipoDoc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ObtenerTipoDocResponse.class)
    public JAXBElement<ArrayOfTipoDoc> createObtenerTipoDocResponseRegistros(ArrayOfTipoDoc value) {
        return new JAXBElement<ArrayOfTipoDoc>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfTipoDoc.class, ObtenerTipoDocResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfMoneda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaMonedasResponse.class)
    public JAXBElement<ArrayOfMoneda> createConsultaMonedasResponseRegistros(ArrayOfMoneda value) {
        return new JAXBElement<ArrayOfMoneda>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfMoneda.class, ConsultaMonedasResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfNomMoneda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaNomMonedaResponse.class)
    public JAXBElement<ArrayOfNomMoneda> createConsultaNomMonedaResponseRegistros(ArrayOfNomMoneda value) {
        return new JAXBElement<ArrayOfNomMoneda>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfNomMoneda.class, ConsultaNomMonedaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPaisesDobleConv }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = PaisesConDobleConvenioResponse.class)
    public JAXBElement<ArrayOfPaisesDobleConv> createPaisesConDobleConvenioResponseRegistros(ArrayOfPaisesDobleConv value) {
        return new JAXBElement<ArrayOfPaisesDobleConv>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfPaisesDobleConv.class, PaisesConDobleConvenioResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCondicionVenta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ObtenerCondicionesVentaResponse.class)
    public JAXBElement<ArrayOfCondicionVenta> createObtenerCondicionesVentaResponseRegistros(ArrayOfCondicionVenta value) {
        return new JAXBElement<ArrayOfCondicionVenta>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfCondicionVenta.class, ObtenerCondicionesVentaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAgenda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = ConsultaAgendaResponse.class)
    public JAXBElement<ArrayOfAgenda> createConsultaAgendaResponseRegistros(ArrayOfAgenda value) {
        return new JAXBElement<ArrayOfAgenda>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfAgenda.class, ConsultaAgendaResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRegimenGanancia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Response/Consultas", name = "Registros", scope = RegimenesGananciasResponse.class)
    public JAXBElement<ArrayOfRegimenGanancia> createRegimenesGananciasResponseRegistros(ArrayOfRegimenGanancia value) {
        return new JAXBElement<ArrayOfRegimenGanancia>(_ConsultaBancosResponseRegistros_QNAME, ArrayOfRegimenGanancia.class, RegimenesGananciasResponse.class, value);
    }

}
