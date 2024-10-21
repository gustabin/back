
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignmentCancel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignmentCancel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesion_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cedente_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cedente_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "AssignmentCancel", propOrder = {
    "intchequeId",
    "cesionId",
    "cedenteDocumentoTipo",
    "cedenteDocumento",
    "firmantes",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class AssignmentCancel {

    @XmlElement(name = "intcheque_id", required = true)
    protected String intchequeId;
    @XmlElement(name = "cesion_id", required = true)
    protected String cesionId;
    @XmlElement(name = "cedente_documento_tipo", required = true)
    protected String cedenteDocumentoTipo;
    @XmlElement(name = "cedente_documento", required = true)
    protected String cedenteDocumento;
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
     * Gets the value of the cesionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionId() {
        return cesionId;
    }

    /**
     * Sets the value of the cesionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionId(String value) {
        this.cesionId = value;
    }

    /**
     * Gets the value of the cedenteDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedenteDocumentoTipo() {
        return cedenteDocumentoTipo;
    }

    /**
     * Sets the value of the cedenteDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedenteDocumentoTipo(String value) {
        this.cedenteDocumentoTipo = value;
    }

    /**
     * Gets the value of the cedenteDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedenteDocumento() {
        return cedenteDocumento;
    }

    /**
     * Sets the value of the cedenteDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedenteDocumento(String value) {
        this.cedenteDocumento = value;
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
