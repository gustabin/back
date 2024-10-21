
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfPais;


/**
 * <p>Clase Java para ConsultaPaisesResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaPaisesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfPais" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaPaisesResponse", propOrder = {
    "registros"
})
public class ConsultaPaisesResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Consultas", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPais> registros;

    /**
     * Obtiene el valor de la propiedad registros.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPais }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPais> getRegistros() {
        return registros;
    }

    /**
     * Define el valor de la propiedad registros.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPais }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfPais> value) {
        this.registros = value;
    }

}
