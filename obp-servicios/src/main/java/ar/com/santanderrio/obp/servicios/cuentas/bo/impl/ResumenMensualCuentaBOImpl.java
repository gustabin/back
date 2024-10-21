/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.ProcessingException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.citi.entities.ResumenCitiIn;
import ar.com.santanderrio.obp.servicios.citi.entities.ResumenFechaOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.resumen.dao.ResumenDAO;
import ar.com.santanderrio.obp.servicios.comun.resumen.entity.ResumenInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.ResumenesCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ComprobanteAdhesionResumenDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaOnDemandDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ResumenMensualCuentaBOImpl.
 */
@Component
public class ResumenMensualCuentaBOImpl implements ResumenMensualCuentaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResumenMensualCuentaBOImpl.class);

    /** The Constant APLICACION_INFI. */
    private static final String APLICACION_INFI = "INFI";

    /** The Constant APLICACION_ACTE. */
    private static final String APLICACION_ACTE = "ACTE";

    /** The Constant ERROR_AL_DESCARGAR_RESUMENES. */
    private static final String ERROR_AL_DESCARGAR_RESUMENES = "Error al descargar resumenes";

    /** The Constant ERROR_AL_GENERAR_PDF. */
    private static final String ERROR_AL_GENERAR_PDF = "Error al generar pdf";

    /** The Constant MARCA_FACTURA_ELECTRONICA. */
    private static final String MARCA_FACTURA_ELECTRONICA = "B";

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The ondeman dao. */
    @Autowired
    private OndemandDAO ondemanDao;

    /** The cantidad meses. */
    @Value("${RESUMENONDEMAND.MESES}")
    private String cantidadMeses;

    /** The ruta archivos tmp. */
    @Value("${RESUMENONDEMAND.RUTA_ARCHIVOS_TMP}")
    private String rutaArchivosTmp;

    /** The nombre zip. */
    @Value("${RESUMENONDEMAND.NOMBRE_ZIP}")
    private String nombreZip;

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "dd/MM/yy";

    /** The Constant AMEX. */
    private static final String AMEX = "AMEX";

    /** The Constant VISA. */
    private static final String VISA = "VISA";

    /** The Constant MASTERCARD. */
    private static final String MASTER = "MASTERCARD";

	/** The Constant PESUBSEG. */
	private static final String PESUBSEG = "PYM";

	/** The Constant CUADRANTE_VALIDO_ADVANCE_1. */
	private static final String CUADRANTE_VALIDO_ADVANCE_1 = "P1";

	/** The Constant CUADRANTE_VALIDO_ADVANCE_2. */
	private static final String CUADRANTE_VALIDO_ADVANCE_2 = "P2";

	/** The Constant CUADRANTE_VALIDO_ADVANCE_3. */
	private static final String CUADRANTE_VALIDO_ADVANCE_3 = "C1";

    /** The respuesta BO. */
    @Autowired
    private RespuestaBO respuestaBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The respuestaFactory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The resumen DAO. */
    @Autowired
    ResumenDAO resumenDAO;

    /** The resumenes citi DAO. */
    @Autowired
    private ResumenesCitiDAO resumenesCitiDAO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The resumenes cuenta DAO. */
	@Autowired
	private ResumenesCuentaDAO resumenesCuentaDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerListaResumen(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * AbstractCuenta)
     */
    @Override
    public Respuesta<List<ResumenMensualCuenta>> obtenerListaResumen(AbstractCuenta cuenta) throws BusinessException {

        Respuesta<List<ResumenMensualCuenta>> respuesta = new Respuesta<List<ResumenMensualCuenta>>();
        try {
            ResumenParams params = configurarParamentrosConsultaLista(cuenta);
            if (params == null) {
                respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_CONSULTA_RESUMENES,
                        CodigoMensajeConstantes.CODIGO_ERROR_LISTA_RESUMENES_ERROR, "");
                return respuesta;
            }
            List<ResumenMensualCuenta> listaResumen = obtenerListaResumenSegunCliente(cuenta.getCliente(), params);
            if (listaResumen != null && !listaResumen.isEmpty()) {
                respuestaBO.armarRespuestaOk(respuesta, listaResumen);
            } else {
               respuesta = respuestaFactory.crearRespuestaWarning(null, "", TipoError.SIN_RESUMENES,
                        CodigoMensajeConstantes.ULTIMOS_RESUMENES_SIN_RESUMENES);
                respuesta.setRespuestaVacia(true);
            }
        } catch (WSODException e) {
            respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_CONSULTA_RESUMENES,
                    CodigoMensajeConstantes.CODIGO_ERROR_LISTA_RESUMENES_ERROR, "");
        } catch (RuntimeException e) {
            respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_CONSULTA_RESUMENES,
                    CodigoMensajeConstantes.CODIGO_ERROR_LISTA_RESUMENES_ERROR, "");
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerMarcaResumenOnline(ar.com.santanderrio.obp.servicios.cuentas.
     * entities.AbstractCuenta)
     */
    @Override
    public Respuesta<Boolean> obtenerMarcaResumenOnline(AbstractCuenta cuenta) {
        Respuesta<Boolean> respuesta;
        ResumenParams params = new ResumenParams();
        params.setIdCta("0");
        params.setUsuarioConsulta(cuenta.getCliente().getNup());
        String codAplicacion = ((Cuenta) cuenta).getCodigoAplicacion();
        params.setFiltroAplicacion(APLICACION_INFI.equals(codAplicacion) ? APLICACION_ACTE : codAplicacion);
        params.setCuenta(cuenta);
        try {
            String marca = ondemanDao.obtenerMarcaImpresion(params);
            if (MARCA_FACTURA_ELECTRONICA.equals(marca)) {
                respuesta = respuestaFactory.crearRespuestaOk(Boolean.TRUE);
            } else {
                respuesta = respuestaFactory.crearRespuestaOk(Boolean.FALSE);
            }
        } catch (WSODException e) {
            LOGGER.error("Error en servicio Ondemand", e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
        } catch (OnDemandDAOException e) {
            LOGGER.error("Servicio Ondemand respuesta con error", e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
        } catch (ProcessingException e) {
            LOGGER.error("Falla al llamar servicio Ondemand", e);
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
        }
        return respuesta;
    }

    /**
     * Configurar paramentros consulta lista.
     *
     * @param cuenta
     *            the cuenta
     * @return the resumen params
     */
    private ResumenParams configurarParamentrosConsultaLista(AbstractCuenta cuenta) {
        ResumenParams params = new ResumenParams();
        params.setUsuarioConsulta(cuenta.getCliente().getNup());
        params.setCuenta(cuenta);
        Date fechaHasta = new Date();
        Date fechaDesde = restarMeses(fechaHasta, Integer.parseInt(cantidadMeses));
        if (validarFechas(fechaDesde, fechaHasta)) {
            params.setFechaDesde(ISBANStringUtils.formatearFecha(fechaDesde, FORMATO_FECHA));
            params.setFechaHasta(ISBANStringUtils.formatearFecha(fechaHasta, FORMATO_FECHA));
        } else {
            return null;
        }
        return params;
    }

    /**
     * Configurar paramentros consulta puntual.
     *
     * @param cuenta
     *            the cuenta
     * @param reporteSeleccionado
     *            the reporte seleccionado
     * @return the resumen params
     */
    private ResumenParams configurarParamentrosConsultaPuntual(AbstractCuenta cuenta,
            ReporteSeleccionado reporteSeleccionado) {
        ResumenParams params = new ResumenParams();
        params.setUsuarioConsulta(cuenta.getCliente().getNup());

        Cuenta cuentaAUX = new Cuenta();
        cuentaAUX.setCbu(cuenta.getCbu());
        cuentaAUX.setTipoCuentaEnum(cuenta.getTipoCuentaEnum());
        cuentaAUX.setCliente(cuenta.getCliente());
        cuentaAUX.setNroCuentaProducto(cuenta.getNroCuentaProducto());
		cuentaAUX.setNroSucursal(StringUtils.substring(cuenta.getNroSucursal(), -3));

		if (!TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum())) {
			String nroCuentaConcat = StringUtils.stripStart(concatenarCBUNroCuentaProd(cuentaAUX),
					ISBANStringUtils.ZERO_STR);
            cuentaAUX.setNroCuentaProducto(nroCuentaConcat);
        }
        params.setCuenta(cuentaAUX);
        params.setFolder(reporteSeleccionado.getFolder());
        params.setFechaPuntual(reporteSeleccionado.getFechaPuntual());
        params.setProveedorTarjeta(reporteSeleccionado.getProveedorTarjeta());

        return params;
    }

    /**
     * Concatenar CB U nro cuenta prod.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    private String concatenarCBUNroCuentaProd(AbstractCuenta cuenta) {
        String cbu = cuenta.getCbu();
        return cuenta.getNroCuentaProducto().concat(cbu.substring(18, 19));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerResumenesPDF(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public Respuesta<ReporteResumenMensualCuenta> obtenerResumenesPDF(List<ResumenMensualCuenta> resumenesSeleccionados,
            AbstractCuenta cuenta) {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();

        respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        try {
            if (!resumenesSeleccionados.isEmpty()) {
                ReporteResumenMensualCuenta reporteResumenMensualCuenta = obtenerReporteResumenSegunCliente(
                        cuenta.getCliente(), resumenesSeleccionados.get(0), cuenta);
                reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
                respuestaBO.armarRespuestaOk(respuesta, reporteResumenMensualCuenta);
                return respuesta;
            }
            LOGGER.debug(ERROR_AL_DESCARGAR_RESUMENES);
            respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.CODIGO_ERROR_ERROR_DESCARGA_RESUMEN, "");
        } catch (WSODException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.CODIGO_ERROR_ERROR_DESCARGA_RESUMEN, "");
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.CODIGO_ERROR_ERROR_DESCARGA_RESUMEN, "");
        } finally {
            eliminarTemp(cuenta.getNroCuentaProducto());
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerResumenPuntualPDF(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ReporteResumenPuntual> obtenerResumenPuntualPDF(List<ReporteSeleccionado> resumenPuntualCuenta,
            IdentificacionCuenta idCuenta, Cliente cliente, Integer id) throws BusinessException {
        Respuesta<ReporteResumenPuntual> respuesta = new Respuesta<ReporteResumenPuntual>();
        respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        try {
            Respuesta<ReporteResumenPuntual> retMetodo = getRespuestaSegunArchivoPuntual(resumenPuntualCuenta, id,
                    cliente, idCuenta, respuesta);
            if (retMetodo != null) {
                return retMetodo;
            }
            return crearRespuestaError();
        } catch (WSODException e) {
            LOGGER.error(ERROR_AL_GENERAR_PDF, e);
            return crearRespuestaError();
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_AL_GENERAR_PDF, e);
            return crearRespuestaError();
        } catch (OnDemandDAOException e) {
            LOGGER.error(ERROR_AL_GENERAR_PDF, e);
            return crearRespuestaError();
        }
    }

    /**
     * Gets the respuesta segun archivo puntual.
     *
     * @param resumenPuntualCuenta
     *            the resumen puntual cuenta
     * @param id
     *            the id
     * @param cliente
     *            the cliente
     * @param idCuenta
     *            the id cuenta
     * @param respuesta
     *            the respuesta
     * @return the respuesta segun archivo puntual
     * @throws WSODException
     *             the WSOD exception
     * @throws OnDemandDAOException
     *             the on demand DAO exception
     */
    @SuppressWarnings("unused")
    private Respuesta<ReporteResumenPuntual> getRespuestaSegunArchivoPuntual(
            List<ReporteSeleccionado> resumenPuntualCuenta, Integer id, Cliente cliente, IdentificacionCuenta idCuenta,
            Respuesta<ReporteResumenPuntual> respuesta) throws WSODException, OnDemandDAOException {
        String esAmex = resumenPuntualCuenta.get(id).getFolder();
        if (AMEX.equals(esAmex)) {
            String nroCuentaProductoAUX = resumenPuntualCuenta.get(id).getNumeroCuenta();
            idCuenta.setNroCuentaProducto(nroCuentaProductoAUX);
        }
        Cuenta cuenta = (Cuenta) cuentaBO.buscarCuentaPorId(cliente, idCuenta);
        String marca = TarjetaUtils.getMarcaAux(cuenta);

        ReporteSeleccionado resumen = obtenerResumen(resumenPuntualCuenta, id);
        if (resumen != null) {
            if (AMEX.equals(marca)) {
                ReporteResumenPuntual reporteResumenPuntual = llamadaOnDemandSantanderRio(cuenta, resumen);
                return armarRespuestaSegunArchivoPuntual(reporteResumenPuntual, TipoArchivoEnum.PDF, respuesta, null);
            }
            if (MASTER.equals(marca) || VISA.equals(marca)) {
                if (StringUtils.defaultString(resumen.getDocId()).equals("")) {
                    ReporteResumenPuntual reporteResumenPuntual = llamadaOnDemandSantanderRio(cuenta, resumen);
                    return armarRespuestaSegunArchivoPuntual(reporteResumenPuntual, TipoArchivoEnum.PDF, respuesta,
                            null);
                } else {
                    ReporteResumenPuntual reporteResumenPuntual = resumenesCitiDAO
                            .consultarResumenesTarjetaMastercard(resumen, marca);
                    return armarRespuestaSegunArchivoPuntual(reporteResumenPuntual, TipoArchivoEnum.PDF, respuesta,
                            null);

                }
            }
        }

        return null;
    }

    /**
	 * Llamada on demand santander.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param resumen
	 *            the resumen
	 * @return the reporte resumen puntual
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 */
    private ReporteResumenPuntual llamadaOnDemandSantanderRio(Cuenta cuenta, ReporteSeleccionado resumen)
            throws WSODException, OnDemandDAOException {
        ResumenParams params = configurarParamentrosConsultaPuntual(cuenta, resumen);
        ReporteResumenPuntual reporteResumenPuntual = ondemanDao.obtenerResumenPuntualPDF(resumen, params);
        return reporteResumenPuntual;
    }

    /**
     * Respuesta ERROR TipoError.ERROR_CARGA_CUOTAS_PENDIENTE. (110005)
     *
     * @return the respuesta
     */
    private Respuesta<ReporteResumenPuntual> crearRespuestaError() {
        return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_DESCARGA_RESUMEN,
                CodigoMensajeConstantes.CODIGO_ERROR_ERROR_DESCARGA_RESUMEN);
    }

    /**
     * Obtener resumen.
     *
     * @param resumenPuntualCuenta
     *            the resumen puntual cuenta
     * @param id
     *            the id
     * @return the reporte seleccionado
     */
    private ReporteSeleccionado obtenerResumen(List<ReporteSeleccionado> resumenPuntualCuenta, Integer id) {

        ReporteSeleccionado resumenMensual = null;
        for (ReporteSeleccionado resumen : resumenPuntualCuenta) {
            if (resumen.getId().equals(id)) {
                resumenMensual = resumen;
                break;
            }
        }
        return resumenMensual;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerResumenDescargaMultiple(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public Respuesta<ReporteResumenMensualCuenta> obtenerResumenDescargaMultiple(
            List<ResumenMensualCuenta> resumenesSeleccionados, AbstractCuenta cuenta, int cantidadADescargar)
            throws BusinessException {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();

        respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        try {
            if (!resumenesSeleccionados.isEmpty()) {
                ReporteResumenMensualCuenta reporteResumenMensualCuenta = obtenerReporteResumenSegunCliente(
                        cuenta.getCliente(), resumenesSeleccionados.get(0), cuenta);
                reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
                respuestaBO.armarRespuestaOk(respuesta, reporteResumenMensualCuenta);
                return respuesta;
            }
        } catch (WSODException e) {
            armarRespuestaDescargarItemError(respuesta, resumenesSeleccionados.get(0), cantidadADescargar);
        } catch (RuntimeException e) {
            armarRespuestaDescargarItemError(respuesta, resumenesSeleccionados.get(0), cantidadADescargar);
        }
        return respuesta;
    }

    /**
     * Eliminar temp.
     *
     * @param nroCuenta
     *            the nro cuenta
     */
    private void eliminarTemp(String nroCuenta) {
        try {
            FileUtils.deleteDirectory(new File(rutaArchivosTmp + File.separator + nroCuenta));
        } catch (IOException e) {
            LOGGER.error("Error al borrar Archivo", e);
        }
    }

    /**
     * Armar respuesta descargar item error.
     *
     * @param respuesta
     *            the respuesta
     * @param resumenMensualCuenta
     *            the resumen mensual cuenta
     * @param cantidadADescargar
     *            the cantidad A descargar
     */
    private void armarRespuestaDescargarItemError(Respuesta<ReporteResumenMensualCuenta> respuesta,
            ResumenMensualCuenta resumenMensualCuenta, int cantidadADescargar) {
        Mensaje mensaje;
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTag("resumenes[" + resumenMensualCuenta.getId().toString() + "]");
        if (cantidadADescargar > 1) {
            mensaje = mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE_VARIOS);
        } else {
            mensaje = mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE_UNO);
        }
        itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_DESCARGA_RESUMEN_MULTIPLE.getDescripcion());
        respuesta.getItemsMensajeRespuesta().add(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
    }

    /**
     * Armar respuesta descargar item puntual error.
     *
     * @param respuesta
     *            the respuesta
     * @param resumenMensualCuenta
     *            the resumen mensual cuenta
     */
    private void armarRespuestaDescargarItemPuntualError(Respuesta<ReporteResumenPuntual> respuesta,
            ResumenMensualCuenta resumenMensualCuenta) {
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTag("resumenes[" + resumenMensualCuenta.getId().toString() + "]");
        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE);
        itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_DESCARGA_RESUMEN_MULTIPLE.getDescripcion());
        respuesta.getItemsMensajeRespuesta().add(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
    }

    /**
     * Armar respuesta segun archivo puntual.
     *
     * @param reporte
     *            the reporte
     * @param tipoArchivo
     *            the tipo archivo
     * @param respuestaReporte
     *            the respuesta reporte
     * @param resumen
     *            the resumen
     * @return the respuesta
     */
    private Respuesta<ReporteResumenPuntual> armarRespuestaSegunArchivoPuntual(ReporteResumenPuntual reporte,
            TipoArchivoEnum tipoArchivo, Respuesta<ReporteResumenPuntual> respuestaReporte,
            ResumenMensualCuenta resumen) {

        reporte.setTipoArchivo(tipoArchivo);
        if (respuestaReporte.getItemsMensajeRespuesta().isEmpty()) {
            // no hubo errores al armar el pdf
            respuestaBO.armarRespuestaOk(respuestaReporte, reporte);
        } else {
            respuestaBO.armarRespuestaWarning(respuestaReporte, respuestaReporte.getItemsMensajeRespuesta(),
                    CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE);
            if (resumen != null) {
                armarRespuestaDescargarItemPuntualError(respuestaReporte, resumen);
            }
        }
        return respuestaReporte;
    }

    /**
     * Restar meses.
     *
     * @param fecha
     *            the fecha
     * @param cantMeses
     *            the cant meses
     * @return the date
     */
    private Date restarMeses(Date fecha, int cantMeses) {
        Calendar fechaCalendar = new GregorianCalendar();
        fechaCalendar.setTime(fecha);
        fechaCalendar.add(Calendar.MONTH, -cantMeses);
        return fechaCalendar.getTime();
    }

    /**
     * Validar fechas.
     *
     * @param fechaDesde
     *            the fecha desde
     * @param fechaHasta
     *            the fecha hasta
     * @return true, if successful
     */
    private boolean validarFechas(Date fechaDesde, Date fechaHasta) {
        boolean result = false;
        if (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) <= 0) {
            result = true;
        }
        return result;
    }

    /**
     * Obtener lista resumen segun cliente.
     *
     * @param cliente
     *            the cliente
     * @param params
     *            the params
     * @return the list
     * @throws WSODException
     *             the WSOD exception
     */
    private List<ResumenMensualCuenta> obtenerListaResumenSegunCliente(Cliente cliente, ResumenParams params)
            throws WSODException {
        List<ResumenMensualCuenta> listaResumenes = null;
		Cuenta cuentaCliente = null;
		
		if (isDuoOPyme(cliente)) {
			//if (contieneCuadranteAdvance(cliente) && cliente.getSegmento().getPesubseg().equals(PESUBSEG)){
			//cuentaCliente = obtenerCuentaClientePorCuentaPivote(cliente, obtenerCuentasPivote(params));
			cuentaCliente = obtenerCuentasPivote(params);
				if (null != cuentaCliente) {
					params.setCuenta(cuentaCliente);
		            listaResumenes = ondemanDao.obtenerListaResumenAdvance(params);
				}else {
					listaResumenes = ondemanDao.obtenerListaResumen(params);
				}
			//}
        } else {
            listaResumenes = ondemanDao.obtenerListaResumen(params);
        }
		
        // Recorre cada resumen y le agrega un id que sera utilizado para
        // identicar que resumen se quiere descargar
        if (listaResumenes != null) {
            Long id = Long.valueOf(0);
            for (ResumenMensualCuenta resumenMensualCuenta : listaResumenes) {
                resumenMensualCuenta.setId(id);
                id++;
            }
        }
        return listaResumenes;
    }

    /**
     * Obtener cuenta pivote.
     *
     * @param params the params
     * @return the cuenta on demand DTO
     * Se agrega un atributo en session para invocar una sola vez CTAXNUP y se graba en las cuentas
     *  de session del cliente cual es pivote y cual no en el atributo isPivote
     */
	private Cuenta obtenerCuentasPivote(ResumenParams params) {		
		Cliente cliente = sesionCliente.getCliente();		
		Cuenta cta = null;
    	if(!sesionCliente.getInvocoCtaPivote()) { 
			List<CuentaOnDemandDTO> listaCuentas = resumenesCuentaDAO.ejecutarCTAXNUP(cliente);								
			if (!listaCuentas.isEmpty()) {
				sesionCliente.setInvocoCtaPivote(true);
				guardarCtasPivoteCtaXnupEnSession(listaCuentas);
				cta = retornarCtaPivoteXnupEnSession(params);	
			}
		}else { 
	    	cta = retornarCtaPivoteXnupEnSession(params);
		}	
		return cta;
	}
	
	

	/**
	 * Guardar ctas pivote cta xnup en session.
	 *
	 * @param listaCuentas the lista cuentas
	 */
	private void guardarCtasPivoteCtaXnupEnSession(List<CuentaOnDemandDTO> listaCuentas) {		
		Cliente cliente = sesionCliente.getCliente();		
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();    	
		cuentas.addAll(cliente.getCuentas());				
		for (CuentaOnDemandDTO cuenta : listaCuentas) {		            
			for (Cuenta cuentaSession : cuentas) {
				if (obtenerNroCuentaUltimos12Nros(cuentaSession.getNroCuentaProducto()).equals(obtenerNroCuentaUltimos12Nros(cuenta.getCuenta())) && cuenta.getCtaPivote().equals("S")) {
					cuentaSession.setPivote(true);							
				}else {
					cuentaSession.setPivote(false);
				}
			}
        }			
	}
	
	
	/**
	 * Retornar cta pivote xnup en session.
	 *
	 * @param params the params
	 * @return the cuenta
	 */
	private Cuenta retornarCtaPivoteXnupEnSession(ResumenParams params) {		
		String nroCtaProd = params.getCuenta().getNroCuentaProducto();
		Cuenta ctaEncontrada = null;
		Cliente cliente = sesionCliente.getCliente();		
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();    	
		cuentas.addAll(cliente.getCuentas());						            
		for (Cuenta cuentaSession : cuentas) {						
			if (obtenerNroCuentaUltimos12Nros(cuentaSession.getNroCuentaProducto()).equals(obtenerNroCuentaUltimos12Nros(nroCtaProd)) && (cuentaSession.isPivote())) {
				ctaEncontrada =  cuentaSession;
				break;
			}
		}
	return ctaEncontrada;
	}
	

	/**
	 * Obtener cuenta cliente por cuenta pivote.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaPivote
	 *            the cuenta pivote
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaClientePorCuentaPivote(Cliente cliente, CuentaOnDemandDTO cuentaPivote) {
		if (null == cuentaPivote)
			return null;

		for (Cuenta cuenta : cliente.getCuentas()) {
			if (cuenta.getTipoCuenta().equalsIgnoreCase(cuentaPivote.getTipoCuenta())
					&& cuenta.getNroSucursal().equals(cuentaPivote.getCentroAlta())
					&& obtenerNroCuentaUltimos12Nros(cuenta.getNroCuentaProducto()).equals(obtenerNroCuentaUltimos12Nros(cuentaPivote.getCuenta()))) {
				return cuenta;
			}
		}
		return null;
	}
	
	/**
	 * Obtener nro cuenta ultimos 12 nros.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the string
	 */
	private String obtenerNroCuentaUltimos12Nros(String nroCuenta) {
		if (!StringUtils.defaultString(nroCuenta).isEmpty())
			return nroCuenta.substring(nroCuenta.length()-12, nroCuenta.length());
		else
			return "";
	}
	/**
     * Obtener reporte resumen segun cliente.
     *
     * @param cliente
     *            the cliente
     * @param resumenMensualCuenta
     *            the resumen mensual cuenta
     * @param cuenta
     *            the cuenta
     * @return the reporte resumen mensual cuenta
     * @throws WSODException
     *             the WSOD exception
     */
    private ReporteResumenMensualCuenta obtenerReporteResumenSegunCliente(Cliente cliente,
            ResumenMensualCuenta resumenMensualCuenta, AbstractCuenta cuenta) throws WSODException {
        if (resumenMensualCuenta.getDocId() != null) {
            ResumenCitiIn resumenCitiIn = new ResumenCitiIn();
            resumenCitiIn.setId(resumenMensualCuenta.getDocId());
            try {
                return resumenesCitiDAO.consultarResumenCityPorId(resumenMensualCuenta);
            } catch (DAOException e) {
                throw new WSODException(e);
            }
        }
        if (isDuoOPyme(cliente) && resumenMensualCuenta.isAdvance()) {
            return ondemanDao.obtenerReporteMensualAdvance(resumenMensualCuenta, cuenta);
        } else {
            return ondemanDao.obtenerReporteMensual(resumenMensualCuenta, cuenta);
        }
    }

    /**
     * Checks if is duo O pyme.
     *
     * @param cliente the cliente
     * @return true, if is duo O pyme
     */
    private boolean isDuoOPyme(Cliente cliente) {
    	Segmento segmento = cliente.getSegmento();
        return segmento != null && (segmento.isDuo() || segmento.isPyme());
    }

	/**
	 * Contiene cuadrante advance.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	private boolean contieneCuadranteAdvance(Cliente cliente) {
		if (cliente.getSegmento().getCuadrante().equals(CUADRANTE_VALIDO_ADVANCE_1)
				|| cliente.getSegmento().getCuadrante().equals(CUADRANTE_VALIDO_ADVANCE_2)
				|| cliente.getSegmento().getCuadrante().equals(CUADRANTE_VALIDO_ADVANCE_3))
			return true;
		else
			return false;
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerMensajeInformativoDescargaResumenes()
     */
    @Override
    public String obtenerMensajeInformativoDescargaResumenes() {
        String mensajeInformativo;
        try {
            Mensaje mensaje = mensajeDAO
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_INFORMATIVO_DESCARGA_RESUMENES);
            mensajeInformativo = mensaje.getMensaje();
        } catch (RuntimeException e) {
            mensajeInformativo = null;
        }
        return mensajeInformativo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * obtenerMensajeErrorPorCantidadFallidos(int)
     */
    @Override
    public ItemMensajeRespuesta obtenerMensajeErrorPorCantidadFallidos(int size) {
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        if (size < 2) {
            item.setMensaje(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_RESUMEN_UNICA_DESCARGA)
                    .getMensaje());
            item.setTag("errorUnicaDescarga");
            item.setTipoError(TipoError.ERROR_DESCARGA_SINGULAR.getDescripcion());
        } else {
            item.setMensaje(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_RESUMEN_VARIAS_DESCARGAS)
                    .getMensaje());
            item.setTag("errorVariasDescargas");
            item.setTipoError(TipoError.ERROR_DESCARGA_MULTIPLE.getDescripcion());
        }
        return item;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * cambiarMarcaImpresion(ar.com.santanderrio.obp.servicios.cuentas.entities.
     * AdhesionResumenDTO)
     */
    @Override
    public Respuesta<Void> cambiarMarcaImpresion(AdhesionResumenDTO adhesionResumenDTO) {

        LOGGER.info("Modifico marca de Impresion.");
        Respuesta<Void> respuesta = respuestaFactory.crearRespuestaOk(Void.class);
        ResumenParams params = new ResumenParams();
        ResumenInEntity resumenInEntity = new ResumenInEntity();

        params.setCuenta(adhesionResumenDTO.getCuenta());
        params.setSoporte(adhesionResumenDTO.getAdhesionAutomatica());
        params.setMotivoConsulta(adhesionResumenDTO.getOpinionUsuario());
        params.setMoneda(adhesionResumenDTO.getCuenta().getMonedaAltair());
        params.setUsuarioConsulta(adhesionResumenDTO.getCuenta().getCliente().getNup());
        String codAplicacion = adhesionResumenDTO.getCuenta().getCodigoAplicacion();
        params.setFiltroAplicacion(APLICACION_INFI.equals(codAplicacion) ? APLICACION_ACTE : codAplicacion);
        resumenInEntity.setNup(adhesionResumenDTO.getCuenta().getCliente().getNup());
        resumenInEntity.setMotivo(adhesionResumenDTO.getOpinionUsuario());

        try {
            ondemanDao.modificarMarcaImpresion(params);

        } catch (WSODException e) {
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
            LOGGER.error("Error en WS", e);
        } catch (OnDemandDAOException e) {
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
            LOGGER.error("Respuesta con Error", e);
        } catch (ProcessingException e) {
            LOGGER.error("Error en el servicio", e);

            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    StringUtils.EMPTY);
        }
        try {
            if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

                resumenDAO.grabarMotivo(resumenInEntity);
            }
        } catch (DAOException e) {
            LOGGER.error("Error SP al grabar motivo", e);
            return respuesta;
        }
        LOGGER.info("FIN - Modifico marca de Impresion.");
        return respuesta;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#
     * crearComprobanteAdhesionResumen(java.lang.String)
     */
    @Override
    public ComprobanteAdhesionResumenDTO crearComprobanteAdhesionResumen(String pid) {
        ComprobanteAdhesionResumenDTO comprobanteAdhesionResumenDTO = new ComprobanteAdhesionResumenDTO();

        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(Calendar.YEAR, 2003);
        cal.set(Calendar.MONTH, 05);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        long segs = cal.getTime().getTime() / 1000;
        String nroComp = pid + (System.currentTimeMillis() / 1000 - segs);
        comprobanteAdhesionResumenDTO.setComprobante(nroComp);

        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentData = sdf.format(d);
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String currentData2 = sdf2.format(d);
        comprobanteAdhesionResumenDTO.setFecha(currentData);
        comprobanteAdhesionResumenDTO.setHora(currentData2);
        return comprobanteAdhesionResumenDTO;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO#consultarFechasPorCuenta(java.lang.String)
     */
    @Override
    public Respuesta<List<ResumenMensualCuenta>> consultarFechasPorCuenta(String string) {

        FechasResumenCitiIn fechasResumenCitiIn = new FechasResumenCitiIn();

        List<ResumenMensualCuenta> listaResumenCuentas = new ArrayList<ResumenMensualCuenta>();

        Boolean errorParcial = Boolean.FALSE;

        Respuesta<List<ResumenMensualCuenta>> respuesta = null;

        try {

            Date referenceDate = new DateTime().minusMonths(18).toDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fechasResumenCitiIn.setFechaCierreDesde(sdf.format(referenceDate));
            fechasResumenCitiIn.setDocumento("");
            fechasResumenCitiIn.setCuenta(string);
            fechasResumenCitiIn.setSucursal("999");
            fechasResumenCitiIn.setFechaCierreHaste(sdf.format(new Date()));

            List<ResumenFechaOutEntity> fechas = resumenesCitiDAO.consultarFechasPorCuenta(fechasResumenCitiIn);

            if (CollectionUtils.isNotEmpty(fechas)) {

                for (ResumenFechaOutEntity resumenFechaOutEntity : fechas) {
                    ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
                    // Ojo aca dale formato
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    try {
                        resumenMensualCuenta.setFecha(format.parse(resumenFechaOutEntity.getFecha()));
                        resumenMensualCuenta.setDocId(resumenFechaOutEntity.getDocId());
                        resumenMensualCuenta.setVisto(false);
                        listaResumenCuentas.add(resumenMensualCuenta);
                    } catch (ParseException e) {
                        LOGGER.error("Falla parseo fecha resumen Citi.");
                        errorParcial = Boolean.TRUE;
                    }
                }
            }
        } catch (DAOException e) {

            LOGGER.error("Error consulta resumenes citi.-");
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_PARCIAL_CONSULTA_RESUMENES,
                    StringUtils.EMPTY);
        }

        if (errorParcial) {

            respuesta = respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                    TipoError.ERROR_PARCIAL_CONSULTA_RESUMENES, StringUtils.EMPTY);
            respuesta.setRespuesta(listaResumenCuentas);

        } else {
            respuesta = respuestaFactory.crearRespuestaOk(listaResumenCuentas);
        }
        return respuesta;
    }

}
