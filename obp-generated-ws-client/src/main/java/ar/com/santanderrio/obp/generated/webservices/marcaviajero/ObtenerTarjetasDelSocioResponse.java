
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for obtenerTarjetasDelSocioResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerTarjetasDelSocioResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://visa.com.ar/visahome/integration/ws/mv/message}baseGatewayResponse">
 *       &lt;sequence>
 *         &lt;element name="fechaInicioMaxima" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="duracionMaxima" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tarjetas" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="tarjeta" type="{http://visa.com.ar/visahome/integration/ws/mv/model}tarjetaConFecha" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "obtenerTarjetasDelSocioResponse", propOrder = {
    "fechaInicioMaxima",
    "duracionMaxima",
    "tarjetas"
})
public class ObtenerTarjetasDelSocioResponse
    extends BaseGatewayResponse
{

    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioMaxima;
    protected Integer duracionMaxima;
    protected ObtenerTarjetasDelSocioResponse.Tarjetas tarjetas;

    /**
     * Gets the value of the fechaInicioMaxima property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioMaxima() {
        return fechaInicioMaxima;
    }

    /**
     * Sets the value of the fechaInicioMaxima property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioMaxima(XMLGregorianCalendar value) {
        this.fechaInicioMaxima = value;
    }

    /**
     * Gets the value of the duracionMaxima property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDuracionMaxima() {
        return duracionMaxima;
    }

    /**
     * Sets the value of the duracionMaxima property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDuracionMaxima(Integer value) {
        this.duracionMaxima = value;
    }

    /**
     * Gets the value of the tarjetas property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenerTarjetasDelSocioResponse.Tarjetas }
     *     
     */
    public ObtenerTarjetasDelSocioResponse.Tarjetas getTarjetas() {
        return tarjetas;
    }

    /**
     * Sets the value of the tarjetas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenerTarjetasDelSocioResponse.Tarjetas }
     *     
     */
    public void setTarjetas(ObtenerTarjetasDelSocioResponse.Tarjetas value) {
        this.tarjetas = value;
    }


    @Override
	public String toString() {
		return "ObtenerTarjetasDelSocioResponse [fechaInicioMaxima=" + fechaInicioMaxima + ", duracionMaxima="
				+ duracionMaxima + ", tarjetas=" + tarjetas + "]";
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
     *         &lt;element name="tarjeta" type="{http://visa.com.ar/visahome/integration/ws/mv/model}tarjetaConFecha" maxOccurs="unbounded" minOccurs="0"/>
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
        "tarjeta"
    })
    public static class Tarjetas {

        protected List<TarjetaConFecha> tarjeta;

        /**
         * Gets the value of the tarjeta property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tarjeta property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTarjeta().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TarjetaConFecha }
         * 
         * 
         */
        public List<TarjetaConFecha> getTarjeta() {
            if (tarjeta == null) {
                tarjeta = new ArrayList<TarjetaConFecha>();
            }
            return this.tarjeta;
        }

		@Override
		public String toString() {
			return "Tarjetas [tarjeta=" + tarjeta + "]";
		}

    }

}
