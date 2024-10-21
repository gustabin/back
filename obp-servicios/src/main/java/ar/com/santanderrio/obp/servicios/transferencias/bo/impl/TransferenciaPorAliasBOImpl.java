/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendido;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AliasCorrespondienteCuentaPropiaException;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.AltaDestinatarioAliasBOImpl;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCorrespondienteCuentaPropiaUnicaException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaPorAliasBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TipoCuentaBanelco;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Transferencia por Alias BO Impl. {@link #aliasCbuDAO}
 * 
 * @author B041299
 * @version 1
 */
@Component
public class TransferenciaPorAliasBOImpl implements TransferenciaPorAliasBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaBOImpl.class);

    /**
     * The Constant
     * LA_CUENTA_SELECCIONADA_NO_SE_ENCUENTRA_EN_BANELCO_SE_QUITA_DE_LA_LISTA.
     */
    private static final String LA_CUENTA_SELECCIONADA_NO_SE_ENCUENTRA_EN_BANELCO_SE_QUITA_DE_LA_LISTA = "La cuenta seleccionada no se encuentra en banelco. Se quita de la lista.";

    /** The Constant LA_CONSULTA_DEL_ALIAS_FUE_EXITOSA. */
    private static final String LA_CONSULTA_DEL_ALIAS_FUE_EXITOSA = "La consulta del alias fue exitosa.";

    /** The Constant OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS. */
    private static final String OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS = "Ocurrió un error al consultar las cuentas saldos.";

    /** The Constant INICIO_VALIDACION_TITULARIDAD_ALIAS. */
    private static final String INICIO_VALIDACION_TITULARIDAD_ALIAS = "INICIO VALIDACION TITULARIDAD ALIAS";

    /** The Constant VALIDACION_OK_TITULARIDAD_ALIAS. */
    private static final String VALIDACION_OK_TITULARIDAD_ALIAS = "VALIDACION_OK_TITULARIDAD_ALIAS";

    /** The Constant ERROR_NO_CONTEMPLADO. */
    private static final String ERROR_NO_CONTEMPLADO = "Error No Contemplado";

    /** The Constant VALIDACION_WARN_TITULARIDAD_ALIAS. */
    private static final String VALIDACION_WARN_TITULARIDAD_ALIAS = "VALIDACION_WARN_TITULARIDAD_ALIAS";

    /** The Constant ALIAS_NO_EXISTE. */
    private static final String ALIAS_NO_EXISTE = "0110";

    /** The Constant ALIAS_ELIMINADO. */
    private static final String ALIAS_ELIMINADO = "0190";

    /** The Constant MONEDA_INCOMPATIBLE. */
    private static final String MONEDA_INCOMPATIBLE = "36";

    /** The Constant CUENTA_INACTIVA. */
    private static final String CUENTA_INACTIVA = "0160";

    /** The Constant NO_EXISTE_CTA_EN_BANELCO. */
    private static final String NO_EXISTE_CTA_EN_BANELCO = "0050";

    /** The Constant CERO. */
    private static final String CERO = "0";

    /** The Constant UNO. */
    private static final String UNO = "1";

    /** The Constant DOS. */
    private static final String DOS = "2";

    /** The Constant PESOS. */
    private static final String PESOS = "PESOS";

    /** The Constant DOLARES. */
    private static final String DOLARES = "DOLARES";

    /** The Constant ERROR CTADEST es propia y tiene una sola Cta. */
    private static final String ERROR_CTADEST_ES_PROPIA_Y_TIENE_UNA_SOLA_CTA = "ERROR CTADEST es propia y tiene una sola Cta.";

    /** The Constant ERROR CTADEST es propia y tiene una sola Cta. */
    private static final String ERROR_CTADEST_ES_PROPIA = "ERROR CTADEST es propia pero tiene otras cuentas";

    /** The alias cbu BO. */
    @Autowired
    private AltaDestinatarioAliasBOImpl altaDestinatarioAliasBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The cuentas Banca Privada BO. */
    @Autowired
    private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;
    
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.transferencias.bo.
     * TransferenciaPorAliasBO#consultarDatosTitularidadExtendida(ar.com.
     * santanderrio.obp.servicios.transferencias.web.view.TransferenciaView)
     */
    @Override
    public Respuesta<TransferenciaDTO> consultarDatosTitularidadExtendida(TransferenciaView transferenciaView,
            Cliente cliente, String userAgent) {
        LOGGER.info(INICIO_VALIDACION_TITULARIDAD_ALIAS);
        ConsultarDatosTitularidadExtendidoResponse consultarDatosTitularidadExtendidoResponse = new ConsultarDatosTitularidadExtendidoResponse();
        List<CuentasAdhesionDebitoView> cuentasCliente = null;
        CuentasAdhesionDebitoView cuentaSeleccionada = null;
        try {
        	Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
			if(!sesionCliente.getCliente().getCuentasMonetarias().isEmpty()) {//Si no tiene cuentas retail no llama a los servicios de saldos.
				respuestaCuentasView = cuentaManager.getCuentasSaldo();
				if (EstadoRespuesta.ERROR.equals(respuestaCuentasView.getEstadoRespuesta())) {
	                LOGGER.info(OCURRIO_UN_ERROR_AL_CONSULTAR_LAS_CUENTAS_SALDOS);
	                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
	                        CodigoMensajeConstantes.ERROR_GENERICO);
	            }
			}
            
            Respuesta<List<ResumenDetalleCuenta>> respuestaResumenes = obtenerCuentasySaldosBP();
            if( !EstadoRespuesta.OK.equals(respuestaResumenes.getEstadoRespuesta())) {
				return respuestaFactory.transformar(null, respuestaResumenes);
			}
            Respuesta<CuentasView> respuestaCuentasViewBP =  CuentasBancaPrivadaUtil.convertirCuentasView(respuestaResumenes);
            if(respuestaCuentasView.getRespuesta() == null) {
				respuestaCuentasView = respuestaCuentasViewBP;
			}else {
				respuestaCuentasView.getRespuesta().getCuentas().addAll(respuestaCuentasViewBP.getRespuesta().getCuentas());
			}
            
            DivisaEnum moneda = null;
            if (transferenciaView.getMoneda().equalsIgnoreCase(PESOS)
                    || transferenciaView.getMoneda().equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
                moneda = DivisaEnum.PESO;
            } else if (transferenciaView.getMoneda().equalsIgnoreCase(DOLARES)
                    || transferenciaView.getMoneda().equalsIgnoreCase(DivisaEnum.DOLAR.getMoneda())) {
                moneda = DivisaEnum.DOLAR;
            }
            cuentasCliente = TransferenciaUtil.filtrarCuentasPorDivisa(respuestaCuentasView.getRespuesta().getCuentas(),
                    moneda);
            if(cuentasCliente == null) {
            	cuentasCliente = TransferenciaUtil.filtrarCuentasPorDivisa(respuestaCuentasView.getRespuesta().getCuentas(),
                        moneda);
            }
            if (transferenciaView.isFromAgendaDestinatario()) {
                cuentaSeleccionada = obtenerCuentaAdhesionDebitoViewDesdeListaDeCuentas(cuentasCliente,
                        transferenciaView.getNroCuenta());
                consultarDatosTitularidadExtendidoResponse = altaDestinatarioAliasBO.realizarConsultaWSconCuentaSaldo(
                        cliente, userAgent, cuentaSeleccionada, transferenciaView.getAliasDestino(),
                        getNombreMonedaSingular(transferenciaView.getMoneda()));
            } else {
                for (CuentasAdhesionDebitoView cuenta : cuentasCliente) {
                    consultarDatosTitularidadExtendidoResponse = altaDestinatarioAliasBO
                            .realizarConsultaWSconCuentaSaldo(cliente, userAgent, cuenta,
                                    transferenciaView.getAliasDestino(),
                                    getNombreMonedaSingular(transferenciaView.getMoneda()));
                    if (null == consultarDatosTitularidadExtendidoResponse.getCodigo()) {
                        LOGGER.info(LA_CONSULTA_DEL_ALIAS_FUE_EXITOSA);
                        cuentaSeleccionada = cuenta;
                        break;
                    } else if (consultarDatosTitularidadExtendidoResponse.getCodigo()
                            .equals(NO_EXISTE_CTA_EN_BANELCO)) {
                        LOGGER.info(LA_CUENTA_SELECCIONADA_NO_SE_ENCUENTRA_EN_BANELCO_SE_QUITA_DE_LA_LISTA);
                        cuentasCliente.remove(cuenta);
                    } else {
                        cuentaSeleccionada = cuenta;
                        break;
                    }
                }
            }
            if (null != consultarDatosTitularidadExtendidoResponse.getTitularidadExtendido()) {
                removerCuentaPropiaDeListaCuentas(cuentasCliente, consultarDatosTitularidadExtendidoResponse
                        .getTitularidadExtendido().getCtaDestino().getNumeroCBU());
            }
            return analizarRespuestaServicio(transferenciaView, cliente, consultarDatosTitularidadExtendidoResponse,
                    cuentasCliente, cuentaSeleccionada);
            // TODO: maximilianos revisar las exceptiones ya que se setea el
            // mismo tipo de error para distintas exceptiones (ERROR_GENERICO)
        } catch (DAOException e) {
            LOGGER.error(ERROR_NO_CONTEMPLADO, e);
            if (transferenciaView.isFromAgendaDestinatario()) {
                return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_NO_CONTEMPLADO,
                        CodigoMensajeConstantes.ERROR_NO_CONTEMPLADO_TITULARIDAD_ALIAS);
            } else {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.ERROR_GENERICO);
            }
        } catch (AliasCorrespondienteCuentaPropiaException e) {
            LOGGER.error(ERROR_CTADEST_ES_PROPIA, e);
            Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = respuestaFactory.crearRespuestaWarning("",
                    TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN,
                    CodigoMensajeConstantes.ALIAS_CORRESPONDE_A_CUENTA_ORIGEN);
            TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
            cuentasCliente.remove(cuentaSeleccionada);
            transferenciaDTO.setCuentasSaldosFiltrada(cuentasCliente);
            respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
            return respuestaTransferenciaDTO;
        } catch (AliasCorrespondienteCuentaPropiaUnicaException e) {
            LOGGER.error(ERROR_CTADEST_ES_PROPIA_Y_TIENE_UNA_SOLA_CTA, e);
            return respuestaFactory.crearRespuestaError("",
                    TipoError.ALIAS_CORRESPONDIENTE_A_CUENTA_ORIGEN_CON_SOLO_UNA_CUENTA,
                    CodigoMensajeConstantes.CUENTA_PROPIA_Y_UNICA);
        } catch (BusinessException be) {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_GENERICO);
        }
    }

    /**
     * Remover cuenta propia de lista cuentas.
     *
     * @param cuentasAdhesionDebitoView
     *            the cuentas adhesion debito view
     * @param cbuDeAlias
     *            the cbu de alias
     */
    private void removerCuentaPropiaDeListaCuentas(List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView,
            String cbuDeAlias) {
        for (CuentasAdhesionDebitoView cuentaAdhesionDebitoView : cuentasAdhesionDebitoView) {
            if (cuentaAdhesionDebitoView.getCbu().equals(cbuDeAlias)) {
                cuentasAdhesionDebitoView.remove(cuentaAdhesionDebitoView);
                break;
            }
        }
    }

    /**
     * Obtener cuenta adhesion debito view desde lista de cuentas.
     *
     * @param cuentasAdhesionDebitoView
     *            the cuentas adhesion debito view
     * @param nroCuenta
     *            the nro cuenta
     * @return the cuentas adhesion debito view
     */
    private CuentasAdhesionDebitoView obtenerCuentaAdhesionDebitoViewDesdeListaDeCuentas(
            List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView, String nroCuenta) {
        for (CuentasAdhesionDebitoView cuentaAdhesionDebitoView : cuentasAdhesionDebitoView) {
            if (cuentaAdhesionDebitoView.getNumero().equals(nroCuenta))
                return cuentaAdhesionDebitoView;
        }
        return null;
    }

    /**
     * Analizar respuesta servicio.
     *
     * @param transferenciaView
     *            the transferencia view
     * @param cliente
     *            the cliente
     * @param response
     *            the response
     * @param cuentasClienteFiltradas
     *            the cuentas cliente filtradas
     * @param cuentaSeleccionada
     *            the cuenta seleccionada
     * @return the respuesta
     * @throws AliasCorrespondienteCuentaPropiaException
     *             the alias correspondiente cuenta propia exception
     * @throws AliasCorrespondienteCuentaPropiaUnicaException
     *             the alias correspondiente cuenta propia unica exception
     * @throws BusinessException
     *             the business exception
     */
    private Respuesta<TransferenciaDTO> analizarRespuestaServicio(TransferenciaView transferenciaView, Cliente cliente,
            ConsultarDatosTitularidadExtendidoResponse response,
            List<CuentasAdhesionDebitoView> cuentasClienteFiltradas, CuentasAdhesionDebitoView cuentaSeleccionada)
            throws AliasCorrespondienteCuentaPropiaException, AliasCorrespondienteCuentaPropiaUnicaException,
            BusinessException {

        if (null != response.getTitularidadExtendido()) {
            if (transferenciaView.isFromAgendaDestinatario()) {
                Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = validarModificacionDeAlias(transferenciaView,
                        response, cuentasClienteFiltradas, cliente, cuentaSeleccionada);
                if (respuestaTransferenciaDTO != null)
                    return respuestaTransferenciaDTO;
            }
            LOGGER.info(VALIDACION_OK_TITULARIDAD_ALIAS);
            verificarCBURespuestaWSNoCorrespondaCuentaPropia(cliente, response.getTitularidadExtendido(),
                    cuentaSeleccionada.getCbu(), transferenciaView.isFromAgendaDestinatario());
            Boolean esRio = ISBANStringUtils
                    .validarCBURio(response.getTitularidadExtendido().getCtaDestino().getNumeroCBU());
            return crearRespuestaOK(response, esRio, cuentasClienteFiltradas, cliente, transferenciaView,
                    cuentaSeleccionada);
        } else {
            LOGGER.info(VALIDACION_WARN_TITULARIDAD_ALIAS);
            return armarRespuestasWarningEsperada(response, transferenciaView.isFromAgendaDestinatario());
        }
    }

    /**
	 * Validar modificacion de alias.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param response
	 *            the response
	 * @param cuentasClienteFiltradas
	 *            the cuentas cliente filtradas
	 * @param cliente
	 *            the cliente
	 * @param cuentaSeleccionada
	 *            the cuenta seleccionada
	 * @return the respuesta
	 */
    private Respuesta<TransferenciaDTO> validarModificacionDeAlias(TransferenciaView transferenciaView,
            ConsultarDatosTitularidadExtendidoResponse response,
            List<CuentasAdhesionDebitoView> cuentasClienteFiltradas, Cliente cliente,
            CuentasAdhesionDebitoView cuentaSeleccionada) {
        if (response.getTitularidadExtendido().getCtaDestino().getNumeroCBU().startsWith("072")) {
            String cbuDestinoRioConsulta = response.getTitularidadExtendido().getCtaDestino().getNumeroCBU();
            IdentificacionCuenta iCuentaDestinoConsulta = ISBANStringUtils.obtenerCuentaDesdeCBU(cbuDestinoRioConsulta);
            IdentificacionCuenta iCuentaDestinoActual = ISBANStringUtils
                    .cargarCuentaDestinatario(transferenciaView.getNroCuentaDestino());
            if (!iCuentaDestinoConsulta.equals(iCuentaDestinoActual)) {
                actualizarDatosAgendadosConLosDeTitularidadAlias(transferenciaView, response);
                crearDTO(response, transferenciaView.getIsRioRio(), cuentasClienteFiltradas, cliente, transferenciaView,
                        cuentaSeleccionada);
                transferenciaView.setWarningConAlias(true);
                TransferenciaUtil.cargarHashDeLaVistaEnSesion(transferenciaView, sesionParametros);
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DATOS_MODIFICADOS_DEL_ALIAS_CONSULTA,
                        CodigoMensajeConstantes.CODIGO_DATOS_DESTINATARIO_DE_ALIAS_MODIFICADOS);
            } else {
                return null;
            }
        } else {
            if (!transferenciaView.getCbu().equals(response.getTitularidadExtendido().getCtaDestino().getNumeroCBU())) {
                actualizarDatosAgendadosConLosDeTitularidadAlias(transferenciaView, response);
                crearDTO(response, transferenciaView.getIsRioRio(), cuentasClienteFiltradas, cliente, transferenciaView,
                        cuentaSeleccionada);
                transferenciaView.setWarningConAlias(true);
                TransferenciaUtil.cargarHashDeLaVistaEnSesion(transferenciaView, sesionParametros);
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DATOS_MODIFICADOS_DEL_ALIAS_CONSULTA,
                        CodigoMensajeConstantes.CODIGO_DATOS_DESTINATARIO_DE_ALIAS_MODIFICADOS);
            } else {
                return null;
            }
        }
    }

    /**
	 * Actualizar datos agendados con los de titularidad alias.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param response
	 *            the response
	 */
    private void actualizarDatosAgendadosConLosDeTitularidadAlias(TransferenciaView transferenciaView,
            ConsultarDatosTitularidadExtendidoResponse response) {
        TitularidadExtendido titularidadExtendido = response.getTitularidadExtendido();
        String moneda = titularidadExtendido.getCtaDestino().getMoneda().getCodigo().equals(UNO)
                ? DivisaEnum.PESO.getMoneda() : DivisaEnum.DOLAR.getMoneda();

        transferenciaView.setTitular(titularidadExtendido.getNombreTitular());
        transferenciaView.setCuit(titularidadExtendido.getCuits().get(0));
        if (!transferenciaView.getIsRioRio()) {
            transferenciaView.setCbu(titularidadExtendido.getCtaDestino().getNumeroCBU());
            if (!ISBANStringUtils.validarCVU(titularidadExtendido.getCtaDestino().getNumeroCBU())) {
            	transferenciaView.setBanco(titularidadExtendido.getNombreBanco());
            } else {
            	transferenciaView.setBanco("-");
            }
        } else {
            transferenciaView.setNroCuentaDestino(ISBANStringUtils
                    .obtenerCuentaDesdeCBU(titularidadExtendido.getCtaDestino().getNumeroCBU()).toString());
            TipoCuenta tipoCuentaDestino = TransferenciaUtil
                    .obtenerTipoCuentaDesdeCBU(titularidadExtendido.getCtaDestino().getNumeroCBU());
            tipoCuentaDestino = tipoCuentaDestino.equals(TipoCuenta.CUENTA_UNICA) ? matchearTipoCuentaDestino(moneda)
                    : tipoCuentaDestino;
            transferenciaView.setTipoCuentaDestinoDescripcion(tipoCuentaDestino.getDescripcion());
            transferenciaView.setTipoCuentaDestino(tipoCuentaDestino.getAbreviatura());
            transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        }
    }

    /**
     * Gets the nombre moneda singular.
     *
     * @param moneda
     *            the moneda
     * @return the nombre moneda singular
     */
    private DivisaEnum getNombreMonedaSingular(String moneda) {
        DivisaEnum divisaEnum = null;
        if (moneda.equalsIgnoreCase(PESOS) || moneda.equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
            divisaEnum = DivisaEnum.PESO;
        } else if (moneda.equalsIgnoreCase(DOLARES) || moneda.equalsIgnoreCase(DivisaEnum.DOLAR.getMoneda())) {
            divisaEnum = DivisaEnum.DOLAR;
        }
        return divisaEnum;
    }

    /**
     * Armar respuestas warning esperadas para el caso de Nueva Tx.
     *
     * @param response
     *            the response
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> armarRespuestasWarningEsperada(
            ConsultarDatosTitularidadExtendidoResponse response, boolean isFromAgendaDestinatario) {
        String codigo = response.getCodigo();

        if (isFromAgendaDestinatario) {
            return crearRespuestaSegunCodigoDeResponseDesdeAgenda(codigo);
        } else {
            return crearRespuestaSegunCodigoDeResponse(codigo);
        }

    }

    /**
     * Crear respuesta segun codigo de response.
     *
     * @param codigo
     *            the codigo
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> crearRespuestaSegunCodigoDeResponse(String codigo) {
        if (StringUtils.equals(codigo, CUENTA_INACTIVA)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_CUENTA_INACTIVA,
                    CodigoMensajeConstantes.ALIAS_CON_CUENTA_INACTIVA);
        } else if (StringUtils.equals(codigo, ALIAS_ELIMINADO)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_ELIMINADO,
                    CodigoMensajeConstantes.ALIAS_INEXISTENTE_ELIMINADO);
        } else if (StringUtils.equals(codigo, MONEDA_INCOMPATIBLE)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_NO_COINCIDE_MONEDA,
                    CodigoMensajeConstantes.CLIENTE_NO_COINCIDE_MONEDA);
        } else if (StringUtils.equals(codigo, ALIAS_NO_EXISTE)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_INEXISTENTE,
                    CodigoMensajeConstantes.FORMATO_ALIAS_INEXISTENTE);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_GENERICO);
        }
    }

    /**
     * Crear respuesta segun codigo de response desde agenda.
     *
     * @param codigo
     *            the codigo
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> crearRespuestaSegunCodigoDeResponseDesdeAgenda(String codigo) {
        if (StringUtils.equals(codigo, CUENTA_INACTIVA)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_CUENTA_INACTIVA,
                    CodigoMensajeConstantes.ALIAS_INEXISTENTE);
        } else if (StringUtils.equals(codigo, ALIAS_ELIMINADO)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_ELIMINADO,
                    CodigoMensajeConstantes.ALIAS_INEXISTENTE_O_ELIMINADO_DESDE_AGENDA);
        } else if (StringUtils.equals(codigo, MONEDA_INCOMPATIBLE)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_NO_COINCIDE_MONEDA,
                    CodigoMensajeConstantes.CLIENTE_NO_COINCIDE_MONEDA);
        } else if (StringUtils.equals(codigo, ALIAS_NO_EXISTE)) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ALIAS_INEXISTENTE,
                    CodigoMensajeConstantes.ALIAS_INEXISTENTE_O_ELIMINADO_DESDE_AGENDA);
        } else {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_NO_CONTEMPLADO,
                    CodigoMensajeConstantes.ERROR_NO_CONTEMPLADO_TITULARIDAD_ALIAS);
        }
    }

    /**
     * Crear respuesta OK. con DTO y mensage.
     *
     * @param response
     *            the response
     * @param esRio
     *            the es rio
     * @param cuentasClienteFiltradas
     *            the cuentas cliente filtradas
     * @param cliente
     *            the cliente
     * @param transferenciaView
     *            the transferencia view
     * @param cuentaSeleccionada
     *            the cuenta seleccionada
     * @return the respuesta
     */
    private Respuesta<TransferenciaDTO> crearRespuestaOK(ConsultarDatosTitularidadExtendidoResponse response,
            boolean esRio, List<CuentasAdhesionDebitoView> cuentasClienteFiltradas, Cliente cliente,
            TransferenciaView transferenciaView, CuentasAdhesionDebitoView cuentaSeleccionada) {
        TransferenciaDTO dto = crearDTO(response, esRio, cuentasClienteFiltradas, cliente, transferenciaView,
                cuentaSeleccionada);
        return respuestaFactory.crearRespuestaOk(dto, "", response.getMensaje());
    }

    /**
     * Crear DTO.
     *
     * @param response
     *            the response
     * @param esRio
     *            the es rio
     * @param cuentasClienteFiltradas
     *            the cuentas cliente filtradas
     * @param cliente
     *            the cliente
     * @param transferenciaView
     *            the transferencia view
     * @param cuentaSeleccionada
     *            the cuenta seleccionada
     * @return the transferencia DTO
     */
    private TransferenciaDTO crearDTO(ConsultarDatosTitularidadExtendidoResponse response, Boolean esRio,
            List<CuentasAdhesionDebitoView> cuentasClienteFiltradas, Cliente cliente,
            TransferenciaView transferenciaView, CuentasAdhesionDebitoView cuentaSeleccionada) {

        TitularidadExtendido titularExtendido = response.getTitularidadExtendido();
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setHaciaOtroBanco(!esRio);
        String moneda = titularExtendido.getCtaDestino().getMoneda().getCodigo().equals(UNO)
                ? DivisaEnum.PESO.getMoneda() : DivisaEnum.DOLAR.getMoneda();
        transferenciaDTO.setHaciaCuentaPropia(TransferenciaUtil.esCuentaPropiaCBU(cliente,
                response.getTitularidadExtendido().getCtaDestino().getNumeroCBU()));
        transferenciaDTO.setMoneda(DivisaEnum.fromMonedaString(moneda));

        if (esRio && !ISBANStringUtils.validarCBUCtaRecaudadora(titularExtendido.getCtaDestino().getNumeroCBU())) {
            transferenciaDTO.setNumeroCuentaDestino(
                    ISBANStringUtils.obtenerCuentaDesdeCBU(titularExtendido.getCtaDestino().getNumeroCBU()));
            TipoCuenta tipoCuentaDestino = TransferenciaUtil
                    .obtenerTipoCuentaDesdeCBU(titularExtendido.getCtaDestino().getNumeroCBU());
            tipoCuentaDestino = tipoCuentaDestino.equals(TipoCuenta.CUENTA_UNICA) ? matchearTipoCuentaDestino(moneda)
                    : tipoCuentaDestino;
            transferenciaDTO.setTipoCuentaDestino(tipoCuentaDestino);
        } else {
            transferenciaDTO.setCbuCuenta(titularExtendido.getCtaDestino().getNumeroCBU());
            transferenciaDTO.setTipoCuentaDestino(obtenerTipoCuentaPorMonedaYporCuenta(moneda,
                    titularExtendido.getCtaDestino().getTipo().getCodigo()));

        }

        transferenciaDTO.setCuit(titularExtendido.getCuits().get(0));
        if (!ISBANStringUtils.validarCVU(titularExtendido.getCtaDestino().getNumeroCBU())) {
        	transferenciaDTO.setBancoDestino(titularExtendido.getNombreBanco());
        } else {
        	transferenciaDTO.setBancoDestino("-");
        }
        transferenciaDTO.setTitular(titularExtendido.getNombreTitular());
        transferenciaDTO.setErrorBanelco(false);
        transferenciaDTO.setCelularMyA(false);
        transferenciaDTO.setAlias(titularExtendido.getAlias());
        transferenciaDTO.setCuentaDestinoBanelco(titularExtendido.getCtaDestino().getNumero());
        transferenciaDTO
                .setTipoDeCuentaFromBanelco(TipoCuentaBanelco.buscarPorCodigoTrfcci(titularExtendido.getCtaDestino()
                        .getTipo().getCodigo().concat(titularExtendido.getCtaDestino().getMoneda().getCodigo())));
        TipoCuenta tipoCuentaOrigen;
        if (transferenciaView.isFromAgendaDestinatario()) {
            tipoCuentaOrigen = TipoCuenta.fromDescripcionConMoneda(transferenciaView.getTipoCuentaDescripcion());
        } else {
            tipoCuentaOrigen = TipoCuenta.fromAbreviatura(cuentaSeleccionada.getAbreviaturaTipoCuenta());
        }
        if (tipoCuentaOrigen.equals(TipoCuenta.CUENTA_UNICA)) {
            tipoCuentaOrigen = TransferenciaUtil.matchearTipoCuentaDestino(transferenciaView.getMoneda());
            transferenciaDTO.setTipoDeCuentaToBanelco(TipoCuentaBanelco
                    .buscarPorCodigoTrfcci(matchearCodigoTipoCuentaConTipoCuentaBanelco(tipoCuentaOrigen)));
        } else {
            transferenciaDTO.setTipoDeCuentaToBanelco(TipoCuentaBanelco
                    .buscarPorCodigoTrfcci(matchearCodigoTipoCuentaConTipoCuentaBanelco(tipoCuentaOrigen)));
        }
        transferenciaDTO.setFiid(titularExtendido.getFiidOrigenLink());
        transferenciaDTO.setBancoReceptor(titularExtendido.getFiidBanco());
        transferenciaDTO.setUser("");
        transferenciaDTO.setCuentasSaldosFiltrada(cuentasClienteFiltradas);
        guardarDestinatarioDTOenSesion(transferenciaDTO);
        return transferenciaDTO;
    }

    /**
     * Guardar destinatario DT oen sesion.
     *
     * @param transferenciaDTO
     *            the transferencia DTO
     */
    private void guardarDestinatarioDTOenSesion(TransferenciaDTO transferenciaDTO) {
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setBancoReceptor(transferenciaDTO.getBancoReceptor());
        destinatarioDTO.setCuentaDestinoBanelco(transferenciaDTO.getCuentaDestinoBanelco());
        destinatarioDTO.setCuit(transferenciaDTO.getCuit());
        destinatarioDTO.setCuit2(transferenciaDTO.getCuit2());
        destinatarioDTO.setCuit3(transferenciaDTO.getCuit3());
        destinatarioDTO.setFiid(transferenciaDTO.getFiid());
        destinatarioDTO.setLongCuentaDestinoBanelco(transferenciaDTO.getLongitudCuentaDestinoBanelco());
        destinatarioDTO.setTipoDeCuentaFromBanelco(transferenciaDTO.getTipoDeCuentaFromBanelco());
        destinatarioDTO.setTipoDeCuentaToBanelco(transferenciaDTO.getTipoDeCuentaToBanelco());
        destinatarioDTO.setTitular(transferenciaDTO.getTitular());
        destinatarioDTO.setUser(transferenciaDTO.getUser());
        destinatarioDTO.setBancoDestino(transferenciaDTO.getBancoDestino());
        sesionParametros.setDestinatarioView(destinatarioDTO);
    }

    /**
     * Matchear codigo tipo cuenta con tipo cuenta banelco.
     *
     * @param tipoCuentaOrigen
     *            the tipo cuenta origen
     * @return the string
     */
    private String matchearCodigoTipoCuentaConTipoCuentaBanelco(TipoCuenta tipoCuentaOrigen) {
        String codigoTipoCuentaBanelco = "";
        if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "01";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CAJA_AHORRO_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "11";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "02";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CAJA_AHORRO_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "12";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo())) {
            codigoTipoCuentaBanelco = "01";
        } else if (tipoCuentaOrigen.getCodigo().equals(TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo())) {
            codigoTipoCuentaBanelco = "02";
        }
        return codigoTipoCuentaBanelco;
    }

    /**
     * Matchear tipo cuenta destino.
     *
     * @param moneda
     *            the moneda
     * @return the tipo cuenta
     */
    public TipoCuenta matchearTipoCuentaDestino(String moneda) {
        if (DivisaEnum.PESO.getMoneda().equalsIgnoreCase(moneda)) {
            return TipoCuenta.CUENTA_UNICA_PESOS;
        } else {
            return TipoCuenta.CUENTA_UNICA_DOLARES;
        }
    }

    /**
     * Obtener tipo cuenta por moneda y codigo de cuenta en Alias.
     *
     * @param moneda
     *            the moneda
     * @param codigoCuenta
     *            the codigo cuenta
     * @return the tipo cuenta
     */
    public TipoCuenta obtenerTipoCuentaPorMonedaYporCuenta(String moneda, String codigoCuenta) {
        if (moneda.equalsIgnoreCase(DivisaEnum.PESO.getMoneda())) {
            if (codigoCuenta.equals(CERO)) {
                return TipoCuenta.CUENTA_CORRIENTE_PESOS;
            } else {
                return TipoCuenta.CAJA_AHORRO_PESOS;
            }
        } else {
            if (codigoCuenta.equals(CERO)) {
                return TipoCuenta.CUENTA_CORRIENTE_DOLARES;
            } else {
                return TipoCuenta.CAJA_AHORRO_DOLARES;
            }
        }
    }

    /**
     * Verificar CBU respuesta WS no corresponda con la cuenta propia
     * encontrada.
     *
     * @param cliente
     *            the cliente
     * @param titularidadExtendido
     *            the titularidad extendido
     * @param cbuCuentaSeleccionada
     *            the cbu cuenta seleccionada
     * @param isFromAgendaDestinatario
     *            the is from agenda destinatario
     * @throws AliasCorrespondienteCuentaPropiaException
     *             the alias correspondiente cuenta propia exception
     * @throws AliasCorrespondienteCuentaPropiaUnicaException
     *             the alias correspondiente cuenta propia unica exception
     */
    private void verificarCBURespuestaWSNoCorrespondaCuentaPropia(Cliente cliente,
            TitularidadExtendido titularidadExtendido, String cbuCuentaSeleccionada, boolean isFromAgendaDestinatario)
            throws AliasCorrespondienteCuentaPropiaException, AliasCorrespondienteCuentaPropiaUnicaException {
        String moneda = titularidadExtendido.getCtaDestino().getMoneda().getCodigo();
        if (StringUtils.equals(titularidadExtendido.getCtaDestino().getNumeroCBU(), cbuCuentaSeleccionada)) {
            if ((TransferenciaUtil.obtenerCantidadDeCuentasPesos(cliente.getCuentas()) <= 1 && moneda.equals(UNO))
                    || (TransferenciaUtil.obtenerCantidadDeCuentasDolares(cliente.getCuentas()) <= 1
                            && moneda.equals(DOS))) {
                throw new AliasCorrespondienteCuentaPropiaUnicaException();
            } else if (isFromAgendaDestinatario) {
                throw new AliasCorrespondienteCuentaPropiaException();
            }
        }
    }
    
    private Respuesta<List<ResumenDetalleCuenta>> obtenerCuentasySaldosBP(){
    	
		List<Cuenta> cuentasBP = sesionCliente.getCliente().getCuentasPrivadas();
		List<CuentaIntermedioDTO> saldosCuentaBP = new ArrayList<CuentaIntermedioDTO>();
		
		try {
			saldosCuentaBP = cuentasBancaPrivadaBO.obtenerSaldoServicioIatx(sesionCliente.getCliente());
		} catch (SQLException e) {
			LOGGER.info("SQL Exception. consultarSaldosCuenta   ", e);
		} 
		
		for (CuentaIntermedioDTO cuentaDTO: saldosCuentaBP) {
			//si alguna cuentaDTO tiene un error en la consulta de saldo, grabo estadisticas ERROR y retorno warning. Flujo alternativo
			if(cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta()) {
				LOGGER.info("ERROR al consultarSaldosCuenta: cuentaDTO.getSaldosServicioIatx().getErrorEnConsulta() ");
				estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_TRANSFERENCIA_SALDOS_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		}
		//si ninguna cuentaDTO tiene un error en la consulta de saldo, grabo estadisticas OK. Flujo normal
		estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_TRANSFERENCIA_SALDOS_BP, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		
		List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
		for (Cuenta cuentaBP : cuentasBP) {
			
			//filtro las cuentas de banca privada que necesito
			if( "07".equals(cuentaBP.getProductoAltair()) && 
					 CuentasBancaPrivadaUtil.isCuentaPrivada(cuentaBP)) {
				
				ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
	            ConsultaSaldoCtasConAperturaOutEntity saldoCuenta = null;
				for (CuentaIntermedioDTO saldoCuentaBP : saldosCuentaBP) {
					//obtengo los salgos de las banca privada filtradas
					if (cuentaBP.obtenerNroCuentaFormateado().equals(saldoCuentaBP.getNumeroCuenta())) {
						saldoCuenta = saldoCuentaBP.getSaldosServicioIatx();
	                    resumenDetalleCuenta = CuentasBancaPrivadaUtil.initResumenDetalleCuenta(cuentaBP, saldoCuenta);
	                }
				}
				
				respuestaDetalleCuenta.add(resumenDetalleCuenta);
	            resumenDetalleCuenta.setAlias(null);
	            resumenDetalleCuenta.setFavorita(Boolean.FALSE);
			}
		}

		return respuestaFactory.crearRespuestaOk(respuestaDetalleCuenta);
	}
}
