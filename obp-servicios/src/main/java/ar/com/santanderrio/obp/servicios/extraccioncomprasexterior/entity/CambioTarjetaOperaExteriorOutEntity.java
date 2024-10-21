/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * CambioTarjetaOperaExteriorOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class CambioTarjetaOperaExteriorOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The tipo llamado. */
	@Field
	private String tipoLlamado;

	/** The cantidad afectada. */
	@Field
	private String cantidadAfectada;

	/** The cantidad registros. */
	@Field
	private Long cantidadRegistros;

	/** The lista tarjetas. */
	@Segment(occursRef = "cantidadRegistros")
	private List<ModifTarjetaOperaExteriorEntity> listaTarjetas;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

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
	 * @param headerTrama
	 *            the new header trama
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
	 * @param codigoRetornoExtendido
	 *            the new codigo retorno extendido
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the tipo llamado.
	 *
	 * @return the tipo llamado
	 */
	public String getTipoLlamado() {
		return tipoLlamado;
	}

	/**
	 * Sets the tipo llamado.
	 *
	 * @param tipoLlamado
	 *            the new tipo llamado
	 */
	public void setTipoLlamado(String tipoLlamado) {
		this.tipoLlamado = tipoLlamado;
	}

	/**
	 * Gets the cantidad afectada.
	 *
	 * @return the cantidad afectada
	 */
	public String getCantidadAfectada() {
		return cantidadAfectada;
	}

	/**
	 * Sets the cantidad afectada.
	 *
	 * @param cantidadAfectada
	 *            the new cantidad afectada
	 */
	public void setCantidadAfectada(String cantidadAfectada) {
		this.cantidadAfectada = cantidadAfectada;
	}

	/**
	 * Gets the cantidad registros.
	 *
	 * @return the cantidad registros
	 */
	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	/**
	 * Sets the cantidad registros.
	 *
	 * @param cantidadRegistros
	 *            the new cantidad registros
	 */
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the lista tarjetas.
	 *
	 * @return the lista tarjetas
	 */
	public List<ModifTarjetaOperaExteriorEntity> getListaTarjetas() {
		return listaTarjetas;
	}

	/**
	 * Sets the lista tarjetas.
	 *
	 * @param listaTarjetas
	 *            the new lista tarjetas
	 */
	public void setListaTarjetas(List<ModifTarjetaOperaExteriorEntity> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

}
