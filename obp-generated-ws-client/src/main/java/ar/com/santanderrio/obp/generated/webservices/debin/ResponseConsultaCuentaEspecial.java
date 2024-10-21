
package ar.com.santanderrio.obp.generated.webservices.debin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseConsultaCuentaEspecial complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseConsultaCuentaEspecial">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="cuentas" type="{http://webservices.api.debin.prismamp.com/}cuentaEspecialDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsultaCuentaEspecial", propOrder = {
    "cuentas"
})
public class ResponseConsultaCuentaEspecial
    extends Response
{

    @XmlElement(nillable = true)
    protected List<CuentaEspecialDTO> cuentas;

    /**
     * Gets the value of the cuentas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CuentaEspecialDTO }
     * 
     * 
     */
    public List<CuentaEspecialDTO> getCuentas() {
        if (cuentas == null) {
            cuentas = new ArrayList<CuentaEspecialDTO>();
        }
        return this.cuentas;
    }

}
