/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Interface TransferenciaSEI.
 */
@Path("/transferenciasPorAlias")
public interface TransferenciaPorAliasSEI {

	/** The Constant NUEVA_TRANSFERENCIA. */
	String SOLICITAR_NUEVA_TRANSFERENCIA = "solicitarNuevaTransferencia";

	/**
	 * Retorna una nueva transferencia view.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param mc
	 *            the mc
	 * @return the nueva transferencia
	 */
	@POST
	@Path(SOLICITAR_NUEVA_TRANSFERENCIA)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferencia,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

}
