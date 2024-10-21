
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseDebin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseDebin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="comprador" type="{http://webservices.api.debin.prismamp.com/}compradorDebinDTO" minOccurs="0"/>
 *         &lt;element name="detalleDebin" type="{http://webservices.api.debin.prismamp.com/}detalleDebinDTO" minOccurs="0"/>
 *         &lt;element name="fechaNegocio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="garantiaOK" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="preautorizado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "responseDebin", propOrder = {
    "comprador",
    "detalleDebin",
    "fechaNegocio",
    "garantiaOK",
    "preautorizado",
    "tipo",
    "vendedor"
})
public class ResponseDebin
    extends Response
{

    protected CompradorDebinDTO comprador;
    protected DetalleDebinDTO detalleDebin;
    protected String fechaNegocio;
    protected boolean garantiaOK;
    protected boolean preautorizado;
    protected String tipo;
    protected VendedorDebinDTO vendedor;

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
     * Obtiene el valor de la propiedad detalleDebin.
     * 
     * @return
     *     possible object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public DetalleDebinDTO getDetalleDebin() {
        return detalleDebin;
    }

    /**
     * Define el valor de la propiedad detalleDebin.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public void setDetalleDebin(DetalleDebinDTO value) {
        this.detalleDebin = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNegocio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaNegocio() {
        return fechaNegocio;
    }

    /**
     * Define el valor de la propiedad fechaNegocio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaNegocio(String value) {
        this.fechaNegocio = value;
    }

    /**
     * Obtiene el valor de la propiedad garantiaOK.
     * 
     */
    public boolean isGarantiaOK() {
        return garantiaOK;
    }

    /**
     * Define el valor de la propiedad garantiaOK.
     * 
     */
    public void setGarantiaOK(boolean value) {
        this.garantiaOK = value;
    }

    /**
     * Obtiene el valor de la propiedad preautorizado.
     * 
     */
    public boolean isPreautorizado() {
        return preautorizado;
    }

    /**
     * Define el valor de la propiedad preautorizado.
     * 
     */
    public void setPreautorizado(boolean value) {
        this.preautorizado = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
