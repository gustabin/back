
package ar.com.santanderrio.obp.generated.webservices.marcaviajero;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for viaje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="viaje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificadorViaje" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="puedeActualizar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paises">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}pais" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tarjetas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}tarjeta" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="accionesPermitidas" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "viaje", namespace = "http://visa.com.ar/visahome/integration/ws/mv/model", propOrder = {
    "identificadorViaje",
    "fechaInicio",
    "fechaFin",
    "puedeActualizar",
    "status",
    "paises",
    "tarjetas",
    "accionesPermitidas"
})
public class Viaje {

    protected Long identificadorViaje;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFin;
    protected Boolean puedeActualizar;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected Viaje.Paises paises;
    @XmlElement(required = true)
    protected Viaje.Tarjetas tarjetas;
    protected Viaje.AccionesPermitidas accionesPermitidas;

    /**
     * Gets the value of the identificadorViaje property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdentificadorViaje() {
        return identificadorViaje;
    }

    /**
     * Sets the value of the identificadorViaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdentificadorViaje(Long value) {
        this.identificadorViaje = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the puedeActualizar property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPuedeActualizar() {
        return puedeActualizar;
    }

    /**
     * Sets the value of the puedeActualizar property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPuedeActualizar(Boolean value) {
        this.puedeActualizar = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the paises property.
     * 
     * @return
     *     possible object is
     *     {@link Viaje.Paises }
     *     
     */
    public Viaje.Paises getPaises() {
        return paises;
    }

    /**
     * Sets the value of the paises property.
     * 
     * @param value
     *     allowed object is
     *     {@link Viaje.Paises }
     *     
     */
    public void setPaises(Viaje.Paises value) {
        this.paises = value;
    }

    /**
     * Gets the value of the tarjetas property.
     * 
     * @return
     *     possible object is
     *     {@link Viaje.Tarjetas }
     *     
     */
    public Viaje.Tarjetas getTarjetas() {
        return tarjetas;
    }

    /**
     * Sets the value of the tarjetas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Viaje.Tarjetas }
     *     
     */
    public void setTarjetas(Viaje.Tarjetas value) {
        this.tarjetas = value;
    }

    /**
     * Gets the value of the accionesPermitidas property.
     * 
     * @return
     *     possible object is
     *     {@link Viaje.AccionesPermitidas }
     *     
     */
    public Viaje.AccionesPermitidas getAccionesPermitidas() {
        return accionesPermitidas;
    }

    /**
     * Sets the value of the accionesPermitidas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Viaje.AccionesPermitidas }
     *     
     */
    public void setAccionesPermitidas(Viaje.AccionesPermitidas value) {
        this.accionesPermitidas = value;
    }


    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Viaje [identificadorViaje=" + identificadorViaje + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", puedeActualizar=" + puedeActualizar + ", status=" + status + ", paises=" + paises
				+ ", tarjetas=" + tarjetas + ", accionesPermitidas=" + accionesPermitidas + "]";
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
     *         &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "accion"
    })
    public static class AccionesPermitidas {

        protected List<String> accion;

        /**
         * Gets the value of the accion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the accion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAccion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAccion() {
            if (accion == null) {
                accion = new ArrayList<String>();
            }
            return this.accion;
        }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "AccionesPermitidas [accion=" + accion + "]";
		}

        
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
     *         &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}pais" maxOccurs="unbounded"/>
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

        @XmlElement(namespace = "http://visa.com.ar/visahome/integration/ws/mv", required = true)
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

		public void setPais(List<Pais> pais) {
			this.pais = pais;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Paises [pais=" + pais + "]";
		}

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
     *         &lt;element ref="{http://visa.com.ar/visahome/integration/ws/mv}tarjeta" maxOccurs="unbounded"/>
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

        @XmlElement(namespace = "http://visa.com.ar/visahome/integration/ws/mv", required = true)
        protected List<Tarjeta> tarjeta;

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
         * {@link Tarjeta }
         * 
         * 
         */
        public List<Tarjeta> getTarjeta() {
            if (tarjeta == null) {
                tarjeta = new ArrayList<Tarjeta>();
            }
            return this.tarjeta;
        }

		public void setTarjeta(List<Tarjeta> tarjeta) {
			this.tarjeta = tarjeta;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Tarjetas [tarjeta=" + tarjeta + "]";
		}

    }

}
