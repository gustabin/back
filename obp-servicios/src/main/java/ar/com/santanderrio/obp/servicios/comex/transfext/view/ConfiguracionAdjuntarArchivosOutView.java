/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

/**
 * The Class ConfiguracionAdjuntarArchivosOutView.
 */
public class ConfiguracionAdjuntarArchivosOutView {
	
	/** The tamanioMaximoArchivo. */
	private String tamMaximoArchivo;
	
	/** The cantidadMaxDeArchivosPosiblesAjuntos. */
	private String cantidadMaxDeArchivosPosiblesAjuntos;
	
	/** The tiposDeArchivosSoportados. */
	private String tiposDeArchivosSoportados;
	
	/** The adjuntarActivo. */
	private Boolean adjuntarActivo;

	/** The conceptosValidos. */
	private List<String> conceptosValidos;
		

	/**
	 * Gets the tam maximo archivo.
	 *
	 * @return the tamMaximoArchivo
	 */
	public String getTamMaximoArchivo() {
		return tamMaximoArchivo;
	}

	/**
	 * Sets the tam maximo archivo.
	 *
	 * @param tamMaximoArchivo
	 *            the tamMaximoArchivo to set
	 */
	public void setTamMaximoArchivo(String tamMaximoArchivo) {
		this.tamMaximoArchivo = tamMaximoArchivo;
	}

	/**
	 * Gets the cantidad max de archivos posibles ajuntos.
	 *
	 * @return the cantidadMaxDeArchivosPosiblesAjuntos
	 */
	public String getCantidadMaxDeArchivosPosiblesAjuntos() {
		return cantidadMaxDeArchivosPosiblesAjuntos;
	}

	/**
	 * Sets the cantidad max de archivos posibles ajuntos.
	 *
	 * @param cantidadMaxDeArchivosPosiblesAjuntos
	 *            the cantidadMaxDeArchivosPosiblesAjuntos to set
	 */
	public void setCantidadMaxDeArchivosPosiblesAjuntos(String cantidadMaxDeArchivosPosiblesAjuntos) {
		this.cantidadMaxDeArchivosPosiblesAjuntos = cantidadMaxDeArchivosPosiblesAjuntos;
	}

	/**
	 * Gets the tipos de archivos soportados.
	 *
	 * @return the tiposDeArchivosSoportados
	 */
	public String getTiposDeArchivosSoportados() {
		return tiposDeArchivosSoportados;
	}

	/**
	 * Sets the tipos de archivos soportados.
	 *
	 * @param tiposDeArchivosSoportados
	 *            the tiposDeArchivosSoportados to set
	 */
	public void setTiposDeArchivosSoportados(String tiposDeArchivosSoportados) {
		this.tiposDeArchivosSoportados = tiposDeArchivosSoportados;
	}

	/**
	 * Gets the adjuntar activo.
	 *
	 * @return the adjuntarActivo
	 */
	public Boolean getAdjuntarActivo() {
		return adjuntarActivo;
	}

	/**
	 * Sets the adjuntar activo.
	 *
	 * @param adjuntarActivo
	 *            the adjuntarActivo to set
	 */
	public void setAdjuntarActivo(Boolean adjuntarActivo) {
		this.adjuntarActivo = adjuntarActivo;
	}

	public List<String> getConceptosValidos() {
		return conceptosValidos;
	}

	public void setConceptosValidos(List<String> conceptosValidos) {
		this.conceptosValidos = conceptosValidos;
	}

}
