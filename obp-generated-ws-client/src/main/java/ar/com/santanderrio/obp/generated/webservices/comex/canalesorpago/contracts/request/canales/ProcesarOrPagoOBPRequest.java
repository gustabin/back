
package ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.FirmaRequest;


/**
 * <p>Java class for ProcesarOrPagoOBPRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcesarOrPagoOBPRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Acepta_Ddjj" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta_Credito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Empresa_Vinculada" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Importe_Pago" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Nro_Doc_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Form" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Nro_Operacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Razon_Social" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Doc_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesarOrPagoOBPRequest", propOrder = {
    "aceptaDdjj",
    "concepto",
    "cuentaCredito",
    "empresaVinculada",
    "importePago",
    "nroDocCliente",
    "nroForm",
    "nroOperacion",
    "nupCliente",
    "razonSocial",
    "tipoDocCliente"
})
public class ProcesarOrPagoOBPRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Acepta_Ddjj", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<Short> aceptaDdjj;
    @XmlElementRef(name = "Concepto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> concepto;
    @XmlElementRef(name = "Cuenta_Credito", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> cuentaCredito;
    @XmlElementRef(name = "Empresa_Vinculada", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<Short> empresaVinculada;
    @XmlElementRef(name = "Importe_Pago", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> importePago;
    @XmlElementRef(name = "Nro_Doc_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> nroDocCliente;
    @XmlElementRef(name = "Nro_Form", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<Integer> nroForm;
    @XmlElementRef(name = "Nro_Operacion", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> nroOperacion;
    @XmlElementRef(name = "Nup_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> nupCliente;
    @XmlElementRef(name = "Razon_Social", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> razonSocial;
    @XmlElementRef(name = "Tipo_Doc_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesOrPago", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocCliente;

    /**
     * Gets the value of the aceptaDdjj property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getAceptaDdjj() {
        return aceptaDdjj;
    }

    /**
     * Sets the value of the aceptaDdjj property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setAceptaDdjj(JAXBElement<Short> value) {
        this.aceptaDdjj = value;
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
     * Gets the value of the cuentaCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuentaCredito() {
        return cuentaCredito;
    }

    /**
     * Sets the value of the cuentaCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuentaCredito(JAXBElement<String> value) {
        this.cuentaCredito = value;
    }

    /**
     * Gets the value of the empresaVinculada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getEmpresaVinculada() {
        return empresaVinculada;
    }

    /**
     * Sets the value of the empresaVinculada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setEmpresaVinculada(JAXBElement<Short> value) {
        this.empresaVinculada = value;
    }

    /**
     * Gets the value of the importePago property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getImportePago() {
        return importePago;
    }

    /**
     * Sets the value of the importePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setImportePago(JAXBElement<BigDecimal> value) {
        this.importePago = value;
    }

    /**
     * Gets the value of the nroDocCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDocCliente() {
        return nroDocCliente;
    }

    /**
     * Sets the value of the nroDocCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDocCliente(JAXBElement<String> value) {
        this.nroDocCliente = value;
    }

    /**
     * Gets the value of the nroForm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNroForm() {
        return nroForm;
    }

    /**
     * Sets the value of the nroForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNroForm(JAXBElement<Integer> value) {
        this.nroForm = value;
    }

    /**
     * Gets the value of the nroOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroOperacion() {
        return nroOperacion;
    }

    /**
     * Sets the value of the nroOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroOperacion(JAXBElement<String> value) {
        this.nroOperacion = value;
    }

    /**
     * Gets the value of the nupCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupCliente() {
        return nupCliente;
    }

    /**
     * Sets the value of the nupCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupCliente(JAXBElement<String> value) {
        this.nupCliente = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRazonSocial(JAXBElement<String> value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the tipoDocCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocCliente() {
        return tipoDocCliente;
    }

    /**
     * Sets the value of the tipoDocCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocCliente(JAXBElement<String> value) {
        this.tipoDocCliente = value;
    }

}
