package ar.com.santanderrio.obp.servicios.scomp.client.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class TarjetaFinanciacionResumen.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class TarjetaFinanciacionResumen {

    /** The tipo tarjeta. */
    @XmlElement(name = "TipoTarjeta")
    protected String tipoTarjeta;
    
    /** The num tarjeta. */
    @XmlElement(name = "NumTarjeta")
    protected String numTarjeta;
    
    /** The importe financiado. */
    @XmlElement(name = "ImporteFinanciado")
    protected String importeFinanciado;
    
    /** The importe cuota. */
    @XmlElement(name = "ImporteCuota")
    protected String importeCuota;
    
    /** The cant cuotas. */
    @XmlElement(name = "CantCuotas")
    protected String cantCuotas;
    
    /** The tasa nominal anual. */
    @XmlElement(name = "TasaNominalAnual")
    protected String tasaNominalAnual;
    
    /** The costo financiero total. */
    @XmlElement(name = "CostoFinancieroTotal")
    protected String costoFinancieroTotal;
    
    /** The num comprobante. */
    @XmlElement(name = "NumComprobante")
    protected String numComprobante;

    /**
     * Gets the tipo tarjeta.
     *
     * @return the tipo tarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the tipo tarjeta.
     *
     * @param tipoTarjeta the new tipo tarjeta
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Gets the num tarjeta.
     *
     * @return the num tarjeta
     */
    public String getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * Sets the num tarjeta.
     *
     * @param numTarjeta the new num tarjeta
     */
    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    /**
     * Gets the importe financiado.
     *
     * @return the importe financiado
     */
    public String getImporteFinanciado() {
        return importeFinanciado;
    }

    /**
     * Sets the importe financiado.
     *
     * @param importeFinanciado the new importe financiado
     */
    public void setImporteFinanciado(String importeFinanciado) {
        this.importeFinanciado = importeFinanciado;
    }

    /**
     * Gets the importe cuota.
     *
     * @return the importe cuota
     */
    public String getImporteCuota() {
        return importeCuota;
    }

    /**
     * Sets the importe cuota.
     *
     * @param importeCuota the new importe cuota
     */
    public void setImporteCuota(String importeCuota) {
        this.importeCuota = importeCuota;
    }

    /**
     * Gets the cant cuotas.
     *
     * @return the cant cuotas
     */
    public String getCantCuotas() {
        return cantCuotas;
    }

    /**
     * Sets the cant cuotas.
     *
     * @param cantCuotas the new cant cuotas
     */
    public void setCantCuotas(String cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    /**
     * Gets the tasa nominal anual.
     *
     * @return the tasa nominal anual
     */
    public String getTasaNominalAnual() {
        return tasaNominalAnual;
    }

    /**
     * Sets the tasa nominal anual.
     *
     * @param tasaNominalAnual the new tasa nominal anual
     */
    public void setTasaNominalAnual(String tasaNominalAnual) {
        this.tasaNominalAnual = tasaNominalAnual;
    }

    /**
     * Gets the costo financiero total.
     *
     * @return the costo financiero total
     */
    public String getCostoFinancieroTotal() {
        return costoFinancieroTotal;
    }

    /**
     * Sets the costo financiero total.
     *
     * @param costoFinancieroTotal the new costo financiero total
     */
    public void setCostoFinancieroTotal(String costoFinancieroTotal) {
        this.costoFinancieroTotal = costoFinancieroTotal;
    }

    /**
     * Gets the num comprobante.
     *
     * @return the num comprobante
     */
    public String getNumComprobante() {
        return numComprobante;
    }

    /**
     * Sets the num comprobante.
     *
     * @param numComprobante the new num comprobante
     */
    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }
    
    
}
