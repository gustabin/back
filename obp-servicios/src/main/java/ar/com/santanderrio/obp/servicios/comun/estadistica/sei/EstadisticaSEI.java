/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.view.EstadisticaView;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Esta interface define la operación en común para el grabado de estadisticas
 * de acceso a comprobantes.
 *
 * @author manuel.vargas
 * @author emilio.watemberg.
 * @param <T>
 *            the generic type
 * @see {@link EstadisticaView}
 * @since Aug 10, 2017.
 */
public abstract class EstadisticaSEI<T> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EstadisticaSEI.class);

	/** The Constant GRABAR_ESTADISTICA_ENDPOINT. */
	private static final String GRABAR_ESTADISTICA_ENDPOINT = "/grabarEstadisticas";

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/**
	 * Este metodo permite obtener la {@link Estadistica} cargada en base al
	 * objeto pasado como parametro.
	 *
	 * @param <T>
	 *            the generic type
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@POST
	@Path(GRABAR_ESTADISTICA_ENDPOINT)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public <T extends EstadisticaView> Respuesta<T> grabarEstadistica(T view) {
		LOGGER.info("invocando a grabar a estadisticas");
		estadisticaManager.add(view.cargarEstadistica());
		return respuestaFactory.crearRespuestaOk(view);
	}

}
