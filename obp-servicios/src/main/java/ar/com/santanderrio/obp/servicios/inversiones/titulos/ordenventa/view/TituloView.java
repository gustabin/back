/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.util.List;

/**
 * The Class TituloView.
 */
public class TituloView {
	
	/** The numero cuenta titulo. */
	private String numeroCuentaTitulo;
	
	/** The numero cuenta operativa. */
	private String numeroCuentaOperativa;
	
	/** The titulares. */
	private List<String> titulares;
	
	/** The titulos para venta. */
	private List<InfoTitulo> titulosParaVenta;
	
	/** The no hay titulos para vender. */
	private Boolean noHayTitulosParaVender = false;

	
	/**
	 * Gets the numero cuenta titulo.
	 *
	 * @return the numero cuenta titulo
	 */
	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	/**
	 * Sets the numero cuenta titulo.
	 *
	 * @param numeroCuentaTitulo
	 *            the new numero cuenta titulo
	 */
	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
	}

	/**
	 * Gets the titulares.
	 *
	 * @return the titulares
	 */
	public List<String> getTitulares() {
		return titulares;
	}

	/**
	 * Sets the titulares.
	 *
	 * @param titulares
	 *            the new titulares
	 */
	public void setTitulares(List<String> titulares) {
		this.titulares = titulares;
	}

	/**
	 * Gets the titulos para venta.
	 *
	 * @return the titulos para venta
	 */
	public List<InfoTitulo> getTitulosParaVenta() {
		return titulosParaVenta;
	}

	/**
	 * Sets the titulos para venta.
	 *
	 * @param titulosParaVenta
	 *            the new titulos para venta
	 */
	public void setTitulosParaVenta(List<InfoTitulo> titulosParaVenta) {
		this.titulosParaVenta = titulosParaVenta;
	}
		
	/**
	 * Gets the no hay titulos para vender.
	 *
	 * @return the no hay titulos para vender
	 */
	public Boolean getNoHayTitulosParaVender() {
		return noHayTitulosParaVender;
	}

	/**
	 * Sets the no hay titulos para vender.
	 *
	 * @param noHayTitulosParaVender
	 *            the new no hay titulos para vender
	 */
	public void setNoHayTitulosParaVender(Boolean noHayTitulosParaVender) {
		this.noHayTitulosParaVender = noHayTitulosParaVender;
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

}