package ar.com.santanderrio.obp.servicios.inversiones.titulos.excel;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosDTO;

public class InfoTenenciasExcel implements Comparable<InfoTenenciasExcel> {

	private String tipo;
	
	private String descripcion;
	
	private String cantidadValorNominal;
	
	private String precioMercado;
	
	private String tenenciaValuadaHoy;
	
	private String resultado;
	
	private int prioridad; 

	
	public InfoTenenciasExcel() {
		super();
	}
	
	public InfoTenenciasExcel(String moneda, TenenciaTitulosDTO tenenciaDTO, boolean esVacio) {
		
		if (esVacio) {
			this.descripcion = "";
			this.cantidadValorNominal = "";
			this.precioMercado = "";
			this.resultado = "";
			this.tenenciaValuadaHoy = "";
			this.tipo = "";
			this.prioridad = 0;
		} else {
			this.descripcion = tenenciaDTO.getDescripcion();
			this.cantidadValorNominal = formatearCantidadValorNominal(tenenciaDTO.getCantidadValorNominal());
			this.precioMercado = tenenciaDTO.getPrecioMercado() == null ? moneda + "-" : moneda + " " + formatearValorDecimales(tenenciaDTO.getPrecioMercado().toString());
			this.resultado = tenenciaDTO.getResultado() == null ? moneda + "-" : moneda + " " + formatearMonto(tenenciaDTO.getResultado().toString());
			this.tenenciaValuadaHoy = tenenciaDTO.getTenenciaValuada() == null ? moneda + "-" : moneda + " " + formatearMonto(tenenciaDTO.getTenenciaValuada().toString());
			this.tipo = tenenciaDTO.getTipo();
			this.prioridad = asignarPrioridad(tenenciaDTO.getTipo());
		}
		
	}
	
	private String formatearMonto (String montoSinFormato) {
		
		String montoFormateado = ISBANStringUtils.formatearConComaYDosDecimales(montoSinFormato);
		
		if (montoSinFormato.startsWith("-")) {
			return "-" + montoFormateado;
		}
		return montoFormateado;
		
	}
	
	private String formatearValorDecimales (String montoOriginal) {
		
		String montoFormateado;
		
		if (montoOriginal.contains(".")) {
			String[] numeroPartido = montoOriginal.split("\\.");
			String numeroFormateado = ISBANStringUtils.formatearConComaYVariosDecimales(montoOriginal, numeroPartido[1].length());
			montoFormateado = numeroFormateado;
		} else {
			String numeroFormateado = ISBANStringUtils.formatearConComaYVariosDecimales(montoOriginal, 0);
			montoFormateado = numeroFormateado.substring(0, numeroFormateado.length()-3);
		}
		
		if (montoOriginal.startsWith("0")) {
			montoFormateado = "0" + montoFormateado;
		}
		
		return montoFormateado;
	}
	
	private String formatearCantidadValorNominal(Double valor) {

		String valorFormateado = ISBANStringUtils.formatearSaldoPrecioReferencia(new BigDecimal(valor));
		String[] partesValor = valorFormateado.split("\\,");
		return partesValor[0];
	}
	
	private int asignarPrioridad (String tipo) {
		
		int prioridadTipo = 0;
		
		if ("Títulos Públicos".equals(tipo)) {
			prioridadTipo = 1;
		} else if ("Títulos Privados".equals(tipo)) {
			prioridadTipo = 2;
		} else if ("Acciones".equals(tipo)) {
			prioridadTipo = 3;
		} else {
			prioridadTipo = 4;
		}
		
		return prioridadTipo;
	}
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCantidadValorNominal() {
		return cantidadValorNominal;
	}

	public void setCantidadValorNominal(String cantidadValorNominal) {
		this.cantidadValorNominal = cantidadValorNominal;
	}

	public String getPrecioMercado() {
		return precioMercado;
	}

	public void setPrecioMercado(String precioMercado) {
		this.precioMercado = precioMercado;
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

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	@Override
	public int compareTo(InfoTenenciasExcel o) {
		
		if (prioridad < o.prioridad) {
			return -1;
		}
		
		if (prioridad > o.prioridad) {
			return 1;
		}

		return 0;
		
	}
			
}
