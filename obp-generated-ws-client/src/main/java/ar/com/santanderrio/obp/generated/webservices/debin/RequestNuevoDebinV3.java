
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para requestNuevoDebinV3 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestNuevoDebinV3">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}request">
 *       &lt;sequence>
 *         &lt;element name="categoriaLimite" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="comprador" type="{http://webservices.api.debin.prismamp.com/}compradorDebinDTO" minOccurs="0"/>
 *         &lt;element name="controlLimite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="detalle" type="{http://webservices.api.debin.prismamp.com/}detalleDebinDTO" minOccurs="0"/>
 *         &lt;element name="mismoTitular" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="recurrencia" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="vendedor" type="{http://webservices.api.debin.prismamp.com/}vendedorDebinDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestNuevoDebinV3", propOrder = {
    "categoriaLimite",
    "comprador",
    "controlLimite",
    "detalle",
    "mismoTitular",
    "recurrencia",
    "vendedor"
})
public class RequestNuevoDebinV3
    extends Request
{

    protected Integer categoriaLimite;
    protected CompradorDebinDTO comprador;
    protected Boolean controlLimite;
    protected DetalleDebinDTO detalle;
    protected Integer mismoTitular;
    protected Boolean recurrencia;
    protected VendedorDebinDTO vendedor;

    /**
     * Obtiene el valor de la propiedad categoriaLimite.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategoriaLimite() {
        return categoriaLimite;
    }

    /**
     * Define el valor de la propiedad categoriaLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategoriaLimite(Integer value) {
        this.categoriaLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad comprador.
     * 
     * @return
     *     possible object is
     *     {@link CompradorDebinDTO }
     *     
     */
    public CompradorDebinDTO getComprador() {
        return comprador;
    }

    /**
     * Define el valor de la propiedad comprador.
     * 
     * @param value
     *     allowed object is
     *     {@link CompradorDebinDTO }
     *     
     */
    public void setComprador(CompradorDebinDTO value) {
        this.comprador = value;
    }

    /**
     * Obtiene el valor de la propiedad controlLimite.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isControlLimite() {
        return controlLimite;
    }

    /**
     * Define el valor de la propiedad controlLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setControlLimite(Boolean value) {
        this.controlLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public DetalleDebinDTO getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public void setDetalle(DetalleDebinDTO value) {
        this.detalle = value;
    }

    /**
     * Obtiene el valor de la propiedad mismoTitular.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMismoTitular() {
        return mismoTitular;
    }

    /**
     * Define el valor de la propiedad mismoTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMismoTitular(Integer value) {
        this.mismoTitular = value;
    }

    /**
     * Obtiene el valor de la propiedad recurrencia.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecurrencia() {
        return recurrencia;
    }

    /**
     * Define el valor de la propiedad recurrencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecurrencia(Boolean value) {
        this.recurrencia = value;
    }

    /**
     * Obtiene el valor de la propiedad vendedor.
     * 
     * @return
     *     possible object is
     *     {@link VendedorDebinDTO }
     *     
     */
    public VendedorDebinDTO getVendedor() {
        return vendedor;
    }

    /**
     * Define el valor de la propiedad vendedor.
     * 
     * @param value
     *     allowed object is
     *     {@link VendedorDebinDTO }
     *     
     */
    public void setVendedor(VendedorDebinDTO value) {
        this.vendedor = value;
    }

}
