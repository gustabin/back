package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;

// TODO: Auto-generated Javadoc
/**
 * The Interface DevolucionDebitoAutomaticoBO.
 */
public interface DevolucionDebitoAutomaticoBO {

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param cliente the cliente
	 * @param cuit the cuit
	 * @param servicio the servicio
	 * @param partida the partida
	 * @param idCliente the id cliente
	 * @param fechaVencimiento the fecha vencimiento
	 * @param empresa the empresa
	 * @param importe the importe
	 * @return the respuesta
	 */
	Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(Cliente cliente, String cuit, String servicio,
			String partida, String idCliente, String fechaVencimiento, String empresa, String importe);	
	
	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobantePDF(DatosComprobanteDevolucionDA datosComprobante);
	
	
}
