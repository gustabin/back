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
public class SimulacionPlazoFijoInEntity {

	/** The Constant DOS_BLANCO. */
	private static final String DOS_BLANCO = "  ";

	/** The Constant CUATRO_BLANCO. */
	private static final String CUATRO_BLANCO = "    ";

	/** The Constant UN_BLANCO. */
	private static final String UN_BLANCO = " ";

	/** The Constant CUATRO_CEROS. */
	private static final String CUATRO_CEROS = "0000";

	/** The Constant NUEVE_CEROS. */
	private static final String NUEVE_CEROS = "000000000";

	/** The Constant DOCE_CEROS. */
	private static final String DOCE_CEROS = "000000000000";

	/** The Constant CARACTER_N. */
	private static final String CARACTER_N = "N";

	/** The Constant CARACTER_B. */
	private static final String CARACTER_B = "B";

	/** The Constant SIGNO. */
	private static final String SIGNO = "+";

	/** The Constant CADENA_IF. */
	private static final String CADENA_IF = "IF ";

	/** The Constant NUMERO_05. */
	private static final String NUMERO_05 = "05";

    /** The Constant CANAL. */
    private static final String CANAL = "04";
        
    /** The Constant CARACTER_S. */
    private static final String CARACTER_S = "S";
	
	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** numerico. */
	private String sucursalCuentaDebito;

	/** numerico. */
	private String numeroCuentaDebito;

	/** The producto. */
	private String producto = DOS_BLANCO;

	/** The subproducto. */
	private String subproducto = CUATRO_BLANCO;

	/** The entidad cuenta plazo. */
	private String entidadCuentaPlazo = CUATRO_BLANCO;

	/** numerico. */
	private String sucursalCuentaPlazo = CUATRO_CEROS;

	/** numerico. */
	private String cuentaPlazo = DOCE_CEROS;

	/** AAAAMMDD. */
	private String fechaAlta;

	/** numerico. */
	private String plazo;

	/** 13 enteros, 2 decimales. */
	private String importePlazoFijo;

	/** The divisa. */
	private String divisa;

	/** 4 enteros, 5 decimales. */
	private String tasa = NUEVE_CEROS;

	/** The tarifa. */
	private String tarifa = CUATRO_BLANCO;

	/** The periodo liquidacion. */
	private String periodoLiquidacion = UN_BLANCO;

	/** The indicador transferible. */
	private String indicadorTransferible = CARACTER_N;

	/** The sucursal radicacion. */
	private String sucursalRadicacion;

	/** The resp impuesto. */
	private String respImpuesto = CARACTER_B;

	/** The tipo PF. */
	private String tipoPF = DOS_BLANCO;

	/** numerico. */
	private String cantidadDias;

	/** 4 enteros, 5 decimales. */
	private String tasaVariable = NUEVE_CEROS;

	/** 4 enteros, 5 decimales. */
	private String spread = NUEVE_CEROS;

	/** The spread signo. */
	private String spreadSigno = SIGNO;

	/** The tipo dia liq variable. */
	private String tipoDiaLiqVariable = CADENA_IF;

	/** numerico. */
	private String numDiaLiqVariable = NUMERO_05;
	
    /** The canal. */
    private String canal = CANAL;
    
    /** The simulacion original. */
    private String simulacionOriginal = CARACTER_S;
    
    private String nroContratoAltair;

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipoCuentaDebito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the tipoCuentaDebito to set
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
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
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
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
	 * Gets the cuenta plazo.
	 *
	 * @return the cuentaPlazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the cuentaPlazo to set
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
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
	 * Gets the importe plazo fijo.
	 *
	 * @return the importePlazoFijo
	 */
	public String getImportePlazoFijo() {
		return importePlazoFijo;
	}

	/**
	 * Sets the importe plazo fijo.
	 *
	 * @param importePlazoFijo
	 *            the importePlazoFijo to set
	 */
	public void setImportePlazoFijo(String importePlazoFijo) {
		this.importePlazoFijo = importePlazoFijo;
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
	 * Gets the tarifa.
	 *
	 * @return the tarifa
	 */
	public String getTarifa() {
		return tarifa;
	}

	/**
	 * Sets the tarifa.
	 *
	 * @param tarifa
	 *            the tarifa to set
	 */
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
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
	 * Gets the indicador transferible.
	 *
	 * @return the indicadorTransferible
	 */
	public String getIndicadorTransferible() {
		return indicadorTransferible;
	}

	/**
	 * Sets the indicador transferible.
	 *
	 * @param indicadorTransferible
	 *            the indicadorTransferible to set
	 */
	public void setIndicadorTransferible(String indicadorTransferible) {
		this.indicadorTransferible = indicadorTransferible;
	}

	/**
	 * Gets the sucursal radicacion.
	 *
	 * @return the sucursalRadicacion
	 */
	public String getSucursalRadicacion() {
		return sucursalRadicacion;
	}

	/**
	 * Sets the sucursal radicacion.
	 *
	 * @param sucursalRadicacion
	 *            the sucursalRadicacion to set
	 */
	public void setSucursalRadicacion(String sucursalRadicacion) {
		this.sucursalRadicacion = sucursalRadicacion;
	}

	/**
	 * Gets the resp impuesto.
	 *
	 * @return the respImpuesto
	 */
	public String getRespImpuesto() {
		return respImpuesto;
	}

	/**
	 * Sets the resp impuesto.
	 *
	 * @param respImpuesto
	 *            the respImpuesto to set
	 */
	public void setRespImpuesto(String respImpuesto) {
		this.respImpuesto = respImpuesto;
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
	 * Gets the spread signo.
	 *
	 * @return the spreadSigno
	 */
	public String getSpreadSigno() {
		return spreadSigno;
	}

	/**
	 * Sets the spread signo.
	 *
	 * @param spreadSigno
	 *            the spreadSigno to set
	 */
	public void setSpreadSigno(String spreadSigno) {
		this.spreadSigno = spreadSigno;
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
     * Gets the canal.
     * 
     * @return the canal
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the canal.
     *  
     * @param canal the canal to set
     */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
     * Gets the simulacion original.
     * 
     * @return the simulacionOriginal
     */
    public String getSimulacionOriginal() {
        return simulacionOriginal;
    }

    /**
     * Sets the simulacion original. 
     * 
     * @param simulacionOriginal the simulacionOriginal to set
     */
    public void setSimulacionOriginal(String simulacionOriginal) {
        this.simulacionOriginal = simulacionOriginal;
    }

	public String getNroContratoAltair() {
		return nroContratoAltair;
	}

	public void setNroContratoAltair(String nroContratoAltair) {
		this.nroContratoAltair = nroContratoAltair;
	}

}
