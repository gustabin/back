package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.ArrayList;
import java.util.List;

public class TenenciasTitulosExcel {

	private String cuenta;
	
	private List<TitulosResumenImpositivoExcel> titulos = new ArrayList<TitulosResumenImpositivoExcel>();

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public List<TitulosResumenImpositivoExcel> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TitulosResumenImpositivoExcel> titulos) {
		this.titulos = titulos;
	}
	
}
