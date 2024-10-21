package ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.ResumenMensualInversionesBO;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenFinanciacion;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;

/**
 * The Class ResumenMensualInversionesBOImpl.
 */
@Component
public class ResumenMensualInversionesBOImpl implements ResumenMensualInversionesBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResumenMensualInversionesBOImpl.class);

    /** The Constant ERROR_AL_DESCARGAR_RESUMENES. */
    private static final String ERROR_AL_DESCARGAR_RESUMENES = "Error al descargar resumenes";

    /** The Constant MOTIVO. */
    private static final String MOTIVO = "Motivo";

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The ondeman dao. */
    @Autowired
    private OndemandDAO ondemanDao;

    /** The cantidad meses. */
    @Value("${RESUMENONDEMANDINV.MESES}")
    private String cantidadMeses;

    /** The ruta archivos tmp. */
    @Value("${RESUMENONDEMAND.RUTA_ARCHIVOS_TMP}")
    private String rutaArchivosTmp;

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "dd/MM/yy";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.
	 * ResumenMensualInversionesBO#obtenerListaResumen(ar.com.santanderrio.obp.
	 * servicios.cuentas.entities.AbstractCuenta, boolean)
	 */
    @Override
    public Respuesta<List<ResumenMensualInversiones>> obtenerListaResumen(AbstractCuenta cuenta, boolean isBP)
            throws BusinessException {
        try {
            ResumenParams params = configurarParametrosConsultaLista(cuenta);
            if (params == null) {
            	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CONSULTA_RESUMENES,
                        CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV);
            }
            List<ResumenMensualInversiones> listaResumen = obtenerListaResumen(params, isBP);
            if (listaResumen != null && !listaResumen.isEmpty()) {
                return respuestaFactory.crearRespuestaOk(listaResumen);
            } else {
            	Respuesta<List<ResumenMensualInversiones>> respuesta = respuestaFactory.crearRespuestaWarning(null, "", TipoError.SIN_RESUMENES,
            	        obtenerCodigoMensajeWarning(isBP));
                respuesta.setRespuestaVacia(true);
                return respuesta;
            }
        } catch (WSODException e) {
        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CONSULTA_RESUMENES,
                    CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV);
        } catch (RuntimeException e) {
        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CONSULTA_RESUMENES,
                    CodigoMensajeConstantes.ERROR_LISTA_RESUMENES_INV);
        }
    }

    /**
     * Obtener codigo mensaje error.
     *
     * @param isBP the is BP
     * @return the string
     */
    private String obtenerCodigoMensajeWarning(boolean isBP) {
        if (tieneCuentasMultiples(isBP)) {
            return isBP ? CodigoMensajeConstantes.ULTIMOS_RESUMENES_SIN_RESUMENES_INV_CTA_MULTIPLE_BP : CodigoMensajeConstantes.ULTIMOS_RESUMENES_SIN_RESUMENES_INV_CTA_MULTIPLE; 
        } else {
            return CodigoMensajeConstantes.ULTIMOS_RESUMENES_SIN_RESUMENES_INV;
        }
    }

    /**
     * Tiene cuentas multiples.
     *
     * @param isBP the is BP
     * @return true, if successful
     */
    private boolean tieneCuentasMultiples(boolean isBP) {
        Cliente cliente = sesionCliente.getCliente();
        if (!isBP) {
            return cliente.getCuentasRetail().size() > 1;
        } else {
            return cliente.getCuentasBancaPrivada().size() > 1;
        }
    }

    /**
     * Configurar parametros consulta lista.
     *
     * @param cuenta
     *            the cuenta
     * @return the resumen params
     */
    private ResumenParams configurarParametrosConsultaLista(AbstractCuenta cuenta) {
        ResumenParams params = new ResumenParams();
        params.setUsuarioConsulta(cuenta.getCliente().getNup());
        params.setCuenta(cuenta);
        params.setMotivoConsulta(MOTIVO);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.
	 * ResumenMensualInversionesBO#obtenerResumenesPDF(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta, boolean)
	 */
    @Override
    public Respuesta<ReporteResumenMensualInversiones> obtenerResumenesPDF(
            List<ResumenMensualInversiones> resumenesSeleccionados, AbstractCuenta cuenta, boolean isBP) {
        try {
            if (!resumenesSeleccionados.isEmpty()) {
                ReporteResumenMensualInversiones reporteResumenMensualInversiones = obtenerReporteResumen(
                        resumenesSeleccionados.get(0), cuenta, isBP);
                reporteResumenMensualInversiones.setTipoArchivo(TipoArchivoEnum.PDF);
                return respuestaFactory.crearRespuestaOk(ReporteResumenMensualInversiones.class, reporteResumenMensualInversiones);
            }
            LOGGER.debug(ERROR_AL_DESCARGAR_RESUMENES);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_INV);
        } catch (WSODException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_INV);
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_INV);
        } finally {
            eliminarTemp(cuenta.getNroCuentaProducto());
        }
    }
    
    @Override
    public Respuesta<ReporteResumenFinanciacion> obtenerResumenFinancieroTenenciasPDF(ResumenFinancieroDTO dto) {
        try {            
            ReporteResumenFinanciacion reporteResumenFinanciacion = ondemanDao.obtenerResumenFinancieroTenenciasPDF(dto);
            reporteResumenFinanciacion.setTipoArchivo(TipoArchivoEnum.PDF);
            return respuestaFactory.crearRespuestaOk(ReporteResumenFinanciacion.class, reporteResumenFinanciacion);
        } catch (WSODException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_RESUMEN,
                    CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_FINANCIERO);
        } catch (RuntimeException e) {
            LOGGER.error(ERROR_AL_DESCARGAR_RESUMENES, e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGA_RESUMEN,
            		CodigoMensajeConstantes.RESUMEN_FINANCIERO_SIN_RESUMENES);
        } /*finally {
            eliminarTemp("");
        }*/
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.bo.
	 * ResumenMensualInversionesBO#obtenerResumenDescargaMultiple(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta, boolean,
	 * int)
	 */
    @Override
    public Respuesta<ReporteResumenMensualInversiones> obtenerResumenDescargaMultiple(
            List<ResumenMensualInversiones> resumenesSeleccionados, AbstractCuenta cuenta, boolean isBP, int cantidadADescargar)
            throws BusinessException {
        try {
            if (!resumenesSeleccionados.isEmpty()) {
                ReporteResumenMensualInversiones reporteResumenMensualInversiones = obtenerReporteResumen(
                        resumenesSeleccionados.get(0), cuenta, isBP);
                reporteResumenMensualInversiones.setTipoArchivo(TipoArchivoEnum.PDF);
                return respuestaFactory.crearRespuestaOk(ReporteResumenMensualInversiones.class, reporteResumenMensualInversiones);
            }
        } catch (WSODException e) {
            return armarRespuestaDescargarItemError(resumenesSeleccionados.get(0), cantidadADescargar);
        } catch (RuntimeException e) {
        	return armarRespuestaDescargarItemError(resumenesSeleccionados.get(0), cantidadADescargar);
        }
        return null;
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
     * @param resumenMensualInversiones the resumen mensual inversiones
     * @param cantidadADescargar the cantidad A descargar
     * @return the respuesta
     */
    private Respuesta<ReporteResumenMensualInversiones> armarRespuestaDescargarItemError(ResumenMensualInversiones resumenMensualInversiones, int cantidadADescargar) {
        Mensaje mensaje;
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTag("resumenes[" + resumenMensualInversiones.getId().toString() + "]");
        if (cantidadADescargar > 1) {
            mensaje = mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_VARIOS_INV);
        } else {
            mensaje = mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_DESCARGA_MULTIPLE_UNO_INV);
        }
        itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_DESCARGA_RESUMEN_MULTIPLE.getDescripcion());
        Respuesta<ReporteResumenMensualInversiones> respuesta = new Respuesta<ReporteResumenMensualInversiones>();
        respuesta.add(itemMensajeRespuesta);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        return respuesta;
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
     * Obtener lista resumen.
     *
     * @param cliente
     *            the cliente
     * @param params
     *            the params
     * @param isBP
     *            the is BP
     * @return the list
     * @throws WSODException
     *             the WSOD exception
     */
    private List<ResumenMensualInversiones> obtenerListaResumen(ResumenParams params, boolean isBP) throws WSODException {

        List<ResumenMensualInversiones> listaResumenes = ondemanDao.obtenerListaResumenInversiones(params, isBP);

		Collections.sort(listaResumenes, new Comparator<ResumenMensualInversiones>() {
			@Override
			public int compare(ResumenMensualInversiones resumen1, ResumenMensualInversiones resumen2) {
				String fechaDesde1 = getFechaYYYYMMDD(resumen1.getFechaDesde());
				String fechaDesde2 = getFechaYYYYMMDD(resumen2.getFechaDesde());
				int res = -1 * fechaDesde1.compareTo(fechaDesde2);
				if (res != 0) {
					return res;
				} else {
					String fechaHasta1 = getFechaYYYYMMDD(resumen1.getFechaHasta());
					String fechaHasta2 = getFechaYYYYMMDD(resumen2.getFechaHasta());
					res = -1 * fechaHasta1.compareTo(fechaHasta2);
					if (res != 0) {
						return res;
					} else {
						String periodo1 = resumen1.getPeriodo().toLowerCase();
						String periodo2 = resumen2.getPeriodo().toLowerCase();
						return -1 * periodo1.compareTo(periodo2);
					}
				}
			}

			private String getFechaYYYYMMDD(String fecha) {
				return "20" + fecha.substring(6, 8) + fecha.substring(3, 5)
						+ fecha.substring(0, 2);
			}
		});

        // Recorre cada resumen y le agrega un id que sera utilizado para
        // identicar que resumen se quiere descargar
        if (listaResumenes != null) {
            Long id = Long.valueOf(0);
            for (ResumenMensualInversiones resumenMensualInversiones : listaResumenes) {
                resumenMensualInversiones.setId(id);
                id++;
            }
        }

        return listaResumenes;
    }

    /**
     * Obtener reporte resumen.
     *
     * @param cliente
     *            the cliente
     * @param resumen
     *            the resumen
     * @param cuenta
     *            the cuenta
     * @param isBP
     *            the is BP
     * @return the reporte resumen mensual inversiones
     * @throws WSODException
     *             the WSOD exception
     */
    private ReporteResumenMensualInversiones obtenerReporteResumen(ResumenMensualInversiones resumen,
            AbstractCuenta cuenta, boolean isBP) throws WSODException {
        return ondemanDao.obtenerReporteMensualInversiones(resumen, cuenta, isBP);
    }
}
