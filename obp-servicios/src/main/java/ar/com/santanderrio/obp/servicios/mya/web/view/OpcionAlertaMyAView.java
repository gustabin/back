/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;

/**
 * The Class OpcionAlertaMyAView.
 */
public class OpcionAlertaMyAView {

	/** The label. */
	private String label;

	/** The activa. */
	private Boolean activa;

	/** The configuracion. */
	private ConfiguracionNotificacionMyAView configuracion;

	/** The numero mensaje. */
	private String numeroMensaje;

	public OpcionAlertaMyAView() {
		super();
	}
	
	/**
	 * Constructor de OpcionAlertaMyAView desde un objeto MensajeSuscripcion.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 */
	public OpcionAlertaMyAView(MensajeSuscripcion mensajeSuscripcion) {
		this.label = mensajeSuscripcion.getLabel();
		this.activa = mensajeSuscripcion.getActiva();
		this.numeroMensaje = mensajeSuscripcion.getNumeroMensaje();
		if (mensajeSuscripcion.getTipoError() != null) {
			this.configuracion = new ConfiguracionNotificacionMyAView(mensajeSuscripcion.getTipoError(), mensajeSuscripcion.getSorpresa());
		} else {
			this.configuracion = new ConfiguracionNotificacionMyAView(mensajeSuscripcion);
		}
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the activa.
	 *
	 * @return the activa
	 */
	public Boolean getActiva() {
		return activa;
	}

	/**
	 * Sets the activa.
	 *
	 * @param activa
	 *            the activa to set
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	/**
	 * Gets the configuracion.
	 *
	 * @return the configuracion
	 */
	public ConfiguracionNotificacionMyAView getConfiguracion() {
		return configuracion;
	}

	/**
	 * Sets the configuracion.
	 *
	 * @param configuracion
	 *            the configuracion to set
	 */
	public void setConfiguracion(ConfiguracionNotificacionMyAView configuracion) {
		this.configuracion = configuracion;
	}

	/**
	 * Gets the numero mensaje.
	 *
	 * @return the numero mensaje
	 */
	public String getNumeroMensaje() {
		return numeroMensaje;
	}

	/**
	 * Sets the numero mensaje.
	 *
	 * @param numeroMensaje
	 *            the new numero mensaje
	 */
	public void setNumeroMensaje(String numeroMensaje) {
		this.numeroMensaje = numeroMensaje;
	}

}
