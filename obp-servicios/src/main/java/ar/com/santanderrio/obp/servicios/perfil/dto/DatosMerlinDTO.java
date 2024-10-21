/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

import java.util.List;

/**
 * Class DatosMerlinDTO.
 *
 * @author Silvina_Luque
 */
public class DatosMerlinDTO {

	/** The resultado merlin. */
	private String resultadoMerlin;

	/** The opciones domicilio. */
	private List<CambioDomicilioDTO> opcionesDomicilio;

	/**
	 * Gets the resultado merlin.
	 *
	 * @return the resultado merlin
	 */
	public String getResultadoMerlin() {
		return resultadoMerlin;
	}

	/**
	 * Sets the resultado merlin.
	 *
	 * @param resultadoMerlin
	 *            the new resultado merlin
	 */
	public void setResultadoMerlin(String resultadoMerlin) {
		this.resultadoMerlin = resultadoMerlin;
	}

	/**
	 * Gets the opciones domicilio.
	 *
	 * @return the opciones domicilio
	 */
	public List<CambioDomicilioDTO> getOpcionesDomicilio() {
		return opcionesDomicilio;
	}

	/**
	 * Sets the opciones domicilio.
	 *
	 * @param opcionesDomicilio
	 *            the new opciones domicilio
	 */
	public void setOpcionesDomicilio(List<CambioDomicilioDTO> opcionesDomicilio) {
		this.opcionesDomicilio = opcionesDomicilio;
	}

}
