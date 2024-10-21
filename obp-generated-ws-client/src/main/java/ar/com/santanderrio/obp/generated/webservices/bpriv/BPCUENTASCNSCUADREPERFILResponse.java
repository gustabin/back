
package ar.com.santanderrio.obp.generated.webservices.bpriv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ar.com.santanderrio.obp.generated.webservices.bpriv.domain.ControlCuadrePerfil;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BP_CUENTAS_CNS_CUADREPERFILResult" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}ControlCuadrePerfil" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bpcuentascnscuadreperfilResult"
})
@XmlRootElement(name = "BP_CUENTAS_CNS_CUADREPERFILResponse")
public class BPCUENTASCNSCUADREPERFILResponse {

    @XmlElementRef(name = "BP_CUENTAS_CNS_CUADREPERFILResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ControlCuadrePerfil> bpcuentascnscuadreperfilResult;

    /**
     * Gets the value of the bpcuentascnscuadreperfilResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ControlCuadrePerfil }{@code >}
     *     
     */
    public JAXBElement<ControlCuadrePerfil> getBPCUENTASCNSCUADREPERFILResult() {
        return bpcuentascnscuadreperfilResult;
    }

    /**
     * Sets the value of the bpcuentascnscuadreperfilResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ControlCuadrePerfil }{@code >}
     *     
     */
    public void setBPCUENTASCNSCUADREPERFILResult(JAXBElement<ControlCuadrePerfil> value) {
        this.bpcuentascnscuadreperfilResult = value;
    }

}
