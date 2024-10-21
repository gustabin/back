
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para detalleDebinDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="detalleDebinDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="idDebin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaCreacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaExpiracion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="estadoDebin" type="{http://webservices.api.debin.prismamp.com/}estadoDebinDTO" minOccurs="0"/>
 *         &lt;element name="concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="evaluacion" type="{http://webservices.api.debin.prismamp.com/}evaluacionDTO" minOccurs="0"/>
 *         &lt;element name="importe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devolucion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "detalleDebinDTO", propOrder = {
    "idDebin",
    "fechaCreacion",
    "fechaExpiracion",
    "estadoDebin",
    "concepto",
    "idUsuario",
    "idComprobante",
    "moneda",
    "evaluacion",
    "importe",
    "descripcion",
    "devolucion",
    "idOperacionOriginal"
})
public class DetalleDebinDTO
    extends BaseDTO
{

    protected String idDebin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCreacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaExpiracion;
    protected EstadoDebinDTO estadoDebin;
    protected String concepto;
    protected String idUsuario;
    protected String idComprobante;
    protected String moneda;
    protected EvaluacionDTO evaluacion;
    protected String importe;
    protected String descripcion;
    protected Boolean devolucion;
    protected String idOperacionOriginal;

    /**
     * Obtiene el valor de la propiedad idDebin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDebin() {
        return idDebin;
    }

    /**
     * Define el valor de la propiedad idDebin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDebin(String value) {
        this.idDebin = value;
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
     * Obtiene el valor de la propiedad idUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define el valor de la propiedad idUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdUsuario(String value) {
        this.idUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad idComprobante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdComprobante() {
        return idComprobante;
    }

    /**
     * Define el valor de la propiedad idComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdComprobante(String value) {
        this.idComprobante = value;
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
     * Obtiene el valor de la propiedad evaluacion.
     * 
     * @return
     *     possible object is
     *     {@link EvaluacionDTO }
     *     
     */
    public EvaluacionDTO getEvaluacion() {
        return evaluacion;
    }

    /**
     * Define el valor de la propiedad evaluacion.
     * 
     * @param value
     *     allowed object is
     *     {@link EvaluacionDTO }
     *     
     */
    public void setEvaluacion(EvaluacionDTO value) {
        this.evaluacion = value;
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
     * Obtiene el valor de la propiedad devolucion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDevolucion() {
        return devolucion;
    }

    /**
     * Define el valor de la propiedad devolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDevolucion(Boolean value) {
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
        return "DetalleDebinDTO [idDebin=" + idDebin + ", estadoDebin=" + estadoDebin + ", concepto=" + concepto + ", idUsuario="
                + idUsuario + ", idComprobante=" + idComprobante + ", moneda=" + moneda + ", evaluacion=" + evaluacion
                + ", importe=" + importe + ", descripcion=" + descripcion + ", devolucion=" + devolucion
                + ", idOperacionOriginal=" + idOperacionOriginal + "]";
    }
    
}
