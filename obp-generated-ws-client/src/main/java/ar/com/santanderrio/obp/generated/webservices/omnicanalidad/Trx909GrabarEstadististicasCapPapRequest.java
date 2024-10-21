
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx909GrabarEstadististicasCapPapRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx909GrabarEstadististicasCapPapRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="OpcionMenuPpal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VdnIngreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx909GrabarEstadististicasCapPapRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx909", propOrder = {
    "opcionMenuPpal",
    "vdnIngreso"
})
public class Trx909GrabarEstadististicasCapPapRequest
    extends RequestBase
{

    @XmlElementRef(name = "OpcionMenuPpal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx909", type = JAXBElement.class)
    protected JAXBElement<String> opcionMenuPpal;
    @XmlElementRef(name = "VdnIngreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx909", type = JAXBElement.class)
    protected JAXBElement<String> vdnIngreso;

    /**
     * Gets the value of the opcionMenuPpal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOpcionMenuPpal() {
        return opcionMenuPpal;
    }

    /**
     * Sets the value of the opcionMenuPpal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOpcionMenuPpal(JAXBElement<String> value) {
        this.opcionMenuPpal = value;
    }

    /**
     * Gets the value of the vdnIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVdnIngreso() {
        return vdnIngreso;
    }

    /**
     * Sets the value of the vdnIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVdnIngreso(JAXBElement<String> value) {
        this.vdnIngreso = value;
    }

}
