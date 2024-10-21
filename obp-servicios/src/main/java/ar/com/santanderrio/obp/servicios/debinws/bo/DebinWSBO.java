package ar.com.santanderrio.obp.servicios.debinws.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ConsultaDetalleDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.DebinWSEliminarOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.RechazarDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.SolicitarContracargoDebinOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;

/**
 * The Interface DebinWSBO.
 */
public interface DebinWSBO {

	/**
	 * Consulta debin.
	 *
	 * @param consultaDebinInDTO the consulta debin in DTO
	 * @return the respuesta
	 */
	Respuesta<ConsultaDebinWSOutDTO> consultaDebin(ConsultaDebinWSInDTO consultaDebinInDTO);

	/**
	 * Consulta detalle debin.
	 *
	 * @param cnsDetalleDebinInDTO the cns detalle debin in DTO
	 * @return the respuesta
	 */
	Respuesta<ConsultaDetalleDebinWSOutDTO> consultaDetalleDebin(ConsultaDetalleDebinWSInDTO cnsDetalleDebinInDTO);

	/**
	 * Descargar comprobante.
	 *
	 * @param comprobanteDTO the comprobante DTO
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarComprobante(ComprobanteInDTO comprobanteDTO);

	/**
	 * Eliminar debin.
	 *
	 * @return the respuesta
	 */
	Respuesta<DebinWSEliminarOutDTO> eliminarDebin();

	/**
	 * Pagar debin.
	 *
	 * @param pagarDebinWSView the pagar debin WS view
	 * @return the respuesta
	 */
	Respuesta<PagarDebinWSView> pagarDebin(PagarDebinWSView pagarDebinWSView);

	/**
	 * Rechazar debin.
	 *
	 * @return the respuesta
	 */
	Respuesta<RechazarDebinWSOutDTO> rechazarDebin();

	Respuesta<SolicitarContracargoDebinOutDTO> solicitarContracargo(Cliente cliente, String debinId);

}