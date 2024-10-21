
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="IdServicioTransaccional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdSolicitudAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", propOrder = {
    "idServicioTransaccional",
    "idSolicitudAutorizacion",
    "indSinonimo",
    "tipoConsulta"
})
public class Trx5008MdlwConsultaSolicitudesAutorizacionOperacionesRequest
    extends RequestContactBase
{

    @XmlElementRef(name = "IdServicioTransaccional", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<String> idServicioTransaccional;
    @XmlElementRef(name = "IdSolicitudAutorizacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<String> idSolicitudAutorizacion;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "TipoConsulta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx5008", type = JAXBElement.class)
    protected JAXBElement<String> tipoConsulta;

    /**
     * Gets the value of the idServicioTransaccional property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdServicioTransaccional() {
        return idServicioTransaccional;
    }

    /**
     * Sets the value of the idServicioTransaccional property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdServicioTransaccional(JAXBElement<String> value) {
        this.idServicioTransaccional = value;
    }

    /**
     * Gets the value of the idSolicitudAutorizacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdSolicitudAutorizacion() {
        return idSolicitudAutorizacion;
    }

    /**
     * Sets the value of the idSolicitudAutorizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdSolicitudAutorizacion(JAXBElement<String> value) {
        this.idSolicitudAutorizacion = value;
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
     * Gets the value of the tipoConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * Sets the value of the tipoConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoConsulta(JAXBElement<String> value) {
        this.tipoConsulta = value;
    }

}
