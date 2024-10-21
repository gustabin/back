/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view;

import java.util.List;

/**
 * The Class InstrumentosEspecieView.
 */
public class InstrumentosEspecieView {

	/** The titulos publicos. */
	private List<PlazoEspecieView> titulosPublicos;
	
	/** The titulos privados. */
	private List<PlazoEspecieView> titulosPrivados;
	
	/** The acciones. */
	private List<PlazoEspecieView> acciones;
	
	/** The cedears. */
	private List<PlazoEspecieView> cedears;

	/**
	 * Gets the titulos publicos.
	 *
	 * @return the titulosPublicos
	 */
	public List<PlazoEspecieView> getTitulosPublicos() {
		return titulosPublicos;
	}

	/**
	 * Sets the titulos publicos.
	 *
	 * @param titulosPublicos
	 *            the titulosPublicos to set
	 */
	public void setTitulosPublicos(List<PlazoEspecieView> titulosPublicos) {
		this.titulosPublicos = titulosPublicos;
	}

	/**
	 * Gets the titulos privados.
	 *
	 * @return the titulosPrivados
	 */
	public List<PlazoEspecieView> getTitulosPrivados() {
		return titulosPrivados;
	}

	/**
	 * Sets the titulos privados.
	 *
	 * @param titulosPrivados
	 *            the titulosPrivados to set
	 */
	public void setTitulosPrivados(List<PlazoEspecieView> titulosPrivados) {
		this.titulosPrivados = titulosPrivados;
	}

	/**
	 * Gets the acciones.
	 *
	 * @return the acciones
	 */
	public List<PlazoEspecieView> getAcciones() {
		return acciones;
	}

	/**
	 * Sets the acciones.
	 *
	 * @param acciones
	 *            the acciones to set
	 */
	public void setAcciones(List<PlazoEspecieView> acciones) {
		this.acciones = acciones;
	}

	/**
	 * Gets the cedears.
	 *
	 * @return the cedears
	 */
	public List<PlazoEspecieView> getCedears() {
		return cedears;
	}

	/**
	 * Sets the cedears.
	 *
	 * @param cedears
	 *            the cedears to set
	 */
	public void setCedears(List<PlazoEspecieView> cedears) {
		this.cedears = cedears;
	}

}
