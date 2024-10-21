
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx700PedTjCoordResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx700PedTjCoordResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Coord_X1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Coord_X2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Coord_Y1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Coord_Y2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Numero_Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx700PedTjCoordResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", propOrder = {
    "coordX1",
    "coordX2",
    "coordY1",
    "coordY2",
    "numeroSerie"
})
public class Trx700PedTjCoordResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Coord_X1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", type = JAXBElement.class)
    protected JAXBElement<String> coordX1;
    @XmlElementRef(name = "Coord_X2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", type = JAXBElement.class)
    protected JAXBElement<String> coordX2;
    @XmlElementRef(name = "Coord_Y1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", type = JAXBElement.class)
    protected JAXBElement<String> coordY1;
    @XmlElementRef(name = "Coord_Y2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", type = JAXBElement.class)
    protected JAXBElement<String> coordY2;
    @XmlElementRef(name = "Numero_Serie", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx700", type = JAXBElement.class)
    protected JAXBElement<String> numeroSerie;

    /**
     * Gets the value of the coordX1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCoordX1() {
        return coordX1;
    }

    /**
     * Sets the value of the coordX1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCoordX1(JAXBElement<String> value) {
        this.coordX1 = value;
    }

    /**
     * Gets the value of the coordX2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCoordX2() {
        return coordX2;
    }

    /**
     * Sets the value of the coordX2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCoordX2(JAXBElement<String> value) {
        this.coordX2 = value;
    }

    /**
     * Gets the value of the coordY1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCoordY1() {
        return coordY1;
    }

    /**
     * Sets the value of the coordY1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCoordY1(JAXBElement<String> value) {
        this.coordY1 = value;
    }

    /**
     * Gets the value of the coordY2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCoordY2() {
        return coordY2;
    }

    /**
     * Sets the value of the coordY2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCoordY2(JAXBElement<String> value) {
        this.coordY2 = value;
    }

    /**
     * Gets the value of the numeroSerie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Sets the value of the numeroSerie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSerie(JAXBElement<String> value) {
        this.numeroSerie = value;
    }

}
