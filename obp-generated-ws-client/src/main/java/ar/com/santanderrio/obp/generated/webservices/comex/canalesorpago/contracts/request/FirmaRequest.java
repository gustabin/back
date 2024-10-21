
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales.ProcesarOrPagoOBPRequest;


/**
 * <p>Java class for FirmaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FirmaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}BaseRequest">
 *       &lt;sequence>
 *         &lt;element name="Firma_datos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_datos_dentro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_formato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_hash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirmaRequest", propOrder = {
    "firmaDatos",
    "firmaDatosDentro",
    "firmaFormato",
    "firmaHash"
})
@XmlSeeAlso({
    ProcesarOrPagoOBPRequest.class
})
public class FirmaRequest
    extends BaseRequest
{

    @XmlElementRef(name = "Firma_datos", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaDatos;
    @XmlElementRef(name = "Firma_datos_dentro", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaDatosDentro;
    @XmlElementRef(name = "Firma_formato", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaFormato;
    @XmlElementRef(name = "Firma_hash", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaHash;

    /**
     * Gets the value of the firmaDatos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaDatos() {
        return firmaDatos;
    }

    /**
     * Sets the value of the firmaDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaDatos(JAXBElement<String> value) {
        this.firmaDatos = value;
    }

    /**
     * Gets the value of the firmaDatosDentro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaDatosDentro() {
        return firmaDatosDentro;
    }

    /**
     * Sets the value of the firmaDatosDentro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaDatosDentro(JAXBElement<String> value) {
        this.firmaDatosDentro = value;
    }

    /**
     * Gets the value of the firmaFormato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaFormato() {
        return firmaFormato;
    }

    /**
     * Sets the value of the firmaFormato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaFormato(JAXBElement<String> value) {
        this.firmaFormato = value;
    }

    /**
     * Gets the value of the firmaHash property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaHash() {
        return firmaHash;
    }

    /**
     * Sets the value of the firmaHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaHash(JAXBElement<String> value) {
        this.firmaHash = value;
    }

}
