
package ar.com.santanderrio.obp.generated.webservices.echeq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListReceivedInDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListReceivedInDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha_pago_desde" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_pago_hasta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cheque_numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidad_registros_pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderby" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListReceivedInDiscount", propOrder = {
    "cuit",
    "fechaPagoDesde",
    "fechaPagoHasta",
    "chequeNumero",
    "numeroPagina",
    "cantidadRegistrosPagina",
    "orderby"
})
public class ListReceivedInDiscount {

    @XmlElement(required = true)
    protected String cuit;
    @XmlElement(name = "fecha_pago_desde")
    protected String fechaPagoDesde;
    @XmlElement(name = "fecha_pago_hasta")
    protected String fechaPagoHasta;
    @XmlElement(name = "cheque_numero")
    protected String chequeNumero;
    @XmlElement(name = "numero_pagina")
    protected String numeroPagina;
    @XmlElement(name = "cantidad_registros_pagina")
    protected String cantidadRegistrosPagina;
    protected String orderby;

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

    /**
     * Gets the value of the fechaPagoDesde property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPagoDesde() {
        return fechaPagoDesde;
    }

    /**
     * Sets the value of the fechaPagoDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPagoDesde(String value) {
        this.fechaPagoDesde = value;
    }

    /**
     * Gets the value of the fechaPagoHasta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPagoHasta() {
        return fechaPagoHasta;
    }

    /**
     * Sets the value of the fechaPagoHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPagoHasta(String value) {
        this.fechaPagoHasta = value;
    }

    /**
     * Gets the value of the chequeNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeNumero() {
        return chequeNumero;
    }

    /**
     * Sets the value of the chequeNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeNumero(String value) {
        this.chequeNumero = value;
    }

    /**
     * Gets the value of the numeroPagina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroPagina() {
        return numeroPagina;
    }

    /**
     * Sets the value of the numeroPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroPagina(String value) {
        this.numeroPagina = value;
    }

    /**
     * Gets the value of the cantidadRegistrosPagina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadRegistrosPagina() {
        return cantidadRegistrosPagina;
    }

    /**
     * Sets the value of the cantidadRegistrosPagina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadRegistrosPagina(String value) {
        this.cantidadRegistrosPagina = value;
    }

    /**
     * Gets the value of the orderby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderby() {
        return orderby;
    }

    /**
     * Sets the value of the orderby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderby(String value) {
        this.orderby = value;
    }

}
