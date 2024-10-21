package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRespuestaFiltroPorFecha;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ResultadoFiltroPorFechaEntity;

public class FiltroFechaEntityMock {

	private FiltroFechaEntityMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	public static FiltroPorFechaEntity armarFiltroFechaEntity() {
			
		FiltroPorFechaEntity filtroPorFechaEntity = new FiltroPorFechaEntity();
		DatosRespuestaFiltroPorFecha datosRespuestaFiltroPorFecha = new DatosRespuestaFiltroPorFecha(); 
		List<ResultadoFiltroPorFechaEntity> resultado = new ArrayList<ResultadoFiltroPorFechaEntity>();

		List<String> monedas = new ArrayList<String>();
		monedas.add("ARS");
		
		ResultadoFiltroPorFechaEntity resultado30D = new ResultadoFiltroPorFechaEntity();
		resultado30D.setCodigoPeriodo("30D");
		resultado30D.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		resultado30D.setDisponibilidadInformacion("S");
		resultado30D.setDisponibilidadPeriodo(30);
		resultado30D.setFechaInicio("2018-07-25T00:00:00");
		resultado30D.setFechaFin("2018-08-24T00:00:00");
		resultado30D.setFechaInfoDisponible("2018-08-24T00:00:00");
		resultado30D.setListaMonedas(monedas);
		resultado30D.setMonedaDefecto("ARS");
		resultado30D.setPorDefecto("S");
		resultado.add(resultado30D);
		
		ResultadoFiltroPorFechaEntity resultadoANIO = new ResultadoFiltroPorFechaEntity();
		resultadoANIO.setCodigoPeriodo("ANIO");
		resultadoANIO.setDescripcionPeriodo("Año en curso");
		resultadoANIO.setDisponibilidadInformacion("N");
		resultadoANIO.setDisponibilidadPeriodo(186);
		resultadoANIO.setFechaInicio("2018-01-01T00:00:00");
		resultadoANIO.setFechaFin("2018-08-24T00:00:00");
		resultadoANIO.setFechaInfoDisponible("2018-08-24T00:00:00");
		List<String> monedas1 = new ArrayList<String>();
		monedas1.add("USD");
		resultadoANIO.setListaMonedas(monedas1);
		resultadoANIO.setMonedaDefecto("ARS");
		resultadoANIO.setPorDefecto("N");
		resultado.add(resultadoANIO);
		
		ResultadoFiltroPorFechaEntity resultado60D = new ResultadoFiltroPorFechaEntity();
		resultado60D.setCodigoPeriodo("60D");
		resultado60D.setDescripcionPeriodo("Ultimos 60 dias");
		resultado60D.setDisponibilidadInformacion("N");
		resultado60D.setDisponibilidadPeriodo(60);
		resultado60D.setFechaInicio("2018-06-24T00:00:00");
		resultado60D.setFechaFin("2018-08-24T00:00:00");
		resultado60D.setFechaInfoDisponible("2018-08-24T00:00:00");
		List<String> monedas2 = new ArrayList<String>();
		monedas2.add("RARS");
		resultado60D.setListaMonedas(monedas2);
		resultado60D.setMonedaDefecto("ARS");
		resultado60D.setPorDefecto("N");
		resultado.add(resultado60D);
		
		ResultadoFiltroPorFechaEntity resultado90D = new ResultadoFiltroPorFechaEntity();
		resultado90D.setCodigoPeriodo("90D");
		resultado90D.setDescripcionPeriodo("Ultimos 90 dias");
		resultado90D.setDisponibilidadInformacion("N");
		resultado90D.setDisponibilidadPeriodo(90);
		resultado90D.setFechaInicio("2018-05-24T00:00:00");
		resultado90D.setFechaFin("2018-08-24T00:00:00");
		resultado90D.setFechaInfoDisponible("2018-08-24T00:00:00");
		List<String> monedas3 = new ArrayList<String>();
		monedas3.add("RUSD");
		resultado90D.setListaMonedas(monedas3);
		resultado90D.setMonedaDefecto("ARS");
		resultado90D.setPorDefecto("N");
		resultado.add(resultado90D);
		
		ResultadoFiltroPorFechaEntity resultado365D = new ResultadoFiltroPorFechaEntity();
		resultado365D.setCodigoPeriodo("365D");
		resultado365D.setDescripcionPeriodo("Ultimos 365 dias");
		resultado365D.setDisponibilidadInformacion("N");
		resultado365D.setDisponibilidadPeriodo(365);
		resultado365D.setFechaInicio("2017-08-24T00:00:00");
		resultado365D.setFechaFin("2018-08-24T00:00:00");
		resultado365D.setFechaInfoDisponible("2018-08-24T00:00:00");
		resultado365D.setListaMonedas(monedas);
		resultado365D.setMonedaDefecto("ARS");
		resultado365D.setPorDefecto("N");
		resultado.add(resultado365D);
		
		ResultadoFiltroPorFechaEntity resultadoOTRO = new ResultadoFiltroPorFechaEntity();
		resultadoOTRO.setCodigoPeriodo("OTRO");
		resultadoOTRO.setDescripcionPeriodo("Otro intervalo");
		resultadoOTRO.setDisponibilidadInformacion("N");
		resultadoOTRO.setDisponibilidadPeriodo(186);
		resultadoOTRO.setFechaInicio("2018-01-01T00:00:00");
		resultadoOTRO.setFechaFin("2018-08-24T00:00:00");
		resultadoOTRO.setFechaInfoDisponible("2018-08-24T00:00:00");
		resultadoOTRO.setListaMonedas(monedas);
		resultadoOTRO.setMonedaDefecto("ARS");
		resultadoOTRO.setPorDefecto("N");
		resultado.add(resultadoOTRO);
		
		datosRespuestaFiltroPorFecha.setResultado(resultado);
		filtroPorFechaEntity.setDatos(datosRespuestaFiltroPorFecha);
		return filtroPorFechaEntity;
	}
		
}
