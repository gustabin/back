
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfRegimenGanancia;


/**
 * <p>Clase Java para RegimenesGananciasResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RegimenesGananciasResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfRegimenGanancia" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegimenesGananciasResponse", propOrder = {
    "registros"
})
public class RegimenesGananciasResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Consultas", type = JAXBElement.class)
    protected JAXBElement<ArrayOfRegimenGanancia> registros;

    /**
     * Obtiene el valor de la propiedad registros.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRegimenGanancia }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRegimenGanancia> getRegistros() {
        return registros;
    }

    /**
     * Define el valor de la propiedad registros.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRegimenGanancia }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfRegimenGanancia> value) {
        this.registros = value;
    }

}
