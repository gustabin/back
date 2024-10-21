/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view;

import java.util.List;

/**
 * The Class EspecieView.
 */
public class EspecieView {

	/** The codigo especie. */
	private String codigoEspecie;
	
	/** The plazos. */
	private List<PlazoEspecieView> plazos;

	/**
	 * Gets the plazos.
	 *
	 * @return the plazos
	 */
	public List<PlazoEspecieView> getPlazos() {
		return plazos;
	}

	/**
	 * Sets the plazos.
	 *
	 * @param plazos
	 *            the plazos to set
	 */
	public void setPlazos(List<PlazoEspecieView> plazos) {
		this.plazos = plazos;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigoEspecie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

}
