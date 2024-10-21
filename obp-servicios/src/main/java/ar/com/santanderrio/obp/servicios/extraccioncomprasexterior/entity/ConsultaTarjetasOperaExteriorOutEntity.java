/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity;

import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * CuentasOperaExteriorOutEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaTarjetasOperaExteriorOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The hay mas datos. */
	@Field
	private String hayMasDatos;

	/** The clave rellamada. */
	@Field
	private String claveRellamada;

	/** The cantidad tarjetas. */
	@Field
	private Long cantidadTarjetas;

	/** The lista tarjetas. */
	@Segment(occursRef = "cantidadTarjetas")
	private List<TarjetaOperacionExteriorEntity> listaTarjetas;

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
	 * Gets the hay mas datos.
	 *
	 * @return the hay mas datos
	 */
	public String getHayMasDatos() {
		return hayMasDatos;
	}

	/**
	 * Sets the hay mas datos.
	 *
	 * @param hayMasDatos
	 *            the new hay mas datos
	 */
	public void setHayMasDatos(String hayMasDatos) {
		this.hayMasDatos = hayMasDatos;
	}

	/**
	 * Gets the clave rellamada.
	 *
	 * @return the clave rellamada
	 */
	public String getClaveRellamada() {
		return claveRellamada;
	}

	/**
	 * Sets the clave rellamada.
	 *
	 * @param claveRellamada
	 *            the new clave rellamada
	 */
	public void setClaveRellamada(String claveRellamada) {
		this.claveRellamada = claveRellamada;
	}

	/**
	 * Gets the cantidad tarjetas.
	 *
	 * @return the cantidad tarjetas
	 */
	public Long getCantidadTarjetas() {
		return cantidadTarjetas;
	}

	/**
	 * Sets the cantidad tarjetas.
	 *
	 * @param cantidadTarjetas
	 *            the new cantidad tarjetas
	 */
	public void setCantidadTarjetas(Long cantidadTarjetas) {
		this.cantidadTarjetas = cantidadTarjetas;
	}

	/**
	 * Gets the lista tarjetas.
	 *
	 * @return the lista tarjetas
	 */
	public List<TarjetaOperacionExteriorEntity> getListaTarjetas() {
		return listaTarjetas;
	}

	/**
	 * Sets the lista tarjetas.
	 *
	 * @param listaTarjetas
	 *            the new lista tarjetas
	 */
	public void setListaTarjetas(List<TarjetaOperacionExteriorEntity> listaTarjetas) {
		this.listaTarjetas = listaTarjetas;
	}

}
