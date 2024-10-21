/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.PlazoFijoVencidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto.TotalesGrillaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.TenenciaValuadaPlazoFijoDTO;

/**
 * The Class TenenciaPlazoFijoView.
 */
public class TenenciaPlazoFijoView {
	
	/** The tenencia plazo fijo pesos. */
	private List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoPesos = new ArrayList<TenenciaValuadaPlazoFijoDTO>();
	
	/** The tenencia plazo fijo dolares. */
	private List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoDolares = new ArrayList<TenenciaValuadaPlazoFijoDTO>();

    /** The legales. */
    private String legales;
       
    /** The legalesCer. */
    private String legalesCer;
    
    /** The plazosFijosVencidos. */
    private List<PlazoFijoVencidoDTO> plazosFijosVencidos = new ArrayList<PlazoFijoVencidoDTO>();

	/** Plazos fijos proximos a vencer en pesos, para el grafico. */
    private Map<String, BigDecimal> plazosProximosVencerPesos = new HashMap<String, BigDecimal>();
    
	/** Plazos fijos proximos a vencer en dolares, para el grafico. */
    private Map<String, BigDecimal> plazosProximosVencerDolares = new HashMap<String, BigDecimal>();
	
    /** Totales de grilla. */
    private TotalesGrillaDTO totalesGrilla;
	
	/**
	 * Gets the tenencia plazo fijo pesos.
	 *
	 * @return the tenencia plazo fijo pesos
	 */
	public List<TenenciaValuadaPlazoFijoDTO> getTenenciaPlazoFijoPesos() {
		return tenenciaPlazoFijoPesos;
	}

	/**
	 * Sets the tenencia plazo fijo pesos.
	 *
	 * @param tenenciaPlazoFijoPesos
	 *            the new tenencia plazo fijo pesos
	 */
	public void setTenenciaPlazoFijoPesos(List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoPesos) {
		this.tenenciaPlazoFijoPesos = tenenciaPlazoFijoPesos;
	}

	/**
	 * Gets the tenencia plazo fijo dolares.
	 *
	 * @return the tenencia plazo fijo dolares
	 */
	public List<TenenciaValuadaPlazoFijoDTO> getTenenciaPlazoFijoDolares() {
		return tenenciaPlazoFijoDolares;
	}

	/**
	 * Sets the tenencia plazo fijo dolares.
	 *
	 * @param tenenciaPlazoFijoDolares
	 *            the new tenencia plazo fijo dolares
	 */
	public void setTenenciaPlazoFijoDolares(List<TenenciaValuadaPlazoFijoDTO> tenenciaPlazoFijoDolares) {
		this.tenenciaPlazoFijoDolares = tenenciaPlazoFijoDolares;
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

	/**
	 * Gets the plazos fijos vencidos.
	 *
	 * @return the plazos fijos vencidos
	 */
	public List<PlazoFijoVencidoDTO> getPlazosFijosVencidos() {
		return plazosFijosVencidos;
	}

	/**
	 * Sets the plazos fijos vencidos.
	 *
	 * @param plazosFijosVencidos
	 *            the new plazos fijos vencidos
	 */
	public void setPlazosFijosVencidos(List<PlazoFijoVencidoDTO> plazosFijosVencidos) {
		this.plazosFijosVencidos = plazosFijosVencidos;
	}

	/**
	 * Gets the plazos proximos vencer pesos.
	 *
	 * @return the plazos proximos vencer pesos
	 */
	public Map<String, BigDecimal> getPlazosProximosVencerPesos() {
		return plazosProximosVencerPesos;
	}

	/**
	 * Sets the plazos proximos vencer pesos.
	 *
	 * @param plazosProximosVencerPesos
	 *            the plazos proximos vencer pesos
	 */
	public void setPlazosProximosVencerPesos(Map<String, BigDecimal> plazosProximosVencerPesos) {
		this.plazosProximosVencerPesos = plazosProximosVencerPesos;
	}

	/**
	 * Gets the plazos proximos vencer dolares.
	 *
	 * @return the plazos proximos vencer dolares
	 */
	public Map<String, BigDecimal> getPlazosProximosVencerDolares() {
		return plazosProximosVencerDolares;
	}

	/**
	 * Sets the plazos proximos vencer dolares.
	 *
	 * @param plazosProximosVencerDolares
	 *            the plazos proximos vencer dolares
	 */
	public void setPlazosProximosVencerDolares(Map<String, BigDecimal> plazosProximosVencerDolares) {
		this.plazosProximosVencerDolares = plazosProximosVencerDolares;
	}
	
	/**
	 * Gets the totales grilla.
	 *
	 * @return the totales grilla
	 */
	public TotalesGrillaDTO getTotalesGrilla() {
		return totalesGrilla;
	}

	/**
	 * Sets the totales grilla.
	 *
	 * @param totalesGrilla
	 *            the new totales grilla
	 */
	public void setTotalesGrilla(TotalesGrillaDTO totalesGrilla) {
		this.totalesGrilla = totalesGrilla;
	}

	/**
	 * Gets the legales cer.
	 *
	 * @return the legales cer
	 */
	public String getLegalesCer() {
		return legalesCer;
	}

	/**
	 * Sets the legales cer.
	 *
	 * @param legalesCer
	 *            the new legales cer
	 */
	public void setLegalesCer(String legalesCer) {
		this.legalesCer = legalesCer;
	}	
	

}
