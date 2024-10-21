
package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solicitudPlanV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solicitudPlanV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFT" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cantidadCuotas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuotasPendientesDeLiquidar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaSolicitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importeCuota" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="importePendienteDeLiquidar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoSolicitud" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="numeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TNA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoPlanV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudPlanV", propOrder = {
    "cft",
    "cantidadCuotas",
    "codigoRespuesta",
    "cuotasPendientesDeLiquidar",
    "fechaSolicitud",
    "importeCuota",
    "importePendienteDeLiquidar",
    "montoSolicitud",
    "numeroComprobante",
    "numeroCuenta",
    "numeroTarjeta",
    "tna",
    "tipoPlanV"
})
public class SolicitudPlanV {

    @XmlElement(name = "CFT")
    protected double cft;
    protected int cantidadCuotas;
    protected String codigoRespuesta;
    protected int cuotasPendientesDeLiquidar;
    protected String fechaSolicitud;
    protected double importeCuota;
    protected double importePendienteDeLiquidar;
    protected double montoSolicitud;
    protected String numeroComprobante;
    protected String numeroCuenta;
    protected String numeroTarjeta;
    @XmlElement(name = "TNA")
    protected double tna;
    protected String tipoPlanV;

    /**
     * Gets the value of the cft property.
     * 
     */
    public double getCFT() {
        return cft;
    }

    /**
     * Sets the value of the cft property.
     * 
     */
    public void setCFT(double value) {
        this.cft = value;
    }

    /**
     * Gets the value of the cantidadCuotas property.
     * 
     */
    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    /**
     * Sets the value of the cantidadCuotas property.
     * 
     */
    public void setCantidadCuotas(int value) {
        this.cantidadCuotas = value;
    }

    /**
     * Gets the value of the codigoRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Sets the value of the codigoRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRespuesta(String value) {
        this.codigoRespuesta = value;
    }

    /**
     * Gets the value of the cuotasPendientesDeLiquidar property.
     * 
     */
    public int getCuotasPendientesDeLiquidar() {
        return cuotasPendientesDeLiquidar;
    }

    /**
     * Sets the value of the cuotasPendientesDeLiquidar property.
     * 
     */
    public void setCuotasPendientesDeLiquidar(int value) {
        this.cuotasPendientesDeLiquidar = value;
    }

    /**
     * Gets the value of the fechaSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Sets the value of the fechaSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaSolicitud(String value) {
        this.fechaSolicitud = value;
    }

    /**
     * Gets the value of the importeCuota property.
     * 
     */
    public double getImporteCuota() {
        return importeCuota;
    }

    /**
     * Sets the value of the importeCuota property.
     * 
     */
    public void setImporteCuota(double value) {
        this.importeCuota = value;
    }

    /**
     * Gets the value of the importePendienteDeLiquidar property.
     * 
     */
    public double getImportePendienteDeLiquidar() {
        return importePendienteDeLiquidar;
    }

    /**
     * Sets the value of the importePendienteDeLiquidar property.
     * 
     */
    public void setImportePendienteDeLiquidar(double value) {
        this.importePendienteDeLiquidar = value;
    }

    /**
     * Gets the value of the montoSolicitud property.
     * 
     */
    public double getMontoSolicitud() {
        return montoSolicitud;
    }

    /**
     * Sets the value of the montoSolicitud property.
     * 
     */
    public void setMontoSolicitud(double value) {
        this.montoSolicitud = value;
    }

    /**
     * Gets the value of the numeroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Sets the value of the numeroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroComprobante(String value) {
        this.numeroComprobante = value;
    }

    /**
     * Gets the value of the numeroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Sets the value of the numeroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuenta(String value) {
        this.numeroCuenta = value;
    }

    /**
     * Gets the value of the numeroTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Sets the value of the numeroTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTarjeta(String value) {
        this.numeroTarjeta = value;
    }

    /**
     * Gets the value of the tna property.
     * 
     */
    public double getTNA() {
        return tna;
    }

    /**
     * Sets the value of the tna property.
     * 
     */
    public void setTNA(double value) {
        this.tna = value;
    }

    /**
     * Gets the value of the tipoPlanV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPlanV() {
        return tipoPlanV;
    }

    /**
     * Sets the value of the tipoPlanV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPlanV(String value) {
        this.tipoPlanV = value;
    }

}
