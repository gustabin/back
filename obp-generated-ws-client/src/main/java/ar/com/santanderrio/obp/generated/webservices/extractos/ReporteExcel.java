
package ar.com.santanderrio.obp.generated.webservices.extractos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReporteExcel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReporteExcel">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio}ReporteResultado">
 *       &lt;sequence>
 *         &lt;element name="ArchivoXLS" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReporteExcel", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", propOrder = {
    "archivoXLS"
})
public class ReporteExcel
    extends ReporteResultado
{

    @XmlElementRef(name = "ArchivoXLS", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reportes.Servicio", type = JAXBElement.class)
    protected JAXBElement<byte[]> archivoXLS;

    /**
     * Gets the value of the archivoXLS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getArchivoXLS() {
        return archivoXLS;
    }

    /**
     * Sets the value of the archivoXLS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setArchivoXLS(JAXBElement<byte[]> value) {
        this.archivoXLS = ((JAXBElement<byte[]> ) value);
    }

}
