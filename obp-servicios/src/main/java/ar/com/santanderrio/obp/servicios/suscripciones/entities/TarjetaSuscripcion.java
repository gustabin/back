/*
 * 
 */
package ar.com.santanderrio.obp.servicios.suscripciones.entities;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMsgMultiple;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class TarjetaSuscripcion.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetaSuscripcion {

	/** The marca tarjeta. */
	private String marcaTarjeta;

	/** The nro tarjeta enmascarado. */
	private String nroTarjetaEnmascarado;

	/** The suscripto. */
	private Boolean suscripto;

	/** The monto suscripto. */
	private String montoSuscripto;

	/** The texto front. */
	private String textoFront;

	/** The mostrar. */
	private Boolean mostrar;

	public TarjetaSuscripcion() {
		super();
	}
	
	/**
	 * Instantiates a new tarjeta suscripcion.
	 *
	 * @param msgMultiple
	 *            the msg multiple
	 */
	public TarjetaSuscripcion(MyaMsgMultiple msgMultiple, Boolean eResumen) {
		this.marcaTarjeta = msgMultiple.getDescripcion().contains("VISA") ? "VISA" : "AMEX";
		this.nroTarjetaEnmascarado = "XXXX-"
				+ TarjetaUtils.cortarNumeroTarjeta(msgMultiple.getNroTarjeta(), this.marcaTarjeta);
		if (msgMultiple.getDescripcion().contains("finalizada en")) {
			String[] parts = msgMultiple.getDescripcion().split("finalizada en");
			String mensajeUno = parts[0].trim();
			this.textoFront = eResumen ? mensajeUno : msgMultiple.getIdMsgMultiple() + "- " + mensajeUno;
		}
		this.mostrar = Boolean.TRUE;
	}

	/**
	 * Gets the marca tarjeta.
	 *
	 * @return the marca tarjeta
	 */
	public String getMarcaTarjeta() {
		return marcaTarjeta;
	}

	/**
	 * Sets the marca tarjeta.
	 *
	 * @param marcaTarjeta
	 *            the new marca tarjeta
	 */
	public void setMarcaTarjeta(String marcaTarjeta) {
		this.marcaTarjeta = marcaTarjeta;
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
	 * Gets the suscripto.
	 *
	 * @return the suscripto
	 */
	public Boolean getSuscripto() {
		return suscripto;
	}

	/**
	 * Sets the suscripto.
	 *
	 * @param suscripto
	 *            the new suscripto
	 */
	public void setSuscripto(Boolean suscripto) {
		this.suscripto = suscripto;
	}

	/**
	 * Gets the monto suscripto.
	 *
	 * @return the monto suscripto
	 */
	public String getMontoSuscripto() {
		return montoSuscripto;
	}

	/**
	 * Sets the monto suscripto.
	 *
	 * @param montoSuscripto
	 *            the new monto suscripto
	 */
	public void setMontoSuscripto(String montoSuscripto) {
		this.montoSuscripto = montoSuscripto;
	}

	/**
	 * Gets the texto front.
	 *
	 * @return the texto front
	 */
	public String getTextoFront() {
		return textoFront;
	}

	/**
	 * Sets the texto front.
	 *
	 * @param textoFront
	 *            the new texto front
	 */
	public void setTextoFront(String textoFront) {
		this.textoFront = textoFront;
	}

	/**
	 * @return the mostrar
	 */
	public Boolean getMostrar() {
		return mostrar;
	}

	/**
	 * @param mostrar
	 *            the mostrar to set
	 */
	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

}