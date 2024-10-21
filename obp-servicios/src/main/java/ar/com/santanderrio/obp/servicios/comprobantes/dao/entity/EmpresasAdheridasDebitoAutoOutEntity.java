/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class EmpresasAdheridasDebitoAutoOutEntity.
 */
@Record
public class EmpresasAdheridasDebitoAutoOutEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The header trama. */
	@Field
	private String headerTrama;

	/** Codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;
	
	/**
	 * Indicador fin de datos
	 */
	@Field
	private String finDatos;
	
	/**
	 * Bloque de datos para rellamada
	 */
	@Field
	private String bloqueRellamada;
	

	/** Cantidad de registros. */
	@Field
	private Long cantidadRegistros;

	/** The empresas. */
	@Segment(occursRef = "cantidadRegistros")
	private List<EmpresaAdheridaEntity> empresas = new ArrayList<EmpresaAdheridaEntity>();

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
	 * getter finDatos
	 * @return
	 */
	public String getFinDatos() {
		return finDatos;
	}

	/**
	 * setter finDatos
	 * @param finDatos
	 */
	public void setFinDatos(String finDatos) {
		this.finDatos = finDatos;
	}

	/**
	 * getter bloqueRellamada
	 * @return
	 */
	public String getBloqueRellamada() {
		return bloqueRellamada;
	}

	/**
	 * setter bloqueRellamada
	 * @param bloqueRellamada
	 */
	public void setBloqueRellamada(String bloqueRellamada) {
		this.bloqueRellamada = bloqueRellamada;
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
	 * @param cantidadRegistros the new cantidad registros
	 */
	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	/**
	 * Gets the empresas.
	 *
	 * @return the destinatarios
	 */
	public List<EmpresaAdheridaEntity> getEmpresas() {
		return empresas;
	}

	/**
	 * Sets the empresas.
	 *
	 * @param empresas the new destinatarios
	 */
	public void setEmpresas(List<EmpresaAdheridaEntity> empresas) {
		this.empresas = empresas;
	}

}
