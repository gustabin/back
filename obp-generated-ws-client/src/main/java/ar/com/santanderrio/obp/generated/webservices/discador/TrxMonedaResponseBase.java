
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrxMonedaResponseBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrxMonedaResponseBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxHeaderResponseBase">
 *       &lt;sequence>
 *         &lt;element name="CCCABON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CCCCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTORI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODABON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODALTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODCANAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONABON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CONCIMP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIVABON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIVCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHVAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPABON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPCARG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPCOTI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPIMPU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMPUEST" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMCLIE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NROMABO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NROMCAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PPRIAPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PSEGAPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGIMEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SDOABOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SDOCARB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SDODABO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SDODCAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIPCAMB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrxMonedaResponseBase", propOrder = {
    "cccabon",
    "ccccarg",
    "centori",
    "codabon",
    "codalta",
    "codcanal",
    "codcarg",
    "conabon",
    "concarg",
    "concimp",
    "divabon",
    "divcarg",
    "fechval",
    "impabon",
    "impcarg",
    "impcoti",
    "impimpu",
    "impuest",
    "nomclie",
    "nromabo",
    "nromcar",
    "ppriape",
    "psegape",
    "regimen",
    "sdoabob",
    "sdocarb",
    "sdodabo",
    "sdodcar",
    "tipcamb"
})
@XmlSeeAlso({
    Trx739MdlwAutorizBancoVendeMonedaExtranjeraResponse.class,
    Trx738MdlwSimulacionBancoVendeMonedaExtranjeraResponse.class
})
public class TrxMonedaResponseBase
    extends TrxHeaderResponseBase
{

    @XmlElementRef(name = "CCCABON", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> cccabon;
    @XmlElementRef(name = "CCCCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> ccccarg;
    @XmlElementRef(name = "CENTORI", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> centori;
    @XmlElementRef(name = "CODABON", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codabon;
    @XmlElementRef(name = "CODALTA", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codalta;
    @XmlElementRef(name = "CODCANAL", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codcanal;
    @XmlElementRef(name = "CODCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> codcarg;
    @XmlElementRef(name = "CONABON", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> conabon;
    @XmlElementRef(name = "CONCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> concarg;
    @XmlElementRef(name = "CONCIMP", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> concimp;
    @XmlElementRef(name = "DIVABON", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> divabon;
    @XmlElementRef(name = "DIVCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> divcarg;
    @XmlElementRef(name = "FECHVAL", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> fechval;
    @XmlElementRef(name = "IMPABON", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> impabon;
    @XmlElementRef(name = "IMPCARG", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> impcarg;
    @XmlElementRef(name = "IMPCOTI", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> impcoti;
    @XmlElementRef(name = "IMPIMPU", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> impimpu;
    @XmlElementRef(name = "IMPUEST", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> impuest;
    @XmlElementRef(name = "NOMCLIE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nomclie;
    @XmlElementRef(name = "NROMABO", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nromabo;
    @XmlElementRef(name = "NROMCAR", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> nromcar;
    @XmlElementRef(name = "PPRIAPE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> ppriape;
    @XmlElementRef(name = "PSEGAPE", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> psegape;
    @XmlElementRef(name = "REGIMEN", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> regimen;
    @XmlElementRef(name = "SDOABOB", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sdoabob;
    @XmlElementRef(name = "SDOCARB", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sdocarb;
    @XmlElementRef(name = "SDODABO", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sdodabo;
    @XmlElementRef(name = "SDODCAR", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> sdodcar;
    @XmlElementRef(name = "TIPCAMB", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs", type = JAXBElement.class)
    protected JAXBElement<String> tipcamb;

    /**
     * Gets the value of the cccabon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCCCABON() {
        return cccabon;
    }

    /**
     * Sets the value of the cccabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCCCABON(JAXBElement<String> value) {
        this.cccabon = value;
    }

    /**
     * Gets the value of the ccccarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCCCCARG() {
        return ccccarg;
    }

    /**
     * Sets the value of the ccccarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCCCCARG(JAXBElement<String> value) {
        this.ccccarg = value;
    }

    /**
     * Gets the value of the centori property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCENTORI() {
        return centori;
    }

    /**
     * Sets the value of the centori property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCENTORI(JAXBElement<String> value) {
        this.centori = value;
    }

    /**
     * Gets the value of the codabon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODABON() {
        return codabon;
    }

    /**
     * Sets the value of the codabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODABON(JAXBElement<String> value) {
        this.codabon = value;
    }

    /**
     * Gets the value of the codalta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODALTA() {
        return codalta;
    }

    /**
     * Sets the value of the codalta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODALTA(JAXBElement<String> value) {
        this.codalta = value;
    }

    /**
     * Gets the value of the codcanal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODCANAL() {
        return codcanal;
    }

    /**
     * Sets the value of the codcanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODCANAL(JAXBElement<String> value) {
        this.codcanal = value;
    }

    /**
     * Gets the value of the codcarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCODCARG() {
        return codcarg;
    }

    /**
     * Sets the value of the codcarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCODCARG(JAXBElement<String> value) {
        this.codcarg = value;
    }

    /**
     * Gets the value of the conabon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCONABON() {
        return conabon;
    }

    /**
     * Sets the value of the conabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCONABON(JAXBElement<String> value) {
        this.conabon = value;
    }

    /**
     * Gets the value of the concarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCONCARG() {
        return concarg;
    }

    /**
     * Sets the value of the concarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCONCARG(JAXBElement<String> value) {
        this.concarg = value;
    }

    /**
     * Gets the value of the concimp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCONCIMP() {
        return concimp;
    }

    /**
     * Sets the value of the concimp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCONCIMP(JAXBElement<String> value) {
        this.concimp = value;
    }

    /**
     * Gets the value of the divabon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDIVABON() {
        return divabon;
    }

    /**
     * Sets the value of the divabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDIVABON(JAXBElement<String> value) {
        this.divabon = value;
    }

    /**
     * Gets the value of the divcarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDIVCARG() {
        return divcarg;
    }

    /**
     * Sets the value of the divcarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDIVCARG(JAXBElement<String> value) {
        this.divcarg = value;
    }

    /**
     * Gets the value of the fechval property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFECHVAL() {
        return fechval;
    }

    /**
     * Sets the value of the fechval property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFECHVAL(JAXBElement<String> value) {
        this.fechval = value;
    }

    /**
     * Gets the value of the impabon property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIMPABON() {
        return impabon;
    }

    /**
     * Sets the value of the impabon property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIMPABON(JAXBElement<String> value) {
        this.impabon = value;
    }

    /**
     * Gets the value of the impcarg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIMPCARG() {
        return impcarg;
    }

    /**
     * Sets the value of the impcarg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIMPCARG(JAXBElement<String> value) {
        this.impcarg = value;
    }

    /**
     * Gets the value of the impcoti property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIMPCOTI() {
        return impcoti;
    }

    /**
     * Sets the value of the impcoti property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIMPCOTI(JAXBElement<String> value) {
        this.impcoti = value;
    }

    /**
     * Gets the value of the impimpu property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIMPIMPU() {
        return impimpu;
    }

    /**
     * Sets the value of the impimpu property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIMPIMPU(JAXBElement<String> value) {
        this.impimpu = value;
    }

    /**
     * Gets the value of the impuest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIMPUEST() {
        return impuest;
    }

    /**
     * Sets the value of the impuest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIMPUEST(JAXBElement<String> value) {
        this.impuest = value;
    }

    /**
     * Gets the value of the nomclie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNOMCLIE() {
        return nomclie;
    }

    /**
     * Sets the value of the nomclie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNOMCLIE(JAXBElement<String> value) {
        this.nomclie = value;
    }

    /**
     * Gets the value of the nromabo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNROMABO() {
        return nromabo;
    }

    /**
     * Sets the value of the nromabo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNROMABO(JAXBElement<String> value) {
        this.nromabo = value;
    }

    /**
     * Gets the value of the nromcar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNROMCAR() {
        return nromcar;
    }

    /**
     * Sets the value of the nromcar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNROMCAR(JAXBElement<String> value) {
        this.nromcar = value;
    }

    /**
     * Gets the value of the ppriape property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPPRIAPE() {
        return ppriape;
    }

    /**
     * Sets the value of the ppriape property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPPRIAPE(JAXBElement<String> value) {
        this.ppriape = value;
    }

    /**
     * Gets the value of the psegape property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPSEGAPE() {
        return psegape;
    }

    /**
     * Sets the value of the psegape property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPSEGAPE(JAXBElement<String> value) {
        this.psegape = value;
    }

    /**
     * Gets the value of the regimen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getREGIMEN() {
        return regimen;
    }

    /**
     * Sets the value of the regimen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setREGIMEN(JAXBElement<String> value) {
        this.regimen = value;
    }

    /**
     * Gets the value of the sdoabob property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSDOABOB() {
        return sdoabob;
    }

    /**
     * Sets the value of the sdoabob property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSDOABOB(JAXBElement<String> value) {
        this.sdoabob = value;
    }

    /**
     * Gets the value of the sdocarb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSDOCARB() {
        return sdocarb;
    }

    /**
     * Sets the value of the sdocarb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSDOCARB(JAXBElement<String> value) {
        this.sdocarb = value;
    }

    /**
     * Gets the value of the sdodabo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSDODABO() {
        return sdodabo;
    }

    /**
     * Sets the value of the sdodabo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSDODABO(JAXBElement<String> value) {
        this.sdodabo = value;
    }

    /**
     * Gets the value of the sdodcar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSDODCAR() {
        return sdodcar;
    }

    /**
     * Sets the value of the sdodcar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSDODCAR(JAXBElement<String> value) {
        this.sdodcar = value;
    }

    /**
     * Gets the value of the tipcamb property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTIPCAMB() {
        return tipcamb;
    }

    /**
     * Sets the value of the tipcamb property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTIPCAMB(JAXBElement<String> value) {
        this.tipcamb = value;
    }

}
