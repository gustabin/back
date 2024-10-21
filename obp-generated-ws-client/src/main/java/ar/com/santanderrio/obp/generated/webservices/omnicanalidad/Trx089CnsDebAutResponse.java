
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx089CnsDebAutResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx089CnsDebAutResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DebitosAutomaticos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089}ArrayOfTrx089DebitoAutomaticoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx089CnsDebAutResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", propOrder = {
    "cantidadRegistros",
    "debitosAutomaticos"
})
public class Trx089CnsDebAutResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadRegistros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<String> cantidadRegistros;
    @XmlElementRef(name = "DebitosAutomaticos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx089", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx089DebitoAutomaticoResponse> debitosAutomaticos;

    /**
     * Gets the value of the cantidadRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadRegistros() {
        return cantidadRegistros;
    }

    /**
     * Sets the value of the cantidadRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadRegistros(JAXBElement<String> value) {
        this.cantidadRegistros = value;
    }

    /**
     * Gets the value of the debitosAutomaticos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx089DebitoAutomaticoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx089DebitoAutomaticoResponse> getDebitosAutomaticos() {
        return debitosAutomaticos;
    }

    /**
     * Sets the value of the debitosAutomaticos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx089DebitoAutomaticoResponse }{@code >}
     *     
     */
    public void setDebitosAutomaticos(JAXBElement<ArrayOfTrx089DebitoAutomaticoResponse> value) {
        this.debitosAutomaticos = value;
    }

}
