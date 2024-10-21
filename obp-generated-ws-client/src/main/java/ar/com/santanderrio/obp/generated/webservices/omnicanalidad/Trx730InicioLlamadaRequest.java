
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx730InicioLlamadaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx730InicioLlamadaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="VDNEntrada" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx730InicioLlamadaRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx730", propOrder = {
    "vdnEntrada"
})
public class Trx730InicioLlamadaRequest
    extends RequestBase
{

    @XmlElement(name = "VDNEntrada")
    protected Integer vdnEntrada;

    /**
     * Gets the value of the vdnEntrada property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVDNEntrada() {
        return vdnEntrada;
    }

    /**
     * Sets the value of the vdnEntrada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVDNEntrada(Integer value) {
        this.vdnEntrada = value;
    }

}
