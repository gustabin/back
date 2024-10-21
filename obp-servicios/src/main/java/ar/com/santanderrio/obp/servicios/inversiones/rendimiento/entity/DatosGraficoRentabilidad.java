/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

/**
 * The Class DatosGraficoRentabilidad.
 */
public class DatosGraficoRentabilidad extends EntityBase {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	/** The lista cuentas. */
	@JsonProperty("ListaCuentas")
	private List<CuentaOPEntity> listaCuentas = new ArrayList<CuentaOPEntity>();

	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The cartera A consultar. */
	@JsonProperty("CarteraAConsultar")
	private String carteraAConsultar;

	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;

	/** The periodo fecha inicial. */
	@JsonProperty("PeriodoFechaInicial")
	private String periodoFechaInicial;

	/** The periodo fecha final. */
	@JsonProperty("PeriodoFechaFinal")
	private String periodoFechaFinal;

	/** The clasificacion. */
	@JsonProperty("Clasificacion")
	private String clasificacion;

	/** The subclasificacion. */
	@JsonProperty("Subclasificacion")
	private String subclasificacion;

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
	public List<CuentaOPEntity> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<CuentaOPEntity> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the cartera A consultar.
	 *
	 * @return the cartera A consultar
	 */
	public String getCarteraAConsultar() {
		return carteraAConsultar;
	}

	/**
	 * Sets the cartera A consultar.
	 *
	 * @param carteraAConsultar
	 *            the new cartera A consultar
	 */
	public void setCarteraAConsultar(String carteraAConsultar) {
		this.carteraAConsultar = carteraAConsultar;
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
	public String getPeriodoFechaInicial() {
		return periodoFechaInicial;
	}

	/**
	 * Sets the periodo fecha inicial.
	 *
	 * @param periodoFechaInicial
	 *            the new periodo fecha inicial
	 */
	public void setPeriodoFechaInicial(String periodoFechaInicial) {
		this.periodoFechaInicial = periodoFechaInicial;
	}

	/**
	 * Gets the periodo fecha final.
	 *
	 * @return the periodo fecha final
	 */
	public String getPeriodoFechaFinal() {
		return periodoFechaFinal;
	}

	/**
	 * Sets the periodo fecha final.
	 *
	 * @param periodoFechaFinal
	 *            the new periodo fecha final
	 */
	public void setPeriodoFechaFinal(String periodoFechaFinal) {
		this.periodoFechaFinal = periodoFechaFinal;
	}

	/**
	 * Gets the clasificacion.
	 *
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * Sets the clasificacion.
	 *
	 * @param clasificacion
	 *            the new clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	/**
	 * Gets the subclasificacion.
	 *
	 * @return the subclasificacion
	 */
	public String getSubclasificacion() {
		return subclasificacion;
	}

	/**
	 * Sets the subclasificacion.
	 *
	 * @param subclasificacion
	 *            the new subclasificacion
	 */
	public void setSubclasificacion(String subclasificacion) {
		this.subclasificacion = subclasificacion;
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

}
