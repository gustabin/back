/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenCuentaPesosView;

/**
 * The Class ConfirmacionPagoPrestamoView.
 *
 * @author B039543
 */
public class ConfirmacionPagoPrestamoView extends DetallePrestamoView {

	/** The cuenta debito. */
	private ResumenCuentaPesosView cuentaDebito;

	/** The legal detalle importes. */
	private String legalDetalleImportes;

	/** The legal detalle tasas. */
	private String legalDetalleTasas;

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuentaDebito
	 */
	public ResumenCuentaPesosView getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the cuentaDebito to set
	 */
	public void setCuentaDebito(ResumenCuentaPesosView cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the legal detalle importes.
	 *
	 * @return the legalDetalleImportes
	 */
	public String getLegalDetalleImportes() {
		return legalDetalleImportes;
	}

	/**
	 * Sets the legal detalle importes.
	 *
	 * @param legalDetalleImportes
	 *            the legalDetalleImportes to set
	 */
	public void setLegalDetalleImportes(String legalDetalleImportes) {
		this.legalDetalleImportes = legalDetalleImportes;
	}

	/**
	 * Gets the legal detalle tasas.
	 *
	 * @return the legalDetalleTasas
	 */
	public String getLegalDetalleTasas() {
		return legalDetalleTasas;
	}

	/**
	 * Sets the legal detalle tasas.
	 *
	 * @param legalDetalleTasas
	 *            the legalDetalleTasas to set
	 */
	public void setLegalDetalleTasas(String legalDetalleTasas) {
		this.legalDetalleTasas = legalDetalleTasas;
	}

}
