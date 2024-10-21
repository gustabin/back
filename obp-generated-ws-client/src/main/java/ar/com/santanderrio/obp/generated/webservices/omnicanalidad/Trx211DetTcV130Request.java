
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx211DetTcV130Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx211DetTcV130Request">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoTitularidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiceSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx211DetTcV130Request", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", propOrder = {
    "codigoTitularidad",
    "indiceSinonimo",
    "numeroSucursal",
    "numeroTarjeta"
})
public class Trx211DetTcV130Request
    extends RequestContactBase
{

    @XmlElementRef(name = "CodigoTitularidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> codigoTitularidad;
    @XmlElementRef(name = "IndiceSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> indiceSinonimo;
    @XmlElementRef(name = "NumeroSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> numeroSucursal;
    @XmlElementRef(name = "NumeroTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx211", type = JAXBElement.class)
    protected JAXBElement<String> numeroTarjeta;

    /**
     * Gets the value of the codigoTitularidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoTitularidad() {
        return codigoTitularidad;
    }

    /**
     * Sets the value of the codigoTitularidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoTitularidad(JAXBElement<String> value) {
        this.codigoTitularidad = value;
    }

    /**
     * Gets the value of the indiceSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndiceSinonimo() {
        return indiceSinonimo;
    }

    /**
     * Sets the value of the indiceSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndiceSinonimo(JAXBElement<String> value) {
        this.indiceSinonimo = value;
    }

    /**
     * Gets the value of the numeroSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSucursal() {
        return numeroSucursal;
    }

    /**
     * Sets the value of the numeroSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSucursal(JAXBElement<String> value) {
        this.numeroSucursal = value;
    }

    /**
     * Gets the value of the numeroTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Sets the value of the numeroTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroTarjeta(JAXBElement<String> value) {
        this.numeroTarjeta = value;
    }

}
