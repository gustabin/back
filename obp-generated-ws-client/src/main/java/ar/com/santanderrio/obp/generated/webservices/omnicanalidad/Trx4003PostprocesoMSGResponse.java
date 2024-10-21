
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx4003PostprocesoMSGResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx4003PostprocesoMSGResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="MensajeRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Resultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx4003PostprocesoMSGResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4003PostprocesoMSG", propOrder = {
    "mensajeRespuesta",
    "resultado"
})
public class Trx4003PostprocesoMSGResponse
    extends ResponseBase
{

    @XmlElementRef(name = "MensajeRespuesta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4003PostprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> mensajeRespuesta;
    @XmlElementRef(name = "Resultado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx4003PostprocesoMSG", type = JAXBElement.class)
    protected JAXBElement<String> resultado;

    /**
     * Gets the value of the mensajeRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    /**
     * Sets the value of the mensajeRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensajeRespuesta(JAXBElement<String> value) {
        this.mensajeRespuesta = value;
    }

    /**
     * Gets the value of the resultado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResultado() {
        return resultado;
    }

    /**
     * Sets the value of the resultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResultado(JAXBElement<String> value) {
        this.resultado = value;
    }

}
