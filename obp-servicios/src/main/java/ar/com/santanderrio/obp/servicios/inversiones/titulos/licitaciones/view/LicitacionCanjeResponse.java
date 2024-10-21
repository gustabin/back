package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

@JsonSerialize(include = Inclusion.NON_NULL)
public class LicitacionCanjeResponse {

	List<LicitacionCanje> licitaciones = new ArrayList<LicitacionCanje>();

	private List<CuentasAdhesionDebitoView> cuentasDebitoPesos;

	private List<CuentasAdhesionDebitoView> cuentasDebitoDolares;

	private boolean condicionesGeneralesAceptadas = false;

	private List<CuentaCustodiaView> cuentasCustodia;

	private boolean comprobantesCnvDisponibles = false;

	private String tramoAyuda;

	private List<CuentaCustodiaView> cuentasCustodiaTotales;

	/**
	 * Cuenta operativa solo para banca privada
	 */
	CuentasAdhesionDebitoView cuentaOperativa;

	public List<LicitacionCanje> getLicitaciones() {
		return licitaciones;
	}

	public void setLicitaciones(List<LicitacionCanje> licitaciones) {
		this.licitaciones = licitaciones;
	}

	public List<CuentasAdhesionDebitoView> getCuentasDebitoPesos() {
		return cuentasDebitoPesos;
	}

	public void setCuentasDebitoPesos(List<CuentasAdhesionDebitoView> cuentasDebitoPesos) {
		this.cuentasDebitoPesos = cuentasDebitoPesos;
	}

	public List<CuentasAdhesionDebitoView> getCuentasDebitoDolares() {
		return cuentasDebitoDolares;
	}

	public void setCuentasDebitoDolares(List<CuentasAdhesionDebitoView> cuentasDebitoDolares) {
		this.cuentasDebitoDolares = cuentasDebitoDolares;
	}

	public boolean isCondicionesGeneralesAceptadas() {
		return condicionesGeneralesAceptadas;
	}

	public void setCondicionesGeneralesAceptadas(boolean condicionesGeneralesAceptadas) {
		this.condicionesGeneralesAceptadas = condicionesGeneralesAceptadas;
	}

	public List<CuentaCustodiaView> getCuentasCustodia() {
		return cuentasCustodia;
	}

	public void setCuentasCustodia(List<CuentaCustodiaView> cuentasCustodia) {
		this.cuentasCustodia = cuentasCustodia;
	}

	public boolean isComprobantesCnvDisponibles() {
		return comprobantesCnvDisponibles;
	}

	public void setComprobantesCnvDisponibles(boolean comprobantesCnvDisponibles) {
		this.comprobantesCnvDisponibles = comprobantesCnvDisponibles;
	}

	public CuentasAdhesionDebitoView getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(CuentasAdhesionDebitoView cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	public String getTramoAyuda() {
		return tramoAyuda;
	}

	public void setTramoAyuda(String tramoAyuda) {
		this.tramoAyuda = tramoAyuda;
	}

	public List<CuentaCustodiaView> getCuentasCustodiaTotales() {
		return cuentasCustodiaTotales;
	}

	public void setCuentasCustodiaTotales(List<CuentaCustodiaView> cuentasCustodiaTotales) {
		this.cuentasCustodiaTotales = cuentasCustodiaTotales;
	}
	
}
