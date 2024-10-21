
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.FirmaRequest;


/**
 * <p>Clase Java para ObtenerCondicionesVentaRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ObtenerCondicionesVentaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Id_Cond_Vta" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerCondicionesVentaRequest", propOrder = {
    "idCondVta"
})
public class ObtenerCondicionesVentaRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Id_Cond_Vta", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", type = JAXBElement.class)
    protected JAXBElement<Long> idCondVta;

    /**
     * Obtiene el valor de la propiedad idCondVta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdCondVta() {
        return idCondVta;
    }

    /**
     * Define el valor de la propiedad idCondVta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdCondVta(JAXBElement<Long> value) {
        this.idCondVta = value;
    }

}
