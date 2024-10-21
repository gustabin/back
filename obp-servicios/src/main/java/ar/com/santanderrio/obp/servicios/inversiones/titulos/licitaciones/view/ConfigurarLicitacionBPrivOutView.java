/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.TenenciaRenovable;

/**
 * The Class ConfigurarLicitacionBPrivOutView.
 */
public class ConfigurarLicitacionBPrivOutView {

	/** The cuenta operativa. */
	CuentasAdhesionDebitoView cuentaOperativa;

	/** The cuentas custodia. */
	List<DatosObtenerSaldoCuentasCustodiaResponse> cuentasCustodia = new ArrayList<DatosObtenerSaldoCuentasCustodiaResponse>();

	/** The datos, lista de tenencias renovables. */
	private List<TenenciaRenovable> listaTenenciaRenovable = new ArrayList<TenenciaRenovable>();

	/** The tramo ayuda. */
	private String tramoAyuda;

	/** The condicionesGeneralesAceptadas. */
	private boolean condicionesGeneralesAceptadas = false;

	/** The cuentasCustodia. */
	private List<CuentaCustodiaView> cuentasCustodiaFirmar = new ArrayList<CuentaCustodiaView>();

	/** The comprobantesCnvDisponibles. */
	private boolean comprobantesCnvDisponibles = false;

	/** The cuentasCustodiaTotales. */
	private List<CuentaCustodiaView> cuentasCustodiaTotales = new ArrayList<CuentaCustodiaView>();

	/** The cuentasTitulo. */
	private List<CuentaTituloParaLicitarViewBPriv> cuentasTitulo = new ArrayList<CuentaTituloParaLicitarViewBPriv>();

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuentaOperativa
	 */
	public CuentasAdhesionDebitoView getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the cuentaOperativa to set
	 */
	public void setCuentaOperativa(CuentasAdhesionDebitoView cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Gets the cuentas custodia.
	 *
	 * @return the cuentaCustodia
	 */
	public List<DatosObtenerSaldoCuentasCustodiaResponse> getCuentasCustodia() {
		return cuentasCustodia;
	}

	/**
	 * Sets the cuentas custodia.
	 *
	 * @param cuentasCustodia
	 *            the new cuentas custodia
	 */
	public void setCuentasCustodia(List<DatosObtenerSaldoCuentasCustodiaResponse> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	/**
	 * Gets the lista tenencia renovable.
	 *
	 * @return the listaTenenciaRenovable
	 */
	public List<TenenciaRenovable> getListaTenenciaRenovable() {
		return listaTenenciaRenovable;
	}

	/**
	 * Sets the lista tenencia renovable.
	 *
	 * @param listaTenenciaRenovable
	 *            the listaTenenciaRenovable to set
	 */
	public void setListaTenenciaRenovable(List<TenenciaRenovable> listaTenenciaRenovable) {
		this.listaTenenciaRenovable = listaTenenciaRenovable;
	}

	/**
	 * Gets the tramo ayuda.
	 *
	 * @return the tramoAyuda
	 */
	public String getTramoAyuda() {
		return tramoAyuda;
	}

	/**
	 * Sets the tramo ayuda.
	 *
	 * @param tramoAyuda
	 *            the tramoAyuda to set
	 */
	public void setTramoAyuda(String tramoAyuda) {
		this.tramoAyuda = tramoAyuda;
	}

	/**
	 * Checks if is condiciones generales aceptadas.
	 *
	 * @return the condicionesGeneralesAceptadas
	 */
	public boolean isCondicionesGeneralesAceptadas() {
		return condicionesGeneralesAceptadas;
	}

	/**
	 * Sets the condiciones generales aceptadas.
	 *
	 * @param condicionesGeneralesAceptadas
	 *            the condicionesGeneralesAceptadas to set
	 */
	public void setCondicionesGeneralesAceptadas(boolean condicionesGeneralesAceptadas) {
		this.condicionesGeneralesAceptadas = condicionesGeneralesAceptadas;
	}

	/**
	 * Gets the cuentas custodia firmar.
	 *
	 * @return the cuentasCustodiaFirmar
	 */
	public List<CuentaCustodiaView> getCuentasCustodiaFirmar() {
		return cuentasCustodiaFirmar;
	}

	/**
	 * Sets the cuentas custodia firmar.
	 *
	 * @param cuentasCustodiaFirmar
	 *            the cuentasCustodiaFirmar to set
	 */
	public void setCuentasCustodiaFirmar(List<CuentaCustodiaView> cuentasCustodiaFirmar) {
		this.cuentasCustodiaFirmar = cuentasCustodiaFirmar;
	}

	/**
	 * Checks if is comprobantes cnv disponibles.
	 *
	 * @return the comprobantesCnvDisponibles
	 */
	public boolean isComprobantesCnvDisponibles() {
		return comprobantesCnvDisponibles;
	}

	/**
	 * Sets the comprobantes cnv disponibles.
	 *
	 * @param comprobantesCnvDisponibles
	 *            the comprobantesCnvDisponibles to set
	 */
	public void setComprobantesCnvDisponibles(boolean comprobantesCnvDisponibles) {
		this.comprobantesCnvDisponibles = comprobantesCnvDisponibles;
	}

	/**
	 * Gets the cuentas custodia totales.
	 *
	 * @return the cuentasCustodiaTotales
	 */
	public List<CuentaCustodiaView> getCuentasCustodiaTotales() {
		return cuentasCustodiaTotales;
	}

	/**
	 * Sets the cuentas custodia totales.
	 *
	 * @param cuentasCustodiaTotales
	 *            the cuentasCustodiaTotales to set
	 */
	public void setCuentasCustodiaTotales(List<CuentaCustodiaView> cuentasCustodiaTotales) {
		this.cuentasCustodiaTotales = cuentasCustodiaTotales;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentasTitulo
	 */
	public List<CuentaTituloParaLicitarViewBPriv> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the cuentasTitulo to set
	 */
	public void setCuentasTitulo(List<CuentaTituloParaLicitarViewBPriv> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
}
