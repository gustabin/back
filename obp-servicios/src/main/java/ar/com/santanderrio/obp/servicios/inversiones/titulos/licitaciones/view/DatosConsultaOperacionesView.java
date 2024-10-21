/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaOperacionesView.
 */
public class DatosConsultaOperacionesView {
	
    /** The descripcion especie. */
    @JsonProperty("Especie")
    private String descripcionEspecie;  
    
	/** fechaOrden. */
	@JsonProperty("FechaOrden")
	private String fechaOrden;
    
	 
    /** The numero orden. */
    @JsonProperty("OrdenId")
    private String numeroOrden;
	
    /** The tipo especie. */
    @JsonProperty("TipoEspecie")
    private String tipoEspecie;
    
    /** The moneda. */
    @JsonProperty("CodMoneda")
    private String moneda;
    
    /** The cantidad nominales. */
    @JsonProperty("Cantidad")
    private String cantidadNominales;
	
    /** The precio. */
    @JsonProperty("Precio")
    private String precio;
    
    /** The signo. */
    @JsonProperty("Signo")
    private String signo;
    
    /** The tipo operacion. */
    @JsonProperty("TipoOperacion")
    private String tipoOperacion;

    
    
    
	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fecha orden
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden
	 *            the new fecha orden
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
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
	 * Gets the signo.
	 *
	 * @return the signo
	 */
	public String getSigno() {
		return signo;
	}

	/**
	 * Sets the signo.
	 *
	 * @param signo
	 *            the new signo
	 */
	public void setSigno(String signo) {
		this.signo = signo;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
    
    

}
