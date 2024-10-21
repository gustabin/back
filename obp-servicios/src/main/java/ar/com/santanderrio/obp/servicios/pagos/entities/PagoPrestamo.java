/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;

/**
 * The Class PagoPrestamo.
 */
public class PagoPrestamo {

	/** The cuenta seleccionada. */
	private AbstractCuenta cuentaSeleccionada;

	/** The pago pendiente prestamo. */
	private PagoPendientePrestamo pagoPendientePrestamo;

	/** The id proceso. */
	private String idProceso;

	/** The comprobante prestamo. */
	private ComprobantePrestamo comprobantePrestamo;

	/** The contador intentos. */
	private ContadorIntentos contadorIntentos;

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuentaSeleccionada
	 */
	public AbstractCuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Gets the comprobante prestamo.
	 *
	 * @return the comprobantePrestamo
	 */
	public ComprobantePrestamo getComprobantePrestamo() {
		return comprobantePrestamo;
	}

	/**
	 * Sets the comprobante prestamo.
	 *
	 * @param comprobantePrestamo
	 *            the comprobantePrestamo to set
	 */
	public void setComprobantePrestamo(ComprobantePrestamo comprobantePrestamo) {
		this.comprobantePrestamo = comprobantePrestamo;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuentaSeleccionada
	 *            the cuentaSeleccionada to set
	 */
	public void setCuentaSeleccionada(AbstractCuenta cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * Gets the pago pendiente prestamo.
	 *
	 * @return the pagoPendientePrestamo
	 */
	public PagoPendientePrestamo getPagoPendientePrestamo() {
		return pagoPendientePrestamo;
	}

	/**
	 * Sets the pago pendiente prestamo.
	 *
	 * @param pagoPendientePrestamo
	 *            the pagoPendientePrestamo to set
	 */
	public void setPagoPendientePrestamo(PagoPendientePrestamo pagoPendientePrestamo) {
		this.pagoPendientePrestamo = pagoPendientePrestamo;
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso
	 *            the new id proceso
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * Gets the contador intentos.
	 *
	 * @return the contador intentos
	 */
	public ContadorIntentos getContadorIntentos() {
		return contadorIntentos;
	}

	/**
	 * Sets the contador intentos.
	 *
	 * @param contadorIntentos
	 *            the new contador intentos
	 */
	public void setContadorIntentos(ContadorIntentos contadorIntentos) {
		this.contadorIntentos = contadorIntentos;
	}

}
