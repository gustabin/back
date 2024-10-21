
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx738MdlwSimulacionBancoVendeMonedaExtranjeraRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx738MdlwSimulacionBancoVendeMonedaExtranjeraRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}SimulacionCompraVentaMonedaExtranjeraBase">
 *       &lt;sequence>
 *         &lt;element name="CondCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaIngrPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomApell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipRefAfip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx738MdlwSimulacionBancoVendeMonedaExtranjeraRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", propOrder = {
    "condCliente",
    "fechaIngrPais",
    "marcaEmpleado",
    "nomApell",
    "tipRefAfip"
})
public class Trx738MdlwSimulacionBancoVendeMonedaExtranjeraRequest
    extends SimulacionCompraVentaMonedaExtranjeraBase
{

    @XmlElementRef(name = "CondCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> condCliente;
    @XmlElementRef(name = "FechaIngrPais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> fechaIngrPais;
    @XmlElementRef(name = "MarcaEmpleado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> marcaEmpleado;
    @XmlElementRef(name = "NomApell", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> nomApell;
    @XmlElementRef(name = "TipRefAfip", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx738", type = JAXBElement.class)
    protected JAXBElement<String> tipRefAfip;

    /**
     * Gets the value of the condCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCondCliente() {
        return condCliente;
    }

    /**
     * Sets the value of the condCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCondCliente(JAXBElement<String> value) {
        this.condCliente = value;
    }

    /**
     * Gets the value of the fechaIngrPais property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaIngrPais() {
        return fechaIngrPais;
    }

    /**
     * Sets the value of the fechaIngrPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaIngrPais(JAXBElement<String> value) {
        this.fechaIngrPais = value;
    }

    /**
     * Gets the value of the marcaEmpleado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaEmpleado() {
        return marcaEmpleado;
    }

    /**
     * Sets the value of the marcaEmpleado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaEmpleado(JAXBElement<String> value) {
        this.marcaEmpleado = value;
    }

    /**
     * Gets the value of the nomApell property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomApell() {
        return nomApell;
    }

    /**
     * Sets the value of the nomApell property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomApell(JAXBElement<String> value) {
        this.nomApell = value;
    }

    /**
     * Gets the value of the tipRefAfip property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipRefAfip() {
        return tipRefAfip;
    }

    /**
     * Sets the value of the tipRefAfip property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipRefAfip(JAXBElement<String> value) {
        this.tipRefAfip = value;
    }

}
