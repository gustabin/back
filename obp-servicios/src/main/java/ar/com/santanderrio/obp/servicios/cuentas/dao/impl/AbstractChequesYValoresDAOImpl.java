/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class AbstractChequesYValoresDAOImpl.
 *
 * @author b039542
 */
public abstract class AbstractChequesYValoresDAOImpl {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractChequesYValoresDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant TIPO_CUENTA_CAMPO_LONGITUD. */
	private static final int TIPO_CUENTA_CAMPO_LONGITUD = 2;

	/** The Constant SUCURSAL_CAMPO_LONGITUD. */
	private static final int SUCURSAL_CAMPO_LONGITUD = 3;

	/** The Constant CUENTA_CAMPO_LONGITUD. */
	private static final int CUENTA_CAMPO_LONGITUD = 7;

	/** The Constant DATE_PATTERN. */
	private static final String DATE_PATTERN = "yyyyMMdd";

	/** The date formatter. */
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant CUENTA_NULL_MSG. */
	private static final String CUENTA_NULL_MSG = "Cuenta es null";

	/** The Constant ERROR_PARSEO_FECHA_MSG. */
	private static final String ERROR_PARSEO_FECHA_MSG = "Error al parsear fecha iatx";

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

	/** The Constant SIN_MOVIMIETOS_CODIGO_RETORNO. */
	// Propio de la clase
	private static final int SIN_MOVIMIETOS_CODIGO_RETORNO = 10002001;

	/** The Constant CON_MOVIMIETOS_CODIGO_RETORNO. */
	private static final int CON_MOVIMIETOS_CODIGO_RETORNO = 0;

	/** The Constant USD_CODE. */
	private static final String USD_CODE = "02";

	/**
	 * Invoca al servicio de valores pendientes (credito o debito) y devuelve la
	 * lista de movimientos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param request
	 *            the request
	 * @param esDebito
	 *            the es debito
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	protected List<DetalleMovimientoChequesYValoresEntity> invocarServicio(AbstractCuenta cuenta, IatxRequest request,
			boolean esDebito) throws DAOException {

		List<DetalleMovimientoChequesYValoresEntity> movimientosList = null;
		try {
			IatxRequestData iatxRequestData = generarIatxRequestData(cuenta);
			request.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			// Cod_retorno
			int codigoDeRetorno = iatxResponse.getErrorCode();
			// Si codigo de retorno es 0 entonces tiene movimientos
			if (codigoDeRetorno == CON_MOVIMIETOS_CODIGO_RETORNO) {

				leerDatosNoComunes(iatxResponse);
				movimientosList = parsearResponseMovimientosPendientesDeConfirmacionIatx(iatxResponse, esDebito);

			} else if (codigoDeRetorno == SIN_MOVIMIETOS_CODIGO_RETORNO) {
				// Si no tiene movimientos devuelvo una lista vacia
				movimientosList = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
			} else {
				// Cualquier otro codigo de error de iatx
				throw new DAOException(CODIGO_DE_ERROR_DESCONOCIDO_MSG);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return movimientosList;
	}

	/**
	 * Leer datos no comunes.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 */
	protected abstract void leerDatosNoComunes(IatxResponse iatxResponse);

	/**
	 * Generar iatx request data.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the iatx request data
	 * @throws DAOException
	 *             the DAO exception
	 */
	private IatxRequestData generarIatxRequestData(AbstractCuenta cuenta) throws DAOException {
		if (cuenta == null) {
			throw new DAOException(CUENTA_NULL_MSG);
		}
		IatxRequestData requestData = new IatxRequestData(cuenta.getCliente());
		/*
		 * Campo / Formato / Descripcion --------------- 1. TIPO_CUENTA N2 Tipo
		 * de Cuenta 2. SUCURSAL_CUENTA N3 Sucursal de la Cuenta 3. NRO_CUENTA
		 * N7 Numero de Cuenta ---------------
		 */
		String tipoDeCuentaCampo = cuenta.getTipoCuenta();
		tipoDeCuentaCampo = StringUtils.right(tipoDeCuentaCampo, TIPO_CUENTA_CAMPO_LONGITUD);
		tipoDeCuentaCampo = StringUtils.leftPad(tipoDeCuentaCampo, TIPO_CUENTA_CAMPO_LONGITUD, CERO_STRING);

		String sucursalCuentaCampo = cuenta.getNroSucursal();
		sucursalCuentaCampo = StringUtils.right(sucursalCuentaCampo, SUCURSAL_CAMPO_LONGITUD);
		sucursalCuentaCampo = StringUtils.leftPad(sucursalCuentaCampo, SUCURSAL_CAMPO_LONGITUD, CERO_STRING);

		String nroCuentaCampo = cuenta.getNroCuentaProducto();
		nroCuentaCampo = StringUtils.right(nroCuentaCampo, CUENTA_CAMPO_LONGITUD);
		nroCuentaCampo = StringUtils.leftPad(nroCuentaCampo, CUENTA_CAMPO_LONGITUD, CERO_STRING);

		requestData.addBodyValue(tipoDeCuentaCampo);
		requestData.addBodyValue(sucursalCuentaCampo);
		requestData.addBodyValue(nroCuentaCampo);

		return requestData;
	}

	/**
	 * Parsear response movimientos pendientes de confirmacion iatx.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param esDebito
	 *            the es debito
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<DetalleMovimientoChequesYValoresEntity> parsearResponseMovimientosPendientesDeConfirmacionIatx(
			IatxResponse iatxResponse, boolean esDebito) throws DAOException {
		List<DetalleMovimientoChequesYValoresEntity> respuestaList = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
		// S4. Cantidad de repeticiones
		int repeticiones = Integer.valueOf(iatxResponse.getNextData());

		DetalleMovimientoChequesYValoresEntity movimiento = null;
		/*
		 * 1. Fecha_Comprobante_Debito N8 2. Descripcion_Movimiento A15 3.
		 * Descripcion_Adicional_Movimiento A30 4. Nro_Comprobante N8 5.
		 * Importe_Debitar N14(12.2) 6. Moneda_movimiento(2) C2 7.
		 * Sucursal_Movimiento_origen N3
		 */
		for (int i = 0; i < repeticiones; i++) {
			movimiento = new DetalleMovimientoChequesYValoresEntity();
			try {
				movimiento.setFecha(dateFormatter.parse(iatxResponse.getNextData()));
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e, ERROR_PARSEO_FECHA_MSG);
			}
			movimiento.setDescripcion(iatxResponse.getNextData());
			movimiento.setDescripcionAdicional(iatxResponse.getNextData());
			movimiento.setNroComprobante(iatxResponse.getNextData());
			movimiento.setImporteMovimiento(
					new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getNextData())));
			movimiento.setIsMovimientoEnDolares(USD_CODE.equals(iatxResponse.getNextData()));
			movimiento.setSucursalOrigen(iatxResponse.getNextData());
			movimiento.setDebito(esDebito);
			respuestaList.add(movimiento);
		}
		return respuestaList;
	}

}
