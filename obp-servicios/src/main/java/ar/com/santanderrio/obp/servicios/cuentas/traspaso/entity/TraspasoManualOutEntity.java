/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class TraspasoManualOutEntity.
 */
@Record
public class TraspasoManualOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** The cantidad cuentas. */
	@Field
	private Long cantidadCuentas;

	/** The paquetes. */
	@Segment(occursRef = "cantidadCuentas")
	private List<PaqueteEntity> paquetes = new ArrayList<PaqueteEntity>();

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
	 * Gets the cantidad cuentas.
	 *
	 * @return the cantidad cuentas
	 */
	public Long getCantidadCuentas() {
		return cantidadCuentas;
	}

	/**
	 * Sets the cantidad cuentas.
	 *
	 * @param cantidadCuentas
	 *            the new cantidad cuentas
	 */
	public void setCantidadCuentas(Long cantidadCuentas) {
		this.cantidadCuentas = cantidadCuentas;
	}

	/**
	 * Gets the paquetes.
	 *
	 * @return the paquetes
	 */
	public List<PaqueteEntity> getPaquetes() {
		return paquetes;
	}

	/**
	 * Sets the paquetes.
	 *
	 * @param paquetes
	 *            the new paquetes
	 */
	public void setPaquetes(List<PaqueteEntity> paquetes) {
		this.paquetes = paquetes;
	}

}
