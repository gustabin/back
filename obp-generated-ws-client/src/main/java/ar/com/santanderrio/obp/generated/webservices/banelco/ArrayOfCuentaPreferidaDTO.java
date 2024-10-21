
package ar.com.santanderrio.obp.generated.webservices.banelco;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfCuentaPreferidaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCuentaPreferidaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaPreferidaDTO" type="{http://dto.cds.banelco.com}CuentaPreferidaDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCuentaPreferidaDTO", namespace = "http://dto.cds.banelco.com", propOrder = {
    "cuentaPreferidaDTO"
})
public class ArrayOfCuentaPreferidaDTO {

    @XmlElement(name = "CuentaPreferidaDTO", nillable = true)
    protected List<CuentaPreferidaDTO> cuentaPreferidaDTO;

    /**
     * Gets the value of the cuentaPreferidaDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentaPreferidaDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentaPreferidaDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CuentaPreferidaDTO }
     * 
     * 
     */
    public List<CuentaPreferidaDTO> getCuentaPreferidaDTO() {
        if (cuentaPreferidaDTO == null) {
            cuentaPreferidaDTO = new ArrayList<CuentaPreferidaDTO>();
        }
        return this.cuentaPreferidaDTO;
    }

}
