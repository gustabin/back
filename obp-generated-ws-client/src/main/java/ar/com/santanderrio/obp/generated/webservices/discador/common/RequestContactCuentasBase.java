
package ar.com.santanderrio.obp.generated.webservices.discador.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.RequestContactBase;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx089CnsDebAutRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx258ConsultaValoresDebitadosRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx278ConsultaModalidadTransferenciaRequest;
import ar.com.santanderrio.obp.generated.webservices.discador.Trx59ConsultaValoresAcreditarRequest;


/**
 * <p>Java class for RequestContactCuentasBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestContactCuentasBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sucursal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestContactCuentasBase", propOrder = {
    "indSinonimo",
    "nroCuenta",
    "sucursal",
    "tipoCuenta"
})
@XmlSeeAlso({
    Trx278ConsultaModalidadTransferenciaRequest.class,
    Trx258ConsultaValoresDebitadosRequest.class,
    Trx089CnsDebAutRequest.class,
    Trx59ConsultaValoresAcreditarRequest.class
})
public class RequestContactCuentasBase
    extends RequestContactBase
{

    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "NroCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", type = JAXBElement.class)
    protected JAXBElement<String> nroCuenta;
    @XmlElementRef(name = "Sucursal", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", type = JAXBElement.class)
    protected JAXBElement<String> sucursal;
    @XmlElementRef(name = "TipoCuenta", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common", type = JAXBElement.class)
    protected JAXBElement<String> tipoCuenta;

    /**
     * Gets the value of the indSinonimo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndSinonimo() {
        return indSinonimo;
    }

    /**
     * Sets the value of the indSinonimo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndSinonimo(JAXBElement<String> value) {
        this.indSinonimo = value;
    }

    /**
     * Gets the value of the nroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the value of the nroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroCuenta(JAXBElement<String> value) {
        this.nroCuenta = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSucursal(JAXBElement<String> value) {
        this.sucursal = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoCuenta(JAXBElement<String> value) {
        this.tipoCuenta = value;
    }

}
