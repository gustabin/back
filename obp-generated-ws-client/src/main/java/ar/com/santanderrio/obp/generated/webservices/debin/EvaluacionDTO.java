
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para evaluacionDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="evaluacionDTO">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}baseDTO">
 *       &lt;sequence>
 *         &lt;element name="puntaje" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="reglas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evaluacionDTO", propOrder = {
    "puntaje",
    "reglas"
})
public class EvaluacionDTO
    extends BaseDTO
{

    protected Integer puntaje;
    protected String reglas;

    /**
     * Obtiene el valor de la propiedad puntaje.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Define el valor de la propiedad puntaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPuntaje(Integer value) {
        this.puntaje = value;
    }

    /**
     * Obtiene el valor de la propiedad reglas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReglas() {
        return reglas;
    }

    /**
     * Define el valor de la propiedad reglas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReglas(String value) {
        this.reglas = value;
    }

}
