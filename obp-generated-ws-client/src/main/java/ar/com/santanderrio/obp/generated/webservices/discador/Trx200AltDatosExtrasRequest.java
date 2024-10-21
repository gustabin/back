
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx200AltDatosExtrasRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx200AltDatosExtrasRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoDatos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosExtras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx200AltDatosExtrasRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx200", propOrder = {
    "codigoDatos",
    "datosExtras"
})
public class Trx200AltDatosExtrasRequest
    extends RequestContactBase
{

    @XmlElementRef(name = "CodigoDatos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx200", type = JAXBElement.class)
    protected JAXBElement<String> codigoDatos;
    @XmlElementRef(name = "DatosExtras", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx200", type = JAXBElement.class)
    protected JAXBElement<String> datosExtras;

    /**
     * Gets the value of the codigoDatos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoDatos() {
        return codigoDatos;
    }

    /**
     * Sets the value of the codigoDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoDatos(JAXBElement<String> value) {
        this.codigoDatos = value;
    }

    /**
     * Gets the value of the datosExtras property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatosExtras() {
        return datosExtras;
    }

    /**
     * Sets the value of the datosExtras property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatosExtras(JAXBElement<String> value) {
        this.datosExtras = value;
    }

}
