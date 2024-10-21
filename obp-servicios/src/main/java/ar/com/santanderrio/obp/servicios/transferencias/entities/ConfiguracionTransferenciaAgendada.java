/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ConfiguracionTransferenciaAgendada.
 */
public class ConfiguracionTransferenciaAgendada {

	/** The servicio. */
	private String servicio;

	/** The version. */
	private String version;

	/** The canal tipo. */
	private String canalTipo;

	/** The canal id. */
	private String canalId;

	/** The canal version. */
	private String canalVersion;

	/** The sub canal tipo. */
	private String subCanalTipo;

	/** The sub canal id. */
	private String subCanalId;

	/** The usuario. */
	private String usuario;

	/** The clave. */
	private String clave;

	/** The nup. */
	private String nup;

	/** The sistema. */
	private String sistema;

	/** The ejecucion modo. */
	private String ejecucionModo;

	/** The nro operacion terminal. */
	private String nroOperacionTerminal;

	/**
	 * Gets the servicio.
	 *
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * Sets the servicio.
	 *
	 * @param servicio
	 *            the new servicio
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

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
	 * Gets the sub canal tipo.
	 *
	 * @return the sub canal tipo
	 */
	public String getSubCanalTipo() {
		return subCanalTipo;
	}

	/**
	 * Sets the sub canal tipo.
	 *
	 * @param subCanalTipo
	 *            the new sub canal tipo
	 */
	public void setSubCanalTipo(String subCanalTipo) {
		this.subCanalTipo = subCanalTipo;
	}

	/**
	 * Gets the sub canal id.
	 *
	 * @return the sub canal id
	 */
	public String getSubCanalId() {
		return subCanalId;
	}

	/**
	 * Sets the sub canal id.
	 *
	 * @param subCanalId
	 *            the new sub canal id
	 */
	public void setSubCanalId(String subCanalId) {
		this.subCanalId = subCanalId;
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
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the sistema.
	 *
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * Sets the sistema.
	 *
	 * @param sistema
	 *            the new sistema
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * Gets the ejecucion modo.
	 *
	 * @return the ejecucion modo
	 */
	public String getEjecucionModo() {
		return ejecucionModo;
	}

	/**
	 * Sets the ejecucion modo.
	 *
	 * @param ejecucionModo
	 *            the new ejecucion modo
	 */
	public void setEjecucionModo(String ejecucionModo) {
		this.ejecucionModo = ejecucionModo;
	}

	/**
	 * Gets the nro operacion terminal.
	 *
	 * @return the nro operacion terminal
	 */
	public String getNroOperacionTerminal() {
		return nroOperacionTerminal;
	}

	/**
	 * Sets the nro operacion terminal.
	 *
	 * @param nroOperacionTerminal
	 *            the new nro operacion terminal
	 */
	public void setNroOperacionTerminal(String nroOperacionTerminal) {
		this.nroOperacionTerminal = nroOperacionTerminal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(canalId).append(canalTipo).append(canalVersion).append(clave)
				.append(ejecucionModo).append(nroOperacionTerminal).append(nup).append(servicio).append(sistema)
				.append(subCanalId).append(subCanalTipo).append(usuario).append(version);
		return hcb.toHashCode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConfiguracionTransferenciaAgendada other = (ConfiguracionTransferenciaAgendada) obj;
		EqualsBuilder eb = new EqualsBuilder().append(canalId, other.canalId).append(canalTipo, other.canalTipo)
				.append(canalVersion, other.canalVersion).append(clave, other.clave)
				.append(ejecucionModo, other.ejecucionModo).append(nroOperacionTerminal, other.nroOperacionTerminal)
				.append(nup, other.nup).append(servicio, other.servicio).append(sistema, other.sistema)
				.append(subCanalId, other.subCanalId).append(subCanalTipo, other.subCanalTipo)
				.append(usuario, other.usuario).append(version, other.version);

		return eb.isEquals();

	}

}
