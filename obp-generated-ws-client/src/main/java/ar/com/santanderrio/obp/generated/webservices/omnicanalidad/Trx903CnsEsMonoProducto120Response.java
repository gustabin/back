
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx903CnsEsMonoProducto120Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx903CnsEsMonoProducto120Response">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxMonoProductoBase">
 *       &lt;sequence>
 *         &lt;element name="Bloqueo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiasVencimiento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EstadoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParamVencimiento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx903CnsEsMonoProducto120Response", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx903", propOrder = {
    "bloqueo",
    "diasVencimiento",
    "estadoCliente",
    "paramVencimiento"
})
public class Trx903CnsEsMonoProducto120Response
    extends TrxMonoProductoBase
{

    @XmlElementRef(name = "Bloqueo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx903", type = JAXBElement.class)
    protected JAXBElement<String> bloqueo;
    @XmlElement(name = "DiasVencimiento")
    protected Integer diasVencimiento;
    @XmlElementRef(name = "EstadoCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx903", type = JAXBElement.class)
    protected JAXBElement<String> estadoCliente;
    @XmlElement(name = "ParamVencimiento")
    protected Integer paramVencimiento;

    /**
     * Gets the value of the bloqueo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBloqueo() {
        return bloqueo;
    }

    /**
     * Sets the value of the bloqueo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBloqueo(JAXBElement<String> value) {
        this.bloqueo = value;
    }

    /**
     * Gets the value of the diasVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiasVencimiento() {
        return diasVencimiento;
    }

    /**
     * Sets the value of the diasVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiasVencimiento(Integer value) {
        this.diasVencimiento = value;
    }

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

    /**
     * Gets the value of the paramVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParamVencimiento() {
        return paramVencimiento;
    }

    /**
     * Sets the value of the paramVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParamVencimiento(Integer value) {
        this.paramVencimiento = value;
    }

}
