
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Endoso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Endoso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha_hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_endoso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivo_repudio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado_endoso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="benef_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="benef_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="benef_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emisor_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencias_pago" type="{http://echeq.amco.com.ar/}Referencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Endoso", propOrder = {
    "fechaHora",
    "documentoTipo",
    "documento",
    "tipoEndoso",
    "motivoRepudio",
    "estadoEndoso",
    "benefDocumento",
    "benefDocumentoTipo",
    "benefRazonSocial",
    "emisorDocumento",
    "emisorDocumentoTipo",
    "emisorRazonSocial",
    "referenciasPago"
})
public class Endoso {

    @XmlElement(name = "fecha_hora")
    protected String fechaHora;
    @XmlElement(name = "documento_tipo")
    protected String documentoTipo;
    protected String documento;
    @XmlElement(name = "tipo_endoso")
    protected String tipoEndoso;
    @XmlElement(name = "motivo_repudio")
    protected String motivoRepudio;
    @XmlElement(name = "estado_endoso")
    protected String estadoEndoso;
    @XmlElement(name = "benef_documento")
    protected String benefDocumento;
    @XmlElement(name = "benef_documento_tipo")
    protected String benefDocumentoTipo;
    @XmlElement(name = "benef_razon_social")
    protected String benefRazonSocial;
    @XmlElement(name = "emisor_documento")
    protected String emisorDocumento;
    @XmlElement(name = "emisor_documento_tipo")
    protected String emisorDocumentoTipo;
    @XmlElement(name = "emisor_razon_social")
    protected String emisorRazonSocial;
    @XmlElement(name = "referencias_pago")
    protected List<Referencia> referenciasPago;

    /**
     * Gets the value of the fechaHora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the value of the fechaHora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaHora(String value) {
        this.fechaHora = value;
    }

    /**
     * Gets the value of the documentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoTipo() {
        return documentoTipo;
    }

    /**
     * Sets the value of the documentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoTipo(String value) {
        this.documentoTipo = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumento(String value) {
        this.documento = value;
    }

    /**
     * Gets the value of the tipoEndoso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoEndoso() {
        return tipoEndoso;
    }

    /**
     * Sets the value of the tipoEndoso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoEndoso(String value) {
        this.tipoEndoso = value;
    }

    /**
     * Gets the value of the motivoRepudio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoRepudio() {
        return motivoRepudio;
    }

    /**
     * Sets the value of the motivoRepudio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoRepudio(String value) {
        this.motivoRepudio = value;
    }

    /**
     * Gets the value of the estadoEndoso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoEndoso() {
        return estadoEndoso;
    }

    /**
     * Sets the value of the estadoEndoso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoEndoso(String value) {
        this.estadoEndoso = value;
    }

    /**
     * Gets the value of the benefDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefDocumento() {
        return benefDocumento;
    }

    /**
     * Sets the value of the benefDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefDocumento(String value) {
        this.benefDocumento = value;
    }

    /**
     * Gets the value of the benefDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefDocumentoTipo() {
        return benefDocumentoTipo;
    }

    /**
     * Sets the value of the benefDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefDocumentoTipo(String value) {
        this.benefDocumentoTipo = value;
    }

    /**
     * Gets the value of the benefRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenefRazonSocial() {
        return benefRazonSocial;
    }

    /**
     * Sets the value of the benefRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenefRazonSocial(String value) {
        this.benefRazonSocial = value;
    }

    /**
     * Gets the value of the emisorDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorDocumento() {
        return emisorDocumento;
    }

    /**
     * Sets the value of the emisorDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorDocumento(String value) {
        this.emisorDocumento = value;
    }

    /**
     * Gets the value of the emisorDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorDocumentoTipo() {
        return emisorDocumentoTipo;
    }

    /**
     * Sets the value of the emisorDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorDocumentoTipo(String value) {
        this.emisorDocumentoTipo = value;
    }

    /**
     * Gets the value of the emisorRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmisorRazonSocial() {
        return emisorRazonSocial;
    }

    /**
     * Sets the value of the emisorRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmisorRazonSocial(String value) {
        this.emisorRazonSocial = value;
    }

    /**
     * Gets the value of the referenciasPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenciasPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenciasPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Referencia }
     * 
     * 
     */
    public List<Referencia> getReferenciasPago() {
        if (referenciasPago == null) {
            referenciasPago = new ArrayList<Referencia>();
        }
        return this.referenciasPago;
    }

}
