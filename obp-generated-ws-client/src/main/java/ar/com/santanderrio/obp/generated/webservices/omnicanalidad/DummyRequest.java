
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DummyRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DummyRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="DatoInt1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DatoInt2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DummyRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", propOrder = {
    "datoInt1",
    "datoInt2"
})
public class DummyRequest
    extends RequestBase
{

    @XmlElement(name = "DatoInt1")
    protected Integer datoInt1;
    @XmlElement(name = "DatoInt2")
    protected Integer datoInt2;

    /**
     * Gets the value of the datoInt1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDatoInt1() {
        return datoInt1;
    }

    /**
     * Sets the value of the datoInt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDatoInt1(Integer value) {
        this.datoInt1 = value;
    }

    /**
     * Gets the value of the datoInt2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDatoInt2() {
        return datoInt2;
    }

    /**
     * Sets the value of the datoInt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDatoInt2(Integer value) {
        this.datoInt2 = value;
    }

}
