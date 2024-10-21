/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;

/**
 * The Interface DebitoAutomaticoBO.
 */
public interface DebitoAutomaticoBO {

	/**
	 * Adherir.
	 *
	 * @param cliente
	 *            the cliente
	 * @param registroSesion
	 *            the registro sesion
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<AdhesionDebitoAutomatico> adherir(Cliente cliente, RegistroSesion registroSesion,
			AdhesionDebitoAutomatico adhesionDebitoAutomatico) throws BusinessException;

	/**
	 * Baja adhesion.
	 *
	 * @param adhesionDebitoAutomatico
	 *            the adhesion debito automatico
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ResultadoTransaccion> bajaAdhesion(AdhesionDebitoAutomatico adhesionDebitoAutomatico, Cliente cliente);
}
