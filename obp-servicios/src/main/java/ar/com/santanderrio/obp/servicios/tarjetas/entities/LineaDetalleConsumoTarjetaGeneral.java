/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class LineaDetalleConsumoTarjetaGeneral.
 */
public class LineaDetalleConsumoTarjetaGeneral {
    /** The descripcion. */
    private String descripcion;

    /** The cuota. */
    private String cuota;

    /** The tiene cuota. */
    private Boolean tieneCuota;

    /** The codigo establecimiento. */
    private String codigoEstablecimiento;

    /** The nro referencia. */
    private String nroReferencia;

    /** The nro tarjeta. */
    private String nroTarjeta;

    /** The nro tarjeta enmascarda. */
    private String nroTarjetaMascara;

    /** The consumo pesos. */
    private Boolean consumoPesos;

    /** The consumo dolares. */
    private Boolean consumoDolares;

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion
     *            the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the cuota.
     *
     * @return the cuota
     */
    public String getCuota() {
        return cuota;
    }

    /**
     * Sets the cuota.
     *
     * @param cuota
     *            the new cuota
     */
    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    /**
     * Gets the tiene cuota.
     *
     * @return the tiene cuota
     */
    public Boolean getTieneCuota() {
        return tieneCuota;
    }

    /**
     * Sets the tiene cuota.
     *
     * @param tieneCuota
     *            the new tiene cuota
     */
    public void setTieneCuota(Boolean tieneCuota) {
        this.tieneCuota = tieneCuota;
    }

    /**
     * Gets the codigo establecimiento.
     *
     * @return the codigoEstablecimiento
     */
    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    /**
     * Sets the codigo establecimiento.
     *
     * @param codigoEstablecimiento
     *            the codigoEstablecimiento to set
     */
    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    /**
     * Gets the nro tarjeta.
     *
     * @return the nroTarjeta
     */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
     * Sets the nro tarjeta.
     *
     * @param nroTarjeta
     *            the nroTarjeta to set
     */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
     * Gets the nro referencia.
     *
     * @return the nroReferencia
     */
    public String getNroReferencia() {
        return nroReferencia;
    }

    /**
     * Sets the nro referencia.
     *
     * @param nroReferencia
     *            the nroReferencia to set
     */
    public void setNroReferencia(String nroReferencia) {
        this.nroReferencia = nroReferencia;
    }

    /**
     * Gets the consumo pesos.
     *
     * @return the consumoPesos
     */
    public Boolean getConsumoPesos() {
        return consumoPesos;
    }

    /**
     * Sets the consumo pesos.
     *
     * @param consumoPesos
     *            the consumoPesos to set
     */
    public void setConsumoPesos(Boolean consumoPesos) {
        this.consumoPesos = consumoPesos;
    }

    /**
     * Gets the consumo dolares.
     *
     * @return the consumoDolares
     */
    public Boolean getConsumoDolares() {
        return consumoDolares;
    }

    /**
     * Sets the consumo dolares.
     *
     * @param consumoDolares
     *            the consumoDolares to set
     */
    public void setConsumoDolares(Boolean consumoDolares) {
        this.consumoDolares = consumoDolares;
    }

    /**
	 * Gets the nro tarjeta mascara.
	 *
	 * @return the nroTarjetaMascara
	 */
    public String getNroTarjetaMascara() {
        return nroTarjetaMascara;
    }

    /**
	 * Sets the nro tarjeta mascara.
	 *
	 * @param nroTarjetaMascara
	 *            the nroTarjetaMascar to set
	 */
    public void setNroTarjetaMascara(String nroTarjetaMascara) {
        this.nroTarjetaMascara = nroTarjetaMascara;
    }

    /**
     * Hash filler.
     *
     * @param hcb
     *            the hcb
     * @param isFirst
     *            the is first
     */
    public void hashFiller(HashCodeBuilder hcb, boolean isFirst) {
        if (isFirst) {
            hcb.append(codigoEstablecimiento);
            hcb.append(consumoDolares);
            hcb.append(consumoPesos);
            hcb.append(cuota);
        } else {
            hcb.append(nroReferencia);
            hcb.append(nroTarjeta);
            hcb.append(tieneCuota);
        }
    }

    /**
     * Equals filler.
     *
     * @param eb
     *            the eb
     * @param isFirst
     *            the is first
     * @param other
     *            the other
     */
    public void equalsFiller(EqualsBuilder eb, boolean isFirst, LineaDetalleConsumoTarjetaDTO other) {
        if (isFirst) {
            eb.append(codigoEstablecimiento, other.getCodigoEstablecimiento());
            eb.append(consumoDolares, other.getConsumoDolares());
            eb.append(consumoPesos, other.getConsumoPesos());
            eb.append(cuota, other.getCuota());
        } else {
            eb.append(nroReferencia, other.getNroReferencia());
            eb.append(nroTarjeta, other.getNroTarjeta());
            eb.append(tieneCuota, other.getTieneCuota());
        }
    }
}
