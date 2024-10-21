/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

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
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinientesOutEntity;

/**
 * The Class IntervinientesDAOImpl.
 */
@Component
public class IntervinientesDAOImpl extends IatxBaseDAO implements IntervinientesDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(IntervinientesDAOImpl.class);

	/** The Constant NOMBRE_INDEX. */
	private static final int NOMBRE_INDEX = 21;

	/** The Constant APELLIDO_INDEX. */
	private static final int APELLIDO_INDEX = 20;

	/** The Constant TIPO_INSCRIPCION_INDEX. */
	private static final int TIPO_INSCRIPCION_INDEX = 25;

	/** The Constant CUIT_CUIL_INDEX. */
	private static final int CUIT_CUIL_INDEX = 26;

	/** The Constant CODIGO_APLICACION. */
	private static final String CODIGO_APLICACION = "    ";

	/** The Constant CALIDAD_DE_PARTICIPACION. */
	private static final String CALIDAD_DE_PARTICIPACION = "  ";

	/** The Constant CANTIDAD_DE_TODOS_LOS_FIRMANTES. */
	private static final String CANTIDAD_DE_TODOS_LOS_FIRMANTES = "0";

	/** The Constant CONSTANTE_DOBLE_CERO. */
	private static final String CONSTANTE_DOBLE_CERO = "00";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The servicio intervinientes. */
	@Value("${SERVICIO.PREFIJO.CNSINTERVI}")
	private String servicioIntervinientes;

	/** The version intervinientes. */
	@Value("${SERVICIO.VERSION.CNSINTERVI}")
	private String versionIntervinientes;

	/** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO#
	 * obtenerIntervinienteTitular(ar.com.santanderrio.obp.servicios.cuentas.
	 * entities.Cuenta)
	 */
	@Override
	public Interviniente obtenerIntervinienteTitular(Cliente cliente, Cuenta cuenta) throws DAOException {

		IatxRequest req = new IatxRequest(servicioIntervinientes, versionIntervinientes);
		IatxRequestData reqData = new IatxRequestData(cliente);

		// agrego al area de datos
		reqData.addBodyValue(cuenta.getCodigoAplicacion());
		reqData.addBodyValue(StringUtils.leftPad(ISBANStringUtils.eliminarCeros(cuenta.getNroSucursal()), 4, "0"));
		reqData.addBodyValue("00");
		reqData.addBodyValue("0000");
		reqData.addBodyValue(
				StringUtils.leftPad(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()), 12, "0"));
		reqData.addBodyValue("TI");
		reqData.addBodyValue("0");
		reqData.addBodyValue("  ");
		reqData.addBodyValue("  ");
		reqData.addBodyValue("  ");
		reqData.addBodyValue("  ");

		reqData.setStatFlag("N");

		req.setData(reqData);

		IatxResponse resu;
		try {
			resu = iatxComm.exec(req);
		} catch (IatxException e) {
			throw new DAOException(e);
		}

		if (resu.getErrorCode() != 0) {
			throw new DAOException(resu.getMensaje());
		}

		Interviniente titular = new Interviniente();

		String nombre = resu.getData(NOMBRE_INDEX).trim();
		String apellido = resu.getData(APELLIDO_INDEX).trim();

		String tipoInscripcion = tipoInscripcion(resu.getData(TIPO_INSCRIPCION_INDEX));
		String cuitcuil = resu.getData(CUIT_CUIL_INDEX);

		titular.setApellido(apellido);
		titular.setCuitcuil(cuitcuil);
		titular.setNombre(nombre);
		titular.setTipoInscripcion(tipoInscripcion);

		return titular;
	}
	
	/**
	 * Tipo inscripcion.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	public String tipoInscripcion(String s) throws DAOException {
		try {
			if (propertyMap.get("CREDITOS.TIPOINSCRIPCION." + s) != null
					&& !"".equals(propertyMap.get("CREDITOS.TIPOINSCRIPCION." + s))) {
				return propertyMap.get("CREDITOS.TIPOINSCRIPCION." + s);
			}
		} catch (Exception e) {
			LOGGER.error("Error al obtener el tipo de inscripcion", e);
		}
		return s;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio CNSINTERVI.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSINTERVI(Cuenta cuenta) {
		IatxRequestData reqData = new IatxRequestData(cuenta.getCliente());
		reqData.addBodyValue(CODIGO_APLICACION);
		reqData.addBodyValue(cuenta.getNroSucursal());
		reqData.addBodyValue(cuenta.getProductoAltair());
		reqData.addBodyValue(cuenta.getSubproductoAltair());
		reqData.addBodyValue(
				StringUtils.leftPad(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()), 12, "0"));
		reqData.addBodyValue(CALIDAD_DE_PARTICIPACION);
		reqData.addBodyValue(CANTIDAD_DE_TODOS_LOS_FIRMANTES);
		reqData.addBodyValue(CONSTANTE_DOBLE_CERO);
		reqData.addBodyValue(CONSTANTE_DOBLE_CERO);
		reqData.addBodyValue(CONSTANTE_DOBLE_CERO);
		reqData.addBodyValue(CONSTANTE_DOBLE_CERO);
		return reqData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IntervinientesOutEntity obtenerIntervinientes(Cuenta cuenta) throws DAOException {
		String servicio = "CNSINTERVI";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		IntervinientesOutEntity intervinientesOutEntity = new IntervinientesOutEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSINTERVI(cuenta);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				intervinientesOutEntity = processTrama(rta.getTrama(), IntervinientesOutEntity.class);
			} else {
				throw new DAOException(rta.getErrorMessage(), String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return intervinientesOutEntity;
	}

}
