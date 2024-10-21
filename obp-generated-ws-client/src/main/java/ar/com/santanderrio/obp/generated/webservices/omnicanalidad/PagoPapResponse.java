
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagoPapResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagoPapResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantInstrumentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuitEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescAcuerdo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DigNumEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DistribucionSucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DistribucionTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndConforming" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroInstancia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoPapResponse", propOrder = {
    "cantInstrumentos",
    "cuitEmpresa",
    "descAcuerdo",
    "digNumEmpresa",
    "distribucionSucursal",
    "distribucionTipo",
    "fecha",
    "indConforming",
    "nroInstancia",
    "nroProducto"
})
public class PagoPapResponse
    extends ResponseBase
{

    @XmlElementRef(name = "CantInstrumentos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cantInstrumentos;
    @XmlElementRef(name = "CuitEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cuitEmpresa;
    @XmlElementRef(name = "DescAcuerdo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> descAcuerdo;
    @XmlElementRef(name = "DigNumEmpresa", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> digNumEmpresa;
    @XmlElementRef(name = "DistribucionSucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> distribucionSucursal;
    @XmlElementRef(name = "DistribucionTipo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> distribucionTipo;
    @XmlElementRef(name = "Fecha", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> fecha;
    @XmlElementRef(name = "IndConforming", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indConforming;
    @XmlElementRef(name = "NroInstancia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroInstancia;
    @XmlElementRef(name = "NroProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> nroProducto;

    /**
     * Gets the value of the cantInstrumentos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantInstrumentos() {
        return cantInstrumentos;
    }

    /**
     * Sets the value of the cantInstrumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantInstrumentos(JAXBElement<String> value) {
        this.cantInstrumentos = value;
    }

    /**
     * Gets the value of the cuitEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuitEmpresa() {
        return cuitEmpresa;
    }

    /**
     * Sets the value of the cuitEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuitEmpresa(JAXBElement<String> value) {
        this.cuitEmpresa = value;
    }

    /**
     * Gets the value of the descAcuerdo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescAcuerdo() {
        return descAcuerdo;
    }

    /**
     * Sets the value of the descAcuerdo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescAcuerdo(JAXBElement<String> value) {
        this.descAcuerdo = value;
    }

    /**
     * Gets the value of the digNumEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDigNumEmpresa() {
        return digNumEmpresa;
    }

    /**
     * Sets the value of the digNumEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDigNumEmpresa(JAXBElement<String> value) {
        this.digNumEmpresa = value;
    }

    /**
     * Gets the value of the distribucionSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDistribucionSucursal() {
        return distribucionSucursal;
    }

    /**
     * Sets the value of the distribucionSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDistribucionSucursal(JAXBElement<String> value) {
        this.distribucionSucursal = value;
    }

    /**
     * Gets the value of the distribucionTipo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDistribucionTipo() {
        return distribucionTipo;
    }

    /**
     * Sets the value of the distribucionTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDistribucionTipo(JAXBElement<String> value) {
        this.distribucionTipo = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFecha(JAXBElement<String> value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the indConforming property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndConforming() {
        return indConforming;
    }

    /**
     * Sets the value of the indConforming property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndConforming(JAXBElement<String> value) {
        this.indConforming = value;
    }

    /**
     * Gets the value of the nroInstancia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroInstancia() {
        return nroInstancia;
    }

    /**
     * Sets the value of the nroInstancia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroInstancia(JAXBElement<String> value) {
        this.nroInstancia = value;
    }

    /**
     * Gets the value of the nroProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroProducto() {
        return nroProducto;
    }

    /**
     * Sets the value of the nroProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroProducto(JAXBElement<String> value) {
        this.nroProducto = value;
    }

}
