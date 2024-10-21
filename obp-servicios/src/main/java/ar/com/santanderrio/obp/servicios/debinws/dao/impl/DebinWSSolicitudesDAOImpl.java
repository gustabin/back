/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.StringUtils;
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
import ar.com.santanderrio.obp.generated.webservices.debin.V3_002fDebinClient;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseNuevoDebin;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ComprobanteSolicitudDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinCBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.debinws.exceptions.DebinDestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.debinws.utils.DebinWSUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.RequestCNSTITCBU;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;




/**
 * The Class DebinDAOImpl.
 * 
 */
@Component("debinwsSolicitudesDAO")
public class DebinWSSolicitudesDAOImpl extends IatxBaseDAO implements DebinWSSolicitudesDAO   {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DebinWSSolicitudesDAOImpl.class);
	
	/** time out code. */
	private static final String TIME_OUT_CODE = "099";
	
    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;
	
    /** The Constant MAX_NRO_SUCURSAL. */
    private static final int MAX_NRO_SUCURSAL = 3;

    /** The Constant MAX_NRO_CUENTA. */
    private static final int MAX_NRO_CUENTA = 7;

    /** The file jasper. */
    @Value("classpath:/report/debinWS/ComprobanteSolicitudDebin.jasper")
    private Resource fileJasperComprobanteSolicitudDebin;

    /** The logo cierre. */
    @Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
    private Resource logoCierre;

    /** The logo cabecera. */
    @Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
    private Resource logoCabecera;

    /** The logo debito. */
    @Value("classpath:/report/comprobantes/logoDebito.png")
    private Resource logoDebito;

    /** The Constant LOGO_CABECERA. */
    private static final String LOGO_CABECERA = "LOGO_CABECERA";

    /** The Constant LOGO_CABECERA. */
    private static final String IMPORTE = "IMPORTE";

    /** The Constant FECHA_VENCIMIENTO. */
    private static final String FECHA_VENCIMIENTO = "FECHA_VENCIMIENTO";

    /** The Constant NUMERO_CUENTA. */
    private static final String NUMERO_CUENTA = "NUMERO_CUENTA";

    /** The Constant TIPO_CUENTA. */
    private static final String TIPO_CUENTA = "TIPO_CUENTA";

    /** The Constant CUIT_DESTINATARIO. */
    private static final String CUIT_DESTINATARIO = "CUIT_DESTINATARIO";

    /** The Constant CBU_DESTINATARIO. */
    private static final String CBU_DESTINATARIO = "CBU_DESTINATARIO";

    /** The Constant ALIAS_DESTINATARIO. */
    private static final String ALIAS_DESTINATARIO = "ALIAS_DESTINATARIO";

    /** The Constant DESCRIPCION. */
    private static final String DESCRIPCION = "DESCRIPCION";

    /** The Constant CONCEPTO. */
    private static final String CONCEPTO = "CONCEPTO";

    /** The Constant NRO_COMPROBANTE. */
    private static final String COMPROBANTE = "COMPROBANTE";

    /** The Constant FECHA. */
    private static final String FECHA = "FECHA";

    /** The Constant LOGO_CIERRE. */
    private static final String LOGO_CIERRE = "LOGO_CIERRE";

    /** The Constant NOMBRE_DESTINATARIO. */
    private static final String NOMBRE_DESTINATARIO = "NOMBRE_DESTINATARIO";

    /** The Constant BANCO_DESTINATARIO. */
    private static final String BANCO_DESTINATARIO = "BANCO_DESTINATARIO";

    /** The transferencias dao. */
    @Autowired
    private TransferenciaDAO transferenciaDAO;

	/** The client. */
	@Autowired
	@Qualifier("gestionDebin")
	private GestionarWS<V3_002fDebinClient> client;

	/**
	 * consultarCNSTITCBU.
	 *
	 * @param entityIn
	 *            the entity in
	 * @return the consulta cbu entity out
	 * @throws DebinCBUInvalidoDAOException
	 *             the debin CBU invalido DAO exception
	 * @throws DebinDestinatarioNoVerificadoException
	 *             the debin destinatario no verificado exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
    public ConsultaCbuEntityOut consultarCNSTITCBU(ConsultaCBUEntityIn entityIn) throws DebinCBUInvalidoDAOException,
    DebinDestinatarioNoVerificadoException, DAOException {
        LOGGER.debug("DebinWSDAOImpl _ Iniciando metodo consultarCNSTITCBU");

        ConsultaCbuEntityOut consultaCBUEntityOut = new ConsultaCbuEntityOut();
        try {
            LOGGER.debug("DebinWSDAOImpl _ Iniciando llamada iatx SERVICIO_CNSTITCBU");
            RequestCNSTITCBU requestCNSTITCBU = new RequestCNSTITCBU();
            requestCNSTITCBU.setCbuDestino(StringUtils.right(entityIn.getCbuDestino(),22));
            requestCNSTITCBU.setDireccionIP(entityIn.getDireccionIP());
            requestCNSTITCBU.setNroCuenta(StringUtils.right(entityIn.getNroCuenta(), MAX_NRO_CUENTA));
            requestCNSTITCBU.setNroSucursal(StringUtils.right(entityIn.getNroSucursal(), MAX_NRO_SUCURSAL));
            requestCNSTITCBU.setNroTarjeta(StringUtils.right(entityIn.getNroTarjeta(), 18));
            requestCNSTITCBU.setTipoCuenta(entityIn.getTipoCuenta());

            IatxResponse iatxResponse = transferenciaDAO.conexionCNSTITICBU(entityIn.getCliente(), requestCNSTITCBU);
            LOGGER.debug("DebinWSDAOImpl _ Finalizando llamada iatx SERVICIO_CNSTITCBU");
            int errorCode = iatxResponse.getErrorCode();
            
            switch (errorCode) {
            case OK_CODIGO_RETORNO:
                consultaCBUEntityOut  = parsearResponseOrigenDestinoValido(iatxResponse);
                break;
            case DebinWSUtils.ERROR_CODIGO_36:
                throw new DebinCBUInvalidoDAOException(DebinWSUtils.ERROR_CODIGO_36);
            case DebinWSUtils.ERROR_CODIGO_57:
                throw new DebinCBUInvalidoDAOException(DebinWSUtils.ERROR_CODIGO_57);
            case DebinWSUtils.ERROR_USUARIOTARJETA_INEXISTENTE_50:
                throw new DebinCBUInvalidoDAOException(DebinWSUtils.ERROR_USUARIOTARJETA_INEXISTENTE_50);

            case DebinWSUtils.DESTINATARIO_NO_VERIFICADO_1:
            case DebinWSUtils.DESTINATARIO_NO_VERIFICADO_2:
                throw new DebinDestinatarioNoVerificadoException();
                
            case DebinWSUtils.ERROR_FLUJO_MANUAL_56:
            case DebinWSUtils.ERROR_FLUJO_MANUAL_65:
            case DebinWSUtils.ERROR_FLUJO_MANUAL_66:
            case DebinWSUtils.ERROR_FLUJO_MANUAL_70:
            case DebinWSUtils.ERROR_FLUJO_MANUAL_74:
            case DebinWSUtils.ERROR_FLUJO_MANUAL_76:
            case DebinWSUtils.ERROR_SIN_REITENTO_505:
            default:
                throw new DebinCBUInvalidoDAOException(errorCode);    
            }
        } catch (TimeOutException e) {
            LOGGER.error(e.getMessage(), e);
            e.setErrorCode(TIME_OUT_CODE);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        LOGGER.debug("DebinWSDAOImpl _ Finalizando llamada SERVICIO_CNSTITCBU");
        return consultaCBUEntityOut;
    }

    /**
	 * Parsear response origen destino valido.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the transferencia
	 * @throws DebinDestinatarioNoVerificadoException
	 *             the debin destinatario no verificado exception
	 */
    private ConsultaCbuEntityOut parsearResponseOrigenDestinoValido(IatxResponse iatxResponse)
            throws  DebinDestinatarioNoVerificadoException {
        ConsultaCbuEntityOut consultaCBUEntityOut = new ConsultaCbuEntityOut();
        consultaCBUEntityOut.setTitular(iatxResponse.getNextData());

        if (StringUtils.EMPTY.equals(consultaCBUEntityOut.getTitular().trim())) {
            LOGGER.info("ERROR_EN_BANELCO_CNSTITCBU_CAMPOS_VACIOS");
            throw new DebinDestinatarioNoVerificadoException();
        } 

        consultaCBUEntityOut.setCuit1(iatxResponse.getNextData());
        consultaCBUEntityOut.setCuit2(iatxResponse.getNextData());
        consultaCBUEntityOut.setCuit3(iatxResponse.getNextData());
        consultaCBUEntityOut.setBandes(iatxResponse.getNextData());
        consultaCBUEntityOut.setLongCtaDestino(iatxResponse.getNextData());
        consultaCBUEntityOut.setCtaDestino(iatxResponse.getNextData());
        consultaCBUEntityOut.setTipoCuentaToBanelco(iatxResponse.getNextData());
        consultaCBUEntityOut.setTipoCuentaFromBanelco(iatxResponse.getNextData());
        consultaCBUEntityOut.setBancoReceptor(iatxResponse.getNextData());
        consultaCBUEntityOut.setFiid(iatxResponse.getNextData());
        consultaCBUEntityOut.setUser(iatxResponse.getNextData());

        return consultaCBUEntityOut;
    }

    /**
	 * consultaCuentasAdheridas.
	 *
	 * @param request
	 *            the request
	 * @return the response vendedor
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ResponseVendedor consultarCuentasAdheridas(RequestConsulta request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS CONSULTA VENDEDOR:");
			services = client.obtenerPort();
			ResponseVendedor respDebin = services.consultaVendedor(request);
			loguearTramaDebinWS(respDebin, "RESPONSE DEBIN WS CONSULTA VENDEDOR:");
			return respDebin;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion consultarCuentasAdheridas con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion consultarCuentasAdheridas {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion consultarCuentasAdheridas con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

	/**
	 * loguearTramaDebinWS.
	 *
	 * @param obj the obj
	 * @param cabecera the cabecera
	 */
	private void loguearTramaDebinWS(Object obj, String cabecera) {
		StringBuilder result = new StringBuilder();
		result.append(cabecera);
		Gson gson = new Gson();
		String out = gson.toJson(obj);
		result.append(" :");
		result.append(out);
		LOGGER.info(result.toString());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO#solicitarDebin(ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebin)
	 */
	@Override
	public ResponseNuevoDebin solicitarDebin(RequestNuevoDebin request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS CREACION DEBIN NEW:");
			services = client.obtenerPort();
			ResponseNuevoDebin respDebin = services.creacionDebinNew(request);
			loguearTramaDebinWS(respDebin, "RESPONSE DEBIN WS CREACION DEBIN NEW:");
			return respDebin;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion solicitarDebin con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion solicitarDebin {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion solicitarDebin con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO#solicitarDebinV3(ar.com.santanderrio.obp.generated.webservices.debin.RequestNuevoDebinV3)
	 */
	@Override
	public ResponseNuevoDebin solicitarDebinV3(RequestNuevoDebinV3 request) throws DAOException {
		V3_002fDebinClient services = null;
		try {
			loguearTramaDebinWS(request, "REQUEST DEBIN WS CREACION DEBIN V3:");
			services = client.obtenerPort();
			ResponseNuevoDebin respDebin = services.creacionDebinV3(request);
			loguearTramaDebinWS(respDebin, "RESPONSE DEBIN WS CREACION DEBIN V3:");
			return respDebin;
		} catch (WebServiceException e) {
			LOGGER.error(
					"WebServiceException Hubo un error al invocar al ws de Debin para la operacion solicitarDebinV3 con los datos {}.",
					request, e);
			if (e.getCause().getCause() instanceof SocketTimeoutException) {
				throw new DAOException(e.getMessage(), TIME_OUT_CODE);
			}
			throw new DAOException(e);
		} catch (DebinApiException_Exception e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion solicitarDebinV3 {}.",
					request, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Debin para la operacion solicitarDebinV3 con los datos {}.",
					request, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO#
	 * descargarComprobante(ar.com.santanderrio.obp.servicios.debinws.dto.
	 * ComprobanteSolicitudDTO)
	 */
	@Override
	public Reporte descargarComprobante(ComprobanteSolicitudDTO datos) {
		LOGGER.debug("DebinWSDAOImpl iniciando descargar comprobante");
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteSolicitudDebin.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			LOGGER.debug("Completando parametros de reporte Solicitud");
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(NOMBRE_DESTINATARIO, datos.getNombreDestinatario());
			parameters.put(IMPORTE, datos.getImporte());
			parameters.put(CUIT_DESTINATARIO, datos.getCuit());
			parameters.put(CBU_DESTINATARIO, datos.getCbu());
			parameters.put(ALIAS_DESTINATARIO, datos.getAlias());
			parameters.put(BANCO_DESTINATARIO, datos.getBanco());
			parameters.put(NUMERO_CUENTA, datos.getNroCuentaAcreditacion());
			parameters.put(TIPO_CUENTA, datos.getTipoCuentaAcreditacion());
			parameters.put(FECHA_VENCIMIENTO, datos.getFechaVencimiento());
			parameters.put(CONCEPTO, datos.getConcepto());
			parameters.put(DESCRIPCION, datos.getDescripcion());
			parameters.put(COMPROBANTE, datos.getNroComprobante());
			parameters.put(FECHA, datos.getFechaHora());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			byte[] byteArray = outStream.toByteArray();
			Reporte reporteOperaExterior = new Reporte();
			reporteOperaExterior.setTipoArchivo(TipoArchivoEnum.PDF);
			reporteOperaExterior.setBytes(byteArray);
			reporteOperaExterior.setNombre("Comprobante_Solicitud_Debin.pdf");
			LOGGER.debug("DebinWSDAOImpl Solicitudes finalizando descargar comprobante");
			return reporteOperaExterior;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
