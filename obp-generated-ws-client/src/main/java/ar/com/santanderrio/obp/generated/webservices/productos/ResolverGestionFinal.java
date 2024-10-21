
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
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRtaResolPredef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="infoRequerida" type="{http://webService.gestcli.ges.rio.com}ArrayOf_1587703434_32493182_nillable_InfoRequeridaWS"/>
 *         &lt;element name="ideGestionSectorAvance" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "comentario",
    "codRtaResolPredef",
    "infoRequerida",
    "ideGestionSectorAvance"
})
@XmlRootElement(name = "resolverGestionFinal")
public class ResolverGestionFinal {

    @XmlElement(required = true, nillable = true)
    protected String codUser;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionSector;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionNro;
    @XmlElement(required = true, nillable = true)
    protected String comentario;
    @XmlElement(required = true, nillable = true)
    protected String codRtaResolPredef;
    @XmlElement(required = true, nillable = true)
    protected ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida;
    @XmlElement(required = true, nillable = true)
    protected String ideGestionSectorAvance;

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
     * Gets the value of the ideGestionSectorAvance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdeGestionSectorAvance() {
        return ideGestionSectorAvance;
    }

    /**
     * Sets the value of the ideGestionSectorAvance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdeGestionSectorAvance(String value) {
        this.ideGestionSectorAvance = value;
    }

}
