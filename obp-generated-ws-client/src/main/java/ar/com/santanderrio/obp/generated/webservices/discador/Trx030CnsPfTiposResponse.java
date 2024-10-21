
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.response.ArrayOfPlazoFijoResponse;


/**
 * <p>Java class for Trx030CnsPfTiposResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx030CnsPfTiposResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadRepeticiones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazosFijos" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ArrayOfPlazoFijoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx030CnsPfTiposResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx030", propOrder = {
    "cantidadRepeticiones",
    "plazosFijos"
})
public class Trx030CnsPfTiposResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadRepeticiones", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx030", type = JAXBElement.class)
    protected JAXBElement<String> cantidadRepeticiones;
    @XmlElementRef(name = "PlazosFijos", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx030", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPlazoFijoResponse> plazosFijos;

    /**
     * Gets the value of the cantidadRepeticiones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadRepeticiones() {
        return cantidadRepeticiones;
    }

    /**
     * Sets the value of the cantidadRepeticiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadRepeticiones(JAXBElement<String> value) {
        this.cantidadRepeticiones = value;
    }

    /**
     * Gets the value of the plazosFijos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPlazoFijoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPlazoFijoResponse> getPlazosFijos() {
        return plazosFijos;
    }

    /**
     * Sets the value of the plazosFijos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPlazoFijoResponse }{@code >}
     *     
     */
    public void setPlazosFijos(JAXBElement<ArrayOfPlazoFijoResponse> value) {
        this.plazosFijos = value;
    }

}
