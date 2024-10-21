/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultarCondicionesImpuestosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.ConsultarCondicionesInteresanteEntity;

/**
 * The Class CondicionesDTO.
 */
public class CondicionesDTO {

	/** The Constant CERO. */
	private static final double CERO_DOUBLE = 0.00;

	/** The Constant CIEN. */
	private static final double CIEN_DOUBLE = 100;

	/** The Constant TRES_SEIS_CINCO. */
	private static final double TRES_SEIS_CINCO_DOUBLE = 365;

	/** The Constant UNO_DOUBLE. */
	private static final double UNO_DOUBLE = 1;

	/** The Constant OCHO. */
	private static final int OCHO_INT = 8;

	/** The importe. */
	private BigDecimal importe;

	/** The nombre plazo fijo. */
	private String nombrePlazoFijo;

	/** The plazo vencimiento. */
	private BigDecimal plazoVencimiento;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The descripcion PF. */
	private String descripcionPF;

	/** The intereses. */
	private BigDecimal intereses;

	/** The tna. */
	private BigDecimal tna;

	/** The tena. */
	private BigDecimal tena;

	/** The informacion. */
	private String informacion;

	/** The tarifa. */
	private String tarifa;

	/** The tasa variable. */
	private String tasaVariable;

	/** The cantidad de dias. */
	private String cantidadDeDias;

	/** The tipo PF. */
	private String tipoPF;

	/** The fecha alta real. */
	private String fechaAltaReal;

	/** The ini. */
	private BigDecimal ini;

	/** The mostrar fecuencia intereses. */
	private String mostrarFecuenciaIntereses;

	/** The impuestos PF. */
	private List<ConsultarCondicionesImpuestosEntity> impuestosPF = new ArrayList<ConsultarCondicionesImpuestosEntity>();

	/** The interesante PF. */
	List<ConsultarCondicionesInteresanteEntity> interesantePF = new ArrayList<ConsultarCondicionesInteresanteEntity>();

	/** The producto. */
	private String producto;

	/** The subproducto. */
	private String subproducto;

	/** The periodo liquidacion. */
	private String periodoLiquidacion;

	/** The fechaMinimaPrecancelar. */
	private String fechaMinimaPrecancelar;

	/** The porcentajePenalizacion. */
	private String porcentajePenalizacion;

	/** The prioridad. */
	private String prioridad;

    /** The saldo inic ur. */
    private BigDecimal saldoInicUr;
    
    /** The cotizacion codigo ur. */
    private BigDecimal cotizacionCodigoUr;
	
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
	 * Gets the plazo vencimiento.
	 *
	 * @return the plazo vencimiento
	 */
	public BigDecimal getPlazoVencimiento() {
		return plazoVencimiento;
	}

