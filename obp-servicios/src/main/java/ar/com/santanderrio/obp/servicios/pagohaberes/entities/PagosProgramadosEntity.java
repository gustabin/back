/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class PagosProgramados.
 *
 */

public class PagosProgramadosEntity extends PagosInformadosEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8066900123547866985L;

	/** The fecha ejecucion. */
	private Date fechaEjecucion;

	/** The tipo agendamiento. */
	private String tipoAgendamiento;

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fecha ejecucion
	 */
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion
	 *            the new fecha ejecucion
	 */
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the tipo agendamiento.
	 *
	 * @return the tipo agendamiento
	 */
	public String getTipoAgendamiento() {
		return tipoAgendamiento;
	}

	/**
	 * Sets the tipo agendamiento.
	 *
	 * @param tipoAgendamiento
	 *            the new tipo agendamiento
	 */
	public void setTipoAgendamiento(String tipoAgendamiento) {
		this.tipoAgendamiento = tipoAgendamiento;
	}

}
