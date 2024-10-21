
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultaCuentaPreferidaResponseDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaCuentaPreferidaResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuentasPreferidasDTO" type="{http://dto.cds.banelco.com}ArrayOfCuentaPreferidaDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaCuentaPreferidaResponseDTO", namespace = "http://dto.cds.banelco.com", propOrder = {
    "cuentasPreferidasDTO"
})
public class ConsultaCuentaPreferidaResponseDTO {

    @XmlElementRef(name = "cuentasPreferidasDTO", namespace = "http://dto.cds.banelco.com", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCuentaPreferidaDTO> cuentasPreferidasDTO;

    /**
     * Obtiene el valor de la propiedad cuentasPreferidasDTO.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCuentaPreferidaDTO }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCuentaPreferidaDTO> getCuentasPreferidasDTO() {
        return cuentasPreferidasDTO;
    }

    /**
     * Define el valor de la propiedad cuentasPreferidasDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCuentaPreferidaDTO }{@code >}
     *     
     */
    public void setCuentasPreferidasDTO(JAXBElement<ArrayOfCuentaPreferidaDTO> value) {
        this.cuentasPreferidasDTO = value;
    }

}
