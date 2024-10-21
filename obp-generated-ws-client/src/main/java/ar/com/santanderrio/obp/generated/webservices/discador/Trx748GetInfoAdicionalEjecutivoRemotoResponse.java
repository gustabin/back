
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx748GetInfoAdicionalEjecutivoRemotoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx748GetInfoAdicionalEjecutivoRemotoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Carterizado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GrupoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoDesborde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegajoOperador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx748GetInfoAdicionalEjecutivoRemotoResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", propOrder = {
    "carterizado",
    "grupoDesborde",
    "grupoOperador",
    "legajoDesborde",
    "legajoOperador"
})
public class Trx748GetInfoAdicionalEjecutivoRemotoResponse
    extends ResponseBase
{

    @XmlElementRef(name = "Carterizado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", type = JAXBElement.class)
    protected JAXBElement<String> carterizado;
    @XmlElementRef(name = "GrupoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", type = JAXBElement.class)
    protected JAXBElement<String> grupoDesborde;
    @XmlElementRef(name = "GrupoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", type = JAXBElement.class)
    protected JAXBElement<String> grupoOperador;
    @XmlElementRef(name = "LegajoDesborde", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", type = JAXBElement.class)
    protected JAXBElement<String> legajoDesborde;
    @XmlElementRef(name = "LegajoOperador", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx748", type = JAXBElement.class)
    protected JAXBElement<String> legajoOperador;

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

}
