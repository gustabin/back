/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.TenenciaRenovable;

/**
 * The Class ConfigurarLicitacionOutView.
 */
public class ConfigurarLicitacionOutView {
	
	/** The cuentas Debito Pesos. */
    private List<CuentasAdhesionDebitoView> cuentasDebitoPesos;
	
	/** The cuentas Debito Dolares. */
    private List<CuentasAdhesionDebitoView> cuentasDebitoDolares;
    
	/** The datos, lista de tenencias renovables. */
    private List<TenenciaRenovable> listaTenenciaRenovable;
    
	/** The tramoAyuda. */
    private String tramoAyuda;
      
    /** The condicionesGeneralesAceptadas. */
    private boolean condicionesGeneralesAceptadas = false;
    
    /** The cuentasCustodia. */
    private List<CuentaCustodiaView> cuentasCustodia = new ArrayList<CuentaCustodiaView>();
   
    /** The comprobantesCnvDisponibles. */
    private boolean comprobantesCnvDisponibles = false;
    
    /** The cuentasCustodiaTotales. */
    private List<CuentaCustodiaView> cuentasCustodiaTotales = new ArrayList<CuentaCustodiaView>();
    
	/** The cuentas titulo. */
	private List<CuentaTituloParaLicitarView> cuentasTitulo = new ArrayList<CuentaTituloParaLicitarView>();

	/**
	 * Gets the cuentas debito pesos.
	 *
	 * @return the cuentas debito pesos
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebitoPesos() {
		return cuentasDebitoPesos;
	}

	/**
	 * Sets the cuentas debito pesos.
	 *
	 * @param cuentasDebitoPesos
	 *            the new cuentas debito pesos
	 */
	public void setCuentasDebitoPesos(List<CuentasAdhesionDebitoView> cuentasDebitoPesos) {
		this.cuentasDebitoPesos = cuentasDebitoPesos;
	}

	/**
	 * Gets the cuentas debito dolares.
	 *
	 * @return the cuentas debito dolares
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDebitoDolares() {
		return cuentasDebitoDolares;
	}

	/**
	 * Sets the cuentas debito dolares.
	 *
	 * @param cuentasDebitoDolares
	 *            the new cuentas debito dolares
	 */
	public void setCuentasDebitoDolares(List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {
		this.cuentasDebitoDolares = cuentasDebitoDolares;
	}

	/**
	 * Gets the lista tenencia renovable.
	 *
	 * @return the lista tenencia renovable
	 */
	public List<TenenciaRenovable> getListaTenenciaRenovable() {
		return listaTenenciaRenovable;
	}

	/**
	 * Sets the lista tenencia renovable.
	 *
	 * @param listaTenenciaRenovable
	 *            the new lista tenencia renovable
	 */
	public void setListaTenenciaRenovable(List<TenenciaRenovable> listaTenenciaRenovable) {
		this.listaTenenciaRenovable = listaTenenciaRenovable;
	}

	/**
	 * Gets the tramo ayuda.
	 *
	 * @return the tramo ayuda
	 */
	public String getTramoAyuda() {
		return tramoAyuda;
	}

	/**
	 * Sets the tramo ayuda.
	 *
	 * @param tramoAyuda
	 *            the new tramo ayuda
	 */
	public void setTramoAyuda(String tramoAyuda) {
		this.tramoAyuda = tramoAyuda;
	}

	/**
	 * Checks if is condiciones generales aceptadas.
	 *
	 * @return true, if is condiciones generales aceptadas
	 */
	public boolean isCondicionesGeneralesAceptadas() {
		return condicionesGeneralesAceptadas;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentas custodia
	 */
	public List<CuentaCustodiaView> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the condiciones generales aceptadas.
	 *
	 * @param condicionesGeneralesAceptadas
	 *            the new condiciones generales aceptadas
	 */
	public void setCondicionesGeneralesAceptadas(boolean condicionesGeneralesAceptadas) {
		this.condicionesGeneralesAceptadas = condicionesGeneralesAceptadas;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the new cuentas custodia
	 */
	public void setCuentasCustodia(List<CuentaCustodiaView> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	/**
	 * Checks if is comprobantes cnv disponibles.
	 *
	 * @return true, if is comprobantes cnv disponibles
	 */
	public boolean isComprobantesCnvDisponibles() {
		return comprobantesCnvDisponibles;
	}

	/**
	 * Sets the comprobantes cnv disponibles.
	 *
	 * @param comprobantesCnvDisponibles
	 *            the new comprobantes cnv disponibles
	 */
	public void setComprobantesCnvDisponibles(boolean comprobantesCnvDisponibles) {
		this.comprobantesCnvDisponibles = comprobantesCnvDisponibles;
	}

	/**
	 * Gets the cuentas custodia totales.
	 *
	 * @return the cuentas custodia totales
	 */
	public List<CuentaCustodiaView> getCuentasCustodiaTotales() {
		return cuentasCustodiaTotales;
	}

	/**
	 * Sets the cuentas custodia totales.
	 *
	 * @param cuentasCustodiaTotales
	 *            the new cuentas custodia totales
	 */
	public void setCuentasCustodiaTotales(List<CuentaCustodiaView> cuentasCustodiaTotales) {
		this.cuentasCustodiaTotales = cuentasCustodiaTotales;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentasTitulo
	 */
	public List<CuentaTituloParaLicitarView> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentasTitulo to set
	 */
	public void setCuentasTitulo(List<CuentaTituloParaLicitarView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

}
