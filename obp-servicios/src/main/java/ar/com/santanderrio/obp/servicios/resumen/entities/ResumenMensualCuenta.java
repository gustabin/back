/*
 * 
 */
package ar.com.santanderrio.obp.servicios.resumen.entities;

import java.util.Date;

/**
 * The Class ResumenMensualCuenta.
 */
public class ResumenMensualCuenta {

	/** The id. */
	private Long id;

	/** The visto. */
	private Boolean visto;

	/** The fecha. */
	private Date fecha;

	/** The carpeta. */
	private String carpeta;

	/** The paquete. */
	private String paquete;

	/** The producto. */
	private String producto;

	/** The producto. */
	private String docId;
	
	/** The is advance. */
	private boolean isAdvance = false;
	
	private String referencia;
	
	private String nroLiquidacion;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the paquete.
	 *
	 * @return the paquete
	 */
	public String getPaquete() {
		return paquete;
	}

	/**
	 * Sets the paquete.
	 *
	 * @param paquete
	 *            the new paquete
	 */
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto
	 *            the new producto
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * Gets the visto.
	 *
	 * @return the visto
	 */
	public Boolean getVisto() {
		return visto;
	}

	/**
	 * Sets the visto.
	 *
	 * @param visto
	 *            the new visto
	 */
	public void setVisto(Boolean visto) {
		this.visto = visto;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return (fecha != null) ? new Date(fecha.getTime()) : null;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
	}

	/**
	 * Gets the carpeta.
	 *
	 * @return the carpeta
	 */
	public String getCarpeta() {
		return carpeta;
	}

	/**
	 * Sets the carpeta.
	 *
	 * @param carpeta
	 *            the new carpeta
	 */
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

    /**
	 * Gets the doc id.
	 *
	 * @return the docId
	 */
    public String getDocId() {
        return docId;
    }

    /**
	 * Sets the doc id.
	 *
	 * @param docId
	 *            the docId to set
	 */
    public void setDocId(String docId) {
        this.docId = docId;
    }

	/**
	 * Checks if is advance.
	 *
	 * @return true, if is advance
	 */
	public boolean isAdvance() {
		return isAdvance;
	}

	/**
	 * Sets the advance.
	 *
	 * @param isAdvance the new advance
	 */
	public void setAdvance(boolean isAdvance) {
		this.isAdvance = isAdvance;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNroLiquidacion() {
		return nroLiquidacion;
	}

	public void setNroLiquidacion(String nroLiquidacion) {
		this.nroLiquidacion = nroLiquidacion;
	}
	
}
