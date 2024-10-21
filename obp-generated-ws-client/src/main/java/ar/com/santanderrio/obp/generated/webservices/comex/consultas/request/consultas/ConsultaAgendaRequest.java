
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.FirmaRequest;


/**
 * <p>Clase Java para ConsultaAgendaRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaAgendaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="IdAgenda" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Nup_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Agenda" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaAgendaRequest", propOrder = {
    "idAgenda",
    "nupCliente",
    "tipoAgenda"
})
public class ConsultaAgendaRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "IdAgenda", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<Long> idAgenda;
    @XmlElementRef(name = "Nup_Cliente", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> nupCliente;
    @XmlElementRef(name = "Tipo_Agenda", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<Long> tipoAgenda;

    /**
     * Obtiene el valor de la propiedad idAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdAgenda() {
        return idAgenda;
    }

    /**
     * Define el valor de la propiedad idAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdAgenda(JAXBElement<Long> value) {
        this.idAgenda = value;
    }

    /**
     * Obtiene el valor de la propiedad nupCliente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupCliente() {
        return nupCliente;
    }

    /**
     * Define el valor de la propiedad nupCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupCliente(JAXBElement<String> value) {
        this.nupCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAgenda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getTipoAgenda() {
        return tipoAgenda;
    }

    /**
     * Define el valor de la propiedad tipoAgenda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setTipoAgenda(JAXBElement<Long> value) {
        this.tipoAgenda = value;
    }

}
