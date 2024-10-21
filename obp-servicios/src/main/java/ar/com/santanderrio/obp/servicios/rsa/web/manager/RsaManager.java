/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.web.manager;

import java.util.Map;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;

/**
 * The Interface RsaManager.
 *
 * @author B039636
 */
public interface RsaManager {

	/**
	 * Analizar operacion de riesgo.
	 *
	 * @param dto
	 *            the dto
	 * @param desafioAplicable 
	 *            the desafio aplicable
	 * @return la acción a tomar. Siempre estado respuesta OK, en caso de no
	 *         estar habilitado RSA o algun error en el WS devuelve estado
	 *         WARNING
	 */
	Respuesta<ActionCode> analizar(RsaDTO dto, TipoDesafioEnum desafioAplicable);

	/**
	 * Este metodo permite analizar el riesgo de una transferencia. Ademas de
	 * retonar el ActionCode retorna una propiedad que nos deja validar el
	 * riesgo de una transferencia.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @param desafioAplicable 
	 *            the desafio aplicable
	 * @return the respuesta
	 */
	Respuesta<RsaRiesgoTransferenciaDTO> analizarRiesgoTransferencia(RsaDTO rsaDTO, TipoDesafioEnum desafioAplicable);

	/**
	 * Invocar rsa analyze para el login.
	 *
	 * @author juan.pablo.gatto.
	 * @param analyzeRequestData
	 *            the analyze request data
	 * @return the rsa analyze response data
	 */
	public Respuesta<RsaAnalyzeResponseData> analizarRsa(RsaAnalyzeRequestData analyzeRequestData);

	/**
	 * Notificar.
	 *
	 * @param datos
	 *            the datos
	 * @param intentosRealizados
	 *            the intentos realizados
	 * @return the respuesta
	 */
	Respuesta<RsaNotifyResponseData> notificar(AutentificacionDTO datos, int intentosRealizados);

	/**
	 * Inicializa el listado para saber si el servicio esta activo o no.
	 */
	void init();

	/**
	 * Gets the rsa estado.
	 *
	 * @return the rsa estado
	 */
	Map<? extends OperacionesRSAEnum, ? extends Boolean> getRsaEstado();

	/**
	 * Checks if is servicio activo.
	 *
	 * @param rsaIngreso
	 *            the rsa ingreso
	 * @return true, if is servicio activo
	 */
	boolean isServicioActivo(OperacionesRSAEnum rsaIngreso);

	/**
	 * Crear rsa analyze request data.
	 *
	 * @param operacion
	 *            the operacion
	 * @param desafioAplicable 
	 *            the desafio aplicable
	 * @return the rsa analyze request data
	 */
	RsaAnalyzeRequestData crearRsaAnalyzeRequestData(RsaDTO operacion, TipoDesafioEnum desafioAplicable);

	/**
	 * Enrolar Crea usuario en RSA si es necesario Guarda datos de comunicacion
	 * en sesion.
	 *
	 * @param createUserRequestData
	 *            the create user request data
	 * @return the respuesta
	 */
	public Respuesta<RsaGenericResponseData> enrolarUser(RsaCreateUserRequestData createUserRequestData);

	/**
	 * Analizar RSA login.
	 *
	 * @param analyzeRequestData
	 *            the analyze request data
	 * @param isLogin
	 *            the is login
	 * @return the respuesta
	 */
	Respuesta<RsaAnalyzeResponseData> analizarRsa(RsaAnalyzeRequestData analyzeRequestData, Boolean isLogin);
	
	
	/**
	 * Update user
	 * @param request
	 * @return
	 */
	Respuesta<RsaUpdateUserResponseData> updateUser(RsaUpdateUserRequestData request);

	/**
	 * Get deviceTokenCookie
	 * @return the device token cookie from RSA
	 */
	Respuesta<String> getRsaDeviceTokenCookie();
}
