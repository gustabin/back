
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas package. 
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

    private final static QName _ConsultaAgendaRequest_QNAME = new QName("Request/Consultas", "ConsultaAgendaRequest");
    private final static QName _ConsultaBancosRequest_QNAME = new QName("Request/Consultas", "ConsultaBancosRequest");
    private final static QName _ConsultaPaisesRequest_QNAME = new QName("Request/Consultas", "ConsultaPaisesRequest");
    private final static QName _ObtenerTipoDocRequest_QNAME = new QName("Request/Consultas", "ObtenerTipoDocRequest");
    private final static QName _ConsultaNomMonedaRequest_QNAME = new QName("Request/Consultas", "ConsultaNomMonedaRequest");
    private final static QName _RegimenesGananciasRequest_QNAME = new QName("Request/Consultas", "RegimenesGananciasRequest");
    private final static QName _ConsultaNomPaisRequest_QNAME = new QName("Request/Consultas", "ConsultaNomPaisRequest");
    private final static QName _PaisesConDobleConvenioRequest_QNAME = new QName("Request/Consultas", "PaisesConDobleConvenioRequest");
    private final static QName _ConsultaMonedasRequest_QNAME = new QName("Request/Consultas", "ConsultaMonedasRequest");
    private final static QName _ConsultaNomMonedaRequestCodigoMoneda_QNAME = new QName("Request/Consultas", "Codigo_Moneda");
    private final static QName _ConsultaAgendaRequestTipoAgenda_QNAME = new QName("Request/Consultas", "Tipo_Agenda");
    private final static QName _ConsultaAgendaRequestIdAgenda_QNAME = new QName("Request/Consultas", "IdAgenda");
    private final static QName _ConsultaAgendaRequestNupCliente_QNAME = new QName("Request/Consultas", "Nup_Cliente");
    private final static QName _RegimenesGananciasRequestCodPaisBCRA_QNAME = new QName("Request/Consultas", "Cod_Pais_BCRA");
    private final static QName _RegimenesGananciasRequestConINPI_QNAME = new QName("Request/Consultas", "Con_INPI");
    private final static QName _RegimenesGananciasRequestConCDI_QNAME = new QName("Request/Consultas", "Con_CDI");
    private final static QName _RegimenesGananciasRequestConceptoBCRA_QNAME = new QName("Request/Consultas", "Concepto_BCRA");
    private final static QName _RegimenesGananciasRequestIdAlicuota_QNAME = new QName("Request/Consultas", "Id_Alicuota");
    private final static QName _ConsultaBancosRequestSwiftBanco_QNAME = new QName("Request/Consultas", "Swift_Banco");
    private final static QName _ConsultaBancosRequestAbaBanco_QNAME = new QName("Request/Consultas", "Aba_Banco");
    private final static QName _ConsultaBancosRequestSoloSeguimiento_QNAME = new QName("Request/Consultas", "Solo_Seguimiento");
    private final static QName _ConsultaBancosRequestPaisBanco_QNAME = new QName("Request/Consultas", "Pais_Banco");
    private final static QName _ConsultaBancosRequestNombreBanco_QNAME = new QName("Request/Consultas", "Nombre_Banco");
    private final static QName _ConsultaBancosRequestLocalidadBanco_QNAME = new QName("Request/Consultas", "Localidad_Banco");
    private final static QName _ConsultaNomPaisRequestCodigoPais_QNAME = new QName("Request/Consultas", "Codigo_Pais");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaAgendaRequest }
     * 
     */
    public ConsultaAgendaRequest createConsultaAgendaRequest() {
        return new ConsultaAgendaRequest();
    }

    /**
     * Create an instance of {@link ConsultaMonedasRequest }
     * 
     */
    public ConsultaMonedasRequest createConsultaMonedasRequest() {
        return new ConsultaMonedasRequest();
    }

    /**
     * Create an instance of {@link RegimenesGananciasRequest }
     * 
     */
    public RegimenesGananciasRequest createRegimenesGananciasRequest() {
        return new RegimenesGananciasRequest();
    }

    /**
     * Create an instance of {@link ObtenerTipoDocRequest }
     * 
     */
    public ObtenerTipoDocRequest createObtenerTipoDocRequest() {
        return new ObtenerTipoDocRequest();
    }

    /**
     * Create an instance of {@link ConsultaBancosRequest }
     * 
     */
    public ConsultaBancosRequest createConsultaBancosRequest() {
        return new ConsultaBancosRequest();
    }

    /**
     * Create an instance of {@link ConsultaNomMonedaRequest }
     * 
     */
    public ConsultaNomMonedaRequest createConsultaNomMonedaRequest() {
        return new ConsultaNomMonedaRequest();
    }

    /**
     * Create an instance of {@link ConsultaNomPaisRequest }
     * 
     */
    public ConsultaNomPaisRequest createConsultaNomPaisRequest() {
        return new ConsultaNomPaisRequest();
    }

    /**
     * Create an instance of {@link PaisesConDobleConvenioRequest }
     * 
     */
    public PaisesConDobleConvenioRequest createPaisesConDobleConvenioRequest() {
        return new PaisesConDobleConvenioRequest();
    }

    /**
     * Create an instance of {@link ConsultaPaisesRequest }
     * 
     */
    public ConsultaPaisesRequest createConsultaPaisesRequest() {
        return new ConsultaPaisesRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaAgendaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaAgendaRequest")
    public JAXBElement<ConsultaAgendaRequest> createConsultaAgendaRequest(ConsultaAgendaRequest value) {
        return new JAXBElement<ConsultaAgendaRequest>(_ConsultaAgendaRequest_QNAME, ConsultaAgendaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaBancosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaBancosRequest")
    public JAXBElement<ConsultaBancosRequest> createConsultaBancosRequest(ConsultaBancosRequest value) {
        return new JAXBElement<ConsultaBancosRequest>(_ConsultaBancosRequest_QNAME, ConsultaBancosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaPaisesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaPaisesRequest")
    public JAXBElement<ConsultaPaisesRequest> createConsultaPaisesRequest(ConsultaPaisesRequest value) {
        return new JAXBElement<ConsultaPaisesRequest>(_ConsultaPaisesRequest_QNAME, ConsultaPaisesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerTipoDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ObtenerTipoDocRequest")
    public JAXBElement<ObtenerTipoDocRequest> createObtenerTipoDocRequest(ObtenerTipoDocRequest value) {
        return new JAXBElement<ObtenerTipoDocRequest>(_ObtenerTipoDocRequest_QNAME, ObtenerTipoDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomMonedaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaNomMonedaRequest")
    public JAXBElement<ConsultaNomMonedaRequest> createConsultaNomMonedaRequest(ConsultaNomMonedaRequest value) {
        return new JAXBElement<ConsultaNomMonedaRequest>(_ConsultaNomMonedaRequest_QNAME, ConsultaNomMonedaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegimenesGananciasRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "RegimenesGananciasRequest")
    public JAXBElement<RegimenesGananciasRequest> createRegimenesGananciasRequest(RegimenesGananciasRequest value) {
        return new JAXBElement<RegimenesGananciasRequest>(_RegimenesGananciasRequest_QNAME, RegimenesGananciasRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaNomPaisRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaNomPaisRequest")
    public JAXBElement<ConsultaNomPaisRequest> createConsultaNomPaisRequest(ConsultaNomPaisRequest value) {
        return new JAXBElement<ConsultaNomPaisRequest>(_ConsultaNomPaisRequest_QNAME, ConsultaNomPaisRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaisesConDobleConvenioRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "PaisesConDobleConvenioRequest")
    public JAXBElement<PaisesConDobleConvenioRequest> createPaisesConDobleConvenioRequest(PaisesConDobleConvenioRequest value) {
        return new JAXBElement<PaisesConDobleConvenioRequest>(_PaisesConDobleConvenioRequest_QNAME, PaisesConDobleConvenioRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaMonedasRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "ConsultaMonedasRequest")
    public JAXBElement<ConsultaMonedasRequest> createConsultaMonedasRequest(ConsultaMonedasRequest value) {
        return new JAXBElement<ConsultaMonedasRequest>(_ConsultaMonedasRequest_QNAME, ConsultaMonedasRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Codigo_Moneda", scope = ConsultaNomMonedaRequest.class)
    public JAXBElement<String> createConsultaNomMonedaRequestCodigoMoneda(String value) {
        return new JAXBElement<String>(_ConsultaNomMonedaRequestCodigoMoneda_QNAME, String.class, ConsultaNomMonedaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Tipo_Agenda", scope = ConsultaAgendaRequest.class)
    public JAXBElement<Long> createConsultaAgendaRequestTipoAgenda(Long value) {
        return new JAXBElement<Long>(_ConsultaAgendaRequestTipoAgenda_QNAME, Long.class, ConsultaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "IdAgenda", scope = ConsultaAgendaRequest.class)
    public JAXBElement<Long> createConsultaAgendaRequestIdAgenda(Long value) {
        return new JAXBElement<Long>(_ConsultaAgendaRequestIdAgenda_QNAME, Long.class, ConsultaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Nup_Cliente", scope = ConsultaAgendaRequest.class)
    public JAXBElement<String> createConsultaAgendaRequestNupCliente(String value) {
        return new JAXBElement<String>(_ConsultaAgendaRequestNupCliente_QNAME, String.class, ConsultaAgendaRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Cod_Pais_BCRA", scope = RegimenesGananciasRequest.class)
    public JAXBElement<String> createRegimenesGananciasRequestCodPaisBCRA(String value) {
        return new JAXBElement<String>(_RegimenesGananciasRequestCodPaisBCRA_QNAME, String.class, RegimenesGananciasRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Con_INPI", scope = RegimenesGananciasRequest.class)
    public JAXBElement<String> createRegimenesGananciasRequestConINPI(String value) {
        return new JAXBElement<String>(_RegimenesGananciasRequestConINPI_QNAME, String.class, RegimenesGananciasRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Con_CDI", scope = RegimenesGananciasRequest.class)
    public JAXBElement<String> createRegimenesGananciasRequestConCDI(String value) {
        return new JAXBElement<String>(_RegimenesGananciasRequestConCDI_QNAME, String.class, RegimenesGananciasRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Concepto_BCRA", scope = RegimenesGananciasRequest.class)
    public JAXBElement<String> createRegimenesGananciasRequestConceptoBCRA(String value) {
        return new JAXBElement<String>(_RegimenesGananciasRequestConceptoBCRA_QNAME, String.class, RegimenesGananciasRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Id_Alicuota", scope = RegimenesGananciasRequest.class)
    public JAXBElement<Long> createRegimenesGananciasRequestIdAlicuota(Long value) {
        return new JAXBElement<Long>(_RegimenesGananciasRequestIdAlicuota_QNAME, Long.class, RegimenesGananciasRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Swift_Banco", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestSwiftBanco(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestSwiftBanco_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Aba_Banco", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestAbaBanco(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestAbaBanco_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Solo_Seguimiento", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestSoloSeguimiento(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestSoloSeguimiento_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Pais_Banco", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestPaisBanco(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestPaisBanco_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Nombre_Banco", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestNombreBanco(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestNombreBanco_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Localidad_Banco", scope = ConsultaBancosRequest.class)
    public JAXBElement<String> createConsultaBancosRequestLocalidadBanco(String value) {
        return new JAXBElement<String>(_ConsultaBancosRequestLocalidadBanco_QNAME, String.class, ConsultaBancosRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "Request/Consultas", name = "Codigo_Pais", scope = ConsultaNomPaisRequest.class)
    public JAXBElement<String> createConsultaNomPaisRequestCodigoPais(String value) {
        return new JAXBElement<String>(_ConsultaNomPaisRequestCodigoPais_QNAME, String.class, ConsultaNomPaisRequest.class, value);
    }

}
