package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;

public class DatosConfigClienteDebinRecurrenteView {

	private List<CuentaView> listaCuentas;
	
	private String mensajeInformativo;
	
	private String terminosCondiciones;
	
	private List<ConceptoDebinRecurrente> listaConceptos;

	
	public List<CuentaView> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<CuentaView> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	public String getTerminosCondiciones() {
		return terminosCondiciones;
	}

	public void setTerminosCondiciones(String terminosCondiciones) {
		this.terminosCondiciones = terminosCondiciones;
	}

	public List<ConceptoDebinRecurrente> getListaConceptos() {
		return listaConceptos;
	}

	public void setListaConceptos(List<ConceptoDebinRecurrente> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}
	
}
