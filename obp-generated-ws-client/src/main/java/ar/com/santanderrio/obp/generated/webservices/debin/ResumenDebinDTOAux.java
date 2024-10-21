
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para resumenDebinDTOAux complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resumenDebinDTOAux">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendedor" type="{http://webservices.api.debin.prismamp.com/}vendedorDebinDTO" minOccurs="0"/>
 *         &lt;element name="comprador" type="{http://webservices.api.debin.prismamp.com/}compradorDebinDTO" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoDebin" type="{http://webservices.api.debin.prismamp.com/}estadoDebinDTO" minOccurs="0"/>
 *         &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resumenDebinDTOAux", propOrder = {
    "id",
    "vendedor",
    "comprador",
    "importe",
    "concepto",
    "estadoDebin",
    "fechaExpiracion"
})
public class ResumenDebinDTOAux
    extends BaseDTO
{

    protected String id;
    protected VendedorDebinDTO vendedor;
    protected CompradorDebinDTO comprador;
    protected String importe;
    protected String concepto;
    protected EstadoDebinDTO estadoDebin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaExpiracion;

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
     * Obtiene el valor de la propiedad importe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporte(String value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoDebin.
     * 
     * @return
     *     possible object is
     *     {@link EstadoDebinDTO }
     *     
     */
    public EstadoDebinDTO getEstadoDebin() {
        return estadoDebin;
    }

    /**
     * Define el valor de la propiedad estadoDebin.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoDebinDTO }
     *     
     */
    public void setEstadoDebin(EstadoDebinDTO value) {
        this.estadoDebin = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaExpiracion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * Define el valor de la propiedad fechaExpiracion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaExpiracion(XMLGregorianCalendar value) {
        this.fechaExpiracion = value;
    }

}
