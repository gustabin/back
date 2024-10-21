
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx739MdlwAutorizBancoVendeMonedaExtranjeraRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx739MdlwAutorizBancoVendeMonedaExtranjeraRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}AutorizBancoCompraVentaExtranjeraRequestBase">
 *       &lt;sequence>
 *         &lt;element name="CodTributa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaIngrPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NioCuentas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomApell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroDocBcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumBoleCvta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisEmisDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipRefAfip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDocBcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx739MdlwAutorizBancoVendeMonedaExtranjeraRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", propOrder = {
    "codTributa",
    "codigoConcepto",
    "fechaIngrPais",
    "marcaEmpleado",
    "nioCuentas",
    "nomApell",
    "nroDocBcra",
    "numBoleCvta",
    "paisEmisDoc",
    "paisNac",
    "tipCambio",
    "tipRefAfip",
    "tipoDocBcra"
})
public class Trx739MdlwAutorizBancoVendeMonedaExtranjeraRequest
    extends AutorizBancoCompraVentaExtranjeraRequestBase
{

    @XmlElementRef(name = "CodTributa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> codTributa;
    @XmlElementRef(name = "CodigoConcepto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> codigoConcepto;
    @XmlElementRef(name = "FechaIngrPais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> fechaIngrPais;
    @XmlElementRef(name = "MarcaEmpleado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> marcaEmpleado;
    @XmlElementRef(name = "NioCuentas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> nioCuentas;
    @XmlElementRef(name = "NomApell", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> nomApell;
    @XmlElementRef(name = "NroDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> nroDocBcra;
    @XmlElementRef(name = "NumBoleCvta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> numBoleCvta;
    @XmlElementRef(name = "PaisEmisDoc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> paisEmisDoc;
    @XmlElementRef(name = "PaisNac", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> paisNac;
    @XmlElementRef(name = "TipCambio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> tipCambio;
    @XmlElementRef(name = "TipRefAfip", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> tipRefAfip;
    @XmlElementRef(name = "TipoDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx739", type = JAXBElement.class)
    protected JAXBElement<String> tipoDocBcra;

    /**
     * Gets the value of the codTributa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodTributa() {
        return codTributa;
    }

    /**
     * Sets the value of the codTributa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodTributa(JAXBElement<String> value) {
        this.codTributa = value;
    }

    /**
     * Gets the value of the codigoConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoConcepto() {
        return codigoConcepto;
    }

    /**
     * Sets the value of the codigoConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoConcepto(JAXBElement<String> value) {
        this.codigoConcepto = value;
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
     * Gets the value of the nioCuentas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNioCuentas() {
        return nioCuentas;
    }

    /**
     * Sets the value of the nioCuentas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNioCuentas(JAXBElement<String> value) {
        this.nioCuentas = value;
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
     * Gets the value of the nroDocBcra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDocBcra() {
        return nroDocBcra;
    }

    /**
     * Sets the value of the nroDocBcra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDocBcra(JAXBElement<String> value) {
        this.nroDocBcra = value;
    }

    /**
     * Gets the value of the numBoleCvta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumBoleCvta() {
        return numBoleCvta;
    }

    /**
     * Sets the value of the numBoleCvta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumBoleCvta(JAXBElement<String> value) {
        this.numBoleCvta = value;
    }

    /**
     * Gets the value of the paisEmisDoc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisEmisDoc() {
        return paisEmisDoc;
    }

    /**
     * Sets the value of the paisEmisDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisEmisDoc(JAXBElement<String> value) {
        this.paisEmisDoc = value;
    }

    /**
     * Gets the value of the paisNac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisNac() {
        return paisNac;
    }

    /**
     * Sets the value of the paisNac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisNac(JAXBElement<String> value) {
        this.paisNac = value;
    }

    /**
     * Gets the value of the tipCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipCambio() {
        return tipCambio;
    }

    /**
     * Sets the value of the tipCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipCambio(JAXBElement<String> value) {
        this.tipCambio = value;
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

    /**
     * Gets the value of the tipoDocBcra property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDocBcra() {
        return tipoDocBcra;
    }

    /**
     * Sets the value of the tipoDocBcra property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDocBcra(JAXBElement<String> value) {
        this.tipoDocBcra = value;
    }

}
