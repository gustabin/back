/**
 * 
 */
package ar.com.santanderrio.obp.servicios.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;

/**
 * Capturar los metodos que esten anotados con CustomPreAuthorize y no
 * satisfagan los permisos en la base. Lanza 403.
 * 
 * @author sergio.e.goldentair
 *
 */
@Provider
public class PermisoMapper implements ExceptionMapper<AccessDeniedException> {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PermisoMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(AccessDeniedException exception) {
		LOGGER.info("La funcionalidad solicitada no tiene permisos para continuar.", exception);
		return Response.status(Response.Status.FORBIDDEN).build();
	}

}
