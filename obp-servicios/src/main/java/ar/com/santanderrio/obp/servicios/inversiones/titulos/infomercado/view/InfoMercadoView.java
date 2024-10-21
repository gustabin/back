/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view;

import java.util.List;

/**
 * The Class InfoMercadoView.
 */
public class InfoMercadoView {
	
	/** The parametros. */
	private List<ParametroInfoMercadoView> parametros;
	
	/** The listas especies. */
	private ListasEspeciesView listasEspecies;
	
	/** The lista plazos. */
	private List<String> listaPlazos;
	
	private Boolean actualizacionAutomatica;
	
	private int tiempoActualizacion;

	/**
	 * Gets the parametros.
	 *
	 * @return the parametros
	 */
	public List<ParametroInfoMercadoView> getParametros() {
		return parametros;
	}

	/**
	 * Sets the parametros.
	 *
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(List<ParametroInfoMercadoView> parametros) {
		this.parametros = parametros;
	}

	/**
	 * Gets the listas especies.
	 *
	 * @return the listasEspecies
	 */
	public ListasEspeciesView getListasEspecies() {
		return listasEspecies;
	}

	/**
	 * Sets the listas especies.
	 *
	 * @param listasEspecies
	 *            the listasEspecies to set
	 */
	public void setListasEspecies(ListasEspeciesView listasEspecies) {
		this.listasEspecies = listasEspecies;
	}

	/**
	 * Gets the lista plazos.
	 *
	 * @return the lista plazos
	 */
	public List<String> getListaPlazos() {
		return listaPlazos;
	}

	/**
	 * Sets the lista plazos.
	 *
	 * @param listaPlazos
	 *            the new lista plazos
	 */
	public void setListaPlazos(List<String> listaPlazos) {
		this.listaPlazos = listaPlazos;
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
