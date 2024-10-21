/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author juan.pablo.picate
 *
 */
public class ImposicionPlazoFijoInEntity {

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant CUATRO_CEROS. */
	private static final String CUATRO_CEROS = "0000";

	/** The Constant DOCE_CEROS. */
	private static final String DOCE_CEROS = "000000000000";

	/** The Constant OCHO_CEROS. */
	private static final String OCHO_CEROS = "00000000";

	/** The Constant NUEVE_CEROS. */
	private static final String NUEVE_CEROS = "000000000";

	/** The Constant QUINCE_CEROS. */
	private static final String QUINCE_CEROS = "000000000000000";

	/** The Constant ESPACIOS_24. */
	private static final String ESPACIOS_24 = "                        ";

	/** numerico. */
	private String tipoCuenta;

	/** numerico. */
	private String sucursalCuentaDebito;

	/** numerico. */
	private String nroCuentaDebito;

	/** The producto. */
	private String producto;

	/** The subproducto. */
	private String subproducto;

	/** The entidad cuenta plazo. */
	private String entidadCuentaPlazo = CUATRO_ESPACIOS;

	/** numerico. */
	private String sucursalCuentaPlazo = CUATRO_CEROS;

	/** numerico. */
	private String nroCuentaPlazo = DOCE_CEROS;

	/** AAAAMMDD. */
	private String fechaAlta;

	/** numerico. */
	private String plazo;

	/** 13 enteros, 2 decimales. */
	private String importeCertificado;

	/** The divisa. */
	private String divisa;

	/** 3 enteros, 5 decimales. */
	private String tasa;

	/** The codigo tarifa. */
	private String codigoTarifa;

	/** The codigo tarifa renovacion. */
	private String codigoTarifaRenovacion = CUATRO_ESPACIOS;

	/** The indicador renovacion. */
	private String indicadorRenovacion;

	/** The indicador capitaliza intereses. */
	private String indicadorCapitalizaIntereses;

	/** The periodo liquidacion. */
	private String periodoLiquidacion;

	/** 3 enteros, 5 decimales. */
	private String spreadRenovacion = OCHO_CEROS;

	/** The indicador certificado transferencia. */
	private String indicadorCertificadoTransferencia = "N";

	/** The sucursal cuenta debito 2. */
	private String sucursalCuentaDebito2;

	/** The responsable impuesto. */
	private String responsableImpuesto = "B";

	/** The forma pago. */
	private String formaPago = "C";

	/** numerico. */
	private String secuencia = NUEVE_CEROS;

	/** The forma cancel. */
	private String formaCancel = " ";

	/** 13 enteros, 2 decimales. */
	private String importeDebitoReversa = QUINCE_CEROS;

	/** The tipo PF. */
	private String tipoPF;

	/** The nio. */
	private String nio = ESPACIOS_24;

	/** numerico. */
	private String cantidadDias;

	/** 3 enteros, 5 decimales. */
	private String tasaVariable;

	/** 3 enteros, 5 decimales. */
	private String spread = OCHO_CEROS;

	/** The signo spread. */
	private String signoSpread = " ";

	/** The tipo dia liq variable. */
	private String tipoDiaLiqVariable = "   ";

	/** numerico. */
	private String numDiaLiqVariable = "00";
	
    /** 13 enteros, 4 decimales. */
    private String saldoInicUr;
    
    /** 5 enteros, 8 decimales. */
    private String cotizacionCodigoUr;
    
    private String nroContratoAltair;

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
	 * Gets the entidad cuenta plazo.
	 *
	 * @return the entidadCuentaPlazo
	 */
	public String getEntidadCuentaPlazo() {
		return entidadCuentaPlazo;
	}

	/**
	 * Sets the entidad cuenta plazo.
	 *
	 * @param entidadCuentaPlazo
	 *            the entidadCuentaPlazo to set
	 */
	public void setEntidadCuentaPlazo(String entidadCuentaPlazo) {
		this.entidadCuentaPlazo = entidadCuentaPlazo;
	}

	/**
	 * Gets the sucursal cuenta plazo.
	 *
	 * @return the sucursalCuentaPlazo
	 */
	public String getSucursalCuentaPlazo() {
		return sucursalCuentaPlazo;
	}

	/**
	 * Sets the sucursal cuenta plazo.
	 *
	 * @param sucursalCuentaPlazo
	 *            the sucursalCuentaPlazo to set
	 */
	public void setSucursalCuentaPlazo(String sucursalCuentaPlazo) {
		this.sucursalCuentaPlazo = sucursalCuentaPlazo;
	}

	/**
	 * Gets the nro cuenta plazo.
	 *
	 * @return the nroCuentaPlazo
	 */
	public String getNroCuentaPlazo() {
		return nroCuentaPlazo;
	}

