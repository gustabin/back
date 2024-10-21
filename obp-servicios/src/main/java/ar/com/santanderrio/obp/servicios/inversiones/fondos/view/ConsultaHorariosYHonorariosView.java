/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoHorarioHonorario;

/**
 * The Class ConsultaHorariosYHonorariosView.
 */
public class ConsultaHorariosYHonorariosView {

	/** The fondo horario. */
	private List<FondoHorarioHonorario> fondoHorario = new ArrayList<FondoHorarioHonorario>();

	/** The fondo honorario. */
	private List<FondoHorarioHonorario> fondoHonorario = new ArrayList<FondoHorarioHonorario>();

	/** The primer legales honorarios. */
	private String primerLegalesHonorarios;

	/** The segundo legales honorarios. */
	private String segundoLegalesHonorarios;

	/** The legales horario. */
	private String legalesHorario;

	/**
	 * Gets the fondo honorario.
	 *
	 * @return the fondoHonorario
	 */
	public List<FondoHorarioHonorario> getFondoHonorario() {
		return fondoHonorario;
	}

	/**
	 * Sets the fondo honorario.
	 *
	 * @param fondoHonorario
	 *            the fondoHonorario to set
	 */
	public void setFondoHonorario(List<FondoHorarioHonorario> fondoHonorario) {
		this.fondoHonorario = fondoHonorario;
	}

	/**
	 * Gets the primer legales honorarios.
	 *
	 * @return the primerLegalesHonorarios
	 */
	public String getPrimerLegalesHonorarios() {
		return primerLegalesHonorarios;
	}

	/**
	 * Sets the primer legales honorarios.
	 *
	 * @param primerLegalesHonorarios
	 *            the primerLegalesHonorarios to set
	 */
	public void setPrimerLegalesHonorarios(String primerLegalesHonorarios) {
		this.primerLegalesHonorarios = primerLegalesHonorarios;
	}

	/**
	 * Gets the segundo legales honorarios.
	 *
	 * @return the segundoLegalesHonorarios
	 */
	public String getSegundoLegalesHonorarios() {
		return segundoLegalesHonorarios;
	}

	/**
	 * Sets the segundo legales honorarios.
	 *
	 * @param segundoLegalesHonorarios
	 *            the segundoLegalesHonorarios to set
	 */
	public void setSegundoLegalesHonorarios(String segundoLegalesHonorarios) {
		this.segundoLegalesHonorarios = segundoLegalesHonorarios;
	}

	/**
	 * Gets the legales horario.
	 *
	 * @return the legalesHorario
	 */
	public String getLegalesHorario() {
		return legalesHorario;
	}

	/**
	 * Sets the legales horario.
	 *
	 * @param legalesHorario
	 *            the legalesHorario to set
	 */
	public void setLegalesHorario(String legalesHorario) {
		this.legalesHorario = legalesHorario;
	}

	/**
	 * Gets the fondo horario.
	 *
	 * @return the fondoHorario
	 */
	public List<FondoHorarioHonorario> getFondoHorario() {
		return fondoHorario;
	}

	/**
	 * Sets the fondo horario.
	 *
	 * @param fondoHorario
	 *            the fondoHorario to set
	 */
	public void setFondoHorario(List<FondoHorarioHonorario> fondoHorario) {
		this.fondoHorario = fondoHorario;
	}
}
