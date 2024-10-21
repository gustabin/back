
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObtenerTipoDocResult" type="{Response/Consultas}ObtenerTipoDocResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "obtenerTipoDocResult"
})
@XmlRootElement(name = "ObtenerTipoDocResponse")
public class ObtenerTipoDocResponse {

    @XmlElementRef(name = "ObtenerTipoDocResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse> obtenerTipoDocResult;

    /**
     * Obtiene el valor de la propiedad obtenerTipoDocResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse }{@code >}
     *     
     */
    public JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse> getObtenerTipoDocResult() {
        return obtenerTipoDocResult;
    }

    /**
     * Define el valor de la propiedad obtenerTipoDocResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse }{@code >}
     *     
     */
    public void setObtenerTipoDocResult(JAXBElement<ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ObtenerTipoDocResponse> value) {
        this.obtenerTipoDocResult = value;
    }

}
