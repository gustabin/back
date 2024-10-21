/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;

/**
 * The Class AgendaDestinatarioView.
 * 
 * @author dante.omar.olmedo
 */
public class AgendaDestinatarioView {

	/** The mensaje cabecera. */
	private String mensajeCabecera;

	/** The lista destinatarios. */
	private List<DestinatarioView> listaDestinatarios;

	/**
	 * Constructor de view desde dto.
	 *
	 * @param dto
	 *            the dto
	 */
	public AgendaDestinatarioView(AgendaDestinatarioDTO dto) {
		this.setMensajeCabecera(dto.getMensajeCabecera());
		List<DestinatarioView> destinatariosView = new ArrayList<DestinatarioView>();
		for (DestinatarioAgendaDTO destinatarioDTO : dto.getListaDestinatarios()) {
			destinatariosView.add(new DestinatarioView(destinatarioDTO));
		}
		this.setListaDestinatarios(destinatariosView);
	}

	/**
	 * Gets the mensaje cabecera.
	 *
	 * @return the mensaje cabecera
	 */
	public String getMensajeCabecera() {
		return mensajeCabecera;
	}

	/**
	 * Sets the mensaje cabecera.
	 *
	 * @param mensajeCabecera
	 *            the new mensaje cabecera
	 */
	public void setMensajeCabecera(String mensajeCabecera) {
		this.mensajeCabecera = mensajeCabecera;
	}

	/**
	 * Gets the lista destinatarios.
	 *
	 * @return the lista destinatarios
	 */
	public List<DestinatarioView> getListaDestinatarios() {
		return listaDestinatarios;
	}

	/**
	 * Sets the lista destinatarios.
	 *
	 * @param listaDestinatarios
	 *            the new lista destinatarios
	 */
	public void setListaDestinatarios(List<DestinatarioView> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

}
