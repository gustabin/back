
package ar.com.santanderrio.obp.generated.webservices.extractos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCuentaTituloFirmada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCuentaTituloFirmada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaTituloFirmada" type="{http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos}CuentaTituloFirmada" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCuentaTituloFirmada", namespace = "http://schemas.datacontract.org/2004/07/SantanderRio.Reporte.AccesoDatos", propOrder = {
    "cuentaTituloFirmada"
})
public class ArrayOfCuentaTituloFirmada {

    @XmlElement(name = "CuentaTituloFirmada", nillable = true)
    protected List<CuentaTituloFirmada> cuentaTituloFirmada;

    /**
     * Gets the value of the cuentaTituloFirmada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentaTituloFirmada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentaTituloFirmada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CuentaTituloFirmada }
     * 
     * 
     */
    public List<CuentaTituloFirmada> getCuentaTituloFirmada() {
        if (cuentaTituloFirmada == null) {
            cuentaTituloFirmada = new ArrayList<CuentaTituloFirmada>();
        }
        return this.cuentaTituloFirmada;
    }

}
