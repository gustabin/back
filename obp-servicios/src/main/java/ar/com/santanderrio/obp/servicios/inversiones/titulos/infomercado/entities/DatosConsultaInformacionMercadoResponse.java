/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaInformacionMercadoResponse.
 */
public class DatosConsultaInformacionMercadoResponse {

	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/** The cantidad detalle. */
	@JsonProperty("CantidadDetalle")
	private Integer cantidadDetalle;

	/** The lista info mercado. */
	@JsonProperty("ListaInfoMercado")
	private List<InfoMercadoResponse> listaInfoMercado;
	
	/** The ActualizacionAutomatica. */
	@JsonProperty("ActualizacionAutomatica")
	private Boolean actualizacionAutomatica;
	
	/** The TiempoActualizacion. */
	@JsonProperty("TiempoActualizacion")
	private int tiempoActualizacion;

	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigoErrorMiddleware
	 */
	public String getCodigoErrorMiddleware() {
		return codigoErrorMiddleware;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the codigoErrorMiddleware to set
	 */
	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
		this.codigoErrorMiddleware = codigoErrorMiddleware;
	}

	/**
	 * Gets the cantidad detalle.
	 *
	 * @return the cantidadDetalle
	 */
	public Integer getCantidadDetalle() {
		return cantidadDetalle;
	}

	/**
	 * Sets the cantidad detalle.
	 *
	 * @param cantidadDetalle
	 *            the cantidadDetalle to set
	 */
	public void setCantidadDetalle(Integer cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}

	/**
	 * Gets the lista info mercado.
	 *
	 * @return the listaInfoMercado
	 */
	public List<InfoMercadoResponse> getListaInfoMercado() {
		return listaInfoMercado;
	}

	/**
	 * Sets the lista info mercado.
	 *
	 * @param listaInfoMercado
	 *            the listaInfoMercado to set
	 */
	public void setListaInfoMercado(List<InfoMercadoResponse> listaInfoMercado) {
		this.listaInfoMercado = listaInfoMercado;
	}

	/**
	 * Gets the ActualizacionAutomatica.
	 *
	 * @return the ActualizacionAutomatica
	 */
	public Boolean getActualizacionAutomatica() {
		return actualizacionAutomatica;
	}

	/**
	 * Sets the lista info mercado.
	 *
	 * @param listaInfoMercado
	 *            the listaInfoMercado to set
	 */
	public void setActualizacionAutomatica(Boolean actualizacionAutomatica) {
		this.actualizacionAutomatica = actualizacionAutomatica;
	}
	
	/**
	 * Gets the ActualizacionAutomatica.
	 *
	 * @return the ActualizacionAutomatica
	 */
	public int getTiempoActualizacion() {
		return tiempoActualizacion;
	}

	/**
	 * Sets the lista info mercado.
	 *
	 * @param listaInfoMercado
	 *            the listaInfoMercado to set
	 */
	public void setTiempoActualizacion(int tiempoActualizacion) {
		this.tiempoActualizacion = tiempoActualizacion;
	}

	
}
