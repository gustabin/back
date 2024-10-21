
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baseGatewayMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseGatewayMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailSocio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseGatewayMessage", namespace = "http://visa.com.ar/visahome/integration/ws/mv/message", propOrder = {
    "tipoDocumento",
    "numeroDocumento",
    "sexo",
    "emailSocio"
})
@XmlSeeAlso({
    BaseGatewayRequest.class,
    BaseGatewayResponse.class
})
public class BaseGatewayMessage {

    @XmlElement(required = true)
    protected String tipoDocumento;
    @XmlElement(required = true)
    protected String numeroDocumento;
    @XmlElement(required = true)
    protected String sexo;
    @XmlElement(required = true)
    protected String emailSocio;

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the numeroDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Sets the value of the numeroDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the emailSocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSocio() {
        return emailSocio;
    }

    /**
     * Sets the value of the emailSocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSocio(String value) {
        this.emailSocio = value;
    }

	@Override
	public String toString() {
		return "BaseGatewayMessage [tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", sexo="
				+ sexo + ", emailSocio=" + emailSocio + "]";
	}

}
