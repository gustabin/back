
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InfoCajeroDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InfoCajeroDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accesoPublico" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="aceptaDepositos" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="aptoNoVidente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="bandaHoraria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantCajeros" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="cirrus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dolaresDisponibles" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esExclusivo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="extraccion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="horarioRestringido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pesosDisponibles" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="reconocimientoImagen" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="servicioActivo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tieneDolares" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoCajeroDTO", namespace = "http://banelcomap.services.external.banelco.com", propOrder = {
    "accesoPublico",
    "aceptaDepositos",
    "aptoNoVidente",
    "bandaHoraria",
    "cantCajeros",
    "cirrus",
    "dolaresDisponibles",
    "esExclusivo",
    "extraccion",
    "horarioRestringido",
    "idBanco",
    "nombreBanco",
    "pesosDisponibles",
    "reconocimientoImagen",
    "servicioActivo",
    "tieneDolares"
})
public class InfoCajeroDTO {

    protected Boolean accesoPublico;
    protected Boolean aceptaDepositos;
    protected Boolean aptoNoVidente;
    @XmlElementRef(name = "bandaHoraria", namespace = "http://banelcomap.services.external.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> bandaHoraria;
    protected Integer cantCajeros;
    protected Boolean cirrus;
    protected Boolean dolaresDisponibles;
    protected Boolean esExclusivo;
    protected Boolean extraccion;
    protected Boolean horarioRestringido;
    @XmlElementRef(name = "idBanco", namespace = "http://banelcomap.services.external.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> idBanco;
    @XmlElementRef(name = "nombreBanco", namespace = "http://banelcomap.services.external.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> nombreBanco;
    protected Boolean pesosDisponibles;
    protected Boolean reconocimientoImagen;
    protected Boolean servicioActivo;
    protected Boolean tieneDolares;

    /**
     * Obtiene el valor de la propiedad accesoPublico.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAccesoPublico() {
        return accesoPublico;
    }

    /**
     * Define el valor de la propiedad accesoPublico.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccesoPublico(Boolean value) {
        this.accesoPublico = value;
    }

    /**
     * Obtiene el valor de la propiedad aceptaDepositos.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAceptaDepositos() {
        return aceptaDepositos;
    }

    /**
     * Define el valor de la propiedad aceptaDepositos.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAceptaDepositos(Boolean value) {
        this.aceptaDepositos = value;
    }

    /**
     * Obtiene el valor de la propiedad aptoNoVidente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAptoNoVidente() {
        return aptoNoVidente;
    }

    /**
     * Define el valor de la propiedad aptoNoVidente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAptoNoVidente(Boolean value) {
        this.aptoNoVidente = value;
    }

    /**
     * Obtiene el valor de la propiedad bandaHoraria.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBandaHoraria() {
        return bandaHoraria;
    }

    /**
     * Define el valor de la propiedad bandaHoraria.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBandaHoraria(JAXBElement<String> value) {
        this.bandaHoraria = value;
    }

    /**
     * Obtiene el valor de la propiedad cantCajeros.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantCajeros() {
        return cantCajeros;
    }

    /**
     * Define el valor de la propiedad cantCajeros.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantCajeros(Integer value) {
        this.cantCajeros = value;
    }

    /**
     * Obtiene el valor de la propiedad cirrus.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCirrus() {
        return cirrus;
    }

    /**
     * Define el valor de la propiedad cirrus.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCirrus(Boolean value) {
        this.cirrus = value;
    }

    /**
     * Obtiene el valor de la propiedad dolaresDisponibles.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDolaresDisponibles() {
        return dolaresDisponibles;
    }

    /**
     * Define el valor de la propiedad dolaresDisponibles.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDolaresDisponibles(Boolean value) {
        this.dolaresDisponibles = value;
    }

    /**
     * Obtiene el valor de la propiedad esExclusivo.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsExclusivo() {
        return esExclusivo;
    }

    /**
     * Define el valor de la propiedad esExclusivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsExclusivo(Boolean value) {
        this.esExclusivo = value;
    }

    /**
     * Obtiene el valor de la propiedad extraccion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExtraccion() {
        return extraccion;
    }

    /**
     * Define el valor de la propiedad extraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExtraccion(Boolean value) {
        this.extraccion = value;
    }

    /**
     * Obtiene el valor de la propiedad horarioRestringido.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHorarioRestringido() {
        return horarioRestringido;
    }

    /**
     * Define el valor de la propiedad horarioRestringido.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHorarioRestringido(Boolean value) {
        this.horarioRestringido = value;
    }

    /**
     * Obtiene el valor de la propiedad idBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdBanco() {
        return idBanco;
    }

    /**
     * Define el valor de la propiedad idBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdBanco(JAXBElement<String> value) {
        this.idBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBanco.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNombreBanco() {
        return nombreBanco;
    }

    /**
     * Define el valor de la propiedad nombreBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNombreBanco(JAXBElement<String> value) {
        this.nombreBanco = value;
    }

    /**
     * Obtiene el valor de la propiedad pesosDisponibles.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPesosDisponibles() {
        return pesosDisponibles;
    }

    /**
     * Define el valor de la propiedad pesosDisponibles.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPesosDisponibles(Boolean value) {
        this.pesosDisponibles = value;
    }

    /**
     * Obtiene el valor de la propiedad reconocimientoImagen.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReconocimientoImagen() {
        return reconocimientoImagen;
    }

    /**
     * Define el valor de la propiedad reconocimientoImagen.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReconocimientoImagen(Boolean value) {
        this.reconocimientoImagen = value;
    }

    /**
     * Obtiene el valor de la propiedad servicioActivo.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServicioActivo() {
        return servicioActivo;
    }

    /**
     * Define el valor de la propiedad servicioActivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServicioActivo(Boolean value) {
        this.servicioActivo = value;
    }

    /**
     * Obtiene el valor de la propiedad tieneDolares.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneDolares() {
        return tieneDolares;
    }

    /**
     * Define el valor de la propiedad tieneDolares.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneDolares(Boolean value) {
        this.tieneDolares = value;
    }

}
