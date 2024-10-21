
package ar.com.santanderrio.obp.generated.webservices.visa.planv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for informacionPlanV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informacionPlanV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoCuotificacionCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuotasMaximo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuotasMinimo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuotificaSaldos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disponibleCuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="limiteFinanciacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoMaximo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoMaximoAFinanciar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoMinimo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pagoMinimo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pagosIngresados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="saldoDolares" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="saldoPesos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="saldoPesosMasDolares" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta12Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta18Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta24Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta36Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta3Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta60Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta6Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TNAHasta9Cuotas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ultimoCierreCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informacionPlanV", propOrder = {
    "codigoCuotificacionCuenta",
    "codigoRespuesta",
    "cuotasMaximo",
    "cuotasMinimo",
    "cuotificaSaldos",
    "disponibleCuotas",
    "limiteFinanciacion",
    "montoMaximo",
    "montoMaximoAFinanciar",
    "montoMinimo",
    "pagoMinimo",
    "pagosIngresados",
    "saldoDolares",
    "saldoPesos",
    "saldoPesosMasDolares",
    "tnaHasta12Cuotas",
    "tnaHasta18Cuotas",
    "tnaHasta24Cuotas",
    "tnaHasta36Cuotas",
    "tnaHasta3Cuotas",
    "tnaHasta60Cuotas",
    "tnaHasta6Cuotas",
    "tnaHasta9Cuotas",
    "ultimoCierreCuenta"
})
public class InformacionPlanV {

    protected String codigoCuotificacionCuenta;
    protected String codigoRespuesta;
    protected int cuotasMaximo;
    protected int cuotasMinimo;
    protected String cuotificaSaldos;
    protected double disponibleCuotas;
    protected double limiteFinanciacion;
    protected double montoMaximo;
    protected double montoMaximoAFinanciar;
    protected double montoMinimo;
    protected double pagoMinimo;
    protected double pagosIngresados;
    protected double saldoDolares;
    protected double saldoPesos;
    protected double saldoPesosMasDolares;
    @XmlElement(name = "TNAHasta12Cuotas")
    protected double tnaHasta12Cuotas;
    @XmlElement(name = "TNAHasta18Cuotas")
    protected double tnaHasta18Cuotas;
    @XmlElement(name = "TNAHasta24Cuotas")
    protected double tnaHasta24Cuotas;
    @XmlElement(name = "TNAHasta36Cuotas")
    protected double tnaHasta36Cuotas;
    @XmlElement(name = "TNAHasta3Cuotas")
    protected double tnaHasta3Cuotas;
    @XmlElement(name = "TNAHasta60Cuotas")
    protected double tnaHasta60Cuotas;
    @XmlElement(name = "TNAHasta6Cuotas")
    protected double tnaHasta6Cuotas;
    @XmlElement(name = "TNAHasta9Cuotas")
    protected double tnaHasta9Cuotas;
    protected String ultimoCierreCuenta;

    /**
     * Gets the value of the codigoCuotificacionCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCuotificacionCuenta() {
        return codigoCuotificacionCuenta;
    }

    /**
     * Sets the value of the codigoCuotificacionCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCuotificacionCuenta(String value) {
        this.codigoCuotificacionCuenta = value;
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
     * Gets the value of the cuotasMaximo property.
     * 
     */
    public int getCuotasMaximo() {
        return cuotasMaximo;
    }

    /**
     * Sets the value of the cuotasMaximo property.
     * 
     */
    public void setCuotasMaximo(int value) {
        this.cuotasMaximo = value;
    }

    /**
     * Gets the value of the cuotasMinimo property.
     * 
     */
    public int getCuotasMinimo() {
        return cuotasMinimo;
    }

    /**
     * Sets the value of the cuotasMinimo property.
     * 
     */
    public void setCuotasMinimo(int value) {
        this.cuotasMinimo = value;
    }

    /**
     * Gets the value of the cuotificaSaldos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuotificaSaldos() {
        return cuotificaSaldos;
    }

    /**
     * Sets the value of the cuotificaSaldos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuotificaSaldos(String value) {
        this.cuotificaSaldos = value;
    }

    /**
     * Gets the value of the disponibleCuotas property.
     * 
     */
    public double getDisponibleCuotas() {
        return disponibleCuotas;
    }

    /**
     * Sets the value of the disponibleCuotas property.
     * 
     */
    public void setDisponibleCuotas(double value) {
        this.disponibleCuotas = value;
    }

    /**
     * Gets the value of the limiteFinanciacion property.
     * 
     */
    public double getLimiteFinanciacion() {
        return limiteFinanciacion;
    }

    /**
     * Sets the value of the limiteFinanciacion property.
     * 
     */
    public void setLimiteFinanciacion(double value) {
        this.limiteFinanciacion = value;
    }

    /**
     * Gets the value of the montoMaximo property.
     * 
     */
    public double getMontoMaximo() {
        return montoMaximo;
    }

    /**
     * Sets the value of the montoMaximo property.
     * 
     */
    public void setMontoMaximo(double value) {
        this.montoMaximo = value;
    }

