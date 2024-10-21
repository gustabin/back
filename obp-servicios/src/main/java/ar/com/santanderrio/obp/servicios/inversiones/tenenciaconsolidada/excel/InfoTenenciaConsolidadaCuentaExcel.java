package ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoBPrivDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

public class InfoTenenciaConsolidadaCuentaExcel {

	private String numeroCuenta;
	
	private String intervinientes;
	
	private List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasPesos;
	
	private List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasDolares;
	
	private TotalesTenenciaConsolidadaExcel totales;

	public InfoTenenciaConsolidadaCuentaExcel() {
		super();
	}
	
	
	public InfoTenenciaConsolidadaCuentaExcel(TenenciaPorCuentaBPrivDTO tenenciasEnCuenta, Cliente cliente) {
		
		this.numeroCuenta = tenenciasEnCuenta.getNroCuentaFormateado();
		this.intervinientes = crearListaIntervinientes(tenenciasEnCuenta.getNroCuenta(), cliente);
		this.listaTenenciasPesos = armarListaDeProductosPorMoneda(tenenciasEnCuenta.getTenenciaPesos().getListaTenenciaProductos(), DivisaEnum.PESO.getSimbolo());
		this.listaTenenciasDolares = armarListaDeProductosPorMoneda(tenenciasEnCuenta.getTenenciaDolares().getListaTenenciaProductos(), DivisaEnum.DOLAR.getSimbolo());
		this.totales = new TotalesTenenciaConsolidadaExcel(tenenciasEnCuenta);
	
	}
	
	private List<InfoTenenciaConsolidadaPorMonedaExcel> armarListaDeProductosPorMoneda(List<TenenciaPorProductoBPrivDTO> listaProductos, String moneda) {
		
		List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasExcel = new ArrayList<InfoTenenciaConsolidadaPorMonedaExcel>();
		
		for (TenenciaPorProductoBPrivDTO producto : listaProductos) {
			if (correspondeMostrarProductoEnExcel(producto)) {
					listaTenenciasExcel.add(generarProducto(producto, moneda));
			}
		}
		
		return listaTenenciasExcel;
		
	}
	
	private InfoTenenciaConsolidadaPorMonedaExcel generarProducto(TenenciaPorProductoBPrivDTO producto, String moneda) {
		
		String guion = "-";
		
		InfoTenenciaConsolidadaPorMonedaExcel infoTenenciasMoneda = new InfoTenenciaConsolidadaPorMonedaExcel();
		if ("LIQ".equals(producto.getProducto()) || "CUS".equals(producto.getProducto())) {
			infoTenenciasMoneda.setProducto(setearTipoDeTenencia(producto.getProducto()));
			infoTenenciasMoneda.setTenenciaValuadaCosto(guion.equals(producto.getTenenciaValuadaCosto()) ? guion : moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getTenenciaValuadaCosto()));
			infoTenenciasMoneda.setTenenciaValuadaHoy(moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getTenenciaValuadaHoy()));
			infoTenenciasMoneda.setResultado(guion.equals(producto.getResultado()) ? guion : moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getResultado()));
		} else {
			infoTenenciasMoneda.setProducto(setearTipoDeTenencia(producto.getProducto()));
			infoTenenciasMoneda.setTenenciaValuadaCosto(guion.equals(producto.getTenenciaValuadaCosto()) ? moneda + guion : moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getTenenciaValuadaCosto()));
			infoTenenciasMoneda.setTenenciaValuadaHoy(moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getTenenciaValuadaHoy()));
			infoTenenciasMoneda.setResultado(guion.equals(producto.getResultado()) ? moneda + guion : moneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(producto.getResultado()));
		}
		
		return infoTenenciasMoneda;
	}

	private Boolean correspondeMostrarProductoEnExcel (TenenciaPorProductoBPrivDTO producto) {
		
		Boolean correspondeMostrarProducto = Boolean.FALSE;
		
		if (!("CUS".equals(producto.getProducto()) && "0.00".equals(producto.getResultado()) &&
				"0.00".equals(producto.getTenenciaValuadaCosto()) && "0.00".equals(producto.getTenenciaValuadaHoy()))) {
			correspondeMostrarProducto = Boolean.TRUE;
		}
		
		return correspondeMostrarProducto;
	}
	
	private String setearTipoDeTenencia (String codigo) {
		
		String tenencia;
		
		if ("PF".equals(codigo)) {
			tenencia = "Plazos Fijo";
		} else if ("FCI".equals(codigo)) {
			tenencia = "Fondos Comunes de Inversión";
		} else if ("TV".equals(codigo)) {
			tenencia = "Títulos Valores";
		} else if ("LIQ".equals(codigo)) {
			tenencia = "Liquidez";
		} else {
			tenencia = "Custodia Monetaria";
		}
		
		return tenencia;
	}
	
	
	private String crearListaIntervinientes (String numeroCuenta, Cliente cliente) {
		
		Cuenta cuenta = cliente.getCuentaOperativaSiContieneNumero(numeroCuenta.replaceAll("/",""));
		List<Interviniente> listaIntervinientes = cuenta.getIntervinientes();
				
		StringBuilder intervinientes = new StringBuilder();
		for (Interviniente interviniente : listaIntervinientes) {
			intervinientes.append(interviniente.getApellido() + ", " + interviniente.getNombre() + " / ");
		}
		
		return intervinientes.substring(0, intervinientes.length() - 2);
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

	public List<InfoTenenciaConsolidadaPorMonedaExcel> getListaTenenciasPesos() {
		return listaTenenciasPesos;
	}

	public void setListaTenenciasPesos(List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasPesos) {
		this.listaTenenciasPesos = listaTenenciasPesos;
	}

	public List<InfoTenenciaConsolidadaPorMonedaExcel> getListaTenenciasDolares() {
		return listaTenenciasDolares;
	}

	public void setListaTenenciasDolares(List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasDolares) {
		this.listaTenenciasDolares = listaTenenciasDolares;
	}

	public TotalesTenenciaConsolidadaExcel getTotales() {
		return totales;
	}

	public void setTotales(TotalesTenenciaConsolidadaExcel totales) {
		this.totales = totales;
	}
	
}
