/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;

/**
 * The Class ComprobantePrestamoReporte.
 */
public class ComprobantePrestamoReporte {

	/** The descripcion prestamo. */
	private String descripcionPrestamo;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The alias cuenta debito. */
	private String aliasCuentaDebito;

	/** The valor descripcion prestamo. */
	private String valorDescripcionPrestamo;

	/** The importe cuota principal. */
	private String importeCuotaPrincipal;

	/** The titular. */
	private String titular;

	/** The cuil. */
	private String cuil;

	/** The condicion IVA. */
	private String condicionIVA;

	/** The cuenta debito. */
	private String cuentaDebito;

	/** The cuota prestamo. */
	private String cuotaPrestamo;

	/** The plazo prestamo. */
	private String plazoPrestamo;

	/** The importe cuota. */
	private String importeCuota;

	/** The importes. */
	private List<ItemReporteDetalle> importes;

	/** The tasas. */
	private List<ItemReporteDetalle> tasas;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The tipo prestamo. */
	private TipoPrestamoEnum tipoPrestamo;

	/** The nro comprobante. */
	private String nroComprobante;

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the tipo prestamo.
	 *
	 * @return the tipo prestamo
	 */
	public TipoPrestamoEnum getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * Sets the tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the new tipo prestamo
	 */
	public void setTipoPrestamo(TipoPrestamoEnum tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the alias cuenta debito.
	 *
	 * @return the alias cuenta debito
	 */
	public String getAliasCuentaDebito() {
		return aliasCuentaDebito;
	}

	/**
	 * Sets the alias cuenta debito.
	 *
	 * @param aliasCuentaDebito
	 *            the new alias cuenta debito
	 */
	public void setAliasCuentaDebito(String aliasCuentaDebito) {
		this.aliasCuentaDebito = aliasCuentaDebito;
	}

	/**
	 * Gets the importe cuota principal.
	 *
	 * @return the importe cuota principal
	 */
	public String getImporteCuotaPrincipal() {
		return importeCuotaPrincipal;
	}

	/**
	 * Sets the importe cuota principal.
	 *
	 * @param importeCuotaPrincipal
	 *            the new importe cuota principal
	 */
	public void setImporteCuotaPrincipal(String importeCuotaPrincipal) {
		this.importeCuotaPrincipal = importeCuotaPrincipal;
	}

	/**
	 * Gets the valor descripcion prestamo.
	 *
	 * @return the valor descripcion prestamo
	 */
	public String getValorDescripcionPrestamo() {
		return valorDescripcionPrestamo;
	}

	/**
	 * Sets the valor descripcion prestamo.
	 *
	 * @param valorDescripcionPrestamo
	 *            the new valor descripcion prestamo
	 */
	public void setValorDescripcionPrestamo(String valorDescripcionPrestamo) {
		this.valorDescripcionPrestamo = valorDescripcionPrestamo;
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
	 * Gets the descripcion prestamo.
	 *
	 * @return the descripcion prestamo
	 */
	public String getDescripcionPrestamo() {
		return descripcionPrestamo;
	}

	/**
	 * Sets the descripcion prestamo.
	 *
	 * @param descripcionPrestamo
	 *            the new descripcion prestamo
	 */
	public void setDescripcionPrestamo(String descripcionPrestamo) {
		this.descripcionPrestamo = descripcionPrestamo;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the cuil.
	 *
	 * @return the cuil
	 */
	public String getCuil() {
		return cuil;
	}

	/**
	 * Sets the cuil.
	 *
	 * @param cuil
	 *            the new cuil
	 */
	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	/**
	 * Gets the condicion IVA.
	 *
	 * @return the condicion IVA
	 */
	public String getCondicionIVA() {
		return condicionIVA;
	}

	/**
	 * Sets the condicion IVA.
	 *
	 * @param condicionIVA
	 *            the new condicion IVA
	 */
	public void setCondicionIVA(String condicionIVA) {
		this.condicionIVA = condicionIVA;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the cuota prestamo.
	 *
	 * @return the cuota prestamo
	 */
	public String getCuotaPrestamo() {
		return cuotaPrestamo;
	}

	/**
	 * Sets the cuota prestamo.
	 *
	 * @param cuotaPrestamo
	 *            the new cuota prestamo
	 */
	public void setCuotaPrestamo(String cuotaPrestamo) {
		this.cuotaPrestamo = cuotaPrestamo;
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
	 * Gets the importes.
	 *
	 * @return the importes
	 */
	public List<ItemReporteDetalle> getImportes() {
		return importes;
	}

	/**
	 * Sets the importes.
	 *
	 * @param importes
	 *            the new importes
	 */
	public void setImportes(List<ItemReporteDetalle> importes) {
		this.importes = importes;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<ItemReporteDetalle> getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the new tasas
	 */
	public void setTasas(List<ItemReporteDetalle> tasas) {
		this.tasas = tasas;
	}

}
