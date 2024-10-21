/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface ComprobantesSietePorVenticuatroBO.
 */
public interface ComprobantesSietePorVenticuatroBO {

	/**
	 * busca los comprobantes de rio en un periodo de tiempo pasado por
	 * parametro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesRio(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes rio asincronicamente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioAsync(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de otros bancos en un periodo de tiempo pasado por
	 * parametro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancos(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes otros bancos asincronicamente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosAsync(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de ACH en un periodo de tiempo pasado por
	 * parametro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesACH(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes ACH asincronicamente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesACHAsync(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de pago de haberes y honorarios en un periodo de
	 * tiempo pasado por parametro.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorarios(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes pago de haberes y honorarios
	 * asincronicamente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosAsync(Cliente cliente,
			TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de rio en un periodo de tiempo pasado por
	 * parametro(no efectuada).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesRioNoEfec(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes rio asincronicamente(no efectuadas).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesRioNoEfecAsync(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de otros bancos en un periodo de tiempo pasado por
	 * parametro(no efectuada).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesOtrosBancosNoEfec(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes otros bancos asincronicamente(no
	 * efectuadas).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesOtrosBancosNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de ACH en un periodo de tiempo pasado por
	 * parametro(no efectuada).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesACHNoEfec(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes ACH asincronicamente(no efectuadas).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the future
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesACHNoEfecAsync(Cliente cliente, TransaccionDTO transaccion);

	/**
	 * busca los comprobantes de pago de haberes y honorarios en un periodo de
	 * tiempo pasado por parametro(no efectuada).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorariosNoEfec(Cliente cliente,
			TransaccionDTO transaccion);

	/**
	 * lama a obtener comprobantes pago de haberes y honorarios
	 * asincronicamente(no efectuadas).
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosNoEfecAsync(Cliente cliente,
			TransaccionDTO transaccion);

	/**
	 * llama a obtener comprobantes pago de haberes y honorarios
	 * asincronicamente.
	 *
	 * @param cliente
	 *            the cliente
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(Cliente cliente,
			TransaccionDTO transaccion);

	Respuesta<ComprobantesDTO> obtenerComprobantesPagoHaberesYHonorariosUnificados(Cliente cliente,
			TransaccionDTO transaccion);

}
