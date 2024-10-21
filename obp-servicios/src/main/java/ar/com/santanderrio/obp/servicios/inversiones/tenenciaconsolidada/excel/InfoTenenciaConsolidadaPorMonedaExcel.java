package ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel;

public class InfoTenenciaConsolidadaPorMonedaExcel {

	private String producto;
	
	private String tenenciaValuadaCosto;
	
	private String tenenciaValuadaHoy;
	
	private String resultado;

	
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTenenciaValuadaCosto() {
		return tenenciaValuadaCosto;
	}

	public void setTenenciaValuadaCosto(String tenenciaValuadaCosto) {
		this.tenenciaValuadaCosto = tenenciaValuadaCosto;
	}

	public String getTenenciaValuadaHoy() {
		return tenenciaValuadaHoy;
	}

	public void setTenenciaValuadaHoy(String tenenciaValuadaHoy) {
		this.tenenciaValuadaHoy = tenenciaValuadaHoy;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
