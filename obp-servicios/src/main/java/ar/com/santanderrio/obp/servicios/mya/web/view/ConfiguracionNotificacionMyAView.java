/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.DiaAvisoPrevio;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.FrecuenciaMya;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.SorpresaMya;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.TarjetaSuscripcion;

/**
 * The Class ConfiguracionNotificacionMyAView.
 */
public class ConfiguracionNotificacionMyAView {

	/** The cabecera. */
	private String cabecera;

	/** The textoCabecera. */
	private String textoCabecera;

	/** The textoColumna. */
	private String textoColumna;

	/** The email1. */
	private Boolean email1;

	/** The email2. */
	private Boolean email2;

	/** The frecuencia vinculada. */
	private String frecuenciaVinculada;

	/** The lista dias mail uno. */
	private List<FrecuenciaMya> listaDiasMailUno;

	/** The lista dias mail dos. */
	private List<FrecuenciaMya> listaDiasMailDos;

	/** The alerta saldo minimo pesos. */
	private String alertaSaldoMinimoPesos;

	/** The lista tarjetas. */
	private List<TarjetaSuscripcion> listaTarjetas;

	/** The numero suscripcion mail uno. */
	private String numeroSuscripcionMailUno;

	/** The numero suscripcion mail dos. */
	private String numeroSuscripcionMailDos;

	/** The tipo error. */
	private String tipoError;

	/** The lista frecuencias mail uno. */
	private List<FrecuenciaMya> listaFrecuenciasMailUno;

	/** The lista frecuencias mail dos. */
	private List<FrecuenciaMya> listaFrecuenciasMailDos;

	/** The lista DAP mail uno. */
	private List<DiaAvisoPrevio> listaDAPMailUno;

	/** The lista DAP mail dos. */
	private List<DiaAvisoPrevio> listaDAPMailDos;

	/** The sorpresa. */
	private SorpresaMya sorpresa;
	

	/**
	 * Instantiates a new configuracion notificacion my A view.
	 */
	public ConfiguracionNotificacionMyAView() {
		super();
	}

	/**
	 * Constructor de ConfiguracionNotificacionMyAView desde un objeto
	 * MensajeSuscripcion.
	 *
	 * @param mensajeSuscripcion
	 *            the mensaje suscripcion
	 */
	public ConfiguracionNotificacionMyAView(MensajeSuscripcion mensajeSuscripcion) {
		this.cabecera = mensajeSuscripcion.getLabel();
		this.textoCabecera = mensajeSuscripcion.getMensaje();
		this.textoColumna = mensajeSuscripcion.getFrecuencia();
		this.email1 = mensajeSuscripcion.getMailUnoActivo();
		this.email2 = mensajeSuscripcion.getMailDosActivo();
		this.frecuenciaVinculada = mensajeSuscripcion.getFrecuenciaVinculada();
		this.listaDiasMailUno = mensajeSuscripcion.getListaDiasMailUno();
		this.listaDiasMailDos = mensajeSuscripcion.getListaDiasMailDos();
		this.alertaSaldoMinimoPesos = mensajeSuscripcion.getAlertaSaldoMinimoPesos();
		this.listaTarjetas = mensajeSuscripcion.getListaTarjetas();
		this.numeroSuscripcionMailUno = mensajeSuscripcion.getNumeroSuscripcionMailUno();
		this.numeroSuscripcionMailDos = mensajeSuscripcion.getNumeroSuscripcionMailDos();
		this.listaFrecuenciasMailUno = mensajeSuscripcion.getListaFrecuenciasMailUno();
		this.listaFrecuenciasMailDos = mensajeSuscripcion.getListaFrecuenciasMailDos();
		this.listaDAPMailUno = mensajeSuscripcion.getListaDAPMailUno();
		this.listaDAPMailDos = mensajeSuscripcion.getListaDAPMailDos();
		this.sorpresa = mensajeSuscripcion.getSorpresa();
	}

