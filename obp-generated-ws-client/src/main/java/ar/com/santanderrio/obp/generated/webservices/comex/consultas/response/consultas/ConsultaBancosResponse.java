
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.CursorResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBanco;


/**
 * <p>Clase Java para ConsultaBancosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaBancosResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}CursorResponse">
 *       &lt;sequence>
 *         &lt;element name="Registros" type="{Domain}ArrayOfBanco" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaBancosResponse", propOrder = {
    "registros"
})
public class ConsultaBancosResponse
    extends CursorResponse
{

    @XmlElementRef(name = "Registros", namespace = "Response/Consultas", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBanco> registros;

    /**
     * Obtiene el valor de la propiedad registros.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBanco }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBanco> getRegistros() {
        return registros;
    }

    /**
     * Define el valor de la propiedad registros.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBanco }{@code >}
     *     
     */
    public void setRegistros(JAXBElement<ArrayOfBanco> value) {
        this.registros = value;
    }

}
