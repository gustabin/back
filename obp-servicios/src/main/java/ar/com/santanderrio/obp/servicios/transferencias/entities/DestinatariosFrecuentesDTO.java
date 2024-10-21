package ar.com.santanderrio.obp.servicios.transferencias.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;

public class DestinatariosFrecuentesDTO {

	/** The destinatariosFavoritos. */
	private List<DestinatarioAgendaDTO> destinatariosFavoritos;
	/** The destinatariosNoFavoritos. */
	private List<DestinatarioAgendaDTO> destinatariosNoFavoritos;

	/**
	 * @param destinatariosFavoritos
	 * @param destinatariosNoFavoritos
	 */
	public DestinatariosFrecuentesDTO(List<DestinatarioAgendaDTO> destinatariosFavoritos,
			List<DestinatarioAgendaDTO> destinatariosNoFavoritos) {
		super();
		this.destinatariosFavoritos = destinatariosFavoritos;
		this.destinatariosNoFavoritos = destinatariosNoFavoritos;
	}

	/**
	 * 
	 */
	public DestinatariosFrecuentesDTO() {
		super();
		this.destinatariosFavoritos = new ArrayList<DestinatarioAgendaDTO>();
		this.destinatariosNoFavoritos = new ArrayList<DestinatarioAgendaDTO>();
	}

	/**
	 * @param destinatariosNoFavoritos
	 */
	public DestinatariosFrecuentesDTO(List<DestinatarioAgendaDTO> destinatariosNoFavoritos) {
		super();
		this.destinatariosNoFavoritos = destinatariosNoFavoritos;
		this.destinatariosFavoritos = new ArrayList<DestinatarioAgendaDTO>();
	}

	/**
	 * @return the destinatariosFavoritos
	 */
	public List<DestinatarioAgendaDTO> getDestinatariosFavoritos() {
		return destinatariosFavoritos;
	}

	/**
	 * @param destinatariosFavoritos
	 *            the destinatariosFavoritos to set
	 */
	public void setDestinatariosFavoritos(List<DestinatarioAgendaDTO> destinatariosFavoritos) {
		this.destinatariosFavoritos = destinatariosFavoritos;
	}

	/**
	 * @return the destinatariosNoFavoritos
	 */
	public List<DestinatarioAgendaDTO> getDestinatariosNoFavoritos() {
		return destinatariosNoFavoritos;
	}

	/**
	 * @param destinatariosNoFavoritos
	 *            the destinatariosNoFavoritos to set
	 */
	public void setDestinatariosNoFavoritos(List<DestinatarioAgendaDTO> destinatariosNoFavoritos) {
		this.destinatariosNoFavoritos = destinatariosNoFavoritos;
	}

}
