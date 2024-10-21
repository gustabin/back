package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.CarteraAConsultar;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRespuestaFiltroCartera;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ResultadoFiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.SucNroCuenta;

public class FiltroCarteraEntityMock {

	private FiltroCarteraEntityMock() {
		throw new IllegalAccessError("Clase para testing");
	}
	
	public static FiltroCarteraEntity armarFiltroCarteraEntity() {
		
		FiltroCarteraEntity filtroCarteraEntity = new FiltroCarteraEntity();
		DatosRespuestaFiltroCartera datosRespuestaFiltroCartera = new DatosRespuestaFiltroCartera();
		ResultadoFiltroCarteraEntity resultadoFiltroCarteraEntity = new ResultadoFiltroCarteraEntity();
		
		List<CarteraAConsultar> resultadoCarteraCliente = new ArrayList<CarteraAConsultar>();
		
		CarteraAConsultar carteraTOT = new CarteraAConsultar();
		carteraTOT.setCodigoCartera("TOT");
		carteraTOT.setDescripcionCartera("Cartera Total");
		carteraTOT.setPorDefecto("S");
		resultadoCarteraCliente.add(carteraTOT);
		
		CarteraAConsultar carteraCTA = new CarteraAConsultar();
		carteraCTA.setCodigoCartera("CTA");
		carteraCTA.setDescripcionCartera("Cuenta Titulo");
		carteraCTA.setPorDefecto("N");
		SucNroCuenta cuenta = new SucNroCuenta();
		cuenta.setNumeroCuenta("12702631");
		cuenta.setSucursal("0");
		carteraCTA.setCuenta(cuenta);
		resultadoCarteraCliente.add(carteraCTA);
		
		CarteraAConsultar carteraPF = new CarteraAConsultar();
		carteraPF.setCodigoCartera("PF");
		carteraPF.setDescripcionCartera("Plazo Fijo");
		carteraPF.setPorDefecto("N");
		resultadoCarteraCliente.add(carteraPF);	
		
		resultadoFiltroCarteraEntity.setResultadoCarteraCliente(resultadoCarteraCliente);
		resultadoFiltroCarteraEntity.setMultiseleccion("S");
		datosRespuestaFiltroCartera.setResultado(resultadoFiltroCarteraEntity);
		filtroCarteraEntity.setDatos(datosRespuestaFiltroCartera);
		
		return filtroCarteraEntity;
	}
	
	
}
