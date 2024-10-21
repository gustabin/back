
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TagAval complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TagAval">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aval_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_caracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_importe_avalado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="aval_sujeto_avalado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TagAval", propOrder = {
    "avalDocumentoTipo",
    "avalDocumento",
    "avalNombre",
    "avalCaracter",
    "avalDomicilio",
    "avalImporteAvalado",
    "avalSujetoAvalado"
})
public class TagAval {

    @XmlElement(name = "aval_documento_tipo")
    protected String avalDocumentoTipo;
    @XmlElement(name = "aval_documento")
    protected String avalDocumento;
    @XmlElement(name = "aval_nombre")
    protected String avalNombre;
    @XmlElement(name = "aval_caracter")
    protected String avalCaracter;
    @XmlElement(name = "aval_domicilio")
    protected String avalDomicilio;
    @XmlElement(name = "aval_importe_avalado")
    protected Double avalImporteAvalado;
    @XmlElement(name = "aval_sujeto_avalado")
    protected String avalSujetoAvalado;

    /**
     * Gets the value of the avalDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalDocumentoTipo() {
        return avalDocumentoTipo;
    }

    /**
     * Sets the value of the avalDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalDocumentoTipo(String value) {
        this.avalDocumentoTipo = value;
    }

    /**
     * Gets the value of the avalDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalDocumento() {
        return avalDocumento;
    }

    /**
     * Sets the value of the avalDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalDocumento(String value) {
        this.avalDocumento = value;
    }

    /**
     * Gets the value of the avalNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalNombre() {
        return avalNombre;
    }

    /**
     * Sets the value of the avalNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalNombre(String value) {
        this.avalNombre = value;
    }

    /**
     * Gets the value of the avalCaracter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalCaracter() {
        return avalCaracter;
    }

    /**
     * Sets the value of the avalCaracter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalCaracter(String value) {
        this.avalCaracter = value;
    }

    /**
     * Gets the value of the avalDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalDomicilio() {
        return avalDomicilio;
    }

    /**
     * Sets the value of the avalDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalDomicilio(String value) {
        this.avalDomicilio = value;
    }

    /**
     * Gets the value of the avalImporteAvalado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAvalImporteAvalado() {
        return avalImporteAvalado;
    }

    /**
     * Sets the value of the avalImporteAvalado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAvalImporteAvalado(Double value) {
        this.avalImporteAvalado = value;
    }

    /**
     * Gets the value of the avalSujetoAvalado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalSujetoAvalado() {
        return avalSujetoAvalado;
    }

    /**
     * Sets the value of the avalSujetoAvalado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalSujetoAvalado(String value) {
        this.avalSujetoAvalado = value;
    }

}
