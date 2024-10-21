
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for ClonarImagenesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClonarImagenesRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Nro_Form" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClonarImagenesRequest", propOrder = {
    "nroForm"
})
public class ClonarImagenesRequest
    extends FirmaRequest
{

    @XmlElement(name = "Nro_Form")
    protected Integer nroForm;

    /**
     * Gets the value of the nroForm property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNroForm() {
        return nroForm;
    }

    /**
     * Sets the value of the nroForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNroForm(Integer value) {
        this.nroForm = value;
    }

}
