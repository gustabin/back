/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenAnterior;

/**
 * View para la para la pantalla de resumenes anteriores de tarjetas.
 */
public class ResumenesAnterioresView {

	/** The marca. */
	private String marca;

	/** The numero. */
	private String numero;

	/** The resumenes. */
	private List<ResumenAnterior> resumenes;

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the resumenes.
	 *
	 * @return the resumenes
	 */
	public List<ResumenAnterior> getResumenes() {
		return resumenes;
	}

	/**
	 * Sets the resumenes.
	 *
	 * @param resumenes
	 *            the new resumenes
	 */
	public void setResumenes(List<ResumenAnterior> resumenes) {
		this.resumenes = resumenes;
	}

}
