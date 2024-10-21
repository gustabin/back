package ar.com.santanderrio.obp.servicios.getnet.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GetNetView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GetnetAdhesionView {
	
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
	private Integer ingresos;
	
	/** The numeroCuenta. */
	private String numeroCuenta;
	
	/** The descripcionCuenta. */
	private String descripcionCuenta;
	
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
	public Integer getIngresos() {
		return ingresos;
	}
	
	/**
	 * Sets the ingresos.
	 *
	 * @param ingresos
	 *            the ingresos to set
	 */
	public void setIngresos(Integer ingresos) {
		this.ingresos = ingresos;
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
	
}
