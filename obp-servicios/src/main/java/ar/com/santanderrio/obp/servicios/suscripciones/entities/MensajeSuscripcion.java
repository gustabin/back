/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class MensajeSuscripcion.
 */
public class MensajeSuscripcion {

	/** The label. */
	private String label;

	/** The activa. */
	private Boolean activa = false;

	/** The mensaje. */
	private String mensaje;

	/** The mail uno activo. */
	private Boolean mailUnoActivo = false;

	/** The lista dias mail uno. */
	private List<FrecuenciaMya> listaDiasMailUno;

	/** The mail dos activo. */
	private Boolean mailDosActivo = false;

	/** The lista dias mail dos. */
	private List<FrecuenciaMya> listaDiasMailDos;

	/** The frecuencia. */
	private String frecuencia;

	/** The frecuencia vinculada. */
	private String frecuenciaVinculada;

	/** The alerta saldo minimo pesos. */
	private String alertaSaldoMinimoPesos;

	/** The lista tarjetas. */
	private List<TarjetaSuscripcion> listaTarjetas;

	/** The numero suscripcion mail uno. */
	private String numeroSuscripcionMailUno;

	/** The numero suscripcion mail dos. */
	private String numeroSuscripcionMailDos;

	/** The numero mensaje. */
	private String numeroMensaje;

	/** The tipo error. */
	private TipoError tipoError;

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

	/** The lista msg multiples. */
	private List<MyaMsgMultiple> listaMsgMultiples;
	

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
	 *            the new label
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
	 *            the new activa
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mail uno activo.
	 *
	 * @return the mail uno activo
	 */
	public Boolean getMailUnoActivo() {
		return mailUnoActivo;
	}

	/**
	 * Sets the mail uno activo.
	 *
	 * @param mailUnoActivo
	 *            the new mail uno activo
	 */
	public void setMailUnoActivo(Boolean mailUnoActivo) {
		this.mailUnoActivo = mailUnoActivo;
	}

	/**
	 * Gets the mail dos activo.
	 *
	 * @return the mail dos activo
	 */
	public Boolean getMailDosActivo() {
		return mailDosActivo;
	}

	/**
	 * Sets the mail dos activo.
	 *
	 * @param mailDosActivo
	 *            the new mail dos activo
	 */
	public void setMailDosActivo(Boolean mailDosActivo) {
		this.mailDosActivo = mailDosActivo;
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
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

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipoError
	 */
	public TipoError getTipoError() {
		return tipoError;
	}

	/**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the tipoError to set
	 */
	public void setTipoError(TipoError tipoError) {
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

	/**
	 * Gets the lista msg multiples.
	 *
	 * @return the lista msg multiples
	 */
	public List<MyaMsgMultiple> getListaMsgMultiples() {
		return listaMsgMultiples;
	}

	/**
	 * Sets the lista msg multiples.
	 *
	 * @param listaMsgMultiples
	 *            the new lista msg multiples
	 */
	public void setListaMsgMultiples(List<MyaMsgMultiple> listaMsgMultiples) {
		this.listaMsgMultiples = listaMsgMultiples;
	}

}