/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

/**
 * The Class OperacionesGrillaView.
 */
public class OperacionesGrillaView {

	/** The primer llamada. */
	protected boolean primerLlamada;

	/** The hay rellamada. */
	protected boolean hayRellamada;
	
	/** The cabecera. */
	protected DatosCesionView cabecera;
	
	/**
	 * Instantiates a new operaciones grilla view.
	 */
	public OperacionesGrillaView(){
		super();
	}

	/**
	 * Gets the primer llamada.
	 *
	 * @return the primer llamada
	 */
	public boolean getPrimerLlamada() {
		return primerLlamada;
	}

	/**
	 * Sets the primer llamada.
	 *
	 * @param primerLlamada
	 *            the new primer llamada
	 */
	public void setPrimerLlamada(boolean primerLlamada) {
		this.primerLlamada = primerLlamada;
	}

	/**
	 * Gets the hay rellamada.
	 *
	 * @return the hay rellamada
	 */
	public boolean getHayRellamada() {
		return hayRellamada;
	}

	/**
	 * Sets the hay rellamada.
	 *
	 * @param hayRellamada
	 *            the new hay rellamada
	 */
	public void setHayRellamada(boolean hayRellamada) {
		this.hayRellamada = hayRellamada;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public DatosCesionView getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(DatosCesionView cabecera) {
		this.cabecera = cabecera;
	}
	
	
	
}
