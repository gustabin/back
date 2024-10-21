
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.MappingModelBase;


/**
 * <p>Java class for PlazoFijoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PlazoFijoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="DescripcionTipoPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiceCorralito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlazoFijoResponse", propOrder = {
    "descripcionTipoPlazoFijo",
    "indiceCorralito",
    "tipoPlazoFijo"
})
public class PlazoFijoResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "DescripcionTipoPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descripcionTipoPlazoFijo;
    @XmlElementRef(name = "IndiceCorralito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indiceCorralito;
    @XmlElementRef(name = "TipoPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> tipoPlazoFijo;

    /**
     * Gets the value of the descripcionTipoPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionTipoPlazoFijo() {
        return descripcionTipoPlazoFijo;
    }

    /**
     * Sets the value of the descripcionTipoPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionTipoPlazoFijo(JAXBElement<String> value) {
        this.descripcionTipoPlazoFijo = value;
    }

    /**
     * Gets the value of the indiceCorralito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndiceCorralito() {
        return indiceCorralito;
    }

    /**
     * Sets the value of the indiceCorralito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndiceCorralito(JAXBElement<String> value) {
        this.indiceCorralito = value;
    }

    /**
     * Gets the value of the tipoPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPlazoFijo() {
        return tipoPlazoFijo;
    }

    /**
     * Sets the value of the tipoPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPlazoFijo(JAXBElement<String> value) {
        this.tipoPlazoFijo = value;
    }

}
