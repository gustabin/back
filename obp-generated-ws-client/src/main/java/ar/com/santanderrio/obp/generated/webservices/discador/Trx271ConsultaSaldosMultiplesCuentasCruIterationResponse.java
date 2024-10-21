
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="DepositosEfectivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorDireccionCa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InteresesGanadosCa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimiteAcuerdoCc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPorConformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SignoSaldoPorConformar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", propOrder = {
    "depositosEfectivo",
    "indicadorDireccionCa",
    "interesesGanadosCa",
    "limiteAcuerdoCc",
    "nroCuenta",
    "nroSucursal",
    "saldoCuenta",
    "saldoPorConformar",
    "signoSaldoCuenta",
    "signoSaldoPorConformar",
    "tipoCuenta"
})
public class Trx271ConsultaSaldosMultiplesCuentasCruIterationResponse
    extends MappingModelBase
{

    @XmlElementRef(name = "DepositosEfectivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivo;
    @XmlElementRef(name = "IndicadorDireccionCa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> indicadorDireccionCa;
    @XmlElementRef(name = "InteresesGanadosCa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> interesesGanadosCa;
    @XmlElementRef(name = "LimiteAcuerdoCc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> limiteAcuerdoCc;
    @XmlElementRef(name = "NroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> nroCuenta;
    @XmlElementRef(name = "NroSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> nroSucursal;
    @XmlElementRef(name = "SaldoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> saldoCuenta;
    @XmlElementRef(name = "SaldoPorConformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> saldoPorConformar;
    @XmlElementRef(name = "SignoSaldoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoCuenta;
    @XmlElementRef(name = "SignoSaldoPorConformar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> signoSaldoPorConformar;
    @XmlElementRef(name = "TipoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx271", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;

    /**
     * Gets the value of the depositosEfectivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositosEfectivo() {
        return depositosEfectivo;
    }

    /**
     * Sets the value of the depositosEfectivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositosEfectivo(JAXBElement<String> value) {
        this.depositosEfectivo = value;
    }

    /**
     * Gets the value of the indicadorDireccionCa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorDireccionCa() {
        return indicadorDireccionCa;
    }

    /**
     * Sets the value of the indicadorDireccionCa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorDireccionCa(JAXBElement<String> value) {
        this.indicadorDireccionCa = value;
    }

    /**
     * Gets the value of the interesesGanadosCa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteresesGanadosCa() {
        return interesesGanadosCa;
    }

    /**
     * Sets the value of the interesesGanadosCa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteresesGanadosCa(JAXBElement<String> value) {
        this.interesesGanadosCa = value;
    }

    /**
     * Gets the value of the limiteAcuerdoCc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteAcuerdoCc() {
        return limiteAcuerdoCc;
    }

    /**
     * Sets the value of the limiteAcuerdoCc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteAcuerdoCc(JAXBElement<String> value) {
        this.limiteAcuerdoCc = value;
    }

    /**
     * Gets the value of the nroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the value of the nroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuenta(JAXBElement<String> value) {
        this.nroCuenta = value;
    }

    /**
     * Gets the value of the nroSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroSucursal() {
        return nroSucursal;
    }

    /**
     * Sets the value of the nroSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroSucursal(JAXBElement<String> value) {
        this.nroSucursal = value;
    }

    /**
     * Gets the value of the saldoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Sets the value of the saldoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoCuenta(JAXBElement<String> value) {
        this.saldoCuenta = value;
    }

    /**
     * Gets the value of the saldoPorConformar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoPorConformar() {
        return saldoPorConformar;
    }

    /**
     * Sets the value of the saldoPorConformar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoPorConformar(JAXBElement<String> value) {
        this.saldoPorConformar = value;
    }

    /**
     * Gets the value of the signoSaldoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoCuenta() {
        return signoSaldoCuenta;
    }

    /**
     * Sets the value of the signoSaldoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoCuenta(JAXBElement<String> value) {
        this.signoSaldoCuenta = value;
    }

    /**
     * Gets the value of the signoSaldoPorConformar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSignoSaldoPorConformar() {
        return signoSaldoPorConformar;
    }

    /**
     * Sets the value of the signoSaldoPorConformar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSignoSaldoPorConformar(JAXBElement<String> value) {
        this.signoSaldoPorConformar = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuenta(JAXBElement<String> value) {
        this.tipoCuenta = value;
    }

}
