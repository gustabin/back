package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.web.view.CelularResponse;

/**
 * 
 * @author Itr
 *
 */
public class CelularMyaYCompaniasTelResponse {
	/** The hayCelular. */
	private boolean hayCelular;

	/** The celular. */
	private CelularResponse celularResponse;

	/** The companias. */
	private List<String> companias;

	/**
	 * @return the hayCelular
	 */
	public boolean isHayCelular() {
		return hayCelular;
	}

	/**
	 * @param hayCelular the hayCelular to set
	 */
	public void setHayCelular(boolean hayCelular) {
		this.hayCelular = hayCelular;
	}

	/**
	 * @return the celularResponse
	 */
	public CelularResponse getCelularResponse() {
		return celularResponse;
	}

	/**
	 * @param celularResponse the celularResponse to set
	 */
	public void setCelularResponse(CelularResponse celularResponse) {
		this.celularResponse = celularResponse;
	}

	/**
	 * @return the companias
	 */
	public List<String> getCompanias() {
		return companias;
	}

	/**
	 * @param companias the companias to set
	 */
	public void setCompanias(List<String> companias) {
		this.companias = companias;
	}

}
