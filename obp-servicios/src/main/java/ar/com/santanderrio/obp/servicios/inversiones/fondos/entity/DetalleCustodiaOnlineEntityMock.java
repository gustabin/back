/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DetalleCustodiaOnlineEntityMock.
 */
public class DetalleCustodiaOnlineEntityMock {

	/**
	 * Instantiates a new detalle custodia online entity mock.
	 */
	private DetalleCustodiaOnlineEntityMock() {
		throw new IllegalAccessError("Clase para testing");
	}	
	
	/**
	 * Armar mock detalle custodia online entity.
	 *
	 * @return the detalle custodia online entity
	 */
	public static DetalleCustodiaOnlineEntity armarMockDetalleCustodiaOnlineEntity() {
	
		DetalleCustodiaOnlineEntity detalleCustodiaOnlineEntity = new DetalleCustodiaOnlineEntity();
		DatosRespuestaCustodia datos = new DatosRespuestaCustodia();
		
		List<TenenciaCuentaCustodiaMoneda> lista = new ArrayList<TenenciaCuentaCustodiaMoneda>();
   	 
   	 	TenenciaCuentaCustodiaMoneda tenencia = new TenenciaCuentaCustodiaMoneda();
   	 	tenencia.setNumeroCuenta("225711");
   	 	tenencia.setSucursalCuenta("0");
   	 	tenencia.setCodigoEspecie("70050");
   	 	tenencia.setDescripcionEspecie("CUSTODIA EFECTIVO $ F. TORNQUIST");
   	 	tenencia.setMoneda("ARS");
   	 	tenencia.setTenenciaNominal("1000");
   	 	tenencia.setCotizacion("1");
   	 	tenencia.setCodigoEstado("0");
   	 	tenencia.setDescripcionEstado("NEGOCIABLE");
   	 	tenencia.setTenenciaValuada("1000");
   	 	tenencia.setTenenciaValuadaReexp("500");
   	 	
   	 	TenenciaCuentaCustodiaMoneda tenencia2 = new TenenciaCuentaCustodiaMoneda();
   	 	tenencia2.setNumeroCuenta("90006722");
   	 	tenencia2.setSucursalCuenta("0");
   	 	tenencia2.setCodigoEspecie("70004");
   	 	tenencia2.setDescripcionEspecie("YENES");
   	 	tenencia2.setMoneda("USD");
   	 	tenencia2.setTenenciaNominal("2000");
   	 	tenencia2.setCotizacion("2.5");
   	 	tenencia2.setCodigoEstado("0");
   	 	tenencia2.setDescripcionEstado("NEGOCIABLE");
   	 	tenencia2.setTenenciaValuada("5000");
   	 	tenencia2.setTenenciaValuadaReexp("10000");
   	 	
   	 	TenenciaCuentaCustodiaMoneda tenencia3 = new TenenciaCuentaCustodiaMoneda();
   	 	tenencia3.setNumeroCuenta("90006722");
   	 	tenencia3.setSucursalCuenta("0");
   	 	tenencia3.setCodigoEspecie("70050");
   	 	tenencia3.setDescripcionEspecie("CUSTODIA EFECTIVO $ F. TORNQUIST");
   	 	tenencia3.setMoneda("ARS");
   	 	tenencia3.setTenenciaNominal("2000");
   	 	tenencia3.setCotizacion("1");
   	 	tenencia3.setCodigoEstado("0");
   	 	tenencia3.setDescripcionEstado("NEGOCIABLE");
   	 	tenencia3.setTenenciaValuada("2000");
   	 	tenencia3.setTenenciaValuadaReexp("1000");
   	 	
   	 	TenenciaCuentaCustodiaMoneda tenencia4 = new TenenciaCuentaCustodiaMoneda();
   	 	tenencia4.setNumeroCuenta("225711");
   	 	tenencia4.setSucursalCuenta("0");
   	 	tenencia4.setCodigoEspecie("70004");
   	 	tenencia4.setDescripcionEspecie("YENES");
   	 	tenencia4.setMoneda("USD");
   	 	tenencia4.setTenenciaNominal("1000");
   	 	tenencia4.setCotizacion("2.5");
   	 	tenencia4.setCodigoEstado("0");
   	 	tenencia4.setDescripcionEstado("NEGOCIABLE");
   	 	tenencia4.setTenenciaValuada("2500");
   	 	tenencia4.setTenenciaValuadaReexp("5000");
   	 	
   	 	TenenciaCuentaCustodiaMoneda tenencia5 = new TenenciaCuentaCustodiaMoneda();
   	 	tenencia5.setNumeroCuenta("225711");
   	 	tenencia5.setSucursalCuenta("0");
   	 	tenencia5.setCodigoEspecie("70006");
   	 	tenencia5.setDescripcionEspecie("FRANCOS SUIZOS");
   	 	tenencia5.setMoneda("USD");
   	 	tenencia5.setTenenciaNominal("1000");
   	 	tenencia5.setCotizacion("1.5");
   	 	tenencia5.setCodigoEstado("0");
   	 	tenencia5.setDescripcionEstado("NEGOCIABLE");
   	 	tenencia5.setTenenciaValuada("1500");
   	 	tenencia5.setTenenciaValuadaReexp("3000");
   	    	 	
   	 	lista.add(tenencia);
   	 	lista.add(tenencia2);
   	 	lista.add(tenencia3);
   	 	lista.add(tenencia4);
   	 	lista.add(tenencia5);
		
   	 	datos.setResultado(lista);
   	 	detalleCustodiaOnlineEntity.setDatos(datos);
		
		return detalleCustodiaOnlineEntity;
	}
	
	
	
}
