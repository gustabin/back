/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;

/**
 * Interface CambioDomicilioBO.
 *
 * @author Silvina_Luque
 */
public interface CambioDomicilioBO {

	/**
	 * Consulta de domicilios regstrados de un cliente.
	 *
	 * @return Lista CambioDomicilioDTO
	 */
	Respuesta<List<CambioDomicilioDTO>> consultarDomiciliosRegistrados();

	/**
	 * Obtener informacion adicional de domicilio y telefono.
	 *
	 * @param cambioDomicilioID
	 *            the cambio domicilio ID
	 * @return Datos adicionales de telefono y domicilio
	 */
	Respuesta<DatosDomTelOutDTO> obtenerInfoAdicionalDomTel(String cambioDomicilioID);

	/**
	 * Guardar cambio de domicilio.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return ResultadoModificacionDomicilioDTO
	 */
	Respuesta<ResultadoModificacionDomicilioDTO> guardarCambioDomicilio(CambioDomicilioView cambioDomicilioView);

	/**
	 * Normaliza un domicilio.
	 *
	 * @param domicilioModificadoView
	 *            the domicilio modificado view
	 * @return Lista domicilios
	 * @throws MerlinError1Exception
	 *             the merlin error 1 exception
	 */
	Respuesta<List<CambioDomicilioDTO>> normalizarDomicilio(CambioDomicilioView domicilioModificadoView) throws MerlinError1Exception;

	/**
	 * comprobante de cambio de domicilio.
	 *
	 * @param datos
	 *            the datos
	 * @return Reporte
	 */
	Respuesta<Reporte> descargarComprobante(DatosComprobanteEntity datos);
}
