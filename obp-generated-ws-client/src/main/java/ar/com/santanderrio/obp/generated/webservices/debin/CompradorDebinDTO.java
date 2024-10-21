
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para compradorDebinDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="compradorDebinDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoComprador" type="{http://webservices.api.debin.prismamp.com/}estadoUsuarioDTO" minOccurs="0"/>
 *         &lt;element name="cuenta" type="{http://webservices.api.debin.prismamp.com/}cuentaDebinDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compradorDebinDTO", propOrder = {
    "codigo",
    "titular",
    "cuit",
    "descripcion",
    "estadoComprador",
    "cuenta"
})
public class CompradorDebinDTO
    extends BaseDTO
{

    protected String codigo;
    protected String titular;
    protected String cuit;
    protected String descripcion;
    protected EstadoUsuarioDTO estadoComprador;
    protected CuentaDebinDTO cuenta;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad titular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Define el valor de la propiedad titular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitular(String value) {
        this.titular = value;
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

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoComprador.
     * 
     * @return
     *     possible object is
     *     {@link EstadoUsuarioDTO }
     *     
     */
    public EstadoUsuarioDTO getEstadoComprador() {
        return estadoComprador;
    }

    /**
     * Define el valor de la propiedad estadoComprador.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoUsuarioDTO }
     *     
     */
    public void setEstadoComprador(EstadoUsuarioDTO value) {
        this.estadoComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link CuentaDebinDTO }
     *     
     */
    public CuentaDebinDTO getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaDebinDTO }
     *     
     */
    public void setCuenta(CuentaDebinDTO value) {
        this.cuenta = value;
    }

    @Override
    public String toString() {
        return "CompradorDebinDTO [cuit=" + cuit + "]";
    }

}
