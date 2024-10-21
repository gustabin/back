/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import org.apache.commons.lang.WordUtils;

import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;

/**
 * The Class ConsultaMonedasView.
 *
 * @author IT Resources
 */
public class ConsultaMonedasView {

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/** The codigo BCRA. */
	private String codigoBCRA;

	public ConsultaMonedasView() {
		super();
	}

	public ConsultaMonedasView(ConsultaMonedaOutDTO monedaDTO) {
		this.descripcion = WordUtils.capitalizeFully(monedaDTO.getDescripcionMoneda()) + "("
				+ monedaDTO.getCodBCRAMoneda() + ")";
		this.id = monedaDTO.getCodigoMoneda();
		this.codigoBCRA = monedaDTO.getCodBCRAMoneda();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo BCRA.
	 *
	 * @return the codigoBCRA
	 */
	public String getCodigoBCRA() {
		return codigoBCRA;
	}

	/**
	 * Sets the codigo BCRA.
	 *
	 * @param codigoBCRA the codigoBCRA to set
	 */
	public void setCodigoBCRA(String codigoBCRA) {
		this.codigoBCRA = codigoBCRA;
	}

}
