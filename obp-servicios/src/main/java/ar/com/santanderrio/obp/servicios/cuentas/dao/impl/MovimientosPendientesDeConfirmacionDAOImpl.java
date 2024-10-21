/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.dao.MovimientosPendientesDeConfirmacionDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.EstadoOperacionMovimimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoOperacionMovimimiento;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class MovimientosPendientesDeConfirmacionDAOImpl.
 */
@Component
/**
 * Conector con el servicio CNSMOVBANE
 * 
 * @author b039542
 *
 */
public class MovimientosPendientesDeConfirmacionDAOImpl implements MovimientosPendientesDeConfirmacionDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosPendientesDeConfirmacionDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio cnsmovbane. */
	@Value("${SERVICIO.PREFIJO.CNSMOVBANE}")
	private String servicioCnsmovbane;

	/** The version cnsmovbane. */
	@Value("${SERVICIO.VERSION.CNSMOVBANE}")
	private String versionCnsmovbane;

	/** The Constant INT_1. */
	// de iatx
	private static final int INT_1 = 1;

	/** The Constant SUCURSAL_CAMPO_LONGITUD. */
	private static final int SUCURSAL_CAMPO_LONGITUD = 4;

	/** The Constant CLAVE_REPOSICIONAMIENTO_CAMPO_LONGITUD. */
	private static final int CLAVE_REPOSICIONAMIENTO_CAMPO_LONGITUD = 5;

	/** The Constant NUO_CAMPO_LONGITUD. */
	private static final int NUO_CAMPO_LONGITUD = 8;

	/** The Constant INT_9. */
	private static final int INT_9 = 9;

	/** The Constant CUENTA_CAMPO_LONGITUD. */
	private static final int CUENTA_CAMPO_LONGITUD = 16;

	/** The Constant CANTIDAD_CUENTAS_CAMPO_LONGITUD. */
	private static final int CANTIDAD_CUENTAS_CAMPO_LONGITUD = 3;

	/** The Constant MONEDA_CAMPO_LONGITUD. */
	private static final int MONEDA_CAMPO_LONGITUD = 3;

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant BLANCO_STRING. */
	private static final String BLANCO_STRING = " ";

	/** The Constant SUCURSAL_VACIO_STRING. */
	private static final String SUCURSAL_VACIO_STRING = StringUtils.repeat(BLANCO_STRING, SUCURSAL_CAMPO_LONGITUD);

	/** The Constant CUENTA_VACIO_STRING. */
	private static final String CUENTA_VACIO_STRING = StringUtils.repeat(BLANCO_STRING, CUENTA_CAMPO_LONGITUD);

	/** The Constant MONEDA_VACIO_STRING. */
	private static final String MONEDA_VACIO_STRING = StringUtils.repeat(BLANCO_STRING, MONEDA_CAMPO_LONGITUD);

	/** The Constant CLAVE_REPOSICIONAMIENTO_CEROS_STRING. */
	private static final String CLAVE_REPOSICIONAMIENTO_CEROS_STRING = StringUtils.repeat(CERO_STRING,
			CLAVE_REPOSICIONAMIENTO_CAMPO_LONGITUD);

	/** The Constant NUO_CEROS_STRING. */
	private static final String NUO_CEROS_STRING = StringUtils.repeat(CERO_STRING, NUO_CAMPO_LONGITUD);

	/** The Constant CODIGO_DE_OPERACION_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_OPERACION_DESCONOCIDO_MSG = "Codigo de operacion desconocido";

	/** The Constant CODIGO_DE_ESTADO_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ESTADO_DESCONOCIDO_MSG = "Codigo de estado desconocido";

	/** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
	private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

	/** The Constant MAXIMO_NUMERO_CUENTAS. */
	// Propio de la clase
	private static final int MAXIMO_NUMERO_CUENTAS = 30;

	/** The Constant SIN_MOVIMIETOS_CODIGO_RETORNO. */
	private static final int SIN_MOVIMIETOS_CODIGO_RETORNO = 10000042;

	/** The Constant CON_MOVIMIETOS_CODIGO_RETORNO. */
	private static final int CON_MOVIMIETOS_CODIGO_RETORNO = 0;

	/** The Constant DEPOSITO_DE_EFECTIVO_CODIGO. */
	// Tipo de operacion
	private static final String DEPOSITO_DE_EFECTIVO_CODIGO = "E";

	/** The Constant PAGO_DE_TARJETA_CODIGO. */
	private static final String PAGO_DE_TARJETA_CODIGO = "T";

	/** The Constant DEPOSITO_DE_CHEQUE_CODIGO. */
	private static final String DEPOSITO_DE_CHEQUE_CODIGO = "C";

	/** The Constant DEPOSITO_DE_CUPONES_CODIGO. */
	private static final String DEPOSITO_DE_CUPONES_CODIGO = "S";

	/** The Constant DEPOSITO_DE_EFECTIVO_ENUM. */
	private static final TipoOperacionMovimimiento DEPOSITO_DE_EFECTIVO_ENUM = TipoOperacionMovimimiento.DEPOSITO_DE_EFECTIVO;

	/** The Constant PAGO_DE_TARJETA_ENUM. */
	private static final TipoOperacionMovimimiento PAGO_DE_TARJETA_ENUM = TipoOperacionMovimimiento.PAGO_DE_TARJETA;

	/** The Constant DEPOSITO_DE_CHEQUE_ENUM. */
	private static final TipoOperacionMovimimiento DEPOSITO_DE_CHEQUE_ENUM = TipoOperacionMovimimiento.DEPOSITO_DE_CHEQUE;

	/** The Constant DEPOSITO_DE_CUPONES_ENUM. */
	private static final TipoOperacionMovimimiento DEPOSITO_DE_CUPONES_ENUM = TipoOperacionMovimimiento.DEPOSITO_DE_CUPONES;

	/** The Constant A_CONFIRMAR_CODIGO. */
	// Estado de operacion
	private static final String A_CONFIRMAR_CODIGO = "00";

	/** The Constant CONFIRMADA_SIN_DIFERENCIA_CODIGO. */
	private static final String CONFIRMADA_SIN_DIFERENCIA_CODIGO = "01";

	/** The Constant CONFIRMADA_CON_DIFERENCIA_CODIGO. */
	private static final String CONFIRMADA_CON_DIFERENCIA_CODIGO = "02";

	/** The Constant A_CONFIRMAR_CON_CPC_CODIGO. */
	private static final String A_CONFIRMAR_CON_CPC_CODIGO = "04";

	/** The Constant SIN_INFORMAR_CODIGO. */
	private static final String SIN_INFORMAR_CODIGO = BLANCO_STRING + BLANCO_STRING;

	/** The Constant A_CONFIRMAR_ENUM. */
	private static final EstadoOperacionMovimimiento A_CONFIRMAR_ENUM = EstadoOperacionMovimimiento.A_CONFIRMAR;

	/** The Constant CONFIRMADA_SIN_DIFERENCIA_ENUM. */
	private static final EstadoOperacionMovimimiento CONFIRMADA_SIN_DIFERENCIA_ENUM = EstadoOperacionMovimimiento.CONFIRMADA_SIN_DIFERENCIA;

	/** The Constant CONFIRMADA_CON_DIFERENCIA_ENUM. */
	private static final EstadoOperacionMovimimiento CONFIRMADA_CON_DIFERENCIA_ENUM = EstadoOperacionMovimimiento.CONFIRMADA_CON_DIFERENCIA;

	/** The Constant A_CONFIRMAR_CON_CPC_ENUM. */
	private static final EstadoOperacionMovimimiento A_CONFIRMAR_CON_CPC_ENUM = EstadoOperacionMovimimiento.A_CONFIRMAR_CON_CPC;

	/** The Constant SIN_INFORMAR_ENUM. */
	private static final EstadoOperacionMovimimiento SIN_INFORMAR_ENUM = EstadoOperacionMovimimiento.SIN_INFORMAR;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.dao.
	 * MovimientosPendientesDeConfirmacionDAO#
	 * obtenerMovimientosPendientesDeConfirmacionPorCuenta(ar.com.santanderrio.
	 * obp.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public List<MovimientoDeCuenta> obtenerMovimientosPendientesDeConfirmacionPorCuenta(AbstractCuenta cuenta)
			throws DAOException {
		List<MovimientoDeCuenta> movimientos = null;
		IatxRequest request = new IatxRequest(servicioCnsmovbane, versionCnsmovbane);
		movimientos = invocarServicio(cuenta, request);
		return movimientos;
	}

	/**
	 * Invocar servicio.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param request
	 *            the request
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<MovimientoDeCuenta> invocarServicio(AbstractCuenta cuenta, IatxRequest request) throws DAOException {
		IatxRequestData iatxRequestData = null;

		String[] tramaRequest = generarRequestMovimientosPendientesDeConfirmacionIatx(cuenta);

		String claveDeReposicionamiento = CLAVE_REPOSICIONAMIENTO_CEROS_STRING;
		String nuo = NUO_CEROS_STRING;
		List<MovimientoDeCuenta> movimientosPendientesList = new ArrayList<MovimientoDeCuenta>();
		boolean llamarIatx = true;
		IatxResponse iatxResponse = null;
		Cliente cliente = cuenta.getCliente();
		List<MovimientoDeCuenta> movimientosList = null;
		try {
			while (llamarIatx) {
				iatxRequestData = generarIatxRequestData(cliente, tramaRequest, claveDeReposicionamiento, nuo);
				request.setData(iatxRequestData);
				iatxResponse = iatxComm.exec(request);

				// S1. Cod_retorno
				int codigoDeRetorno = iatxResponse.getErrorCode();
				// Si codigo de retorno es 0 entonces tiene movimientos
				if (codigoDeRetorno == CON_MOVIMIETOS_CODIGO_RETORNO) {
					// S2. Clave de reposicionamiento
					claveDeReposicionamiento = iatxResponse.getNextData();
					// S3. NUO
					nuo = iatxResponse.getNextData();
					movimientosList = parsearResponseMovimientosPendientesDeConfirmacionIatx(iatxResponse);
					movimientosPendientesList.addAll(movimientosList);
					// Mientras sea distinta de 0 se debe volver a llamar a iatx
					llamarIatx = !CLAVE_REPOSICIONAMIENTO_CEROS_STRING.equals(claveDeReposicionamiento);

				} else if (codigoDeRetorno == SIN_MOVIMIETOS_CODIGO_RETORNO) {
					// Si no tiene movimientos termina el ciclo de llamadas
					llamarIatx = false;
				} else {
					// Cualquier otro codigo de error de iatx
					throw new DAOException(CODIGO_DE_ERROR_DESCONOCIDO_MSG);
				}
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return movimientosPendientesList;
	}

	/**
	 * Generar iatx request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @param trama
	 *            the trama
	 * @param claveDeReposicionamiento
	 *            the clave de reposicionamiento
	 * @param nuo
	 *            the nuo
	 * @return the iatx request data
	 */
	private IatxRequestData generarIatxRequestData(Cliente cliente, String[] trama, String claveDeReposicionamiento,
			String nuo) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		// itero y agrego los elementos de la trama al iatx request
		for (String string : trama) {
			requestData.addBodyValue(string);
		}
		// Clave de reposicionamiento y nuo
		requestData.addBodyValue(claveDeReposicionamiento);
		requestData.addBodyValue(nuo);

		return requestData;
	}

	/**
	 * Arma el IatxRequestData para la cuenta. No agrega codigo de repeticion ni
	 * NUO
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return IatxRequestData
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String[] generarRequestMovimientosPendientesDeConfirmacionIatx(AbstractCuenta cuenta) throws DAOException {
		String codigoTipoCuenta = cuenta.getTipoCuenta();
		if (codigoTipoCuenta == null || codigoTipoCuenta.trim().isEmpty()) {
			throw new DAOException("Tipo de cuenta no especificado");
		}
		TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigoTipoCuenta);
		// Obtengo el estado de la cuenta necesario para armar el request (4
		// casos distintos)
		Boolean isCuentaUnica = TipoCuenta.CUENTA_UNICA.equals(tipoCuenta);
		Boolean isCuentaCerrada = cuenta.isCuentaCerrada();

		return generarTramaRequestMovimientosPendendientes(cuenta, isCuentaUnica, isCuentaCerrada);

	}

	/**
	 * Genera un array de Strings con la cantidad de cuentas (N3) y las 30
	 * repeticiones de 23 caracteres que representan las 30 posibles cuentas
	 * para iatx.
	 *
	 * @param abstractCuenta
	 *            the abstract cuenta
	 * @param isCuentaUnica
	 *            the is cuenta unica
	 * @param isCuentaCerrada
	 *            the is cuenta cerrada
	 * @return the string[]
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String[] generarTramaRequestMovimientosPendendientes(AbstractCuenta abstractCuenta, Boolean isCuentaUnica,
			Boolean isCuentaCerrada) throws DAOException {
		/*
		 * Campo / Formato Cantidad de cuentas / N3 + 30 elementos del siguente
		 * tipo --------------- Sucursal / A4 Cuenta / A16 Moneda / A3
		 * ---------------
		 */

		List<String> elementosTrama = new ArrayList<String>();
		int cantidadDeCuentas = 1;
		// Si es cuenta unica vale x2 cuentas
		if (isCuentaUnica) {
			cantidadDeCuentas++;
		}
		// N3 en este caso solo sera de 1 caracter numerico y completa 2 ceros a
		// la izquierda
		String cantidadDeCuentasCampo = StringUtils.leftPad(String.valueOf(cantidadDeCuentas),
				CANTIDAD_CUENTAS_CAMPO_LONGITUD, CERO_STRING);

		elementosTrama.add(cantidadDeCuentasCampo);

		// Registro Cuentas 23 chars
		// A4
		String sucursalCampo = abstractCuenta.getNroSucursal().trim();
		sucursalCampo = StringUtils.leftPad(sucursalCampo, SUCURSAL_CAMPO_LONGITUD, CERO_STRING);
		// A16
		String cuentaCampo = null;

		if (isCuentaCerrada) {
			CuentaCerrada cuentaCerrada = CuentaCerrada.class.cast(abstractCuenta);
			String nroCuentaProducto = cuentaCerrada.getNroCuentaProducto();
			// se toman los ultimos 9 digitos de cuenta
			nroCuentaProducto = nroCuentaProducto.substring(nroCuentaProducto.length() - INT_9);

			cuentaCampo = StringUtils.leftPad(cuentaCerrada.getProductoAltair().concat(nroCuentaProducto).trim(),
					CUENTA_CAMPO_LONGITUD, CERO_STRING);
		} else {
			cuentaCampo = StringUtils.leftPad(Cuenta.class.cast(abstractCuenta).getContratoAltair().trim(),
					CUENTA_CAMPO_LONGITUD, CERO_STRING);
		}

		String monedaCampo = null;
		// agrego los elementos a la trama
		for (int i = INT_1; i <= MAXIMO_NUMERO_CUENTAS; i++) {
			// Si son cuentas
			if (i <= cantidadDeCuentas) {
				if (isCuentaUnica) {
					// primer elemento de cuenta con moneda ARS, segundo con USD
					monedaCampo = getMoneda(i);
				} else {
					// Si es cuenta cerrada y no es cuenta unica toma el valor
					// de moneda altair
					if (isCuentaCerrada) {
						monedaCampo = MONEDA_VACIO_STRING;
//						monedaCampo = Cuenta.class.cast(abstractCuenta).getMonedaAltair();
					} else {
						monedaCampo = getMoneda(i);
					}
				}
			} else {
				// campos vacios
				sucursalCampo = SUCURSAL_VACIO_STRING;
				cuentaCampo = CUENTA_VACIO_STRING;
				monedaCampo = MONEDA_VACIO_STRING;
			}
			elementosTrama.add(sucursalCampo);
			elementosTrama.add(cuentaCampo);
			elementosTrama.add(monedaCampo);
		}

		return elementosTrama.toArray(new String[0]);
	}

	/**
	 * Gets the moneda.
	 *
	 * @param i
	 *            the i
	 * @return the moneda
	 */
	private String getMoneda(int i) {
		if (i == INT_1) {
			return DivisaEnum.PESO.getCodigo();
		} else {
			return DivisaEnum.DOLAR.getCodigo();
		}
	}

	/**
	 * Parsear response movimientos pendientes de confirmacion iatx.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<MovimientoDeCuenta> parsearResponseMovimientosPendientesDeConfirmacionIatx(IatxResponse iatxResponse)
			throws DAOException {
		List<MovimientoDeCuenta> respuestaList = new ArrayList<MovimientoDeCuenta>();
		// S4. Cantidad de repeticiones
		int repeticiones = Integer.valueOf(iatxResponse.getNextData());

		MovimientoDeCuenta movimiento = null;
		for (int i = 0; i < repeticiones; i++) {
			movimiento = new MovimientoDeCuenta();
			movimiento.getSucursal().setIdSucursal(iatxResponse.getNextData());
			movimiento.setTipoDeCuenta(iatxResponse.getNextData());
			movimiento.setNumeroDeCuenta(iatxResponse.getNextData());
			movimiento.setDivisa(DivisaEnum.fromCodigoString(iatxResponse.getNextData()));
			movimiento.setOrigenTransaccion(iatxResponse.getNextData());
			movimiento.setFecha(new SimpleDateFormat("dd/MM/yyy").format(new Date()));
			movimiento.setHora(iatxResponse.getNextData());
			movimiento.setTipoDeOperacion(this.decodificarOperacion(iatxResponse.getNextData()));
			movimiento.setNumeroDeTicket(iatxResponse.getNextData());
			movimiento.getSucursalOrigen().setIdSucursal(iatxResponse.getNextData());
			movimiento.setUsuarioBanco(iatxResponse.getNextData());
			movimiento.setImporteOperacion(
					new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getNextData())));

			movimiento.setEstado(this.decodificarEstado(iatxResponse.getNextData()));

			respuestaList.add(movimiento);
		}
		return respuestaList;
	}

	/**
	 * Decodificar estado.
	 *
	 * @param estado
	 *            the estado
	 * @return the estado operacion movimimiento
	 * @throws DAOException
	 *             the DAO exception
	 */
	private EstadoOperacionMovimimiento decodificarEstado(String estado) throws DAOException {
		if (A_CONFIRMAR_CODIGO.equals(estado)) {
			return A_CONFIRMAR_ENUM;
		}
		if (CONFIRMADA_SIN_DIFERENCIA_CODIGO.equals(estado)) {
			return CONFIRMADA_SIN_DIFERENCIA_ENUM;
		}
		if (CONFIRMADA_CON_DIFERENCIA_CODIGO.equals(estado)) {
			return CONFIRMADA_CON_DIFERENCIA_ENUM;
		}
		if (A_CONFIRMAR_CON_CPC_CODIGO.equals(estado)) {
			return A_CONFIRMAR_CON_CPC_ENUM;
		}
		if (SIN_INFORMAR_CODIGO.equals(estado)) {
			return SIN_INFORMAR_ENUM;
		}
		// Si no es ninguno, tiro exception
		throw new DAOException(CODIGO_DE_ESTADO_DESCONOCIDO_MSG);
	}

	/**
	 * Decodificar operacion.
	 *
	 * @param codigoOperacion
	 *            the codigo operacion
	 * @return the tipo operacion movimimiento
	 * @throws DAOException
	 *             the DAO exception
	 */
	private TipoOperacionMovimimiento decodificarOperacion(String codigoOperacion) throws DAOException {
		if (DEPOSITO_DE_EFECTIVO_CODIGO.equals(codigoOperacion)) {
			return DEPOSITO_DE_EFECTIVO_ENUM;
		}
		if (PAGO_DE_TARJETA_CODIGO.equals(codigoOperacion)) {
			return PAGO_DE_TARJETA_ENUM;
		}
		if (DEPOSITO_DE_CHEQUE_CODIGO.equals(codigoOperacion)) {
			return DEPOSITO_DE_CHEQUE_ENUM;
		}
		if (DEPOSITO_DE_CUPONES_CODIGO.equals(codigoOperacion)) {
			return DEPOSITO_DE_CUPONES_ENUM;
		}

		// Si no es ninguno, tiro exception
		throw new DAOException(CODIGO_DE_OPERACION_DESCONOCIDO_MSG);
	}

}
