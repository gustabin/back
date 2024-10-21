/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.dto;

import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;

/**
 * The Class DatosSolicitudTarjetaRecargableDTO.
 */
public class DatosSolicitudTarjetaRecargableDTO {

	/** The nro gestion. */
	private String nroGestion;

	/** The cod retorno. */
	private String codRetorno;

	/** The desc error. */
	private String descError;

	/**
	 * Instantiates a new datos solicitud tarjeta recargable DTO.
	 *
	 * @param solicitudTarjetaRecargableOutEntity
	 *            the solicitud tarjeta recargable out entity
	 */
	public DatosSolicitudTarjetaRecargableDTO(SolicitudTarjetaRecargableOutEntity solicitudTarjetaRecargableOutEntity) {
		super();
		this.nroGestion = solicitudTarjetaRecargableOutEntity.getNroGestion();
		this.codRetorno = solicitudTarjetaRecargableOutEntity.getCodRetorno();
		this.descError = solicitudTarjetaRecargableOutEntity.getDescError();
	}

	/**
	 * Gets the nro gestion.
	 *
	 * @return the nro gestion
	 */
	public String getNroGestion() {
		return nroGestion;
	}

	/**
	 * Sets the nro gestion.
	 *
	 * @param nroGestion
	 *            the new nro gestion
	 */
	public void setNroGestion(String nroGestion) {
		this.nroGestion = nroGestion;
	}

	/**
	 * Gets the cod retorno.
	 *
	 * @return the cod retorno
	 */
	public String getCodRetorno() {
		return codRetorno;
	}

	/**
	 * Sets the cod retorno.
	 *
	 * @param codRetorno
	 *            the new cod retorno
	 */
	public void setCodRetorno(String codRetorno) {
		this.codRetorno = codRetorno;
	}

	/**
	 * Gets the desc error.
	 *
	 * @return the desc error
	 */
	public String getDescError() {
		return descError;
	}

	/**
	 * Sets the desc error.
	 *
	 * @param descError
	 *            the new desc error
	 */
	public void setDescError(String descError) {
		this.descError = descError;
	}

}
