
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.RequestBase;


/**
 * <p>Java class for IdentificacionDeLlamadaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentificacionDeLlamadaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="IdIvr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IdPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PwdRacf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="TipoBanca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsrRacf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacionDeLlamadaRequest", propOrder = {
    "idIvr",
    "idPort",
    "idUsuario",
    "pwdRacf",
    "sessionID",
    "tipoBanca",
    "usrRacf"
})
public class IdentificacionDeLlamadaRequest
    extends RequestBase
{

    @XmlElement(name = "IdIvr")
    protected Integer idIvr;
    @XmlElement(name = "IdPort")
    protected Integer idPort;
    @XmlElementRef(name = "IdUsuario", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> idUsuario;
    @XmlElementRef(name = "PwdRacf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> pwdRacf;
    @XmlElement(name = "SessionID")
    protected Integer sessionID;
    @XmlElementRef(name = "TipoBanca", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> tipoBanca;
    @XmlElementRef(name = "UsrRacf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> usrRacf;

    /**
     * Gets the value of the idIvr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdIvr() {
        return idIvr;
    }

    /**
     * Sets the value of the idIvr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdIvr(Integer value) {
        this.idIvr = value;
    }

    /**
     * Gets the value of the idPort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdPort() {
        return idPort;
    }

    /**
     * Sets the value of the idPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdPort(Integer value) {
        this.idPort = value;
    }

    /**
     * Gets the value of the idUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets the value of the idUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdUsuario(JAXBElement<String> value) {
        this.idUsuario = value;
    }

    /**
     * Gets the value of the pwdRacf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPwdRacf() {
        return pwdRacf;
    }

    /**
     * Sets the value of the pwdRacf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPwdRacf(JAXBElement<String> value) {
        this.pwdRacf = value;
    }

    /**
     * Gets the value of the sessionID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSessionID(Integer value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the tipoBanca property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoBanca() {
        return tipoBanca;
    }

    /**
     * Sets the value of the tipoBanca property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoBanca(JAXBElement<String> value) {
        this.tipoBanca = value;
    }

    /**
     * Gets the value of the usrRacf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsrRacf() {
        return usrRacf;
    }

    /**
     * Sets the value of the usrRacf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsrRacf(JAXBElement<String> value) {
        this.usrRacf = value;
    }

}
