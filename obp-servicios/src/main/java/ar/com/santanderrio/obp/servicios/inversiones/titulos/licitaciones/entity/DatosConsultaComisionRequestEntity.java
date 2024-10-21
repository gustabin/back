/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaEspeciesRequestEntity.
 */
public class DatosConsultaComisionRequestEntity{
	
	/** The descripcion nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The CtaTitulos. */
	@JsonProperty("CtaTitulos")
	private String ctaTitulos;
	
	/** The codigoEspecie. */
	@JsonProperty("CodigoEspecie")
	private String codigoEspecie;
	
	/** The tipoOperacion. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	
	/** The idMercado. */
	@JsonProperty("IdMercado")
	private int idMercado;

	
	/**
	 * Sets the codigoEspecie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCtaTitulos(String ctaTitulos) {
		this.ctaTitulos = ctaTitulos;
	}
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getCtaTitulos() {
		return ctaTitulos;
	}
	
	/**
	 * Sets the codigoEspecie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}
	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Gets the codigoEspecie.
	 *
	 * @return the codigoEspecie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigoEspecie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the tipoOperacion.
	 *
	 * @return the tipoOperacion
	 */
	public String geTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipoOperacion.
	 *
	 * @param string
	 *            the tipoOperacion to set
	 */
	public void seTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


	/**
	 * Gets the idMercado.
	 *
	 * @return the idMercado
	 */
	public int getIdMercado() {
		return idMercado;
	}

	/**
	 * Sets the idMercado.
	 *
	 * @param idMercado
	 *            the idMercado to set
	 */
	public void setIdMercado(int idMercado) {
		this.idMercado = idMercado;
	}

}
