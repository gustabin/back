/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

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
public class SimulacionPlazoFijoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** numerico. */
	@Field
	private String codigoRetornoExtendido;

	/** The producto. */
	@Field
	private String producto;

	/** The desc producto. */
	@Field
	private String descProducto;

	/** The subproducto. */
	@Field
	private String subproducto;

	/** The desc subproducto. */
	@Field
	private String descSubproducto;

	/** The tarifa. */
	@Field
	private String tarifa;

	/** numerico. */
	@Field
	private String plazo;

	/** AAAA-MM-DD. */
	@Field
	private String fechaProven;

	/** The per liq. */
	@Field
	private String perLiq;

	/** 13 enteros, 2 decimales. */
	@Field
	private String importe;

	/** 3 enteros, 5 decimales. */
	@Field
	private String tasa;

	/** 13 enteros, 2 decimales. */
	@Field
	private String interes;

	/** 13 enteros, 2 decimales. */
	@Field
	private String importeADebitar;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuCap;

	/** 13 enteros, 2 decimales. */
	@Field
	private String impuInt;

	/** 3 enteros, 5 decimales. */
	@Field
	private String tasaRenovacion;

	/** AAAA-MM-DD. */
	@Field
	private String fechaAltaReal;

	/** The tipo PF. */
	@Field
	private String tipoPF;

	/** The indicador precancelable. */
	@Field
	private String indicadorPrecancelable;

	/** numerico. */
	@Field
	private String minimoDiasPrecancelar;

	/** AAAA-DD-MM. */
	@Field
	private String fechaMinimaPrecancelar;

	/** 3 enteros, 5 decimales. */
	@Field
	private String porcentajePenalizacion;

	/** 4 enteros, 5 decimales. */
	@Field
	private String tasaPrimerTramo;

	/** 4 enteros, 5 decimales. */
	@Field
	private String tasaVarMinimoGarantizada;

	/** 5 decimales. */
	@Field
	private String ajusteAlAlta;

	/** The signo ajuste al alta. */
	@Field
	private String signoAjusteAlAlta;

    /** The codigo ur. */
    @Field
    private String codigoUr;
    
    /** 13 enteros, 4 decimales.  */
    @Field
    private String saldoInicUr;
    
    /** 5 enteros, 8 decimales.  */
    @Field
    private String cotizacionCodigoUr;
	
	/** The cant impuestos. */
	@Field()
	private Long cantImpuestos;

	/** The impuestos PF. */
	@Segment(minOccurs = 5, maxOccurs = 5)
	private List<ConsultarCondicionesImpuestosEntity> impuestosPF = new ArrayList<ConsultarCondicionesImpuestosEntity>();

	/** The cantidad repeticiones interesante. */
	@Field()
	private Long cantidadRepeticionesInteresante;

	/**
	 * Lista de impuestos e intereses segun frecuencia de cobro. Usado en plazo
	 * fijo Interesante Tasa Fija
	 */
	@Segment(occursRef = "cantidadRepeticionesInteresante")
	private List<ConsultarCondicionesInteresanteEntity> interesantePF = new ArrayList<ConsultarCondicionesInteresanteEntity>();

	
	/**
	 * @param headerTrama
	 * @param codigoRetornoExtendido
	 * @param producto
	 * @param descProducto
	 * @param subproducto
	 * @param descSubproducto
	 * @param tarifa
	 * @param plazo
	 * @param fechaProven
	 * @param perLiq
	 * @param importe
	 * @param tasa
	 * @param interes
	 * @param importeADebitar
	 * @param impuCap
	 * @param impuInt
	 * @param tasaRenovacion
	 * @param fechaAltaReal
	 * @param tipoPF
	 * @param indicadorPrecancelable
	 * @param minimoDiasPrecancelar
	 * @param fechaMinimaPrecancelar
	 * @param porcentajePenalizacion
	 * @param tasaPrimerTramo
	 * @param tasaVarMinimoGarantizada
	 * @param ajusteAlAlta
	 * @param signoAjusteAlAlta
	 * @param codigoUr
	 * @param saldoInicUr
	 * @param cotizacionCodigoUr
	 * @param cantImpuestos
	 * @param impuestosPF
	 * @param cantidadRepeticionesInteresante
	 * @param interesantePF
	 */
	public SimulacionPlazoFijoOutEntity(String headerTrama, String codigoRetornoExtendido, String producto,
			String descProducto, String subproducto, String descSubproducto, String tarifa, String plazo,
			String fechaProven, String perLiq, String importe, String tasa, String interes, String importeADebitar,
			String impuCap, String impuInt, String tasaRenovacion, String fechaAltaReal, String tipoPF,
			String indicadorPrecancelable, String minimoDiasPrecancelar, String fechaMinimaPrecancelar,
			String porcentajePenalizacion, String tasaPrimerTramo, String tasaVarMinimoGarantizada, String codigoUr, String saldoInicUr, String cotizacionCodigoUr,
			Long cantImpuestos, List<ConsultarCondicionesImpuestosEntity> impuestosPF,
			Long cantidadRepeticionesInteresante, List<ConsultarCondicionesInteresanteEntity> interesantePF) {
		super();
		this.headerTrama = headerTrama;
		this.codigoRetornoExtendido = codigoRetornoExtendido;
		this.producto = producto;
		this.descProducto = descProducto;
		this.subproducto = subproducto;
		this.descSubproducto = descSubproducto;
		this.tarifa = tarifa;
		this.plazo = plazo;
		this.fechaProven = fechaProven;
		this.perLiq = perLiq;
		this.importe = importe;
		this.tasa = tasa;
		this.interes = interes;
		this.importeADebitar = importeADebitar;
		this.impuCap = impuCap;
		this.impuInt = impuInt;
		this.tasaRenovacion = tasaRenovacion;
		this.fechaAltaReal = fechaAltaReal;
		this.tipoPF = tipoPF;
		this.indicadorPrecancelable = indicadorPrecancelable;
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
		this.fechaMinimaPrecancelar = fechaMinimaPrecancelar;
		this.porcentajePenalizacion = porcentajePenalizacion;
		this.tasaPrimerTramo = tasaPrimerTramo;
		this.tasaVarMinimoGarantizada = tasaVarMinimoGarantizada;
		this.codigoUr = codigoUr;
		this.saldoInicUr = saldoInicUr;
		this.cotizacionCodigoUr = cotizacionCodigoUr;
		this.cantImpuestos = cantImpuestos;
		this.impuestosPF = impuestosPF;
		this.cantidadRepeticionesInteresante = cantidadRepeticionesInteresante;
		this.interesantePF = interesantePF;
	}
	
	/**
	 * Constructor vacio
	 */
	public SimulacionPlazoFijoOutEntity() {
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
	 * Gets the desc producto.
	 *
	 * @return the descProducto
	 */
	public String getDescProducto() {
		return descProducto;
	}

	/**
	 * Sets the desc producto.
	 *
	 * @param descProducto
	 *            the descProducto to set
	 */
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
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
	 * Gets the desc subproducto.
	 *
	 * @return the descSubproducto
	 */
	public String getDescSubproducto() {
		return descSubproducto;
	}

	/**
	 * Sets the desc subproducto.
	 *
	 * @param descSubproducto
	 *            the descSubproducto to set
	 */
	public void setDescSubproducto(String descSubproducto) {
		this.descSubproducto = descSubproducto;
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
	 * Gets the fecha proven.
	 *
	 * @return the fechaProven
	 */
	public String getFechaProven() {
		return fechaProven;
	}

	/**
	 * Sets the fecha proven.
	 *
	 * @param fechaProven
	 *            the fechaProven to set
	 */
	public void setFechaProven(String fechaProven) {
		this.fechaProven = fechaProven;
	}

	/**
	 * Gets the per liq.
	 *
	 * @return the perLiq
	 */
	public String getPerLiq() {
		return perLiq;
	}

	/**
	 * Sets the per liq.
	 *
	 * @param perLiq
	 *            the perLiq to set
	 */
	public void setPerLiq(String perLiq) {
		this.perLiq = perLiq;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
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
	 * Gets the interes.
	 *
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * Sets the interes.
	 *
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * Gets the importe A debitar.
	 *
	 * @return the importeADebitar
	 */
	public String getImporteADebitar() {
		return importeADebitar;
	}

	/**
	 * Sets the importe A debitar.
	 *
	 * @param importeADebitar
	 *            the importeADebitar to set
	 */
	public void setImporteADebitar(String importeADebitar) {
		this.importeADebitar = importeADebitar;
	}

	/**
	 * Gets the impu cap.
	 *
	 * @return the impuCap
	 */
	public String getImpuCap() {
		return impuCap;
	}

	/**
	 * Sets the impu cap.
	 *
	 * @param impuCap
	 *            the impuCap to set
	 */
	public void setImpuCap(String impuCap) {
		this.impuCap = impuCap;
	}

	/**
	 * Gets the impu int.
	 *
	 * @return the impuInt
	 */
	public String getImpuInt() {
		return impuInt;
	}

	/**
	 * Sets the impu int.
	 *
	 * @param impuInt
	 *            the impuInt to set
	 */
	public void setImpuInt(String impuInt) {
		this.impuInt = impuInt;
	}

	/**
	 * Gets the tasa renovacion.
	 *
	 * @return the tasaRenovacion
	 */
	public String getTasaRenovacion() {
		return tasaRenovacion;
	}

	/**
	 * Sets the tasa renovacion.
	 *
	 * @param tasaRenovacion
	 *            the tasaRenovacion to set
	 */
	public void setTasaRenovacion(String tasaRenovacion) {
		this.tasaRenovacion = tasaRenovacion;
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
	 * Gets the tasa primer tramo.
	 *
	 * @return the tasaPrimerTramo
	 */
	public String getTasaPrimerTramo() {
		return tasaPrimerTramo;
	}

	/**
	 * Sets the tasa primer tramo.
	 *
	 * @param tasaPrimerTramo
	 *            the tasaPrimerTramo to set
	 */
	public void setTasaPrimerTramo(String tasaPrimerTramo) {
		this.tasaPrimerTramo = tasaPrimerTramo;
	}

	/**
	 * Gets the tasa var minimo garantizada.
	 *
	 * @return the tasaVarMinimoGarantizada
	 */
	public String getTasaVarMinimoGarantizada() {
		return tasaVarMinimoGarantizada;
	}

	/**
	 * Sets the tasa var minimo garantizada.
	 *
	 * @param tasaVarMinimoGarantizada
	 *            the tasaVarMinimoGarantizada to set
	 */
	public void setTasaVarMinimoGarantizada(String tasaVarMinimoGarantizada) {
		this.tasaVarMinimoGarantizada = tasaVarMinimoGarantizada;
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
	 * Gets the signo ajuste al alta.
	 *
	 * @return the signoAjusteAlAlta
	 */
	public String getSignoAjusteAlAlta() {
		return signoAjusteAlAlta;
	}

	/**
	 * Sets the signo ajuste al alta.
	 *
	 * @param signoAjusteAlAlta
	 *            the signoAjusteAlAlta to set
	 */
	public void setSignoAjusteAlAlta(String signoAjusteAlAlta) {
		this.signoAjusteAlAlta = signoAjusteAlAlta;
	}
	
    /**
     * Gets the codigo ur. No se usa este campo.
     * 
     * @return the codigoUr
     */
    public String getCodigoUr() {
        return codigoUr;
    }

    /**
     * Sets the codigo ur. No se usa este campo.
     * 
     * @param codigoUr the codigoUr to set
     */
    public void setCodigoUr(String codigoUr) {
        this.codigoUr = codigoUr;
    }

    /**
     * Gets the saldo inic ur. Capital UVA.
     * 
     * @return the saldoInicUr
     */
    public String getSaldoInicUr() {
        return saldoInicUr;
    }

    /**
     * Sets the saldo inic ur. Capital UVA.
     * 
     * @param saldoInicUr the saldoInicUr to set
     */
    public void setSaldoInicUr(String saldoInicUr) {
        this.saldoInicUr = saldoInicUr;
    }

    /**
     * Gets the cotizacion codigo ur. Valor UVA.
     * 
     * @return the cotizacionCodigoUr
     */
    public String getCotizacionCodigoUr() {
        return cotizacionCodigoUr;
    }

    /**
     * Sets the cotizacion codigo ur. Valor UVA.
     * 
     * @param cotizacionCodigoUr the cotizacionCodigoUr to set
     */
    public void setCotizacionCodigoUr(String cotizacionCodigoUr) {
        this.cotizacionCodigoUr = cotizacionCodigoUr;
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
	public List<ConsultarCondicionesImpuestosEntity> getImpuestosPF() {
		return impuestosPF;
	}

	/**
	 * Sets the impuestos PF.
	 *
	 * @param impuestosPF
	 *            the impuestosPF to set
	 */
	public void setImpuestosPF(List<ConsultarCondicionesImpuestosEntity> impuestosPF) {
		this.impuestosPF = impuestosPF;
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
	public List<ConsultarCondicionesInteresanteEntity> getInteresantePF() {
		return interesantePF;
	}

	/**
	 * Sets the interesante PF.
	 *
	 * @param interesantePF
	 *            the interesantePF to set
	 */
	public void setInteresantePF(List<ConsultarCondicionesInteresanteEntity> interesantePF) {
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cantImpuestos", cantImpuestos).append("impuestosPF", impuestosPF)
				.toString();
	}
}
