
package ar.com.santanderrio.obp.generated.webservices.mercado.canal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DatoFirma complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatoFirma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Dato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosFirmado" type="{http://schemas.datacontract.org/2004/07/Isban.Adsec.Service}DatosFirmado" minOccurs="0"/>
 *         &lt;element name="Firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoHash" type="{http://schemas.datacontract.org/2004/07/Isban.Adsec.Service}TipoHash" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatoFirma", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity", propOrder = {
    "dato",
    "datosFirmado",
    "firma",
    "tipoHash"
})
@XmlSeeAlso({
    ConsultarOperacionesMultimercadoParameterRequest.class
})
public class DatoFirma {

    @XmlElementRef(name = "Dato", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity", type = JAXBElement.class)
    protected JAXBElement<String> dato;
    @XmlElement(name = "DatosFirmado")
    protected DatosFirmado datosFirmado;
    @XmlElementRef(name = "Firma", namespace = "http://schemas.datacontract.org/2004/07/Isban.Adsec.Service.Entity", type = JAXBElement.class)
    protected JAXBElement<String> firma;
    @XmlElement(name = "TipoHash")
    protected TipoHash tipoHash;

    /**
     * Gets the value of the dato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDato() {
        return dato;
    }

    /**
     * Sets the value of the dato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDato(JAXBElement<String> value) {
        this.dato = value;
    }

    /**
     * Gets the value of the datosFirmado property.
     * 
     * @return
     *     possible object is
     *     {@link DatosFirmado }
     *     
     */
    public DatosFirmado getDatosFirmado() {
        return datosFirmado;
    }

    /**
     * Sets the value of the datosFirmado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosFirmado }
     *     
     */
    public void setDatosFirmado(DatosFirmado value) {
        this.datosFirmado = value;
    }

    /**
     * Gets the value of the firma property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirma(JAXBElement<String> value) {
        this.firma = value;
    }

    /**
     * Gets the value of the tipoHash property.
     * 
     * @return
     *     possible object is
     *     {@link TipoHash }
     *     
     */
    public TipoHash getTipoHash() {
        return tipoHash;
    }

    /**
     * Sets the value of the tipoHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoHash }
     *     
     */
    public void setTipoHash(TipoHash value) {
        this.tipoHash = value;
    }

}
