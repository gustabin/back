
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.BaseCursor;


/**
 * <p>Clase Java para ConsultaBancosRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaBancosRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}BaseCursor">
 *       &lt;sequence>
 *         &lt;element name="Aba_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Localidad_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Solo_Seguimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Swift_Banco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaBancosRequest", propOrder = {
    "abaBanco",
    "localidadBanco",
    "nombreBanco",
    "paisBanco",
    "soloSeguimiento",
    "swiftBanco"
})
public class ConsultaBancosRequest
    extends BaseCursor
{

    @XmlElementRef(name = "Aba_Banco", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> abaBanco;
    @XmlElementRef(name = "Localidad_Banco", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> localidadBanco;
    @XmlElementRef(name = "Nombre_Banco", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> nombreBanco;
    @XmlElementRef(name = "Pais_Banco", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> paisBanco;
    @XmlElementRef(name = "Solo_Seguimiento", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> soloSeguimiento;
    @XmlElementRef(name = "Swift_Banco", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> swiftBanco;

    /**
     * Obtiene el valor de la propiedad abaBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAbaBanco() {
        return abaBanco;
    }

    /**
     * Define el valor de la propiedad abaBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAbaBanco(JAXBElement<String> value) {
        this.abaBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad localidadBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadBanco() {
        return localidadBanco;
    }

    /**
     * Define el valor de la propiedad localidadBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadBanco(JAXBElement<String> value) {
        this.localidadBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBanco() {
        return nombreBanco;
    }

    /**
     * Define el valor de la propiedad nombreBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBanco(JAXBElement<String> value) {
        this.nombreBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad paisBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisBanco() {
        return paisBanco;
    }

    /**
     * Define el valor de la propiedad paisBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisBanco(JAXBElement<String> value) {
        this.paisBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad soloSeguimiento.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSoloSeguimiento() {
        return soloSeguimiento;
    }

    /**
     * Define el valor de la propiedad soloSeguimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSoloSeguimiento(JAXBElement<String> value) {
        this.soloSeguimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad swiftBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSwiftBanco() {
        return swiftBanco;
    }

    /**
     * Define el valor de la propiedad swiftBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSwiftBanco(JAXBElement<String> value) {
        this.swiftBanco = value;
    }

}
