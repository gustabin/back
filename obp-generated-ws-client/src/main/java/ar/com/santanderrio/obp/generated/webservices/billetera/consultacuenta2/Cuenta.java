
package ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cuenta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cuenta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCuentaTodoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esPrimeraVez" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preguntaSeguridad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companiaCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="piso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codPostal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefonoFijo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marcaCBU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monedaCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCBU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://billetera.prismamp.com.ar/billeteraCuenta}mediosDePago" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cuenta", propOrder = {
    "idCuenta",
    "tipoCuentaTodoPago",
    "esPrimeraVez",
    "mail",
    "preguntaSeguridad",
    "estadoMail",
    "companiaCelular",
    "numeroCelular",
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
    "marcaCBU",
    "tipoCuenta",
    "monedaCuenta",
    "numeroCuenta",
    "numeroCBU",
    "tipoAcreditacion",
    "mediosDePago"
})
public class Cuenta {

    @XmlElementRef(name = "idCuenta", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> idCuenta;
    @XmlElementRef(name = "tipoCuentaTodoPago", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaTodoPago;
    @XmlElementRef(name = "esPrimeraVez", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> esPrimeraVez;
    @XmlElementRef(name = "mail", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> mail;
    @XmlElementRef(name = "preguntaSeguridad", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> preguntaSeguridad;
    @XmlElementRef(name = "estadoMail", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> estadoMail;
    @XmlElementRef(name = "companiaCelular", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> companiaCelular;
    @XmlElementRef(name = "numeroCelular", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> numeroCelular;
    @XmlElementRef(name = "nombre", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> nombre;
    @XmlElementRef(name = "apellido", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> apellido;
    @XmlElementRef(name = "fechaNacimiento", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "nacionalidad", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> nacionalidad;
    @XmlElementRef(name = "calle", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> calle;
    @XmlElementRef(name = "numeroCalle", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> numeroCalle;
    @XmlElementRef(name = "piso", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> piso;
    @XmlElementRef(name = "departamento", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> departamento;
    @XmlElementRef(name = "codPostal", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> codPostal;
    @XmlElementRef(name = "provincia", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> provincia;
    @XmlElementRef(name = "localidad", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> localidad;
    @XmlElementRef(name = "telefonoFijo", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> telefonoFijo;
    @XmlElementRef(name = "marcaCBU", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> marcaCBU;
    @XmlElementRef(name = "tipoCuenta", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;
    @XmlElementRef(name = "monedaCuenta", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> monedaCuenta;
    @XmlElementRef(name = "numeroCuenta", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuenta;
    @XmlElementRef(name = "numeroCBU", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> numeroCBU;
    @XmlElementRef(name = "tipoAcreditacion", namespace = "http://billetera.prismamp.com.ar/billeteraCuenta", type = JAXBElement.class)
    protected JAXBElement<String> tipoAcreditacion;
    protected List<MedioDePago> mediosDePago;

    /**
     * Gets the value of the idCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCuenta() {
        return idCuenta;
    }

    /**
     * Sets the value of the idCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCuenta(JAXBElement<String> value) {
        this.idCuenta = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tipoCuentaTodoPago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaTodoPago() {
        return tipoCuentaTodoPago;
    }

    /**
     * Sets the value of the tipoCuentaTodoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaTodoPago(JAXBElement<String> value) {
        this.tipoCuentaTodoPago = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the esPrimeraVez property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEsPrimeraVez() {
        return esPrimeraVez;
    }

    /**
     * Sets the value of the esPrimeraVez property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEsPrimeraVez(JAXBElement<String> value) {
        this.esPrimeraVez = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMail() {
        return mail;
    }

    /**
     * Sets the value of the mail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMail(JAXBElement<String> value) {
        this.mail = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the preguntaSeguridad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    /**
     * Sets the value of the preguntaSeguridad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreguntaSeguridad(JAXBElement<String> value) {
        this.preguntaSeguridad = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the estadoMail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoMail() {
        return estadoMail;
    }

    /**
     * Sets the value of the estadoMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoMail(JAXBElement<String> value) {
        this.estadoMail = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the companiaCelular property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCompaniaCelular() {
        return companiaCelular;
    }

    /**
     * Sets the value of the companiaCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCompaniaCelular(JAXBElement<String> value) {
        this.companiaCelular = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the numeroCelular property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCelular() {
        return numeroCelular;
    }

    /**
     * Sets the value of the numeroCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCelular(JAXBElement<String> value) {
        this.numeroCelular = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombre(JAXBElement<String> value) {
        this.nombre = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setApellido(JAXBElement<String> value) {
        this.apellido = ((JAXBElement<String> ) value);
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
        this.fechaNacimiento = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNacionalidad(JAXBElement<String> value) {
        this.nacionalidad = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the calle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCalle(JAXBElement<String> value) {
        this.calle = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the numeroCalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCalle() {
        return numeroCalle;
    }

    /**
     * Sets the value of the numeroCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCalle(JAXBElement<String> value) {
        this.numeroCalle = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the piso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPiso() {
        return piso;
    }

    /**
     * Sets the value of the piso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPiso(JAXBElement<String> value) {
        this.piso = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the departamento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepartamento() {
        return departamento;
    }

    /**
     * Sets the value of the departamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepartamento(JAXBElement<String> value) {
        this.departamento = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodPostal(JAXBElement<String> value) {
        this.codPostal = ((JAXBElement<String> ) value);
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
        this.provincia = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidad(JAXBElement<String> value) {
        this.localidad = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the telefonoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Sets the value of the telefonoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelefonoFijo(JAXBElement<String> value) {
        this.telefonoFijo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the marcaCBU property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaCBU() {
        return marcaCBU;
    }

    /**
     * Sets the value of the marcaCBU property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaCBU(JAXBElement<String> value) {
        this.marcaCBU = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuenta(JAXBElement<String> value) {
        this.tipoCuenta = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the monedaCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaCuenta() {
        return monedaCuenta;
    }

    /**
     * Sets the value of the monedaCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaCuenta(JAXBElement<String> value) {
        this.monedaCuenta = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the numeroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the value of the numeroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuenta(JAXBElement<String> value) {
        this.numeroCuenta = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the numeroCBU property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCBU() {
        return numeroCBU;
    }

    /**
     * Sets the value of the numeroCBU property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCBU(JAXBElement<String> value) {
        this.numeroCBU = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tipoAcreditacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoAcreditacion() {
        return tipoAcreditacion;
    }

    /**
     * Sets the value of the tipoAcreditacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoAcreditacion(JAXBElement<String> value) {
        this.tipoAcreditacion = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the mediosDePago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediosDePago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediosDePago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedioDePago }
     * 
     * 
     */
    public List<MedioDePago> getMediosDePago() {
        if (mediosDePago == null) {
            mediosDePago = new ArrayList<MedioDePago>();
        }
        return this.mediosDePago;
    }

}
