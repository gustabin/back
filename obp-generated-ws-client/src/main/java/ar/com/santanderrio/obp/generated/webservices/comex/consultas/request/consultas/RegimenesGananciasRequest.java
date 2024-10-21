
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.request.consultas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.request.BaseCursor;


/**
 * <p>Clase Java para RegimenesGananciasRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RegimenesGananciasRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}BaseCursor">
 *       &lt;sequence>
 *         &lt;element name="Cod_Pais_BCRA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_CDI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Con_INPI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Concepto_BCRA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Alicuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegimenesGananciasRequest", propOrder = {
    "codPaisBCRA",
    "conCDI",
    "conINPI",
    "conceptoBCRA",
    "idAlicuota"
})
public class RegimenesGananciasRequest
    extends BaseCursor
{

    @XmlElementRef(name = "Cod_Pais_BCRA", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> codPaisBCRA;
    @XmlElementRef(name = "Con_CDI", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> conCDI;
    @XmlElementRef(name = "Con_INPI", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> conINPI;
    @XmlElementRef(name = "Concepto_BCRA", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<String> conceptoBCRA;
    @XmlElementRef(name = "Id_Alicuota", namespace = "Request/Consultas", type = JAXBElement.class)
    protected JAXBElement<Long> idAlicuota;

    /**
     * Obtiene el valor de la propiedad codPaisBCRA.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodPaisBCRA() {
        return codPaisBCRA;
    }

    /**
     * Define el valor de la propiedad codPaisBCRA.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodPaisBCRA(JAXBElement<String> value) {
        this.codPaisBCRA = value;
    }

    /**
     * Obtiene el valor de la propiedad conCDI.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConCDI() {
        return conCDI;
    }

    /**
     * Define el valor de la propiedad conCDI.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConCDI(JAXBElement<String> value) {
        this.conCDI = value;
    }

    /**
     * Obtiene el valor de la propiedad conINPI.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConINPI() {
        return conINPI;
    }

    /**
     * Define el valor de la propiedad conINPI.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConINPI(JAXBElement<String> value) {
        this.conINPI = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptoBCRA.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConceptoBCRA() {
        return conceptoBCRA;
    }

    /**
     * Define el valor de la propiedad conceptoBCRA.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConceptoBCRA(JAXBElement<String> value) {
        this.conceptoBCRA = value;
    }

    /**
     * Obtiene el valor de la propiedad idAlicuota.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdAlicuota() {
        return idAlicuota;
    }

    /**
     * Define el valor de la propiedad idAlicuota.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdAlicuota(JAXBElement<Long> value) {
        this.idAlicuota = value;
    }

}
