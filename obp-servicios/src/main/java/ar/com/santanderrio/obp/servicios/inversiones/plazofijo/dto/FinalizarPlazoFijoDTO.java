/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.math.BigDecimal;

/**
 * The Class FinalizarPlazoFijoDTO.
 */
public class FinalizarPlazoFijoDTO {

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The nro cuenta debito. */
	private String nroCuentaDebito;

	/** The producto. */
	private String producto;

	/** The subproducto. */
	private String subproducto;

	/** The fecha alta. */
	private String fechaAlta;

	/** The plazo. */
	private String plazo;

	/** The importe certificado. */
	private BigDecimal importeCertificado;

	/** The divisa. */
	private String divisa;

	/** The tasa. */
	private String tasa;

	/** The codigo tarifa. */
	private String codigoTarifa;

	/** The accion al vencimiento. */
	private String accionAlVencimiento;

	/** The periodo liquidacion. */
	private String periodoLiquidacion;

	/** The tipo PF. */
	private String tipoPF;

	/** The cantidad dias. */
	private String cantidadDias;

	/** The tasa variable. */
	private String tasaVariable;

	/** The nombre plazo fijo. */
	private String nombrePlazoFijo;

	/** The indicador renovacion. */
	private String indicadorRenovacion;

	/** The indicador capitaliza intereses. */
	private String indicadorCapitalizaIntereses;

	/** The mensaje. */
	private String mensaje;

	/** The numero Comprobante. */
	private String numeroComprobante;

	/** The fecha Constitucion. */
	private String fechaConstitucion;

	/** The minimo dias Precancelar. */
	private String minimoDiasPrecancelar;
	
    /** The importe saldo inic ur. */
    private BigDecimal saldoInicUr;
    
    /** The importe cotizacion codigo ur. */
    private BigDecimal cotizacionCodigoUr;
    
    /** The tasa efectiva. */
    private BigDecimal tasaEfectiva;
    
    /** The descripcion accion vencimiento. */
    private String descripcionAccionVencimiento;

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the subproducto.
	 *
	 * @return the subproducto
	 */
	public String getSubproducto() {
		return subproducto;
	}

	/**
	 * Sets the subproducto.
	 *
	 * @param subproducto
	 *            the subproducto to set
	 */
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the tasa.
	 *
	 * @return the tasa
	 */
	public String getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 *
	 * @param tasa
	 *            the tasa to set
	 */
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the codigo tarifa.
	 *
	 * @return the codigoTarifa
	 */
	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	/**
	 * Sets the codigo tarifa.
	 *
	 * @param codigoTarifa
	 *            the codigoTarifa to set
	 */
	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	/**
	 * Gets the periodo liquidacion.
	 *
	 * @return the periodoLiquidacion
	 */
	public String getPeriodoLiquidacion() {
		return periodoLiquidacion;
	}

