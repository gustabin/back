/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ChequesASimularDTO.
 */
public class ChequesASimularDTO {
	
	/** The subprod by P. */
	private String subprodByP;
	
	/** The tipo cuenta. */
	private String tipoCuenta;
	
	/** The suc cuenta. */
	private String sucCuenta;
	
	/** The nro cuenta. */
	private String nroCuenta;
	
	/** The calificado. */
	private String calificado;
	
	/** The disponible. */
	private String disponible;
	
	/** The linea. */
	private String linea;
	
	/** The tipo linea. */
	private String tipoLinea;
	
	/** The nro tramite. */
	private String nroTramite;
	
	/** The lista cheques. */
	private List<ChequeASimularDTO> listaCheques = new ArrayList<ChequeASimularDTO>();

	/**
	 * Gets the subprod by P.
	 *
	 * @return the subprod by P
	 */
	public String getSubprodByP() {
		return subprodByP;
	}

	/**
	 * Sets the subprod by P.
	 *
	 * @param subprodByP
	 *            the new subprod by P
	 */
	public void setSubprodByP(String subprodByP) {
		this.subprodByP = subprodByP;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the suc cuenta.
	 *
	 * @return the suc cuenta
	 */
	public String getSucCuenta() {
		return sucCuenta;
	}

	/**
	 * Sets the suc cuenta.
	 *
	 * @param sucCuenta
	 *            the new suc cuenta
	 */
	public void setSucCuenta(String sucCuenta) {
		this.sucCuenta = sucCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the calificado.
	 *
	 * @return the calificado
	 */
	public String getCalificado() {
		return calificado;
	}

	/**
	 * Sets the calificado.
	 *
	 * @param calificado
	 *            the new calificado
	 */
	public void setCalificado(String calificado) {
		this.calificado = calificado;
	}

	/**
	 * Gets the disponible.
	 *
	 * @return the disponible
	 */
	public String getDisponible() {
		return disponible;
	}

	/**
	 * Sets the disponible.
	 *
	 * @param disponible
	 *            the new disponible
	 */
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	/**
	 * Gets the linea.
	 *
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * Sets the linea.
	 *
	 * @param linea
	 *            the new linea
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}

	/**
	 * Gets the tipo linea.
	 *
	 * @return the tipo linea
	 */
	public String getTipoLinea() {
		return tipoLinea;
	}

	/**
	 * Sets the tipo linea.
	 *
	 * @param tipoLinea
	 *            the new tipo linea
	 */
	public void setTipoLinea(String tipoLinea) {
		this.tipoLinea = tipoLinea;
	}

	/**
	 * Gets the lista cheques.
	 *
	 * @return the lista cheques
	 */
	public List<ChequeASimularDTO> getListaCheques() {
		return listaCheques;
	}

	/**
	 * Sets the lista cheques.
	 *
	 * @param listaCheques
	 *            the new lista cheques
	 */
	public void setListaCheques(List<ChequeASimularDTO> listaCheques) {
		this.listaCheques = listaCheques;
	}

	/**
	 * Gets the nro tramite.
	 *
	 * @return the nro tramite
	 */
	public String getNroTramite() {
		return nroTramite;
	}

	/**
	 * Sets the nro tramite.
	 *
	 * @param nroTramite
	 *            the new nro tramite
	 */
	public void setNroTramite(String nroTramite) {
		this.nroTramite = nroTramite;
	}
	
	

}
