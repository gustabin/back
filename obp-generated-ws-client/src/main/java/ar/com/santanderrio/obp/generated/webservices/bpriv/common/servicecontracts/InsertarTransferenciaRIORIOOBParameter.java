
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters.BaseParameter;


/**
 * <p>Java class for InsertarTransferenciaRIORIOOBParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InsertarTransferenciaRIORIOOBParameter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CbuDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comision" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaAltairDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaAltairOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuitDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoOrd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertarTransferenciaRIORIOOBParameter", propOrder = {
    "beneficiario",
    "cbuDestino",
    "comision",
    "concepto",
    "cuentaAltairDest",
    "cuentaAltairOrig",
    "cuitDestino",
    "importe",
    "moneda",
    "observaciones",
    "oper",
    "sucursalDest",
    "sucursalOrig",
    "tipoOrd"
})
public class InsertarTransferenciaRIORIOOBParameter
    extends BaseParameter
{

    @XmlElementRef(name = "Beneficiario", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> beneficiario;
    @XmlElementRef(name = "CbuDestino", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cbuDestino;
    @XmlElementRef(name = "Comision", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> comision;
    @XmlElementRef(name = "Concepto", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> concepto;
    @XmlElementRef(name = "CuentaAltairDest", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuentaAltairDest;
    @XmlElementRef(name = "CuentaAltairOrig", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuentaAltairOrig;
    @XmlElementRef(name = "CuitDestino", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuitDestino;
    @XmlElementRef(name = "Importe", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importe;
    @XmlElementRef(name = "Moneda", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Observaciones", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> observaciones;
    @XmlElementRef(name = "Oper", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> oper;
    @XmlElementRef(name = "SucursalDest", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> sucursalDest;
    @XmlElementRef(name = "SucursalOrig", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> sucursalOrig;
    @XmlElementRef(name = "TipoOrd", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> tipoOrd;

    /**
     * Gets the value of the beneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBeneficiario() {
        return beneficiario;
    }

    /**
     * Sets the value of the beneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBeneficiario(JAXBElement<String> value) {
        this.beneficiario = value;
    }

    /**
     * Gets the value of the cbuDestino property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCbuDestino() {
        return cbuDestino;
    }

    /**
     * Sets the value of the cbuDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCbuDestino(JAXBElement<String> value) {
        this.cbuDestino = value;
    }

    /**
     * Gets the value of the comision property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getComision() {
        return comision;
    }

    /**
     * Sets the value of the comision property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setComision(JAXBElement<BigDecimal> value) {
        this.comision = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConcepto(JAXBElement<String> value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the cuentaAltairDest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaAltairDest() {
        return cuentaAltairDest;
    }

    /**
     * Sets the value of the cuentaAltairDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaAltairDest(JAXBElement<String> value) {
        this.cuentaAltairDest = value;
    }

    /**
     * Gets the value of the cuentaAltairOrig property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaAltairOrig() {
        return cuentaAltairOrig;
    }

    /**
     * Sets the value of the cuentaAltairOrig property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaAltairOrig(JAXBElement<String> value) {
        this.cuentaAltairOrig = value;
    }

    /**
     * Gets the value of the cuitDestino property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitDestino() {
        return cuitDestino;
    }

    /**
     * Sets the value of the cuitDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitDestino(JAXBElement<String> value) {
        this.cuitDestino = value;
    }

    /**
     * Gets the value of the importe property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImporte() {
        return importe;
    }

    /**
     * Sets the value of the importe property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImporte(JAXBElement<BigDecimal> value) {
        this.importe = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneda(JAXBElement<String> value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObservaciones(JAXBElement<String> value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the oper property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOper() {
        return oper;
    }

    /**
     * Sets the value of the oper property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOper(JAXBElement<String> value) {
        this.oper = value;
    }

    /**
     * Gets the value of the sucursalDest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalDest() {
        return sucursalDest;
    }

    /**
     * Sets the value of the sucursalDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalDest(JAXBElement<String> value) {
        this.sucursalDest = value;
    }

    /**
     * Gets the value of the sucursalOrig property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalOrig() {
        return sucursalOrig;
    }

    /**
     * Sets the value of the sucursalOrig property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalOrig(JAXBElement<String> value) {
        this.sucursalOrig = value;
    }

    /**
     * Gets the value of the tipoOrd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoOrd() {
        return tipoOrd;
    }

    /**
     * Sets the value of the tipoOrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoOrd(JAXBElement<String> value) {
        this.tipoOrd = value;
    }

}
