/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;

/**
 * Acceso a los servicios de Agenda de destinatario.
 *
 * @author luis_moyano
 */
public interface AgendaDestinatarioDAO {

	/**
	 * Consulta de agenda de destintatarios.
	 *
	 * @param entity
	 *            the entity
	 * @return the consulta agenda destinatario out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaAgendaDestinatarioOutEntity consultar(ConsultaAgendaDestinatarioInEntity entity) throws DAOException;

	/**
	 * Eliminar u agregar agenda de destinatarios.
	 *
	 * @param entity
	 *            the entity
	 * @param ip
	 *            the ip
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the agenda destinatario out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	AgendaDestinatarioOutEntity eliminarUAgregar(AgendaDestinatarioInEntity entity, String ip,
			TipoOperacionACTAGEDESTEnum tipoOperacion) throws DAOException;

	/**
	 * Se valida si la cuenta ingresada existe, y devuelve los datos del titular
	 * para las cuentas rio.
	 *
	 * @param entity
	 *            the entity
	 * @return the datos cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatosCliente validarCuenta(ValidacionCuentaInEntity entity) throws DAOException;

	/**
	 * Se valida si la cuenta ingresada existe, y devuelve los datos del titular
	 * para el alta de transferencia por cbu.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the validacion cuenta out CBU entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ValidacionCuentaOutCBUEntity validarCuentaTransferenciaCBU(ValidacionCuentaInCBUEntity transferencia)
			throws DAOException;

}
