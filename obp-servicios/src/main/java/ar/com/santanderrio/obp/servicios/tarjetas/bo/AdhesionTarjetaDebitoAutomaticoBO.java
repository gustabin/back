/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosAdhesionDebitoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;

/**
 * The Interface AdhesionTarjetaDebitoAutomaticoBO.
 */
public interface AdhesionTarjetaDebitoAutomaticoBO {

	/**
	 * Extraer datos tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
	List<TarjetasAdhesionDebitoView> extraerDatosTarjeta(Cliente cliente);

	/**
	 * Adherir tarjeta debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosAdhesionDebito
	 *            the datos adhesion debito
	 * @return the comprobante feedback
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteFeedbackView adherirTarjetaDebitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjeta datosAdhesionDebito) throws DAOException;

}
