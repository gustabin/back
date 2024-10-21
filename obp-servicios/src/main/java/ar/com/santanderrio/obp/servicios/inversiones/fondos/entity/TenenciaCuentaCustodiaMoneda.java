/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class TenenciaCuentaCustodiaMoneda.
 */
public class TenenciaCuentaCustodiaMoneda implements Comparable<TenenciaCuentaCustodiaMoneda> {

	/** the GUID Error. */
	@JsonProperty("GUIDError")
	private String error;

	/** the numero cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;

	/** the sucursal. */
	@JsonProperty("SucursalCuenta")
	private String sucursalCuenta;

	/** the Codigo Especie. */
	@JsonProperty("CodigoEspecie")
	private String codigoEspecie;

	/** the Descripcion Especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;

	/** the Moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** the Tenencia Nominal. */
	@JsonProperty("TenenciaNominal")
	private String tenenciaNominal;

	/** the Cotizacion. */
	@JsonProperty("Cotizacion")
	private String cotizacion;

	/** the CodigoEstado. */
	@JsonProperty("CodigoEstado")
	private String codigoEstado;

	/** the CodigoEstado. */
	@JsonProperty("DescripcionEstado")
	private String descripcionEstado;
	
	/** the Tenencia Valuada. */
	@JsonProperty("TenenciaValuada")
	private String tenenciaValuada;

	/** the Tenencia Valuada Reexp. */
	@JsonProperty("TenenciaValuadaReexp")
	private String tenenciaValuadaReexp;

	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the tenencia nominal.
	 *
	 * @return the tenencia nominal
	 */
	public String getTenenciaNominal() {
		return tenenciaNominal;
	}

	/**
	 * Sets the tenencia nominal.
	 *
	 * @param tenenciaNominal
	 *            the new tenencia nominal
	 */
	public void setTenenciaNominal(String tenenciaNominal) {
		this.tenenciaNominal = tenenciaNominal;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the new cotizacion
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the codigo estado.
	 *
	 * @return the codigo estado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Sets the codigo estado.
	 *
	 * @param codigoEstado
	 *            the new codigo estado
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Gets the descripcion estado.
	 *
	 * @return the descripcion estado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	/**
	 * Sets the descripcion estado.
	 *
	 * @param descripcionEstado
	 *            the new descripcion estado
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenencia valuada reexp
	 */
	public String getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the new tenencia valuada reexp
	 */
	public void setTenenciaValuadaReexp(String tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TenenciaCuentaCustodiaMoneda tenencia1) {
		
//		return this.numeroCuenta.compareToIgnoreCase(tenencia1.numeroCuenta);
		
		String a= String.valueOf(this.getNumeroCuenta()) + this.getMoneda();
        String b= String.valueOf(tenencia1.getNumeroCuenta()) + tenencia1.getMoneda();
        return a.compareTo(b);
	
	}
	
	
}
