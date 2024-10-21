/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

/**
 * The Class DatosComparativaCarteraRequest.
 */
public class DatosComparativaCarteraRequest extends EntityBase {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The lista cuentas. */
	@JsonProperty("Cuenta")
	private CuentaOPEntity listaCuentas = new CuentaOPEntity();
	
	/** The codigo comparacion. */
	@JsonProperty("CodigoComparacion")
	private String codigoComparacion;
	
	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The periodo fecha inicial. */
	@JsonProperty("PeriodoFechaInicial")
	private Date periodoFechaInicial;
	
	/** The periodo fecha final. */
	@JsonProperty("PeriodoFechaFinal")
	private Date periodoFechaFinal;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The datos servicios. */
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public CuentaOPEntity getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(CuentaOPEntity listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the codigo comparacion.
	 *
	 * @return the codigo comparacion
	 */
	public String getCodigoComparacion() {
		return codigoComparacion;
	}

	/**
	 * Sets the codigo comparacion.
	 *
	 * @param codigoComparacion
	 *            the new codigo comparacion
	 */
	public void setCodigoComparacion(String codigoComparacion) {
		this.codigoComparacion = codigoComparacion;
	}

	/**
	 * Gets the codigo periodo.
	 *
	 * @return the codigo periodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo
	 *            the new codigo periodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the periodo fecha inicial.
	 *
	 * @return the periodo fecha inicial
	 */
	public Date getPeriodoFechaInicial() {
		return periodoFechaInicial;
	}

	/**
	 * Sets the periodo fecha inicial.
	 *
	 * @param periodoFechaInicial
	 *            the new periodo fecha inicial
	 */
	public void setPeriodoFechaInicial(Date periodoFechaInicial) {
		this.periodoFechaInicial = periodoFechaInicial;
	}

	/**
	 * Gets the periodo fecha final.
	 *
	 * @return the periodo fecha final
	 */
	public Date getPeriodoFechaFinal() {
		return periodoFechaFinal;
	}

	/**
	 * Sets the periodo fecha final.
	 *
	 * @param periodoFechaFinal
	 *            the new periodo fecha final
	 */
	public void setPeriodoFechaFinal(Date periodoFechaFinal) {
		this.periodoFechaFinal = periodoFechaFinal;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the datos servicios.
	 *
	 * @return the datos servicios
	 */
	public DatosServiciosEntity getDatosServicios() {
		return datosServicios;
	}

	/**
	 * Sets the datos servicios.
	 *
	 * @param datosServicios
	 *            the new datos servicios
	 */
	public void setDatosServicios(DatosServiciosEntity datosServicios) {
		this.datosServicios = datosServicios;
	}

}
