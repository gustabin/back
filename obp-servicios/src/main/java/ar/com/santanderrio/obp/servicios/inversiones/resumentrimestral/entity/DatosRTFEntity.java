package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosRTFEntity {
	
	@JsonProperty("ListaRTF")
	private List<InfoRTFEntity> listaRTF;
	
	@JsonProperty("ListaCuentas")
	private List<InfoRTFCuentaEntity> listaCuentas;
	
	@JsonProperty("UltimoPeriodoGenerado")
	private String ultPeriodo;
	
	@JsonProperty("UltimoAnioGenerado")
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
