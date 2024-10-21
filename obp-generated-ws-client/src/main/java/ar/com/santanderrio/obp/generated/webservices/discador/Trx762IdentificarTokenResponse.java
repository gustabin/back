
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx762IdentificarTokenResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx762IdentificarTokenResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="IndToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TarjetaCoordenadas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx762IdentificarTokenResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx762", propOrder = {
    "indToken",
    "tarjetaCoordenadas"
})
public class Trx762IdentificarTokenResponse
    extends ResponseBase
{

    @XmlElementRef(name = "IndToken", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx762", type = JAXBElement.class)
    protected JAXBElement<String> indToken;
    @XmlElementRef(name = "TarjetaCoordenadas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx762", type = JAXBElement.class)
    protected JAXBElement<String> tarjetaCoordenadas;

    /**
     * Gets the value of the indToken property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndToken() {
        return indToken;
    }

    /**
     * Sets the value of the indToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndToken(JAXBElement<String> value) {
        this.indToken = value;
    }

    /**
     * Gets the value of the tarjetaCoordenadas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTarjetaCoordenadas() {
        return tarjetaCoordenadas;
    }

    /**
     * Sets the value of the tarjetaCoordenadas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTarjetaCoordenadas(JAXBElement<String> value) {
        this.tarjetaCoordenadas = value;
    }

}
