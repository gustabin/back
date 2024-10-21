/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelper;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionMovimientosPendientesDeConfirmacion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionMovimientosValores;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ChequesYValoresPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosPendientesDeConfirmacion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosValoresPendientes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.OrdenMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.OrigenTransaccionEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ItemMovimiento;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class MovimientosManagerImpl.
 */
@Component
public class MovimientosManagerImpl implements MovimientosManager {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MovimientosManagerImpl.class);

    /** The Constant TAMAÑO_PAGINA. */
    private static final int TAMANO_PAGINA = 10;

    /** The Constant CANTIDAD_MOVIMIENTOS. */
    private static final int CANTIDAD_MOVIMIENTOS = 0;

    /** The Constant INDICADOR_ESTADO_TARJETA_CREDITO_S. */
    private static final String INDICADOR_ESTADO_TARJETA_CREDITO_S = "S";

    /** The Constant INDICADOR_ESTADO_TARJETA_CREDITO_N. */
    private static final String INDICADOR_ESTADO_TARJETA_CREDITO_N = "N";

    /** The Constant CONTROLAR_CANTIDAD_DECIMALES. */
    private static final int CONTROLAR_CANTIDAD_DECIMALES = 3;

    /** The Constant VALORES_SERVICIO_ERROR_MSG. */
    private static final String VALORES_SERVICIO_ERROR_MSG = "Error al obtener valores pendientes";

    /** The Constant BIG_DECIMAL_INFINITO. */
    private static final BigDecimal BIG_DECIMAL_INFINITO = new BigDecimal("999999999.99");

    /** The Constant simpleDateFormatMobile. */
    private static final SimpleDateFormat simpleDateFormatMobile = new SimpleDateFormat("dd/MM/yy");

    /** The Constant simpleDateFormatDesktop. */
    private static final SimpleDateFormat simpleDateFormatDesktop = new SimpleDateFormat("dd/MM/yyyy");

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /**
     * The Enum TipoOperacionMovimiento.
     */
    private static enum TipoOperacionMovimiento {

        /** The credito. */
        CREDITO,

        /** The debito. */
        DEBITO
    }

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The cuentas BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The obtener movimientos pendientes. */
    @Autowired
    private MovimientosPendientesDeConfirmacionBO movimientosPendientesDeConfirmacionBO;

    /** The session movimientos valores. */
    @Autowired
    private SessionMovimientosValores sessionMovimientosValores;

    /** The session movimientos pendientes de confirmacion. */
    @Autowired
    private SessionMovimientosPendientesDeConfirmacion sessionMovimientosPendientesDeConfirmacion;

    /** The session movimientos. */
    @Autowired
    private SessionMovimientos sessionMovimientos;

    /** The ultimos movimientos BO. */
    @Autowired
    private UltimosMovimientosBO ultimosMovimientosBO;

    /** The cheques Y valores pendientes de confirmacion BO. */
    @Autowired
    private ChequesYValoresPendientesDeConfirmacionBO chequesYValoresPendientesDeConfirmacionBO;
    
    /** The reporte BO. */
    @Autowired
    private ReporteComprobantePDFBO reporteBO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.web.manager.MovimientosManager#
     * getMovimientosPendientes(ar.com.santanderrio.obp.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientes(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        String[] numeroCuenta = consultaUltimosMovimientosView.getNumero().split("-");
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
        cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);

        identificacionCuenta.setNroSucursal(numeroCuenta[0]);
        identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
        Cliente cliente = sesionCliente.getCliente();
        Respuesta<MovimientosPendientesDeConfirmacionView> respuestaMovimientosPendientesView = new Respuesta<MovimientosPendientesDeConfirmacionView>();
        try {
            AbstractCuenta cuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);

            MovimientosPendientesDeConfirmacionView movimientosPendientesView = new MovimientosPendientesDeConfirmacionView();

            Respuesta<MovimientosPendientesDeConfirmacion> respuestaMovimientosPendientes = movimientosPendientesDeConfirmacionBO
                    .obtenerMovimientosPendientesDeConfirmacionPorCuenta(cuenta);
            if (respuestaMovimientosPendientes.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                movimientosPendientesView
                        .setCantidadDeMovimientosPendientesDeConfirmacion(respuestaMovimientosPendientes.getRespuesta()
                                .getCantidadDeMovimientosPendientesDeConfirmacion());

                Iterator<MovimientoDeCuenta> movimientoIterator = respuestaMovimientosPendientes.getRespuesta()
                        .getMovimientosPendientesDeConfirmacion().iterator();

                List<MovimientoDeCuentaView> movimientosPendientesDeConfirmacion = new ArrayList<MovimientoDeCuentaView>();
                while (movimientoIterator.hasNext()) {
                    armarMovimientoView(movimientoIterator.next(), movimientosPendientesDeConfirmacion);
                }
                sessionMovimientosPendientesDeConfirmacion.setMovimientoDeCuenta(
                        respuestaMovimientosPendientes.getRespuesta().getMovimientosPendientesDeConfirmacion());
                sessionMovimientosPendientesDeConfirmacion.setCuenta(consultaUltimosMovimientosView.getNumero());

                sessionMovimientosPendientesDeConfirmacion
                        .setTipoCuenta(getDescripcionCuenta(consultaUltimosMovimientosView));

                movimientosPendientesView.setMovimientosPendientesDeConfirmacion(movimientosPendientesDeConfirmacion);
                respuestaMovimientosPendientesView.setRespuesta(movimientosPendientesView);
                respuestaMovimientosPendientesView.setEstadoRespuesta(EstadoRespuesta.OK);
                respuestaMovimientosPendientesView.setRespuestaVacia(false);
                grabarEstadisticaMovimientosPendientes(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {

                respuestaMovimientosPendientesView = respuestaFactory.crearRespuestaError("",
                        TipoError.ERROR_MOVIMIENTOS_PENDIENTES,
                        CodigoMensajeConstantes.CODIGO_ERROR_MOVIMIENTOS_PENDIENTES);
                // En el caso que el servicio de Itx de error
                grabarEstadisticaMovimientosPendientes(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }

        } catch (ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex);
            respuestaMovimientosPendientesView = respuestaFactory.crearRespuestaError("",
                    TipoError.ERROR_MOVIMIENTOS_PENDIENTES,
                    CodigoMensajeConstantes.CODIGO_ERROR_MOVIMIENTOS_PENDIENTES);
            grabarEstadisticaMovimientosPendientes(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return respuestaMovimientosPendientesView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * getMovimientos(ar.com.santanderrio.obp.servicios.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientoView> getMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {

        LOGGER.info("Inicio de MovimientosManager.");
        Respuesta<MovimientoView> repuestaValidacion;
        repuestaValidacion = validarFormatos(consultaUltimosMovimientosView);
        if (!repuestaValidacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            return repuestaValidacion;
        }

        ConsultaUltimosMovimientos filtroMovimientos = crearConsultaUltimosMovimientos(consultaUltimosMovimientosView);
        filtroMovimientos = normalizarFiltro(filtroMovimientos);
        Respuesta<ConsultaUltimosMovimientos> respuestaFiltroValidado = ultimosMovimientosBO
                .validarFiltros(filtroMovimientos, sessionMovimientos.getFiltro(), consultaUltimosMovimientosView.getPrimerIngreso());

        if (!EstadoRespuesta.OK.equals(respuestaFiltroValidado.getEstadoRespuesta())) {
            return Respuesta.copy(MovimientoView.class, respuestaFiltroValidado);
        }
        ConsultaUltimosMovimientos filtroValidado = respuestaFiltroValidado.getRespuesta();

        return consultarMovimientosConFiltroValidado(filtroValidado);
    }

    /**
     * Consultar movimientos con filtro validado.
     *
     * @param filtroValidado
     *            the filtro validado
     * @return the respuesta
     */
    private Respuesta<MovimientoView> consultarMovimientosConFiltroValidado(ConsultaUltimosMovimientos filtroValidado) {

        Respuesta<DetalleUltimosMovimientos> respuestaDetalleUltimosMovimientos = ultimosMovimientosBO
                .obtenerUltimosMovimientos(filtroValidado, sessionMovimientos.getFiltro());
        sessionMovimientos.setFiltro(filtroValidado);

        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(filtroValidado.getCuenta().getTipoCuenta());

        if (EstadoRespuesta.ERROR.equals(respuestaDetalleUltimosMovimientos.getEstadoRespuesta())) {
            sessionMovimientos.setFiltro(null);
            estadisticaManager.add(
                    getCodTipoTransacionFromTipoCuentaFiltro(tipoCuenta, filtroValidado.getMoneda(), filtroValidado),
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            String codMensaje = CodigoMensajeConstantes.CODIGO_ERROR_GENERICO;
            if (!isFiltroDefault(filtroValidado)) {
                codMensaje = CodigoMensajeConstantes.CODIGO_ERROR_AL_DESCARGAR_ALGUN_RESUMEN_EN_ZIP;
            }
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SIN_MOVIMIENTOS, codMensaje);
        }
        Respuesta<MovimientoView> respuestaMovimientoView = new Respuesta<MovimientoView>();
        MovimientoView movimientoView = new MovimientoView();
        if (EstadoRespuesta.OK.equals(respuestaDetalleUltimosMovimientos.getEstadoRespuesta())) {
            DetalleUltimosMovimientos detalleUltimosMovimientos = respuestaDetalleUltimosMovimientos.getRespuesta();
            movimientoView = popularMovimientoView(detalleUltimosMovimientos);
            if (!sesionParametros.getBusquedaDefaultCincuentaYTresDiasRTL()) {
	            estadisticaManager.add(
	                    getCodTipoTransacionFromTipoCuentaFiltro(tipoCuenta, filtroValidado.getMoneda(), filtroValidado),
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
            return respuestaFactory.crearRespuestaOk(movimientoView);
        } else {
            DetalleUltimosMovimientos detalleUltimosMovimientos = respuestaDetalleUltimosMovimientos.getRespuesta();
            movimientoView = popularMovimientoView(detalleUltimosMovimientos);
            respuestaMovimientoView = Respuesta.copy(MovimientoView.class, respuestaDetalleUltimosMovimientos);
            respuestaMovimientoView.setRespuesta(movimientoView);
            if (!sesionParametros.getBusquedaDefaultCincuentaYTresDiasRTL()) {
	            if (!existeErrorParcial(respuestaDetalleUltimosMovimientos)) {
	                estadisticaManager.add(getCodTipoTransacionFromTipoCuentaFiltro(tipoCuenta, filtroValidado.getMoneda(),
	                        filtroValidado), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	            } else {
	                estadisticaManager.add(getCodTipoTransacionFromTipoCuentaFiltro(tipoCuenta, filtroValidado.getMoneda(),
	                        filtroValidado), EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
	            }
            }
        }
        return respuestaMovimientoView;
    }

    /**
     * Existe error parcial.
     *
     * @param respuestaDetalleUltimosMovimientos
     *            the respuesta detalle ultimos movimientos
     * @return true, if successful
     */
    private boolean existeErrorParcial(Respuesta<?> respuestaDetalleUltimosMovimientos) {

        List<ItemMensajeRespuesta> itemsMensajeRespuesta = respuestaDetalleUltimosMovimientos
                .getItemsMensajeRespuesta();

        if (itemsMensajeRespuesta != null) {
            for (ItemMensajeRespuesta itemMensajeRespuesta : itemsMensajeRespuesta) {
                if ((TipoError.ERROR_PARCIAL_GRILLA_MOVIMIENTOS.getDescripcion()
                        .equals(itemMensajeRespuesta.getTipoError()))
                        || (TipoError.ERROR_PARCIAL_GRILLA_MOVIMIENTOS_DIA.getDescripcion()
                                .equals(itemMensajeRespuesta.getTipoError()))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Normalizar filtro.
     *
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @return the consulta ultimos movimientos
     */
    public ConsultaUltimosMovimientos normalizarFiltro(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
        ConsultaUltimosMovimientos normalizado = new ConsultaUltimosMovimientos(consultaUltimosMovimientos);

        // importes
        if (consultaUltimosMovimientos.getImporteHasta() == null) {
            normalizado.setImporteHasta(BIG_DECIMAL_INFINITO);
        }
        if (consultaUltimosMovimientos.getImporteDesde() == null) {
            normalizado.setImporteDesde(BigDecimal.ZERO);
        }

        RangoFechaEnum rango = consultaUltimosMovimientos.getRangoFecha();

        if (rango == null) {
            rango = RangoFechaEnum.DEFAULT;
            normalizado.setRangoFecha(rango);
        }

        if (rango.equals(RangoFechaEnum.PERSONALIZADO)) {
            // fechas
            normalizarRangoPersonalizado(consultaUltimosMovimientos, normalizado);
        } else {
            normalizado.setFechaDesde(null);
            normalizado.setFechaHasta(null);
        }

        normalizado
                .setPalabraClave(ISBANStringUtils.normalizarCaraceteres(consultaUltimosMovimientos.getPalabraClave()));
        normalizado.setTipoCuenta(consultaUltimosMovimientos.getTipoCuenta());
        return normalizado;
    }

    /**
     * Normalizar rango personalizado.
     *
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     * @param normalizado
     *            the normalizado
     */
    private void normalizarRangoPersonalizado(ConsultaUltimosMovimientos consultaUltimosMovimientos,
            ConsultaUltimosMovimientos normalizado) {
        if (consultaUltimosMovimientos.getFechaDesde() == null && consultaUltimosMovimientos.getFechaHasta() != null) {
            normalizado.setFechaDesde(consultaUltimosMovimientos.getFechaHasta());
            normalizado.setFechaHasta(consultaUltimosMovimientos.getFechaHasta());
        } else if (consultaUltimosMovimientos.getFechaHasta() == null
                && consultaUltimosMovimientos.getFechaDesde() != null) {
            normalizado.setFechaDesde(consultaUltimosMovimientos.getFechaDesde());
            normalizado.setFechaHasta(consultaUltimosMovimientos.getFechaDesde());
        } else if (consultaUltimosMovimientos.getFechaHasta() != null
                && consultaUltimosMovimientos.getFechaDesde() != null) {
            normalizado.setFechaDesde(consultaUltimosMovimientos.getFechaDesde());
            normalizado.setFechaHasta(consultaUltimosMovimientos.getFechaHasta());
        }
    }

    /**
     * Popular movimiento view.
     *
     * @param detalleUltimosMovimientos
     *            the detalle ultimos movimientos
     * @return the movimiento view
     */
    private MovimientoView popularMovimientoView(DetalleUltimosMovimientos detalleUltimosMovimientos) {

        MovimientoView movimientoView = new MovimientoView();

        if (detalleUltimosMovimientos != null && detalleUltimosMovimientos.getDetalleMovimiento() != null
                && !detalleUltimosMovimientos.getDetalleMovimiento().isEmpty()) {
            int indice = sessionMovimientos.getFiltro().getOffset() * TAMANO_PAGINA;

            int indiceFin = ((indice + TAMANO_PAGINA) > detalleUltimosMovimientos.getDetalleMovimiento().size())
                    ? detalleUltimosMovimientos.getDetalleMovimiento().size() : (indice + TAMANO_PAGINA);
            List<DetalleMovimientoEntity> deetalleMovimientosList = detalleUltimosMovimientos.getDetalleMovimiento()
                    .subList(indice, indiceFin);
            for (DetalleMovimientoEntity detalleMovimiento : deetalleMovimientosList) {
                ItemMovimiento itemMovimiento = generateItemMovimiento(detalleMovimiento);
                movimientoView.addItemMovimiento(itemMovimiento);
            }
            sessionMovimientos.getFiltro().setOffset(sessionMovimientos.getFiltro().getOffset() + 1);
            movimientoView.setLastPage(indiceFin == detalleUltimosMovimientos.getDetalleMovimiento().size());
        }

        return movimientoView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * isFiltroDefault(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * ConsultaUltimosMovimientos)
     */
    @Override
    public boolean isFiltroDefault(ConsultaUltimosMovimientos consultaUltimosMovimientos) {
        boolean isDefault = false;
        if (consultaUltimosMovimientos != null) {
            ConsultaUltimosMovimientos normalizado = normalizarFiltro(consultaUltimosMovimientos);
            isDefault = (normalizado.getRangoFecha().equals(RangoFechaEnum.DEFAULT) || normalizado.getRangoFecha().equals(RangoFechaEnum.CINCUENTA_Y_TRES_DIAS)) 
                    && normalizado.getImporteDesde().compareTo(BigDecimal.ZERO) == 0
                    && normalizado.getImporteHasta().compareTo(BIG_DECIMAL_INFINITO) == 0
                    && "".equals(normalizado.getPalabraClave().trim());

        }
        return isDefault;
    }

    /**
     * Validar formatos.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the respuesta
     */
    private Respuesta<MovimientoView> validarFormatos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        Respuesta<MovimientoView> respuestaMovimientoView = new Respuesta<MovimientoView>();

        // verificar importes
        if (existeErrorImporte(consultaUltimosMovimientosView.getImporteDesde())
                || existeErrorImporte(consultaUltimosMovimientosView.getImporteHasta())) {
            respuestaMovimientoView = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                    TipoError.SIN_MOVIMIENTOS, CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS);
            respuestaMovimientoView.setRespuestaVacia(true);
            return respuestaMovimientoView;
        }

        // verificar fechas
        if (existeErrorFechas(consultaUltimosMovimientosView.getFechaDesde())
                || existeErrorFechas(consultaUltimosMovimientosView.getFechaHasta())) {
            respuestaMovimientoView = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                    TipoError.SIN_MOVIMIENTOS, CodigoMensajeConstantes.CODIGO_SIN_MOVIMIENTOS);
            respuestaMovimientoView.setRespuestaVacia(true);
            return respuestaMovimientoView;
        }

        respuestaMovimientoView = respuestaFactory.crearRespuestaOk(MovimientoView.class);
        return respuestaMovimientoView;
    }

    /**
     * Existe error fechas.
     *
     * @param fecha
     *            the fecha
     * @return true, if successful
     */
    private boolean existeErrorFechas(String fecha) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean existeError = false;
        try {
            if (fecha != null && !"".equalsIgnoreCase(fecha)) {
                formatter.parse(fecha);
            }
        } catch (ParseException e) {
            LOGGER.info("ParseException: " + e);
            existeError = true;
            return existeError;
        }
        return existeError;
    }

    /**
     * Existe error importe.
     *
     * @param importe
     *            the importe
     * @return true, if successful
     */
    private boolean existeErrorImporte(String importe) {
        int cantidadSeparadorDecimal = 0;
        boolean existeError = false;
        boolean existeSeparadorDecimal = false;
        int posicionSeparadorDecimal = 0;
        StringBuffer parteEntera = new StringBuffer();
        StringBuffer parteDecimal = new StringBuffer();

        if (StringUtils.isNotBlank(importe)) {

            // convertir todas las comas por puntos
            String newImporte = importe.replace(',', '.');

            for (int i = 0; i < newImporte.length(); i++) {
                // Detectar si hay un punto para definir decimal en la cadena
                if (newImporte.charAt(i) == '.' || newImporte.charAt(i) == ',') {
                    existeSeparadorDecimal = true;
                    cantidadSeparadorDecimal = cantidadSeparadorDecimal + 1;
                    // Si hay punto guardar la posicion donde se encuentra el
                    // caracter
                    if (newImporte.charAt(i) == '.') {
                        posicionSeparadorDecimal = newImporte.indexOf('.');
                    } else {
                        posicionSeparadorDecimal = newImporte.indexOf(',');
                    }

                }
                if (cantidadSeparadorDecimal > 1) {
                    // Hay mas de un separador decimal, devolver error
                    existeError = true;
                }
            }

            if (!existeError && existeSeparadorDecimal) {
                // Si el separador decimal esta al final o al principio no es un
                // decimal.
                if (posicionSeparadorDecimal == newImporte.length() - 1 || posicionSeparadorDecimal == 0) {
                    existeError = true;
                }
                // Si tiene mas de 2 decimales, tambien devolver error.
                if (posicionSeparadorDecimal < newImporte.length() - CONTROLAR_CANTIDAD_DECIMALES) {
                    existeError = true;
                }
            }
            // Guardar parte entera.
            if (!existeError) {
                if (existeSeparadorDecimal) {
                    for (int i = 0; i < posicionSeparadorDecimal; i++) {
                        parteEntera.append(newImporte.charAt(i));
                    }
                } else {
                    for (int i = 0; i < newImporte.length(); i++) {
                        parteEntera.append(newImporte.charAt(i));
                    }
                }
            }

            // Guardar parte decimal, en caso que tenga decimales.
            if (!existeError && existeSeparadorDecimal) {
                for (int i = posicionSeparadorDecimal + 1; i < newImporte.length(); i++) {
                    parteDecimal.append(newImporte.charAt(i));
                }
            }

            // verificar que son digitos la parte entera
            if (!existeError) {
                for (int i = 0; i < parteEntera.length(); i++) {
                    if (!Character.isDigit(parteEntera.charAt(i))) {
                        existeError = true;
                    }
                }

            }
            if (!existeError && existeSeparadorDecimal) {
                // verificar que son digitos la parte decimal, en caso que tenga
                // decimales.
                for (int i = 0; i < parteDecimal.length(); i++) {
                    if (!Character.isDigit(parteDecimal.charAt(i))) {
                        existeError = true;
                    }
                }
            }
            // Verificar que si aun no hay error, las fechas no deben ser menor
            // a 60 dias a partir de hoy
        }
        return existeError;
    }

    /**
     * Gets the cod tipo transacion from tipo cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param divisa
     *            the divisa
     * @param filtro
     *            the filtro
     * @return the cod tipo transacion from tipo cuenta
     */
    private String getCodTipoTransacionFromTipoCuentaFiltro(TipoCuenta tipoCuenta, DivisaEnum divisa,
            ConsultaUltimosMovimientos filtro) {

        String response = "";
        if (filtro == null || isFiltroDefault(filtro)) {
            response = filtroDefaultONullEstadisticaConstant(tipoCuenta, divisa);
            if ("".equals(response)) {
                response = filtroDefaultONullEstadisticaConstant2(tipoCuenta, divisa);
            }

        } else {
            if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCuenta)) {
                return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CAD_AVANZADOS;
            }

            if (TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta)) {
                return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CAP_AVANZADOS;
            }

            response = nofiltroDefaultONullEstadisticaConstant(tipoCuenta, filtro);
        }

        return response;
    }

    /**
     * Filtro default O null estadistica constant.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param divisa
     *            the divisa
     * @return the string
     */
    private String filtroDefaultONullEstadisticaConstant(TipoCuenta tipoCuenta, DivisaEnum divisa) {
        if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CAD;
        }

        if (TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CAP;
        }

        if (TipoCuenta.CUENTA_UNICA.equals(tipoCuenta) && DivisaEnum.PESO.equals(divisa)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CU;
        }
        return "";
    }

    /**
     * Filtro default O null estadistica constant 2.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param divisa
     *            the divisa
     * @return the string
     */
    private String filtroDefaultONullEstadisticaConstant2(TipoCuenta tipoCuenta, DivisaEnum divisa) {
        if (TipoCuenta.CUENTA_UNICA.equals(tipoCuenta) && DivisaEnum.DOLAR.equals(divisa)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CU_DOLARES;
        }

        if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CCD;
        }
        if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CCP;
        }
        return "";
    }

    /**
     * Nofiltro default O null estadistica constant.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @param filtro
     *            the filtro
     * @return the string
     */
    private String nofiltroDefaultONullEstadisticaConstant(TipoCuenta tipoCuenta, ConsultaUltimosMovimientos filtro) {
        if (TipoCuenta.CUENTA_UNICA.equals(tipoCuenta)) {
            if (filtro.getIsTraspasoAutomatico() != null && filtro.getIsTraspasoAutomatico()) {
                return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CU_AVANZADOS;
            } else {
                return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CU_SIN_TRASPASO_AVANZADOS;
            }
        }

        if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CCD_AVANZADOS;
        }
        if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta)) {
            return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_CCP_AVANZADOS;
        }
        return "";
    }

    /**
     * Generate item movimiento.
     *
     * @param detalleMovimiento
     *            the detalle movimiento
     * @return the item movimiento
     */
    private ItemMovimiento generateItemMovimiento(DetalleMovimientoEntity detalleMovimiento) {
        ItemMovimiento im = new ItemMovimiento();

        im.setFechaDetalle(simpleDateFormatDesktop.format(detalleMovimiento.getFecha()));
        im.setFecha(simpleDateFormatDesktop.format(detalleMovimiento.getFecha()));
        if (sesionParametros.getRegistroSession().isMobile()) {
            im.setFecha(simpleDateFormatMobile.format(detalleMovimiento.getFecha()));
        }
        im.setDescripcion(detalleMovimiento.getDescripcion());
        im.setDetalle(detalleMovimiento.getDescripcionAdicional());
        // Agregar formato
        im.setImporte(ISBANStringUtils.formatearSaldo(detalleMovimiento.getImporteMovimiento().abs()));
        im.setIsDebito(detalleMovimiento.getImporteMovimiento().compareTo(BigDecimal.ZERO) < 0);
        // Agregar formato
        if (detalleMovimiento.getIsDelDia()) {

            im.setSaldo("");
        } else {

            im.setSaldo(ISBANStringUtils.formatearSaldoConSigno(detalleMovimiento.getSaldoCuenta()));
        }
        im.setIsCajaDeAhoroEnPesos(detalleMovimiento.getIsCajaDeAhoroEnPesos());
        im.setIsCuentaCorrienteEnPesos(detalleMovimiento.getIsCuentaCorrienteEnPesos());
        im.setNumeroReferencia(detalleMovimiento.getNumeroReferencia());
        im.setHora(detalleMovimiento.getHora());
        im.setNumeroSucursal(StringUtils.right(detalleMovimiento.getNumeroSucursal(), 3));
        im.setDescripcionSucursal(detalleMovimiento.getDescripcionSucursal());
        im.setIsCheque(detalleMovimiento.isCheque());
        im.setIsRechazado(detalleMovimiento.isRechazado());
        im.setMotivoRechazo(detalleMovimiento.getMotivoRechazo());
        im.setObservacion(detalleMovimiento.getObservacion());
        im.setId(detalleMovimiento.getId());
        im.setDelDia(detalleMovimiento.getIsDelDia());
        if (detalleMovimiento.getIsMovimientoEnDolares()) {
            im.setDivisa(DivisaEnum.DOLAR.getSimbolo());
        } else {
            im.setDivisa(DivisaEnum.PESO.getSimbolo());
        }
        return im;
    }

    /**
     * Armar movimiento view.
     *
     * @param movimientoDeCuenta
     *            the movimiento de cuenta
     * @param movimientosPendientesDeConfirmacion
     *            the movimientos pendientes de confirmacion
     */
    private void armarMovimientoView(MovimientoDeCuenta movimientoDeCuenta,
            List<MovimientoDeCuentaView> movimientosPendientesDeConfirmacion) {
        String origen = new String();
        MovimientoDeCuentaView movimientoDeCuentaView = new MovimientoDeCuentaView();
        movimientoDeCuentaView
                .setImporteOperacion(ISBANStringUtils.formatearSaldo(movimientoDeCuenta.getImporteOperacion()));
        movimientoDeCuentaView.setDivisa(movimientoDeCuenta.getDivisa().getSimbolo());
        movimientoDeCuentaView.setHora(quitarSegundos(movimientoDeCuenta.getHora()));
        movimientoDeCuentaView.setNumeroDeCuenta(movimientoDeCuenta.getNumeroDeCuenta());
        movimientoDeCuentaView.setNumeroDeTicket(movimientoDeCuenta.getNumeroDeTicket());
        movimientoDeCuentaView.setFecha(movimientoDeCuenta.getFecha());
        if (StringUtils.isNotBlank(movimientoDeCuenta.getOrigenTransaccion())) {
            origen = OrigenTransaccionEnum.valueOf(movimientoDeCuenta.getOrigenTransaccion()).toString();
        }
        String descripcion = movimientoDeCuenta.getTipoDeOperacion().toString() + " por " + origen;
        movimientoDeCuentaView.setDescripcionDeOperacion(descripcion);
        StringBuilder sb = new StringBuilder("Sucursal ");
        sb.append(ISBANStringUtils.formatearSucursal(movimientoDeCuenta.getSucursalOrigen().getIdSucursal()));
        sb.append(" - ");
        sb.append(movimientoDeCuenta.getSucursalOrigen().getDescripcion());
        movimientoDeCuentaView.setSucursal(sb.toString());
        movimientosPendientesDeConfirmacion.add(movimientoDeCuentaView);
    }

    /**
     * Quita los segundos de la hora si el formato viene HH:mm:ss.
     *
     * @param hora
     *            the hora
     * @return the string
     */
    private String quitarSegundos(String hora) {

        if (hora != null) {
            if (hora.length() > 5) {

                return hora.substring(0, 5);
            }
            return hora;
        }
        return null;
    }

    /**
     * Crear consulta ultimos movimientos.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the consulta ultimos movimientos
     */
    private ConsultaUltimosMovimientos crearConsultaUltimosMovimientos(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCantidadMovimientos(CANTIDAD_MOVIMIENTOS);
        setFecha(consultaUltimosMovimientosView, consultaUltimosMovimientos);
        if (consultaUltimosMovimientosView.getImporteDesde() != null
                && !"".equalsIgnoreCase(consultaUltimosMovimientosView.getImporteDesde())) {
            consultaUltimosMovimientos
                    .setImporteDesde(new BigDecimal(consultaUltimosMovimientosView.getImporteDesde()));
        }
        if (consultaUltimosMovimientosView.getImporteHasta() != null
                && !"".equalsIgnoreCase(consultaUltimosMovimientosView.getImporteHasta())) {
            consultaUltimosMovimientos
                    .setImporteHasta(new BigDecimal(consultaUltimosMovimientosView.getImporteHasta()));
        }
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        String[] numeroCuenta = consultaUltimosMovimientosView.getNumero().split("-");

        StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
        cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);

        identificacionCuenta.setNroSucursal(numeroCuenta[0]);
        identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
        Cliente cliente = sesionCliente.getCliente();
        AbstractCuenta cuenta;
        cuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);
        if (cuenta.isCuentaCerrada()) {
            consultaUltimosMovimientos.setIndicador(INDICADOR_ESTADO_TARJETA_CREDITO_N);
        } else {
            if (cuenta.isCuentaUnica()) {
                consultaUltimosMovimientos.setIndicador(INDICADOR_ESTADO_TARJETA_CREDITO_S);
            } else {
                consultaUltimosMovimientos.setIndicador(INDICADOR_ESTADO_TARJETA_CREDITO_N);
            }
        }
        consultaUltimosMovimientos.setCuenta(cuenta);
        consultaUltimosMovimientos.setOrdenMovimientos(OrdenMovimientos.DESCENDENTE);
        consultaUltimosMovimientos.setTipoCuenta(consultaUltimosMovimientosView.getTipoCuenta());
        
        setearRangoFecha(consultaUltimosMovimientos, consultaUltimosMovimientosView);

        consultaUltimosMovimientos.setMoneda(consultaUltimosMovimientosView.getMoneda());
        consultaUltimosMovimientos.setPalabraClave(consultaUltimosMovimientosView.getPalabraClave());
        consultaUltimosMovimientos.setOffset(0);
        consultaUltimosMovimientos.setDesdeDefault(consultaUltimosMovimientosView.getDesdeDefault());
        return consultaUltimosMovimientos;
    }

	/**
	 * Setear rango fecha.
	 *
	 * @param consultaUltimosMovimientos
	 *            the consulta ultimos movimientos
	 * @param consultaUltimosMovimientosView
	 *            the consulta ultimos movimientos view
	 */
	private void setearRangoFecha(ConsultaUltimosMovimientos consultaUltimosMovimientos, 
			ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
		
		if (RangoFechaEnum.DEFAULT.equals(consultaUltimosMovimientosView.getRango())) {
        	sesionParametros.setBusquedaDefaultCincuentaYTresDiasRTL(Boolean.FALSE);
			consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
		} else if (sesionParametros.getBusquedaDefaultCincuentaYTresDiasRTL() && 
        	RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(consultaUltimosMovimientosView.getRango())) {
        	consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.SESENTA_DIAS);
        } else if (!sesionParametros.getBusquedaDefaultCincuentaYTresDiasRTL() && 
            RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(consultaUltimosMovimientosView.getRango())) {
        	sesionParametros.setBusquedaDefaultCincuentaYTresDiasRTL(Boolean.TRUE);
        	consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.CINCUENTA_Y_TRES_DIAS);
        } else {
        	sesionParametros.setBusquedaDefaultCincuentaYTresDiasRTL(Boolean.FALSE);
        	consultaUltimosMovimientos.setRangoFecha(consultaUltimosMovimientosView.getRango());
        }
		
	}
    
    
    /**
     * Sets the fecha.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @param consultaUltimosMovimientos
     *            the consulta ultimos movimientos
     */
    private void setFecha(ConsultaUltimosMovimientosView consultaUltimosMovimientosView,
            ConsultaUltimosMovimientos consultaUltimosMovimientos) {
        if (consultaUltimosMovimientosView.getFechaDesde() != null
                && !StringUtils.EMPTY.equalsIgnoreCase(consultaUltimosMovimientosView.getFechaDesde())) {
            consultaUltimosMovimientos.setFechaDesde(parseDate(consultaUltimosMovimientosView.getFechaDesde()));
        }
        if (consultaUltimosMovimientosView.getFechaHasta() != null
                && !StringUtils.EMPTY.equalsIgnoreCase(consultaUltimosMovimientosView.getFechaHasta())) {
            consultaUltimosMovimientos.setFechaHasta(parseDate(consultaUltimosMovimientosView.getFechaHasta()));
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.web.manager.MovimientosManager#
     * getMovimientosValores(ar.com.santanderrio.obp.example.bienvenida.web.
     * movimiento.view.ConsultaUltimosMovimientosView)
     */
    @Override
    public Respuesta<MovimientoValoresView> getMovimientosValores(
            ConsultaUltimosMovimientosView consultaUltimosMovimientosView) {
        Respuesta<MovimientoValoresView> respuesta = new Respuesta<MovimientoValoresView>();
        respuesta.setRespuestaVacia(true);
        // Obtengo la cuenta a consultar
        ConsultaUltimosMovimientos consultaUltimosMovimientos = crearConsultaUltimosMovimientos(
                consultaUltimosMovimientosView);
        AbstractCuenta cuenta = consultaUltimosMovimientos.getCuenta();

        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> valoresCreditoRespuesta = null;
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> valoresDebitoRespuesta = null;

        valoresCreditoRespuesta = getValores(cuenta, TipoOperacionMovimiento.CREDITO);
        valoresDebitoRespuesta = getValores(cuenta, TipoOperacionMovimiento.DEBITO);
        List<DetalleMovimientoChequesYValoresEntity> valoresCreditos = null;
        List<DetalleMovimientoChequesYValoresEntity> valoresDebitos = null;
        if (EstadoRespuesta.OK.equals(valoresCreditoRespuesta.getEstadoRespuesta())) {

            valoresCreditos = valoresCreditoRespuesta.getRespuesta();
        } else {
            valoresCreditos = Collections.emptyList();
        }
        if (EstadoRespuesta.OK.equals(valoresDebitoRespuesta.getEstadoRespuesta())) {

            valoresDebitos = valoresDebitoRespuesta.getRespuesta();
        } else {
            valoresDebitos = Collections.emptyList();
        }
        // se guardan valores de consulta en sesion (sin importar resultado)
        MovimientosValoresPendientes movimientosValoresPendientes = new MovimientosValoresPendientes();
        movimientosValoresPendientes.setCuenta(consultaUltimosMovimientosView.getNumero());
        movimientosValoresPendientes.setValoresCredito(valoresCreditos);
        movimientosValoresPendientes.setValoresDebito(valoresDebitos);
        try {
            movimientosValoresPendientes.setTipoCuenta(getDescripcionCuenta(consultaUltimosMovimientosView));
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                    TipoError.ERROR_MOVIMIENTOS_VALORES_PENDIENTES,
                    CodigoMensajeConstantes.CODIGO_ERROR_MOVIMIENTOS_VALORES_PENDIENTES);
        }

        sessionMovimientosValores.setMovimientosValoresPendientes(movimientosValoresPendientes);

        // SI AMBOS SON Error, La respuesta es error
        if (EstadoRespuesta.ERROR.equals(valoresCreditoRespuesta.getEstadoRespuesta())
                && EstadoRespuesta.ERROR.equals(valoresDebitoRespuesta.getEstadoRespuesta())) {

            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_VALORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            return respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_MOVIMIENTOS_VALORES_PENDIENTES,
                    CodigoMensajeConstantes.CODIGO_ERROR_MOVIMIENTOS_VALORES_PENDIENTES);
        }

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        if (EstadoRespuesta.ERROR.equals(valoresCreditoRespuesta.getEstadoRespuesta()) != EstadoRespuesta.ERROR
                .equals(valoresDebitoRespuesta.getEstadoRespuesta())) {

            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_VALORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
            respuesta = respuestaFactory.crearRespuestaWarning(MovimientoValoresView.class, null,
                    TipoError.WARNING_MOVIMIENTOS_VALORES_PENDIENTES,
                    CodigoMensajeConstantes.WARNING_MOVIMIENTOS_VALORES_PENDIENTES, "");
        } else {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_VALORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        MovimientoValoresView movimientoValoresView = new MovimientoValoresView();
        movimientoValoresView.setValoresCredito(generarMovimientoListView(valoresCreditos));
        movimientoValoresView.setValoresDebito(generarMovimientoListView(valoresDebitos));
        movimientoValoresView.setCuenta(consultaUltimosMovimientosView.getNumero());

        respuesta.setRespuesta(movimientoValoresView);

        if (movimientoValoresView.getCantidadValoresCredito() + movimientoValoresView.getCantidadValoresDebito() > 0) {
            respuesta.setRespuestaVacia(false);
        }
        return respuesta;
    }

    /**
     * Gets the descripcion cuenta.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the descripcion cuenta
     * @throws ServiceException
     *             the service exception
     */
    private String getDescripcionCuenta(ConsultaUltimosMovimientosView consultaUltimosMovimientosView)
            throws ServiceException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        String[] numeroCuenta = consultaUltimosMovimientosView.getNumero().split("-");

        StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
        cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);

        identificacionCuenta.setNroSucursal(numeroCuenta[0]);
        identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
        Cliente cliente = sesionCliente.getCliente();
        AbstractCuenta cuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);
        return TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.web.manager.MovimientosManager#
     * getMovimientosValoresReporte(ar.com.santanderrio.obp.cuentas.web.view.
     * ConsultaUltimosMovimientosView)
     */

    /**
     * Gets the movimientos valores reporte.
     *
     * @return the movimientos valores reporte
     */
    private Respuesta<Reporte> getMovimientosValoresReporte() {
        MovimientosValoresPendientes movimientosValoresPendientes = sessionMovimientosValores
                .getMovimientosValoresPendientes();
        String tipoCuenta = movimientosValoresPendientes.getTipoCuenta();
        tipoCuenta = tipoCuenta.toUpperCase().substring(0, 1).concat(tipoCuenta.toLowerCase().substring(1));
        movimientosValoresPendientes.setTipoCuenta(tipoCuenta);
        Respuesta<Reporte> respuesta = chequesYValoresPendientesDeConfirmacionBO.obtenerReporte(
                movimientosValoresPendientes, ExcelBuilderHelper.VALORES_PENDIENTES, sesionCliente.getCliente());
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.web.manager.MovimientosManager#
     * getDetalleMovimiento()
     */
    @Override
    public Respuesta<DetalleMovimientosView> getDetalleMovimiento(EstadoDetalleMovimientoView movimientoDia) {

        // Buscar id en sesion y grabar estadistica si es del dia o no
        if (movimientoDia.getIsDelDia()) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_MOVIMIENTO_DIA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DETALLE_MOVIMIENTO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        AbstractCuenta cuenta = sessionMovimientos.getFiltro().getCuenta();
        DetalleMovimientosView detalleMovimientosView = new DetalleMovimientosView();
        String nroSucursalProducto = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
        String nroCuentaProducto = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
        String nroCuentaCompleto = nroSucursalProducto + "-" + nroCuentaProducto;
        detalleMovimientosView.setNumeroCuenta(nroCuentaCompleto);

        String descripcionTipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda();
        detalleMovimientosView.setTipoCuenta(descripcionTipoCuenta);
        detalleMovimientosView.setAliasCuenta(cuenta.getAlias());
        if (movimientoDia.getIsRechazado()) {
            detalleMovimientosView.setMensajeInformativo(ultimosMovimientosBO
                    .obtenerMensajeInformativo(cuenta.getNroSucursal(), cuenta.getNroCuentaProducto()));
        }
        return respuestaFactory.crearRespuestaOk(detalleMovimientosView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.cuentas.web.manager.MovimientosManager#
     * getMovimientosPendientesDeConfirmacionReporte(ar.com.santanderrio.obp.
     * cuentas.web.view.ConsultaUltimosMovimientosView)
     */

    /**
     * Gets the movimientos pendientes de confirmacion reporte.
     *
     * @return the movimientos pendientes de confirmacion reporte
     */
    private Respuesta<Reporte> getMovimientosPendientesDeConfirmacionReporte() {

        MovimientosPendientesDeConfirmacion movimientoDeCuenta = new MovimientosPendientesDeConfirmacion();
        String tipoCuenta = sessionMovimientosPendientesDeConfirmacion.getTipoCuenta();
        tipoCuenta = tipoCuenta.toUpperCase().substring(0, 1).concat(tipoCuenta.toLowerCase().substring(1));
        movimientoDeCuenta.setTipoCuenta(tipoCuenta);
        movimientoDeCuenta.setCuenta(sessionMovimientosPendientesDeConfirmacion.getCuenta());
        movimientoDeCuenta.setMovimientosPendientesDeConfirmacion(
                sessionMovimientosPendientesDeConfirmacion.getMovimientoDeCuenta());
        Respuesta<Reporte> respuesta = chequesYValoresPendientesDeConfirmacionBO.obtenerReporte(movimientoDeCuenta,
                ExcelBuilderHelper.MOVIMIENTOS_PENDIENTES_CONFIRMACION, sesionCliente.getCliente());
        return respuesta;
    }

    /**
     * Gets the ultimos movimientos reporte.
     *
     * @return the ultimos movimientos reporte
     */
    private Respuesta<Reporte> getUltimosMovimientosReporte() {
        try {
            // creamos el filtro para bajar los registro para el reporte en
            // excel
            ConsultaUltimosMovimientos filtroConsulta = generarFiltroReporteExcel();
            // metodo que se va
            Respuesta<Reporte> respuesta = ultimosMovimientosBO.exportarUltimosMovimientos(filtroConsulta,
                    sesionParametros.getRegistroSession().getDispositivo());
            if (respuesta == null || respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
                    || respuesta.getRespuesta() == null) {
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_EXPORTAR,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, VALORES_SERVICIO_ERROR_MSG);
            } else {
                estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_EXPORTAR,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }
            return respuesta;

        } catch (RuntimeException e) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_MOVS_EXPORTAR,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, VALORES_SERVICIO_ERROR_MSG);
        }
    }

    /**
	 * Actualiza la modena para dispositivos mobile en el filtro.
	 *
	 * @return the consulta ultimos movimientos
	 */
    private ConsultaUltimosMovimientos generarFiltroReporteExcel() {
        ConsultaUltimosMovimientos filtro = sessionMovimientos.getFiltro();
        
        if (RangoFechaEnum.CINCUENTA_Y_TRES_DIAS.equals(sessionMovimientos.getFiltro().getRangoFecha())) {
        	filtro.setRangoFecha(RangoFechaEnum.SESENTA_DIAS);
        }
        
        if (filtro.getCuenta().isCuentaUnica()) {
            filtro.setTipoCuenta(TipoCuenta.CUENTA_UNICA);
        }
        return filtro;
    }

    /**
     * Gets the valores.
     *
     * @param cuenta
     *            the cuenta
     * @param tipoOperacion
     *            the tipo operacion
     * @return the valores
     */
    private Respuesta<List<DetalleMovimientoChequesYValoresEntity>> getValores(AbstractCuenta cuenta,
            TipoOperacionMovimiento tipoOperacion) {
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuesta = null;
        if (TipoOperacionMovimiento.CREDITO.equals(tipoOperacion)) {
            respuesta = chequesYValoresPendientesDeConfirmacionBO
                    .obtenerCreditosPendientesDeConfirmacionPorCuenta(cuenta);
        } else if (TipoOperacionMovimiento.DEBITO.equals(tipoOperacion)) {
            respuesta = chequesYValoresPendientesDeConfirmacionBO
                    .obtenerDebitosPendientesDeConfirmacionPorCuenta(cuenta);
        }
        return respuesta;
    }

    /**
     * Generar movimiento list view.
     *
     * @param detalleMovimientoList
     *            the detalle movimiento list
     * @return the list
     */
    private List<ItemMovimiento> generarMovimientoListView(
            List<DetalleMovimientoChequesYValoresEntity> detalleMovimientoList) {

        Boolean isMobile = sesionParametros.getRegistroSession().isMobile();
        ItemMovimiento item = null;
        List<ItemMovimiento> itemMovimientoList = new ArrayList<ItemMovimiento>();
        for (DetalleMovimientoChequesYValoresEntity detalleMovimiento : detalleMovimientoList) {
            item = new ItemMovimiento();
            item.setDescripcion(detalleMovimiento.getDescripcion());
            item.setDetalle(detalleMovimiento.getDescripcionAdicional());
            item.setNumeroComprobante(detalleMovimiento.getNroComprobante());
            item.setImporte(ISBANStringUtils.formatearSaldo(detalleMovimiento.getImporteMovimiento()));
            if (detalleMovimiento.getIsMovimientoEnDolares()) {
                item.setDivisa(DivisaEnum.DOLAR.getSimbolo());
            } else {
                item.setDivisa(DivisaEnum.PESO.getSimbolo());
            }
            item.setFecha(simpleDateFormatDesktop.format(detalleMovimiento.getFecha()));
            if (isMobile) {
                item.setFecha(simpleDateFormatMobile.format(detalleMovimiento.getFecha()));
            }
            item.setFechaDetalle(simpleDateFormatDesktop.format(detalleMovimiento.getFecha()));
            itemMovimientoList.add(item);
        }
        return itemMovimientoList;
    }

    /**
     * Gets the identificacion cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @return the identificacion cuenta
     */
    @Override
    public IdentificacionCuenta getIdentificacionCuenta(String cuenta) {
        String[] numeroCuenta = cuenta.split("-");
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
        cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);

        identificacionCuenta.setNroSucursal(numeroCuenta[0]);
        identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
        return identificacionCuenta;
    }

    /**
     * Grabar estadistica movimientos pendientes.
     *
     * @param codigoOkError
     *            the codigo ok error
     */
    private void grabarEstadisticaMovimientosPendientes(String codigoOkError) {
        Estadistica estadistica = new Estadistica();
        estadistica
                .setCodigoTransaccion(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_MOVS_A_CONF);
        estadistica.setCodigoError(codigoOkError);
        if (!estadisticaManager.add(estadistica)) {
            LOGGER.debug("Error grabando la estadistica de movimientos pendientes");
        }
    }

    /**
     * Parses the date.
     *
     * @param fecha
     *            the fecha
     * @return the date
     */
    private Date parseDate(String fecha) {
        Date date = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (fecha != null && !"".equalsIgnoreCase(fecha)) {
                date = formatter.parse(fecha);
            }
            return date;
        } catch (ParseException e) {
            LOGGER.error("Error grabando la estadistica de movimientos pendientes", e);
            return null;
        }
    }

    /**
     * Gets the movimientos pendientes detalle.
     *
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @return the movimientos pendientes detalle
     */
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosPendientesDetalle(String cuenta,
            Cliente cliente) {
        LOGGER.info("Inicio de getMovimientosPendientesDetalle ");
        IdentificacionCuenta identificacionCuenta = getIdentificacionCuenta(cuenta);
        AbstractCuenta abstractCuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);
        grabarEstadisticaMovimientosPendientes(abstractCuenta);

        return new Respuesta<MovimientosPendientesDeConfirmacionView>();
    }

    /**
     * Gets the movimientos valores pendientes detalle.
     *
     * @param cuenta
     *            the cuenta
     * @param cliente
     *            the cliente
     * @return the movimientos valores pendientes detalle
     */
    public Respuesta<MovimientosPendientesDeConfirmacionView> getMovimientosValoresPendientesDetalle(String cuenta,
            Cliente cliente) {
        LOGGER.info("Inicio de getMovimientosValoresPendientesDetalle ");
        IdentificacionCuenta identificacionCuenta = getIdentificacionCuenta(cuenta);
        AbstractCuenta abstractCuenta = cuentaBO.getCuentaById(identificacionCuenta, cliente);
        grabarEstadisticaMovimientosValores(abstractCuenta);

        return respuestaFactory.crearRespuestaOk(MovimientosPendientesDeConfirmacionView.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * getMovimientosPendientesReporte(java.lang.String)
     */
    @Override
    public Response getMovimientosPendientesReporte(String numeroCuenta) {
        ResponseBuilder responseBuilder = null;
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        LOGGER.info("Inicio de MovimientosController getMovimientosValoresReporte ");
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        consultaUltimosMovimientosView.setNumero(numeroCuenta);
        Respuesta<Reporte> respuestaReporte;
        respuestaReporte = getMovimientosPendientesDeConfirmacionReporte();
        if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
            ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
            responseBuilder = Response.ok((Object) resumenesView.getByteArray());
            responseBuilder.header("Content-Disposition", "attachment; filename=\"MovimientosPendientes.xls\"");
            estadisticaManager.add(
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_EXPORTACION_MOVIMIENTOS_PENDIENTES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_AL_DESCARGAR_ALGUN_RESUMEN_EN_ZIP);
            responseBuilder = Response.ok((Object) respuestaView);
            estadisticaManager.add(
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_EXPORTACION_MOVIMIENTOS_PENDIENTES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return responseBuilder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * exportarMovimientos()
     */
    @Override
    public Response exportarMovimientos() {
        ResponseBuilder responseBuilder = null;
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        LOGGER.info("Inicio de MovimientosController exportarMovimientos ");
        Respuesta<Reporte> respuestaReporte;
        respuestaReporte = getUltimosMovimientosReporte();
        if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
            ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
            respuestaView.setRespuesta(resumenesView);
            respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
            responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
            responseBuilder.header("Content-Disposition", "attachment; filename=\"UltimosMovimientos.xls\"");
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_AL_DESCARGAR_ALGUN_RESUMEN_EN_ZIP);
            responseBuilder = Response.ok((Object) respuestaView);
        }
        return responseBuilder.build();

    }
    
    @Override
    public Respuesta<ReporteView> exportarMovimientosPDF() {
        DetalleComprobanteView detalleUltimosMovimientos = ultimosMovimientosBO.exportarUltimosMovimientosPDF(
                generarFiltroReporteExcel(), sesionParametros.getRegistroSession().getDispositivo());
        Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(detalleUltimosMovimientos);
        Respuesta<ReporteView> respuestaView;
        if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
            ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
            respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE,
                    CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
        }
        return respuestaView;
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * getMovimientosValoresPendientesReporte(java.lang.String)
     */
    @Override
    public Response getMovimientosValoresPendientesReporte(String numeroCuenta) {
        ResponseBuilder responseBuilder = null;
        Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
        LOGGER.info("Inicio de MovimientosController getMovimientosValoresReporte ");
        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        consultaUltimosMovimientosView.setNumero(numeroCuenta);
        Respuesta<Reporte> respuestaReporte;
        respuestaReporte = getMovimientosValoresReporte();
        if (EstadoRespuesta.OK.equals(respuestaReporte.getEstadoRespuesta())) {
            ReporteView resumenesView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
            respuestaView.setRespuesta(resumenesView);
            respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
            responseBuilder = Response.ok((Object) respuestaView.getRespuesta().getByteArray());
            responseBuilder.header("Content-Disposition", "attachment; filename=\"MovimientosValoresPendientes.xls\"");
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_EXPORTAR_VALORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_AL_DESCARGAR_ALGUN_RESUMEN_EN_ZIP);
            responseBuilder = Response.ok((Object) respuestaView);
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_EXPORTAR_VALORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return responseBuilder.build();
    }

    /**
     * Grabar estadistica movimientos pendientes.
     *
     * @param cuenta
     *            the cuenta
     */
    private void grabarEstadisticaMovimientosPendientes(AbstractCuenta cuenta) {
        Estadistica estadistica = new Estadistica();
        LOGGER.debug("Par�metros de la consulta de �ltimos movimientos: " + cuenta);
        if (cuenta != null) {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta());
        estadistica.setCodigoTransaccion(EstadisticasConstants.getCodTipoTransacionMovsAConfFromTipoCuenta(tipoCuenta));
        if (!estadisticaManager.add(estadistica)) {
            LOGGER.debug("Error grabando estadistica movimientos pendientes.");
        }
    }

    /**
     * Grabar estadistica movimientos valores.
     *
     * @param cuenta
     *            the cuenta
     */
    private void grabarEstadisticaMovimientosValores(AbstractCuenta cuenta) {

        Estadistica estadistica = new Estadistica();
        LOGGER.debug("Par�metros de la consulta de movimientos de valores: " + cuenta);
        if (cuenta != null) {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VALORES);
        if (!estadisticaManager.add(estadistica)) {
            LOGGER.debug("Error grabando estadistica para detalle de valores.");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cuentas.web.manager.MovimientosManager#
     * obtenerMasMovimientos()
     */
    @Override
    public Respuesta<MovimientoView> obtenerMasMovimientos() {
        return consultarMovimientosConFiltroValidado(sessionMovimientos.getFiltro());
    }

}