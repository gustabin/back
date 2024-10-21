
package ar.com.santanderrio.obp.generated.webservices.debin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseListaDebinAux complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseListaDebinAux">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="debines" type="{http://webservices.api.debin.prismamp.com/}resumenDebinDTOAux" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="siguientePagina" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseListaDebinAux", propOrder = {
    "debines",
    "siguientePagina"
})
public class ResponseListaDebinAux
    extends Response
{

    @XmlElement(nillable = true)
    protected List<ResumenDebinDTOAux> debines;
    protected Integer siguientePagina;

    /**
     * Gets the value of the debines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the debines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDebines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResumenDebinDTOAux }
     * 
     * 
     */
    public List<ResumenDebinDTOAux> getDebines() {
        if (debines == null) {
            debines = new ArrayList<ResumenDebinDTOAux>();
        }
        return this.debines;
    }

    /**
     * Obtiene el valor de la propiedad siguientePagina.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSiguientePagina() {
        return siguientePagina;
    }

    /**
     * Define el valor de la propiedad siguientePagina.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSiguientePagina(Integer value) {
        this.siguientePagina = value;
    }

}
