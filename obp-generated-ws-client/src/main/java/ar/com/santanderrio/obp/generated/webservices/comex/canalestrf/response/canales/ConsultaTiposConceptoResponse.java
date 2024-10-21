
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfTiposConcepto;


/**
 * <p>Java class for ConsultaTiposConceptoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaTiposConceptoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfTiposConcepto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaTiposConceptoResponse", propOrder = {
    "registros"
})
public class ConsultaTiposConceptoResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTiposConcepto> registros;

    /**
     * Gets the value of the registros property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTiposConcepto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTiposConcepto> getRegistros() {
        return registros;
    }

    /**
     * Sets the value of the registros property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTiposConcepto }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfTiposConcepto> value) {
        this.registros = value;
    }

}
