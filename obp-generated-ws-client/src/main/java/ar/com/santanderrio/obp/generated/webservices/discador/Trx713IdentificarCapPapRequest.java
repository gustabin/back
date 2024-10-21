
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx713IdentificarCapPapRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx713IdentificarCapPapRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="ClaveCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaveNueva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodOper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuitCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx713IdentificarCapPapRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", propOrder = {
    "claveCliente",
    "claveNueva",
    "codApp",
    "codOper",
    "cuitCliente"
})
public class Trx713IdentificarCapPapRequest
    extends RequestBase
{

    @XmlElementRef(name = "ClaveCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", type = JAXBElement.class)
    protected JAXBElement<String> claveCliente;
    @XmlElementRef(name = "ClaveNueva", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", type = JAXBElement.class)
    protected JAXBElement<String> claveNueva;
    @XmlElementRef(name = "CodApp", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", type = JAXBElement.class)
    protected JAXBElement<String> codApp;
    @XmlElementRef(name = "CodOper", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", type = JAXBElement.class)
    protected JAXBElement<String> codOper;
    @XmlElementRef(name = "CuitCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx713", type = JAXBElement.class)
    protected JAXBElement<String> cuitCliente;

    /**
     * Gets the value of the claveCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaveCliente() {
        return claveCliente;
    }

    /**
     * Sets the value of the claveCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaveCliente(JAXBElement<String> value) {
        this.claveCliente = value;
    }

    /**
     * Gets the value of the claveNueva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaveNueva() {
        return claveNueva;
    }

    /**
     * Sets the value of the claveNueva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaveNueva(JAXBElement<String> value) {
        this.claveNueva = value;
    }

    /**
     * Gets the value of the codApp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodApp() {
        return codApp;
    }

    /**
     * Sets the value of the codApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodApp(JAXBElement<String> value) {
        this.codApp = value;
    }

    /**
     * Gets the value of the codOper property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodOper() {
        return codOper;
    }

    /**
     * Sets the value of the codOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodOper(JAXBElement<String> value) {
        this.codOper = value;
    }

    /**
     * Gets the value of the cuitCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitCliente() {
        return cuitCliente;
    }

    /**
     * Sets the value of the cuitCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitCliente(JAXBElement<String> value) {
        this.cuitCliente = value;
    }

}
