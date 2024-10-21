
package ar.com.santanderrio.obp.generated.webservices.discador;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx7005ObtenerDatosSesionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx7005ObtenerDatosSesionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}RequestBase">
 *       &lt;sequence>
 *         &lt;element name="Numero_Sesion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx7005ObtenerDatosSesionRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx7005", propOrder = {
    "numeroSesion"
})
public class Trx7005ObtenerDatosSesionRequest
    extends RequestBase
{

    @XmlElement(name = "Numero_Sesion")
    protected Integer numeroSesion;

    /**
     * Gets the value of the numeroSesion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroSesion() {
        return numeroSesion;
    }

    /**
     * Sets the value of the numeroSesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroSesion(Integer value) {
        this.numeroSesion = value;
    }

}
