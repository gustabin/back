
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx741MdlwAutorizBancoCompraMonedaExtranjeraRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx741MdlwAutorizBancoCompraMonedaExtranjeraRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}AutorizBancoCompraVentaExtranjeraRequestBase">
 *       &lt;sequence>
 *         &lt;element name="CodTributa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaIngrPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NioCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NioDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomApell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroDocBcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumBoleCvta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisEmisDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipRefAfip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx741MdlwAutorizBancoCompraMonedaExtranjeraRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", propOrder = {
    "codTributa",
    "codigoConcepto",
    "fechaIngrPais",
    "nioCredito",
    "nioDebito",
    "nomApell",
    "nroDocBcra",
    "numBoleCvta",
    "paisEmisDoc",
    "paisNac",
    "tipRefAfip",
    "tipoCambio",
    "tipoDocBcra"
})
public class Trx741MdlwAutorizBancoCompraMonedaExtranjeraRequest
    extends AutorizBancoCompraVentaExtranjeraRequestBase
{

    @XmlElementRef(name = "CodTributa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> codTributa;
    @XmlElementRef(name = "CodigoConcepto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> codigoConcepto;
    @XmlElementRef(name = "FechaIngrPais", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> fechaIngrPais;
    @XmlElementRef(name = "NioCredito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nioCredito;
    @XmlElementRef(name = "NioDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nioDebito;
    @XmlElementRef(name = "NomApell", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nomApell;
    @XmlElementRef(name = "NroDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> nroDocBcra;
    @XmlElementRef(name = "NumBoleCvta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> numBoleCvta;
    @XmlElementRef(name = "PaisEmisDoc", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> paisEmisDoc;
    @XmlElementRef(name = "PaisNac", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> paisNac;
    @XmlElementRef(name = "TipRefAfip", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> tipRefAfip;
    @XmlElementRef(name = "TipoCambio", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
    protected JAXBElement<String> tipoCambio;
    @XmlElementRef(name = "TipoDocBcra", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx741", type = JAXBElement.class)
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
     * Gets the value of the nioCredito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNioCredito() {
        return nioCredito;
    }

    /**
     * Sets the value of the nioCredito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNioCredito(JAXBElement<String> value) {
        this.nioCredito = value;
    }

    /**
     * Gets the value of the nioDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNioDebito() {
        return nioDebito;
    }

    /**
     * Sets the value of the nioDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNioDebito(JAXBElement<String> value) {
        this.nioDebito = value;
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
     * Gets the value of the tipoCambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCambio() {
        return tipoCambio;
    }

    /**
     * Sets the value of the tipoCambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCambio(JAXBElement<String> value) {
        this.tipoCambio = value;
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
