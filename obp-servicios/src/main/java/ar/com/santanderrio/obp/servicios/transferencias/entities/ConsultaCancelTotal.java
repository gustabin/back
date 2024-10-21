/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DatosOrigenTransferenciaAgendadaDTO;

/**
 * The Class ConsultaCancelTotal.
 */
public class ConsultaCancelTotal extends ConsultaEntity {

	/** Serial. */
	private static final long serialVersionUID = -5078947202455657539L;

	/**
	 * Gets the datos origen.
	 *
	 * @return the datosOrigen
	 */
	public DatosOrigenTransferenciaAgendadaDTO getDatosOrigen() {
		return datosOrigen;
	}

	/**
	 * Sets the datos origen.
	 *
	 * @param datosOrigen
	 *            the datosOrigen to set
	 */
	public void setDatosOrigen(DatosOrigenTransferenciaAgendadaDTO datosOrigen) {
		this.datosOrigen = datosOrigen;
	}

	/** The datos origen. */
	private DatosOrigenTransferenciaAgendadaDTO datosOrigen;
}
