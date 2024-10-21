/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;

/**
 * The Class RespuestaConfiguracionOrdenVentaDTOMock.
 */
public class RespuestaConfiguracionOrdenVentaDTOMock {

    /**
	 * Instantiates a new respuesta configuracion orden venta DTO mock.
	 */
    private RespuestaConfiguracionOrdenVentaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
	
	/**
	 * Armar respuesta DTO.
	 *
	 * @return the respuesta
	 */
	public static Respuesta<ConfiguracionOrdenVentaDTO> armarRespuestaDTO() {
		
		Respuesta<ConfiguracionOrdenVentaDTO> respuesta = new Respuesta<ConfiguracionOrdenVentaDTO>();
		ConfiguracionOrdenVentaDTO configuracionOrdenVentaDTO = new ConfiguracionOrdenVentaDTO();
		
		PlazoAcreditacionOrdenVentaDTO plazo = new PlazoAcreditacionOrdenVentaDTO();
		plazo.setId(1);
		plazo.setNombre("Inmediato");
		plazo.setTenenciaNegociable(new BigDecimal("100000"));
		plazo.setPrecioReferencia(new BigDecimal("100.25"));
		plazo.setFechaReferencia("2018/03/20");
		plazo.setFechaDeLiquidacion("2018/03/20");
		plazo.setHoraReferencia("12:05:35");
		plazo.setNominalMinimoValue(new BigDecimal("10"));
		plazo.setNominalMaximoValue(new BigDecimal("500"));
		plazo.setNominalIncrementoValue(new BigDecimal("10"));
		plazo.setEsPesos(true);
		plazo.setPrecioMinimoValue(new BigDecimal("10"));
		plazo.setPrecioMaximoValue(new BigDecimal("100"));
		plazo.setPrecioIncrementoValue(new BigDecimal("10"));
		plazo.setRequierePrecioLimite(false);
			
//		PlazoAcreditacionOrdenVentaDTO plazo2 = new PlazoAcreditacionOrdenVentaDTO();
//		plazo2.setId(1);
//		plazo2.setNombre("24 hs");
//		plazo2.setTenenciaNegociable(new BigDecimal("100000"));
//		plazo2.setPrecioReferencia(new BigDecimal("100.25"));
//		plazo2.setFechaReferencia("2018/03/20");
//		plazo2.setFechaDeLiquidacion("2018/03/20");
//		plazo2.setHoraReferencia("12:05:35");
//		plazo2.setNominalMinimoValue(new BigDecimal("20"));
//		plazo2.setNominalMaximoValue(new BigDecimal("1500"));
//		plazo2.setNominalIncrementoValue(new BigDecimal("20"));
//		plazo2.setEsPesos(true);
//		plazo2.setPrecioMinimoValue(new BigDecimal("20"));
//		plazo2.setPrecioMaximoValue(new BigDecimal("200"));
//		plazo2.setPrecioIncrementoValue(new BigDecimal("20"));
//		plazo2.setRequierePrecioLimite(false);
		
		List<PlazoAcreditacionOrdenVentaDTO> plazos = new ArrayList<PlazoAcreditacionOrdenVentaDTO>();
		plazos.add(plazo);
//		plazos.add(plazo2);
		
		configuracionOrdenVentaDTO.setMoneda("Pesos");
		configuracionOrdenVentaDTO.setPlazos(plazos);
		
		configuracionOrdenVentaDTO.setMensajePrecioLimite("En el tramo competitivo el cliente indica el precio / tasa requerido para la emisión. En el tramo no competitivo, el cliente acepta el precio / tasa de corte que surge en la emisión como resultado de la licitación");
		configuracionOrdenVentaDTO.setTextoOrigenFondos("En función de lo dispuesto por la Resolución 30/2017 de la Unidad de Información Financiera declaro bajo juramento que el origen de los fondos con los que realizo la operación es lícito y provienen de la actividad declarada.");
		configuracionOrdenVentaDTO.setLeyendaLegal("Condiciones...");
		
		CondicionesGeneralesCuentasCustodiaOrdenVentaDTO condicionesDTO = new CondicionesGeneralesCuentasCustodiaOrdenVentaDTO();
		List<CuentaCustodiaView> cuentas = new ArrayList<CuentaCustodiaView>();
		CuentaCustodiaView cuenta = new CuentaCustodiaView();
		cuenta.setSucursal("190");
		cuenta.setNumeroCuenta("1234567");
		cuentas.add(cuenta);
		condicionesDTO.setCuentasFirmadasSinValidacion(cuentas);
		condicionesDTO.setCuentasSinFirmarSinValidacion(cuentas);
		condicionesDTO.setIntro("legal intro");
		condicionesDTO.setCondiciones("legal condiciones");
		condicionesDTO.setLegal("legal");
		condicionesDTO.setCondicionesAceptadas(true);
		condicionesDTO.setComprobantesDisponibles(true);
		configuracionOrdenVentaDTO.setCondicionesGenerales(condicionesDTO);
		
		respuesta.setRespuesta(configuracionOrdenVentaDTO);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		
		return respuesta;
	}
	
}