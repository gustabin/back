
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx59ConsultaValoresAcreditarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx59ConsultaValoresAcreditarResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="Cantidad_Valores_Acreditar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_24hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_24hs_CC_USS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_48hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_48hs_CC_USS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_72hs_CC_ARS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Depositos_72hs_CC_USS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValoresAcreditar" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059}ArrayOfTrx59ConsultaValoresAcreditarIterationResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx59ConsultaValoresAcreditarResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", propOrder = {
    "cantidadValoresAcreditar",
    "depositos24HsCCARS",
    "depositos24HsCCUSS",
    "depositos48HsCCARS",
    "depositos48HsCCUSS",
    "depositos72HsCCARS",
    "depositos72HsCCUSS",
    "valoresAcreditar"
})
public class Trx59ConsultaValoresAcreditarResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "Cantidad_Valores_Acreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> cantidadValoresAcreditar;
    @XmlElementRef(name = "Depositos_24hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos24HsCCARS;
    @XmlElementRef(name = "Depositos_24hs_CC_USS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos24HsCCUSS;
    @XmlElementRef(name = "Depositos_48hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos48HsCCARS;
    @XmlElementRef(name = "Depositos_48hs_CC_USS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos48HsCCUSS;
    @XmlElementRef(name = "Depositos_72hs_CC_ARS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos72HsCCARS;
    @XmlElementRef(name = "Depositos_72hs_CC_USS", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<String> depositos72HsCCUSS;
    @XmlElementRef(name = "ValoresAcreditar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx059", type = JAXBElement.class)
    protected JAXBElement<ArrayOfTrx59ConsultaValoresAcreditarIterationResponse> valoresAcreditar;

    /**
     * Gets the value of the cantidadValoresAcreditar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadValoresAcreditar() {
        return cantidadValoresAcreditar;
    }

    /**
     * Sets the value of the cantidadValoresAcreditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadValoresAcreditar(JAXBElement<String> value) {
        this.cantidadValoresAcreditar = value;
    }

    /**
     * Gets the value of the depositos24HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos24HsCCARS() {
        return depositos24HsCCARS;
    }

    /**
     * Sets the value of the depositos24HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos24HsCCARS(JAXBElement<String> value) {
        this.depositos24HsCCARS = value;
    }

    /**
     * Gets the value of the depositos24HsCCUSS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos24HsCCUSS() {
        return depositos24HsCCUSS;
    }

    /**
     * Sets the value of the depositos24HsCCUSS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos24HsCCUSS(JAXBElement<String> value) {
        this.depositos24HsCCUSS = value;
    }

    /**
     * Gets the value of the depositos48HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos48HsCCARS() {
        return depositos48HsCCARS;
    }

    /**
     * Sets the value of the depositos48HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos48HsCCARS(JAXBElement<String> value) {
        this.depositos48HsCCARS = value;
    }

    /**
     * Gets the value of the depositos48HsCCUSS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos48HsCCUSS() {
        return depositos48HsCCUSS;
    }

    /**
     * Sets the value of the depositos48HsCCUSS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos48HsCCUSS(JAXBElement<String> value) {
        this.depositos48HsCCUSS = value;
    }

    /**
     * Gets the value of the depositos72HsCCARS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos72HsCCARS() {
        return depositos72HsCCARS;
    }

    /**
     * Sets the value of the depositos72HsCCARS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos72HsCCARS(JAXBElement<String> value) {
        this.depositos72HsCCARS = value;
    }

    /**
     * Gets the value of the depositos72HsCCUSS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepositos72HsCCUSS() {
        return depositos72HsCCUSS;
    }

    /**
     * Sets the value of the depositos72HsCCUSS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepositos72HsCCUSS(JAXBElement<String> value) {
        this.depositos72HsCCUSS = value;
    }

    /**
     * Gets the value of the valoresAcreditar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx59ConsultaValoresAcreditarIterationResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTrx59ConsultaValoresAcreditarIterationResponse> getValoresAcreditar() {
        return valoresAcreditar;
    }

    /**
     * Sets the value of the valoresAcreditar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTrx59ConsultaValoresAcreditarIterationResponse }{@code >}
     *     
     */
    public void setValoresAcreditar(JAXBElement<ArrayOfTrx59ConsultaValoresAcreditarIterationResponse> value) {
        this.valoresAcreditar = value;
    }

}
