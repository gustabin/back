/**
 * 
 */
package ar.com.santanderrio.obp.servicios.exception;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.filter.FilterConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.IntegracionObpTbancoException;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.obp.manager.ObpManager;

/**
 * The Class IntegracionObpTbancoMapper.
 *
 * @author sergio.e.goldentair
 */
@Provider
public class IntegracionObpTbancoMapper implements ExceptionMapper<IntegracionObpTbancoException> {
    /** The Constant MENSAJE_ERROR. */
    public final static String MENSAJE_ERROR_TBANCO_OBP = "Ocurrió un error y no podemos procesar tu solicitud en este momento.";
    /** The Constant Estadistica TBANCO-OBP. */
    public final static String ESTADISTICA_OBP = "13684";

    /** The message context. */
    @Context
    private MessageContext messageContext;

    /** The cliente service. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Response toResponse(IntegracionObpTbancoException exception) {
        ObpManager obpManager = ContextHolder.getContext().getBean(ObpManager.class);
        Respuesta respuestaWarning = obpManager.obtenerTokenEncriptado(exception.getResumenCliente());
        respuestaWarning.setSkipLog(Boolean.TRUE);
        if (!EstadoRespuesta.WARNING.equals(respuestaWarning.getEstadoRespuesta())) {
            respuestaWarning = new Respuesta<String>();
            respuestaWarning.setEstadoRespuesta(EstadoRespuesta.ERROR);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setMensaje(MENSAJE_ERROR_TBANCO_OBP);
            item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuestaWarning.add(item);
        }
        HttpSession session = messageContext.getHttpServletRequest().getSession(false);
        subirFaltantesEnSession(session, exception.getResumenCliente(), exception.getCredencialCliente());
        EstadisticaManager estadisticaManager = ContextHolder.getContext().getBean(EstadisticaManager.class);
        estadisticaManager.add(ESTADISTICA_OBP, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        session.setAttribute(FilterConstants.REQUIERE_INVALIDAR_SESSION, Boolean.TRUE);
        return Response.status(Response.Status.OK).entity(respuestaWarning)
                .header("Content-Type", MediaType.APPLICATION_JSON).build();
    }

    /**
	 * Subir a session datos requeridos para poder escribir la estadistica del
	 * salto a obp.
	 *
	 * @param session
	 *            the session
	 * @param cliente
	 *            the cliente
	 * @param credencialCliente
	 *            the credencial cliente
	 */
    private void subirFaltantesEnSession(HttpSession session, ResumenCliente cliente,
            CredencialCliente credencialCliente) {
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setLogFile((String) session.getAttribute("logFileName"));
        registroSesion.setPid((String) session.getAttribute("pid"));
        registroSesion.setIp(credencialCliente.getIp());
        if (cliente != null) {
            if (cliente.getValorSinonimo() != null) {
                registroSesion.setSinonimo(cliente.getValorSinonimo());
            } else {
                registroSesion.setSinonimo(cliente.getFechaNacimiento());
            }
            registroSesion.setNup(cliente.getNup());
            registroSesion.setFechaNacimiento(cliente.getFechaNacimiento());
            registroSesion.setDni(cliente.getDni());

        } else {
            registroSesion.setDni(credencialCliente.getDniOri());
        }
        registroSesion.setClienteSinonimo(Boolean.FALSE);

        registroSesion.setHostName(credencialCliente.getHostName());
        registroSesion.setDispositivo(credencialCliente.getDispositivo());
        registroSesion.setNavegador(credencialCliente.getNavegador());
        sesionParametros.setRegistroSession(registroSesion);
        sesionCliente.setCliente(new Cliente(cliente));
    }

}
