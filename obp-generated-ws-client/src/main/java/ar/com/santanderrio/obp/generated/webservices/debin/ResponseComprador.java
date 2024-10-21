
package ar.com.santanderrio.obp.generated.webservices.debin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseComprador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseComprador">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="cuentas" type="{http://webservices.api.debin.prismamp.com/}cuentaDebinDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreTitular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseComprador", propOrder = {
    "cuentas",
    "cuit",
    "nombreTitular"
})
public class ResponseComprador
    extends Response
{

    @XmlElement(nillable = true)
    protected List<CuentaDebinDTO> cuentas;
    protected String cuit;
    protected String nombreTitular;

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
     * {@link CuentaDebinDTO }
     * 
     * 
     */
    public List<CuentaDebinDTO> getCuentas() {
        if (cuentas == null) {
            cuentas = new ArrayList<CuentaDebinDTO>();
        }
        return this.cuentas;
    }

    /**
     * Obtiene el valor de la propiedad cuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Define el valor de la propiedad cuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTitular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Define el valor de la propiedad nombreTitular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTitular(String value) {
        this.nombreTitular = value;
    }

}
