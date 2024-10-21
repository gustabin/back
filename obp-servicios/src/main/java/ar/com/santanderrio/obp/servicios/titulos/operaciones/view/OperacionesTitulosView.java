/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import java.util.List;

/**
 * The Class OperacionesTitulosView.
 */
public class OperacionesTitulosView {
    
    /** The hay mas. */
    private Boolean hayMas;
    
    /** The operaciones. */
    private List<OperacionTitulosView> operaciones;
    
    /** The mensaje vacio. */
    private String mensajeVacio;
    
    /** The legales. */
    private String legales;
    
    /**
	 * Gets the hay mas.
	 *
	 * @return the hay mas
	 */
    public Boolean getHayMas() {
        return hayMas;
    }
    
    /**
	 * Sets the hay mas.
	 *
	 * @param hayMas
	 *            the new hay mas
	 */
    public void setHayMas(Boolean hayMas) {
        this.hayMas = hayMas;
    }
    
    /**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
    public List<OperacionTitulosView> getOperaciones() {
        return operaciones;
    }
    
    /**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the new operaciones
	 */
    public void setOperaciones(List<OperacionTitulosView> operaciones) {
        this.operaciones = operaciones;
    }
    
    /**
	 * Gets the mensaje vacio.
	 *
	 * @return the mensaje vacio
	 */
    public String getMensajeVacio() {
        return mensajeVacio;
    }
    
    /**
	 * Sets the mensaje vacio.
	 *
	 * @param mensajeVacio
	 *            the new mensaje vacio
	 */
    public void setMensajeVacio(String mensajeVacio) {
        this.mensajeVacio = mensajeVacio;
    }
	
	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}
	
	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
    
    

}
