
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx168TransfOtroBanCciRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx168TransfOtroBanCciRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="AliasCbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantidadDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndicadorFuncion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Periodica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlazoAcreditacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransfACH" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168}Trx168TransferenciaAch" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx168TransfOtroBanCciRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", propOrder = {
    "aliasCbu",
    "cantidadDias",
    "idTransferencia",
    "indicadorFuncion",
    "periodica",
    "plazoAcreditacion",
    "transfACH"
})
public class Trx168TransfOtroBanCciRequest
    extends RequestBase
{

    @XmlElementRef(name = "AliasCbu", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> aliasCbu;
    @XmlElementRef(name = "CantidadDias", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> cantidadDias;
    @XmlElementRef(name = "IDTransferencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> idTransferencia;
    @XmlElementRef(name = "IndicadorFuncion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> indicadorFuncion;
    @XmlElementRef(name = "Periodica", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> periodica;
    @XmlElementRef(name = "PlazoAcreditacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> plazoAcreditacion;
    @XmlElementRef(name = "TransfACH", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<Trx168TransferenciaAch> transfACH;

    /**
     * Gets the value of the aliasCbu property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAliasCbu() {
        return aliasCbu;
    }

    /**
     * Sets the value of the aliasCbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAliasCbu(JAXBElement<String> value) {
        this.aliasCbu = value;
    }

    /**
     * Gets the value of the cantidadDias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCantidadDias() {
        return cantidadDias;
    }

    /**
     * Sets the value of the cantidadDias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCantidadDias(JAXBElement<String> value) {
        this.cantidadDias = value;
    }

    /**
     * Gets the value of the idTransferencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIDTransferencia() {
        return idTransferencia;
    }

    /**
     * Sets the value of the idTransferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIDTransferencia(JAXBElement<String> value) {
        this.idTransferencia = value;
    }

    /**
     * Gets the value of the indicadorFuncion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndicadorFuncion() {
        return indicadorFuncion;
    }

    /**
     * Sets the value of the indicadorFuncion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndicadorFuncion(JAXBElement<String> value) {
        this.indicadorFuncion = value;
    }

    /**
     * Gets the value of the periodica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPeriodica() {
        return periodica;
    }

    /**
     * Sets the value of the periodica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPeriodica(JAXBElement<String> value) {
        this.periodica = value;
    }

    /**
     * Gets the value of the plazoAcreditacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazoAcreditacion() {
        return plazoAcreditacion;
    }

    /**
     * Sets the value of the plazoAcreditacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazoAcreditacion(JAXBElement<String> value) {
        this.plazoAcreditacion = value;
    }

    /**
     * Gets the value of the transfACH property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Trx168TransferenciaAch }{@code >}
     *     
     */
    public JAXBElement<Trx168TransferenciaAch> getTransfACH() {
        return transfACH;
    }

    /**
     * Sets the value of the transfACH property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Trx168TransferenciaAch }{@code >}
     *     
     */
    public void setTransfACH(JAXBElement<Trx168TransferenciaAch> value) {
        this.transfACH = value;
    }

}
