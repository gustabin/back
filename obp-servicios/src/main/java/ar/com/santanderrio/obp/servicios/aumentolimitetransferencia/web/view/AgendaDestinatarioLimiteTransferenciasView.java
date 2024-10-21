/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;

/**
 * The Class AgendaDestinatarioLimiteTransferenciasView.
 */
public class AgendaDestinatarioLimiteTransferenciasView {

	/** The mensaje cabecera. */
	private String mensajeCabecera;

	/** The lista destinatarios. */
	private List<DestinatarioView> listaDestinatarios;

	/** Mensaje de ayuda para agregar nuevo destinatario. */
	private String mensajeAyudaNuevoDestinatario;

	/** Mensaje de ayuda sobre la operacion. */
	private String mensajeAyudaAumentoLimite;
	
	/** Mensaje de validacion del importe. */
	private String mensajeValidacionImporte;

	/**
	 * Gets the mensaje ayuda nuevo destinatario.
	 *
	 * @return the mensaje ayuda nuevo destinatario
	 */
	public String getMensajeAyudaNuevoDestinatario() {
		return mensajeAyudaNuevoDestinatario;
	}

	/**
	 * Sets the mensaje ayuda nuevo destinatario.
	 *
	 * @param mensajeAyudaNuevoDestinatario
	 *            the new mensaje ayuda nuevo destinatario
	 */
	public void setMensajeAyudaNuevoDestinatario(String mensajeAyudaNuevoDestinatario) {
		this.mensajeAyudaNuevoDestinatario = mensajeAyudaNuevoDestinatario;
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

	/**
	 * Gets the mensaje ayuda aumento limite.
	 *
	 * @return the mensaje ayuda aumento limite
	 */
	public String getMensajeAyudaAumentoLimite() {
		return mensajeAyudaAumentoLimite;
	}

	/**
	 * Sets the mensaje ayuda aumento limite.
	 *
	 * @param mensajeAyudaAumentoLimite
	 *            the new mensaje ayuda aumento limite
	 */
	public void setMensajeAyudaAumentoLimite(String mensajeAyudaAumentoLimite) {
		this.mensajeAyudaAumentoLimite = mensajeAyudaAumentoLimite;
	}

	/**
	 * Gets the mensaje validacion importe.
	 *
	 * @return the mensaje validacion importe
	 */
	public String getMensajeValidacionImporte() {
		return mensajeValidacionImporte;
	}

	/**
	 * Sets the mensaje validacion importe.
	 *
	 * @param mensajeValidacionImporte
	 *            the new mensaje validacion importe
	 */
	public void setMensajeValidacionImporte(String mensajeValidacionImporte) {
		this.mensajeValidacionImporte = mensajeValidacionImporte;
	}

}
