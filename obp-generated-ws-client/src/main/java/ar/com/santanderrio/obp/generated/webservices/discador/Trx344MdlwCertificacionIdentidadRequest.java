
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.discador.contracts.request.common.TDatosClienteRequest;


/**
 * <p>Java class for Trx344MdlwCertificacionIdentidadRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx344MdlwCertificacionIdentidadRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Cliente" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Request}TDatosClienteRequest" minOccurs="0"/>
 *         &lt;element name="Encriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinEncript" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InicioEncriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudEncriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudSinEncriptar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SinEncriptar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx344MdlwCertificacionIdentidadRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", propOrder = {
    "cliente",
    "encriptado",
    "finEncript",
    "inicioEncriptado",
    "longitudEncriptado",
    "longitudSinEncriptar",
    "marcaOperacion",
    "sinEncriptar"
})
public class Trx344MdlwCertificacionIdentidadRequest
    extends RequestBase
{

    @XmlElementRef(name = "Cliente", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<TDatosClienteRequest> cliente;
    @XmlElementRef(name = "Encriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> encriptado;
    @XmlElementRef(name = "FinEncript", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> finEncript;
    @XmlElementRef(name = "InicioEncriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> inicioEncriptado;
    @XmlElementRef(name = "LongitudEncriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> longitudEncriptado;
    @XmlElementRef(name = "LongitudSinEncriptar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> longitudSinEncriptar;
    @XmlElementRef(name = "MarcaOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> marcaOperacion;
    @XmlElementRef(name = "SinEncriptar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx344", type = JAXBElement.class)
    protected JAXBElement<String> sinEncriptar;

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public JAXBElement<TDatosClienteRequest> getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TDatosClienteRequest }{@code >}
     *     
     */
    public void setCliente(JAXBElement<TDatosClienteRequest> value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the encriptado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEncriptado() {
        return encriptado;
    }

    /**
     * Sets the value of the encriptado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEncriptado(JAXBElement<String> value) {
        this.encriptado = value;
    }

    /**
     * Gets the value of the finEncript property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFinEncript() {
        return finEncript;
    }

    /**
     * Sets the value of the finEncript property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFinEncript(JAXBElement<String> value) {
        this.finEncript = value;
    }

    /**
     * Gets the value of the inicioEncriptado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInicioEncriptado() {
        return inicioEncriptado;
    }

    /**
     * Sets the value of the inicioEncriptado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInicioEncriptado(JAXBElement<String> value) {
        this.inicioEncriptado = value;
    }

    /**
     * Gets the value of the longitudEncriptado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitudEncriptado() {
        return longitudEncriptado;
    }

    /**
     * Sets the value of the longitudEncriptado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitudEncriptado(JAXBElement<String> value) {
        this.longitudEncriptado = value;
    }

    /**
     * Gets the value of the longitudSinEncriptar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongitudSinEncriptar() {
        return longitudSinEncriptar;
    }

    /**
     * Sets the value of the longitudSinEncriptar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongitudSinEncriptar(JAXBElement<String> value) {
        this.longitudSinEncriptar = value;
    }

    /**
     * Gets the value of the marcaOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaOperacion() {
        return marcaOperacion;
    }

    /**
     * Sets the value of the marcaOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaOperacion(JAXBElement<String> value) {
        this.marcaOperacion = value;
    }

    /**
     * Gets the value of the sinEncriptar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSinEncriptar() {
        return sinEncriptar;
    }

    /**
     * Sets the value of the sinEncriptar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSinEncriptar(JAXBElement<String> value) {
        this.sinEncriptar = value;
    }

}
