
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx742MdlwConsultaPlazoFijoNoAltairUviRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx742MdlwConsultaPlazoFijoNoAltairUviRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="CantRegConsultar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="CuentaPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaRellamada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Custodia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaContable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaDesde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaHasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorEstado2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorEstadoPf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Secuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecuenciaRellamada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoFecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx742MdlwConsultaPlazoFijoNoAltairUviRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", propOrder = {
    "cantRegConsultar",
    "cliente",
    "cuentaPlazo",
    "cuentaRellamada",
    "custodia",
    "ejecutivo",
    "fechaContable",
    "fechaDesde",
    "fechaHasta",
    "indicadorEstado2",
    "indicadorEstadoPf",
    "secuencia",
    "secuenciaRellamada",
    "sucursalCuenta",
    "tipoFecha"
})
public class Trx742MdlwConsultaPlazoFijoNoAltairUviRequest
    extends RequestBase
{

    @XmlElementRef(name = "CantRegConsultar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> cantRegConsultar;
    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "CuentaPlazo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> cuentaPlazo;
    @XmlElementRef(name = "CuentaRellamada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> cuentaRellamada;
    @XmlElementRef(name = "Custodia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> custodia;
    @XmlElementRef(name = "Ejecutivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivo;
    @XmlElementRef(name = "FechaContable", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> fechaContable;
    @XmlElementRef(name = "FechaDesde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> fechaDesde;
    @XmlElementRef(name = "FechaHasta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> fechaHasta;
    @XmlElementRef(name = "IndicadorEstado2", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> indicadorEstado2;
    @XmlElementRef(name = "IndicadorEstadoPf", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> indicadorEstadoPf;
    @XmlElementRef(name = "Secuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> secuencia;
    @XmlElementRef(name = "SecuenciaRellamada", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> secuenciaRellamada;
    @XmlElementRef(name = "SucursalCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuenta;
    @XmlElementRef(name = "TipoFecha", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx742", type = JAXBElement.class)
    protected JAXBElement<String> tipoFecha;

    /**
     * Gets the value of the cantRegConsultar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantRegConsultar() {
        return cantRegConsultar;
    }

    /**
     * Sets the value of the cantRegConsultar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantRegConsultar(JAXBElement<String> value) {
        this.cantRegConsultar = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public JAXBElement<TDatosClienteRequest> getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public void setCliente(JAXBElement<TDatosClienteRequest> value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the cuentaPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaPlazo() {
        return cuentaPlazo;
    }

    /**
     * Sets the value of the cuentaPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaPlazo(JAXBElement<String> value) {
        this.cuentaPlazo = value;
    }

    /**
     * Gets the value of the cuentaRellamada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaRellamada() {
        return cuentaRellamada;
    }

    /**
     * Sets the value of the cuentaRellamada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaRellamada(JAXBElement<String> value) {
        this.cuentaRellamada = value;
    }

    /**
     * Gets the value of the custodia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCustodia() {
        return custodia;
    }

    /**
     * Sets the value of the custodia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCustodia(JAXBElement<String> value) {
        this.custodia = value;
    }

    /**
     * Gets the value of the ejecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEjecutivo() {
        return ejecutivo;
    }

    /**
     * Sets the value of the ejecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEjecutivo(JAXBElement<String> value) {
        this.ejecutivo = value;
    }

    /**
     * Gets the value of the fechaContable property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaContable() {
        return fechaContable;
    }

    /**
     * Sets the value of the fechaContable property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaContable(JAXBElement<String> value) {
        this.fechaContable = value;
    }

    /**
     * Gets the value of the fechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the value of the fechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaDesde(JAXBElement<String> value) {
        this.fechaDesde = value;
    }

    /**
     * Gets the value of the fechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the value of the fechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaHasta(JAXBElement<String> value) {
        this.fechaHasta = value;
    }

    /**
     * Gets the value of the indicadorEstado2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorEstado2() {
        return indicadorEstado2;
    }

    /**
     * Sets the value of the indicadorEstado2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorEstado2(JAXBElement<String> value) {
        this.indicadorEstado2 = value;
    }

    /**
     * Gets the value of the indicadorEstadoPf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorEstadoPf() {
        return indicadorEstadoPf;
    }

    /**
     * Sets the value of the indicadorEstadoPf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorEstadoPf(JAXBElement<String> value) {
        this.indicadorEstadoPf = value;
    }

    /**
     * Gets the value of the secuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuencia() {
        return secuencia;
    }

    /**
     * Sets the value of the secuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuencia(JAXBElement<String> value) {
        this.secuencia = value;
    }

    /**
     * Gets the value of the secuenciaRellamada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecuenciaRellamada() {
        return secuenciaRellamada;
    }

    /**
     * Sets the value of the secuenciaRellamada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecuenciaRellamada(JAXBElement<String> value) {
        this.secuenciaRellamada = value;
    }

    /**
     * Gets the value of the sucursalCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuenta() {
        return sucursalCuenta;
    }

    /**
     * Sets the value of the sucursalCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuenta(JAXBElement<String> value) {
        this.sucursalCuenta = value;
    }

    /**
     * Gets the value of the tipoFecha property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoFecha() {
        return tipoFecha;
    }

    /**
     * Sets the value of the tipoFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoFecha(JAXBElement<String> value) {
        this.tipoFecha = value;
    }

}
