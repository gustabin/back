/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.AdhesionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.AdhesionTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.FormaDePagoTarjetaEnum;

/**
 * The Class AdhesionTarjetasDAOImpl.
 *
 * @author b039542
 */
@Component
public class AdhesionTarjetasDAOImpl implements AdhesionTarjetasDAO {

	/** The Constant GENERANDO_REQUEST_MSG. */
	private static final String GENERANDO_REQUEST_MSG = "Generando request para tarjeta {}...";

	/** The Constant PARSEANDO_RESPUESTA_MSG. */
	private static final String PARSEANDO_RESPUESTA_MSG = "Parseando respuesta...";

	/** The Constant CODIGO_DE_RETORNO_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_RETORNO_DESCONOCIDO_MSG = "Código de retorno desconocido. Retornando NULL...";

	/** The Constant CODIGO_DE_RETORNO_MSG. */
	private static final String CODIGO_DE_RETORNO_MSG = "Código de retorno: {}";

	/** The Constant NOMBRE_SERVICIO_IATX. */
	private static final String NOMBRE_SERVICIO_IATX = "CNSADHETAR";

	/** The Constant VERSION_SERVICIO_IATX. */
	private static final String VERSION_SERVICIO_IATX = "120";

	/** The Constant TIPO_TARJETA_LENGTH. */
	private static final int TIPO_TARJETA_LENGTH = 2;

	/** The Constant NRO_TARJETA_LENGTH. */
	private static final int NRO_TARJETA_LENGTH = 10;

	/** The Constant ZERO_STRING. */
	private static final String ZERO_STRING = "0";

	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

	/** The Constant ERROR_FECHA. */
	private static final String ERROR_FECHA = "Error al formatear fecha {}";

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionTarjetasDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.tarjetas.dao.AdhesionTarjetasDAO#
	 * consultarAdhesionTarjeta(ar.com.santanderrio.obp.cuentas.entities.Cuenta,
	 * ar.com.santanderrio.base.clientes.entities.ResumenCliente)
	 */
	@Override
	public AdhesionTarjeta consultarAdhesionTarjeta(Cuenta tarjeta, ResumenCliente cliente) throws DAOException {

		// FIXME determinar como devuelve si hay error, por ahora devuelve null
		IatxRequest request = generarRequest(tarjeta, cliente);
		IatxResponse response = null;
		try {
			response = iatxComm.exec(request);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		}
		AdhesionTarjeta result = null;
		int errorCode = response.getErrorCode();
		LOGGER.info(CODIGO_DE_RETORNO_MSG, errorCode);
		if (errorCode == 0) {
			result = leerResponse(response);
		} else {
			LOGGER.info(CODIGO_DE_RETORNO_DESCONOCIDO_MSG);
		}

		return result;

	}

	/**
	 * Generar request.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param cliente
	 *            the cliente
	 * @return the iatx request
	 */
	private IatxRequest generarRequest(Cuenta tarjeta, ResumenCliente cliente) {
		IatxRequest request = new IatxRequest(NOMBRE_SERVICIO_IATX, VERSION_SERVICIO_IATX);
		IatxRequestData data = new IatxRequestData(cliente);
		LOGGER.info(GENERANDO_REQUEST_MSG, tarjeta);
		Integer tipoCuentaCodigo = tarjeta.getTipoCuentaEnum().getCodigo();
		String tipoCuentaString = String.valueOf(tipoCuentaCodigo);
		tipoCuentaString = StringUtils.leftPad(tipoCuentaString, TIPO_TARJETA_LENGTH, ZERO_STRING);
		data.addBodyValue(tipoCuentaString);
		String nroCuentaProducto = tarjeta.getNroCuentaProducto().trim();
		nroCuentaProducto = StringUtils.leftPad(nroCuentaProducto, NRO_TARJETA_LENGTH, ZERO_STRING);
		data.addBodyValue(nroCuentaProducto);
		request.setData(data);
		return request;
	}

	/**
	 * Leer response.
	 *
	 * @param response
	 *            the response
	 * @return the adhesion tarjeta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private AdhesionTarjeta leerResponse(IatxResponse response) throws DAOException {
		AdhesionTarjeta adhesionTarjeta = new AdhesionTarjeta();
		LOGGER.info(PARSEANDO_RESPUESTA_MSG);
		// Sucursal_Cuenta_Debito
		// Tipo_Cuenta_Débito
		// Nro_Cuenta_Débito
		// Nro-firmante
		// Forma_Pago
		// Importe de agendamiento
		// Fecha de proxima agenda

		adhesionTarjeta.setSucursalCuentaDebito(response.getNextData());
		adhesionTarjeta.setTipoCuentaDebito(response.getNextData());
		adhesionTarjeta.setNroCuentaDebito(response.getNextData());
		adhesionTarjeta.setNroFirmante(response.getNextData());
		adhesionTarjeta.setFormaDePago(FormaDePagoTarjetaEnum.porCodigo(response.getNextData()));
		String importe = response.getNextData();
		adhesionTarjeta.setImporteDeAgendamiento(new BigDecimal(importe));

		String fechaString = response.getNextData();
		try {
			adhesionTarjeta.setFechaDeProximaAgenda(dateFormatter.parse(fechaString));
		} catch (ParseException e) {
			LOGGER.error(ERROR_FECHA, fechaString, e);
			throw new DAOException(e, e.getMessage());
		}

		return adhesionTarjeta;
	}
}
