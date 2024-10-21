/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;

/**
 * The Class DatosDetalleMovimientosPeriodoRequest.
 */
public class DatosDetalleMovimientosPeriodoRequest {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The lista cuentas. */
	@JsonProperty("ListaCuentas")
	private List<CuentaOPEntity> listaCuentas = new ArrayList<CuentaOPEntity>();
	
	/** The cartera A consultar. */
	@JsonProperty("CarteraAConsultar")
	private String carteraAConsultar;
	
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

	/** The clasificacion. */
	@JsonProperty("Clasificacion")
	private String clasificacion;
	
	/** The subclasificacion. */
	@JsonProperty("Subclasificacion")
	private String subclasificacion;

	/** The agrupacion. */
	@JsonProperty("Agrupacion")
	private String agrupacion;
	
	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;
	
	/** The datos servicios. */
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;

	
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
	 * Gets the agrupacion.
	 *
	 * @return the agrupacion
	 */
	public String getAgrupacion() {
		return agrupacion;
	}

	/**
	 * Sets the agrupacion.
	 *
	 * @param agrupacion
	 *            the new agrupacion
	 */
	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}

	/**
	 * Gets the instrumento.
	 *
	 * @return the instrumento
	 */
	public String getInstrumento() {
		return instrumento;
	}

	/**
	 * Sets the instrumento.
	 *
	 * @param instrumento
	 *            the new instrumento
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
