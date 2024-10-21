/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.bo.ClienteCitiBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

/**
 * The Class ClienteCitiMananagerImpl.
 */
@Component
public class ClienteCitiMananagerImpl implements ClienteCitiMananager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteCitiMananagerImpl.class);

	/** The cliente citi BO. */
	@Autowired
	private ClienteCitiBO clienteCitiBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.citi.manager.ClienteCitiMananager#
	 * agregarNUPSesion()
	 */
	@Override
	public void marcarClienteCiti() {

		if (sesionCliente.getCliente().getIsExCiti() == null) {

			LOGGER.info("Se marca al cliente");

			Respuesta<Boolean> resp = clienteCitiBO.isExCiti(sesionCliente.getCliente().getNup());

			if (EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())) {

				sesionCliente.getCliente().setIsExCiti(resp.getRespuesta());

				estadisticaManager.add(EstadisticasConstants.CONSULTA_STORED_PROCEDURE_CLIENTES_EXCITI,
				        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			} else {

				estadisticaManager.add(EstadisticasConstants.CONSULTA_STORED_PROCEDURE_CLIENTES_EXCITI,
				        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

			}

		}
	}

}
