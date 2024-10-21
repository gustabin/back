/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.RecargaTarjetaView;

/**
 * DTO de recarga de tarjeta.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecargaTarjeta implements Serializable {

	/** Versión autogenerado. */
	private static final long serialVersionUID = -8025657193098205169L;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The importe. */
	private String importe;

	/** The divisa. */
	private String divisa;

	/** The fecha de operacion. */
	private String fechaDeOperacion;

	/** The saldo cuenta origen. */
	private String saldoCuentaOrigen;

	/** The numero tarjeta destino. */
	private String numeroTarjetaDestino;

	/** The recarga tarjeta view. */
	private RecargaTarjetaView recargaTarjetaView = new RecargaTarjetaView();

	/** The challenge. */
	// atributos que no uso pero el front me envia
	private String challenge;

	/** The desafio. */
	private AutentificacionDTO desafio;

	/** The reintentar. */
	private String reintentar;

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.recargaTarjetaView.setDivisa(divisa);
		this.divisa = divisa;
	}

	/**
	 * Gets the fecha de operacion.
	 *
	 * @return the fechaDeOperacion
	 */
	public String getFechaDeOperacion() {
		return fechaDeOperacion;
	}

	/**
	 * Sets the fecha de operacion.
	 *
	 * @param fechaDeOperacion
	 *            the fechaDeOperacion to set
	 */
	public void setFechaDeOperacion(String fechaDeOperacion) {
		this.fechaDeOperacion = fechaDeOperacion;
	}

	/**
	 * Gets the saldo cuenta origen.
	 *
	 * @return the saldoCuentaOrigen
	 */
	public String getSaldoCuentaOrigen() {
		return saldoCuentaOrigen;
	}

	/**
	 * Sets the saldo cuenta origen.
	 *
	 * @param saldoCuentaOrigen
	 *            the saldoCuentaOrigen to set
	 */
	public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
		this.saldoCuentaOrigen = saldoCuentaOrigen;
	}

	/**
	 * Gets the numero tarjeta destino.
	 *
	 * @return the numeroTarjetaDestino
	 */
	public String getNumeroTarjetaDestino() {
		return numeroTarjetaDestino;
	}

	/**
	 * Sets the numero tarjeta destino.
	 *
	 * @param numeroTarjetaDestino
	 *            the numeroTarjetaDestino to set
	 */
	public void setNumeroTarjetaDestino(String numeroTarjetaDestino) {
		this.numeroTarjetaDestino = numeroTarjetaDestino;
	}

	/**
	 * Gets the importe recarga pesos.
	 *
	 * @return the importe recarga pesos
	 */
	public String getImporteRecargaPesos() {
		return recargaTarjetaView.getImporteRecargaPesos();
	}

	/**
	 * Sets the importe recarga pesos.
	 *
	 * @param importeRecargaPesos
	 *            the new importe recarga pesos
	 */
	public void setImporteRecargaPesos(String importeRecargaPesos) {
		recargaTarjetaView.setImporteRecargaPesos(importeRecargaPesos);
	}

	/**
	 * Gets the sucursal cuenta destino.
	 *
	 * @return the sucursal cuenta destino
	 */
	public String getSucursalCuentaDestino() {
		return recargaTarjetaView.getSucursalCuentaDestino();
	}

	/**
	 * Sets the sucursal cuenta destino.
	 *
	 * @param sucursalCuentaDestino
	 *            the new sucursal cuenta destino
	 */
	public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
		recargaTarjetaView.setSucursalCuentaDestino(sucursalCuentaDestino);
	}

	/**
	 * Gets the tipo cuenta destino.
	 *
	 * @return the tipo cuenta destino
	 */
	public String getTipoCuentaDestino() {
		return recargaTarjetaView.getTipoCuentaDestino();
	}

	/**
	 * Sets the tipo cuenta destino.
	 *
	 * @param tipoCuentaDestino
	 *            the new tipo cuenta destino
	 */
	public void setTipoCuentaDestino(String tipoCuentaDestino) {
		recargaTarjetaView.setTipoCuentaDestino(tipoCuentaDestino);
	}

	/**
	 * Gets the nro cuenta destino.
	 *
	 * @return the nro cuenta destino
	 */
	public String getNroCuentaDestino() {
		return recargaTarjetaView.getNroCuentaDestino();
	}

	/**
	 * Sets the nro cuenta destino.
	 *
	 * @param nroCuentaDestino
	 *            the new nro cuenta destino
	 */
	public void setNroCuentaDestino(String nroCuentaDestino) {
		recargaTarjetaView.setNroCuentaDestino(nroCuentaDestino);
	}

	/**
	 * Gets the sucursal cuenta origen.
	 *
	 * @return the sucursal cuenta origen
	 */
	public String getSucursalCuentaOrigen() {
		return this.recargaTarjetaView.getSucursalCuentaOrigen();
	}

	/**
	 * Sets the sucursal cuenta origen.
	 *
	 * @param sucursalCuentaOrigen
	 *            the new sucursal cuenta origen
	 */
	public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
		recargaTarjetaView.setSucursalCuentaOrigen(sucursalCuentaOrigen);
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipo cuenta origen
	 */
	public String getTipoCuentaOrigen() {
		return this.recargaTarjetaView.getTipoCuentaOrigen();
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the new tipo cuenta origen
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		recargaTarjetaView.setTipoCuentaOrigen(tipoCuentaOrigen);
	}

	/**
	 * Gets the nro cuenta origen.
	 *
	 * @return the nro cuenta origen
	 */
	public String getNroCuentaOrigen() {
		return this.recargaTarjetaView.getNroCuentaOrigen();
	}

	/**
	 * Sets the nro cuenta origen.
	 *
	 * @param nroCuentaOrigen
	 *            the new nro cuenta origen
	 */
	public void setNroCuentaOrigen(String nroCuentaOrigen) {
		recargaTarjetaView.setNroCuentaOrigen(nroCuentaOrigen);
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return this.recargaTarjetaView.getNroTarjeta();
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.recargaTarjetaView.setNroTarjeta(nroTarjeta);
	}

	/**
	 * Gets the fecha de recarga.
	 *
	 * @return the fecha de recarga
	 */
	public String getFechaDeRecarga() {
		return this.recargaTarjetaView.getFechaDeRecarga();
	}

	/**
	 * Sets the fecha de recarga.
	 *
	 * @param fechaDeRecarga
	 *            the new fecha de recarga
	 */
	public void setFechaDeRecarga(String fechaDeRecarga) {
		this.recargaTarjetaView.setFechaDeRecarga(fechaDeRecarga);
	}

	/**
	 * Gets the abreviatura tipo cuenta.
	 *
	 * @return the abreviaturaTipoCuenta
	 */
	public String getAbreviaturaTipoCuenta() {
		return this.recargaTarjetaView.getAbreviaturaTipoCuenta();
	}

	/**
	 * Sets the abreviatura tipo cuenta.
	 *
	 * @param abreviaturaTipoCuenta
	 *            the abreviaturaTipoCuenta to set
	 */
	public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
		this.recargaTarjetaView.setAbreviaturaTipoCuenta(abreviaturaTipoCuenta);
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return this.recargaTarjetaView.getFechaInicio();
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.recargaTarjetaView.setFechaInicio(fechaInicio);
	}

	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public String getFrecuencia() {
		return this.recargaTarjetaView.getFrecuencia();
	}

	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia
	 *            the new frecuencia
	 */
	public void setFrecuencia(String frecuencia) {
		this.recargaTarjetaView.setFrecuencia(frecuencia);
	}

	/**
	 * Gets the fecha prox recarga.
	 *
	 * @return the fecha prox recarga
	 */
	public String getFechaProxRecarga() {
		return this.recargaTarjetaView.getFechaProxRecarga();
	}

	/**
	 * Sets the fecha prox recarga.
	 *
	 * @param fechaProxRecarga
	 *            the new fecha prox recarga
	 */
	public void setFechaProxRecarga(String fechaProxRecarga) {
		this.recargaTarjetaView.setFechaProxRecarga(fechaProxRecarga);
	}

	/**
	 * Gets the fecha de modificacion.
	 *
	 * @return the fecha de modificacion
	 */
	public String getFechaDeModificacion() {
		return this.recargaTarjetaView.getFechaDeModificacion();
	}

	/**
	 * Sets the fecha de modificacion.
	 *
	 * @param fechaDeModificacion
	 *            the new fecha de modificacion
	 */
	public void setFechaDeModificacion(String fechaDeModificacion) {
		this.recargaTarjetaView.setFechaDeModificacion(fechaDeModificacion);
	}

	/**
	 * Gets the recarga tarjeta view.
	 *
	 * @return the recarga tarjeta view
	 */
	public RecargaTarjetaView getRecargaTarjetaView() {
		return recargaTarjetaView;
	}

	/**
	 * Sets the recarga tarjeta view.
	 *
	 * @param recargaTarjetaView
	 *            the new recarga tarjeta view
	 */
	public void setRecargaTarjetaView(RecargaTarjetaView recargaTarjetaView) {
		this.recargaTarjetaView = recargaTarjetaView;
	}

	/**
	 * Gets the challenge.
	 *
	 * @return the challenge
	 */
	public String getChallenge() {
		return challenge;
	}

	/**
	 * Sets the challenge.
	 *
	 * @param challenge
	 *            the new challenge
	 */
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

}
