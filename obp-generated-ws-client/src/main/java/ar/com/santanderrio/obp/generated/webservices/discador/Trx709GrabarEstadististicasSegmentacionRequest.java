
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx709GrabarEstadististicasSegmentacionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx709GrabarEstadististicasSegmentacionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="OpcionPreatendedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx709GrabarEstadististicasSegmentacionRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx709", propOrder = {
    "opcionPreatendedor",
    "vdnIngreso"
})
public class Trx709GrabarEstadististicasSegmentacionRequest
    extends RequestBase
{

    @XmlElementRef(name = "OpcionPreatendedor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx709", type = JAXBElement.class)
    protected JAXBElement<String> opcionPreatendedor;
    @XmlElementRef(name = "VdnIngreso", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx709", type = JAXBElement.class)
    protected JAXBElement<String> vdnIngreso;

    /**
     * Gets the value of the opcionPreatendedor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOpcionPreatendedor() {
        return opcionPreatendedor;
    }

    /**
     * Sets the value of the opcionPreatendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOpcionPreatendedor(JAXBElement<String> value) {
        this.opcionPreatendedor = value;
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
