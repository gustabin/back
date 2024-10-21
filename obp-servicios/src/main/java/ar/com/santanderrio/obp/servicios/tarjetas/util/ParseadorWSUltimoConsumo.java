/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.MovimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaUltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSUltimoConsumo.
 *
 * @author sabrina.cis
 */
public interface ParseadorWSUltimoConsumo {

	/**
	 * Es tarjeta sin consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esTarjetaSinConsumos(RetornoTarjetasEntity retorno) throws ParseadorVisaException;

	/**
	 * Obtiene los ultimos consumos de la tarjeta ingresada por parametro. Tag
	 * /tarjetas/tarjeta/document/movimientos/.
	 *
	 * @param entity
	 *            the entity
	 * @return the ultimos consumos
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	UltimosConsumosEntity obtenerMovimientos(TarjetaEntity entity) throws ParseadorVisaException;

	/**
	 * Obtiene la lista de tarjetas del ultimo consumo de la tarjeta ingresada
	 * por parametro. Tag /tarjetas/tarjeta/document/movimientos/tarjeta/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<TarjetaUltimosConsumosEntity> obtenerMovimientosDeUnaTarjeta(TarjetaEntity entity)
			throws ParseadorVisaException;

	/**
	 * Obtiene el codigo de tarjeta. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta codigoTarjeta/.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerCodigoTarjeta(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener tarjeta ultimos consumos entity.
	 *
	 * @param entity
	 *            the entity
	 * @return the tarjeta ultimos consumos entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaUltimosConsumosEntity obtenerTarjetaUltimosConsumosEntity(TarjetaEntity entity)
			throws ParseadorVisaException;

	/**
	 * Obtener consumo dolares.
	 *
	 * @param entity
	 *            the entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumoDolares(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener consumo pesos.
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerConsumoPesos(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener lista movimientos.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<MovimientoEntity> obtenerListaMovimientos(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener descripcion movimiento.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerDescripcion(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener fecha.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFecha(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener establecimiento codigo.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerEstablecimientoCodigo(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener ticket.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTicket(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener importe valor.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerImporteValor(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener tipo moneda.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerTipoMoneda(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Es importe pesos.
	 *
	 * @param entity
	 *            the entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esImportePesos(MovimientoEntity entity) throws ParseadorVisaException;

	/**
	 * Es credito.
	 *
	 * @param entity
	 *            the entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean esCredito(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException;

	/**
	 * Obtener tarjeta ultimos consumos entity.
	 *
	 * @param entity
	 *            the entity
	 * @param numeroTarjetaActiva
	 *            the numero tarjeta activa
	 * @return the tarjeta ultimos consumos entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaUltimosConsumosEntity obtenerTarjetaUltimosConsumosEntity(TarjetaEntity entity, String numeroTarjetaActiva)
			throws ParseadorVisaException;

	/**
	 * Obtener tarjeta antigua.
	 *
	 * @param retorno
	 *            the retorno
	 * @param numeroTarjetaActivaPP
	 *            the numero tarjeta activa PP
	 * @return the tarjeta entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	TarjetaEntity obtenerTarjetaAntigua(RetornoTarjetasEntity retorno, String numeroTarjetaActivaPP)
			throws ParseadorVisaException;

}
