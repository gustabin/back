/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.TarjetaRecargableBO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.TipoDocumentoDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.TarjetaRecargableManager;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.NacionalidadView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ProvinciaView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.SexoView;

/**
 * The Class TarjetaRecargableManagerImpl.
 */
@Component
public class TarjetaRecargableManagerImpl implements TarjetaRecargableManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaRecargableManagerImpl.class);

    /** The Constant RESPUESTA_OK. */
    private static final String RESPUESTA_OK = "Respuesta del Manager OK: {}.";

    /** The Constant RESPUESTA_ERROR. */
    private static final String RESPUESTA_ERROR = "Respuesta del Manager con error.";

    /** The Constant FORMATO_FECHA_COMPROBANTE. */
    private static final String FORMATO_FECHA_COMPROBANTE = "dd/MM/yyyy HH:mm";

    /** The datos selectores service. */
    @Autowired
    private DatosSelectoresService datosSelectoresService;

    /** The tarjeta recargable BO. */
    @Autowired
    private TarjetaRecargableBO tarjetaRecargableBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<ComprobanteAltaSolicitudTarjetaRecargableView> desafioOperacionRSA;

    /** The valor desafio cambio limite extraccion. */
    @Value("${TRJCOORD.OPERAINDISTINTO.TRJRECARGABLE:3}")
    private Integer valorDesafio;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.
     * TarjetaRecargableManager#obtenerDatosIniciales()
     */
    @Override
    public Respuesta<DatosSolicitanteTarjetaRecargableView> obtenerDatosIniciales() {
        LOGGER.info("Se obtienen los datos iniciales para solicitud de una tarjeta recargable.");
        vaciarDesafio();
        Respuesta<DatosSolicitanteTarjetaRecargableView> respuesta;
        DatosSolicitanteTarjetaRecargableView datosSolicitanteTarjetaRecargableView = new DatosSolicitanteTarjetaRecargableView();
        datosSolicitanteTarjetaRecargableView
                .setNacionalidad(this.dtoToViewNacionalidad(datosSelectoresService.obtenerNacionalidad()));
        datosSolicitanteTarjetaRecargableView.setSexo(this.dtoToViewSexo(datosSelectoresService.obtenerSexo()));
        datosSolicitanteTarjetaRecargableView
                .setEstadoCivil(this.dtoToViewEstadoCivil(datosSelectoresService.obtenerEstadoCivil()));
        datosSolicitanteTarjetaRecargableView
                .setTipoDocumento(this.dtoToViewTipoDocumento(datosSelectoresService.obtenerTiposDeDocumento()));
        datosSolicitanteTarjetaRecargableView
                .setProvincia(this.dtoToViewProvincia(datosSelectoresService.obtenerProvincias()));
        respuesta = respuestaFactory.crearRespuestaOk(datosSolicitanteTarjetaRecargableView);

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.
     * TarjetaRecargableManager#altaSolicitudTarjetaRecargable(ar.com.
     * santanderrio.obp.servicios.tarjetarecargable.web.view.
     * ComprobanteAltaSolicitudTarjetaRecargableView)
     */
    @Override
    public Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> altaSolicitudTarjetaRecargable(
            ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView) {
        LOGGER.info("TarjetaRecargableManagerImpl - altaSolicitudTarjetaRecargable");
        estadisticaManager.add("11110", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaFinal = validarAltaSolicitudTarjetaRecargable(
                comprobanteAltaSolicitudTarjetaRecargableView);

        if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
            return validarRespuestaFinal(respuestaFinal);
        }

        try {
            DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO = new DatosSolicitudTarjetaRecargableInDTO();

            Cliente cliente = sesionCliente.getCliente();
            BeanUtils.copyProperties(comprobanteAltaSolicitudTarjetaRecargableView,
                    datosSolicitudTarjetaRecargableInDTO);
            setearDatosDelCliente(datosSolicitudTarjetaRecargableInDTO, cliente);
            validarCuentaGold(datosSolicitudTarjetaRecargableInDTO, cliente);
            Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO;

            respuestaDTO = tarjetaRecargableBO.altaSolicitudTarjetaRecargable(datosSolicitudTarjetaRecargableInDTO);

            EstadoRespuesta resp = respuestaDTO.getEstadoRespuesta();
            return generarRespuestaDTO(comprobanteAltaSolicitudTarjetaRecargableView, respuestaDTO, resp);
        } catch (Exception e) {
            LOGGER.error("Error al solicitar tarjeta recargable", e);
            vaciarDesafio();
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_SOLICITUDES_TARJETA_RECARGABLE);
        }
    }

    /**
     * Validar respuesta final.
     *
     * @param respuestaFinal
     *            the respuesta final
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> validarRespuestaFinal(
            Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaFinal) {
        if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta())) {
            vaciarDesafio();
        }
        return respuestaFinal;
    }

    /**
     * Generar respuesta DTO.
     *
     * @param comprobanteAltaSolicitudTarjetaRecargableView
     *            the comprobante alta solicitud tarjeta recargable view
     * @param respuestaDTO
     *            the respuesta DTO
     * @param resp
     *            the resp
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> generarRespuestaDTO(
            ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView,
            Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO, EstadoRespuesta resp) {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaFinal;
        switch (resp) {
        case OK:
            setearRespuestaDTOOk(comprobanteAltaSolicitudTarjetaRecargableView, respuestaDTO);
            return crearRespuestaExitosa(respuestaDTO.getRespuesta());
        case WARNING:
            LOGGER.info("Ha ocurrido un error parcial al solicitar una tarjeta recargable.");
            respuestaFinal = respuestaFactory.crearRespuestaWarning(ComprobanteAltaSolicitudTarjetaRecargableView.class,
                    comprobanteAltaSolicitudTarjetaRecargableView, respuestaDTO.getItemsMensajeRespuesta());
            vaciarDesafio();
            return respuestaFinal;
        case ERROR:
            LOGGER.info(RESPUESTA_ERROR, respuestaDTO);
            vaciarDesafio();
            return respuestaFactory.crearRespuestaError(ComprobanteAltaSolicitudTarjetaRecargableView.class,
                    respuestaDTO.getItemsMensajeRespuesta());
        default:
            LOGGER.error("Ha ocurrido un error inesperado");
            vaciarDesafio();
            throw new RobotException("Error inesperado");
        }
    }

    /**
     * Setear respuesta DTO ok.
     *
     * @param comprobanteAltaSolicitudTarjetaRecargableView
     *            the comprobante alta solicitud tarjeta recargable view
     * @param respuestaDTO
     *            the respuesta DTO
     */
    private void setearRespuestaDTOOk(
            ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView,
            Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO) {
        LOGGER.info(RESPUESTA_OK, respuestaDTO);
        if (comprobanteAltaSolicitudTarjetaRecargableView.getDesafio() != null) {
            comprobanteAltaSolicitudTarjetaRecargableView.setDesafio(null);
        }
        DatosComprobanteSolicitudTarjetaRecargableView datosComprobante = new DatosComprobanteSolicitudTarjetaRecargableView();
        BeanUtils.copyProperties(comprobanteAltaSolicitudTarjetaRecargableView, datosComprobante);
        datosComprobante.setNroGestion(respuestaDTO.getRespuesta().getNroGestion());
        sesionParametros.setDatosComprobanteSolicitudTarjetaRecargableView(datosComprobante);
        vaciarDesafio();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.
     * TarjetaRecargableManager#comprobanteSolicitudTarjetaRecargable()
     */
    @Override
    public Respuesta<ReporteView> comprobanteSolicitudTarjetaRecargable() {
        DatosComprobanteSolicitudTarjetaRecargableView datosComprobante = sesionParametros
                .getDatosComprobanteSolicitudTarjetaRecargableView();
        Respuesta<Reporte> reporteRespuesta = tarjetaRecargableBO
                .comprobanteSolicitudTarjetaRecargable(datosComprobante);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }
        return respuestaView;
    }

    /**
     * Validar alta solicitud tarjeta recargable.
     *
     * @param comprobanteAltaSolicitudTarjetaRecargableView
     *            the comprobante alta solicitud tarjeta recargable view
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> validarAltaSolicitudTarjetaRecargable(
            ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView) {
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        return desafioOperacionRSA
                .validarOperacionRSA(comprobanteAltaSolicitudTarjetaRecargableView, valorDesafio,
                        autentificacionCodEstDTO);
    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken("11499");
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken("11500");
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas("10834");
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas("10835");
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco("10836");
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco("10836");
        return autentificacionCodEstDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
     * ModifLimiteDebitoManager#comprobanteModifLimitesExtraccion(ar.com.
     * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
     * ComprobanteDescargaCambioLimiteView)
     */
    @Override
    public Respuesta<Void> vaciarDesafio() {
        sesionParametros.resetearDesafioEnCurso();
        return new Respuesta<Void>();
    }

    /**
     * Setear datos del cliente.
     *
     * @param datosSolicitudTarjetaRecargableInDTO
     *            the datos solicitud tarjeta recargable in DTO
     * @param cliente
     *            the cliente
     */
    private void setearDatosDelCliente(DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO,
            Cliente cliente) {

        datosSolicitudTarjetaRecargableInDTO.setNupCliente(cliente.getNup());
        datosSolicitudTarjetaRecargableInDTO.setTipoDocumentoCliente(cliente.getTipoDocumento());
        datosSolicitudTarjetaRecargableInDTO.setNroDocumentoCliente(cliente.getDni());
        datosSolicitudTarjetaRecargableInDTO.setApellido1Cliente(cliente.getApellido1());
        datosSolicitudTarjetaRecargableInDTO.setApellido2Cliente(cliente.getApellido2());
        datosSolicitudTarjetaRecargableInDTO.setNombreCliente(cliente.getNombre());
        datosSolicitudTarjetaRecargableInDTO.setFechaNacimientoCliente(cliente.getFechaNacimiento());
        datosSolicitudTarjetaRecargableInDTO.setEmail("");
    }

    /**
     * Validar cuenta gold.
     *
     * @param datosSolicitudTarjetaRecargableInDTO
     *            the datos solicitud tarjeta recargable in DTO
     * @param cliente
     *            the cliente
     */
    private void validarCuentaGold(DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO,
            Cliente cliente) {

        List<Cuenta> cuentasCliente = cliente.getCuentas();
        for (Cuenta cuenta : cuentasCliente) {
            int tipoCuenta = Integer.parseInt(cuenta.getTipoCuenta());
            int codPaquete = Integer.parseInt(cuenta.getCodigoPaquete());
            if (tipoCuenta == 2 && codPaquete >= 1 && codPaquete <= 499) {
                datosSolicitudTarjetaRecargableInDTO.setCodigoAplicacion(cuenta.getCodigoAplicacion());
                datosSolicitudTarjetaRecargableInDTO.setNroSucursal4(cuenta.getNroSucursal());
                datosSolicitudTarjetaRecargableInDTO.setNroCuentaProducto(cuenta.getNroCuentaProducto());
                datosSolicitudTarjetaRecargableInDTO.setMonedaAltair(cuenta.getMonedaAltair());
                datosSolicitudTarjetaRecargableInDTO.setNroOrdenFirmante(cuenta.getNroOrdenFirmante());
                datosSolicitudTarjetaRecargableInDTO.setProductoAltair(cuenta.getProductoAltair());
                datosSolicitudTarjetaRecargableInDTO.setSubproductoAltair(cuenta.getSubproductoAltair());
                break;
            }
        }
    }

    /**
     * Dto to view nacionalidad.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<NacionalidadView> dtoToViewNacionalidad(List<Opcion> lista) {
        List<NacionalidadView> listaRespuesta = new ArrayList<NacionalidadView>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            NacionalidadView nacionalidadView = new NacionalidadView();
            nacionalidadView.setId(opcion.getOpcion());
            nacionalidadView.setDescripcion(opcion.getOpcion());
            listaRespuesta.add(nacionalidadView);
        }
        return listaRespuesta;
    }

    /**
     * Dto to view sexo.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<SexoView> dtoToViewSexo(List<Opcion> lista) {
        List<SexoView> listaRespuesta = new ArrayList<SexoView>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            SexoView sexoView = new SexoView();
            sexoView.setId(Integer.toString(i));
            sexoView.setDescripcion(opcion.getOpcion());
            listaRespuesta.add(sexoView);
        }
        return listaRespuesta;
    }

    /**
     * Dto to view estado civil.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<EstadoCivilView> dtoToViewEstadoCivil(List<Opcion> lista) {
        List<EstadoCivilView> listaRespuesta = new ArrayList<EstadoCivilView>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            EstadoCivilView estadoCivilView = new EstadoCivilView();
            estadoCivilView.setId(Integer.toString(i));
            estadoCivilView.setDescripcion(opcion.getOpcion());
            listaRespuesta.add(estadoCivilView);
        }
        return listaRespuesta;
    }

    /**
     * Dto to view tipo documento.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<TipoDocumentoDTO> dtoToViewTipoDocumento(List<Opcion> lista) {
        List<TipoDocumentoDTO> listaRespuesta = new ArrayList<TipoDocumentoDTO>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            TipoDocumentoDTO tipoDocumentoView = new TipoDocumentoDTO();
            tipoDocumentoView.setId(Integer.toString(i));
            tipoDocumentoView.setDescripcion(opcion.getOpcion());
            listaRespuesta.add(tipoDocumentoView);
        }
        return listaRespuesta;
    }

    /**
     * Dto to view provincia.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<ProvinciaView> dtoToViewProvincia(List<Opcion> lista) {
        List<ProvinciaView> listaRespuesta = new ArrayList<ProvinciaView>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            ProvinciaView provinciaView = new ProvinciaView();
            provinciaView.setId(Integer.toString(i));
            provinciaView.setDescripcion(opcion.getOpcion());
            listaRespuesta.add(provinciaView);
        }
        return listaRespuesta;
    }

    /**
     * Crear respuesta exitosa.
     *
     * @param respuestaStoreProcedure
     *            the respuesta store procedure
     * @return the respuesta
     */
    private Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> crearRespuestaExitosa(
            DatosSolicitudTarjetaRecargableDTO respuestaStoreProcedure) {
        ComprobanteAltaSolicitudTarjetaRecargableView respuestaView = new ComprobanteAltaSolicitudTarjetaRecargableView();
        respuestaView.setComprobante(respuestaStoreProcedure.getNroGestion());
        respuestaView.setFechaHora(DateFormatUtils.format(new Date(), FORMATO_FECHA_COMPROBANTE));
        respuestaView.setMensaje(
                "<p>La solicitud de la tarjeta</p><p><b>Visa Santander Recargable</b><br> se realizó con éxito. </p>");
        return respuestaFactory.crearRespuestaOk(ComprobanteAltaSolicitudTarjetaRecargableView.class, respuestaView);
    }
}
