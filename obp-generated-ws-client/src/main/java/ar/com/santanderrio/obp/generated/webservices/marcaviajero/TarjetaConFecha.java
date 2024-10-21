
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tarjetaConFecha complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tarjetaConFecha">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellidoNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimosCuatro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="condicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechasInhabilitadas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}fecha" maxOccurs="unbounded"/>
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
@XmlType(name = "tarjetaConFecha", namespace = "http://visa.com.ar/visahome/integration/ws/mv/model", propOrder = {
    "apellidoNombre",
    "ultimosCuatro",
    "condicion",
    "producto",
    "fechasInhabilitadas"
})
public class TarjetaConFecha {

    protected String apellidoNombre;
    @XmlElement(required = true)
    protected String ultimosCuatro;
    @XmlElement(required = true)
    protected String condicion;
    @XmlElement(required = true)
    protected String producto;
    @XmlElement(required = true)
    protected TarjetaConFecha.FechasInhabilitadas fechasInhabilitadas;

    /**
     * Gets the value of the apellidoNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoNombre() {
        return apellidoNombre;
    }

    /**
     * Sets the value of the apellidoNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoNombre(String value) {
        this.apellidoNombre = value;
    }

    /**
     * Gets the value of the ultimosCuatro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimosCuatro() {
        return ultimosCuatro;
    }

    /**
     * Sets the value of the ultimosCuatro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimosCuatro(String value) {
        this.ultimosCuatro = value;
    }

    /**
     * Gets the value of the condicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Sets the value of the condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicion(String value) {
        this.condicion = value;
    }

    /**
     * Gets the value of the producto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Sets the value of the producto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducto(String value) {
        this.producto = value;
    }

    /**
     * Gets the value of the fechasInhabilitadas property.
     * 
     * @return
     *     possible object is
     *     {@link TarjetaConFecha.FechasInhabilitadas }
     *     
     */
    public TarjetaConFecha.FechasInhabilitadas getFechasInhabilitadas() {
        return fechasInhabilitadas;
    }

    /**
     * Sets the value of the fechasInhabilitadas property.
     * 
     * @param value
     *     allowed object is
     *     {@link TarjetaConFecha.FechasInhabilitadas }
     *     
     */
    public void setFechasInhabilitadas(TarjetaConFecha.FechasInhabilitadas value) {
        this.fechasInhabilitadas = value;
    }


    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetaConFecha [apellidoNombre=" + apellidoNombre + ", ultimosCuatro=" + ultimosCuatro + ", condicion="
				+ condicion + ", producto=" + producto + ", fechasInhabilitadas=" + fechasInhabilitadas + "]";
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
     *         &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}fecha" maxOccurs="unbounded"/>
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
        "fecha"
    })
    public static class FechasInhabilitadas {

        @XmlElement(namespace = "http://visa.com.ar/visahome/integration/ws/mv", required = true)
        protected List<Fecha> fecha;

        /**
         * Gets the value of the fecha property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the fecha property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFecha().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Fecha }
         * 
         * 
         */
        public List<Fecha> getFecha() {
            if (fecha == null) {
                fecha = new ArrayList<Fecha>();
            }
            return this.fecha;
        }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "FechasInhabilitadas [fecha=" + fecha + "]";
		}
        
        

    }

}
