/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridadResponse;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;

/**
 * The Interface PreguntasSeguridadDAO.
 */
public interface PreguntasSeguridadDAO {

	/**
	 * Obtener preguntas seguridad.
	 *
	 * @param cliente the cliente
	 * @return the preguntas seguridad response
	 * @throws ErrorRutinaFechasException
	 * @throws ErrorDb2Exception
	 * @throws ErrorCicsException
	 * @throws FuncionInvalidaException
	 * @throws ErrorModuloException
	 * @throws HayRespuestasErroneasException
	 */
	PreguntasSeguridadResponse obtenerPreguntasSeguridad(IdentificadorClienteInEntity cliente)
			throws ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException,
			ErrorModuloException, HayRespuestasErroneasException;

}