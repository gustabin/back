/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;

/**
 * The Interface EstadisticaManager.
 *
 * @author Federico_Juliano
 */
public interface EstadisticaManager {

	/**
	 * Adds the. Solo usarlo para estadisticas con particularidades
	 *
	 * @param estadistica
	 *            the estadistica
	 * @return true, if successful
	 */
	boolean add(Estadistica estadistica);

	/**
	 * Guarda una estadistica con el codigo y el estado.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 * @return true, if successful
	 */
	boolean add(String codigoTransaccion, String resultado);

	/**
	 * Guarda una estadistica para login.
	 *
	 * @param estadistica
	 *            the estadistica
	 * @param registroSesion
	 *            the registro sesion
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	boolean add(Estadistica estadistica, RegistroSesion registroSesion, Cliente cliente);

	/**
	 * Guarda una estadistica con el código, el estado y la cuenta de origen.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 * @param nroCtaOrigen
	 *            the nro cta origen
	 * @return true, if successful
	 */
	boolean add(String codigoTransaccion, String resultado, String nroCtaOrigen);

	/**
	 * Adds the.
	 *
	 * @param estadistica
	 *            the estadistica
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 * @return true, if successful
	 */
	boolean add(Estadistica estadistica, String codigoTransaccion, String resultado);

	/**
	 * Adds the.
	 *
	 * @param codigoTransaccion
	 *            the codigo transaccion
	 * @param resultado
	 *            the resultado
	 * @param nroCtaOrigen
	 *            the nro cta origen
	 * @param importe
	 *            the importe
	 * @param moneda
	 *            the moneda
	 * @return true, if successful
	 */
	boolean add(String codigoTransaccion, String resultado, String nroCtaOrigen, String importe, String moneda);
	
	/**
	 * Agrega estadistica
	 * @param codigoTransaccion
	 * @param resultado
	 * @param nroComprobante
	 * @param importe
	 * @param moneda
	 * @return
	 */
	boolean add(String codigoTransaccion, String resultado, String nroComprobante, BigDecimal importe, String moneda);

}
