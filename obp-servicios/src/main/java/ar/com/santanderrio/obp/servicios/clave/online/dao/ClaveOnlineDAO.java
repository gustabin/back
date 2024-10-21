/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionCvv2InactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionPinInactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticarTarjetaDebitoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.CLienteSinContratoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineNoEsPersonaFisicaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinAutValidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinonimoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.DniInvalidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.IpBloqueadaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SinCelularRegistradoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SistemaClaveNoDisponibleException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;

/**
 * The Interface ClaveOnlineDAO.
 */
public interface ClaveOnlineDAO {

	/**
	 * Identificar cliente.
	 *
	 * @param identificadorCliente
	 *            the identificador cliente
	 * @return the identificador cliente out entity
	 * @throws DAOException
	 *             the DAO exception
	 * @throws DniInvalidoException
	 *             the dni invalido exception
	 * @throws ClienteSinonimoException
	 *             the cliente sinonimo exception
	 * @throws ClienteSinAutValidoException
	 *             the cliente sin aut valido exception
	 * @throws ClienteBloqueadoException
	 *             the cliente bloqueado exception
	 * @throws ClienteBloqueado2Exception
	 *             the cliente bloqueado 2 exception
	 * @throws ClaveOnlineNoEsPersonaFisicaException 
	 * @throws IpBloqueadaException 
	 * @throws SistemaClaveNoDisponibleException 
	 * @throws CLienteSinContratoException 
	 * @throws SinTarjetaValidaException 
	 * @throws SinCelularRegistradoException 
	 * @throws AutenticacionPinInactivaException 
	 * @throws AutenticacionSmsOtpInactivaException 
	 * @throws AutenticacionCvv2InactivaException 
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorModuloException 
	 * @throws ErrorDb2Exception 
	 * @throws FuncionInvalidaException 
	 * @throws AutenticarTarjetaDebitoException 
	 * @throws ErrorCicsException 
	 */
	IdentificadorClienteOutEntity identificarCliente(IdentificadorClienteInEntity identificadorCliente)
			throws DAOException, DniInvalidoException, ClienteSinonimoException, ClienteSinAutValidoException,
			ClienteBloqueadoException, ClienteBloqueado2Exception, ClaveOnlineNoEsPersonaFisicaException, IpBloqueadaException, SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException, SinCelularRegistradoException, AutenticacionPinInactivaException, AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ErrorRutinaFechasException, ErrorModuloException, ErrorDb2Exception, FuncionInvalidaException, AutenticarTarjetaDebitoException, ErrorCicsException;
	
	/**
	 * Obtener clave WhatsApp habilitado.
	 *
	 * @return flag
	 * @throws DAOException
	 *             the DAO exception
	 */
	Boolean obtenerClaveWhatsapp() throws DAOException;

}
