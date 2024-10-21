/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.dao.PrestamoCuotaPagaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.DatoClienteCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;

/**
 * The Class PrestamoCuotaPagaDAOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class PrestamoCuotaPagaDAOImpl extends IatxBaseDAO implements PrestamoCuotaPagaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoCuotaPagaDAOImpl.class);

	/** The Constant SERVICIO_CNSPMOHIST. */
	private static final String SERVICIO_CNSPMOHIST = "CNSPMOHIST";

	/** The Constant VERSION_120. */
	private static final String VERSION_120 = "120";

	/** The Constant COD_ERROR_VALIDATE. */
	private static final String COD_ERROR_VALIDATE = "0001";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant CONSULTA_OK. */
	private static final String CONSULTA_OK = "La consulta al DAO se realizo exitosamente: {}.";

	/** The Constant MENSAJE_ERROR_SERVICIO. */
	private static final String MENSAJE_ERROR_SERVICIO = "Error del servicio iatx con codigo extendido de error {}.";

	/** The Constant SERVICIO_CNINTERVI. */
	private static final String SERVICIO_CNINTERVI = "CNINTERVI";

	/** The Constant MENSAJE_ERROR_VALIDACION. */
	private static final String MENSAJE_ERROR_VALIDACION = "Error en la validacion de las expresiones regulares con codigo extendido de error {}.";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/**
	 * Obtiene las cuotas pagas de un prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the consulta cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SinCuotasPagasException
	 *             the sin cuotas pagas exception
	 */
	@Override
	public ConsultaCuotaPagaOutEntity obtenerCuotasPagasPrestamo(Cliente cliente, ConsultaCuotaPagaInEntity inEntity)
			throws DAOException, SinCuotasPagasException {
		try {
			ConsultaCuotaPagaOutEntity outEntity = ejecutarConsultaServicioCNSPMOHIST(cliente, inEntity);
			if (outEntity.getCantidadOcurrencias() == 0) {
				throw new SinCuotasPagasException();
			}
			LOGGER.info(CONSULTA_OK, outEntity);
			return outEntity;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene los datos del cliente de las cuotas pagas del prestamo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the dato cliente cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public DatoClienteCuotaPagaOutEntity obtenerDatosClienteCuotasPagasPrestamo(Cliente cliente,
			DatoClienteCuotaPagaInEntity inEntity) throws DAOException {
		try {
			DatoClienteCuotaPagaOutEntity outEntity = ejecutarConsultaServicioCNINTERVI(cliente, inEntity);
			LOGGER.info(CONSULTA_OK, outEntity);
			return outEntity;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Ejecuta la consulta al servicio CNINTERVI.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the dato cliente cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private DatoClienteCuotaPagaOutEntity ejecutarConsultaServicioCNINTERVI(Cliente cliente,
			DatoClienteCuotaPagaInEntity inEntity) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNINTERVI, VERSION_120);
		while (ValidationEntity.validate(inEntity)) {
			try {
				iatxRequest.setData(generarRequestDataCNINTERVI(cliente, inEntity));
				IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
				int errorCode = iatxResponse.getErrorCode();
				if (OK_CODIGO_RETORNO == errorCode) {
					return processTrama(iatxResponse.getTrama(), DatoClienteCuotaPagaOutEntity.class);
				} else {
					throw new DAOException(MENSAJE_ERROR_SERVICIO, String.valueOf(errorCode));
				}
			} catch (IatxException e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}

		}
		throw new DAOException(MENSAJE_ERROR_VALIDACION, COD_ERROR_VALIDATE);
	}

	/**
	 * Ejecuta la consulta al servicio CNSPMOHIST.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the consulta cuota paga out entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SinCuotasPagasException
	 *             the sin cuotas pagas exception
	 */
	private ConsultaCuotaPagaOutEntity ejecutarConsultaServicioCNSPMOHIST(Cliente cliente,
			ConsultaCuotaPagaInEntity inEntity) throws DAOException, SinCuotasPagasException {
		IatxRequest iatxRequest = new IatxRequest(SERVICIO_CNSPMOHIST, VERSION_120);
		while (ValidationEntity.validate(inEntity)) {
			ConsultaCuotaPagaOutEntity cuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
			try {
				iatxRequest.setData(generarRequestDataCNSPMOHIST(cliente, inEntity));
				IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
				int errorCode = iatxResponse.getErrorCode();
				if (OK_CODIGO_RETORNO == errorCode) {
					if (iatxResponse.getIatxBody().size() == 1) 
						throw new SinCuotasPagasException();
					else
						cuotaPagaOutEntity = processTrama(iatxResponse.getTrama(), ConsultaCuotaPagaOutEntity.class);
				} else {
					throw new DAOException(MENSAJE_ERROR_SERVICIO, String.valueOf(errorCode));
				}
			} catch (IatxException e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
			return cuotaPagaOutEntity;
		}
		throw new DAOException(MENSAJE_ERROR_VALIDACION, COD_ERROR_VALIDATE);
	}

	/**
	 * Genera el request data para el servicio CNSPMOHIST.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataCNSPMOHIST(Cliente cliente, ConsultaCuotaPagaInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(inEntity.getNumRegistros());
		iatxRequestData.addBodyValue(inEntity.getOficina());
		iatxRequestData.addBodyValue(inEntity.getCuenta());
		iatxRequestData.addBodyValue(inEntity.getCodEvento());
		iatxRequestData.addBodyValue(inEntity.getFecInicio());
		iatxRequestData.addBodyValue(inEntity.getFecFin());
		iatxRequestData.addBodyValue(inEntity.getTipomov());
		iatxRequestData.addBodyValue(inEntity.getTimestamp());
		iatxRequestData.addBodyValue(inEntity.getCodconli());
		iatxRequestData.addBodyValue(inEntity.getNumSecuencia());
		return iatxRequestData;
	}

	/**
	 * Genera el request data para el servicio CNINTERVI.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inEntity
	 *            the in entity
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataCNINTERVI(Cliente cliente, DatoClienteCuotaPagaInEntity inEntity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(inEntity.getAplicacion());
		iatxRequestData.addBodyValue(inEntity.getSucursal());
		iatxRequestData.addBodyValue(inEntity.getCodigoProducto());
		iatxRequestData.addBodyValue(inEntity.getCodigoSubproducto());
		iatxRequestData.addBodyValue(inEntity.getNumeroContrato());
		iatxRequestData.addBodyValue(inEntity.getCalidadParticipacion());
		iatxRequestData.addBodyValue(inEntity.getCantidadParticipantes());
		return iatxRequestData;
	}
}
