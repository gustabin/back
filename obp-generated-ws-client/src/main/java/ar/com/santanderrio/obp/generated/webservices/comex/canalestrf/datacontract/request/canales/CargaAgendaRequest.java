
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for CargaAgendaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CargaAgendaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Aba_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aba_Inter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Activo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Banco_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Banco_Inter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Domicilio_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Agenda" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Localidad_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nombre_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nup_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Agenda" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargaAgendaRequest", propOrder = {
    "abaBenef",
    "abaInter",
    "activo",
    "bancoBenef",
    "bancoInter",
    "domicilioBenef",
    "idAgenda",
    "localidadBenef",
    "nombreBenef",
    "nupCliente",
    "paisBenef",
    "tipoAgenda"
})
public class CargaAgendaRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Aba_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> abaBenef;
    @XmlElementRef(name = "Aba_Inter", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> abaInter;
    @XmlElementRef(name = "Activo", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> activo;
    @XmlElementRef(name = "Banco_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> bancoBenef;
    @XmlElementRef(name = "Banco_Inter", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> bancoInter;
    @XmlElementRef(name = "Domicilio_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> domicilioBenef;
    @XmlElement(name = "Id_Agenda")
    protected BigDecimal idAgenda;
    @XmlElementRef(name = "Localidad_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> localidadBenef;
    @XmlElementRef(name = "Nombre_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nombreBenef;
    @XmlElementRef(name = "Nup_Cliente", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> nupCliente;
    @XmlElementRef(name = "Pais_Benef", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> paisBenef;
    @XmlElementRef(name = "Tipo_Agenda", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Short> tipoAgenda;

    /**
     * Gets the value of the abaBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAbaBenef() {
        return abaBenef;
    }

    /**
     * Sets the value of the abaBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAbaBenef(JAXBElement<String> value) {
        this.abaBenef = value;
    }

    /**
     * Gets the value of the abaInter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAbaInter() {
        return abaInter;
    }

    /**
     * Sets the value of the abaInter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAbaInter(JAXBElement<String> value) {
        this.abaInter = value;
    }

    /**
     * Gets the value of the activo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivo() {
        return activo;
    }

    /**
     * Sets the value of the activo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivo(JAXBElement<String> value) {
        this.activo = value;
    }

    /**
     * Gets the value of the bancoBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoBenef() {
        return bancoBenef;
    }

    /**
     * Sets the value of the bancoBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoBenef(JAXBElement<String> value) {
        this.bancoBenef = value;
    }

    /**
     * Gets the value of the bancoInter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBancoInter() {
        return bancoInter;
    }

    /**
     * Sets the value of the bancoInter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBancoInter(JAXBElement<String> value) {
        this.bancoInter = value;
    }

    /**
     * Gets the value of the domicilioBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDomicilioBenef() {
        return domicilioBenef;
    }

    /**
     * Sets the value of the domicilioBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDomicilioBenef(JAXBElement<String> value) {
        this.domicilioBenef = value;
    }

    /**
     * Gets the value of the idAgenda property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAgenda() {
        return idAgenda;
    }

    /**
     * Sets the value of the idAgenda property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAgenda(BigDecimal value) {
        this.idAgenda = value;
    }

    /**
     * Gets the value of the localidadBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalidadBenef() {
        return localidadBenef;
    }

    /**
     * Sets the value of the localidadBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalidadBenef(JAXBElement<String> value) {
        this.localidadBenef = value;
    }

    /**
     * Gets the value of the nombreBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBenef() {
        return nombreBenef;
    }

    /**
     * Sets the value of the nombreBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBenef(JAXBElement<String> value) {
        this.nombreBenef = value;
    }

    /**
     * Gets the value of the nupCliente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNupCliente() {
        return nupCliente;
    }

    /**
     * Sets the value of the nupCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNupCliente(JAXBElement<String> value) {
        this.nupCliente = value;
    }

    /**
     * Gets the value of the paisBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPaisBenef() {
        return paisBenef;
    }

    /**
     * Sets the value of the paisBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPaisBenef(JAXBElement<String> value) {
        this.paisBenef = value;
    }

    /**
     * Gets the value of the tipoAgenda property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getTipoAgenda() {
        return tipoAgenda;
    }

    /**
     * Sets the value of the tipoAgenda property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setTipoAgenda(JAXBElement<Short> value) {
        this.tipoAgenda = value;
    }

}
