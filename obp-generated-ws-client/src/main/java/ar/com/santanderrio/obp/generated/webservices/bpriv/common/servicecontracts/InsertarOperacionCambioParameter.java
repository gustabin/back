
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters.BaseParameter;


/**
 * <p>Java class for InsertarOperacionCambioParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InsertarOperacionCambioParameter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="Boleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantTitu8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Capital" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CuentaDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaOri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuitCuil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Especie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FciTipoCambio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MoneLiq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuitCuil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumMercap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrigenOrden" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrecUni8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="SucuDest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucuOri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoOrd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorInterno8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Xmonto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertarOperacionCambioParameter", propOrder = {
    "boleto",
    "cantTitu8",
    "capital",
    "cuentaDest",
    "cuentaOri",
    "cuitCuil",
    "especie",
    "fciTipoCambio",
    "moneLiq",
    "nroCuitCuil",
    "numMercap",
    "nup",
    "oper",
    "origenOrden",
    "precUni8",
    "sucuDest",
    "sucuOri",
    "tipoOrd",
    "valorInterno8",
    "xmonto"
})
public class InsertarOperacionCambioParameter
    extends BaseParameter
{

    @XmlElementRef(name = "Boleto", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> boleto;
    @XmlElementRef(name = "CantTitu8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cantTitu8;
    @XmlElementRef(name = "Capital", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> capital;
    @XmlElementRef(name = "CuentaDest", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuentaDest;
    @XmlElementRef(name = "CuentaOri", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuentaOri;
    @XmlElementRef(name = "CuitCuil", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cuitCuil;
    @XmlElementRef(name = "Especie", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> especie;
    @XmlElementRef(name = "FciTipoCambio", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fciTipoCambio;
    @XmlElementRef(name = "MoneLiq", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> moneLiq;
    @XmlElementRef(name = "NroCuitCuil", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> nroCuitCuil;
    @XmlElementRef(name = "NumMercap", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> numMercap;
    @XmlElementRef(name = "Nup", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> nup;
    @XmlElementRef(name = "Oper", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> oper;
    @XmlElementRef(name = "OrigenOrden", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> origenOrden;
    @XmlElementRef(name = "PrecUni8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> precUni8;
    @XmlElementRef(name = "SucuDest", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> sucuDest;
    @XmlElementRef(name = "SucuOri", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> sucuOri;
    @XmlElementRef(name = "TipoOrd", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> tipoOrd;
    @XmlElementRef(name = "ValorInterno8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> valorInterno8;
    @XmlElementRef(name = "Xmonto", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> xmonto;

    /**
     * Gets the value of the boleto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBoleto() {
        return boleto;
    }

    /**
     * Sets the value of the boleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBoleto(JAXBElement<String> value) {
        this.boleto = value;
    }

    /**
     * Gets the value of the cantTitu8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCantTitu8() {
        return cantTitu8;
    }

    /**
     * Sets the value of the cantTitu8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCantTitu8(JAXBElement<BigDecimal> value) {
        this.cantTitu8 = value;
    }

    /**
     * Gets the value of the capital property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCapital() {
        return capital;
    }

    /**
     * Sets the value of the capital property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCapital(JAXBElement<BigDecimal> value) {
        this.capital = value;
    }

    /**
     * Gets the value of the cuentaDest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaDest() {
        return cuentaDest;
    }

    /**
     * Sets the value of the cuentaDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaDest(JAXBElement<String> value) {
        this.cuentaDest = value;
    }

    /**
     * Gets the value of the cuentaOri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaOri() {
        return cuentaOri;
    }

    /**
     * Sets the value of the cuentaOri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaOri(JAXBElement<String> value) {
        this.cuentaOri = value;
    }

    /**
     * Gets the value of the cuitCuil property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitCuil() {
        return cuitCuil;
    }

    /**
     * Sets the value of the cuitCuil property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitCuil(JAXBElement<String> value) {
        this.cuitCuil = value;
    }

    /**
     * Gets the value of the especie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEspecie() {
        return especie;
    }

    /**
     * Sets the value of the especie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEspecie(JAXBElement<String> value) {
        this.especie = value;
    }

    /**
     * Gets the value of the fciTipoCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFciTipoCambio() {
        return fciTipoCambio;
    }

    /**
     * Sets the value of the fciTipoCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFciTipoCambio(JAXBElement<BigDecimal> value) {
        this.fciTipoCambio = value;
    }

    /**
     * Gets the value of the moneLiq property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneLiq() {
        return moneLiq;
    }

    /**
     * Sets the value of the moneLiq property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneLiq(JAXBElement<String> value) {
        this.moneLiq = value;
    }

    /**
     * Gets the value of the nroCuitCuil property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuitCuil() {
        return nroCuitCuil;
    }

    /**
     * Sets the value of the nroCuitCuil property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuitCuil(JAXBElement<String> value) {
        this.nroCuitCuil = value;
    }

    /**
     * Gets the value of the numMercap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumMercap() {
        return numMercap;
    }

    /**
     * Sets the value of the numMercap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumMercap(JAXBElement<String> value) {
        this.numMercap = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup(JAXBElement<String> value) {
        this.nup = value;
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
     * Gets the value of the origenOrden property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigenOrden() {
        return origenOrden;
    }

    /**
     * Sets the value of the origenOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigenOrden(JAXBElement<String> value) {
        this.origenOrden = value;
    }

    /**
     * Gets the value of the precUni8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPrecUni8() {
        return precUni8;
    }

    /**
     * Sets the value of the precUni8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPrecUni8(JAXBElement<BigDecimal> value) {
        this.precUni8 = value;
    }

    /**
     * Gets the value of the sucuDest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucuDest() {
        return sucuDest;
    }

    /**
     * Sets the value of the sucuDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucuDest(JAXBElement<String> value) {
        this.sucuDest = value;
    }

    /**
     * Gets the value of the sucuOri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucuOri() {
        return sucuOri;
    }

    /**
     * Sets the value of the sucuOri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucuOri(JAXBElement<String> value) {
        this.sucuOri = value;
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

    /**
     * Gets the value of the valorInterno8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getValorInterno8() {
        return valorInterno8;
    }

    /**
     * Sets the value of the valorInterno8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setValorInterno8(JAXBElement<BigDecimal> value) {
        this.valorInterno8 = value;
    }

    /**
     * Gets the value of the xmonto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXmonto() {
        return xmonto;
    }

    /**
     * Sets the value of the xmonto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXmonto(JAXBElement<String> value) {
        this.xmonto = value;
    }

}
