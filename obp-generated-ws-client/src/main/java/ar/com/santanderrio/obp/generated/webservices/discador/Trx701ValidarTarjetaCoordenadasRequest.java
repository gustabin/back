
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx701ValidarTarjetaCoordenadasRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx701ValidarTarjetaCoordenadasRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701}TrxCoordenadasRequestBase">
 *       &lt;sequence>
 *         &lt;element name="CoordX1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CoordX2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CoordY1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CoordY2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorCoordenada1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorCoordenada2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx701ValidarTarjetaCoordenadasRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", propOrder = {
    "coordX1",
    "coordX2",
    "coordY1",
    "coordY2",
    "nroSerie",
    "valorCoordenada1",
    "valorCoordenada2"
})
public class Trx701ValidarTarjetaCoordenadasRequest
    extends TrxCoordenadasRequestBase
{

    @XmlElementRef(name = "CoordX1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> coordX1;
    @XmlElementRef(name = "CoordX2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> coordX2;
    @XmlElementRef(name = "CoordY1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> coordY1;
    @XmlElementRef(name = "CoordY2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> coordY2;
    @XmlElementRef(name = "NroSerie", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> nroSerie;
    @XmlElementRef(name = "ValorCoordenada1", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> valorCoordenada1;
    @XmlElementRef(name = "ValorCoordenada2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx701", type = JAXBElement.class)
    protected JAXBElement<String> valorCoordenada2;

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
     * Gets the value of the nroSerie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSerie() {
        return nroSerie;
    }

    /**
     * Sets the value of the nroSerie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSerie(JAXBElement<String> value) {
        this.nroSerie = value;
    }

    /**
     * Gets the value of the valorCoordenada1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorCoordenada1() {
        return valorCoordenada1;
    }

    /**
     * Sets the value of the valorCoordenada1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorCoordenada1(JAXBElement<String> value) {
        this.valorCoordenada1 = value;
    }

    /**
     * Gets the value of the valorCoordenada2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getValorCoordenada2() {
        return valorCoordenada2;
    }

    /**
     * Sets the value of the valorCoordenada2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setValorCoordenada2(JAXBElement<String> value) {
        this.valorCoordenada2 = value;
    }

}
