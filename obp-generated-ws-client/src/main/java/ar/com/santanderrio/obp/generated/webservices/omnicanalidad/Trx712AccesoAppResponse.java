
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx712AccesoAppResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx712AccesoAppResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Acceso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx712AccesoAppResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx712", propOrder = {
    "acceso"
})
public class Trx712AccesoAppResponse
    extends ResponseBase
{

    @XmlElement(name = "Acceso")
    protected Integer acceso;

    /**
     * Gets the value of the acceso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAcceso() {
        return acceso;
    }

    /**
     * Sets the value of the acceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAcceso(Integer value) {
        this.acceso = value;
    }

}
