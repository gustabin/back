/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.dto;

/**
 * The Class CotizacionDTO.
 */
public class CotizacionDTO {

	/** Variable numeroCuentaTarjeta. */
	private String numeroCuentaTarjeta;

	/** Variable tipoTarjeta. */
	private String tipoTarjeta;

	/** Variable numeroTarjeta. */
	private String numeroTarjeta;

	/** Variable importePesos. */
	private String importePesos;

	/** Variable importeDolares. */
	private String importeDolares;

	/** Variable importePesosConvertido. */
	private String importePesosConvertido;

	/** Variable importeDolaresConvertido. */
	private String importeDolaresConvertido;

	/** Variable cotizacionDolares. */
	private String cotizacionDolares;

	/** Variable monedaPago. */
	private String monedaPago;

	/** Variable importeTotalConvertido. */
	private String importeTotalConvertido;

	/**
	 * Instantiates a new cotizacion DTO.
	 */
	public CotizacionDTO() {
		super();
	}

	/**
	 * Instantiates a new cotizacion DTO.
	 *
	 * @param error
	 *            the error
	 */
	public CotizacionDTO(Boolean error) {

		if (error) {
			this.numeroCuentaTarjeta = "--";
			this.tipoTarjeta = "--";
			this.numeroTarjeta = "--";
			this.importePesos = "--";
			this.importeDolares = "--";
			this.importeDolaresConvertido = "--";
			this.importePesosConvertido = "--";
			this.cotizacionDolares = "--";
			this.monedaPago = "--";
			this.importeTotalConvertido = "--";
		}
	}

	/**
	 * Devuelve el numeroCuentaTarjeta.
	 *
	 * @return el numeroCuentaTarjeta
	 */
	public String getNumeroCuentaTarjeta() {
		return numeroCuentaTarjeta;
	}

	/**
	 * Setea el numeroCuentaTarjeta.
	 *
	 * @param numeroCuentaTarjeta
	 *            el nuevo numeroCuentaTarjeta
	 */
	public void setNumeroCuentaTarjeta(String numeroCuentaTarjeta) {
		this.numeroCuentaTarjeta = numeroCuentaTarjeta;
	}

	/**
	 * Devuelve el tipoTarjeta.
	 *
	 * @return El tipoTarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Setea el tipoTarjeta.
	 *
	 * @param tipoTarjeta
	 *            el nuevo tipoTarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Devuelve el numeroTarjeta.
	 *
	 * @return el numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Setea el numeroTarjeta.
	 *
	 * @param numeroTarjeta
	 *            el nuevo numeroTarjeta
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Devuelve el importePesos.
	 *
	 * @return el importePesos
	 */
	public String getImportePesos() {
		return importePesos;
	}

	/**
	 * Setea el importePesos.
	 *
	 * @param importePesos
	 *            el nuevo importePesos
	 */
	public void setImportePesos(String importePesos) {
		this.importePesos = importePesos;
	}

	/**
	 * Devuelve el importeDolares.
	 *
	 * @return el importeDolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Setea el importeDolares.
	 *
	 * @param importeDolares
	 *            el nuevo importeDolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

	/**
	 * Devuelve el importePesosConvertido.
	 *
	 * @return el importePesosConvertido
	 */
	public String getImportePesosConvertido() {
		return importePesosConvertido;
	}

	/**
	 * Setea el importePesosConvertido.
	 *
	 * @param importePesosConvertido
	 *            el nuevo importePesosConvertido
	 */
	public void setImportePesosConvertido(String importePesosConvertido) {
		this.importePesosConvertido = importePesosConvertido;
	}

	/**
	 * Devuelve el importeDolaresConvertido.
	 *
	 * @return el importeDolaresConvertido
	 */
	public String getImporteDolaresConvertido() {
		return importeDolaresConvertido;
	}

	/**
	 * Setea el importeDolaresConvertido.
	 *
	 * @param importeDolaresConvertido
	 *            el nuevo importeDolaresConvertido
	 */
	public void setImporteDolaresConvertido(String importeDolaresConvertido) {
		this.importeDolaresConvertido = importeDolaresConvertido;
	}

	/**
	 * Devuelve la cotizacionDolares.
	 *
	 * @return la cotizacionDolares
	 */
	public String getCotizacionDolares() {
		return cotizacionDolares;
	}

	/**
	 * Setea la cotizacionDolares.
	 *
	 * @param cotizacionDolares
	 *            la nueva cotizacionDolares
	 */
	public void setCotizacionDolares(String cotizacionDolares) {
		this.cotizacionDolares = cotizacionDolares;
	}

	/**
	 * Devuelve la monedaPago.
	 *
	 * @return la monedaPago
	 */
	public String getMonedaPago() {
		return monedaPago;
	}

	/**
	 * Setea la monedaPago.
	 *
	 * @param monedaPago
	 *            la nueva monedaPago
	 */
	public void setMonedaPago(String monedaPago) {
		this.monedaPago = monedaPago;
	}

	/**
	 * Devuelve el importeTotalConvertido.
	 *
	 * @return el importeTotalConvertido
	 */
	public String getImporteTotalConvertido() {
		return importeTotalConvertido;
	}

	/**
	 * Setea el importeTotalConvertido.
	 *
	 * @param importeTotalConvertido
	 *            el nuevo importeTotalConvertido
	 */
	public void setImporteTotalConvertido(String importeTotalConvertido) {
		this.importeTotalConvertido = importeTotalConvertido;
	}

}
