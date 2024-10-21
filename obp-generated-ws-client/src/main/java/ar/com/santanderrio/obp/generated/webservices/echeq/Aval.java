
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Aval complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Aval">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aval_caracter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_importe_avalado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="aval_sujeto_avalado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_entidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aval_estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aval", propOrder = {
    "avalCaracter",
    "avalDomicilio",
    "avalDocumentoTipo",
    "avalDocumento",
    "avalImporteAvalado",
    "avalSujetoAvalado",
    "avalRazonSocial",
    "avalFecha",
    "avalEntidad",
    "avalEstado"
})
public class Aval {

    @XmlElement(name = "aval_caracter")
    protected String avalCaracter;
    @XmlElement(name = "aval_domicilio")
    protected String avalDomicilio;
    @XmlElement(name = "aval_documento_tipo")
    protected String avalDocumentoTipo;
    @XmlElement(name = "aval_documento")
    protected String avalDocumento;
    @XmlElement(name = "aval_importe_avalado")
    protected Double avalImporteAvalado;
    @XmlElement(name = "aval_sujeto_avalado")
    protected String avalSujetoAvalado;
    @XmlElement(name = "aval_razon_social")
    protected String avalRazonSocial;
    @XmlElement(name = "aval_fecha")
    protected String avalFecha;
    @XmlElement(name = "aval_entidad")
    protected String avalEntidad;
    @XmlElement(name = "aval_estado")
    protected String avalEstado;

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

    /**
     * Gets the value of the avalRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalRazonSocial() {
        return avalRazonSocial;
    }

    /**
     * Sets the value of the avalRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalRazonSocial(String value) {
        this.avalRazonSocial = value;
    }

    /**
     * Gets the value of the avalFecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalFecha() {
        return avalFecha;
    }

    /**
     * Sets the value of the avalFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalFecha(String value) {
        this.avalFecha = value;
    }

    /**
     * Gets the value of the avalEntidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalEntidad() {
        return avalEntidad;
    }

    /**
     * Sets the value of the avalEntidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalEntidad(String value) {
        this.avalEntidad = value;
    }

    /**
     * Gets the value of the avalEstado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvalEstado() {
        return avalEstado;
    }

    /**
     * Sets the value of the avalEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvalEstado(String value) {
        this.avalEstado = value;
    }

}
