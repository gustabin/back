/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

/**
 * The Class DatosConsultarTenenciaRenovableResponse.
 */
public class DatosConsultarTenenciaRenovableResponse {

	/** The cuenta titulo. */
	long cuentaTitulo;

	/** The cno. */
	String cno;

	/** The especie. */
	String especie;

	/** The especie descripcion. */
	String especieDescripcion;

	/** The lamina minima. */
	double laminaMinima;

	/** The incremento minimo. */
	double incrementoMinimo;

	/** The cantidad. */
	double cantidad;
	
	/** The cantidad maxima. */
	double cantidadMaxima;

	/** The lugar. */
	short lugar;

	/** The lugardescripcion. */
	String lugardescripcion;

	/** The coeficiente. */
	double coeficiente;
	
	/** The codigoMoneda. */
	String codigoMoneda;
	
	double precio;

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public long getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(long cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the cno.
	 *
	 * @return the cno
	 */
	public String getCno() {
		return cno;
	}

	/**
	 * Sets the cno.
	 *
	 * @param cno
	 *            the new cno
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the especie descripcion.
	 *
	 * @return the especie descripcion
	 */
	public String getEspecieDescripcion() {
		return especieDescripcion;
	}

	/**
	 * Sets the especie descripcion.
	 *
	 * @param especieDescripcion
	 *            the new especie descripcion
	 */
	public void setEspecieDescripcion(String especieDescripcion) {
		this.especieDescripcion = especieDescripcion;
	}

	/**
	 * Gets the lamina minima.
	 *
	 * @return the lamina minima
	 */
	public double getLaminaMinima() {
		return laminaMinima;
	}

	/**
	 * Sets the lamina minima.
	 *
	 * @param laminaMinima
	 *            the new lamina minima
	 */
	public void setLaminaMinima(double laminaMinima) {
		this.laminaMinima = laminaMinima;
	}

	/**
	 * Gets the incremento minimo.
	 *
	 * @return the incremento minimo
	 */
	public double getIncrementoMinimo() {
		return incrementoMinimo;
	}

	/**
	 * Sets the incremento minimo.
	 *
	 * @param incrementoMinimo
	 *            the new incremento minimo
	 */
	public void setIncrementoMinimo(double incrementoMinimo) {
		this.incrementoMinimo = incrementoMinimo;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
	/**
	 * Gets the cantidad maxima.
	 *
	 * @return the cantidad maxima
	 */
	public double getCantidadMaxima() {
		return cantidadMaxima;
	}

	/**
	 * Sets the cantidad maxima.
	 *
	 * @param cantidadMaxima
	 *            the new cantidad maxima
	 */
	public void setCantidadMaxima(double cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	
	/**
	 * Gets the lugar.
	 *
	 * @return the lugar
	 */
	public short getLugar() {
		return lugar;
	}

	/**
	 * Sets the lugar.
	 *
	 * @param lugar
	 *            the new lugar
	 */
	public void setLugar(short lugar) {
		this.lugar = lugar;
	}

	/**
	 * Gets the lugardescripcion.
	 *
	 * @return the lugardescripcion
	 */
	public String getLugardescripcion() {
		return lugardescripcion;
	}

	/**
	 * Sets the lugardescripcion.
	 *
	 * @param lugardescripcion
	 *            the new lugardescripcion
	 */
	public void setLugardescripcion(String lugardescripcion) {
		this.lugardescripcion = lugardescripcion;
	}

	/**
	 * Gets the coeficiente.
	 *
	 * @return the coeficiente
	 */
	public double getCoeficiente() {
		return coeficiente;
	}

	/**
	 * Sets the coeficiente.
	 *
	 * @param coeficiente
	 *            the new coeficiente
	 */
	public void setCoeficiente(double coeficiente) {
		this.coeficiente = coeficiente;
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
