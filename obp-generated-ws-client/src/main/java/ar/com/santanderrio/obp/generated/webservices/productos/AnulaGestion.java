
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ideGestionSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ideGestionNro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ideGestionSector",
    "ideGestionNro",
    "codUser",
    "comentario"
})
@XmlRootElement(name = "anulaGestion")
public class AnulaGestion {

    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String codUser;
    @XmlElement(required = true, nillable = true)
    protected String comentario;

    /**
     * Gets the value of the ideGestionSector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdeGestionSector() {
        return ideGestionSector;
    }

    /**
     * Sets the value of the ideGestionSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdeGestionSector(String value) {
        this.ideGestionSector = value;
    }

    /**
     * Gets the value of the ideGestionNro property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdeGestionNro() {
        return ideGestionNro;
    }

    /**
     * Sets the value of the ideGestionNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdeGestionNro(Integer value) {
        this.ideGestionNro = value;
    }

    /**
     * Gets the value of the codUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodUser() {
        return codUser;
    }

    /**
     * Sets the value of the codUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodUser(String value) {
        this.codUser = value;
    }

    /**
     * Gets the value of the comentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

}
