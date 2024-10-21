
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx773ExtraccionSinTarjetaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx773ExtraccionSinTarjetaResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoIdentificacionMandato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CodigoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx773ExtraccionSinTarjetaResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx773", propOrder = {
    "codigoError",
    "codigoIdentificacionMandato",
    "codigoOperacion"
})
public class Trx773ExtraccionSinTarjetaResponse
    extends ResponseBase
{

    @XmlElementRef(name = "CodigoError", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx773", type = JAXBElement.class)
    protected JAXBElement<String> codigoError;
    @XmlElement(name = "CodigoIdentificacionMandato")
    protected Integer codigoIdentificacionMandato;
    @XmlElementRef(name = "CodigoOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx773", type = JAXBElement.class)
    protected JAXBElement<String> codigoOperacion;

    /**
     * Gets the value of the codigoError property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoError(JAXBElement<String> value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the codigoIdentificacionMandato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoIdentificacionMandato() {
        return codigoIdentificacionMandato;
    }

    /**
     * Sets the value of the codigoIdentificacionMandato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoIdentificacionMandato(Integer value) {
        this.codigoIdentificacionMandato = value;
    }

    /**
     * Gets the value of the codigoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoOperacion() {
        return codigoOperacion;
    }

    /**
     * Sets the value of the codigoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoOperacion(JAXBElement<String> value) {
        this.codigoOperacion = value;
    }

}
