package ar.com.santanderrio.obp.servicios.debinws.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CreacionDebinWSOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarAliasOutDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuOutDTO;

/**
 * The Interface DebinWSSolicitudesBO.
 */
public interface DebinWSSolicitudesBO {

	/**
	 * Consulta cuentas adheridas.
	 *
	 * @param solicitudDebinInDTO the solicitud debin in DTO
	 * @return the respuesta
	 */
	Respuesta<CuentasAdheridasOutDTO> consultaCuentasAdheridas(CuentasAdheridasInDTO solicitudDebinInDTO);

	/**
	 * Validar cbu debin.
	 *
	 * @param validarCbuInDTO the validar cbu in DTO
	 * @return the respuesta
	 */
	Respuesta<ValidarCbuOutDTO> validarCbuDebin(ValidarCbuInDTO validarCbuInDTO);

	/**
	 * Validar alias debin.
	 *
	 * @param validarAliasInDTO the validar alias in DTO
	 * @return the respuesta
	 */
	Respuesta<ValidarAliasOutDTO> validarAliasDebin(ValidarAliasInDTO validarAliasInDTO);

	/**
	 * Solicitar debin.
	 *
	 * @param creacionDebinInDTO the creacion debin in DTO
	 * @return the respuesta
	 */
	Respuesta<CreacionDebinWSOutDTO> solicitarDebin(CreacionDebinWSInDTO creacionDebinInDTO);

	/**
	 * Descargar comprobante.
	 *
	 * @param comprobanteDTO the comprobante DTO
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarComprobante(ComprobanteSolicitudDTO comprobanteDTO);

}