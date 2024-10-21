/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosRespuesta Cartera.
 */
public class DatosRespuestaCartera {

	/** The resultado. */
	@JsonProperty("ListaResultadoPorProducto")
	private List<TenenciaValuadaCarteraListaResultadosEntity> listaResultadoPorProducto;

	/** The resultado. */
	@JsonProperty("TenenciaTotalCarteraBP")
	private TenenciaValuadaCarteraResultadosEntity tenenciaTotalCarteraBP;

	/** The resultado. */
	@JsonProperty("TenenciaTotalCarteraRTL")
	private TenenciaValuadaCarteraResultadosEntity tenenciaTotalCarteraRTL;

	/** lista de errores*. */
	@JsonProperty("ListaErrores")
	private List<ItemError> listaErrores;

	/**
	 * Instantiates a new datos respuesta.
	 */
	public DatosRespuestaCartera() {
		listaResultadoPorProducto = new ArrayList<TenenciaValuadaCarteraListaResultadosEntity>();
		tenenciaTotalCarteraBP = new TenenciaValuadaCarteraResultadosEntity();
		tenenciaTotalCarteraRTL = new TenenciaValuadaCarteraResultadosEntity();
	}

	/**
	 * Gets the listaResultadoPorProducto.
	 *
	 * @return the listaResultadoPorProducto
	 */
	public List<TenenciaValuadaCarteraListaResultadosEntity> getListaResultadoPorProducto() {
		return listaResultadoPorProducto;
	}

	/**
	 * Sets the listaResultadoPorProducto.
	 *
	 * @param listaResultadoPorProducto
	 *            the new lista resultado por producto
	 */
	public void setListaResultadoPorProducto(
			List<TenenciaValuadaCarteraListaResultadosEntity> listaResultadoPorProducto) {
		this.listaResultadoPorProducto = listaResultadoPorProducto;
	}

	/**
	 * Gets the listaResultadoPorProducto.
	 *
	 * @return the listaResultadoPorProducto
	 */
	public TenenciaValuadaCarteraResultadosEntity getTenenciaTotalCarteraBP() {
		return tenenciaTotalCarteraBP;
	}

	/**
	 * Sets the listaResultadoPorProducto.
	 *
	 * @param TenenciaTotalCarteraBP
	 *            the new tenencia total cartera BP
	 */
	public void setTenenciaTotalCarteraBP(TenenciaValuadaCarteraResultadosEntity TenenciaTotalCarteraBP) {
		this.tenenciaTotalCarteraBP = TenenciaTotalCarteraBP;
	}

	/**
	 * Gets the listaResultadoPorProducto.
	 *
	 * @return the listaResultadoPorProducto
	 */
	public TenenciaValuadaCarteraResultadosEntity getTenenciaTotalCarteraRTL() {
		return tenenciaTotalCarteraRTL;
	}

	/**
	 * Sets the listaResultadoPorProducto.
	 *
	 * @param TenenciaTotalCarteraRTL
	 *            the new tenencia total cartera RTL
	 */
	public void setTenenciaTotalCarteraRTL(TenenciaValuadaCarteraResultadosEntity TenenciaTotalCarteraRTL) {
		this.tenenciaTotalCarteraRTL = TenenciaTotalCarteraRTL;
	}

	/**
	 * Gets the lista errores.
	 *
	 * @return the lista errores
	 */
	public List<ItemError> getListaErrores() {
		return listaErrores;
	}

	/**
	 * Sets the lista errores.
	 *
	 * @param listaErrores
	 *            the new lista errores
	 */
	public void setListaErrores(List<ItemError> listaErrores) {
		this.listaErrores = listaErrores;
	}

}