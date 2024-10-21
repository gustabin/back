
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.santanderrio.obp.generated.webservices.banelco package. 
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

    private final static QName _ConsultaCuentaPreferidaResponseDTOCuentasPreferidasDTO_QNAME = new QName("http://dto.cds.banelco.com", "cuentasPreferidasDTO");
    private final static QName _WSTerminalDTOCanal_QNAME = new QName("http://model.webservices.banelco.com", "canal");
    private final static QName _WSTerminalDTOTerminal_QNAME = new QName("http://model.webservices.banelco.com", "terminal");
    private final static QName _WSTerminalDTODireccionIP_QNAME = new QName("http://model.webservices.banelco.com", "direccionIP");
    private final static QName _WSTerminalDTODatosTerminal_QNAME = new QName("http://model.webservices.banelco.com", "datosTerminal");
    private final static QName _EstadoCajeroRequestDTOIdCajero_QNAME = new QName("http://model.webservices.banelco.com", "idCajero");
    private final static QName _ICajeroIdCajero_QNAME = new QName("http://business.model.cds.banelco.com", "idCajero");
    private final static QName _WSTerminalDataIpOrigen_QNAME = new QName("http://model.webservices.banelco.com", "ipOrigen");
    private final static QName _WSTerminalDataIdSession_QNAME = new QName("http://model.webservices.banelco.com", "idSession");
    private final static QName _WSTerminalDataFutureUse1_QNAME = new QName("http://model.webservices.banelco.com", "futureUse1");
    private final static QName _WSTerminalDataFutureUse2_QNAME = new QName("http://model.webservices.banelco.com", "futureUse2");
    private final static QName _CuentaPreferidaDTOTipo_QNAME = new QName("http://dto.cds.banelco.com", "tipo");
    private final static QName _CuentaPreferidaDTONumero_QNAME = new QName("http://dto.cds.banelco.com", "numero");
    private final static QName _WSTarjetaDTOMiembro_QNAME = new QName("http://model.webservices.banelco.com", "miembro");
    private final static QName _WSTarjetaDTONumero_QNAME = new QName("http://model.webservices.banelco.com", "numero");
    private final static QName _WSUserDataNumeroDocumento_QNAME = new QName("http://model.webservices.banelco.com", "numeroDocumento");
    private final static QName _WSUserDataTipoDocumento_QNAME = new QName("http://model.webservices.banelco.com", "tipoDocumento");
    private final static QName _InfoCajeroDTOBandaHoraria_QNAME = new QName("http://banelcomap.services.external.banelco.com", "bandaHoraria");
    private final static QName _InfoCajeroDTONombreBanco_QNAME = new QName("http://banelcomap.services.external.banelco.com", "nombreBanco");
    private final static QName _InfoCajeroDTOIdBanco_QNAME = new QName("http://banelcomap.services.external.banelco.com", "idBanco");
    private final static QName _EstadoCajeroResponseDTODisponibleHopper1_QNAME = new QName("http://dto.banelco.com", "disponibleHopper1");
    private final static QName _EstadoCajeroResponseDTOExtraccion_QNAME = new QName("http://dto.banelco.com", "extraccion");
    private final static QName _EstadoCajeroResponseDTODisponibleHopper2_QNAME = new QName("http://dto.banelco.com", "disponibleHopper2");
    private final static QName _EstadoCajeroResponseDTODisponibleHopper3_QNAME = new QName("http://dto.banelco.com", "disponibleHopper3");
    private final static QName _EstadoCajeroResponseDTOBandaHoraria_QNAME = new QName("http://dto.banelco.com", "bandaHoraria");
    private final static QName _EstadoCajeroResponseDTODisponibleHopper4_QNAME = new QName("http://dto.banelco.com", "disponibleHopper4");
    private final static QName _EstadoCajeroResponseDTORespuesta_QNAME = new QName("http://dto.banelco.com", "respuesta");
    private final static QName _EstadoCajeroResponseDTOIdCajero_QNAME = new QName("http://dto.banelco.com", "idCajero");
    private final static QName _EstadoCajeroResponseDTOServicioActivo_QNAME = new QName("http://dto.banelco.com", "servicioActivo");
    private final static QName _EstadoCajeroResponseDTOIdBanco_QNAME = new QName("http://dto.banelco.com", "idBanco");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.santanderrio.obp.generated.webservices.banelco
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InfoCajeroDTO }
     * 
     */
    public InfoCajeroDTO createInfoCajeroDTO() {
        return new InfoCajeroDTO();
    }

    /**
     * Create an instance of {@link BlanquearPin }
     * 
     */
    public BlanquearPin createBlanquearPin() {
        return new BlanquearPin();
    }

    /**
     * Create an instance of {@link WSTerminalData }
     * 
     */
    public WSTerminalData createWSTerminalData() {
        return new WSTerminalData();
    }

    /**
     * Create an instance of {@link WSUserData }
     * 
     */
    public WSUserData createWSUserData() {
        return new WSUserData();
    }

    /**
     * Create an instance of {@link WSTarjetaDTO }
     * 
     */
    public WSTarjetaDTO createWSTarjetaDTO() {
        return new WSTarjetaDTO();
    }

    /**
     * Create an instance of {@link AsignarLimiteVIPResponse }
     * 
     */
    public AsignarLimiteVIPResponse createAsignarLimiteVIPResponse() {
        return new AsignarLimiteVIPResponse();
    }

    /**
     * Create an instance of {@link GetEstadoCajeroResponse }
     * 
     */
    public GetEstadoCajeroResponse createGetEstadoCajeroResponse() {
        return new GetEstadoCajeroResponse();
    }

    /**
     * Create an instance of {@link EstadoCajeroResponseDTO }
     * 
     */
    public EstadoCajeroResponseDTO createEstadoCajeroResponseDTO() {
        return new EstadoCajeroResponseDTO();
    }

    /**
     * Create an instance of {@link BlanquearAlfaResponse }
     * 
     */
    public BlanquearAlfaResponse createBlanquearAlfaResponse() {
        return new BlanquearAlfaResponse();
    }

    /**
     * Create an instance of {@link BlanquearPinAlfaResponse }
     * 
     */
    public BlanquearPinAlfaResponse createBlanquearPinAlfaResponse() {
        return new BlanquearPinAlfaResponse();
    }

    /**
     * Create an instance of {@link BlanquearPinAlfa }
     * 
     */
    public BlanquearPinAlfa createBlanquearPinAlfa() {
        return new BlanquearPinAlfa();
    }

    /**
     * Create an instance of {@link ConsultaEstadoCajeroWithIdResponse }
     * 
     */
    public ConsultaEstadoCajeroWithIdResponse createConsultaEstadoCajeroWithIdResponse() {
        return new ConsultaEstadoCajeroWithIdResponse();
    }

    /**
     * Create an instance of {@link ConsultarCuentaPreferidaResponse }
     * 
     */
    public ConsultarCuentaPreferidaResponse createConsultarCuentaPreferidaResponse() {
        return new ConsultarCuentaPreferidaResponse();
    }

    /**
     * Create an instance of {@link ConsultaCuentaPreferidaResponseDTO }
     * 
     */
    public ConsultaCuentaPreferidaResponseDTO createConsultaCuentaPreferidaResponseDTO() {
        return new ConsultaCuentaPreferidaResponseDTO();
    }

    /**
     * Create an instance of {@link ConsultarEstadoCajeroConBanco }
     * 
     */
    public ConsultarEstadoCajeroConBanco createConsultarEstadoCajeroConBanco() {
        return new ConsultarEstadoCajeroConBanco();
    }

    /**
     * Create an instance of {@link WSServiceData }
     * 
     */
    public WSServiceData createWSServiceData() {
        return new WSServiceData();
    }

    /**
     * Create an instance of {@link ConsultarEstadoCajeroConBancoResponse }
     * 
     */
    public ConsultarEstadoCajeroConBancoResponse createConsultarEstadoCajeroConBancoResponse() {
        return new ConsultarEstadoCajeroConBancoResponse();
    }

    /**
     * Create an instance of {@link ICajero }
     * 
     */
    public ICajero createICajero() {
        return new ICajero();
    }

    /**
     * Create an instance of {@link AsignarLimiteVIP }
     * 
     */
    public AsignarLimiteVIP createAsignarLimiteVIP() {
        return new AsignarLimiteVIP();
    }

    /**
     * Create an instance of {@link WSTerminalDTO }
     * 
     */
    public WSTerminalDTO createWSTerminalDTO() {
        return new WSTerminalDTO();
    }

    /**
     * Create an instance of {@link BlanquearPinResponse }
     * 
     */
    public BlanquearPinResponse createBlanquearPinResponse() {
        return new BlanquearPinResponse();
    }

    /**
     * Create an instance of {@link BlanquearAlfa }
     * 
     */
    public BlanquearAlfa createBlanquearAlfa() {
        return new BlanquearAlfa();
    }

    /**
     * Create an instance of {@link ModificarCuentaPreferida }
     * 
     */
    public ModificarCuentaPreferida createModificarCuentaPreferida() {
        return new ModificarCuentaPreferida();
    }

    /**
     * Create an instance of {@link CuentaPreferidaDTO }
     * 
     */
    public CuentaPreferidaDTO createCuentaPreferidaDTO() {
        return new CuentaPreferidaDTO();
    }

    /**
     * Create an instance of {@link ConsultarCuentaPreferida }
     * 
     */
    public ConsultarCuentaPreferida createConsultarCuentaPreferida() {
        return new ConsultarCuentaPreferida();
    }

    /**
     * Create an instance of {@link GetEstadoCajero }
     * 
     */
    public GetEstadoCajero createGetEstadoCajero() {
        return new GetEstadoCajero();
    }

    /**
     * Create an instance of {@link EstadoCajeroRequestDTO }
     * 
     */
    public EstadoCajeroRequestDTO createEstadoCajeroRequestDTO() {
        return new EstadoCajeroRequestDTO();
    }

    /**
     * Create an instance of {@link ModificarCotizacionResponse }
     * 
     */
    public ModificarCotizacionResponse createModificarCotizacionResponse() {
        return new ModificarCotizacionResponse();
    }

    /**
     * Create an instance of {@link CambiarClaseResponse }
     * 
     */
    public CambiarClaseResponse createCambiarClaseResponse() {
        return new CambiarClaseResponse();
    }

    /**
     * Create an instance of {@link ModificarCotizacion }
     * 
     */
    public ModificarCotizacion createModificarCotizacion() {
        return new ModificarCotizacion();
    }

    /**
     * Create an instance of {@link CambiarClase }
     * 
     */
    public CambiarClase createCambiarClase() {
        return new CambiarClase();
    }

    /**
     * Create an instance of {@link ConsultaEstadoCajeroWithId }
     * 
     */
    public ConsultaEstadoCajeroWithId createConsultaEstadoCajeroWithId() {
        return new ConsultaEstadoCajeroWithId();
    }

    /**
     * Create an instance of {@link ModificarCuentaPreferidaResponse }
     * 
     */
    public ModificarCuentaPreferidaResponse createModificarCuentaPreferidaResponse() {
        return new ModificarCuentaPreferidaResponse();
    }

    /**
     * Create an instance of {@link ConsultarEstadoCajero }
     * 
     */
    public ConsultarEstadoCajero createConsultarEstadoCajero() {
        return new ConsultarEstadoCajero();
    }

    /**
     * Create an instance of {@link ConsultarEstadoCajeroResponse }
     * 
     */
    public ConsultarEstadoCajeroResponse createConsultarEstadoCajeroResponse() {
        return new ConsultarEstadoCajeroResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCuentaPreferidaDTO }
     * 
     */
    public ArrayOfCuentaPreferidaDTO createArrayOfCuentaPreferidaDTO() {
        return new ArrayOfCuentaPreferidaDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCuentaPreferidaDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.cds.banelco.com", name = "cuentasPreferidasDTO", scope = ConsultaCuentaPreferidaResponseDTO.class)
    public JAXBElement<ArrayOfCuentaPreferidaDTO> createConsultaCuentaPreferidaResponseDTOCuentasPreferidasDTO(ArrayOfCuentaPreferidaDTO value) {
        return new JAXBElement<ArrayOfCuentaPreferidaDTO>(_ConsultaCuentaPreferidaResponseDTOCuentasPreferidasDTO_QNAME, ArrayOfCuentaPreferidaDTO.class, ConsultaCuentaPreferidaResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "canal", scope = WSTerminalDTO.class)
    public JAXBElement<String> createWSTerminalDTOCanal(String value) {
        return new JAXBElement<String>(_WSTerminalDTOCanal_QNAME, String.class, WSTerminalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "terminal", scope = WSTerminalDTO.class)
    public JAXBElement<String> createWSTerminalDTOTerminal(String value) {
        return new JAXBElement<String>(_WSTerminalDTOTerminal_QNAME, String.class, WSTerminalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "direccionIP", scope = WSTerminalDTO.class)
    public JAXBElement<String> createWSTerminalDTODireccionIP(String value) {
        return new JAXBElement<String>(_WSTerminalDTODireccionIP_QNAME, String.class, WSTerminalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "datosTerminal", scope = WSTerminalDTO.class)
    public JAXBElement<String> createWSTerminalDTODatosTerminal(String value) {
        return new JAXBElement<String>(_WSTerminalDTODatosTerminal_QNAME, String.class, WSTerminalDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "idCajero", scope = EstadoCajeroRequestDTO.class)
    public JAXBElement<String> createEstadoCajeroRequestDTOIdCajero(String value) {
        return new JAXBElement<String>(_EstadoCajeroRequestDTOIdCajero_QNAME, String.class, EstadoCajeroRequestDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://business.model.cds.banelco.com", name = "idCajero", scope = ICajero.class)
    public JAXBElement<String> createICajeroIdCajero(String value) {
        return new JAXBElement<String>(_ICajeroIdCajero_QNAME, String.class, ICajero.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "canal", scope = WSServiceData.class)
    public JAXBElement<String> createWSServiceDataCanal(String value) {
        return new JAXBElement<String>(_WSTerminalDTOCanal_QNAME, String.class, WSServiceData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "terminal", scope = WSServiceData.class)
    public JAXBElement<String> createWSServiceDataTerminal(String value) {
        return new JAXBElement<String>(_WSTerminalDTOTerminal_QNAME, String.class, WSServiceData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "ipOrigen", scope = WSTerminalData.class)
    public JAXBElement<String> createWSTerminalDataIpOrigen(String value) {
        return new JAXBElement<String>(_WSTerminalDataIpOrigen_QNAME, String.class, WSTerminalData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "idSession", scope = WSTerminalData.class)
    public JAXBElement<String> createWSTerminalDataIdSession(String value) {
        return new JAXBElement<String>(_WSTerminalDataIdSession_QNAME, String.class, WSTerminalData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "datosTerminal", scope = WSTerminalData.class)
    public JAXBElement<String> createWSTerminalDataDatosTerminal(String value) {
        return new JAXBElement<String>(_WSTerminalDTODatosTerminal_QNAME, String.class, WSTerminalData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "futureUse1", scope = WSTerminalData.class)
    public JAXBElement<String> createWSTerminalDataFutureUse1(String value) {
        return new JAXBElement<String>(_WSTerminalDataFutureUse1_QNAME, String.class, WSTerminalData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "futureUse2", scope = WSTerminalData.class)
    public JAXBElement<String> createWSTerminalDataFutureUse2(String value) {
        return new JAXBElement<String>(_WSTerminalDataFutureUse2_QNAME, String.class, WSTerminalData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.cds.banelco.com", name = "tipo", scope = CuentaPreferidaDTO.class)
    public JAXBElement<String> createCuentaPreferidaDTOTipo(String value) {
        return new JAXBElement<String>(_CuentaPreferidaDTOTipo_QNAME, String.class, CuentaPreferidaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.cds.banelco.com", name = "numero", scope = CuentaPreferidaDTO.class)
    public JAXBElement<String> createCuentaPreferidaDTONumero(String value) {
        return new JAXBElement<String>(_CuentaPreferidaDTONumero_QNAME, String.class, CuentaPreferidaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "miembro", scope = WSTarjetaDTO.class)
    public JAXBElement<String> createWSTarjetaDTOMiembro(String value) {
        return new JAXBElement<String>(_WSTarjetaDTOMiembro_QNAME, String.class, WSTarjetaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "numero", scope = WSTarjetaDTO.class)
    public JAXBElement<String> createWSTarjetaDTONumero(String value) {
        return new JAXBElement<String>(_WSTarjetaDTONumero_QNAME, String.class, WSTarjetaDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "numeroDocumento", scope = WSUserData.class)
    public JAXBElement<String> createWSUserDataNumeroDocumento(String value) {
        return new JAXBElement<String>(_WSUserDataNumeroDocumento_QNAME, String.class, WSUserData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.webservices.banelco.com", name = "tipoDocumento", scope = WSUserData.class)
    public JAXBElement<String> createWSUserDataTipoDocumento(String value) {
        return new JAXBElement<String>(_WSUserDataTipoDocumento_QNAME, String.class, WSUserData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banelcomap.services.external.banelco.com", name = "bandaHoraria", scope = InfoCajeroDTO.class)
    public JAXBElement<String> createInfoCajeroDTOBandaHoraria(String value) {
        return new JAXBElement<String>(_InfoCajeroDTOBandaHoraria_QNAME, String.class, InfoCajeroDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banelcomap.services.external.banelco.com", name = "nombreBanco", scope = InfoCajeroDTO.class)
    public JAXBElement<String> createInfoCajeroDTONombreBanco(String value) {
        return new JAXBElement<String>(_InfoCajeroDTONombreBanco_QNAME, String.class, InfoCajeroDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banelcomap.services.external.banelco.com", name = "idBanco", scope = InfoCajeroDTO.class)
    public JAXBElement<String> createInfoCajeroDTOIdBanco(String value) {
        return new JAXBElement<String>(_InfoCajeroDTOIdBanco_QNAME, String.class, InfoCajeroDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "disponibleHopper1", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Double> createEstadoCajeroResponseDTODisponibleHopper1(Double value) {
        return new JAXBElement<Double>(_EstadoCajeroResponseDTODisponibleHopper1_QNAME, Double.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "extraccion", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Boolean> createEstadoCajeroResponseDTOExtraccion(Boolean value) {
        return new JAXBElement<Boolean>(_EstadoCajeroResponseDTOExtraccion_QNAME, Boolean.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "disponibleHopper2", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Double> createEstadoCajeroResponseDTODisponibleHopper2(Double value) {
        return new JAXBElement<Double>(_EstadoCajeroResponseDTODisponibleHopper2_QNAME, Double.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "disponibleHopper3", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Double> createEstadoCajeroResponseDTODisponibleHopper3(Double value) {
        return new JAXBElement<Double>(_EstadoCajeroResponseDTODisponibleHopper3_QNAME, Double.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "bandaHoraria", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<String> createEstadoCajeroResponseDTOBandaHoraria(String value) {
        return new JAXBElement<String>(_EstadoCajeroResponseDTOBandaHoraria_QNAME, String.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "disponibleHopper4", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Double> createEstadoCajeroResponseDTODisponibleHopper4(Double value) {
        return new JAXBElement<Double>(_EstadoCajeroResponseDTODisponibleHopper4_QNAME, Double.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "respuesta", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<String> createEstadoCajeroResponseDTORespuesta(String value) {
        return new JAXBElement<String>(_EstadoCajeroResponseDTORespuesta_QNAME, String.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "idCajero", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<String> createEstadoCajeroResponseDTOIdCajero(String value) {
        return new JAXBElement<String>(_EstadoCajeroResponseDTOIdCajero_QNAME, String.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "servicioActivo", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<Boolean> createEstadoCajeroResponseDTOServicioActivo(Boolean value) {
        return new JAXBElement<Boolean>(_EstadoCajeroResponseDTOServicioActivo_QNAME, Boolean.class, EstadoCajeroResponseDTO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dto.banelco.com", name = "idBanco", scope = EstadoCajeroResponseDTO.class)
    public JAXBElement<String> createEstadoCajeroResponseDTOIdBanco(String value) {
        return new JAXBElement<String>(_EstadoCajeroResponseDTOIdBanco_QNAME, String.class, EstadoCajeroResponseDTO.class, value);
    }

}
