package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPorClasificacion;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RespuestaRentabilidadPeriodoEntity;

public class RentabilidadPeriodoEntityMock {

	private RentabilidadPeriodoEntityMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	public static RespuestaRentabilidadPeriodoEntity armarRentabilidadPeriodoEntityMock() {
	
		RespuestaRentabilidadPeriodoEntity respuestaRentabilidadPeriodoEntity = new RespuestaRentabilidadPeriodoEntity();
		
		respuestaRentabilidadPeriodoEntity.setCodigoPeriodo("30D");
		respuestaRentabilidadPeriodoEntity.setDescripcionPeriodo("Ultimos 30 dias");
		respuestaRentabilidadPeriodoEntity.setFechaInicio("2018-07-25T00:00:00");
		respuestaRentabilidadPeriodoEntity.setFechaFin("2018-08-24T00:00:00");
		respuestaRentabilidadPeriodoEntity.setMoneda("ARS");
		respuestaRentabilidadPeriodoEntity.setRentabilidadNoRealizada(new BigDecimal("2104.0"));
		respuestaRentabilidadPeriodoEntity.setRentabilidadRealizada(new BigDecimal("1104.0"));
		respuestaRentabilidadPeriodoEntity.setRentabilidadTotal(new BigDecimal("3208.0"));
		respuestaRentabilidadPeriodoEntity.setTna(new BigDecimal("4.55"));
		
		List<RentabilidadPorClasificacion> resultadoPorClasificacion = new ArrayList<RentabilidadPorClasificacion>();
		RentabilidadPorClasificacion rentabilidad1 = new RentabilidadPorClasificacion();
		rentabilidad1.setCodigoSubclasificacion("TV");
		rentabilidad1.setDescripcionSubclasificacion("Titulos Valores");
		rentabilidad1.setDistribucion(new BigDecimal("100.0"));
		rentabilidad1.setRentabilidadNeta(new BigDecimal("-641.6"));
		resultadoPorClasificacion.add(rentabilidad1);
		
		RentabilidadPorClasificacion rentabilidad2 = new RentabilidadPorClasificacion();
		rentabilidad2.setCodigoSubclasificacion("TV");
		rentabilidad2.setDescripcionSubclasificacion("Titulos Valores");
		rentabilidad2.setDistribucion(new BigDecimal("100.0"));
		rentabilidad2.setRentabilidadNeta(new BigDecimal("641.6"));
		resultadoPorClasificacion.add(rentabilidad2);
		
		RentabilidadPorClasificacion rentabilidad3 = new RentabilidadPorClasificacion();
		rentabilidad3.setCodigoSubclasificacion("TV");
		rentabilidad3.setDescripcionSubclasificacion("Titulos Valores");
		rentabilidad3.setDistribucion(new BigDecimal("100.0"));
		rentabilidad3.setRentabilidadNeta(new BigDecimal("641.6"));
		resultadoPorClasificacion.add(rentabilidad3);
		
		RentabilidadPorClasificacion rentabilidad4 = new RentabilidadPorClasificacion();
		rentabilidad4.setCodigoSubclasificacion("TV");
		rentabilidad4.setDescripcionSubclasificacion("Titulos Valores");
		rentabilidad4.setDistribucion(new BigDecimal("100.0"));
		rentabilidad4.setRentabilidadNeta(new BigDecimal("641.6"));
		resultadoPorClasificacion.add(rentabilidad4);
		
		respuestaRentabilidadPeriodoEntity.setResultadoPorClasificacion(resultadoPorClasificacion);
		
		return respuestaRentabilidadPeriodoEntity;
	}
}
