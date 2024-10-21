
package ar.com.santanderrio.obp.generated.webservices.debin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseNuevoDebin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseNuevoDebin">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservices.api.debin.prismamp.com/}response">
 *       &lt;sequence>
 *         &lt;element name="detalleDebin" type="{http://webservices.api.debin.prismamp.com/}detalleDebinDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseNuevoDebin", propOrder = {
    "detalleDebin"
})
public class ResponseNuevoDebin
    extends Response
{

    protected DetalleDebinDTO detalleDebin;

    /**
     * Obtiene el valor de la propiedad detalleDebin.
     * 
     * @return
     *     possible object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public DetalleDebinDTO getDetalleDebin() {
        return detalleDebin;
    }

    /**
     * Define el valor de la propiedad detalleDebin.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleDebinDTO }
     *     
     */
    public void setDetalleDebin(DetalleDebinDTO value) {
        this.detalleDebin = value;
    }

}
