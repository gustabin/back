
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfStPagoCap;


/**
 * <p>Java class for Trx715TPagoCapResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx715TPagoCapResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="PagosCap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfStPagoCap" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx715TPagoCapResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx715", propOrder = {
    "pagosCap"
})
public class Trx715TPagoCapResponse
    extends ResponseBase
{

    @XmlElementRef(name = "PagosCap", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx715", type = JAXBElement.class)
    protected JAXBElement<ArrayOfStPagoCap> pagosCap;

    /**
     * Gets the value of the pagosCap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStPagoCap }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStPagoCap> getPagosCap() {
        return pagosCap;
    }

    /**
     * Sets the value of the pagosCap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStPagoCap }{@code >}
     *     
     */
    public void setPagosCap(JAXBElement<ArrayOfStPagoCap> value) {
        this.pagosCap = value;
    }

}
