/*
 * 
 */
package ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaCanalReq;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaConfigReq;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.cnsagenda.CNSAgendaSubCanalReq;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.modificacion.ModificacionTAConfigReq;

/**
 * The Class SietePorVeintiCuatroConfigReq.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ CNSAgendaConfigReq.class, ModificacionTAConfigReq.class })
public class SietePorVeintiCuatroConfigReq {

	/** The servicio. */
	@XmlElement
	private String servicio;

	/** The version. */
	@XmlElement
	private String version;

	/** The canal. */
	@XmlElement
	private CNSAgendaCanalReq canal;

	/** The subcanal. */
	@XmlElement
	private CNSAgendaSubCanalReq subcanal;

	/** The usuario. */
	@XmlElement
	private String usuario;

	/** The clave. */
	@XmlElement
	private String clave;

	/** The sistema. */
	@XmlElement
	private String sistema;

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
	 *            the servicio to set
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
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public CNSAgendaCanalReq getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public void setCanal(CNSAgendaCanalReq canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public CNSAgendaSubCanalReq getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the subcanal to set
	 */
	public void setSubcanal(CNSAgendaSubCanalReq subcanal) {
		this.subcanal = subcanal;
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
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 *            the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

}