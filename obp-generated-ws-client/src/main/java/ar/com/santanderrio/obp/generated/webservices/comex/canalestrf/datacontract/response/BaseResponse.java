
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaCuentaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaAgendaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ClonarImagenesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaAgendaCuentaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaConceptoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaImagenTrfResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaMotExcepcionResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaNifResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaTiposConceptoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaCuentaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.DesvinculaAgendaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObtenerMsgSwiftResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarNifResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaBPMResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarNIFxNUPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ValidarPosicionResponse;


/**
 * <p>Java class for BaseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Error_Interface" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Error_Sistema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseResponse", propOrder = {
    "errorInterface",
    "errorSistema"
})
@XmlSeeAlso({
    ConsultaImagenTrfResponse.class,
    CargaAgendaCuentaResponse.class,
    ConsultaDetalleTrfResponse.class,
    ConsultaAgendaCuentaResponse.class,
    ObtenerMsgSwiftResponse.class,
    ValidarNIFxNUPResponse.class,
    ConsultaNifResponse.class,
    CargaAgendaResponse.class,
    ConsultaConceptoResponse.class,
    ConsultaMotExcepcionResponse.class,
    CargaDocResponse.class,
    ConsultaTiposConceptoResponse.class,
    ClonarImagenesResponse.class,
    ConceptosPorTipoResponse.class,
    DesvinculaAgendaCuentaResponse.class,
    ProcesarTransferenciaBPMResponse.class,
    ProcesarNifResponse.class,
    ConsultaDetalleTrfOBPResponse.class,
    CursorResponse.class,
    BorrarDocResponse.class,
    DesvinculaAgendaResponse.class,
    ValidarPosicionResponse.class,
    ProcesarTransferenciaOBPResponse.class,
    ProcesarTransferenciaResponse.class
})
public class BaseResponse {

    @XmlElementRef(name = "Error_Interface", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorInterface;
    @XmlElementRef(name = "Error_Sistema", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorSistema;

    /**
     * Gets the value of the errorInterface property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorInterface() {
        return errorInterface;
    }

    /**
     * Sets the value of the errorInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorInterface(JAXBElement<String> value) {
        this.errorInterface = value;
    }

    /**
     * Gets the value of the errorSistema property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorSistema() {
        return errorSistema;
    }

    /**
     * Sets the value of the errorSistema property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorSistema(JAXBElement<String> value) {
        this.errorSistema = value;
    }

}
