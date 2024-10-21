/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.AltaCuentaRequest;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultarCuenta;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ObjectFactory;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dao.ConsultarXmlDAO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarCBUDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AltaCuentaDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class BilleteraServiceHelper.
 */
@Component
public class BilleteraServiceHelper {

    /** The Constant ERRORSMAP. */
    public static final Map<String, String> ERRORSMAP;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraServiceHelper.class);

    /** The canal. */
    @Value("${BILLETERA.CANAL}")
    private String canal;

    /** The consultar Xml DAO. */
    @Autowired
    private ConsultarXmlDAO consultarXmlDAO;

    /** The id banco. */
    @Value("${BILLETERA.IDBANCO}")
    private String idBanco;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The tipoCuenta. */
    @Value("${BILLETERA.TIPOCUENTA}")
    private String tipoCuenta;

    /** The tipos cta. */
    @Value("${BILLETERA.TIPOSCTA}")
    private String tiposCta;

    /** The tipos trj. */
    @Value("${BILLETERA.TIPOSTRJ}")
    private String tiposTrj;

    static {
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put(BilleteraConstants.STATUS_1000, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1000);
        tempMap.put(BilleteraConstants.STATUS_1001, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1001);
        tempMap.put(BilleteraConstants.STATUS_1003, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1003);
        tempMap.put(BilleteraConstants.STATUS_1009, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1009);
        tempMap.put(BilleteraConstants.STATUS_1011, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1011);
        tempMap.put(BilleteraConstants.STATUS_1028, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1028);
        tempMap.put(BilleteraConstants.STATUS_1040, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1040);
        tempMap.put(BilleteraConstants.STATUS_1055, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1055);
        tempMap.put(BilleteraConstants.STATUS_1060, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1060);
        tempMap.put(BilleteraConstants.STATUS_1061, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1061);
        tempMap.put(BilleteraConstants.STATUS_1062, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1062);
        tempMap.put(BilleteraConstants.STATUS_1063, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1063);
        tempMap.put(BilleteraConstants.STATUS_1064, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1064);
        tempMap.put(BilleteraConstants.STATUS_1092, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1092);
        tempMap.put(BilleteraConstants.STATUS_9501, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_9501);
        tempMap.put(BilleteraConstants.STATUS_9505, CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_9505);
        ERRORSMAP = Collections.unmodifiableMap(tempMap);
    }

    /**
     * Obtiene Empresa Celular a partir del codigo recibido desde los servicios
     * de TodoPago.
     *
     * @param empCelWs
     *            Valor a convertir en formato TodoPago
     * @return Valor convertido al formato de la aplicacion
     */
    public static String getEmpresaCel(String empCelWs) {
        if (BilleteraConstants.EMP_CEL_CLARO_WS.equals(empCelWs)) {
            return BilleteraConstants.EMP_CEL_CLARO;
        } else if (BilleteraConstants.EMP_CEL_MOVI_WS.equals(empCelWs)) {
            return BilleteraConstants.EMP_CEL_MOVI;
        } else if (BilleteraConstants.EMP_CEL_NEXT_WS.equals(empCelWs)) {
            return BilleteraConstants.EMP_CEL_NEXT;
        } else if (BilleteraConstants.EMP_CEL_PERS_WS.equals(empCelWs)) {
            return BilleteraConstants.EMP_CEL_PERS;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Empresa Celular para invocar a servicios de TodoPago.
     *
     * @param empCel
     *            Valor a convertir
     * @return Valor convertido al formato TodoPago
     */
    public static String getEmpresaCelWs(String empCel) {
        if (BilleteraConstants.EMP_CEL_CLARO.equals(empCel)) {
            return BilleteraConstants.EMP_CEL_CLARO_WS;
        } else if (BilleteraConstants.EMP_CEL_MOVI.equals(empCel)) {
            return BilleteraConstants.EMP_CEL_MOVI_WS;
        } else if (BilleteraConstants.EMP_CEL_NEXT.equals(empCel)) {
            return BilleteraConstants.EMP_CEL_NEXT_WS;
        } else if (BilleteraConstants.EMP_CEL_PERS.equals(empCel)) {
            return BilleteraConstants.EMP_CEL_PERS_WS;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Genero para invocar a servicios de TodoPago.
     *
     * @param genero
     *            Valor a convertir
     * @return Valor convertido al formato TodoPago
     */
    public static String getGeneroWs(String genero) {
        if (BilleteraConstants.GENERO_IATX_H.equals(genero)) {
            return BilleteraConstants.GENERO_WS_M;
        } else if (BilleteraConstants.GENERO_IATX_M.equals(genero)) {
            return BilleteraConstants.GENERO_WS_F;
        }
        return null;
    }

    /**
     * Obtiene Marca de Tarjeta a partir del identificador de medio de pago.
     *
     * @param tipoMedioPago
     *            Tipo de medio de pago
     * @param idMedioPago
     *            Identificador de medio de pago
     * @return Nombre de la tarjeta
     */
    public static String getMarcaTrjWs(String tipoMedioPago, String idMedioPago) {
        if (BilleteraConstants.TIPO_MP_RECARGABLE.equals(tipoMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_RECARGABLE;
        } else if (BilleteraConstants.TIPO_MP_CREDITO.equals(tipoMedioPago)) {
            return getMarcaTrjCred(idMedioPago);
        } else if (BilleteraConstants.TIPO_MP_DEBITO.equals(tipoMedioPago)) {
            return getMarcaTrjDeb(idMedioPago);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Medio de Pago para invocar a servicios de TodoPago.
     *
     * @param tipoCta
     *            Valor a convertir
     * @return Valor convertido al formato TodoPago
     */
    public static String getMedioPagoWs(String tipoCta) {
        if (BilleteraConstants.TIPOCTA_VISA.equals(tipoCta)) {
            return BilleteraConstants.IDMEDIOPAGO_VISA;
        } else if (BilleteraConstants.TIPOCTA_AMEX.equals(tipoCta)) {
            return BilleteraConstants.IDMEDIOPAGO_AMEX;
        } else if (BilleteraConstants.TIPOCTA_DEB.equals(tipoCta)) {
            return BilleteraConstants.IDMEDIOPAGO_DEB;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Nombre de Tarjeta a partir del identificador de medio de pago.
     *
     * @param idMedioPago
     *            Identificador de medio de pago
     * @return Nombre de la tarjeta
     */
    public static String getNombreTrjWs(String idMedioPago) {
        if (BilleteraConstants.IDMEDIOPAGO_AMEX.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_AMEX;
        } else if (BilleteraConstants.IDMEDIOPAGO_VISA.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_VISA;
        } else if (isTrjDeb(idMedioPago)) {
            return BilleteraConstants.TRJ_DEB;
        } else if (isTrjCred(idMedioPago)) {
            return BilleteraConstants.TRJ_CRED;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Numero de Tarjeta formateado a partir del identificador de medio
     * de pago.
     *
     * @param nroTrj
     *            Numero de tarjeta
     * @param idMedioPago
     *            Identificador de medio de pago
     * @return Numero de tarjeta formateado
     */
    public static String getNroTrjFmtWs(String nroTrj, String idMedioPago) {
        if (BilleteraConstants.IDMEDIOPAGO_VISA.equals(idMedioPago) || isTrjCred(idMedioPago)
                || isTrjDeb(idMedioPago)) {
            return BilleteraConstants.MASK_XXXX + nroTrj.substring(nroTrj.length() - BilleteraConstants.OFF_VISA);
        } else if (BilleteraConstants.IDMEDIOPAGO_AMEX.equals(idMedioPago)) {
            String nroTrjFmt = ISBANStringUtils.eliminarCeros(nroTrj);
            nroTrjFmt = nroTrjFmt.substring(0, BilleteraConstants.LEN_AMEX);
            return BilleteraConstants.MASK_XXXXX
                    + nroTrjFmt.substring(nroTrjFmt.length() - BilleteraConstants.OFF_AMEX);
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene Tipo de Cuenta para invocar a servicios de TodoPago.
     *
     * @param ctaTipo
     *            the cta tipo
     * @return Valor convertido al formato TodoPago
     */
    public static String getTipoCuentaWs(String ctaTipo) {
        if (BilleteraConstants.tiposCtaCteLst.contains(ctaTipo)) {
            return BilleteraConstants.CTA_CTE;
        } else if (BilleteraConstants.tiposCtaAhorroLst.contains(ctaTipo)) {
            return BilleteraConstants.CAJA_AHORRO;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Gets the marca trj cred.
     *
     * @param idMedioPago
     *            the id medio pago
     * @return the marca trj cred
     */
    private static String getMarcaTrjCred(String idMedioPago) {
        if (BilleteraConstants.IDMEDIOPAGO_VISA.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_VISA;
        } else if (BilleteraConstants.IDMEDIOPAGO_AMEX.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_AMEX;
        } else {
            return getMarcaTrjCredOtros(idMedioPago);
        }
    }

    /**
     * Gets the marca trj cred otros.
     *
     * @param idMedioPago
     *            the id medio pago
     * @return the marca trj cred otros
     */
    private static String getMarcaTrjCredOtros(String idMedioPago) {
        if (BilleteraConstants.IDMEDIOPAGO_MASTER.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_MASTER;
        } else if (BilleteraConstants.IDMEDIOPAGO_CABAL.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_CABAL;
        } else if (BilleteraConstants.IDMEDIOPAGO_DINERS.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_DINERS;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Gets the marca trj deb.
     *
     * @param idMedioPago
     *            the id medio pago
     * @return the marca trj deb
     */
    private static String getMarcaTrjDeb(String idMedioPago) {
        if (BilleteraConstants.IDMEDIOPAGO_DEB.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_VISA_DEBITO;
        } else if (BilleteraConstants.IDMEDIOPAGO_MAESTRO.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_MAESTRO;
        } else if (BilleteraConstants.IDMEDIOPAGO_CABAL24.equals(idMedioPago)) {
            return BilleteraConstants.TRJ_MARCA_CABAL24;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Obtiene numero de tarjeta a enviar a servicios.
     *
     * @param mp
     *            Medio de pago asociado
     * @return Numero de tarjeta (15 digitos para AMEX, 16 digitos para el
     *         resto)
     */
    private static String getNroTrjParam(MedioDePagoBilleteraDTO mp) {
        if (BilleteraConstants.IDMEDIOPAGO_AMEX.equals(mp.getIdMedioDePago())) {
            return mp.getNumeroTarjeta().substring(0, BilleteraConstants.LEN_AMEX);
        }
        return mp.getNumeroTarjeta();
    }

    /**
     * Checks if is trj cred.
     *
     * @param idMedioPago
     *            the id medio pago
     * @return true, if is trj cred
     */
    private static boolean isTrjCred(String idMedioPago) {
        return BilleteraConstants.IDMEDIOPAGO_CABAL.equals(idMedioPago)
                || BilleteraConstants.IDMEDIOPAGO_DINERS.equals(idMedioPago)
                || BilleteraConstants.IDMEDIOPAGO_MASTER.equals(idMedioPago);
    }

    /**
     * Checks if is trj deb.
     *
     * @param idMedioPago
     *            the id medio pago
     * @return true, if is trj deb
     */
    private static boolean isTrjDeb(String idMedioPago) {
        return BilleteraConstants.IDMEDIOPAGO_DEB.equals(idMedioPago)
                || BilleteraConstants.IDMEDIOPAGO_CABAL24.equals(idMedioPago)
                || BilleteraConstants.IDMEDIOPAGO_MAESTRO.equals(idMedioPago);
    }

    /**
     * Carga elementos en params para un medio de pago a modificar con novedad
     * B.
     *
     * @param params
     *            Parametros en proceso
     * @param ctasPrNoCs
     *            the ctas pr no cs
     */
    public void addParamsBaja(Map<String, List<String>> params, List<MedioDePagoBilleteraDTO> ctasPrNoCs) {
        for (MedioDePagoBilleteraDTO mp : ctasPrNoCs) {
            if (BilleteraConstants.TIPO_NOVEDAD_BAJA.equals(mp.getTipoNovedad())) {
                addParam(params, BilleteraConstants.PARAM_NRO_TARJETA, getNroTrjParam(mp));
                addParam(params, BilleteraConstants.PARAM_ID_MEDIOPAGO, mp.getIdMedioDePago());
                // Se envia marca de Favorito = "N" para las tarjetas con
                // novedad "B" para evitar error 9517 (Mas de un favorito
                // informado)
                addParam(params, BilleteraConstants.PARAM_FAVORITO, BilleteraConstants.FAVORITO_N);
                addParam(params, BilleteraConstants.PARAM_FECHA_VENC, mp.getFechaVencimiento());
                addParam(params, BilleteraConstants.PARAM_TIPO_NOVEDAD, mp.getTipoNovedad());
            }
        }
    }

    /**
     * Carga elementos en params para un medio de pago desvinculado.
     *
     * @param params
     *            Parametros en proceso
     * @param vincular
     *            Indices de elementos a vincular
     * @param ctasPrCs
     *            the ctas pr cs
     */
    public void addParamsBajaPorDesvincular(Map<String, List<String>> params, String[] vincular,
            List<MedioDePagoBilleteraDTO> ctasPrCs) {
        for (MedioDePagoBilleteraDTO mp : ctasPrCs) {
            int idx = mp.getCtaAsociada().getIndex();
            if (isDesvinculado(idx, vincular)) {
                addParam(params, BilleteraConstants.PARAM_NRO_TARJETA, getNroTrjParam(mp));
                addParam(params, BilleteraConstants.PARAM_ID_MEDIOPAGO, mp.getIdMedioDePago());
                // Se envia marca de Favorito = "N" para las tarjetas con
                // novedad "B" para evitar error 9517 (Mas de un favorito
                // informado)
                addParam(params, BilleteraConstants.PARAM_FAVORITO, BilleteraConstants.FAVORITO_N);
                addParam(params, BilleteraConstants.PARAM_FECHA_VENC, mp.getFechaVencimiento());
                addParam(params, BilleteraConstants.PARAM_TIPO_NOVEDAD, BilleteraConstants.TIPO_NOVEDAD_BAJA);
            }
        }
    }

    /**
     * Carga elementos en SIParams para un medio de pago a modificar con novedad
     * M (utilizado en el circuito de modificacion de medios de pago).
     *
     * @param params
     *            Parametros en proceso
     * @param ctasPrCs
     *            the ctas pr cs
     */
    public void addParamsFvMod(Map<String, List<String>> params, List<MedioDePagoBilleteraDTO> ctasPrCs) {
        for (MedioDePagoBilleteraDTO mp : ctasPrCs) {
            if (BilleteraConstants.TIPO_NOVEDAD_MOD.equals(mp.getTipoNovedad())) {
                addParam(params, BilleteraConstants.PARAM_NRO_TARJETA, getNroTrjParam(mp));
                addParam(params, BilleteraConstants.PARAM_ID_MEDIOPAGO, mp.getIdMedioDePago());
                addParam(params, BilleteraConstants.PARAM_FAVORITO, mp.getFavorito());
                addParam(params, BilleteraConstants.PARAM_FECHA_VENC, mp.getFechaVencimiento());
                addParam(params, BilleteraConstants.PARAM_TIPO_NOVEDAD, BilleteraConstants.TIPO_NOVEDAD_MOD);
            }
        }
    }

    /**
     * Carga elementos en params para un medio de pago a modificar con novedad
     * M.
     *
     * @param params
     *            Parametros en proceso
     * @param principal
     *            Indice del medio de pago favorito (referido a vector de
     *            cuentas)
     * @param vincular
     *            Contiene los indices de cuenta seleccionados para vincular
     * @param ctasPrCs
     *            the ctas pr cs
     */
    public void addParamsMod(Map<String, List<String>> params, String principal, String[] vincular,
            List<MedioDePagoBilleteraDTO> ctasPrCs) {
        int idxPrincipalOri = -1;
        MedioDePagoBilleteraDTO mpfOriginal = getFavorito(ctasPrCs);
        if (mpfOriginal != null) {
            idxPrincipalOri = mpfOriginal.getCtaAsociada().getIndex();
        }
        boolean cbioPpal = cambioPrincipal(principal, ctasPrCs);
        int idxPrincipal = Integer.parseInt(principal);
        detectarModificaciones(params, vincular, ctasPrCs, idxPrincipalOri, cbioPpal, idxPrincipal);
    }

    /**
     * Determina si se cambio el favorito en la pantalla.
     *
     * @param principalActual
     *            Indice del favorito actual
     * @param ctasPrCs
     *            the ctas pr cs
     * @return true si se modifico el favorito
     */
    public boolean cambioPrincipal(String principalActual, List<MedioDePagoBilleteraDTO> ctasPrCs) {
        MedioDePagoBilleteraDTO mpfOriginal = getFavorito(ctasPrCs);
        if (mpfOriginal != null) {
            Cuenta cuentaOriginal = mpfOriginal.getCtaAsociada();
            if (cuentaOriginal.getIndex() != Integer.parseInt(principalActual)) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check errors.
     *
     * @param statusSet
     *            the status set
     * @return the respuesta
     */
    public Respuesta<AdministrarMedioPagoDTO> checkErrors(Set<String> statusSet) {
        if (statusSet.size() == 1) {
            Iterator<String> errors = statusSet.iterator();
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
                    getCodigoMensajeError(errors.next()));
        } else {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
        }
    }

    /**
     * Fill params administrar medio pago.
     *
     * @param params
     *            the params
     * @param cliente
     *            the cliente
     * @param idx
     *            the idx
     * @param principal
     *            the principal
     * @param tipoNov
     *            the tipo nov
     * @throws ParseException
     *             the parse exception
     */
    public void fillParamsAdministrarMedioPago(Map<String, List<String>> params, Cliente cliente, String idx,
            String principal, String tipoNov) throws ParseException {
        Cuenta cuentaVinc = cliente.getCuentas().get(Integer.parseInt(idx));
        String numeroTarjeta = ISBANStringUtils.eliminarCeros(cuentaVinc.getNroTarjetaCredito());
        String tipoCta = cuentaVinc.getTipoCuenta();
        String favorito = idx.equals(principal) ? BilleteraConstants.FAVORITO_S : BilleteraConstants.FAVORITO_N;
        String fechaVenc = BilleteraUtils.getFechaVtoTrj(cuentaVinc.getCbu(), false);

        if (BilleteraConstants.TIPOCTA_AMEX.equals(tipoCta)) {
            numeroTarjeta = numeroTarjeta.substring(0, BilleteraConstants.LEN_AMEX);
        }

        addParam(params, BilleteraConstants.PARAM_NRO_TARJETA, numeroTarjeta);
        addParam(params, BilleteraConstants.PARAM_ID_MEDIOPAGO, getMedioPagoWs(tipoCta));
        addParam(params, BilleteraConstants.PARAM_FAVORITO, favorito);
        addParam(params, BilleteraConstants.PARAM_FECHA_VENC, fechaVenc);
        addParam(params, BilleteraConstants.PARAM_TIPO_NOVEDAD, tipoNov);
    }

    /**
     * Fill params alta cuenta.
     *
     * @param dto
     *            the dto
     * @param datosSolicitante
     *            the datos solicitante
     * @param cliente
     *            the cliente
     * @param email
     *            the email
     * @return the alta cuenta request
     * @throws DAOException
     *             the DAO exception
     */
    public AltaCuentaRequest fillParamsAltaCuenta(BilleteraInDTO dto, DatosSolicitanteEntity datosSolicitante,
            Cliente cliente, String email) throws DAOException {
        ObjectFactory objectFactory = new ObjectFactory();
        AltaCuentaRequest parameters = objectFactory.createAltaCuentaRequest();
        String contrasenia = BilleteraUtils.hash(dto.getClave());
        String respSeg = BilleteraUtils.hash(dto.getRespuestaSeguridad().toUpperCase());
        parameters.setBanco(idBanco);
        parameters.setTipoCuenta(tipoCuenta);
        parameters.setMail(email);
        parameters.setContrasenia(contrasenia);
        parameters.setPreguntaSeguridad(dto.getPreguntaSeguridad());
        parameters.setRespPregSeguridad(respSeg);
        parameters.setTipoDocumento(BilleteraUtils.tipoDocBanelco(cliente.getTipoDocumento()));
        parameters.setNroDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni()));
        parameters.setGenero(getGeneroWs(datosSolicitante.getSexo().trim()));
        parameters.setNombre(cliente.getNombre());
        parameters.setApellido(cliente.getApellido1());
        parameters.setFechaNacimiento(cliente.getFechaNacimiento());
        parameters.setNacionalidad(
                consultarXmlDAO.consultarNacionalidadPorId(datosSolicitante.getNacionalidad()).getIdPrisma());
        parameters.setCalle(datosSolicitante.getCalle().trim());
        parameters.setNumeroCalle(datosSolicitante.getCalleNro().trim());
        parameters.setPiso(datosSolicitante.getPiso().trim());
        parameters.setDepartamento(datosSolicitante.getDepto().trim());
        parameters.setCodPostal(datosSolicitante.getCp().trim());
        parameters.setProvincia(consultarXmlDAO.consultarProvinciaPorId(datosSolicitante.getProvincia()).getIdPrisma());
        parameters.setLocalidad(consultarXmlDAO.consultarLocalidadPorId(datosSolicitante.getProvincia()).getId());
//        String telFijo = datosSolicitante.getTelefono().trim();
//        parameters.setTelefonoFijo((telFijo.length() <= 10) ? telFijo : "");
        parameters.setTelefonoFijo(""); // TODO : Comentado hasta verificar bien de donde se saca el telefono ya que se envian solo 4 dgitos actualmente y falla
        parameters.setCompaniaCelular(getEmpresaCelWs(dto.getEmpresaSeleccionada()));
        parameters.setNumeroCelular(dto.getCodigoArea() + dto.getTelefono());
        parameters.setAceptaTyC(BilleteraConstants.ACEPTA_TYC);
        parameters.setFactorValidacion(BilleteraConstants.FACTOR_VALIDACION);
        parameters.setCanal(canal);

        return parameters;
    }

    /**
     * Fill params consultar cuenta.
     *
     * @param sexo
     *            the sexo
     * @param cliente
     *            the cliente
     * @return the consultar cuenta
     */
    public ConsultarCuenta fillParamsConsultarCuenta(String sexo, Cliente cliente) {
        ObjectFactory objectFactory = new ObjectFactory();
        ConsultarCuenta parameters = objectFactory.createConsultarCuenta();
        parameters.setBanco(idBanco);
        parameters.setTipoDocumento(BilleteraUtils.tipoDocBanelco(cliente.getTipoDocumento()));
        parameters.setNroDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni()));
        parameters.setGenero(getGeneroWs(sexo));

        return parameters;
    }

    /**
     * Obtiene medio de pago favorito.
     *
     * @param lst
     *            Lista de medios de pago
     * @return Medio de pago favorito
     */
    public MedioDePagoBilleteraDTO getFavorito(List<MedioDePagoBilleteraDTO> lst) {
        Iterator<MedioDePagoBilleteraDTO> itMediosPago = lst.iterator();
        while (itMediosPago.hasNext()) {
            MedioDePagoBilleteraDTO mp = itMediosPago.next();
            if (BilleteraConstants.FAVORITO_S.equals(mp.getFavorito())) {
                return mp;
            }
        }
        return null;
    }

    /**
     * Obtiene las tarjetas vinculadas en Billetera.
     *
     * @param ctasPrCs
     *            Medios de pago que tiene el cliente y aparecen en Prisma
     * @return Medios de pago que tiene al cliente y aparecen en Prisma
     *         (favorito primero)
     */
    public List<MedioDePagoBilleteraDTO> getTarjetasVinculadasBilletera(List<MedioDePagoBilleteraDTO> ctasPrCs) {
        List<MedioDePagoBilleteraDTO> lst = new ArrayList<MedioDePagoBilleteraDTO>();
        // Tarjetas ya vinculadas - Favorita
        MedioDePagoBilleteraDTO mpf = getFavorito(ctasPrCs);
        if (mpf != null) {
            lst.add(mpf);
        }
        // Tarjetas ya vinculadas - No favoritas
        for (MedioDePagoBilleteraDTO mp : ctasPrCs) {
            if (!BilleteraConstants.FAVORITO_S.equals(mp.getFavorito())) {
                lst.add(mp);
            }
        }
        return lst;
    }

    /**
     * Detecta si hay items que se desvincularon.
     *
     * @param vincular
     *            Contiene los indices de cuenta seleccionados para vincular
     * @param ctasPrCs
     *            the ctas pr cs
     * @return true si existe al menos un item desvinculado
     */
    public boolean hayDesvinculados(String[] vincular, List<MedioDePagoBilleteraDTO> ctasPrCs) {
        for (MedioDePagoBilleteraDTO mp : ctasPrCs) {
            int idx = mp.getCtaAsociada().getIndex();
            if (isDesvinculado(idx, vincular)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detecta si hay nuevos items a vincular.
     *
     * @param vincular
     *            Contiene los indices de cuenta seleccionados para vincular
     * @param ctasPrCs
     *            the ctas pr cs
     * @return true si existe al menos un nuevo item a vincular
     */
    public boolean hayNuevosVinculados(String[] vincular, List<MedioDePagoBilleteraDTO> ctasPrCs) {
        for (String idx : vincular) {
            if (!isMpVinculado(idx, ctasPrCs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determina si un medio de pago (indicado por idx) fue desvinculado.
     *
     * @param idx
     *            Indice a verificar
     * @param vincular
     *            Indices de tarjetas a vincular
     * @return true si la tarjeta fue desvinculada
     */
    public boolean isDesvinculado(int idx, String[] vincular) {
        boolean desvinculado = false;
        boolean found = false;
        if (vincular != null) {
            for (String vinc : vincular) {
                if (Integer.parseInt(vinc) == idx) {
                    found = true;
                }
            }
            if (!found) {
                desvinculado = true;
            }
        }
        return desvinculado;
    }

    /**
     * Determina si un medio de pago ya esta vinculado.
     *
     * @param idx
     *            Indice de cuenta del medio de pago a procesar
     * @param ctasPrCs
     *            the ctas pr cs
     * @return true si el medio de pago ya esta vinculado
     */
    public boolean isMpVinculado(String idx, List<MedioDePagoBilleteraDTO> ctasPrCs) {
        List<MedioDePagoBilleteraDTO> mediosPago = getTarjetasVinculadasBilletera(ctasPrCs);
        for (MedioDePagoBilleteraDTO mp : mediosPago) {
            if (mp.getCtaAsociada().getIndex() == Integer.parseInt(idx)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procesamiento de errores para Administrar CBU.
     *
     * @param status
     *            Status recibido
     * @param sesionBilletera
     *            the sesion billetera
     * @return the respuesta
     */
    public Respuesta<AdministrarCBUDTO> prcErrorAdmCbu(String status, SesionBilletera sesionBilletera) {
        if (BilleteraConstants.STATUS_00000.equals(status)) {
            return respuestaFactory.crearRespuestaOk(AdministrarCBUDTO.class, new AdministrarCBUDTO());
        } else {
            sesionBilletera.setErrCta(true);
            LOGGER.error("administrarCbu - Error al invocar administrarCBU. Status: {}", status);
            String codMensaje = getCodigoMensajeError(status);
            if (BilleteraConstants.statusErrorCuentaTpLst.contains(status)) {
                String errMsg = mensajeDAO.obtenerMensajePorCodigo(codMensaje).getMensaje();
                return respuestaFactory.crearRespuestaErrorPersonalizado(AdministrarCBUDTO.class, errMsg,
                        BilleteraConstants.COD_ERR_0);
            } else if (BilleteraConstants.statusErrorRetAdmCbuLst.contains(status)) {
                LOGGER.error(mensajeDAO.obtenerMensajePorCodigo(codMensaje).getMensaje());
                return respuestaFactory.crearRespuestaOk(AdministrarCBUDTO.class, new AdministrarCBUDTO());
            } else if (BilleteraConstants.STATUS_1091.equals(status)) {
                LOGGER.error(
                        mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO).getMensaje());
                return respuestaFactory.crearRespuestaOk(AdministrarCBUDTO.class, new AdministrarCBUDTO());
            } else {
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
            }
        }
    }

    /**
     * Procesamiento de errores para Administrar Clave.
     *
     * @param status
     *            Status recibido
     * @return the respuesta
     */
    public Respuesta<Void> prcErrorAdmClave(String status) {
        if (BilleteraConstants.STATUS_00000.equals(status)) {
            return respuestaFactory.crearRespuestaOk(Void.class);
        } else {
            String codMensaje = getCodigoMensajeError(status);
            String errMsg = mensajeDAO.obtenerMensajePorCodigo(codMensaje).getMensaje();
            if (BilleteraConstants.statusErrorCuentaTpLst.contains(status)) {
                return respuestaFactory.crearRespuestaErrorPersonalizado(Void.class, errMsg,
                        TipoError.ERROR_PRIMER_INGRESO1.toString());
            } else if (BilleteraConstants.STATUS_1055.equals(status)) {
                return respuestaFactory.crearRespuestaErrorPersonalizado(Void.class, errMsg,
                        TipoError.ERROR_PRIMER_INGRESO2.toString());
            } else if (BilleteraConstants.STATUS_1064.equals(status)) {
                return respuestaFactory.crearRespuestaErrorPersonalizado(Void.class, errMsg,
                        TipoError.ERROR_PRIMER_INGRESO3.toString());
            } else {
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
            }
        }
    }

    /**
     * Procesamiento de errores para Administrar Medios de Pago.
     *
     * @param status
     *            Status recibido
     * @param sesionBilletera
     *            the sesion billetera
     * @return the respuesta
     */
    public Respuesta<AdministrarMedioPagoDTO> prcErrorAdmMedioPago(String status, SesionBilletera sesionBilletera) {
        if (BilleteraConstants.STATUS_00000.equals(status)) {
            return respuestaFactory.crearRespuestaOk(AdministrarMedioPagoDTO.class, new AdministrarMedioPagoDTO());
        } else {
            sesionBilletera.setErrCta(true);
            LOGGER.error("administrarMedioPago - Error al invocar administrarMedioPago. Status: {}", status);
            String codMensaje = getCodigoMensajeError(status);
            return prcStatusAdmMedioPago(status, codMensaje);
        }
    }

    /**
     * prcErrorAltaCuenta.
     *
     * @param altaCuentaDTO
     *            the alta cuenta DTO
     * @param sesionBilletera
     *            the sesion billetera
     * @return the respuesta
     */
    public Respuesta<AltaCuentaDTO> prcErrorAltaCuenta(AltaCuentaDTO altaCuentaDTO, SesionBilletera sesionBilletera) {
        String status = altaCuentaDTO.getStatus();
        if (BilleteraConstants.STATUS_00000.equals(status)) {
            sesionBilletera.setIdCuentaAltaCuenta(altaCuentaDTO.getIdCuenta());
            return respuestaFactory.crearRespuestaOk(AltaCuentaDTO.class, altaCuentaDTO);
        }
        LOGGER.error("Error al invocar altaCuenta. Status: {}", status);
        String err;
        String codMensaje = getCodigoMensajeError(status);
        if (BilleteraConstants.statusErrorReturnAdhLst.contains(status)) {
            if (BilleteraConstants.STATUS_1040.equals(status)) {
                codMensaje = CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA;
            }
            err = BilleteraConstants.COD_ERR_1;
        } else if (BilleteraConstants.STATUS_1009.equals(status)) {
            err = BilleteraConstants.COD_ERR_2;
        } else {
            codMensaje = CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA;
            err = BilleteraConstants.COD_ERR_0;
        }
        Respuesta<AltaCuentaDTO> respuestaError = respuestaFactory.crearRespuestaError(AltaCuentaDTO.class, null,
                TipoError.ERROR_ADHESION_BILLETERA, codMensaje);
        respuestaError.getItemsMensajeRespuesta().get(0).setExtra(err);
        return respuestaError;
    }

    /**
     * Adds the param.
     *
     * @param params
     *            the params
     * @param paramKey
     *            the param key
     * @param paramValue
     *            the param value
     */
    private void addParam(Map<String, List<String>> params, String paramKey, String paramValue) {
        if (params.get(paramKey) == null) {
            params.put(paramKey, new ArrayList<String>());
        }
        params.get(paramKey).add(paramValue);
    }

    /**
     * Detectar modificaciones.
     *
     * @param params
     *            the params
     * @param vincular
     *            the vincular
     * @param ctasPrCs
     *            the ctas pr cs
     * @param idxPrincipalOri
     *            the idx principal ori
     * @param cbioPpal
     *            the cbio ppal
     * @param idxPrincipal
     *            the idx principal
     */
    private void detectarModificaciones(Map<String, List<String>> params, String[] vincular,
            List<MedioDePagoBilleteraDTO> ctasPrCs, int idxPrincipalOri, boolean cbioPpal, int idxPrincipal) {
        for (MedioDePagoBilleteraDTO mp : ctasPrCs) {
            int idx = mp.getCtaAsociada().getIndex();
            // Detecta medios de pago con modificacion
            // (con estado de favorito cambiado, o novedad de modificacion
            // cargada por ajuste de fv)
            if ((cbioPpal && (idx == idxPrincipal || idx == idxPrincipalOri))
                    || BilleteraConstants.TIPO_NOVEDAD_MOD.equals(mp.getTipoNovedad())) {
                // Omite informar medios de pago con favorito removido si
                // tambien fueron desvinculados
                if (cbioPpal && idx == idxPrincipalOri && isDesvinculado(idx, vincular)) {
                    continue;
                }
                String favorito = mp.getCtaAsociada().getIndex() == idxPrincipal ? BilleteraConstants.FAVORITO_S
                        : BilleteraConstants.FAVORITO_N;
                addParam(params, BilleteraConstants.PARAM_NRO_TARJETA, getNroTrjParam(mp));
                addParam(params, BilleteraConstants.PARAM_ID_MEDIOPAGO, mp.getIdMedioDePago());
                addParam(params, BilleteraConstants.PARAM_FAVORITO, favorito);
                addParam(params, BilleteraConstants.PARAM_FECHA_VENC, mp.getFechaVencimiento());
                addParam(params, BilleteraConstants.PARAM_TIPO_NOVEDAD, BilleteraConstants.TIPO_NOVEDAD_MOD);
            }
        }
    }

    /**
     * Gets the codigo mensaje error.
     *
     * @param status
     *            the status
     * @return the codigo mensaje error
     */
    private String getCodigoMensajeError(String status) {
        String codigoMensaje = ERRORSMAP.get(status);
        if (codigoMensaje != null) {
            return codigoMensaje;
        }
        return CodigoMensajeConstantes.CODIGO_ERROR_GENERICO;
    }

    /**
     * Prc status adm medio pago.
     *
     * @param status
     *            the status
     * @param codMensaje
     *            the cod mensaje
     * @return the respuesta
     */
    private Respuesta<AdministrarMedioPagoDTO> prcStatusAdmMedioPago(String status, String codMensaje) {
        if (BilleteraConstants.statusErrorCuentaTpLst.contains(status)
                || BilleteraConstants.statusErrorAdmMedioPagoLst.contains(status)) {
            String errMsg = mensajeDAO.obtenerMensajePorCodigo(codMensaje).getMensaje();
            LOGGER.error(errMsg);
            return respuestaFactory.crearRespuestaOk(AdministrarMedioPagoDTO.class, new AdministrarMedioPagoDTO());
        } else if (BilleteraConstants.statusErrorOperAdmMedioPagoLst.contains(status)
                || BilleteraConstants.statusErrorGralAdmMedioPagoLst.contains(status)
                || BilleteraConstants.statusErrorGricoAdmMedioPagoLst.contains(status)) {
            return respuestaFactory.crearRespuestaOk(AdministrarMedioPagoDTO.class, new AdministrarMedioPagoDTO());
        } else {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }
}
