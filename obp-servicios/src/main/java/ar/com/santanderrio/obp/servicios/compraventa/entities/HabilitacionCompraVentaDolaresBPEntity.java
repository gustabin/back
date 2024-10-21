package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;


/**
 * The Class HabilitacionCompraVentaDolaresBPEntity.
 */
@Record
public class HabilitacionCompraVentaDolaresBPEntity {
	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;
	
	
	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;
	
	/** The cantidad de miscelaneos. */
	@Field
	private String cantidadDeMiscelaneos;

	/** The codigo de miscelaneo. */
	@Field
	private String codigoDeMiscelaneo;
	
	/** The fecha de inicio de bloqueo. */
	@Field
	private String fechaDeInicioDeBloqueo;
	
	/** The codigo error de miscelaneo. */
	@Field
	private String codErrorDeMisceleano;
	
	/** The error en consulta. */
	private Boolean errorEnConsulta = false;

	/**
	 * Gets the header trama.
	 *
	 * @return the header trama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama the new header trama
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigo retorno extendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the cantidad de miscelaneos.
	 *
	 * @return the cantidad de miscelaneos
	 */
	public String getCantidadDeMiscelaneos() {
		return cantidadDeMiscelaneos;
	}

	/**
	 * Sets the cantidad de miscelaneos.
	 *
	 * @param cantidadDeMiscelaneos the new cantidad de miscelaneos
	 */
	public void setCantidadDeMiscelaneos(String cantidadDeMiscelaneos) {
		this.cantidadDeMiscelaneos = cantidadDeMiscelaneos;
	}

	/**
	 * Gets the codigo de miscelaneo.
	 *
	 * @return the codigo de miscelaneo
	 */
	public String getCodigoDeMiscelaneo() {
		return codigoDeMiscelaneo;
	}

	/**
	 * Sets the codigo de miscelaneo.
	 *
	 * @param codigoDeMiscelaneo the new codigo de miscelaneo
	 */
	public void setCodigoDeMiscelaneo(String codigoDeMiscelaneo) {
		this.codigoDeMiscelaneo = codigoDeMiscelaneo;
	}

	/**
	 * Gets the cod error de misceleano.
	 *
	 * @return the cod error de misceleano
	 */
	public String getCodErrorDeMisceleano() {
		return codErrorDeMisceleano;
	}
	
	/**
	 * Sets the cod error de misceleano.
	 *
	 * @param codErrorDeMisceleano the new fecha de inicio de bloqueo
	 */
	public void setCodErrorDeMisceleano(String codErrorDeMisceleano) {
		this.codErrorDeMisceleano = codErrorDeMisceleano;
	}

	/**
	 * Gets the fecha de inicio de bloqueo.
	 *
	 * @return the fecha de inicio de bloqueo
	 */
	public String getFechaDeInicioDeBloqueo() {
		return fechaDeInicioDeBloqueo;
	}

	/**
	 * Sets the fecha de inicio de bloqueo.
	 *
	 * @param fechaDeInicioDeBloqueo the new fecha de inicio de bloqueo
	 */
	public void setFechaDeInicioDeBloqueo(String fechaDeInicioDeBloqueo) {
		this.fechaDeInicioDeBloqueo = fechaDeInicioDeBloqueo;
	}

	/**
	 * Gets the error en consulta.
	 *
	 * @return the error en consulta
	 */
	public Boolean getErrorEnConsulta() {
		return errorEnConsulta;
	}

	/**
	 * Sets the error en consulta.
	 *
	 * @param errorEnConsulta the new error en consulta
	 */
	public void setErrorEnConsulta(Boolean errorEnConsulta) {
		this.errorEnConsulta = errorEnConsulta;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}
	
	
	
	
}
