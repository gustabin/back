/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornar datos del DAO.
 * 
 * @author juan.pablo.picate
 *
 */
@Record
public class ImposicionPlazoFijoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** numerico. */
	@Field
	private String codigoRetornoExtendido;

	/** The entidad cuenta plazo. */
	@Field
	private String entidadCuentaPlazo;

	/** numerico. */
	@Field
	private String sucursalCuentaPlazo;

	/** numerico. */
	@Field
	private String numeroCuentaPlazo;

	/** numerico. */
	@Field
	private String secuencia;

	/** DD-MM-AAAA. */
	@Field
	private String fechaVencimiento;

	/** The periodo liquidacion. */
	@Field
	private String periodoLiquidacion;

	/** 12 enteros, 2 decimales. */
	@Field
	private String importeCertificado;

	/** 12 enteros, 2 decimales. */
	@Field
	private String intereses;

	/** numerico. */
	@Field
	private String plazo;

	/** 12 enteros, 2 decimales. */
	@Field
	private String importeDebitado;

	/** 12 enteros, 2 decimales. */
	@Field
	private String totalImpuestosCobrados;

	/** 12 enteros, 2 decimales. */
	@Field
	private String totalImpuestosAlVencimiento;

	/** DD-MM-AAAA. */
	@Field
	private String fechaAltaReal;

	/** The nio. */
	@Field
	private String nio;

	/** The indicador precancelable. */
	@Field
	private String indicadorPrecancelable;

	/** numerico. */
	@Field
	private String minimoDiasPrecancelar;

	/** DD-MM-AAAA. */
	@Field
	private String fechaMinimaPrecancelar;

	/** 3 enteros, 5 decimales. */
	@Field
	private String porcentajePenalizacion;

	/** 4 enteros, 5 decimales. */
	@Field
	private String tasasPrimerTramo;

	/** 4 enteros, 5 decimales. */
	@Field
	private String tasaVariableMinimoGarantizada;

	/** numerico. */
	@Field
	private String ajusteAlAlta;

	/** The signo del ajuste. */
	@Field
	private String signoDelAjuste;

    /** The codigo ur. */
    @Field
    private String codigoUr;
    
    /** 13 enteros, 4 decimales. */
    @Field
    private String saldoInicUr;
    
    /** 5 enteros, 8 decimales. */
    @Field
    private String cotizacionCodigoUr;
	
	/** The cant impuestos. */
	@Field()
	private Long cantImpuestos;

	/** The impuestos PF. */
	@Segment(minOccurs = 5, maxOccurs = 5)
	private List<ImposicionImpuestosPlazoFijoEntity> impuestosPF = new ArrayList<ImposicionImpuestosPlazoFijoEntity>();

	/** The cantidad repeticiones interesante. */
	@Field()
	private Long cantidadRepeticionesInteresante;

	/** The interesante PF. */
	@Segment(occursRef = "cantidadRepeticionesInteresante")
	private List<ImposicionInteresantePlazoFijoEntity> interesantePF = new ArrayList<ImposicionInteresantePlazoFijoEntity>();

	
	
	/**
	 * @param headerTrama
	 * @param codigoRetornoExtendido
	 * @param entidadCuentaPlazo
	 * @param sucursalCuentaPlazo
	 * @param numeroCuentaPlazo
	 * @param secuencia
	 * @param fechaVencimiento
	 * @param periodoLiquidacion
	 * @param importeCertificado
	 * @param intereses
	 * @param plazo
	 * @param importeDebitado
	 * @param totalImpuestosCobrados
	 * @param totalImpuestosAlVencimiento
	 * @param fechaAltaReal
	 * @param nio
	 * @param indicadorPrecancelable
	 * @param minimoDiasPrecancelar
	 * @param fechaMinimaPrecancelar
	 * @param porcentajePenalizacion
	 * @param tasasPrimerTramo
	 * @param tasaVariableMinimoGarantizada
	 * @param ajusteAlAlta
	 * @param signoDelAjuste
	 * @param codigoUr
	 * @param saldoInicUr
	 * @param cotizacionCodigoUr
	 * @param cantImpuestos
	 * @param impuestosPF
	 * @param cantidadRepeticionesInteresante
	 * @param interesantePF
	 */
	public ImposicionPlazoFijoOutEntity(String headerTrama, String codigoRetornoExtendido, String entidadCuentaPlazo,
			String sucursalCuentaPlazo, String numeroCuentaPlazo, String secuencia, String fechaVencimiento,
			String periodoLiquidacion, String importeCertificado, String intereses, String plazo,
			String importeDebitado, String totalImpuestosCobrados, String totalImpuestosAlVencimiento,
			String fechaAltaReal, String indicadorPrecancelable, String minimoDiasPrecancelar,
			String fechaMinimaPrecancelar, String porcentajePenalizacion, String tasasPrimerTramo,
			String tasaVariableMinimoGarantizada, String codigoUr,
			String saldoInicUr, String cotizacionCodigoUr, Long cantImpuestos,
			List<ImposicionImpuestosPlazoFijoEntity> impuestosPF, Long cantidadRepeticionesInteresante,
			List<ImposicionInteresantePlazoFijoEntity> interesantePF) {
		super();
		this.headerTrama = headerTrama;
		this.codigoRetornoExtendido = codigoRetornoExtendido;
		this.entidadCuentaPlazo = entidadCuentaPlazo;
		this.sucursalCuentaPlazo = sucursalCuentaPlazo;
		this.numeroCuentaPlazo = numeroCuentaPlazo;
		this.secuencia = secuencia;
		this.fechaVencimiento = fechaVencimiento;
		this.periodoLiquidacion = periodoLiquidacion;
		this.importeCertificado = importeCertificado;
		this.intereses = intereses;
		this.plazo = plazo;
		this.importeDebitado = importeDebitado;
		this.totalImpuestosCobrados = totalImpuestosCobrados;
		this.totalImpuestosAlVencimiento = totalImpuestosAlVencimiento;
		this.fechaAltaReal = fechaAltaReal;
		this.indicadorPrecancelable = indicadorPrecancelable;
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
		this.fechaMinimaPrecancelar = fechaMinimaPrecancelar;
		this.porcentajePenalizacion = porcentajePenalizacion;
		this.tasasPrimerTramo = tasasPrimerTramo;
		this.tasaVariableMinimoGarantizada = tasaVariableMinimoGarantizada;
		this.codigoUr = codigoUr;
		this.saldoInicUr = saldoInicUr;
		this.cotizacionCodigoUr = cotizacionCodigoUr;
		this.cantImpuestos = cantImpuestos;
		this.impuestosPF = impuestosPF;
		this.cantidadRepeticionesInteresante = cantidadRepeticionesInteresante;
		this.interesantePF = interesantePF;
	}

	/**
	 * 
	 */
	public ImposicionPlazoFijoOutEntity() {
		super();
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
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
	 * Gets the numero cuenta plazo.
	 *
	 * @return the numeroCuentaPlazo
	 */
	public String getNumeroCuentaPlazo() {
		return numeroCuentaPlazo;
	}

	/**
	 * Sets the numero cuenta plazo.
	 *
	 * @param numeroCuentaPlazo
	 *            the numeroCuentaPlazo to set
	 */
	public void setNumeroCuentaPlazo(String numeroCuentaPlazo) {
		this.numeroCuentaPlazo = numeroCuentaPlazo;
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
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the intereses to set
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
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
	 * Gets the importe debitado.
	 *
	 * @return the importeDebitado
	 */
	public String getImporteDebitado() {
		return importeDebitado;
	}

	/**
	 * Sets the importe debitado.
	 *
	 * @param importeDebitado
	 *            the importeDebitado to set
	 */
	public void setImporteDebitado(String importeDebitado) {
		this.importeDebitado = importeDebitado;
	}

	/**
	 * Gets the total impuestos cobrados.
	 *
	 * @return the totalImpuestosCobrados
	 */
	public String getTotalImpuestosCobrados() {
		return totalImpuestosCobrados;
	}

	/**
	 * Sets the total impuestos cobrados.
	 *
	 * @param totalImpuestosCobrados
	 *            the totalImpuestosCobrados to set
	 */
	public void setTotalImpuestosCobrados(String totalImpuestosCobrados) {
		this.totalImpuestosCobrados = totalImpuestosCobrados;
	}

	/**
	 * Gets the total impuestos al vencimiento.
	 *
	 * @return the totalImpuestosAlVencimiento
	 */
	public String getTotalImpuestosAlVencimiento() {
		return totalImpuestosAlVencimiento;
	}

	/**
	 * Sets the total impuestos al vencimiento.
	 *
	 * @param totalImpuestosAlVencimiento
	 *            the totalImpuestosAlVencimiento to set
	 */
	public void setTotalImpuestosAlVencimiento(String totalImpuestosAlVencimiento) {
		this.totalImpuestosAlVencimiento = totalImpuestosAlVencimiento;
	}

	/**
	 * Gets the fecha alta real.
	 *
	 * @return the fechaAltaReal
	 */
	public String getFechaAltaReal() {
		return fechaAltaReal;
	}

	/**
	 * Sets the fecha alta real.
	 *
	 * @param fechaAltaReal
	 *            the fechaAltaReal to set
	 */
	public void setFechaAltaReal(String fechaAltaReal) {
		this.fechaAltaReal = fechaAltaReal;
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
	public void setNIO(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the indicador precancelable.
	 *
	 * @return the indicadorPrecancelable
	 */
	public String getIndicadorPrecancelable() {
		return indicadorPrecancelable;
	}

	/**
	 * Sets the indicador precancelable.
	 *
	 * @param indicadorPrecancelable
	 *            the indicadorPrecancelable to set
	 */
	public void setIndicadorPrecancelable(String indicadorPrecancelable) {
		this.indicadorPrecancelable = indicadorPrecancelable;
	}

	/**
	 * Gets the minimo dias precancelar.
	 *
	 * @return the minimoDiasPrecancelar
	 */
	public String getMinimoDiasPrecancelar() {
		return minimoDiasPrecancelar;
	}

	/**
	 * Sets the minimo dias precancelar.
	 *
	 * @param minimoDiasPrecancelar
	 *            the minimoDiasPrecancelar to set
	 */
	public void setMinimoDiasPrecancelar(String minimoDiasPrecancelar) {
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
	}

	/**
	 * Gets the fecha minima precancelar.
	 *
	 * @return the fechaMinimaPrecancelar
	 */
	public String getFechaMinimaPrecancelar() {
		return fechaMinimaPrecancelar;
	}

	/**
	 * Sets the fecha minima precancelar.
	 *
	 * @param fechaMinimaPrecancelar
	 *            the fechaMinimaPrecancelar to set
	 */
	public void setFechaMinimaPrecancelar(String fechaMinimaPrecancelar) {
		this.fechaMinimaPrecancelar = fechaMinimaPrecancelar;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the tasas primer tramo.
	 *
	 * @return the tasasPrimerTramo
	 */
	public String getTasasPrimerTramo() {
		return tasasPrimerTramo;
	}

	/**
	 * Sets the tasas primer tramo.
	 *
	 * @param tasasPrimerTramo
	 *            the tasasPrimerTramo to set
	 */
	public void setTasasPrimerTramo(String tasasPrimerTramo) {
		this.tasasPrimerTramo = tasasPrimerTramo;
	}

	/**
	 * Gets the tasa variable minimo garantizada.
	 *
	 * @return the tasaVariableMinimoGarantizada
	 */
	public String getTasaVariableMinimoGarantizada() {
		return tasaVariableMinimoGarantizada;
	}

	/**
	 * Sets the tasa variable minimo garantizada.
	 *
	 * @param tasaVariableMinimoGarantizada
	 *            the tasaVariableMinimoGarantizada to set
	 */
	public void setTasaVariableMinimoGarantizada(String tasaVariableMinimoGarantizada) {
		this.tasaVariableMinimoGarantizada = tasaVariableMinimoGarantizada;
	}

	/**
	 * Gets the ajuste al alta.
	 *
	 * @return the ajusteAlAlta
	 */
	public String getAjusteAlAlta() {
		return ajusteAlAlta;
	}

	/**
	 * Sets the ajuste al alta.
	 *
	 * @param ajusteAlAlta
	 *            the ajusteAlAlta to set
	 */
	public void setAjusteAlAlta(String ajusteAlAlta) {
		this.ajusteAlAlta = ajusteAlAlta;
	}

	/**
	 * Gets the signo del ajuste.
	 *
	 * @return the signoDelAjuste
	 */
	public String getSignoDelAjuste() {
		return signoDelAjuste;
	}

	/**
	 * Sets the signo del ajuste.
	 *
	 * @param signoDelAjuste
	 *            the signoDelAjuste to set
	 */
	public void setSignoDelAjuste(String signoDelAjuste) {
		this.signoDelAjuste = signoDelAjuste;
	}

	/**
	 * Gets the cant impuestos.
	 *
	 * @return the cantImpuestos
	 */
	public Long getCantImpuestos() {
		return cantImpuestos;
	}

	/**
	 * Sets the cant impuestos.
	 *
	 * @param cantImpuestos
	 *            the cantImpuestos to set
	 */
	public void setCantImpuestos(Long cantImpuestos) {
		this.cantImpuestos = cantImpuestos;
	}

	/**
	 * Gets the impuestos PF.
	 *
	 * @return the impuestosPF
	 */
	public List<ImposicionImpuestosPlazoFijoEntity> getImpuestosPF() {
		return impuestosPF;
	}

	/**
	 * Sets the impuestos PF.
	 *
	 * @param impuestosPF
	 *            the impuestosPF to set
	 */
	public void setImpuestosPF(List<ImposicionImpuestosPlazoFijoEntity> impuestosPF) {
		this.impuestosPF = impuestosPF;
	}
	
    /**
     * Gets the codigo ur.
     * 
     * @return the codigoUr
     */
    public String getCodigoUr() {
        return codigoUr;
    }

    /**
     * Sets the codigo ur.
     * 
     * @param codigoUr the codigoUr to set
     */
    public void setCodigoUr(String codigoUr) {
        this.codigoUr = codigoUr;
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

	/**
	 * Gets the cantidad repeticiones interesante.
	 *
	 * @return the cantidadRepeticionesInteresante
	 */
	public Long getCantidadRepeticionesInteresante() {
		return cantidadRepeticionesInteresante;
	}

	/**
	 * Sets the cantidad repeticiones interesante.
	 *
	 * @param cantidadRepeticionesInteresante
	 *            the cantidadRepeticionesInteresante to set
	 */
	public void setCantidadRepeticionesInteresante(Long cantidadRepeticionesInteresante) {
		this.cantidadRepeticionesInteresante = cantidadRepeticionesInteresante;
	}

	/**
	 * Gets the interesante PF.
	 *
	 * @return the interesantePF
	 */
	public List<ImposicionInteresantePlazoFijoEntity> getInteresantePF() {
		return interesantePF;
	}

	/**
	 * Sets the interesante PF.
	 *
	 * @param interesantePF
	 *            the interesantePF to set
	 */
	public void setInteresantePF(List<ImposicionInteresantePlazoFijoEntity> interesantePF) {
		this.interesantePF = interesantePF;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(headerTrama).append(codigoRetornoExtendido).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ImposicionPlazoFijoOutEntity other = (ImposicionPlazoFijoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append("headerTrama", other.headerTrama).append("codigoRetornoExtendido",
				other.codigoRetornoExtendido);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido).append("cantImpuestos", cantImpuestos)
				.append("impuestosPF", impuestosPF)
				.append("cantidadRepeticionesInteresante", cantidadRepeticionesInteresante)
				.append("interesantePF", interesantePF).toString();
	}

}
