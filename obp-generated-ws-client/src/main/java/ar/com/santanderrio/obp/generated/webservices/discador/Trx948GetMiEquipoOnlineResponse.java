
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx948GetMiEquipoOnlineResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx948GetMiEquipoOnlineResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Carterizado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClienteGes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EsolWatson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RentaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Zona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx948GetMiEquipoOnlineResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", propOrder = {
    "carterizado",
    "clienteGes",
    "esolWatson",
    "grupoDesborde",
    "grupoOperador",
    "legajoDesborde",
    "legajoOperador",
    "rentaCliente",
    "zona"
})
public class Trx948GetMiEquipoOnlineResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Carterizado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> carterizado;
    @XmlElementRef(name = "ClienteGes", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> clienteGes;
    @XmlElementRef(name = "EsolWatson", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> esolWatson;
    @XmlElementRef(name = "GrupoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> grupoDesborde;
    @XmlElementRef(name = "GrupoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> grupoOperador;
    @XmlElementRef(name = "LegajoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> legajoDesborde;
    @XmlElementRef(name = "LegajoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> legajoOperador;
    @XmlElementRef(name = "RentaCliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> rentaCliente;
    @XmlElementRef(name = "Zona", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx948", type = JAXBElement.class)
    protected JAXBElement<String> zona;

    /**
     * Gets the value of the carterizado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCarterizado() {
        return carterizado;
    }

    /**
     * Sets the value of the carterizado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCarterizado(JAXBElement<String> value) {
        this.carterizado = value;
    }

    /**
     * Gets the value of the clienteGes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClienteGes() {
        return clienteGes;
    }

    /**
     * Sets the value of the clienteGes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClienteGes(JAXBElement<String> value) {
        this.clienteGes = value;
    }

    /**
     * Gets the value of the esolWatson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEsolWatson() {
        return esolWatson;
    }

    /**
     * Sets the value of the esolWatson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEsolWatson(JAXBElement<String> value) {
        this.esolWatson = value;
    }

    /**
     * Gets the value of the grupoDesborde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoDesborde() {
        return grupoDesborde;
    }

    /**
     * Sets the value of the grupoDesborde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoDesborde(JAXBElement<String> value) {
        this.grupoDesborde = value;
    }

    /**
     * Gets the value of the grupoOperador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGrupoOperador() {
        return grupoOperador;
    }

    /**
     * Sets the value of the grupoOperador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGrupoOperador(JAXBElement<String> value) {
        this.grupoOperador = value;
    }

    /**
     * Gets the value of the legajoDesborde property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLegajoDesborde() {
        return legajoDesborde;
    }

    /**
     * Sets the value of the legajoDesborde property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLegajoDesborde(JAXBElement<String> value) {
        this.legajoDesborde = value;
    }

    /**
     * Gets the value of the legajoOperador property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLegajoOperador() {
        return legajoOperador;
    }

    /**
     * Sets the value of the legajoOperador property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLegajoOperador(JAXBElement<String> value) {
        this.legajoOperador = value;
    }

    /**
     * Gets the value of the rentaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRentaCliente() {
        return rentaCliente;
    }

    /**
     * Sets the value of the rentaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRentaCliente(JAXBElement<String> value) {
        this.rentaCliente = value;
    }

    /**
     * Gets the value of the zona property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZona() {
        return zona;
    }

    /**
     * Sets the value of the zona property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZona(JAXBElement<String> value) {
        this.zona = value;
    }

}
