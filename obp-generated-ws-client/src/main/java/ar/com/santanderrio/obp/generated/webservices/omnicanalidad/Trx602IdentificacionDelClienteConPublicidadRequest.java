
package ar.com.santanderrio.obp.generated.webservices.omnicanalidad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Trx602IdentificacionDelClienteConPublicidadRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trx602IdentificacionDelClienteConPublicidadRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs}TrxIdentificacionClienteProductRequest">
 *       &lt;sequence>
 *         &lt;element name="BancaPrivada" type="{http://schemas.microsoft.com/2003/10/Serialization/}char" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trx602IdentificacionDelClienteConPublicidadRequest", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Trx602", propOrder = {
    "bancaPrivada"
})
public class Trx602IdentificacionDelClienteConPublicidadRequest
    extends TrxIdentificacionClienteProductRequest
{

    @XmlElement(name = "BancaPrivada")
    protected Integer bancaPrivada;

    /**
     * Gets the value of the bancaPrivada property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBancaPrivada() {
        return bancaPrivada;
    }

    /**
     * Sets the value of the bancaPrivada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBancaPrivada(Integer value) {
        this.bancaPrivada = value;
    }

}
