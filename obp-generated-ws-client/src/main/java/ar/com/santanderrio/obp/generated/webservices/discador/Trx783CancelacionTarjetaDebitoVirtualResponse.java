
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx783CancelacionTarjetaDebitoVirtualResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx783CancelacionTarjetaDebitoVirtualResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="DescripcionOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultadoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx783CancelacionTarjetaDebitoVirtualResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx783", propOrder = {
    "descripcionOperacion",
    "resultadoOperacion"
})
public class Trx783CancelacionTarjetaDebitoVirtualResponse
    extends ResponseBase
{

    @XmlElementRef(name = "DescripcionOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx783", type = JAXBElement.class)
    protected JAXBElement<String> descripcionOperacion;
    @XmlElementRef(name = "ResultadoOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx783", type = JAXBElement.class)
    protected JAXBElement<String> resultadoOperacion;

    /**
     * Gets the value of the descripcionOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionOperacion() {
        return descripcionOperacion;
    }

    /**
     * Sets the value of the descripcionOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionOperacion(JAXBElement<String> value) {
        this.descripcionOperacion = value;
    }

    /**
     * Gets the value of the resultadoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResultadoOperacion() {
        return resultadoOperacion;
    }

    /**
     * Sets the value of the resultadoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResultadoOperacion(JAXBElement<String> value) {
        this.resultadoOperacion = value;
    }

}
