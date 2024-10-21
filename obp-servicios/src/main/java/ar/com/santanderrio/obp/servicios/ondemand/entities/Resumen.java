/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ondemand.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * The Class Resumen.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Resumen {

	/** The fecha. */
	@XmlAttribute(name = "fecha")
	private String fecha;

	/** The folder. */
	@XmlAttribute(required = false, name = "folder")
	private String folder;

	/** The paquete. */
	@XmlAttribute(required = false, name = "paquete")
	private String paquete;

	/** The producto. */
	@XmlAttribute(required = false, name = "producto")
	private String producto;

    /** The fecha desde. */
    @XmlAttribute(name = "fecha-desde")
    private String fechaDesde;

    /** The fecha hasta. */
    @XmlAttribute(name = "fecha-hasta")
    private String fechaHasta;

    /** The cuenta. */
    @XmlAttribute(name = "resumen-cuenta")
    private String cuenta;

    /** The periodo. */
    @XmlAttribute(name = "periodo")
    private String periodo;

    @XmlAttribute(name = "referencia")
    private String referencia;
 
    @XmlAttribute(name = "nro_liquidacion")
    private String nroLiquidacion;
    
    
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the folder.
	 *
	 * @return the folder
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Sets the folder.
	 *
	 * @param folder
	 *            the new folder
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

    /**
     * @return the fechaDesde
     */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
