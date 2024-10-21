package ar.com.santanderrio.obp.servicios.inversiones.fondos.excel;

import java.util.List;

public class FciCuentaTitulosExcel {

	private String numeroCuenta;
	
	private String intervinientes;
	
	private List<FciExcel> fciPesos;
	
	private Boolean tieneFciPesos = Boolean.FALSE;
	
	private List<FciExcel> fciDolares;
	
	private Boolean tieneFciDolares = Boolean.FALSE;
	
	private FciTotalesCuenta totales;

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getIntervinientes() {
		return intervinientes;
	}

	public void setIntervinientes(String intervinientes) {
		this.intervinientes = intervinientes;
	}

	public List<FciExcel> getFciPesos() {
		return fciPesos;
	}

	public void setFciPesos(List<FciExcel> fciPesos) {
		this.fciPesos = fciPesos;
	}

	public Boolean getTieneFciPesos() {
		return tieneFciPesos;
	}

	public void setTieneFciPesos(Boolean tieneFciPesos) {
		this.tieneFciPesos = tieneFciPesos;
	}

	public List<FciExcel> getFciDolares() {
		return fciDolares;
	}

	public void setFciDolares(List<FciExcel> fciDolares) {
		this.fciDolares = fciDolares;
	}

	public Boolean getTieneFciDolares() {
		return tieneFciDolares;
	}

	public void setTieneFciDolares(Boolean tieneFciDolares) {
		this.tieneFciDolares = tieneFciDolares;
	}

	public FciTotalesCuenta getTotales() {
		return totales;
	}

	public void setTotales(FciTotalesCuenta totales) {
		this.totales = totales;
	}

	
	

}


