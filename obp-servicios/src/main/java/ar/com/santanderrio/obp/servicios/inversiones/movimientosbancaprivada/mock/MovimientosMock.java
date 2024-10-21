/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.movimientosbancaprivada.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.RtaMovimientosCuentaBP;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;

/**
 * The Class MovimientosMock.
 */
public class MovimientosMock {

	/**
	 * Instantiates a new movimientos mock.
	 */
	private MovimientosMock() {
		throw new IllegalAccessError("Clase de mock");
	}
	
	/** The Constant FECHA. */
	private static final String FECHA = "2018-02-15 00:00:00.0";
	
	/** The Constant NUMERO_CUENTA. */
	private static final String NUMERO_CUENTA = "7003523508";
	
	/** The Constant NUMERO_MOVIMIENTO. */
	private static final String NUMERO_MOVIMIENTO = "001687652";
	
	/** The Constant TEXTO_DEL_APUNTE. */
	private static final String TEXTO_DEL_APUNTE = "MOVIMIENTO MANUAL";
	
	/**
	 * Enviar lista movimientos armada.
	 *
	 * @return the movimientos cuenta BP out entity
	 */
	public static MovimientosCuentaBPOutEntity enviarListaMovimientosArmada() {

		MovimientosCuentaBPOutEntity respuestaMovimientos = new MovimientosCuentaBPOutEntity();
		List<RtaMovimientosCuentaBP> listaMovimientos = new ArrayList<RtaMovimientosCuentaBP>();
		RtaMovimientosCuentaBP movimiento = new RtaMovimientosCuentaBP.RtaMovimientosCuentaBPBuilder().sucursal("0250")
				 .cuenta(NUMERO_CUENTA)
				 .divisa("ARS")
				 .numeroMov(NUMERO_MOVIMIENTO)
				 .fechaValor(FECHA)
				 .fechaOperacion(FECHA)
				 .concepto("MOVIMIENTO DE PRUEBA CAJA DE AHORRO PESOS")
				 .textoDelApunte(TEXTO_DEL_APUNTE)
				 .saldoInicio("0")
				 .ingresos("99998")
				 .egresos("0")
				 .saldoFinal("0")
				 .indMov("01")
				 .build();
		for (int i= 0; i < 12; i++) {
			listaMovimientos.add(movimiento);
		}
		
		RtaMovimientosCuentaBP movimientoCuentaCorriente = new RtaMovimientosCuentaBP.RtaMovimientosCuentaBPBuilder().sucursal("0250")
				 .cuenta(NUMERO_CUENTA)
				 .divisa("ARS")
				 .numeroMov(NUMERO_MOVIMIENTO)
				 .fechaValor(FECHA)
				 .fechaOperacion(FECHA)
				 .concepto("MOVIMIENTO DE PRUEBA CUENTA CORRIENTE PESOS")
				 .textoDelApunte(TEXTO_DEL_APUNTE)
				 .saldoInicio("0")
				 .ingresos("1000000")
				 .egresos("0")
				 .saldoFinal("0")
				 .indMov("00")
				 .build();
		
		for (int i= 0; i < 15; i++) {
			listaMovimientos.add(movimientoCuentaCorriente);
		}
		
		respuestaMovimientos.setRespuesta(listaMovimientos);
		return respuestaMovimientos;
		
	}
	
	/**
	 * Enviar lista movimientos armada dolares.
	 *
	 * @return the movimientos cuenta BP out entity
	 */
	public static MovimientosCuentaBPOutEntity enviarListaMovimientosArmadaDolares() {

		MovimientosCuentaBPOutEntity respuestaMovimientos = new MovimientosCuentaBPOutEntity();
		List<RtaMovimientosCuentaBP> listaMovimientos = new ArrayList<RtaMovimientosCuentaBP>();
		RtaMovimientosCuentaBP movimiento = new RtaMovimientosCuentaBP.RtaMovimientosCuentaBPBuilder().sucursal("0250")
				 .cuenta(NUMERO_CUENTA)
				 .divisa("USD")
				 .numeroMov(NUMERO_MOVIMIENTO)
				 .fechaValor(FECHA)
				 .fechaOperacion(FECHA)
				 .concepto("MOVIMIENTO PRUEBA DOLARES")
				 .textoDelApunte(TEXTO_DEL_APUNTE)
				 .saldoInicio("0")
				 .ingresos("500000")
				 .egresos("0")
				 .saldoFinal("0")
				 .indMov("01")
				 .build();
		
		for (int i= 0; i < 15; i++) {
			listaMovimientos.add(movimiento);
		}
		
		respuestaMovimientos.setRespuesta(listaMovimientos);
		return respuestaMovimientos;
	
	}
		
}