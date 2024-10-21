
package ar.com.santanderrio.obp.generated.webservices.segmento;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetClientDetailedPositionByNupResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetClientDetailedPositionByNupResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AntiguedadTotalBsr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CantidadMesesInvTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CantidadMesesMBB" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CantidadMesesSaldoCta" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CuadranteCRM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoMBBPromedio6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MontoMinInvTotal6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MontoMinimoMBBU6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MontoTenenciaAccionesBonos" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MontoTenenciaPlazoFijo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioConsumosDebito6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioConsumosTarjAmex6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioConsumosTarjVisa6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioExtraccionesDebito6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioInversionesTotal6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PromedioSaldoCuenta6M" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClientDetailedPositionByNupResponse", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", propOrder = {
    "antiguedadTotalBsr",
    "cantidadMesesInvTotal",
    "cantidadMesesMBB",
    "cantidadMesesSaldoCta",
    "cuadranteCRM",
    "montoMBBPromedio6M",
    "montoMinInvTotal6M",
    "montoMinimoMBBU6M",
    "montoTenenciaAccionesBonos",
    "montoTenenciaPlazoFijo",
    "promedioConsumosDebito6M",
    "promedioConsumosTarjAmex6M",
    "promedioConsumosTarjVisa6M",
    "promedioExtraccionesDebito6M",
    "promedioInversionesTotal6M",
    "promedioSaldoCuenta6M"
})
public class GetClientDetailedPositionByNupResponse2 {

