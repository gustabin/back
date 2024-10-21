
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfPagoPapResponse;


/**
 * <p>Java class for Trx5042MdlwConsultaPagosPapResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5042MdlwConsultaPagosPapResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="PagosPap" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfPagoPapResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5042MdlwConsultaPagosPapResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5042", propOrder = {
    "pagosPap"
})
public class Trx5042MdlwConsultaPagosPapResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "PagosPap", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5042", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPagoPapResponse> pagosPap;

    /**
     * Gets the value of the pagosPap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPagoPapResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPagoPapResponse> getPagosPap() {
        return pagosPap;
    }

    /**
     * Sets the value of the pagosPap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPagoPapResponse }{@code >}
     *     
     */
    public void setPagosPap(JAXBElement<ArrayOfPagoPapResponse> value) {
        this.pagosPap = value;
    }

}
