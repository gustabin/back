//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.01 at 06:27:14 AM ART 
//

package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}Canal"/>
 *         &lt;element ref="{}SubCanal"/>
 *         &lt;element ref="{}TpoComprobante"/>
 *         &lt;element ref="{}SubTpoComprobante"/>
 *         &lt;element ref="{}EstadoOper"/>
 *         &lt;element ref="{}FechaOper"/>
 *         &lt;element ref="{}HoraOper"/>
 *         &lt;element ref="{}Cliente"/>
 *         &lt;element ref="{}Origen"/>
 *         &lt;element ref="{}Destino"/>
 *         &lt;element ref="{}NroComprobante"/>
 *         &lt;element ref="{}NroOperacion"/>
 *         &lt;element ref="{}NroSolicitud"/>
 *         &lt;element ref="{}Estado"/>
 *         &lt;element ref="{}TpoPago"/>
 *         &lt;element ref="{}ImporteTrfDeb"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlType
public class ComprobantePaghabcci extends Comprobante implements Serializable {
    /** Serial id. */
    private static final long serialVersionUID = -785546668945526348L;

    /** The estado oper. */
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;

    /** The fecha oper. */
    @XmlElement(name = "FechaOper")
    protected String fechaOper;

    /** The hora oper. */
    @XmlElement(name = "HoraOper")
    protected String horaOper;

    /** The cliente. */
    @XmlElement(name = "Cliente")
    protected Cliente cliente;

    /** The origen. */
    @XmlElement(name = "Origen")
    protected Origen origen;

    /** The destino. */
    @XmlElement(name = "Destino")
    protected DestinoPaghabcci destino;

    /** The nro comprobante. */
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;

    /** The nro operacion. */
    @XmlElement(name = "NroOperacion")
    protected String nroOperacion;

    /** The nro solicitud. */
    @XmlElement(name = "NroSolicitud")
    protected String nroSolicitud;

    /** The estado. */
    @XmlElement(name = "Estado")
    protected String estado;

    /** The tpo pago. */
    @XmlElement(name = "TpoPago")
    protected String tpoPago;

    /** The importe trf deb. */
    @XmlElement(name = "ImporteTrfDeb")
    protected String importeTrfDeb;

    /**
     * Gets the value of the estadoOper property.
     * 
     * @return possible object is {@link OperacionEstado }
     * 
     */
    public OperacionEstado getEstadoOper() {
        return estadoOper;
    }

    /**
     * Sets the value of the estadoOper property.
     * 
     * @param value
     *            allowed object is {@link OperacionEstado }
     * 
     */
    public void setEstadoOper(OperacionEstado value) {
        this.estadoOper = value;
    }

    /**
     * Gets the value of the fechaOper property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFechaOper() {
        return fechaOper;
    }

    /**
     * Sets the value of the fechaOper property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFechaOper(String value) {
        this.fechaOper = value;
    }

    /**
     * Gets the value of the horaOper property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getHoraOper() {
        return horaOper;
    }

    /**
     * Sets the value of the horaOper property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setHoraOper(String value) {
        this.horaOper = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return possible object is {@link Cliente }
     * 
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *            allowed object is {@link Cliente }
     * 
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the origen property.
     * 
     * @return possible object is {@link Origen }
     * 
     */
    public Origen getOrigen() {
        return origen;
    }

    /**
     * Sets the value of the origen property.
     * 
     * @param value
     *            allowed object is {@link Origen }
     * 
     */
    public void setOrigen(Origen value) {
        this.origen = value;
    }

    /**
     * Gets the value of the destino property.
     * 
     * @return possible object is {@link Destino }
     * 
     */
    public DestinoPaghabcci getDestino() {
        return destino;
    }

    /**
     * Sets the value of the destino property.
     * 
     * @param value
     *            allowed object is {@link Destino }
     * 
     */
    public void setDestino(DestinoPaghabcci value) {
        this.destino = value;
    }

    /**
     * Gets the value of the nroComprobante property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the value of the nroComprobante property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroComprobante(String value) {
        this.nroComprobante = value;
    }

    /**
     * Gets the value of the nroOperacion property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroOperacion() {
        return nroOperacion;
    }

    /**
     * Sets the value of the nroOperacion property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroOperacion(String value) {
        this.nroOperacion = value;
    }

    /**
     * Gets the value of the nroSolicitud property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * Sets the value of the nroSolicitud property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNroSolicitud(String value) {
        this.nroSolicitud = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the tpoPago property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpoPago() {
        return tpoPago;
    }

    /**
     * Sets the value of the tpoPago property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTpoPago(String value) {
        this.tpoPago = value;
    }

    /**
     * Gets the value of the importeTrfDeb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getImporteTrfDeb() {
        return importeTrfDeb;
    }

    /**
     * Sets the value of the importeTrfDeb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setImporteTrfDeb(String value) {
        this.importeTrfDeb = value;
    }

}
