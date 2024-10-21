/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class FinalizarPrecancelableOutView.
 */
public class FinalizarPrecancelableOutView {

	/** The mensaje. */
	private String mensaje;

	/** The plazo actual. */
	private String plazoActual;

	/** The fecha alta PF. */
	private String fechaAltaPF;

	/** The fecha actual. */
	private String fechaActual;

	/** The hora actual. */
	private String horaActual;

	/** The dias transcurridos. */
	private String diasTranscurridos;
		
	/** The penalizacion precancelacion. */
	private String penalizacionPrecancelacion;
	
	/** The interes precancelacion. */
	private String interesPrecancelacion;
	
	/** The intereses A cobrar. */
	private String interesesACobrar;
	

	
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
	 * Gets the plazo actual.
	 *
	 * @return the plazo actual
	 */
	public String getPlazoActual() {
		return plazoActual;
	}

	/**
	 * Sets the plazo actual.
	 *
	 * @param plazoActual
	 *            the new plazo actual
	 */
	public void setPlazoActual(String plazoActual) {
		this.plazoActual = plazoActual;
	}

	/**
	 * Gets the fecha alta PF.
	 *
	 * @return the fecha alta PF
	 */
	public String getFechaAltaPF() {
		return fechaAltaPF;
	}

	/**
	 * Sets the fecha alta PF.
	 *
	 * @param fechaAltaPF
	 *            the new fecha alta PF
	 */
	public void setFechaAltaPF(String fechaAltaPF) {
		this.fechaAltaPF = fechaAltaPF;
	}

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	public String getFechaActual() {
		this.fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return fechaActual;
	}

	/**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the new fecha actual
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * Gets the hora actual.
	 *
	 * @return the hora actual
	 */
	public String getHoraActual() {
		this.horaActual = new SimpleDateFormat("HH:mm").format(new Date());
		return horaActual;
	}

	/**
	 * Sets the hora actual.
	 *
	 * @param horaActual
	 *            the new hora actual
	 */
	public void setHoraActual(String horaActual) {
		this.horaActual = horaActual;
	}

	/**
	 * Gets the dias transcurridos.
	 *
	 * @return the dias transcurridos
	 */
	public String getDiasTranscurridos() {
		return diasTranscurridos;
	}

	/**
	 * Sets the dias transcurridos.
	 *
	 * @param diasTranscurridos
	 *            the new dias transcurridos
	 */
	public void setDiasTranscurridos(String diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}

	/**
	 * Gets the penalizacion precancelacion.
	 *
	 * @return the penalizacionPrecancelacion
	 */
	public String getPenalizacionPrecancelacion() {
		return penalizacionPrecancelacion;
	}

	/**
	 * Sets the penalizacion precancelacion.
	 *
	 * @param penalizacionPrecancelacion
	 *            the penalizacionPrecancelacion to set
	 */
	public void setPenalizacionPrecancelacion(String penalizacionPrecancelacion) {
		this.penalizacionPrecancelacion = penalizacionPrecancelacion;
	}

	/**
	 * Gets the intereses A cobrar.
	 *
	 * @return the interesesACobrar
	 */
	public String getInteresesACobrar() {
		return interesesACobrar;
	}

	/**
	 * Sets the intereses A cobrar.
	 *
	 * @param interesesACobrar
	 *            the interesesACobrar to set
	 */
	public void setInteresesACobrar(String interesesACobrar) {
		this.interesesACobrar = interesesACobrar;
	}

	/**
	 * Gets the interes precancelacion.
	 *
	 * @return the interesPrecancelacion
	 */
	public String getInteresPrecancelacion() {
		return interesPrecancelacion;
	}

	/**
	 * Sets the interes precancelacion.
	 *
	 * @param interesPrecancelacion
	 *            the interesPrecancelacion to set
	 */
	public void setInteresPrecancelacion(String interesPrecancelacion) {
		this.interesPrecancelacion = interesPrecancelacion;
	}

	
}
