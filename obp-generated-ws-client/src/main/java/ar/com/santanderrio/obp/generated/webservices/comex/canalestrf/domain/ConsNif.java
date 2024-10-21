
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsNif complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsNif">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Activo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clave_Identificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Op_Pendiente" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Cuit_Rep_Legal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Desc_Tipo_Doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Alta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Id_Estado_Nif" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Nacionalidad_Actual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Numero_Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Nacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Que_Expide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Residencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Representante_Legal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Residencia_Tribut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Persona" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsNif", propOrder = {
    "activo",
    "ciudad",
    "claveIdentificacion",
    "conOpPendiente",
    "cuitRepLegal",
    "descTipoDoc",
    "direccion",
    "fechaAlta",
    "fechaModif",
    "fechaNacimiento",
    "idBeneficiario",
    "idEstadoNif",
    "nacionalidadActual",
    "nif",
    "nombreBeneficiario",
    "numeroDocumento",
    "paisNacimiento",
    "paisQueExpide",
    "paisResidencia",
    "provincia",
    "representanteLegal",
    "residenciaTribut",
    "tipoDocumento",
    "tipoPersona"
})
public class ConsNif {

    @XmlElementRef(name = "Activo", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> activo;
    @XmlElementRef(name = "Ciudad", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> ciudad;
    @XmlElementRef(name = "Clave_Identificacion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> claveIdentificacion;
    @XmlElement(name = "Con_Op_Pendiente")
    protected BigDecimal conOpPendiente;
    @XmlElementRef(name = "Cuit_Rep_Legal", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> cuitRepLegal;
    @XmlElementRef(name = "Desc_Tipo_Doc", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> descTipoDoc;
    @XmlElementRef(name = "Direccion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> direccion;
    @XmlElementRef(name = "Fecha_Alta", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaAlta;
    @XmlElementRef(name = "Fecha_Modif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaModif;
    @XmlElementRef(name = "Fecha_Nacimiento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "Id_Beneficiario", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> idBeneficiario;
    @XmlElementRef(name = "Id_Estado_Nif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> idEstadoNif;
    @XmlElementRef(name = "Nacionalidad_Actual", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nacionalidadActual;
    @XmlElementRef(name = "Nif", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nif;
    @XmlElementRef(name = "Nombre_Beneficiario", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nombreBeneficiario;
    @XmlElementRef(name = "Numero_Documento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> numeroDocumento;
    @XmlElementRef(name = "Pais_Nacimiento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisNacimiento;
    @XmlElementRef(name = "Pais_Que_Expide", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisQueExpide;
    @XmlElementRef(name = "Pais_Residencia", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisResidencia;
    @XmlElementRef(name = "Provincia", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> provincia;
    @XmlElementRef(name = "Representante_Legal", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> representanteLegal;
    @XmlElementRef(name = "Residencia_Tribut", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> residenciaTribut;
    @XmlElementRef(name = "Tipo_Documento", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocumento;
    @XmlElementRef(name = "Tipo_Persona", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> tipoPersona;

    /**
     * Gets the value of the activo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivo() {
        return activo;
    }

    /**
     * Sets the value of the activo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivo(JAXBElement<String> value) {
        this.activo = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCiudad(JAXBElement<String> value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the claveIdentificacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClaveIdentificacion() {
        return claveIdentificacion;
    }

    /**
     * Sets the value of the claveIdentificacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClaveIdentificacion(JAXBElement<String> value) {
        this.claveIdentificacion = value;
    }

    /**
     * Gets the value of the conOpPendiente property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConOpPendiente() {
        return conOpPendiente;
    }

    /**
     * Sets the value of the conOpPendiente property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConOpPendiente(BigDecimal value) {
        this.conOpPendiente = value;
    }

    /**
     * Gets the value of the cuitRepLegal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitRepLegal() {
        return cuitRepLegal;
    }

    /**
     * Sets the value of the cuitRepLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitRepLegal(JAXBElement<String> value) {
        this.cuitRepLegal = value;
    }

    /**
     * Gets the value of the descTipoDoc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescTipoDoc() {
        return descTipoDoc;
    }

    /**
     * Sets the value of the descTipoDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescTipoDoc(JAXBElement<String> value) {
        this.descTipoDoc = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDireccion(JAXBElement<String> value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaAlta(JAXBElement<String> value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaModif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaModif() {
        return fechaModif;
    }

    /**
     * Sets the value of the fechaModif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaModif(JAXBElement<String> value) {
        this.fechaModif = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaNacimiento(JAXBElement<String> value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the idBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * Sets the value of the idBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setIdBeneficiario(JAXBElement<BigDecimal> value) {
        this.idBeneficiario = value;
    }

    /**
     * Gets the value of the idEstadoNif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getIdEstadoNif() {
        return idEstadoNif;
    }

    /**
     * Sets the value of the idEstadoNif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setIdEstadoNif(JAXBElement<BigDecimal> value) {
        this.idEstadoNif = value;
    }

    /**
     * Gets the value of the nacionalidadActual property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNacionalidadActual() {
        return nacionalidadActual;
    }

    /**
     * Sets the value of the nacionalidadActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNacionalidadActual(JAXBElement<String> value) {
        this.nacionalidadActual = value;
    }

    /**
     * Gets the value of the nif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNif() {
        return nif;
    }

    /**
     * Sets the value of the nif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNif(JAXBElement<String> value) {
        this.nif = value;
    }

    /**
     * Gets the value of the nombreBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    /**
     * Sets the value of the nombreBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBeneficiario(JAXBElement<String> value) {
        this.nombreBeneficiario = value;
    }

    /**
     * Gets the value of the numeroDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Sets the value of the numeroDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroDocumento(JAXBElement<String> value) {
        this.numeroDocumento = value;
    }

    /**
     * Gets the value of the paisNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Sets the value of the paisNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisNacimiento(JAXBElement<String> value) {
        this.paisNacimiento = value;
    }

    /**
     * Gets the value of the paisQueExpide property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisQueExpide() {
        return paisQueExpide;
    }

    /**
     * Sets the value of the paisQueExpide property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisQueExpide(JAXBElement<String> value) {
        this.paisQueExpide = value;
    }

    /**
     * Gets the value of the paisResidencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisResidencia() {
        return paisResidencia;
    }

    /**
     * Sets the value of the paisResidencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisResidencia(JAXBElement<String> value) {
        this.paisResidencia = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProvincia(JAXBElement<String> value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the representanteLegal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRepresentanteLegal() {
        return representanteLegal;
    }

    /**
     * Sets the value of the representanteLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRepresentanteLegal(JAXBElement<String> value) {
        this.representanteLegal = value;
    }

    /**
     * Gets the value of the residenciaTribut property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResidenciaTribut() {
        return residenciaTribut;
    }

    /**
     * Sets the value of the residenciaTribut property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResidenciaTribut(JAXBElement<String> value) {
        this.residenciaTribut = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocumento(JAXBElement<String> value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setTipoPersona(JAXBElement<BigDecimal> value) {
        this.tipoPersona = value;
    }

}
