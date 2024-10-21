
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ResponseBase", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "codRetorno",
    "responseString",
    "token",
    "writeLastPacket",
    "writeLastPacketDb"
})
@XmlSeeAlso({
    Trx755ClaveByIdResponse.class,
    Trx754IndicadorClienteResponse.class,
    Trx752CampaniaAudioResponse.class,
    Trx753LogSlautoResponse.class,
    Trx751ReporteLlamadaTcResponse.class,
    Trx749AgendarRellamadaResponse.class,
    Trx748GetInfoAdicionalEjecutivoRemotoResponse.class,
    Trx746ValidarClaveCitiResponse.class,
    Trx745GetNupCitiResponse.class,
    DummyRespond.class,
    PublicTrxError.class,
    Trx991PingIATXResponse.class,
    Trx990PingDBResponse.class,
    Trx909GrabarEstadististicasCapPapResponse.class,
    Trx908GetVDNyVQaTransferirResponse.class,
    Trx743AltaTarjetaResponse.class,
    Trx736SendSessionIdResponse.class,
    Trx735EstadisticasIVRResponse.class,
    Trx734LlamadoPrioritarioResponse.class,
    Trx732DatosRuteoDerivacionResponse.class,
    Trx731GetDatosInicioResponse.class,
    Trx730InicioLlamadaResponse.class,
    Trx728TBanelcoVIDResponse.class,
    Trx727RtaVerbalIdResponse.class,
    Trx725SetRespuestaEncuestaResponse.class,
    Trx724GetEncuestaResponse.class,
    Trx723SetEncuestaResponse.class,
    Trx197EvaluarCampaniasResponse.class,
    Trx722IngresoIvrResponse.class,
    Trx720CnsAlertasCrmResponse.class,
    Trx719GrabarEstadisticasLlamadaFinResponse.class,
    Trx718GrabarEstadisticasLlamadaResponse.class,
    Trx717DocumentoCapResponse.class,
    Trx716DetallePagoCapResponse.class,
    Trx715TPagoCapResponse.class,
    Trx714ProveedorCapResponse.class,
    Trx713IdentificarCapPapResponse.class,
    Trx712AccesoAppResponse.class,
    Trx832DatosRuteoDerivacionResponse.class,
    Trx767AccesoCorresponsaliaResponse.class,
    Trx766ChatMoraResponse.class,
    Trx765FirmanteEmpresasResponse.class,
    Trx765EmpresasResponse.class,
    Trx053TInicioFaxSuscripcionResponse.class,
    Trx711GetCantidadDeLlamadasMoraResponse.class,
    Trx709GrabarEstadististicasSegmentacionResponse.class,
    Trx705TGetVerbalIdNegativoResponse.class,
    TrxMonoProductoBase.class,
    Trx7012ObtenerTransferenciaTercerosResponse.class,
    Trx7011ObtenerTransferenciaBancoResponse.class,
    Trx4000RegistroClickToCallResponse.class,
    Trx4002PreprocesoMSGResponse.class,
    Trx4001ObtenerSolicitudClickToCallResponse.class,
    Trx764CancelarLlamadaClienteResponse.class,
    Trx4004IdentificarClienteResponse.class,
    Trx4003PostprocesoMSGResponse.class,
    PagoPapResponse.class,
    ResultSolicitudesAutorizacionNPF.class,
    StDocumentoCap.class,
    GetRemoveTokenResponse.class,
    StDetallePagoCap.class,
    TypeResultCertificadoUvi.class,
    StPagoCap.class,
    Trx7009SetNodoArbolIVRResponse.class,
    Trx7010ObtenerHistorialSolicitudesResponse.class,
    Trx7008GrabarDetallesTransferenciaTercerosResponse.class,
    Trx7007GrabarDetallesTransferenciaResponse.class,
    Trx7006GrabarDatosSesionResponse.class,
    Trx7005ObtenerDatosSesionResponse.class,
    Trx7004ObtenerNroSesionResponse.class,
    Trx7001AddTokenResponse.class,
    Trx5223GetPedidosTransferenciaTercerosResponse.class,
    Trx5223GetPedidosTransferenciaTercerosRepetitionItem.class,
    Trx5024CnsTransfObanResponse.class,
    Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesResponse.class,
    Trx40PedSessionIdResponse.class,
    Trx5006ConsultaMovimientosCruIterationResponse.class,
    Trx301InicioVidResponse.class,
    Trx271ConsultaSaldosMultiplesCuentasCruResponse.class,
    IdentificacionDeLlamadaResponseBase.class,
    Trx809GrabarEstadististicasSegmentacionV2Response.class,
    Trx200AltDatosExtrasResponse.class,
    TrxHeaderResponseBase.class,
    Trx763ValidarTokenResponse.class,
    Trx762IdentificarTokenResponse.class,
    Trx761LlamadasArbolNuevoResponse.class,
    Trx760ClienteArbolResponse.class,
    Trx759RellamadoGalaResponse.class,
    Trx758IntencionMensajeResponse.class,
    Trx757ClickToCallResponse.class,
    Trx756LogRioHelpResponse.class
})
public class ResponseBase
    extends MappingModelBase
{

    @XmlElement(name = "CodRetorno")
    protected Integer codRetorno;
    @XmlElementRef(name = "ResponseString", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> responseString;
    @XmlElementRef(name = "Token", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> token;
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
