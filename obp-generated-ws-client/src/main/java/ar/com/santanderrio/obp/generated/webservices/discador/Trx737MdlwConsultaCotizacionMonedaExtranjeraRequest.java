
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteRequest;


/**
 * <p>Java class for Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Aplicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="ImporteMonExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteMonNac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Segmento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoOper" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", propOrder = {
    "aplicacion",
    "cliente",
    "importeMonExt",
    "importeMonNac",
    "numeroContrato",
    "segmento",
    "sucursal",
    "tipoCot",
    "tipoOper"
})
public class Trx737MdlwConsultaCotizacionMonedaExtranjeraRequest
    extends RequestBase
{

    @XmlElementRef(name = "Aplicacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> aplicacion;
    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "ImporteMonExt", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> importeMonExt;
    @XmlElementRef(name = "ImporteMonNac", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> importeMonNac;
    @XmlElementRef(name = "NumeroContrato", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> numeroContrato;
    @XmlElementRef(name = "Segmento", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> segmento;
    @XmlElementRef(name = "Sucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> sucursal;
    @XmlElementRef(name = "TipoCot", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> tipoCot;
    @XmlElementRef(name = "TipoOper", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx737", type = JAXBElement.class)
    protected JAXBElement<String> tipoOper;

    /**
     * Gets the value of the aplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAplicacion() {
        return aplicacion;
    }

    /**
     * Sets the value of the aplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAplicacion(JAXBElement<String> value) {
        this.aplicacion = value;
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
     * Gets the value of the importeMonExt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteMonExt() {
        return importeMonExt;
    }

    /**
     * Sets the value of the importeMonExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteMonExt(JAXBElement<String> value) {
        this.importeMonExt = value;
    }

    /**
     * Gets the value of the importeMonNac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteMonNac() {
        return importeMonNac;
    }

    /**
     * Sets the value of the importeMonNac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteMonNac(JAXBElement<String> value) {
        this.importeMonNac = value;
    }

    /**
     * Gets the value of the numeroContrato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Sets the value of the numeroContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroContrato(JAXBElement<String> value) {
        this.numeroContrato = value;
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
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<String> value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the tipoCot property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCot() {
        return tipoCot;
    }

    /**
     * Sets the value of the tipoCot property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCot(JAXBElement<String> value) {
        this.tipoCot = value;
    }

    /**
     * Gets the value of the tipoOper property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoOper() {
        return tipoOper;
    }

    /**
     * Sets the value of the tipoOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoOper(JAXBElement<String> value) {
        this.tipoOper = value;
    }

}
