/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * The Class DescuentoChequesEntity.
 */
@Record
public class DescuentoChequesEntity {
	
	/** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;
    
    /** Tiene rellamada. */
	@Field
	private String tieneRellamada;
    
	/** Cantidad de registros. */
	@Field
	private Long cantidadRegistros;
	
	/** The operaciones. */
	@Segment(occursRef = "cantidadRegistros")
	private List<OperacionDescuentoEntity> operaciones = new ArrayList<OperacionDescuentoEntity>();
	
	/** The ultimo registro. */
	private String ultimoRegistro;

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
	 * Gets the tiene rellamada.
	 *
	 * @return the tiene rellamada
	 */
	public String getTieneRellamada() {
		return tieneRellamada;
	}

	/**
	 * Sets the tiene rellamada.
	 *
	 * @param tieneRellamada
	 *            the new tiene rellamada
	 */
	public void setTieneRellamada(String tieneRellamada) {
		this.tieneRellamada = tieneRellamada;
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
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<OperacionDescuentoEntity> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
	public void setOperaciones(List<OperacionDescuentoEntity> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * Gets the ultimo registro.
	 *
	 * @return the ultimo registro
	 */
	public String getUltimoRegistro() {
		return ultimoRegistro;
	}

	/**
	 * Sets the ultimo registro.
	 *
	 * @param ultimoRegistro
	 *            the new ultimo registro
	 */
	public void setUltimoRegistro(String ultimoRegistro) {
		this.ultimoRegistro = ultimoRegistro;
	}
	
	
	
}
