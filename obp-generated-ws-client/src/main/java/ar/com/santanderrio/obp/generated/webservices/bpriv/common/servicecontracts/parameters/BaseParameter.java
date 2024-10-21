
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCMBGRABAMOVPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSATITPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSCUADREPERFILPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSMOVIMIENTOSPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSROSSIPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPCUENTASCNSSALDOSPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPORDENESCMBALTAPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.BPORDENESCMBESTADOPARAMETER;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaEntreBancosOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaEntreBancosParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaRIORIOOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaRIORIOParameter;


/**
 * <p>Java class for BaseParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CantidadDeRegistros" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseParameter", propOrder = {
    "cantidadDeRegistros",
    "ip",
    "password",
    "tipoUser",
    "usuario"
})
@XmlSeeAlso({
    BPCUENTASCNSMOVIMIENTOSPARAMETER.class,
    BPORDENESCMBESTADOPARAMETER.class,
    BPCUENTASCNSSALDOSPARAMETER.class,
    InsertarTransferenciaRIORIOOBParameter.class,
    InsertarTransferenciaEntreBancosParameter.class,
    InsertarOperacionCambioParameter.class,
    BPCUENTASCMBGRABAMOVPARAMETER.class,
    InsertarTransferenciaEntreBancosOBParameter.class,
    BPCUENTASCNSROSSIPARAMETER.class,
    BPCUENTASCNSCUADREPERFILPARAMETER.class,
    BPORDENESCMBALTAPARAMETER.class,
    InsertarOperacionCambioOBParameter.class,
    BPCUENTASCNSATITPARAMETER.class,
    InsertarTransferenciaRIORIOParameter.class
})
public class BaseParameter {

    @XmlElementRef(name = "CantidadDeRegistros", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", type = JAXBElement.class)
    protected JAXBElement<Integer> cantidadDeRegistros;
    @XmlElementRef(name = "Ip", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", type = JAXBElement.class)
    protected JAXBElement<String> ip;
    @XmlElementRef(name = "Password", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", type = JAXBElement.class)
    protected JAXBElement<String> password;
    @XmlElementRef(name = "TipoUser", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", type = JAXBElement.class)
    protected JAXBElement<String> tipoUser;
    @XmlElementRef(name = "Usuario", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters", type = JAXBElement.class)
    protected JAXBElement<String> usuario;

    /**
     * Gets the value of the cantidadDeRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCantidadDeRegistros() {
        return cantidadDeRegistros;
    }

    /**
     * Sets the value of the cantidadDeRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCantidadDeRegistros(JAXBElement<Integer> value) {
        this.cantidadDeRegistros = value;
    }

    /**
     * Gets the value of the ip property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIp() {
        return ip;
    }

    /**
     * Sets the value of the ip property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIp(JAXBElement<String> value) {
        this.ip = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPassword(JAXBElement<String> value) {
        this.password = value;
    }

    /**
     * Gets the value of the tipoUser property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoUser() {
        return tipoUser;
    }

    /**
     * Sets the value of the tipoUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoUser(JAXBElement<String> value) {
        this.tipoUser = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuario(JAXBElement<String> value) {
        this.usuario = value;
    }

}
