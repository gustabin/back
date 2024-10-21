/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.pagos.bo.DatosAdicionalesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.InformacionAdicional;
import ar.com.santanderrio.obp.servicios.pagos.service.DatosAdicionalesService;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;

/**
 * Informacion adicional para servicios de PagoMiscuentas DTF 16416.
 *
 * @author B041299
 */
@Component
class DatosAdicionalesServiceImpl implements DatosAdicionalesService {

	/** The datos adicionales BO. */
	@Autowired
	private DatosAdicionalesBO datosAdicionalesBO;

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagosService.class);

	/** The Constant DESCRIPCION_LOG. */
	private static final String DESCRIPCION_LOG = "Descripci�n: {}.";

	/** The Constant ERROR_LOG. */
	private static final String ERROR_LOG = "Ha ocurrido un error en servicio {}. " + DESCRIPCION_LOG;

	/** The Constant INVOCACION_INICIO_LOG. */
	private static final String INVOCACION_INICIO_LOG = "Invocando servicio {}.";

	/** The Constant INVOCACION_RESPUESTA_LOG. */
	private static final String INVOCACION_RESPUESTA_LOG = "Resultado de invocaci�n servicio {}: {}.";

	/** The Constant DETALLE_DE_PAGOS_Y_DEUDA. */
	private static final Object DETALLE_DE_PAGOS_Y_DEUDA = "DatosAdicionalesBO#altaInformacionAdicional()";

	/**
	 * Alta informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	@Override
	public Respuesta<Boolean> altaInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws ServiceException {
		try {
			// TODO: LOGS messages.
			LOGGER.info(INVOCACION_INICIO_LOG, DETALLE_DE_PAGOS_Y_DEUDA);
			Respuesta<Boolean> detalles = datosAdicionalesBO.altaInformacionAdicional(cliente, infoAdicional);
			LOGGER.info(INVOCACION_RESPUESTA_LOG, DETALLE_DE_PAGOS_Y_DEUDA, detalles.getEstadoRespuesta());
			return detalles;

		} catch (BusinessException e) {
			LOGGER.error(ERROR_LOG, DETALLE_DE_PAGOS_Y_DEUDA, e.getMessage(), e);
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	/**
	 * Modificacion informacion adicional.
	 *
	 * @param cliente
	 *            the cliente
	 * @param infoAdicional
	 *            the info adicional
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	@Override
	public Respuesta<Boolean> modificacionInformacionAdicional(Cliente cliente, InformacionAdicional infoAdicional)
			throws ServiceException {
		try {
			return datosAdicionalesBO.modificacionInformacionAdicional(cliente, infoAdicional);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

}
