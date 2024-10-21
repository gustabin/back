
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerViajesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerViajesResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayResponse">
 *       &lt;sequence>
 *         &lt;element name="listaViajes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="viaje" type="{http://visa.com.ar/visahome/integration/ws/mv/model}viaje" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerViajesResponse", propOrder = {
    "listaViajes"
})
public class ObtenerViajesResponse
    extends BaseGatewayResponse
{

    protected ObtenerViajesResponse.ListaViajes listaViajes;

    /**
     * Gets the value of the listaViajes property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenerViajesResponse.ListaViajes }
     *     
     */
    public ObtenerViajesResponse.ListaViajes getListaViajes() {
        return listaViajes;
    }

    /**
     * Sets the value of the listaViajes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenerViajesResponse.ListaViajes }
     *     
     */
    public void setListaViajes(ObtenerViajesResponse.ListaViajes value) {
        this.listaViajes = value;
    }


    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ObtenerViajesResponse [listaViajes=" + listaViajes + "]";
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
     *         &lt;element name="viaje" type="{http://visa.com.ar/visahome/integration/ws/mv/model}viaje" maxOccurs="unbounded" minOccurs="0"/>
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
        "viaje"
    })
    public static class ListaViajes {

        protected List<Viaje> viaje;

        /**
         * Gets the value of the viaje property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the viaje property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getViaje().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Viaje }
         * 
         * 
         */
        public List<Viaje> getViaje() {
            if (viaje == null) {
                viaje = new ArrayList<Viaje>();
            }
            return this.viaje;
        }

		public void setViaje(List<Viaje> viaje) {
			this.viaje = viaje;
		}

		@Override
		public String toString() {
			return "ListaViajes [viaje=" + viaje + "]";
		}
        
    }

}
