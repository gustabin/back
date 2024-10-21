/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosServicios.
 *
 * @author marcelo.ruiz
 */
public class DatosServiciosEntity extends EntityBase {

	/** The canal tipo. */
	@JsonProperty("CanalTipo")
	private String canalTipo;

	/** The canal id. */
	@JsonProperty("CanalId")
	private String canalId;

	/** The canal version. */
	@JsonProperty("CanalVersion")
	private String canalVersion;

	/** The subcanal tipo. */
	@JsonProperty("SubcanalTipo")
	private String subcanalTipo;

	/** The subcanal id. */
	@JsonProperty("SubcanalId")
	private String subcanalId;

	/** The usuario tipo. */
	@JsonProperty("UsuarioTipo")
	private String usuarioTipo;

	/** The usuario id. */
	@JsonProperty("UsuarioId")
	private String usuarioId;

	/** The usuario atrib. */
	@JsonProperty("UsuarioAtrib")
	private String usuarioAtrib;

	/** The usuario pwd. */
	@JsonProperty("UsuarioPwd")
	private String usuarioPwd;

	/** The tipo persona. */
	@JsonProperty("TipoPersona")
	private String tipoPersona;

	/** The tipo id. */
	@JsonProperty("TipoId")
	private String tipoId;

	/** The id cliente. */
	@JsonProperty("IdCliente")
	private String idCliente;

	/** The fecha nac. */
	@JsonProperty("FechaNac")
	private String fechaNac;

	/**
	 * Gets the canal tipo.
	 *
	 * @return the canal tipo
	 */
	public String getCanalTipo() {
		return canalTipo;
	}

	/**
	 * Sets the canal tipo.
	 *
	 * @param canalTipo
	 *            the new canal tipo
	 */
	public void setCanalTipo(String canalTipo) {
		this.canalTipo = canalTipo;
	}

	/**
	 * Gets the canal id.
	 *
	 * @return the canal id
	 */
	public String getCanalId() {
		return canalId;
	}

	/**
	 * Sets the canal id.
	 *
	 * @param canalId
	 *            the new canal id
	 */
	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}

	/**
	 * Gets the canal version.
	 *
	 * @return the canal version
	 */
	public String getCanalVersion() {
		return canalVersion;
	}

	/**
	 * Sets the canal version.
	 *
	 * @param canalVersion
	 *            the new canal version
	 */
	public void setCanalVersion(String canalVersion) {
		this.canalVersion = canalVersion;
	}

	/**
	 * Gets the subcanal tipo.
	 *
	 * @return the subcanal tipo
	 */
	public String getSubcanalTipo() {
		return subcanalTipo;
	}

	/**
	 * Sets the subcanal tipo.
	 *
	 * @param subcanalTipo
	 *            the new subcanal tipo
	 */
	public void setSubcanalTipo(String subcanalTipo) {
		this.subcanalTipo = subcanalTipo;
	}

	/**
	 * Gets the subcanal id.
	 *
	 * @return the subcanal id
	 */
	public String getSubcanalId() {
		return subcanalId;
	}

	/**
	 * Sets the subcanal id.
	 *
	 * @param subcanalId
	 *            the new subcanal id
	 */
	public void setSubcanalId(String subcanalId) {
		this.subcanalId = subcanalId;
	}

	/**
	 * Gets the usuario tipo.
	 *
	 * @return the usuario tipo
	 */
	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	/**
	 * Sets the usuario tipo.
	 *
	 * @param usuarioTipo
	 *            the new usuario tipo
	 */
	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	/**
	 * Gets the usuario id.
	 *
	 * @return the usuario id
	 */
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * Sets the usuario id.
	 *
	 * @param usuarioId
	 *            the new usuario id
	 */
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * Gets the usuario atrib.
	 *
	 * @return the usuario atrib
	 */
	public String getUsuarioAtrib() {
		return usuarioAtrib;
	}

	/**
	 * Sets the usuario atrib.
	 *
	 * @param usuarioAtrib
	 *            the new usuario atrib
	 */
	public void setUsuarioAtrib(String usuarioAtrib) {
		this.usuarioAtrib = usuarioAtrib;
	}

	/**
	 * Gets the usuario pwd.
	 *
	 * @return the usuario pwd
	 */
	public String getUsuarioPwd() {
		return usuarioPwd;
	}

	/**
	 * Sets the usuario pwd.
	 *
	 * @param usuarioPwd
	 *            the new usuario pwd
	 */
	public void setUsuarioPwd(String usuarioPwd) {
		this.usuarioPwd = usuarioPwd;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the tipo id.
	 *
	 * @return the tipo id
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * Sets the tipo id.
	 *
	 * @param tipoId
	 *            the new tipo id
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the id cliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente
	 *            the new id cliente
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the new fecha nac
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

}
