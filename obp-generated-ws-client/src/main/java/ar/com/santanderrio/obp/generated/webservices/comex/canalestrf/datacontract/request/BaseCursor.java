
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.BuscarDespachosRequest;


/**
 * <p>Java class for BaseCursor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseCursor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Cantidad_Registros" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseCursor", propOrder = {
    "cantidadRegistros"
})
@XmlSeeAlso({
    BuscarDespachosRequest.class
})
public class BaseCursor
    extends FirmaRequest
{

    @XmlElementRef(name = "Cantidad_Registros", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<Integer> cantidadRegistros;

    /**
     * Gets the value of the cantidadRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCantidadRegistros() {
        return cantidadRegistros;
    }

    /**
     * Sets the value of the cantidadRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCantidadRegistros(JAXBElement<Integer> value) {
        this.cantidadRegistros = value;
    }

}
