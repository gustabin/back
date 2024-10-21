package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFCuentaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFEntity;

public class ResumenTrimestralView {
	
	@JsonProperty("ListaRTF")
	private List<InfoRTFEntity> listaRTF;
	
	@JsonProperty("ListaCuentas")
	private List<InfoRTFCuentaEntity> listaCuentas;
	
	@JsonProperty("Ayuda")
	private String ayuda;
	
	@JsonProperty("MensajeSinResumen")
	private String mensajeSinResumen;
	
	@JsonProperty("UltPeriodo")
	private String ultPeriodo;
	
	@JsonProperty("UltAnio")
	private int ultAnio;

	public List<InfoRTFEntity> getListaRTF() {
		return listaRTF;
	}

	public void setListaRTF(List<InfoRTFEntity> listaRTF) {
		this.listaRTF = listaRTF;
	}

	public List<InfoRTFCuentaEntity> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<InfoRTFCuentaEntity> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public String getAyuda() {
		return ayuda;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public String getMensajeSinResumen() {
		return mensajeSinResumen;
	}

	public void setMensajeSinResumen(String mensajeSinResumen) {
		this.mensajeSinResumen = mensajeSinResumen;
	}

	public String getUltPeriodo() {
		return ultPeriodo;
	}

	public void setUltPeriodo(String ultPeriodo) {
		this.ultPeriodo = ultPeriodo;
	}

	public int getUltAnio() {
		return ultAnio;
	}

	public void setUltAnio(int ultAnio) {
		this.ultAnio = ultAnio;
	}
	
	


}
