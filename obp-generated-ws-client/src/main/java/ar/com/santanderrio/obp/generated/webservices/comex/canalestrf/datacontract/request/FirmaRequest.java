
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.BorrarDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaDespachosRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.CargaDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ClonarImagenesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConceptosPorTipoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaConceptoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaDetalleTrfOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaDetalleTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaImagenTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaMotExcepcionRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaNifRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaOperacionesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ConsultaTiposConceptoRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.DesvinculaAgendaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.DesvinculaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ObtenerMsgSwiftRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarNifRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTransferenciaOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTrfBPMRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ProcesarTrfRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ValidarNIFxNUPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales.ValidarPosicionRequest;


/**
 * <p>Java class for FirmaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FirmaRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}BaseRequest">
 *       &lt;sequence>
 *         &lt;element name="Firma_datos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_datos_dentro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_formato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Firma_hash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirmaRequest", propOrder = {
    "firmaDatos",
    "firmaDatosDentro",
    "firmaFormato",
    "firmaHash"
})
@XmlSeeAlso({
    ProcesarTransferenciaOBPRequest.class,
    ValidarPosicionRequest.class,
    ConsultaDetalleTrfRequest.class,
    DesvinculaAgendaCuentaRequest.class,
    ConsultaMotExcepcionRequest.class,
    CargaAgendaCuentaRequest.class,
    ConsultaImagenTrfRequest.class,
    CargaDespachosRequest.class,
    ClonarImagenesRequest.class,
    ConsultaDetalleTrfOBPRequest.class,
    ConsultaOperacionesRequest.class,
    ValidarNIFxNUPRequest.class,
    ProcesarTrfBPMRequest.class,
    CargaDocRequest.class,
    ConsultaAgendaCuentaRequest.class,
    ObtenerMsgSwiftRequest.class,
    ConsultaConceptoRequest.class,
    DesvinculaAgendaRequest.class,
    ConceptosPorTipoRequest.class,
    CargaAgendaRequest.class,
    BorrarDocRequest.class,
    ConsultaNifRequest.class,
    ProcesarNifRequest.class,
    ProcesarTrfRequest.class,
    ConsultaTiposConceptoRequest.class,
    BaseCursor.class
})
public class FirmaRequest
    extends BaseRequest
{

    @XmlElementRef(name = "Firma_datos", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaDatos;
    @XmlElementRef(name = "Firma_datos_dentro", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaDatosDentro;
    @XmlElementRef(name = "Firma_formato", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaFormato;
    @XmlElementRef(name = "Firma_hash", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request", type = JAXBElement.class)
    protected JAXBElement<String> firmaHash;

    /**
     * Gets the value of the firmaDatos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaDatos() {
        return firmaDatos;
    }

    /**
     * Sets the value of the firmaDatos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaDatos(JAXBElement<String> value) {
        this.firmaDatos = value;
    }

    /**
     * Gets the value of the firmaDatosDentro property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaDatosDentro() {
        return firmaDatosDentro;
    }

    /**
     * Sets the value of the firmaDatosDentro property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaDatosDentro(JAXBElement<String> value) {
        this.firmaDatosDentro = value;
    }

    /**
     * Gets the value of the firmaFormato property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaFormato() {
        return firmaFormato;
    }

    /**
     * Sets the value of the firmaFormato property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaFormato(JAXBElement<String> value) {
        this.firmaFormato = value;
    }

    /**
     * Gets the value of the firmaHash property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFirmaHash() {
        return firmaHash;
    }

    /**
     * Sets the value of the firmaHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFirmaHash(JAXBElement<String> value) {
        this.firmaHash = value;
    }

}
