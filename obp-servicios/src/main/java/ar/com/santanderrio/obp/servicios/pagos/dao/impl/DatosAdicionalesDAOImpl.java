/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.DatosAdicionalesDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaDAOImpl;

/**
 * The Class DatosAdicionalesDAOImpl.
 *
 * @author B039636
 */
@Component
public class DatosAdicionalesDAOImpl implements DatosAdicionalesDAO {

	/** The Constant ERROR_AL_INVOCAR_A_ALTPAUADIC. */
	private static final String ERROR_AL_INVOCAR_A_ALTPAUADIC = "Error al invocar a ALTPAUADIC";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** El servicio ALTPAUADIC. */
	private static final String SERVICIO_DATOS = "ALTPAUADIC";

	/** La version de ALTPAUADIC. */
	private static final String VERSION_DATOS = "100";

	/** The Constant ACCION_ALTA. */
	private static final String ACCION_ALTA = "A";

	/** The Constant EMPRESA_LARGO. */
	private static final int EMPRESA_LARGO = 4;

	/** The Constant IDEN_LARGO. */
	private static final int IDEN_LARGO = 19;

	/** The Constant MENSAJE_LARGO. */
	private static final int MENSAJE_LARGO = 20;

	/** The Constant ACCION_MODIFICAR. */
	private static final String ACCION_MODIFICAR = "M";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DatosAdicionalesDAO#
	 * altaInformacionAdicional(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional)
	 */
	@Override
	public Respuesta<Boolean> altaInformacionAdicional(Cliente cliente, InformacionAdicional info) throws DAOException {
		info.setAccion(ACCION_ALTA);
		return ejecutar(cliente, info);
	}

	/**
	 * Invocar servicio.
	 *
	 * @param cliente
	 *            the cliente
	 * @param info
	 *            the info
	 * @param request
	 *            the request
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<Boolean> invocarServicio(Cliente cliente, InformacionAdicional info, IatxRequest request)
			throws DAOException {
		Respuesta<Boolean> resp = new Respuesta<Boolean>();
		resp.setRespuesta(Boolean.FALSE);
		IatxRequestData requestData = new IatxRequestData(cliente);

		requestData.addBodyValue(StringUtils.leftPad(info.getCodigoEmpresa().trim(), EMPRESA_LARGO));
		requestData.addBodyValue(StringUtils.rightPad(info.getIdentificacion().trim(), IDEN_LARGO));
		requestData.addBodyValue(info.getAccion());
		requestData.addBodyValue(StringUtils.rightPad(info.getMensaje().trim(), MENSAJE_LARGO));

		request.setData(requestData);

		IatxResponse iatxResponse;
		try {
			iatxResponse = iatxComm.exec(request);
			int codigoDeRetorno = iatxResponse.getErrorCode();
			if (codigoDeRetorno == 0) {
				resp.setRespuesta(Boolean.TRUE);
			} else {
				throw new DAOException();
			}
		} catch (IatxException e) {
			LOGGER.error(ERROR_AL_INVOCAR_A_ALTPAUADIC, e);
			throw new DAOException(e, ERROR_AL_INVOCAR_A_ALTPAUADIC);
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.dao.DatosAdicionalesDAO#
	 * modificacionInformacionAdicional(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional)
	 */
	@Override
	public Respuesta<Boolean> modificacionInformacionAdicional(Cliente cliente, InformacionAdicional info)
			throws DAOException {
		info.setAccion(ACCION_MODIFICAR);
		return ejecutar(cliente, info);
	}

	/**
	 * Ejecutar.
	 *
	 * @param cliente
	 *            the cliente
	 * @param info
	 *            the info
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<Boolean> ejecutar(Cliente cliente, InformacionAdicional info) throws DAOException {
		IatxRequest request = new IatxRequest(SERVICIO_DATOS, VERSION_DATOS);
		return invocarServicio(cliente, info, request);
	}
}
