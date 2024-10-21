/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;

/**
 * The Interface RsaBO.
 *
 * @author Ignacio_Valek
 */
public interface RsaBO extends BusinessObject {

	/**
	 * Analizar.
	 *
	 * @param requestData
	 *            the request data
	 * @return the rsa analyze response data
	 * @throws BusinessException
	 *             the business exception
	 */
	@Deprecated
	// Usar #analizar(rsaAnalyzeRequestData)
	RsaAnalyzeResponseData analizar(RsaRequestData requestData) throws BusinessException;

	/**
	 * Analizar RSA.
	 *
	 * @param requestData
	 *            the request data
	 * @return the rsa analyze response data
	 */
	Respuesta<RsaAnalyzeResponseData> analizar(RsaAnalyzeRequestData requestData);

	/**
	 * Analizar RSA Login.
	 *
	 * @param requestData
	 *            the request data
	 * @param isLogin
	 *            the is login
	 * @return the rsa analyze response data
	 */
	Respuesta<RsaAnalyzeResponseData> analizar(RsaAnalyzeRequestData requestData, Boolean isLogin);

	/**
	 * Crear usuario.
	 *
	 * @param requestData
	 *            the request data
	 * @return the rsa create user response data
	 * @throws BusinessException
	 *             the business exception
	 */
	RsaCreateUserResponseData crearUsuario(RsaCreateUserRequestData requestData) throws BusinessException;

	/**
	 * Notificar.
	 *
	 * @param requestData
	 *            the request data
	 * @return the rsa notify response data
	 * @throws BusinessException
	 *             the business exception
	 */
	RsaNotifyResponseData notificar(RsaNotifyRequestData requestData) throws BusinessException;

	/**
	 * Challenge.
	 *
	 * @param requestData
	 *            the request data
	 * @return the rsa analyze response data
	 * @throws BusinessException
	 *             the business exception
	 */
	RsaAnalyzeResponseData challenge(RsaAnalyzeRequestData requestData) throws BusinessException;
	
	
	/**
	 * Update User
	 * @param requestData
	 * @return
	 * @throws BusinessException
	 */
	Respuesta<RsaUpdateUserResponseData> updateUser(RsaUpdateUserRequestData requestData);

}
