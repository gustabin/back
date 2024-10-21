
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para requestDebin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestDebin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}request">
 *       &lt;sequence>
 *         &lt;element name="idDebin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestDebin", propOrder = {
    "idDebin"
})
public class RequestDebin
    extends Request
{

    protected String idDebin;

    /**
     * Obtiene el valor de la propiedad idDebin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDebin() {
        return idDebin;
    }

    /**
     * Define el valor de la propiedad idDebin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDebin(String value) {
        this.idDebin = value;
    }

    @Override
    public String toString() {
        return "RequestDebin [idDebin=" + idDebin + "]";
    }

}
