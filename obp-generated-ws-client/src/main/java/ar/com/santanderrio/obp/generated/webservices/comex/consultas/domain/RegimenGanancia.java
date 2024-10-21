
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RegimenGanancia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RegimenGanancia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Alicuota_Benef" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Alicuota_Deudor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Con_cdi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_inpi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Alicuota" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="Pais_bcra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegimenGanancia", propOrder = {
    "alicuotaBenef",
    "alicuotaDeudor",
    "conCdi",
    "conInpi",
    "descripcion",
    "idAlicuota",
    "paisBcra"
})
public class RegimenGanancia {

    @XmlElementRef(name = "Alicuota_Benef", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaBenef;
    @XmlElementRef(name = "Alicuota_Deudor", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> alicuotaDeudor;
    @XmlElementRef(name = "Con_cdi", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> conCdi;
    @XmlElementRef(name = "Con_inpi", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> conInpi;
    @XmlElementRef(name = "Descripcion", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> descripcion;
    @XmlElementRef(name = "Id_Alicuota", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<Short> idAlicuota;
    @XmlElementRef(name = "Pais_bcra", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> paisBcra;

    /**
     * Obtiene el valor de la propiedad alicuotaBenef.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAlicuotaBenef() {
        return alicuotaBenef;
    }

    /**
     * Define el valor de la propiedad alicuotaBenef.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAlicuotaBenef(JAXBElement<BigDecimal> value) {
        this.alicuotaBenef = value;
    }

    /**
     * Obtiene el valor de la propiedad alicuotaDeudor.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAlicuotaDeudor() {
        return alicuotaDeudor;
    }

    /**
     * Define el valor de la propiedad alicuotaDeudor.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAlicuotaDeudor(JAXBElement<BigDecimal> value) {
        this.alicuotaDeudor = value;
    }

    /**
     * Obtiene el valor de la propiedad conCdi.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConCdi() {
        return conCdi;
    }

    /**
     * Define el valor de la propiedad conCdi.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConCdi(JAXBElement<String> value) {
        this.conCdi = value;
    }

    /**
     * Obtiene el valor de la propiedad conInpi.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConInpi() {
        return conInpi;
    }

    /**
     * Define el valor de la propiedad conInpi.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConInpi(JAXBElement<String> value) {
        this.conInpi = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcion(JAXBElement<String> value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad idAlicuota.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getIdAlicuota() {
        return idAlicuota;
    }

    /**
     * Define el valor de la propiedad idAlicuota.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setIdAlicuota(JAXBElement<Short> value) {
        this.idAlicuota = value;
    }

    /**
     * Obtiene el valor de la propiedad paisBcra.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisBcra() {
        return paisBcra;
    }

    /**
     * Define el valor de la propiedad paisBcra.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisBcra(JAXBElement<String> value) {
        this.paisBcra = value;
    }

}
