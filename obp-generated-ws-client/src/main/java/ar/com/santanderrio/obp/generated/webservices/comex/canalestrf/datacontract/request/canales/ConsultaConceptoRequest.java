
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.canales;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.request.FirmaRequest;


/**
 * <p>Java class for ConsultaConceptoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaConceptoRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request}FirmaRequest">
 *       &lt;sequence>
 *         &lt;element name="Canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cod_Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Id_Concepto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Producto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaConceptoRequest", propOrder = {
    "canal",
    "codConcepto",
    "idConcepto",
    "producto"
})
public class ConsultaConceptoRequest
    extends FirmaRequest
{

    @XmlElementRef(name = "Canal", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> canal;
    @XmlElementRef(name = "Cod_Concepto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<String> codConcepto;
    @XmlElementRef(name = "Id_Concepto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Integer> idConcepto;
    @XmlElementRef(name = "Producto", namespace = "http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Request.CanalesTrf", type = JAXBElement.class)
    protected JAXBElement<Integer> producto;

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCanal(JAXBElement<String> value) {
        this.canal = value;
    }

    /**
     * Gets the value of the codConcepto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodConcepto() {
        return codConcepto;
    }

    /**
     * Sets the value of the codConcepto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodConcepto(JAXBElement<String> value) {
        this.codConcepto = value;
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
     * Gets the value of the producto property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getProducto() {
        return producto;
    }

    /**
     * Sets the value of the producto property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setProducto(JAXBElement<Integer> value) {
        this.producto = value;
    }

}
