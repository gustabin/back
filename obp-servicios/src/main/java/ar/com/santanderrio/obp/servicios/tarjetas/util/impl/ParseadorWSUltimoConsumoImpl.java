/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.MovimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaUltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.UltimosConsumosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoConsumo;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * Parseador de WS Visa para la tarea unificada UltimoConsumo.
 *
 * @author sabrina.cis
 */
@Component
public class ParseadorWSUltimoConsumoImpl extends ParseadorWSVisaImpl implements ParseadorWSUltimoConsumo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParseadorWSUltimoConsumoImpl.class);

	/** The Constant CODIGO_ERROR_CONSUMOS. */
	private static final String CODIGO_ERROR_CONSUMOS = "112107";

	/** The Constant PESOS. */
	private static final String PESOS = "pesos";

	/**
	 * Tarjeta tiene codigo error consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean esTarjetaSinConsumos(RetornoTarjetasEntity retorno) throws ParseadorVisaException {
		if (CODIGO_ERROR_CONSUMOS != null) {
			Boolean error = true;
			List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
			for (TarjetaEntity tarjeta : tarjetasEntity) {
				String codigoErrorTarjeta = obtenerCodigoError(tarjeta);
				if (codigoErrorTarjeta == null || !codigoErrorTarjeta.equals(CODIGO_ERROR_CONSUMOS)) {
					error = false;
					break;
				}
			}
			return error;
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtiene el codigo de tarjeta. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta codigoTarjeta/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerCodigoTarjeta(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getCodigoTarjeta() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getCodigoTarjeta();
	}

	/**
	 * Obtiene los ultimos consumos de la tarjeta ingresada por parametro. Tag
	 * /tarjetas/tarjeta/document/movimientos/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public UltimosConsumosEntity obtenerMovimientos(TarjetaEntity entity) throws ParseadorVisaException {
		TarjetaDocumentEntity document = obtenerDocument(entity);
		if (document.getUltimosMovimientos() == null) {
			throw new ParseadorVisaException();
		}
		return document.getUltimosMovimientos();
	}

	/**
	 * Obtiene la lista de movimientos de tarjetas. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<TarjetaUltimosConsumosEntity> obtenerMovimientosDeUnaTarjeta(TarjetaEntity entity)
			throws ParseadorVisaException {
		UltimosConsumosEntity consumos = obtenerMovimientos(entity);
		if (consumos.getTarjetaList() == null) {
			throw new ParseadorVisaException();
		}
		return consumos.getTarjetaList();
	}

	/**
	 * Obtiene los consumos de la tarjeta ingresada por parametro. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/movimiento/.
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public TarjetaUltimosConsumosEntity obtenerTarjetaUltimosConsumosEntity(TarjetaEntity entity)
			throws ParseadorVisaException {
		String numeroTarjetaActiva = this.obtenerNumeroTarjetaActiva(entity);
		List<TarjetaUltimosConsumosEntity> consumos = obtenerMovimientosDeUnaTarjeta(entity);
		TarjetaUltimosConsumosEntity tarjetaUltimosConsumos = null;
		for (TarjetaUltimosConsumosEntity tarjetaUltimosConsumosEntity : consumos) {
			String codigoTarjeta = this.obtenerCodigoTarjeta(tarjetaUltimosConsumosEntity);
			if (codigoTarjeta.equals(numeroTarjetaActiva)) {
				tarjetaUltimosConsumos = tarjetaUltimosConsumosEntity;
				break;
			}
		}
		return tarjetaUltimosConsumos;
	}

	/**
	 * Obtiene los consumos de la tarjeta activa ingresada por parametro. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/movimiento/.
	 *
	 * @param entity
	 *            the entity
	 * @param numeroTarjetaActiva
	 *            the numero tarjeta activa
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public TarjetaUltimosConsumosEntity obtenerTarjetaUltimosConsumosEntity(TarjetaEntity entity,
			String numeroTarjetaActiva) throws ParseadorVisaException {
		TarjetaUltimosConsumosEntity tarjetaUltimosConsumos = null;
		if (entity != null) {
			List<TarjetaUltimosConsumosEntity> consumos = obtenerMovimientosDeUnaTarjeta(entity);
			for (TarjetaUltimosConsumosEntity tarjetaUltimosConsumosEntity : consumos) {
				String codigoTarjeta = this.obtenerCodigoTarjeta(tarjetaUltimosConsumosEntity);
				if (codigoTarjeta.equals(numeroTarjetaActiva)) {
					tarjetaUltimosConsumos = tarjetaUltimosConsumosEntity;
					break;
				}
			}
		}
		return tarjetaUltimosConsumos;
	}

	/**
	 * Obtiene la tarjeta TI antigua. Tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/.
	 *
	 * @param retorno
	 *            the retorno
	 * @param numeroTarjetaActivaPP
	 *            the numero tarjeta activa PP
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public TarjetaEntity obtenerTarjetaAntigua(RetornoTarjetasEntity retorno, String numeroTarjetaActivaPP)
			throws ParseadorVisaException {
		TarjetaEntity tarjetaAntigua = null;
		List<TarjetaEntity> tarjetasEntity = obtenerTarjetas(retorno);
		for (TarjetaEntity tarjeta : tarjetasEntity) {
			if (!tarjetaTieneError(tarjeta)) {
				String numeroTarjetaActiva = obtenerNumeroTarjetaActiva(tarjeta);
				if (esCategoriaTitular(tarjeta) && !numeroTarjetaActiva.equals(numeroTarjetaActivaPP)) {
					tarjetaAntigua = tarjeta;
					break;
				}
			}
		}
		return tarjetaAntigua;
	}

	/**
	 * Gets the consumo dolares. tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/dolares/
	 *
	 * @param entity
	 *            the entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumoDolares(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getDolares() == null || StringUtils.isEmpty(entity.getDolares())) {
			throw new ParseadorVisaException();
		}
		try {
			return TarjetaBOUtils.convertirSaldo(entity.getDolares());
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error("Error en Gets the consumo dolares.", e);
			throw new ParseadorVisaException();
		}

	}

	/**
	 * Obtener pesos.
	 *
	 * @param entity
	 *            the entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumoPesos(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getPesos() == null || StringUtils.isEmpty(entity.getPesos())) {
			throw new ParseadorVisaException();
		}
		try {
			return TarjetaBOUtils.convertirSaldo(entity.getPesos());
		} catch (TarjetaBOUtilsException e) {
			LOGGER.error("Error en Gets the consumo dolares.", e);
			throw new ParseadorVisaException();
		}
	}

	/**
	 * Obtener lista movimientos. Clase que representa el tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/List(movimiento)
	 *
	 * @param entity
	 *            the entity
	 * @return the consumo dolares
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<MovimientoEntity> obtenerListaMovimientos(TarjetaUltimosConsumosEntity entity)
			throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getMovimientosList() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getMovimientosList();
	}

	/**
	 * Obtiene la descripcion del movimiento.
	 *
	 * @param entity
	 *            the entity
	 * @return String
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerDescripcion(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null || entity.getEstablecimiento() == null
				|| entity.getEstablecimiento().getDescripcion() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getEstablecimiento().getDescripcion();
	}

	/**
	 * Obtener fecha.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFecha(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null || entity.getFecha() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getFecha();
	}

	/**
	 * Obtener establecimiento codigo.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerEstablecimientoCodigo(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null || entity.getEstablecimiento() == null || entity.getEstablecimiento().getCodigo() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getEstablecimiento().getCodigo();
	}

	/**
	 * Obtener ticket.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerTicket(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null || entity.getTicket() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getTicket();
	}

	/**
	 * Obtener importe valor. tag
	 * /tarjetas/tarjeta/document/movimientos/tarjeta/movimiento/importe/
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerImporteValor(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null || entity.getImporte() == null || entity.getImporte().getValor() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getImporte().getValor();
	}

	/**
	 * Obtener tipo moneda.
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerTipoMoneda(MovimientoEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getTipoMoneda() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getTipoMoneda();
	}

	/**
	 * Es importe pesos.
	 *
	 * @param entity
	 *            the entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean esImportePesos(MovimientoEntity entity) throws ParseadorVisaException {
		return StringUtils.equals(obtenerTipoMoneda(entity), PESOS);
	}

	/**
	 * Es credito.
	 *
	 * @param entity
	 *            the entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean esCredito(TarjetaUltimosConsumosEntity entity) throws ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getCodigoTarjeta() == null) {
			throw new ParseadorVisaException();
		}
		String codigoTarjeta = entity.getCodigoTarjeta();
		return StringUtils.containsOnly(codigoTarjeta, TarjetaUtils.CERO_ENTERO) || StringUtils.isEmpty(codigoTarjeta);
	}
}
