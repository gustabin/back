
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AutorizacionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AutorizacionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="Autorizantes" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}ArrayOfAutorizanteRequest" minOccurs="0"/>
 *         &lt;element name="Cantidad_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Funcion_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Solicitud_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Indicador_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp_Autor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutorizacionRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", propOrder = {
    "autorizantes",
    "cantidadAutor",
    "estadoAutor",
    "funcionAutor",
    "idSolicitudAutor",
    "indicadorAutor",
    "timestampAutor"
})
public class AutorizacionRequest
    extends MappingModelBase
{

    @XmlElementRef(name = "Autorizantes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<ArrayOfAutorizanteRequest> autorizantes;
    @XmlElementRef(name = "Cantidad_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> cantidadAutor;
    @XmlElementRef(name = "Estado_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> estadoAutor;
    @XmlElementRef(name = "Funcion_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> funcionAutor;
    @XmlElementRef(name = "Id_Solicitud_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> idSolicitudAutor;
    @XmlElementRef(name = "Indicador_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> indicadorAutor;
    @XmlElementRef(name = "Timestamp_Autor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request", type = JAXBElement.class)
    protected JAXBElement<String> timestampAutor;

    /**
     * Gets the value of the autorizantes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfAutorizanteRequest }{@code >}
     *     
     */
    public JAXBElement<ArrayOfAutorizanteRequest> getAutorizantes() {
        return autorizantes;
    }

    /**
     * Sets the value of the autorizantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfAutorizanteRequest }{@code >}
     *     
     */
    public void setAutorizantes(JAXBElement<ArrayOfAutorizanteRequest> value) {
        this.autorizantes = value;
    }

    /**
     * Gets the value of the cantidadAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadAutor() {
        return cantidadAutor;
    }

    /**
     * Sets the value of the cantidadAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadAutor(JAXBElement<String> value) {
        this.cantidadAutor = value;
    }

    /**
     * Gets the value of the estadoAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoAutor() {
        return estadoAutor;
    }

    /**
     * Sets the value of the estadoAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoAutor(JAXBElement<String> value) {
        this.estadoAutor = value;
    }

    /**
     * Gets the value of the funcionAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFuncionAutor() {
        return funcionAutor;
    }

    /**
     * Sets the value of the funcionAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFuncionAutor(JAXBElement<String> value) {
        this.funcionAutor = value;
    }

    /**
     * Gets the value of the idSolicitudAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdSolicitudAutor() {
        return idSolicitudAutor;
    }

    /**
     * Sets the value of the idSolicitudAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdSolicitudAutor(JAXBElement<String> value) {
        this.idSolicitudAutor = value;
    }

    /**
     * Gets the value of the indicadorAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorAutor() {
        return indicadorAutor;
    }

    /**
     * Sets the value of the indicadorAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorAutor(JAXBElement<String> value) {
        this.indicadorAutor = value;
    }

    /**
     * Gets the value of the timestampAutor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimestampAutor() {
        return timestampAutor;
    }

    /**
     * Sets the value of the timestampAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimestampAutor(JAXBElement<String> value) {
        this.timestampAutor = value;
    }

}
