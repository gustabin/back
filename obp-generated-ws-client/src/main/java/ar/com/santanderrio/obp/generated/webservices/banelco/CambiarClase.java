
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
 *         &lt;element name="in2" type="{http://model.webservices.banelco.com}WSTarjetaDTO"/>
 *         &lt;element name="in3" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "in3"
})
@XmlRootElement(name = "cambiarClase")
public class CambiarClase {

    @XmlElement(required = true, nillable = true)
    protected WSTerminalDTO in0;
    @XmlElement(required = true, nillable = true)
    protected WSUserData in1;
    @XmlElement(required = true, nillable = true)
    protected WSTarjetaDTO in2;
    @XmlElement(required = true, nillable = true)
    protected String in3;

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
     *     {@link WSTarjetaDTO }
     *     
     */
    public WSTarjetaDTO getIn2() {
        return in2;
    }

    /**
     * Define el valor de la propiedad in2.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTarjetaDTO }
     *     
     */
    public void setIn2(WSTarjetaDTO value) {
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

}
