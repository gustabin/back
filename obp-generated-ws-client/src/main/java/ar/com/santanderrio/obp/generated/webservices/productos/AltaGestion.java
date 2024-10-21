
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
 *         &lt;element name="codAsociacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoPersona" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nup" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="medioIngreso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="infoRequerida" type="{http://webService.gestcli.ges.rio.com}ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS"/>
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
    "codAsociacion",
    "tipoPersona",
    "nup",
    "codSector",
    "codUser",
    "medioIngreso",
    "comentario",
    "infoRequerida"
})
@XmlRootElement(name = "altaGestion")
public class AltaGestion {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codAsociacion;
    @XmlElement(required = true, nillable = true)
    protected String tipoPersona;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer nup;
    @XmlElement(required = true, nillable = true)
    protected String codSector;
    @XmlElement(required = true, nillable = true)
    protected String codUser;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer medioIngreso;
    @XmlElement(required = true, nillable = true)
    protected String comentario;
    @XmlElement(required = true, nillable = true)
    protected ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida;

    /**
     * Gets the value of the codAsociacion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodAsociacion() {
        return codAsociacion;
    }

    /**
     * Sets the value of the codAsociacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodAsociacion(Integer value) {
        this.codAsociacion = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPersona(String value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNup(Integer value) {
        this.nup = value;
    }

    /**
     * Gets the value of the codSector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSector() {
        return codSector;
    }

    /**
     * Sets the value of the codSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSector(String value) {
        this.codSector = value;
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
     * Gets the value of the medioIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMedioIngreso() {
        return medioIngreso;
    }

    /**
     * Sets the value of the medioIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMedioIngreso(Integer value) {
        this.medioIngreso = value;
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

    /**
     * Gets the value of the infoRequerida property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOf158770343432493182NillableInfoRequeridaWS }
     *     
     */
    public ArrayOf158770343432493182NillableInfoRequeridaWS getInfoRequerida() {
        return infoRequerida;
    }

    /**
     * Sets the value of the infoRequerida property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOf158770343432493182NillableInfoRequeridaWS }
     *     
     */
    public void setInfoRequerida(ArrayOf158770343432493182NillableInfoRequeridaWS value) {
        this.infoRequerida = value;
    }

}
