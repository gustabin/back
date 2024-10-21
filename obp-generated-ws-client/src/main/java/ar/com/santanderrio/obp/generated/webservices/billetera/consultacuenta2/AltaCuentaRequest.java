
package ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for altaCuentaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="altaCuentaRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="banco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contrasenia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preguntaSeguridad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="respPregSeguridad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="genero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCalle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="piso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codPostal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefonoFijo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companiaCelular" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCelular" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aceptaTyC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="factorValidacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "altaCuentaRequest", propOrder = {
    "banco",
    "tipoCuenta",
    "mail",
    "contrasenia",
    "preguntaSeguridad",
    "respPregSeguridad",
    "tipoDocumento",
    "nroDocumento",
    "genero",
    "nombre",
    "apellido",
    "fechaNacimiento",
    "nacionalidad",
    "calle",
    "numeroCalle",
    "piso",
    "departamento",
    "codPostal",
    "provincia",
    "localidad",
    "telefonoFijo",
    "companiaCelular",
    "numeroCelular",
    "aceptaTyC",
    "factorValidacion",
    "canal"
})
public class AltaCuentaRequest {

    @XmlElement(required = true, nillable = true)
    protected String banco;
    @XmlElement(required = true, nillable = true)
    protected String tipoCuenta;
    @XmlElement(required = true, nillable = true)
    protected String mail;
    @XmlElement(required = true, nillable = true)
    protected String contrasenia;
    @XmlElement(required = true, nillable = true)
    protected String preguntaSeguridad;
    @XmlElement(required = true, nillable = true)
    protected String respPregSeguridad;
    @XmlElement(required = true, nillable = true)
    protected String tipoDocumento;
    @XmlElement(required = true, nillable = true)
    protected String nroDocumento;
    @XmlElement(required = true, nillable = true)
    protected String genero;
    @XmlElement(required = true, nillable = true)
    protected String nombre;
    @XmlElement(required = true, nillable = true)
    protected String apellido;
    @XmlElement(required = true, nillable = true)
    protected String fechaNacimiento;
    @XmlElement(required = true, nillable = true)
    protected String nacionalidad;
    @XmlElement(required = true, nillable = true)
    protected String calle;
    @XmlElement(required = true, nillable = true)
    protected String numeroCalle;
    @XmlElement(required = true, nillable = true)
    protected String piso;
    @XmlElement(required = true, nillable = true)
    protected String departamento;
    @XmlElement(required = true, nillable = true)
    protected String codPostal;
    @XmlElement(required = true, nillable = true)
    protected String provincia;
    @XmlElement(required = true, nillable = true)
    protected String localidad;
    @XmlElement(required = true, nillable = true)
    protected String telefonoFijo;
    @XmlElement(required = true, nillable = true)
    protected String companiaCelular;
    @XmlElement(required = true, nillable = true)
    protected String numeroCelular;
    @XmlElement(required = true, nillable = true)
    protected String aceptaTyC;
    @XmlElement(required = true, nillable = true)
    protected String factorValidacion;
    @XmlElement(required = true, nillable = true)
    protected String canal;

    /**
     * Gets the value of the banco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Sets the value of the banco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCuenta(String value) {
        this.tipoCuenta = value;
    }

    /**
     * Gets the value of the mail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the value of the mail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMail(String value) {
        this.mail = value;
    }

    /**
     * Gets the value of the contrasenia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Sets the value of the contrasenia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenia(String value) {
        this.contrasenia = value;
    }

    /**
     * Gets the value of the preguntaSeguridad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    /**
     * Sets the value of the preguntaSeguridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreguntaSeguridad(String value) {
        this.preguntaSeguridad = value;
    }

    /**
     * Gets the value of the respPregSeguridad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespPregSeguridad() {
        return respPregSeguridad;
    }

    /**
     * Sets the value of the respPregSeguridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespPregSeguridad(String value) {
        this.respPregSeguridad = value;
    }

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
     * Gets the value of the nroDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroDocumento() {
        return nroDocumento;
    }

    /**
     * Sets the value of the nroDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroDocumento(String value) {
        this.nroDocumento = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNacimiento(String value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the numeroCalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCalle() {
        return numeroCalle;
    }

    /**
     * Sets the value of the numeroCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCalle(String value) {
        this.numeroCalle = value;
    }

    /**
     * Gets the value of the piso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the value of the piso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPiso(String value) {
        this.piso = value;
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostal(String value) {
        this.codPostal = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the telefonoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Sets the value of the telefonoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoFijo(String value) {
        this.telefonoFijo = value;
    }

    /**
     * Gets the value of the companiaCelular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompaniaCelular() {
        return companiaCelular;
    }

    /**
     * Sets the value of the companiaCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompaniaCelular(String value) {
        this.companiaCelular = value;
    }

    /**
     * Gets the value of the numeroCelular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCelular() {
        return numeroCelular;
    }

    /**
     * Sets the value of the numeroCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCelular(String value) {
        this.numeroCelular = value;
    }

    /**
     * Gets the value of the aceptaTyC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAceptaTyC() {
        return aceptaTyC;
    }

    /**
     * Sets the value of the aceptaTyC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAceptaTyC(String value) {
        this.aceptaTyC = value;
    }

    /**
     * Gets the value of the factorValidacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactorValidacion() {
        return factorValidacion;
    }

    /**
     * Sets the value of the factorValidacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactorValidacion(String value) {
        this.factorValidacion = value;
    }

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

}
