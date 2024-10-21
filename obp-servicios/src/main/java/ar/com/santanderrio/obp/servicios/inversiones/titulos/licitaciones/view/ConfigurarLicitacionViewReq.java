/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;

/**
 * The Class ConfigurarLicitacionViewReq.
 */
public class ConfigurarLicitacionViewReq {

	/** The renovacion. */
	String renovacion;

	/** The cuenta titulo. */
	String cuentaTitulo;

	/** The monedas. */
	List<String> monedas = new ArrayList<String>();

	/** The codigo especie. */
	String codigoEspecie;
	
	/** The tipo cuenta especie. */
	String tipoCuentaEspecie;
	
	/** The tipo oferta *. */
	String tipoOferta;

	/** The tipo especie. */
	String tipoEspecie;
	
	/** The cuentas titulo. */
	private List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();

	/**
	 * Gets the renovacion.
	 *
	 * @return the renovacion
	 */
	public String getRenovacion() {
		return renovacion;
	}

	/**
	 * Sets the renovacion.
	 *
	 * @param renovacion
	 *            the new renovacion
	 */
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the monedas.
	 *
	 * @return the monedas
	 */
	public List<String> getMonedas() {
		return monedas;
	}

	/**
	 * Sets the monedas.
	 *
	 * @param monedas
	 *            the new monedas
	 */
	public void setMonedas(List<String> monedas) {
		this.monedas = monedas;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigo especie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the new codigo especie
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the tipo cuenta especie.
	 *
	 * @return the tipoCuentaEspecie
	 */
	public String getTipoCuentaEspecie() {
		return tipoCuentaEspecie;
	}

	/**
	 * Sets the tipo cuenta especie.
	 *
	 * @param tipoCuentaEspecie
	 *            the tipoCuentaEspecie to set
	 */
	public void setTipoCuentaEspecie(String tipoCuentaEspecie) {
		this.tipoCuentaEspecie = tipoCuentaEspecie;
	}

	/**
	 * Gets the tipo oferta.
	 *
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * Sets the tipo oferta.
	 *
	 * @param tipoOferta
	 *            the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipoEspecie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the tipoEspecie to set
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentasTitulo
	 */
	public List<CuentaTituloView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentasTitulo to set
	 */
	public void setCuentasTitulo(List<CuentaTituloView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
	
}
