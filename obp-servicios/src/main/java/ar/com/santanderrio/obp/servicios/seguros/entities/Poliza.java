/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

/**
 * The Class Poliza.
 */
public class Poliza {
	/*
	 * {"codigoRespuesta":"0","mensaje":"TransacciÃ³n exitosa","respuesta":[{
	 * "titulo":"Accidentes personales Zurich Santander","codigoAseguradora":999
	 * ,"aseguradora":"TELEMARKETING BSR","descripcionCorta":"VISA XXXX-1157"
	 * ,"cuota":28.76,"sumaAsegurada":150000,"codigoRamo":1,"numeroPoliza":31,
	 * "numeroCertificado":245,"numeroComprobante":0},{
	 * "titulo":"Accidentes personales Zurich Santander","codigoAseguradora":1,
	 * "aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"VISA XXXX-1157","cuota":151.49,"sumaAsegurada":
	 * 400000,"codigoRamo":1,"numeroPoliza":31,"numeroCertificado":369,
	 * "numeroComprobante":0},{"titulo":"Vida Zurich Santander"
	 * ,"codigoAseguradora":1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"","cuota":0,"sumaAsegurada":19800,"codigoRamo":18,
	 * "numeroPoliza":300060,"numeroCertificado":937079,"numeroComprobante":0},{
	 * "titulo":"Vida Zurich Santander","codigoAseguradora":1,
	 * "aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"VISA XXXX-1157","cuota":967.44,"sumaAsegurada":
	 * 500000,"codigoRamo":18,"numeroPoliza":300086,"numeroCertificado":85602,
	 * "numeroComprobante":0},{"titulo":"Ingreso protegido","codigoAseguradora":
	 * 1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"VISA XXXX-1157","cuota":83.05,"sumaAsegurada":11400,
	 * "codigoRamo":20,"numeroPoliza":2,"numeroCertificado":391,
	 * "numeroComprobante":0},{"titulo":"Vivienda Zurich Santander"
	 * ,"codigoAseguradora":1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"VISA XXXX-1157","cuota":182.76,"sumaAsegurada":
	 * 400000,"codigoRamo":21,"numeroPoliza":1824517,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"ProtecciÃ³n mÃ³vil","codigoAseguradora"
	 * :1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":46.6,"sumaAsegurada":
	 * 1980,"codigoRamo":25,"numeroPoliza":315,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"ProtecciÃ³n mÃ³vil","codigoAseguradora"
	 * :1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":46.6,"sumaAsegurada":
	 * 1980,"codigoRamo":25,"numeroPoliza":319,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"ProtecciÃ³n mÃ³vil","codigoAseguradora"
	 * :1,"aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":26.24,"sumaAsegurada":
	 * 1100,"codigoRamo":25,"numeroPoliza":346,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"ProtecciÃ³n mÃ³vil","codigoAseguradora"
	 * :963,"aseguradora":"APP MOBILE","descripcionCorta":"CC $ 211-000000000/0"
	 * ,"cuota":30.85,"sumaAsegurada":1300,"codigoRamo":25,"numeroPoliza":387,
	 * "numeroCertificado":0,"numeroComprobante":0},{
	 * "titulo":"ProtecciÃ³n mÃ³vil","codigoAseguradora":963,
	 * "aseguradora":"APP MOBILE","descripcionCorta":"CC $ 294-000000000/0"
	 * ,"cuota":37.82,"sumaAsegurada":1600,"codigoRamo":25,"numeroPoliza":389,
	 * "numeroCertificado":0,"numeroComprobante":0},{
	 * "titulo":"Compra protegida Zurich Santander","codigoAseguradora":1,
	 * "aseguradora":"ZURICH SANTANDER SEGUROS ARGENTINA"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":22.31,"sumaAsegurada":
	 * 5000,"codigoRamo":26,"numeroPoliza":195,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"Compra protegida Zurich Santander"
	 * ,"codigoAseguradora":963,"aseguradora":"APP MOBILE"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":44.19,"sumaAsegurada":
	 * 6600,"codigoRamo":26,"numeroPoliza":239,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"Compra protegida Zurich Santander"
	 * ,"codigoAseguradora":963,"aseguradora":"APP MOBILE"
	 * ,"descripcionCorta":"CC $ 211-000000000/0","cuota":25.04,"sumaAsegurada":
	 * 6600,"codigoRamo":26,"numeroPoliza":273,"numeroCertificado":0,
	 * "numeroComprobante":0},{"titulo":"Autos","codigoAseguradora":996,
	 * "aseguradora":"ONLINE BANKING","descripcionCorta":"EZD179","cuota":0,
	 * "sumaAsegurada":29400,"codigoRamo":35,"numeroPoliza":2289807,
	 * "numeroCertificado":0,"numeroComprobante":0},{"titulo":"Autos",
	 * "codigoAseguradora":996,"aseguradora":"ONLINE BANKING","descripcionCorta"
	 * :"FWM246","cuota":0,"sumaAsegurada":71400,"codigoRamo":35,"numeroPoliza":
	 * 2326582,"numeroCertificado":0,"numeroComprobante":0}]}
	 */

