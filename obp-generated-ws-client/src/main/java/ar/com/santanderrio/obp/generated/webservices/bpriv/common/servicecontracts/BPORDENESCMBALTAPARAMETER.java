
package ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.parameters.BaseParameter;


/**
 * <p>Java class for BP_ORDENES_CMB_ALTA_PARAMETER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BP_ORDENES_CMB_ALTA_PARAMETER">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts.Parameters}BaseParameter">
 *       &lt;sequence>
 *         &lt;element name="Br" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantTitu8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Capital" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CheckMiami" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodSist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CostOrd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Detalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Especie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fciagente" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Fcifacredita" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Fcifondo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Fcifprevresc" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Fciplan" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Fcitipocambio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Fcitipocomision" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="FechaEnvio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaLiq" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaOrden" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FechaRecep" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="HoraEmision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Impreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Intereses8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Modo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MoneLiq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NUP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroMovCust" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="NumMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumMercap" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumOrden" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OperModi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Plazonormal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrecUni8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Preciolimite8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Preciomercado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RenAutomatica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SesionIVR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Ssbremanente8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TasaBp" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TasaNomAnual" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TipoOrd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPlazo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorExcedido" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *         &lt;element name="ValorInterno8" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Xmonto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BP_ORDENES_CMB_ALTA_PARAMETER", propOrder = {
    "br",
    "cantTitu8",
    "capital",
    "checkMiami",
    "cno",
    "codSist",
    "costOrd",
    "detalle",
    "especie",
    "estado",
    "fciagente",
    "fcifacredita",
    "fcifondo",
    "fcifprevresc",
    "fciplan",
    "fcitipocambio",
    "fcitipocomision",
    "fechaEnvio",
    "fechaLiq",
    "fechaOrden",
    "fechaRecep",
    "horaEmision",
    "impreso",
    "intereses8",
    "modo",
    "moneLiq",
    "nup",
    "nroMovCust",
    "numMae",
    "numMercap",
    "numOrden",
    "observaciones",
    "operAlta",
    "operModi",
    "plazo",
    "plazonormal",
    "precUni8",
    "preciolimite8",
    "preciomercado",
    "renAutomatica",
    "sector",
    "sesionIVR",
    "ssbremanente8",
    "tasaBp",
    "tasaNomAnual",
    "tipoOrd",
    "tipoPlazo",
    "valorExcedido",
    "valorInterno8",
    "xmonto"
})
public class BPORDENESCMBALTAPARAMETER
    extends BaseParameter
{

    @XmlElementRef(name = "Br", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> br;
    @XmlElementRef(name = "CantTitu8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> cantTitu8;
    @XmlElementRef(name = "Capital", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> capital;
    @XmlElementRef(name = "CheckMiami", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> checkMiami;
    @XmlElementRef(name = "Cno", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> cno;
    @XmlElementRef(name = "CodSist", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> codSist;
    @XmlElementRef(name = "CostOrd", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> costOrd;
    @XmlElementRef(name = "Detalle", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> detalle;
    @XmlElementRef(name = "Especie", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> especie;
    @XmlElementRef(name = "Estado", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> estado;
    @XmlElementRef(name = "Fciagente", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fciagente;
    @XmlElementRef(name = "Fcifacredita", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fcifacredita;
    @XmlElementRef(name = "Fcifondo", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fcifondo;
    @XmlElementRef(name = "Fcifprevresc", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fcifprevresc;
    @XmlElementRef(name = "Fciplan", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fciplan;
    @XmlElementRef(name = "Fcitipocambio", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fcitipocambio;
    @XmlElementRef(name = "Fcitipocomision", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> fcitipocomision;
    @XmlElementRef(name = "FechaEnvio", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fechaEnvio;
    @XmlElementRef(name = "FechaLiq", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fechaLiq;
    @XmlElementRef(name = "FechaOrden", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fechaOrden;
    @XmlElementRef(name = "FechaRecep", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> fechaRecep;
    @XmlElementRef(name = "HoraEmision", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> horaEmision;
    @XmlElementRef(name = "Impreso", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> impreso;
    @XmlElementRef(name = "Intereses8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> intereses8;
    @XmlElementRef(name = "Modo", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> modo;
    @XmlElementRef(name = "MoneLiq", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> moneLiq;
    @XmlElementRef(name = "NUP", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> nup;
    @XmlElementRef(name = "NroMovCust", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> nroMovCust;
    @XmlElementRef(name = "NumMae", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> numMae;
    @XmlElementRef(name = "NumMercap", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> numMercap;
    @XmlElementRef(name = "NumOrden", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> numOrden;
    @XmlElementRef(name = "Observaciones", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> observaciones;
    @XmlElementRef(name = "OperAlta", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> operAlta;
    @XmlElementRef(name = "OperModi", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> operModi;
    @XmlElementRef(name = "Plazo", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> plazo;
    @XmlElementRef(name = "Plazonormal", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> plazonormal;
    @XmlElementRef(name = "PrecUni8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> precUni8;
    @XmlElementRef(name = "Preciolimite8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> preciolimite8;
    @XmlElementRef(name = "Preciomercado", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> preciomercado;
    @XmlElementRef(name = "RenAutomatica", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> renAutomatica;
    @XmlElementRef(name = "Sector", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> sector;
    @XmlElementRef(name = "SesionIVR", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> sesionIVR;
    @XmlElementRef(name = "Ssbremanente8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> ssbremanente8;
    @XmlElementRef(name = "TasaBp", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> tasaBp;
    @XmlElementRef(name = "TasaNomAnual", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> tasaNomAnual;
    @XmlElementRef(name = "TipoOrd", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> tipoOrd;
    @XmlElementRef(name = "TipoPlazo", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> tipoPlazo;
    @XmlElementRef(name = "ValorExcedido", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<Short> valorExcedido;
    @XmlElementRef(name = "ValorInterno8", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> valorInterno8;
    @XmlElementRef(name = "Xmonto", namespace = "http://schemas.datacontract.org/2004/07/BancaPrivada.Common.ServiceContracts", type = JAXBElement.class)
    protected JAXBElement<String> xmonto;

    /**
     * Gets the value of the br property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBr() {
        return br;
    }

    /**
     * Sets the value of the br property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBr(JAXBElement<String> value) {
        this.br = value;
    }

    /**
     * Gets the value of the cantTitu8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCantTitu8() {
        return cantTitu8;
    }

    /**
     * Sets the value of the cantTitu8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCantTitu8(JAXBElement<BigDecimal> value) {
        this.cantTitu8 = value;
    }

    /**
     * Gets the value of the capital property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getCapital() {
        return capital;
    }

    /**
     * Sets the value of the capital property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setCapital(JAXBElement<BigDecimal> value) {
        this.capital = value;
    }

    /**
     * Gets the value of the checkMiami property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCheckMiami() {
        return checkMiami;
    }

    /**
     * Sets the value of the checkMiami property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCheckMiami(JAXBElement<String> value) {
        this.checkMiami = value;
    }

    /**
     * Gets the value of the cno property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCno() {
        return cno;
    }

    /**
     * Sets the value of the cno property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCno(JAXBElement<String> value) {
        this.cno = value;
    }

    /**
     * Gets the value of the codSist property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodSist() {
        return codSist;
    }

    /**
     * Sets the value of the codSist property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodSist(JAXBElement<String> value) {
        this.codSist = value;
    }

    /**
     * Gets the value of the costOrd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCostOrd() {
        return costOrd;
    }

    /**
     * Sets the value of the costOrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCostOrd(JAXBElement<String> value) {
        this.costOrd = value;
    }

    /**
     * Gets the value of the detalle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDetalle() {
        return detalle;
    }

    /**
     * Sets the value of the detalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDetalle(JAXBElement<String> value) {
        this.detalle = value;
    }

    /**
     * Gets the value of the especie property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEspecie() {
        return especie;
    }

    /**
     * Sets the value of the especie property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEspecie(JAXBElement<String> value) {
        this.especie = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstado(JAXBElement<String> value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fciagente property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFciagente() {
        return fciagente;
    }

    /**
     * Sets the value of the fciagente property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFciagente(JAXBElement<BigDecimal> value) {
        this.fciagente = value;
    }

    /**
     * Gets the value of the fcifacredita property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFcifacredita() {
        return fcifacredita;
    }

    /**
     * Sets the value of the fcifacredita property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFcifacredita(JAXBElement<XMLGregorianCalendar> value) {
        this.fcifacredita = value;
    }

    /**
     * Gets the value of the fcifondo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFcifondo() {
        return fcifondo;
    }

    /**
     * Sets the value of the fcifondo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFcifondo(JAXBElement<BigDecimal> value) {
        this.fcifondo = value;
    }

    /**
     * Gets the value of the fcifprevresc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFcifprevresc() {
        return fcifprevresc;
    }

    /**
     * Sets the value of the fcifprevresc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFcifprevresc(JAXBElement<XMLGregorianCalendar> value) {
        this.fcifprevresc = value;
    }

    /**
     * Gets the value of the fciplan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFciplan() {
        return fciplan;
    }

    /**
     * Sets the value of the fciplan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFciplan(JAXBElement<BigDecimal> value) {
        this.fciplan = value;
    }

    /**
     * Gets the value of the fcitipocambio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFcitipocambio() {
        return fcitipocambio;
    }

    /**
     * Sets the value of the fcitipocambio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFcitipocambio(JAXBElement<BigDecimal> value) {
        this.fcitipocambio = value;
    }

    /**
     * Gets the value of the fcitipocomision property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFcitipocomision() {
        return fcitipocomision;
    }

    /**
     * Sets the value of the fcitipocomision property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFcitipocomision(JAXBElement<BigDecimal> value) {
        this.fcitipocomision = value;
    }

    /**
     * Gets the value of the fechaEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Sets the value of the fechaEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFechaEnvio(JAXBElement<XMLGregorianCalendar> value) {
        this.fechaEnvio = value;
    }

    /**
     * Gets the value of the fechaLiq property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFechaLiq() {
        return fechaLiq;
    }

    /**
     * Sets the value of the fechaLiq property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFechaLiq(JAXBElement<XMLGregorianCalendar> value) {
        this.fechaLiq = value;
    }

    /**
     * Gets the value of the fechaOrden property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFechaOrden() {
        return fechaOrden;
    }

    /**
     * Sets the value of the fechaOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFechaOrden(JAXBElement<XMLGregorianCalendar> value) {
        this.fechaOrden = value;
    }

    /**
     * Gets the value of the fechaRecep property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFechaRecep() {
        return fechaRecep;
    }

    /**
     * Sets the value of the fechaRecep property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFechaRecep(JAXBElement<XMLGregorianCalendar> value) {
        this.fechaRecep = value;
    }

    /**
     * Gets the value of the horaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHoraEmision() {
        return horaEmision;
    }

    /**
     * Sets the value of the horaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHoraEmision(JAXBElement<String> value) {
        this.horaEmision = value;
    }

    /**
     * Gets the value of the impreso property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getImpreso() {
        return impreso;
    }

    /**
     * Sets the value of the impreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setImpreso(JAXBElement<String> value) {
        this.impreso = value;
    }

    /**
     * Gets the value of the intereses8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getIntereses8() {
        return intereses8;
    }

    /**
     * Sets the value of the intereses8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setIntereses8(JAXBElement<BigDecimal> value) {
        this.intereses8 = value;
    }

    /**
     * Gets the value of the modo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModo() {
        return modo;
    }

    /**
     * Sets the value of the modo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModo(JAXBElement<String> value) {
        this.modo = value;
    }

    /**
     * Gets the value of the moneLiq property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMoneLiq() {
        return moneLiq;
    }

    /**
     * Sets the value of the moneLiq property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMoneLiq(JAXBElement<String> value) {
        this.moneLiq = value;
    }

    /**
     * Gets the value of the nup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNUP() {
        return nup;
    }

    /**
     * Sets the value of the nup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNUP(JAXBElement<String> value) {
        this.nup = value;
    }

    /**
     * Gets the value of the nroMovCust property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getNroMovCust() {
        return nroMovCust;
    }

    /**
     * Sets the value of the nroMovCust property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setNroMovCust(JAXBElement<BigDecimal> value) {
        this.nroMovCust = value;
    }

    /**
     * Gets the value of the numMae property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumMae() {
        return numMae;
    }

    /**
     * Sets the value of the numMae property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumMae(JAXBElement<String> value) {
        this.numMae = value;
    }

    /**
     * Gets the value of the numMercap property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumMercap() {
        return numMercap;
    }

    /**
     * Sets the value of the numMercap property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumMercap(JAXBElement<String> value) {
        this.numMercap = value;
    }

    /**
     * Gets the value of the numOrden property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumOrden() {
        return numOrden;
    }

    /**
     * Sets the value of the numOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumOrden(JAXBElement<String> value) {
        this.numOrden = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setObservaciones(JAXBElement<String> value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the operAlta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperAlta() {
        return operAlta;
    }

    /**
     * Sets the value of the operAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperAlta(JAXBElement<String> value) {
        this.operAlta = value;
    }

    /**
     * Gets the value of the operModi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOperModi() {
        return operModi;
    }

    /**
     * Sets the value of the operModi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOperModi(JAXBElement<String> value) {
        this.operModi = value;
    }

    /**
     * Gets the value of the plazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazo() {
        return plazo;
    }

    /**
     * Sets the value of the plazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazo(JAXBElement<String> value) {
        this.plazo = value;
    }

    /**
     * Gets the value of the plazonormal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlazonormal() {
        return plazonormal;
    }

    /**
     * Sets the value of the plazonormal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlazonormal(JAXBElement<String> value) {
        this.plazonormal = value;
    }

    /**
     * Gets the value of the precUni8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPrecUni8() {
        return precUni8;
    }

    /**
     * Sets the value of the precUni8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPrecUni8(JAXBElement<BigDecimal> value) {
        this.precUni8 = value;
    }

    /**
     * Gets the value of the preciolimite8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPreciolimite8() {
        return preciolimite8;
    }

    /**
     * Sets the value of the preciolimite8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPreciolimite8(JAXBElement<BigDecimal> value) {
        this.preciolimite8 = value;
    }

    /**
     * Gets the value of the preciomercado property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreciomercado() {
        return preciomercado;
    }

    /**
     * Sets the value of the preciomercado property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreciomercado(JAXBElement<String> value) {
        this.preciomercado = value;
    }

    /**
     * Gets the value of the renAutomatica property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRenAutomatica() {
        return renAutomatica;
    }

    /**
     * Sets the value of the renAutomatica property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRenAutomatica(JAXBElement<String> value) {
        this.renAutomatica = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSector(JAXBElement<String> value) {
        this.sector = value;
    }

    /**
     * Gets the value of the sesionIVR property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSesionIVR() {
        return sesionIVR;
    }

    /**
     * Sets the value of the sesionIVR property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSesionIVR(JAXBElement<BigDecimal> value) {
        this.sesionIVR = value;
    }

    /**
     * Gets the value of the ssbremanente8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSsbremanente8() {
        return ssbremanente8;
    }

    /**
     * Sets the value of the ssbremanente8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSsbremanente8(JAXBElement<BigDecimal> value) {
        this.ssbremanente8 = value;
    }

    /**
     * Gets the value of the tasaBp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getTasaBp() {
        return tasaBp;
    }

    /**
     * Sets the value of the tasaBp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setTasaBp(JAXBElement<BigDecimal> value) {
        this.tasaBp = value;
    }

    /**
     * Gets the value of the tasaNomAnual property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getTasaNomAnual() {
        return tasaNomAnual;
    }

    /**
     * Sets the value of the tasaNomAnual property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setTasaNomAnual(JAXBElement<BigDecimal> value) {
        this.tasaNomAnual = value;
    }

    /**
     * Gets the value of the tipoOrd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoOrd() {
        return tipoOrd;
    }

    /**
     * Sets the value of the tipoOrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoOrd(JAXBElement<String> value) {
        this.tipoOrd = value;
    }

    /**
     * Gets the value of the tipoPlazo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoPlazo() {
        return tipoPlazo;
    }

    /**
     * Sets the value of the tipoPlazo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoPlazo(JAXBElement<String> value) {
        this.tipoPlazo = value;
    }

    /**
     * Gets the value of the valorExcedido property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public JAXBElement<Short> getValorExcedido() {
        return valorExcedido;
    }

    /**
     * Sets the value of the valorExcedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Short }{@code >}
     *     
     */
    public void setValorExcedido(JAXBElement<Short> value) {
        this.valorExcedido = value;
    }

    /**
     * Gets the value of the valorInterno8 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getValorInterno8() {
        return valorInterno8;
    }

    /**
     * Sets the value of the valorInterno8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setValorInterno8(JAXBElement<BigDecimal> value) {
        this.valorInterno8 = value;
    }

    /**
     * Gets the value of the xmonto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXmonto() {
        return xmonto;
    }

    /**
     * Sets the value of the xmonto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXmonto(JAXBElement<String> value) {
        this.xmonto = value;
    }

}
