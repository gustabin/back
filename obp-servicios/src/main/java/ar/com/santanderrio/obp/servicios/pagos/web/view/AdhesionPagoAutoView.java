/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

/**
 * The Class AdhesionPagoAutoView.
 */
public class AdhesionPagoAutoView {

	/** desde CNSDDIADHG y MediosdePago o campo nombre servicio empresa. */
	private String nombreFantasia;

	/** el medio de pago segun selector de cuenta:. */
	private String aliasCuenta;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/** The nro cuenta producto debito. */
	private String nroCuentaProductoDebito;

	/** The id cliente en la empresa. */
	private String idClienteEnLaEmpresa;

	/** The xxx. */
	private String xxx; // TODO

	/** The nuevo limite. */
	private String nuevoLimite;

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombre fantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia
	 *            the new nombre fantasia
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	/**
	 * Gets the alias cuenta.
	 *
	 * @return the alias cuenta
	 */
	public String getAliasCuenta() {
		return aliasCuenta;
	}

	/**
	 * Sets the alias cuenta.
	 *
	 * @param aliasCuenta
	 *            the new alias cuenta
	 */
	public void setAliasCuenta(String aliasCuenta) {
		this.aliasCuenta = aliasCuenta;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the nro cuenta producto debito.
	 *
	 * @return the nro cuenta producto debito
	 */
	public String getNroCuentaProductoDebito() {
		return nroCuentaProductoDebito;
	}

	/**
	 * Sets the nro cuenta producto debito.
	 *
	 * @param nroCuentaProductoDebito
	 *            the new nro cuenta producto debito
	 */
	public void setNroCuentaProductoDebito(String nroCuentaProductoDebito) {
		this.nroCuentaProductoDebito = nroCuentaProductoDebito;
	}

	/**
	 * Gets the id cliente en la empresa.
	 *
	 * @return the id cliente en la empresa
	 */
	public String getIdClienteEnLaEmpresa() {
		return idClienteEnLaEmpresa;
	}

	/**
	 * Sets the id cliente en la empresa.
	 *
	 * @param idClienteEnLaEmpresa
	 *            the new id cliente en la empresa
	 */
	public void setIdClienteEnLaEmpresa(String idClienteEnLaEmpresa) {
		this.idClienteEnLaEmpresa = idClienteEnLaEmpresa;
	}

	/**
	 * Gets the nuevo limite.
	 *
	 * @return the nuevo limite
	 */
	public String getNuevoLimite() {
		return nuevoLimite;
	}

	/**
	 * Sets the nuevo limite.
	 *
	 * @param nuevoLimite
	 *            the new nuevo limite
	 */
	public void setNuevoLimite(String nuevoLimite) {
		this.nuevoLimite = nuevoLimite;
	}

}
