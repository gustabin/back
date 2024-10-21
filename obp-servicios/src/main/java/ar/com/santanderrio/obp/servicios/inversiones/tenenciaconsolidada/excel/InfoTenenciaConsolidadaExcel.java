package ar.com.santanderrio.obp.servicios.inversiones.tenenciaconsolidada.excel;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;

public class InfoTenenciaConsolidadaExcel {

	private List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasPesos;
	
	private List<InfoTenenciaConsolidadaPorMonedaExcel> listaTenenciasDolares;
	
	private TotalesTenenciaConsolidadaExcel totales;
	
	public InfoTenenciaConsolidadaExcel() {
		super();
	}
	
	public InfoTenenciaConsolidadaExcel(List<TenenciaProductosPorMonedaView> listaTenencias) {
		
		TotalesTenenciaConsolidadaExcel totalesTenencia = new TotalesTenenciaConsolidadaExcel();
		
		for (TenenciaProductosPorMonedaView tenenciaMoneda : listaTenencias) {
			if (MonedaEspecieEnum.ARS.getCodigo().equals(tenenciaMoneda.getMoneda())) {
				List<InfoTenenciaConsolidadaPorMonedaExcel> listaPesos = new ArrayList<InfoTenenciaConsolidadaPorMonedaExcel>();
				for (TenenciaPorProductoView producto : tenenciaMoneda.getListaTenenciaProductos()) {
					listaPesos.add(crearProductoExcel(producto, tenenciaMoneda.getMoneda()));
				}
				this.listaTenenciasPesos = listaPesos;
				totalesTenencia.setResultadoArs(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalResultado());
				totalesTenencia.setTenenciaValuadaCostoArs(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalTenenciaValuadaCosto());
				totalesTenencia.setTenenciaValuadaHoyArs(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalTenenciaValuadaHoy());
			} else {
				List<InfoTenenciaConsolidadaPorMonedaExcel> listaDolares = new ArrayList<InfoTenenciaConsolidadaPorMonedaExcel>();
				for (TenenciaPorProductoView producto : tenenciaMoneda.getListaTenenciaProductos()) {
					listaDolares.add(crearProductoExcel(producto, tenenciaMoneda.getMoneda()));
				}
				this.listaTenenciasDolares = listaDolares;
				totalesTenencia.setResultadoUsd(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalResultado());
				totalesTenencia.setTenenciaValuadaCostoUsd(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalTenenciaValuadaCosto());
				totalesTenencia.setTenenciaValuadaHoyUsd(DivisaEnum.fromCodigoString(tenenciaMoneda.getMoneda()).getSimbolo() + " " + tenenciaMoneda.getTotalTenenciaValuadaHoy());
			}
		}
		
		this.totales = totalesTenencia;
		
	}
	
	private InfoTenenciaConsolidadaPorMonedaExcel crearProductoExcel(TenenciaPorProductoView producto, String moneda) {
		
		String guion = "-";
		
		InfoTenenciaConsolidadaPorMonedaExcel productoExcel = new InfoTenenciaConsolidadaPorMonedaExcel();
		productoExcel.setProducto(setearTipoDeTenencia(producto.getProducto()));
		
		productoExcel.setTenenciaValuadaCosto(guion.equals(producto.getTenenciaValuadaCosto()) ? DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " +  guion : DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " + producto.getTenenciaValuadaCosto());
		productoExcel.setTenenciaValuadaHoy(guion.equals(producto.getTenenciaValuadaHoy()) ? DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " +  guion : DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " + producto.getTenenciaValuadaHoy());
		productoExcel.setResultado(guion.equals(producto.getResultado()) ? DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " + guion : DivisaEnum.fromCodigoString(moneda).getSimbolo() + " " + producto.getResultado());
		
		return productoExcel;
		
	}

	private String setearTipoDeTenencia (String codigo) {
		
		String tenencia;
		
		if ("PF".equals(codigo)) {
			tenencia = "Plazos Fijo";
		} else if ("FCI".equals(codigo)) {
			tenencia = "Fondos Comunes de Inversión";
		} else if ("TV".equals(codigo)) {
			tenencia = "Títulos Valores";
		} else {
			tenencia = "Custodia Monetaria";
		}
		
		return tenencia;
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
