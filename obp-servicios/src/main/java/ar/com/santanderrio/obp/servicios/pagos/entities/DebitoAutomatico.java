/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DebitoAutomatico.
 */
public class DebitoAutomatico {

    /** The codigo servicio. */
    private String codigoServicio;

    /** The nombre servicio. */
    private String nombreServicio;

    /** The numero partida. */
    private String numeroPartida;

    /** The estado cliente. */
    private String estadoCliente;

    /** The estado servicio. */
    private String estadoServicio;

    /** The fecha servicio. */
    private Date fechaServicio;

    /** The tipo fecha. */
    private String tipoFecha;

    /** The tipo stop debit. */
    private String tipoStopDebit;

	/** The tipo subcuenta CU. */
    private String tipoSubcuentaCU;

    /**
     * Gets the codigo servicio.
     *
     * @return the codigoServicio
     */
    public String getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Sets the codigo servicio.
     *
     * @param codigoServicio
     *            the codigoServicio to set
     */
    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    /**
     * Gets the nombre servicio.
     *
     * @return the nombreServicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Sets the nombre servicio.
     *
     * @param nombreServicio
     *            the nombreServicio to set
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * Gets the numero partida.
     *
     * @return the numeroPartida
     */
    public String getNumeroPartida() {
        return numeroPartida;
    }

    /**
     * Sets the numero partida.
     *
     * @param numeroPartida
     *            the numeroPartida to set
     */
    public void setNumeroPartida(String numeroPartida) {
        this.numeroPartida = numeroPartida;
    }

    /**
     * Gets the estado cliente.
     *
     * @return the estadoCliente
     */
    public String getEstadoCliente() {
        return estadoCliente;
    }

    /**
     * Sets the estado cliente.
     *
     * @param estadoCliente
     *            the estadoCliente to set
     */
    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    /**
     * Gets the estado servicio.
     *
     * @return the estadoServicio
     */
    public String getEstadoServicio() {
        return estadoServicio;
    }

    /**
     * Sets the estado servicio.
     *
     * @param estadoServicio
     *            the estadoServicio to set
     */
    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    /**
     * Gets the fecha servicio.
     *
     * @return the fechaServicio
     */
    public Date getFechaServicio() {
        return fechaServicio == null ? null : new Date(fechaServicio.getTime());
    }

    /**
     * Sets the fecha servicio.
     *
     * @param fechaServicio
     *            the fechaServicio to set
     */
    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio == null ? null : new Date(fechaServicio.getTime());
    }

    /**
     * Gets the tipo fecha.
     *
     * @return the tipoFecha
     */
    public String getTipoFecha() {
        return tipoFecha;
    }

    /**
     * Sets the tipo fecha.
     *
     * @param tipoFecha
     *            the tipoFecha to set
     */
    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    /**
     * Gets the tipo stop debit.
     *
     * @return the tipoStopDebit
     */
    public String getTipoStopDebit() {
        return tipoStopDebit;
    }

    /**
     * Sets the tipo stop debit.
     *
     * @param tipoStopDebit
     *            the tipoStopDebit to set
     */
    public void setTipoStopDebit(String tipoStopDebit) {
        this.tipoStopDebit = tipoStopDebit;
    }

	/**
	 * Gets the tipo subcuenta CU.
	 *
	 * @return the tipo subcuenta CU
	 */   
    public String getTipoSubcuentaCU() {
        return tipoSubcuentaCU;
    }

    /**
     * Sets the tipo subcuenta CU.
     *
     * @param tipoSubcuentaCU the new tipo subcuenta CU
     */
    public void setTipoSubcuentaCU(String tipoSubcuentaCU) {
        this.tipoSubcuentaCU = tipoSubcuentaCU;
    }

    /**
     * Es producto adherido.
     *
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @return true, if successful
     */
    public boolean esProductoAdherido(String nroCuentaProducto) {
        String numeroPartidaDebitoFormateado = ISBANStringUtils.eliminarCeros(this.numeroPartida);
        String numeroCuentaProductoFormateado = ISBANStringUtils.eliminarCeros(nroCuentaProducto);

        return numeroPartidaDebitoFormateado.equals(numeroCuentaProductoFormateado);
    }

    /**
     * Tiene stop debit.
     *
     * @return true, if successful
     */
    public boolean tieneStopDebit() {
        return StringUtils.isNotBlank(this.tipoStopDebit);
    }

}
