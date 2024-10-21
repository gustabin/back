package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;

public class RecurrenciasView {

	private List<RecurrenciaItemView> listaDebin = new ArrayList<RecurrenciaItemView>();

	private boolean hayMasRegistros;
	
	private List<CuentaView> cuentasSelector;
	
	public List<RecurrenciaItemView> getListaDebin() {
		return listaDebin;
	}

	public void setListaDebin(List<RecurrenciaItemView> listaDebin) {
		this.listaDebin = listaDebin;
	}

	public boolean getHayMasRegistros() {
		return hayMasRegistros;
	}

	public void setHayMasRegistros(boolean hayMasRegistros) {
		this.hayMasRegistros = hayMasRegistros;
	}

	public List<CuentaView> getCuentasSelector() {
		return cuentasSelector;
	}

	public void setCuentasSelector(List<CuentaView> cuentasSelector) {
		this.cuentasSelector = cuentasSelector;
	}
	
}
