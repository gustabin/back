/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.entities;

import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;

/**
 * The Class AumentoLimiteTransferenciaInEntity.
 */
public class AumentoLimiteTransferenciaInEntity {

	/** The cod asociacion. */
	private Integer codAsociacion;

	/** The tipo persona. */
	private String tipoPersona;

	/** The nup. */
	private Integer nup;

	/** The cod user. */
	private String codUser;

	/** The cod sector. */
	private String codSector;

	/** The comentario. */
	private String comentario;

	/** The medio ingreso. */
	private Integer medioIngreso;

	/** The info requerida. */
	private ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida;

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
	 * Gets the comentario.
	 *
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Sets the comentario.
	 *
	 * @param comentario
	 *            the new comentario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Gets the info requerida.
	 *
	 * @return the info requerida
	 */
	public ArrayOf158770343432493182NillableInfoRequeridaWS getInfoRequerida() {
		return infoRequerida;
	}

	/**
	 * Sets the info requerida.
	 *
	 * @param infoRequerida
	 *            the new info requerida
	 */
	public void setInfoRequerida(ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida) {
		this.infoRequerida = infoRequerida;
	}

	/**
	 * Gets the cod user.
	 *
	 * @return the cod user
	 */
	public String getCodUser() {
		return codUser;
	}

	/**
	 * Sets the cod user.
	 *
	 * @param codUser
	 *            the new cod user
	 */
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}

	/**
	 * Gets the cod asociacion.
	 *
	 * @return the cod asociacion
	 */
	public Integer getCodAsociacion() {
		return codAsociacion;
	}

	/**
	 * Sets the cod asociacion.
	 *
	 * @param codAsociacion
	 *            the new cod asociacion
	 */
	public void setCodAsociacion(Integer codAsociacion) {
		this.codAsociacion = codAsociacion;
	}

	/**
	 * Gets the medio ingreso.
	 *
	 * @return the medio ingreso
	 */
	public Integer getMedioIngreso() {
		return medioIngreso;
	}

	/**
	 * Sets the medio ingreso.
	 *
	 * @param medioIngreso
	 *            the new medio ingreso
	 */
	public void setMedioIngreso(Integer medioIngreso) {
		this.medioIngreso = medioIngreso;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public Integer getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(Integer nup) {
		this.nup = nup;
	}

	/**
	 * Gets the cod sector.
	 *
	 * @return the cod sector
	 */
	public String getCodSector() {
		return codSector;
	}

	/**
	 * Sets the cod sector.
	 *
	 * @param codSector
	 *            the new cod sector
	 */
	public void setCodSector(String codSector) {
		this.codSector = codSector;
	}

}
