/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class PreFormalizacionPrestamoDAOImpl.
 */
@Component
public class PreFormalizacionPrestamoDAOImpl implements PreFormalizacionPrestamoDAO {

	public static final String ID_ERROR_CODE_PREFORMALIZACION = "E_CNSPMOFORM";

	/** The Constant SERVICIO_CNSPMOFORM. */
	private static final String SERVICIO_CNSPMOFORM = "CNSPMOFORM";

	/** The Constant VERSION_140. */
	private static final String VERSION_140 = "140";

	/** The Constant NRO_SUCURSAL_LENGTH. */
	private static final int NRO_SUCURSAL_LENGTH = 4;

	/** The Constant NRO_CUENTA_LENGTH. */
	private static final int NRO_CUENTA_LENGTH = 12;

	/** The Constant DAT_CONTRATO_INDEX. */
	private static final int DAT_CONTRATO_INDEX = 9;

	/** The Constant NRO_SUCURSAL_INDEX. */
	private static final int NRO_SUCURSAL_INDEX = 2;

	/** The Constant NRO_CUENTA_INDEX. */
	private static final int NRO_CUENTA_INDEX = 4;

	/** The Constant TIPO_CUENTA_INDEX. */
	private static final int TIPO_CUENTA_INDEX = 3;

	/** The Constant PLAZO_INDEX_START. */
	private static final int PLAZO_INDEX_START = 25;

	/** The Constant PLAZO_INDEX_END. */
	private static final int PLAZO_INDEX_END = 28;

	/** The Constant MOTIVO_INDEX_START. */
	private static final int MOTIVO_INDEX_START = 19;

	/** The Constant MOTIVO_INDEX_END. */
	private static final int MOTIVO_INDEX_END = 24;

	/** The Constant MONTO_A_PAGAR_INDEX. */
	private static final int MONTO_A_PAGAR_INDEX = 14;

	/** The Constant FECHAS_INICIO_INDEX. */
	private static final int FECHAS_INICIO_INDEX = 22;

	/** The Constant IMPCONCE_INDEX. */
	private static final int IMPCONCE_INDEX = 15;

	/** The Constant CFT_INDEX. */
	private static final int CFT_INDEX = 164;
	
	/** The Constant CFT_SIMP_INDEX. */
	private static final int CFTSIMP_INDEX = 165;
	
	/** The Constant UNIDAD_EXPOSICION_INDEX. */
	private static final int UNIDAD_EXPOSICION_INDEX = 166;
	
	/** The Constant COTIZACION_UNIDAD_EXP_INDEX. */
	private static final int COTIZACION_UNIDAD_EXP_INDEX = 167;
	
	/** The Constant FECHA_COTIZACION_INDEX. */
	private static final int FECHA_COTIZACION_INDEX = 168;
	
	/** The Constant IMP_SOL_VISUALIZAR_INDEX. */
	private static final int IMP_SOL_A_VISUALIZAR_INDEX = 169;

	private static final int NUMERO_PROPUESTA = 78;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Obtiene la PreFormalizacion de un prestamo.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public PreFormalizacion obtenerPreFormalizacion(Prestamo prestamo) throws DAOException {
		return obtenerPreFormalizacion(prestamo.getCuenta());
	}

	/**
	 * Obtiene la PreFormalizacion de un prestamo a partir de la cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public PreFormalizacion obtenerPreFormalizacion(Cuenta cuenta) throws DAOException {
		return obtenerPreFormalizacion(cuenta.getCliente(), cuenta.getNroSucursal(), cuenta.getNroCuentaProducto());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO#
	 * obtenerPreFormalizacion(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public PreFormalizacion obtenerPreFormalizacion(Cliente cliente, String nroSucursal, String nroCuentaProducto)
			throws DAOException {
		IatxRequest req = new IatxRequest(SERVICIO_CNSPMOFORM, VERSION_140);
		IatxRequestData reqData = new IatxRequestData(cliente);

		// agrego al area de datos
		reqData.addBodyValue(StringUtils.leftPad(Long.valueOf(nroSucursal).toString(), NRO_SUCURSAL_LENGTH, '0'));
		reqData.addBodyValue(StringUtils.leftPad(Long.valueOf(nroCuentaProducto).toString(), NRO_CUENTA_LENGTH, '0'));

		req.setData(reqData);

		IatxResponse resu;
		try {
			resu = iatxComm.exec(req);
		} catch (IatxException e) {
			throw new DAOException(e);
		}

		if (resu.getErrorCode() != 0) {
			throw new DAOException(resu.getMensaje(), ID_ERROR_CODE_PREFORMALIZACION);
		}

		String datContrato = resu.getData(DAT_CONTRATO_INDEX);
		String plazo = datContrato.substring(PLAZO_INDEX_START, PLAZO_INDEX_END);
		String codigoDestino = datContrato.substring(MOTIVO_INDEX_START, MOTIVO_INDEX_END);
		PreFormalizacion preFormalizacion = new PreFormalizacion();
		preFormalizacion.setCodigoDestinoPrestamo(codigoDestino);
		preFormalizacion.setPlazo(String.valueOf(Integer.parseInt(plazo)));
		preFormalizacion.getPrestamoDebitoAdherido().setNroSucursal(resu.getData(NRO_SUCURSAL_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setNumero(resu.getData(NRO_CUENTA_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setTipo(resu.getData(TIPO_CUENTA_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar(resu.getData(MONTO_A_PAGAR_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setFechaInicio(resu.getData(FECHAS_INICIO_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setImpconce(resu.getData(IMPCONCE_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setCft(ISBANStringUtils.stringToBigDecimal(resu.getData(CFT_INDEX), 3, 6, false));
		preFormalizacion.getPrestamoDebitoAdherido().setCftsimp
			(ISBANStringUtils.stringToBigDecimal(resu.getData(CFTSIMP_INDEX), 3, 6, false));
		preFormalizacion.getPrestamoDebitoAdherido().setUnidadExposicion(resu.getData(UNIDAD_EXPOSICION_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setCotizacionUnidadExp
		(ISBANStringUtils.stringToBigDecimal(resu.getData(COTIZACION_UNIDAD_EXP_INDEX), 6, 5, false));
		preFormalizacion.getPrestamoDebitoAdherido().setFechaCotizacion(resu.getData(FECHA_COTIZACION_INDEX));
		preFormalizacion.getPrestamoDebitoAdherido().setImpSolAVisualizar
			(ISBANStringUtils.stringToBigDecimal(resu.getData(IMP_SOL_A_VISUALIZAR_INDEX), 13, 4, false));
		preFormalizacion.getPrestamoDebitoAdherido().setNumeroPropuesta(resu.getData(NUMERO_PROPUESTA));
		return preFormalizacion;
	}

}