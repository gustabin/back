/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaSuscripcionSaldoPDCResponse.
 */
public class DatosConsultaSuscripcionSaldoPDCResponse {
	
	/** The cantidad filas. */
	@JsonProperty("CantidadFilas")
	private int cantidadFilas;
	
	/** The lista cuentas. */
	@JsonProperty("Cuentas")
	private List<CuentasPDC> listaCuentas = new ArrayList<CuentasPDC>();
	
	/** The codigo error middleware. */
	@JsonProperty("CodigoErrorMiddleware")
	private String codigoErrorMiddleware;

	/**
	 * Gets the cantidad filas.
	 *
	 * @return the cantidadFilas
	 */
	public int getCantidadFilas() {
		return cantidadFilas;
	}

	/**
	 * Sets the cantidad filas.
	 *
	 * @param cantidadFilas
	 *            the cantidadFilas to set
	 */
	public void setCantidadFilas(int cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the listaCuentas
	 */
	public List<CuentasPDC> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the listaCuentas to set
	 */
	public void setListaCuentas(List<CuentasPDC> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the codigo error middleware.
	 *
	 * @return the codigoErrorMiddleware
	 */
	public String getCodigoErrorMiddleware() {
		return codigoErrorMiddleware;
	}

	/**
	 * Sets the codigo error middleware.
	 *
	 * @param codigoErrorMiddleware
	 *            the codigoErrorMiddleware to set
	 */
	public void setCodigoErrorMiddleware(String codigoErrorMiddleware) {
		this.codigoErrorMiddleware = codigoErrorMiddleware;
	}

}
