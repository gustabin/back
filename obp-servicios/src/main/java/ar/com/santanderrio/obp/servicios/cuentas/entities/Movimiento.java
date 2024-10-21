/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class Movimiento.
 */
public class Movimiento {

    /** The fecha movimiento. */
    private String fechaMovimiento;

    /** The hora movimiento. */
    private String horaMovimiento;

    /** The numero comprobante. */
    private String numeroComprobante;

    /** The clase movimiento. */
    private String claseMovimiento;

    /** The importe movimiento. */
    private String importeMovimiento;

    /** The descripcion movimiento. */
    private String descripcionMovimiento;

    /** The descripcion adicional movimiento. */
    private String descripcionAdicionalMovimiento;

    /** The sucursal origen. */
    private String sucursalOrigen;

    /** The moneda movimiento. */
    private String monedaMovimiento;

    /** The saldo cuenta. */
    private String saldoCuenta;

    /** The codigo movimiento. */
    private String codigoMovimiento;

    /** The sub codigo movimiento. */
    private String subCodigoMovimiento;

    /** The fecha valor. */
    private String fechaValor;

    /** The codigo alta ir. */
    private String codigoAltaIr;

    /** The indicador oberservacion. */
    private String indicadorOberservacion;

    /** The observacion. */
    private String observacion;

    /** The indicador movimiento. */
    private String indicadorMovimiento;

    /** The canal. */
    private String canal;

    /** The referencia interna. */
    /*
     * AUX
     */
    private String referenciaInterna;

    /** The indicador movimiento automatico. */
    private String indicadorMovimientoAutomatico;

    /** The usuario. */
    private String usuario;
    
    /** The tipo consulta. */
    private TipoConsultaMovimientos tipoConsulta;

    /**
     * Gets the clase movimiento.
     *
     * @return the clase movimiento
     */
    public String getClaseMovimiento() {
        return claseMovimiento;
    }

    /**
     * Setter para clase movimiento.
     *
     * @param claseMovimiento
     *            el nuevo clase movimiento
     */
    public void setClaseMovimiento(String claseMovimiento) {
        this.claseMovimiento = claseMovimiento;
    }

    /**
     * Gets the fecha movimiento.
     *
     * @return the fecha movimiento
     */
    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    /**
     * Setter para fecha movimiento.
     *
     * @param fechaMovimiento
     *            el nuevo fecha movimiento
     */
    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    /**
     * Gets the hora movimiento.
     *
     * @return the hora movimiento
     */
    public String getHoraMovimiento() {
        return horaMovimiento;
    }

    /**
     * Setter para hora movimiento.
     *
     * @param horaMovimiento
     *            el nuevo hora movimiento
     */
    public void setHoraMovimiento(String horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
    }

    /**
     * Gets the numero comprobante.
     *
     * @return the numero comprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Setter para numero comprobante.
     *
     * @param numeroComprobante
     *            el nuevo numero comprobante
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * Gets the importe movimiento.
     *
     * @return the importe movimiento
     */
    public String getImporteMovimiento() {
        return importeMovimiento;
    }

    /**
     * Setter para importe movimiento.
     *
     * @param importeMovimiento
     *            el nuevo importe movimiento
     */
    public void setImporteMovimiento(String importeMovimiento) {
        this.importeMovimiento = importeMovimiento;
    }

    /**
     * Gets the descripcion movimiento.
     *
     * @return the descripcion movimiento
     */
    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    /**
     * Setter para descripcion movimiento.
     *
     * @param descripcionMovimiento
     *            el nuevo descripcion movimiento
     */
    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    /**
     * Gets the descripcion adicional movimiento.
     *
     * @return the descripcion adicional movimiento
     */
    public String getDescripcionAdicionalMovimiento() {
        return descripcionAdicionalMovimiento;
    }

    /**
     * Setter para descripcion adicional movimiento.
     *
     * @param descripcionAdicionalMovimiento
     *            el nuevo descripcion adicional movimiento
     */
    public void setDescripcionAdicionalMovimiento(String descripcionAdicionalMovimiento) {
        this.descripcionAdicionalMovimiento = descripcionAdicionalMovimiento;
    }

    /**
     * Gets the sucursal origen.
     *
     * @return the sucursal origen
     */
    public String getSucursalOrigen() {
        return sucursalOrigen;
    }

    /**
     * Setter para sucursal origen.
     *
     * @param sucursalOrigen
     *            el nuevo sucursal origen
     */
    public void setSucursalOrigen(String sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }

    /**
     * Gets the moneda movimiento.
     *
     * @return the moneda movimiento
     */
    public String getMonedaMovimiento() {
        return monedaMovimiento;
    }

    /**
     * Setter para moneda movimiento.
     *
     * @param monedaMovimiento
     *            el nuevo moneda movimiento
     */
    public void setMonedaMovimiento(String monedaMovimiento) {
        this.monedaMovimiento = monedaMovimiento;
    }

    /**
     * Gets the saldo cuenta.
     *
     * @return the saldo cuenta
     */
    public String getSaldoCuenta() {
        return saldoCuenta;
    }

    /**
     * Setter para saldo cuenta.
     *
     * @param saldoCuenta
     *            el nuevo saldo cuenta
     */
    public void setSaldoCuenta(String saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    /**
     * Gets the codigo movimiento.
     *
     * @return the codigo movimiento
     */
    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }

