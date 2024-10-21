/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

/**
 * The Class ResultadoCalculoView.
 */
public class ResultadoCalculoView {

	/** The datos del prestamo. */
	private List<TasaView> datosDelPrestamo;

	/** The datos primera cuota. */
	private List<TasaView> datosPrimeraCuota;

	/** The tasas. */
	private List<TasaView> tasas;

	/**
	 * Gets the datos del prestamo.
	 *
	 * @return the datos del prestamo
	 */
	public List<TasaView> getDatosDelPrestamo() {
		return datosDelPrestamo;
	}

	/**
	 * Sets the datos del prestamo.
	 *
	 * @param datosDelPrestamo
	 *            the new datos del prestamo
	 */
	public void setDatosDelPrestamo(List<TasaView> datosDelPrestamo) {
		this.datosDelPrestamo = datosDelPrestamo;
	}

	/**
	 * Gets the datos primera cuota.
	 *
	 * @return the datos primera cuota
	 */
	public List<TasaView> getDatosPrimeraCuota() {
		return datosPrimeraCuota;
	}

	/**
	 * Sets the datos primera cuota.
	 *
	 * @param datosPrimeraCuota
	 *            the new datos primera cuota
	 */
	public void setDatosPrimeraCuota(List<TasaView> datosPrimeraCuota) {
		this.datosPrimeraCuota = datosPrimeraCuota;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<TasaView> getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the new tasas
	 */
	public void setTasas(List<TasaView> tasas) {
		this.tasas = tasas;
	}

}
