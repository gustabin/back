
package ar.com.santanderrio.obp.generated.webservices.monedero;

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
 *         &lt;element name="Id_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoNumDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID_CuentaVirtual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idBanco",
    "password",
    "tipoNumDoc",
    "idCuentaVirtual"
})
@XmlRootElement(name = "ObtenerCuenta")
public class ObtenerCuenta {

    @XmlElement(name = "Id_Banco")
    protected String idBanco;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TipoNumDoc")
    protected String tipoNumDoc;
    @XmlElement(name = "ID_CuentaVirtual")
    protected String idCuentaVirtual;

    /**
     * Gets the value of the idBanco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBanco() {
        return idBanco;
    }

    /**
     * Sets the value of the idBanco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBanco(String value) {
        this.idBanco = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the tipoNumDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNumDoc() {
        return tipoNumDoc;
    }

    /**
     * Sets the value of the tipoNumDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNumDoc(String value) {
        this.tipoNumDoc = value;
    }

    /**
     * Gets the value of the idCuentaVirtual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDCuentaVirtual() {
        return idCuentaVirtual;
    }

    /**
     * Sets the value of the idCuentaVirtual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDCuentaVirtual(String value) {
        this.idCuentaVirtual = value;
    }

}
