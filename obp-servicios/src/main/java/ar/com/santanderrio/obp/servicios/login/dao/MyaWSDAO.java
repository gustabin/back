/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dao;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaCodigoRetornoErrorException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaMailRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaTelefonoRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaWarningException;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;

/**
 * The Interface MyaWSDAO.
 */
public interface MyaWSDAO {

	/**
	 * Gets the estado cliente.
	 *
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @return the estado cliente
	 * @throws MyaServiceException
	 *             the mya service exception
	 */
	MyaDTOOut getEstadoCliente(MyaDTOIn myaDTOIn) throws MyaServiceException;

	/**
	 * Update destino.
	 *
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @throws MyaCodigoRetornoErrorException
	 *             the mya codigo retorno error exception
	 * @throws MyaServiceException
	 *             the mya service exception
	 * @throws MyaMailRegistradoException
	 *             the mya mail registrado exception
	 * @throws MyaTelefonoRegistradoException
	 *             the mya telefono registrado exception
	 */
	void updateDestino(MyaDTOIn myaDTOIn) throws MyaCodigoRetornoErrorException, MyaServiceException,
			MyaMailRegistradoException, MyaTelefonoRegistradoException;

	/**
	 * Registrar con destino.
	 *
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @throws MyaServiceException
	 *             the mya service exception
	 * @throws MyaWarningException
	 *             the mya warning exception
	 * @throws MyaCodigoRetornoErrorException
	 *             the mya codigo retorno error exception
	 * @throws MyaTelefonoRegistradoException
	 *             the mya telefono registrado exception
	 */
	void registrarConDestino(MyaDTOIn myaDTOIn) throws MyaServiceException, MyaWarningException,
			MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException;

	/**
	 * Update estado cliente.
	 *
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @return the string
	 * @throws MyaWarningException
	 *             the mya warning exception
	 */
	void updateEstadoCliente(MyaDTOIn myaDTOIn) throws MyaWarningException;

	/**
	 * Gets the suscripciones.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroProducto
	 *            the numero producto
	 * @return the suscripciones
	 * @throws MyaServiceException
	 *             the mya service exception
	 */
	MyaXmlResponse getSuscripciones(Cliente cliente, String numeroProducto) throws MyaServiceException;

	/**
	 * Update mensajes.
	 *
	 * @param cliente
	 *            the cliente
	 * @param updateMensajesMyaDTOIn
	 *            the update mensajes mya DTO in
	 * @throws MyaServiceException
	 *             the mya service exception
	 */
	void updateMensajes(Cliente cliente, UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn) throws MyaServiceException;

	MyaDTOOut getStatusCliente(MyaDTOIn myaDTOIn) throws MyaServiceException;
	
	String confirmarEmail(Cliente cliente, ConfirmarMailViewIn confirmarMailIn) throws MyaServiceException;

	/**
	 * getEstadoClienteV3
	 *
	 * @param myaDTOIn the myaDTOIn
	 * @return the MyaDTOOut
	 * @throws MyaServiceException the mya service exception
	 */
	MyaDTOOut getEstadoClienteV3(MyaDTOIn myaDTOIn) throws MyaServiceException;

}
