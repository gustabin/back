/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;
import java.util.Map;

/**
 * The Class ResumenFondoDetalleEntity.
 */
public class ResumenFondoDetalleEntity {

	/** The cuenta. */
	private String cuenta;

	/** The anio. */
	private String anio;
	
	/** the participantes*/
	private List<ParticipantesEntity> participantes;

	/**
	 * @return the participantes
	 */
	public List<ParticipantesEntity> getParticipantes() {
		return participantes;
	}

	/**
	 * @param participantes the participantes to set
	 */
	public void setParticipantes(List<ParticipantesEntity> participantes) {
		this.participantes = participantes;
	}

	/** The fondos. */
	private Map<String, FondoDetalleEntity> fondos;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the fondos.
	 *
	 * @return the fondos
	 */
	public Map<String, FondoDetalleEntity> getFondos() {
		return fondos;
	}

	/**
	 * Sets the fondos.
	 *
	 * @param fondos
	 *            the fondos to set
	 */
	public void setFondos(Map<String, FondoDetalleEntity> fondos) {
		this.fondos = fondos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenFondoDetalleEntity [cuenta=" + cuenta + ", anio=" + anio + ", fondos=" + fondos + "]";
	}

}
