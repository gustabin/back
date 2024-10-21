/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class AbstractPrestamoView.
 */

@JsonSerialize(include = Inclusion.NON_NULL)
public abstract class AbstractPrestamoView {

	/** The tipo prestamo label. */
	private String tipoPrestamoLabel;

	/** The tipo prestamo. */
	private String tipoPrestamo;

	/** The alias prestamo. */
	private String aliasPrestamo;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The importe cuota. */
	private String importeCuota;

	/** The numero cuota. */
	private String numeroCuota;

	/** The plazo prestamo. */
	private String plazoPrestamo;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The divisa. */
	private String divisa;

	/** The uvas. */
	private List<UvaView> uvas;

	/**
	 * Instantiates a new abstract prestamo view.
	 */
	public AbstractPrestamoView() {
		super();
	}

	/**
	 * Gets the importe cuota.
	 *
	 * @return the importe cuota
	 */
	public String getImporteCuota() {
		return importeCuota;
	}

	/**
	 * Sets the importe cuota.
	 *
	 * @param importeCuota
	 *            the new importe cuota
	 */
	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}

	/**
	 * Gets the numero cuota.
	 *
	 * @return the numero cuota
	 */
	public String getNumeroCuota() {
		return numeroCuota;
	}

	/**
	 * Sets the numero cuota.
	 *
	 * @param numeroCuota
	 *            the new numero cuota
	 */
	public void setNumeroCuota(String numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/**
	 * Gets the plazo prestamo.
	 *
	 * @return the plazo prestamo
	 */
	public String getPlazoPrestamo() {
		return plazoPrestamo;
	}

	/**
	 * Sets the plazo prestamo.
	 *
	 * @param plazoPrestamo
	 *            the new plazo prestamo
	 */
	public void setPlazoPrestamo(String plazoPrestamo) {
		this.plazoPrestamo = plazoPrestamo;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the tipo prestamo label.
	 *
	 * @return the tipo prestamo label
	 */
	public String getTipoPrestamoLabel() {
		return tipoPrestamoLabel;
	}

	/**
	 * Sets the tipo prestamo label.
	 *
	 * @param tipoPrestamoLabel
	 *            the new tipo prestamo label
	 */
	public void setTipoPrestamoLabel(String tipoPrestamoLabel) {
		this.tipoPrestamoLabel = tipoPrestamoLabel;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * Gets the alias prestamo.
	 *
	 * @return the alias prestamo
	 */
	public String getAliasPrestamo() {
		return aliasPrestamo;
	}

	/**
	 * Sets the alias prestamo.
	 *
	 * @param aliasPrestamo
	 *            the new alias prestamo
	 */
	public void setAliasPrestamo(String aliasPrestamo) {
		this.aliasPrestamo = aliasPrestamo;
	}

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the uvas.
	 *
	 * @return the uvas
	 */
	public List<UvaView> getUvas() {
		return uvas;
	}

	/**
	 * Sets the uvas.
	 *
	 * @param uvas
	 *            the new uvas
	 */
	public void setUvas(List<UvaView> uvas) {
		this.uvas = uvas;
	}

}