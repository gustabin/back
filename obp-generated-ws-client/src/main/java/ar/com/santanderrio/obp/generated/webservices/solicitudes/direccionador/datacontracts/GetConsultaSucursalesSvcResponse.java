
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetConsultaSucursalesSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetConsultaSucursalesSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MensajeError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursales" type="{http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts}ArrayOfSucursalSvcResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetConsultaSucursalesSvcResponse", propOrder = {
    "codigoError",
    "mensajeError",
    "sucursales"
})
public class GetConsultaSucursalesSvcResponse {

    @XmlElement(name = "CodigoError")
    protected BigDecimal codigoError;
    @XmlElementRef(name = "MensajeError", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<String> mensajeError;
    @XmlElementRef(name = "Sucursales", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSucursalSvcResponse> sucursales;

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCodigoError(BigDecimal value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the mensajeError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the value of the mensajeError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensajeError(JAXBElement<String> value) {
        this.mensajeError = value;
    }

    /**
     * Gets the value of the sucursales property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSucursalSvcResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSucursalSvcResponse> getSucursales() {
        return sucursales;
    }

    /**
     * Sets the value of the sucursales property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSucursalSvcResponse }{@code >}
     *     
     */
    public void setSucursales(JAXBElement<ArrayOfSucursalSvcResponse> value) {
        this.sucursales = value;
    }

}
