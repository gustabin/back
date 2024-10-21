/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.entities.IndicadorFuncion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.BeneficiarioNoVerificadException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaOrigenSinBanelcoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoERRORenCNSException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TitularidadNoVerificadaException;

/**
 * The Interface TransferenciaDAO.
 *
 * @author B039636
 */
public interface TransferenciaDAO {

	/**
	 * Ejecutar ALTADHABCF.
	 *
	 * @param cliente the cliente
	 * @throws DAOException the DAO exception
	 */
	void ejecutarALTADHABCF(Cliente cliente) throws DAOException;

	/**
	 * Realizar transferencia.
	 *
	 * @param cliente          the cliente
	 * @param transferencia    the transferencia
	 * @param indicadorFuncion the indicador funcion
	 * @return the transferencia
	 * @throws SaldoInsuficienteException      the saldo insuficiente exception
	 * @throws CuentaSinOperarException        the cuenta sin operar exception
	 * @throws ImporteLimiteException          the importe limite exception
	 * @throws DAOException                    the DAO exception
	 * @throws CuentaOrigenSinBanelcoException the cuenta origen sin banelco
	 *                                         exception
	 */
	TransferenciaDTO ejecutarTransferenciaInmediataOtrosBancos(Cliente cliente, TransferenciaDTO transferencia,
	        IndicadorFuncion indicadorFuncion) throws SaldoInsuficienteException, CuentaSinOperarException,
	        ImporteLimiteException, DAOException, CuentaOrigenSinBanelcoException;

	/**
	 * Validar origen destino transferencia.
	 *
	 * @param cliente          the cliente
	 * @param transferencia    the transferencia
	 * @param nroTarjetaActiva the nro tarjeta activa
	 * @return the transferencia
	 * @throws DestinatarioNoVerificadoException           the destinatario no
	 *                                                     verificado exception
	 * @throws DAOException                                the DAO exception
	 * @throws TitularidadNoVerificadaException            the titularidad no
	 *                                                     verificada exception
	 * @throws BeneficiarioNoVerificadException            the beneficiario no
	 *                                                     verificad exception
	 * @throws DestinatarioNoVerificadoERRORenCNSException the destinatario no
	 *                                                     verificado ERRO ren CNS
	 *                                                     exception
	 */
	TransferenciaDTO validarOrigenDestinoTransferencia(Cliente cliente, TransferenciaDTO transferencia,
	        String nroTarjetaActiva)
	        throws DestinatarioNoVerificadoException, DAOException, TitularidadNoVerificadaException,
	        BeneficiarioNoVerificadException, DestinatarioNoVerificadoERRORenCNSException;

	/**
	 * validarOrigenDestinoTransferenciaCVU.
	 *
	 * @param transferencia      the transferencia
	 * @param userAgent          the user agent
	 * @param monedaSeleccionada the moneda Seleccionada
	 * @param cuentaSeleccionada the cuenta seleccionada
	 * @return the transferencia DTO
	 * @throws DestinatarioNoVerificadoException           the destinatario no
	 *                                                     verificado exception
	 * @throws DAOException                                the DAO exception
	 * @throws TitularidadNoVerificadaException            the titularidad no
	 *                                                     verificada exception
	 * @throws BeneficiarioNoVerificadException            the beneficiario no
	 *                                                     verificad exception
	 * @throws DestinatarioNoVerificadoERRORenCNSException the destinatario no
	 *                                                     verificado ERRO ren CNS
	 *                                                     exception
	 */
	TransferenciaDTO validarOrigenDestinoTransferenciaCVU(TransferenciaDTO transferencia, String userAgent,
	        String monedaSeleccionada, Cuenta cuentaSeleccionada)
	        throws DestinatarioNoVerificadoException, DAOException, TitularidadNoVerificadaException,
	        BeneficiarioNoVerificadException, DestinatarioNoVerificadoERRORenCNSException;

	/**
	 * Generar comprobante transferencia.
	 *
	 * @param transferencia the transferencia
	 * @return the reporte
	 * @throws DAOException the DAO exception
	 */
	Reporte generarComprobanteTransferencia(TransferenciaDTO transferencia) throws DAOException;

	/**
	 * Realizar transferencia rio.
	 *
	 * @param cliente       the cliente
	 * @param transferencia the transferencia
	 * @return the transferencia
	 * @throws SaldoInsuficienteException      the saldo insuficiente exception
	 * @throws CuentaSinOperarException        the cuenta sin operar exception
	 * @throws ImporteLimiteException          the importe limite exception
	 * @throws DAOException                    the DAO exception
	 * @throws CuentaOrigenSinBanelcoException the cuenta origen sin banelco
	 *                                         exception
	 */
	TransferenciaDTO realizarTransferenciaInmediataRioRio(Cliente cliente, TransferenciaDTO transferencia)
	        throws SaldoInsuficienteException, CuentaSinOperarException, ImporteLimiteException, DAOException,
	        CuentaOrigenSinBanelcoException;

	/**
	 * Ejecutar mod trfe.
	 *
	 * @param cliente the cliente
	 * @return TransferenciaModtrfe
	 * @throws DAOException the DAO exception
	 */
	TransferenciaModtrfe ejecutarModTrfe(Cliente cliente) throws DAOException;

	/**
	 * conexionCNSTITICBU.
	 *
	 * @param cliente          the cliente
	 * @param requestCNSTITCBU the request CNSTITCBU
	 * @return the iatx response
	 * @throws DAOException the DAO exception
	 */
	IatxResponse conexionCNSTITICBU(Cliente cliente, RequestCNSTITCBU requestCNSTITCBU) throws DAOException;

	/**
	 * Ejecutar agendar transferencia.
	 *
	 * @param transferenciaDTO the transferencia DTO
	 * @param cliente          the cliente
	 * @return the transferencia ejecutada DTO
	 * @throws DAOException the DAO exception
	 */
	TransferenciaDTO ejecutarAgendarTransferenciaProgramada(TransferenciaDTO transferenciaDTO, Cliente cliente)
	        throws DAOException;


    /**
     * Se limpia cache
     * 
     * @param cliente
     */
    void limpiarCache(Cliente cliente);

}
