
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.FirmaRequest;


/**
 * <p>Clase Java para ConsultaBeneficiarioRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaBeneficiarioRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Id_beneficiario" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Nup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaBeneficiarioRequest", propOrder = {
    "idBeneficiario",
    "nup"
})
public class ConsultaBeneficiarioRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Id_beneficiario", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", type = JAXBElement.class)
    protected JAXBElement<Long> idBeneficiario;
    @XmlElementRef(name = "Nup", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.Consultas", type = JAXBElement.class)
    protected JAXBElement<String> nup;

    /**
     * Obtiene el valor de la propiedad idBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * Define el valor de la propiedad idBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdBeneficiario(JAXBElement<Long> value) {
        this.idBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad nup.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNup() {
        return nup;
    }

    /**
     * Define el valor de la propiedad nup.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNup(JAXBElement<String> value) {
        this.nup = value;
    }

}
