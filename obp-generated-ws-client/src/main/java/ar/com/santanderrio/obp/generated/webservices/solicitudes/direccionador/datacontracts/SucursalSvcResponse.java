
package ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.datacontracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SucursalSvcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SucursalSvcResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Descri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SucursalSvcResponse", propOrder = {
    "descri",
    "domicilio",
    "localidad",
    "sucursal"
})
public class SucursalSvcResponse {

    @XmlElementRef(name = "Descri", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<String> descri;
    @XmlElementRef(name = "Domicilio", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<String> domicilio;
    @XmlElementRef(name = "Localidad", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<String> localidad;
    @XmlElementRef(name = "Sucursal", namespace = "http://schemas.datacontract.org/2004/07/BSCH.CRM.Contracts.Direccionador.DataContracts", type = JAXBElement.class)
    protected JAXBElement<String> sucursal;

    /**
     * Gets the value of the descri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescri() {
        return descri;
    }

    /**
     * Sets the value of the descri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescri(JAXBElement<String> value) {
        this.descri = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilio(JAXBElement<String> value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidad(JAXBElement<String> value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<String> value) {
        this.sucursal = value;
    }

}
