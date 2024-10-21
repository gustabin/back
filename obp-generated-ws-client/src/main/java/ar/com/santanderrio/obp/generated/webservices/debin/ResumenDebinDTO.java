
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para resumenDebinDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resumenDebinDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendedor" type="{http://webservices.api.debin.prismamp.com/}vendedorDebinDTO" minOccurs="0"/>
 *         &lt;element name="comprador" type="{http://webservices.api.debin.prismamp.com/}compradorDebinDTO" minOccurs="0"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoDebin" type="{http://webservices.api.debin.prismamp.com/}estadoDebinDTO" minOccurs="0"/>
 *         &lt;element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="devolucion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idOperacionOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resumenDebinDTO", propOrder = {
    "id",
    "tipo",
    "vendedor",
    "comprador",
    "moneda",
    "importe",
    "concepto",
    "estadoDebin",
    "vencimiento",
    "fechaCreacion",
    "devolucion",
    "idOperacionOriginal"
})
public class ResumenDebinDTO
    extends BaseDTO
{

    protected String id;
    protected String tipo;
    protected VendedorDebinDTO vendedor;
    protected CompradorDebinDTO comprador;
    protected String moneda;
    protected String importe;
    protected String concepto;
    protected EstadoDebinDTO estadoDebin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar vencimiento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCreacion;
    protected boolean devolucion;
    protected String idOperacionOriginal;

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
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
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
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVencimiento(XMLGregorianCalendar value) {
        this.vencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCreacion.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Define el valor de la propiedad fechaCreacion.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFechaCreacion(XMLGregorianCalendar value) {
        this.fechaCreacion = value;
    }
    /**
     * Obtiene el valor de la propiedad devolucion.
     * 
     */
    public boolean isDevolucion() {
        return devolucion;
    }

    /**
     * Define el valor de la propiedad devolucion.
     * 
     */
    public void setDevolucion(boolean value) {
        this.devolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacionOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacionOriginal() {
        return idOperacionOriginal;
    }

    /**
     * Define el valor de la propiedad idOperacionOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacionOriginal(String value) {
        this.idOperacionOriginal = value;
    }

    @Override
    public String toString() {
        return "ResumenDebinDTO [id=" + id + ", tipo=" + tipo + ", vendedor=" + vendedor + ", comprador=" + comprador
                + ", moneda=" + moneda + ", importe=" + importe + ", concepto=" + concepto + ", estadoDebin="
                + estadoDebin +  ", devolucion=" + devolucion + ", idOperacionOriginal="
                + idOperacionOriginal + "]";
    }

}
