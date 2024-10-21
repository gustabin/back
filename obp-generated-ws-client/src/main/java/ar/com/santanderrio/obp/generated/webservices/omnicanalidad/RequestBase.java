
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="IdTransaction" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestBase", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "idTransaction",
    "token"
})
@XmlSeeAlso({
    Trx755ClaveByIdRequest.class,
    Trx754IndicadorClienteRequest.class,
    Trx752CampaniaAudioRequest.class,
    Trx753LogSlautoRequest.class,
    Trx751ReporteLlamadaTcRequest.class,
    Trx749AgendarRellamadaRequest.class,
    Trx748GetInfoAdicionalEjecutivoRemotoRequest.class,
    Trx746ValidarClaveCitiRequest.class,
    Trx745GetNupCitiRequest.class,
    DummyRequest.class,
    GetRemoveTokenRequest.class,
    IdentificacionDeLlamadaRequest.class,
    Trx991PingIATXRequest.class,
    Trx990PingDBRequest.class,
    Trx910MdlwConsultaProdMoraTemprana110Request.class,
    Trx909GrabarEstadististicasCapPapRequest.class,
    Trx908GetVDNyVQaTransferirRequest.class,
    Trx744AltaAdhesionDebitoRequest.class,
    Trx743AltaTarjetaRequest.class,
    Trx742MdlwConsultaPlazoFijoNoAltairUviRequest.class,
    AutorizBancoCompraVentaExtranjeraRequestBase.class,
    SimulacionCompraVentaMonedaExtranjeraBase.class,
    Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest.class,
    Trx736SendSessionIdRequest.class,
    Trx735EstadisticasIVRRequest.class,
    Trx734LlamadoPrioritarioRequest.class,
    Trx732DatosRuteoDerivacionRequest.class,
    Trx731GetDatosInicioRequest.class,
    Trx730InicioLlamadaRequest.class,
    Trx728TBanelcoVIDRequest.class,
    Trx727RtaVerbalIdRequest.class,
    Trx725SetRespuestaEncuestaRequest.class,
    Trx724GetEncuestaRequest.class,
    Trx723SetEncuestaRequest.class,
    Trx169AutTransfObanCciRequest.class,
    Trx344MdlwCertificacionIdentidadRequest.class,
    Trx197EvaluarCampaniasRequest.class,
    Trx168TransfOtroBanCciRequest.class,
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
    Trx832DatosRuteoDerivacionRequest.class,
    Trx767AccesoCorresponsaliaRequest.class,
    Trx766ChatMoraRequest.class,
    Trx765FirmanteEmpresasRequest.class,
    Trx053TInicioFaxSuscripcionRequest.class,
    Trx711GetCantidadDeLlamadasMoraRequest.class,
    Trx710MdlwConsultaProdMoraTempranaRequest.class,
    Trx709GrabarEstadististicasSegmentacionRequest.class,
    Trx707MdlwAltaResetClaveBRequest.class,
    Trx706MdlwValidaCVV2Request.class,
    Trx705TGetVerbalIdNegativoRequest.class,
    Trx7012ObtenerTransferenciaTercerosRequest.class,
    Trx7011ObtenerTransferenciaBancoRequest.class,
    IdentificacionDeLlamadaRequestBase.class,
    Trx4000RegistroClickToCallRequest.class,
    Trx4002PreprocesoMSGRequest.class,
    Trx4001ObtenerSolicitudClickToCallRequest.class,
    Trx764CancelarLlamadaClienteRequest.class,
    Trx4004IdentificarClienteRequest.class,
    Trx4003PostprocesoMSGRequest.class,
    Trx7009SetNodoArbolIVRRequest.class,
    Trx7010ObtenerHistorialSolicitudesRequest.class,
    Trx7008GrabarDetallesTransferenciaTercerosRequest.class,
    Trx7007GrabarDetallesTransferenciasOtrosBancosRequest.class,
    Trx7006GrabarDatosSesionRequest.class,
    Trx7005ObtenerDatosSesionRequest.class,
    Trx7004ObtenerNroSesionRequest.class,
    Trx7001AddTokenRequest.class,
    Trx5042MdlwConsultaPagosPapRequest.class,
    TransferenciasRequest.class,
    Trx40PedSessionIdRequest.class,
    Trx301InicioVidRequest.class,
    Trx201IdentificacionDeLlamadaRequest.class,
    Trx809GrabarEstadististicasSegmentacionV2Request.class,
    RequestContactBase.class,
    TrxIdentificacionClienteProductRequest.class,
    Trx763ValidarTokenRequest.class,
    Trx762IdentificarTokenRequest.class,
    Trx761LlamadasArbolNuevoRequest.class,
    Trx760ClienteArbolRequest.class,
    Trx759RellamadoGalaRequest.class,
    Trx758IntencionMensajeRequest.class,
    Trx757ClickToCallRequest.class,
    Trx756LogRioHelpRequest.class
})
public class RequestBase
    extends MappingModelBase
{

    @XmlElement(name = "IdTransaction")
    protected Integer idTransaction;
    @XmlElementRef(name = "Token", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> token;

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

}
