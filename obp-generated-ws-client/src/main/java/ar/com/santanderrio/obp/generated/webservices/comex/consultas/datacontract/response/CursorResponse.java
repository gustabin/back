
package ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaNomPaisResponse;


/**
 * <p>Clase Java para CursorResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CursorResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/BSR.COMEX.Service.Contracts.Response}BaseResponse">
 *       &lt;sequence>
 *         &lt;element name="Cant_Reg" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CursorResponse", propOrder = {
    "cantReg"
})
@XmlSeeAlso({
    ConsultaNomPaisResponse.class,
    ConsultaBancosResponse.class
})
public class CursorResponse
    extends BaseResponse
{

    @XmlElement(name = "Cant_Reg")
    protected BigDecimal cantReg;

    /**
     * Obtiene el valor de la propiedad cantReg.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCantReg() {
        return cantReg;
    }

    /**
     * Define el valor de la propiedad cantReg.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCantReg(BigDecimal value) {
        this.cantReg = value;
    }

}
