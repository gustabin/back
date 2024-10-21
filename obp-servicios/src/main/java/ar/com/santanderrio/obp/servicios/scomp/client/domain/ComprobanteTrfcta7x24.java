//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.28 at 06:12:09 PM ART 
//

package ar.com.santanderrio.obp.servicios.scomp.client.domain;

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
 *         &lt;element ref="{}DescEstado"/>
 *         &lt;element ref="{}FechaOper"/>
 *         &lt;element ref="{}HoraOper"/>
 *         &lt;element ref="{}Cliente"/>
 *         &lt;element ref="{}Origen"/>
 *         &lt;element ref="{}Destino"/>
 *         &lt;element ref="{}TransfDiferida"/>
 *         &lt;element ref="{}NroComprobante"/>
 *         &lt;element ref="{}ImporteDebito"/>
 *         &lt;element ref="{}Concepto"/>
 *         &lt;element ref="{}DescConcepto"/>
 *         &lt;element ref="{}TitularCtaCredito"/>
 *         &lt;element ref="{}Recordatorio"/>
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
public class ComprobanteTrfcta7x24 extends Comprobante {
    @XmlElement(name = "EstadoOper")
    protected OperacionEstado estadoOper;
    @XmlElement(name = "DescEstado")
    protected String descEstado;
    @XmlElement(name = "FechaOper")
    protected String fechaOper;
    @XmlElement(name = "HoraOper")
    protected String horaOper;
    @XmlElement(name = "Cliente")
    protected Cliente cliente;
    @XmlElement(name = "Origen")
    protected Origen origen;
    @XmlElement(name = "Destino")
    protected DestinoTrfcta destino;
    @XmlElement(name = "TransfDiferida")
    protected String transfDiferida;
    @XmlElement(name = "NroComprobante")
    protected String nroComprobante;
    @XmlElement(name = "ImporteDebito")
    protected String importeDebito;
    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "DescConcepto")
    protected String descConcepto;
    @XmlElement(name = "TitularCtaCredito")
    protected String titularCtaCredito;
    @XmlElement(name = "Recordatorio")
    protected String recordatorio;

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
     * Gets the value of the descEstado property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescEstado() {
        return descEstado;
    }

    /**
     * Sets the value of the descEstado property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescEstado(String value) {
        this.descEstado = value;
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
     * @return possible object is {@link DestinoTrfcta }
     * 
     */
    public DestinoTrfcta getDestino() {
        return destino;
    }

    /**
     * Sets the value of the destino property.
     * 
     * @param value
     *            allowed object is {@link DestinoTrfcta }
     * 
     */
    public void setDestino(DestinoTrfcta value) {
        this.destino = value;
    }

    /**
     * Gets the value of the transfDiferida property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTransfDiferida() {
        return transfDiferida;
    }

    /**
     * Sets the value of the transfDiferida property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTransfDiferida(String value) {
        this.transfDiferida = value;
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
     * Gets the value of the importeDebito property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getImporteDebito() {
        return importeDebito;
    }

    /**
     * Sets the value of the importeDebito property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setImporteDebito(String value) {
        this.importeDebito = value;
    }

    /**
     * Gets the value of the concepto property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Sets the value of the concepto property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Gets the value of the descConcepto property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescConcepto() {
        return descConcepto;
    }

    /**
     * Sets the value of the descConcepto property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescConcepto(String value) {
        this.descConcepto = value;
    }

    /**
     * Gets the value of the titularCtaCredito property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTitularCtaCredito() {
        return titularCtaCredito;
    }

    /**
     * Sets the value of the titularCtaCredito property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTitularCtaCredito(String value) {
        this.titularCtaCredito = value;
    }

    /**
     * Gets the value of the recordatorio property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getRecordatorio() {
        return recordatorio;
    }

    /**
     * Sets the value of the recordatorio property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setRecordatorio(String value) {
        this.recordatorio = value;
    }

}
