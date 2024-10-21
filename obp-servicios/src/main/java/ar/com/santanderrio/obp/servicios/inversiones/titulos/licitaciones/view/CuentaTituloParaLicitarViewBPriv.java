/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class CuentaTituloParaLicitarViewBPriv.
 */
public class CuentaTituloParaLicitarViewBPriv {
	
	/** The numero cuenta operativa formateado. */
	private String numeroCuentaOperativaFormateado;
	
	/** The numero cuenta titulo. */
	private String numeroCuentaTitulo;
	
	/** The numero cuenta operativa. */
	private String numeroCuentaOperativa;
	
	/** The sucursal. */
	private String sucursal;
	
	/** intervinientes de la cuenta. */
	private List<Interviniente> intervinientes = new ArrayList<Interviniente>();

	/** lista de licitaciones vigentes para el nro cuenta. */
	List<LicitacionVigente> licitacionesVigentesList = new ArrayList<LicitacionVigente>();

	/**
	 * Gets the numero cuenta titulo.
	 *
	 * @return the numeroCuentaTitulo
	 */
	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	/**
	 * Sets the numero cuenta titulo.
	 *
	 * @param numeroCuentaTitulo
	 *            the numeroCuentaTitulo to set
	 */
	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
	}

	/**
	 * Gets the numero cuenta operativa.
	 *
	 * @return the numeroCuentaOperativa
	 */
	public String getNumeroCuentaOperativa() {
		return numeroCuentaOperativa;
	}

	/**
	 * Sets the numero cuenta operativa.
	 *
	 * @param numeroCuentaOperativa
	 *            the numeroCuentaOperativa to set
	 */
	public void setNumeroCuentaOperativa(String numeroCuentaOperativa) {
		this.numeroCuentaOperativa = numeroCuentaOperativa;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the intervinientes to set
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the licitaciones vigentes list.
	 *
	 * @return the licitacionesVigentesList
	 */
	public List<LicitacionVigente> getLicitacionesVigentesList() {
		return licitacionesVigentesList;
	}

	/**
	 * Sets the licitaciones vigentes list.
	 *
	 * @param licitacionesVigentesList
	 *            the licitacionesVigentesList to set
	 */
	public void setLicitacionesVigentesList(List<LicitacionVigente> licitacionesVigentesList) {
		this.licitacionesVigentesList = licitacionesVigentesList;
	}

	/**
	 * Gets the numero cuenta operativa formateado.
	 *
	 * @return the numeroCuentaOperativaFormateado
	 */
	public String getNumeroCuentaOperativaFormateado() {
		return numeroCuentaOperativaFormateado;
	}

	/**
	 * Sets the numero cuenta operativa formateado.
	 *
	 * @param numeroCuentaOperativaFormateado
	 *            the numeroCuentaOperativaFormateado to set
	 */
	public void setNumeroCuentaOperativaFormateado(String numeroCuentaOperativaFormateado) {
		this.numeroCuentaOperativaFormateado = numeroCuentaOperativaFormateado;
	}
}
