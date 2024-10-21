/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.AdhesionPagoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion;

/**
 * The Class AdhesionPagoDAOImpl.
 *
 * @author b039542
 */
@Component
public class AdhesionPagoDAOImpl implements AdhesionPagoDAO {

	/** The Constant SOLICITUD_YA_PROCESADADA_CODIGO_IATX. */
	private static final int SOLICITUD_YA_PROCESADADA_CODIGO_IATX = 10000163;

	/** The Constant NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX. */
	private static final int NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX = 10000080;

	/** The Constant NOMBRE_SERVICIO_EMPRESA_CANTIDAD_DIGITOS. */
	private static final int NOMBRE_SERVICIO_EMPRESA_CANTIDAD_DIGITOS = 10;

	/** The Constant TIPO_CUENTA_DEBITO_CANTIDAD_DIGITOS. */
	private static final int TIPO_CUENTA_DEBITO_CANTIDAD_DIGITOS = 2;

	/** The Constant SUCURSAL_CUENTA_DEBITO_CANTIDAD_DIGITOS. */
	private static final int SUCURSAL_CUENTA_DEBITO_CANTIDAD_DIGITOS = 3;

	/** The Constant NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS. */
	private static final int NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS = 7;

	/** The Constant NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS total -19-. */
	private static final int NUMERO_CUENTA_DEBITO_MAX_DIGITOS = 19;

	/** The Constant NUMERO_ORDEN_FIRMANTE_CANTIDAD_DIGITOS. */
	private static final int NUMERO_ORDEN_FIRMANTE_CANTIDAD_DIGITOS = 3;

	/** The Constant LIMITE_ADHESION_CANTIDAD_DIGITOS. */
	private static final int LIMITE_ADHESION_CANTIDAD_DIGITOS = 14;

	/** The Constant CODIGO_CLIENTE_EMPRESA_PAD. */
	private static final int CODIGO_CLIENTE_EMPRESA_PAD = 19;

	/** The Constant TIPO_CUENTA_CANTIDAD_DIGITOS. */
	private static final int TIPO_CUENTA_CANTIDAD_DIGITOS = 2;

	/** The Constant NOMBRE_SERVICIO_MODDDIADHE. */
	private static final String NOMBRE_SERVICIO_MODDDIADHE = "MODDDIADHE";

	/** The Constant VERSION_SERVICIO_MODDDIADHE. */
	private static final String VERSION_SERVICIO_MODDDIADHE = "100";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant NOMBRE_SERVICIO_ALTPAUADHE. */
	private static final String NOMBRE_SERVICIO_ALTPAUADHE = "ALTPAUADHE";

