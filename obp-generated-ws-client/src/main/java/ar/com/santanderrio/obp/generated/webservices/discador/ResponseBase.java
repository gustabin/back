
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.GetRemoveTokenResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.IdentificacionDeLlamadaResponseBase;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.PagoPapResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ResultSolicitudesAutorizacionNPF;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.StDetallePagoCap;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.StDocumentoCap;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.StPagoCap;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.TypeResultCertificadoUvi;


/**
 * <p>Java class for ResponseBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CodRetorno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ResponseString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Server" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WriteLastPacket" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="WriteLastPacketDb" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBase", propOrder = {
    "codRetorno",
    "responseString",
    "result",
    "server",
    "token",
    "trxId",
    "writeLastPacket",
    "writeLastPacketDb"
})
@XmlSeeAlso({
    Trx773ExtraccionSinTarjetaResponse.class,
    Trx993CacheStatusResponse.class,
    Trx5223GetPedidosTransferenciaTercerosResponse.class,
    Trx5223GetPedidosTransferenciaTercerosRepetitionItem.class,
    Trx5024CnsTransfObanResponse.class,
    Trx992CacheStatusResponse.class,
    Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse.class,
    Trx948GetMiEquipoOnlineResponse.class,
    Trx40PedSessionIdResponse.class,
    Trx5006ConsultaMovimientosCruIterationResponse.class,
    Trx781IndicadoresClienteResponse.class,
    Trx848GetInfoAdicionalEjecutivoOnlineResponse.class,
    DummyRespond.class,
    PublicTrxError.class,
    Trx783CancelacionTarjetaDebitoVirtualResponse.class,
    ProxyLoginActorTokenResponse.class,
    Trx782CreacionTicketZendeskResponse.class,
    Trx991PingIATXResponse.class,
    Trx780ObtenerDatosCroupierResponse.class,
    Trx990PingDBResponse.class,
    Trx6009ObtenerNotificacionesPushResponse.class,
    Trx779LogLlamadaDiscadorResponse.class,
    Trx772EnvioNotificacionPushResponse.class,
    Trx909GrabarEstadististicasCapPapResponse.class,
    Trx908GetVDNyVQaTransferirResponse.class,
    Trx301InicioVidResponse.class,
    Trx271ConsultaSaldosMultiplesCuentasCruResponse.class,
    Trx771DenunciaTarjetaResponse.class,
    Trx770AudioAccesoAppResponse.class,
    Trx809GrabarEstadististicasSegmentacionV2Response.class,
    Trx769LogCallBackResponse.class,
    Trx768LogoutExternoResponse.class,
    Trx767AccesoCorresponsaliaResponse.class,
    Trx763ValidarTokenResponse.class,
    Trx766ChatMoraResponse.class,
    Trx762IdentificarTokenResponse.class,
    Trx765FirmanteEmpresasResponse.class,
    Trx765EmpresasResponse.class,
    Trx761LlamadasArbolNuevoResponse.class,
    Trx053TInicioFaxSuscripcionResponse.class,
    Trx760ClienteArbolResponse.class,
    Trx759RellamadoGalaResponse.class,
    Trx758IntencionMensajeResponse.class,
    Trx757ClickToCallResponse.class,
    PagoPapResponse.class,
    ResultSolicitudesAutorizacionNPF.class,
    StDocumentoCap.class,
    GetRemoveTokenResponse.class,
    StDetallePagoCap.class,
    TypeResultCertificadoUvi.class,
    StPagoCap.class,
    Trx756LogRioHelpResponse.class,
    Trx755ClaveByIdResponse.class,
    Trx754IndicadorClienteResponse.class,
    Trx752CampaniaAudioResponse.class,
    Trx4000RegistroClickToCallResponse.class,
    Trx753LogSlautoResponse.class,
    Trx4002PreprocesoMSGResponse.class,
    Trx4001ObtenerSolicitudClickToCallResponse.class,
    Trx751ReporteLlamadaTcResponse.class,
    Trx764CancelarLlamadaClienteResponse.class,
    Trx749AgendarRellamadaResponse.class,
    Trx6008CnstjcdetResponse.class,
    Trx748GetInfoAdicionalEjecutivoRemotoResponse.class,
    Trx6007CnsmiscelResponse.class,
    Trx746ValidarClaveCitiResponse.class,
    Trx6006CnstjcpdoResponse.class,
    Trx6005CnstjcdatoResponse.class,
    Trx745GetNupCitiResponse.class,
    Trx6004GetInfoClientePrismaResponse.class,
    Trx6003GetInfoClienteSelectResponse.class,
    Trx743AltaTarjetaResponse.class,
    Trx6002SucursalResponse.class,
    Trx6001CnsamecotiResponse.class,
    Trx6000CnspersfisResponse.class,
    Trx4014MoraTardiaResponse.class,
    Trx4012LogSTTMotivoLlamadoResponse.class,
    Trx4011AVLogEnvioMailResponse.class,
    Trx736SendSessionIdResponse.class,
    Trx4009GetIntentResponse.class,
    Trx735EstadisticasIVRResponse.class,
    Trx4008AddHistWspResponse.class,
    Trx4007GetTokenAddChatHistResponse.class,
    Trx734LlamadoPrioritarioResponse.class,
    Trx4006PromesaPagoMoraResponse.class,
    Trx732DatosRuteoDerivacionResponse.class,
    Trx731GetDatosInicioResponse.class,
    Trx4005ParametrosAppsAVResponse.class,
    Trx4004IdentificarClienteResponse.class,
    Trx730InicioLlamadaResponse.class,
    Trx4003PostprocesoMSGResponse.class,
    Trx728TBanelcoVIDResponse.class,
    Trx727RtaVerbalIdResponse.class,
    Trx725SetRespuestaEncuestaResponse.class,
    RestServiceClientResponse.class,
    Trx724GetEncuestaResponse.class,
    Trx723SetEncuestaResponse.class,
    Trx722IngresoIvrResponse.class,
    Trx720CnsAlertasCrmResponse.class,
    Trx719GrabarEstadisticasLlamadaFinResponse.class,
    IdentificacionDeLlamadaResponseBase.class,
    Trx718GrabarEstadisticasLlamadaResponse.class,
    Trx717DocumentoCapResponse.class,
    Trx716DetallePagoCapResponse.class,
    Trx715TPagoCapResponse.class,
    Trx714ProveedorCapResponse.class,
    Trx713IdentificarCapPapResponse.class,
    Trx712AccesoAppResponse.class,
    Trx711GetCantidadDeLlamadasMoraResponse.class,
    Trx709GrabarEstadististicasSegmentacionResponse.class,
    Trx705TGetVerbalIdNegativoResponse.class,
    Trx200AltDatosExtrasResponse.class,
    TrxMonoProductoBase.class,
    Trx7012ObtenerTransferenciaTercerosResponse.class,
    Trx197EvaluarCampaniasResponse.class,
    Trx7011ObtenerTransferenciaBancoResponse.class,
    Trx7009SetNodoArbolIVRResponse.class,
    Trx7010ObtenerHistorialSolicitudesResponse.class,
    Trx7008GrabarDetallesTransferenciaTercerosResponse.class,
    Trx7007GrabarDetallesTransferenciaResponse.class,
    Trx7006GrabarDatosSesionResponse.class,
    Trx7005ObtenerDatosSesionResponse.class,
    Trx7004ObtenerNroSesionResponse.class,
    TrxHeaderResponseBase.class,
    Trx832DatosRuteoDerivacionResponse.class,
    Trx7001AddTokenResponse.class,
    Trx778IdentificacionDispositivosActivosResponse.class,
    Trx776LogDatabaseResponse.class,
    Trx777LogDerivacionHabilidadesResponse.class,
    Trx774EnvioMailResponse.class,
    Trx775LogCallbackChatResponse.class
})
public class ResponseBase
    extends MappingModelBase
{

    @XmlElement(name = "CodRetorno")
    protected Integer codRetorno;
    @XmlElementRef(name = "ResponseString", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> responseString;
    @XmlElementRef(name = "Result", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> result;
    @XmlElementRef(name = "Server", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> server;
    @XmlElementRef(name = "Token", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> token;
    @XmlElementRef(name = "TrxId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> trxId;
    @XmlElement(name = "WriteLastPacket")
    protected Boolean writeLastPacket;
    @XmlElement(name = "WriteLastPacketDb")
    protected Boolean writeLastPacketDb;

    /**
     * Gets the value of the codRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodRetorno() {
        return codRetorno;
    }

    /**
     * Sets the value of the codRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodRetorno(Integer value) {
        this.codRetorno = value;
    }

    /**
     * Gets the value of the responseString property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResponseString() {
        return responseString;
    }

    /**
     * Sets the value of the responseString property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResponseString(JAXBElement<String> value) {
        this.responseString = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResult(JAXBElement<String> value) {
        this.result = value;
    }

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServer(JAXBElement<String> value) {
        this.server = value;
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

    /**
     * Gets the value of the writeLastPacket property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWriteLastPacket() {
        return writeLastPacket;
    }

    /**
     * Sets the value of the writeLastPacket property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWriteLastPacket(Boolean value) {
        this.writeLastPacket = value;
    }

    /**
     * Gets the value of the writeLastPacketDb property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWriteLastPacketDb() {
        return writeLastPacketDb;
    }

    /**
     * Sets the value of the writeLastPacketDb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWriteLastPacketDb(Boolean value) {
        this.writeLastPacketDb = value;
    }

}