	/** The titulo. */
	private String titulo;

	/** The codigo aseguradora. */
	private Long codigoAseguradora;

	/** The aseguradora. */
	private String aseguradora;

	/** The descripcion corta. */
	private String descripcionCorta;

	/** The cuota. */
	private String cuota;

	/** The suma asegurada. */
	private String sumaAsegurada;

	/** The codigo ramo. */
	private Long codigoRamo;

	/** The numero poliza. */
	private String numeroPoliza;

	/** The numero certificado. */
	private String numeroCertificado;

	/** The numero comprobante. */
	private String numeroComprobante;

	private Long estadoPoliza;

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the codigo aseguradora.
	 *
	 * @return the codigo aseguradora
	 */
	public Long getCodigoAseguradora() {
		return codigoAseguradora;
	}

	/**
	 * Sets the codigo aseguradora.
	 *
	 * @param codigoAseguradora
	 *            the new codigo aseguradora
	 */
	public void setCodigoAseguradora(Long codigoAseguradora) {
		this.codigoAseguradora = codigoAseguradora;
	}

	/**
	 * Gets the aseguradora.
	 *
	 * @return the aseguradora
	 */
	public String getAseguradora() {
		return aseguradora;
	}

	/**
	 * Sets the aseguradora.
	 *
	 * @param aseguradora
	 *            the new aseguradora
	 */
	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	/**
	 * Gets the descripcion corta.
	 *
	 * @return the descripcion corta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * Sets the descripcion corta.
	 *
	 * @param descripcionCorta
	 *            the new descripcion corta
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * Gets the cuota.
	 *
	 * @return the cuota
	 */
	public String getCuota() {
		return cuota;
	}

	/**
	 * Sets the cuota.
	 *
	 * @param cuota
	 *            the new cuota
	 */
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}

	/**
	 * Gets the suma asegurada.
	 *
	 * @return the suma asegurada
	 */
	public String getSumaAsegurada() {
		return sumaAsegurada;
	}

	/**
	 * Sets the suma asegurada.
	 *
	 * @param sumaAsegurada
	 *            the new suma asegurada
	 */
	public void setSumaAsegurada(String sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	/**
	 * Gets the codigo ramo.
	 *
	 * @return the codigo ramo
	 */
	public Long getCodigoRamo() {
		return codigoRamo;
	}

	/**
	 * Sets the codigo ramo.
	 *
	 * @param codigoRamo
	 *            the new codigo ramo
	 */
	public void setCodigoRamo(Long codigoRamo) {
		this.codigoRamo = codigoRamo;
	}

	/**
	 * Gets the numero poliza.
	 *
	 * @return the numero poliza
	 */
	public String getNumeroPoliza() {
		return numeroPoliza;
	}

	/**
	 * Sets the numero poliza.
	 *
	 * @param numeroPoliza
	 *            the new numero poliza
	 */
	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	/**
	 * Gets the numero certificado.
	 *
	 * @return the numero certificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public Long getEstadoPoliza() {
		return estadoPoliza;
	}

	public void setEstadoPoliza(Long estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
	}
}
