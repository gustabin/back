
package ar.com.santanderrio.obp.generated.webservices.productos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetornoListaInfoAdjWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RetornoListaInfoAdjWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="infosAdjData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetornoListaInfoAdjWS", namespace = "http://webService.core.ges.rio.com", propOrder = {
    "codigo",
    "descripcion",
    "infosAdjData"
})
public class RetornoListaInfoAdjWS {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codigo;
    @XmlElement(required = true, nillable = true)
    protected String descripcion;
    @XmlElement(required = true, nillable = true)
    protected String infosAdjData;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the infosAdjData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfosAdjData() {
        return infosAdjData;
    }

    /**
     * Sets the value of the infosAdjData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfosAdjData(String value) {
        this.infosAdjData = value;
    }

}