    @XmlElementRef(name = "AntiguedadTotalBsr", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> antiguedadTotalBsr;
    @XmlElementRef(name = "CantidadMesesInvTotal", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cantidadMesesInvTotal;
    @XmlElementRef(name = "CantidadMesesMBB", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cantidadMesesMBB;
    @XmlElementRef(name = "CantidadMesesSaldoCta", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cantidadMesesSaldoCta;
    @XmlElementRef(name = "CuadranteCRM", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> cuadranteCRM;
    @XmlElementRef(name = "MontoMBBPromedio6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoMBBPromedio6M;
    @XmlElementRef(name = "MontoMinInvTotal6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoMinInvTotal6M;
    @XmlElementRef(name = "MontoMinimoMBBU6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoMinimoMBBU6M;
    @XmlElementRef(name = "MontoTenenciaAccionesBonos", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoTenenciaAccionesBonos;
    @XmlElementRef(name = "MontoTenenciaPlazoFijo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoTenenciaPlazoFijo;
    @XmlElementRef(name = "PromedioConsumosDebito6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioConsumosDebito6M;
    @XmlElementRef(name = "PromedioConsumosTarjAmex6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioConsumosTarjAmex6M;
    @XmlElementRef(name = "PromedioConsumosTarjVisa6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioConsumosTarjVisa6M;
    @XmlElementRef(name = "PromedioExtraccionesDebito6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioExtraccionesDebito6M;
    @XmlElementRef(name = "PromedioInversionesTotal6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioInversionesTotal6M;
    @XmlElementRef(name = "PromedioSaldoCuenta6M", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> promedioSaldoCuenta6M;

    /**
     * Gets the value of the antiguedadTotalBsr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAntiguedadTotalBsr() {
        return antiguedadTotalBsr;
    }

    /**
     * Sets the value of the antiguedadTotalBsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAntiguedadTotalBsr(JAXBElement<BigDecimal> value) {
        this.antiguedadTotalBsr = value;
    }

    /**
     * Gets the value of the cantidadMesesInvTotal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCantidadMesesInvTotal() {
        return cantidadMesesInvTotal;
    }

    /**
     * Sets the value of the cantidadMesesInvTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCantidadMesesInvTotal(JAXBElement<BigDecimal> value) {
        this.cantidadMesesInvTotal = value;
    }

    /**
     * Gets the value of the cantidadMesesMBB property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCantidadMesesMBB() {
        return cantidadMesesMBB;
    }

    /**
     * Sets the value of the cantidadMesesMBB property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCantidadMesesMBB(JAXBElement<BigDecimal> value) {
        this.cantidadMesesMBB = value;
    }

    /**
     * Gets the value of the cantidadMesesSaldoCta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCantidadMesesSaldoCta() {
        return cantidadMesesSaldoCta;
    }

    /**
     * Sets the value of the cantidadMesesSaldoCta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCantidadMesesSaldoCta(JAXBElement<BigDecimal> value) {
        this.cantidadMesesSaldoCta = value;
    }

    /**
     * Gets the value of the cuadranteCRM property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuadranteCRM() {
        return cuadranteCRM;
    }

    /**
     * Sets the value of the cuadranteCRM property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuadranteCRM(JAXBElement<String> value) {
        this.cuadranteCRM = value;
    }

    /**
     * Gets the value of the montoMBBPromedio6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoMBBPromedio6M() {
        return montoMBBPromedio6M;
    }

    /**
     * Sets the value of the montoMBBPromedio6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoMBBPromedio6M(JAXBElement<BigDecimal> value) {
        this.montoMBBPromedio6M = value;
    }

    /**
     * Gets the value of the montoMinInvTotal6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoMinInvTotal6M() {
        return montoMinInvTotal6M;
    }

    /**
     * Sets the value of the montoMinInvTotal6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoMinInvTotal6M(JAXBElement<BigDecimal> value) {
        this.montoMinInvTotal6M = value;
    }

    /**
     * Gets the value of the montoMinimoMBBU6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoMinimoMBBU6M() {
        return montoMinimoMBBU6M;
    }

    /**
     * Sets the value of the montoMinimoMBBU6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoMinimoMBBU6M(JAXBElement<BigDecimal> value) {
        this.montoMinimoMBBU6M = value;
    }

    /**
     * Gets the value of the montoTenenciaAccionesBonos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoTenenciaAccionesBonos() {
        return montoTenenciaAccionesBonos;
    }

    /**
     * Sets the value of the montoTenenciaAccionesBonos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoTenenciaAccionesBonos(JAXBElement<BigDecimal> value) {
        this.montoTenenciaAccionesBonos = value;
    }

    /**
     * Gets the value of the montoTenenciaPlazoFijo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoTenenciaPlazoFijo() {
        return montoTenenciaPlazoFijo;
    }

    /**
     * Sets the value of the montoTenenciaPlazoFijo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoTenenciaPlazoFijo(JAXBElement<BigDecimal> value) {
        this.montoTenenciaPlazoFijo = value;
    }

    /**
     * Gets the value of the promedioConsumosDebito6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioConsumosDebito6M() {
        return promedioConsumosDebito6M;
    }

    /**
     * Sets the value of the promedioConsumosDebito6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioConsumosDebito6M(JAXBElement<BigDecimal> value) {
        this.promedioConsumosDebito6M = value;
    }

    /**
     * Gets the value of the promedioConsumosTarjAmex6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioConsumosTarjAmex6M() {
        return promedioConsumosTarjAmex6M;
    }

    /**
     * Sets the value of the promedioConsumosTarjAmex6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioConsumosTarjAmex6M(JAXBElement<BigDecimal> value) {
        this.promedioConsumosTarjAmex6M = value;
    }

    /**
     * Gets the value of the promedioConsumosTarjVisa6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioConsumosTarjVisa6M() {
        return promedioConsumosTarjVisa6M;
    }

    /**
     * Sets the value of the promedioConsumosTarjVisa6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioConsumosTarjVisa6M(JAXBElement<BigDecimal> value) {
        this.promedioConsumosTarjVisa6M = value;
    }

    /**
     * Gets the value of the promedioExtraccionesDebito6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioExtraccionesDebito6M() {
        return promedioExtraccionesDebito6M;
    }

    /**
     * Sets the value of the promedioExtraccionesDebito6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioExtraccionesDebito6M(JAXBElement<BigDecimal> value) {
        this.promedioExtraccionesDebito6M = value;
    }

    /**
     * Gets the value of the promedioInversionesTotal6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioInversionesTotal6M() {
        return promedioInversionesTotal6M;
    }

    /**
     * Sets the value of the promedioInversionesTotal6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioInversionesTotal6M(JAXBElement<BigDecimal> value) {
        this.promedioInversionesTotal6M = value;
    }

    /**
     * Gets the value of the promedioSaldoCuenta6M property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPromedioSaldoCuenta6M() {
        return promedioSaldoCuenta6M;
    }

    /**
     * Sets the value of the promedioSaldoCuenta6M property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPromedioSaldoCuenta6M(JAXBElement<BigDecimal> value) {
        this.promedioSaldoCuenta6M = value;
    }

}
