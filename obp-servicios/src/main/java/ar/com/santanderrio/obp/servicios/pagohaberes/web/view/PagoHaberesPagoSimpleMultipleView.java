/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import ar.com.santanderrio.obp.servicios.pagohaberes.entities.DatosEmpleadoPagoHaberesSimpleMultipleEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class PagoHaberesPagoSimpleMultipleView.
 */
public class PagoHaberesPagoSimpleMultipleView {

	/** The pago haberes empleados view. */
	private List<PagoInformadoView> pagoHaberesEmpleadosView;

	/** The fecha. */
	private String fecha;

	/** The modoEjecucion. */
	private Boolean modoEjecucion;

	/** The is pago simple. */
	private Boolean isPagoSimple;

	/** The primera Vez. */
	private Boolean primeraVez;

	/**
	 * Gets the modo ejecucion.
	 *
	 * @return the modoEjecucion
	 */
	public Boolean getModoEjecucion() {
		return modoEjecucion;
	}

	/**
	 * Sets the modo ejecucion.
	 *
	 * @param modoEjecucion
	 *            the modoEjecucion to set
	 */
	public void setModoEjecucion(Boolean modoEjecucion) {
		this.modoEjecucion = modoEjecucion;
	}

	/**
	 * Gets the pago haberes empleados view.
	 *
	 * @return the pagoHaberesEmpleadosView
	 */
	public List<PagoInformadoView> getPagoHaberesEmpleadosView() {
		return pagoHaberesEmpleadosView;
	}

	/**
	 * Sets the pago haberes empleados view.
	 *
	 * @param pagoHaberesEmpleadosView
	 *            the pagoHaberesEmpleadosView to set
	 */
	public void setPagoHaberesEmpleadosView(List<PagoInformadoView> pagoHaberesEmpleadosView) {
		this.pagoHaberesEmpleadosView = pagoHaberesEmpleadosView;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the checks if is pago simple.
	 *
	 * @return the checks if is pago simple
	 */
	public Boolean getIsPagoSimple() {
		return isPagoSimple;
	}

	/**
	 * Sets the checks if is pago simple.
	 *
	 * @param isPagoSimple
	 *            the new checks if is pago simple
	 */
	public void setIsPagoSimple(Boolean isPagoSimple) {
		this.isPagoSimple = isPagoSimple;
	}

	/**
	 * Gets the primera vez.
	 *
	 * @return the primera vez
	 */
	public Boolean getPrimeraVez() {
		return primeraVez;
	}

	/**
	 * Sets the primera vez.
	 *
	 * @param primeraVez
	 *            the new primera vez
	 */
	public void setPrimeraVez(Boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public BigDecimal amountTotalPayees() {
		BigDecimal total = BigDecimal.ZERO;

		for (PagoInformadoView pago : pagoHaberesEmpleadosView) {
			BigDecimal importe = new BigDecimal(pago.getImporte());
			total = total.add(importe);
		}

		return total;
	}

}
