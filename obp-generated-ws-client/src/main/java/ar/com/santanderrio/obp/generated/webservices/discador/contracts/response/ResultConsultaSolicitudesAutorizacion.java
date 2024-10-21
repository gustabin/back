
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.MappingModelBase;
import ar.com.santanderrio.obp.generated.webservices.discador.StatusResult;


/**
 * <p>Java class for ResultConsultaSolicitudesAutorizacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultConsultaSolicitudesAutorizacion">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}MappingModelBase">
 *       &lt;sequence>
 *         &lt;element name="CantRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Delimitador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HeaderTrama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorRegistrosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusResult" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}StatusResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultConsultaSolicitudesAutorizacion", propOrder = {
    "cantRegistros",
    "delimitador",
    "headerTrama",
    "indicadorRegistrosAdicionales",
    "longitud",
    "statusResult"
})
public class ResultConsultaSolicitudesAutorizacion
    extends MappingModelBase
{

    @XmlElementRef(name = "CantRegistros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> cantRegistros;
    @XmlElementRef(name = "Delimitador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> delimitador;
    @XmlElementRef(name = "HeaderTrama", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> headerTrama;
    @XmlElementRef(name = "IndicadorRegistrosAdicionales", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> indicadorRegistrosAdicionales;
    @XmlElementRef(name = "Longitud", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<String> longitud;
    @XmlElementRef(name = "StatusResult", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response", type = JAXBElement.class)
    protected JAXBElement<StatusResult> statusResult;

    /**
     * Gets the value of the cantRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantRegistros() {
        return cantRegistros;
    }

    /**
     * Sets the value of the cantRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantRegistros(JAXBElement<String> value) {
        this.cantRegistros = value;
    }

    /**
     * Gets the value of the delimitador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDelimitador() {
        return delimitador;
    }

    /**
     * Sets the value of the delimitador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDelimitador(JAXBElement<String> value) {
        this.delimitador = value;
    }

    /**
     * Gets the value of the headerTrama property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the value of the headerTrama property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHeaderTrama(JAXBElement<String> value) {
        this.headerTrama = value;
    }

    /**
     * Gets the value of the indicadorRegistrosAdicionales property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorRegistrosAdicionales() {
        return indicadorRegistrosAdicionales;
    }

    /**
     * Sets the value of the indicadorRegistrosAdicionales property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorRegistrosAdicionales(JAXBElement<String> value) {
        this.indicadorRegistrosAdicionales = value;
    }

    /**
     * Gets the value of the longitud property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitud() {
        return longitud;
    }

    /**
     * Sets the value of the longitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitud(JAXBElement<String> value) {
        this.longitud = value;
    }

    /**
     * Gets the value of the statusResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public JAXBElement<StatusResult> getStatusResult() {
        return statusResult;
    }

    /**
     * Sets the value of the statusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatusResult }{@code >}
     *     
     */
    public void setStatusResult(JAXBElement<StatusResult> value) {
        this.statusResult = value;
    }

}
