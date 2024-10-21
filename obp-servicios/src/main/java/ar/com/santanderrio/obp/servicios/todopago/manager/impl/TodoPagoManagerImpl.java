/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CuentaAcreditacionView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.EmpresaCelularView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.PaisOrigenDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.SolicitudTodoPagoDTO;
import ar.com.santanderrio.obp.servicios.todopago.manager.TodoPagoManager;
import ar.com.santanderrio.obp.servicios.todopago.web.utils.TodoPagoHelper;
import ar.com.santanderrio.obp.servicios.todopago.web.view.AdhesionRespuestaView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComboView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoManagerImpl.
 */
@Component("todoPagoManager")
public class TodoPagoManagerImpl implements TodoPagoManager {

    /** The Constant CONS_FINAL. */
    private static final String CONS_FINAL = "6";
    
    /** The Constant DOMICILIO_PRIMARIO. */
    private static final String DOMICILIO_PRIMARIO = "PRI";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoPagoManagerImpl.class);

    /** The TodoPago BO. */
    @Autowired
    @Qualifier("todoPagoBO")
    private TodoPagoBO todoPagoBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The CambioDomicilio BO. */
    @Autowired
    private CambioDomicilioBO cambioDomicilioBO;

    /** The TodoPago helper. */
    @Autowired
    private TodoPagoHelper todoPagoHelper;
    
    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The tipos cta. */
    @Value("${BILLETERA.TIPOSCTA}")
    private String tiposCta;

    /** The config TodoPago mail bcc. */
    @Value("${BILLETERA.MAIL.BCC}")
    private String configTodoPagoMailBcc;

    /** The config TodoPago mail from. */
    @Value("${BILLETERA.MAIL.FROM}")
    private String configTodoPagoMailFrom;

    /** The config TodoPago mail subject. */
    @Value("${BILLETERA.MAIL.SUBJECT}")
    private String configTodoPagoMailSubject;

    /** The config TodoPago mail to. */
    @Value("${BILLETERA.MAIL.TO}")
    private String configTodoPagoMailTo;

    /** The selectores BO. */
    @Autowired
    private DatosSelectoresBO selectoresBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The tyc url. */
    @Value("${BILLETERA.TYC.URL}")
    private String tycUrl;

    /**
     * Gets the cuenta default.
     *
     * @param ctas
     *            the ctas
     * @return the cuenta default
     */
    private int getCuentaDefault(List<Cuenta> ctas) {
        int defCtaIdx = -1;
        // Busca cuenta para seleccionar
        for (Cuenta cuenta : ctas) {
            if ("01".equals(cuenta.getNroOrdenFirmante())) {
                defCtaIdx = cuenta.getIndex();
                break;
            }
        }
        if (defCtaIdx == -1 && !ctas.isEmpty()) {
            // Fija la primera cuenta como seleccionada por defecto
            defCtaIdx = 0;
        }
        return defCtaIdx;
    }

    /*
     * (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.todopago.manager.TodoPagoManager#
     * descargaComprobanteAdhesion()
     */
    @Override
    public Respuesta<ReporteView> descargaComprobanteAdhesion() {
        ComprobanteAdhesionTodoPagoView comprobanteView = sesionParametros.getComprobanteAdhesionTodoPagoView();
        Respuesta<Reporte> reporteRespuesta = todoPagoBO.generarComprobante(comprobanteView);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }
        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.todopago.manager.TodoPagoManager#
     * adhesionTodoPago(ar.com.santanderrio.obp.servicios.todopago.web.view.
     * TodoPagoView)
     */
    @Override
    public Respuesta<TodoPagoView> adhesionTodoPago(TodoPagoView view) throws DAOException {
        Respuesta<SolicitudTodoPagoDTO> reporteRespuesta = todoPagoBO.adhesionTodoPago(view);
        if (EstadoRespuesta.OK.equals(reporteRespuesta.getEstadoRespuesta())) {
            SolicitudTodoPagoDTO solicitudTodoPagoDTO = reporteRespuesta.getRespuesta();
            TodoPagoView todoPagoView = crearRetornoSolicitudTodoPago(solicitudTodoPagoDTO);
            estadisticaManager.add(getEstCodeInicio(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            return respuestaFactory.crearRespuestaOk(todoPagoView);
        } else {
            estadisticaManager.add(getEstCodeInicio(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            TodoPagoView todoPagoView = new TodoPagoView();
            todoPagoView.setUrlAC(mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOPAGO_ADHESION_URL_ALTA_CUENTA).getMensaje());
            return respuestaFactory.crearRespuestaError(TodoPagoView.class, todoPagoView, null, TipoError.ERROR_TODOPAGO_SIN_CUENTAS_OPERATIVAS,
                    CodigoMensajeConstantes.TODOPAGO_ADHESION_ERROR_CUENTAS);
        }
    }

    /**
     * Crear retorno solicitud TodoPago.
     *
     * @param dto
     *            the dto
     * @return the TodoPago view
     */
    private TodoPagoView crearRetornoSolicitudTodoPago(SolicitudTodoPagoDTO dto) {
        LOGGER.debug("TodoPagoManager - crearRetornoSolicitudTodoPago");
        TodoPagoView retornoView = new TodoPagoView();
        cargarDomicilio(retornoView);
        cargarCombos(retornoView);
        retornoView.setNombre(dto.getNombre().trim());
        retornoView.setApellido(dto.getApellido().trim());
        retornoView.setNumeroDocumento(dto.getNumeroDocumento().trim());
        retornoView.setCuitDni(dto.getCuitDni());
        retornoView.setFechaNacimiento(dto.getFechaNacimiento());
        retornoView.setTycUrl(tycUrl);
        List<Cuenta> ctasFilter = BilleteraUtils.getCtas(sesionCliente.getCliente(),
                Arrays.asList(tiposCta.split("\\|")));
        List<CuentaAcreditacionView> ctas = getCuentasAcreditacionView(ctasFilter);
        retornoView.setCuentasDestino(ctas);
        return retornoView;
    }

    /**
     * Cargar domicilio.
     *
     * @param retornoView the retorno view
     */
    private void cargarDomicilio(TodoPagoView retornoView) {
        DomicilioDTO dom = new DomicilioDTO();
        Respuesta<List<CambioDomicilioDTO>> domicilios = cambioDomicilioBO.consultarDomiciliosRegistrados();
        for (CambioDomicilioDTO domicilio : domicilios.getRespuesta()) {
            if (DOMICILIO_PRIMARIO.equals(domicilio.getTipoDomicilio())) {
                dom.setCalle(domicilio.getCalle().trim());
                dom.setCodigoPostal(domicilio.getCodigoPostal().trim());
                dom.setDepartamento(domicilio.getDepartamento().trim());
                dom.setLocalidad(domicilio.getLocalidad().trim());
                dom.setNumero(ISBANStringUtils.sacarCerosYBlancosIzq(domicilio.getApt().trim()));
                dom.setPiso(domicilio.getPiso() != null ? domicilio.getPiso().trim() : "");
                dom.setProvincia(domicilio.getDescProvincia());
                retornoView.setTelefonoFijo(obtenerTelefono(domicilio));
                break;
            }
        }
        retornoView.setDomicilioLegal(dom);
    }

    /**
     * Obtener telefono.
     *
     * @param domicilio the domicilio
     * @return the string
     */
    private String obtenerTelefono(CambioDomicilioDTO domicilio) {

        StringBuilder sb = new StringBuilder();

        sb.append(domicilio.getPrefijo() != null ? "(" + domicilio.getPrefijo().trim() + ")" : "");
        sb.append(domicilio.getCaracteristica() != null ? " " + domicilio.getCaracteristica().trim() : "");
        sb.append(domicilio.getNumeroTelefono() != null ? "-" + domicilio.getNumeroTelefono().trim() : "");

        return sb.toString();
    }

    /**
     * Cargar combos.
     *
     * @param retornoView the retorno view
     */
    private void cargarCombos(TodoPagoView retornoView) {
        List<EmpresaCelularView> empresasCelular = new ArrayList<EmpresaCelularView>();
        TipoCompaniaEnum[] valuesTipoCompania = TipoCompaniaEnum.values();
        for (TipoCompaniaEnum tipoCompania : valuesTipoCompania) {
            if (tipoCompania != TipoCompaniaEnum.NULL) {
                EmpresaCelularView empresaCelularView = new EmpresaCelularView();
                empresaCelularView.setDescripcion(tipoCompania.getDescripcion());
                empresaCelularView.setId(tipoCompania.getCodigo());
                empresasCelular.add(empresaCelularView);
            }
        }
        retornoView.setComboEmpresa(empresasCelular);

        Segmento segmento = sesionCliente.getCliente().getSegmento();
        List<ComboView> condicionesIva = cargarOpcionesCombo(selectoresBO.obtenerCondicionIVA(), (segmento.isDuo() || segmento.isPyme()) ? CONS_FINAL : null);
        retornoView.setComboIva(condicionesIva);

        List<ComboView> condicionesIIBB = cargarOpcionesCombo(selectoresBO.obtenerCondicionIIBB(), null);
        retornoView.setComboIibb(condicionesIIBB);

        List<ComboView> sexo = cargarOpcionesCombo(selectoresBO.obtenerSexoCompleto(), null);
        retornoView.setComboSexo(sexo);

        cargarCombosAdicionales(retornoView);
    }

    /**
     * Cargar opciones combo.
     *
     * @param opciones the opciones
     * @param excludeId the excludeId
     * @return the list
     */
    private List<ComboView> cargarOpcionesCombo(List<Opcion> opciones, String excludeId) {
        List<ComboView> comboOpts = new ArrayList<ComboView>();
        for (Opcion opcion : opciones) {
            if (excludeId == null || !excludeId.equals(opcion.getId())) {
                ComboView viewOpt = new ComboView();
                viewOpt.setDescripcion(opcion.getOpcion());
                viewOpt.setId(opcion.getId());
                comboOpts.add(viewOpt);
            }
        }
        return comboOpts;
    }

    /**
     * Cargar combos adicionales.
     *
     * @param retornoView the retorno view
     */
    private void cargarCombosAdicionales(TodoPagoView retornoView) {
        List<ComboView> comboPais = new ArrayList<ComboView>();
        List<Opcion> opcionesPais;

        opcionesPais = selectoresBO.obtenerPaisesDeNacimiento();
        for (Opcion opcionPais : opcionesPais) {
            ComboView paises = new ComboView();
            if ("Argentina".equalsIgnoreCase(opcionPais.getOpcion())) {
                PaisOrigenDTO paisOrigen = new PaisOrigenDTO();
                paisOrigen.setId(opcionPais.getId());
                paisOrigen.setDescripcion(opcionPais.getOpcion());
                retornoView.setPaisOrigen(paisOrigen);
            }
            paises.setDescripcion(opcionPais.getOpcion());
            paises.setId(opcionPais.getId());
            comboPais.add(paises);
        }
        retornoView.setComboPais(comboPais);

        List<ComboView> comboProvincia = new ArrayList<ComboView>();
        List<Opcion> opcionesProv = selectoresBO.obtenerProvincias();
        for (Opcion opcionProvincia : opcionesProv) {
            ComboView provincias = new ComboView();
            provincias.setDescripcion(opcionProvincia.getOpcion());
            provincias.setId(opcionProvincia.getId());
            comboProvincia.add(provincias);
        }
        retornoView.setComboProvincia(comboProvincia);
    }

    /**
     * Gets the cuentas acreditacion view.
     *
     * @param cuentas
     *            the cuentas
     * @return the cuentas acreditacion view
     */
    private List<CuentaAcreditacionView> getCuentasAcreditacionView(List<Cuenta> cuentas) {
        List<CuentaAcreditacionView> cuentasAcreditacionView = new ArrayList<CuentaAcreditacionView>();
        int id = 0;
        int defIdx = getCuentaDefault(cuentas);
        for (Cuenta cuenta : cuentas) {
            CuentaAcreditacionView cuentaAcreditacion = new CuentaAcreditacionView();
            cuentaAcreditacion.setAlias(cuenta.getAlias());
            cuentaAcreditacion.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
            cuentaAcreditacion.setId(String.valueOf(id));
            cuentaAcreditacion.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
            cuentaAcreditacion.setSaldoPesos(cuenta.obtenerSaldoFormateado());
            cuentaAcreditacion.setDefaultCta(id == defIdx);
            cuentasAcreditacionView.add(cuentaAcreditacion);
            id++;
        }
        return cuentasAcreditacionView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.todopago.manager.TodoPagoManager#
     * confirmarAdhesion(ar.com.santanderrio.obp.servicios.todopago.web.view.
     * TodoPagoView)
     */
    @Override
    public Respuesta<AdhesionRespuestaView> confirmarAdhesion(TodoPagoView viewRequest) throws DAOException {

        Cliente cliente = sesionCliente.getCliente();

        estadisticaManager.add(getEstCodeConfirmacion(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        viewRequest.setNup(cliente.getNup());
        String cuil = todoPagoBO.obtenerCuil();
        if (cuil != null && !"".equals(cuil.trim())) {
            viewRequest.setCuitDni(cuil);
        } else {
            viewRequest.setCuitDni(viewRequest.getCuitDni().replaceAll("-", "").trim());
        }
        viewRequest.setRazonSocial("");
        viewRequest.setNumeroDocumento(ISBANStringUtils.eliminarCeros(cliente.getDni()));
        viewRequest.setTipoDocumento(cliente.getTipoDocumento());
        viewRequest.setNombreContacto(viewRequest.getNombre());
        viewRequest.setApellidoContacto(viewRequest.getApellido());
        viewRequest.setDomicilioFacturacion(viewRequest.getDomicilioLegal());

        // obtengo el cbu desde el indice de la cuenta que recibo del front
        List<Cuenta> ctasFilter = BilleteraUtils.getCtas(sesionCliente.getCliente(),
                Arrays.asList(tiposCta.split("\\|")));
        List<CuentaAcreditacionView> ctas = getCuentasAcreditacionView(ctasFilter);
        Integer indiceCuenta = Integer.parseInt(viewRequest.getNumeroCuenta());
        for (Cuenta cuenta : ctasFilter) {
            if (ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta)
                    .equals(ctas.get(indiceCuenta).getNumero())) {
                viewRequest.setCbu(cuenta.getCbu());
                viewRequest.setNumeroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
                viewRequest.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
                break;
            }
        }

        AdhesionTodoPagoInDTO dto = todoPagoHelper.crearAdhesionTodoPagoDTO(viewRequest);
        AdhesionTodoPagoEnvioMailDTO envioMailDto = prepararDatosEnvioMail(viewRequest);

        Respuesta<Boolean> respuestaEnvioMail = todoPagoBO.enviarMail(envioMailDto);
        if (EstadoRespuesta.OK.equals(respuestaEnvioMail.getEstadoRespuesta())) {
            dto.setEnvioMailSatisfactorio("S");
            estadisticaManager.add(getEstCodeAdhesion(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            dto.setEnvioMailSatisfactorio("N");
        }

        Respuesta<ResultadoTransaccion> respuestaAltaAdhesionTodoPago = todoPagoBO.altaAdhesion(dto);
        Respuesta<AdhesionRespuestaView> respuestaFinal;
        AdhesionRespuestaView adhesionRespuestaView = new AdhesionRespuestaView();
        if (EstadoRespuesta.OK.equals(respuestaAltaAdhesionTodoPago.getEstadoRespuesta())) {
            Date fechaTransaccion = dto.getFechaSolicitud();
            adhesionRespuestaView.setFecha(ISBANStringUtils.formatearFecha(fechaTransaccion, "dd/MM/yyyy"));
            adhesionRespuestaView.setHora(ISBANStringUtils.formatearFecha(fechaTransaccion, "HH:mm a"));
            String mensajeOk = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOPAGO_ADHESION_OK).getMensaje();
            adhesionRespuestaView.setMensaje(mensajeOk);
            adhesionRespuestaView.setNroComprobante(FechaUtils.getDateTimeYYYYMMDDhhmmssff2(fechaTransaccion));
            respuestaFinal = respuestaFactory.crearRespuestaOk(AdhesionRespuestaView.class, adhesionRespuestaView);
        } else if (respuestaAltaAdhesionTodoPago.getRespuesta() != null) {
            estadisticaManager.add(getEstCodeAdhesion(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            adhesionRespuestaView.setTipoError("ERROR_BOTON_DE_PAGO");
            adhesionRespuestaView.setMensaje(
                    mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOPAGO_ADHESION_ERROR).getMensaje());
            respuestaFinal = respuestaFactory.crearRespuestaError(AdhesionRespuestaView.class,
                    respuestaAltaAdhesionTodoPago.getItemsMensajeRespuesta());
            respuestaFinal.setRespuesta(adhesionRespuestaView);
        } else {
            estadisticaManager.add(getEstCodeAdhesion(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            adhesionRespuestaView.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
            adhesionRespuestaView.setMensaje(
                    mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.TODOPAGO_ADHESION_ERROR_GEN).getMensaje());
            respuestaFinal = respuestaFactory.crearRespuestaError(AdhesionRespuestaView.class,
                    respuestaAltaAdhesionTodoPago.getItemsMensajeRespuesta());
            respuestaFinal.setRespuesta(adhesionRespuestaView);
        }
        return respuestaFinal;
    }

    /**
     * Gets the est code adhesion.
     *
     * @return the est code adhesion
     */
    private String getEstCodeAdhesion() {
        Segmento segmento = sesionCliente.getCliente().getSegmento();
        return (segmento.isDuo() || segmento.isPyme()) ? EstadisticasConstants.BOTON_PAGO_ADHESION_ADVANCE :
            EstadisticasConstants.BOTON_PAGO_ADHESION;
    }
    
    /**
     * Gets the est code confirmacion.
     *
     * @return the est code confirmacion
     */
    private String getEstCodeConfirmacion() {
        Segmento segmento = sesionCliente.getCliente().getSegmento();
        return (segmento.isDuo() || segmento.isPyme()) ? EstadisticasConstants.BOTON_PAGO_CONFIRMACION_ADVANCE :
            EstadisticasConstants.BOTON_PAGO_CONFIRMACION;
    }

    /**
     * Gets the est code inicio.
     *
     * @return the est code inicio
     */
    private String getEstCodeInicio() {
        Segmento segmento = sesionCliente.getCliente().getSegmento();
        return (segmento.isDuo() || segmento.isPyme()) ? EstadisticasConstants.BOTON_PAGO_INICIO_ADVANCE :
            EstadisticasConstants.BOTON_PAGO_INICIO;
    }

    /**
     * Preparar datos envio mail.
     *
     * @param viewRequest
     *            the view request
     * @return the adhesion TodoPago envio mail DTO
     */
    private AdhesionTodoPagoEnvioMailDTO prepararDatosEnvioMail(TodoPagoView viewRequest) {
        AdhesionTodoPagoEnvioMailDTO mailDTO = new AdhesionTodoPagoEnvioMailDTO();

        mailDTO.setMailBcc(configTodoPagoMailBcc);
        mailDTO.setMailFrom(configTodoPagoMailFrom);
        mailDTO.setMailSubject(configTodoPagoMailSubject);
        mailDTO.setMailTo(configTodoPagoMailTo);
        mailDTO.setMailBody(todoPagoHelper.crearAltaAdhesionEnvioMailDTO(viewRequest));

        return mailDTO;
    }

}
