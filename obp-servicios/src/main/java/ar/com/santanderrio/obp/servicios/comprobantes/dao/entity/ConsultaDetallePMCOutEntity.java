/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class ConsultaDetallePMCOutEntity.
 *
 * @author luis.pedro.lopez
 */
@Record
public class ConsultaDetallePMCOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** Leyenda de recibo. */
	@Field
	private String leyendaRecibo;

	/** Cantidad de registros. */
	@Field
	private Long cantidadPagos;

	/** The fondos. */
	@Segment(occursRef = "cantidadPagos", nillable = false)
	private List<DetallePMCEntity> destinatarios = new ArrayList<DetallePMCEntity>();

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
	 * Gets the cantidad pagos.
	 *
	 * @return the cantidad pagos
	 */
	public Long getCantidadPagos() {
		return cantidadPagos;
	}

	/**
	 * Sets the cantidad pagos.
	 *
	 * @param cantidadPagos
	 *            the new cantidad pagos
	 */
	public void setCantidadPagos(Long cantidadPagos) {
		this.cantidadPagos = cantidadPagos;
	}

	/**
	 * Gets the destinatarios.
	 *
	 * @return the destinatarios
	 */
	public List<DetallePMCEntity> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * Sets the destinatarios.
	 *
	 * @param destinatarios
	 *            the new destinatarios
	 */
	public void setDestinatarios(List<DetallePMCEntity> destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * Gets the leyenda recibo.
	 *
	 * @return the leyendaRecibo
	 */
	public String getLeyendaRecibo() {
		return leyendaRecibo;
	}

	/**
	 * Sets the leyenda recibo.
	 *
	 * @param leyendaRecibo
	 *            the leyendaRecibo to set
	 */
	public void setLeyendaRecibo(String leyendaRecibo) {
		this.leyendaRecibo = leyendaRecibo;
	}

}
