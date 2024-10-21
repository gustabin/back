/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.io.Serializable;

/**
 * The Class PrestamosDisponibles.
 */
public class PrestamosDisponibles implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1931226893019731765L;

	/** The tiene personal. */
	private Boolean tienePersonal;

	/** The tiene hipotecario. */
	private Boolean tieneHipotecario;

	/** The tiene prendario. */
	private Boolean tienePrendario;

    /** The tiene OpenCredit. */
    private Boolean tieneOpenCredit;
    
    /** PreAprobado MonoProducto*/
    private Boolean tienePrearpobadoMonoproducto;

	/** Prestamos encolados*/
    private Boolean tieneEncolados;

	private Boolean contieneRefinanciacion;

	/**
	 * Instantiates a new prestamos disponibles.
	 */
	public PrestamosDisponibles() {
		this.tieneHipotecario = false;
		this.tienePersonal = false;
		this.tienePrendario = false;
		this.tieneOpenCredit = false;
		this.tienePrearpobadoMonoproducto = false;
		this.tieneEncolados = false;
		this.contieneRefinanciacion = false;
	}

	
	/**
	 * Tiene prestamo.
	 *
	 * @return the boolean
	 */
	public Boolean tienePrestamo() {
		return this.tienePersonal || this.tieneHipotecario || this.tienePrendario || this.tieneOpenCredit || this.tienePrearpobadoMonoproducto;
	}

	/**
	 * Gets the tiene personal.
	 *
	 * @return the tiene personal
	 */
	public Boolean getTienePersonal() {
		return tienePersonal;
	}

	/**
	 * Sets the tiene personal.
	 *
	 * @param tienePersonal
	 *            the new tiene personal
	 */
	public void setTienePersonal(Boolean tienePersonal) {
		this.tienePersonal = tienePersonal;
	}

	/**
	 * Gets the tiene hipotecario.
	 *
	 * @return the tiene hipotecario
	 */
	public Boolean getTieneHipotecario() {
		return tieneHipotecario;
	}

	/**
	 * Sets the tiene hipotecario.
	 *
	 * @param tieneHipotecario
	 *            the new tiene hipotecario
	 */
	public void setTieneHipotecario(Boolean tieneHipotecario) {
		this.tieneHipotecario = tieneHipotecario;
	}

	/**
	 * Gets the tiene prendario.
	 *
	 * @return the tiene prendario
	 */
	public Boolean getTienePrendario() {
		return tienePrendario;
	}

	/**
	 * Sets the tiene prendario.
	 *
	 * @param tienePrendario
	 *            the new tiene prendario
	 */
	public void setTienePrendario(Boolean tienePrendario) {
		this.tienePrendario = tienePrendario;
	}

    /**
	 * Gets the tiene open credit.
	 *
	 * @return the tieneOpenCredit
	 */
    public Boolean getTieneOpenCredit() {
        return tieneOpenCredit;
    }

    /**
	 * Sets the tiene open credit.
	 *
	 * @param tieneOpenCredit
	 *            the tieneOpenCredit to set
	 */
    public void setTieneOpenCredit(Boolean tieneOpenCredit) {
        this.tieneOpenCredit = tieneOpenCredit;
    }

	public Boolean getTienePrearpobadoMonoproducto() {
		return tienePrearpobadoMonoproducto;
	}

	public void setTienePrearpobadoMonoproducto(Boolean tienePrearpobadoMonoproducto) {
		this.tienePrearpobadoMonoproducto = tienePrearpobadoMonoproducto;
	}

	public Boolean getTieneEncolados() {
		return tieneEncolados;
	}

	public void setTieneEncolados(Boolean tieneEncolados) {
		this.tieneEncolados = tieneEncolados;
	}

	public Boolean getContieneRefinanciacion() {
		return contieneRefinanciacion;
	}

	public void setContieneRefinanciacion(Boolean contieneRefinanciacion) {
		this.contieneRefinanciacion = contieneRefinanciacion;
	}
}
