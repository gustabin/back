
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.base.Entity;


/**
 * <p>Java class for Saldo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Saldo">
 *   &lt;complexContent>
 *     &lt;extension base="{Domain/Base}Entity">
 *       &lt;sequence>
 *         &lt;element name="ASESOR" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CAhorroDolares" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CAhorroPesos" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CUENTA" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SUCURSAL" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Saldo", propOrder = {
    "asesor",
    "cAhorroDolares",
    "cAhorroPesos",
    "cuenta",
    "descripcion",
    "fecha",
    "sucursal"
})
public class Saldo
    extends Entity
{

    @XmlElementRef(name = "ASESOR", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<Integer> asesor;
    @XmlElementRef(name = "CAhorroDolares", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cAhorroDolares;
    @XmlElementRef(name = "CAhorroPesos", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cAhorroPesos;
    @XmlElementRef(name = "CUENTA", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<Long> cuenta;
    @XmlElementRef(name = "DESCRIPCION", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<String> descripcion;
    @XmlElementRef(name = "FECHA", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<String> fecha;
    @XmlElementRef(name = "SUCURSAL", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Domain", type = JAXBElement.class)
    protected JAXBElement<Short> sucursal;

    /**
     * Gets the value of the asesor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getASESOR() {
        return asesor;
    }

    /**
     * Sets the value of the asesor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setASESOR(JAXBElement<Integer> value) {
        this.asesor = value;
    }

    /**
     * Gets the value of the cAhorroDolares property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCAhorroDolares() {
        return cAhorroDolares;
    }

    /**
     * Sets the value of the cAhorroDolares property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCAhorroDolares(JAXBElement<BigDecimal> value) {
        this.cAhorroDolares = value;
    }

    /**
     * Gets the value of the cAhorroPesos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCAhorroPesos() {
        return cAhorroPesos;
    }

    /**
     * Sets the value of the cAhorroPesos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCAhorroPesos(JAXBElement<BigDecimal> value) {
        this.cAhorroPesos = value;
    }

    /**
     * Gets the value of the cuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getCUENTA() {
        return cuenta;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setCUENTA(JAXBElement<Long> value) {
        this.cuenta = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDESCRIPCION() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDESCRIPCION(JAXBElement<String> value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFECHA() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFECHA(JAXBElement<String> value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getSUCURSAL() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setSUCURSAL(JAXBElement<Short> value) {
        this.sucursal = value;
    }

}
