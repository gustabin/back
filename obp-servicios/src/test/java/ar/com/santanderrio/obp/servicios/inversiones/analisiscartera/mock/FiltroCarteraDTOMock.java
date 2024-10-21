package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.CuentaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResultadoCarteraCliente;

public class FiltroCarteraDTOMock {

	private FiltroCarteraDTOMock() {
		throw new IllegalAccessError("Clase para testing");
	}

	public static FiltroCarteraDTO armarFiltroCarteraDTOMock() {
		
		FiltroCarteraDTO filtroDTO = new FiltroCarteraDTO();
    	
    	List<ResultadoCarteraCliente> listaCarteraCliente = new ArrayList<ResultadoCarteraCliente>();
    	ResultadoCarteraCliente resultadoCarteraTOT = new ResultadoCarteraCliente();
    	resultadoCarteraTOT.setCodigoCartera("TOT");
    	resultadoCarteraTOT.setDescripcionCartera("Cartera Total");
    	resultadoCarteraTOT.setPorDefecto(Boolean.TRUE);
    	listaCarteraCliente.add(resultadoCarteraTOT);
    	
    	ResultadoCarteraCliente resultadoCarteraCTA = new ResultadoCarteraCliente();
    	resultadoCarteraCTA.setCodigoCartera("CTA");
    	resultadoCarteraCTA.setDescripcionCartera("Cuenta Título");
    	CuentaACView cuentaView = new CuentaACView();
    	cuentaView.setNumeroCuenta("12702631");
    	cuentaView.setSucursal("0");
    	resultadoCarteraCTA.setCuenta(cuentaView);
    	listaCarteraCliente.add(resultadoCarteraCTA);
    	
    	ResultadoCarteraCliente resultadoCarteraPF = new ResultadoCarteraCliente();
    	resultadoCarteraPF.setCodigoCartera("PF");
    	resultadoCarteraPF.setDescripcionCartera("Plazo Fijo");
    	listaCarteraCliente.add(resultadoCarteraPF);
    	
    	filtroDTO.setResultadoCarteraCliente(listaCarteraCliente);
   	
    	return filtroDTO; 
    	
	}
	
	
}
