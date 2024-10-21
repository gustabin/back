
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
 *         &lt;element name="codUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ideGestionSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ideGestionNro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRtaResolPredef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="infoRequerida" type="{http://webService.gestcli.ges.rio.com}ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codSector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroOrdenActor" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "codUser",
    "ideGestionSector",
    "ideGestionNro",
    "accion",
    "comentario",
    "codRtaResolPredef",
    "infoRequerida",
    "monto",
    "moneda",
    "codSector",
    "nroOrdenActor"
})
@XmlRootElement(name = "avanzarGestionConMonto")
public class AvanzarGestionConMonto {

    @XmlElement(required = true, nillable = true)
    protected String codUser;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String accion;
    @XmlElement(required = true, nillable = true)
    protected String comentario;
    @XmlElement(required = true, nillable = true)
    protected String codRtaResolPredef;
    @XmlElement(required = true, nillable = true)
    protected ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida;
    @XmlElement(required = true, nillable = true)
    protected String monto;
    @XmlElement(required = true, nillable = true)
    protected String moneda;
    @XmlElement(required = true, nillable = true)
    protected String codSector;
    @XmlElement(required = true, nillable = true)
    protected String nroOrdenActor;

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
     *     {@link String }
     *     
     */
    public String getIdeGestionNro() {
        return ideGestionNro;
    }

    /**
     * Sets the value of the ideGestionNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdeGestionNro(String value) {
        this.ideGestionNro = value;
    }

    /**
     * Gets the value of the accion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Sets the value of the accion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccion(String value) {
        this.accion = value;
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
     * Gets the value of the codRtaResolPredef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRtaResolPredef() {
        return codRtaResolPredef;
    }

    /**
     * Sets the value of the codRtaResolPredef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRtaResolPredef(String value) {
        this.codRtaResolPredef = value;
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

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
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
     * Gets the value of the nroOrdenActor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroOrdenActor() {
        return nroOrdenActor;
    }

    /**
     * Sets the value of the nroOrdenActor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroOrdenActor(String value) {
        this.nroOrdenActor = value;
    }

}
