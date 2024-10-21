
package ar.com.santanderrio.obp.generated.webservices.echeq;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IssueAssignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IssueAssignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intcheque_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cedente_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cedente_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesionario_documento_tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesionario_documento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesionario_nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesionario_domicilio" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "IssueAssignment", propOrder = {
    "intchequeId",
    "cedenteDocumentoTipo",
    "cedenteDocumento",
    "cesionarioDocumentoTipo",
    "cesionarioDocumento",
    "cesionarioNombre",
    "cesionarioDomicilio",
    "firmantes",
    "trkCnl",
    "trkScnl",
    "trkJsessionid"
})
public class IssueAssignment {

    @XmlElement(name = "intcheque_id", required = true)
    protected String intchequeId;
    @XmlElement(name = "cedente_documento_tipo", required = true)
    protected String cedenteDocumentoTipo;
    @XmlElement(name = "cedente_documento", required = true)
    protected String cedenteDocumento;
    @XmlElement(name = "cesionario_documento_tipo", required = true)
    protected String cesionarioDocumentoTipo;
    @XmlElement(name = "cesionario_documento", required = true)
    protected String cesionarioDocumento;
    @XmlElement(name = "cesionario_nombre", required = true)
    protected String cesionarioNombre;
    @XmlElement(name = "cesionario_domicilio", required = true)
    protected String cesionarioDomicilio;
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
     * Gets the value of the cesionarioDocumentoTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioDocumentoTipo() {
        return cesionarioDocumentoTipo;
    }

    /**
     * Sets the value of the cesionarioDocumentoTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioDocumentoTipo(String value) {
        this.cesionarioDocumentoTipo = value;
    }

    /**
     * Gets the value of the cesionarioDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioDocumento() {
        return cesionarioDocumento;
    }

    /**
     * Sets the value of the cesionarioDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioDocumento(String value) {
        this.cesionarioDocumento = value;
    }

    /**
     * Gets the value of the cesionarioNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioNombre() {
        return cesionarioNombre;
    }

    /**
     * Sets the value of the cesionarioNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioNombre(String value) {
        this.cesionarioNombre = value;
    }

    /**
     * Gets the value of the cesionarioDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionarioDomicilio() {
        return cesionarioDomicilio;
    }

    /**
     * Sets the value of the cesionarioDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionarioDomicilio(String value) {
        this.cesionarioDomicilio = value;
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
