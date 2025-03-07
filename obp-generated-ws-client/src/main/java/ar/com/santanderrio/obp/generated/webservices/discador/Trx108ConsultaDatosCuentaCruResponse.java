
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx108ConsultaDatosCuentaCruResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx108ConsultaDatosCuentaCruResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ConsultaDatosCuentaResponseBase220">
 *       &lt;sequence>
 *         &lt;element name="Acumulado_ARS_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Acumulado_USD_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_24hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_24hs_CC_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_48hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_48hs_CC_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_72hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_72hs_CC_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_Efectivo_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_Efectivo_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disponible_ARS_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disponible_USD_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disponile_ARS_Menor_Cuenta_del_Conjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Disponile_USD_Menor_Cuenta_del_Conjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Indicador_Direcciona_CA_Peticion_Cambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Intereses_Ganadas_CA_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Intereses_Ganados_CA_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Limite_Acuerdo_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Limite_Acuerdo_CC_USD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_ACAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_ACAH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_ACCD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_ACTE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tope_ARS_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tope_USD_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx108ConsultaDatosCuentaCruResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", propOrder = {
    "acumuladoARSCuenta",
    "acumuladoUSDCuenta",
    "depositos24HsCCARS",
    "depositos24HsCCUSD",
    "depositos48HsCCARS",
    "depositos48HsCCUSD",
    "depositos72HsCCARS",
    "depositos72HsCCUSD",
    "depositosEfectivoARS",
    "depositosEfectivoUSD",
    "disponibleARSCuenta",
    "disponibleUSDCuenta",
    "disponileARSMenorCuentaDelConjunto",
    "disponileUSDMenorCuentaDelConjunto",
    "indicadorDireccionaCAPeticionCambio",
    "interesesGanadasCAARS",
    "interesesGanadosCAUSD",
    "limiteAcuerdoCCARS",
    "limiteAcuerdoCCUSD",
    "saldoACAD",
    "saldoACAH",
    "saldoACCD",
    "saldoACTE",
    "topeARSCuenta",
    "topeUSDCuenta"
})
public class Trx108ConsultaDatosCuentaCruResponse
    extends ConsultaDatosCuentaResponseBase220
{

    @XmlElementRef(name = "Acumulado_ARS_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> acumuladoARSCuenta;
    @XmlElementRef(name = "Acumulado_USD_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> acumuladoUSDCuenta;
    @XmlElementRef(name = "Depositos_24hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos24HsCCARS;
    @XmlElementRef(name = "Depositos_24hs_CC_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos24HsCCUSD;
    @XmlElementRef(name = "Depositos_48hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos48HsCCARS;
    @XmlElementRef(name = "Depositos_48hs_CC_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos48HsCCUSD;
    @XmlElementRef(name = "Depositos_72hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos72HsCCARS;
    @XmlElementRef(name = "Depositos_72hs_CC_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositos72HsCCUSD;
    @XmlElementRef(name = "Depositos_Efectivo_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivoARS;
    @XmlElementRef(name = "Depositos_Efectivo_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> depositosEfectivoUSD;
    @XmlElementRef(name = "Disponible_ARS_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> disponibleARSCuenta;
    @XmlElementRef(name = "Disponible_USD_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> disponibleUSDCuenta;
    @XmlElementRef(name = "Disponile_ARS_Menor_Cuenta_del_Conjunto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> disponileARSMenorCuentaDelConjunto;
    @XmlElementRef(name = "Disponile_USD_Menor_Cuenta_del_Conjunto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> disponileUSDMenorCuentaDelConjunto;
    @XmlElementRef(name = "Indicador_Direcciona_CA_Peticion_Cambio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> indicadorDireccionaCAPeticionCambio;
    @XmlElementRef(name = "Intereses_Ganadas_CA_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> interesesGanadasCAARS;
    @XmlElementRef(name = "Intereses_Ganados_CA_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> interesesGanadosCAUSD;
    @XmlElementRef(name = "Limite_Acuerdo_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> limiteAcuerdoCCARS;
    @XmlElementRef(name = "Limite_Acuerdo_CC_USD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> limiteAcuerdoCCUSD;
    @XmlElementRef(name = "Saldo_ACAD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> saldoACAD;
    @XmlElementRef(name = "Saldo_ACAH", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> saldoACAH;
    @XmlElementRef(name = "Saldo_ACCD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> saldoACCD;
    @XmlElementRef(name = "Saldo_ACTE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> saldoACTE;
    @XmlElementRef(name = "Tope_ARS_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> topeARSCuenta;
    @XmlElementRef(name = "Tope_USD_Cuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx108", type = JAXBElement.class)
    protected JAXBElement<String> topeUSDCuenta;

    /**
     * Gets the value of the acumuladoARSCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcumuladoARSCuenta() {
        return acumuladoARSCuenta;
    }

    /**
     * Sets the value of the acumuladoARSCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcumuladoARSCuenta(JAXBElement<String> value) {
        this.acumuladoARSCuenta = value;
    }

    /**
     * Gets the value of the acumuladoUSDCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAcumuladoUSDCuenta() {
        return acumuladoUSDCuenta;
    }

    /**
     * Sets the value of the acumuladoUSDCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcumuladoUSDCuenta(JAXBElement<String> value) {
        this.acumuladoUSDCuenta = value;
    }

    /**
     * Gets the value of the depositos24HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos24HsCCARS() {
        return depositos24HsCCARS;
    }

    /**
     * Sets the value of the depositos24HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos24HsCCARS(JAXBElement<String> value) {
        this.depositos24HsCCARS = value;
    }

    /**
     * Gets the value of the depositos24HsCCUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos24HsCCUSD() {
        return depositos24HsCCUSD;
    }

    /**
     * Sets the value of the depositos24HsCCUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos24HsCCUSD(JAXBElement<String> value) {
        this.depositos24HsCCUSD = value;
    }

    /**
     * Gets the value of the depositos48HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos48HsCCARS() {
        return depositos48HsCCARS;
    }

    /**
     * Sets the value of the depositos48HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos48HsCCARS(JAXBElement<String> value) {
        this.depositos48HsCCARS = value;
    }

    /**
     * Gets the value of the depositos48HsCCUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos48HsCCUSD() {
        return depositos48HsCCUSD;
    }

    /**
     * Sets the value of the depositos48HsCCUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos48HsCCUSD(JAXBElement<String> value) {
        this.depositos48HsCCUSD = value;
    }

    /**
     * Gets the value of the depositos72HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos72HsCCARS() {
        return depositos72HsCCARS;
    }

    /**
     * Sets the value of the depositos72HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos72HsCCARS(JAXBElement<String> value) {
        this.depositos72HsCCARS = value;
    }

    /**
     * Gets the value of the depositos72HsCCUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos72HsCCUSD() {
        return depositos72HsCCUSD;
    }

    /**
     * Sets the value of the depositos72HsCCUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos72HsCCUSD(JAXBElement<String> value) {
        this.depositos72HsCCUSD = value;
    }

    /**
     * Gets the value of the depositosEfectivoARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositosEfectivoARS() {
        return depositosEfectivoARS;
    }

    /**
     * Sets the value of the depositosEfectivoARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositosEfectivoARS(JAXBElement<String> value) {
        this.depositosEfectivoARS = value;
    }

    /**
     * Gets the value of the depositosEfectivoUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositosEfectivoUSD() {
        return depositosEfectivoUSD;
    }

    /**
     * Sets the value of the depositosEfectivoUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositosEfectivoUSD(JAXBElement<String> value) {
        this.depositosEfectivoUSD = value;
    }

    /**
     * Gets the value of the disponibleARSCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisponibleARSCuenta() {
        return disponibleARSCuenta;
    }

    /**
     * Sets the value of the disponibleARSCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisponibleARSCuenta(JAXBElement<String> value) {
        this.disponibleARSCuenta = value;
    }

    /**
     * Gets the value of the disponibleUSDCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisponibleUSDCuenta() {
        return disponibleUSDCuenta;
    }

    /**
     * Sets the value of the disponibleUSDCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisponibleUSDCuenta(JAXBElement<String> value) {
        this.disponibleUSDCuenta = value;
    }

    /**
     * Gets the value of the disponileARSMenorCuentaDelConjunto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisponileARSMenorCuentaDelConjunto() {
        return disponileARSMenorCuentaDelConjunto;
    }

    /**
     * Sets the value of the disponileARSMenorCuentaDelConjunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisponileARSMenorCuentaDelConjunto(JAXBElement<String> value) {
        this.disponileARSMenorCuentaDelConjunto = value;
    }

    /**
     * Gets the value of the disponileUSDMenorCuentaDelConjunto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisponileUSDMenorCuentaDelConjunto() {
        return disponileUSDMenorCuentaDelConjunto;
    }

    /**
     * Sets the value of the disponileUSDMenorCuentaDelConjunto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisponileUSDMenorCuentaDelConjunto(JAXBElement<String> value) {
        this.disponileUSDMenorCuentaDelConjunto = value;
    }

    /**
     * Gets the value of the indicadorDireccionaCAPeticionCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorDireccionaCAPeticionCambio() {
        return indicadorDireccionaCAPeticionCambio;
    }

    /**
     * Sets the value of the indicadorDireccionaCAPeticionCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorDireccionaCAPeticionCambio(JAXBElement<String> value) {
        this.indicadorDireccionaCAPeticionCambio = value;
    }

    /**
     * Gets the value of the interesesGanadasCAARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteresesGanadasCAARS() {
        return interesesGanadasCAARS;
    }

    /**
     * Sets the value of the interesesGanadasCAARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteresesGanadasCAARS(JAXBElement<String> value) {
        this.interesesGanadasCAARS = value;
    }

    /**
     * Gets the value of the interesesGanadosCAUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInteresesGanadosCAUSD() {
        return interesesGanadosCAUSD;
    }

    /**
     * Sets the value of the interesesGanadosCAUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInteresesGanadosCAUSD(JAXBElement<String> value) {
        this.interesesGanadosCAUSD = value;
    }

    /**
     * Gets the value of the limiteAcuerdoCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteAcuerdoCCARS() {
        return limiteAcuerdoCCARS;
    }

    /**
     * Sets the value of the limiteAcuerdoCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteAcuerdoCCARS(JAXBElement<String> value) {
        this.limiteAcuerdoCCARS = value;
    }

    /**
     * Gets the value of the limiteAcuerdoCCUSD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLimiteAcuerdoCCUSD() {
        return limiteAcuerdoCCUSD;
    }

    /**
     * Sets the value of the limiteAcuerdoCCUSD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLimiteAcuerdoCCUSD(JAXBElement<String> value) {
        this.limiteAcuerdoCCUSD = value;
    }

    /**
     * Gets the value of the saldoACAD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoACAD() {
        return saldoACAD;
    }

    /**
     * Sets the value of the saldoACAD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoACAD(JAXBElement<String> value) {
        this.saldoACAD = value;
    }

    /**
     * Gets the value of the saldoACAH property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoACAH() {
        return saldoACAH;
    }

    /**
     * Sets the value of the saldoACAH property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoACAH(JAXBElement<String> value) {
        this.saldoACAH = value;
    }

    /**
     * Gets the value of the saldoACCD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoACCD() {
        return saldoACCD;
    }

    /**
     * Sets the value of the saldoACCD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoACCD(JAXBElement<String> value) {
        this.saldoACCD = value;
    }

    /**
     * Gets the value of the saldoACTE property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSaldoACTE() {
        return saldoACTE;
    }

    /**
     * Sets the value of the saldoACTE property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSaldoACTE(JAXBElement<String> value) {
        this.saldoACTE = value;
    }

    /**
     * Gets the value of the topeARSCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTopeARSCuenta() {
        return topeARSCuenta;
    }

    /**
     * Sets the value of the topeARSCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTopeARSCuenta(JAXBElement<String> value) {
        this.topeARSCuenta = value;
    }

    /**
     * Gets the value of the topeUSDCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTopeUSDCuenta() {
        return topeUSDCuenta;
    }

    /**
     * Sets the value of the topeUSDCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTopeUSDCuenta(JAXBElement<String> value) {
        this.topeUSDCuenta = value;
    }

}
