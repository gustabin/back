
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx113CnsAdhesionAdomTcResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx113CnsAdhesionAdomTcResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="FormaPagoTarjeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SucursalCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuentaDebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx113CnsAdhesionAdomTcResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx113", propOrder = {
    "formaPagoTarjeta",
    "numeroCuentaDebito",
    "sucursalCuentaDebito",
    "tipoCuentaDebito"
})
public class Trx113CnsAdhesionAdomTcResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "FormaPagoTarjeta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx113", type = JAXBElement.class)
    protected JAXBElement<String> formaPagoTarjeta;
    @XmlElementRef(name = "NumeroCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx113", type = JAXBElement.class)
    protected JAXBElement<String> numeroCuentaDebito;
    @XmlElementRef(name = "SucursalCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx113", type = JAXBElement.class)
    protected JAXBElement<String> sucursalCuentaDebito;
    @XmlElementRef(name = "TipoCuentaDebito", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx113", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuentaDebito;

    /**
     * Gets the value of the formaPagoTarjeta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormaPagoTarjeta() {
        return formaPagoTarjeta;
    }

    /**
     * Sets the value of the formaPagoTarjeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormaPagoTarjeta(JAXBElement<String> value) {
        this.formaPagoTarjeta = value;
    }

    /**
     * Gets the value of the numeroCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroCuentaDebito() {
        return numeroCuentaDebito;
    }

    /**
     * Sets the value of the numeroCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroCuentaDebito(JAXBElement<String> value) {
        this.numeroCuentaDebito = value;
    }

    /**
     * Gets the value of the sucursalCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursalCuentaDebito() {
        return sucursalCuentaDebito;
    }

    /**
     * Sets the value of the sucursalCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursalCuentaDebito(JAXBElement<String> value) {
        this.sucursalCuentaDebito = value;
    }

    /**
     * Gets the value of the tipoCuentaDebito property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuentaDebito() {
        return tipoCuentaDebito;
    }

    /**
     * Sets the value of the tipoCuentaDebito property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuentaDebito(JAXBElement<String> value) {
        this.tipoCuentaDebito = value;
    }

}
