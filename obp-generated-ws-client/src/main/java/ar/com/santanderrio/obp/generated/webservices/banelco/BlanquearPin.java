
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
 *         &lt;element name="in0" type="{http://model.webservices.banelco.com}WSTerminalData"/>
 *         &lt;element name="in1" type="{http://model.webservices.banelco.com}WSUserData"/>
 *         &lt;element name="in2" type="{http://model.webservices.banelco.com}WSTarjetaDTO"/>
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
    "in2"
})
@XmlRootElement(name = "blanquearPin")
public class BlanquearPin {

    @XmlElement(required = true, nillable = true)
    protected WSTerminalData in0;
    @XmlElement(required = true, nillable = true)
    protected WSUserData in1;
    @XmlElement(required = true, nillable = true)
    protected WSTarjetaDTO in2;

    /**
     * Obtiene el valor de la propiedad in0.
     * 
     * @return
     *     possible object is
     *     {@link WSTerminalData }
     *     
     */
    public WSTerminalData getIn0() {
        return in0;
    }

    /**
     * Define el valor de la propiedad in0.
     * 
     * @param value
     *     allowed object is
     *     {@link WSTerminalData }
     *     
     */
    public void setIn0(WSTerminalData value) {
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

}
