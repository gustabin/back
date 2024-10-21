
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx002IdentificacionDelClienteResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx002IdentificacionDelClienteResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45mp1">
 *       &lt;sequence>
 *         &lt;element name="HorarioMora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx002IdentificacionDelClienteResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", propOrder = {
    "horarioMora",
    "mora"
})
public class Trx002IdentificacionDelClienteResponse
    extends TrxClienteResponseBaseOfTrx002IdentificacionDelClienteProductResponsewzz45Mp1
{

    @XmlElementRef(name = "HorarioMora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", type = JAXBElement.class)
    protected JAXBElement<String> horarioMora;
    @XmlElementRef(name = "Mora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx002", type = JAXBElement.class)
    protected JAXBElement<String> mora;

    /**
     * Gets the value of the horarioMora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHorarioMora() {
        return horarioMora;
    }

    /**
     * Sets the value of the horarioMora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHorarioMora(JAXBElement<String> value) {
        this.horarioMora = value;
    }

    /**
     * Gets the value of the mora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMora() {
        return mora;
    }

    /**
     * Sets the value of the mora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMora(JAXBElement<String> value) {
        this.mora = value;
    }

}
