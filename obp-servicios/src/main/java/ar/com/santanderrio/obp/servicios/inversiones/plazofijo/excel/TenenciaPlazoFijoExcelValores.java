package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.excel;

import org.joda.time.DateTime;

public class TenenciaPlazoFijoExcelValores implements Comparable<TenenciaPlazoFijoExcelValores> {

	
	private String tipo;
	
	private String fechaVencimiento;
	
	private String capitalInicial;
	
	private String tna;
	
	private String tenenciaValuadaHoy;
	
	private String resultado;
	
	private String prioridad;
	
	private Boolean tipoRepetido = Boolean.FALSE;	

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCapitalInicial() {
		return capitalInicial;
	}

	public void setCapitalInicial(String capitalInicial) {
		this.capitalInicial = capitalInicial;
	}

	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
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

	
	public Boolean getTipoRepetido() {
		return tipoRepetido;
	}

	public void setTipoRepetido(Boolean tipoRepetido) {
		this.tipoRepetido = tipoRepetido;
	}
		
	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	
	@Override
	public int compareTo(TenenciaPlazoFijoExcelValores o) {

		int resultado = 0;
		if (Integer.parseInt(prioridad) < Integer.parseInt(o.prioridad)) {
			resultado = -1;
		}
		
		if (Integer.parseInt(prioridad) > Integer.parseInt(o.prioridad)) {
			resultado = 1;
		}
		
		if (resultado != 0) {
			return resultado;
		}
			
		String anio1 = fechaVencimiento.substring(6, fechaVencimiento.length());
		String mes1 = fechaVencimiento.substring(3, 5);
		String dia1 = fechaVencimiento.substring(0, 2);
		
		DateTime date1 = new DateTime(anio1+"-"+mes1+"-"+dia1);
		
		String anio2 = o.fechaVencimiento.substring(6, o.fechaVencimiento.length());
		String mes2 = o.fechaVencimiento.substring(3, 5);
		String dia2 = o.fechaVencimiento.substring(0, 2);
		
		DateTime date2 = new DateTime(anio2+"-"+mes2+"-"+dia2);
		
		return date1.compareTo(date2);
									
	}
			
}
