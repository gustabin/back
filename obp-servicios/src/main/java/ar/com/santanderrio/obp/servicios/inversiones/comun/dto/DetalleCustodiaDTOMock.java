/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaCuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaTenenciaExperesada;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class DetalleCustodiaDTOMock.
 */
public class DetalleCustodiaDTOMock {

	/**
	 * Instantiates a new detalle custodia DTO mock.
	 */
	private DetalleCustodiaDTOMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	/**
	 * Armar mock detalle custodia DTO.
	 *
	 * @return the detalle custodia DTO
	 */
	public static DetalleCustodiaDTO armarMockDetalleCustodiaDTO() {
	
		DetalleCustodiaDTO detalleCustodiaDTO = new DetalleCustodiaDTO();
		
		List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView = new ArrayList<DetalleCustodiaCuentaView>();
		
		DetalleCustodiaCuentaView detalle1 = new DetalleCustodiaCuentaView();
		detalle1.setNumeroCuenta("140742");
		detalle1.setTotalPesos("200.00");
		detalle1.setTotalDolares("200.00");
		List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaPeso = new ArrayList<DetalleCustodiaCuentaMonedaView>();
		DetalleCustodiaCuentaMonedaView detallePeso1 = new DetalleCustodiaCuentaMonedaView();
		detallePeso1.setDescripcionCustodiaMonetaria("CUSTODIA EFECTIVO $");
		detallePeso1.setCantidadValorNominal("100.00");
		detallePeso1.setPrecioMercado("1.00");
		detallePeso1.setTenenciaValuadaHoy("100.00");
		detallePeso1.setEstado("NEGOCIABLE");
		detallePeso1.setTenenciaReexpresada("50.00");
		detalleCustodiaCuentaPeso.add(detallePeso1);
		
		DetalleCustodiaCuentaMonedaView detallePeso2 = new DetalleCustodiaCuentaMonedaView();
		detallePeso2.setDescripcionCustodiaMonetaria("CUSTODIA EFECTIVO $ F. TORNQUIST");
		detallePeso2.setCantidadValorNominal("100.00");
		detallePeso2.setPrecioMercado("1.00");
		detallePeso2.setTenenciaValuadaHoy("100.00");
		detallePeso2.setEstado("NEGOCIABLE");
		detallePeso2.setTenenciaReexpresada("50.00");
		detalleCustodiaCuentaPeso.add(detallePeso2);
	
		List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaDolar = new ArrayList<DetalleCustodiaCuentaMonedaView>();
		DetalleCustodiaCuentaMonedaView detalleDolar1 = new DetalleCustodiaCuentaMonedaView();
		detalleDolar1.setDescripcionCustodiaMonetaria("Cust. Monet. Fondos U$S");
		detalleDolar1.setCantidadValorNominal("100.00");
		detalleDolar1.setPrecioMercado("1.00");
		detalleDolar1.setTenenciaValuadaHoy("100.00");
		detalleDolar1.setEstado("NEGOCIABLE");
		detalleDolar1.setTenenciaReexpresada("200.00");
		detalleCustodiaCuentaDolar.add(detalleDolar1);
		
		DetalleCustodiaCuentaMonedaView detalleDolar2 = new DetalleCustodiaCuentaMonedaView();
		detalleDolar2.setDescripcionCustodiaMonetaria("DOLARES CANADIENSES");
		detalleDolar2.setCantidadValorNominal("100.00");
		detalleDolar2.setPrecioMercado("1.00");
		detalleDolar2.setTenenciaValuadaHoy("100.00");
		detalleDolar2.setEstado("NEGOCIABLE");
		detalleDolar2.setTenenciaReexpresada("200.00");
		detalleCustodiaCuentaDolar.add(detalleDolar2);
	
		DetalleCustodiaTenenciaExperesada tenenciaExpresadaPesos = new DetalleCustodiaTenenciaExperesada();
		tenenciaExpresadaPesos.setTenenciaMonedaPrincipal("200.00");
		tenenciaExpresadaPesos.setTenenciaPrincipalEnOtraMoneda("100.00");
		tenenciaExpresadaPesos.setTenenciaMonedaSecundaria("200.00");
		tenenciaExpresadaPesos.setTotalMonedaSecundaria("300.00");
		
		DetalleCustodiaTenenciaExperesada tenenciaExpresadaDolares = new DetalleCustodiaTenenciaExperesada();
		tenenciaExpresadaDolares.setTenenciaMonedaPrincipal("200.00");
		tenenciaExpresadaDolares.setTenenciaPrincipalEnOtraMoneda("400.00");
		tenenciaExpresadaDolares.setTenenciaMonedaSecundaria("200.00");
		tenenciaExpresadaDolares.setTotalMonedaSecundaria("600.00");
		
		detalle1.setDetalleCustodiaCuentaDolar(detalleCustodiaCuentaDolar);
		detalle1.setDetalleCustodiaCuentaPeso(detalleCustodiaCuentaPeso);
		detalle1.setTenenciaExpresadaDolares(tenenciaExpresadaDolares);
		detalle1.setTenenciaExpresadaPesos(tenenciaExpresadaPesos);
		
		List<Interviniente> listaIntervinientes1 = new ArrayList<Interviniente>();
		Interviniente interviniente1 = new Interviniente();
		interviniente1.setApellido("Byezkajlo");
		interviniente1.setNombre("Albino Celso");
		listaIntervinientes1.add(interviniente1);
		
		Interviniente interviniente2 = new Interviniente();
		interviniente2.setApellido("Calderon Noel");
		interviniente2.setNombre("Arancha Milena");
		listaIntervinientes1.add(interviniente2);
		
		detalle1.setIntervinientes(listaIntervinientes1);
		
		DetalleCustodiaCuentaView detalle2 = new DetalleCustodiaCuentaView();
		detalle2.setNumeroCuenta("90005958");
		detalle2.setTotalPesos("200.00");
		detalle2.setTotalDolares("200.00");
		List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaPeso2 = new ArrayList<DetalleCustodiaCuentaMonedaView>();
		DetalleCustodiaCuentaMonedaView detallePeso3 = new DetalleCustodiaCuentaMonedaView();
		detallePeso3.setDescripcionCustodiaMonetaria("CUST. EFECT. $ (BODEN 07 y CEDRO)");
		detallePeso3.setCantidadValorNominal("100.00");
		detallePeso3.setPrecioMercado("1.00");
		detallePeso3.setTenenciaValuadaHoy("100.00");
		detallePeso3.setEstado("NEGOCIABLE");
		detallePeso3.setTenenciaReexpresada("50.00");
		detalleCustodiaCuentaPeso2.add(detallePeso3);
		
		DetalleCustodiaCuentaMonedaView detallePeso4 = new DetalleCustodiaCuentaMonedaView();
		detallePeso4.setDescripcionCustodiaMonetaria("CUSTODIA EFECTIVO $");
		detallePeso4.setCantidadValorNominal("100.00");
		detallePeso4.setPrecioMercado("1.00");
		detallePeso4.setTenenciaValuadaHoy("100.00");
		detallePeso4.setEstado("NEGOCIABLE");
		detallePeso4.setTenenciaReexpresada("50.00");
		detalleCustodiaCuentaPeso2.add(detallePeso4);
	
		List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaDolar2 = new ArrayList<DetalleCustodiaCuentaMonedaView>();
		DetalleCustodiaCuentaMonedaView detalleDolar3 = new DetalleCustodiaCuentaMonedaView();
		detalleDolar3.setDescripcionCustodiaMonetaria("CUST. EFVO. U$S (NUEVAS ACREDITACIONES)");
		detalleDolar3.setCantidadValorNominal("100.00");
		detalleDolar3.setPrecioMercado("1.00");
		detalleDolar3.setTenenciaValuadaHoy("100.00");
		detalleDolar3.setEstado("NEGOCIABLE");
		detalleDolar3.setTenenciaReexpresada("200.00");
		detalleCustodiaCuentaDolar2.add(detalleDolar3);
		
		DetalleCustodiaCuentaMonedaView detalleDolar4 = new DetalleCustodiaCuentaMonedaView();
		detalleDolar4.setDescripcionCustodiaMonetaria("Bolivares Venezolanos");
		detalleDolar4.setCantidadValorNominal("100.00");
		detalleDolar4.setPrecioMercado("1.00");
		detalleDolar4.setTenenciaValuadaHoy("100.00");
		detalleDolar4.setEstado("NEGOCIABLE");
		detalleDolar4.setTenenciaReexpresada("200.00");
		detalleCustodiaCuentaDolar2.add(detalleDolar4);
	
		DetalleCustodiaTenenciaExperesada tenenciaExpresadaPesos2 = new DetalleCustodiaTenenciaExperesada();
		tenenciaExpresadaPesos2.setTenenciaMonedaPrincipal("200.00");
		tenenciaExpresadaPesos2.setTenenciaPrincipalEnOtraMoneda("100.00");
		tenenciaExpresadaPesos2.setTenenciaMonedaSecundaria("200.00");
		tenenciaExpresadaPesos2.setTotalMonedaSecundaria("300.00");
		
		DetalleCustodiaTenenciaExperesada tenenciaExpresadaDolares2 = new DetalleCustodiaTenenciaExperesada();
		tenenciaExpresadaDolares2.setTenenciaMonedaPrincipal("200.00");
		tenenciaExpresadaDolares2.setTenenciaPrincipalEnOtraMoneda("400.00");
		tenenciaExpresadaDolares2.setTenenciaMonedaSecundaria("200.00");
		tenenciaExpresadaDolares2.setTotalMonedaSecundaria("600.00");
		
		detalle2.setDetalleCustodiaCuentaDolar(detalleCustodiaCuentaDolar2);
		detalle2.setDetalleCustodiaCuentaPeso(detalleCustodiaCuentaPeso2);
		detalle2.setTenenciaExpresadaDolares(tenenciaExpresadaDolares2);
		detalle2.setTenenciaExpresadaPesos(tenenciaExpresadaPesos2);
		
		List<Interviniente> listaIntervinientes2 = new ArrayList<Interviniente>();
		Interviniente interviniente3 = new Interviniente();
		interviniente3.setApellido("Byezkajlo");
		interviniente3.setNombre("Albino Celso");
		listaIntervinientes2.add(interviniente3);
		
		Interviniente interviniente4 = new Interviniente();
		interviniente4.setApellido("Calderon Noel");
		interviniente4.setNombre("Arancha Milena");
		listaIntervinientes2.add(interviniente4);
		
		detalle2.setIntervinientes(listaIntervinientes2);
		
		listaDetalleCustodiaCuentaView.add(detalle1);
		listaDetalleCustodiaCuentaView.add(detalle2);
		detalleCustodiaDTO.setListaDetalleCustodiaCuentaView(listaDetalleCustodiaCuentaView);
		
		return detalleCustodiaDTO;
		
	}
	
