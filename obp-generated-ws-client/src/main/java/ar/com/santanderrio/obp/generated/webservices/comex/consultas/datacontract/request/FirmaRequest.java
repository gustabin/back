
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas.ConsultaBeneficiarioRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.consultas.ObtenerCondicionesVentaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaAgendaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaMonedasRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaNomMonedaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaNomPaisRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ConsultaPaisesRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.ObtenerTipoDocRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas.PaisesConDobleConvenioRequest;


/**
 * <p>Clase Java para FirmaRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
    ConsultaBeneficiarioRequest.class,
    ObtenerCondicionesVentaRequest.class,
    ConsultaAgendaRequest.class,
    ConsultaMonedasRequest.class,
    ObtenerTipoDocRequest.class,
    ConsultaNomMonedaRequest.class,
    ConsultaNomPaisRequest.class,
    PaisesConDobleConvenioRequest.class,
    ConsultaPaisesRequest.class,
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
     * Obtiene el valor de la propiedad firmaDatos.
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
     * Define el valor de la propiedad firmaDatos.
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
     * Obtiene el valor de la propiedad firmaDatosDentro.
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
     * Define el valor de la propiedad firmaDatosDentro.
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
     * Obtiene el valor de la propiedad firmaFormato.
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
     * Define el valor de la propiedad firmaFormato.
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
     * Obtiene el valor de la propiedad firmaHash.
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
     * Define el valor de la propiedad firmaHash.
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
