/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenCuentaPesosView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.bo.ItemReporteDetalle;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PreFormalizacionPrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ImporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.TasaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.UvaView;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetalleCuotaPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximaCuotaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.TasaValorView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;

/**
 * The Class PrestamoManagerImpl.
 *
 * @author B039637
 */
@Component
public class PrestamoManagerImpl implements PrestamoManager {

    /** The Constant SUPER. */
    private static final String SUPER = "Super ";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoManagerImpl.class);

    /** The Constant ERROR_GRABANDO_LA_ESTADISTICA_DE_PAGO_PRESTAMO. */
    private static final String ERROR_GRABANDO_LA_ESTADISTICA_DE_PAGO_PRESTAMO = "Error grabando la estadistica de pago de prestamo.";

    /** The Constant DESCRIPCION_LOG. */
    private static final String DESCRIPCION_LOG = "Descripcion: {}.";

    /** The Constant ERROR_LOG. */
    private static final String ERROR_LOG = "Ha ocurrido un error en servicio {}. " + DESCRIPCION_LOG;

    /** The Constant PAGAR. */
    private static final String PAGAR = "PagosPrestamoManager#pagar()";

    /** The Constant OBTENER_COMPROBANTE. */
    private static final String OBTENER_COMPROBANTE = "/obtenerComprobantePrestamo";

    /** The Constant SIN_PLAZO. */
    private static final String SIN_PLAZO = "-";

    /** The Constant PRESTAMO_PERSONAL. */
    private static final String PRESTAMO_PERSONAL = "Préstamo Personal";

    /** The Constant PRESTAMO_HIPOTECARIO. */
    private static final String PRESTAMO_HIPOTECARIO = "Préstamo Hipotecario";	

    /** The Constant PRESTAMO_PRENDARIO. */
    private static final String PRESTAMO_PRENDARIO = "Préstamo Prendario";

    /** The Constant SUPER_PRESTAMO_PERSONAL. */
    private static final String SUPER_PRESTAMO_PERSONAL = "Súper Préstamo Personal";

    /** The Constant SUPER_PRESTAMO_HIPOTECARIO. */
    private static final String SUPER_PRESTAMO_HIPOTECARIO = "Súper Préstamo Hipotecario";

    /** The Constant SUPER_PRESTAMO_PRENDARIO. */
    private static final String SUPER_PRESTAMO_PRENDARIO = "Súper Préstamo Prendario";

    /** The Constant PERSONAL. */
    private static final String PERSONAL = "PERSONAL";

    /** The Constant HIPOTECARIO. */
    private static final String HIPOTECARIO = "HIPOTECARIO";

    /** The Constant PRENDARIO. */
    private static final String PRENDARIO = "PRENDARIO";

    /** The Constant PRENDARIO. */
    private static final String UVA = "UVA";
    
    /** The Constant LEGAL_SEGURO_VIDA. */
    private static final String LEGAL_SEGURO_VIDA = "1006";

    /** The Constant OTROS_IMPUESTOS. */
    private static final String OTROS_IMPUESTOS = "Otros impuestos";

    /** The Constant CARGO_SEGURO_VIDA. */
    private static final String CARGO_SEGURO_VIDA = "Cargo por seguro de vida";
    
    /** The Constant LEGAL_DIFERENCIA_SUMATORIA. */
    private static final String LEGAL_DIFERENCIA_SUMATORIA = "De existir diferencia entre la sumatoria de los rubros que se detalla y el \"Importe de la cuota\", la misma corresponde a los intereses punitorios.";

    /** The Consant DETALLE_PRESTAMO_VIEW. */
    private static final String DETALLE_PRESTAMO_VIEW = "DetallePrestamoView";
    
    /** The hora inicio operaciones. */
    @Value("${CREDITOS.HORAINICIOOPERACIONES}")
    private String horaInicioOperaciones;

    /** The hora fin operaciones. */
    @Value("${CREDITOS.HORAFINOPERACIONES}")
    private String horaFinOperaciones;

    /** The prestamo service. */
    @Autowired
    private PrestamoService prestamoService;

    /** The session parametros. */
    @Autowired
    private SesionParametros sessionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The cuenta manager. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The pre formalizacion prestamo service. */
    @Autowired
    private PreFormalizacionPrestamoService preFormalizacionPrestamoService;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The prestamo bo. */
    @Autowired
    private PrestamoBO prestamoBo;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The destino prestamo bo. */
    @Autowired
    private DestinoPrestamoBO destinoPrestamoBo;

    /** The pre formalizacion prestamo BO. */
    @Autowired
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;

    /**
     * Pagar.
     *
     * @param idProceso
     *            the id proceso
     * @return the respuesta
     */
    private Respuesta<Prestamo> pagar(String idProceso) {
        PagoPrestamo pagoPrestamo = sessionParametros.getPagoPrestamo();

        if (!idProceso.equals(pagoPrestamo.getIdProceso())) {
            // Devolver error generico? En el caso que tenga 2 pestañas
            // abiertas
            return null;
        }

        ContadorIntentos contadorReintentos = pagoPrestamo.getContadorIntentos();
        if (contadorReintentos == null) {
            ContadorIntentos contador = ContextHolder.getContext().getBean(ContadorIntentos.class);
            pagoPrestamo.setContadorIntentos(contador);
            pagoPrestamo.getContadorIntentos().reset();
        }

        PagoPendientePrestamo pagoPendientePrestamo = pagoPrestamo.getPagoPendientePrestamo();
        Prestamo prestamo = pagoPendientePrestamo.getPrestamo();

        CuentasView cuentasView = obtenerCuentasPagoPrestamo(pagoPendientePrestamo).getRespuesta();
        Respuesta<ComprobantePrestamo> resp = prestamoService.pagar(pagoPrestamo, cuentasView.getCuentas().size());
        Respuesta<Prestamo> respuesta = new Respuesta<Prestamo>();
        respuesta.setItemMensajeRespuesta(resp.getItemsMensajeRespuesta());

        respuesta = Respuesta.copy(Prestamo.class, resp);
        if (EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())
                || EstadoRespuesta.WARNING.equals(resp.getEstadoRespuesta())) {
            pagoPrestamo.setComprobantePrestamo(resp.getRespuesta());
            if (pagoPendientePrestamo.getPreFormalizacion() != null) {
                prestamo.setPlazo(pagoPendientePrestamo.getPreFormalizacion().getPlazo());
            } else {
                prestamo.setPlazo(SIN_PLAZO);
            }
            respuesta.setRespuesta(prestamo);
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO);
        } else if (evaluarTipoError(resp, TipoError.ERROR_SALDOS_INSUFICIENTES)
                || evaluarTipoError(resp, TipoError.ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS)) {
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_SALDO_INSUFICIENTE);
        } else if (evaluarTipoError(respuesta, TipoError.ERROR_PAGO_CON_ANTERIORIDAD)) {// Si
                                                                                        // hay
                                                                                        // error
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_CON_ANTERIORIDAD);
        } else {
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO);
        }

        return respuesta;
    }

    /**
     * Evaluar tipo error.
     *
     * @param <T>
     *            the generic type
     * @param respuesta
     *            the respuesta
     * @param errorSaldosInsuficientes
     *            the error saldos insuficientes
     * @return true, if successful
     */
    private <T> boolean evaluarTipoError(Respuesta<T> respuesta, TipoError errorSaldosInsuficientes) {
        for (ItemMensajeRespuesta itemMensajeRespuesta : respuesta.getItemsMensajeRespuesta()) {
            if (errorSaldosInsuficientes.equals(itemMensajeRespuesta.getTipoError())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Grabar estadistica.
     *
     * @param resultado
     *            the resultado
     * @param codigoTransaccion
     *            the codigo transaccion
     */
    private void grabarEstadistica(String resultado, String codigoTransaccion) {
        if (!estadisticaManager.add(codigoTransaccion, resultado)) {
            LOGGER.debug(ERROR_LOG, PAGAR, ERROR_GRABANDO_LA_ESTADISTICA_DE_PAGO_PRESTAMO);
        }
    }

    /**
     * Gets the pago pendiente prestamo.
     *
     * @param nroPrestamo
     *            the nro prestamo
     * @return the pago pendiente prestamo
     */
    private PagoPendientePrestamo getPagoPendientePrestamo(String nroPrestamo) {
        PagoPendientePrestamo pagoPendientePrestamo = null;
        // SE ELIMINA LA BARRA AL NUMERO DE PRESTAMO YA QUE SOLO SE COLOCA PARA
        // LA VISTA
        nroPrestamo = nroPrestamo.replaceAll("/", "");
        Respuesta<List<PagoPendiente>> pagoPendienteList = sessionParametros.getRespuestaPagosPendientes();
        if (pagoPendienteList != null && pagoPendienteList.getRespuesta() != null) {
            Iterator<PagoPendiente> respuestaIterator = pagoPendienteList.getRespuesta().iterator();
            while (respuestaIterator.hasNext()) {
                PagoPendiente pagoPendiente = respuestaIterator.next();
                if (pagoPendiente instanceof PagoPendientePrestamo) {
                    pagoPendientePrestamo = (PagoPendientePrestamo) pagoPendiente;
                    if (ISBANStringUtils.eliminarCeros(pagoPendientePrestamo.getPrestamo().getNumeroCuentaProducto())
                            .equals(ISBANStringUtils.eliminarCeros(nroPrestamo))) {
                        break;
                    }
                }
            }
        }
        return pagoPendientePrestamo;
    }

    /**
     * Obtener plazo Y cuenta asociada prestamo.
     *
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @return the respuesta
     */
    private Respuesta<PreFormalizacion> obtenerPlazoYCuentaAsociadaPrestamo(
            PagoPendientePrestamo pagoPendientePrestamo) {
        Respuesta<PreFormalizacion> preFormalizacion = new Respuesta<PreFormalizacion>();
        try {
            preFormalizacion = preFormalizacionPrestamoService
                    .obtenerPreFormalizacion(pagoPendientePrestamo.getPrestamo());
            if (EstadoRespuesta.OK.equals(preFormalizacion.getEstadoRespuesta())
                    || EstadoRespuesta.WARNING.equals(preFormalizacion.getRespuesta())) {
                // Guardar en sesion PreFormalizacion
                pagoPendientePrestamo.setPreFormalizacion(preFormalizacion.getRespuesta());
            } else {
                preFormalizacion = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DETALLE_CUOTA,
                        CodigoMensajeConstantes.CODIGO_ERROR_DETALLE_CUOTA);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            preFormalizacion = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DETALLE_CUOTA,
                    CodigoMensajeConstantes.CODIGO_ERROR_DETALLE_CUOTA);
        }
        return preFormalizacion;
    }

    /**
     * Obtener cuentas pago prestamo.
     *
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @return the respuesta
     */
    private Respuesta<CuentasView> obtenerCuentasPagoPrestamo(PagoPendientePrestamo pagoPendientePrestamo) {
        Respuesta<CuentasView> respuestaCuentas = cuentaManager.getCuentasActivas();
        // agregar itemsmensajes para las cuentras que fallaron
        if (respuestaCuentas.getRespuesta() != null) {
            ordenarCuentasPrestamo(pagoPendientePrestamo, respuestaCuentas);

            respuestaCuentas.getRespuesta().setSelected(0);
            if (respuestaCuentas.getRespuesta().getCuentas() != null
                    && sessionParametros.getPagoPrestamo().getCuentaSeleccionada() != null) {

                int i = 0;
                for (CuentasAdhesionDebitoView cuenta : respuestaCuentas.getRespuesta().getCuentas()) {

                    if (isSelectedAccount(cuenta)) {
                        respuestaCuentas.getRespuesta().setSelected(i);
                        break;
                    }
                    i++;
                }
            }
        }

        return respuestaCuentas;
    }

    /**
     * Checks if is selected account.
     *
     * @param cuenta
     *            the cuenta
     * @return true, if is selected account
     */
    private boolean isSelectedAccount(CuentasAdhesionDebitoView cuenta) {

        if (cuenta.getNumero() == null) {
            return false;
        }
        IdentificacionCuenta iden = new IdentificacionCuenta(cuenta.getNumero());
        try {
            return Integer.parseInt(iden.getNroSucursal()) == Integer
                    .parseInt(sessionParametros.getPagoPrestamo().getCuentaSeleccionada().getNroSucursal())
                    && Integer.parseInt(iden.getNroCuentaProducto()) == Integer.parseInt(
                            sessionParametros.getPagoPrestamo().getCuentaSeleccionada().getNroCuentaProducto());
        } catch (NumberFormatException e) {
            LOGGER.debug("Error al parsear Sucursal al buscar cuenta seleccionada.-", e.getMessage());
            return false;
        }
    }

    /**
     * Ordenar cuentas prestamo.
     *
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @param respuestaCuentas
     *            the respuesta cuentas
     */
    private void ordenarCuentasPrestamo(PagoPendientePrestamo pagoPendientePrestamo,
            Respuesta<CuentasView> respuestaCuentas) {

        PreFormalizacion preformalizacion = pagoPendientePrestamo.getPreFormalizacion();
        List<CuentasAdhesionDebitoView> cuentasActivas = respuestaCuentas.getRespuesta().getCuentas();
        List<CuentasAdhesionDebitoView> cuentasTemp = new ArrayList<CuentasAdhesionDebitoView>();
        List<CuentasAdhesionDebitoView> cuentasRemover = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaAdherida = null;
        if (preformalizacion != null) {
            // busca la cuenta adherida en la lista y lo elimina de la misma
            for (CuentasAdhesionDebitoView cuenta : cuentasActivas) {
                IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(cuenta.getNumero());
                if ((identificacionCuenta.getNroCuentaProducto()
                        .equals(preformalizacion.getPrestamoDebitoAdherido().getNumero()))
                        && (identificacionCuenta.getNroSucursal()
                                .equals(preformalizacion.getPrestamoDebitoAdherido().getNroSucursal()))) {
                    cuentaAdherida = cuenta;
                    cuentasRemover.add(cuenta);
                }
                if (!cuenta.getShowSaldoPesos()) {
                    cuentasRemover.add(cuenta);
                }
            }
        }

        // agrega la cuenta adherida encontrada y la agrega como primer elemento
        // de la lista que se enviara
        if (cuentaAdherida != null) {
            cuentasTemp.add(cuentaAdherida);
        }

        cuentasActivas.removeAll(cuentasRemover);

        cuentaManager.agregarSaldoErrorneoEnCuentasActivas(respuestaCuentas, cuentasTemp);
    }

    /**
     * Obtener comprobante prestamo.
     *
     * @param numeroPrestamo
     *            the numero prestamo
     * @return the respuesta
     */
    private Respuesta<ReporteView> obtenerComprobantePrestamo(String numeroPrestamo) {

        Respuesta<ReporteView> respuestaView;
        try {
            PagoPrestamo pagoPrestamo = sessionParametros.getPagoPrestamo();
            PagoPendientePrestamo pagoPendientePrestamo = pagoPrestamo.getPagoPendientePrestamo();
            ComprobantePrestamo comprobantePrestamo = pagoPrestamo.getComprobantePrestamo();
            Respuesta<Reporte> respuesta = prestamoService.obtenerComprobantePrestamo(pagoPendientePrestamo,
                    comprobantePrestamo);

            respuestaView = Respuesta.copy(ReporteView.class, respuesta);
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
                    || EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
                ReporteView resumenesMensualesView = ReporteView.fromReporte(respuesta.getRespuesta());
                respuestaView.setRespuesta(resumenesMensualesView);
                grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
                        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_PAGO_CUOTA);
            } else {
                grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_PAGO_CUOTA);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            respuestaView = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_COMPROBANTE_PRESTAMO,
                    CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_PRESTAMO);
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_PAGO_CUOTA);

        }
        return respuestaView;
    }

    /**
     * Guardar pago prestamo.
     *
     * @param nroCuenta
     *            the nro cuenta
     * @param pagoPrestamo
     *            the pago prestamo
     */
    private void guardarPagoPrestamo(String nroCuenta, PagoPrestamo pagoPrestamo) {
        AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(nroCuenta);
        pagoPrestamo.setCuentaSeleccionada(cuenta);
        sessionParametros.setPagoPrestamo(pagoPrestamo);
    }

    /**
     * Obtener detalle prestamo view.
     *
     * @param prestamo
     *            the prestamo
     * @param preFormalizacion
     *            the pre formalizacion
     * @param detallePrestamoView
     *            the detalle prestamo view
     * @param isUva 
     */
    private void obtenerDetallePrestamoView(Prestamo prestamo, PreFormalizacion preFormalizacion,
            DetallePrestamoView detallePrestamoView, Boolean isUva) {

    	if (TipoPrestamoEnum.UVA.equals(prestamo.getTipoPrestamoEnum()) && StringUtils.isNotBlank(prestamo.getNroExp())) {
	        detallePrestamoView.setTipoPrestamoLabel(TipoPrestamoEnum.UVA.getDescripcion());
	        detallePrestamoView.setTipoPrestamo(TipoPrestamoEnum.UVA.name());
    	}
    	else {
    		detallePrestamoView.setTipoPrestamoLabel(prestamo.getClaseCuenta());
	        detallePrestamoView.setTipoPrestamo(prestamo.getTipoPrestamoEnum().name());
    	}
        detallePrestamoView.setNumeroPrestamo(ISBANStringUtils
                .formatearNroPrestamo(prestamo.getCuenta().getNroSucursal(), prestamo.getNumeroCuentaProducto()));
        detallePrestamoView.setAliasPrestamo(null);
        if (preFormalizacion != null) {
            detallePrestamoView.setNumeroCuenta(ISBANStringUtils
                    .formatearSucursal(preFormalizacion.getPrestamoDebitoAdherido().getNroSucursal()) + "-"
                    + ISBANStringUtils.formatearNumeroCuenta(preFormalizacion.getPrestamoDebitoAdherido().getNumero()));
            detallePrestamoView.setPlazoPrestamo(preFormalizacion.getPlazo());
            detallePrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_2));

            AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(detallePrestamoView.getNumeroCuenta());

            if (cuenta != null) {
                detallePrestamoView.setAliasCuenta(cuenta.getAlias());
                detallePrestamoView
                        .setTipoCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda());
            }
        } else {
            setearCuentaVacia(detallePrestamoView);
            detallePrestamoView.setPlazoPrestamo(SIN_PLAZO);

        }
