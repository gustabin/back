
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EndorseCancel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndorseCancel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endosante_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endosante_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="motivo_anulacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "EndorseCancel", propOrder = {
    "intchequeId",
    "endosanteDocumentoTipo",
    "endosanteDocumento",
    "motivoAnulacion",
    "firmantes",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class EndorseCancel {

    @XmlElement(name = "intcheque_id", required = true)
    protected String intchequeId;
    @XmlElement(name = "endosante_documento_tipo", required = true)
    protected String endosanteDocumentoTipo;
    @XmlElement(name = "endosante_documento", required = true)
    protected String endosanteDocumento;
    @XmlElement(name = "motivo_anulacion", required = true)
    protected String motivoAnulacion;
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
     * Gets the value of the endosanteDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndosanteDocumentoTipo() {
        return endosanteDocumentoTipo;
    }

    /**
     * Sets the value of the endosanteDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndosanteDocumentoTipo(String value) {
        this.endosanteDocumentoTipo = value;
    }

    /**
     * Gets the value of the endosanteDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndosanteDocumento() {
        return endosanteDocumento;
    }

    /**
     * Sets the value of the endosanteDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndosanteDocumento(String value) {
        this.endosanteDocumento = value;
    }

    /**
     * Gets the value of the motivoAnulacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    /**
     * Sets the value of the motivoAnulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoAnulacion(String value) {
        this.motivoAnulacion = value;
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
