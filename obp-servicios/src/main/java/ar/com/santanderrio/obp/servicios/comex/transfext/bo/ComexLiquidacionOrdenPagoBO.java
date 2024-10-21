package ar.com.santanderrio.obp.servicios.comex.transfext.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.CargaDocumentoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConfiguracionLiquidacionOrdenPagoDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarOrdenPagoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarOrdenPagoInView;

public interface ComexLiquidacionOrdenPagoBO {

	/**
	 * Devuelve lista de conceptos y cuentas para la pantalla de configuracion de
	 * liquidacion orden de pago
	 * 
	 * @param cliente
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<ConfiguracionLiquidacionOrdenPagoDTO> inicioLiquidacionOrdenPago(Cliente cliente)
			throws BusinessException;

	/**
	 * Limpia cache de conceptos
	 * 
	 * @return
	 */
	Respuesta<Boolean> limpiarConceptos();

	/**
	 * Adjunta un archivo, al adjuntar el primero recupera el número de transacción.
	 * 
	 * @return
	 */
	Respuesta<CargaDocumentoDTO> adjuntarArchivos(AdjuntarArchivosDTO adjuntarArchivosInDto);

	/**
	 * Procesar orden pago comex
	 * 
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<ProcesarOrdenPagoOutDTO> procesarOrdenPago(Cliente cliente, ProcesarOrdenPagoInView view) throws BusinessException;
	
	/**
	 * Elimina un archivo adjunto de la solicitud.
	 * 
	 * @return
	 */
	Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto);

}
