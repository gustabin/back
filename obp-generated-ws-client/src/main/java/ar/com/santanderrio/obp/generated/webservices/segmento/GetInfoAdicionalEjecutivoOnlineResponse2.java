
package ar.com.santanderrio.obp.generated.webservices.segmento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetInfoAdicionalEjecutivoOnlineResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInfoAdicionalEjecutivoOnlineResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoMejorProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ColorSemaforo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Equipo" type="{http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData}ArrayOfInfoMiEquipo" minOccurs="0"/>
 *         &lt;element name="Grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdEjecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaVip" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MejorProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RentaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RentabilidadSemestral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Segmento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentaFAC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentabilidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalAdministradora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInfoAdicionalEjecutivoOnlineResponse", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", propOrder = {
    "codigoMejorProducto",
    "codigoRetorno",
    "colorSemaforo",
    "ejecutivo",
    "email",
    "equipo",
    "grupo",
    "idEjecutivo",
    "marcaVip",
    "mejorProducto",
    "rentaCliente",
    "rentabilidadSemestral",
    "resultado",
    "segmento",
    "semaforoRentaFAC",
    "semaforoRentabilidad",
    "sucursalAdministradora",
    "tipoCliente",
    "zona"
})
public class GetInfoAdicionalEjecutivoOnlineResponse2 {

    @XmlElementRef(name = "CodigoMejorProducto", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> codigoMejorProducto;
    @XmlElementRef(name = "CodigoRetorno", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> codigoRetorno;
    @XmlElementRef(name = "ColorSemaforo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> colorSemaforo;
    @XmlElementRef(name = "Ejecutivo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivo;
    @XmlElementRef(name = "Email", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> email;
    @XmlElementRef(name = "Equipo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<ArrayOfInfoMiEquipo> equipo;
    @XmlElementRef(name = "Grupo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> grupo;
    @XmlElementRef(name = "IdEjecutivo", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> idEjecutivo;
    @XmlElement(name = "MarcaVip")
    protected Boolean marcaVip;
    @XmlElementRef(name = "MejorProducto", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> mejorProducto;
    @XmlElementRef(name = "RentaCliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> rentaCliente;
    @XmlElementRef(name = "RentabilidadSemestral", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> rentabilidadSemestral;
    @XmlElementRef(name = "Resultado", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> resultado;
    @XmlElementRef(name = "Segmento", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> segmento;
    @XmlElementRef(name = "SemaforoRentaFAC", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentaFAC;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElementRef(name = "SucursalAdministradora", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> sucursalAdministradora;
    @XmlElementRef(name = "TipoCliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> tipoCliente;
    @XmlElementRef(name = "Zona", namespace = "http://schemas.datacontract.org/2004/07/BSR.CRMBE.API.Services.DataContracts.ClientData", type = JAXBElement.class)
    protected JAXBElement<String> zona;

    /**
     * Gets the value of the codigoMejorProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoMejorProducto() {
        return codigoMejorProducto;
    }

    /**
     * Sets the value of the codigoMejorProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoMejorProducto(JAXBElement<String> value) {
        this.codigoMejorProducto = value;
    }

    /**
     * Gets the value of the codigoRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Sets the value of the codigoRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoRetorno(JAXBElement<String> value) {
        this.codigoRetorno = value;
    }

    /**
     * Gets the value of the colorSemaforo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getColorSemaforo() {
        return colorSemaforo;
    }

    /**
     * Sets the value of the colorSemaforo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setColorSemaforo(JAXBElement<String> value) {
        this.colorSemaforo = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEmail(JAXBElement<String> value) {
        this.email = value;
    }

    /**
     * Gets the value of the equipo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInfoMiEquipo }{@code >}
     *     
     */
    public JAXBElement<ArrayOfInfoMiEquipo> getEquipo() {
        return equipo;
    }

    /**
     * Sets the value of the equipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfInfoMiEquipo }{@code >}
     *     
     */
    public void setEquipo(JAXBElement<ArrayOfInfoMiEquipo> value) {
        this.equipo = value;
    }

    /**
     * Gets the value of the grupo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupo() {
        return grupo;
    }

    /**
     * Sets the value of the grupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupo(JAXBElement<String> value) {
        this.grupo = value;
    }

    /**
     * Gets the value of the idEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdEjecutivo() {
        return idEjecutivo;
    }

    /**
     * Sets the value of the idEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdEjecutivo(JAXBElement<String> value) {
        this.idEjecutivo = value;
    }

    /**
     * Gets the value of the marcaVip property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMarcaVip() {
        return marcaVip;
    }

    /**
     * Sets the value of the marcaVip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMarcaVip(Boolean value) {
        this.marcaVip = value;
    }

    /**
     * Gets the value of the mejorProducto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMejorProducto() {
        return mejorProducto;
    }

    /**
     * Sets the value of the mejorProducto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMejorProducto(JAXBElement<String> value) {
        this.mejorProducto = value;
    }

    /**
     * Gets the value of the rentaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRentaCliente() {
        return rentaCliente;
    }

    /**
     * Sets the value of the rentaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRentaCliente(JAXBElement<String> value) {
        this.rentaCliente = value;
    }

    /**
     * Gets the value of the rentabilidadSemestral property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRentabilidadSemestral() {
        return rentabilidadSemestral;
    }

    /**
     * Sets the value of the rentabilidadSemestral property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRentabilidadSemestral(JAXBElement<String> value) {
        this.rentabilidadSemestral = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResultado(JAXBElement<String> value) {
        this.resultado = value;
    }

    /**
     * Gets the value of the segmento property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSegmento() {
        return segmento;
    }

    /**
     * Sets the value of the segmento property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSegmento(JAXBElement<String> value) {
        this.segmento = value;
    }

    /**
     * Gets the value of the semaforoRentaFAC property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentaFAC() {
        return semaforoRentaFAC;
    }

    /**
     * Sets the value of the semaforoRentaFAC property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentaFAC(JAXBElement<String> value) {
        this.semaforoRentaFAC = value;
    }

    /**
     * Gets the value of the semaforoRentabilidad property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentabilidad() {
        return semaforoRentabilidad;
    }

    /**
     * Sets the value of the semaforoRentabilidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentabilidad(JAXBElement<String> value) {
        this.semaforoRentabilidad = value;
    }

    /**
     * Gets the value of the sucursalAdministradora property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalAdministradora() {
        return sucursalAdministradora;
    }

    /**
     * Sets the value of the sucursalAdministradora property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalAdministradora(JAXBElement<String> value) {
        this.sucursalAdministradora = value;
    }

    /**
     * Gets the value of the tipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Sets the value of the tipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCliente(JAXBElement<String> value) {
        this.tipoCliente = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZona(JAXBElement<String> value) {
        this.zona = value;
    }

}