//        detallePrestamoView.agregarAclaracionReferencia(LEGAL_DIFERENCIA_SUMATORIA);
        detallePrestamoView.setCuotaPrestamo(ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo()));

        if (prestamo.getDivisa().equals(DivisaEnum.PESO)) {
            detallePrestamoView.setDivisa(DivisaEnum.PESO.getSimbolo());
        } else {
            detallePrestamoView.setDivisa(DivisaEnum.DOLAR.getSimbolo());
        }
        detallePrestamoView
                .setSaldoAnteriorCapitalSinAjustar(ISBANStringUtils.formatearSaldoConSigno(prestamo.getSaldoPrevio()));
        if (prestamo.getIndex().trim().isEmpty()) {
            detallePrestamoView.setShowCoeficienteIndexacionReciboEnTermino(false);
        } else {
            detallePrestamoView.setCoeficienteIndexacionReciboEnTermino(
                    prestamo.getIndex().trim() + " " + formatearCoefIndexacion(prestamo.getFactorIndex()));
            detallePrestamoView.setShowCoeficienteIndexacionReciboEnTermino(true);
        }
        
//        ID 7326 - Se pide eliminar el capital ajustado
//        if (!isZero(prestamo.getImporteAjussal())) {
//        	detallePrestamoView.setCapitalAjustado(ISBANStringUtils.formatearSaldo(prestamo.getImporteAjussal()));
//        	detallePrestamoView.setShowCapitalAjustado(true);
//        }
        
        detallePrestamoView.setShowCapitalAjustado(false);
        BigDecimal capitalAjustadoSobreLaCuota;
        BigDecimal saldoPrevio = prestamo.getSaldoPrevio();
        BigDecimal importeAjussal = prestamo.getImporteAjussal();
        capitalAjustadoSobreLaCuota = saldoPrevio.add(importeAjussal);

        detallePrestamoView
                .setCapitalAjustodoSobreLaCuota(ISBANStringUtils.formatearSaldoConSigno(capitalAjustadoSobreLaCuota));
        detallePrestamoView.setShowCapitalAjustodoSobreLaCuota(!isZero(importeAjussal));

        detallePrestamoView.setFechaDeVencimiento(ISBANStringUtils.formatearFecha(prestamo.getVencimientoCuota()));
        detallePrestamoView.setImporteValue(ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteTotalCuota()));
        
        List<TasaView> tasaViewList;
        armarImporteViewList(prestamo, detallePrestamoView, isUva);
        tasaViewList = armarTasaViewList(prestamo);
        detallePrestamoView.setTasas(tasaViewList);

        // ******** Datos de UVAs ******** //
        if (StringUtils.isNotBlank(prestamo.getNroExp())) {
            List<UvaView> uvaViewList;
            uvaViewList = armarUvaViewList(prestamo);
            detallePrestamoView.setUvas(uvaViewList);
        }

    }

    /**
     * Sets the ear cuenta vacia.
     *
     * @param detallePrestamoView
     *            the new ear cuenta vacia
     */
    private void setearCuentaVacia(DetallePrestamoView detallePrestamoView) {

        detallePrestamoView.setAliasCuenta(null);
        detallePrestamoView.setTipoCuenta(null);
        detallePrestamoView.setNumeroCuenta("-");
    }

    /**
     * Formatear coef indexacion.
     *
     * @param factorIndex
     *            the factor index
     * @return the string
     */
    private String formatearCoefIndexacion(String factorIndex) {
        BigDecimal coef = ISBANStringUtils.stringToBigDecimal(factorIndex, 3, 6, false);
        return ISBANStringUtils.formatearSaldoConSigno(coef);
    }

    /**
     * Armar tasa view list.
     *
     * @param prestamo
     *            the prestamo
     * @return the list
     */
    private List<TasaView> armarTasaViewList(Prestamo prestamo) {
        List<TasaView> tasaViewList = new ArrayList<TasaView>();
        TasaView tasaView = new TasaView();
        if (!isZero(prestamo.getTasaPrestamo())) {
            agregarTasa("Tasa Nominal Anual (T.N.A)", ISBANStringUtils.formatearSaldoConSigno(prestamo.getTasaPrestamo()),
                    tasaViewList, tasaView);
        }
        if (!isZero(prestamo.getTasaAnualEfectiva())) {
            agregarTasa("Tasa Efectiva Anual (T.E.A)", ISBANStringUtils.formatearSaldoConSigno(prestamo.getTasaAnualEfectiva()),
                    tasaViewList, tasaView);
        }
        if (!isZero(prestamo.getCostoFinancieroTotal())) {
            agregarTasa("Costo Financiero Total Efectivo Anual (Con Impuestos)",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getCostoFinancieroTotal()), tasaViewList,
                    tasaView);
        }
        if (!isZero(prestamo.getCostoFinancieroTotalSinImpuestos())) {
            agregarTasa("Costo Financiero Total Efectivo Anual (Sin Impuestos)",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getCostoFinancieroTotalSinImpuestos()),
                    tasaViewList, tasaView);
        }
        return tasaViewList;
    }

    /**
     * Agregar tasa.
     *
     * @param label
     *            the label
     * @param tasa
     *            the tasa
     * @param tasaViewList
     *            the tasa view list
     * @param tasaView
     *            the tasa view
     */
    private void agregarTasa(String label, String tasa, List<TasaView> tasaViewList, TasaView tasaView) {
        tasaView = new TasaView();
        tasaView.setLabel(label);
        tasaView.setTasa(tasa);
        tasaViewList.add(tasaView);
    }

    /**
     * Armar uva view list.
     *
     * @param prestamo
     *            the prestamo
     * @return the list
     */
    public List<UvaView> armarUvaViewList(Prestamo prestamo) {
        List<UvaView> uvaViewList = new ArrayList<UvaView>();
        UvaView uvaView = new UvaView();
        if (!isZero(prestamo.getCotizCambio())) {
            agregarUva("Cotización de UVAs", "$ " + ISBANStringUtils.formatearSaldo(prestamo.getCotizCambio()), uvaViewList,
                    uvaView);
        }
        if (!(prestamo.getFecCotiz().equals(null))) {
            agregarUva("Fecha de cotización de UVA", ISBANStringUtils.formatearFecha(prestamo.getFecCotiz()), uvaViewList,
                    uvaView);
        }
//        if (!isZero(prestamo.getAjusteSaldo())) {
//            agregarUva("Capital ajustado", ISBANStringUtils.formatearSaldo(prestamo.getImporteAjussal()),
//                    uvaViewList, uvaView);
//        }
        if (!isZero(prestamo.getImporteTotal())) {
            agregarUva("Importe de la cuota pura en UVAs", "$ " + ISBANStringUtils.formatearSaldo(prestamo.getImporteTotal()), 
            		uvaViewList, uvaView);
        }
        if (!isZero(prestamo.getCapitalPend())) {
            agregarUva("Capital en UVAs", ISBANStringUtils.formatearSaldo(prestamo.getCapitalPend()), uvaViewList,
                    uvaView);
        }
        if (!isZero(prestamo.getInteresesPend())) {
            agregarUva("Interés en UVAs", ISBANStringUtils.formatearSaldo(prestamo.getInteresesPend()), uvaViewList,
                    uvaView);
        }
        return uvaViewList;
    }

    /**
     * Agregar tasa.
     *
     * @param label
     *            the label
     * @param uva
     *            the uva
     * @param uvaViewList
     *            the uva view list
     * @param uvaView
     *            the uva view
     */
    private void agregarUva(String label, String uva, List<UvaView> uvaViewList, UvaView uvaView) {
        uvaView = new UvaView();
        uvaView.setLabel(label);
        uvaView.setUva(uva);
        uvaViewList.add(uvaView);
    }

    /**
     * Armar importe view list.
     *
     * @param prestamo
     *            the prestamo
     * @param detallePrestamoView
     *            the detalle prestamo view
     * @param isUva 
     * @return the list
     */
    private void armarImporteViewList(Prestamo prestamo, DetallePrestamoView detallePrestamoView, Boolean isUva) {
        List<ImporteView> importeViewList = new ArrayList<ImporteView>();
        ImporteView importeView = new ImporteView();
        if (!isZero(prestamo.getImporteAmortizacion())) {
            agregarImporte("Capital", ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteAmortizacion()),
                    importeViewList, importeView);
        }
        if (!isZero(prestamo.getImporteIntereses())) {
            agregarImporte("Intereses compensatorios del periodo",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteIntereses()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getImporteAjusteCapmor())) {
            agregarImporte("Ajuste de capital en mora",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteAjusteCapmor()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getImportesPunitorios())) {
            agregarImporte("Intereses punitorios",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImportesPunitorios()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getInteresCompensatorioPendiente())) {
            agregarImporte("Intereses compensatorios posteriores al vto.",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getInteresCompensatorioPendiente()),
                    importeViewList, importeView);
        }
        if (!isZero(prestamo.getImporteSeguroVida())) {
            detallePrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(LEGAL_SEGURO_VIDA));
            agregarImporte(CARGO_SEGURO_VIDA + " (" + detallePrestamoView.getAclaracionReferencias().size() + ")",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteSeguroVida()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getImporteSeguroDelBien())) {
            agregarImporte("Seguro del bien",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteSeguroDelBien()), importeViewList,
                    importeView);
        }

        agregadoImportesPrestamo(prestamo, importeView, importeViewList, detallePrestamoView, isUva);
        detallePrestamoView.setImportes(importeViewList);
    }

    /**
     * Agregado importes prestamo.
     *
     * @param prestamo
     *            the prestamo
     * @param importeView
     *            the importe view
     * @param importeViewList
     *            the importe view list
     * @param detallePrestamoView
     *            the detalle prestamo view
     * @param isUva 
     */
    private void agregadoImportesPrestamo(Prestamo prestamo, ImporteView importeView, List<ImporteView> importeViewList,
            DetallePrestamoView detallePrestamoView, Boolean isUva) {
        if (!isZero(prestamo.getImporteTotalSeguros())) {
            BigDecimal resultado = prestamo.getImporteTotalSeguros().subtract(prestamo.getImporteSeguroDelBien())
                    .subtract(prestamo.getImporteSeguroVida());
            if (isValorMinimoSeguros(resultado)) {
                agregarImporte("Otros seguros", ISBANStringUtils.formatearSaldoConSigno(resultado), importeViewList,
                        importeView);
            }
        }
        if (!isZero(prestamo.getImporteIVA())) {
            agregarImporte("IVA Tasa general", ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteIVA()),
                    importeViewList, importeView);
        }
        if (!isZero(prestamo.getImporteIVAAdicional())) {
            agregarImporte("IVA Adicional", ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteIVAAdicional()),
                    importeViewList, importeView);
        }
        if (!isZero(prestamo.getImporteEndeudamiento())) {
            agregarImporte("Impuesto al endeudamiento",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteEndeudamiento()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getIngresosBrutos())) {
            agregarImporte("Percepcion ingresos brutos",
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getIngresosBrutos()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getOtrosImpuestos())) {
        	detallePrestamoView.setTieneOtrosImpuestos(Boolean.TRUE);
        	/*if(isUva) {
        		detallePrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3).replace("(4)", ""));
        	}else {*/
        		detallePrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3));
        	/*}*/
            String referencia = " (" + detallePrestamoView.getAclaracionReferencias().size() + ")";
            agregarImporte(OTROS_IMPUESTOS + referencia,
                    ISBANStringUtils.formatearSaldoConSigno(prestamo.getOtrosImpuestos()), importeViewList,
                    importeView);
        }
        if (!isZero(prestamo.getGastosPendientes())) {
            agregarImporte("Gastos anexos", ISBANStringUtils.formatearSaldoConSigno(prestamo.getGastosPendientes()),
                    importeViewList, importeView);
        }
        if (!isZero(prestamo.getComisionesPendientes())) {
            agregarImporte("Comisiones", ISBANStringUtils.formatearSaldoConSigno(prestamo.getComisionesPendientes()),
                    importeViewList, importeView);
        }
    }

    /**
     * Agregar importe.
     *
     * @param label
     *            the label
     * @param importe
     *            the importe
     * @param importeViewList
     *            the importe view list
     * @param importeView
     *            the importe view
     */
    private void agregarImporte(String label, String importe, List<ImporteView> importeViewList,
            ImporteView importeView) {
        importeView = new ImporteView();
        importeView.setLabel(label);
        importeView.setImporte(importe);
        importeViewList.add(importeView);
    }

    /**
     * Checks if is zero.
     *
     * @param value
     *            the value
     * @return true, if is zero
     */
    private boolean isZero(BigDecimal value) {

        if (value != null) {
            int isZero = BigDecimal.ZERO.compareTo(value);
            return isZero == 0;
        }
        return true;

    }

    /**
     * Funcion que evalua el valor 0.009 si el valor es mayor a eso se muestra
     * sino no.
     *
     * @param value
     *            the value
     * @return true, if is valor minimo seguros
     */
    private boolean isValorMinimoSeguros(BigDecimal value) {
        BigDecimal valorMinimo = new BigDecimal("0.009");
        Boolean isMenosValorMinimo = true;
        if (value != null) {
            if (valorMinimo.compareTo(value) >= 0) {
                isMenosValorMinimo = false;
            }
        }
        return isMenosValorMinimo;
    }

    /**
     * Obtener reporte detalle prestamo.
     *
     * @param numeroPrestamo
     *            the numero prestamo
     * @param isUva 
     * @return the respuesta
     */
    private Respuesta<ReporteView> obtenerReporteDetallePrestamo(String numeroPrestamo, Boolean isUva) {

        Respuesta<Reporte> respuesta;
        Respuesta<PreFormalizacion> respuestaPreFormalizacion;
        Respuesta<ReporteView> respuestaView;
        try {
            PagoPendientePrestamo pagoPendientePrestamo = getPagoPendientePrestamo(numeroPrestamo);
            respuestaPreFormalizacion = preFormalizacionPrestamoService
                    .obtenerPreFormalizacion(pagoPendientePrestamo.getPrestamo());
            PreFormalizacion preFormalizacion = respuestaPreFormalizacion.getRespuesta();
            DetallePrestamoView detallePrestamoView = new DetallePrestamoView();
            obtenerDetallePrestamoView(pagoPendientePrestamo.getPrestamo(), preFormalizacion, detallePrestamoView, isUva);

            ComprobantePrestamoReporte comprobantePrestamoReporte = armarComprobanteDetallePrestamo(
                    detallePrestamoView);

            respuesta = prestamoService.obtenerReporteDetallePrestamo(comprobantePrestamoReporte);

            respuestaView = Respuesta.copy(ReporteView.class, respuesta);
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())
                    || EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
                ReporteView resumenesMensualesView = ReporteView.fromReporte(respuesta.getRespuesta());
                respuestaView.setRespuesta(resumenesMensualesView);
                grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
                        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_DETALLE);
            } else {
                grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                        EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_DETALLE);
            }

            return respuestaView;
        } catch (ServiceException e) {
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGA_COMPROBANTE_DETALLE);
            LOGGER.error("Error de transaccion al descargar comprobante", e);
            return respuestaFactory.crearRespuestaError(ReporteView.class,
                    "Error de transaccion al descargar comprobante", null);
        }
    }

    /**
     * Armar comprobante detalle prestamo.
     *
     * @param view
     *            the view
     * @return the comprobante prestamo reporte
     */
    private ComprobantePrestamoReporte armarComprobanteDetallePrestamo(DetallePrestamoView view) {

        TipoPrestamoEnum tipo = TipoPrestamoEnum.fromString(view.getTipoPrestamo());

        ComprobantePrestamoReporte comprobantePrestamoReporte = new ComprobantePrestamoReporte();
        if (StringUtils.isNotBlank(view.getAliasCuenta())) {
            comprobantePrestamoReporte.setAliasCuentaDebito("Cuenta \"" + view.getAliasCuenta() + "\"");
        }
        comprobantePrestamoReporte.setCuentaDebito(view.getNumeroCuenta());
        comprobantePrestamoReporte.setCuotaPrestamo(view.getCuotaPrestamo());
        comprobantePrestamoReporte.setDescripcionPrestamo(SUPER + tipo.getDescripcion());
        comprobantePrestamoReporte.setValorDescripcionPrestamo(view.getNumeroPrestamo());

        comprobantePrestamoReporte.setFechaVencimiento(view.getFechaDeVencimiento());
        comprobantePrestamoReporte.setImporteCuota(view.getDivisa() + " " + view.getImporteValue());
        comprobantePrestamoReporte.setImportes(convertirListaImportes(view.getDivisa(), view.getImportes()));
        comprobantePrestamoReporte.setPlazoPrestamo(view.getPlazoPrestamo());
        comprobantePrestamoReporte.setTasas(convertirListaTasas(view.getTasas()));
        comprobantePrestamoReporte.setTipoPrestamo(tipo);
        comprobantePrestamoReporte.setCuentaDebito(view.getNumeroCuenta());
        comprobantePrestamoReporte.setTipoCuenta(view.getTipoCuenta());
        comprobantePrestamoReporte.setImporteCuotaPrincipal(view.getDivisa() + " " + view.getImporteValue());

        return comprobantePrestamoReporte;

    }

    /**
     * Convertir lista importes.
     *
     * @param divisa
     *            the divisa
     * @param importes
     *            the importes
     * @return the list
     */
    private List<ItemReporteDetalle> convertirListaImportes(String divisa, List<ImporteView> importes) {
        List<ItemReporteDetalle> listaConvertida = new ArrayList<ItemReporteDetalle>();
        if (importes.isEmpty()) {
            return null;
        } else {
            for (ImporteView importe : importes) {
                ItemReporteDetalle itemReporteDetalle = new ItemReporteDetalle();
                itemReporteDetalle.setLabel(importe.getLabel());
                itemReporteDetalle.setValue(divisa + " " + importe.getImporte());
                listaConvertida.add(itemReporteDetalle);
            }
        }
        return listaConvertida;
    }

    /**
     * Convertir lista tasas.
     *
     * @param tasas
     *            the tasas
     * @return the list
     */
    private List<ItemReporteDetalle> convertirListaTasas(List<TasaView> tasas) {
        List<ItemReporteDetalle> listaConvertida = new ArrayList<ItemReporteDetalle>();
        if (tasas.isEmpty()) {
            return null;
        } else {
            for (TasaView tasa : tasas) {
                ItemReporteDetalle itemReporteDetalle = new ItemReporteDetalle();
                itemReporteDetalle.setLabel(tasa.getLabel());
                itemReporteDetalle.setValue(tasa.getTasa() + "%");
                listaConvertida.add(itemReporteDetalle);
            }
        }
        return listaConvertida;
    }

    /**
     * Obtener mensaje pago ok.
     *
     * @param respuesta
     *            the respuesta
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the string
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.pagos.web.manager.PrestamoManager#
     * obtenerMensajePagoOk(ar.com.santanderrio.obp.pagos.entities.Prestamo)
     */
    private String obtenerMensajePagoOk(Prestamo respuesta, ComprobantePrestamo comprobantePrestamo) {
        return prestamoService.obtenerMensajePagoOk(respuesta, comprobantePrestamo);
    }

    /**
     * Builds the comprobante view.
     *
     * @param pagoPrestamo
     *            the pago prestamo
     * @return the comprobante view
     */
    public ComprobanteView buildComprobanteView(PagoPrestamo pagoPrestamo) {
        ComprobanteView comprobante = new ComprobanteView();

        ComprobantePrestamo comprobantePrestamo = pagoPrestamo.getComprobantePrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = pagoPrestamo.getPagoPendientePrestamo();
        Prestamo prestamo = pagoPendientePrestamo.getPrestamo();

        List<ImporteView> importeViewList = armarImporteViewList(comprobantePrestamo);
        List<TasaView> tasaViewList = armarTasaViewList(comprobantePrestamo);

        Interviniente interviniente = comprobantePrestamo.getInterviniente();
        comprobante.setTipoPrestamo(prestamo.getTipoPrestamoEnum().name());
        comprobante.setTipoPrestamoLabel(prestamo.getTipoPrestamoEnum().getDescripcion());
        comprobante.setNumeroPrestamo(ISBANStringUtils.formatearNroPrestamo(prestamo.getCuenta().getNroSucursal(),
                prestamo.getNumeroCuentaProducto()));
        comprobante.setTitular(StringUtils
                .capitalize(StringUtils.lowerCase(interviniente.getNombre() + " " + interviniente.getApellido())));
        if (interviniente.getTipoInscripcion() != null && !"".equals(interviniente.getTipoInscripcion())) {
            comprobante.setTipoInscripcion(
                    TipoIdentificacion.fromKey(interviniente.getTipoInscripcion()).getDescripcion());
        }
        comprobante.setCondicionIVA(
                StringUtils.capitalize(StringUtils.lowerCase(comprobantePrestamo.getDescripcionIVA())));
        comprobante.setNroCuentaAsociada(prestamo.getCuentaRelacionada());
        comprobante.setCuotaPrestamo(String.valueOf(Long.parseLong(prestamo.getNumeroRecibo())));

        if (prestamo.getDivisa().equals(DivisaEnum.PESO)) {
            comprobante.setDivisa(DivisaEnum.PESO.getSimbolo());
        } else {
            comprobante.setDivisa(DivisaEnum.DOLAR.getSimbolo());
        }

        if (pagoPendientePrestamo.getPreFormalizacion() != null) {
            comprobante.setPlazoPrestamo(
                    String.valueOf(Long.parseLong(pagoPendientePrestamo.getPreFormalizacion().getPlazo())));
        } else {
            comprobante.setPlazoPrestamo(SIN_PLAZO);
        }

        comprobante
                .setSaldoAnteriorCapitalSinAjustar(ISBANStringUtils.formatearSaldoConSigno(prestamo.getSaldoPrevio()));
        comprobante
                .setImporteValue(ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteTotalRecibo()));
        
        if (!isZero(prestamo.getImporteAjussal()) && !StringUtils.isNotBlank(prestamo.getNroExp())) {
        	comprobante.setCapitalAjustado(ISBANStringUtils.formatearSaldo(prestamo.getImporteAjussal()));
        	comprobante.setShowCapitalAjustado(true);
        }
        else {
        	comprobante.setShowCapitalAjustado(false);
        }
        comprobante.setImportes(importeViewList);

        Cuenta cuenta = comprobantePrestamo.getCuenta();

        comprobante.setNroCuenta(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
                + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
        comprobante.setTipoCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda());

        comprobante.setTasas(tasaViewList);
        Date fechaDate = ISBANStringUtils.formatearFecha(comprobantePrestamo.getVencimientoRecibo(), "yyyy-MM-dd");
        comprobante.setFechaDeVencimiento(ISBANStringUtils.formatearFecha(fechaDate));
        String fechaHoy = ISBANStringUtils.formatearFecha(new Date(), "dd/MM/yyyy HH:mm");
        comprobante.setFechaHoraComprobante(fechaHoy);
        comprobante.setNroComprobante(comprobantePrestamo.getNio());

        if (BigDecimal.ZERO.compareTo(comprobantePrestamo.getSaldoCapital()) != 0) {
            comprobante.setSaldoDeudaCapital(
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getSaldoCapital()));
        }

        // ******** Datos de UVAs ******** //
        if (StringUtils.isNotBlank(prestamo.getNroExp())) {
            List<UvaView> uvaViewList;
            uvaViewList = armarUvaViewList(prestamo);
            comprobante.setUvas(uvaViewList);
        }

        return comprobante;
    }

    /**
     * Armar importe view list.
     *
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the list
     */
    private List<ImporteView> armarImporteViewList(ComprobantePrestamo comprobantePrestamo) {
        List<ImporteView> importeViewList = new ArrayList<ImporteView>();
        ImporteView importeView = new ImporteView();
        if (!isZero(comprobantePrestamo.getImporteAmortizacion())) {
            agregarImporte("Capital",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteAmortizacion()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getImporteIntereses())) {
            agregarImporte("Intereses compensatorios del periodo",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteIntereses()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getImpAjusCapmor())) {
            agregarImporte("Ajuste de capital en mora",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImpAjusCapmor()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getImportePunitorios())) {
            agregarImporte("Intereses punitorios",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImportePunitorios()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getImporteComplementario())) {
            agregarImporte("Intereses compensatorios posteriores al vto.",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteComplementario()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getImporteSeguroVida())) {
            agregarImporte(CARGO_SEGURO_VIDA,
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteSeguroVida()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getImporteSeguroBien())) {
            agregarImporte("Seguro del bien",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteSeguroBien()),
                    importeViewList, importeView);
        }
        agregadoImportesComprobantePrestamo(comprobantePrestamo, importeView, importeViewList);

        return importeViewList;
    }

    /**
     * Agregado importes comprobante prestamo.
     *
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @param importeView
     *            the importe view
     * @param importeViewList
     *            the importe view list
     */
    private void agregadoImportesComprobantePrestamo(ComprobantePrestamo comprobantePrestamo, ImporteView importeView,
            List<ImporteView> importeViewList) {
        if (!isZero(comprobantePrestamo.getImporteTotalSeguros())) {
            BigDecimal resultado = comprobantePrestamo.getImporteTotalSeguros()
                    .subtract(comprobantePrestamo.getImporteSeguroBien())
                    .subtract(comprobantePrestamo.getImporteSeguroVida());
            if (isValorMinimoSeguros(resultado)) {
                agregarImporte("Otros seguros", ISBANStringUtils.formatearSaldoConSigno(resultado), importeViewList,
                        importeView);
            }
        }
        if (!isZero(comprobantePrestamo.getImporteIVA())) {
            agregarImporte("IVA Tasa general",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteIVA()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getImporteIVAAdicional())) {
            agregarImporte("IVA adicional",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteIVAAdicional()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getImpuestoEndFinal())) {
            agregarImporte("Impuesto al endeudamiento",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImpuestoEndFinal()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getIngresosBrutos())) {
            agregarImporte("Percepcion ingresos brutos",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getIngresosBrutos()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getOtrosImpuestos())) {
            agregarImporte(OTROS_IMPUESTOS,
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getOtrosImpuestos()), importeViewList,
                    importeView);
        }
        if (!isZero(comprobantePrestamo.getGastos())) {
            agregarImporte("Gastos anexos", ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getGastos()),
                    importeViewList, importeView);
        }
        if (!isZero(comprobantePrestamo.getComisiones())) {
            agregarImporte("Comisiones", ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getComisiones()),
                    importeViewList, importeView);
        }
    }

    /**
     * Armar tasa view list.
     *
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the list
     */
    private List<TasaView> armarTasaViewList(ComprobantePrestamo comprobantePrestamo) {
        List<TasaView> tasaViewList = new ArrayList<TasaView>();
        TasaView tasaView = new TasaView();
        if (!isZero(comprobantePrestamo.getTasaPrestamo())) {
            agregarTasa("Tasa Nominal Anual (T.N.A)",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getTasaPrestamo()), tasaViewList,
                    tasaView);
        }
        if (!isZero(comprobantePrestamo.getTea())) {
            agregarTasa("Tasa Efectiva Anual (T.E.A)", ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getTea()),
                    tasaViewList, tasaView);
        }
        if (!isZero(comprobantePrestamo.getCftnaSinImp())) {
            agregarTasa("Costo Financiero Total Nominal Anual (Sin Impuestos)",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getCftnaSinImp()), tasaViewList,
                    tasaView);
        }
        if (!isZero(comprobantePrestamo.getCftna())) {
            agregarTasa("Costo Financiero Total Nominal Anual (Con Impuestos)",
                    ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getCftna()), tasaViewList, tasaView);
        }
        return tasaViewList;
    }

    /**
     * Gets the comprobante.
     *
     * @param nroPrestamo
     *            the nro prestamo
     * @return the comprobante
     */
    private ComprobanteView getComprobante(String nroPrestamo) {
        ComprobanteView comprobante = null;
        try {
            PagoPrestamo pagoPrestamo = sessionParametros.getPagoPrestamo();
            comprobante = buildComprobanteView(pagoPrestamo);
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_OK,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPROBANTE_PRESTAMO);
        } catch (Exception e) {
            LOGGER.error(ERROR_LOG, OBTENER_COMPROBANTE, e);
            grabarEstadistica(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR,
                    EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPROBANTE_PRESTAMO);
        }
        return comprobante;
    }

    /**
     * Sets the estadistica manager.
     *
     * @param estadisticaManager
     *            the new estadistica manager
     */
    public void setEstadisticaManager(EstadisticaManager estadisticaManager) {
        this.estadisticaManager = estadisticaManager;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * getDetallePrestamos(java.lang.String)
     */
    @Override
    public Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo,
            Boolean desdeInicioPrestamo) {

        Respuesta<DetallePrestamoView> respuesta = new Respuesta<DetallePrestamoView>();
        Respuesta<PreFormalizacion> preFormalizacionRespuesta;
        PagoPendientePrestamo pagoPendientePrestamo = obtenerPagoPendientePrestamo(consultaPrestamo.getNumeroPrestamo(),
                desdeInicioPrestamo);

        if (pagoPendientePrestamo != null && pagoPendientePrestamo.getPrestamo() != null) {

            preFormalizacionRespuesta = obtenerPlazoYCuentaAsociadaPrestamo(pagoPendientePrestamo);
            respuesta = armarPrestamoView(respuesta, pagoPendientePrestamo.getPrestamo(), preFormalizacionRespuesta,
                    desdeInicioPrestamo, consultaPrestamo.getIsUva());
        } else {
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_DETALLE_CUOTA,
                    CodigoMensajeConstantes.CODIGO_STOP_DEBIT_FALLO);
            if (SUPER_PRESTAMO_PERSONAL.equals(consultaPrestamo.getTipoPrestamo())) {
                if (desdeInicioPrestamo) {
                    estadisticaManager.add(EstadisticasConstants.CODIGO_DETALLE_INICIO_PRESTAMO,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                } else {
                    estadisticaManager.add(EstadisticasConstants.CODIGO_DETALLE_CALENDARIO_PRESTAMO,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                }
            } else if (SUPER_PRESTAMO_HIPOTECARIO.equals(consultaPrestamo.getTipoPrestamo())) {
                estadisticaManager.add(EstadisticasConstants.VER_DETALLE_PRESTAMO_HIPOTECARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            } else if (SUPER_PRESTAMO_PRENDARIO.equals(consultaPrestamo.getTipoPrestamo())) {
                estadisticaManager.add(EstadisticasConstants.VER_DETALLE_PRESTAMO_PRENDARIO,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }

        }

        return respuesta;

    }

    /**
     * Obtener pago pendiente prestamo.
     *
     * @param numeroPrestamo
     *            the numero prestamo
     * @param desdeInicioPrestamo
     *            the desde inicio prestamo
     * @return the pago pendiente prestamo
     */
    private PagoPendientePrestamo obtenerPagoPendientePrestamo(String numeroPrestamo, Boolean desdeInicioPrestamo) {

        //TODO: REVIEW HERE
        Cliente cliente = sesionCliente.getCliente();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();

        if (!desdeInicioPrestamo) {
            pagoPendientePrestamo = getPagoPendientePrestamo(numeroPrestamo);
        } else {
            PrestamoDTO prestamoDetails = new PrestamoDTO();
            try {
                prestamoDetails = prestamoBo.obtenerDetallePrestamo(numeroPrestamo, cliente);
            } catch (BusinessException e) {
                LOGGER.error(e.getMessage(), e);
            }
            pagoPendientePrestamo.setPrestamo(prestamoDetails.getPrestamo());
        }

        return pagoPendientePrestamo;
    }

    /**
     * Armar prestamo view.
     *
     * @param respuesta
     *            the respuesta
     * @param prestamo
     *            the prestamo
     * @param preFormalizacionRespuesta
     *            the pre formalizacion respuesta
     * @param desdeInicioPrestamo
     *            the desde inicio prestamo
     * @param isUva 
     * @return the respuesta
     */
    private Respuesta<DetallePrestamoView> armarPrestamoView(Respuesta<DetallePrestamoView> respuesta,
            Prestamo prestamo, Respuesta<PreFormalizacion> preFormalizacionRespuesta, Boolean desdeInicioPrestamo, Boolean isUva) {

        DetallePrestamoView detallePrestamoView = new DetallePrestamoView();
        PreFormalizacion preFormalizacion = preFormalizacionRespuesta.getRespuesta();

        obtenerDetallePrestamoView(prestamo, preFormalizacion, detallePrestamoView, isUva);

        respuesta.setRespuesta(detallePrestamoView);

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);
        
        String codigoEstadisticaPreformalizacion = EstadoRespuesta.ERROR.equals(preFormalizacionRespuesta.getEstadoRespuesta())
                ? EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL : EstadisticasConstants.CODIGO_ESTADISTICAS_OK;

        if (PRESTAMO_PERSONAL.equals(prestamo.getClaseCuenta())) {
            if (desdeInicioPrestamo) {
                estadisticaManager.add(EstadisticasConstants.CODIGO_DETALLE_INICIO_PRESTAMO,
                        codigoEstadisticaPreformalizacion);
            } else {
                estadisticaManager.add(EstadisticasConstants.CODIGO_DETALLE_CALENDARIO_PRESTAMO,
                        codigoEstadisticaPreformalizacion);
            }
        } else if (PRESTAMO_HIPOTECARIO.equals(prestamo.getClaseCuenta())) {
            estadisticaManager.add(EstadisticasConstants.VER_DETALLE_PRESTAMO_HIPOTECARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (PRESTAMO_PRENDARIO.equals(prestamo.getClaseCuenta())) {
            estadisticaManager.add(EstadisticasConstants.VER_DETALLE_PRESTAMO_PRENDARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * obtenerComprobante(java.lang.String)
     */
    @Override
    public Respuesta<ComprobanteView> obtenerComprobante(String nroPrestamo) {
        Respuesta<ComprobanteView> respuesta = new Respuesta<ComprobanteView>();
        ComprobanteView comprobante = getComprobante(nroPrestamo);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(comprobante);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * obtenerConfiguracionPagoCuotaPrestamo(ar.com.santanderrio.obp.servicios.
     * pagos.web.view.ConsultaPrestamo)
     */
    @Override
    public Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(
            ConsultaPrestamo consultaPrestamo, Boolean desdeInicioPrestamo) {

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuestaPagoCuotaPrestamoView = new Respuesta<ConfiguracionPagoCuotaPrestamo>();
        if (!prestamoService.validarHorario()) {

            String[] parametros = { horaInicioOperaciones, horaFinOperaciones };

            Mensaje mensaje = mensajeBO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_HORARIO_DE_OPERACIONES);
            MessageFormat format = new MessageFormat(mensaje.getMensaje());
            respuestaPagoCuotaPrestamoView = respuestaFactory.crearRespuestaErrorPersonalizado(
                    ConfiguracionPagoCuotaPrestamo.class, format.format(parametros),
                    TipoError.ERROR_HORARIO_DE_OPERACIONES.getDescripcion());
        } else {
            // Si ya traigo número de proceso y es igual al que tiene el
            // préstamo en
            // sesión, recupero el pago que venía manejando
            PagoPrestamo pagoPrestamo;
            if (!StringUtils.isNotBlank(consultaPrestamo.getIdProceso()) || (sessionParametros.getPagoPrestamo() != null
                    && !consultaPrestamo.getIdProceso().equals(sessionParametros.getPagoPrestamo().getIdProceso()))) {
                pagoPrestamo = new PagoPrestamo();
                // Obtiene los pagos pendientes de la sesion y me instancia el
                // que
                // se corresponde con el prestamo consultado

                PagoPendientePrestamo pagoPendientePrestamo = obtenerPagoPendientePrestamo(
                        consultaPrestamo.getNumeroPrestamo(), desdeInicioPrestamo);
                PreFormalizacion preFormalizacion = obtenerPreFormalizacion(pagoPendientePrestamo);
                pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
                pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);

                String idProceso = UUID.randomUUID().toString();
                pagoPrestamo.setIdProceso(idProceso);

                sessionParametros.setPagoPrestamo(pagoPrestamo);
            } else {
                pagoPrestamo = sessionParametros.getPagoPrestamo();
            }

            PagoPendientePrestamo pagoPendientePrestamo = pagoPrestamo.getPagoPendientePrestamo();
            Respuesta<CuentasView> respuestaObtenerCuentas = obtenerCuentasPagoPrestamo(pagoPendientePrestamo);
            if (respuestaObtenerCuentas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                respuestaPagoCuotaPrestamoView = respuestaFactory.crearRespuestaError("",
                        TipoError.ERROR_PAGO_PRESTAMOS_CONFIG_CUENTAS,
                        CodigoMensajeConstantes.CODIGO_ERROR_OBTENER_CUENTAS_PRESTAMO);
            } else {
                Prestamo prestamo = pagoPendientePrestamo.getPrestamo();
                ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamoView = new ConfiguracionPagoCuotaPrestamo();
                configuracionPagoCuotaPrestamoView.setCuentasView(respuestaObtenerCuentas.getRespuesta());
                configuracionPagoCuotaPrestamoView.setIdProceso(pagoPrestamo.getIdProceso());
                if (pagoPendientePrestamo.getPreFormalizacion() != null) {
                    configuracionPagoCuotaPrestamoView
                            .setPlazoPrestamo(pagoPendientePrestamo.getPreFormalizacion().getPlazo());
                } else {
                    configuracionPagoCuotaPrestamoView.setPlazoPrestamo("-");
                }
                configuracionPagoCuotaPrestamoView
                        .setFechaVencimiento(ISBANStringUtils.formatearFecha(prestamo.getVencimientoCuota()));
                configuracionPagoCuotaPrestamoView
                        .setImporteCuota(ISBANStringUtils.formatearSaldoConSigno(prestamo.getImporteTotalCuota()));
                configuracionPagoCuotaPrestamoView
                        .setNumeroCuota(ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo()));
                configuracionPagoCuotaPrestamoView.setAliasPrestamo(null);
                configuracionPagoCuotaPrestamoView.setNumeroPrestamo(ISBANStringUtils.formatearNroPrestamo(
                        prestamo.getCuenta().getNroSucursal(), prestamo.getNumeroCuentaProducto()));
                configuracionPagoCuotaPrestamoView.setTipoPrestamo(prestamo.getTipoPrestamoEnum().name());
                configuracionPagoCuotaPrestamoView.setTipoPrestamoLabel(prestamo.getClaseCuenta());
                if (prestamo.getDivisa().equals(DivisaEnum.PESO)) {
                    configuracionPagoCuotaPrestamoView.setDivisa(DivisaEnum.PESO.getSimbolo());
                } else {
                    configuracionPagoCuotaPrestamoView.setDivisa(DivisaEnum.DOLAR.getSimbolo());
                }

                // ******** Datos de UVAs ******** //
                if (StringUtils.isNotBlank(prestamo.getNroExp())) {
                    List<UvaView> uvaViewList;
                    uvaViewList = armarUvaViewList(prestamo);
                    configuracionPagoCuotaPrestamoView.setUvas(uvaViewList);
                }

                respuestaPagoCuotaPrestamoView.setRespuesta(configuracionPagoCuotaPrestamoView);
                respuestaPagoCuotaPrestamoView.setRespuestaVacia(false);
                respuestaPagoCuotaPrestamoView.setEstadoRespuesta(EstadoRespuesta.OK);

                if (PRESTAMO_PERSONAL.equals(prestamo.getClaseCuenta())) {
                    estadisticaManager.add(EstadisticasConstants.INICIO_PAGAR_CUOTA_PRESTAMO_PERSONAL,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                } else if (PRESTAMO_HIPOTECARIO.equals(prestamo.getClaseCuenta())) {
                    estadisticaManager.add(EstadisticasConstants.INICIO_PAGAR_CUOTA_PRESTAMO_HIPOTECARIO,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                } else if (PRESTAMO_PRENDARIO.equals(prestamo.getClaseCuenta())) {
                    estadisticaManager.add(EstadisticasConstants.INICIO_PAGAR_CUOTA_PRESTAMO_PRENDARIO,
                            EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                }
            }
        }
        return respuestaPagoCuotaPrestamoView;
    }

    /**
     * Obtener pre formalizacion.
     *
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @return the pre formalizacion
     */
    private PreFormalizacion obtenerPreFormalizacion(PagoPendientePrestamo pagoPendientePrestamo) {

        // Obtener el preformalizacion
        if (pagoPendientePrestamo.getPreFormalizacion() == null) {
            Respuesta<PreFormalizacion> preFormalizacionRespuesta = obtenerPlazoYCuentaAsociadaPrestamo(
                    pagoPendientePrestamo);
            if (preFormalizacionRespuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                return preFormalizacionRespuesta.getRespuesta();
            }
        }
        return pagoPendientePrestamo.getPreFormalizacion();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * obtenerConfirmacion(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<ConfirmacionPagoPrestamoView> obtenerConfirmacion(ConsultaPrestamo consultaPrestamo) {
        PagoPrestamo pagoPrestamo = sessionParametros.getPagoPrestamo();

        if (!consultaPrestamo.getIdProceso().equals(pagoPrestamo.getIdProceso())) {
            // Devolver error generico? En el caso que tenga 2 pestañas
            // abiertas
            return null;
        }
        Respuesta<ConfirmacionPagoPrestamoView> respuestaConfirmacionPagoPrestamoView = new Respuesta<ConfirmacionPagoPrestamoView>();
        try {
            PagoPendientePrestamo pagoPendientePrestamo = pagoPrestamo.getPagoPendientePrestamo();

            guardarPagoPrestamo(consultaPrestamo.getNumeroCuenta(), pagoPrestamo);
            armarRespuestaConfirmacionPagoPrestamoView(respuestaConfirmacionPagoPrestamoView, pagoPendientePrestamo,
                    consultaPrestamo.getNumeroCuenta(), consultaPrestamo.getIsUva());
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return respuestaConfirmacionPagoPrestamoView;
    }

    /**
     * Armar respuesta confirmacion pago prestamo view.
     *
     * @param respuestaConfirmacionPagoPrestamoView
     *            the respuesta confirmacion pago prestamo view
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @param nroCuentaSeleccionada
     *            the nro cuenta seleccionada
     * @param isUva 
     * @throws ServiceException
     *             the service exception
     */
    private void armarRespuestaConfirmacionPagoPrestamoView(
            Respuesta<ConfirmacionPagoPrestamoView> respuestaConfirmacionPagoPrestamoView,
            PagoPendientePrestamo pagoPendientePrestamo, String nroCuentaSeleccionada, Boolean isUva) throws ServiceException {

        ConfirmacionPagoPrestamoView confirmacionPagoPrestamoView = new ConfirmacionPagoPrestamoView();

        ResumenCuentaPesosView cuentaView;
        Respuesta<CuentasView> cuenta = cuentaManager.getCuenta(nroCuentaSeleccionada);
        if (cuenta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            AbstractCuenta abstractCuenta = cuentaManager.obtenerCuentaById(nroCuentaSeleccionada);
            cuentaView = new ResumenCuentaPesosView();
            cuentaView.setNumero(ISBANStringUtils.formatearNroPrestamo(abstractCuenta.getNroSucursal(),
                    abstractCuenta.getNroCuentaProducto()));
            cuentaView.setDescripcionTipoCuenta(
                    TipoCuenta.fromCodigo(abstractCuenta.getTipoCuenta()).getDescripcionConMoneda());
            cuentaView.setSaldoPesos("--");
            cuentaView.setAlias(abstractCuenta.getAlias());
        } else {
            List<CuentasAdhesionDebitoView> cuentas = cuenta.getRespuesta().getCuentas();
            cuentaView = new ResumenCuentaPesosView(cuentas.get(0));
        }
        // if error

        confirmacionPagoPrestamoView.setCuentaDebito(cuentaView);

        PreFormalizacion preFormalizacion = obtenerPreFormalizacion(pagoPendientePrestamo);

        respuestaConfirmacionPagoPrestamoView.setRespuesta(confirmacionPagoPrestamoView);
        respuestaConfirmacionPagoPrestamoView.setRespuestaVacia(false);
        respuestaConfirmacionPagoPrestamoView.setEstadoRespuesta(EstadoRespuesta.OK);

        obtenerDetallePrestamoView(pagoPendientePrestamo.getPrestamo(), preFormalizacion, confirmacionPagoPrestamoView, isUva);
        agregarLegalImportes(confirmacionPagoPrestamoView, isUva);
    }

    /**
	 * Agregar legal importes.
	 *
	 * @param confirmacionPagoPrestamoView
	 *            the confirmacion pago prestamo view
     * @param isUva 
	 */
    private void agregarLegalImportes(ConfirmacionPagoPrestamoView confirmacionPagoPrestamoView, boolean isUva) {
        confirmacionPagoPrestamoView.getAclaracionReferencias().clear();
        confirmacionPagoPrestamoView.agregarAclaracionReferencia(LEGAL_DIFERENCIA_SUMATORIA);
        for (ImporteView importe : confirmacionPagoPrestamoView.getImportes()) {
        	int legal = 1;
            if (StringUtils.contains(importe.getLabel(), CARGO_SEGURO_VIDA)) {
                importe.setLabel(CARGO_SEGURO_VIDA + " (" + legal + ")");
                importe.setHasLegal(true);
                confirmacionPagoPrestamoView
                        .agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(LEGAL_SEGURO_VIDA));
                legal++;
            }
            if (StringUtils.contains(importe.getLabel(), OTROS_IMPUESTOS)) {
                importe.setLabel(OTROS_IMPUESTOS + " (" + legal + ")");
                importe.setHasLegal(true);
                confirmacionPagoPrestamoView.setTieneOtrosImpuestos(Boolean.TRUE);
                confirmacionPagoPrestamoView.agregarAclaracionReferencia("");
               /* if(isUva)
                	confirmacionPagoPrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_3).replace("(4)", ""));
                else*/
                	confirmacionPagoPrestamoView.agregarAclaracionReferencia(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_3));
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * efectuarPago(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<RespuestaPagoPrestamoView> efectuarPago(ConsultaPrestamo consultaPrestamo) {
        Respuesta<Prestamo> resp = pagar(consultaPrestamo.getIdProceso());
        Respuesta<RespuestaPagoPrestamoView> respuesta = Respuesta.copy(RespuestaPagoPrestamoView.class, resp);
        if (resp.getEstadoRespuesta().equals(EstadoRespuesta.OK)
                || resp.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
            RespuestaPagoPrestamoView respuestaPago = new RespuestaPagoPrestamoView();
            PagoPrestamo pagoPrestamo = sessionParametros.getPagoPrestamo();
            String mensaje = obtenerMensajePagoOk(resp.getRespuesta(), pagoPrestamo.getComprobantePrestamo());
            respuestaPago.setMensaje(mensaje);
            respuesta.setRespuesta(respuestaPago);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * obtenerReporteComprobante(java.lang.String)
     */
    @Override
    public Respuesta<ReporteView> obtenerReporteComprobante(String numeroPrestamo) {
        Respuesta<ReporteView> respuesta;
        respuesta = this.obtenerComprobantePrestamo(numeroPrestamo);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * getReporteDetallePrestamo(java.lang.String)
     */
    @Override
    public Respuesta<ReporteView> getReporteDetallePrestamo(String numeroPrestamo, Boolean isUva) {
        Respuesta<ReporteView> respuesta;

        respuesta = this.obtenerReporteDetallePrestamo(numeroPrestamo, isUva);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager#
     * notificarDetalleImporteCuota(java.lang.String)
     */
    @Override
    public void notificarDetalleImporteCuota(String tipo) {
        if (tipo.contains(PERSONAL)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_PERSONAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (tipo.contains(HIPOTECARIO)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_HIPOTECARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (tipo.contains(PRENDARIO)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_IMPORTE_CUOTA_PRESTAMO_PRENDARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.web.manager.PrestamoManager#
     * notificarDetalleTasasCuota()
     */
    @Override
    public void notificarDetalleTasasCuota(String tipo) {
        if (tipo.contains(PERSONAL)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_PERSONAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (tipo.contains(HIPOTECARIO)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_HIPOTECARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (tipo.contains(PRENDARIO)) {
            estadisticaManager.add(EstadisticasConstants.ACCESO_VER_DETALLE_TASAS_CUOTA_PRESTAMO_PRENDARIO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager#
     * verDetallePrestamo(ar.com.santanderrio.obp.servicios.pagos.web.view.
     * ConsultaPrestamo)
     */
    @Override
    public Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo) {
        try {
            final PrestamoDTO prestamoDetails = prestamoBo.obtenerDetallePrestamo(consultaPrestamo.getNumeroPrestamo(),
                    sesionCliente.getCliente());
            final Prestamo prestamo = prestamoDetails.getPrestamo();
            final PreFormalizacion preFormalizacion = prestamoDetails.getPreFormalizacion();

            if (preFormalizacion != null) {
                DetalleCuotaPrestamoView detalleCuota = armarDetallePrestamo(prestamo, preFormalizacion);
                grabadoEstadisticas(consultaPrestamo.getTipoPrestamo(), EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                return respuestaFactory.crearRespuestaOk(detalleCuota);
            }
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return detallePrestamoError(consultaPrestamo.getTipoPrestamo());
    }

    /**
     * Armar detalle prestamo.
     *
     * @param prestamo
     *            the prestamo
     * @param preFormalizacion
     *            the pre formalizacion
     * @return the detalle cuota prestamo view
     */
    private DetalleCuotaPrestamoView armarDetallePrestamo(Prestamo prestamo, PreFormalizacion preFormalizacion) {

        DetalleCuotaPrestamoView detallePrestamo = new DetalleCuotaPrestamoView();

        TasaValorView tasas = new TasaValorView(prestamo, preFormalizacion);
        Cuenta cuentaDebito = sesionCliente.getCliente()
                .getCuentaSiContieneNumero(preFormalizacion.getPrestamoDebitoAdherido().getNumero());

        CuentaView cuentaDebitoView = ProximaCuotaView.obtenerCuentaDebito(cuentaDebito,
                preFormalizacion.getPrestamoDebitoAdherido().getNroSucursal(),
                preFormalizacion.getPrestamoDebitoAdherido().getNumero());

        detallePrestamo.setCuenta(cuentaDebitoView);
        
        
        // Seteo el tipo de Prestamo UVA solo si es Personal
        if (TipoPrestamoEnum.PERSONAL.equals(prestamo.getTipoPrestamoEnum()) && 
        		StringUtils.isNotBlank(preFormalizacion.getPrestamoDebitoAdherido().getUnidadExposicion())) {
	        detallePrestamo.setTipoPrestamo("Súper " + TipoPrestamoEnum.UVA.getDescripcion());
	        detallePrestamo.setLegalPlazoPrestamo(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_PREAPROBADO_LEGAL_2));
    	}
    	else {
    		detallePrestamo.setTipoPrestamo("Súper " + prestamo.getClaseCuenta());
    		detallePrestamo.setLegalPlazoPrestamo(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.PRESTAMO_UVA_LEGAL_2));
    	}
        
        if (StringUtils.isNotBlank(preFormalizacion.getPrestamoDebitoAdherido().getUnidadExposicion())) {
	        detallePrestamo.setIsUva(true);
    	} else {
    		detallePrestamo.setIsUva(false);
    	}
        
        detallePrestamo.setNumeroPrestamo(ISBANStringUtils.formatearNroPrestamo(prestamo.getCuenta().getNroSucursal(),
                prestamo.getNumeroCuentaProducto()));
        detallePrestamo.setPlazoPrestamo(preFormalizacion.getPlazo());
        String importeSolicitado = "$ " + ISBANStringUtils.formatearSaldo(ISBANStringUtils
                .stringToBigDecimal(preFormalizacion.getPrestamoDebitoAdherido().getMontoAPagar(), 13, 4, false));

        detallePrestamo.setImporteSolicitado(importeSolicitado);
        Date fechaInicio = ISBANStringUtils.formatearFecha(preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio(), "yyyy-MM-dd");
        detallePrestamo.setFechaAltaPrestamo(ISBANStringUtils.formatearFecha(fechaInicio, "dd/MM/yyyy"));
        detallePrestamo.setTasas(tasas);

        detallePrestamo.setDestinoPrestamo(
                destinoPrestamoBo.buscarDescripcionPorCodigoDestinoFondo(preFormalizacion.getCodigoDestinoPrestamo()));

        // ******** Datos de UVAs ******** //
        if (StringUtils.isNotBlank(preFormalizacion.getPrestamoDebitoAdherido().getUnidadExposicion())) {
        	detallePrestamo.setImporteSolicitadoUvas(ISBANStringUtils.formatearSaldo
        			(preFormalizacion.getPrestamoDebitoAdherido().getImpSolAVisualizar()));
        	detallePrestamo.setCotizacionUva("$ " + ISBANStringUtils.formatearSaldo
        			(preFormalizacion.getPrestamoDebitoAdherido().getCotizacionUnidadExp()));
        	Date fechaCotizacion = ISBANStringUtils.formatearFecha(preFormalizacion.getPrestamoDebitoAdherido().getFechaCotizacion(), 
        			"yyyy-MM-dd");
        	detallePrestamo.setFechaCotizacionUva(ISBANStringUtils.formatearFecha(fechaCotizacion, "dd/MM/yyyy"));
        }
        
        return detallePrestamo;
    }

    /**
     * Detalle prestamo error.
     *
     * @param tipoPrestamo
     *            the tipo prestamo
     * @return the respuesta
     */
    private Respuesta<DetalleCuotaPrestamoView> detallePrestamoError(String tipoPrestamo) {
        grabadoEstadisticas(tipoPrestamo, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        return respuestaFactory.crearRespuestaError(DetalleCuotaPrestamoView.class,
                "Error al consultar detalle prestamo", null);
    }

    /**
     * Grabado estadisticas.
     *
     * @param tipoPrestamo
     *            the tipo prestamo
     * @param codigoEstadisticaResultado
     *            the codigo estadistica resultado
     */
    private void grabadoEstadisticas(String tipoPrestamo, String codigoEstadisticaResultado) {

        if (SUPER_PRESTAMO_PERSONAL.equals(tipoPrestamo)) {
            estadisticaManager.add(EstadisticasConstants.DETALLE_PRESTAMO_PERSONAL, codigoEstadisticaResultado);
        } else if (SUPER_PRESTAMO_HIPOTECARIO.equals(tipoPrestamo)) {
            estadisticaManager.add(EstadisticasConstants.DETALLE_PRESTAMO_HIPOTECARIO, codigoEstadisticaResultado);
        } else {
            estadisticaManager.add(EstadisticasConstants.DETALLE_PRESTAMO_PRENDARIO, codigoEstadisticaResultado);
        }
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager#descargarPdfTasas()
	 */
	@Override
	public Respuesta<ReporteView> descargarPdfTasas() {
		 ComprobanteView comprobante = buildComprobanteView(sessionParametros.getPagoPrestamo());
		 Respuesta<Reporte> reporte = prestamoBo.obtenerReportePdf(comprobante);
		 String estadistica = obtenerEstadisticaPorTipoPrestamo(comprobante.getTipoPrestamo());
		 if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
				ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(reporteView);
			} else {
				estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			}
	}

	/**
	 * Obtener estadistica por tipo prestamo.
	 *
	 * @param tipoPrestamo
	 *            the tipo prestamo
	 * @return the string
	 */
	private String obtenerEstadisticaPorTipoPrestamo(String tipoPrestamo) {
		if("PERSONAL".equals(tipoPrestamo)) {
			return EstadisticasConstants.DESCARGA_PDF_PRESTAMO_PERSONAL;
		}else if ("HIPOTECARIOS".equals(tipoPrestamo)) {
			return EstadisticasConstants.DESCARGA_PDF_PRESTAMO_HIPOTECARIO;
		}
		return EstadisticasConstants.DESCARGA_PDF_PRESTAMO_PRENDARIO;
	}

}
