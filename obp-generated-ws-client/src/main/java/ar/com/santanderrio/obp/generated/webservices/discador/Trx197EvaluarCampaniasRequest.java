
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx197EvaluarCampaniasRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx197EvaluarCampaniasRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="CodAgente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Opcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PinCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx197EvaluarCampaniasRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", propOrder = {
    "codAgente",
    "fechaNacimiento",
    "idCliente",
    "indSinonimo",
    "nroCuenta",
    "opcion",
    "pinCliente",
    "sucursalCuenta",
    "tipoCuenta",
    "tipoId",
    "tipoPersona"
})
public class Trx197EvaluarCampaniasRequest
    extends RequestBase
{

    @XmlElementRef(name = "CodAgente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> codAgente;
    @XmlElementRef(name = "FechaNacimiento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> fechaNacimiento;
    @XmlElementRef(name = "IdCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> idCliente;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "NroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> nroCuenta;
    @XmlElementRef(name = "Opcion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> opcion;
    @XmlElementRef(name = "PinCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> pinCliente;
    @XmlElementRef(name = "SucursalCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuenta;
    @XmlElementRef(name = "TipoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;
    @XmlElementRef(name = "TipoId", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> tipoId;
    @XmlElementRef(name = "TipoPersona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx197", type = JAXBElement.class)
    protected JAXBElement<String> tipoPersona;

    /**
     * Gets the value of the codAgente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodAgente() {
        return codAgente;
    }

    /**
     * Sets the value of the codAgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodAgente(JAXBElement<String> value) {
        this.codAgente = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaNacimiento(JAXBElement<String> value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the idCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the value of the idCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCliente(JAXBElement<String> value) {
        this.idCliente = value;
    }

    /**
     * Gets the value of the indSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndSinonimo() {
        return indSinonimo;
    }

    /**
     * Sets the value of the indSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndSinonimo(JAXBElement<String> value) {
        this.indSinonimo = value;
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
     * Gets the value of the opcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOpcion() {
        return opcion;
    }

    /**
     * Sets the value of the opcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOpcion(JAXBElement<String> value) {
        this.opcion = value;
    }

    /**
     * Gets the value of the pinCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPinCliente() {
        return pinCliente;
    }

    /**
     * Sets the value of the pinCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPinCliente(JAXBElement<String> value) {
        this.pinCliente = value;
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

    /**
     * Gets the value of the tipoId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoId() {
        return tipoId;
    }

    /**
     * Sets the value of the tipoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoId(JAXBElement<String> value) {
        this.tipoId = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPersona(JAXBElement<String> value) {
        this.tipoPersona = value;
    }

}
