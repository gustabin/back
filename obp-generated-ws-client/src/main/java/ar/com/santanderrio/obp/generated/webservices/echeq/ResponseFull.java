
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseFull complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseFull">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stat" type="{http://echeq.amco.com.ar/}CCEStat"/>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cod_visualizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="beneficiario_razon_social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto_total_emitido" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monto_total_recib_pend_recepcion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monto_total_recibido" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monto_total_custodia" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monto_total_emit_pend_recepcion" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="total_cheques" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cheques" type="{http://echeq.amco.com.ar/}Cheques" minOccurs="0"/>
 *         &lt;element name="cuentas" type="{http://echeq.amco.com.ar/}Cuenta" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="infraestructurasMercado" type="{http://echeq.amco.com.ar/}IMF" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseFull", propOrder = {
    "code",
    "message",
    "stat",
    "intchequeId",
    "codVisualizacion",
    "beneficiarioDocumentoTipo",
    "beneficiarioDocumento",
    "beneficiarioRazonSocial",
    "montoTotalEmitido",
    "montoTotalRecibPendRecepcion",
    "montoTotalRecibido",
    "montoTotalCustodia",
    "montoTotalEmitPendRecepcion",
    "totalCheques",
    "cheques",
    "cuentas",
    "infraestructurasMercado"
})
public class ResponseFull {

    protected String code;
    protected String message;
    @XmlElement(required = true)
    protected CCEStat stat;
    @XmlElement(name = "intcheque_id")
    protected String intchequeId;
    @XmlElement(name = "cod_visualizacion")
    protected String codVisualizacion;
    @XmlElement(name = "beneficiario_documento_tipo")
    protected String beneficiarioDocumentoTipo;
    @XmlElement(name = "beneficiario_documento")
    protected String beneficiarioDocumento;
    @XmlElement(name = "beneficiario_razon_social")
    protected String beneficiarioRazonSocial;
    @XmlElement(name = "monto_total_emitido")
    protected Double montoTotalEmitido;
    @XmlElement(name = "monto_total_recib_pend_recepcion")
    protected Double montoTotalRecibPendRecepcion;
    @XmlElement(name = "monto_total_recibido")
    protected Double montoTotalRecibido;
    @XmlElement(name = "monto_total_custodia")
    protected Double montoTotalCustodia;
    @XmlElement(name = "monto_total_emit_pend_recepcion")
    protected Double montoTotalEmitPendRecepcion;
    @XmlElement(name = "total_cheques")
    protected Double totalCheques;
    protected Cheques cheques;
    protected List<Cuenta> cuentas;
    protected List<IMF> infraestructurasMercado;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the stat property.
     * 
     * @return
     *     possible object is
     *     {@link CCEStat }
     *     
     */
    public CCEStat getStat() {
        return stat;
    }

    /**
     * Sets the value of the stat property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCEStat }
     *     
     */
    public void setStat(CCEStat value) {
        this.stat = value;
    }

    /**
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the codVisualizacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodVisualizacion() {
        return codVisualizacion;
    }

    /**
     * Sets the value of the codVisualizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodVisualizacion(String value) {
        this.codVisualizacion = value;
    }

    /**
     * Gets the value of the beneficiarioDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumentoTipo() {
        return beneficiarioDocumentoTipo;
    }

    /**
     * Sets the value of the beneficiarioDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumentoTipo(String value) {
        this.beneficiarioDocumentoTipo = value;
    }

    /**
     * Gets the value of the beneficiarioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioDocumento() {
        return beneficiarioDocumento;
    }

    /**
     * Sets the value of the beneficiarioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioDocumento(String value) {
        this.beneficiarioDocumento = value;
    }

    /**
     * Gets the value of the beneficiarioRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioRazonSocial() {
        return beneficiarioRazonSocial;
    }

    /**
     * Sets the value of the beneficiarioRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioRazonSocial(String value) {
        this.beneficiarioRazonSocial = value;
    }

    /**
     * Gets the value of the montoTotalEmitido property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoTotalEmitido() {
        return montoTotalEmitido;
    }

    /**
     * Sets the value of the montoTotalEmitido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoTotalEmitido(Double value) {
        this.montoTotalEmitido = value;
    }

    /**
     * Gets the value of the montoTotalRecibPendRecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoTotalRecibPendRecepcion() {
        return montoTotalRecibPendRecepcion;
    }

    /**
     * Sets the value of the montoTotalRecibPendRecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoTotalRecibPendRecepcion(Double value) {
        this.montoTotalRecibPendRecepcion = value;
    }

    /**
     * Gets the value of the montoTotalRecibido property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoTotalRecibido() {
        return montoTotalRecibido;
    }

    /**
     * Sets the value of the montoTotalRecibido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoTotalRecibido(Double value) {
        this.montoTotalRecibido = value;
    }

    /**
     * Gets the value of the montoTotalCustodia property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoTotalCustodia() {
        return montoTotalCustodia;
    }

    /**
     * Sets the value of the montoTotalCustodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoTotalCustodia(Double value) {
        this.montoTotalCustodia = value;
    }

    /**
     * Gets the value of the montoTotalEmitPendRecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMontoTotalEmitPendRecepcion() {
        return montoTotalEmitPendRecepcion;
    }

    /**
     * Sets the value of the montoTotalEmitPendRecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMontoTotalEmitPendRecepcion(Double value) {
        this.montoTotalEmitPendRecepcion = value;
    }

    /**
     * Gets the value of the totalCheques property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalCheques() {
        return totalCheques;
    }

    /**
     * Sets the value of the totalCheques property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalCheques(Double value) {
        this.totalCheques = value;
    }

    /**
     * Gets the value of the cheques property.
     * 
     * @return
     *     possible object is
     *     {@link Cheques }
     *     
     */
    public Cheques getCheques() {
        return cheques;
    }

    /**
     * Sets the value of the cheques property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cheques }
     *     
     */
    public void setCheques(Cheques value) {
        this.cheques = value;
    }

    /**
     * Gets the value of the cuentas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cuenta }
     * 
     * 
     */
    public List<Cuenta> getCuentas() {
        if (cuentas == null) {
            cuentas = new ArrayList<Cuenta>();
        }
        return this.cuentas;
    }

    /**
     * Gets the value of the infraestructurasMercado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infraestructurasMercado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfraestructurasMercado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IMF }
     * 
     * 
     */
    public List<IMF> getInfraestructurasMercado() {
        if (infraestructurasMercado == null) {
            infraestructurasMercado = new ArrayList<IMF>();
        }
        return this.infraestructurasMercado;
    }

}
