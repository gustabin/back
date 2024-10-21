
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConcepto;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDocumentoConcepto;


/**
 * <p>Java class for ConsultaConceptoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaConceptoResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Avanza_vinculada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ayuda_Documentacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_4237" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Cuit_Benef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Fecha_Emb_Est" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Fecha_Embarque" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Form_Inver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_NIF_Gan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_Pos_Aran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Declara_Impuestos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Declaracion_Jurada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Declaraciones" type="{Domain}ArrayOfConcepto" minOccurs="0"/>
 *         &lt;element name="Descripcion_Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Documentos" type="{Domain}ArrayOfDocumentoConcepto" minOccurs="0"/>
 *         &lt;element name="Edita_Impo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Concepto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Ingresa_Despachos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ingresa_Nif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Inv_Acre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda_Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monto_Com4834" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Monto_Emp_Vinculada" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Mostar_Emp_Vinculada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mostrar_Com4834" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo_Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaConceptoResponse", propOrder = {
    "avanzaVinculada",
    "ayudaDocumentacion",
    "con4237",
    "conCuitBenef",
    "conFechaEmbEst",
    "conFechaEmbarque",
    "conFormInver",
    "conNIFGan",
    "conPosAran",
    "declaraImpuestos",
    "declaracionJurada",
    "declaraciones",
    "descripcionConcepto",
    "documentos",
    "editaImpo",
    "idConcepto",
    "ingresaDespachos",
    "ingresaNif",
    "invAcre",
    "monedaCuenta",
    "montoCom4834",
    "montoEmpVinculada",
    "mostarEmpVinculada",
    "mostrarCom4834",
    "tipoConcepto"
})
public class ConsultaConceptoResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Avanza_vinculada", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> avanzaVinculada;
    @XmlElementRef(name = "Ayuda_Documentacion", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> ayudaDocumentacion;
    @XmlElementRef(name = "Con_4237", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> con4237;
    @XmlElementRef(name = "Con_Cuit_Benef", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conCuitBenef;
    @XmlElementRef(name = "Con_Fecha_Emb_Est", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conFechaEmbEst;
    @XmlElementRef(name = "Con_Fecha_Embarque", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conFechaEmbarque;
    @XmlElementRef(name = "Con_Form_Inver", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conFormInver;
    @XmlElementRef(name = "Con_NIF_Gan", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conNIFGan;
    @XmlElementRef(name = "Con_Pos_Aran", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> conPosAran;
    @XmlElementRef(name = "Declara_Impuestos", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> declaraImpuestos;
    @XmlElementRef(name = "Declaracion_Jurada", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> declaracionJurada;
    @XmlElementRef(name = "Declaraciones", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfConcepto> declaraciones;
    @XmlElementRef(name = "Descripcion_Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> descripcionConcepto;
    @XmlElementRef(name = "Documentos", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDocumentoConcepto> documentos;
    @XmlElementRef(name = "Edita_Impo", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> editaImpo;
    @XmlElementRef(name = "Id_Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<Integer> idConcepto;
    @XmlElementRef(name = "Ingresa_Despachos", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> ingresaDespachos;
    @XmlElementRef(name = "Ingresa_Nif", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> ingresaNif;
    @XmlElementRef(name = "Inv_Acre", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> invAcre;
    @XmlElementRef(name = "Moneda_Cuenta", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> monedaCuenta;
    @XmlElementRef(name = "Monto_Com4834", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoCom4834;
    @XmlElementRef(name = "Monto_Emp_Vinculada", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> montoEmpVinculada;
    @XmlElementRef(name = "Mostar_Emp_Vinculada", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> mostarEmpVinculada;
    @XmlElementRef(name = "Mostrar_Com4834", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> mostrarCom4834;
    @XmlElementRef(name = "Tipo_Concepto", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<String> tipoConcepto;

    /**
     * Gets the value of the avanzaVinculada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAvanzaVinculada() {
        return avanzaVinculada;
    }

    /**
     * Sets the value of the avanzaVinculada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAvanzaVinculada(JAXBElement<String> value) {
        this.avanzaVinculada = value;
    }

    /**
     * Gets the value of the ayudaDocumentacion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAyudaDocumentacion() {
        return ayudaDocumentacion;
    }

    /**
     * Sets the value of the ayudaDocumentacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAyudaDocumentacion(JAXBElement<String> value) {
        this.ayudaDocumentacion = value;
    }

    /**
     * Gets the value of the con4237 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCon4237() {
        return con4237;
    }

    /**
     * Sets the value of the con4237 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCon4237(JAXBElement<String> value) {
        this.con4237 = value;
    }

    /**
     * Gets the value of the conCuitBenef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConCuitBenef() {
        return conCuitBenef;
    }

    /**
     * Sets the value of the conCuitBenef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConCuitBenef(JAXBElement<String> value) {
        this.conCuitBenef = value;
    }

    /**
     * Gets the value of the conFechaEmbEst property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConFechaEmbEst() {
        return conFechaEmbEst;
    }

    /**
     * Sets the value of the conFechaEmbEst property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConFechaEmbEst(JAXBElement<String> value) {
        this.conFechaEmbEst = value;
    }

    /**
     * Gets the value of the conFechaEmbarque property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConFechaEmbarque() {
        return conFechaEmbarque;
    }

    /**
     * Sets the value of the conFechaEmbarque property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConFechaEmbarque(JAXBElement<String> value) {
        this.conFechaEmbarque = value;
    }

    /**
     * Gets the value of the conFormInver property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConFormInver() {
        return conFormInver;
    }

    /**
     * Sets the value of the conFormInver property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConFormInver(JAXBElement<String> value) {
        this.conFormInver = value;
    }

    /**
     * Gets the value of the conNIFGan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConNIFGan() {
        return conNIFGan;
    }

    /**
     * Sets the value of the conNIFGan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConNIFGan(JAXBElement<String> value) {
        this.conNIFGan = value;
    }

    /**
     * Gets the value of the conPosAran property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConPosAran() {
        return conPosAran;
    }

    /**
     * Sets the value of the conPosAran property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConPosAran(JAXBElement<String> value) {
        this.conPosAran = value;
    }

    /**
     * Gets the value of the declaraImpuestos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeclaraImpuestos() {
        return declaraImpuestos;
    }

    /**
     * Sets the value of the declaraImpuestos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeclaraImpuestos(JAXBElement<String> value) {
        this.declaraImpuestos = value;
    }

    /**
     * Gets the value of the declaracionJurada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeclaracionJurada() {
        return declaracionJurada;
    }

    /**
     * Sets the value of the declaracionJurada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeclaracionJurada(JAXBElement<String> value) {
        this.declaracionJurada = value;
    }

    /**
     * Gets the value of the declaraciones property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConcepto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfConcepto> getDeclaraciones() {
        return declaraciones;
    }

    /**
     * Sets the value of the declaraciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfConcepto }{@code >}
     *     
     */
    public void setDeclaraciones(JAXBElement<ArrayOfConcepto> value) {
        this.declaraciones = value;
    }

    /**
     * Gets the value of the descripcionConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescripcionConcepto() {
        return descripcionConcepto;
    }

    /**
     * Sets the value of the descripcionConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescripcionConcepto(JAXBElement<String> value) {
        this.descripcionConcepto = value;
    }

    /**
     * Gets the value of the documentos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDocumentoConcepto }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDocumentoConcepto> getDocumentos() {
        return documentos;
    }

    /**
     * Sets the value of the documentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDocumentoConcepto }{@code >}
     *     
     */
    public void setDocumentos(JAXBElement<ArrayOfDocumentoConcepto> value) {
        this.documentos = value;
    }

    /**
     * Gets the value of the editaImpo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEditaImpo() {
        return editaImpo;
    }

    /**
     * Sets the value of the editaImpo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEditaImpo(JAXBElement<String> value) {
        this.editaImpo = value;
    }

    /**
     * Gets the value of the idConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getIdConcepto() {
        return idConcepto;
    }

    /**
     * Sets the value of the idConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setIdConcepto(JAXBElement<Integer> value) {
        this.idConcepto = value;
    }

    /**
     * Gets the value of the ingresaDespachos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIngresaDespachos() {
        return ingresaDespachos;
    }

    /**
     * Sets the value of the ingresaDespachos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIngresaDespachos(JAXBElement<String> value) {
        this.ingresaDespachos = value;
    }

    /**
     * Gets the value of the ingresaNif property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIngresaNif() {
        return ingresaNif;
    }

    /**
     * Sets the value of the ingresaNif property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIngresaNif(JAXBElement<String> value) {
        this.ingresaNif = value;
    }

    /**
     * Gets the value of the invAcre property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInvAcre() {
        return invAcre;
    }

    /**
     * Sets the value of the invAcre property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInvAcre(JAXBElement<String> value) {
        this.invAcre = value;
    }

    /**
     * Gets the value of the monedaCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMonedaCuenta() {
        return monedaCuenta;
    }

    /**
     * Sets the value of the monedaCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMonedaCuenta(JAXBElement<String> value) {
        this.monedaCuenta = value;
    }

    /**
     * Gets the value of the montoCom4834 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoCom4834() {
        return montoCom4834;
    }

    /**
     * Sets the value of the montoCom4834 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoCom4834(JAXBElement<BigDecimal> value) {
        this.montoCom4834 = value;
    }

    /**
     * Gets the value of the montoEmpVinculada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getMontoEmpVinculada() {
        return montoEmpVinculada;
    }

    /**
     * Sets the value of the montoEmpVinculada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setMontoEmpVinculada(JAXBElement<BigDecimal> value) {
        this.montoEmpVinculada = value;
    }

    /**
     * Gets the value of the mostarEmpVinculada property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMostarEmpVinculada() {
        return mostarEmpVinculada;
    }

    /**
     * Sets the value of the mostarEmpVinculada property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMostarEmpVinculada(JAXBElement<String> value) {
        this.mostarEmpVinculada = value;
    }

    /**
     * Gets the value of the mostrarCom4834 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMostrarCom4834() {
        return mostrarCom4834;
    }

    /**
     * Sets the value of the mostrarCom4834 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMostrarCom4834(JAXBElement<String> value) {
        this.mostrarCom4834 = value;
    }

    /**
     * Gets the value of the tipoConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipoConcepto() {
        return tipoConcepto;
    }

    /**
     * Sets the value of the tipoConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipoConcepto(JAXBElement<String> value) {
        this.tipoConcepto = value;
    }

}
