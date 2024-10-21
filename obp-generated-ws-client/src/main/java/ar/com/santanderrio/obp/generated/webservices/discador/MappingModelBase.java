
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.AddGetRemoveTokenRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.AutorizacionRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.ProductosRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteHeadRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.IdentificacionDeLlamadaProductoResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.IdentificacionDelClienteProductResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ImpuestoPlazoFijoResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.InteresantesResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.PlazoFijoResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ProductoMoraResponse;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ResultCertificadoUvi;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ResultConsultaSolicitudesAutorizacion;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ResultImpuestoPfUvi;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ResultProductoAltairPrestamos;


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
@XmlType(name = "MappingModelBase", propOrder = {
    "requestString"
})
@XmlSeeAlso({
    Trx5024CnsTransfObanResponseRepetitionItem.class,
    StatusResult.class,
    AddGetRemoveTokenRequest.class,
    ProductosRequest.class,
    AutorizacionRequest.class,
    MappingModelResponseBase.class,
    Trx278ConsultaModalidadTransferenciaIterationResponse.class,
    Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse.class,
    Trx258ConsultaValoresDebitadosIterationResponse.class,
    PlazoFijoResponse.class,
    InteresantesResponse.class,
    ImpuestoPlazoFijoResponse.class,
    ProductoMoraResponse.class,
    ResultConsultaSolicitudesAutorizacion.class,
    IdentificacionDeLlamadaProductoResponse.class,
    ResultCertificadoUvi.class,
    ResultProductoAltairPrestamos.class,
    IdentificacionDelClienteProductResponse.class,
    ResultImpuestoPfUvi.class,
    Trx15ConsultaCotizacionDivisaIterationResponse.class,
    TrxIdentificacionClienteProductResponse.class,
    Trx089DebitoAutomaticoResponse.class,
    Trx083TasaPlazoFijoResponse.class,
    Trx59ConsultaValoresAcreditarIterationResponse.class,
    TDatosClienteHeadRequest.class,
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