	/**
	 * Sets the nro cuenta plazo.
	 *
	 * @param nroCuentaPlazo
	 *            the nroCuentaPlazo to set
	 */
	public void setNroCuentaPlazo(String nroCuentaPlazo) {
		this.nroCuentaPlazo = nroCuentaPlazo;
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
	public String getImporteCertificado() {
		return importeCertificado;
	}

	/**
	 * Sets the importe certificado.
	 *
	 * @param importeCertificado
	 *            the importeCertificado to set
	 */
	public void setImporteCertificado(String importeCertificado) {
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
	 * Gets the codigo tarifa renovacion.
	 *
	 * @return the codigoTarifaRenovacion
	 */
	public String getCodigoTarifaRenovacion() {
		return codigoTarifaRenovacion;
	}

	/**
	 * Sets the codigo tarifa renovacion.
	 *
	 * @param codigoTarifaRenovacion
	 *            the codigoTarifaRenovacion to set
	 */
	public void setCodigoTarifaRenovacion(String codigoTarifaRenovacion) {
		this.codigoTarifaRenovacion = codigoTarifaRenovacion;
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
	 * Gets the spread renovacion.
	 *
	 * @return the spreadRenovacion
	 */
	public String getSpreadRenovacion() {
		return spreadRenovacion;
	}

	/**
	 * Sets the spread renovacion.
	 *
	 * @param spreadRenovacion
	 *            the spreadRenovacion to set
	 */
	public void setSpreadRenovacion(String spreadRenovacion) {
		this.spreadRenovacion = spreadRenovacion;
	}

	/**
	 * Gets the indicador certificado transferencia.
	 *
	 * @return the indicadorCertificadoTransferencia
	 */
	public String getIndicadorCertificadoTransferencia() {
		return indicadorCertificadoTransferencia;
	}

	/**
	 * Sets the indicador certificado transferencia.
	 *
	 * @param indicadorCertificadoTransferencia
	 *            the indicadorCertificadoTransferencia to set
	 */
	public void setIndicadorCertificadoTransferencia(String indicadorCertificadoTransferencia) {
		this.indicadorCertificadoTransferencia = indicadorCertificadoTransferencia;
	}

	/**
	 * Gets the sucursal cuenta debito 2.
	 *
	 * @return the sucursalCuentaDebito2
	 */
	public String getSucursalCuentaDebito2() {
		return sucursalCuentaDebito2;
	}

	/**
	 * Sets the sucursal cuenta debito 2.
	 *
	 * @param sucursalCuentaDebito2
	 *            the sucursalCuentaDebito2 to set
	 */
	public void setSucursalCuentaDebito2(String sucursalCuentaDebito2) {
		this.sucursalCuentaDebito2 = sucursalCuentaDebito2;
	}

	/**
	 * Gets the responsable impuesto.
	 *
	 * @return the responsableImpuesto
	 */
	public String getResponsableImpuesto() {
		return responsableImpuesto;
	}

	/**
	 * Sets the responsable impuesto.
	 *
	 * @param responsableImpuesto
	 *            the responsableImpuesto to set
	 */
	public void setResponsableImpuesto(String responsableImpuesto) {
		this.responsableImpuesto = responsableImpuesto;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the forma cancel.
	 *
	 * @return the formaCancel
	 */
	public String getFormaCancel() {
		return formaCancel;
	}

	/**
	 * Sets the forma cancel.
	 *
	 * @param formaCancel
	 *            the formaCancel to set
	 */
	public void setFormaCancel(String formaCancel) {
		this.formaCancel = formaCancel;
	}

	/**
	 * Gets the importe debito reversa.
	 *
	 * @return the importeDebitoReversa
	 */
	public String getImporteDebitoReversa() {
		return importeDebitoReversa;
	}

	/**
	 * Sets the importe debito reversa.
	 *
	 * @param importeDebitoReversa
	 *            the importeDebitoReversa to set
	 */
	public void setImporteDebitoReversa(String importeDebitoReversa) {
		this.importeDebitoReversa = importeDebitoReversa;
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
	 * Gets the nio.
	 *
	 * @return the nIO
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the new nio
	 */
	public void setNio(String nio) {
		this.nio = nio;
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
	 * Gets the spread.
	 *
	 * @return the spread
	 */
	public String getSpread() {
		return spread;
	}

	/**
	 * Sets the spread.
	 *
	 * @param spread
	 *            the spread to set
	 */
	public void setSpread(String spread) {
		this.spread = spread;
	}

	/**
	 * Gets the signo spread.
	 *
	 * @return the signoSpread
	 */
	public String getSignoSpread() {
		return signoSpread;
	}

	/**
	 * Sets the signo spread.
	 *
	 * @param signoSpread
	 *            the signoSpread to set
	 */
	public void setSignoSpread(String signoSpread) {
		this.signoSpread = signoSpread;
	}

	/**
	 * Gets the tipo dia liq variable.
	 *
	 * @return the tipoDiaLiqVariable
	 */
	public String getTipoDiaLiqVariable() {
		return tipoDiaLiqVariable;
	}

	/**
	 * Sets the tipo dia liq variable.
	 *
	 * @param tipoDiaLiqVariable
	 *            the tipoDiaLiqVariable to set
	 */
	public void setTipoDiaLiqVariable(String tipoDiaLiqVariable) {
		this.tipoDiaLiqVariable = tipoDiaLiqVariable;
	}

	/**
	 * Gets the num dia liq variable.
	 *
	 * @return the numDiaLiqVariable
	 */
	public String getNumDiaLiqVariable() {
		return numDiaLiqVariable;
	}

	/**
	 * Sets the num dia liq variable.
	 *
	 * @param numDiaLiqVariable
	 *            the numDiaLiqVariable to set
	 */
	public void setNumDiaLiqVariable(String numDiaLiqVariable) {
		this.numDiaLiqVariable = numDiaLiqVariable;
	}
	
    /**
     * Gets the saldo inic ur.
     * 
     * @return the saldoInicUr
     */
    public String getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
     * Sets the saldo inic ur.
     * 
     * @param saldoInicUr the saldoInicUr to set
     */
    public void setSaldoInicUr(String saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
     * Gets the cotizacion codigo ur.
     * 
     * @return the cotizacionCodigoUr
     */
    public String getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
     * Sets the cotizacion codigo ur.
     * 
     * @param cotizacionCodigoUr the cotizacionCodigoUr to set
     */
    public void setCotizacionCodigoUr(String cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
    }

	public String getNroContratoAltair() {
		return nroContratoAltair;
	}

	public void setNroContratoAltair(String nroContratoAltair) {
		this.nroContratoAltair = nroContratoAltair;
	}
}
