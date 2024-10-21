
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReporteResultado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReporteResultado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ErrorCodigo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ErrorDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReporteResultado", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", propOrder = {
    "errorCodigo",
    "errorDescripcion"
})
@XmlSeeAlso({
    ReporteExcel.class,
    ReportePdf.class
})
public class ReporteResultado {

    @XmlElement(name = "ErrorCodigo")
    protected Integer errorCodigo;
    @XmlElementRef(name = "ErrorDescripcion", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", type = JAXBElement.class)
    protected JAXBElement<String> errorDescripcion;

    /**
     * Gets the value of the errorCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getErrorCodigo() {
        return errorCodigo;
    }

    /**
     * Sets the value of the errorCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setErrorCodigo(Integer value) {
        this.errorCodigo = value;
    }

    /**
     * Gets the value of the errorDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErrorDescripcion() {
        return errorDescripcion;
    }

    /**
     * Sets the value of the errorDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErrorDescripcion(JAXBElement<String> value) {
        this.errorDescripcion = ((JAXBElement<String> ) value);
    }

}
