/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import java.util.List;

/**
 * The Class PagoHaberesView.
 */
public class PagoHaberesView {

	/** The pago haberes empleados view. */
	List<PagoInformadoView> pagoHaberesEmpleadosView;

	/** The pago haberes agendados view. */
	List<PagoProgramadoView> pagoHaberesAgendadosView;

	/** The total nomina. */
	String totalNomina;

	/** The total pagos programados. */
	String totalPagosProgramados;

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
	 * Gets the pago haberes agendados view.
	 *
	 * @return the pagoHaberesAgendadosView
	 */
	public List<PagoProgramadoView> getPagoHaberesAgendadosView() {
		return pagoHaberesAgendadosView;
	}

	/**
	 * Sets the pago haberes agendados view.
	 *
	 * @param pagoHaberesAgendadosView
	 *            the pagoHaberesAgendadosView to set
	 */
	public void setPagoHaberesAgendadosView(List<PagoProgramadoView> pagoHaberesAgendadosView) {
		this.pagoHaberesAgendadosView = pagoHaberesAgendadosView;
	}

	/**
	 * Gets the total nomina.
	 *
	 * @return the total nomina
	 */
	public String getTotalNomina() {
		return totalNomina;
	}

	/**
	 * Sets the total nomina.
	 *
	 * @param totalNomina
	 *            the new total nomina
	 */
	public void setTotalNomina(String totalNomina) {
		this.totalNomina = totalNomina;
	}

	/**
	 * Gets the total pagos programados.
	 *
	 * @return the total pagos programados
	 */
	public String getTotalPagosProgramados() {
		return totalPagosProgramados;
	}

	/**
	 * Sets the total pagos programados.
	 *
	 * @param totalPagosProgramados
	 *            the new total pagos programados
	 */
	public void setTotalPagosProgramados(String totalPagosProgramados) {
		this.totalPagosProgramados = totalPagosProgramados;
	}

}
