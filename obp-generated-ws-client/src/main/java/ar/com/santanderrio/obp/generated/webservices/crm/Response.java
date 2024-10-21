
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codRet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", propOrder = {
    "codRet",
    "idSist",
    "descError",
    "detError"
})
@XmlSeeAlso({
    ResponseCliente.class,
    ResponseGrupal.class,
    SubResponseGrupal.class
})
public abstract class Response {

    protected String codRet;
    protected String idSist;
    protected String descError;
    protected String detError;

    /**
     * Gets the value of the codRet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRet() {
        return codRet;
    }

    /**
     * Sets the value of the codRet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRet(String value) {
        this.codRet = value;
    }

    /**
     * Gets the value of the idSist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSist() {
        return idSist;
    }

    /**
     * Sets the value of the idSist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSist(String value) {
        this.idSist = value;
    }

    /**
     * Gets the value of the descError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescError() {
        return descError;
    }

    /**
     * Sets the value of the descError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescError(String value) {
        this.descError = value;
    }

    /**
     * Gets the value of the detError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetError() {
        return detError;
    }

    /**
     * Sets the value of the detError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetError(String value) {
        this.detError = value;
    }

}
