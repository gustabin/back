
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx168TransfOtroBanCciResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx168TransfOtroBanCciResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CodigoEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaCompensacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteDebitado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndBD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroSecuencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx168TransfOtroBanCciResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", propOrder = {
    "codigoEstado",
    "fechaCompensacion",
    "importeDebitado",
    "indBD",
    "numeroComprobante",
    "numeroSecuencia"
})
public class Trx168TransfOtroBanCciResponse
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CodigoEstado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> codigoEstado;
    @XmlElementRef(name = "FechaCompensacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> fechaCompensacion;
    @XmlElementRef(name = "ImporteDebitado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> importeDebitado;
    @XmlElementRef(name = "IndBD", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> indBD;
    @XmlElementRef(name = "NumeroComprobante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> numeroComprobante;
    @XmlElementRef(name = "NumeroSecuencia", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx168", type = JAXBElement.class)
    protected JAXBElement<String> numeroSecuencia;

    /**
     * Gets the value of the codigoEstado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * Sets the value of the codigoEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodigoEstado(JAXBElement<String> value) {
        this.codigoEstado = value;
    }

    /**
     * Gets the value of the fechaCompensacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaCompensacion() {
        return fechaCompensacion;
    }

    /**
     * Sets the value of the fechaCompensacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaCompensacion(JAXBElement<String> value) {
        this.fechaCompensacion = value;
    }

    /**
     * Gets the value of the importeDebitado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImporteDebitado() {
        return importeDebitado;
    }

    /**
     * Sets the value of the importeDebitado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImporteDebitado(JAXBElement<String> value) {
        this.importeDebitado = value;
    }

    /**
     * Gets the value of the indBD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndBD() {
        return indBD;
    }

    /**
     * Sets the value of the indBD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndBD(JAXBElement<String> value) {
        this.indBD = value;
    }

    /**
     * Gets the value of the numeroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Sets the value of the numeroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroComprobante(JAXBElement<String> value) {
        this.numeroComprobante = value;
    }

    /**
     * Gets the value of the numeroSecuencia property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroSecuencia() {
        return numeroSecuencia;
    }

    /**
     * Sets the value of the numeroSecuencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroSecuencia(JAXBElement<String> value) {
        this.numeroSecuencia = value;
    }

}
