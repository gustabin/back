package ar.com.santanderrio.obp.servicios.ejecutivoselect.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comex.transfext.view.ComboView;


/**
 * The Class InicioFlujoEMailOutView.
 */
public class InicioFlujoEMailOutView {

	/** The email operador. */
	private String emailOperador;

	/** The email cliente. */
	private String emailCliente;

	/** The lista motivos. */
	private List<ComboView> listaMotivos;
	
	/** The mensaje tooltip. */
	private String mensajeTooltip;

	/**
	 * Instantiates a new inicio flujo E mail out view.
	 */
	public InicioFlujoEMailOutView() {
		super();
	}

	/**
	 * Gets the email operador.
	 *
	 * @return the email operador
	 */
	public String getEmailOperador() {
		return emailOperador;
	}

	/**
	 * Sets the email operador.
	 *
	 * @param emailOperador
	 *            the new email operador
	 */
	public void setEmailOperador(String emailOperador) {
		this.emailOperador = emailOperador;
	}

	/**
	 * Gets the email cliente.
	 *
	 * @return the email cliente
	 */
	public String getEmailCliente() {
		return emailCliente;
	}

	/**
	 * Sets the email cliente.
	 *
	 * @param emailCliente
	 *            the new email cliente
	 */
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	/**
	 * Gets the lista motivos.
	 *
	 * @return the lista motivos
	 */
	public List<ComboView> getListaMotivos() {
		return listaMotivos;
	}

	/**
	 * Sets the lista motivos.
	 *
	 * @param listaMotivos
	 *            the new lista motivos
	 */
	public void setListaMotivos(List<ComboView> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	/**
	 * Gets the mensaje tooltip.
	 *
	 * @return the mensaje tooltip
	 */
	public String getMensajeTooltip() {
		return mensajeTooltip;
	}

	/**
	 * Sets the mensaje tooltip.
	 *
	 * @param mensajeTooltip the new mensaje tooltip
	 */
	public void setMensajeTooltip(String mensajeTooltip) {
		this.mensajeTooltip = mensajeTooltip;
	}

	
}
