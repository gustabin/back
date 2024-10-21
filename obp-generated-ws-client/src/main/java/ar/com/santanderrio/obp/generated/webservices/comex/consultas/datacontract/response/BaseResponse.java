
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.canales.ConsultaBeneficiarioResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaAgendaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomMonedaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerCondicionesVentaResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.PaisesConDobleConvenioResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.RegimenesGananciasResponse;


/**
 * <p>Clase Java para BaseResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
    ConsultaAgendaResponse.class,
    ConsultaMonedasResponse.class,
    ObtenerCondicionesVentaResponse.class,
    RegimenesGananciasResponse.class,
    PaisesConDobleConvenioResponse.class,
    ObtenerTipoDocResponse.class,
    ConsultaNomMonedaResponse.class,
    ConsultaPaisesResponse.class,
    ConsultaBeneficiarioResponse.class,
    CursorResponse.class
})
public class BaseResponse {

    @XmlElementRef(name = "Error_Interface", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorInterface;
    @XmlElementRef(name = "Error_Sistema", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response", type = JAXBElement.class)
    protected JAXBElement<String> errorSistema;

    /**
     * Obtiene el valor de la propiedad errorInterface.
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
     * Define el valor de la propiedad errorInterface.
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
     * Obtiene el valor de la propiedad errorSistema.
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
     * Define el valor de la propiedad errorSistema.
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
