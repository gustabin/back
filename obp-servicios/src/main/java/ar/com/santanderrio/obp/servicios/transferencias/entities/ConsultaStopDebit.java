/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

/**
 * The Class ConsultaStopDebit.
 * 
 * @version 2. Manuel.
 */
public class ConsultaStopDebit extends ConsultaEntity {

	/** Serial. */
	private static final long serialVersionUID = 8912869066665191077L;

	// nuevo:
	/** The id transaccion. */
	private String idTransaccion;

	/** The nro recurrencia. */
	private String nroRecurrencia;

	/**
	 * Gets the id transaccion.
	 *
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Sets the id transaccion.
	 *
	 * @param idTransaccion
	 *            the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Gets the nro recurrencia.
	 *
	 * @return the nroRecurrencia
	 */
	public String getNroRecurrencia() {
		return nroRecurrencia;
	}

	/**
	 * Sets the nro recurrencia.
	 *
	 * @param nroRecurrencia
	 *            the nroRecurrencia to set
	 */
	public void setNroRecurrencia(String nroRecurrencia) {
		this.nroRecurrencia = nroRecurrencia;
	}
}
