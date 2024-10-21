/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

/**
 * The Class OperacionTitulosView.
 */
public class OperacionTitulosView extends DetalleComprobanteView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fecha. */
	private String fecha;
	
	/** The numero. */
	private String numero;
	
	/** The tipo. */
	private String tipo;
	
	/** The moneda. */
	private String moneda;
	
	/** The cantidad nominales. */
	private String cantidadNominales;
	
	/** The precio. */
	private String precio;
	
	/** The legal. */
	private String legal;
	
	private String tipoPliego;
	
	/** The fecha key. */
	protected final String fechaKey = "FECHA";
	
	/** The numero operacion key. */
	protected final String numeroOperacionKey = "NUMERO_OPERACION";
	
	/** The tipo especie key. */
	protected final String tipoEspecieKey = "TIPO_ESPECIE";
	
	/** The moneda key. */
	protected final String monedaKey = "MONEDA";
	
	/** The cantidad nominales key. */
	protected final String cantidadNominalesKey = "CANTIDAD_NOMINALES";
	
	/** The legal key. */
	protected final String legalKey = "LEGAL";
	
	/** The precio key. */
	protected final String precioKey = "PRECIO";
	
	/** The cuenta titulos key. */
	protected final String cuentaTitulosKey = "CUENTA_TITULOS";
	
	/** The comisiones key. */
	protected final String comisionesKey = "COMISIONES";
	
	/** The impuestos key. */
	protected final String impuestosKey = "IMPUESTOS";
	
	/** The path inversiones. */
	protected final String pathInversiones = "inversiones/";

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
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#getFecha()
	 */
	public String getFecha() {
		return fecha;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView#setFecha(java.lang.String)
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the cantidad nominales.
	 *
	 * @return the cantidad nominales
	 */
	public String getCantidadNominales() {
		return cantidadNominales;
	}

	/**
	 * Sets the cantidad nominales.
	 *
	 * @param cantidadNominales
	 *            the new cantidad nominales
	 */
	public void setCantidadNominales(String cantidadNominales) {
		this.cantidadNominales = cantidadNominales;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio
	 *            the new precio
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getTipoPliego() {
		return tipoPliego;
	}

	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
	}

}
