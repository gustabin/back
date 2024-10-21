
package ar.com.santanderrio.obp.generated.webservices.segmento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetInfoAdicionalParaDireccionadorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInfoAdicionalParaDireccionadorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ColorSemaforo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EjecutivoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExCiti" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IndicadorTipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MejorProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSucursalAdministradora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Segmento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentaFacturacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VIP" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInfoAdicionalParaDireccionadorResponse", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", propOrder = {
    "colorSemaforo",
    "ejecutivo",
    "ejecutivoId",
    "exCiti",
    "indicadorTipoCliente",
    "mejorProducto",
    "numeroSucursalAdministradora",
    "segmento",
    "semaforoRentaFacturacion",
    "semaforoRentabilidad",
    "vip"
})
public class GetInfoAdicionalParaDireccionadorResponse2 {

    @XmlElementRef(name = "ColorSemaforo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> colorSemaforo;
    @XmlElementRef(name = "Ejecutivo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivo;
    @XmlElementRef(name = "EjecutivoId", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivoId;
    @XmlElement(name = "ExCiti")
    protected Boolean exCiti;
    @XmlElementRef(name = "IndicadorTipoCliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> indicadorTipoCliente;
    @XmlElementRef(name = "MejorProducto", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> mejorProducto;
    @XmlElementRef(name = "NumeroSucursalAdministradora", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> numeroSucursalAdministradora;
    @XmlElementRef(name = "Segmento", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> segmento;
    @XmlElementRef(name = "SemaforoRentaFacturacion", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentaFacturacion;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElement(name = "VIP")
    protected Boolean vip;

    /**
     * Gets the value of the colorSemaforo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColorSemaforo() {
        return colorSemaforo;
    }

    /**
     * Sets the value of the colorSemaforo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColorSemaforo(JAXBElement<String> value) {
        this.colorSemaforo = value;
    }

    /**
     * Gets the value of the ejecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEjecutivo() {
        return ejecutivo;
    }

    /**
     * Sets the value of the ejecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEjecutivo(JAXBElement<String> value) {
        this.ejecutivo = value;
    }

    /**
     * Gets the value of the ejecutivoId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEjecutivoId() {
        return ejecutivoId;
    }

    /**
     * Sets the value of the ejecutivoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEjecutivoId(JAXBElement<String> value) {
        this.ejecutivoId = value;
    }

    /**
     * Gets the value of the exCiti property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExCiti() {
        return exCiti;
    }

    /**
     * Sets the value of the exCiti property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExCiti(Boolean value) {
        this.exCiti = value;
    }

    /**
     * Gets the value of the indicadorTipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorTipoCliente() {
        return indicadorTipoCliente;
    }

    /**
     * Sets the value of the indicadorTipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorTipoCliente(JAXBElement<String> value) {
        this.indicadorTipoCliente = value;
    }

    /**
     * Gets the value of the mejorProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMejorProducto() {
        return mejorProducto;
    }

    /**
     * Sets the value of the mejorProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMejorProducto(JAXBElement<String> value) {
        this.mejorProducto = value;
    }

    /**
     * Gets the value of the numeroSucursalAdministradora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSucursalAdministradora() {
        return numeroSucursalAdministradora;
    }

    /**
     * Sets the value of the numeroSucursalAdministradora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSucursalAdministradora(JAXBElement<String> value) {
        this.numeroSucursalAdministradora = value;
    }

    /**
     * Gets the value of the segmento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSegmento() {
        return segmento;
    }

    /**
     * Sets the value of the segmento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSegmento(JAXBElement<String> value) {
        this.segmento = value;
    }

    /**
     * Gets the value of the semaforoRentaFacturacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentaFacturacion() {
        return semaforoRentaFacturacion;
    }

    /**
     * Sets the value of the semaforoRentaFacturacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentaFacturacion(JAXBElement<String> value) {
        this.semaforoRentaFacturacion = value;
    }

    /**
     * Gets the value of the semaforoRentabilidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentabilidad() {
        return semaforoRentabilidad;
    }

    /**
     * Sets the value of the semaforoRentabilidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentabilidad(JAXBElement<String> value) {
        this.semaforoRentabilidad = value;
    }

    /**
     * Gets the value of the vip property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVIP() {
        return vip;
    }

    /**
     * Sets the value of the vip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVIP(Boolean value) {
        this.vip = value;
    }

}
