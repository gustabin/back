/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * DatosTelefonoEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class DatosTelefonoEntity {

	/** The secuencia telefono. */
	@Field
	private String secuenciaTelefono;

	/** The secuencia domicilio. */
	@Field
	private String secuenciaDomicilio;

	/** The tipo telefono. */
	@Field
	private String tipoTelefono;

	/** The clase telefono. */
	@Field
	private String claseTelefono;

	/** The prefijo. */
	@Field
	private String prefijo;

	/** The caracteristica. */
	@Field
	private String caracteristica;

	/** The nro telefono. */
	@Field
	private String nroTelefono;

	/** The nro interno. */
	@Field
	private String nroInterno;

	/** The observaciones semaforo. */
	@Field
	private String observacionesSemaforo;

	/** The timestamp. */
	@Field
	private String timestamp;

	/**
	 * Gets the secuencia telefono.
	 *
	 * @return the secuencia telefono
	 */
	public String getSecuenciaTelefono() {
		return secuenciaTelefono;
	}

	/**
	 * Sets the secuencia telefono.
	 *
	 * @param secuenciaTelefono
	 *            the new secuencia telefono
	 */
	public void setSecuenciaTelefono(String secuenciaTelefono) {
		this.secuenciaTelefono = secuenciaTelefono;
	}

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the tipo telefono.
	 *
	 * @return the tipo telefono
	 */
	public String getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * Sets the tipo telefono.
	 *
	 * @param tipoTelefono
	 *            the new tipo telefono
	 */
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	/**
	 * Gets the clase telefono.
	 *
	 * @return the clase telefono
	 */
	public String getClaseTelefono() {
		return claseTelefono;
	}

	/**
	 * Sets the clase telefono.
	 *
	 * @param claseTelefono
	 *            the new clase telefono
	 */
	public void setClaseTelefono(String claseTelefono) {
		this.claseTelefono = claseTelefono;
	}

	/**
	 * Gets the prefijo.
	 *
	 * @return the prefijo
	 */
	public String getPrefijo() {
		return prefijo;
	}

	/**
	 * Sets the prefijo.
	 *
	 * @param prefijo
	 *            the new prefijo
	 */
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	/**
	 * Gets the caracteristica.
	 *
	 * @return the caracteristica
	 */
	public String getCaracteristica() {
		return caracteristica;
	}

	/**
	 * Sets the caracteristica.
	 *
	 * @param caracteristica
	 *            the new caracteristica
	 */
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

	/**
	 * Gets the nro telefono.
	 *
	 * @return the nro telefono
	 */
	public String getNroTelefono() {
		return nroTelefono;
	}

	/**
	 * Sets the nro telefono.
	 *
	 * @param nroTelefono
	 *            the new nro telefono
	 */
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	/**
	 * Gets the nro interno.
	 *
	 * @return the nro interno
	 */
	public String getNroInterno() {
		return nroInterno;
	}

	/**
	 * Sets the nro interno.
	 *
	 * @param nroInterno
	 *            the new nro interno
	 */
	public void setNroInterno(String nroInterno) {
		this.nroInterno = nroInterno;
	}

	/**
	 * Gets the observaciones semaforo.
	 *
	 * @return the observaciones semaforo
	 */
	public String getObservacionesSemaforo() {
		return observacionesSemaforo;
	}

	/**
	 * Sets the observaciones semaforo.
	 *
	 * @param observacionesSemaforo
	 *            the new observaciones semaforo
	 */
	public void setObservacionesSemaforo(String observacionesSemaforo) {
		this.observacionesSemaforo = observacionesSemaforo;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp
	 *            the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
