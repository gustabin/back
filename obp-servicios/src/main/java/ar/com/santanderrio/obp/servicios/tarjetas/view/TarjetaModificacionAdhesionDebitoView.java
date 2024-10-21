/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class TarjetaModificacionAdhesionDebitoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetaModificacionAdhesionDebitoView {

	/** The tarjeta para titulo. */
	private String tarjetaParaTitulo;

	/** The tarjeta Subtitulo. */
	private String tarjetaSubtitulo;

	/** The nro tarjeta enmascarado. */
	private String nroTarjetaEnmascarado;

	/** The alias cuenta debito. */
	private String aliasCuentaDebito;

	/** The tipo cuenta debito. */
	private String tipoCuentaDebito;

	/** The nro cuenta debito. */
	private String nroCuentaDebito;

	/** The saldo cuenta debito. */
	private String saldoCuentaDebito;

	/** The is adhesion total. */
	private Boolean isAdhesionTotal;

	/** The is adhesion minimo. */
	private Boolean isAdhesionMinimo;

	/** The mensaje informacion. */
	private String mensajeInformacion;

	/** The mensaje adhesion. */
	private String mensajeAdhesion;

	/** The cbu. */
	private String cbu;

	/** The tipo tarjeta. */
	private String tipoTarjeta;

	/**
	 * Gets the tarjeta para titulo.
	 *
	 * @return the tarjeta para titulo
	 */
	public String getTarjetaParaTitulo() {
		return tarjetaParaTitulo;
	}

	/**
	 * Sets the tarjeta para titulo.
	 *
	 * @param tarjetaParaTitulo
	 *            the new tarjeta para titulo
	 */
	public void setTarjetaParaTitulo(String tarjetaParaTitulo) {
		this.tarjetaParaTitulo = tarjetaParaTitulo;
	}

	/**
	 * Gets the nro tarjeta enmascarado.
	 *
	 * @return the nro tarjeta enmascarado
	 */
	public String getNroTarjetaEnmascarado() {
		return nroTarjetaEnmascarado;
	}

	/**
	 * Sets the nro tarjeta enmascarado.
	 *
	 * @param nroTarjetaEnmascarado
	 *            the new nro tarjeta enmascarado
	 */
	public void setNroTarjetaEnmascarado(String nroTarjetaEnmascarado) {
		this.nroTarjetaEnmascarado = nroTarjetaEnmascarado;
	}

	/**
	 * Gets the alias cuenta debito.
	 *
	 * @return the alias cuenta debito
	 */
	public String getAliasCuentaDebito() {
		return aliasCuentaDebito;
	}

	/**
	 * Sets the alias cuenta debito.
	 *
	 * @param aliasCuentaDebito
	 *            the new alias cuenta debito
	 */
	public void setAliasCuentaDebito(String aliasCuentaDebito) {
		this.aliasCuentaDebito = aliasCuentaDebito;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the nro cuenta debito.
	 *
	 * @return the nro cuenta debito
	 */
	public String getNroCuentaDebito() {
		return nroCuentaDebito;
	}

	/**
	 * Sets the nro cuenta debito.
	 *
	 * @param nroCuentaDebito
	 *            the new nro cuenta debito
	 */
	public void setNroCuentaDebito(String nroCuentaDebito) {
		this.nroCuentaDebito = nroCuentaDebito;
	}

	/**
	 * Gets the saldo cuenta debito.
	 *
	 * @return the saldo cuenta debito
	 */
	public String getSaldoCuentaDebito() {
		return saldoCuentaDebito;
	}

	/**
	 * Sets the saldo cuenta debito.
	 *
	 * @param saldoCuentaDebito
	 *            the new saldo cuenta debito
	 */
	public void setSaldoCuentaDebito(String saldoCuentaDebito) {
		this.saldoCuentaDebito = saldoCuentaDebito;
	}

	/**
	 * Gets the mensaje informacion.
	 *
	 * @return the mensaje informacion
	 */
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	/**
	 * Sets the mensaje informacion.
	 *
	 * @param mensajeInformacion
	 *            the new mensaje informacion
	 */
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

	/**
	 * Gets the mensaje adhesion.
	 *
	 * @return the mensaje adhesion
	 */
	public String getMensajeAdhesion() {
		return mensajeAdhesion;
	}

	/**
	 * Sets the mensaje adhesion.
	 *
	 * @param mensajeAdhesion
	 *            the new mensaje adhesion
	 */
	public void setMensajeAdhesion(String mensajeAdhesion) {
		this.mensajeAdhesion = mensajeAdhesion;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Gets the checks if is adhesion total.
	 *
	 * @return the checks if is adhesion total
	 */
	public Boolean getIsAdhesionTotal() {
		return isAdhesionTotal;
	}

	/**
	 * Sets the checks if is adhesion total.
	 *
	 * @param isAdhesionTotal
	 *            the new checks if is adhesion total
	 */
	public void setIsAdhesionTotal(Boolean isAdhesionTotal) {
		this.isAdhesionTotal = isAdhesionTotal;
	}

	/**
	 * Gets the checks if is adhesion minimo.
	 *
	 * @return the checks if is adhesion minimo
	 */
	public Boolean getIsAdhesionMinimo() {
		return isAdhesionMinimo;
	}

	/**
	 * Sets the checks if is adhesion minimo.
	 *
	 * @param isAdhesionMinimo
	 *            the new checks if is adhesion minimo
	 */
	public void setIsAdhesionMinimo(Boolean isAdhesionMinimo) {
		this.isAdhesionMinimo = isAdhesionMinimo;
	}

	/**
	 * Gets the tarjeta subtitulo.
	 *
	 * @return the tarjeta subtitulo
	 */
	public String getTarjetaSubtitulo() {
		return tarjetaSubtitulo;
	}

	/**
	 * Sets the tarjeta subtitulo.
	 * 
	 * @param tarjetaSubtitulo
	 *            the new tarjeta subtitulo
	 */
	public void setTarjetaSubtitulo(String tarjetaSubtitulo) {
		this.tarjetaSubtitulo = tarjetaSubtitulo;
	}

}