	/**
	 * Sets the periodo liquidacion.
	 *
	 * @param periodoLiquidacion
	 *            the periodoLiquidacion to set
	 */
	public void setPeriodoLiquidacion(String periodoLiquidacion) {
		this.periodoLiquidacion = periodoLiquidacion;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipoPF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the tipoPF to set
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * Gets the tasa variable.
	 *
	 * @return the tasaVariable
	 */
	public String getTasaVariable() {
		return tasaVariable;
	}

	/**
	 * Sets the tasa variable.
	 *
	 * @param tasaVariable
	 *            the tasaVariable to set
	 */
	public void setTasaVariable(String tasaVariable) {
		this.tasaVariable = tasaVariable;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursalCuentaDebito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the sucursalCuentaDebito to set
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the accion al vencimiento.
	 *
	 * @return the accionAlVencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * Sets the accion al vencimiento.
	 *
	 * @param accionAlVencimiento
	 *            the accionAlVencimiento to set
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the importe certificado.
	 *
	 * @return the importeCertificado
	 */
	public BigDecimal getImporteCertificado() {
		return importeCertificado;
	}

	/**
	 * Sets the importe certificado.
	 *
	 * @param importeCertificado
	 *            the importeCertificado to set
	 */
	public void setImporteCertificado(BigDecimal importeCertificado) {
		this.importeCertificado = importeCertificado;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nroCuentaDebito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the nroCuentaDebito to set
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Gets the cantidad dias.
	 *
	 * @return the cantidadDias
	 */
	public String getCantidadDias() {
		return cantidadDias;
	}

	/**
	 * Sets the cantidad dias.
	 *
	 * @param cantidadDias
	 *            the cantidadDias to set
	 */
	public void setCantidadDias(String cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	/**
	 * Gets the indicador renovacion.
	 *
	 * @return the indicadorRenovacion
	 */
	public String getIndicadorRenovacion() {
		return indicadorRenovacion;
	}

	/**
	 * Sets the indicador renovacion.
	 *
	 * @param indicadorRenovacion
	 *            the indicadorRenovacion to set
	 */
	public void setIndicadorRenovacion(String indicadorRenovacion) {
		this.indicadorRenovacion = indicadorRenovacion;
	}

	/**
	 * Gets the indicador capitaliza intereses.
	 *
	 * @return the indicadorCapitalizaIntereses
	 */
	public String getIndicadorCapitalizaIntereses() {
		return indicadorCapitalizaIntereses;
	}

	/**
	 * Sets the indicador capitaliza intereses.
	 *
	 * @param indicadorCapitalizaIntereses
	 *            the indicadorCapitalizaIntereses to set
	 */
	public void setIndicadorCapitalizaIntereses(String indicadorCapitalizaIntereses) {
		this.indicadorCapitalizaIntereses = indicadorCapitalizaIntereses;
	}

	/**
	 * Gets the nombre plazo fijo.
	 *
	 * @return the nombrePlazoFijo
	 */
	public String getNombrePlazoFijo() {
		return nombrePlazoFijo;
	}

	/**
	 * Sets the nombre plazo fijo.
	 *
	 * @param nombrePlazoFijo
	 *            the nombrePlazoFijo to set
	 */
	public void setNombrePlazoFijo(String nombrePlazoFijo) {
		this.nombrePlazoFijo = nombrePlazoFijo;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha constitucion.
	 *
	 * @return the fecha constitucion
	 */
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}

	/**
	 * Sets the fecha constitucion.
	 *
	 * @param fechaConstitucion
	 *            the new fecha constitucion
	 */
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	/**
	 * Gets the minimo dias precancelar.
	 *
	 * @return the minimo dias precancelar
	 */
	public String getMinimoDiasPrecancelar() {
		return minimoDiasPrecancelar;
	}

	/**
	 * Sets the minimo dias precancelar.
	 *
	 * @param minimoDiasPrecancelar
	 *            the new minimo dias precancelar
	 */
	public void setMinimoDiasPrecancelar(String minimoDiasPrecancelar) {
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
	}
	
	   /**
     * Gets the saldo inic ur.
     * 
     * @return the saldoInicUr
     */
    public BigDecimal getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
     * Sets the saldo inic ur. 
     * 
     * @param saldoInicUr the saldoInicUr to set
     */
    public void setSaldoInicUr(BigDecimal saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
     * Gets the cotizacion codigo ur.
     * 
     * @return the cotizacionCodigoUr
     */
    public BigDecimal getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
     * Sets the cotizacion codigo ur.
     * 
     * @param cotizacionCodigoUr the cotizacionCodigoUr to set
     */
    public void setCotizacionCodigoUr(BigDecimal cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
    }

    /**
	 * Gets the tasa efectiva.
	 *
	 * @return the tasaEfectiva
	 */
    public BigDecimal getTasaEfectiva() {
        return tasaEfectiva;
    }

    /**
	 * Sets the tasa efectiva.
	 *
	 * @param tasaEfectiva
	 *            the tasaEfectiva to set
	 */
    public void setTasaEfectiva(BigDecimal tasaEfectiva) {
        this.tasaEfectiva = tasaEfectiva;
    }

    /**
	 * Gets the descripcion accion vencimiento.
	 *
	 * @return the descripcionAccionVencimiento
	 */
    public String getDescripcionAccionVencimiento() {
        return descripcionAccionVencimiento;
    }

    /**
	 * Sets the descripcion accion vencimiento.
	 *
	 * @param descripcionAccionVencimiento
	 *            the descripcionAccionVencimiento to set
	 */
    public void setDescripcionAccionVencimiento(String descripcionAccionVencimiento) {
        this.descripcionAccionVencimiento = descripcionAccionVencimiento;
    }
    

}
