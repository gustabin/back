
package ar.com.santanderrio.obp.generated.webservices.banelco;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstadoCajeroResponseDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstadoCajeroResponseDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bandaHoraria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disponibleHopper1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="disponibleHopper2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="disponibleHopper3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="disponibleHopper4" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="extraccion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monedaHopper1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monedaHopper2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monedaHopper3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monedaHopper4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="respuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicioActivo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoCajeroResponseDTO", namespace = "http://dto.banelco.com", propOrder = {
    "bandaHoraria",
    "disponibleHopper1",
    "disponibleHopper2",
    "disponibleHopper3",
    "disponibleHopper4",
    "extraccion",
    "idBanco",
    "idCajero",
    "monedaHopper1",
    "monedaHopper2",
    "monedaHopper3",
    "monedaHopper4",
    "respuesta",
    "servicioActivo"
})
public class EstadoCajeroResponseDTO {

    @XmlElementRef(name = "bandaHoraria", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> bandaHoraria;
    @XmlElementRef(name = "disponibleHopper1", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Double> disponibleHopper1;
    @XmlElementRef(name = "disponibleHopper2", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Double> disponibleHopper2;
    @XmlElementRef(name = "disponibleHopper3", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Double> disponibleHopper3;
    @XmlElementRef(name = "disponibleHopper4", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Double> disponibleHopper4;
    @XmlElementRef(name = "extraccion", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Boolean> extraccion;
    @XmlElementRef(name = "idBanco", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> idBanco;
    @XmlElementRef(name = "idCajero", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> idCajero;
    protected Integer monedaHopper1;
    protected Integer monedaHopper2;
    protected Integer monedaHopper3;
    protected Integer monedaHopper4;
    @XmlElementRef(name = "respuesta", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<String> respuesta;
    @XmlElementRef(name = "servicioActivo", namespace = "http://dto.banelco.com", type = JAXBElement.class)
    protected JAXBElement<Boolean> servicioActivo;

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
     * Obtiene el valor de la propiedad disponibleHopper1.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDisponibleHopper1() {
        return disponibleHopper1;
    }

    /**
     * Define el valor de la propiedad disponibleHopper1.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDisponibleHopper1(JAXBElement<Double> value) {
        this.disponibleHopper1 = value;
    }

    /**
     * Obtiene el valor de la propiedad disponibleHopper2.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDisponibleHopper2() {
        return disponibleHopper2;
    }

    /**
     * Define el valor de la propiedad disponibleHopper2.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDisponibleHopper2(JAXBElement<Double> value) {
        this.disponibleHopper2 = value;
    }

    /**
     * Obtiene el valor de la propiedad disponibleHopper3.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDisponibleHopper3() {
        return disponibleHopper3;
    }

    /**
     * Define el valor de la propiedad disponibleHopper3.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDisponibleHopper3(JAXBElement<Double> value) {
        this.disponibleHopper3 = value;
    }

    /**
     * Obtiene el valor de la propiedad disponibleHopper4.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getDisponibleHopper4() {
        return disponibleHopper4;
    }

    /**
     * Define el valor de la propiedad disponibleHopper4.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setDisponibleHopper4(JAXBElement<Double> value) {
        this.disponibleHopper4 = value;
    }

    /**
     * Obtiene el valor de la propiedad extraccion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getExtraccion() {
        return extraccion;
    }

    /**
     * Define el valor de la propiedad extraccion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setExtraccion(JAXBElement<Boolean> value) {
        this.extraccion = value;
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
     * Obtiene el valor de la propiedad idCajero.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdCajero() {
        return idCajero;
    }

    /**
     * Define el valor de la propiedad idCajero.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdCajero(JAXBElement<String> value) {
        this.idCajero = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaHopper1.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonedaHopper1() {
        return monedaHopper1;
    }

    /**
     * Define el valor de la propiedad monedaHopper1.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonedaHopper1(Integer value) {
        this.monedaHopper1 = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaHopper2.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonedaHopper2() {
        return monedaHopper2;
    }

    /**
     * Define el valor de la propiedad monedaHopper2.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonedaHopper2(Integer value) {
        this.monedaHopper2 = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaHopper3.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonedaHopper3() {
        return monedaHopper3;
    }

    /**
     * Define el valor de la propiedad monedaHopper3.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonedaHopper3(Integer value) {
        this.monedaHopper3 = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaHopper4.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonedaHopper4() {
        return monedaHopper4;
    }

    /**
     * Define el valor de la propiedad monedaHopper4.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonedaHopper4(Integer value) {
        this.monedaHopper4 = value;
    }

    /**
     * Obtiene el valor de la propiedad respuesta.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRespuesta() {
        return respuesta;
    }

    /**
     * Define el valor de la propiedad respuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRespuesta(JAXBElement<String> value) {
        this.respuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad servicioActivo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getServicioActivo() {
        return servicioActivo;
    }

    /**
     * Define el valor de la propiedad servicioActivo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setServicioActivo(JAXBElement<Boolean> value) {
        this.servicioActivo = value;
    }

}
