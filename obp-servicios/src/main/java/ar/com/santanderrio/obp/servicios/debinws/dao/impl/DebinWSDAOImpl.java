package ar.com.santanderrio.obp.servicios.debinws.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException_Exception;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebito;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebitoV3;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseContracargo;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseListaDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResumenDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.V3_002fDebinClient;
import ar.com.santanderrio.obp.servicios.debinws.common.DebinWSConstants;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteInDTO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 * The Class DebinWSDAOImpl.
 */
@Component
@Qualifier("debinWSDAOImpl")
public class DebinWSDAOImpl  extends IatxBaseDAO implements DebinWSDAO   {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(DebinWSDAOImpl.class);
	
	/** The Constant TIME_OUT_CODE. */
	protected static final String TIME_OUT_CODE = "099";
    
    /** The file jasper comprobante confirmacion debito. */
    @Value("classpath:/report/debinWS/ComprobanteConfirmacionDebito.jasper")
    private Resource fileJasperComprobanteConfirmacionDebito;

    /** The file jasper comprobante rechazo debin. */
    @Value("classpath:/report/debinWS/ComprobanteRechazoDebin.jasper")
    private Resource fileJasperComprobanteRechazoDebin;

    /** The file jasper comprobante eliminacion debin. */
    @Value("classpath:/report/debinWS/ComprobanteEliminacionDebin.jasper")
    private Resource fileJasperComprobanteEliminacionDebin;
    
    /** The logo cierre. */
    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    protected Resource logoCierre;

    /** The logo cabecera. */
    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    protected Resource logoCabecera;

    /** The logo debito. */
    @Value("classpath:/report/comprobantes/logoDebito.png")
    private Resource logoDebito;

    /** The Constant LOGO_CABECERA. */
    protected static final String LOGO_CABECERA = "LOGO_CABECERA";

    /** The Constant IMPORTE. */
    private static final String IMPORTE = "IMPORTE";

    /** The Constant FECHA_SOLICITUD. */
    private static final String FECHA_SOLICITUD = "FECHA_SOLICITUD";

    /** The Constant FECHA_VENCIMIENTO. */
    private static final String FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";

    /** The Constant NUMERO_CUENTA. */
    private static final String NUMERO_CUENTA = "NUMERO_CUENTA";

    /** The Constant TIPO_CUENTA. */
    private static final String TIPO_CUENTA = "TIPO_CUENTA";

    /** The Constant SOLICITANTE. */
    private static final String SOLICITANTE = "SOLICITANTE";

    /** The Constant CUIT. */
    private static final String CUIT = "CUIT";

    /** The Constant CBU. */
    private static final String CBU = "CBU";

    /** The Constant ALIAS. */
    private static final String ALIAS = "ALIAS";

    /** The Constant DESCRIPCION. */
    private static final String DESCRIPCION = "DESCRIPCION";

    /** The Constant CONCEPTO. */
    private static final String CONCEPTO = "CONCEPTO";

    /** The Constant ID_DEBIN. */
    private static final String ID_DEBIN = "ID_DEBIN";

    /** The Constant COMPROBANTE. */
    protected static final String COMPROBANTE = "COMPROBANTE";

    /** The Constant FECHA. */
    protected static final String FECHA = "FECHA";

    /** The Constant LOGO_CIERRE. */
    protected static final String LOGO_CIERRE = "LOGO_CIERRE";

    /** The Constant PAGO. */
    private static final String PAGO = "ConfirmacionDebito";

    /** The Constant RECHAZO. */
    private static final String RECHAZO = "RechazoDebin";

    /** The Constant ELIMINACION. */
    private static final String ELIMINACION = "EliminacionDebin";

    /** The Constant TIPO_DEBIN_NOMBRE. */
    private static final String TIPO_DEBIN_NOMBRE = "TIPO_DEBIN_NOMBRE";

	/** The client. */
	@Autowired
	@Qualifier("gestionDebin")
	protected GestionarWS<V3_002fDebinClient> client;

