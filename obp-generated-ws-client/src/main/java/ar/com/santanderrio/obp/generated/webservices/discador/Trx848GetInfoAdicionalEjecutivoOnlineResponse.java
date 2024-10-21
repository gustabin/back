
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx848GetInfoAdicionalEjecutivoOnlineResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx848GetInfoAdicionalEjecutivoOnlineResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Carterizado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoMejorProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ColorSemaforo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaVip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx848GetInfoAdicionalEjecutivoOnlineResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", propOrder = {
    "carterizado",
    "codigoMejorProducto",
    "codigoRetorno",
    "colorSemaforo",
    "grupoDesborde",
    "grupoOperador",
    "legajoDesborde",
    "legajoOperador",
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
public class Trx848GetInfoAdicionalEjecutivoOnlineResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Carterizado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> carterizado;
    @XmlElementRef(name = "CodigoMejorProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> codigoMejorProducto;
    @XmlElementRef(name = "CodigoRetorno", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> codigoRetorno;
    @XmlElementRef(name = "ColorSemaforo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> colorSemaforo;
    @XmlElementRef(name = "GrupoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> grupoDesborde;
    @XmlElementRef(name = "GrupoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> grupoOperador;
    @XmlElementRef(name = "LegajoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> legajoDesborde;
    @XmlElementRef(name = "LegajoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> legajoOperador;
    @XmlElementRef(name = "MarcaVip", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> marcaVip;
    @XmlElementRef(name = "MejorProducto", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> mejorProducto;
    @XmlElementRef(name = "RentaCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> rentaCliente;
    @XmlElementRef(name = "RentabilidadSemestral", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> rentabilidadSemestral;
    @XmlElementRef(name = "Resultado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> resultado;
    @XmlElementRef(name = "Segmento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> segmento;
    @XmlElementRef(name = "SemaforoRentaFAC", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentaFAC;
    @XmlElementRef(name = "SemaforoRentabilidad", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentabilidad;
    @XmlElementRef(name = "SucursalAdministradora", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> sucursalAdministradora;
    @XmlElementRef(name = "TipoCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> tipoCliente;
    @XmlElementRef(name = "Zona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx848", type = JAXBElement.class)
    protected JAXBElement<String> zona;

    /**
     * Gets the value of the carterizado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCarterizado() {
        return carterizado;
    }

    /**
     * Sets the value of the carterizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCarterizado(JAXBElement<String> value) {
        this.carterizado = value;
    }

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
     * Gets the value of the grupoDesborde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoDesborde() {
        return grupoDesborde;
    }

    /**
     * Sets the value of the grupoDesborde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoDesborde(JAXBElement<String> value) {
        this.grupoDesborde = value;
    }

    /**
     * Gets the value of the grupoOperador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoOperador() {
        return grupoOperador;
    }

    /**
     * Sets the value of the grupoOperador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoOperador(JAXBElement<String> value) {
        this.grupoOperador = value;
    }

    /**
     * Gets the value of the legajoDesborde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLegajoDesborde() {
        return legajoDesborde;
    }

    /**
     * Sets the value of the legajoDesborde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLegajoDesborde(JAXBElement<String> value) {
        this.legajoDesborde = value;
    }

    /**
     * Gets the value of the legajoOperador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLegajoOperador() {
        return legajoOperador;
    }

    /**
     * Sets the value of the legajoOperador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLegajoOperador(JAXBElement<String> value) {
        this.legajoOperador = value;
    }

    /**
     * Gets the value of the marcaVip property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaVip() {
        return marcaVip;
    }

    /**
     * Sets the value of the marcaVip property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaVip(JAXBElement<String> value) {
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