	/** The Constant VERSION_SERVICIO_ALTPAUADHE. */
	private static final String VERSION_SERVICIO_ALTPAUADHE = "100";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionPagoDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.pagos.dao.AdhesionPagoDAO#modificarAdhesionPago(
	 * ar.com.santanderrio.obp.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.pagos.entities.PagoPendiente,
	 * java.math.BigDecimal,
	 * ar.com.santanderrio.obp.cuentas.entities.IdentificacionCuenta,
	 * ar.com.santanderrio.obp.pagos.entities.TipoDeModificacion)
	 */
	@Override
	public ResultadoTransaccion modificarAdhesionPago(Cliente cliente, PagoPendiente servicioAdherido,
			Cuenta cuentaDebito, BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion) {
		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_MODDDIADHE, VERSION_SERVICIO_MODDDIADHE);
		IatxRequestData iatxRequestData = generarModificarAdhesionRequestData(cliente, servicioAdherido, cuentaDebito,
				limiteAdhesion, tipoDeModificacion);

		iatxRequest.setData(iatxRequestData);
		ResultadoTransaccion resultado = new ResultadoTransaccion();
		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				resultado = iatxResponse.getResultadoTransaccion();
				resultado.setEstadoRespuesta(EstadoRespuesta.OK);
				break;
			case SOLICITUD_YA_PROCESADADA_CODIGO_IATX:
			case NO_ES_TITULAR_DE_CUENTA_CODIGO_IATX:
				resultado = iatxResponse.getResultadoTransaccion();
				resultado.setEstadoRespuesta(EstadoRespuesta.WARNING);
				break;
			default:
				resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}
		return resultado;
	}

	/**
	 * Generar modificar adhesion request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @param cuentaDebito
	 *            the cuenta debito
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @return the iatx request data
	 */
	private IatxRequestData generarModificarAdhesionRequestData(Cliente cliente, PagoPendiente servicioAdherido,
			Cuenta cuentaDebito, BigDecimal limiteAdhesion, TipoDeModificacion tipoDeModificacion) {

		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		// FIXME todo todo todo emparchado

		// Cuit Empresa N11 Cuit de la Empresa retornado por CNSDDIADHG
		iatxRequestData.addBodyValue(servicioAdherido.getCuitEmpresa());
		// Nombre Servicio Empresa C10 Nombre del Servicio de la Empresa
		// retornado por CNSDDIADHG
		// Se agrega uppercase
		iatxRequestData.addBodyValue(
				StringUtils.left(servicioAdherido.getNombreEmpresaIatx(), NOMBRE_SERVICIO_EMPRESA_CANTIDAD_DIGITOS)
						.toUpperCase(Locale.getDefault()));
		// Nro. Partida Servicio Empresa C22 Número de partida del Servicio
		// retornado por CNSDDIADHG
		// se agrega trim
		iatxRequestData.addBodyValue(servicioAdherido.getCodigoClienteEmpresa().trim());
		// Tipo de Modificación N1 1- Si solo modifica la Cta. Débito
		// 2- Si solo modifica el Limite Débito
		// 3- Si modifica ambos
		iatxRequestData.addBodyValue(obtenerCodigoTipoDeModificacion(tipoDeModificacion));
		// Tipo Cuenta Débito N1 Tipo de Cta del servicio IDECLTSDO v1.80
		// correspondiente a la cta seleccionada por el cliente
		String tipoCuenta = cuentaDebito.getTipoCuentaEnum().getCodigo().toString();
		// Se agrega un "0" a la izquierda (max 2 digitos)
		iatxRequestData.addBodyValue(StringUtils.leftPad(tipoCuenta, TIPO_CUENTA_DEBITO_CANTIDAD_DIGITOS, "0"));
		// Sucursal Cuenta Débito N3 Nro Sucursal del servicio IDECLTSDO v1.80
		// correspondiente a la cta seleccionada por el cliente
		iatxRequestData.addBodyValue(
				StringUtils.right(cuentaDebito.getNroSucursal(), SUCURSAL_CUENTA_DEBITO_CANTIDAD_DIGITOS));
		// Nro. Cuenta Producto Débito N8 Nro Cta del servicio IDECLTSDO v1.80
		// correspondiente a la cta seleccionada por el cliente
		// FIXME Se trunca a 7 digitos, revisar si anda con 8
		iatxRequestData.addBodyValue(
				StringUtils.right(cuentaDebito.getNroCuentaProducto(), NUMERO_CUENTA_DEBITO_CANTIDAD_DIGITOS));
		// Nro. Orden Firmante N2 Nro Orden Firmante del servicio IDECLTSDO
		// v1.80 correspondiente a la cta seleccionada por el cliente
		// se cambia a 3 digitos
		iatxRequestData.addBodyValue(
				StringUtils.right(cuentaDebito.getNroOrdenFirmante(), NUMERO_ORDEN_FIRMANTE_CANTIDAD_DIGITOS));
		// Límite Adhesión DDI N16 Ingresado por el cliente ó valor anterior si
		// no hubo modificación
		// Se cambia a 9(12)v99
		iatxRequestData.addBodyValue(
				ISBANStringUtils.ajustadorBigDecimalIatx(limiteAdhesion, LIMITE_ADHESION_CANTIDAD_DIGITOS));

		return iatxRequestData;

	}

	/**
	 * Obtener codigo tipo de modificacion.
	 *
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @return the string
	 */
	private String obtenerCodigoTipoDeModificacion(TipoDeModificacion tipoDeModificacion) {
		String codigoTipoModificacion = null;
		switch (tipoDeModificacion) {
		case MOD_CUENTA_DEBITO:
			codigoTipoModificacion = "1";
			break;
		case MOD_LIMITE_DEBITO:
			codigoTipoModificacion = "2";
			break;
		case MOD_AMBOS:
			codigoTipoModificacion = "3";
			break;
		default:
			LOGGER.debug("Tipo de modificacion no evaluado, se envía campo en blanco");
			codigoTipoModificacion = " ";
			break;
		}
		return codigoTipoModificacion;
	}

	/**
	 * Modificar adhesion pago automatico de pago mis cuentas. DTF 10216, 9770,
	 * 10217.
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @param cuentaDebito
	 *            the cuenta debito
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @param cuentaDelServicio
	 *            the cuenta del servicio
	 * @return the resultado transaccion
	 * @throws DAOException
	 *             the DAO exception
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.AdhesionPagoDAO#modificarAdhesionPagoAutomaticoDePagoMisCuentas(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 *      ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente,
	 *      ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 *      java.math.BigDecimal,
	 *      ar.com.santanderrio.obp.servicios.pagos.entities.TipoDeModificacion)
	 */
	@Override
	public ResultadoTransaccion modificarAdhesionPagoAutomaticoDePagoMisCuentas(Cliente cliente,
			PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal limiteAdhesion,
			TipoDeModificacion tipoDeModificacion, String cuentaDelServicio) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_ALTPAUADHE, VERSION_SERVICIO_ALTPAUADHE);
		IatxRequestData iatxRequestData = generarModificarAdhesionPAutoPMisCuentasRequestData(cliente, servicioAdherido,
				cuentaDebito, limiteAdhesion, tipoDeModificacion, cuentaDelServicio);

		iatxRequest.setData(iatxRequestData);
		ResultadoTransaccion resultado = new ResultadoTransaccion();
		try {
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				resultado = iatxResponse.getResultadoTransaccion();
				resultado.setEstadoRespuesta(EstadoRespuesta.OK);
				break;
			default:
				resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			resultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
			throw new DAOException(e);
		}
		return resultado;
	}

	/**
	 * Generar modificar adhesion Pago auto Pago mis cuentas request data.
	 *
	 * @param cliente
	 *            the cliente
	 * @param servicioAdherido
	 *            the servicio adherido
	 * @param cuentaDebito
	 *            the cuenta debito
	 * @param limiteAdhesion
	 *            the limite adhesion
	 * @param tipoDeModificacion
	 *            the tipo de modificacion
	 * @param cuentaDelServicio
	 *            TODO: the cuenta del servicio, sin "00". Ej.: "201-361807/3"
	 *            1234567890123456789 sería: "2013618073 " [lenght = 19].
	 * @return the iatx request data
	 */
	private IatxRequestData generarModificarAdhesionPAutoPMisCuentasRequestData(Cliente cliente,
			PagoPendiente servicioAdherido, Cuenta cuentaDebito, BigDecimal limiteAdhesion,
			TipoDeModificacion tipoDeModificacion, String cuentaDelServicio) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		// Alta-Baja -A1- B=BAJA. chango to constant VALUE_BAJA
		iatxRequestData.addBodyValue("A");

		// Nombre Servicio Empresa -A4- Nombre del Servicio de la Empresa
		// retornado por CNSPAUDEUD. + Se agrega uppercase
		iatxRequestData.addBodyValue(servicioAdherido.getNombreEmpresaIatx().toUpperCase(Locale.getDefault()));

		// Identificacion del cliente empresa -A19- +se completa con blancos a
		// derecha
		iatxRequestData.addBodyValue(
				StringUtils.rightPad(servicioAdherido.getCodigoClienteEmpresa(), CODIGO_CLIENTE_EMPRESA_PAD));

		// monto tope -N14,2-
		iatxRequestData.addBodyValue(
				ISBANStringUtils.ajustadorBigDecimalIatx(limiteAdhesion, LIMITE_ADHESION_CANTIDAD_DIGITOS));

		// Tipo Cuenta -A2- Tipo de Cta del servicio IDECLTSDO v1.80
		// correspondiente a la cta seleccionada por el cliente
		iatxRequestData.addBodyValue(StringUtils.leftPad(cuentaDebito.getTipoCuentaSinUnificar().toString(),
				TIPO_CUENTA_CANTIDAD_DIGITOS, "0"));

		// Numero cuenta -A19-
		iatxRequestData.addBodyValue(StringUtils.rightPad(cuentaDelServicio, NUMERO_CUENTA_DEBITO_MAX_DIGITOS));

		return iatxRequestData;
	}

}
