/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.impl.SietePorVenticuatroV1WSDAOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ComprobanteCancelTotal;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ComprobanteStopDebit;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaAgendaTransferencias;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaCancelTotal;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;

/**
 * The Interface AgendaTransferenciaDAO.
 * 
 * @version 2: @author B041299 Manuel Vargas. Se vuelve a la invocacion de
 *          7x24V1.ENDPOINT
 * @see properties en configuracion
 * @see SietePorVenticuatroV1WSDAOImpl
 */
public interface AgendaTransferenciaDAO {

	/**
	 * Obtener transferencias agendadas.
	 *
	 * @param consultaAgendaTransferencias
	 *            the consulta agenda transferencias
	 * @param isRio
	 *            the is rio
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<TransferenciaAgendada> obtenerTransferenciasAgendadas(
			ConsultaAgendaTransferencias consultaAgendaTransferencias, boolean isRio) throws DAOException;

	/**
	 * Stop debit.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @param cliente
	 *            the cliente
	 * @return the comprobante stop debit
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteStopDebit stopDebit(TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente)
			throws DAOException;

	/**
	 * Cancel total, Eliminar.
	 *
	 * @param consultaCancelTotal
	 *            the consulta cancel total
	 * @param cliente
	 *            the cliente
	 * @return the comprobante cancel total
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComprobanteCancelTotal cancelTotal(ConsultaCancelTotal consultaCancelTotal, Cliente cliente) throws DAOException;

	/**
	 * Ejecutar agenda transferencia.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @param cliente
	 *            the cliente
	 * @return the transferencia ejecutada
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	TransferenciaEjecutadaDTO ejecutarTransferenciaAgendada(TransferenciaAgendadaDTO transferenciaAgendadaDTO,
			Cliente cliente) throws DAOException, ImporteConvertException;

	/**
	 * Ejecutar agendamiento transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @return the transferencia DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	TransferenciaDTO ejecutarAgendamientoRecordatorioTransferencia(TransferenciaDTO transferenciaDTO, Cliente cliente)
			throws DAOException;

	/**
	 * Ejecutar agendamiento automatico transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @return the transferencia DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	TransferenciaDTO ejecutarAgendamientoAutomaticoTransferencia(TransferenciaDTO transferenciaDTO, Cliente cliente)
			throws DAOException;

	/**
	 * Ejecutar modificacion de transferencia agendada.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param tipoAgendamiento
	 *            the tipo agendamiento
	 * @param cliente
	 *            the cliente
	 * @return the transferencia agendada DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	TransferenciaAgendadaDTO ejecutarModificacionDeTransferenciaAgendada(TransferenciaAgendadaDTO transferenciaDTO,
			String tipoAgendamiento, Cliente cliente) throws DAOException;

}