    /**
     * Setter para codigo movimiento.
     *
     * @param codigoMovimiento
     *            el nuevo codigo movimiento
     */
    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    /**
     * Gets the sub codigo movimiento.
     *
     * @return the sub codigo movimiento
     */
    public String getSubCodigoMovimiento() {
        return subCodigoMovimiento;
    }

    /**
     * Setter para sub codigo movimiento.
     *
     * @param subCodigoMovimiento
     *            el nuevo sub codigo movimiento
     */
    public void setSubCodigoMovimiento(String subCodigoMovimiento) {
        this.subCodigoMovimiento = subCodigoMovimiento;
    }

    /**
     * Gets the fecha valor.
     *
     * @return the fecha valor
     */
    public String getFechaValor() {
        return fechaValor;
    }

    /**
     * Setter para fecha valor.
     *
     * @param fechaValor
     *            el nuevo fecha valor
     */
    public void setFechaValor(String fechaValor) {
        this.fechaValor = fechaValor;
    }

    /**
     * Gets the codigo alta ir.
     *
     * @return the codigo alta ir
     */
    public String getCodigoAltaIr() {
        return codigoAltaIr;
    }

    /**
     * Setter para codigo alta ir.
     *
     * @param codigoAltaIr
     *            el nuevo codigo alta ir
     */
    public void setCodigoAltaIr(String codigoAltaIr) {
        this.codigoAltaIr = codigoAltaIr;
    }

    /**
     * Gets the indicador oberservacion.
     *
     * @return the indicador oberservacion
     */
    public String getIndicadorOberservacion() {
        return indicadorOberservacion;
    }

    /**
     * Setter para indicador oberservacion.
     *
     * @param indicadorOberservacion
     *            el nuevo indicador oberservacion
     */
    public void setIndicadorOberservacion(String indicadorOberservacion) {
        this.indicadorOberservacion = indicadorOberservacion;
    }

    /**
     * Gets the observacion.
     *
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Setter para observacion.
     *
     * @param observacion
     *            el nuevo observacion
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Gets the indicador movimiento.
     *
     * @return the indicador movimiento
     */
    public String getIndicadorMovimiento() {
        return indicadorMovimiento;
    }

    /**
     * Setter para indicador movimiento.
     *
     * @param indicadorMovimiento
     *            el nuevo indicador movimiento
     */
    public void setIndicadorMovimiento(String indicadorMovimiento) {
        this.indicadorMovimiento = indicadorMovimiento;
    }

    /**
     * Gets the canal.
     *
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Setter para canal.
     *
     * @param canal
     *            el nuevo canal
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Gets the referencia interna.
     *
     * @return the referencia interna
     */
    public String getReferenciaInterna() {
        return referenciaInterna;
    }

    /**
     * Setter para referencia interna.
     *
     * @param referenciaInterna
     *            el nuevo referencia interna
     */
    public void setReferenciaInterna(String referenciaInterna) {
        this.referenciaInterna = referenciaInterna;
    }

    /**
     * Gets the indicador movimiento automatico.
     *
     * @return the indicador movimiento automatico
     */
    public String getIndicadorMovimientoAutomatico() {
        return indicadorMovimientoAutomatico;
    }

    /**
     * Setter para indicador movimiento automatico.
     *
     * @param indicadorMovimientoAutomatico
     *            el nuevo indicador movimiento automatico
     */
    public void setIndicadorMovimientoAutomatico(String indicadorMovimientoAutomatico) {
        this.indicadorMovimientoAutomatico = indicadorMovimientoAutomatico;
    }

    /**
     * Gets the usuario.
     *
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Setter para usuario.
     *
     * @param usuario
     *            el nuevo usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
	 * Gets the tipo consulta.
	 *
	 * @return the tipo consulta
	 */
    public TipoConsultaMovimientos getTipoConsulta() {
        return tipoConsulta;
    }

    /**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta
	 *            the new tipo consulta
	 */
    public void setTipoConsulta(TipoConsultaMovimientos tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
    /**
	 * Gets the del dia.
	 *
	 * @return the del dia
	 */
    public Boolean getDelDia() {
        return TipoConsultaMovimientos.MOVIMIENTOS_DEL_DIA.equals(this.getTipoConsulta());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("fechaMovimiento", fechaMovimiento).append("horaMovimiento", horaMovimiento)
                .append("numeroComprobante", numeroComprobante).append("claseMovimiento", claseMovimiento)
                .append("importeMovimiento", importeMovimiento).append("descripcionMovimiento", descripcionMovimiento)
                .append("descripcionAdicionalMovimiento", descripcionAdicionalMovimiento)
                .append("sucursalOrigen", sucursalOrigen).append("monedaMovimiento", monedaMovimiento)
                .append("saldoCuenta", saldoCuenta).append("codigoMovimiento", codigoMovimiento)
                .append("subCodigoMovimiento", subCodigoMovimiento).append("fechaValor", fechaValor)
                .append("codigoAltaIr", codigoAltaIr).append("indicadorOberservacion", indicadorOberservacion)
                .append("observacion", observacion).append("indicadorMovimiento", indicadorMovimiento)
                .append("canal", canal).append("referenciaInterna", referenciaInterna)
                .append("indicadorMovimientoAutomatico", indicadorMovimientoAutomatico).append("usuario", usuario)
                .append("tipoConsulta", tipoConsulta);
        return builder.toString();
    }
    
    

}
