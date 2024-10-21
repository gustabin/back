
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para requestContracargo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestContracargo">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}request">
 *       &lt;sequence>
 *         &lt;element name="comprador" type="{http://webservices.api.debin.prismamp.com/}datosUsuarioBreveDTO" minOccurs="0"/>
 *         &lt;element name="detalle" type="{http://webservices.api.debin.prismamp.com/}detalleDebinBreveDTO" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendedor" type="{http://webservices.api.debin.prismamp.com/}datosUsuarioBreveDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestContracargo", propOrder = {
    "comprador",
    "detalle",
    "id",
    "tipo",
    "vendedor"
})
public class RequestContracargo
    extends Request
{

    protected DatosUsuarioBreveDTO comprador;
    protected DetalleDebinBreveDTO detalle;
    protected String id;
    protected String tipo;
    protected DatosUsuarioBreveDTO vendedor;

    /**
     * Obtiene el valor de la propiedad comprador.
     * 
     * @return
     *     possible object is
     *     {@link DatosUsuarioBreveDTO }
     *     
     */
    public DatosUsuarioBreveDTO getComprador() {
        return comprador;
    }

    /**
     * Define el valor de la propiedad comprador.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUsuarioBreveDTO }
     *     
     */
    public void setComprador(DatosUsuarioBreveDTO value) {
        this.comprador = value;
    }

    /**
     * Obtiene el valor de la propiedad detalle.
     * 
     * @return
     *     possible object is
     *     {@link DetalleDebinBreveDTO }
     *     
     */
    public DetalleDebinBreveDTO getDetalle() {
        return detalle;
    }

    /**
     * Define el valor de la propiedad detalle.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleDebinBreveDTO }
     *     
     */
    public void setDetalle(DetalleDebinBreveDTO value) {
        this.detalle = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *     {@link DatosUsuarioBreveDTO }
     *     
     */
    public DatosUsuarioBreveDTO getVendedor() {
        return vendedor;
    }

    /**
     * Define el valor de la propiedad vendedor.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUsuarioBreveDTO }
     *     
     */
    public void setVendedor(DatosUsuarioBreveDTO value) {
        this.vendedor = value;
    }

    @Override
    public String toString() {
        return "RequestContracargo [comprador=" + comprador + ", detalle=" + detalle + ", id=" + id + ", tipo=" + tipo
                + ", vendedor=" + vendedor + "]";
    }

}
