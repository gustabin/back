
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.ApiConnectTokenRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.GetRemoveTokenRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.IdentificacionDeLlamadaRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.IdentificacionDeLlamadaRequestBase;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TransferenciasRequest;


/**
 * <p>Java class for RequestBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrelationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdTransaction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="JsonRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MockCode" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MockCode" minOccurs="0"/>
 *         &lt;element name="SubCanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestBase", propOrder = {
    "canal",
    "correlationId",
    "idTransaction",
    "jsonRequest",
    "mockCode",
    "subCanal",
    "token",
    "trxId"
})
@XmlSeeAlso({
    Trx773ExtraccionSinTarjetaRequest.class,
    Trx993MemoryCacheStatusRequest.class,
    Trx5042MdlwConsultaPagosPapRequest.class,
    Trx992CacheStatusRequest.class,
    Trx948GetMiEquipoOnlineRequest.class,
    Trx40PedSessionIdRequest.class,
    Trx781IndicadoresClienteRequest.class,
    Trx848GetInfoAdicionalEjecutivoOnlineRequest.class,
    DummyRequest.class,
    Trx783CancelacionTarjetaDebitoVirtualRequest.class,
    ProxyLoginActorTokenRequest.class,
    Trx782CreacionTicketZendeskRequest.class,
    TransferenciasRequest.class,
    ApiConnectTokenRequest.class,
    IdentificacionDeLlamadaRequestBase.class,
    GetRemoveTokenRequest.class,
    IdentificacionDeLlamadaRequest.class,
    Trx991PingIATXRequest.class,
    Trx780ObtenerDatosCroupierRequest.class,
    Trx990PingDBRequest.class,
    Trx6009ObtenerNotificacionesPushRequest.class,
    Trx910MdlwConsultaProdMoraTemprana110Request.class,
    Trx779LogLlamadaDiscadorRequest.class,
    Trx772EnvioNotificacionPushRequest.class,
    Trx909GrabarEstadististicasCapPapRequest.class,
    Trx908GetVDNyVQaTransferirRequest.class,
    Trx301InicioVidRequest.class,
    Trx771DenunciaTarjetaRequest.class,
    Trx770AudioAccesoAppRequest.class,
    Trx809GrabarEstadististicasSegmentacionV2Request.class,
    Trx201IdentificacionDeLlamadaRequest.class,
    Trx769LogCallBackRequest.class,
    Trx768LogoutExternoRequest.class,
    Trx767AccesoCorresponsaliaRequest.class,
    Trx763ValidarTokenRequest.class,
    Trx766ChatMoraRequest.class,
    Trx762IdentificarTokenRequest.class,
    Trx765FirmanteEmpresasRequest.class,
    Trx761LlamadasArbolNuevoRequest.class,
    Trx053TInicioFaxSuscripcionRequest.class,
    Trx760ClienteArbolRequest.class,
    Trx759RellamadoGalaRequest.class,
    Trx758IntencionMensajeRequest.class,
    Trx757ClickToCallRequest.class,
    Trx756LogRioHelpRequest.class,
    Trx755ClaveByIdRequest.class,
    Trx754IndicadorClienteRequest.class,
    Trx752CampaniaAudioRequest.class,
    Trx4000RegistroClickToCallRequest.class,
    Trx753LogSlautoRequest.class,
    Trx4002PreprocesoMSGRequest.class,
    Trx4001ObtenerSolicitudClickToCallRequest.class,
    Trx751ReporteLlamadaTcRequest.class,
    Trx764CancelarLlamadaClienteRequest.class,
    Trx749AgendarRellamadaRequest.class,
    Trx6008CnstjcdetRequest.class,
    Trx748GetInfoAdicionalEjecutivoRemotoRequest.class,
    Trx6007CnsmiscelRequest.class,
    Trx746ValidarClaveCitiRequest.class,
    Trx6006CnstjcpdoRequest.class,
    Trx6005CnstjcdatoRequest.class,
    Trx745GetNupCitiRequest.class,
    Trx6004GetInfoClientePrismaRequest.class,
    Trx744AltaAdhesionDebitoRequest.class,
    Trx6003GetInfoClienteSelectRequest.class,
    Trx743AltaTarjetaRequest.class,
    Trx6002SucursalRequest.class,
    Trx742MdlwConsultaPlazoFijoNoAltairUviRequest.class,
    Trx6001CnsamecotiRequest.class,
    Trx6000CnspersfisRequest.class,
    Trx4014MoraTardiaRequest.class,
    AutorizBancoCompraVentaExtranjeraRequestBase.class,
    SimulacionCompraVentaMonedaExtranjeraBase.class,
    Trx4012LogSTTMotivoLlamadoRequest.class,
    Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest.class,
    Trx4011AVLogEnvioMailRequest.class,
    Trx736SendSessionIdRequest.class,
    Trx4009GetIntentRequest.class,
    Trx735EstadisticasIVRRequest.class,
    Trx4008AddHistWspRequest.class,
    Trx4007GetTokenAddChatHistRequest.class,
    Trx734LlamadoPrioritarioRequest.class,
    Trx4006PromesaPagoMoraRequest.class,
    Trx732DatosRuteoDerivacionRequest.class,
    Trx731GetDatosInicioRequest.class,
    Trx4005ParametrosAppsAVRequest.class,
    Trx4004IdentificarClienteRequest.class,
    Trx730InicioLlamadaRequest.class,
    Trx4003PostprocesoMSGRequest.class,
    Trx728TBanelcoVIDRequest.class,
    Trx727RtaVerbalIdRequest.class,
    Trx1010MdlwConsultaProdMoraTemprana130Request.class,
    TrxIdentificacionClienteProductRequest.class,
    Trx725SetRespuestaEncuestaRequest.class,
    RestServiceClientRequest.class,
    Trx724GetEncuestaRequest.class,
    Trx723SetEncuestaRequest.class,
    Trx722IngresoIvrRequest.class,
    Trx720CnsAlertasCrmRequest.class,
    Trx719GrabarEstadisticasLlamadaFinRequest.class,
    Trx718GrabarEstadisticasLlamadaRequest.class,
    Trx717DocumentoCapRequest.class,
    Trx716DetallePagoCapRequest.class,
    Trx715TPagoCapRequest.class,
    Trx714ProveedorCapRequest.class,
    Trx713IdentificarCapPapRequest.class,
    Trx712AccesoAppRequest.class,
    Trx711GetCantidadDeLlamadasMoraRequest.class,
    Trx710MdlwConsultaProdMoraTempranaRequest.class,
    Trx709GrabarEstadististicasSegmentacionRequest.class,
    Trx707MdlwAltaResetClaveBRequest.class,
    Trx706MdlwValidaCVV2Request.class,
    Trx705TGetVerbalIdNegativoRequest.class,
    Trx169AutTransfObanCciRequest.class,
    Trx7012ObtenerTransferenciaTercerosRequest.class,
    Trx344MdlwCertificacionIdentidadRequest.class,
    Trx197EvaluarCampaniasRequest.class,
    Trx7011ObtenerTransferenciaBancoRequest.class,
    Trx7009SetNodoArbolIVRRequest.class,
    Trx7010ObtenerHistorialSolicitudesRequest.class,
    Trx7008GrabarDetallesTransferenciaTercerosRequest.class,
    Trx7007GrabarDetallesTransferenciasOtrosBancosRequest.class,
    Trx7006GrabarDatosSesionRequest.class,
    Trx7005ObtenerDatosSesionRequest.class,
    Trx168TransfOtroBanCciRequest.class,
    Trx7004ObtenerNroSesionRequest.class,
    RequestContactBase.class,
    Trx832DatosRuteoDerivacionRequest.class,
    Trx7001AddTokenRequest.class,
    Trx778IdentificacionDispositivosActivosRequest.class,
    Trx776LogDatabaseRequest.class,
    Trx777LogDerivacionHabilidadesRequest.class,
    Trx774EnvioMailRequest.class,
    Trx775LogCallbackChatRequest.class
})
public class RequestBase
    extends MappingModelBase
{

    @XmlElementRef(name = "Canal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> canal;
    @XmlElementRef(name = "CorrelationId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> correlationId;
    @XmlElement(name = "IdTransaction")
    protected Integer idTransaction;
    @XmlElementRef(name = "JsonRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> jsonRequest;
    @XmlElement(name = "MockCode")
    protected MockCode mockCode;
    @XmlElementRef(name = "SubCanal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> subCanal;
    @XmlElementRef(name = "Token", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> token;
    @XmlElementRef(name = "TrxId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> trxId;

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanal(JAXBElement<String> value) {
        this.canal = value;
    }

    /**
     * Gets the value of the correlationId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCorrelationId(JAXBElement<String> value) {
        this.correlationId = value;
    }

    /**
     * Gets the value of the idTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTransaction() {
        return idTransaction;
    }

    /**
     * Sets the value of the idTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTransaction(Integer value) {
        this.idTransaction = value;
    }

    /**
     * Gets the value of the jsonRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJsonRequest() {
        return jsonRequest;
    }

    /**
     * Sets the value of the jsonRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJsonRequest(JAXBElement<String> value) {
        this.jsonRequest = value;
    }

    /**
     * Gets the value of the mockCode property.
     * 
     * @return
     *     possible object is
     *     {@link MockCode }
     *     
     */
    public MockCode getMockCode() {
        return mockCode;
    }

    /**
     * Sets the value of the mockCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MockCode }
     *     
     */
    public void setMockCode(MockCode value) {
        this.mockCode = value;
    }

    /**
     * Gets the value of the subCanal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubCanal() {
        return subCanal;
    }

    /**
     * Sets the value of the subCanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubCanal(JAXBElement<String> value) {
        this.subCanal = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setToken(JAXBElement<String> value) {
        this.token = value;
    }

    /**
     * Gets the value of the trxId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTrxId() {
        return trxId;
    }

    /**
     * Sets the value of the trxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTrxId(JAXBElement<String> value) {
        this.trxId = value;
    }

}