    /**
     * Gets the value of the montoMaximoAFinanciar property.
     * 
     */
    public double getMontoMaximoAFinanciar() {
        return montoMaximoAFinanciar;
    }

    /**
     * Sets the value of the montoMaximoAFinanciar property.
     * 
     */
    public void setMontoMaximoAFinanciar(double value) {
        this.montoMaximoAFinanciar = value;
    }

    /**
     * Gets the value of the montoMinimo property.
     * 
     */
    public double getMontoMinimo() {
        return montoMinimo;
    }

    /**
     * Sets the value of the montoMinimo property.
     * 
     */
    public void setMontoMinimo(double value) {
        this.montoMinimo = value;
    }

    /**
     * Gets the value of the pagoMinimo property.
     * 
     */
    public double getPagoMinimo() {
        return pagoMinimo;
    }

    /**
     * Sets the value of the pagoMinimo property.
     * 
     */
    public void setPagoMinimo(double value) {
        this.pagoMinimo = value;
    }

    /**
     * Gets the value of the pagosIngresados property.
     * 
     */
    public double getPagosIngresados() {
        return pagosIngresados;
    }

    /**
     * Sets the value of the pagosIngresados property.
     * 
     */
    public void setPagosIngresados(double value) {
        this.pagosIngresados = value;
    }

    /**
     * Gets the value of the saldoDolares property.
     * 
     */
    public double getSaldoDolares() {
        return saldoDolares;
    }

    /**
     * Sets the value of the saldoDolares property.
     * 
     */
    public void setSaldoDolares(double value) {
        this.saldoDolares = value;
    }

    /**
     * Gets the value of the saldoPesos property.
     * 
     */
    public double getSaldoPesos() {
        return saldoPesos;
    }

    /**
     * Sets the value of the saldoPesos property.
     * 
     */
    public void setSaldoPesos(double value) {
        this.saldoPesos = value;
    }

    /**
     * Gets the value of the saldoPesosMasDolares property.
     * 
     */
    public double getSaldoPesosMasDolares() {
        return saldoPesosMasDolares;
    }

    /**
     * Sets the value of the saldoPesosMasDolares property.
     * 
     */
    public void setSaldoPesosMasDolares(double value) {
        this.saldoPesosMasDolares = value;
    }

    /**
     * Gets the value of the tnaHasta12Cuotas property.
     * 
     */
    public double getTNAHasta12Cuotas() {
        return tnaHasta12Cuotas;
    }

    /**
     * Sets the value of the tnaHasta12Cuotas property.
     * 
     */
    public void setTNAHasta12Cuotas(double value) {
        this.tnaHasta12Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta18Cuotas property.
     * 
     */
    public double getTNAHasta18Cuotas() {
        return tnaHasta18Cuotas;
    }

    /**
     * Sets the value of the tnaHasta18Cuotas property.
     * 
     */
    public void setTNAHasta18Cuotas(double value) {
        this.tnaHasta18Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta24Cuotas property.
     * 
     */
    public double getTNAHasta24Cuotas() {
        return tnaHasta24Cuotas;
    }

    /**
     * Sets the value of the tnaHasta24Cuotas property.
     * 
     */
    public void setTNAHasta24Cuotas(double value) {
        this.tnaHasta24Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta36Cuotas property.
     * 
     */
    public double getTNAHasta36Cuotas() {
        return tnaHasta36Cuotas;
    }

    /**
     * Sets the value of the tnaHasta36Cuotas property.
     * 
     */
    public void setTNAHasta36Cuotas(double value) {
        this.tnaHasta36Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta3Cuotas property.
     * 
     */
    public double getTNAHasta3Cuotas() {
        return tnaHasta3Cuotas;
    }

    /**
     * Sets the value of the tnaHasta3Cuotas property.
     * 
     */
    public void setTNAHasta3Cuotas(double value) {
        this.tnaHasta3Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta60Cuotas property.
     * 
     */
    public double getTNAHasta60Cuotas() {
        return tnaHasta60Cuotas;
    }

    /**
     * Sets the value of the tnaHasta60Cuotas property.
     * 
     */
    public void setTNAHasta60Cuotas(double value) {
        this.tnaHasta60Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta6Cuotas property.
     * 
     */
    public double getTNAHasta6Cuotas() {
        return tnaHasta6Cuotas;
    }

    /**
     * Sets the value of the tnaHasta6Cuotas property.
     * 
     */
    public void setTNAHasta6Cuotas(double value) {
        this.tnaHasta6Cuotas = value;
    }

    /**
     * Gets the value of the tnaHasta9Cuotas property.
     * 
     */
    public double getTNAHasta9Cuotas() {
        return tnaHasta9Cuotas;
    }

    /**
     * Sets the value of the tnaHasta9Cuotas property.
     * 
     */
    public void setTNAHasta9Cuotas(double value) {
        this.tnaHasta9Cuotas = value;
    }

    /**
     * Gets the value of the ultimoCierreCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimoCierreCuenta() {
        return ultimoCierreCuenta;
    }

    /**
     * Sets the value of the ultimoCierreCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimoCierreCuenta(String value) {
        this.ultimoCierreCuenta = value;
    }

}
