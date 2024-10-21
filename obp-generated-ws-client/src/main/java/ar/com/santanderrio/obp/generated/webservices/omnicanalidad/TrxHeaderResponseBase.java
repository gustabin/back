
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrxHeaderResponseBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrxHeaderResponseBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Delimitador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeaderTrama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}StatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrxHeaderResponseBase", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "delimitador",
    "headerTrama",
    "longitud",
    "statusResult"
})
@XmlSeeAlso({
    Trx747RescateFciCitiResponse.class,
    Trx910MdlwConsultaProdMoraTemprana110Response.class,
    Trx744AltaAdhesionDebitoResponse.class,
    Trx742MdlwConsultaPlazoFijoNoAltairUviResponse.class,
    Trx741MdlwAutorizBancoCompraMonedaExtranjeraResponse.class,
    Trx740MdlwSimulacionBancoCompraMonedaExtranjeraResponse.class,
    TrxMonedaResponseBase.class,
    Trx737MdlwConsultaCotizacionMonedaExtranjeraResponse.class,
    Trx726MdlwIdentificacionDelClienteVerIdResponse.class,
    Trx169AutTransfObanCciResponse.class,
    Trx113CnsAdhesionAdomTcResponse.class,
    Trx90StopDebitResponse.class,
    Trx089CnsDebAutResponse.class,
    Trx083CnsTasasPfResponse.class,
    Trx59ConsultaValoresAcreditarResponse.class,
    Trx344MdlwCertificacionIdentidadResponse.class,
    Trx296MdlwStopRenovacionPlazoFijoAltairResponse.class,
    Trx168TransfOtroBanCciResponse.class,
    Trx146CnsPuntosSuperclubCliResponse.class,
    Trx030CnsPfTiposResponse.class,
    Trx021PedChequeraResponse.class,
    Trx016MfTransferenciaResponse.class,
    Trx15ConsultaCotizacionDivisaResponse.class,
    Trx710MdlwConsultaProdMoraTempranaResponse.class,
    Trx707MdlwAltaResetClaveBResponse.class,
    Trx706MdlwValidaCVV2Response.class,
    Trx701ValidarTarjetaCoordenadasResponse.class,
    TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45Mp1 .class,
    Trx700PedTjCoordResponse.class,
    Trx619MfPlazoFijoV140Response.class,
    TrxClienteResponseBaseOfTrx602IdentificacionDelClienteProductResponsecQymPDgn.class,
    Trx544CnsCertificacionIdentidadAnphResponse.class,
    Trx5042MdlwConsultaPagosPapResponse.class,
    Trx050MdlwConsultaCotizacionTransferenciaResponse.class,
    Trx5006ConsultaMovimientosCruResponse.class,
    Trx414CondPfPfiResponse.class,
    ConsultaDatosCuentaResponseBase.class,
    Trx302CnsCuotaPresAltairResponse.class,
    Trx278ConsultaModalidadTransferenciaResponse.class,
    Trx258ConsultaValoresDebitadosResponse.class,
    Trx218MfPagoTarjetaV120Response.class,
    Trx211DetTcV130Response.class,
    TrxClienteResponseBaseOfTrx802IdentificacionDelClienteProductResponse7IUOSFqe.class
})
public class TrxHeaderResponseBase
    extends ResponseBase
{

    @XmlElementRef(name = "Delimitador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> delimitador;
    @XmlElementRef(name = "HeaderTrama", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> headerTrama;
    @XmlElementRef(name = "Longitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> longitud;
    @XmlElementRef(name = "StatusResult", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<StatusResult> statusResult;

    /**
     * Gets the value of the delimitador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelimitador() {
        return delimitador;
    }

    /**
     * Sets the value of the delimitador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelimitador(JAXBElement<String> value) {
        this.delimitador = value;
    }

    /**
     * Gets the value of the headerTrama property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the value of the headerTrama property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHeaderTrama(JAXBElement<String> value) {
        this.headerTrama = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitud(JAXBElement<String> value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the statusResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public JAXBElement<StatusResult> getStatusResult() {
        return statusResult;
    }

    /**
     * Sets the value of the statusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public void setStatusResult(JAXBElement<StatusResult> value) {
        this.statusResult = value;
    }

}
