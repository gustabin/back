
package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsultarOperacionesMultimercadoParameterRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultarOperacionesMultimercadoParameterRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity}DatoFirma">
 *       &lt;sequence>
 *         &lt;element name="Datos" type="{http://schemas.datacontract.org/2004/07/ISBAN.Mercados.ServiceContracts}ConsultarOperacionesMultimercadoParameter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarOperacionesMultimercadoParameterRequest", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity", propOrder = {
    "datos"
})
public class ConsultarOperacionesMultimercadoParameterRequest
    extends DatoFirma
{

    @XmlElementRef(name = "Datos", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity", type = JAXBElement.class)
    protected JAXBElement<ConsultarOperacionesMultimercadoParameter> datos;

    /**
     * Gets the value of the datos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ConsultarOperacionesMultimercadoParameter }{@code >}
     *     
     */
    public JAXBElement<ConsultarOperacionesMultimercadoParameter> getDatos() {
        return datos;
    }

    /**
     * Sets the value of the datos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ConsultarOperacionesMultimercadoParameter }{@code >}
     *     
     */
    public void setDatos(JAXBElement<ConsultarOperacionesMultimercadoParameter> value) {
        this.datos = value;
    }

}
