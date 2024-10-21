package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel;

import java.util.List;

public class TenenciaPlazoFijoExcel {

	private String numeroCuenta;
	
	private String intervinientes;
	
	private Boolean tienePlazosFijosPesos = Boolean.FALSE;
	
	private Boolean tienePlazosFijosDolares = Boolean.FALSE;
	
	private List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoPesos;

	private List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoDolares;
	
    private TotalesPlazoFijoExcel totalesGrilla;

    
	public List<TenenciaPlazoFijoExcelValores> getTenenciaPlazoFijoPesos() {
		return tenenciaPlazoFijoPesos;
	}

	public void setTenenciaPlazoFijoPesos(List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoPesos) {
		this.tenenciaPlazoFijoPesos = tenenciaPlazoFijoPesos;
	}

	public List<TenenciaPlazoFijoExcelValores> getTenenciaPlazoFijoDolares() {
		return tenenciaPlazoFijoDolares;
	}

	public void setTenenciaPlazoFijoDolares(List<TenenciaPlazoFijoExcelValores> tenenciaPlazoFijoDolares) {
		this.tenenciaPlazoFijoDolares = tenenciaPlazoFijoDolares;
	}

	public TotalesPlazoFijoExcel getTotalesGrilla() {
		return totalesGrilla;
	}

	public void setTotalesGrilla(TotalesPlazoFijoExcel totalesGrilla) {
		this.totalesGrilla = totalesGrilla;
	}

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

	public Boolean getTienePlazosFijosPesos() {
		return tienePlazosFijosPesos;
	}

	public void setTienePlazosFijosPesos(Boolean tienePlazosFijosPesos) {
		this.tienePlazosFijosPesos = tienePlazosFijosPesos;
	}

	public Boolean getTienePlazosFijosDolares() {
		return tienePlazosFijosDolares;
	}

	public void setTienePlazosFijosDolares(Boolean tienePlazosFijosDolares) {
		this.tienePlazosFijosDolares = tienePlazosFijosDolares;
	}
		
}
