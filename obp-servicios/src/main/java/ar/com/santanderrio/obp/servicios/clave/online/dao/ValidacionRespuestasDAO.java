/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionCvv2InactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionPinInactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticarTarjetaDebitoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.CLienteSinContratoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado3Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado4Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SinCelularRegistradoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SistemaClaveNoDisponibleException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaNoReintentarException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaReintentarException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;

/**
 * The Interface ValidacionRespuestasDAO.
 */
public interface ValidacionRespuestasDAO {

	/**
	 * Validar respuesta.
	 *
	 * @param validacionPreguntaIn
	 *            the validacion pregunta in
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionRespuestaReintentarException
	 *             the validacion respuesta reintentar exception
	 * @throws ValidacionRespuestaNoReintentarException
	 *             the validacion respuesta no reintentar exception
	 * @throws ClienteBloqueado2Exception
	 *             the cliente bloqueado 2 exception
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorDb2Exception 
	 * @throws ErrorCicsException 
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 */
	void validarRespuesta(ValidacionPreguntaIn validacionPreguntaIn)
			throws DAOException, ValidacionRespuestaReintentarException, ValidacionRespuestaNoReintentarException,
			ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException;
	
	/**
	 * 
	 * @param validacionPreguntaIn
	 * @return
	 * @throws DAOException
	 * @throws NumeroTelIcorrectoException
	 * @throws ValidacionRespuestaNoReintentarException 
	 * @throws ValidacionRespuestaReintentarException 
	 * @throws ClienteBloqueado2Exception 
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorDb2Exception 
	 * @throws ErrorCicsException 
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 * @throws ClienteBloqueado3Exception 
	 * @throws SistemaClaveNoDisponibleException
	 * @throws AutenticacionCvv2InactivaException 
	 * @throws AutenticacionSmsOtpInactivaException 
	 * @throws AutenticacionPinInactivaException 
	 * @throws AutenticarTarjetaDebitoException 
	 * @throws SinCelularRegistradoException 
	 * @throws SinTarjetaValidaException 
	 * @throws CLienteSinContratoException 
	 * @throws ClienteBloqueado4Exception 
	 * @throws HayRespuestasErroneasException
	 */
	IdentificadorClienteOutEntity validarRespuestaTelefono(ValidacionPreguntaIn validacionPreguntaIn)
			throws DAOException, ValidacionRespuestaNoReintentarException, ValidacionRespuestaReintentarException,
			ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException,
			ErrorModuloException, FuncionInvalidaException, ClienteBloqueado3Exception,
			SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException,
			SinCelularRegistradoException, AutenticarTarjetaDebitoException, AutenticacionPinInactivaException,
			AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ClienteBloqueado4Exception, HayRespuestasErroneasException;

}


