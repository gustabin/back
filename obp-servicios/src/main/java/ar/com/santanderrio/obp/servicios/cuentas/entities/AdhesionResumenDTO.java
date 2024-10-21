/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Class AdhesionResumenDTO.
 */
public class AdhesionResumenDTO {

	/** The cuenta. */
	private Cuenta cuenta;

	/** The adhesion automatica. */
	private String adhesionAutomatica;

	/** The opinion usuario. */
	private String opinionUsuario;

	/**
	 * Instantiates a new detalle documento DTO.
	 */
	public AdhesionResumenDTO() {
		super();
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the adhesion automatica.
	 *
	 * @return the adhesion automatica
	 */
	public String getAdhesionAutomatica() {
		return adhesionAutomatica;
	}

	/**
	 * Sets the adhesion automatica.
	 *
	 * @param adhesionAutomatica
	 *            the new adhesion automatica
	 */
	public void setAdhesionAutomatica(String adhesionAutomatica) {
		this.adhesionAutomatica = adhesionAutomatica;
	}

	/**
	 * Gets the opinion usuario.
	 *
	 * @return the opinion usuario
	 */
	public String getOpinionUsuario() {
		return opinionUsuario;
	}

	/**
	 * Sets the opinion usuario.
	 *
	 * @param opinionUsuario
	 *            the new opinion usuario
	 */
	public void setOpinionUsuario(String opinionUsuario) {
		this.opinionUsuario = opinionUsuario;
	}

}