	/**
	 * Armar mock detalle custodia DTO error.
	 *
	 * @return the detalle custodia DTO
	 */
	public static DetalleCustodiaDTO armarMockDetalleCustodiaDTOError() {
		
		DetalleCustodiaDTO detalleCustodiaDTO = new DetalleCustodiaDTO();
		
		List<DetalleCustodiaCuentaView> listaDetalleCustodiaCuentaView = new ArrayList<DetalleCustodiaCuentaView>();
		
		DetalleCustodiaCuentaView detalle1 = new DetalleCustodiaCuentaView();
		detalle1.setNumeroCuenta("140742");
		
		List<Interviniente> listaIntervinientes1 = new ArrayList<Interviniente>();
		Interviniente interviniente1 = new Interviniente();
		interviniente1.setApellido("Byezkajlo");
		interviniente1.setNombre("Albino Celso");
		listaIntervinientes1.add(interviniente1);
		
		Interviniente interviniente2 = new Interviniente();
		interviniente2.setApellido("Calderon Noel");
		interviniente2.setNombre("Arancha Milena");
		listaIntervinientes1.add(interviniente2);
		
		detalle1.setIntervinientes(listaIntervinientes1);
		
		DetalleCustodiaCuentaView detalle2 = new DetalleCustodiaCuentaView();
		detalle2.setNumeroCuenta("90005958");
		
		List<Interviniente> listaIntervinientes2 = new ArrayList<Interviniente>();
		Interviniente interviniente3 = new Interviniente();
		interviniente3.setApellido("Byezkajlo");
		interviniente3.setNombre("Albino Celso");
		listaIntervinientes2.add(interviniente3);
		
		Interviniente interviniente4 = new Interviniente();
		interviniente4.setApellido("Calderon Noel");
		interviniente4.setNombre("Arancha Milena");
		listaIntervinientes2.add(interviniente4);
		
		detalle2.setIntervinientes(listaIntervinientes2);
		
		listaDetalleCustodiaCuentaView.add(detalle1);
		listaDetalleCustodiaCuentaView.add(detalle2);
		detalleCustodiaDTO.setListaDetalleCustodiaCuentaView(listaDetalleCustodiaCuentaView);
		
		return detalleCustodiaDTO;
		
	}
	
}
