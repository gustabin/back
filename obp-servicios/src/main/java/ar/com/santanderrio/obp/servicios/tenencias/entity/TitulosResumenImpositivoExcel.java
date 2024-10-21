package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tenencias.view.ResumenCuentaInversionesView;

public class TitulosResumenImpositivoExcel {
	
	private String nombre;
	
	private List<ResumenCuentaInversionesView> list;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ResumenCuentaInversionesView> getList() {
		return list;
	}

	public void setList(List<ResumenCuentaInversionesView> list) {
		this.list = list;
	}
	
}
