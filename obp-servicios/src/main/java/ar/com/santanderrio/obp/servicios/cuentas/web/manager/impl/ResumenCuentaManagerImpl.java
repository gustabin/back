/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiMigrada;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ComprobanteAdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.ResumenCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.AdhesionResumenView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenMesualCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class ResumenCuentaManagerImpl.
 */
@Component
public class ResumenCuentaManagerImpl implements ResumenCuentaManager {

    /** marca de impresion de resumen Online *. */
    private static final String MARCA_RESUMEN_ONLINE = "B";

    /** marca de impresion de resumen a domicilio *. */
    private static final String MARCA_RESUMEN_DOMICILIO = "P";

    /** The Constant SE_GRABA_ESTADISTICA. */
    private static final String SE_GRABA_ESTADISTICA = "Se graba estadistica {}";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResumenCuentaManagerImpl.class);

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The cuenta manager. */
    @Autowired
    private CuentaManager cuentaManager;

    /** The resumen mensual cuenta BO. */
    @Autowired
    private ResumenMensualCuentaBO resumenMensualCuentaBO;

    /** The session detalle cuentas. */
    @Autowired
    private SessionResumenCuenta sesionResumenCuenta;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;
    
//  @Autowired
//  private SesionCliente sesioncliente;
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager#
     * comprobanteAdhesionResumen(ar.com.santanderrio.obp.servicios.cuentas.web.
     * view.AdhesionResumenView)
     */
    @Override
    public void comprobanteAdhesionResumen(AdhesionResumenView view) {
        String estadistica = EstadisticasConstants.COMPROBANTE_ADHESION_RESUMEN_ONLINE;
        if (view.isResumenOnline()) {
            estadistica = EstadisticasConstants.COMPROBANTE_ADHESION_RESUMEN_FISICO;
        }
        LOGGER.info(SE_GRABA_ESTADISTICA, estadistica);
        estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.
     * ResumenCuentaManager#obtenerListaResumen(java.lang.String)
     */
    @Override
    public Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(String nroCuenta) {
        Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDesdeServicio = null;
        try {

            Boolean errorCuentasCiti = null;
            
            AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(nroCuenta);
            // Busco los resumenes en sesion
            
            Respuesta<List<ResumenMensualCuenta>> resumenesSesionList = sesionResumenCuenta
                    .getResumenesByCuenta(cuenta);
            // si la sesion no estan cargado los resumenes los voy a buscar al
            // servicio
            if (resumenesSesionList == null || !EstadoRespuesta.OK.equals(resumenesSesionList.getEstadoRespuesta())) {
                 
                resumenesMensualesDesdeServicio = resumenMensualCuentaBO.obtenerListaResumen(cuenta);
                if(!EstadoRespuesta.ERROR.equals(resumenesMensualesDesdeServicio.getEstadoRespuesta()) && sesionCliente.getCliente().getIsExCiti() != null && sesionCliente.getCliente().getIsExCiti()) {
                	
                     Respuesta<CuentaCitiMigrada> respCuentaMigrada = cuentaBO.obtenerCuentaCiti(cuenta, sesionCliente.getCliente());                	                  	   
                     if(EstadoRespuesta.OK.equals(respCuentaMigrada.getEstadoRespuesta())
                             && CollectionUtils.isNotEmpty(respCuentaMigrada.getRespuesta().getCuentasCiti())) {
                         errorCuentasCiti = Boolean.FALSE;
                         estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICAS_OBTENER_CUENTA_EXCITI,
                                 EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                         List<ResumenMensualCuenta> fechas = new ArrayList<ResumenMensualCuenta>();
                         for (CuentaCitiEntity ctaci : respCuentaMigrada.getRespuesta().getCuentasCiti()) {
                             Respuesta<List<ResumenMensualCuenta>> resumenesCiti = resumenMensualCuentaBO.consultarFechasPorCuenta(ctaci.getCuentaCiti());
                             if (!EstadoRespuesta.ERROR.equals(resumenesCiti.getEstadoRespuesta())) {
                                 fechas.addAll(resumenesCiti.getRespuesta());
                             } else {
                                 errorCuentasCiti = Boolean.TRUE;
                             }
                         }
                         ordenarFechas(fechas);
                         
                         agregarListaResumenesCiti(resumenesMensualesDesdeServicio, fechas);
                     } else {
                         estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICAS_OBTENER_CUENTA_EXCITI,
                                 EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                    }
                }
                Respuesta<ResumenesMensualesCuentaView> respResumenMensual = armarRespuestaResumenMesualCuentaView(resumenesMensualesDesdeServicio, cuenta);
                
                if(errorCuentasCiti != null) {
                    if(!errorCuentasCiti) {
                        estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICAS_VER_LISTA_RESUMENES_EXCITI,
                                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                    } else {
                        estadisticaManager.add(EstadisticasConstants.CODIGO_ESTADISTICAS_VER_LISTA_RESUMENES_EXCITI,
                                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                        if(EstadoRespuesta.WARNING.equals(respResumenMensual.getEstadoRespuesta())){
                            ItemMensajeRespuesta item = respuestaFactory.generarItemMensajeRespuesta("",  TipoError.ERROR_CONSULTA_RESUMENES, 
                                    CodigoMensajeConstantes.MENSAJE_ERROR_RESUMENES_EXCITI);
                            respResumenMensual.getItemsMensajeRespuesta().add(item);
                        } else {
                            respResumenMensual =  respuestaFactory.crearRespuestaWarning(respResumenMensual.getRespuesta(),"",  TipoError.ERROR_CONSULTA_RESUMENES, 
                                    CodigoMensajeConstantes.MENSAJE_ERROR_RESUMENES_EXCITI);
                        }
                    }
                }
                if(!EstadoRespuesta.ERROR.equals(respResumenMensual.getEstadoRespuesta())) {
                    sesionResumenCuenta.setResumenesMensualesDisponiblesByCuenta(resumenesMensualesDesdeServicio, cuenta);
                }
                return respResumenMensual;
            } else {
                // Los resumenes se encuentran en sesion los devuelve haciendo
                // una respuesta para la vista
                return armarRespuestaResumenMesualCuentaView(resumenesSesionList, cuenta);
            }
        } catch (BusinessException ex) {
            LOGGER.error(ex.getMessage(), ex);
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError(null, TipoError.CODIGO_ERROR_LISTA_RESUMENES_ERROR,
                    CodigoMensajeConstantes.CODIGO_ERROR_LISTA_RESUMENES_ERROR);
        }
    }

    /**
	 * Agregar lista resumenes citi.
	 *
	 * @param resumenesMensualesDesdeServicio
	 *            the resumenes mensuales desde servicio
	 * @param resumenesCiti
	 *            the resumenes citi
	 */
    private void agregarListaResumenesCiti(Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDesdeServicio,
            List<ResumenMensualCuenta> resumenesCiti) {
        
        
        if(CollectionUtils.isEmpty(resumenesMensualesDesdeServicio.getRespuesta())) {
            resumenesMensualesDesdeServicio.setRespuesta(new ArrayList<ResumenMensualCuenta>());
        }
            
        if(CollectionUtils.isNotEmpty(resumenesCiti)) {
            
            Long index  = new Long(resumenesMensualesDesdeServicio.getRespuesta().size());
            
            
            for (ResumenMensualCuenta resumenCiti :resumenesCiti) {
                resumenCiti.setId(index++); 
                resumenesMensualesDesdeServicio.getRespuesta().add(resumenCiti);
            }
        }
    }

    /**
	 * Ordenar fechas.
	 *
	 * @param fechasresumenesCiti
	 *            the fechasresumenes citi
	 */
    private void ordenarFechas(List<ResumenMensualCuenta> fechasresumenesCiti) {
        Collections.sort(fechasresumenesCiti, new Comparator<ResumenMensualCuenta>() {
                    @Override
                    public int compare(ResumenMensualCuenta resumen1, ResumenMensualCuenta resumen2) {
                        return resumen2.getFecha().compareTo(resumen1.getFecha());
                    }
                });
    }

    /**
     * Armar respuesta resumen mesual cuenta view.
     *
     * @param respuestaResumenesList
     *            the respuesta resumenes list
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    private Respuesta<ResumenesMensualesCuentaView> armarRespuestaResumenMesualCuentaView(
            Respuesta<List<ResumenMensualCuenta>> respuestaResumenesList, AbstractCuenta cuenta) {
        List<ResumenMesualCuentaView> resumenMesualCuentaViewList = new ArrayList<ResumenMesualCuentaView>();
        Respuesta<ResumenesMensualesCuentaView> respuestaView = new Respuesta<ResumenesMensualesCuentaView>();

        if (EstadoRespuesta.OK.equals(respuestaResumenesList.getEstadoRespuesta())) {
            List<ResumenMensualCuenta> resumenesList = respuestaResumenesList.getRespuesta();
            for (ResumenMensualCuenta resumenMensualCuenta : resumenesList) {
                ResumenMesualCuentaView resumenMesualCuentaView = new ResumenMesualCuentaView();
                resumenMesualCuentaView.setMes(formatearMes(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setAnio(formatearAnio(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setDia(formatearDia(resumenMensualCuenta.getFecha()));
                resumenMesualCuentaView.setVisto(resumenMensualCuenta.getVisto());
                resumenMesualCuentaView.setId(resumenMensualCuenta.getId());
                resumenMesualCuentaViewList.add(resumenMesualCuentaView);
            }
            ResumenesMensualesCuentaView resumenesMensualesCuentaView = new ResumenesMensualesCuentaView();
            String nroSucursalProducto = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
            String nroCuentaProducto = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
            String nroCuentaCompleto = nroSucursalProducto + "-" + nroCuentaProducto;
            resumenesMensualesCuentaView.setNumeroCuenta(nroCuentaCompleto);

            String mensajeInformativo = resumenMensualCuentaBO.obtenerMensajeInformativoDescargaResumenes();
            resumenesMensualesCuentaView.setMensaje(mensajeInformativo);
            resumenesMensualesCuentaView.setResumenes(resumenMesualCuentaViewList);
            String tipoCuenta = TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcionConMoneda();
            resumenesMensualesCuentaView.setTipoCuenta(tipoCuenta);

            respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaView.setRespuesta(resumenesMensualesCuentaView);
            respuestaView.setRespuestaVacia(false);
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else if (EstadoRespuesta.WARNING.equals(respuestaResumenesList.getEstadoRespuesta())) {
            respuestaView.setEstadoRespuesta(EstadoRespuesta.WARNING);
            respuestaView.setRespuestaVacia(true);
            respuestaView.setItemMensajeRespuesta(respuestaResumenesList.getItemsMensajeRespuesta());
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        } else {
            respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuestaView.setRespuestaVacia(true);
            respuestaView.setItemMensajeRespuesta(respuestaResumenesList.getItemsMensajeRespuesta());
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        if (!EstadoRespuesta.ERROR.equals(respuestaView.getEstadoRespuesta()) && !respuestaView.isRespuestaVacia()
                && !cuenta.isCuentaCerrada()) {

            LOGGER.info("Se consulta marca de Impresion de Resumen");
            String codigoEstadistica = EstadisticasConstants.CONSULTA_ADHESION_ONLINE_RESUMEN_CUENTA;

            String estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;

            inicializarContadorIntentosCambioMarca();

            Respuesta<Boolean> respuesta = resumenMensualCuentaBO.obtenerMarcaResumenOnline(cuenta);
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

                if (respuesta.getRespuesta()) {
                    codigoEstadistica = EstadisticasConstants.CONSULTA_ADHESION_DOMICILIO_RESUMEN_CUENTA;
                }
                respuestaView.getRespuesta().setResumenOnline(respuesta.getRespuesta());
            } else {

                estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
                respuestaView.getRespuesta().setResumenOnline(null);
            }
            LOGGER.info("FIN - Consulta marca de Impresion de Resumen. respuesta: ", estadoEstadistica);
            estadisticaManager.add(codigoEstadistica, estadoEstadistica);
        }

        return respuestaView;
    }

    /**
     * Formatear dia.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    private String formatearDia(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    /**
     * Formatear mes.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    private String formatearMes(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
        return ISBANStringUtils.convertirPrimerLetraEnMayuscula(formateador.format(fecha));
    }

    /**
     * Formatear anio.
     *
     * @param fecha
     *            the fecha
     * @return the string
     */
    private String formatearAnio(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
        return formateador.format(fecha);
    }

    /**
     * Inicializa el contador de intentos para cambio de marca.
     */
    private void inicializarContadorIntentosCambioMarca() {

        sesionParametros.setContador(new ContadorIntentos(2));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.
     * ResumenCuentaManager#obtenerResumenesPDF(ar.com.santanderrio.obp.
     * servicios.cuentas.web.view.ConsultaCuentaView)
     */
    @Override
    public Respuesta<ReporteView> obtenerResumenesPDF(ConsultaCuentaView consultaCuentaView) {
        Respuesta<ReporteView> respuestaView = null;
        AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(consultaCuentaView.getNumeroCuenta());
        List<ResumenMensualCuenta> resumenesSeleccionados = sesionResumenCuenta
                .getResumenesByIds(Arrays.asList(consultaCuentaView.getFechas()), cuenta);
        Respuesta<ReporteResumenMensualCuenta> reporteMensual = resumenMensualCuentaBO
                .obtenerResumenesPDF(resumenesSeleccionados, cuenta);
        respuestaView = Respuesta.copy(ReporteView.class, reporteMensual);
        if (reporteMensual.getRespuesta() != null) {
            ReporteView resumenesMensualesView = ReporteView.fromReporte(reporteMensual.getRespuesta());
            respuestaView.setRespuesta(resumenesMensualesView);
        }
        String estadistica = EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGAR_RESUMENES;
        Boolean isResumenExCiti = isResumenCiti(resumenesSeleccionados);
        if (isResumenExCiti) {
            estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_DESCARGA_SIMPLE_RESUMEN_EXCITI;
        }
        if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())
                || EstadoRespuesta.WARNING.equals(respuestaView.getEstadoRespuesta())) {
            sesionResumenCuenta.marcarVistos(resumenesSeleccionados, cuenta);
            estadisticaManager.add(estadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(estadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.ResumenCuentaManager#obtenerResumenDescargaMultiple(java.util.List, java.lang.String, int)
     */
    @Override
    public Respuesta<ReporteView> obtenerResumenDescargaMultiple(
            List<String> idsSeleccionadas, String nroCuenta,
            int cantidadADescargar) {
        Respuesta<ReporteView> respuestaView = null;
        String estadistica = EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGAR_RESUMENES_MULTIPLE;
        try {
            AbstractCuenta cuenta = cuentaManager.obtenerCuentaById(nroCuenta);
            List<ResumenMensualCuenta> resumenesSeleccionados = sesionResumenCuenta
                    .getResumenesByIds(idsSeleccionadas, cuenta);

            Boolean isResumenExCiti = isResumenCiti(resumenesSeleccionados);
            if (isResumenExCiti) {
                estadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_DESCARGA_MULTIPLE_RESUMEN_EXCITI;
            }
            Respuesta<ReporteResumenMensualCuenta> reporteMensual = resumenMensualCuentaBO
                    .obtenerResumenDescargaMultiple(resumenesSeleccionados,
                            cuenta, cantidadADescargar);
            respuestaView = Respuesta.copy(ReporteView.class, reporteMensual);
            if (reporteMensual.getRespuesta() != null) {
                ReporteView resumenesMensualesView = ReporteView
                        .fromReporte(reporteMensual.getRespuesta());
                respuestaView.setRespuesta(resumenesMensualesView);
            }
            if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
                sesionResumenCuenta.marcarVistos(resumenesSeleccionados,
                        cuenta);
                estadisticaManager.add(estadistica,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

            } else {
                estadisticaManager.add(estadistica,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } catch (BusinessException ex) {
            LOGGER.error(ex.getMessage(), ex);
            estadisticaManager.add(estadistica,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /**
     * Checks if is resumen citi.
     *
     * @param resumenesSeleccionados the resumenes seleccionados
     * @return the boolean
     */
    private Boolean isResumenCiti(
            List<ResumenMensualCuenta> resumenesSeleccionados) {
        for (ResumenMensualCuenta resumenMensual : resumenesSeleccionados) {
            if (resumenMensual.getDocId() != null) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.web.manager.
     * ResumenCuentaManager#confirmarAdhesionResumen(ar.com.santanderrio.obp.
     * servicios.cuentas.web.view.AdhesionResumenView)
     */
    @Override
    public Respuesta<AdhesionResumenView> confirmarAdhesionResumen(AdhesionResumenView adhesionResumenView) {
        LOGGER.info("Modificacion marca de impresion de resumen");
        Boolean resumenOnline = adhesionResumenView.isResumenOnline();
        Cuenta cuenta = (Cuenta) cuentaManager.obtenerCuentaById(adhesionResumenView.getNumeroCuenta());
        AdhesionResumenDTO adhesionResumenDTO = new AdhesionResumenDTO();
        adhesionResumenDTO.setCuenta(cuenta);
        Respuesta<AdhesionResumenView> respuestaResumenMensualCuenta;
        Respuesta<Void> respuesta;
        ComprobanteAdhesionResumenDTO comprobanteAdhesionResumenDTO;
        String marcaResumen = MARCA_RESUMEN_ONLINE;
        String opinionUsuario = null;
        String codigoEstadistica = EstadisticasConstants.ADHESION_RESUMEN_CUENTAS_ONLINE;
        String estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
        String mensajeError = CodigoMensajeConstantes.SOLICITUD_RESUMEN_ONLINE_ERROR;
        if (resumenOnline) {

            marcaResumen = MARCA_RESUMEN_DOMICILIO;
            opinionUsuario = adhesionResumenView.getOpinionUsuario();
            codigoEstadistica = EstadisticasConstants.ADHESION_RESUMEN_CUENTAS_FISICO;
            mensajeError = CodigoMensajeConstantes.SOLICITUD_RESUMEN_DOMICILIO_ERROR;
        }

        adhesionResumenDTO.setOpinionUsuario(opinionUsuario);
        adhesionResumenDTO.setAdhesionAutomatica(marcaResumen);
        respuesta = resumenMensualCuentaBO.cambiarMarcaImpresion(adhesionResumenDTO);

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

            comprobanteAdhesionResumenDTO = resumenMensualCuentaBO
                    .crearComprobanteAdhesionResumen(sesionParametros.getRegistroSession().getPid());
            respuestaResumenMensualCuenta = respuestaFactory.crearRespuestaOk(AdhesionResumenView.class,
                    mapearComprobanteAdhesionResumen(comprobanteAdhesionResumenDTO,
                            adhesionResumenView.getNumeroCuenta(), !resumenOnline));
        } else {
            estadoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
            TipoError tipoError = TipoError.ERROR_SOLICITUD_RESUMEN;
            if (sesionParametros.getContador().permiteReintentar()) {

                tipoError = TipoError.ERROR_SOLICITUD_RESUMEN_INTENTOS;
            }
            respuestaResumenMensualCuenta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, tipoError,
                    mensajeError, adhesionResumenView.getNumeroCuenta());
        }
        estadisticaManager.add(codigoEstadistica, estadoEstadistica);
        LOGGER.info("FIN - Modificacion marca de impresion de resumen");
        return respuestaResumenMensualCuenta;
    }

    /**
     * Mapeo comprobante Adhesion Resumen.
     *
     * @param comprobanteAdhesionResumenDTO
     *            the comprobante adhesion resumen DTO
     * @param numeroCuenta
     *            the numero cuenta
     * @param online
     *            the online
     * @return the adhesion resumen view
     */
    private AdhesionResumenView mapearComprobanteAdhesionResumen(
            ComprobanteAdhesionResumenDTO comprobanteAdhesionResumenDTO, String numeroCuenta, Boolean online) {
        AdhesionResumenView adhesionResumenView = new AdhesionResumenView();

        String codigoMensaje = online ? CodigoMensajeConstantes.SOLICITUD_RESUMEN_ONLINE_OK
                : CodigoMensajeConstantes.SOLICITUD_RESUMEN_DOMICILIO_OK;
        Mensaje mensajeOK = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
        adhesionResumenView.setMensaje(MessageFormat.format(mensajeOK.getMensaje(), numeroCuenta));

        adhesionResumenView.setNumeroCuenta(numeroCuenta);
        adhesionResumenView.setNumeroComprobante(comprobanteAdhesionResumenDTO.getComprobante());
        adhesionResumenView.setFecha(comprobanteAdhesionResumenDTO.getFecha());
        adhesionResumenView.setHora(comprobanteAdhesionResumenDTO.getHora());

        return adhesionResumenView;
    }

}
