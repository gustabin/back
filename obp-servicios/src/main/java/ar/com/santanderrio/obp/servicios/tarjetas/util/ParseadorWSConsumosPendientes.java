/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSConsumosPendientes.
 *
 * @author florencia.n.martinez
 */
public interface ParseadorWSConsumosPendientes {

	/**
	 * Tiene error de credenciales.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneErrorDeCredenciales(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Tiene XML todas tarjetas con codigos de error.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneXMLTodasTarjetasConCodigosDeError(List<TarjetaEntity> tarjetas) throws ParseadorVisaException;

	/**
	 * Obtener tarjetas.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaEntity> obtenerTarjetas(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener numero tarjeta activa.
	 *
	 * @param datos
	 *            the datos
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNumeroTarjetaActiva(DatosEntity datos) throws ParseadorVisaException;

	/**
	 * Obtener datos.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the datos
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	DatosEntity obtenerDatos(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener nombre habiente.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNombreHabiente(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener por numero de tarjeta activa.
	 *
	 * @param retorno
	 *            the retorno
	 * @param nroTarjetaCreditoCortado
	 *            the nro tarjeta credito cortado
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerPorNumeroDeTarjetaActiva(RetornoTarjetasEntity retorno, String nroTarjetaCreditoCortado)
			throws ParseadorVisaException;

	/**
	 * Obtener autorizaciones de tarjeta entity.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<ConsumoPendienteConfirmacionEntity> obtenerAutorizacionesDeTarjetaEntity(TarjetaEntity tarjetaEntity)
			throws ParseadorVisaException;

	/**
	 * Obtener consumo total en pesos.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerConsumoTotalEnPesos(ConsumoPendienteConfirmacionEntity consumoPendiente)
			throws ParseadorVisaException;

	/**
	 * Obtener consumo total en dolares.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerConsumoTotalEnDolares(ConsumoPendienteConfirmacionEntity consumoPendiente)
			throws ParseadorVisaException;

	/**
	 * Es consumo total en pesos cero.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @return the Boolean
	 */
	Boolean esConsumoTotalEnPesosCero(ConsumoPendienteConfirmacionEntity consumoPendiente);

	/**
	 * Es consumo total en dolares cero.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @return the Boolean
	 */
	Boolean esConsumoTotalEnDolaresCero(ConsumoPendienteConfirmacionEntity consumoPendiente);

	/**
	 * Obtener autorizaciones de consumo pendiente confirmacion.
	 *
	 * @param consumoPendiente
	 *            the consumo pendiente
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<AutorizacionEntity> obtenerAutorizacionesDeConsumoPendienteConfirmacion(
			ConsumoPendienteConfirmacionEntity consumoPendiente) throws ParseadorVisaException;

	/**
	 * Obtener descripcion.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerDescripcion(AutorizacionEntity autorizacion) throws ParseadorVisaException;

	/**
	 * Obtener importe.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerImporte(AutorizacionEntity autorizacion) throws ParseadorVisaException;

	/**
	 * Obtener fecha.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFecha(AutorizacionEntity autorizacion) throws ParseadorVisaException;

	/**
	 * No tiene consumos pendientes.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the Boolean
	 */
	Boolean noTieneConsumosPendientes(RetornoTarjetasEntity respuesta);

	/**
	 * Tiene todas tarjetas con codigo error consumos.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneTodasTarjetasConCodigoErrorConsumos(List<TarjetaEntity> tarjetas) throws ParseadorVisaException;

	/**
	 * Tienen todas tarjetas error consumos.
	 *
	 * @param entity
	 *            the entity
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tienenTodasTarjetasErrorConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Tienen todas tarjetas error sin consumos.
	 *
	 * @param entity
	 *            the entity
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tienenTodasTarjetasErrorSinConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Tienen error consumos.
	 *
	 * @param entity
	 *            the entity
	 * @return the Boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tienenErrorConsumos(RetornoTarjetasEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener codigo establecimiento.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCodigoEstablecimiento(AutorizacionEntity autorizacion) throws ParseadorVisaException;

	/**
	 * Recuperar numero tarjeta desde codigo tarjeta.
	 *
	 * @param codigoTarjeta
	 *            the codigo tarjeta
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String recuperarNumeroTarjetaDesdeCodigoTarjeta(String codigoTarjeta, RetornoTarjetasEntity entity)
			throws ParseadorVisaException;

	/**
	 * Obtener nro comprobante.
	 *
	 * @param autorizacion
	 *            the autorizacion
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerNroComprobante(AutorizacionEntity autorizacion) throws ParseadorVisaException;
}