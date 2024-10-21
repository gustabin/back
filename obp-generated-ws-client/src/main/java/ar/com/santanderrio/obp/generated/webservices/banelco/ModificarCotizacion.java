
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://model.webservices.banelco.com}WSTerminalDTO"/>
 *         &lt;element name="in1" type="{http://model.webservices.banelco.com}WSUserData"/>
 *         &lt;element name="in2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="in3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="in4" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="in5" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "in0",
    "in1",
    "in2",
    "in3",
    "in4",
    "in5"
})
@XmlRootElement(name = "modificarCotizacion")
public class ModificarCotizacion {

    @XmlElement(required = true, nillable = true)
    protected WSTerminalDTO in0;
    @XmlElement(required = true, nillable = true)
    protected WSUserData in1;
    @XmlElement(required = true, nillable = true)
    protected String in2;
    @XmlElement(required = true, nillable = true)
    protected String in3;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double in4;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double in5;

    /**
     * Obtiene el valor de la propiedad in0.
     * 
     * @return
     *     possible object is
     *     {@link WSTerminalDTO }
     *     
     */
    public WSTerminalDTO getIn0() {
        return in0;
    }

    /**
     * Define el valor de la propiedad in0.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTerminalDTO }
     *     
     */
    public void setIn0(WSTerminalDTO value) {
        this.in0 = value;
    }

    /**
     * Obtiene el valor de la propiedad in1.
     * 
     * @return
     *     possible object is
     *     {@link WSUserData }
     *     
     */
    public WSUserData getIn1() {
        return in1;
    }

    /**
     * Define el valor de la propiedad in1.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUserData }
     *     
     */
    public void setIn1(WSUserData value) {
        this.in1 = value;
    }

    /**
     * Obtiene el valor de la propiedad in2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIn2() {
        return in2;
    }

    /**
     * Define el valor de la propiedad in2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIn2(String value) {
        this.in2 = value;
    }

    /**
     * Obtiene el valor de la propiedad in3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIn3() {
        return in3;
    }

    /**
     * Define el valor de la propiedad in3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIn3(String value) {
        this.in3 = value;
    }

    /**
     * Obtiene el valor de la propiedad in4.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIn4() {
        return in4;
    }

    /**
     * Define el valor de la propiedad in4.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIn4(Double value) {
        this.in4 = value;
    }

    /**
     * Obtiene el valor de la propiedad in5.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIn5() {
        return in5;
    }

    /**
     * Define el valor de la propiedad in5.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIn5(Double value) {
        this.in5 = value;
    }

}