	/**
	 * Sets the plazo vencimiento.
	 *
	 * @param plazoVencimiento
	 *            the new plazo vencimiento
	 */
	public void setPlazoVencimiento(BigDecimal plazoVencimiento) {
		this.plazoVencimiento = plazoVencimiento;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the descripcion PF.
	 *
	 * @return the descripcionPF
	 */
	public String getDescripcionPF() {
		return descripcionPF;
	}

	/**
	 * Sets the descripcion PF.
	 *
	 * @param descripcionPF
	 *            the descripcionPF to set
	 */
	public void setDescripcionPF(String descripcionPF) {
		this.descripcionPF = descripcionPF;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public BigDecimal getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the tna
	 * @param plazoVencimiento
	 *            the plazo vencimiento
	 */
	public void setTna(BigDecimal tna, BigDecimal plazoVencimiento) {
		this.tna = tna;
		this.tena = calcularTea(tna, plazoVencimiento);
	}

	/**
	 * Calcular tea.
	 *
	 * @param tna
	 *            the tna
	 * @param plazoVencimiento
	 *            the plazo vencimiento
	 * @return the big decimal
	 */
	private BigDecimal calcularTea(BigDecimal tna, BigDecimal plazoVencimiento) {

		BigDecimal tnaFinal = tna.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal valor = BigDecimal.valueOf(CERO_DOUBLE);
		if ((tnaFinal != null) && (plazoVencimiento != null)) {
			BigDecimal term1 = tnaFinal.divide(BigDecimal.valueOf(CIEN_DOUBLE), OCHO_INT, BigDecimal.ROUND_CEILING);
			BigDecimal term2 = plazoVencimiento.divide(BigDecimal.valueOf(TRES_SEIS_CINCO_DOUBLE), OCHO_INT,
					BigDecimal.ROUND_CEILING);
			BigDecimal termA = (term1.multiply(term2)).add(BigDecimal.valueOf(UNO_DOUBLE));
			BigDecimal termB = (BigDecimal.valueOf(TRES_SEIS_CINCO_DOUBLE)).divide(plazoVencimiento, OCHO_INT,
					BigDecimal.ROUND_CEILING);
			BigDecimal termC = (BigDecimal.valueOf(Math.pow(termA.doubleValue(), termB.doubleValue())))
					.setScale(OCHO_INT, BigDecimal.ROUND_CEILING);
			valor = (termC.subtract(BigDecimal.valueOf(UNO_DOUBLE))).multiply(BigDecimal.valueOf(CIEN_DOUBLE));
		}
		return valor;

	}

	/**
	 * Gets the informacion.
	 *
	 * @return the informacion
	 */
	public String getInformacion() {
		return informacion;
	}

	/**
	 * Sets the informacion.
	 *
	 * @param informacion
	 *            the informacion to set
	 */
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public BigDecimal getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the tena.
	 *
	 * @return the tena
	 */
	public BigDecimal getTena() {
		return tena;
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
	 *            the new tarifa
	 */
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	/**
	 * Gets the tasa variable.
	 *
	 * @return the tasa variable
	 */
	public String getTasaVariable() {
		return tasaVariable;
	}

	/**
	 * Sets the tasa variable.
	 *
	 * @param tasaVariable
	 *            the new tasa variable
	 */
	public void setTasaVariable(String tasaVariable) {
		this.tasaVariable = tasaVariable;
	}

	/**
	 * Gets the cantidad de dias.
	 *
	 * @return the cantidad de dias
	 */
	public String getCantidadDeDias() {
		return cantidadDeDias;
	}

	/**
	 * Sets the cantidad de dias.
	 *
	 * @param cantidadDeDias
	 *            the new cantidad de dias
	 */
	public void setCantidadDeDias(String cantidadDeDias) {
		this.cantidadDeDias = cantidadDeDias;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipo PF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the new tipo PF
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * Gets the fecha alta real.
	 *
	 * @return the fecha alta real
	 */
	public String getFechaAltaReal() {
		return fechaAltaReal;
	}

	/**
	 * Sets the fecha alta real.
	 *
	 * @param fechaAltaReal
	 *            the new fecha alta real
	 */
	public void setFechaAltaReal(String fechaAltaReal) {
		this.fechaAltaReal = fechaAltaReal;
	}

	/**
	 * Gets the impuestos PF.
	 *
	 * @return the impuestos PF
	 */
	public List<ConsultarCondicionesImpuestosEntity> getImpuestosPF() {
		return impuestosPF;
	}

	/**
	 * Sets the impuestos PF.
	 *
	 * @param impuestosPF
	 *            the new impuestos PF
	 */
	public void setImpuestosPF(List<ConsultarCondicionesImpuestosEntity> impuestosPF) {
		this.impuestosPF = impuestosPF;
	}

	/**
	 * Gets the interesante PF.
	 *
	 * @return the interesante PF
	 */
	public List<ConsultarCondicionesInteresanteEntity> getInteresantePF() {
		return interesantePF;
	}

	/**
	 * Sets the interesante PF.
	 *
	 * @param interesantePF
	 *            the new interesante PF
	 */
	public void setInteresantePF(List<ConsultarCondicionesInteresanteEntity> interesantePF) {
		this.interesantePF = interesantePF;
	}

	/**
	 * Gets the ini.
	 *
	 * @return the ini
	 */
	public BigDecimal getIni() {
		return ini;
	}

	/**
	 * Sets the ini.
	 *
	 * @param ini
	 *            the new ini
	 */
	public void setIni(BigDecimal ini) {
		this.ini = ini;
	}

	/**
	 * Gets the mostrar fecuencia intereses.
	 *
	 * @return the mostrar fecuencia intereses
	 */
	public String getMostrarFecuenciaIntereses() {
		return mostrarFecuenciaIntereses;
	}

	/**
	 * Sets the mostrar fecuencia intereses.
	 *
	 * @param mostrarFecuenciaIntereses
	 *            the new mostrar fecuencia intereses
	 */
	public void setMostrarFecuenciaIntereses(String mostrarFecuenciaIntereses) {
		this.mostrarFecuenciaIntereses = mostrarFecuenciaIntereses;
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
	 *            the new producto
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
	 *            the new subproducto
	 */
	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	/**
	 * Gets the periodo liquidacion.
	 *
	 * @return the periodo liquidacion
	 */
	public String getPeriodoLiquidacion() {
		return periodoLiquidacion;
	}

	/**
	 * Sets the periodo liquidacion.
	 *
	 * @param periodoLiquidacion
	 *            the new periodo liquidacion
	 */
	public void setPeriodoLiquidacion(String periodoLiquidacion) {
		this.periodoLiquidacion = periodoLiquidacion;
	}

	/**
	 * Gets the fecha minima precancelar.
	 *
	 * @return the fecha minima precancelar
	 */
	public String getFechaMinimaPrecancelar() {
		return fechaMinimaPrecancelar;
	}

	/**
	 * Sets the fecha minima precancelar.
	 *
	 * @param fechaMinimaPrecancelar
	 *            the new fecha minima precancelar
	 */
	public void setFechaMinimaPrecancelar(String fechaMinimaPrecancelar) {
		this.fechaMinimaPrecancelar = fechaMinimaPrecancelar;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentaje penalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the new porcentaje penalizacion
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
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

}
