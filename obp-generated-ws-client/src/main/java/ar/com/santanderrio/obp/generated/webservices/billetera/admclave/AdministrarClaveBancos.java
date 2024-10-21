
package ar.com.santanderrio.obp.generated.webservices.billetera.admclave;

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
 *         &lt;element name="contrasenia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="respPregSeguridad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoNovedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "contrasenia",
    "respPregSeguridad",
    "tipoNovedad",
    "canal"
})
@XmlRootElement(name = "AdministrarClaveBancos")
public class AdministrarClaveBancos {

    protected int idCuenta;
    @XmlElement(required = true)
    protected String banco;
    @XmlElement(required = true)
    protected String contrasenia;
    @XmlElement(required = true)
    protected String respPregSeguridad;
    @XmlElement(required = true)
    protected String tipoNovedad;
    protected int canal;

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
     * Obtiene el valor de la propiedad contrasenia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Define el valor de la propiedad contrasenia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenia(String value) {
        this.contrasenia = value;
    }

    /**
     * Obtiene el valor de la propiedad respPregSeguridad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespPregSeguridad() {
        return respPregSeguridad;
    }

    /**
     * Define el valor de la propiedad respPregSeguridad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespPregSeguridad(String value) {
        this.respPregSeguridad = value;
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

}
