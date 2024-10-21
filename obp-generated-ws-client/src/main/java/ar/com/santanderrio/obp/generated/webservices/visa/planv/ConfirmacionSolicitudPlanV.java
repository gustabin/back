
package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for confirmacionSolicitudPlanV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="confirmacionSolicitudPlanV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="costoFinancieroTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="importeCuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="montoCargosAdministrativos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoSeguroDeVida" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoSolicitud" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="numeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCuotas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TNA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalIVA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmacionSolicitudPlanV", propOrder = {
    "codigoRespuesta",
    "costoFinancieroTotal",
    "importeCuotas",
    "moneda",
    "montoCargosAdministrativos",
    "montoSeguroDeVida",
    "montoSolicitud",
    "numeroComprobante",
    "numeroTarjeta",
    "planCuotas",
    "tna",
    "totalIVA"
})
public class ConfirmacionSolicitudPlanV {

    protected String codigoRespuesta;
    protected double costoFinancieroTotal;
    protected double importeCuotas;
    protected String moneda;
    protected double montoCargosAdministrativos;
    protected double montoSeguroDeVida;
    protected double montoSolicitud;
    protected String numeroComprobante;
    protected String numeroTarjeta;
    protected int planCuotas;
    @XmlElement(name = "TNA")
    protected double tna;
    protected double totalIVA;

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
     * Gets the value of the costoFinancieroTotal property.
     * 
     */
    public double getCostoFinancieroTotal() {
        return costoFinancieroTotal;
    }

    /**
     * Sets the value of the costoFinancieroTotal property.
     * 
     */
    public void setCostoFinancieroTotal(double value) {
        this.costoFinancieroTotal = value;
    }

    /**
     * Gets the value of the importeCuotas property.
     * 
     */
    public double getImporteCuotas() {
        return importeCuotas;
    }

    /**
     * Sets the value of the importeCuotas property.
     * 
     */
    public void setImporteCuotas(double value) {
        this.importeCuotas = value;
    }

    /**
     * Gets the value of the moneda property.
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
     * Sets the value of the moneda property.
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
     * Gets the value of the montoCargosAdministrativos property.
     * 
     */
    public double getMontoCargosAdministrativos() {
        return montoCargosAdministrativos;
    }

    /**
     * Sets the value of the montoCargosAdministrativos property.
     * 
     */
    public void setMontoCargosAdministrativos(double value) {
        this.montoCargosAdministrativos = value;
    }

    /**
     * Gets the value of the montoSeguroDeVida property.
     * 
     */
    public double getMontoSeguroDeVida() {
        return montoSeguroDeVida;
    }

    /**
     * Sets the value of the montoSeguroDeVida property.
     * 
     */
    public void setMontoSeguroDeVida(double value) {
        this.montoSeguroDeVida = value;
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
     * Gets the value of the planCuotas property.
     * 
     */
    public int getPlanCuotas() {
        return planCuotas;
    }

    /**
     * Sets the value of the planCuotas property.
     * 
     */
    public void setPlanCuotas(int value) {
        this.planCuotas = value;
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
     * Gets the value of the totalIVA property.
     * 
     */
    public double getTotalIVA() {
        return totalIVA;
    }

    /**
     * Sets the value of the totalIVA property.
     * 
     */
    public void setTotalIVA(double value) {
        this.totalIVA = value;
    }

}
