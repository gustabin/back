/*
 * 
 */
package ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * Interfaz StopDebitTarjetasBO.
 */
public interface StopDebitTarjetasBO {

	/**
	 * Realizar stop debit tarjeta.
	 * 
	 * @param cliente
	 *            the cliente
	 * @param datos
	 *            the datos
	 * @return the stop debit out
	 * @throws DAOException
	 *             the DAO exception
	 */
	StopDebitOut realizarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos) throws DAOException;

	/**
	 * Cancelar stop debit tarjeta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datos
	 *            the datos
	 * @param nroTarjeta
	 *            the nro tarjeta
	 * @return the comprobante feedback
	 * @throws BusinessException
	 *             the business exception
	 */
    ComprobanteFeedbackView cancelarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos, String nroTarjeta)
            throws BusinessException;

	/**
	 * Revisar horario solicitud.
	 *
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the boolean
	 * @throws DAOException
	 *             the DAO exception
	 */
	Boolean revisarHorarioSolicitud(String fechaVencimiento) throws DAOException;

}
