
package ar.com.santanderrio.obp.generated.webservices.bpriv.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSaldo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSaldo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Saldo" type="{http://schemas.datacontract.org/2004/07/BancaPrivada.Domain}Saldo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSaldo", propOrder = {
    "saldo"
})
public class ArrayOfSaldo {

    @XmlElement(name = "Saldo", nillable = true)
    protected List<Saldo> saldo;

    /**
     * Gets the value of the saldo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the saldo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSaldo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Saldo }
     * 
     * 
     */
    public List<Saldo> getSaldo() {
        if (saldo == null) {
            saldo = new ArrayList<Saldo>();
        }
        return this.saldo;
    }

}
