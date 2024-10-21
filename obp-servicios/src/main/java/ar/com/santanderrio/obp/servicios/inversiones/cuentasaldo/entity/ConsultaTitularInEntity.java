/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity;

/**
 * The Class ConsultaTitularInEntity.
 */
public class ConsultaTitularInEntity {

	/** The Constant CANAL_OPERACION. */
	private static final String CANAL_OPERACION = "79";

	/** The cuenta altair. */
	private String cuentaAltair;

	/** The sucursal altair. */
	private String sucursalAltair;

	/** The canal. */
	private String canal;

	/** The usuario. */
	private String usuario;


	/** The pass. */
	private String pass;

	/**
	 * Instantiates a new cuenta saldo in entity.
	 */
	public ConsultaTitularInEntity() {
		this.canal = CANAL_OPERACION;
	}


	/**
	 * Gets the cuenta altair.
	 *
	 * @return the cuenta altair
	 */
	public String getCuentaAltair() {
		return cuentaAltair;
	}

	/**
	 * Sets the cuenta altair.
	 *
	 * @param cuentaAltair
	 *            the new cuenta altair
	 */
	public void setCuentaAltair(String cuentaAltair) {
		this.cuentaAltair = cuentaAltair;
	}

	/**
	 * Gets the sucursal altair.
	 *
	 * @return the sucursal altair
	 */
	public String getSucursalAltair() {
		return sucursalAltair;
	}

	/**
	 * Sets the sucursal altair.
	 *
	 * @param sucursalAltair
	 *            the new sucursal altair
	 */
	public void setSucursalAltair(String sucursalAltair) {
		this.sucursalAltair = sucursalAltair;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the pass.
	 *
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the pass.
	 *
	 * @param pass
	 *            the new pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
}
