/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

import java.util.Date;

import ar.com.santanderrio.obp.servicios.inversiones.comun.OrdenBaseView;

/**
 * The Class OrdenPlazoFijoView.
 */
public class OrdenPlazoFijoView extends OrdenBaseView{

	/** The tipo plazo fijo. */
	private String tipoPlazoFijo;
	
	/** The plazo. */
	private String plazo;
	
	/** The tna. */
	private String tna;
	
	/** The accion al vencimiento. */
	private String accionAlVencimiento;
	
	/** The capital. */
	private String capital;
	
	/** The moneda liq. */
	private String monedaLiq;
	
	/** The puede confirmar. */
	private boolean puedeConfirmar;
	
	/** The fecha liquidacion. */
	private Date fechaLiquidacion;

	/**
	 * Gets the tipo plazo fijo.
	 *
	 * @return the tipo plazo fijo
	 */
	public String getTipoPlazoFijo() {
		return tipoPlazoFijo;
	}

	/**
	 * Sets the tipo plazo fijo.
	 *
	 * @param tipoPlazoFijo
	 *            the new tipo plazo fijo
	 */
	public void setTipoPlazoFijo(String tipoPlazoFijo) {
		this.tipoPlazoFijo = tipoPlazoFijo;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the accion al vencimiento.
	 *
	 * @return the accion al vencimiento
	 */
	public String getAccionAlVencimiento() {
		return accionAlVencimiento;
	}

	/**
	 * Sets the accion al vencimiento.
	 *
	 * @param accionAlVencimiento
	 *            the new accion al vencimiento
	 */
	public void setAccionAlVencimiento(String accionAlVencimiento) {
		this.accionAlVencimiento = accionAlVencimiento;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the moneda liq.
	 *
	 * @return the moneda liq
	 */
	public String getMonedaLiq() {
		return monedaLiq;
	}

	/**
	 * Sets the moneda liq.
	 *
	 * @param monedaLiq
	 *            the new moneda liq
	 */
	public void setMonedaLiq(String monedaLiq) {
		this.monedaLiq = monedaLiq;
	}

	/**
	 * Checks if is puede confirmar.
	 *
	 * @return true, if is puede confirmar
	 */
	public boolean isPuedeConfirmar() {
		return puedeConfirmar;
	}

	/**
	 * Sets the puede confirmar.
	 *
	 * @param puedeConfirmar
	 *            the new puede confirmar
	 */
	public void setPuedeConfirmar(boolean puedeConfirmar) {
		this.puedeConfirmar = puedeConfirmar;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
}
