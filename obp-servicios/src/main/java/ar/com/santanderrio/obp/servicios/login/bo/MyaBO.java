/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;

/**
 * The Interface MyaBO.
 */
public interface MyaBO {

	/**
	 * Obtener estado mya.
	 *
	 * @param cliente
	 *            the cliente
	 * @param clienteSinonimo
	 *            the cliente sinonimo
	 * @return the respuesta
	 */
	Respuesta<CredencialesMya> obtenerEstadoMya(Cliente cliente, Boolean clienteSinonimo);

	/**
	 * Update destinos.
	 *
	 * @param datos
	 *            the datos
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	public Respuesta<Void> updateDestinos(CredencialesMyaIn datos, Cliente cliente);

	/**
	 * * Alta de cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Void> altaCliente(Cliente cliente);

	/**
	 * Registrar cliente mya.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<Void> registrarClienteMya(Cliente cliente, CredencialesMyaIn datos);

	/**
	 * Actualizar estado mya.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<Void> actualizarEstadoMya(Cliente cliente);

	/**
	 * Actualiza contrato.
	 *
	 * @param cliente
	 *            the cliente
	 * @param clienteSinonimo
	 *            the cliente sinonimo
	 * @return the respuesta
	 */
	Respuesta<Void> actualizaContrato(Cliente cliente, Boolean clienteSinonimo);

	/**
	 * ** Obtiene el reporte de los terminos y condiciones.
	 *
	 * @param texto
	 *            the texto
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerTerminosCondicionesPDF(String texto);

	/**
	 * Ws getEstadoCliente mya.
	 *
	 * @param cliente
	 *            the cliente
	 * @param myaDTOIn
	 *            the mya DTO in
	 * @return the mya DTO out
	 */
	MyaDTOOut consultaWsEstadoCliente(Cliente cliente, MyaDTOIn myaDTOIn);
	
	MyaDTOOut consultaWsStatusCliente(Cliente cliente, MyaDTOIn myaDTOIn);
	
	String confirmarEmail(Cliente cliente, ConfirmarMailViewIn confirmarMailIn) throws BusinessException;

	MyaDTOOut consultaWsEstadoClienteV3(Cliente cliente, MyaDTOIn myaDTOIn);

}
