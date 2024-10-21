
package ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDocumentoConcepto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDocumentoConcepto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentoConcepto" type="{Domain}DocumentoConcepto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDocumentoConcepto", propOrder = {
    "documentoConcepto"
})
public class ArrayOfDocumentoConcepto {

    @XmlElement(name = "DocumentoConcepto", nillable = true)
    protected List<DocumentoConcepto> documentoConcepto;

    /**
     * Gets the value of the documentoConcepto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoConcepto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoConcepto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoConcepto }
     * 
     * 
     */
    public List<DocumentoConcepto> getDocumentoConcepto() {
        if (documentoConcepto == null) {
            documentoConcepto = new ArrayList<DocumentoConcepto>();
        }
        return this.documentoConcepto;
    }

}
