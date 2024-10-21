
package ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MedioPago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MedioPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroTarjeta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idMedioDePago" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="favorito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoNovedad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedioPago", propOrder = {
    "numeroTarjeta",
    "idMedioDePago",
    "favorito",
    "fechaVencimiento",
    "monto",
    "tipoNovedad"
})
public class MedioPago {

    @XmlElement(required = true)
    protected String numeroTarjeta;
    protected int idMedioDePago;
    @XmlElement(required = true)
    protected String favorito;
    protected int fechaVencimiento;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double monto;
    @XmlElement(required = true)
    protected String tipoNovedad;

    /**
     * Obtiene el valor de la propiedad numeroTarjeta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Define el valor de la propiedad numeroTarjeta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTarjeta(String value) {
        this.numeroTarjeta = value;
    }

    /**
     * Obtiene el valor de la propiedad idMedioDePago.
     * 
     */
    public int getIdMedioDePago() {
        return idMedioDePago;
    }

    /**
     * Define el valor de la propiedad idMedioDePago.
     * 
     */
    public void setIdMedioDePago(int value) {
        this.idMedioDePago = value;
    }

    /**
     * Obtiene el valor de la propiedad favorito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFavorito() {
        return favorito;
    }

    /**
     * Define el valor de la propiedad favorito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFavorito(String value) {
        this.favorito = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     */
    public int getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     */
    public void setFechaVencimiento(int value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonto(Double value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoNovedad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNovedad() {
        return tipoNovedad;
    }

    /**
     * Define el valor de la propiedad tipoNovedad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNovedad(String value) {
        this.tipoNovedad = value;
    }

}
