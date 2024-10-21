/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

/**
 * The Class RentabilidadTotalView.
 */
public class RentabilidadTotalView {

	/** The valores selector retail. */
	private ValoresRentabilidadCabeceraView valoresSelectorRetail;
	
	/** The valores selector privada. */
	private ValoresRentabilidadCabeceraView valoresSelectorPrivada;
	
	/** The mensaje ayuda rentabilidad pesos RTL. */
	private String mensajeAyudaRentabilidadPesosRTL;
	
	/** The mensaje ayuda rentabilidad dolares RTL. */
	private String mensajeAyudaRentabilidadDolaresRTL;

	/** The mensaje ayuda rentabilidad pesos BP. */
	private String mensajeAyudaRentabilidadPesosBP;
	
	/** The mensaje ayuda rentabilidad dolares BP. */
	private String mensajeAyudaRentabilidadDolaresBP;
	
	/** The hay error. */
	private Boolean hayError = false;

	
	/**
	 * Gets the valores selector retail.
	 *
	 * @return the valores selector retail
	 */
	public ValoresRentabilidadCabeceraView getValoresSelectorRetail() {
		return valoresSelectorRetail;
	}

	/**
	 * Sets the valores selector retail.
	 *
	 * @param valoresSelectorRetail
	 *            the new valores selector retail
	 */
	public void setValoresSelectorRetail(ValoresRentabilidadCabeceraView valoresSelectorRetail) {
		this.valoresSelectorRetail = valoresSelectorRetail;
	}

	/**
	 * Gets the valores selector privada.
	 *
	 * @return the valores selector privada
	 */
	public ValoresRentabilidadCabeceraView getValoresSelectorPrivada() {
		return valoresSelectorPrivada;
	}

	/**
	 * Sets the valores selector privada.
	 *
	 * @param valoresSelectorPrivada
	 *            the new valores selector privada
	 */
	public void setValoresSelectorPrivada(ValoresRentabilidadCabeceraView valoresSelectorPrivada) {
		this.valoresSelectorPrivada = valoresSelectorPrivada;
	}

	/**
	 * Gets the mensaje ayuda rentabilidad pesos RTL.
	 *
	 * @return the mensaje ayuda rentabilidad pesos RTL
	 */
	public String getMensajeAyudaRentabilidadPesosRTL() {
		return mensajeAyudaRentabilidadPesosRTL;
	}

	/**
	 * Sets the mensaje ayuda rentabilidad pesos RTL.
	 *
	 * @param mensajeAyudaRentabilidadPesosRTL
	 *            the new mensaje ayuda rentabilidad pesos RTL
	 */
	public void setMensajeAyudaRentabilidadPesosRTL(String mensajeAyudaRentabilidadPesosRTL) {
		this.mensajeAyudaRentabilidadPesosRTL = mensajeAyudaRentabilidadPesosRTL;
	}

	/**
	 * Gets the mensaje ayuda rentabilidad dolares RTL.
	 *
	 * @return the mensaje ayuda rentabilidad dolares RTL
	 */
	public String getMensajeAyudaRentabilidadDolaresRTL() {
		return mensajeAyudaRentabilidadDolaresRTL;
	}

	/**
	 * Sets the mensaje ayuda rentabilidad dolares RTL.
	 *
	 * @param mensajeAyudaRentabilidadDolaresRTL
	 *            the new mensaje ayuda rentabilidad dolares RTL
	 */
	public void setMensajeAyudaRentabilidadDolaresRTL(String mensajeAyudaRentabilidadDolaresRTL) {
		this.mensajeAyudaRentabilidadDolaresRTL = mensajeAyudaRentabilidadDolaresRTL;
	}

	/**
	 * Gets the mensaje ayuda rentabilidad pesos BP.
	 *
	 * @return the mensaje ayuda rentabilidad pesos BP
	 */
	public String getMensajeAyudaRentabilidadPesosBP() {
		return mensajeAyudaRentabilidadPesosBP;
	}

	/**
	 * Sets the mensaje ayuda rentabilidad pesos BP.
	 *
	 * @param mensajeAyudaRentabilidadPesosBP
	 *            the new mensaje ayuda rentabilidad pesos BP
	 */
	public void setMensajeAyudaRentabilidadPesosBP(String mensajeAyudaRentabilidadPesosBP) {
		this.mensajeAyudaRentabilidadPesosBP = mensajeAyudaRentabilidadPesosBP;
	}

	/**
	 * Gets the mensaje ayuda rentabilidad dolares BP.
	 *
	 * @return the mensaje ayuda rentabilidad dolares BP
	 */
	public String getMensajeAyudaRentabilidadDolaresBP() {
		return mensajeAyudaRentabilidadDolaresBP;
	}

	/**
	 * Sets the mensaje ayuda rentabilidad dolares BP.
	 *
	 * @param mensajeAyudaRentabilidadDolaresBP
	 *            the new mensaje ayuda rentabilidad dolares BP
	 */
	public void setMensajeAyudaRentabilidadDolaresBP(String mensajeAyudaRentabilidadDolaresBP) {
		this.mensajeAyudaRentabilidadDolaresBP = mensajeAyudaRentabilidadDolaresBP;
	}

	/**
	 * Gets the hay error.
	 *
	 * @return the hay error
	 */
	public Boolean getHayError() {
		return hayError;
	}

	/**
	 * Sets the hay error.
	 *
	 * @param hayError
	 *            the new hay error
	 */
	public void setHayError(Boolean hayError) {
		this.hayError = hayError;
	}
				
}