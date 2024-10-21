
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5024CnsTransfObanResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5024CnsTransfObanResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Transferencias" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5024}ArrayOfTrx5024CnsTransfObanResponseRepetitionItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5024CnsTransfObanResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5024", propOrder = {
    "transferencias"
})
public class Trx5024CnsTransfObanResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Transferencias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5024", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx5024CnsTransfObanResponseRepetitionItem> transferencias;

    /**
     * Gets the value of the transferencias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5024CnsTransfObanResponseRepetitionItem }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx5024CnsTransfObanResponseRepetitionItem> getTransferencias() {
        return transferencias;
    }

    /**
     * Sets the value of the transferencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx5024CnsTransfObanResponseRepetitionItem }{@code >}
     *     
     */
    public void setTransferencias(JAXBElement<ArrayOfTrx5024CnsTransfObanResponseRepetitionItem> value) {
        this.transferencias = value;
    }

}
