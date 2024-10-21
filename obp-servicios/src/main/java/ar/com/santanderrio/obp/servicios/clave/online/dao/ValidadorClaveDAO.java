/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.*;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import oracle.net.aso.h;

/**
 * The Interface ValidadorClaveDAO.
 */
public interface ValidadorClaveDAO {

	/**
	 * Validar clave.
	 *
	 * @param datosAutenticacionInEntity
	 *            the datos autenticacion in entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ErrorClaveOnlineConReintentoException
	 *             the error clave online con reintento exception
	 * @throws ClienteBloqueadoException
	 *             the cliente bloqueado exception
	 * @throws ClienteBloqueado2Exception
	 *             the cliente bloqueado 2 exception
	 * @throws SinReintentosSMSException
	 *             the sin reintentos SMS exception
	 * @throws ErrorEnvioSMSException
	 *             the error envio SMS exception
	 * @throws ErrorPinBanelcoException 
	 * @throws ErrorPinOtpException 
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorDb2Exception 
	 * @throws ErrorCicsException 
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 * @throws ErrorLogicoOtpException 
	 * @throws h 
	 */
	void validarClave(DatosAutenticacionInEntity datosAutenticacionInEntity)
			throws DAOException, ErrorClaveOnlineConReintentoException, ClienteBloqueadoException,
			ClienteBloqueado2Exception, SinReintentosSMSException, ErrorEnvioSMSException, ErrorPinBanelcoException, ErrorPinOtpException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, ErrorLogicoOtpException, OtpVencidoException;

}
