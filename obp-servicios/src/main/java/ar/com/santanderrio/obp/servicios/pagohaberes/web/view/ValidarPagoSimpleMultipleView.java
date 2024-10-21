/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import java.util.List;

/**
 * The Class ValidarPagoSimpleMultipleView.
 */
public class ValidarPagoSimpleMultipleView {

	/** The pagoHaberesEmpleadosView. */
	private List<PagoInformadoView> pagoHaberesEmpleadosView;

	/** The fecha. */
	private String fecha;

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

}
