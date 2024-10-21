/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraInDTO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraOutDTO;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;

/**
 * The Interface PedidoChequeraBO.
 */
public interface PedidoChequeraBO {

	/**
	 * realiza consultar de Tenencias.
	 *
	 * @param reqChequera
	 *            the req chequera
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<List<Respuesta<ChequeraOutDTO>>> confirmarPedidoChequera(ChequeraInDTO reqChequera);

	/**
	 * Generar comprobante chequera.
	 *
	 * @param chequeraConfirmacionView
	 *            the chequera confirmacion view
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobanteChequera(ChequeraConfirmacionViewResponse chequeraConfirmacionView);
}
