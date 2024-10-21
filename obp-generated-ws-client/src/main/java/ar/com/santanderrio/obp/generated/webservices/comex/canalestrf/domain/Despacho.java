
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Despacho complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Despacho">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Banco_Emi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha_Embarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nom_Banco_Emisor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nro_Despacho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo_Aforo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Saldo_Despacho" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Tipo_Despacho" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Despacho", propOrder = {
    "bancoEmi",
    "fechaEmbarque",
    "idMoneda",
    "moneda",
    "nomBancoEmisor",
    "nroDespacho",
    "saldoAforo",
    "saldoDespacho",
    "tipoDespacho"
})
public class Despacho {

    @XmlElementRef(name = "Banco_Emi", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> bancoEmi;
    @XmlElementRef(name = "Fecha_Embarque", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> fechaEmbarque;
    @XmlElementRef(name = "Id_Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> idMoneda;
    @XmlElementRef(name = "Moneda", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> moneda;
    @XmlElementRef(name = "Nom_Banco_Emisor", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nomBancoEmisor;
    @XmlElementRef(name = "Nro_Despacho", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> nroDespacho;
    @XmlElementRef(name = "Saldo_Aforo", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> saldoAforo;
    @XmlElementRef(name = "Saldo_Despacho", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> saldoDespacho;
    @XmlElementRef(name = "Tipo_Despacho", namespace = "Domain", type = JAXBElement.class)
    protected JAXBElement<String> tipoDespacho;

    /**
     * Gets the value of the bancoEmi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoEmi() {
        return bancoEmi;
    }

    /**
     * Sets the value of the bancoEmi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoEmi(JAXBElement<String> value) {
        this.bancoEmi = value;
    }

    /**
     * Gets the value of the fechaEmbarque property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFechaEmbarque() {
        return fechaEmbarque;
    }

    /**
     * Sets the value of the fechaEmbarque property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFechaEmbarque(JAXBElement<String> value) {
        this.fechaEmbarque = value;
    }

    /**
     * Gets the value of the idMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdMoneda() {
        return idMoneda;
    }

    /**
     * Sets the value of the idMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdMoneda(JAXBElement<String> value) {
        this.idMoneda = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneda(JAXBElement<String> value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the nomBancoEmisor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomBancoEmisor() {
        return nomBancoEmisor;
    }

    /**
     * Sets the value of the nomBancoEmisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomBancoEmisor(JAXBElement<String> value) {
        this.nomBancoEmisor = value;
    }

    /**
     * Gets the value of the nroDespacho property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNroDespacho() {
        return nroDespacho;
    }

    /**
     * Sets the value of the nroDespacho property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNroDespacho(JAXBElement<String> value) {
        this.nroDespacho = value;
    }

    /**
     * Gets the value of the saldoAforo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSaldoAforo() {
        return saldoAforo;
    }

    /**
     * Sets the value of the saldoAforo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSaldoAforo(JAXBElement<BigDecimal> value) {
        this.saldoAforo = value;
    }

    /**
     * Gets the value of the saldoDespacho property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSaldoDespacho() {
        return saldoDespacho;
    }

    /**
     * Sets the value of the saldoDespacho property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSaldoDespacho(JAXBElement<BigDecimal> value) {
        this.saldoDespacho = value;
    }

    /**
     * Gets the value of the tipoDespacho property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoDespacho() {
        return tipoDespacho;
    }

    /**
     * Sets the value of the tipoDespacho property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoDespacho(JAXBElement<String> value) {
        this.tipoDespacho = value;
    }

}
