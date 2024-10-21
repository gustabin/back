/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cotizacion.bo.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cotizacion.bo.CotizacionBO;
import ar.com.santanderrio.obp.servicios.cotizacion.dao.CotizacionDAO;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionDTO;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionParametrosEntity;
import ar.com.santanderrio.obp.servicios.cotizacion.entities.CotizacionEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;

/**
 * Clase CotizacionBOImpl.
 */
@Component
public class CotizacionBOImpl implements CotizacionBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CotizacionBOImpl.class);

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant SEIS_STRING. */
	private static final String SEIS_STRING = "6";

	/** The Constant SIETE_STRING. */
	private static final String SIETE_STRING = "7";

	/** The Constant UNO_INTEGER. */
	private static final int UNO_INTEGER = 1;

	/** The Constant QUINCE_INTEGER. */
	private static final int QUINCE_INTEGER = 15;

	/** Variable datosCotizacionDAO. */
	@Autowired
	CotizacionDAO datosCotizacionDAO;

	/**
	 * Obtiene los datos datos de cotizacion del dolar del dia.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @param divisa
	 *            the divisa
	 * @return the cotizacion DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public CotizacionDTO obtenerDatosCotizacion(Cliente cliente, DatosTarjeta datoTarjeta, String divisa)
			throws BusinessException {

		CotizacionEntity cotizacionEntity = new CotizacionEntity();

		try {
			cotizacionEntity = datosCotizacionDAO.obtenerDatosCotizacion(cliente,
					generateCotizacionData(datoTarjeta, cliente, divisa));
		} catch (DAOException e) {
			throw new BusinessException(e);
		}

		CotizacionDTO cotizacionDTO = new CotizacionDTO();
		try {
			BeanUtils.copyProperties(cotizacionDTO, cotizacionEntity);
		} catch (IllegalAccessException e1) {
			throw new BusinessException(e1);
		} catch (InvocationTargetException e2) {
			throw new BusinessException(e2);
		}

		return cotizacionDTO;
	}

	/**
	 * Genera el objeto para el llamado al servicio de cotizacion a partir de
	 * los datos de una tarjeta y un cliente.
	 *
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @param cliente
	 *            the cliente
	 * @param divisa
	 *            the divisa
	 * @return the cotizacion parametros entity
	 */
	private CotizacionParametrosEntity generateCotizacionData(DatosTarjeta datoTarjeta, Cliente cliente,
			String divisa) {
		CotizacionParametrosEntity cotizacionParametrosEntity = validarImportesPesosDolares(datoTarjeta);

		cotizacionParametrosEntity.setMonedaPago(divisa);
		List<Cuenta> cuentas = cliente.getCuentas();
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getNroTarjetaCredito().equals(datoTarjeta.getNumeroTarjeta())) {
				cotizacionParametrosEntity
						.setNroCuentaTarjeta(StringUtils.substring(cuenta.getNroCuentaProducto(), UNO_INTEGER));
			}
		}
		cotizacionParametrosEntity.setNroTarjeta(ISBANStringUtils.eliminarCeros(datoTarjeta.getNumeroTarjeta()));
		cotizacionParametrosEntity.setTipoTarjeta(obtenerTipoTarjeta(datoTarjeta.getTipoCuentaTarjeta()));
		return cotizacionParametrosEntity;
	}

	/**
	 * Obtener tipo tarjeta.
	 *
	 * @param tipoCuentaTarjeta
	 *            the tipo cuenta tarjeta
	 * @return the string
	 */
	private String obtenerTipoTarjeta(TipoCuentaTarjeta tipoCuentaTarjeta) {
		if (TipoCuentaTarjeta.TIPOCTA_VISA.equals(tipoCuentaTarjeta)) {
			return SIETE_STRING;
		} else {
			return SEIS_STRING;
		}
	}

	/**
	 * Validar importes pesos dolares.
	 *
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @return the cotizacion parametros entity
	 */
	private CotizacionParametrosEntity validarImportesPesosDolares(DatosTarjeta datoTarjeta) {
		CotizacionParametrosEntity cotizacionParametrosEntity = new CotizacionParametrosEntity();
		cotizacionParametrosEntity.setImporteDolares(obtenerImporteDolares(datoTarjeta));
		cotizacionParametrosEntity.setImportePesos(obtenerImportePesos(datoTarjeta));
		return cotizacionParametrosEntity;
	}

	/**
	 * Obtener importe pesos.
	 *
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @return the string
	 */
	private String obtenerImportePesos(DatosTarjeta datoTarjeta) {
		int res;
		res = BigDecimal.ZERO.compareTo(datoTarjeta.getSaldoUltimoCierrePesosTC());
		if (res == UNO_INTEGER) {
			return ISBANStringUtils.ajustadorBigDecimalIatx(datoTarjeta.getSaldoUltimoCierrePesosTC(), QUINCE_INTEGER);
		} else {
			return StringUtils.repeat(CERO_STRING, QUINCE_INTEGER);
		}
	}

	/**
	 * Obtener importe dolares.
	 *
	 * @param datoTarjeta
	 *            the dato tarjeta
	 * @return the string
	 */
	private String obtenerImporteDolares(DatosTarjeta datoTarjeta) {
		int res = BigDecimal.ZERO.compareTo(datoTarjeta.getSaldoUltimoCierreDolaresTC());
		if (res == UNO_INTEGER) {
			return ISBANStringUtils.ajustadorBigDecimalIatx(datoTarjeta.getSaldoUltimoCierreDolaresTC(),
					QUINCE_INTEGER);
		} else {
			return StringUtils.repeat(CERO_STRING, QUINCE_INTEGER);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cotizacion.bo.CotizacionBO#
	 * obtenerDatosCotizacionSinExcepcion(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta,
	 * java.lang.String)
	 */
	@Override
	public CotizacionDTO obtenerDatosCotizacionSinExcepcion(Cliente cliente, DatosTarjeta datoTarjeta, String divisa) {
		try {
			return obtenerDatosCotizacion(cliente, datoTarjeta, divisa);
		} catch (BusinessException e) {
			LOGGER.info(e.getMessage(), e);
			return new CotizacionDTO(true);
		}
	}

}
