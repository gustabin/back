
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EndorseWithoutGuarantee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndorseWithoutGuarantee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tenedor_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tenedor_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_endoso_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beneficiario_endoso_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datos_aval" type="{http://echeq.amco.com.ar/}Aval"/>
 *         &lt;element name="firmantes" type="{http://echeq.amco.com.ar/}Firmante" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="trk_cnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_scnl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trk_jsessionid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndorseWithoutGuarantee", propOrder = {
    "intchequeId",
    "tenedorDocumentoTipo",
    "tenedorDocumento",
    "beneficiarioEndosoDocumentoTipo",
    "beneficiarioEndosoDocumento",
    "datosAval",
    "firmantes",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class EndorseWithoutGuarantee {

    @XmlElement(name = "intcheque_id", required = true)
    protected String intchequeId;
    @XmlElement(name = "tenedor_documento_tipo", required = true)
    protected String tenedorDocumentoTipo;
    @XmlElement(name = "tenedor_documento", required = true)
    protected String tenedorDocumento;
    @XmlElement(name = "beneficiario_endoso_documento_tipo", required = true)
    protected String beneficiarioEndosoDocumentoTipo;
    @XmlElement(name = "beneficiario_endoso_documento", required = true)
    protected String beneficiarioEndosoDocumento;
    @XmlElement(name = "datos_aval", required = true)
    protected Aval datosAval;
    protected List<Firmante> firmantes;
    @XmlElement(name = "trk_cnl")
    protected String trkCnl;
    @XmlElement(name = "trk_scnl")
    protected String trkScnl;
    @XmlElement(name = "trk_jsessionid")
    protected String trkJsessionid;

    /**
     * Gets the value of the intchequeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchequeId() {
        return intchequeId;
    }

    /**
     * Sets the value of the intchequeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchequeId(String value) {
        this.intchequeId = value;
    }

    /**
     * Gets the value of the tenedorDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenedorDocumentoTipo() {
        return tenedorDocumentoTipo;
    }

    /**
     * Sets the value of the tenedorDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenedorDocumentoTipo(String value) {
        this.tenedorDocumentoTipo = value;
    }

    /**
     * Gets the value of the tenedorDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenedorDocumento() {
        return tenedorDocumento;
    }

    /**
     * Sets the value of the tenedorDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenedorDocumento(String value) {
        this.tenedorDocumento = value;
    }

    /**
     * Gets the value of the beneficiarioEndosoDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioEndosoDocumentoTipo() {
        return beneficiarioEndosoDocumentoTipo;
    }

    /**
     * Sets the value of the beneficiarioEndosoDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioEndosoDocumentoTipo(String value) {
        this.beneficiarioEndosoDocumentoTipo = value;
    }

    /**
     * Gets the value of the beneficiarioEndosoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiarioEndosoDocumento() {
        return beneficiarioEndosoDocumento;
    }

    /**
     * Sets the value of the beneficiarioEndosoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiarioEndosoDocumento(String value) {
        this.beneficiarioEndosoDocumento = value;
    }

    /**
     * Gets the value of the datosAval property.
     * 
     * @return
     *     possible object is
     *     {@link Aval }
     *     
     */
    public Aval getDatosAval() {
        return datosAval;
    }

    /**
     * Sets the value of the datosAval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Aval }
     *     
     */
    public void setDatosAval(Aval value) {
        this.datosAval = value;
    }

    /**
     * Gets the value of the firmantes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firmantes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirmantes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Firmante }
     * 
     * 
     */
    public List<Firmante> getFirmantes() {
        if (firmantes == null) {
            firmantes = new ArrayList<Firmante>();
        }
        return this.firmantes;
    }

    /**
     * Gets the value of the trkCnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkCnl() {
        return trkCnl;
    }

    /**
     * Sets the value of the trkCnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkCnl(String value) {
        this.trkCnl = value;
    }

    /**
     * Gets the value of the trkScnl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkScnl() {
        return trkScnl;
    }

    /**
     * Sets the value of the trkScnl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkScnl(String value) {
        this.trkScnl = value;
    }

    /**
     * Gets the value of the trkJsessionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrkJsessionid() {
        return trkJsessionid;
    }

    /**
     * Sets the value of the trkJsessionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrkJsessionid(String value) {
        this.trkJsessionid = value;
    }

}
