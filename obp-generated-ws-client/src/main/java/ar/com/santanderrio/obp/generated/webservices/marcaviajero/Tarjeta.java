
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tarjeta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tarjeta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellidoNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimosCuatro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="condicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tarjeta", namespace = "http://visa.com.ar/visahome/integration/ws/mv/model", propOrder = {
    "apellidoNombre",
    "ultimosCuatro",
    "condicion",
    "producto"
})
public class Tarjeta {

    protected String apellidoNombre;
    @XmlElement(required = true)
    protected String ultimosCuatro;
    @XmlElement(required = true)
    protected String condicion;
    @XmlElement(required = true)
    protected String producto;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tarjeta [apellidoNombre=" + apellidoNombre + ", ultimosCuatro=" + ultimosCuatro + ", condicion="
				+ condicion + ", producto=" + producto + "]";
	}

}
