package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.dao;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.SolicitudDevolucionDebitoOutEntity;

/**
 * The Interface DevolucionDebitoAutomaticoDAO.
 */
public interface DevolucionDebitoAutomaticoDAO {

	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the reporte
	 */
	Reporte generarComprobantePDF(DatosComprobanteDevolucionDA datosComprobante);

	/**
	 * Ejecutar solicitud devolucion DA.
	 *
	 * @param cliente the cliente
	 * @param cuit the cuit
	 * @param servicio the servicio
	 * @param partida the partida
	 * @param idCliente the id cliente
	 * @param fechaVencimiento the fecha vencimiento
	 * @return the solicitud devolucion debito out entity
	 */
	SolicitudDevolucionDebitoOutEntity ejecutarSolicitudDevolucionDA(Cliente cliente, String cuit, String servicio, String partida, String idCliente,
			String fechaVencimiento); 
	
}
