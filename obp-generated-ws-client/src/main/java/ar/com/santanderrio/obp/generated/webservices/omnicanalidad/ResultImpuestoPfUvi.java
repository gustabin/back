
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultImpuestoPfUvi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultImpuestoPfUvi">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="DescripcionImpuesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MomentoCobro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoMonedaExtranjera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoMonedaLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultImpuestoPfUvi", propOrder = {
    "descripcionImpuesto",
    "momentoCobro",
    "montoMonedaExtranjera",
    "montoMonedaLocal"
})
public class ResultImpuestoPfUvi
    extends MappingModelBase
{

    @XmlElementRef(name = "DescripcionImpuesto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descripcionImpuesto;
    @XmlElementRef(name = "MomentoCobro", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> momentoCobro;
    @XmlElementRef(name = "MontoMonedaExtranjera", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> montoMonedaExtranjera;
    @XmlElementRef(name = "MontoMonedaLocal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> montoMonedaLocal;

    /**
     * Gets the value of the descripcionImpuesto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionImpuesto() {
        return descripcionImpuesto;
    }

    /**
     * Sets the value of the descripcionImpuesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionImpuesto(JAXBElement<String> value) {
        this.descripcionImpuesto = value;
    }

    /**
     * Gets the value of the momentoCobro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMomentoCobro() {
        return momentoCobro;
    }

    /**
     * Sets the value of the momentoCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMomentoCobro(JAXBElement<String> value) {
        this.momentoCobro = value;
    }

    /**
     * Gets the value of the montoMonedaExtranjera property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMontoMonedaExtranjera() {
        return montoMonedaExtranjera;
    }

    /**
     * Sets the value of the montoMonedaExtranjera property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMontoMonedaExtranjera(JAXBElement<String> value) {
        this.montoMonedaExtranjera = value;
    }

    /**
     * Gets the value of the montoMonedaLocal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMontoMonedaLocal() {
        return montoMonedaLocal;
    }

    /**
     * Sets the value of the montoMonedaLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMontoMonedaLocal(JAXBElement<String> value) {
        this.montoMonedaLocal = value;
    }

}
