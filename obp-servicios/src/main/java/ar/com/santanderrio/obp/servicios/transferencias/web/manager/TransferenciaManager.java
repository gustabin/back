/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.web.view.AyudaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Interface TransferenciaManager.
 *
 * @author B039636
 */
public interface TransferenciaManager {

	/**
	 * Solicitar nueva transferencia.
	 *
	 * @author emilio.watemberg
	 * @author manuel.vargas B041299
	 * @param transferencia
	 *            the transferencia
	 * @param userAgent
	 *            the user agent
	 * @return the respuesta
	 * @see Cuenta BO.getCuentasSaldos obtiene cuentas ordenadas.
	 * @since May 4, 2016
	 */
	Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferencia, String userAgent);

	/**
	 * Ejecutar nueva transferencia.
	 *
	 * @author emilio.watemberg
	 * @param transferencia
	 *            the transferencia
	 * @return the respuesta
	 * @since May 4, 2016
	 */
	Respuesta<TransferenciaView> ejecutarNuevaTransferencia(TransferenciaView transferencia);

	/**
	 * Obtiene una lista con los tipos de cuenta disponible para realizar una
	 * transferencia.
	 *
	 * @return lista de tipos de cuenta
	 */
	Respuesta<List<TipoDeCuentaView>> obtenerTiposDeCuenta();

	/**
	 * Consultar titularidad. En el caso que el usuario modifique la cuenta de
	 * origen se debe volver a consultar la titularidad (CNSTITCBU110).
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param agent
	 *            the agent
	 * @return the respuesta
	 */
	Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferencia, String agent);

	/**
	 * Este metodo cancela el desafio en curso.
	 */
	void cancelarDesafioEnCurso();

	/**
	 * Obtener informacion destinatario.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 * @return the respuesta
	 */
	Respuesta<TransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView);

	/**
	 * Validar contrato transferencia.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> validarContratoTransferencia();

	/**
	 * Actualizar saldo.
	 *
	 * @return the respuesta
	 */
	Respuesta<CuentasView> actualizarSaldo();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDF();

	/**
	 * Cargar vista solicitar.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param cliente
	 *            the cliente
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param isCuentaPropia
	 *            the is cuenta propia
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	TransferenciaView cargarVistaSolicitar(TransferenciaView transferenciaView, Cliente cliente,
			CuentasView cuentasViewFiltradas, TransferenciaDTO transferenciaDTO, boolean isCuentaPropia,
			boolean isRioRio) throws BusinessException;

	/**
	 * Obtener ayudas con template.
	 *
	 * @return the list
	 */
	List<AyudaView> obtenerAyudasConTemplate();
	
	/**
	 * Consultar horarios.
	 *
	 * @return the respuesta
	 */
	Respuesta<TransferenciaView> consultarHorarios();
}
