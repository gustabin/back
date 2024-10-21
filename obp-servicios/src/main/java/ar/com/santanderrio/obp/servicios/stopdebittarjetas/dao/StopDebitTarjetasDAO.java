/*
 * 
 */
package ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * Interfaz StopDebitTarjetasDAO.
 */
public interface StopDebitTarjetasDAO {

	/** Constante ESTADO_OK. */
	String ESTADO_OK = "OK";

	/** Constante ESTADO_ERROR. */
	String ESTADO_ERROR = "ERROR";

	/** Constante CODIGO_ERROR_STOP_DEBIT_PEDIDO. */
	int CODIGO_ERROR_STOP_DEBIT_PEDIDO = 10001166;
	
	/** The codigo error stop debit pedido2. */
	int CODIGO_ERROR_STOP_DEBIT_PEDIDO2 = 10001186;
	
	/** The CODIGO_ERROR_STOP_DEBIT_DIA_NO_PERMITIDO. */
	int CODIGO_ERROR_STOP_DEBIT_DIA_NO_PERMITIDO = 10001034;

	/**
	 * Realizar stop debit tarjeta. Este metodo se comunica con el servicio IATX
	 * STPDEB para ejecutar el stop debit configurado.
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
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteFeedbackView cancelarStopDebitTarjeta(Cliente cliente, DatosStopDebit datos, String nroTarjeta)
			throws DAOException;

}
