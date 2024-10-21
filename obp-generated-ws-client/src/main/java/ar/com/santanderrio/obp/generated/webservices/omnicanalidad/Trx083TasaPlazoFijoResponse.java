
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx083TasaPlazoFijoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx083TasaPlazoFijoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="DescripcionSubtipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TasaPlazoFijoCanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx083TasaPlazoFijoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", propOrder = {
    "descripcionSubtipo",
    "importe",
    "moneda",
    "plazoDias",
    "tasaPlazoFijo",
    "tasaPlazoFijoCanal",
    "tipoPlazo"
})
public class Trx083TasaPlazoFijoResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "DescripcionSubtipo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> descripcionSubtipo;
    @XmlElementRef(name = "Importe", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> importe;
    @XmlElementRef(name = "Moneda", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "PlazoDias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> plazoDias;
    @XmlElementRef(name = "TasaPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> tasaPlazoFijo;
    @XmlElementRef(name = "TasaPlazoFijoCanal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> tasaPlazoFijoCanal;
    @XmlElementRef(name = "TipoPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> tipoPlazo;

    /**
     * Gets the value of the descripcionSubtipo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionSubtipo() {
        return descripcionSubtipo;
    }

    /**
     * Sets the value of the descripcionSubtipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionSubtipo(JAXBElement<String> value) {
        this.descripcionSubtipo = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporte(JAXBElement<String> value) {
        this.importe = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneda(JAXBElement<String> value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the plazoDias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazoDias() {
        return plazoDias;
    }

    /**
     * Sets the value of the plazoDias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazoDias(JAXBElement<String> value) {
        this.plazoDias = value;
    }

    /**
     * Gets the value of the tasaPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaPlazoFijo() {
        return tasaPlazoFijo;
    }

    /**
     * Sets the value of the tasaPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaPlazoFijo(JAXBElement<String> value) {
        this.tasaPlazoFijo = value;
    }

    /**
     * Gets the value of the tasaPlazoFijoCanal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTasaPlazoFijoCanal() {
        return tasaPlazoFijoCanal;
    }

    /**
     * Sets the value of the tasaPlazoFijoCanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTasaPlazoFijoCanal(JAXBElement<String> value) {
        this.tasaPlazoFijoCanal = value;
    }

    /**
     * Gets the value of the tipoPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPlazo() {
        return tipoPlazo;
    }

    /**
     * Sets the value of the tipoPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPlazo(JAXBElement<String> value) {
        this.tipoPlazo = value;
    }

}
