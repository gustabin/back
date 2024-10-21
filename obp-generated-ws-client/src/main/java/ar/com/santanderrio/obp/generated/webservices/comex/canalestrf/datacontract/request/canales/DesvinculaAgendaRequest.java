
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for DesvinculaAgendaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DesvinculaAgendaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Id_Agenda" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Tipo_Agenda" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesvinculaAgendaRequest", propOrder = {
    "idAgenda",
    "tipoAgenda"
})
public class DesvinculaAgendaRequest
    extends FirmaRequest
{

    @XmlElement(name = "Id_Agenda")
    protected Long idAgenda;
    @XmlElement(name = "Tipo_Agenda")
    protected Short tipoAgenda;

    /**
     * Gets the value of the idAgenda property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAgenda() {
        return idAgenda;
    }

    /**
     * Sets the value of the idAgenda property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAgenda(Long value) {
        this.idAgenda = value;
    }

    /**
     * Gets the value of the tipoAgenda property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTipoAgenda() {
        return tipoAgenda;
    }

    /**
     * Sets the value of the tipoAgenda property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTipoAgenda(Short value) {
        this.tipoAgenda = value;
    }

}