	/**
	 * Constructor de ConfiguracionNotificacionMyAView con error.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param sorpresa
	 *            the sorpresa
	 */
	public ConfiguracionNotificacionMyAView(TipoError tipoError, SorpresaMya sorpresa) {
		this.tipoError = tipoError.getDescripcion();
		if (!TipoError.SIN_DESTINOS.equals(tipoError)) {
			this.sorpresa = sorpresa;
		}
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the texto cabecera.
	 *
	 * @return the textoCabecera
	 */
	public String getTextoCabecera() {
		return textoCabecera;
	}

	/**
	 * Sets the texto cabecera.
	 *
	 * @param textoCabecera
	 *            the textoCabecera to set
	 */
	public void setTextoCabecera(String textoCabecera) {
		this.textoCabecera = textoCabecera;
	}

	/**
	 * Gets the texto columna.
	 *
	 * @return the textoColumna
	 */
	public String getTextoColumna() {
		return textoColumna;
	}

	/**
	 * Sets the texto columna.
	 *
	 * @param textoColumna
	 *            the textoColumna to set
	 */
	public void setTextoColumna(String textoColumna) {
		this.textoColumna = textoColumna;
	}

	/**
	 * Gets the email 1.
	 *
	 * @return the email1
	 */
	public Boolean getEmail1() {
		return email1;
	}

	/**
	 * Sets the email 1.
	 *
	 * @param email1
	 *            the email1 to set
	 */
	public void setEmail1(Boolean email1) {
		this.email1 = email1;
	}

	/**
	 * Gets the email 2.
	 *
	 * @return the email2
	 */
	public Boolean getEmail2() {
		return email2;
	}

	/**
	 * Sets the email 2.
	 *
	 * @param email2
	 *            the email2 to set
	 */
	public void setEmail2(Boolean email2) {
		this.email2 = email2;
	}

	/**
	 * Gets the frecuencia vinculada.
	 *
	 * @return the frecuencia vinculada
	 */
	public String getFrecuenciaVinculada() {
		return frecuenciaVinculada;
	}

	/**
	 * Sets the frecuencia vinculada.
	 *
	 * @param frecuenciaVinculada
	 *            the new frecuencia vinculada
	 */
	public void setFrecuenciaVinculada(String frecuenciaVinculada) {
		this.frecuenciaVinculada = frecuenciaVinculada;
	}

	/**
	 * Gets the lista dias mail uno.
	 *
	 * @return the lista dias mail uno
	 */
	public List<FrecuenciaMya> getListaDiasMailUno() {
		return listaDiasMailUno;
	}

	/**
	 * Sets the lista dias mail uno.
	 *
	 * @param listaDiasMailUno
	 *            the new lista dias mail uno
	 */
	public void setListaDiasMailUno(List<FrecuenciaMya> listaDiasMailUno) {
		this.listaDiasMailUno = listaDiasMailUno;
	}

	/**
	 * Gets the lista dias mail dos.
	 *
	 * @return the lista dias mail dos
	 */
	public List<FrecuenciaMya> getListaDiasMailDos() {
		return listaDiasMailDos;
	}

	/**
	 * Sets the lista dias mail dos.
	 *
	 * @param listaDiasMailDos
	 *            the new lista dias mail dos
	 */
	public void setListaDiasMailDos(List<FrecuenciaMya> listaDiasMailDos) {
		this.listaDiasMailDos = listaDiasMailDos;
	}

	/**
	 * Gets the alerta saldo minimo pesos.
	 *
	 * @return the alerta saldo minimo pesos
	 */
	public String getAlertaSaldoMinimoPesos() {
		return alertaSaldoMinimoPesos;
	}

	/**
	 * Sets the alerta saldo minimo pesos.
	 *
	 * @param alertaSaldoMinimoPesos
	 *            the new alerta saldo minimo pesos
	 */
	public void setAlertaSaldoMinimoPesos(String alertaSaldoMinimoPesos) {
		this.alertaSaldoMinimoPesos = alertaSaldoMinimoPesos;
	}

	/**
	 * Gets the lista tarjetas.
	 *
	 * @return the lista tarjetas
	 */
	public List<TarjetaSuscripcion> getListaTarjetas() {
		return listaTarjetas;
	}

	/**
	 * Sets the lista tarjetas.
	 *
	 * @param listaTarjetas
	 *            the new lista tarjetas
	 */
	public void setListaTarjetas(List<TarjetaSuscripcion> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}

	/**
	 * Gets the numero suscripcion mail uno.
	 *
	 * @return the numero suscripcion mail uno
	 */
	public String getNumeroSuscripcionMailUno() {
		return numeroSuscripcionMailUno;
	}

	/**
	 * Sets the numero suscripcion mail uno.
	 *
	 * @param numeroSuscripcionMailUno
	 *            the new numero suscripcion mail uno
	 */
	public void setNumeroSuscripcionMailUno(String numeroSuscripcionMailUno) {
		this.numeroSuscripcionMailUno = numeroSuscripcionMailUno;
	}

	/**
	 * Gets the numero suscripcion mail dos.
	 *
	 * @return the numero suscripcion mail dos
	 */
	public String getNumeroSuscripcionMailDos() {
		return numeroSuscripcionMailDos;
	}

	/**
	 * Sets the numero suscripcion mail dos.
	 *
	 * @param numeroSuscripcionMailDos
	 *            the new numero suscripcion mail dos
	 */
	public void setNumeroSuscripcionMailDos(String numeroSuscripcionMailDos) {
		this.numeroSuscripcionMailDos = numeroSuscripcionMailDos;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipoError
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the tipoError to set
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	/**
	 * Gets the lista frecuencias mail uno.
	 *
	 * @return the lista frecuencias mail uno
	 */
	public List<FrecuenciaMya> getListaFrecuenciasMailUno() {
		return listaFrecuenciasMailUno;
	}

	/**
	 * Sets the lista frecuencias mail uno.
	 *
	 * @param listaFrecuenciasMailUno
	 *            the new lista frecuencias mail uno
	 */
	public void setListaFrecuenciasMailUno(List<FrecuenciaMya> listaFrecuenciasMailUno) {
		this.listaFrecuenciasMailUno = listaFrecuenciasMailUno;
	}

	/**
	 * Gets the lista frecuencias mail dos.
	 *
	 * @return the lista frecuencias mail dos
	 */
	public List<FrecuenciaMya> getListaFrecuenciasMailDos() {
		return listaFrecuenciasMailDos;
	}

	/**
	 * Sets the lista frecuencias mail dos.
	 *
	 * @param listaFrecuenciasMailDos
	 *            the new lista frecuencias mail dos
	 */
	public void setListaFrecuenciasMailDos(List<FrecuenciaMya> listaFrecuenciasMailDos) {
		this.listaFrecuenciasMailDos = listaFrecuenciasMailDos;
	}

	/**
	 * Gets the lista DAP mail uno.
	 *
	 * @return the lista DAP mail uno
	 */
	public List<DiaAvisoPrevio> getListaDAPMailUno() {
		return listaDAPMailUno;
	}

	/**
	 * Sets the lista DAP mail uno.
	 *
	 * @param listaDAPMailUno
	 *            the new lista DAP mail uno
	 */
	public void setListaDAPMailUno(List<DiaAvisoPrevio> listaDAPMailUno) {
		this.listaDAPMailUno = listaDAPMailUno;
	}

	/**
	 * Gets the lista DAP mail dos.
	 *
	 * @return the lista DAP mail dos
	 */
	public List<DiaAvisoPrevio> getListaDAPMailDos() {
		return listaDAPMailDos;
	}

	/**
	 * Sets the lista DAP mail dos.
	 *
	 * @param listaDAPMailDos
	 *            the new lista DAP mail dos
	 */
	public void setListaDAPMailDos(List<DiaAvisoPrevio> listaDAPMailDos) {
		this.listaDAPMailDos = listaDAPMailDos;
	}

	/**
	 * Gets the sorpresa.
	 *
	 * @return the sorpresa
	 */
	public SorpresaMya getSorpresa() {
		return sorpresa;
	}

	/**
	 * Sets the sorpresa.
	 *
	 * @param sorpresa
	 *            the new sorpresa
	 */
	public void setSorpresa(SorpresaMya sorpresa) {
		this.sorpresa = sorpresa;
	}
	
}