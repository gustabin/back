
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReportePdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReportePdf">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReporteResultado">
 *       &lt;sequence>
 *         &lt;element name="ArchivoPdf" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportePdf", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", propOrder = {
    "archivoPdf"
})
public class ReportePdf
    extends ReporteResultado
{

    @XmlElementRef(name = "ArchivoPdf", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", type = JAXBElement.class)
    protected JAXBElement<byte[]> archivoPdf;

    /**
     * Gets the value of the archivoPdf property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getArchivoPdf() {
        return archivoPdf;
    }

    /**
     * Sets the value of the archivoPdf property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setArchivoPdf(JAXBElement<byte[]> value) {
        this.archivoPdf = ((JAXBElement<byte[]> ) value);
    }

}
