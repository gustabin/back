/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaAdicionalSolicitadaView;

/**
 * The Class AltaTarjetaCreditoAdicionalOutEntity.
 */
@Record
public class AltaTarjetaCreditoAdicionalOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The apellido nombre adicional. */
	@Field
	private String apellidoNombreAdicional;

	/** The dni adicional. */
	@Field
	private String dniAdicional;

	/** The tarjetas adicionales solicitadas. */
	@Field
	private List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas;

	/** The fecha hora. */
	@Field
	private String fechaHora;

	/** The legal. */
	@Field
	private String legal;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the apellido nombre adicional.
	 *
	 * @return the apellidoNombreAdicional
	 */
	public String getApellidoNombreAdicional() {
		return apellidoNombreAdicional;
	}

	/**
	 * Sets the apellido nombre adicional.
	 *
	 * @param apellidoNombreAdicional
	 *            the apellidoNombreAdicional to set
	 */
	public void setApellidoNombreAdicional(String apellidoNombreAdicional) {
		this.apellidoNombreAdicional = apellidoNombreAdicional;
	}

	/**
	 * Gets the dni adicional.
	 *
	 * @return the dniAdicional
	 */
	public String getDniAdicional() {
		return dniAdicional;
	}

	/**
	 * Sets the dni adicional.
	 *
	 * @param dniAdicional
	 *            the dniAdicional to set
	 */
	public void setDniAdicional(String dniAdicional) {
		this.dniAdicional = dniAdicional;
	}

	/**
	 * Gets the tarjetas adicionales solicitadas.
	 *
	 * @return the tarjetasAdicionalesSolicitadas
	 */
	public List<TarjetaAdicionalSolicitadaView> getTarjetasAdicionalesSolicitadas() {
		return tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Sets the tarjetas adicionales solicitadas.
	 *
	 * @param tarjetasAdicionalesSolicitadas
	 *            the tarjetasAdicionalesSolicitadas to set
	 */
	public void setTarjetasAdicionalesSolicitadas(List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas) {
		this.tarjetasAdicionalesSolicitadas = tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

}
