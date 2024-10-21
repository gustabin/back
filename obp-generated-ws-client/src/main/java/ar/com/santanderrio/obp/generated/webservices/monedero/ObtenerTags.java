
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
 *         &lt;element name="Categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID_Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID_Tag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagCantReg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PagNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "idCuentaVirtual",
    "categoria",
    "idUsuario",
    "idTag",
    "pagCantReg",
    "pagNum"
})
@XmlRootElement(name = "ObtenerTags")
public class ObtenerTags {

    @XmlElement(name = "Id_Banco")
    protected String idBanco;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TipoNumDoc")
    protected String tipoNumDoc;
    @XmlElement(name = "ID_CuentaVirtual")
    protected String idCuentaVirtual;
    @XmlElement(name = "Categoria")
    protected String categoria;
    @XmlElement(name = "ID_Usuario")
    protected String idUsuario;
    @XmlElement(name = "ID_Tag")
    protected String idTag;
    @XmlElement(name = "PagCantReg")
    protected String pagCantReg;
    @XmlElement(name = "PagNum")
    protected String pagNum;

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

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the idUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDUsuario() {
        return idUsuario;
    }

    /**
     * Sets the value of the idUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDUsuario(String value) {
        this.idUsuario = value;
    }

    /**
     * Gets the value of the idTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTag() {
        return idTag;
    }

    /**
     * Sets the value of the idTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTag(String value) {
        this.idTag = value;
    }

    /**
     * Gets the value of the pagCantReg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagCantReg() {
        return pagCantReg;
    }

    /**
     * Sets the value of the pagCantReg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagCantReg(String value) {
        this.pagCantReg = value;
    }

    /**
     * Gets the value of the pagNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagNum() {
        return pagNum;
    }

    /**
     * Sets the value of the pagNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagNum(String value) {
        this.pagNum = value;
    }

}
