
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx6003GetInfoClienteSelectResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx6003GetInfoClienteSelectResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}ResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CelularEjecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuadrante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutiveAttentionSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutiveLoan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdEjecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsAdvance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsSelect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsUniv" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MailEjecutivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MarcaSelectOnline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pesubseg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pesucadm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Picture" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="SelectOnlineAttentionSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SemaforoRentaFac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoMasivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx6003GetInfoClienteSelectResponse", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", propOrder = {
    "celularEjecutivo",
    "cuadrante",
    "ejecutivo",
    "executiveAttentionSchedule",
    "executiveLoan",
    "idEjecutivo",
    "isAdvance",
    "isSelect",
    "isUniv",
    "mailEjecutivo",
    "marcaSelectOnline",
    "pesubseg",
    "pesucadm",
    "picture",
    "selectOnlineAttentionSchedule",
    "semaforoRentaFac",
    "telefonoMasivo"
})
public class Trx6003GetInfoClienteSelectResponse
    extends ResponseBase
{

    @XmlElementRef(name = "CelularEjecutivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> celularEjecutivo;
    @XmlElementRef(name = "Cuadrante", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> cuadrante;
    @XmlElementRef(name = "Ejecutivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> ejecutivo;
    @XmlElementRef(name = "ExecutiveAttentionSchedule", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> executiveAttentionSchedule;
    @XmlElementRef(name = "ExecutiveLoan", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> executiveLoan;
    @XmlElementRef(name = "IdEjecutivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> idEjecutivo;
    @XmlElement(name = "IsAdvance")
    protected Boolean isAdvance;
    @XmlElement(name = "IsSelect")
    protected Boolean isSelect;
    @XmlElement(name = "IsUniv")
    protected Boolean isUniv;
    @XmlElementRef(name = "MailEjecutivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> mailEjecutivo;
    @XmlElementRef(name = "MarcaSelectOnline", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> marcaSelectOnline;
    @XmlElementRef(name = "Pesubseg", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> pesubseg;
    @XmlElementRef(name = "Pesucadm", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> pesucadm;
    @XmlElementRef(name = "Picture", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<byte[]> picture;
    @XmlElementRef(name = "SelectOnlineAttentionSchedule", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> selectOnlineAttentionSchedule;
    @XmlElementRef(name = "SemaforoRentaFac", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> semaforoRentaFac;
    @XmlElementRef(name = "TelefonoMasivo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx6003", type = JAXBElement.class)
    protected JAXBElement<String> telefonoMasivo;

    /**
     * Gets the value of the celularEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCelularEjecutivo() {
        return celularEjecutivo;
    }

    /**
     * Sets the value of the celularEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCelularEjecutivo(JAXBElement<String> value) {
        this.celularEjecutivo = value;
    }

    /**
     * Gets the value of the cuadrante property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCuadrante() {
        return cuadrante;
    }

    /**
     * Sets the value of the cuadrante property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCuadrante(JAXBElement<String> value) {
        this.cuadrante = value;
    }

    /**
     * Gets the value of the ejecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEjecutivo() {
        return ejecutivo;
    }

    /**
     * Sets the value of the ejecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEjecutivo(JAXBElement<String> value) {
        this.ejecutivo = value;
    }

    /**
     * Gets the value of the executiveAttentionSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExecutiveAttentionSchedule() {
        return executiveAttentionSchedule;
    }

    /**
     * Sets the value of the executiveAttentionSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExecutiveAttentionSchedule(JAXBElement<String> value) {
        this.executiveAttentionSchedule = value;
    }

    /**
     * Gets the value of the executiveLoan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExecutiveLoan() {
        return executiveLoan;
    }

    /**
     * Sets the value of the executiveLoan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExecutiveLoan(JAXBElement<String> value) {
        this.executiveLoan = value;
    }

    /**
     * Gets the value of the idEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdEjecutivo() {
        return idEjecutivo;
    }

    /**
     * Sets the value of the idEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdEjecutivo(JAXBElement<String> value) {
        this.idEjecutivo = value;
    }

    /**
     * Gets the value of the isAdvance property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAdvance() {
        return isAdvance;
    }

    /**
     * Sets the value of the isAdvance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAdvance(Boolean value) {
        this.isAdvance = value;
    }

    /**
     * Gets the value of the isSelect property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSelect() {
        return isSelect;
    }

    /**
     * Sets the value of the isSelect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSelect(Boolean value) {
        this.isSelect = value;
    }

    /**
     * Gets the value of the isUniv property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsUniv() {
        return isUniv;
    }

    /**
     * Sets the value of the isUniv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsUniv(Boolean value) {
        this.isUniv = value;
    }

    /**
     * Gets the value of the mailEjecutivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMailEjecutivo() {
        return mailEjecutivo;
    }

    /**
     * Sets the value of the mailEjecutivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMailEjecutivo(JAXBElement<String> value) {
        this.mailEjecutivo = value;
    }

    /**
     * Gets the value of the marcaSelectOnline property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMarcaSelectOnline() {
        return marcaSelectOnline;
    }

    /**
     * Sets the value of the marcaSelectOnline property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMarcaSelectOnline(JAXBElement<String> value) {
        this.marcaSelectOnline = value;
    }

    /**
     * Gets the value of the pesubseg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPesubseg() {
        return pesubseg;
    }

    /**
     * Sets the value of the pesubseg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPesubseg(JAXBElement<String> value) {
        this.pesubseg = value;
    }

    /**
     * Gets the value of the pesucadm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPesucadm() {
        return pesucadm;
    }

    /**
     * Sets the value of the pesucadm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPesucadm(JAXBElement<String> value) {
        this.pesucadm = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setPicture(JAXBElement<byte[]> value) {
        this.picture = value;
    }

    /**
     * Gets the value of the selectOnlineAttentionSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSelectOnlineAttentionSchedule() {
        return selectOnlineAttentionSchedule;
    }

    /**
     * Sets the value of the selectOnlineAttentionSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSelectOnlineAttentionSchedule(JAXBElement<String> value) {
        this.selectOnlineAttentionSchedule = value;
    }

    /**
     * Gets the value of the semaforoRentaFac property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSemaforoRentaFac() {
        return semaforoRentaFac;
    }

    /**
     * Sets the value of the semaforoRentaFac property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSemaforoRentaFac(JAXBElement<String> value) {
        this.semaforoRentaFac = value;
    }

    /**
     * Gets the value of the telefonoMasivo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTelefonoMasivo() {
        return telefonoMasivo;
    }

    /**
     * Sets the value of the telefonoMasivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTelefonoMasivo(JAXBElement<String> value) {
        this.telefonoMasivo = value;
    }

}
