/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosCompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaComisionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RespuestaSimulacionERI;

/**
 * The Class CompraVentaTitulosValoresDTOMock.
 */
public class CompraVentaTitulosValoresDTOMock {

	/**
	 * Instantiates a new compra venta titulos valores DTO mock.
	 */
	private CompraVentaTitulosValoresDTOMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	
	/**
	 * Armar mock compra venta titulos valores.
	 *
	 * @return the compra venta titulos valores DTO
	 */
	public static CompraVentaTitulosValoresDTO armarMockCompraVentaTitulosValores() {
		
		CompraVentaTitulosValoresDTO respuesta = new CompraVentaTitulosValoresDTO();
		DatosCompraVtaTitulosResponse datos = new DatosCompraVtaTitulosResponse();
		DatosConsultaComisionResponse datosComision = new DatosConsultaComisionResponse();
		datos.setFechaLiquidacion(null);
		datos.setFechaDebitoCuenta(null);
		datos.setFechaPrecioRef(null);
		datos.setImporteDebitoCredito(0.0);
		datos.setCantidad(150.0);
		datos.setCotizacion(17.0);
		datos.setCotizacionLimite(16.0);
		datos.setImportePoderCompra(100.0);
		datos.setImporteTeorico(2550.0);
		datos.setInteres(2515.17);
		datos.setDerechos(2.04);
		datos.setArancelPorcel(0.7);
		datos.setIva(3.75);
		datos.setComision(17.84);
		datos.setImpuestos(4.18);
		datos.setFechaOperacion(null);
		datos.setNumeroOrden("1234567");
		datos.setNumeroOperacion("987654321");
		datos.setCodigoRespERI(0);
		datos.setCapital("2525.00");
		datos.setInstrumento("Acciones");
		datos.setInstrumentoCodigo("ACC");
		datos.setHoraPrecioRef("10:55:00");
		datos.setLegales("Texto legal mockeado");
		datos.setCodigoErrorMiddleware(null);
		datosComision.setBonificacion("0.7");
		datosComision.setComision("0.3");
		datosComision.setComisionOriginal("1");
		datosComision.setInformacion("");
		datosComision.setTieneBonificacion(true);
		
		DatosConsultaComisionResponse datosConsultaComisionResponse = new DatosConsultaComisionResponse();
		datosConsultaComisionResponse.setBonificacion("BONIFICACION");
		
		RespuestaSimulacionERI respuestaERI = new RespuestaSimulacionERI();
		respuestaERI.setIdEvaluacion(20876);
		respuestaERI.setTipoDisclaimer(1);
		respuestaERI.setDisclaimer("<Mensaje><CantidadDeDisclaimers>1</CantidadDeDisclaimers><Disclaimers><Disclaimer><Titulo></Titulo><Text>Banco Santander informa que no se encuentra disponible el sistema de determinación de concentración máxima de productos para perfiles. Desea continuar?</Text></Disclaimer></Disclaimers></Mensaje>");
		respuestaERI.setDisclaimerCumplimiento(null);
		datos.setDatosConsultaComisionResponsee(datosConsultaComisionResponse);
		datos.setRespuestaSimulacionERI(respuestaERI);
		datos.setDatosConsultaComisionResponsee(datosComision);
		
		respuesta.setDatos(datos);
		return respuesta;
	}
	
}
