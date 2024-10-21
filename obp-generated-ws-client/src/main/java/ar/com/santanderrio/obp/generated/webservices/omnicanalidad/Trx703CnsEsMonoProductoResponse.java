
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx703CnsEsMonoProductoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx703CnsEsMonoProductoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxMonoProductoBase">
 *       &lt;sequence>
 *         &lt;element name="EstadoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx703CnsEsMonoProductoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx703", propOrder = {
    "estadoCliente"
})
public class Trx703CnsEsMonoProductoResponse
    extends TrxMonoProductoBase
{

    @XmlElementRef(name = "EstadoCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx703", type = JAXBElement.class)
    protected JAXBElement<String> estadoCliente;

    /**
     * Gets the value of the estadoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoCliente() {
        return estadoCliente;
    }

    /**
     * Sets the value of the estadoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoCliente(JAXBElement<String> value) {
        this.estadoCliente = value;
    }

}
