/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CuentasPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaSuscripcionSaldoPDCResponse;

/**
 * The Class ConsultaSuscripcionSaldoPDCDTOMock.
 */
public class ConsultaSuscripcionSaldoPDCDTOMock {

    /**
	 * Instantiates a new consulta suscripcion saldo PDCDTO mock.
	 */
    private ConsultaSuscripcionSaldoPDCDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
	
    
    /**
	 * Armar consulta suscripcion saldo PDCDTO mock.
	 *
	 * @return the consulta suscripcion saldo PDCDTO
	 */
    public static ConsultaSuscripcionSaldoPDCDTO armarConsultaSuscripcionSaldoPDCDTOMock() {
    	
    	ConsultaSuscripcionSaldoPDCDTO respuesta = new ConsultaSuscripcionSaldoPDCDTO();
    	DatosConsultaSuscripcionSaldoPDCResponse datos = new DatosConsultaSuscripcionSaldoPDCResponse();
    	List<CuentasPDC> listaCuentas = new ArrayList<CuentasPDC>();
    	CuentasPDC cuentaPDC = new CuentasPDC();
    	cuentaPDC.setCuentaTitulos("13233938");
    	cuentaPDC.setMoneda("ARS");
    	cuentaPDC.setIndicadorPDC("NA");
    	listaCuentas.add(cuentaPDC);
    	datos.setListaCuentas(listaCuentas);
    	respuesta.setDatos(datos);
    	
    	return respuesta;
    	
    }
	
}
