/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.SolicitudTodoPagoDTO;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Interface TodoPagoBO.
 */
public interface TodoPagoBO {

    /**
     * Adhesion TodoPago.
     *
     * @param view
     *            the view
     * @return the respuesta
     * @throws DAOException
     *             the DAO exception
     */
    Respuesta<SolicitudTodoPagoDTO> adhesionTodoPago(TodoPagoView view) throws DAOException;

    /**
     * Enviar mail.
     *
     * @param adhesionTodoPagoEnvioMailDTO
     *            the adhesion todopago envio mail DTO
     * @return the respuesta
     */
    Respuesta<Boolean> enviarMail(AdhesionTodoPagoEnvioMailDTO adhesionTodoPagoEnvioMailDTO);

    /**
     * Alta adhesion.
     *
     * @param inDto
     *            the in dto
     * @return the respuesta
     */
    Respuesta<ResultadoTransaccion> altaAdhesion(AdhesionTodoPagoInDTO inDto);

    /**
	 * Obtener el cuil|cuit|cdi de un nup en ese orden.
	 *
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
    String obtenerCuil() throws DAOException;

    /**
     * Generar comprobante.
     *
     * @param view
     *            the view request
     * @return the respuesta
     */
    Respuesta<Reporte> generarComprobante(ComprobanteAdhesionTodoPagoView view);
}