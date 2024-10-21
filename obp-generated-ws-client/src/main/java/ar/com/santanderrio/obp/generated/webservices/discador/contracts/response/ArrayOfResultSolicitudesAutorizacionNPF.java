
package ar.com.santanderrio.obp.generated.webservices.discador.contracts.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfResultSolicitudesAutorizacionNPF complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfResultSolicitudesAutorizacionNPF">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultSolicitudesAutorizacionNPF" type="{http://schemas.datacontract.org/2004/07/ISBAN.SrvTran.Common.DataContratcs.Common.Response}ResultSolicitudesAutorizacionNPF" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfResultSolicitudesAutorizacionNPF", propOrder = {
    "resultSolicitudesAutorizacionNPF"
})
public class ArrayOfResultSolicitudesAutorizacionNPF {

    @XmlElement(name = "ResultSolicitudesAutorizacionNPF", nillable = true)
    protected List<ResultSolicitudesAutorizacionNPF> resultSolicitudesAutorizacionNPF;

    /**
     * Gets the value of the resultSolicitudesAutorizacionNPF property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultSolicitudesAutorizacionNPF property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultSolicitudesAutorizacionNPF().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultSolicitudesAutorizacionNPF }
     * 
     * 
     */
    public List<ResultSolicitudesAutorizacionNPF> getResultSolicitudesAutorizacionNPF() {
        if (resultSolicitudesAutorizacionNPF == null) {
            resultSolicitudesAutorizacionNPF = new ArrayList<ResultSolicitudesAutorizacionNPF>();
        }
        return this.resultSolicitudesAutorizacionNPF;
    }

}
