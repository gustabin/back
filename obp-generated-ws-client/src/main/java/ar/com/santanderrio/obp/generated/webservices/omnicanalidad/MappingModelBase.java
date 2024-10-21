
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MappingModelBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MappingModelBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MappingModelBase", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "requestString"
})
@XmlSeeAlso({
    StatusResult.class,
    ProductosRequest.class,
    MappingModelResponseBase.class,
    AutorizacionRequest.class,
    AddGetRemoveTokenRequest.class,
    Trx089DebitoAutomaticoResponse.class,
    Trx083TasaPlazoFijoResponse.class,
    Trx59ConsultaValoresAcreditarIterationResponse.class,
    TDatosClienteHeadRequest.class,
    Trx15ConsultaCotizacionDivisaIterationResponse.class,
    PlazoFijoResponse.class,
    InteresantesResponse.class,
    ImpuestoPlazoFijoResponse.class,
    ProductoMoraResponse.class,
    ResultConsultaSolicitudesAutorizacion.class,
    ResultCertificadoUvi.class,
    ResultProductoAltairPrestamos.class,
    IdentificacionDelClienteProductResponse.class,
    ResultImpuestoPfUvi.class,
    Trx5024CnsTransfObanResponseRepetitionItem.class,
    Trx278ConsultaModalidadTransferenciaIterationResponse.class,
    Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse.class,
    Trx258ConsultaValoresDebitadosIterationResponse.class,
    IdentificacionDeLlamadaProductoResponse.class,
    TrxIdentificacionClienteProductResponse.class,
    ResponseBase.class,
    RequestBase.class
})
public class MappingModelBase {

    @XmlElementRef(name = "RequestString", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> requestString;

    /**
     * Gets the value of the requestString property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRequestString() {
        return requestString;
    }

    /**
     * Sets the value of the requestString property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRequestString(JAXBElement<String> value) {
        this.requestString = value;
    }

}
