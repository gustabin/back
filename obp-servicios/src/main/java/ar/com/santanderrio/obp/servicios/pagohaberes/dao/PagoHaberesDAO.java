/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.CBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.RecuperarDatosPorCBUDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;

/**
 * The Interface PagoHaberesDAO.
 */
public interface PagoHaberesDAO {

	/**
	 * Checks if is cliente valido.
	 *
	 * @param cliente
	 *            the cliente
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the boolean
	 * @throws DAOException
	 *             the DAO exception
	 * @throws CuentaInvalidaException
	 *             the cuenta invalida exception
	 */
	PagoInformadoView isClienteValido(Cliente cliente, PagoInformadoView pagoInformadoView)
			throws DAOException, CuentaInvalidaException;

	/**
	 * Validar CBU.
	 *
	 * @param clienteIngresado
	 *            the cliente ingresado
	 * @param validarCBUView
	 *            the validar CBU view
	 * @return ValidacionCuentaOutCBUEntity
	 * @throws CBUInvalidoDAOException
	 *             the CBU invalido DAO exception
	 * @throws DestinatarioNoVerificadoException
	 *             the destinatario no verificado exception
	 * @throws RecuperarDatosPorCBUDAOException
	 *             the recuperar datos por CBUDAO exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	ValidacionCuentaOutCBUEntity validarCBU(Cliente clienteIngresado, ValidacionesPagoPorCBUView validarCBUView)
			throws CBUInvalidoDAOException, DestinatarioNoVerificadoException, RecuperarDatosPorCBUDAOException,
			DAOException;

	/**
	 * Pago haberes CBU.
	 *
	 * @param clienteIngresado
	 *            the cliente ingresado
	 * @param datosDestinatarioView
	 *            the datos destinatario view
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @return IatxResponse
	 * @throws SaldoInsuficienteException
	 *             the saldo insuficiente exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	IatxResponse pagoHaberesCBU(Cliente clienteIngresado, DatosDestinatarioView datosDestinatarioView,
			Cuenta cuentaOrigen) throws SaldoInsuficienteException, DAOException;

	/**
	 * Generar comprobante adhesion.
	 *
	 * @param comprobanteAdhesionEmpleado
	 *            the comprobante adhesion empleado
	 * @return the reporte
	 */
	Reporte generarComprobanteAdhesion(ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado);

	/**
	 * Generar comprobante pago por CBU.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the reporte
	 */
	Reporte generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU);

	/**
	 * Genera el reporte de empleado.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the respuesta
	 */
	Reporte generarComprobantePagoSimple(ComprobantePagoHaberesCBUEntity comprobante);

}
