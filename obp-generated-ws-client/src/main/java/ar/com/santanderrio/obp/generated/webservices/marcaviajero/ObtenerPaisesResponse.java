
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerPaisesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerPaisesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paises" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pais" type="{http://visa.com.ar/visahome/integration/ws/mv/model}pais" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerPaisesResponse", propOrder = {
    "paises"
})
public class ObtenerPaisesResponse {

    protected ObtenerPaisesResponse.Paises paises;

    /**
     * Gets the value of the paises property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenerPaisesResponse.Paises }
     *     
     */
    public ObtenerPaisesResponse.Paises getPaises() {
        return paises;
    }

    /**
     * Sets the value of the paises property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenerPaisesResponse.Paises }
     *     
     */
    public void setPaises(ObtenerPaisesResponse.Paises value) {
        this.paises = value;
    }


    @Override
	public String toString() {
		return "ObtenerPaisesResponse [paises=" + paises + "]";
	}


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
     *         &lt;element name="pais" type="{http://visa.com.ar/visahome/integration/ws/mv/model}pais" maxOccurs="unbounded" minOccurs="0"/>
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
        "pais"
    })
    public static class Paises {

        protected List<Pais> pais;

        /**
         * Gets the value of the pais property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the pais property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPais().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Pais }
         * 
         * 
         */
        public List<Pais> getPais() {
            if (pais == null) {
                pais = new ArrayList<Pais>();
            }
            return this.pais;
        }

		@Override
		public String toString() {
			return "Paises [pais=" + pais + "]";
		}
        
    }

}
