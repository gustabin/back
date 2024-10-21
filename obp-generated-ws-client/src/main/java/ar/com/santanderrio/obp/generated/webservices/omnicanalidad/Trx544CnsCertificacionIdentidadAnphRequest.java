
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx544CnsCertificacionIdentidadAnphRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx544CnsCertificacionIdentidadAnphRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestContactBase">
 *       &lt;sequence>
 *         &lt;element name="Encriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinEncriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndSinonimo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InicioEncriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudEncriptado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudSinEncriptar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaIdentificacionPositiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "Trx544CnsCertificacionIdentidadAnphRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", propOrder = {
    "encriptado",
    "finEncriptado",
    "indSinonimo",
    "inicioEncriptado",
    "longitudEncriptado",
    "longitudSinEncriptar",
    "marcaIdentificacionPositiva",
    "marcaOperacion",
    "sinEncriptar"
})
public class Trx544CnsCertificacionIdentidadAnphRequest
    extends RequestContactBase
{

    @XmlElementRef(name = "Encriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> encriptado;
    @XmlElementRef(name = "FinEncriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> finEncriptado;
    @XmlElementRef(name = "IndSinonimo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> indSinonimo;
    @XmlElementRef(name = "InicioEncriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> inicioEncriptado;
    @XmlElementRef(name = "LongitudEncriptado", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> longitudEncriptado;
    @XmlElementRef(name = "LongitudSinEncriptar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> longitudSinEncriptar;
    @XmlElementRef(name = "MarcaIdentificacionPositiva", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> marcaIdentificacionPositiva;
    @XmlElementRef(name = "MarcaOperacion", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> marcaOperacion;
    @XmlElementRef(name = "SinEncriptar", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx544", type = JAXBElement.class)
    protected JAXBElement<String> sinEncriptar;

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
     * Gets the value of the finEncriptado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFinEncriptado() {
        return finEncriptado;
    }

    /**
     * Sets the value of the finEncriptado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFinEncriptado(JAXBElement<String> value) {
        this.finEncriptado = value;
    }

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
     * Gets the value of the marcaIdentificacionPositiva property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaIdentificacionPositiva() {
        return marcaIdentificacionPositiva;
    }

    /**
     * Sets the value of the marcaIdentificacionPositiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaIdentificacionPositiva(JAXBElement<String> value) {
        this.marcaIdentificacionPositiva = value;
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
