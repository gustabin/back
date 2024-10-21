/**
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EliminarOperacionDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EliminarOperacionEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class EliminarOperacionDAOImpl.
 *
 * @author luis.pedro.lopez
 */
@Component
public class EliminarOperacionDAOImpl extends IatxBaseDAO implements EliminarOperacionDAO {
	
	/** The Constant VERSION_BAJESTTRA_. */
	private static final String NOMBRE_SERVICIO = "BAJESTTRA_";

	/** The Constant SERVICIO_BAJESTTRA_. */
	private static final String VERSION_SERVICIO = "100";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DescuentoChequesDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EliminarOperacionDAO#eliminarOperacion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public EliminarOperacionEntity eliminarOperacion(Cliente cliente, String nroTramite) throws DAOException {
		EliminarOperacionEntity entity = new EliminarOperacionEntity();
		try {
			entity = ejecucionConsulta(nroTramite, cliente);
			if (entity == null) {
				throw new DAOException("Error de servicio");
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return entity;
	}

	/**
	 * Ejecucion consulta.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the eliminar operacion entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private EliminarOperacionEntity ejecucionConsulta(String nroTramite, Cliente cliente) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO, VERSION_SERVICIO);
		EliminarOperacionEntity operacion = null;
		try {
			IatxRequestData iatxRequestData = generarRequest(nroTramite, cliente);
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				operacion = processTrama(iatxResponse.getTrama(), EliminarOperacionEntity.class);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return operacion;
	}

	/**
	 * Generar request.
	 *
	 * @param nroTramite
	 *            the nro tramite
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequest(String nroTramite, Cliente cliente) {
		IatxRequestData request = new IatxRequestData(cliente);
		request.addBodyValue(nroTramite);
		request.addBodyValue(ISBANStringUtils.repeat(" ", 50));
		return request;
	}

}

