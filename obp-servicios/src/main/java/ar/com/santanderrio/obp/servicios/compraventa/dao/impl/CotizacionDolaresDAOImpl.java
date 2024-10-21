/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.BotonPanicoCompraventaException;
import ar.com.santanderrio.obp.servicios.compraventa.dao.CotizacionDolaresDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ConsultaCotizacionEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class CotizacionDolaresDAOImpl.
 *
 * @author sabrina.cis
 */
@TargetSystem(DataBase.ESTADISTICAS)
@Component
public class CotizacionDolaresDAOImpl implements CotizacionDolaresDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CotizacionDolaresDAOImpl.class);

	/** The Constant COD_ERROR_SUCURSAL_FUERA_HORARIO. */
	private static final int COD_ERROR_SUCURSAL_FUERA_HORARIO = 10000077;

	/** The Constant COD_ERROR_BOTON_PANICO. */
	private static final int COD_ERROR_BOTON_PANICO = 10000777;

	private static final int COD_ERROR_SERVICIO_INACTIVO = 10099906;

	private static final String ERROR_SERVICIO_INACTIVO = "La operatoria de compra-venta de dólares se encuentra habilitada los días hábiles de 10 a 15 h.";

	/** The Constant COD_ERROR_CERO. */
	private static final int COD_ERROR_CERO = 00000000;

	/** The Constant CERO. */
	private static final int CERO = 0;

	/** The Constant MONEDAS_QUINCE_CEROS. */
	private static final String MONEDAS_QUINCE_CEROS = "000000000000000";

	/** The servicio consulta cotizacion. */
	@Value("${SERVICIO.PREFIJO.CNSCOTCN}")
	private String servicioConsultaCotizacion;

	/** The version 100 cons cotizacion. */
	@Value("${SERVICIO.VERSION.CNSCOTCN}")
	private String version100ConsCotizacion;
	/** The servicio consulta cotizacion. */
	@Value("${SERVICIO.PREFIJO.CNSMISCEL}")
	private String servicioConsultaHabilitacionBP;

	/** The version 100 cons cotizacion. */
	@Value("${SERVICIO.VERSION.CNSMISCEL}")
	private String version110HabilitaccionBP;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The detalle CompraVentaDolaresUtil. */
	@Autowired
	private CompraVentaDolaresUtil compraVentaDolaresUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.compraventa.dao.CotizacionDolaresDAO#
	 * obtenerCotizacion(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 * java.lang.String)
	 */
	@Override
	public ConsultaCotizacionEntity obtenerCotizacion(Cliente cliente, Cuenta cuentaOrigen, String tipoOperacion, Boolean canalBancaPrivada)
			throws DAOException, BotonPanicoCompraventaException {
		IatxResponse iatxResponse = new IatxResponse();
		try {
			IatxRequest request = buildIatxRequest(cliente, cuentaOrigen, tipoOperacion);
			if (canalBancaPrivada) {
				request.getData().setCanalTipo(CompraVentaStringUtil.CANAL_BP);
				request.getData().setSubCanalTipo(CompraVentaStringUtil.SUB_CANAL_BP);
			}
			iatxResponse = iatxComm.exec(request);
			return buildResponse(iatxResponse);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e, iatxResponse.getErrorMessage());
		}

	}

	/**
	 * Genera el request al servicio consulta cotizacion de Iatx.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(Cliente cliente, Cuenta cuentaOrigen, String tipoOperacion) {
		IatxRequest iatxRequest = new IatxRequest(servicioConsultaCotizacion, version100ConsCotizacion);
		iatxRequest.setData(generarRequestData(cliente, cuentaOrigen, tipoOperacion));
		return iatxRequest;
	}

	/**
	 * Genera los datos para realizar el request a Iatx.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestData(Cliente cliente, Cuenta cuentaOrigen, String tipoOperacion) {
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(obtenerAplicacion(cuentaOrigen, tipoOperacion));
		requestData.addBodyValue(obtenerSucursal(cuentaOrigen));
		requestData.addBodyValue(obtenerCuentaDebitoOperante(cuentaOrigen));
		requestData.addBodyValue(obtenerOpcionTomaCotizacion(tipoOperacion));
		requestData.addBodyValue(obtenerTipoOperacion(tipoOperacion));
		requestData.addBodyValue(MONEDAS_QUINCE_CEROS);
		requestData.addBodyValue(MONEDAS_QUINCE_CEROS);
		requestData.addBodyValue(obtenerSegmento(cuentaOrigen));
		return requestData;
	}

	/**
	 * Construye el objeto respuesta en base a la respuesta de Iatx sin error.
	 * Realiza el manejo de excepciones segun el codigo de error retornado por
	 * el servicio de Iatx
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta cotizacion
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BotonPanicoCompraventaException
	 */
	private ConsultaCotizacionEntity buildResponse(IatxResponse iatxResponse)
			throws DAOException, BotonPanicoCompraventaException {
		ConsultaCotizacionEntity consultaCotizacion = new ConsultaCotizacionEntity();
		if (iatxResponse.getErrorCode() == CERO) {
			obtenerDatosDeLaCotizacion(iatxResponse, consultaCotizacion);
		} else if (iatxResponse.getErrorCode() == COD_ERROR_SUCURSAL_FUERA_HORARIO) {
			throw new DAOException();
		} else if (iatxResponse.getErrorCode() == COD_ERROR_SERVICIO_INACTIVO) {
			throw new DAOException(ERROR_SERVICIO_INACTIVO, String.valueOf(COD_ERROR_SERVICIO_INACTIVO));
		} else if (COD_ERROR_BOTON_PANICO == iatxResponse.getErrorCode()) {
			throw new BotonPanicoCompraventaException(iatxResponse.getIatxBody().get(3));
		} else if (iatxResponse.getErrorCode() != COD_ERROR_SUCURSAL_FUERA_HORARIO
				&& iatxResponse.getErrorCode() != COD_ERROR_CERO) {
			throw new DAOException();
		} else if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			throw new DAOException();
		}
		return consultaCotizacion;
	}

	/**
	 * Obtener datos de la cotizacion.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param consultaCotizacion
	 *            the consulta cotizacion
	 * @return the consulta cotizacion
	 */
	private ConsultaCotizacionEntity obtenerDatosDeLaCotizacion(IatxResponse iatxResponse,
			ConsultaCotizacionEntity consultaCotizacion) {
		consultaCotizacion.setCotizacionSalida(iatxResponse.getNextData().trim());
		consultaCotizacion.setImporteSalida(iatxResponse.getNextData().trim());
		consultaCotizacion.setSpredAplicado(iatxResponse.getNextData().trim());
		LOGGER.info("Una vez completo, se devuelve el objeto");
		return consultaCotizacion;
	}

	/**
	 * (cuenta origen en pesos) IDECLTSDO.tipo_cuenta= [01|02], enviar ACAH.
	 * IDECLTSDOtipo_cuenta=00 enviar ACTE (cuenta origen en dólares)
	 * IDECLTSDO.tipo_cuenta= [04|02] enviar ACAD. IDECLTSDO.tipo_cuenta=03
	 * enviar ACCD.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerAplicacion(Cuenta cuentaOrigen, String tipoOperacion) {
		return compraVentaDolaresUtil.obtenerAplicacion(cuentaOrigen, tipoOperacion);
	}

	/**
	 * Si Operación seleccionada es Compra Enviar V (Cliente Compra – Banco
	 * Vende). Si Operación seleccionada es Venta Enviar C (Cliente Vende –
	 * Banco Compra)
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTipoOperacion(String tipoOperacion) {
		return compraVentaDolaresUtil.obtenerTipoOperacion(tipoOperacion);
	}

	/**
	 * IDECLTSDO. Nro_cuenta_Producto Enviar los 12 digitos de la derecha.
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	private String obtenerCuentaDebitoOperante(Cuenta cuentaOrigen) {
		return compraVentaDolaresUtil.obtenerCuentaDebitoOperante(cuentaOrigen);
	}

	/**
	 * De la Cuenta Origen seleccionada en el selector o la cuenta Origen
	 * default: IDECLTSDO. Nro_Sucursal
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	private String obtenerSucursal(Cuenta cuentaOrigen) {
		return cuentaOrigen.getNroSucursal();
	}

	/**
	 * Si Operación seleccionada es Compra Enviar N. Si Operación seleccionada
	 * es Venta Enviar E *
	 *
	 * @param opcionOperacion
	 *            the opcion operacion
	 * @return the string
	 */
	private String obtenerOpcionTomaCotizacion(String opcionOperacion) {
		return compraVentaDolaresUtil.obtenerOpcionTomaCotizacion(opcionOperacion);
	}

	/**
	 * Enviar los ultimos 3 digitos de IDECLTSDO. Nro_Sucursal
	 *
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return the string
	 */
	private String obtenerSegmento(Cuenta cuentaOrigen) {
		return compraVentaDolaresUtil.obtenerSegmento(cuentaOrigen);
	}

}
