/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.PosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaPosicionConsolidadaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSPosicionConsolidada;

/**
 * The Class ParseadorWSPosicionConsolidadaImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ParseadorWSPosicionConsolidadaImpl extends ParseadorWSVisaImpl implements ParseadorWSPosicionConsolidada {

	/**
	 * Obtener tarjeta posicion consolidada. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/
	 *
	 * @param respuestaWSVisa
	 *            the respuesta WS visa
	 * @return the posicion consolidada entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public PosicionConsolidadaEntity obtenerTarjetaPosicionConsolidada(RetornoTarjetasEntity respuestaWSVisa)
			throws ParseadorVisaException {
		List<TarjetaEntity> tarjetas = obtenerTarjetas(respuestaWSVisa);
		if (!tarjetas.isEmpty()) {
			TarjetaEntity t1 = tarjetas.get(0);
			if (t1.getPosicionConsolidada() == null) {
				throw new ParseadorVisaException();
			}
			return t1.getPosicionConsolidada();
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtiene el consumo en pesos. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/consumos/pesos/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosPesos(PosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getPesos() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getPesos());
	}

	/**
	 * Obtiene el consumo en pesos. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/consumos/pesos/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosPesos(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getPesos() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getPesos());
	}

	/**
	 * Obtiene el consumo en pesos. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/consumos/dolares/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosDolares(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getDolares() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getDolares());
	}

	/**
	 * Obtiene los datos de los saldos en cuotas de la Tarjeta. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/consumos/dolares/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosDolares(PosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getDolares() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getDolares());
	}

	/**
	 * Obtiene los datos de las Tarjetas. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/
	 *
	 * @param entity
	 *            the entity
	 * @return the list
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<TarjetaPosicionConsolidadaEntity> obtenerListaTarjetaPosicionConsolidada(
			PosicionConsolidadaEntity entity) throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getTarjetas() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getTarjetas();
	}

	/**
	 * Obtiene los datos de las Tarjetas. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/numero/
	 *
	 * @param entity
	 *            the entity
	 * @return the string
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerNumeroDeTarjeta(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getNumero() == null) {
			throw new ParseadorVisaException();
		}
		return entity.getNumero();
	}

	/**
	 * Obtener posicion consolidada para adicional. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/numero/
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
	@Override
	public TarjetaPosicionConsolidadaEntity obtenerTarjetaPosicionConsolidada(String numeroTarjeta,
			PosicionConsolidadaEntity entity) throws TarjetaBOUtilsException, ParseadorVisaException {
		List<TarjetaPosicionConsolidadaEntity> tarjetasPosicionConsolidada = obtenerListaTarjetaPosicionConsolidada(
				entity);
		for (TarjetaPosicionConsolidadaEntity t1 : tarjetasPosicionConsolidada) {
			String numero = obtenerNumeroDeTarjeta(t1);
			if (numero.equals(numeroTarjeta)) {
				return t1;
			}
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtiene el consumo en pesos. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/consumos/pesos/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosPesosAdicional(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getPesos() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getPesos());
	}

	/**
	 * Obtiene el consumo en pesos. Tag
	 * /tarjetas/tarjeta/posicionConsolidada/tarjeta/consumos/dolares/
	 *
	 * @param entity
	 *            the entity
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public BigDecimal obtenerConsumosDolaresAdicional(TarjetaPosicionConsolidadaEntity entity)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		if (entity == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos() == null) {
			throw new ParseadorVisaException();
		}
		if (entity.getConsumos().getDolares() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(entity.getConsumos().getDolares());
	}
}
