/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.EntityBaseTitulos;

/**
 * The Class DatosConsultaTenenciaRequestEntity.
 */
public class DatosConsultaTenenciaRequestEntity extends EntityBaseTitulos {

	/** The lista cuentas titulos. */
	@JsonProperty("ListaCuentasTitulos")
	private String listaCuentasTitulos;
	
	/** The segmento. */
	@JsonProperty("SegmentoCliente")
	private String segmento;
	
	/** The canal tipo. */
	@JsonProperty("FechaCustodia")
	private String fechaCustodia;
	
	/** The estado cuenta titulo. */
	@JsonProperty("EstadoCuentaTitulo")
	private String estadoCuentaTitulo;

	/** The estado especie. */
	@JsonProperty("EstadoEspecie")
	private String estadoEspecie;

	/** The estado tenencia. */
	@JsonProperty("EstadoTenencia")
	private String estadoTenencia;

	
	/**
	 * Gets the lista cuentas titulos.
	 *
	 * @return the lista cuentas titulos
	 */
	public String getListaCuentasTitulos() {
		return listaCuentasTitulos;
	}

	/**
	 * Sets the lista cuentas titulos.
	 *
	 * @param listaCuentasTitulos
	 *            the new lista cuentas titulos
	 */
	public void setListaCuentasTitulos(String listaCuentasTitulos) {
		this.listaCuentasTitulos = listaCuentasTitulos;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the fecha custodia.
	 *
	 * @return the fecha custodia
	 */
	public String getFechaCustodia() {
		return fechaCustodia;
	}

	/**
	 * Sets the fecha custodia.
	 *
	 * @param fechaCustodia
	 *            the new fecha custodia
	 */
	public void setFechaCustodia(String fechaCustodia) {
		this.fechaCustodia = fechaCustodia;
	}

	/**
	 * Gets the estado cuenta titulo.
	 *
	 * @return the estado cuenta titulo
	 */
	public String getEstadoCuentaTitulo() {
		return estadoCuentaTitulo;
	}

	/**
	 * Sets the estado cuenta titulo.
	 *
	 * @param estadoCuentaTitulo
	 *            the new estado cuenta titulo
	 */
	public void setEstadoCuentaTitulo(String estadoCuentaTitulo) {
		this.estadoCuentaTitulo = estadoCuentaTitulo;
	}

	/**
	 * Gets the estado especie.
	 *
	 * @return the estado especie
	 */
	public String getEstadoEspecie() {
		return estadoEspecie;
	}

	/**
	 * Sets the estado especie.
	 *
	 * @param estadoEspecie
	 *            the new estado especie
	 */
	public void setEstadoEspecie(String estadoEspecie) {
		this.estadoEspecie = estadoEspecie;
	}

	/**
	 * Gets the estado tenencia.
	 *
	 * @return the estado tenencia
	 */
	public String getEstadoTenencia() {
		return estadoTenencia;
	}

	/**
	 * Sets the estado tenencia.
	 *
	 * @param estadoTenencia
	 *            the new estado tenencia
	 */
	public void setEstadoTenencia(String estadoTenencia) {
		this.estadoTenencia = estadoTenencia;
	}
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		
		
		Date date = new Date();
		
		System.out.println(date.toString());
	}

}
