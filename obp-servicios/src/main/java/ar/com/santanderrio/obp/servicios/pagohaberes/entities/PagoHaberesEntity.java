/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * The Class PagoHaberes.
 *
 */

public class PagoHaberesEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8066900123547866985L;

	/** The pagos informados list. */
	private List<PagosInformadosEntity> pagosInformadosList;

	/** The pagos programados list. */
	private List<PagosProgramadosEntity> pagosProgramadosList;

	/** The total nomina. */
	private BigDecimal totalNomina;

	/** The total pagos programados. */
	private BigDecimal totalPagosProgramados;

	/**
	 * Gets the pagos informados list.
	 *
	 * @return the pagosInformadosList
	 */
	public List<PagosInformadosEntity> getPagosInformadosList() {
		return pagosInformadosList;
	}

	/**
	 * Sets the pagos informados list.
	 *
	 * @param pagosInformadosList
	 *            the pagosInformadosList to set
	 */
	public void setPagosInformadosList(List<PagosInformadosEntity> pagosInformadosList) {
		this.pagosInformadosList = pagosInformadosList;
	}

	/**
	 * Gets the pagos programados list.
	 *
	 * @return the pagosProgramadosList
	 */
	public List<PagosProgramadosEntity> getPagosProgramadosList() {
		return pagosProgramadosList;
	}

	/**
	 * Sets the pagos programados list.
	 *
	 * @param pagosProgramadosList
	 *            the pagosProgramadosList to set
	 */
	public void setPagosProgramadosList(List<PagosProgramadosEntity> pagosProgramadosList) {
		this.pagosProgramadosList = pagosProgramadosList;
	}

	/**
	 * Gets the total nomina.
	 *
	 * @return the total nomina
	 */
	public BigDecimal getTotalNomina() {
		return totalNomina;
	}

	/**
	 * Sets the total nomina.
	 *
	 * @param totalNomina
	 *            the new total nomina
	 */
	public void setTotalNomina(BigDecimal totalNomina) {
		this.totalNomina = totalNomina;
	}

	/**
	 * Gets the total pagos programados.
	 *
	 * @return the total pagos programados
	 */
	public BigDecimal getTotalPagosProgramados() {
		return totalPagosProgramados;
	}

	/**
	 * Sets the total pagos programados.
	 *
	 * @param totalPagosProgramados
	 *            the new total pagos programados
	 */
	public void setTotalPagosProgramados(BigDecimal totalPagosProgramados) {
		this.totalPagosProgramados = totalPagosProgramados;
	}

}
