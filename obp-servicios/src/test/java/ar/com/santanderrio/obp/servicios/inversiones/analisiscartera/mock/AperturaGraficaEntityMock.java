package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaResultado;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosAperturaGraficaEntity;

public class AperturaGraficaEntityMock {

	private AperturaGraficaEntityMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	public static AperturaGraficaEntity armarAperturaGraficaEntityMock() {
		
		AperturaGraficaEntity aperturaGraficaEntity = new AperturaGraficaEntity();
		DatosAperturaGraficaEntity datos = new DatosAperturaGraficaEntity();
		List<AperturaGraficaResultado> listaResultado = new ArrayList<AperturaGraficaResultado>();
		
		AperturaGraficaResultado res1 = new AperturaGraficaResultado();
		res1.setCodigoPeriodo("30D");
		res1.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res1.setFechaInicio("2018-07-25T00:00:00");
		res1.setFechaFin("2018-08-24T00:00:00");
		res1.setMoneda("USD");
		res1.setCodigoClasificacion("PROD");
		res1.setDescripcionClasificacion("PRODUCTO");
		res1.setCodigoSubclasificacion("TV");
		res1.setDescripcionSubclasificacion("TITULOS VALORES");
		res1.setPorDefecto("N");
		res1.setRentabilidadDisponible("S");
		res1.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res2 = new AperturaGraficaResultado();
		res2.setCodigoPeriodo("30D");
		res2.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res2.setFechaInicio("2018-07-25T00:00:00");
		res2.setFechaFin("2018-08-24T00:00:00");
		res2.setMoneda("USD");
		res2.setCodigoClasificacion("PROD");
		res2.setDescripcionClasificacion("PRODUCTO");
		res2.setCodigoSubclasificacion("PF");
		res2.setDescripcionSubclasificacion("PLAZO FIJO");
		res2.setPorDefecto("N");
		res2.setRentabilidadDisponible("S");
		res2.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res3 = new AperturaGraficaResultado();
		res3.setCodigoPeriodo("30D");
		res3.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res3.setFechaInicio("2018-07-25T00:00:00");
		res3.setFechaFin("2018-08-24T00:00:00");
		res3.setMoneda("USD");
		res3.setCodigoClasificacion("PROD");
		res3.setDescripcionClasificacion("PRODUCTO");
		res3.setCodigoSubclasificacion("CUS");
		res3.setDescripcionSubclasificacion("CUSTODIA MONETARIA");
		res3.setPorDefecto("N");
		res3.setRentabilidadDisponible("S");
		res3.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res4 = new AperturaGraficaResultado();
		res4.setCodigoPeriodo("30D");
		res4.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res4.setFechaInicio("2018-07-25T00:00:00");
		res4.setFechaFin("2018-08-24T00:00:00");
		res4.setMoneda("USD");
		res4.setCodigoClasificacion("PROD");
		res4.setDescripcionClasificacion("PRODUCTO");
		res4.setCodigoSubclasificacion("FCI");
		res4.setDescripcionSubclasificacion("FONDOS COMUNES DE INVERSION");
		res4.setPorDefecto("N");
		res4.setRentabilidadDisponible("S");
		res4.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res5 = new AperturaGraficaResultado();
		res5.setCodigoPeriodo("30D");
		res5.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res5.setFechaInicio("2018-07-25T00:00:00");
		res5.setFechaFin("2018-08-24T00:00:00");
		res5.setMoneda("USD");
		res5.setCodigoClasificacion("PROD");
		res5.setDescripcionClasificacion("PRODUCTO");
		res5.setCodigoSubclasificacion("TOT");
		res5.setDescripcionSubclasificacion("TOTAL CARTERA");
		res5.setPorDefecto("S");
		res5.setRentabilidadDisponible("S");
		res5.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res6 = new AperturaGraficaResultado();
		res6.setCodigoPeriodo("30D");
		res6.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res6.setFechaInicio("2018-07-25T00:00:00");
		res6.setFechaFin("2018-08-24T00:00:00");
		res6.setMoneda("USD");
		res6.setCodigoClasificacion("ASCL");
		res6.setDescripcionClasificacion("ASSET CLASS");
		res6.setCodigoSubclasificacion("NUE");
		res6.setDescripcionSubclasificacion("NUEVA SUBCLASIFICACION PARA PROBAR");
		res6.setPorDefecto("N");
		res6.setRentabilidadDisponible("S");
		res6.setRendimientoDisponible("S");
		
		AperturaGraficaResultado res7 = new AperturaGraficaResultado();
		res7.setCodigoPeriodo("30D");
		res7.setDescripcionPeriodo("ULTIMOS 30 DIAS");
		res7.setFechaInicio("2018-07-25T00:00:00");
		res7.setFechaFin("2018-08-24T00:00:00");
		res7.setMoneda("USD");
		res7.setCodigoClasificacion("ASCL");
		res7.setDescripcionClasificacion("ASSET CLASS");
		res7.setCodigoSubclasificacion("LIQ");
		res7.setDescripcionSubclasificacion("LIQUIDEZ");
		res7.setPorDefecto("N");
		res7.setRentabilidadDisponible("S");
		res7.setRendimientoDisponible("S");
		
		
		listaResultado.add(res1);
		listaResultado.add(res2);
		listaResultado.add(res3);
		listaResultado.add(res6);
		listaResultado.add(res7);
		listaResultado.add(res5);
		
		datos.setResultado(listaResultado);
		aperturaGraficaEntity.setDatos(datos);
		
		return aperturaGraficaEntity;
	}
	
	
}
