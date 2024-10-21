
package ar.com.santanderrio.obp.generated.webservices.crm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubRequestCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubRequestCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codAlerta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveUnica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estGestion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubRequestCliente", propOrder = {
    "codAlerta",
    "claveUnica",
    "usuario",
    "estGestion"
})
public class SubRequestCliente {

    protected String codAlerta;
    protected String claveUnica;
    protected String usuario;
    protected String estGestion;

    /**
     * Gets the value of the codAlerta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAlerta() {
        return codAlerta;
    }

    /**
     * Sets the value of the codAlerta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAlerta(String value) {
        this.codAlerta = value;
    }

    /**
     * Gets the value of the claveUnica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveUnica() {
        return claveUnica;
    }

    /**
     * Sets the value of the claveUnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveUnica(String value) {
        this.claveUnica = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the estGestion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstGestion() {
        return estGestion;
    }

    /**
     * Sets the value of the estGestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstGestion(String value) {
        this.estGestion = value;
    }

}
