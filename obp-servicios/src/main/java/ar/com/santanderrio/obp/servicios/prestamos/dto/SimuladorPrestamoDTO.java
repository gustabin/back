package ar.com.santanderrio.obp.servicios.prestamos.dto;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class SimuladorPrestamoDTO.
 */
public class SimuladorPrestamoDTO extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The importe seleccionado. */
	private String importeSeleccionado;

	/** The fecha seleccionada. */
	private String fechaSeleccionada;

	/** The max imp prest. */
	private String maxImpPrest;

	/** The cuenta seleccionada. */
	private Cuenta cuentaSeleccionada;

	/** The nya cliente. */
	private String nyaCliente;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantDiasUltimoCambioClave;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantDiasUltimoCambioToken;

	/** Se usa en RSA. */
	@JsonIgnore
	private String tipoPrestamo;

	/** Se usa en RSA. */
	@JsonIgnore
	private String subproductoPrestamo;

	/** Se usa en RSA. */
	@JsonIgnore
	private String anioNacimiento;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantidadDiasCambioMail;

	/** Se usa en RSA. */
	@JsonIgnore
	private Integer cantidadDiasCambioCel;

	/** Se usa en RSA. */
	@JsonIgnore
	private String fase;

	/**
	 * Instantiates a new simulador prestamo DTO.
	 */
	public SimuladorPrestamoDTO() {
		super(OperacionesRSAEnum.PRESTAMOS);
	}

	public Integer getCantidadDiasCambioMail() {
		return cantidadDiasCambioMail;
	}

	public void setCantidadDiasCambioMail(Integer cantidadDiasCambioMail) {
		this.cantidadDiasCambioMail = cantidadDiasCambioMail;
	}

	public Integer getCantidadDiasCambioCel() {
		return cantidadDiasCambioCel;
	}

	public void setCantidadDiasCambioCel(Integer cantidadDiasCambioCel) {
		this.cantidadDiasCambioCel = cantidadDiasCambioCel;
	}

	/**
	 * Gets the importe seleccionado.
	 *
	 * @return the importe seleccionado
	 */
	public String getImporteSeleccionado() {
		return importeSeleccionado;
	}

	/**
	 * Sets the importe seleccionado.
	 *
	 * @param importeSeleccionado the new importe seleccionado
	 */
	public void setImporteSeleccionado(String importeSeleccionado) {
		this.importeSeleccionado = importeSeleccionado;
	}

	/**
	 * Gets the fecha seleccionada.
	 *
	 * @return the fecha seleccionada
	 */
	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	/**
	 * Sets the fecha seleccionada.
	 *
	 * @param fechaSeleccionada the new fecha seleccionada
	 */
	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	/**
	 * Gets the max imp prest.
	 *
	 * @return the max imp prest
	 */
	public String getMaxImpPrest() {
		return maxImpPrest;
	}

	/**
	 * Sets the max imp prest.
	 *
	 * @param maxImpPrest the new max imp prest
	 */
	public void setMaxImpPrest(String maxImpPrest) {
		this.maxImpPrest = maxImpPrest;
	}

	/**
	 * Gets the cuenta seleccionada.
	 *
	 * @return the cuenta seleccionada
	 */
	public Cuenta getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * Sets the cuenta seleccionada.
	 *
	 * @param cuenta the new cuenta seleccionada
	 */
	public void setCuentaSeleccionada(Cuenta cuenta) {
		this.cuentaSeleccionada = cuenta;
	}

	/**
	 * Gets the nya cliente.
	 *
	 * @return the nya cliente
	 */
	public String getNyaCliente() {
		return nyaCliente;
	}

	/**
	 * Sets the nya cliente.
	 *
	 * @param nyaCliente the new nya cliente
	 */
	public void setNyaCliente(String nyaCliente) {
		this.nyaCliente = nyaCliente;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

	/**
	 * @return the nombrePrestamo
	 */
	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * @param tipoPrestamo the nombrePrestamo to set
	 */
	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	/**
	 * @return the subproductoPrestamo
	 */
	public String getSubproductoPrestamo() {
		return subproductoPrestamo;
	}

	/**
	 * @return the anioNacimiento
	 */
	public String getAnioNacimiento() {
		return anioNacimiento;
	}

	/**
	 * @param subproductoPrestamo the subproductoPrestamo to set
	 */
	public void setSubproductoPrestamo(String subproductoPrestamo) {
		this.subproductoPrestamo = subproductoPrestamo;
	}

	/**
	 * @param anioNacimiento the anioNacimiento to set
	 */
	public void setAnioNacimiento(String anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

}
