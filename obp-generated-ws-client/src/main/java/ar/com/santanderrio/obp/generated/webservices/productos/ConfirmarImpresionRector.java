
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
 *         &lt;element name="cod_user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cod_entidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ide_gestion_sector" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ide_gestion_nro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cod_result_impresion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="desc_result_impresion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "codEntidad",
    "ideGestionSector",
    "ideGestionNro",
    "codResultImpresion",
    "descResultImpresion"
})
@XmlRootElement(name = "confirmarImpresionRector")
public class ConfirmarImpresionRector {

    @XmlElement(name = "cod_user", required = true, nillable = true)
    protected String codUser;
    @XmlElement(name = "cod_entidad", required = true, nillable = true)
    protected String codEntidad;
    @XmlElement(name = "ide_gestion_sector", required = true, nillable = true)
    protected String ideGestionSector;
    @XmlElement(name = "ide_gestion_nro")
    protected int ideGestionNro;
    @XmlElement(name = "cod_result_impresion")
    protected int codResultImpresion;
    @XmlElement(name = "desc_result_impresion", required = true, nillable = true)
    protected String descResultImpresion;

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
     * Gets the value of the codEntidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodEntidad() {
        return codEntidad;
    }

    /**
     * Sets the value of the codEntidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodEntidad(String value) {
        this.codEntidad = value;
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
     */
    public int getIdeGestionNro() {
        return ideGestionNro;
    }

    /**
     * Sets the value of the ideGestionNro property.
     * 
     */
    public void setIdeGestionNro(int value) {
        this.ideGestionNro = value;
    }

    /**
     * Gets the value of the codResultImpresion property.
     * 
     */
    public int getCodResultImpresion() {
        return codResultImpresion;
    }

    /**
     * Sets the value of the codResultImpresion property.
     * 
     */
    public void setCodResultImpresion(int value) {
        this.codResultImpresion = value;
    }

    /**
     * Gets the value of the descResultImpresion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescResultImpresion() {
        return descResultImpresion;
    }

    /**
     * Sets the value of the descResultImpresion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescResultImpresion(String value) {
        this.descResultImpresion = value;
    }

}
