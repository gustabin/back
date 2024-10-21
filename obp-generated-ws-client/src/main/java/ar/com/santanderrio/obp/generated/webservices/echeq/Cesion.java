
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Cesion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Cesion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cesion_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado_cesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cedente_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cedente_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cedente_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cesionario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cesionario_documento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cesionario_nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cesionario_domicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_emision_cesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_ult_modificacion_cesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cesion_motivo_repudio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidad_gestora_responsable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidad_responsable_admision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cesion", propOrder = {
    "cesionId",
    "estadoCesion",
    "cedenteDocumentoTipo",
    "cedenteDocumento",
    "cedenteNombre",
    "cesionarioDocumentoTipo",
    "cesionarioDocumento",
    "cesionarioNombre",
    "cesionarioDomicilio",
    "fechaEmisionCesion",
    "fechaUltModificacionCesion",
    "cesionMotivoRepudio",
    "entidadGestoraResponsable",
    "entidadResponsableAdmision",
    "intchequeId",
    "chequeNumero",
    "monto"
})
public class Cesion {

    @XmlElement(name = "cesion_id")
    protected String cesionId;
    @XmlElement(name = "estado_cesion")
    protected String estadoCesion;
    @XmlElement(name = "cedente_documento_tipo")
    protected String cedenteDocumentoTipo;
    @XmlElement(name = "cedente_documento")
    protected String cedenteDocumento;
    @XmlElement(name = "cedente_nombre")
    protected String cedenteNombre;
    @XmlElement(name = "cesionario_documento_tipo")
    protected String cesionarioDocumentoTipo;
    @XmlElement(name = "cesionario_documento")
    protected Double cesionarioDocumento;
    @XmlElement(name = "cesionario_nombre")
    protected String cesionarioNombre;
    @XmlElement(name = "cesionario_domicilio")
    protected String cesionarioDomicilio;
    @XmlElement(name = "fecha_emision_cesion")
    protected String fechaEmisionCesion;
    @XmlElement(name = "fecha_ult_modificacion_cesion")
    protected String fechaUltModificacionCesion;
    @XmlElement(name = "cesion_motivo_repudio")
    protected String cesionMotivoRepudio;
    @XmlElement(name = "entidad_gestora_responsable")
    protected String entidadGestoraResponsable;
    @XmlElement(name = "entidad_responsable_admision")
    protected String entidadResponsableAdmision;
    @XmlElement(name = "intcheque_id")
    protected String intchequeId;
    @XmlElement(name = "cheque_numero")
    protected String chequeNumero;
    protected String monto;

    /**
     * Gets the value of the cesionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionId() {
        return cesionId;
    }

    /**
     * Sets the value of the cesionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionId(String value) {
        this.cesionId = value;
    }

    /**
     * Gets the value of the estadoCesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoCesion() {
        return estadoCesion;
    }

    /**
     * Sets the value of the estadoCesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoCesion(String value) {
        this.estadoCesion = value;
    }

    /**
     * Gets the value of the cedenteDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedenteDocumentoTipo() {
        return cedenteDocumentoTipo;
    }

    /**
     * Sets the value of the cedenteDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedenteDocumentoTipo(String value) {
        this.cedenteDocumentoTipo = value;
    }

    /**
     * Gets the value of the cedenteDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedenteDocumento() {
        return cedenteDocumento;
    }

    /**
     * Sets the value of the cedenteDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedenteDocumento(String value) {
        this.cedenteDocumento = value;
    }

    /**
     * Gets the value of the cedenteNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedenteNombre() {
        return cedenteNombre;
    }

    /**
     * Sets the value of the cedenteNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedenteNombre(String value) {
        this.cedenteNombre = value;
    }

    /**
     * Gets the value of the cesionarioDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioDocumentoTipo() {
        return cesionarioDocumentoTipo;
    }

    /**
     * Sets the value of the cesionarioDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioDocumentoTipo(String value) {
        this.cesionarioDocumentoTipo = value;
    }

    /**
     * Gets the value of the cesionarioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCesionarioDocumento() {
        return cesionarioDocumento;
    }

    /**
     * Sets the value of the cesionarioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCesionarioDocumento(Double value) {
        this.cesionarioDocumento = value;
    }

    /**
     * Gets the value of the cesionarioNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioNombre() {
        return cesionarioNombre;
    }

    /**
     * Sets the value of the cesionarioNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioNombre(String value) {
        this.cesionarioNombre = value;
    }

    /**
     * Gets the value of the cesionarioDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioDomicilio() {
        return cesionarioDomicilio;
    }

    /**
     * Sets the value of the cesionarioDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioDomicilio(String value) {
        this.cesionarioDomicilio = value;
    }

    /**
     * Gets the value of the fechaEmisionCesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmisionCesion() {
        return fechaEmisionCesion;
    }

    /**
     * Sets the value of the fechaEmisionCesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmisionCesion(String value) {
        this.fechaEmisionCesion = value;
    }

    /**
     * Gets the value of the fechaUltModificacionCesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaUltModificacionCesion() {
        return fechaUltModificacionCesion;
    }

    /**
     * Sets the value of the fechaUltModificacionCesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaUltModificacionCesion(String value) {
        this.fechaUltModificacionCesion = value;
    }

    /**
     * Gets the value of the cesionMotivoRepudio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionMotivoRepudio() {
        return cesionMotivoRepudio;
    }

    /**
     * Sets the value of the cesionMotivoRepudio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionMotivoRepudio(String value) {
        this.cesionMotivoRepudio = value;
    }

    /**
     * Gets the value of the entidadGestoraResponsable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadGestoraResponsable() {
        return entidadGestoraResponsable;
    }

    /**
     * Sets the value of the entidadGestoraResponsable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadGestoraResponsable(String value) {
        this.entidadGestoraResponsable = value;
    }

    /**
     * Gets the value of the entidadResponsableAdmision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadResponsableAdmision() {
        return entidadResponsableAdmision;
    }

    /**
     * Sets the value of the entidadResponsableAdmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadResponsableAdmision(String value) {
        this.entidadResponsableAdmision = value;
    }

    /**
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the chequeNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeNumero() {
        return chequeNumero;
    }

    /**
     * Sets the value of the chequeNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeNumero(String value) {
        this.chequeNumero = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonto(String value) {
        this.monto = value;
    }

}
