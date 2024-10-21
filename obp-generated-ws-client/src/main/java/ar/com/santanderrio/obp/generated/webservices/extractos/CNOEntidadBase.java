
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CNOEntidadBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CNOEntidadBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CNOEntidadBase", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", propOrder = {
    "cno"
})
@XmlSeeAlso({
    CuentaDetalle.class
})
public class CNOEntidadBase {

    @XmlElementRef(name = "CNO", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", type = JAXBElement.class)
    protected JAXBElement<String> cno;

    /**
     * Gets the value of the cno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCNO() {
        return cno;
    }

    /**
     * Sets the value of the cno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCNO(JAXBElement<String> value) {
        this.cno = ((JAXBElement<String> ) value);
    }

}