	/**
	 * Consulta debin new.
	 *
	 * @param request the request
	 * @return the response debin
	 * @throws DAOException the DAO exception
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#consultaDebinNew(ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin)
	 */
	@Override
	public ResponseDebin consultaDebinNew(RequestDebin request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS CONSULTA DEBIN NEW:");
			services = client.obtenerPort();
			ResponseDebin respDebin = services.consultaDebinNew(request);
			loguearTramaDebinWS(respDebin, "RESPONSE DEBIN WS CONSULTA DEBIN NEW:");
			return respDebin;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion consultaDebinNew con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion consultaDebinNew con los datos {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion consultaDebinNew con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}
	
	/**
	 * Solicitud de contracargo (Desconocer Pago)
	 * 
	 * @param RequestContracargo
	 * @return ResponseContracargo
	 * @throws DAOException
	 */
	@Override
	public ResponseContracargo solicitarContracargo(RequestContracargo request ) throws DAOException {
		V3_002fDebinClient services = null;
		
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS SOLICIAR CONTRACARGO");
			services = client.obtenerPort();
			ResponseContracargo respDebin = services.solicitudContracargo(request);
			loguearTramaDebinWS(respDebin, "RESPONSE DEBIN WS SOLICIAR CONTRACARGO:");
			return respDebin;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion solicitarContracargo con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion solicitarContracargo con los datos {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion solicitarContracargo con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

	/**
	 * Loguear trama debin WS.
	 *
	 * @param obj the obj
	 * @param cabecera the cabecera
	 */
	protected void loguearTramaDebinWS(Object obj, String cabecera) {
        StringBuilder result = new StringBuilder();
        result.append( cabecera);
        Gson gson = new Gson();
        String out = gson.toJson(obj);
        result.append(" :");
        result.append( out);
        LOGGER.info(result.toString());
    }

    /**
     * Lista debin new.
     *
     * @param request the request
     * @return the response lista debin
     * @throws DAOException the DAO exception
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#listaDebinNew(ar.com.santanderrio.obp.generated.webservices.debin.RequestListaDebin)
     */
    @Override
	public ResponseListaDebin listaDebinNew(RequestListaDebin request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS LISTA_DEBIN_NEW");
			services = client.obtenerPort();
			ResponseListaDebin respListaDebin = services.listaDebinNew(request);
			loguearTramaDebinWS(respListaDebin, "RESPONSE DEBIN WS LISTA_DEBIN_NEW");

			// Filtro adicional para excluir debines plf web del listado por tipo debin
			if (DebinWSConstants.TIPO_CONSULTA_DEBIN.equalsIgnoreCase(request.getTipo()) && respListaDebin.getDebines() != null) {
				Iterator<ResumenDebinDTO> it = respListaDebin.getDebines().iterator();
				while (it.hasNext()) {
					ResumenDebinDTO debin = it.next();
					if (debin.getConcepto() != null && debin.getConcepto().contains("M")) {
						it.remove();
					}
				}
			}
			return respListaDebin;

		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException  Hubo un error al invocar al ws de Debin para la operacion listaDebinNew con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DAOException Hubo un error al invocar al ws de Debin para la operacion listaDebinNew con los datos {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion listaDebinNew con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

    /**
     * Eliminar debin.
     *
     * @param request the request
     * @return the response
     * @throws DAOException the DAO exception
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#eliminarDebin(ar.com.santanderrio.obp.generated.webservices.debin.RequestDebin)
     */
    @Override
    public Response eliminarDebin(RequestDebin request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			services = client.obtenerPort();
			loguearTramaDebinWS(request, "REQUEST DEBIN WS ELIMINAR DEBIN:");
			Response respEliminar = services.eliminacionDebin(request);
			loguearTramaDebinWS(respEliminar, "RESPONSE DEBIN WS ELIMINAR DEBIN:");
			return respEliminar;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion eliminacionDebin con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion eliminacionDebin con los datos {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion eliminacionDebin con los datos {}.",
					request, e);
			throw new DAOException(e);
		} catch (StackOverflowError e) {
			LOGGER.error(
					"StackOverflowError Hubo un error al invocar al ws de Debin para la operacion eliminar con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
    }

    /**
     * Descargar comprobante.
     *
     * @param datos the datos
     * @return the reporte
     */
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#descargarComprobante(ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteContracargoInDTO)
     */
    @Override
    public Reporte descargarComprobante(ComprobanteInDTO datos) {
        LOGGER.debug("DebinWSDAOImpl iniciando descargar comprobante");
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            // se carga el reporte
            jasperReport = null;
            if (PAGO.equals(datos.getTipoComprobante())) {
                jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteConfirmacionDebito.getInputStream());
                jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            } else if (RECHAZO.equals(datos.getTipoComprobante())) {
                jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteRechazoDebin.getInputStream());
                jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            } else if (ELIMINACION.equals(datos.getTipoComprobante())) {
                jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteEliminacionDebin.getInputStream());
                jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            }

            // se procesa el archivo jasper
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            LOGGER.debug("Completando parametros de reporte " + datos.getTipoComprobante());
            parameters.put(TIPO_DEBIN_NOMBRE, datos.getTipoDebinNombre());
            parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
            parameters.put(IMPORTE, datos.getImporte());
            parameters.put(FECHA_SOLICITUD, datos.getFechaSolicitud());
            parameters.put(FECHA_VENCIMIENTO, datos.getFechaVencimiento());
            parameters.put(NUMERO_CUENTA, datos.getNumeroCuenta());
            parameters.put(TIPO_CUENTA, datos.getTipoCuenta());
            parameters.put(SOLICITANTE, datos.getSolicitante());
            parameters.put(CUIT, datos.getCuit());
            parameters.put(CBU, datos.getCbu());
            parameters.put(ALIAS, datos.getAlias());
            parameters.put(DESCRIPCION, datos.getDescripcion());
            parameters.put(CONCEPTO, datos.getConcepto());
            parameters.put(ID_DEBIN, datos.getIdDebin());
            parameters.put(COMPROBANTE, datos.getComprobante());
            parameters.put(FECHA, datos.getFecha());
            parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
            byte[] byteArray = outStream.toByteArray();
            Reporte reporteOperaExterior = new Reporte();
            reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
            reporteOperaExterior.setBytes(byteArray);
            reporteOperaExterior.setNombre("Comprobante_" + datos.getTipoComprobante() + ".pdf");
            LOGGER.debug("DebinWSDAOImpl finalizando descargar comprobante");

            return reporteOperaExterior;
        } catch (JRException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new ISBANRuntimeException(ex);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ISBANRuntimeException(e);
        }
    }

	/**
	 * Adhesion comprador.
	 *
	 * @param request the request
	 * @return the response
	 * @throws DAOException the DAO exception
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#adhesionComprador(ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion)
	 */
	@Override
	public Response adhesionComprador(RequestAdhesion request) throws DAOException {
		V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS ADHESION_COMPRADOR:"); 
            Response respAdhesion = services.adhesionComprador(request);
            loguearTramaDebinWS(respAdhesion, "RESPONSE DEBIN WS ADHESION_COMPRADOR:"); 
            return respAdhesion;
        } catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Debin para la operacion adhesionComprador con los datos {}.",
                    request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error(
                    "DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion adhesionComprador con los datos {}.",
                    request, e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error(
                    "RuntimeException Hubo un error al invocar al ws de Debin para la operacion adhesionComprador con los datos {}.",
                    request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
	}

	/**
	 * Confirmacion debito.
	 *
	 * @param request the request
	 * @return the response
	 * @throws DAOException the DAO exception
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#confirmacionDebito(ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebito)
	 */
	@Override
	public Response confirmacionDebito(RequestConfirmacionDebito request) throws DAOException {
		V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS CONFIRMACION_DEBITO:"); 
            Response respDebito = services.confirmacionDebito(request);
            loguearTramaDebinWS(respDebito, "RESPONSE DEBIN WS CONFIRMACION_DEBITO:"); 
            return respDebito;
        } catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Debin para la operacion confirmacionDebito con los datos {}.",
                    request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error(
                    "DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion confirmacionDebito con los datos {} \n{} \n{}.",
                    request, e, e.getFaultInfo().getMensaje());
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error(
                    "RuntimeException Hubo un error al invocar al ws de Debin para la operacion confirmacionDebito con los datos {}.",
                    request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
	}

	/**
	 * Confirmacion debito V3.
	 *
	 * @param request the request
	 * @return the response
	 * @throws DAOException the DAO exception
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO#confirmacionDebitoV3(ar.com.santanderrio.obp.generated.webservices.debin.RequestConfirmacionDebitoV3)
	 */
	@Override
	public Response confirmacionDebitoV3(RequestConfirmacionDebitoV3 request) throws DAOException {
		V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS CONFIRMACION_DEBITO_V3:"); 
            Response respDebito = services.confirmacionDebitoV3(request);
            loguearTramaDebinWS(respDebito, "RESPONSE DEBIN WS CONFIRMACION_DEBITO_V3:"); 
            return respDebito;
        } catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Debin para la operacion confirmacionDebitoV3 con los datos {}.",
                    request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error(
                    "DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion confirmacionDebitoV3 con los datos {} \n{} \n{}.",
                    request, e, e.getFaultInfo().getMensaje());
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error(
                    "RuntimeException Hubo un error al invocar al ws de Debin para la operacion confirmacionDebitoV3 con los datos {}.",
                    request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
	}

}
