
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx083CnsTasasPfResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx083CnsTasasPfResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CantidadRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tasas" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083}ArrayOfTrx083TasaPlazoFijoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx083CnsTasasPfResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", propOrder = {
    "cantidadRegistros",
    "tasas"
})
public class Trx083CnsTasasPfResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CantidadRegistros", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<String> cantidadRegistros;
    @XmlElementRef(name = "Tasas", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx083", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx083TasaPlazoFijoResponse> tasas;

    /**
     * Gets the value of the cantidadRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadRegistros() {
        return cantidadRegistros;
    }

    /**
     * Sets the value of the cantidadRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadRegistros(JAXBElement<String> value) {
        this.cantidadRegistros = value;
    }

    /**
     * Gets the value of the tasas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx083TasaPlazoFijoResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx083TasaPlazoFijoResponse> getTasas() {
        return tasas;
    }

    /**
     * Sets the value of the tasas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx083TasaPlazoFijoResponse }{@code >}
     *     
     */
    public void setTasas(JAXBElement<ArrayOfTrx083TasaPlazoFijoResponse> value) {
        this.tasas = value;
    }

}
