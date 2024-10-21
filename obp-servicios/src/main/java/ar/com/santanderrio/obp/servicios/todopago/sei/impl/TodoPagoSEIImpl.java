/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.todopago.manager.TodoPagoManager;
import ar.com.santanderrio.obp.servicios.todopago.sei.TodoPagoSEI;
import ar.com.santanderrio.obp.servicios.todopago.web.view.AdhesionRespuestaView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoSEIImpl.
 */
@Component("TodoPagoSEI")
public class TodoPagoSEIImpl implements TodoPagoSEI {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoPagoSEIImpl.class);

    /** The Constant MENSAJE_RESPUESTA. */
    private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The TodoPago manager. */
	@Autowired
	@Qualifier("todoPagoManager")
	private TodoPagoManager todoPagoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.todopago.sei.TodoPagoSEI#
	 * descargaComprobanteAdhesion()
	 */
	public Respuesta<ReporteView> descargaComprobanteAdhesion() {
		Respuesta<ReporteView> respuesta = todoPagoManager.descargaComprobanteAdhesion();
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.todopago.sei.TodoPagoSEI#
	 * confirmarAdhesion(ar.com.santanderrio.obp.servicios.todopago.web.view.
	 * TodoPagoView)
	 */
	@Override
	public Respuesta<AdhesionRespuestaView> confirmarAdhesion(TodoPagoView viewRequest) throws DAOException {
		Respuesta<AdhesionRespuestaView> respuesta = todoPagoManager.confirmarAdhesion(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.todopago.sei.TodoPagoSEI#
	 * adhesionTodoPago(ar.com.santanderrio.obp.servicios.todopago.web.view.
	 * TodoPagoView)
	 */
	@Override
	public Respuesta<TodoPagoView> adhesionTodoPago(TodoPagoView viewRequest) throws DAOException {
		Respuesta<TodoPagoView> respuesta = todoPagoManager.adhesionTodoPago(viewRequest);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta);
		return respuesta;
	}
}
