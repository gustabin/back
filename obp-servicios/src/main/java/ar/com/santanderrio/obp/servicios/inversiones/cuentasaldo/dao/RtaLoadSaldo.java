/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

/**
 * Clase que Modela Resultado del store.
 */

public class RtaLoadSaldo {

	/** The cuentasaldo. */
	private String cuentasaldo;

	/** The descripcion. */
	private String descripcion;

	/** The sucursal. */
	private String sucursal;

	/** The asesor. */
	private String asesor;

	/** The fecha. */
	private String fecha;

	/** The c ahorro pesos. */
	private String cAhorroPesos;

	/** The c ahorro dolares. */
	private String cAhorroDolares;

	
	/**
	 * Instantiates a new rta load saldo.
	 */
	public RtaLoadSaldo() {
		super();
	}
	
	/**
	 * Instantiates a new rta load saldo.
	 *
	 * @param cuentasaldo
	 *            the cuentasaldo
	 * @param descripcion
	 *            the descripcion
	 * @param sucursal
	 *            the sucursal
	 * @param asesor
	 *            the asesor
	 * @param fecha
	 *            the fecha
	 * @param cAhorroPesos
	 *            the c ahorro pesos
	 * @param cAhorroDolares
	 *            the c ahorro dolares
	 */
	public RtaLoadSaldo(String cuentasaldo, String descripcion, String sucursal, String asesor, String fecha,
			String cAhorroPesos, String cAhorroDolares) {

		this.cuentasaldo = cuentasaldo;
		this.descripcion = descripcion;
		this.sucursal = sucursal;
		this.asesor = asesor;
		this.fecha = fecha;
		this.cAhorroPesos = cAhorroPesos;
		this.cAhorroDolares = cAhorroDolares;
	}

	/**
	 * Gets the cuentasaldo.
	 *
	 * @return the cuentasaldo
	 */
	public String getCuentasaldo() {
		return cuentasaldo;
	}

	/**
	 * Sets the cuentasaldo.
	 *
	 * @param cuentasaldo
	 *            the new cuentasaldo
	 */
	public void setCuentasaldo(String cuentasaldo) {
		this.cuentasaldo = cuentasaldo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the asesor.
	 *
	 * @return the asesor
	 */
	public String getAsesor() {
		return asesor;
	}

	/**
	 * Sets the asesor.
	 *
	 * @param asesor
	 *            the new asesor
	 */
	public void setAsesor(String asesor) {
		this.asesor = asesor;
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
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the c ahorro pesos.
	 *
	 * @return the c ahorro pesos
	 */
	public String getcAhorroPesos() {
		return cAhorroPesos;
	}

	/**
	 * Sets the c ahorro pesos.
	 *
	 * @param cAhorroPesos
	 *            the new c ahorro pesos
	 */
	public void setcAhorroPesos(String cAhorroPesos) {
		this.cAhorroPesos = cAhorroPesos;
	}

	/**
	 * Gets the c ahorro dolares.
	 *
	 * @return the c ahorro dolares
	 */
	public String getcAhorroDolares() {
		return cAhorroDolares;
	}

	/**
	 * Sets the c ahorro dolares.
	 *
	 * @param cAhorroDolares
	 *            the new c ahorro dolares
	 */
	public void setcAhorroDolares(String cAhorroDolares) {
		this.cAhorroDolares = cAhorroDolares;
	}

}