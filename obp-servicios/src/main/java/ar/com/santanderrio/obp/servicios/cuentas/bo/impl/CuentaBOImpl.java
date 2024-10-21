/*
 *
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.delete.account.connector.DeleteAccountConnector;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountRequest;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountResponse;
import ar.com.santanderrio.obp.servicios.delete.account.model.Error;
import ar.com.santanderrio.obp.servicios.delete.account.utils.AccountUtils;
import ar.com.santanderrio.obp.servicios.delete.account.config.InvalidanteConfig;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.DeleteAccountResponseDTO;
import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorCodeEnum;
import ar.com.santanderrio.obp.servicios.delete.account.web.enums.ErrorLevel;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.Invalidante;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.citi.dao.CuentasCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiMigrada;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiInEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentasCitiOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaCerradaBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ReporteCBUCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaInexistenteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaMigradaException;

@Component
public class CuentaBOImpl implements CuentaBO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuentaBOImpl.class);

    private static final String CUENTAS_TAG = "cuentas[";
    private static final String TAG_FIN = "]";

    private static final String ERROR_AL_OBTENER_RESUMEN = "Error al obtener resumen";
    private static final String ERROR_AL_OBTENER_SALDOS_CONSOLIDADOS_ACTUALIZADOS = "Error al obtenerSaldosConsolidadosActualizados()";
    private static final String ERROR_NO_HAY_CTA_PRINCIPAL = "No hay cuenta principal";
    private static final String ERROR_ERROR_CTASDOM = "Error al consultar saldo de cuentas.";

    private static final String GUION = "-";
    private static final String MONEDA_DOLAR = "USD";
    private static final String MONEDA_PESOS = "ARS";

    private static final String TIPOCTA_VISARECARGABLE = "77";
    private static final String ENTIDAD = "0072";

    private static final int NUMERO_UNO = 1;
    private static final int MAX_ALIAS_LENGTH = 18;
    private static final int PLAZO_MAX_CTA_CERRADA = -60;
    private static final int FECHA_DESDE_MOVIMIENTO = -60;

    private static final String PLACEHOLDER_PARAM = "{0}";

    /**
     * The Constant ERROR_CUENTA_MIGRADA.
     */
    private static final String PATTERN_MSG_CTA_MIGRADA = "la cuenta.+([0-9]{3,4}-[0-9]{6}/[0-9]{1}) se encuentra migrada"
            + ".+continuadora es.+([0-9]{3,4}-[0-9]{6}/[0-9]{1})\\."; // Cierre de sucursales

    @Autowired
    private DetalleCuentaDAO detalleCuentaDAO;

    @Autowired
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    @Autowired
    private CuentaCerradaBO cuentaCerradaBO;

    @Autowired
    private BanelcoDAO banelcoDAO;

    @Autowired
    private ReporteCBUCuentaDAO reporteCBUCuentaDAO;

    @Autowired
    private CuentasCitiDAO cuentasCitiDAO;

    @Autowired
    private DeleteAccountConnector deleteCuentaConnector;

    @Autowired
    private MensajeBO mensajeBO;

    @Autowired
    private RespuestaFactory respuestaFactory;

    private SimpleDateFormat sfCerradaSinGuiones = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    protected SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerInfoCuentas(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<List<ResumenDetalleCuenta>> obtenerInfoCuentas(Cliente cliente) throws BusinessException {

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas;
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        List<ResumenDetalleCuenta> listaResumenDetalleCuentas = new ArrayList<ResumenDetalleCuenta>();

        obtenerCuentas(cliente, itemsMensajeRespuesta, listaResumenDetalleCuentas);
        try {
            obtenerDetalleCuentasCerradas(cliente, itemsMensajeRespuesta, listaResumenDetalleCuentas);
        } catch (BusinessException e) {
            itemsMensajeRespuesta.add(respuestaFactory.generarItemMensajeRespuesta(null, TipoError.ERROR_ITEM_CUENTA,
                    CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDO));
        }
        if (itemsMensajeRespuesta.isEmpty()) {

            respuestaDetalleCuentas = respuestaFactory.crearRespuestaOk(listaResumenDetalleCuentas);
            respuestaDetalleCuentas.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        } else {
            respuestaDetalleCuentas = respuestaFactory.crearRespuestaWarning(listaResumenDetalleCuentas,
                    itemsMensajeRespuesta);
        }

        return respuestaDetalleCuentas;
    }

    /**
     * Obtener cuentas.
     *
     * @param cliente                    the cliente
     * @param itemsMensajesErrores       the items mensajes errores
     * @param listaResumenDetalleCuentas the lista resumen detalle cuentas
     */
    private void obtenerCuentas(Cliente cliente, List<ItemMensajeRespuesta> itemsMensajesErrores,
                                List<ResumenDetalleCuenta> listaResumenDetalleCuentas) {
        Boolean hayFavorita = Boolean.FALSE;
        Cuenta cuentaPrincipal = null;
        ResumenDetalleCuenta resumenPrincipal = null;
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.isTipoCuentaMonetaria()) {
                ResumenDetalleCuenta resumenDetalleCuenta = null;
                try {
                    resumenDetalleCuenta = obtenerResumenDetalleCuenta(cuenta);
                } catch (DAOException e) {
                    LOGGER.error("Error al obtener Resumen", e);

                    resumenDetalleCuenta = initResumenDetalleCuenta(cuenta);
                    resumenDetalleCuenta.setCbu(cuenta.getCbu());
                    resumenDetalleCuenta.setCuentaCerrada(false);
                    itemsMensajesErrores.add(respuestaFactory.generarItemMensajeRespuesta(cuenta.getNumeroCuentaTag(),
                            TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDO));
                }
                Calendar calendar = Calendar.getInstance();
                resumenDetalleCuenta.setFechaHastaMovimiento(ISBANStringUtils.formatearFecha(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_YEAR, FECHA_DESDE_MOVIMIENTO);
                resumenDetalleCuenta.setFechaDesdeMovimiento(ISBANStringUtils.formatearFecha(calendar.getTime()));
                resumenDetalleCuenta.setProductoAlt(cuenta.getProductoAltair());
                resumenDetalleCuenta.setSubProductoAlt(cuenta.getSubproductoAltair());
                Map<String, AliasFavoritoProducto> aliasPorNroProducto = getAliasPorNroCuentaProducto(cliente);
                if (aliasPorNroProducto != null) {

                    AliasFavoritoProducto aliasFavorito = aliasPorNroProducto
                            .get(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()));
                    if (aliasFavorito != null) {

                        resumenDetalleCuenta.setAlias(aliasFavorito.getAlias());
                        resumenDetalleCuenta.setFavorita(Boolean.TRUE.equals(aliasFavorito.getFavorita()));
                        cuenta.setAlias(aliasFavorito.getAlias());
                        cuenta.setIsFavorita(Boolean.TRUE.equals(aliasFavorito.getFavorita()));
                        hayFavorita = hayFavorita || Boolean.TRUE.equals(aliasFavorito.getFavorita());
                    } else {
                        resumenDetalleCuenta.setAlias(null);
                        resumenDetalleCuenta.setFavorita(Boolean.FALSE);
                        cuenta.setAlias(null);
                        cuenta.setIsFavorita(Boolean.FALSE);
                    }
                    if (cuenta.isCuentaPrincipal()) {

                        cuentaPrincipal = cuenta;
                        resumenPrincipal = resumenDetalleCuenta;
                    }
                }
                listaResumenDetalleCuentas.add(resumenDetalleCuenta);
            }

        }
        if (!hayFavorita && cuentaPrincipal != null && resumenPrincipal != null) {

            cuentaPrincipal.setIsFavorita(Boolean.TRUE);
            resumenPrincipal.setFavorita(Boolean.TRUE);
        }
        if (listaResumenDetalleCuentas.size() == 1) {
            listaResumenDetalleCuentas.get(0).setFavorita(Boolean.FALSE);
        }
    }

    /**
     * Gets the alias por nro cuenta producto.
     *
     * @param cliente the cliente
     * @return the alias por nro cuenta producto
     */
    private Map<String, AliasFavoritoProducto> getAliasPorNroCuentaProducto(Cliente cliente) {
        Map<String, AliasFavoritoProducto> aliasPorNroProducto = null;
        List<AliasFavoritoProducto> listAliasFavoritos = obtenerAliasYFavoritos(cliente.getNup());
        if (listAliasFavoritos != null) {
            aliasPorNroProducto = new HashMap<String, AliasFavoritoProducto>();
            for (AliasFavoritoProducto aliasFavoritoProducto : listAliasFavoritos) {
                aliasPorNroProducto.put(ISBANStringUtils.eliminarCeros(aliasFavoritoProducto.getNroCtaProducto()),
                        aliasFavoritoProducto);
            }
        }
        return aliasPorNroProducto;
    }

    /**
     * Obtener resumen detalle cuenta.
     *
     * @param cuenta the cuenta
     * @return the resumen detalle cuenta
     * @throws DAOException the DAO exception
     */
    private ResumenDetalleCuenta obtenerResumenDetalleCuenta(Cuenta cuenta) throws DAOException {
        ResumenDetalleCuenta resumenDetalleCuenta = initResumenDetalleCuenta(cuenta);
        ConsultaDetalleCuentaInEntity consultaDetalleCuentaIn = getConsultaDetalleCuentaInEntity(cuenta);
        ConsultaDetalleCuentaOutEntity detalleCuenta = detalleCuentaDAO.consultaDetalleCuenta(consultaDetalleCuentaIn);
        return generarResumenDetalleCuenta(resumenDetalleCuenta, cuenta, detalleCuenta);
    }

    /**
     * Obtener detalle cuentas cerradas.
     *
     * @param cliente                    the cliente
     * @param itemsMensajesErrores       the items mensajes errores
     * @param listaResumenDetalleCuentas the lista resumen detalle cuentas
     * @throws BusinessException the business exception
     */
    private void obtenerDetalleCuentasCerradas(Cliente cliente, List<ItemMensajeRespuesta> itemsMensajesErrores,
                                               List<ResumenDetalleCuenta> listaResumenDetalleCuentas) throws BusinessException {

        Respuesta<List<CuentaCerrada>> respuestaCuentasCerradas = obtenerCuentascerradas(cliente);

        if (EstadoRespuesta.ERROR.equals(respuestaCuentasCerradas.getEstadoRespuesta())) {
            throw new BusinessException();
        }
        if (EstadoRespuesta.WARNING.equals(respuestaCuentasCerradas.getEstadoRespuesta())) {
            return;
        }

        for (CuentaCerrada cuentaCerrada : cliente.getCuentasCerradas()) {

            ResumenDetalleCuenta resumenDetalleCuenta = obtenerResumenDetalleCuentaCerrada(itemsMensajesErrores,
                    cuentaCerrada);
            listaResumenDetalleCuentas.add(resumenDetalleCuenta);
        }
    }

    /**
     * Obtener resumen detalle cuenta cerrada.
     *
     * @param itemsMensajesErrores the items mensajes errores
     * @param cuentaCerrada        the cuenta cerrada
     * @return the resumen detalle cuenta
     * @throws BusinessException the business exception
     */
    private ResumenDetalleCuenta obtenerResumenDetalleCuentaCerrada(List<ItemMensajeRespuesta> itemsMensajesErrores,
                                                                    CuentaCerrada cuentaCerrada) throws BusinessException {
        ResumenDetalleCuenta resumenDetalleCuenta = initResumenDetalleCuenta(cuentaCerrada);
        try {
            resumenDetalleCuenta.setFechaHastaMovimiento(
                    ISBANStringUtils.formatearFecha(sfCerradaSinGuiones.parse(cuentaCerrada.getFechaBajaContrato())));
            resumenDetalleCuenta.setMotivoCuentaCerrada(cuentaCerrada.getMotivoBaja());
        } catch (ParseException e) {
            throw new BusinessException(e);
        }
        if (verificarCuentaCerradaExcedente(cuentaCerrada)) {
            itemsMensajesErrores.add(respuestaFactory.generarItemMensajeRespuesta(cuentaCerrada.getNumeroCuentaTag(),
                    TipoError.ERROR_CUENTA_CERRADA_MAYOR_60_DIAS,
                    CodigoMensajeConstantes.CODIGO_ERROR_CUENTA_CERRADA_MAYOR_60_DIAS));
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, FECHA_DESDE_MOVIMIENTO);
            resumenDetalleCuenta.setFechaDesdeMovimiento(ISBANStringUtils.formatearFecha(calendar.getTime()));
        }

        return resumenDetalleCuenta;
    }

    /**
     * Verificar cuenta cerrada excedente.
     *
     * @param cuentaCerrada the cuenta cerrada
     * @return true, if successful
     * @throws BusinessException the business exception
     */
    private boolean verificarCuentaCerradaExcedente(CuentaCerrada cuentaCerrada) throws BusinessException {
        try {
            Calendar fechaLimite = Calendar.getInstance();
            limpiarHoras(fechaLimite);
            // poner fecha limite a las 00:00:00

            fechaLimite.add(Calendar.DAY_OF_YEAR, PLAZO_MAX_CTA_CERRADA);
            Calendar fechaCierre = Calendar.getInstance();
            String fcierre = cuentaCerrada.getFechaBajaContrato().replaceAll("-", "");
            Date fechaCierreDate = sfCerradaSinGuiones.parse(fcierre);
            fechaCierre.setTime(fechaCierreDate);
            limpiarHoras(fechaCierre);

            return fechaLimite.compareTo(fechaCierre) > 0;

        } catch (ParseException ex) {
            throw new BusinessException(ex, ex.getMessage());
        }
    }

    /**
     * Limpiar horas.
     *
     * @param fecha the fecha
     */
    private void limpiarHoras(Calendar fecha) {
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Obtener cuentascerradas.
     *
     * @param cliente the cliente
     * @return the respuesta
     */
    private Respuesta<List<CuentaCerrada>> obtenerCuentascerradas(Cliente cliente) {

        Respuesta<List<CuentaCerrada>> respuestaCuentasCerradas;

        if (cliente.getCuentasCerradas() == null) {

            respuestaCuentasCerradas = cuentaCerradaBO.obtenerCuentasCerradas(cliente);
            if (respuestaCuentasCerradas != null
                    && EstadoRespuesta.ERROR.equals(respuestaCuentasCerradas.getEstadoRespuesta())) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ITEM_CUENTA,
                        StringUtils.EMPTY);
            }
            if (respuestaCuentasCerradas == null
                    || !EstadoRespuesta.OK.equals(respuestaCuentasCerradas.getEstadoRespuesta())) {
                return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.WARNING,
                        StringUtils.EMPTY);
            }
            cliente.setCuentasCerradas(respuestaCuentasCerradas.getRespuesta());
        } else {
            respuestaCuentasCerradas = respuestaFactory.crearRespuestaOk(cliente.getCuentasCerradas());
        }
        return respuestaCuentasCerradas;
    }

    @Override
    public List<AliasFavoritoProducto> obtenerAliasYFavoritos(String nup) {

        List<AliasFavoritoProducto> aliasFavoritos;
        try {
            aliasFavoritos = aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(Long.parseLong(nup));
        } catch (DAOException e) {
            LOGGER.error("Error al obtener Alias Favorito", e);
            aliasFavoritos = null;
        }
        return aliasFavoritos;
    }

    @Override
    public Respuesta<DeleteAccountResponseDTO> deleteCuenta(DeleteAccountRequest request, Cuenta cuenta) throws BusinessException {
        final Respuesta<DeleteAccountResponseDTO> responseWrapper = new Respuesta<DeleteAccountResponseDTO>();
        final DeleteAccountResponseDTO responseData = new DeleteAccountResponseDTO();
        responseData.setTipoInvalidante(TipoError.INVALIDANTES_SUCURSAL);

        try {
            final DeleteAccountResponse response = deleteCuentaConnector.deleteAccountById(request);

            if (StringUtils.isEmpty(response.getCode()) && StringUtils.isEmpty(response.getMessage())) {
                mapInvalidantes(ErrorLevel.WARNING, response.getWarnings(), response, responseData, cuenta);
                boolean isWarning = AccountUtils.containsErrorType(response.getWarnings(), Arrays.asList(ErrorCodeEnum.BALANCES_ARS,
                        ErrorCodeEnum.BALANCES_USD));
                responseData.setNewAccountNumber(response.getNewAccountNumber());
                responseWrapper.setEstadoRespuesta(isWarning ? EstadoRespuesta.WARNING : EstadoRespuesta.OK);
                responseWrapper.setItemMensajeRespuesta(isWarning ? Collections.singletonList(respuestaFactory.crearItemMensajeRespuesta(
                        null, TipoError.INVALIDANTES_SALDO_POSITIVO, null)) : null);
            }

            if (StringUtils.isNotEmpty(response.getCode())) {
                if (ErrorCodeEnum.ACCOUNT_CLOSURE.getCode().equalsIgnoreCase(response.getCode())) {
                    mapInvalidantes(ErrorLevel.ERROR, response.getErrors(), response, responseData, cuenta);
                    mapInvalidantes(ErrorLevel.WARNING, response.getWarnings(), response, responseData, cuenta);
                    responseWrapper.setEstadoRespuesta(EstadoRespuesta.ERROR);
                    responseWrapper.setItemMensajeRespuesta(Collections.singletonList(respuestaFactory.crearItemMensajeRespuesta(
                            null, responseData.getTipoInvalidante(), null)));
                } else {
                    throw new BusinessException(response.getCode());
                }
            }
            responseWrapper.setRespuesta(responseData);
            return responseWrapper;
        } catch (DAOException e) {
            LOGGER.error("delete account: Response Code: {}", e.getErrorCode(), e);
            throw new BusinessException(e, e.getMessage());
        }
    }

    private void mapInvalidantes(ErrorLevel errorLevel, List<Error> invalidantes, DeleteAccountResponse source, DeleteAccountResponseDTO responseData,
                                 Cuenta cuenta) {
        List<String> monedasBalancesPositivos = new ArrayList<String>();
        if (invalidantes == null || invalidantes.isEmpty()) {
            return;
        }

        for (Error error : invalidantes) {
            if (errorLevel.equals(ErrorLevel.WARNING) && !InvalidanteConfig.isAllowedWarning(error.getType())) {
                continue;
            }
            Invalidante invalidante = mapInvalidante(errorLevel, error);

            if (ErrorCodeEnum.BALANCES_ARS.equals(error.getType())) {
                monedasBalancesPositivos.add(DivisaEnum.PESO.getSimbolo());
                String mensaje = invalidante.getLabel().replace(PLACEHOLDER_PARAM,
                        ISBANStringUtils.formatearSaldoSinAbsConDivisa(new BigDecimal(cuenta.obtenerSaldo()), DivisaEnum.PESO));
                invalidante.setLabel(mensaje);
            }

            if (ErrorCodeEnum.UNPAYMENT_ARS.equals(error.getType())) {
                monedasBalancesPositivos.add(DivisaEnum.DOLAR.getSimbolo());
                String mensaje = invalidante.getLabel().replace(PLACEHOLDER_PARAM,
                        ISBANStringUtils.formatearSaldoSinAbsConDivisa(new BigDecimal(cuenta.obtenerSaldo()), DivisaEnum.PESO));
                invalidante.setLabel(mensaje);
            }

            if (ErrorCodeEnum.BALANCES_USD.equals(error.getType())) {
                invalidante.setLabel(invalidante.getLabel());
            }

            if (Invalidante.ErrorType.ONLINE.equals(invalidante.getType())) {
                responseData.setTipoInvalidante(TipoError.INVALIDANTES_ONLINE);
            }
            responseData.getInvalidantes().add(invalidante);
            responseData.setMensajeInformativo(buildMensajeInformativo(monedasBalancesPositivos));
        }
    }

    private String buildMensajeInformativo(List<String> monedas) {
        StringBuilder sb = new StringBuilder();
        monedas.iterator();
        for (Iterator<String> i = monedas.iterator(); i.hasNext(); ) {
            sb.append(i.next());
            sb.append(i.hasNext() ? ", " : "");
        }
        return sb.toString();
    }

    private Invalidante mapInvalidante(ErrorLevel errorLevel, Error error) {
        final String mensaje = StringUtils.isEmpty(error.getType().getMensaje()) ?
                error.getMessage() : mensajeBO.obtenerMensajePorCodigo(error.getType().getMensaje()).getMensaje();
        return error.getType().getInvalidante(errorLevel, mensaje);
    }

    private ResumenDetalleCuenta initResumenDetalleCuenta(AbstractCuenta abstractCuenta) {

        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        resumenDetalleCuenta.setCuentaPrincipal(abstractCuenta.isCuentaPrincipal());
        resumenDetalleCuenta.setNroCuentaProducto(abstractCuenta.getNroCuentaProducto());
        resumenDetalleCuenta.setNroSucursal(abstractCuenta.getNroSucursal());
        resumenDetalleCuenta.setTipoCuenta(abstractCuenta.getTipoCuenta());
        resumenDetalleCuenta.setCuentaUnica(abstractCuenta.isCuentaUnica());
        resumenDetalleCuenta.setCuentaCerrada(abstractCuenta.isCuentaCerrada());
        resumenDetalleCuenta.setFavorita(abstractCuenta.getIsFavorita());
        resumenDetalleCuenta.setAlias(abstractCuenta.getAlias());
        if (!abstractCuenta.isCuentaCerrada()) {

            resumenDetalleCuenta.setConvenioPAS(((Cuenta) abstractCuenta).isConvenioPAS());
        }
        resumenDetalleCuenta.setCodigoTitularidad(abstractCuenta.getCodigoTitularidad());
        resumenDetalleCuenta.setCliente(abstractCuenta.getCliente());
        resumenDetalleCuenta.setIsRepatriacion(abstractCuenta.isRepatriacion());

        return resumenDetalleCuenta;
    }


    /**
     * Generar resumen detalle cuenta.
     * <p>
     * the favorita
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @param abstractCuenta       the abstract cuenta
     * @param detalleCuenta        the detalle cuenta
     * @return the resumen detalle cuenta
     */
    private ResumenDetalleCuenta generarResumenDetalleCuenta(ResumenDetalleCuenta resumenDetalleCuenta,
                                                             AbstractCuenta abstractCuenta, ConsultaDetalleCuentaOutEntity detalleCuenta) {

        resumenDetalleCuenta.setCbu(abstractCuenta.getCbu());
        resumenDetalleCuenta.setCuentaPrincipal(abstractCuenta.isCuentaPrincipal());
        resumenDetalleCuenta.setCuentaTraspasoAutomatico(detalleCuenta.getTraspasoAutomaticoActivo());
        resumenDetalleCuenta
                .setSolicitudPendienteTraspasoAutomatico(detalleCuenta.getSolicitudPendienteTraspasoAutomatico());

        resumenDetalleCuenta.setIndicadorSobregiro(detalleCuenta.getIndSobregiro());
        abstractCuenta.setIndicadorSobregiro(detalleCuenta.getIndSobregiro());
        Boolean cuentaHabilitadaTraspaso = ((Cuenta) abstractCuenta).puedeRealizarSolicitudTraspaso();
        resumenDetalleCuenta.setShowSolicitarTraspaso(cuentaHabilitadaTraspaso);
        resumenDetalleCuenta.setShowRealizarTraspaso(cuentaHabilitadaTraspaso
                && !detalleCuenta.getTraspasoAutomaticoActivo() && !detalleCuenta.isModoNocturno());
        resumenDetalleCuenta.setTraspasoAutomaticoActivo(detalleCuenta.getTraspasoAutomaticoActivo());
        resumenDetalleCuenta
                .setSolicitudPendienteTraspasoAutomatico(detalleCuenta.getSolicitudPendienteTraspasoAutomatico());
        resumenDetalleCuenta.setModoNocturno(detalleCuenta.isModoNocturno());

        if (abstractCuenta.isCuentaUnica() && detalleCuenta.getTraspasoAutomaticoActivo()) {
            resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(detalleCuenta.getSaldoCuentaUSD()));
            resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(detalleCuenta.getSaldoCuenta()));
            if (((Cuenta) abstractCuenta).isConvenioPAS()) {
                resumenDetalleCuenta.setConvenioPAS(Boolean.TRUE);
                resumenDetalleCuenta.setSaldoCuentaSueldoPesos(getSaldoBigDecimal(detalleCuenta.getSaldoACAH()));
            } else {
                resumenDetalleCuenta.setSaldoCajaAhorroPesos(getSaldoBigDecimal(detalleCuenta.getSaldoACAH()));
            }
            resumenDetalleCuenta.setSaldoCuentaCorrientePesos(getSaldoBigDecimal(detalleCuenta.getSaldoACTE()));
            resumenDetalleCuenta.setSaldoCajaAhorroDolares(getSaldoBigDecimal(detalleCuenta.getSaldoACAD()));
        }

        if (abstractCuenta.isCuentaUnica() && !detalleCuenta.getTraspasoAutomaticoActivo()) {
            resumenDetalleCuenta.setSaldoUnificado(getSaldoBigDecimal(detalleCuenta.getSaldoCuenta()));
            resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(detalleCuenta.getSaldoCuenta()));
            if (((Cuenta) abstractCuenta).isConvenioPAS()) {
                resumenDetalleCuenta.setConvenioPAS(Boolean.TRUE);
                resumenDetalleCuenta.setSaldoCuentaSueldoPesos(getSaldoBigDecimal(detalleCuenta.getSaldoACAH()));
            } else {
                resumenDetalleCuenta.setSaldoCajaAhorroPesos(getSaldoBigDecimal(detalleCuenta.getSaldoACAH()));
            }
            resumenDetalleCuenta.setSaldoCuentaCorrientePesos(getSaldoBigDecimal(detalleCuenta.getSaldoACTE()));
            resumenDetalleCuenta.setSaldoCajaAhorroDolares(getSaldoBigDecimal(detalleCuenta.getSaldoCuentaUSD()));
        }

        if (!abstractCuenta.isCuentaUnica()) {
            if (((Cuenta) abstractCuenta).isCuentaPesos()) {
                resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(detalleCuenta.getSaldoCuenta()));
            } else {
                resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(detalleCuenta.getSaldoCuentaUSD()));
            }
        }

        if (detalleCuenta.getDisponibleARSCuenta() != null) {
            resumenDetalleCuenta.setDisponibleCuentaPesos(getSaldoBigDecimal(detalleCuenta.getDisponibleARSCuenta()));
        }
        BigDecimal disponibleDescubierto = obtenerDisponibleDescubierto(abstractCuenta, detalleCuenta);
        resumenDetalleCuenta.setDisponibleDescubierto(disponibleDescubierto);
        BigDecimal limiteDescubierto = obtenerLimitDescubierto(abstractCuenta, detalleCuenta);
        resumenDetalleCuenta.setLimiteDescubierto(limiteDescubierto);
        return resumenDetalleCuenta;
    }

    /**
     * Gets the saldo big decimal.
     *
     * @param saldo the saldo
     * @return the saldo big decimal
     */
    private BigDecimal getSaldoBigDecimal(String saldo) {

        try {
            return ISBANStringUtils.convertirStrToBigDecimal(saldo, 2);
        } catch (ImporteConvertException e) {
            LOGGER.info("Error al parsear importe o no existe importe", e);
        }
        return null;
    }

    /**
     * Obtener limit descubierto.
     *
     * @param abstractCuenta the abstract cuenta
     * @param detalleCuenta  the detalle cuenta
     * @return the big decimal
     */
    private BigDecimal obtenerLimitDescubierto(AbstractCuenta abstractCuenta,
                                               ConsultaDetalleCuentaOutEntity detalleCuenta) {
        BigDecimal limiteAcuerdo;
        if (abstractCuenta.isCuentaUnica() || ((Cuenta) abstractCuenta).isCuentaPesos()) {
            limiteAcuerdo = getSaldoBigDecimal(detalleCuenta.getLimiteAcuerdoCC());

        } else {
            limiteAcuerdo = getSaldoBigDecimal(detalleCuenta.getLimiteAcuerdoCCUSD());
        }
        return limiteAcuerdo;
    }

    /**
     * Obtener disponible descubierto.
     *
     * @param abstractCuenta the abstract cuenta
     * @param detalleCuenta  the detalle cuenta
     * @return the big decimal
     */
    private BigDecimal obtenerDisponibleDescubierto(AbstractCuenta abstractCuenta,
                                                    ConsultaDetalleCuentaOutEntity detalleCuenta) {
        BigDecimal disponibleAcuerdo;
        if (abstractCuenta.isCuentaUnica() || ((Cuenta) abstractCuenta).isCuentaPesos()) {
            BigDecimal limiteAcuerdo = getSaldoBigDecimal(detalleCuenta.getLimiteAcuerdoCC());
            if (!detalleCuenta.getTraspasoAutomaticoActivo()) {
                BigDecimal saldoCtaCte = getSaldoBigDecimal(detalleCuenta.getSaldoACTE());
                if (saldoCtaCte != null && saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
                    disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
                } else {
                    disponibleAcuerdo = limiteAcuerdo;
                }
            } else {
                BigDecimal saldoCtaCte = getSaldoBigDecimal(detalleCuenta.getSaldoCuenta());
                if (saldoCtaCte != null && saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
                    disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
                } else {
                    disponibleAcuerdo = limiteAcuerdo;
                }
            }
            return disponibleAcuerdo;
        }
        BigDecimal limiteAcuerdo = getSaldoBigDecimal(detalleCuenta.getLimiteAcuerdoCCUSD());
        BigDecimal saldoCtaCte = getSaldoBigDecimal(detalleCuenta.getSaldoCuentaUSD());
        if (saldoCtaCte != null && limiteAcuerdo != null && saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
            disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
        } else {
            disponibleAcuerdo = limiteAcuerdo;
        }
        return disponibleAcuerdo;
    }

    /**
     * Gets the consulta detalle cuenta in entity.
     *
     * @param cuenta the cuenta
     * @return the consulta detalle cuenta in entity
     */
    private ConsultaDetalleCuentaInEntity getConsultaDetalleCuentaInEntity(Cuenta cuenta) {
        ConsultaDetalleCuentaInEntity consultaDetalleCuentaIn = new ConsultaDetalleCuentaInEntity();
        consultaDetalleCuentaIn.setCliente(cuenta.getCliente());

        String sucursal = StringUtils.stripStart(cuenta.getNroSucursal(), "0");
        String sucursalFormateada = StringUtils.leftPad(sucursal, 3, "0");
        String numeroCuenta = StringUtils.stripStart(cuenta.getNroCuentaProducto(), "0");
        String numeroCuentaFormateada = StringUtils.leftPad(numeroCuenta, 7, "0");
        String tipoCuenta = StringUtils.leftPad(cuenta.getTipoCuentaEnum().getCodigo().toString(), 2, "0");
        String sucursalAddFormateada = StringUtils.leftPad(sucursal, 4, "0");
        String numeroCuentaFormateadaAdd = StringUtils.stripStart(cuenta.getContratoAltair(), "0");
        numeroCuentaFormateadaAdd = StringUtils.leftPad(numeroCuentaFormateadaAdd, 12, "0");

        consultaDetalleCuentaIn.setNroCuenta(numeroCuentaFormateada);
        consultaDetalleCuentaIn.setSucursalCuenta(sucursalFormateada);
        consultaDetalleCuentaIn.setTipoCuenta(tipoCuenta);
        consultaDetalleCuentaIn.setEntidad(ENTIDAD);
        consultaDetalleCuentaIn.setSucursal(sucursalAddFormateada);
        consultaDetalleCuentaIn.setNroCuentaAdd(numeroCuentaFormateadaAdd);
        consultaDetalleCuentaIn.setDivisa(cuenta.getMonedaAltair());

        return consultaDetalleCuentaIn;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerSaldosConsolidadosActualizados(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldosConsolidadosActualizados(Cliente cliente) {

        Respuesta<List<Cuenta>> respuestaCuentas = null;
        Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
        try {
            if (!sesionParametros.isPrimerIngreso()) {
                respuestaCuentas = getCuentasSaldoPorCliente(cliente);
            } else {
                respuestaCuentas = new Respuesta<List<Cuenta>>();
                respuestaCuentas.setRespuesta(cliente.getCuentas());
                respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
                sesionParametros.setPrimerIngreso(false);
            }
            if (respuestaCuentas != null && EstadoRespuesta.OK.equals(respuestaCuentas.getEstadoRespuesta())) {
                respuesta = obtenerSaldoConsolidadoCliente(cliente);

            } else {
                respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_TABLERO_HOME,
                        CodigoMensajeConstantes.ERROR_TABLERO_HOME);
                LOGGER.debug(ERROR_AL_OBTENER_SALDOS_CONSOLIDADOS_ACTUALIZADOS);
            }
        } catch (RuntimeException e) {
            LOGGER.debug(ERROR_AL_OBTENER_SALDOS_CONSOLIDADOS_ACTUALIZADOS, e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_TABLERO_HOME,
                    CodigoMensajeConstantes.ERROR_TABLERO_HOME);
        } catch (BusinessException e) {
            LOGGER.debug(ERROR_AL_OBTENER_SALDOS_CONSOLIDADOS_ACTUALIZADOS + "", e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_TABLERO_HOME,
                    CodigoMensajeConstantes.ERROR_TABLERO_HOME);
        }

        return respuesta;

    }

    /**
     * Este metodo invoca IDECLTSDO180.
     *
     * @param cliente           the cliente
     * @param cuentasMonetarias the cuentas monetarias
     * @throws BusinessException the business exception
     */
    private void obtenerCuentas(Cliente cliente, List<Cuenta> cuentasMonetarias) throws BusinessException {
        int cantidadCuentas = 0;
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.isTipoCuentaMonetaria()) {
                if (cuenta.isCuentaUnica()) {
                    cantidadCuentas = cantidadCuentas + 2;
                } else {
                    cantidadCuentas++;
                }
                cuentasMonetarias.add(cuenta);
            }
        }
        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar cuentas", e);
            throw new BusinessException(e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentaSeleccionada(ar.com.santanderrio.obp.base.respuesta.entities
     * .Respuesta)
     */
    @Override
    public Respuesta<Integer> obtenerCuentaSeleccionada(Respuesta<List<ResumenDetalleCuenta>> respuestaCuentas)
            throws BusinessException {
        Respuesta<Integer> respuesta = new Respuesta<Integer>();
        Integer seleccionada = 0;
        ResumenDetalleCuenta cuentaSeleccionada = null;

        List<ResumenDetalleCuenta> cuentas = respuestaCuentas.getRespuesta();
        Integer i = 0;
        for (ResumenDetalleCuenta resumenDetalleCuenta : cuentas) {
            if (resumenDetalleCuenta.isFavorita()) {
                cuentaSeleccionada = resumenDetalleCuenta;
                seleccionada = i;
                break;
            }
            i++;
        }

        if (cuentas.isEmpty()) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            return respuesta;
        }

        if (cuentaSeleccionada == null) {
            cuentaSeleccionada = cuentas.get(0);
            i = 0;
        }

        List<ItemMensajeRespuesta> items = respuestaCuentas.getItemsMensajeRespuesta();
        if (items != null) {

            for (ItemMensajeRespuesta item : items) {
                if (item.getTipoError().equals(TipoError.ERROR_ITEM_CUENTA.getDescripcion())
                        && cuentaSeleccionada.getNumeroCuentaYSucursal()
                        .equals(item.getTag().substring(CUENTAS_TAG.length(), item.getTag().length() - 1))) {
                    respuesta.add(respuestaFactory.generarItemMensajeRespuesta(
                            CUENTAS_TAG + cuentaSeleccionada.getNumeroCuentaYSucursal() + TAG_FIN,
                            TipoError.ERROR_ITEM_CUENTA_SELECCIONADA,
                            CodigoMensajeConstantes.CODIGO_ERROR_ITEM_CUENTA_SELECCIONADA));
                }
            }
        }
        respuesta.setRespuesta(seleccionada);
        return respuesta;
    }

    /**
     * Obtiene las cuentas en Banelco.
     *
     * @param cliente the cliente
     * @return the list
     * @throws BusinessException the business exception
     */
    @Override
    public List<Cuenta> obtenerCuentasBanelcoPesos(Cliente cliente) throws BusinessException {
        List<CuentaPagoMisCuentas> cuentasHabilitadas;
        try {
            cuentasHabilitadas = banelcoDAO.obtenerCuentasBanelcoHabilitadas(cliente);
        } catch (DAOException e) {
            LOGGER.error("Error obteniendo cuentas Banelco.", e);
            throw new BusinessException("Error obteniendo cuentas Banelco" + e.getErrorCode());
        }
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        int cantidadCuentas = 0;
        if (CollectionUtils.isNotEmpty(cuentasHabilitadas)) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.esSaldoPesos() && existeCuentaEnBanelco(cuenta, cuentasHabilitadas)) {
                    if (cuenta.isCuentaUnica()) {
                        cantidadCuentas = cantidadCuentas + 2;
                    } else {
                        cantidadCuentas++;
                    }
                    cuentasMonetarias.add(cuenta);
                }
            }
            try {
                detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
            } catch (DAOException e) {
                LOGGER.error(ERROR_ERROR_CTASDOM, e);
                throw new BusinessException(ERROR_ERROR_CTASDOM);
            }
            return CuentasUtils.ordenarCuentasMonetarias(cuentasMonetarias);
        }
        throw new BusinessException("Error obteniendo cuentas Banelco");
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentasDebito(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public List<Cuenta> obtenerCuentasDebito(Cliente cliente) throws BusinessException {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        int cantidadCuentas = 0;
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.esSaldoPesos()) {
                if (cuenta.isCuentaUnica()) {
                    cantidadCuentas = cantidadCuentas + 2;
                } else {
                    cantidadCuentas++;
                }
                cuentasMonetarias.add(cuenta);
            }
        }

        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar saldo de cuentas");
            throw new BusinessException("Error al consultar saldo de cuentas");
        }

        return CuentasUtils.ordenarCuentasMonetarias(cuentasMonetarias);
    }

    /**
     * Devuelve si la Cuenta del cliente tiene el mismo numero que las cuentas
     * de pago mis cuentas.
     *
     * @param cuenta       the cuenta
     * @param listaCuentas the lista cuentas
     * @return true, si existe
     */
    private boolean existeCuentaEnBanelco(Cuenta cuenta, List<CuentaPagoMisCuentas> listaCuentas) {
        for (CuentaPagoMisCuentas cuentaPagoMisCuentas : listaCuentas) {
            if (new BigDecimal(cuenta.getNroCuentaProducto())
                    .equals(new BigDecimal(cuentaPagoMisCuentas.getNroCuentaProducto()))) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#getCuentasSaldo(ar.
     * com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente) {
        try {
            List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
            Respuesta<List<ResumenDetalleCuenta>> respuesta = new Respuesta<List<ResumenDetalleCuenta>>();
            List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();

            List<DatoItemMensaje> itemsMensajesErrores = new ArrayList<DatoItemMensaje>();
            obtenerCuentas(cliente, cuentasMonetarias);
            for (Cuenta cuenta : cuentasMonetarias) {
                DetalleCuentaEntity detalleCuenta = getDetalleCuenta(cuenta);
                ResumenDetalleCuenta resumenDetalleCuenta = generarResumenDetalleCuenta(cuenta, detalleCuenta,
                        cuenta.isCuentaCerrada(),
                        TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta())), false,
                        cuenta.isConvenioPAS(), cuenta.getIsFavorita());
                resumenDetalleCuenta.setAlias(cuenta.getAlias());
                setCuentaPrincipal(resumenDetalleCuenta, cuenta.isCuentaCerrada(), cuenta.isCuentaPrincipal());
                respuestaDetalleCuenta.add(resumenDetalleCuenta);
            }
            if (!respuestaDetalleCuenta.isEmpty()) {
                CuentasUtils.ordenarCuentas(respuestaDetalleCuenta);
                if (CollectionUtils.isEmpty(itemsMensajesErrores)) {
                    respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
                    respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
                } else {
                    respuesta = respuestaFactory.crearRespuestaWarning(itemsMensajesErrores, respuestaDetalleCuenta);
                }
                respuesta.setRespuestaVacia(Boolean.FALSE);
            } else {
                respuesta.setRespuestaVacia(Boolean.TRUE);
            }
            respuesta.setRespuesta(respuestaDetalleCuenta);
            return respuesta;
        } catch (BusinessException e) {
            LOGGER.error("ERROR_OBTENCION_CUENTAS", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SERVICIO,
                    CodigoMensajeConstantes.ERROR_GENERICO);
        }
    }

    /**
     * Sets the cuenta principal.
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @param cuentaCerrada        the cuenta cerrada
     * @param cuentaPrincipal      the cuenta principal
     */
    private void setCuentaPrincipal(ResumenDetalleCuenta resumenDetalleCuenta, boolean cuentaCerrada,
                                    boolean cuentaPrincipal) {
        if (!cuentaCerrada) {
            resumenDetalleCuenta.setCuentaPrincipal(cuentaPrincipal);
        }

    }

    /**
     * Gets the detalle cuenta.
     *
     * @param cuenta the cuenta
     * @return the detalle cuenta
     */
    private DetalleCuentaEntity getDetalleCuenta(Cuenta cuenta) {
        DetalleCuentaEntity detalleCuenta = new DetalleCuentaEntity();
        detalleCuenta.setSaldoACAH("0");
        detalleCuenta.setSaldoACTE("0");
        detalleCuenta.setSaldoACAD("0");
        detalleCuenta.setLimiteAcuerdoCtaCtePesos("0");
        detalleCuenta.setLimiteAcuerdoCtaCteDolares("0");
        detalleCuenta.setDireccionaCA("");
        if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
            detalleCuenta.setSaldoPesos(cuenta.getSaldoCUPesos());
            detalleCuenta.setSaldoDolares(cuenta.getSaldoCUDls());
        } else {
            if (isCuentaPesos(cuenta)) {
                detalleCuenta.setSaldoPesos(cuenta.getSaldoCuenta());
            } else {
                detalleCuenta.setSaldoDolares(cuenta.getSaldoCuenta());
            }
        }
        return detalleCuenta;
    }

    /**
     * Checks if is cuenta pesos.
     *
     * @param cuenta the cuenta
     * @return true, if is cuenta pesos
     */
    private boolean isCuentaPesos(AbstractCuenta cuenta) {
        return TipoCuenta.CAJA_AHORRO_PESOS.equals(cuenta.getTipoCuentaEnum())
                || TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(cuenta.getTipoCuentaEnum());
    }

    /**
     * Generar resumen detalle cuenta.
     *
     * @param abstractCuenta           the abstract cuenta
     * @param detalleCuenta            the detalle cuenta
     * @param cuentaCerrada            the cuenta cerrada
     * @param cuentaUnica              the cuenta unica
     * @param cuentaTraspasoAutomatico the cuenta traspaso automatico
     * @param convenioPAS              the convenio pas
     * @param favorita                 the favorita
     * @return the resumen detalle cuenta
     */
    private ResumenDetalleCuenta generarResumenDetalleCuenta(AbstractCuenta abstractCuenta,
                                                             DetalleCuentaEntity detalleCuenta, boolean cuentaCerrada, boolean cuentaUnica,
                                                             boolean cuentaTraspasoAutomatico, boolean convenioPAS, Boolean favorita) {
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        if (!cuentaCerrada) {
            resumenDetalleCuenta.setCbu(abstractCuenta.getCbu());
        }
        resumenDetalleCuenta.setNroCuentaProducto(abstractCuenta.getNroCuentaProducto());
        resumenDetalleCuenta.setNroSucursal(abstractCuenta.getNroSucursal());
        resumenDetalleCuenta.setTipoCuenta(abstractCuenta.getTipoCuenta());
        resumenDetalleCuenta.setCuentaUnica(cuentaUnica);
        resumenDetalleCuenta.setCuentaTraspasoAutomatico(cuentaTraspasoAutomatico);
        resumenDetalleCuenta.setCuentaCerrada(cuentaCerrada);
        resumenDetalleCuenta.setConvenioPAS(convenioPAS);

        if (cuentaUnica && cuentaTraspasoAutomatico && !cuentaCerrada && detalleCuenta != null) {
            BigDecimal saldoDolares = new BigDecimal(detalleCuenta.getSaldoDolares());
            BigDecimal saldoPesos = new BigDecimal(detalleCuenta.getSaldoPesos());
            resumenDetalleCuenta.setSaldoDolares(saldoDolares);
            resumenDetalleCuenta.setSaldoPesos(saldoPesos);
        }

        if (cuentaUnica && !cuentaTraspasoAutomatico && !cuentaCerrada && detalleCuenta != null) {
            BigDecimal saldoUnificado = new BigDecimal(detalleCuenta.getSaldoPesos());
            resumenDetalleCuenta.setSaldoUnificado(saldoUnificado);
            if (convenioPAS) {
                BigDecimal saldoCuentaSueldoPesos = new BigDecimal(detalleCuenta.getSaldoACAH());
                resumenDetalleCuenta.setSaldoCuentaSueldoPesos(saldoCuentaSueldoPesos);
            } else {
                BigDecimal saldoCajaAhorroPesos = new BigDecimal(detalleCuenta.getSaldoACAH());
                resumenDetalleCuenta.setSaldoCajaAhorroPesos(saldoCajaAhorroPesos);
            }
            BigDecimal saldoCuentaCorrientePesos = new BigDecimal(detalleCuenta.getSaldoACTE());
            String monto = detalleCuenta.getSaldoDolares();
            monto = monto.replaceAll("\\+", "");
            BigDecimal saldoCajaAhorroDolares = new BigDecimal(monto);
            resumenDetalleCuenta.setSaldoCuentaCorrientePesos(saldoCuentaCorrientePesos);
            resumenDetalleCuenta.setSaldoCajaAhorroDolares(saldoCajaAhorroDolares);
        }

        if (!cuentaUnica && !cuentaCerrada && detalleCuenta != null) {

            if (isCuentaPesos(abstractCuenta)) {
                BigDecimal saldoPesos = new BigDecimal(detalleCuenta.getSaldoPesos());
                resumenDetalleCuenta.setSaldoPesos(saldoPesos);
            } else {
                BigDecimal saldoDolares = new BigDecimal(detalleCuenta.getSaldoDolares());
                resumenDetalleCuenta.setSaldoDolares(saldoDolares);
            }
        }
        if (!cuentaCerrada && detalleCuenta != null) {
            if (detalleCuenta.getDisponibleCuentaPesos() != null) {
                resumenDetalleCuenta.setDisponibleCuentaPesos(new BigDecimal(detalleCuenta.getDisponibleCuentaPesos()));
            }
            BigDecimal disponibleDescubierto = obtenerDisponibleDescubierto(abstractCuenta, detalleCuenta);
            resumenDetalleCuenta.setDisponibleDescubierto(disponibleDescubierto);
            BigDecimal limiteDescubierto = obtenerLimitDescubierto(abstractCuenta, detalleCuenta);
            resumenDetalleCuenta.setLimiteDescubierto(limiteDescubierto);
        }

        resumenDetalleCuenta.setFavorita(favorita != null ? favorita : false);

        resumenDetalleCuenta.setCodigoTitularidad(abstractCuenta.getCodigoTitularidad());
        resumenDetalleCuenta.setCliente(abstractCuenta.getCliente());
        return resumenDetalleCuenta;
    }

    /**
     * Obtener limit descubierto.
     *
     * @param abstractCuenta the abstract cuenta
     * @param detalleCuenta  the detalle cuenta
     * @return the big decimal
     */
    private BigDecimal obtenerLimitDescubierto(AbstractCuenta abstractCuenta, DetalleCuentaEntity detalleCuenta) {
        BigDecimal limiteAcuerdo;
        if (abstractCuenta.isCuentaUnica() || isCuentaPesos(abstractCuenta)) {
            limiteAcuerdo = new BigDecimal(detalleCuenta.getLimiteAcuerdoCtaCtePesos());

        } else {
            limiteAcuerdo = new BigDecimal(detalleCuenta.getLimiteAcuerdoCtaCteDolares());
        }
        return limiteAcuerdo;
    }

    /**
     * Obtener disponible descubierto.
     *
     * @param abstractCuenta the abstract cuenta
     * @param detalleCuenta  the detalle cuenta
     * @return the big decimal
     */
    private BigDecimal obtenerDisponibleDescubierto(AbstractCuenta abstractCuenta, DetalleCuentaEntity detalleCuenta) {
        BigDecimal disponibleAcuerdo;
        if (abstractCuenta.isCuentaUnica() || isCuentaPesos(abstractCuenta)) {
            BigDecimal limiteAcuerdo = new BigDecimal(detalleCuenta.getLimiteAcuerdoCtaCtePesos());
            if (isCuentaTraspasoAutomatico(abstractCuenta, detalleCuenta)) {
                BigDecimal saldoCtaCte = new BigDecimal(detalleCuenta.getSaldoACTE());
                if (saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
                    disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
                } else {
                    disponibleAcuerdo = limiteAcuerdo;
                }
            } else {
                BigDecimal saldoCtaCte = new BigDecimal(detalleCuenta.getSaldoPesos());
                if (saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
                    disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
                } else {
                    disponibleAcuerdo = limiteAcuerdo;
                }
            }
        } else {
            BigDecimal limiteAcuerdo = new BigDecimal(detalleCuenta.getLimiteAcuerdoCtaCteDolares());
            BigDecimal saldoCtaCte = new BigDecimal(detalleCuenta.getSaldoDolares());
            if (saldoCtaCte.compareTo(BigDecimal.ZERO) < 0) {
                disponibleAcuerdo = limiteAcuerdo.add(saldoCtaCte);
            } else {
                disponibleAcuerdo = limiteAcuerdo;
            }
        }
        return disponibleAcuerdo;
    }

    /**
     * Checks if is cuenta traspaso automatico.
     *
     * @param cuenta        the cuenta
     * @param detalleCuenta the detalle cuenta
     * @return true, if is cuenta traspaso automatico
     */
    public boolean isCuentaTraspasoAutomatico(AbstractCuenta cuenta, DetalleCuentaEntity detalleCuenta) {
        boolean cuentaTraspasoAutomatico = false;
        if (cuenta.isCuentaUnica() && "A".equalsIgnoreCase(detalleCuenta.getDireccionaCA().trim())) {

            cuentaTraspasoAutomatico = true;
        }
        return cuentaTraspasoAutomatico;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * getCuentasSaldoPorCliente(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Respuesta<List<Cuenta>> getCuentasSaldoPorCliente(Cliente cliente) throws BusinessException {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        Respuesta<List<Cuenta>> repuesta = new Respuesta<List<Cuenta>>();
        obtenerCuentas(cliente, cuentasMonetarias);
        repuesta.setRespuesta(cuentasMonetarias);
        repuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        repuesta.setRespuestaVacia(false);
        repuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        return repuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * hasCuentasMonetariasActivas(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Boolean hasCuentasMonetariasActivas(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isTipoCuentaMonetaria()) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * hasCuentasMonetariasCerradas(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Boolean hasCuentasMonetariasCerradas(Cliente cliente) {
        List<CuentaCerrada> cuentas = cliente.getCuentasCerradas();
        if (cuentas != null) {
            for (CuentaCerrada cuenta : cuentas) {
                if (cuenta.isTipoCuentaMonetaria()) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerSaldoActualizado(ar.com.santanderrio.obp.servicios.cuentas.
     * entities.Cuenta)
     */
    @Override
    public Cuenta obtenerSaldoActualizado(Cuenta cuenta) throws BusinessException {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        int cantidadCuentas = 0;
        if (cuenta.isCuentaUnica()) {
            cantidadCuentas = cantidadCuentas + 2;
        } else {
            cantidadCuentas++;
        }
        cuentasMonetarias.add(cuenta);

        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cuenta.getCliente(), cantidadCuentas);
            return cuenta;
        } catch (DAOException e) {
            LOGGER.error("Error al consultar cuentas", e);
            throw new BusinessException();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentaPrincipalCliente(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Cuenta obtenerCuentaPrincipalCliente(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isCuentaPrincipal()) {
                return cuenta;
            }
        }
        return new Cuenta();
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerSaldoConsolidadoCliente(ar.com.santanderrio.obp.servicios.clientes
     * .entities.Cliente)
     */
    @Override
    public Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldoConsolidadoCliente(Cliente cliente) {

        SaldosConsolidadosCuentaDTO saldosConsolidadosDTO = new SaldosConsolidadosCuentaDTO();

        List<Cuenta> cuentasCliente = cliente.getCuentas();

        List<Cuenta> cuentasMonetarias = obtenerCuentasMonetarias(cuentasCliente);

        if (!cuentasMonetarias.isEmpty()) {
            for (Cuenta cuenta : cuentasMonetarias) {
                if (cuenta.isCuentaUnica()) {
                    sumarCuentaUnica(cuenta, saldosConsolidadosDTO);
                } else {
                    sumarCuentaMonetaria(cuenta, saldosConsolidadosDTO);
                }
            }
        }
        if (saldosConsolidadosDTO.getSaldoPesosValue() != null) {

            saldosConsolidadosDTO
                    .setSaldoPesos(ISBANStringUtils.formatearSaldo(saldosConsolidadosDTO.getSaldoPesosValue()));
        }
        if (saldosConsolidadosDTO.getSaldoDolaresValue() != null) {
            saldosConsolidadosDTO
                    .setSaldoDolares(ISBANStringUtils.formatearSaldo(saldosConsolidadosDTO.getSaldoDolaresValue()));
        }

        saldosConsolidadosDTO.setHostNocturno(cliente.isHostNocturno());

        return respuestaFactory.crearRespuestaOk(saldosConsolidadosDTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerSaldoConsolidadoCliente(java.util.List)
     */
    @Override
    public Respuesta<SaldosConsolidadosCuentaDTO> obtenerSaldoConsolidadoCliente(
            List<ResumenDetalleCuenta> listaResumenDetalleCuenta) {

        SaldosConsolidadosCuentaDTO saldosConsolidadosDTO = new SaldosConsolidadosCuentaDTO();
        Respuesta<SaldosConsolidadosCuentaDTO> respuesta = new Respuesta<SaldosConsolidadosCuentaDTO>();
        respuesta.setRespuesta(saldosConsolidadosDTO);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        if (CollectionUtils.isNotEmpty(listaResumenDetalleCuenta)) {
            for (ResumenDetalleCuenta resumenDetalleCuenta : listaResumenDetalleCuenta) {
                if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
                    return respuesta;
                }
                if (!resumenDetalleCuenta.isCuentaCerrada()) {
                    sumarCuenta(saldosConsolidadosDTO, respuesta, resumenDetalleCuenta);
                }
            }
        }
        if (saldosConsolidadosDTO.getSaldoPesosValue() != null
                && !GUION.equals(saldosConsolidadosDTO.getSaldoPesos())) {
            saldosConsolidadosDTO
                    .setSaldoPesos(ISBANStringUtils.formatearSaldoSinAbs(saldosConsolidadosDTO.getSaldoPesosValue()));
        }
        if (saldosConsolidadosDTO.getSaldoDolaresValue() != null
                && !GUION.equals(saldosConsolidadosDTO.getSaldoDolares())) {
            saldosConsolidadosDTO.setSaldoDolares(
                    ISBANStringUtils.formatearSaldoSinAbs(saldosConsolidadosDTO.getSaldoDolaresValue()));
        }
        return respuesta;
    }

    /**
     * Sumar cuenta.
     *
     * @param saldosConsolidadosDTO the saldos consolidados DTO
     * @param respuesta             the respuesta
     * @param resumenDetalleCuenta  the resumen detalle cuenta
     */
    private void sumarCuenta(SaldosConsolidadosCuentaDTO saldosConsolidadosDTO,
                             Respuesta<SaldosConsolidadosCuentaDTO> respuesta, ResumenDetalleCuenta resumenDetalleCuenta) {
        if (errorCuenta(resumenDetalleCuenta)) {
            actualizarRespuesta(respuesta, resumenDetalleCuenta);
        } else {
            sumarCuenta(saldosConsolidadosDTO, resumenDetalleCuenta);
        }
        respuesta.setRespuesta(saldosConsolidadosDTO);
    }

    /**
     * Actualizar respuesta.
     *
     * @param respuestaSaldos      the respuesta saldos
     * @param resumenDetalleCuenta the resumen detalle cuenta
     */
    private void actualizarRespuesta(Respuesta<SaldosConsolidadosCuentaDTO> respuestaSaldos,
                                     ResumenDetalleCuenta resumenDetalleCuenta) {

        if (isCuentaPesos(resumenDetalleCuenta.getTipoCuenta())) {
            if (respuestaSaldos.getRespuesta() != null
                    && GUION.equals(respuestaSaldos.getRespuesta().getSaldoDolares())) {
                respuestaSaldos.setEstadoRespuesta(EstadoRespuesta.ERROR);
            }
            respuestaSaldos.getRespuesta().setSaldoPesos(GUION);

        }
        if (isCuentaDolares(resumenDetalleCuenta.getTipoCuenta())) {
            if (respuestaSaldos.getRespuesta() != null
                    && GUION.equals(respuestaSaldos.getRespuesta().getSaldoPesos())) {
                respuestaSaldos.setEstadoRespuesta(EstadoRespuesta.ERROR);
            }
            respuestaSaldos.getRespuesta().setSaldoDolares(GUION);
        }
        if (resumenDetalleCuenta.isCuentaUnica()) {
            respuestaSaldos.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaSaldos.getRespuesta().setSaldoDolares(GUION);
            respuestaSaldos.getRespuesta().setSaldoPesos(GUION);
        }
    }

    /**
     * Sumar cuenta.
     *
     * @param saldosConsolidadosDTO the saldos consolidados DTO
     * @param resumenDetalleCuenta  the resumen detalle cuenta
     */
    private void sumarCuenta(SaldosConsolidadosCuentaDTO saldosConsolidadosDTO,
                             ResumenDetalleCuenta resumenDetalleCuenta) {
        if (resumenDetalleCuenta.isCuentaUnica()) {
            sumarCuentaUnica(resumenDetalleCuenta, saldosConsolidadosDTO);
        } else {
            sumarCuentaMonetaria(resumenDetalleCuenta, saldosConsolidadosDTO);
        }
    }

    /**
     * Error cuenta.
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @return the boolean
     */
    private Boolean errorCuenta(ResumenDetalleCuenta resumenDetalleCuenta) {

        return resumenDetalleCuenta.getTraspasoAutomaticoActivo() == null
                && resumenDetalleCuenta.getSolicitudPendienteTraspasoAutomatico() == null;
    }

    /**
     * Obtener cuentas monetarias.
     *
     * @param cuentasCliente the cuentas cliente
     * @return the list
     */
    private List<Cuenta> obtenerCuentasMonetarias(List<Cuenta> cuentasCliente) {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
            for (Cuenta cuenta : cuentasCliente) {
                if (cuenta.isTipoCuentaMonetaria()) {
                    cuentasMonetarias.add(cuenta);
                }
            }
        }
        return cuentasMonetarias;
    }

    /**
     * Sumar cuenta monetaria.
     *
     * @param cuenta the cuenta
     * @param saldos the saldos
     */
    private void sumarCuentaMonetaria(Cuenta cuenta, SaldosConsolidadosCuentaDTO saldos) {

        BigDecimal saldoCuenta;
        if (StringUtils.isNotBlank(cuenta.getSaldoCuenta())) {
            saldoCuenta = new BigDecimal(cuenta.getSaldoCuenta());
            if (cuenta.isCuentaPesos()) {
                saldos.setSaldoPesosValue(saldos.getSaldoPesosValue() == null ? saldoCuenta
                        : saldos.getSaldoPesosValue().add(saldoCuenta));
            }
            if (cuenta.isCuentaDolares()) {
                saldos.setSaldoDolaresValue(saldos.getSaldoDolaresValue() == null ? saldoCuenta
                        : saldos.getSaldoDolaresValue().add(saldoCuenta));
            }
        }
    }

    /**
     * Sumar cuenta unica.
     *
     * @param cuenta the cuenta
     * @param saldos the saldos
     */
    private void sumarCuentaUnica(Cuenta cuenta, SaldosConsolidadosCuentaDTO saldos) {

        BigDecimal saldoPesos;
        BigDecimal saldoDolares;
        if (StringUtils.isNotBlank(cuenta.getSaldoCUPesos())) {
            saldoPesos = new BigDecimal(cuenta.getSaldoCUPesos());
            saldos.setSaldoPesosValue(
                    saldos.getSaldoPesosValue() == null ? saldoPesos : saldos.getSaldoPesosValue().add(saldoPesos));
        }
        if (StringUtils.isNotBlank(cuenta.getSaldoCUDls())) {
            saldoDolares = new BigDecimal(cuenta.getSaldoCUDls());
            saldos.setSaldoDolaresValue(saldos.getSaldoDolaresValue() == null ? saldoDolares
                    : saldos.getSaldoDolaresValue().add(saldoDolares));
        }
    }

    /**
     * Sumar cuenta unica.
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @param saldos               the saldos
     */
    private void sumarCuentaUnica(ResumenDetalleCuenta resumenDetalleCuenta, SaldosConsolidadosCuentaDTO saldos) {

        if (!resumenDetalleCuenta.isCuentaCerrada()) {

            BigDecimal saldoPesos;
            BigDecimal saldoDolares;
            saldoPesos = resumenDetalleCuenta.getSaldoPesos();
            saldos.setSaldoPesosValue(
                    saldos.getSaldoPesosValue() == null ? saldoPesos : saldos.getSaldoPesosValue().add(saldoPesos));
            if (resumenDetalleCuenta.getSaldoCajaAhorroDolares() != null) {
                saldoDolares = resumenDetalleCuenta.getSaldoCajaAhorroDolares();
                saldos.setSaldoDolaresValue(saldos.getSaldoDolaresValue() == null ? saldoDolares
                        : saldos.getSaldoDolaresValue().add(saldoDolares));
            }
        }
    }

    /**
     * Sumar cuenta monetaria.
     *
     * @param resumenDetalleCuenta the resumen detalle cuenta
     * @param saldos               the saldos
     */
    private void sumarCuentaMonetaria(ResumenDetalleCuenta resumenDetalleCuenta, SaldosConsolidadosCuentaDTO saldos) {

        BigDecimal saldoPesos;
        BigDecimal saldoDolares;
        if (isCuentaPesos(resumenDetalleCuenta.getTipoCuenta()) && resumenDetalleCuenta.getSaldoPesos() != null) {
            saldoPesos = resumenDetalleCuenta.getSaldoPesos();
            saldos.setSaldoPesosValue(
                    saldos.getSaldoPesosValue() == null ? saldoPesos : saldos.getSaldoPesosValue().add(saldoPesos));
        }
        if (isCuentaDolares(resumenDetalleCuenta.getTipoCuenta()) && resumenDetalleCuenta.getSaldoDolares() != null) {
            saldoDolares = resumenDetalleCuenta.getSaldoDolares();
            saldos.setSaldoDolaresValue(saldos.getSaldoDolaresValue() == null ? saldoDolares
                    : saldos.getSaldoDolaresValue().add(saldoDolares));
        }
    }

    /**
     * Checks if is cuenta pesos.
     *
     * @param codigo the codigo
     * @return true, if is cuenta pesos
     */
    public boolean isCuentaPesos(String codigo) {
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);

        return TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta) || TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta);
    }

    /**
     * Checks if is cuenta pesos.
     *
     * @param codigo the codigo
     * @return true, if is cuenta pesos
     */
    public boolean isCuentaUnica(String codigo) {
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);

        return TipoCuenta.CUENTA_UNICA.equals(tipoCuenta);
    }

    /**
     * Checks if is cuenta dolares.
     *
     * @param codigo the codigo
     * @return true, if is cuenta dolares
     */
    public boolean isCuentaDolares(String codigo) {

        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(codigo);

        return TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCuenta)
                || TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCuenta);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentaPrincipal(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public Respuesta<ResumenDetalleCuenta> obtenerCuentaPrincipal(Cliente cliente) throws BusinessException {

        Respuesta<ResumenDetalleCuenta> respuestaDetalleCuenta = new Respuesta<ResumenDetalleCuenta>();
        try {
            ResumenDetalleCuenta resumenCtaPrincipal;

            resumenCtaPrincipal = buscarCuentaPrincipal(cliente);

            if (resumenCtaPrincipal != null) {
                respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
                respuestaDetalleCuenta.setRespuesta(resumenCtaPrincipal);
            } else {
                throw new BusinessException(ERROR_NO_HAY_CTA_PRINCIPAL);
            }
        } catch (Exception e) {
            LOGGER.error("Error al buscar cuenta principal", e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        }

        return respuestaDetalleCuenta;
    }

    /**
     * Buscar cuenta principal.
     *
     * @param cliente the cliente
     * @return the resumen detalle cuenta
     * @throws BusinessException the business exception
     */
    private ResumenDetalleCuenta buscarCuentaPrincipal(Cliente cliente) throws BusinessException {

        ResumenDetalleCuenta resumenCtaPrincipalResponse = null;
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        List<Cuenta> cuentas = cliente.getCuentas();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isCuentaPrincipal()) {

                try {
                    resumenCtaPrincipalResponse = obtenerResumenDetalleCuenta(cuenta);
                } catch (DAOException e) {
                    LOGGER.error(ERROR_AL_OBTENER_RESUMEN, e);
                    resumenCtaPrincipalResponse = initResumenDetalleCuenta(cuenta);
                    resumenCtaPrincipalResponse.setCbu(cuenta.getCbu());
                    resumenCtaPrincipalResponse.setCuentaCerrada(false);
                    itemsMensajeRespuesta.add(respuestaFactory.generarItemMensajeRespuesta(cuenta.getNumeroCuentaTag(),
                            TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDO));
                }
            }
        }

        if (resumenCtaPrincipalResponse == null && CollectionUtils.isEmpty(cliente.getCuentasCerradas())) {
            for (CuentaCerrada cuentaCerrada : cliente.getCuentasCerradas()) {
                if (cuentaCerrada.isCuentaPrincipal()) {

                    resumenCtaPrincipalResponse = obtenerResumenDetalleCuentaCerrada(itemsMensajeRespuesta,
                            cuentaCerrada);
                }
            }
        }
        return resumenCtaPrincipalResponse;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerCuentas(ar.
     * com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<List<ResumenDetalleCuenta>> obtenerCuentas(Cliente cliente) throws BusinessException {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerReporteCBUCuenta(ar.com.santanderrio.obp.servicios.cuentas.
     * entities.IdentificacionCuenta,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Reporte> obtenerReporteCBUCuenta(IdentificacionCuenta id, Cliente cliente, String alias)
            throws BusinessException {
        Respuesta<Reporte> respuestaReporteCBU = new Respuesta<Reporte>();

        try {
            AbstractCuenta cuenta = buscarCuentaPorId(cliente, id);

            Reporte reporte = reporteCBUCuentaDAO.obtenerReporteCBUCuenta(cliente, cuenta, alias);

            respuestaReporteCBU.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaReporteCBU.setRespuesta(reporte);
        } catch (Exception e) {
            LOGGER.error("Error al obtener reporte CBU", e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

            respuestaReporteCBU.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaReporteCBU.add(itemMensajeRespuesta);
        }
        return respuestaReporteCBU;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#getCuentaById(ar.
     * com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public AbstractCuenta getCuentaById(IdentificacionCuenta id, Cliente cliente) {
        return this.buscarCuentaPorId(cliente, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#actualizarAlias(ar.
     * com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta,
     * java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Void> actualizarAlias(IdentificacionCuenta identificacionCuenta, String alias, Cliente cliente)
            throws BusinessException {
        Respuesta<Void> respuestaAlias = new Respuesta<Void>();

        try {
            if (alias.length() > MAX_ALIAS_LENGTH) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_ALIAS,
                        CodigoMensajeConstantes.CODIGO_ERROR_ALIAS);
            }
            AbstractCuenta cuenta = buscarCuentaPorId(cliente, identificacionCuenta);
            Long nup = Long.valueOf(cliente.getNup());
            String nroCtaProducto = cuenta.getNroCuentaProducto();
            aliasFavoritoProductoDAO.actualizaAlias(nup, nroCtaProducto, alias);

            cuenta.setAlias(alias);

            respuestaAlias.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaAlias.setRespuestaVacia(false);
        } catch (DAOException e) {
            LOGGER.error("Erros al actualizar Alias", e);
            respuestaAlias = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_ACTUALIZAR_ALIAS,
                    CodigoMensajeConstantes.CODIGO_ERROR_ALIAS);
        }
        return respuestaAlias;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#marcarFavorita(ar.
     * com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Void> marcarFavorita(IdentificacionCuenta identificacionCuenta, Cliente cliente)
            throws BusinessException {
        Respuesta<Void> respuestaAlias = new Respuesta<Void>();

        try {
            List<Cuenta> cuentas = cliente.getCuentas();
            for (Cuenta cuenta : cuentas) {
                if (cuenta.isTipoCuentaMonetaria()) {
                    if (!coincideCuentaId(cuenta, identificacionCuenta)) {
                        actualizaFavorito(cliente, cuenta, false);
                    }
                }
            }
            AbstractCuenta cuentaFavorita = buscarCuentaPorId(cliente, identificacionCuenta);
            actualizaFavorito(cliente, cuentaFavorita, true);

            respuestaAlias.setEstadoRespuesta(EstadoRespuesta.OK);
        } catch (RuntimeException e) {
            LOGGER.error("Error al actualizar favorito", e);
            respuestaAlias = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_MARCAR_FAVORITA,
                    CodigoMensajeConstantes.CODIGO_ERROR_FAVORITA);
        } catch (BusinessException e) {
            LOGGER.error("Error al buscar cuenta", e);
            respuestaAlias = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_MARCAR_FAVORITA,
                    CodigoMensajeConstantes.CODIGO_ERROR_FAVORITA);
        }
        return respuestaAlias;
    }

    /**
     * Actualiza favorito.
     *
     * @param cliente  the cliente
     * @param cuenta   the cuenta
     * @param favorita the favorita
     * @throws BusinessException the business exception
     */
    private void actualizaFavorito(Cliente cliente, AbstractCuenta cuenta, boolean favorita) throws BusinessException {
        try {
            Long nup = Long.valueOf(cliente.getNup());
            String nroCtaProducto = cuenta.getNroCuentaProducto();
            aliasFavoritoProductoDAO.actualizaFavorito(nup, nroCtaProducto, favorita);

            cuenta.setIsFavorita(favorita);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar favorito", e);
            throw new BusinessException(e);
        }
    }

    /**
     * Coincide cuenta id.
     *
     * @param cuenta the cuenta
     * @param id     the id
     * @return true, if successful
     */
    private boolean coincideCuentaId(AbstractCuenta cuenta, IdentificacionCuenta id) {
        boolean eqNroCuentaProducto = Long.parseLong(cuenta.getNroCuentaProducto()) == Long
                .parseLong(id.getNroCuentaProducto());
        boolean eqNroSucursal = Long.parseLong(cuenta.getNroSucursal()) == Long.parseLong(id.getNroSucursal());
        return eqNroCuentaProducto && eqNroSucursal;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * cargarAliasYFavoritos(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente)
     */
    @Override
    public void cargarAliasYFavoritos(Cliente cliente, boolean isTipoCuentaMonetaria) throws BusinessException {
        boolean hayFavorita = false;
        Long nup = Long.valueOf(cliente.getNup());

        try {
            List<AliasFavoritoProducto> aliasFavoritos = aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(nup);
            setearAliasDeCuentasBancaPrivada(aliasFavoritos, cliente);
            if (aliasFavoritos != null) {
                for (AliasFavoritoProducto aliasFavoritoProducto : aliasFavoritos) {
                    String nroCtaProducto = aliasFavoritoProducto.getNroCtaProducto();
                    AbstractCuenta cuenta = buscarCuentaByNroCta(cliente.getCuentas(), nroCtaProducto);
                    if (setAtributosCuenta(cuenta, aliasFavoritoProducto, isTipoCuentaMonetaria)) {
                        hayFavorita = true;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener alias y favoritos", e);
            throw new BusinessException(e);
        }

        try {
            // principal como favorita
            if (!hayFavorita) {
                List<Cuenta> cuentas = cliente.getCuentas();
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.isCuentaPrincipal() && !cuenta.isTipoTarjetaValida()) {
                        cuenta.setIsFavorita(true);
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Setear alias de cuentas banca privada.
     *
     * @param aliasFavoritos the alias favoritos
     * @param cliente        the cliente
     */
    private void setearAliasDeCuentasBancaPrivada(List<AliasFavoritoProducto> aliasFavoritos, Cliente cliente) {
        if (aliasFavoritos != null) {
            List<Cuenta> cuentasBP = obtenerCuentasOperativasBancaPrivada(cliente);
            for (AliasFavoritoProducto alias : aliasFavoritos) {
                AbstractCuenta cuenta = buscarCuentaByNroCta(cuentasBP, alias.getNroCtaProducto());
                if (cuenta != null) {
                    cuenta.setAlias(alias.getAlias());
                }
            }
        }
    }

    /**
     * Obtener cuentas operativas banca privada.
     *
     * @param cliente the cliente
     * @return the list
     */
    private List<Cuenta> obtenerCuentasOperativasBancaPrivada(Cliente cliente) {
        List<Cuenta> cuentasBP = new ArrayList<Cuenta>();
        for (CuentaBancaPrivada cuentaBP : cliente.getCuentasBancaPrivada()) {
            cuentasBP.add(cuentaBP.getCuentaOperativa());
        }
        return cuentasBP;
    }

    /**
     * Sets the atributos cuenta.
     *
     * @param cuenta                the cuenta
     * @param aliasFavoritoProducto the alias favorito producto
     * @param isTipoCuentaMonetaria the is tipo cuenta monetaria
     * @return true, if successful
     */
    private boolean setAtributosCuenta(AbstractCuenta cuenta, AliasFavoritoProducto aliasFavoritoProducto,
                                       boolean isTipoCuentaMonetaria) {
        if (cuenta != null) {
            cuenta.setAlias(aliasFavoritoProducto.getAlias());
            if (!cuenta.isCuentaCerrada()) {

                if (isTipoCuentaMonetaria && cuenta.isTipoCuentaMonetaria()
                        || (!isTipoCuentaMonetaria && cuenta.isTipoTarjetaValida())) {
                    if (cuenta.isTipoCuentaMonetaria() || cuenta.isTipoTarjetaValida()) {
                        cuenta.setIsFavorita(aliasFavoritoProducto.getFavorita());
                    }
                }
                if (Boolean.TRUE.equals(aliasFavoritoProducto.getFavorita())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Buscar cuenta by nro cta.
     *
     * @param cuentas        the cuentas
     * @param nroCtaProducto the nro cta producto
     * @return the abstract cuenta
     */
    private AbstractCuenta buscarCuentaByNroCta(List<Cuenta> cuentas, String nroCtaProducto) {

        AbstractCuenta found = null;
        if (cuentas != null) {
            Iterator<Cuenta> it = cuentas.iterator();
            while (it.hasNext() && found == null) {
                Cuenta cuenta = it.next();
                if (Long.valueOf(cuenta.getNroCuentaProducto()).equals(Long.valueOf(nroCtaProducto))) {
                    found = cuenta;
                }
            }
        }
        return found;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentaFavorita(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente)
     */
    // TODO ver urgente

    @Override
    public Respuesta<ResumenDetalleCuenta> obtenerCuentaFavorita(Cliente cliente) throws BusinessException {

        Respuesta<ResumenDetalleCuenta> respuestaDetalleCuenta = new Respuesta<ResumenDetalleCuenta>();
        Cuenta cuenta = null;
        ResumenDetalleCuenta resumenCtaPrincipal = null;
        try {
            cuenta = obtenerCuentaPrincipalCliente(cliente);

            if (cuenta.getIsFavorita() && !cuenta.isCuentaCerrada()) {
                resumenCtaPrincipal = obtenerResumenDetalleCuenta(cuenta);
            }

        } catch (DAOException e) {
            LOGGER.error("No se pudo obtener cuenta principal", e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());

            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        }
        if (resumenCtaPrincipal == null) {
            resumenCtaPrincipal = buscarCuentaPrincipal(cliente);
        }

        if (resumenCtaPrincipal != null) {
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaDetalleCuenta.setRespuesta(resumenCtaPrincipal);
        } else {
            throw new BusinessException(ERROR_NO_HAY_CTA_PRINCIPAL);
        }
        return respuestaDetalleCuenta;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#buscarCuentaPorId(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta)
     */
    @Override
    public AbstractCuenta buscarCuentaPorId(Cliente cliente, IdentificacionCuenta id) {

        AbstractCuenta cuentaEncontrada = null;
        List<Cuenta> cuentas = cliente.getCuentas();
        if (cuentas != null) {
            for (Cuenta cuenta : cuentas) {
                if (coincideCuentaId(cuenta, id)) {
                    cuentaEncontrada = cuenta;
                }
            }
        }
        if (cuentaEncontrada == null) {
            List<CuentaCerrada> cuentasCerradas = cliente.getCuentasCerradas();
            if (cuentasCerradas != null) {
                for (CuentaCerrada cuenta : cuentasCerradas) {
                    if (coincideCuentaId(cuenta, id)) {
                        cuentaEncontrada = cuenta;
                    }
                }
            }
        }
        return cuentaEncontrada;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#buscarCuentaPorId(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String)
     */
    @Override
    public AbstractCuenta buscarCuentaPorId(Cliente cliente, String id) {
        AbstractCuenta cuentaEncontrada = null;

        List<Cuenta> cuentas = cliente.getCuentas();
        if (cuentas != null) {
            for (Cuenta cuenta : cuentas) {
                if (coincideCuentaId(cuenta, id)) {
                    cuentaEncontrada = cuenta;
                    break;
                }
            }
        }
        if (cuentaEncontrada == null) {
            List<CuentaCerrada> cuentasCerradas = cliente.getCuentasCerradas();
            if (cuentasCerradas != null) {
                for (CuentaCerrada cuenta : cuentasCerradas) {
                    if (coincideCuentaId(cuenta, id)) {
                        cuentaEncontrada = cuenta;
                        break;
                    }
                }
            }
        }
        return cuentaEncontrada;
    }

    /**
     * Coincide cuenta id.
     *
     * @param cuenta the cuenta
     * @param id     the id
     * @return true, if successful
     */
    private boolean coincideCuentaId(AbstractCuenta cuenta, String id) {
        return Long.parseLong(cuenta.getId().toString()) == Long.parseLong(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerCuentaPorId(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String)
     */
    @Override
    public Cuenta obtenerCuentaPorId(Cliente cliente, String id) {

        return (Cuenta) this.buscarCuentaPorId(cliente, id);

    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * buscarCuentaByNroTarjetaRecargable(java.util.List, java.lang.String)
     */
    @Override
    public Cuenta buscarCuentaByNroTarjetaRecargable(List<Cuenta> cuentas, String nroTarjeta) throws BusinessException {
        if (cuentas != null) {
            for (Cuenta cuenta : cuentas) {
                if (cuenta.getTipoCuenta().equals(TIPOCTA_VISARECARGABLE) && cuenta.correspondeNroTarjeta(nroTarjeta)) {
                    return cuenta;
                }
            }
        }
        throw new BusinessException("Error obteniendo cuenta Visa Recargables");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#verificarCuenta(ar.
     * com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<Cliente> verificarCuenta(Cliente cliente, String tipoCuenta, String sucursalCuenta,
                                              String nroCuenta) throws BusinessException {
        Cliente clienteRespuesta = null;
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.OK;
        Respuesta<Cliente> respuesta = new Respuesta<Cliente>();
        boolean respuestaVacia = false;
        try {
            clienteRespuesta = detalleCuentaDAO.obtenerCuenta(cliente, tipoCuenta, sucursalCuenta, nroCuenta);
            if (clienteRespuesta == null) {
                estadoRespuesta = EstadoRespuesta.WARNING;
                respuestaVacia = true;
            }
        } catch (CuentaMigradaException e) { // Cierre de sucursales
            LOGGER.error(e.getMessage(), e);
            String[] nrosCuentaMigradas = obtenerNrosCuentaMigracion(e.getMessage());
            return respuestaFactory.crearRespuestaError(nrosCuentaMigradas[1],
                    ErrorAgendaDestinatariosEnum.CUENTAMIGRADA_NUEVA_TRANSFERENCIA.getTipoError(),
                    ErrorAgendaDestinatariosEnum.CUENTAMIGRADA_NUEVA_TRANSFERENCIA.getCodigoMensaje(), nrosCuentaMigradas[0],
                    nrosCuentaMigradas[1]);
        } catch (DAOException e) {
            estadoRespuesta = EstadoRespuesta.ERROR;
            LOGGER.error(e.getMessage(), e);
            respuestaVacia = true;
        }
        respuesta.setRespuestaVacia(respuestaVacia);
        respuesta.setEstadoRespuesta(estadoRespuesta);
        respuesta.setRespuesta(clienteRespuesta);

        return respuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#verificarCuenta2(ar
     * .com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<Cliente> verificarCuenta2(Cliente cliente, String tipoCuenta, String sucursalCuenta,
                                               String nroCuenta) {
        Cliente clienteRespuesta = null;
        EstadoRespuesta estadoRespuesta = EstadoRespuesta.OK;
        Respuesta<Cliente> respuesta = new Respuesta<Cliente>();
        boolean respuestaVacia = false;
        try {
            clienteRespuesta = detalleCuentaDAO.obtenerCuenta2(cliente, tipoCuenta, sucursalCuenta, nroCuenta);
            respuesta.setRespuestaVacia(respuestaVacia);
            respuesta.setEstadoRespuesta(estadoRespuesta);
            respuesta.setRespuesta(clienteRespuesta);
        } catch (CuentaMigradaException e) { // Cierre de sucursales
            LOGGER.error(e.getMessage(), e);
            String[] nrosCuentaMigradas = obtenerNrosCuentaMigracion(e.getMessage());

            LOGGER.info("Se setea en DatosTransferenciaDestino la cuenta continuadora.");
            LOGGER.info("Cuenta continuadora: {}" , nrosCuentaMigradas[1]);

            sesionParametros.getDatosTransferenciaDestino().setNumeroCuenta(nrosCuentaMigradas[1]);

            return respuestaFactory.crearRespuestaError(nrosCuentaMigradas[1],
                    ErrorAgendaDestinatariosEnum.CUENTAMIGRADA_AGENDA.getTipoError(),
                    ErrorAgendaDestinatariosEnum.CUENTAMIGRADA_AGENDA.getCodigoMensaje(), nrosCuentaMigradas[0],
                    nrosCuentaMigradas[1]);
        } catch (CuentaInexistenteException e) {
            setearItemEnRespuesta(CodigoMensajeConstantes.CODIGO_CUENTA_INEXISTENTE_CONSULTA_CUENTA,
                    TipoError.ERROR_CUENTA_INEXISTENTE.getDescripcion(), respuesta);
            LOGGER.error(e.getMessage(), e);
            respuestaVacia = true;
        } catch (DAOException e) {
            setearItemEnRespuesta(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO,
                    TipoError.ERROR_EN_SERVICIO_TIMEOUT_CUENTA.getDescripcion(), respuesta);
            LOGGER.error(e.getMessage(), e);
            respuestaVacia = true;
        }

        return respuesta;
    }


    /**
     * Setear item en respuesta.
     *
     * @param codigoMensaje    the codigo mensaje
     * @param tipoError        the tipo error
     * @param respuestaCliente the respuesta cliente
     */
    private void setearItemEnRespuesta(String codigoMensaje, String tipoError, Respuesta<Cliente> respuestaCliente) {
        Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTag(null);
        item.setTipoError(tipoError);
        item.setMensaje(mensaje.getMensaje());
        respuestaCliente.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaCliente.setRespuestaVacia(true);
        respuestaCliente.setRespuesta(null);
        respuestaCliente.add(item);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerCuenta(ar.
     * com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String)
     */
    @Override
    public Respuesta<ResumenDetalleCuenta> obtenerCuenta(Cliente cliente, String nroCuenta) throws BusinessException {
        Respuesta<ResumenDetalleCuenta> respuestaDetalleCuenta = new Respuesta<ResumenDetalleCuenta>();
        try {
            IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(nroCuenta);

            AbstractCuenta cuenta = getCuentaById(identificacionCuenta, cliente);

            ResumenDetalleCuenta resumenDetalleCuenta = obtenerResumenDetalleCuenta((Cuenta) cuenta);
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaDetalleCuenta.setRespuesta(resumenDetalleCuenta);
        } catch (Exception e) {
            LOGGER.error(ERROR_AL_OBTENER_RESUMEN, e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        }

        return respuestaDetalleCuenta;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerCuentaPrivada(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, java.lang.String)
     */
    @Override
    public Respuesta<ResumenDetalleCuenta> obtenerCuentaPrivada(Cliente cliente, String nroCuenta)
            throws BusinessException {
        Respuesta<ResumenDetalleCuenta> respuestaDetalleCuenta = new Respuesta<ResumenDetalleCuenta>();
        try {
            IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(nroCuenta);
            AbstractCuenta cuenta = getCuentaPrivadaById(identificacionCuenta, cliente);
            ResumenDetalleCuenta resumenDetalleCuenta = obtenerResumenDetalleCuenta((Cuenta) cuenta);
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaDetalleCuenta.setRespuesta(resumenDetalleCuenta);
        } catch (Exception e) {
            LOGGER.error(ERROR_AL_OBTENER_RESUMEN, e);
            ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
            itemMensajeRespuesta.setMensaje(e.getMessage());
            itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            respuestaDetalleCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaDetalleCuenta.add(itemMensajeRespuesta);
        }
        return respuestaDetalleCuenta;
    }

    /**
     * Gets the cuenta privada by id.
     *
     * @param id      the id
     * @param cliente the cliente
     * @return the cuenta privada by id
     */
    public AbstractCuenta getCuentaPrivadaById(IdentificacionCuenta id, Cliente cliente) {
        return buscarCuentaPrivadaPorId(cliente, id);
    }

    /**
     * Buscar cuenta privada por id.
     *
     * @param cliente the cliente
     * @param id      the id
     * @return the abstract cuenta
     */
    public AbstractCuenta buscarCuentaPrivadaPorId(Cliente cliente, IdentificacionCuenta id) {

        AbstractCuenta cuentaEncontrada = null;

        List<Cuenta> cuentas = cliente.getCuentasPrivadas();
        if (cuentas != null) {
            for (Cuenta cuenta : cuentas) {
                if (coincideCuentaId(cuenta, id)) {
                    cuentaEncontrada = cuenta;
                }
            }
        }
        return cuentaEncontrada;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * hasCuentasMonetariasActivasEnPesos(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public Boolean hasCuentasMonetariasActivasEnPesos(Cliente cliente) {
        if (!CollectionUtils.isEmpty(cliente.getCuentas())) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.isCuentaPesos() || cuenta.isCuentaUnica()) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * getCuentasSaldoPorMoneda(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente, java.lang.String)
     */
    @Override
    public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoPorMoneda(Cliente cliente, String tipoMoneda)
            throws BusinessException {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        int cantidadCuentas = 0;
        if (TipoMonedaInversionEnum.ARG.getSimbolo().equals(tipoMoneda)) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.isCuentaPesos() || cuenta.isCuentaUnica()) {
                    if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
                        cantidadCuentas = cantidadCuentas + 2;
                    } else {
                        cantidadCuentas++;
                    }
                    cuentasMonetarias.add(cuenta);
                }
            }
        }
        if (TipoMonedaInversionEnum.USD.getSimbolo().equals(tipoMoneda)) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.isCuentaDolares() || cuenta.isCuentaUnica()) {
                    if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
                        cantidadCuentas = cantidadCuentas + 2;
                    } else {
                        cantidadCuentas++;
                    }
                    cuentasMonetarias.add(cuenta);
                }
            }
        }
        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error", e);
            throw new BusinessException(e);
        }
        List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        List<ItemMensajeRespuesta> itemsMensajesErrores = new ArrayList<ItemMensajeRespuesta>();
        for (Cuenta cuenta : cuentasMonetarias) {
            ResumenDetalleCuenta resumenDetalleCuenta;
            try {
                resumenDetalleCuenta = obtenerResumenDetalleCuenta(cuenta);
            } catch (DAOException e) {
                LOGGER.error(ERROR_AL_OBTENER_RESUMEN, e);

                resumenDetalleCuenta = initResumenDetalleCuenta(cuenta);
                resumenDetalleCuenta.setCbu(cuenta.getCbu());
                resumenDetalleCuenta.setCuentaCerrada(false);
                itemsMensajesErrores.add(respuestaFactory.generarItemMensajeRespuesta(cuenta.getNumeroCuentaTag(),
                        TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDO));
            }
            respuestaDetalleCuenta.add(resumenDetalleCuenta);
        }
        CuentasUtils.ordenarCuentas(respuestaDetalleCuenta);
        Respuesta<List<ResumenDetalleCuenta>> repuesta = new Respuesta<List<ResumenDetalleCuenta>>();
        repuesta.setRespuesta(respuestaDetalleCuenta);
        repuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        repuesta.setRespuestaVacia(false);
        repuesta.setItemMensajeRespuesta(itemsMensajesErrores);
        return repuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#
     * obtenerMensajeCopiarCBU()
     */
    @Override
    public Respuesta<String> obtenerMensajeCopiarCBU() {
        Respuesta<String> respuesta = new Respuesta<String>();
        String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COPIAR_CBU_OK).getMensaje();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(mensaje);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#getCuentasSaldo(ar.
     * com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.List)
     */
    @Override
    public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldo(Cliente cliente, List<Cuenta> cuentas)
            throws BusinessException {
        List<Cuenta> cuentasMonetarias = new ArrayList<Cuenta>();
        Boolean hayFavorita = Boolean.FALSE;
        Cuenta cuentaPrincipal = null;
        ResumenDetalleCuenta resumenPrincipal = null;
        List<ItemMensajeRespuesta> itemsMensajesErrores = new ArrayList<ItemMensajeRespuesta>();
        int cantidadCuentas = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isTipoCuentaMonetaria()) {
                if (TipoCuenta.CUENTA_UNICA.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()))) {
                    cantidadCuentas = cantidadCuentas + 2;
                } else {
                    cantidadCuentas++;
                }
                cuentasMonetarias.add(cuenta);
            }
        }
        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error", e);
            throw new BusinessException(e);
        }
        List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();

        Map<String, AliasFavoritoProducto> aliasPorNroProducto = getAliasPorNroCuentaProducto(cliente);

        for (Cuenta cuenta : cuentasMonetarias) {
            ResumenDetalleCuenta resumenDetalleCuenta;
            try {
                resumenDetalleCuenta = obtenerResumenDetalleCuenta(cuenta);
                respuestaDetalleCuenta.add(resumenDetalleCuenta);
            } catch (DAOException e) {
                LOGGER.error(ERROR_AL_OBTENER_RESUMEN, e);
                resumenDetalleCuenta = initResumenDetalleCuenta(cuenta);
                resumenDetalleCuenta.setCbu(cuenta.getCbu());
                resumenDetalleCuenta.setCuentaCerrada(false);
                itemsMensajesErrores.add(respuestaFactory.generarItemMensajeRespuesta(cuenta.getNumeroCuentaTag(),
                        TipoError.ERROR_ITEM_CUENTA, CodigoMensajeConstantes.CODIGO_ERROR_RECUPERAR_SALDO));
            }

            if (aliasPorNroProducto != null) {

                AliasFavoritoProducto aliasFavorito = aliasPorNroProducto
                        .get(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()));

                if (aliasFavorito != null) {

                    resumenDetalleCuenta.setAlias(aliasFavorito.getAlias());
                    resumenDetalleCuenta.setFavorita(Boolean.TRUE.equals(aliasFavorito.getFavorita()));
                    cuenta.setAlias(aliasFavorito.getAlias());
                    cuenta.setIsFavorita(Boolean.TRUE.equals(aliasFavorito.getFavorita()));
                    hayFavorita = hayFavorita || Boolean.TRUE.equals(aliasFavorito.getFavorita());
                } else {
                    resumenDetalleCuenta.setAlias(null);
                    resumenDetalleCuenta.setFavorita(Boolean.FALSE);
                    cuenta.setAlias(null);
                    cuenta.setIsFavorita(Boolean.FALSE);
                }
                if (cuenta.isCuentaPrincipal()) {

                    cuentaPrincipal = cuenta;
                    resumenPrincipal = resumenDetalleCuenta;
                }
            }
        }
        if (!hayFavorita && cuentaPrincipal != null && resumenPrincipal != null) {

            cuentaPrincipal.setIsFavorita(Boolean.TRUE);
            resumenPrincipal.setFavorita(Boolean.TRUE);
        }
        CuentasUtils.ordenarCuentas(respuestaDetalleCuenta);
        Respuesta<List<ResumenDetalleCuenta>> repuesta = new Respuesta<List<ResumenDetalleCuenta>>();
        repuesta.setRespuesta(respuestaDetalleCuenta);
        repuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        repuesta.setRespuestaVacia(false);
        repuesta.setItemMensajeRespuesta(itemsMensajesErrores);
        return repuesta;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerCuentas(java
     * .util.List, java.util.List,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public void obtenerCuentas(List<Cuenta> cuentasCliente, List<Cuenta> cuentasMonetarias, Cliente cliente)
            throws BusinessException {
        int cantidadCuentas = 0;
        for (Cuenta cuenta : cuentasCliente) {
            if (cuenta.isTipoCuentaMonetaria()) {
                if (cuenta.esCuentaUnica()) {
                    cantidadCuentas = cantidadCuentas + 2;
                } else {
                    cantidadCuentas++;
                }
                cuentasMonetarias.add(cuenta);
            }
        }
        try {
            detalleCuentaDAO.actualizarSaldo(cuentasMonetarias, cliente, cantidadCuentas);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar cuentas", e);
            throw new BusinessException(e);
        }

    }

    /**
     * Obtener cuenta citi.
     *
     * @param cliente the cliente
     * @param cuenta  the cuenta
     * @return the cuenta citi migrada
     */
    @Override
    public Respuesta<CuentaCitiMigrada> obtenerCuentaCiti(AbstractCuenta cuenta, Cliente cliente) {

        CuentasCitiInEntity consultaTraspasoManualInEntity = new CuentasCitiInEntity();
        consultaTraspasoManualInEntity.setCliente(cliente);
        if (cuenta.isCuentaCerrada()) {
            CuentaCerrada cta = (CuentaCerrada) cuenta;
            consultaTraspasoManualInEntity.setSucursal(cta.getNroSucursal());
            consultaTraspasoManualInEntity.setCuentaAltair(cta.getNroCuentaProducto());
        } else {
            Cuenta cta = (Cuenta) cuenta;
            consultaTraspasoManualInEntity.setSucursal(cta.getOficinaAltair());
            consultaTraspasoManualInEntity.setCuentaAltair(
                    (cta.getContratoAltair() != null) ? cta.getContratoAltair().substring(4, 16) : null);
            consultaTraspasoManualInEntity.setDivisa(cta.getMonedaAltair());
        }
        consultaTraspasoManualInEntity.setIsCuentaUnica(cuenta.isCuentaUnica());

        CuentaCitiMigrada cuentaCitiMigrada = new CuentaCitiMigrada();
        try {
            if (consultaTraspasoManualInEntity.getIsCuentaUnica()) {
                obtenerCuentasMigradas(consultaTraspasoManualInEntity, cuentaCitiMigrada, MONEDA_PESOS);
                obtenerCuentasMigradas(consultaTraspasoManualInEntity, cuentaCitiMigrada, MONEDA_DOLAR);
            } else {
                obtenerCuentasMigradas(consultaTraspasoManualInEntity, cuentaCitiMigrada,
                        consultaTraspasoManualInEntity.getDivisa());
            }
        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
        }
        return respuestaFactory.crearRespuestaOk(cuentaCitiMigrada);
    }

    /**
     * Obtener cuentas migradas.
     *
     * @param consultaTraspasoManualInEntity the consulta traspaso manual in entity
     * @param cuentaCitiMigrada              the cuenta citi migrada
     * @param moneda                         the moneda
     * @throws DAOException the DAO exception
     */
    private void obtenerCuentasMigradas(CuentasCitiInEntity consultaTraspasoManualInEntity,
                                        CuentaCitiMigrada cuentaCitiMigrada, String moneda) throws DAOException {
        CuentasCitiOutEntity outEntity;
        outEntity = cuentasCitiDAO.ejecutarConsultaCuentaCity(consultaTraspasoManualInEntity, moneda);
        for (CuentaCitiEntity cuentaCitiEntity : outEntity.getCuentasCiti()) {
            cuentaCitiMigrada.getCuentasCiti().add(cuentaCitiEntity);
        }
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#tieneUnaSolaCuentaIncluyendoBancaPrivada(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    public Boolean tieneUnaSolaCuentaIncluyendoBancaPrivada(Cliente cliente) {
        boolean unicaCuenta = true;
        List<Cuenta> cuentasPrivadas = cliente.getCuentasPrivadas();
        if (tieneUnaSolaCuenta(cliente)) {
            if (cuentasPrivadas.size() >= 1) {
                unicaCuenta = false;
            }
        } else {
            unicaCuenta = false;
        }
        return unicaCuenta;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#tieneUnaSolaCuenta(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Boolean tieneUnaSolaCuenta(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        int cantidadCuentas = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isTipoCuentaMonetaria()) {
                cantidadCuentas++;
            }
        }
        Respuesta<List<CuentaCerrada>> respuestaCuentasCerradas = obtenerCuentascerradas(cliente);

        if (respuestaCuentasCerradas != null &&
                EstadoRespuesta.OK.equals(respuestaCuentasCerradas.getEstadoRespuesta()) &&
                respuestaCuentasCerradas.getRespuesta().size() >= NUMERO_UNO) {

            cantidadCuentas++;

        }

        return 1 == cantidadCuentas;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO#obtenerDetalleCuenta(ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
     */
    @Override
    public ConsultaDetalleCuentaOutEntity obtenerDetalleCuenta(Cuenta cuenta) throws DAOException {
        ConsultaDetalleCuentaInEntity consultaDetalleCuentaIn = getConsultaDetalleCuentaInEntity(cuenta);
        return detalleCuentaDAO.consultaDetalleCuenta(consultaDetalleCuentaIn);
    }

    /**
     * Obtiene los numeros de una cuenta migrada a partir de mensaje de
     * error.
     *
     * @param msgCtaMigrada the error msg
     * @return the string
     */
    private String[] obtenerNrosCuentaMigracion(String msgCtaMigrada) {
        String msg = msgCtaMigrada.toLowerCase();
        String[] ret = new String[2];
        Matcher m = Pattern.compile(PATTERN_MSG_CTA_MIGRADA).matcher(msg.trim());
        if (m.find()) {
            ret[0] = m.group(1);
            ret[1] = m.group(2);
        }
        return ret;
    }
}