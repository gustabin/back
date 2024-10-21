/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class AgendaDestinatarioRSADTO.
 */
public class AgendaDestinatarioRSADTO extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The destinatario propio. */
	Boolean destinatarioPropio;

	/** The nombre destinatario. */
	String nombreDestinatario;

	/** The numero cuenta destinatario. */
	String numeroCuentaDestinatario;

	/** The tipo destinatario. */
	TipoAgendaEnum tipoDestinatario;
	
	String cuitDestinatario;

	/**
	 * Instantiates a new agenda destinatario RSADTO.
	 *
	 * @param destinatarioPropio
	 *            the destinatario propio
	 * @param nombreDestinatario
	 *            the nombre destinatario
	 * @param numeroCuentaDestinatario
	 *            the numero cuenta destinatario
	 * @param tipoDestinatario
	 *            the tipo destinatario
	 */
	public AgendaDestinatarioRSADTO(Boolean destinatarioPropio, String nombreDestinatario,
			String numeroCuentaDestinatario, TipoAgendaEnum tipoDestinatario, String cuitDestinatario) {
		super(OperacionesRSAEnum.AGENDA_DESTINATARIO);
		this.destinatarioPropio = destinatarioPropio;
		this.nombreDestinatario = nombreDestinatario;
		this.numeroCuentaDestinatario = numeroCuentaDestinatario;
		this.tipoDestinatario = tipoDestinatario;
		this.cuitDestinatario = cuitDestinatario;
	}

	/**
	 * Gets the destinatario propio.
	 *
	 * @return the destinatario propio
	 */
	public Boolean getDestinatarioPropio() {
		return destinatarioPropio;
	}

	/**
	 * Sets the destinatario propio.
	 *
	 * @param destinatarioPropio
	 *            the new destinatario propio
	 */
	public void setDestinatarioPropio(Boolean destinatarioPropio) {
		this.destinatarioPropio = destinatarioPropio;
	}

	/**
	 * Gets the nombre destinatario.
	 *
	 * @return the nombre destinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * Sets the nombre destinatario.
	 *
	 * @param nombreDestinatario
	 *            the new nombre destinatario
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * Gets the numero cuenta destinatario.
	 *
	 * @return the numero cuenta destinatario
	 */
	public String getNumeroCuentaDestinatario() {
		return numeroCuentaDestinatario;
	}

	/**
	 * Sets the numero cuenta destinatario.
	 *
	 * @param numeroCuentaDestinatario
	 *            the new numero cuenta destinatario
	 */
	public void setNumeroCuentaDestinatario(String numeroCuentaDestinatario) {
		this.numeroCuentaDestinatario = numeroCuentaDestinatario;
	}

	/**
	 * Gets the tipo destinatario.
	 *
	 * @return the tipo destinatario
	 */
	public TipoAgendaEnum getTipoDestinatario() {
		return tipoDestinatario;
	}

	/**
	 * Sets the tipo destinatario.
	 *
	 * @param tipoDestinatario
	 *            the new tipo destinatario
	 */
	public void setTipoDestinatario(TipoAgendaEnum tipoDestinatario) {
		this.tipoDestinatario = tipoDestinatario;
	}

	public String getCuitDestinatario() {
		return cuitDestinatario;
	}

	public void setCuitDestinatario(String cuitDestinatario) {
		this.cuitDestinatario = cuitDestinatario;
	}

	
}
