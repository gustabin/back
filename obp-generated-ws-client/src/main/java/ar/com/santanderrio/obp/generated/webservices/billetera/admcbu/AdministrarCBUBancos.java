
package ar.com.santanderrio.obp.generated.webservices.billetera.admcbu;

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
 *         &lt;element name="idCuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="banco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cbu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoNovedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codBanco" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idCuenta",
    "banco",
    "canal",
    "tipoAcreditacion",
    "cbu",
    "tipoCuenta",
    "monedaCuenta",
    "nroCuenta",
    "tipoNovedad",
    "codBanco",
    "cuit"
})
@XmlRootElement(name = "administrarCBUBancos")
public class AdministrarCBUBancos {

    protected int idCuenta;
    @XmlElement(required = true)
    protected String banco;
    protected int canal;
    @XmlElement(required = true)
    protected String tipoAcreditacion;
    @XmlElement(required = true)
    protected String cbu;
    @XmlElement(required = true)
    protected String tipoCuenta;
    @XmlElement(required = true)
    protected String monedaCuenta;
    @XmlElement(required = true)
    protected String nroCuenta;
    @XmlElement(required = true)
    protected String tipoNovedad;
    @XmlElement(required = true, nillable = true)
    protected String codBanco;
    @XmlElement(required = true)
    protected String cuit;

    /**
     * Obtiene el valor de la propiedad idCuenta.
     * 
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * Define el valor de la propiedad idCuenta.
     * 
     */
    public void setIdCuenta(int value) {
        this.idCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanco(String value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad canal.
     * 
     */
    public int getCanal() {
        return canal;
    }

    /**
     * Define el valor de la propiedad canal.
     * 
     */
    public void setCanal(int value) {
        this.canal = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAcreditacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAcreditacion() {
        return tipoAcreditacion;
    }

    /**
     * Define el valor de la propiedad tipoAcreditacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAcreditacion(String value) {
        this.tipoAcreditacion = value;
    }

    /**
     * Obtiene el valor de la propiedad cbu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Define el valor de la propiedad cbu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbu(String value) {
        this.cbu = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Define el valor de la propiedad tipoCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCuenta(String value) {
        this.tipoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaCuenta() {
        return monedaCuenta;
    }

    /**
     * Define el valor de la propiedad monedaCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaCuenta(String value) {
        this.monedaCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad nroCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Define el valor de la propiedad nroCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCuenta(String value) {
        this.nroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoNovedad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNovedad() {
        return tipoNovedad;
    }

    /**
     * Define el valor de la propiedad tipoNovedad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNovedad(String value) {
        this.tipoNovedad = value;
    }

    /**
     * Obtiene el valor de la propiedad codBanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodBanco() {
        return codBanco;
    }

    /**
     * Define el valor de la propiedad codBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodBanco(String value) {
        this.codBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad cuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Define el valor de la propiedad cuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

}
