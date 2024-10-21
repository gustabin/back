/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.bo.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MedioPago;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MedioPagoResponse;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPago;
import ar.com.santanderrio.obp.generated.webservices.billetera.admmediopago.MediosPagoResponse;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultaCuentaResult;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.ConsultarCuenta;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.MedioDePago;
import ar.com.santanderrio.obp.servicios.billetera.bo.BilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dao.BilleteraDAO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarCBUDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarMedioPagoDTO.AdministrarMedioPagoItem;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.ConsultaCuentaDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.CuentaBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.ValidarUsuarioBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraServiceHelper;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;

/**
 * The Class BilleteraBO.
 *
 */
@Component("billeteraBO")
public class BilleteraBOImpl implements BilleteraBO {

    /** The Constant ESTADOESP_NO_ACTIVA. */
    private static final String ESTADOESP_NO_ACTIVA = "NA";

    /** The Constant ESTADOESP_V. */
    private static final String ESTADOESP_V = "V";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraBOImpl.class);

    /** The billetera DAO. */
    @Autowired
    protected BilleteraDAO billeteraDAO;

    /** The billetera service helper. */
    @Autowired
    protected BilleteraServiceHelper billeteraServiceHelper;

    /** The canal. */
    @Value("${BILLETERA.CANAL}")
    protected String canal;

    /** The contrato DAO. */
    @Autowired
    protected ContratoDAO contratoDAO;

    /** The datos selectores service. */
    @Autowired
    protected DatosSelectoresService datosSelectoresService;

    /** The datos solicitante DAO. */
    @Autowired
    protected DatosSolicitanteDAO datosSolicitanteDAO;

    /** The id banco. */
    @Value("${BILLETERA.IDBANCO}")
    protected String idBanco;

    /** The mensaje DAO. */
    @Autowired
    protected MensajeDAO mensajeDAO;

    /** The nup dao. */
    @Autowired
    protected NUPDAO nupDao;

    /** The respuesta factory. */
    @Autowired
    protected RespuestaFactory respuestaFactory;

    /** The sesion billetera. */
    @Autowired
    protected SesionBilletera sesionBilletera;

    /** The sesion cliente. */
    @Autowired
    protected SesionCliente sesionCliente;

    /** The tipo acreditacion. */
    @Value("${BILLETERA.TIPOACRED}")
    protected String tipoAcreditacion;

    /** The tipoCuenta. */
    @Value("${BILLETERA.TIPOCUENTA}")
    protected String tipoCuenta;

    /** The tipos cta. */
    @Value("${BILLETERA.TIPOSCTA}")
    protected String tiposCta;

    /** The tipos trj. */
    @Value("${BILLETERA.TIPOSTRJ}")
    protected String tiposTrj;

    /**
     * Marcar adhesion.
     *
     * @param cliente
     *            the cliente
     * @param registroSesion
     *            the registro sesion
     * @return true, if successful
     */
    @Override
    public boolean marcarAdhesion(Cliente cliente, RegistroSesion registroSesion) {
        try {
            aceptarContrato(cliente, registroSesion.getClienteSinonimo());
            return true;
        } catch (DAOException e) {
            LOGGER.error("Error al fijar la marca de adhesion", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ar.com.santanderrio.obp.servicios.billetera.bo.BilleteraBO#validarUsuario
     * (int)
     */
    @Override
    public Respuesta<ValidarUsuarioBilleteraDTO> validarUsuario(int idxCtaTp, RegistroSesion registroSesion) {
        try {
            if (!BilleteraUtils.checkEdad(sesionCliente.getCliente())) {
                // Fija la marca de adhesion en HB_CLIENT_MASTER
                marcarAdhesion(sesionCliente.getCliente(), registroSesion);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BILLETERA_MENOR_EDAD,
                        CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_1009);
            }
        } catch (ParseException e) {
            LOGGER.error("Error al determinar la edad", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }

        List<MedioDePagoBilleteraDTO> tarjetasCliente;
        try {
            tarjetasCliente = getTarjetasCliente();
        } catch (ParseException e) {
            LOGGER.error("Error al obtener las tarjetas del cliente.", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
        List<Cuenta> ctas = getCuentasAcreditacion();
        if (ctas.isEmpty() && tarjetasCliente.isEmpty()) {
            LOGGER.error("Error: no se dispone de cuentas ni tarjetas para operar en Billetera.");
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIN_CUENTAS_Y_TARJETAS,
                    CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_SIN_CUENTAS_Y_TARJETAS);
        }

        // Consulta cuenta Billetera del usuario a validar utilizando cache.
        // El cache se limpia para forzar una nueva consulta:
        // - luego de invocar a administrarClaveBancos sin error en Primer
        // Ingreso
        // - luego de invocar a administrarClaveBancos sin error en Recupero de
        // Clave
        // - luego del comprobante de Configuracion
        Respuesta<ConsultaCuentaDTO> respuestaConsultarCuenta = getRespuestaConsultarCuenta();

        return obtenerRespuestaValidarUsuario(respuestaConsultarCuenta, idxCtaTp, ctas, tarjetasCliente);
    }

    /**
     * Aceptar contrato.
     *
     * @param cliente
     *            the cliente
     * @param clienteSinonimo
     *            the cliente sinonimo
     * @throws DAOException
     *             the DAO exception
     */
    private void aceptarContrato(Cliente cliente, boolean clienteSinonimo) throws DAOException {
        LOGGER.info("Aceptar Contrato de Billetera");
        try {
            ContratoInEntity contratoInEntity = new ContratoInEntity();
            contratoInEntity.setDni(cliente.getDni());
            contratoInEntity.setFechaNac(cliente.getFechaNacimiento());
            contratoDAO.aceptarContrato(contratoInEntity, CampoEnum.ADHESION_BILLETERA,
                    clienteSinonimo ? SinonimoEnum.REPETIDO : SinonimoEnum.NO_REPETIDO);
        } catch (DAOException e) {
            LOGGER.error("Error al aceptar el contrato ", e);
            throw e;
        }
    }

    /**
     * Implementa proceso de actualizacion de medios de pago.
     *
     * @param idCuenta
     *            Identificador de cuenta TodoPago a procesar
     * @throws DAOException
     *             the DAO exception
     */
    private void actualizarMediosPago(String idCuenta) throws DAOException {
        Map<String, List<String>> params = new HashMap<String, List<String>>();

        // Acciones necesarias al modificar la billetera

        // Carga en params los mps ajustados con novedad M porque estan vencidos
        billeteraServiceHelper.addParamsFvMod(params, sesionBilletera.getList(BilleteraConstants.LST_PR_CS));

        // Carga en params los mps ajustados con novedad B porque no estan mas
        // en RCD
        billeteraServiceHelper.addParamsBaja(params, sesionBilletera.getList(BilleteraConstants.LST_PR_NOCS));

        if (!params.isEmpty()) {
            MediosPago mediosPago = getMediosPago(params);
            MediosPagoResponse mediosPagoResponse = billeteraDAO.administrarMedioPago(Integer.parseInt(idCuenta),
                    idBanco, canal, mediosPago);
            List<MedioPagoResponse> medioPagoResponse = mediosPagoResponse.getMedioPagoResponse();

            actualizarNovedadMediosPago(medioPagoResponse);
        }
    }

    /**
     * Actualiza el tipo de novedad de los medios de pago en las listas cargadas
     * en session.
     *
     * @param medioPagoResponse
     *            resultante del servicio administrarMedioPago
     */
    private void actualizarNovedadMediosPago(List<MedioPagoResponse> medioPagoResponse) {
        List<MedioDePagoBilleteraDTO> ctasPrCs = sesionBilletera.getList(BilleteraConstants.LST_PR_CS);
        List<MedioDePagoBilleteraDTO> ctasPrNoCs = sesionBilletera.getList(BilleteraConstants.LST_PR_NOCS);

        for (MedioPagoResponse medioPago : medioPagoResponse) {
            String status = medioPago.getStatus();
            if (BilleteraConstants.STATUS_00000.equals(status)) {
                // Actualiza medios de pago en listas para no ser impactados
                // nuevamente
                String nroTrj = medioPago.getNumeroTarjeta();
                String nroT = ISBANStringUtils.eliminarCeros(nroTrj);
                nroT = BilleteraUtils.llenarConCerosDerecha(nroT, BilleteraConstants.LEN_VISA);
                MedioDePagoBilleteraDTO mp = BilleteraUtils.getMedioPago(ctasPrCs, nroT);
                ajustaMp(ctasPrNoCs, nroT, mp);
            }
        }
    }

    /**
     * Administrar cbu.
     *
     * @param idxCta
     *            the idx cta
     * @param cuit
     *            the cuit
     * @param modo
     *            the modo
     * @return the respuesta
     */
    private Respuesta<AdministrarCBUDTO> administrarCbu(int idxCta, String cuit, String modo) {
        String idCuenta = "";
        String novedad = "";
        if (BilleteraConstants.MODE_ADH.equals(modo)) {
            idCuenta = sesionBilletera.getIdCuentaAltaCuenta();
            novedad = BilleteraConstants.TIPO_NOVEDAD_ALTA;
        } else if (BilleteraConstants.MODE_ALTA.equals(modo)) {
            idCuenta = sesionBilletera.getIdCuenta();
            novedad = BilleteraConstants.TIPO_NOVEDAD_ALTA;
        } else if (BilleteraConstants.MODE_MOD.equals(modo)) {
            idCuenta = sesionBilletera.getIdCuenta();
            novedad = BilleteraConstants.TIPO_NOVEDAD_MOD;
        }
        try {
            Cuenta cuenta = sesionCliente.getCliente().getCuentas().get(idxCta);
            String status = billeteraDAO.administrarCBUBancos(Integer.parseInt(idCuenta), idBanco,
                    Integer.parseInt(canal), tipoAcreditacion, cuenta.getCbu(),
                    BilleteraServiceHelper.getTipoCuentaWs(cuenta.getTipoCuenta()), cuenta.getMonedaBanelco(),
                    cuenta.getNroCuentaProducto(), novedad, idBanco, cuit);
            return billeteraServiceHelper.prcErrorAdmCbu(status, sesionBilletera);
        } catch (DAOException e) {
            sesionBilletera.setErrCta(true);
            LOGGER.error("administrarCbu - Error al invocar administrarCBU: ", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }

    /**
     * Administrar medio pago.
     *
     * @param inDTO
     *            the dto
     * @param modificacion
     *            the modificacion
     * @return the respuesta
     */
    private Respuesta<AdministrarMedioPagoDTO> administrarMedioPago(BilleteraInDTO inDTO, boolean modificacion) {
        String[] vincular = inDTO.getVincular();
        String principal = inDTO.getPrincipal();
        try {
            String idCuenta;
            if (modificacion) {
                String idxCtaTp = inDTO.getIdxCtaTp();
                int indexCtaTp = Integer.parseInt(idxCtaTp);
                ConsultaCuentaDTO consultacuentaDTO = sesionBilletera.getConsultaCuentaDto();
                idCuenta = consultacuentaDTO.getCuentas().get(indexCtaTp).getIdCuenta();
            } else {
                idCuenta = sesionBilletera.getIdCuentaAltaCuenta();
            }
            Map<String, List<String>> params = getParams(modificacion, vincular, principal);

            MediosPago mediosPago = getMediosPago(params);

            MediosPagoResponse mediosPagoResponse = billeteraDAO.administrarMedioPago(Integer.parseInt(idCuenta),
                    idBanco, canal, mediosPago);
            List<MedioPagoResponse> medioPagoResponse = mediosPagoResponse.getMedioPagoResponse();
            for (MedioPagoResponse medioPago : medioPagoResponse) {
                String status = medioPago.getStatus();
                Respuesta<AdministrarMedioPagoDTO> respuestaAdmMedioPago = billeteraServiceHelper
                        .prcErrorAdmMedioPago(status, sesionBilletera);
                if (EstadoRespuesta.ERROR.equals(respuestaAdmMedioPago.getEstadoRespuesta())) {
                    return respuestaAdmMedioPago;
                }
            }
            if (modificacion) {
                actualizarNovedadMediosPago(medioPagoResponse);
            }
            return crearAdministrarMedioPagoDTO(medioPagoResponse);
        } catch (Exception e) {
            LOGGER.error("administrarMedioPago - Error al invocar administrarMedioPago: ", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }

    /**
     * Ajusta las marcas de novedad para medios de pago vencidos (coloca M y
     * nueva fecha de vencimiento tomada del CBU) y para medios de pago no
     * disponibles por el cliente (coloca B).
     */
    private void ajustaMediosPago() {
        // Fija marca de modificacion a tarjetas vencidas (presentes en Prisma y
        // perfil BSR)
        List<MedioDePagoBilleteraDTO> ctasPrCs = sesionBilletera.getList(BilleteraConstants.LST_PR_CS);
        List<MedioDePagoBilleteraDTO> mediosPago = billeteraServiceHelper.getTarjetasVinculadasBilletera(ctasPrCs);
        Iterator<MedioDePagoBilleteraDTO> itMediosPago = mediosPago.iterator();
        while (itMediosPago.hasNext()) {
            MedioDePagoBilleteraDTO mp = itMediosPago.next();
            if (BilleteraConstants.MP_VENCIDO.equals(mp.getEstadoMedioPago())) {
                Cuenta cuenta = mp.getCtaAsociada();
                mp.setTipoNovedad(BilleteraConstants.TIPO_NOVEDAD_MOD);
                try {
                    mp.setFechaVencimiento(BilleteraUtils.getFechaVtoTrj(cuenta.getCbu(), false));
                } catch (ParseException e) {
                    LOGGER.error("Error al obtener la fecha de vencimiento para el medio de pago", e);
                }
            }
        }
        // Desvincula tarjetas en Prisma (informadas como del Banco) que no
        // estan presentes en el perfil de usuario BSR
        List<MedioDePagoBilleteraDTO> ctasPrNoCs = sesionBilletera.getList(BilleteraConstants.LST_PR_NOCS);
        Iterator<MedioDePagoBilleteraDTO> itCuentas = ctasPrNoCs.iterator();
        while (itCuentas.hasNext()) {
            MedioDePagoBilleteraDTO mp = itCuentas.next();
            mp.setTipoNovedad(BilleteraConstants.TIPO_NOVEDAD_BAJA);
        }
    }

    /**
     * Ajusta mp.
     *
     * @param ctasPrNoCs
     *            the ctas pr no cs
     * @param nroT
     *            the nro T
     * @param mp
     *            the mp
     */
    private void ajustaMp(List<MedioDePagoBilleteraDTO> ctasPrNoCs, String nroT, MedioDePagoBilleteraDTO mp) {
        MedioDePagoBilleteraDTO mpDTO = mp;
        if (mp != null) {
            mpDTO.setEstadoMedioPago("");
            mpDTO.setTipoNovedad("");
        } else {
            mpDTO = BilleteraUtils.getMedioPago(ctasPrNoCs, nroT);
            if (mpDTO != null) {
                mpDTO.setTipoNovedad("");
            }
        }
    }

    /**
     * Consultar cuenta.
     *
     * @return the respuesta
     */
    private Respuesta<ConsultaCuentaDTO> consultarCuenta() {
        // Invoca a CNSPERSFIS
        DatosSolicitanteEntity datosSolicitante;
        try {
            datosSolicitante = obtenerDatosSolicitante();
            sesionBilletera.setDatosSolicitante(datosSolicitante);
        } catch (Exception e) {
            LOGGER.error("Error al consultar datos del solicitante.", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
        ConsultarCuenta parameters = billeteraServiceHelper.fillParamsConsultarCuenta(datosSolicitante.getSexo(),
                sesionCliente.getCliente());
        ConsultaCuentaResult consultaCuentaResult;
        try {
            consultaCuentaResult = billeteraDAO.consultarCuenta(parameters);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar cuenta Billetera.", e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
        if (BilleteraConstants.STATUS_00000.equals(consultaCuentaResult.getStatus().getValue())) {
            try {
                ConsultaCuentaDTO consultaCuentaDTO = crearConsultaCuentaDTO(consultaCuentaResult);
                sesionBilletera.setConsultaCuentaDto(consultaCuentaDTO);
                return respuestaFactory.crearRespuestaOk(consultaCuentaDTO);
            } catch (IllegalAccessException e) {
                LOGGER.error("Error al crear DTO.", e);
            } catch (InvocationTargetException e) {
                LOGGER.error("Error al crear DTO.", e);
            }
        } else {
            LOGGER.error("Error al consultar cuenta Billetera. Status = {}", consultaCuentaResult.getStatus());
        }

        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
    }

    /**
     * Crear administrar medio pago DTO.
     *
     * @param medioPagoResponse
     *            the medio pago response
     * @return the respuesta
     */
    private Respuesta<AdministrarMedioPagoDTO> crearAdministrarMedioPagoDTO(List<MedioPagoResponse> medioPagoResponse) {
        AdministrarMedioPagoDTO administrarMedioPagoDTO = new AdministrarMedioPagoDTO();
        List<AdministrarMedioPagoItem> items = new ArrayList<AdministrarMedioPagoItem>();

        for (MedioPagoResponse medioPago : medioPagoResponse) {
            AdministrarMedioPagoItem item = administrarMedioPagoDTO.new AdministrarMedioPagoItem();
            item.setStatus(medioPago.getStatus());
            item.setNumeroTarjeta(medioPago.getNumeroTarjeta());
            items.add(item);
        }
        administrarMedioPagoDTO.setItems(items);

        return respuestaFactory.crearRespuestaOk(administrarMedioPagoDTO);
    }

    /**
     * Creates the consulta cuenta DTO.
     *
     * @param consultaCuentResult
     *            the cuenta billetera
     * @return the consulta cuenta DTO
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws InvocationTargetException
     *             the invocation target exception
     */
    private ConsultaCuentaDTO crearConsultaCuentaDTO(ConsultaCuentaResult consultaCuentResult)
            throws IllegalAccessException, InvocationTargetException {
        ConsultaCuentaDTO consultaCuentaDTO = new ConsultaCuentaDTO();

        if (!BilleteraConstants.EXISTE_USUARIO_NO.equals(consultaCuentResult.getExisteUsuario().getValue())
                && !BilleteraConstants.EXISTE_USUARIO_BLOQ.equals(consultaCuentResult.getExisteUsuario().getValue())) {
            consultaCuentaDTO.setCuentas(getCuentasBilleteraDTO(consultaCuentResult));
        }
        consultaCuentaDTO.setExisteUsuario(consultaCuentResult.getExisteUsuario().getValue());
        consultaCuentaDTO.setStatus(consultaCuentResult.getStatus().getValue());
        return consultaCuentaDTO;
    }

    /**
     * Crear medio pago DTO.
     *
     * @param cuentaTrj
     *            the cuenta trj
     * @param vinculado
     *            the vinculado
     * @param favorito
     *            the favorito
     * @return the medio de pago billetera DTO
     * @throws ParseException
     *             the parse exception
     */
    private MedioDePagoBilleteraDTO crearMedioPagoDTO(Cuenta cuentaTrj, boolean vinculado, boolean favorito)
            throws ParseException {
        MedioDePagoBilleteraDTO medioPago = new MedioDePagoBilleteraDTO();
        medioPago.setActiva(BilleteraConstants.N);
        medioPago.setCtaAsociada(cuentaTrj);
        medioPago.setEstadoMedioPago("");
        medioPago.setEstadoVinc(vinculado ? BilleteraConstants.S : BilleteraConstants.N);
        medioPago.setFavorito(favorito ? BilleteraConstants.S : BilleteraConstants.N);
        medioPago.setFechaVencimiento(BilleteraUtils.getFechaVtoTrjDisplay(cuentaTrj.getCbu()));
        medioPago.setFlagMedioPagoBanco("");
        medioPago.setIdMedioDePago("");
        medioPago.setNumeroTarjeta(
                BilleteraUtils.getNroTrjFmt(cuentaTrj.getNroTarjetaCredito(), cuentaTrj.getTipoCuenta()));
        medioPago.setTipoMedioPago("");
        medioPago.setTipoNovedad("");
        medioPago.setValidaBanco("");
        medioPago.setDisableControls(false);
        medioPago.setEstadoEsp("");
        medioPago.setMarca(BilleteraUtils.getMarcaTrj(cuentaTrj));
        return medioPago;
    }

    /**
     * Fill params vinculados.
     *
     * @param vincular
     *            the vincular
     * @param principal
     *            the principal
     * @param params
     *            the params
     * @throws ParseException
     *             the parse exception
     */
    private void fillParamsVinculados(String[] vincular, String principal, Map<String, List<String>> params)
            throws ParseException {
        for (String idx : vincular) {
            if (!billeteraServiceHelper.isMpVinculado(idx, sesionBilletera.getList(BilleteraConstants.LST_PR_CS))) {
                billeteraServiceHelper.fillParamsAdministrarMedioPago(params, sesionCliente.getCliente(), idx,
                        principal, BilleteraConstants.TIPO_NOVEDAD_ALTA);
            }
        }
    }

    /**
     * Gets the cuentas billetera DTO.
     *
     * @param consultaCuentResult
     *            the consulta cuent result
     * @return the cuentas billetera DTO
     */
    private List<CuentaBilleteraDTO> getCuentasBilleteraDTO(ConsultaCuentaResult consultaCuentResult) {
        Iterator<ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.Cuenta> itCuentas = consultaCuentResult
                .getCuentas().iterator();
        List<CuentaBilleteraDTO> cuentasBilleteraDTO = new ArrayList<CuentaBilleteraDTO>();
        while (itCuentas.hasNext()) {
            ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.Cuenta cuenta = itCuentas.next();
            CuentaBilleteraDTO cuentaBilleteraDTO = new CuentaBilleteraDTO();
            cuentaBilleteraDTO.setApellido(cuenta.getApellido().getValue());
            cuentaBilleteraDTO.setCalle(cuenta.getCalle().getValue());
            cuentaBilleteraDTO.setCodPostal(cuenta.getCodPostal().getValue());
            cuentaBilleteraDTO.setCompaniaCelular(cuenta.getCompaniaCelular().getValue());
            cuentaBilleteraDTO.setDepartamento(cuenta.getDepartamento().getValue());
            cuentaBilleteraDTO.setEsPrimeraVez(cuenta.getEsPrimeraVez().getValue());
            cuentaBilleteraDTO.setEstadoMail(cuenta.getEstadoMail().getValue());
            cuentaBilleteraDTO.setFechaNacimiento(cuenta.getFechaNacimiento().getValue());
            cuentaBilleteraDTO.setIdCuenta(cuenta.getIdCuenta().getValue());
            cuentaBilleteraDTO.setLocalidad(cuenta.getLocalidad().getValue());
            cuentaBilleteraDTO.setMail(cuenta.getMail().getValue());
            cuentaBilleteraDTO.setMarcaCBU(cuenta.getMarcaCBU().getValue());

            cuentaBilleteraDTO.setMediosDePago(getMediosDePago(cuenta));

            if (cuenta.getMonedaCuenta() != null) {
                cuentaBilleteraDTO.setMonedaCuenta(cuenta.getMonedaCuenta().getValue());
            }
            cuentaBilleteraDTO.setNacionalidad(cuenta.getNacionalidad().getValue());
            cuentaBilleteraDTO.setNombre(cuenta.getNombre().getValue());
            cuentaBilleteraDTO.setNumeroCalle(cuenta.getNumeroCalle().getValue());
            if (cuenta.getNumeroCBU() != null) {
                cuentaBilleteraDTO.setNumeroCBU(cuenta.getNumeroCBU().getValue());
            }
            cuentaBilleteraDTO.setNumeroCelular(cuenta.getNumeroCelular().getValue());
            if (cuenta.getNumeroCuenta() != null) {
                cuentaBilleteraDTO.setNumeroCuenta(cuenta.getNumeroCuenta().getValue());
            }
            cuentaBilleteraDTO.setPiso(cuenta.getPiso().getValue());
            cuentaBilleteraDTO.setPreguntaSeguridad(cuenta.getPreguntaSeguridad().getValue());
            cuentaBilleteraDTO.setProvincia(cuenta.getProvincia().getValue());
            cuentaBilleteraDTO.setTelefonoFijo(cuenta.getTelefonoFijo().getValue());
            if (cuenta.getTipoAcreditacion() != null) {
                cuentaBilleteraDTO.setTipoAcreditacion(cuenta.getTipoAcreditacion().getValue());
            }
            if (cuenta.getTipoCuenta() != null) {
                cuentaBilleteraDTO.setTipoCuenta(cuenta.getTipoCuenta().getValue());
            }
            cuentaBilleteraDTO.setTipoCuentaTodoPago(cuenta.getTipoCuentaTodoPago().getValue());
            cuentasBilleteraDTO.add(cuentaBilleteraDTO);
        }
        return cuentasBilleteraDTO;
    }

    /**
     * Gets the emails registrados.
     *
     * @param cuentasBilletera
     *            the cuentas
     * @return the emails registrados
     */
    private List<String> getEmailsRegistrados(List<CuentaBilleteraDTO> cuentasBilletera) {
        List<String> emailsRegistrados = new ArrayList<String>();
        for (CuentaBilleteraDTO cuentaBilletera : cuentasBilletera) {
            emailsRegistrados.add(cuentaBilletera.getMail());
        }
        return emailsRegistrados;
    }

    /**
     * Gets the marcar adhesion.
     *
     * @param impactCta
     *            the impact cta
     * @param impactTrjs
     *            the impact trjs
     * @param impactCtaOk
     *            the impact cta ok
     * @param impactTrjOk
     *            the impact trj ok
     * @return the marcar adhesion
     */
    private boolean getMarcarAdhesion(boolean impactCta, boolean impactTrjs, boolean impactCtaOk, boolean impactTrjOk) {
        if (impactCtaOk && impactTrjOk) {
            return true;
        }
        if (impactCtaOk && !impactTrjs) {
            return true;
        }
        if (!impactCta && impactTrjOk) {
            return true;
        }
        return false;
    }

    /**
     * Gets the medios de pago.
     *
     * @param cuenta
     *            the cuenta
     * @return the medios de pago
     */
    private List<MedioDePagoBilleteraDTO> getMediosDePago(
            ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.Cuenta cuenta) {
        List<MedioDePagoBilleteraDTO> mediosDePago = new ArrayList<MedioDePagoBilleteraDTO>();
        Iterator<MedioDePago> itMediosDePago = cuenta.getMediosDePago().iterator();
        while (itMediosDePago.hasNext()) {
            MedioDePago medioDePago = itMediosDePago.next();
            MedioDePagoBilleteraDTO medioDePagoDTO = new MedioDePagoBilleteraDTO();
            medioDePagoDTO.setEstadoMedioPago(medioDePago.getEstadoMedioPago().getValue());
            medioDePagoDTO.setFavorito(medioDePago.getFavorito().getValue());
            medioDePagoDTO.setFechaVencimiento(medioDePago.getFechaVencimiento().getValue());
            medioDePagoDTO.setFlagMedioPagoBanco(medioDePago.getFlagMedioPagoBanco().getValue());
            medioDePagoDTO.setIdMedioDePago(medioDePago.getIdMedioDePago().getValue());
            medioDePagoDTO.setNumeroTarjeta(getNumTrj(medioDePago));
            medioDePagoDTO.setTipoMedioPago(medioDePago.getTipoMedioPago().getValue());
            medioDePagoDTO.setValidaBanco(medioDePago.getValidaBanco().getValue());
            medioDePagoDTO.setDisableControls(false);
            medioDePagoDTO.setEstadoEsp("");
            medioDePagoDTO.setEstadoVinc(BilleteraConstants.S);
            medioDePagoDTO.setMarca("");
            mediosDePago.add(medioDePagoDTO);
        }
        return mediosDePago;
    }

    /**
     * Gets the medios pago.
     *
     * @param params
     *            the params
     * @return the medios pago
     */
    private MediosPago getMediosPago(Map<String, List<String>> params) {
        List<String> nroTrjs = params.get(BilleteraConstants.PARAM_NRO_TARJETA);
        List<String> idMps = params.get(BilleteraConstants.PARAM_ID_MEDIOPAGO);
        List<String> favs = params.get(BilleteraConstants.PARAM_FAVORITO);
        List<String> fecVencs = params.get(BilleteraConstants.PARAM_FECHA_VENC);
        List<String> tpoNovs = params.get(BilleteraConstants.PARAM_TIPO_NOVEDAD);
        MediosPago mediosPago = new MediosPago();
        if (idMps != null) {
            List<MedioPago> medioPago = mediosPago.getMedioPago();
            for (int i = 0; i < idMps.size(); i++) {
                MedioPago mp = new MedioPago();
                mp.setNumeroTarjeta(nroTrjs.get(i));
                mp.setIdMedioDePago(Integer.parseInt(idMps.get(i)));
                mp.setFavorito(favs.get(i));
                mp.setFechaVencimiento(Integer.parseInt(fecVencs.get(i)));
                mp.setTipoNovedad(tpoNovs.get(i));
                medioPago.add(mp);
            }
        }
        return mediosPago;
    }

    /**
     * Gets the medios pago consulta.
     *
     * @return the medios pago consulta
     * @throws ParseException
     *             the parse exception
     */
    private List<MedioDePagoBilleteraDTO> getMediosPagoConsulta() throws ParseException {
        List<MedioDePagoBilleteraDTO> cuentasConsulta = new ArrayList<MedioDePagoBilleteraDTO>();
        cuentasConsulta.addAll(getMediosPagoVinculados());
        cuentasConsulta.addAll(getMediosPagoDisponibleVincular());
        return cuentasConsulta;
    }

    /**
     * Gets the medios pago disponible vincular.
     *
     * @return the medios pago disponible vincular
     * @throws ParseException
     *             the parse exception
     */
    private List<MedioDePagoBilleteraDTO> getMediosPagoDisponibleVincular() throws ParseException {
        // Tarjetas que pueden vincularse
        List<MedioDePagoBilleteraDTO> mediosPagoDisponibleVincular = new ArrayList<MedioDePagoBilleteraDTO>();
        List<MedioDePagoBilleteraDTO> ctasNoPrCs = sesionBilletera.getList(BilleteraConstants.LST_NOPR_CS);
        Iterator<MedioDePagoBilleteraDTO> itMediosPagoDisponibles = ctasNoPrCs.iterator();
        while (itMediosPagoDisponibles.hasNext()) {
            MedioDePagoBilleteraDTO mp = itMediosPagoDisponibles.next();
            Cuenta cuenta = mp.getCtaAsociada();
            boolean estadoEsp = BilleteraConstants.ESTADO_PROBLEMA.equals(cuenta.getEstadoTarjetaCredito())
                    || BilleteraConstants.ESTADO_CERRADA.equals(cuenta.getEstadoTarjetaCredito());
            if (!BilleteraUtils.trjMostrar(cuenta) || estadoEsp) {
                continue;
            }
            MedioDePagoBilleteraDTO medioPagoDisponibleVincular = crearMedioPagoDTO(cuenta, false, false);
            medioPagoDisponibleVincular.setDisableControls(false);
            medioPagoDisponibleVincular.setEstadoEsp("");
            medioPagoDisponibleVincular
                    .setFechaVencimiento(StringUtils.capitalize(BilleteraUtils.getFechaVtoTrjDisplay(cuenta.getCbu())));
            mediosPagoDisponibleVincular.add(medioPagoDisponibleVincular);
        }
        return mediosPagoDisponibleVincular;
    }

    /**
     * Gets the medios pago otros bancos.
     *
     * @return the medios pago otros bancos
     */
    private List<MedioDePagoBilleteraDTO> getMediosPagoOtrosBancos() {
        // Tarjetas de Otros Bancos
        List<MedioDePagoBilleteraDTO> mediosPagoOtrosBancos = new ArrayList<MedioDePagoBilleteraDTO>();
        List<MedioDePagoBilleteraDTO> ctasOb = sesionBilletera.getList(BilleteraConstants.LST_PR_OB);
        if (!ctasOb.isEmpty()) {
            Iterator<MedioDePagoBilleteraDTO> itMediosPagoOtrosBancos = ctasOb.iterator();
            while (itMediosPagoOtrosBancos.hasNext()) {
                MedioDePagoBilleteraDTO mp = itMediosPagoOtrosBancos.next();
                MedioDePagoBilleteraDTO medioPagoOtroBanco = new MedioDePagoBilleteraDTO();
                BeanUtils.copyProperties(mp, medioPagoOtroBanco);
                medioPagoOtroBanco.setNumeroTarjeta("XXXXXX" + mp.getNumeroTarjeta());
                medioPagoOtroBanco
                        .setMarca(BilleteraServiceHelper.getMarcaTrjWs(mp.getTipoMedioPago(), mp.getIdMedioDePago()));
                medioPagoOtroBanco.setDisableControls(true);
                medioPagoOtroBanco.setEstadoEsp("");
                mediosPagoOtrosBancos.add(medioPagoOtroBanco);
            }
        }
        return mediosPagoOtrosBancos;
    }

    /**
     * Gets the medios pago vinculados.
     *
     * @return the medios pago vinculados
     * @throws ParseException
     *             the parse exception
     */
    private List<MedioDePagoBilleteraDTO> getMediosPagoVinculados() throws ParseException {
        // Tarjetas ya vinculadas
        List<MedioDePagoBilleteraDTO> mediosPagoVinculados = new ArrayList<MedioDePagoBilleteraDTO>();
        List<MedioDePagoBilleteraDTO> ctasPrCs = sesionBilletera.getList(BilleteraConstants.LST_PR_CS);
        Iterator<MedioDePagoBilleteraDTO> itMediosPagoVinculados = billeteraServiceHelper
                .getTarjetasVinculadasBilletera(ctasPrCs).iterator();
        while (itMediosPagoVinculados.hasNext()) {
            MedioDePagoBilleteraDTO mp = itMediosPagoVinculados.next();
            Cuenta cuenta = mp.getCtaAsociada();
            if (!BilleteraUtils.trjMostrar(cuenta)) {
                continue;
            }
            boolean estadoEsp = BilleteraConstants.ESTADO_PROBLEMA.equals(cuenta.getEstadoTarjetaCredito())
                    || BilleteraConstants.ESTADO_CERRADA.equals(cuenta.getEstadoTarjetaCredito());
            boolean isFechaVencMpMenorHoy = BilleteraUtils.isFechaVencMpMenorHoy(mp);
            MedioDePagoBilleteraDTO medioPagoVinculado = crearMedioPagoDTO(cuenta, true,
                    BilleteraConstants.S.equals(mp.getFavorito()));
            if ((BilleteraConstants.ESTADO_NORENOVAR.equals(cuenta.getEstadoTarjetaCredito()) && isFechaVencMpMenorHoy)
                    || estadoEsp) {
                medioPagoVinculado.setEstadoEsp(estadoEsp ? ESTADOESP_NO_ACTIVA : ESTADOESP_V);
                medioPagoVinculado.setDisableControls(true);
            }
            String fechaVencimiento;
            if (BilleteraConstants.MP_VENCIDO.equals(mp.getEstadoMedioPago())) {
                fechaVencimiento = StringUtils.capitalize(BilleteraUtils.getFechaVtoTrjDisplay(cuenta.getCbu()));
            } else {
                fechaVencimiento = StringUtils.capitalize(BilleteraUtils.getVtoTrjDisplay(mp.getFechaVencimiento()));
            }
            medioPagoVinculado.setFechaVencimiento(fechaVencimiento);
            medioPagoVinculado
                    .setMarca(BilleteraServiceHelper.getMarcaTrjWs(mp.getTipoMedioPago(), mp.getIdMedioDePago()));
            medioPagoVinculado.setNumeroTarjeta(
                    BilleteraServiceHelper.getNroTrjFmtWs(mp.getNumeroTarjeta(), mp.getIdMedioDePago()));
            mediosPagoVinculados.add(medioPagoVinculado);
        }
        return mediosPagoVinculados;
    }

    /**
     * Gets the num trj.
     *
     * @param medioDePago
     *            the medio de pago
     * @return the num trj
     */
    private String getNumTrj(MedioDePago medioDePago) {
        String numTrj = medioDePago.getNumeroTarjeta().getValue();
        if (BilleteraConstants.IDMEDIOPAGO_AMEX.equals(medioDePago.getIdMedioDePago().getValue())
                && numTrj.length() > BilleteraConstants.LEN_AMEX) {
            numTrj = numTrj.substring(0, BilleteraConstants.LEN_AMEX);
        }
        return numTrj;
    }

    /**
     * Gets the params.
     *
     * @param modificacion
     *            the modificacion
     * @param vincular
     *            the vincular
     * @param principal
     *            the principal
     * @return the params
     * @throws ParseException
     *             the parse exception
     */
    private Map<String, List<String>> getParams(boolean modificacion, String[] vincular, String principal)
            throws ParseException {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        if (modificacion) {
            if (vincular != null) {
                fillParamsVinculados(vincular, principal, params);
            }
            // Acciones necesarias al modificar la billetera
            billeteraServiceHelper.addParamsMod(params, principal, vincular,
                    sesionBilletera.getList(BilleteraConstants.LST_PR_CS)); // carga
                                                                            // en
                                                                            // params
                                                                            // los
                                                                            // mps
                                                                            // ajustados
                                                                            // por
                                                                            // estar
                                                                            // vencidos,
                                                                            // y
                                                                            // los
                                                                            // que
                                                                            // cambiaron
                                                                            // su
                                                                            // estado
                                                                            // de
                                                                            // favorito
            billeteraServiceHelper.addParamsBaja(params, sesionBilletera.getList(BilleteraConstants.LST_PR_NOCS)); // carga
                                                                                                                   // en
                                                                                                                   // params
                                                                                                                   // los
                                                                                                                   // mps
                                                                                                                   // ajustados
                                                                                                                   // con
                                                                                                                   // novedad
                                                                                                                   // B
                                                                                                                   // porque
                                                                                                                   // no
                                                                                                                   // estan
                                                                                                                   // mas
                                                                                                                   // en
                                                                                                                   // RCD
            billeteraServiceHelper.addParamsBajaPorDesvincular(params, vincular,
                    sesionBilletera.getList(BilleteraConstants.LST_PR_CS)); // carga
                                                                            // en
                                                                            // params
                                                                            // los
                                                                            // mps
                                                                            // desvinculados
        } else if (vincular != null) {
            for (String idx : vincular) {
                billeteraServiceHelper.fillParamsAdministrarMedioPago(params, sesionCliente.getCliente(), idx,
                        principal, BilleteraConstants.TIPO_NOVEDAD_ALTA);
            }
        }
        return params;
    }

    /**
     * Gets the pregunta seguridad.
     *
     * @param prgSegId
     *            the prg seg id
     * @return the pregunta seguridad
     */
    private String getPreguntaSeguridad(String prgSegId) {
        List<Opcion> preguntasSeguridad = datosSelectoresService.obtenerPreguntasSeguridad();
        for (Opcion opc : preguntasSeguridad) {
            if (opc.getId().equals(prgSegId)) {
                return opc.getOpcion();
            }
        }
        return "";
    }

    /**
     * Gets the respuesta consultar cuenta.
     *
     * @return the respuesta consultar cuenta
     */
    private Respuesta<ConsultaCuentaDTO> getRespuestaConsultarCuenta() {
        Respuesta<ConsultaCuentaDTO> respuestaConsultarCuenta;
        if (sesionBilletera.getConsultaCuentaDto() == null) {
            respuestaConsultarCuenta = consultarCuenta();
        } else {
            respuestaConsultarCuenta = respuestaFactory.crearRespuestaOk(sesionBilletera.getConsultaCuentaDto());
        }
        return respuestaConsultarCuenta;
    }

    /**
     * Gets the tarjetas cliente.
     *
     * @return the tarjetas cliente
     * @throws ParseException
     *             the parse exception
     */
    private List<MedioDePagoBilleteraDTO> getTarjetasCliente() throws ParseException {
        List<Cuenta> cuentasTrj = BilleteraUtils.getCtasTrj(sesionCliente.getCliente(),
                Arrays.asList(tiposTrj.split("\\|")));
        List<MedioDePagoBilleteraDTO> mediosPago = new ArrayList<MedioDePagoBilleteraDTO>();
        for (Cuenta cuentaTrj : cuentasTrj) {
            boolean estadoEsp = BilleteraConstants.ESTADO_PROBLEMA.equals(cuentaTrj.getEstadoTarjetaCredito())
                    || BilleteraConstants.ESTADO_CERRADA.equals(cuentaTrj.getEstadoTarjetaCredito());
            if (!BilleteraUtils.trjMostrar(cuentaTrj) || estadoEsp) {
                continue;
            }
            mediosPago.add(crearMedioPagoDTO(cuentaTrj, false, false));
        }
        return mediosPago;
    }

    ///////////////////////////////////
    /**
     * misTarjetasCheck.
     *
     * @param indexCtaTp
     *            the index cta tp
     * @param cliente
     *            the cliente
     * @param idCuenta
     *            the id cuenta
     * @param consultaCuentaDTO
     *            the consulta cuenta DTO
     */
    private void misTarjetasCheck(int indexCtaTp, Cliente cliente, String idCuenta,
            ConsultaCuentaDTO consultaCuentaDTO) {

        sesionBilletera.setCtasMap(BilleteraUtils.getCtasPrCs(cliente, tiposTrj, idCuenta, consultaCuentaDTO));

        if (indexCtaTp >= 0) {
            // Verifica medios de pago de la cuenta que contengan
            // inconsistencias con OBP
            // y actualiza su Tipo de Novedad ('M' en lista PrCs y 'B' en
            // PrNoCs)
            // según la acción a tomar en cada uno
            ajustaMediosPago();

            try {
                // Actualiza los medios de pago de la cuenta invocando al
                // servicio administrarMedioPago
                actualizarMediosPago(idCuenta);
            } catch (DAOException e) {
                LOGGER.error("Error al actualizar medios de pago", e);
            }
        }
    }

    /**
     * Obtener datos solicitante.
     *
     * @return the datos solicitante entity
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    private DatosSolicitanteEntity obtenerDatosSolicitante()
            throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException {
        Cliente cliente = sesionCliente.getCliente();
        DatosSolicitanteCriterioEntity datosSolicitanteCriterio = new DatosSolicitanteCriterioEntity();
        datosSolicitanteCriterio.setDocTipo(cliente.getTipoDocumento());
        datosSolicitanteCriterio.setDoc(cliente.getDni());
        return datosSolicitanteDAO.getDatosDelSolicitante(datosSolicitanteCriterio, cliente);
    }

    /**
     * Obtener respuesta validar usuario.
     *
     * @param respuestaConsultarCuenta
     *            the respuesta consultar cuenta
     * @param idxCtaTp
     *            the idx cta tp
     * @param ctas
     *            the ctas
     * @param tarjetasCliente
     *            the tarjetas cliente
     * @return the respuesta
     */
    private Respuesta<ValidarUsuarioBilleteraDTO> obtenerRespuestaValidarUsuario(
            Respuesta<ConsultaCuentaDTO> respuestaConsultarCuenta, int idxCtaTp, List<Cuenta> ctas,
            List<MedioDePagoBilleteraDTO> tarjetasCliente) {
        if (EstadoRespuesta.OK.equals(respuestaConsultarCuenta.getEstadoRespuesta())) {
            ValidarUsuarioBilleteraDTO validarUsuarioDTO = new ValidarUsuarioBilleteraDTO();
            ConsultaCuentaDTO consultaCuentaDTO = respuestaConsultarCuenta.getRespuesta();
            validarUsuarioDTO.setIdxCtaTp(String.valueOf(idxCtaTp));
            // Carga cuentas de acreditacion
            validarUsuarioDTO.setCuentasAcreditacion(ctas);
            if (BilleteraConstants.EXISTE_USUARIO_NO.equals(consultaCuentaDTO.getExisteUsuario())) {
                // Usuario no adherido
                // Carga tarjetas a presentar
                return prcUsuarioNoAdherido(tarjetasCliente, validarUsuarioDTO);
            } else if (BilleteraConstants.EXISTE_USUARIO_SI.equals(consultaCuentaDTO.getExisteUsuario())) {
                // Usuario registrado
                return prcUsuarioRegistrado(idxCtaTp, tarjetasCliente, validarUsuarioDTO, consultaCuentaDTO);
            } else if (BilleteraConstants.EXISTE_USUARIO_BLOQ.equals(consultaCuentaDTO.getExisteUsuario())) {
                // Usuario bloqueado
                LOGGER.error("Error al validar el usuario: usuario bloqueado");
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_BLOQUEADO,
                        CodigoMensajeConstantes.CODIGO_ERROR_BILLETERA_USUARIO_BLOQUEADO);
            } else {
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
            }
        } else {
            LOGGER.error("Error al consultar la cuenta Billetera del usuario.");
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }

    /**
     * Prc usuario no adherido.
     *
     * @param tarjetasCliente
     *            the tarjetas cliente
     * @param validarUsuarioDTO
     *            the validar usuario DTO
     * @return the respuesta
     */
    private Respuesta<ValidarUsuarioBilleteraDTO> prcUsuarioNoAdherido(List<MedioDePagoBilleteraDTO> tarjetasCliente,
            ValidarUsuarioBilleteraDTO validarUsuarioDTO) {
        validarUsuarioDTO.setMediosPago(tarjetasCliente);
        validarUsuarioDTO.setMensaje(mensajeDAO
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_CUENTA_ACREDITACION).getMensaje());
        validarUsuarioDTO.setViewName(BilleteraConstants.VIEW_ADH);
        return respuestaFactory.crearRespuestaOk(validarUsuarioDTO);
    }

    /**
     * Prc usuario registrado.
     *
     * @param idxCtaTp
     *            the idx cta tp
     * @param tarjetasCliente
     *            the tarjetas cliente
     * @param validarUsuarioDTO
     *            the validar usuario DTO
     * @param consultaCuentaDTO
     *            the consulta cuenta DTO
     * @return the respuesta
     */
    private Respuesta<ValidarUsuarioBilleteraDTO> prcUsuarioRegistrado(int idxCtaTp,
            List<MedioDePagoBilleteraDTO> tarjetasCliente, ValidarUsuarioBilleteraDTO validarUsuarioDTO,
            ConsultaCuentaDTO consultaCuentaDTO) {
        CuentaBilleteraDTO cuentaBilleteraDTO = consultaCuentaDTO.getCuentas().get(idxCtaTp);
        validarUsuarioDTO.setEstadoMail(cuentaBilleteraDTO.getEstadoMail());
        validarUsuarioDTO.setMailRegistrado(cuentaBilleteraDTO.getMail());
        validarUsuarioDTO.setMediosPago(tarjetasCliente);
        validarUsuarioDTO.setEmailsRegistrados(getEmailsRegistrados(consultaCuentaDTO.getCuentas()));
        if (BilleteraConstants.S.equals(cuentaBilleteraDTO.getEsPrimeraVez())) {
            // Primer ingreso: presenta pantalla de login
            validarUsuarioDTO.setViewName(BilleteraConstants.VIEW_CLV);
            String prgSegId = cuentaBilleteraDTO.getPreguntaSeguridad();
            validarUsuarioDTO.setPreguntaSeguridad(getPreguntaSeguridad(prgSegId));
            return respuestaFactory.crearRespuestaOk(validarUsuarioDTO);
        } else if (BilleteraConstants.ESTADOMAIL_PENDIENTE.equals(cuentaBilleteraDTO.getEstadoMail())) {
            // Presenta pantalla de confirmacion de mail
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MAIL_NO_CONFIRMADO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        } else if (BilleteraConstants.ESTADOMAIL_CONFIRMADO.equals(cuentaBilleteraDTO.getEstadoMail())) {
            // Presenta pantalla de consulta de tarjetas
            validarUsuarioDTO.setViewName(BilleteraConstants.VIEW_CNS);
            validarUsuarioDTO.setTelefono(cuentaBilleteraDTO.getNumeroCelular());
            String empCel = BilleteraServiceHelper.getEmpresaCel(cuentaBilleteraDTO.getCompaniaCelular());
            validarUsuarioDTO.setEmpresa(TipoCompaniaEnum.fromCodigo(empCel).getDescripcion());

            // Ajusta los medios de pago y carga listas de cuentas en session
            String idCuenta = consultaCuentaDTO.getCuentas().get(idxCtaTp).getIdCuenta();
            misTarjetasCheck(idxCtaTp, sesionCliente.getCliente(), idCuenta, consultaCuentaDTO);

            // Carga tarjetas a presentar
            try {
                validarUsuarioDTO.setMediosPago(getMediosPagoConsulta());
                validarUsuarioDTO.setMediosPagoOtrosBancos(getMediosPagoOtrosBancos());
            } catch (ParseException e) {
                LOGGER.error("Error al obtener la tarjetas a presentar", e);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
            }
            validarUsuarioDTO.setMensaje(
                    mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_CUENTA_ACREDITACION)
                            .getMensaje());

            return respuestaFactory.crearRespuestaOk(validarUsuarioDTO);
        } else {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }
    }

    /**
     * Crear billetera DTO.
     *
     * @param inDTO
     *            the dto
     * @param respuestaAdmCbu
     *            the respuesta adm cbu
     * @param respuestaAdmMedioPago
     *            the respuesta adm medio pago
     * @param impactCta
     *            the impact cta
     * @param impactTrjs
     *            the impact trjs
     * @return the billetera DTO
     */
    protected BilleteraDTO crearBilleteraDTO(BilleteraInDTO inDTO, Respuesta<AdministrarCBUDTO> respuestaAdmCbu,
            Respuesta<AdministrarMedioPagoDTO> respuestaAdmMedioPago, boolean impactCta, boolean impactTrjs) {
        Date date = new Date();
        BilleteraDTO billeteraDTO = new BilleteraDTO();
        billeteraDTO.setFecha(new SimpleDateFormat("dd/MM/yyyy").format(date));
        billeteraDTO.setHora(new SimpleDateFormat("HH:mm").format(date));
        billeteraDTO.setNroComprobante(new SimpleDateFormat("yyMMddHHmmss").format(date));
        billeteraDTO.setCuentaAcreditacion(inDTO.getCuentaAcreditacion());
        if (respuestaAdmCbu != null) {
            int ctaIdx = inDTO.getCuentaAcreditacion().intValue();
            Cuenta cuentaAcreditacionSeleccionada = getCuentasAcreditacion().get(ctaIdx);
            String desc = BilleteraUtils.getDescCta(cuentaAcreditacionSeleccionada.getTipoCuenta());
            String nroCtaCanonico = ISBANStringUtils
                    .formatearNroCuentaProductoConSucursal(cuentaAcreditacionSeleccionada);
            String ctaDesc = desc + " " + nroCtaCanonico;
            if (EstadoRespuesta.ERROR.equals(respuestaAdmCbu.getEstadoRespuesta())) {
                billeteraDTO.setCuentaAcreditacionError(ctaDesc);
            } else {
                billeteraDTO.setCuentaAcreditacionOk(ctaDesc);
            }
        } else {
            billeteraDTO.setCuentaAcreditacion(null);
        }

        boolean okTrj = false;
        if (respuestaAdmMedioPago != null) {
            List<MedioDePagoBilleteraDTO> mediosDePagoOk = new ArrayList<MedioDePagoBilleteraDTO>();
            List<String> mediosDePagoError = new ArrayList<String>();
            for (AdministrarMedioPagoItem item : respuestaAdmMedioPago.getRespuesta().getItems()) {
                String status = item.getStatus();
                String nroTrj = item.getNumeroTarjeta();
                nroTrj = BilleteraUtils.llenarConCerosDerecha(nroTrj, BilleteraConstants.LEN_VISA);
                Cuenta cuenta = BilleteraUtils.getCtaFromNroTrj(sesionCliente.getCliente(), nroTrj);
                String nombreTrj = BilleteraUtils.getNombreTrj(cuenta);
                String nroTrjFmt = BilleteraUtils.getNroTrjFmt(nroTrj, cuenta.getTipoCuenta());

                if (!BilleteraConstants.STATUS_00000.equals(status)) {
                    mediosDePagoError.add(nombreTrj + " " + nroTrjFmt);
                } else {
                    getMedioPagoDTO(inDTO, nroTrj.trim(), mediosDePagoOk);
                    okTrj = true;
                }
            }
            billeteraDTO.setMediosDePagoOk(mediosDePagoOk);
            billeteraDTO.setMediosDePagoError(mediosDePagoError);
        }

        boolean impactCtaOk = impactCta && !sesionBilletera.getErrCta();
        boolean impactTrjOk = impactTrjs && okTrj;
        billeteraDTO.setMarcarAdhesion(getMarcarAdhesion(impactCta, impactTrjs, impactCtaOk, impactTrjOk));

        return billeteraDTO;
    }

    /**
     * Gets the cuentas acreditacion.
     *
     * @return the cuentas acreditacion
     */
    protected List<Cuenta> getCuentasAcreditacion() {
        return BilleteraUtils.getCtas(sesionCliente.getCliente(), Arrays.asList(tiposCta.split("\\|")));
    }

    /**
	 * Gets the medio pago DTO.
	 *
	 * @param inDTO
	 *            the dto
	 * @param nroTrj
	 *            the nro trj
	 * @param mediosDePagoOk
	 *            the medios de pago ok
	 * @return the medio pago DTO
	 */
    protected void getMedioPagoDTO(BilleteraInDTO inDTO, String nroTrj, List<MedioDePagoBilleteraDTO> mediosDePagoOk) {
        for (MedioDePagoBilleteraDTO mp : inDTO.getMediosDePagoActivos()) {
            String nroTarjetaCredito = ISBANStringUtils.eliminarCeros(mp.getCtaAsociada().getNroTarjetaCredito());
            if (nroTarjetaCredito.equals(nroTrj)) {
            	mediosDePagoOk.add(mp);
            }
        }
    }

    /**
     * Prc adm cbu.
     *
     * @param cta
     *            the cta
     * @param mode
     *            the mode
     * @return the respuesta
     */
    protected Respuesta<AdministrarCBUDTO> prcAdmCbu(int cta, String mode) {
        String cuit;
        try {
            NupDTO nupDTO = nupDao.obtenerNUP(sesionCliente.getCliente());
            String[] tiposDoc = { NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CDI };
            cuit = nupDTO.getCuit(tiposDoc);
        } catch (DAOException e) {
            LOGGER.error("Error al consultar CUIT", e);
            sesionBilletera.setErrCta(true);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
        }

        return administrarCbu(cta, cuit, mode);
    }

    /**
     * Prc adm medio pago.
     *
     * @param inDTO
     *            the dto
     * @param modificacion
     *            the modificacion
     * @return the respuesta
     */
    protected Respuesta<AdministrarMedioPagoDTO> prcAdmMedioPago(BilleteraInDTO inDTO, boolean modificacion) {
        Respuesta<AdministrarMedioPagoDTO> respuestaAdmMedioPago = null;
        List<Cuenta> cuentasTrj = BilleteraUtils.getCtasTrj(sesionCliente.getCliente(),
                Arrays.asList(tiposTrj.split("\\|")));
        boolean impactTrjs = inDTO.getMediosDePagoActivos() != null && !inDTO.getMediosDePagoActivos().isEmpty();
        if (!cuentasTrj.isEmpty() && impactTrjs) {
            respuestaAdmMedioPago = administrarMedioPago(inDTO, modificacion);
            if (EstadoRespuesta.ERROR.equals(respuestaAdmMedioPago.getEstadoRespuesta())) {
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
                        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
            }
            int errcnt = 0;
            Set<String> statusSet = new HashSet<String>();
            for (AdministrarMedioPagoItem item : respuestaAdmMedioPago.getRespuesta().getItems()) {
                String status = item.getStatus();
                if (!BilleteraConstants.STATUS_00000.equals(status)) {
                    statusSet.add(status);
                    errcnt++;
                }
            }
            if (errcnt == respuestaAdmMedioPago.getRespuesta().getItems().size()) {
                // Fallaron todas las adhesiones
                LOGGER.error("Error al adherir medios de pago");
                // Verifica si fallo por un unico motivo o por varios
                return billeteraServiceHelper.checkErrors(statusSet);
            }
        }
        return respuestaAdmMedioPago;
    }

    /**
     * Sets the sesion billetera user data.
     *
     * @param idxCtaTp
     *            the idxCtaTp
     * @param billeteraDTO
     *            the billetera DTO
     */
    protected void setSesionBilleteraUserData(int idxCtaTp, BilleteraDTO billeteraDTO) {
        CuentaBilleteraDTO cuentaBilleteraDto = sesionBilletera.getConsultaCuentaDto().getCuentas().get(idxCtaTp);
        sesionBilletera.setEmail(cuentaBilleteraDto.getMail());
        String empCel = BilleteraServiceHelper.getEmpresaCel(cuentaBilleteraDto.getCompaniaCelular());
        sesionBilletera.setEmpresaCelular(TipoCompaniaEnum.fromCodigo(empCel).getDescripcion());
        sesionBilletera.setNumeroCelular(cuentaBilleteraDto.getNumeroCelular());
        sesionBilletera.setTarjetasOk(billeteraDTO.getMediosDePagoOk());
        sesionBilletera.setCuentaAcreditacion(
                billeteraDTO.getCuentaAcreditacionOk() != null ? billeteraDTO.getCuentaAcreditacionOk() : "-");
        sesionBilletera.setNroComprobante(billeteraDTO.getNroComprobante());
    }

}