
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.BaseResponse;


/**
 * <p>Java class for ConsultaImagenTrfResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsultaImagenTrfResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Data_Imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="Id_CT" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Nro_Form" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaImagenTrfResponse", propOrder = {
    "dataImagen",
    "idCT",
    "nroForm"
})
public class ConsultaImagenTrfResponse
    extends BaseResponse
{

    @XmlElementRef(name = "Data_Imagen", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<byte[]> dataImagen;
    @XmlElementRef(name = "Id_CT", namespace = "Response/Canales", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> idCT;
    @XmlElement(name = "Nro_Form")
    protected Integer nroForm;

    /**
     * Gets the value of the dataImagen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getDataImagen() {
        return dataImagen;
    }

    /**
     * Sets the value of the dataImagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setDataImagen(JAXBElement<byte[]> value) {
        this.dataImagen = value;
    }

    /**
     * Gets the value of the idCT property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getIdCT() {
        return idCT;
    }

    /**
     * Sets the value of the idCT property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setIdCT(JAXBElement<BigDecimal> value) {
        this.idCT = value;
    }

    /**
     * Gets the value of the nroForm property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNroForm() {
        return nroForm;
    }

    /**
     * Sets the value of the nroForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNroForm(Integer value) {
        this.nroForm = value;
    }

}
