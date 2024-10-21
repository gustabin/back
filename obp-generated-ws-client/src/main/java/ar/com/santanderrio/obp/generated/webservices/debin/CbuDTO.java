
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cbuDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cbuDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="cbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cvu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cbuDTO", propOrder = {
    "cbu",
    "cvu"
})
public class CbuDTO
    extends BaseDTO
{

    protected String cbu;
    protected String cvu;

    /**
     * Obtiene el valor de la propiedad cbu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Define el valor de la propiedad cbu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbu(String value) {
        this.cbu = value;
    }

    /**
     * Obtiene el valor de la propiedad cvu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvu() {
        return cvu;
    }

    /**
     * Define el valor de la propiedad cvu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvu(String value) {
        this.cvu = value;
    }

}
