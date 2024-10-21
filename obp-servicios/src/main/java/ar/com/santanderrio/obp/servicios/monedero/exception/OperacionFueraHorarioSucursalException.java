/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.exception;

/**
 * The Class OperacionFueraHorarioSucursalException.
 */
public class OperacionFueraHorarioSucursalException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "La operacion se encuentra fuera de horario de la sucursal";

	/**
	 * Instantiates a new operacion fuera horario sucursal exception.
	 */
	public OperacionFueraHorarioSucursalException() {
		super(MESSAGE);
	}

}