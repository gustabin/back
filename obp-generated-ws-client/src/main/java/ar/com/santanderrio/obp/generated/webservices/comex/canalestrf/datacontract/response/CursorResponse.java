
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BuscarDespachosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;


/**
 * <p>Java class for CursorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
    ConsultaOperacionesResponse.class,
    BuscarDespachosResponse.class
})
public class CursorResponse
    extends BaseResponse
{

    @XmlElement(name = "Cant_Reg")
    protected BigDecimal cantReg;

    /**
     * Gets the value of the cantReg property.
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
     * Sets the value of the cantReg property.
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
