/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class MovimientoDeCuentaView.
 */
public class MovimientoDeCuentaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The sucursal. */
	private String sucursal = "";

	/** The numero de cuenta. */
	private String numeroDeCuenta = "";

	/** The divisa. */
	private String divisa = "";

	/** The origen transaccion. */
	private String origenTransaccion = "";

	/** The hora. */
	private String hora = "";

	/** The descripcion de operacion. */
	private String descripcionDeOperacion = "";

	/** The numero de ticket. */
	private String numeroDeTicket = "";

	/** The importe operacion. */
	private String importeOperacion = "";

	/** The fecha. */
	private String fecha = "";

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Setter para sucursal.
	 *
	 * @param sucursal
	 *            el nuevo sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the numero de cuenta.
	 *
	 * @return the numero de cuenta
	 */
	public String getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	/**
	 * Setter para numero de cuenta.
	 *
	 * @param numeroDeCuenta
	 *            el nuevo numero de cuenta
	 */
	public void setNumeroDeCuenta(String numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
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
	 * Setter para divisa.
	 *
	 * @param divisa
	 *            el nuevo divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the origen transaccion.
	 *
	 * @return the origen transaccion
	 */
	public String getOrigenTransaccion() {
		return origenTransaccion;
	}

	/**
	 * Setter para origen transaccion.
	 *
	 * @param origenTransaccion
	 *            el nuevo origen transaccion
	 */
	public void setOrigenTransaccion(String origenTransaccion) {
		this.origenTransaccion = origenTransaccion;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Setter para hora.
	 *
	 * @param hora
	 *            el nuevo hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the numero de ticket.
	 *
	 * @return the numero de ticket
	 */
	public String getNumeroDeTicket() {
		return numeroDeTicket;
	}

	/**
	 * Setter para numero de ticket.
	 *
	 * @param numeroDeTicket
	 *            el nuevo numero de ticket
	 */
	public void setNumeroDeTicket(String numeroDeTicket) {
		this.numeroDeTicket = numeroDeTicket;
	}

	/**
	 * Gets the importe operacion.
	 *
	 * @return the importe operacion
	 */
	public String getImporteOperacion() {
		return importeOperacion;
	}

	/**
	 * Setter para importe operacion.
	 *
	 * @param importeOperacion
	 *            el nuevo importe operacion
	 */
	public void setImporteOperacion(String importeOperacion) {
		this.importeOperacion = importeOperacion;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * Setter para fecha.
	 *
	 * @param fecha
	 *            el nuevo fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the descripcion de operacion.
	 *
	 * @return the descripcion de operacion
	 */
	public String getDescripcionDeOperacion() {
		return descripcionDeOperacion;
	}

	/**
	 * Setter para descripcion de operacion.
	 *
	 * @param descripcionDeOperacion
	 *            el nuevo descripcion de operacion
	 */
	public void setDescripcionDeOperacion(String descripcionDeOperacion) {
		this.descripcionDeOperacion = descripcionDeOperacion;
	}
}
