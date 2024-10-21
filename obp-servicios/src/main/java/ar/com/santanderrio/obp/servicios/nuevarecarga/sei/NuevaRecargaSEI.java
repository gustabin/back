/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;

/**
 * The Interface NuevaRecargaSEI.
 *
 * @author florencia.n.martinez
 */
@Path("/nuevaRecarga")
public interface NuevaRecargaSEI {

	/**
	 * Obtiene la confirmacion de la nueva recarga.
	 *
	 * @param datosConfirmacionRecarga
	 *            the datos confirmacion recarga
	 * @return the respuesta
	 */
	@POST
	@Path("/recargar")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionRecargaView> obtenerConfirmacionNuevaRecarga(
			ConfirmacionRecargaView datosConfirmacionRecarga);

	/**
	 * Se graba estadisticas de visualizacion del comprobante.
	 *
	 * @return true, if successful
	 */
	@POST
	@Path("/estadisticaVerComprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	boolean estadisticaVerComprobante();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF();
	
	/**
	 * Continuar Pago - Carga en sesion el hash del pago.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @return the respuesta
	 */
	@POST
	@Path("/continuarPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> continuarPago(ConfirmacionRecargaView nuevoPagoView);

    @POST
    @Path("/grabarEstadisticaCargaDatosRecargaCelulares")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Boolean grabarEstadisticaCargaDatosRecargaCelulares();
    
    @POST
    @Path("/grabarEstadisticaConfirmacionRecargaCelulares")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Boolean grabarEstadisticaConfirmacionRecargaCelulares();
    
	@POST
	@Path("/agenda/eliminarCelular")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Respuesta<Void> eliminarCelular(CelularView nuevoNumero);
	
	@POST
	@Path("/agenda/actualizarAliasCelular")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta<Void> actualizarAliasCelular(CelularView nuevoNumero);

	@POST
	@Path("/agenda/obtenerCelulares")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Respuesta<List <CelularView>> obtenerCelulares();

	@POST
    @Path("/grabarEstadisticaIngresoConfiguracionDesdeAgenda")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Boolean grabarEstadisticaIngresoConfiguracionDesdeAgenda();
}
