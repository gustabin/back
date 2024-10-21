package ar.com.santanderrio.obp.servicios.getnet.dto;

import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;

/**
 * The Class GetNetDTO.
 */
public class GetnetAdhesionDTO {
	
	/** The email. */
	private String email;
	
	/** The celular. */
	private String celular;
	
	/** The cbu. */
	private String cbu;
	
	/** The nombre fantasia. */
	private String nombreFantasia;
	
	/** The actividad. */
	private String actividad;
	
	/** The ingresos. */
	private String ingresos;
	
	/** The ingreso. */
	private Integer ingreso;
	
	/** The numeroCuenta. */
	private String numeroCuenta;
	
	/** The descripcionCuenta. */
	private String descripcionCuenta;
	
	/** The numeroComprobante. */
	private String numeroComprobante;
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}
	
	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}
	
	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombreFantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantansia.
	 *
	 * @param nombreFantasia
	 *            the nombre fantasia to set
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}
	
	/**
	 * Gets the actividad.
	 *
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}
	
	/**
	 * Sets the actividad.
	 *
	 * @param actividad
	 *            the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	
	/**
	 * Gets the ingresos.
	 *
	 * @return the ingresos
	 */
	public String getIngresos() {
		return ingresos;
	}
	
	/**
	 * Sets the ingresos.
	 *
	 * @param ingresos
	 *            the ingresos to set
	 */
	public void setIngresos(String ingresos) {
		this.ingresos = ingresos;
	}
	
	/**
	 * Gets the ingreso.
	 *
	 * @return the ingreso
	 */
	public Integer getIngreso() {
		return ingreso;
	}
	
	/**
	 * Sets the ingreso.
	 *
	 * @param ingreso
	 *            the ingreso to set
	 */
	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}
	
	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	/**
	 * Gets the descripcion cuenta.
	 *
	 * @return the descripcionCuenta
	 */
	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}
	
	/**
	 * Sets the descripcion cuenta.
	 *
	 * @param descripcionCuenta
	 *            the descripcion cuenta to set
	 */
	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}
	
	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	
	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numero comprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
	/**
	 * 
	 */
	public GetnetAdhesionDTO(GetnetAdhesionInView view) {
		this.email = view.getEmail();
		this.celular = view.getCelular();
		this.nombreFantasia = view.getNombreFantasia();
		this.cbu = view.getCbu();
		this.actividad = view.getActividad();
		this.ingreso = view.getIngreso();
		this.ingresos = view.getIngresos();
		this.numeroCuenta = view.getNumeroCuenta();
		this.descripcionCuenta = view.getDescripcionCuenta();
	}
	
}
