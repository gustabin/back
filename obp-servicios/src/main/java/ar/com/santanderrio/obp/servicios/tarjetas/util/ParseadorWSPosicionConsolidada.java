/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaPosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSPosicionConsolidada.
 *
 * @author sabrina.cis
 */
public interface ParseadorWSPosicionConsolidada {

	/**
	 * Obtener consumos en dolares para titular.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosDolares(PosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener consumos en pesos para titular.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosPesos(PosicionConsolidadaEntity tarjeta)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener tarjeta posicion consolidada.
	 *
	 * @param respuestaWSVisa
	 *            the respuesta WS visa
	 * @return the posicion consolidada entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	PosicionConsolidadaEntity obtenerTarjetaPosicionConsolidada(RetornoTarjetasEntity respuestaWSVisa)
			throws ParseadorVisaException;

	/**
	 * Obtener lista tarjeta posicion consolidada.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaPosicionConsolidadaEntity> obtenerListaTarjetaPosicionConsolidada(PosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener posicion consolidada para adicional.
	 *
	 * @param numeroTarjeta
	 *            the numero tarjeta
	 * @param entity
	 *            the entity
	 * @return the tarjeta posicion consolidada
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaPosicionConsolidadaEntity obtenerTarjetaPosicionConsolidada(String numeroTarjeta,
			PosicionConsolidadaEntity entity) throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener consumos pesos adicional.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosPesosAdicional(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener consumos dolares adicional.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosDolaresAdicional(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener consumos pesos.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosPesos(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Obtener consumos dolares.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumosDolares(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException;

}
