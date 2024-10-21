package ar.com.santanderrio.obp.servicios.debinws.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.generated.webservices.debin.DebinApiException_Exception;
import ar.com.santanderrio.obp.generated.webservices.debin.V3_002fDebinClient;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSAdhesionDAO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ComprobanteAdhesionEntity;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
@Qualifier("debinWSAdhesionDAOImpl")
public class DebinWSAdhesionDAOImpl extends DebinWSDAOImpl implements DebinWSAdhesionDAO {

    /** The Constant TITULO. */
	private static final String TITULO = "TITULO";

    /** The Constant SUBTITULO. */
    private static final String SUBTITULO = "SUBTITULO";

    /** The Constant SUBREPORT_DIR. */
    private static final String SUBREPORT_DIR = "SUBREPORT_DIR";

    /** The Constant CUENTAS_ELIMINADAS. */
    private static final String CUENTAS_ELIMINADAS = "CUENTAS_ELIMINADAS";

    /** The Constant CUENTAS_ADHERIDAS. */
    private static final String CUENTAS_ADHERIDAS = "CUENTAS_ADHERIDAS";

    /** The Constant IS_BAJA. */
    private static final String IS_BAJA = "IS_BAJA";

    /** The Constant IS_ADHESION. */
    private static final String IS_ADHESION = "IS_ADHESION";

    /** The file jasper comprobante gestion adhesion debin. */
    @Value("classpath:/report/debinWS/ComprobanteGestionAdhesionDebin.jasper")
    private Resource fileJasperComprobanteGestionAdhesionDebin;

    @Override
    public ResponseVendedor consultaVendedor(RequestConsulta request) throws DAOException {
        V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS CONSULTA VENDEDOR DEBIN:"); 
            ResponseVendedor respConsulta = services.consultaVendedor(request);
            loguearTramaDebinWS(respConsulta, "RESPONSE DEBIN WS CONSULTA VENDEDOR DEBIN:"); 
            return respConsulta;
        } catch (WebServiceException e) {
            LOGGER.error("WebServiceException Hubo un error al invocar al ws de Debin para la operacion consultaVendedor con los datos {}.", request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error("DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion consultaVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error("RuntimeException Hubo un error al invocar al ws de Debin para la operacion consultaVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
    }

    @Override
    public Response adhesionVendedor(RequestAdhesion request) throws DAOException {
        V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS ADHESION VENDEDOR DEBIN:"); 
            Response respAdhesion = services.adhesionVendedor(request);
            loguearTramaDebinWS(respAdhesion, "RESPONSE DEBIN WS ADHESION VENDEDOR DEBIN:"); 
            return respAdhesion;
        } catch (WebServiceException e) {
            LOGGER.error("WebServiceException Hubo un error al invocar al ws de Debin para la operacion adhesionVendedor con los datos {}.", request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error("DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion adhesionVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error("RuntimeException Hubo un error al invocar al ws de Debin para la operacion adhesionVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
    }

    @Override
    public Response bajaCuentaVendedor(RequestAdhesion request) throws DAOException {
        V3_002fDebinClient services = null;
        try {
            services = client.obtenerPort();
            loguearTramaDebinWS(request, "REQUEST DEBIN WS BAJA ADHESION VENDEDOR DEBIN:"); 
            Response respDesadhesion = services.bajaCuentaVendedor(request);
            loguearTramaDebinWS(respDesadhesion, "RESPONSE DEBIN WS BAJA ADHESION VENDEDOR DEBIN:"); 
            return respDesadhesion;
        } catch (WebServiceException e) {
            LOGGER.error("WebServiceException Hubo un error al invocar al ws de Debin para la operacion bajaCuentaVendedor con los datos {}.", request, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
        } catch (DebinApiException_Exception e) {
            LOGGER.error("DebinApiException_Exception Hubo un error al invocar al ws de Debin para la operacion bajaCuentaVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error("RuntimeException Hubo un error al invocar al ws de Debin para la operacion bajaCuentaVendedor con los datos {}.", request, e);
            throw new DAOException(e);
        } finally {
            client.liberarPort(services);
        }
    }

	@Override
	public Reporte generarComprobante(ComprobanteAdhesionEntity comprobanteAdhesionEntity) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(fileJasperComprobanteGestionAdhesionDebin.getInputStream());

			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(TITULO, comprobanteAdhesionEntity.getTitulo());
			parameters.put(SUBTITULO, comprobanteAdhesionEntity.getSubtitulo());
			parameters.put(COMPROBANTE, comprobanteAdhesionEntity.getNumeroComprobante());
			parameters.put(FECHA, comprobanteAdhesionEntity.getFecha());
			parameters.put(SUBREPORT_DIR, fileJasperComprobanteGestionAdhesionDebin.getFile().getParent()+File.separator);
			parameters.put(CUENTAS_ELIMINADAS, comprobanteAdhesionEntity.getCuentasBaja());
			parameters.put(CUENTAS_ADHERIDAS, comprobanteAdhesionEntity.getCuentasAdhesion());
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(IS_BAJA, Boolean.FALSE);
			if (comprobanteAdhesionEntity.getCuentasBaja() != null && comprobanteAdhesionEntity.getCuentasBaja().size()>0) {
				parameters.put(IS_BAJA, Boolean.TRUE);
			}
			parameters.put(IS_ADHESION, Boolean.FALSE);
			if (comprobanteAdhesionEntity.getCuentasAdhesion() != null && comprobanteAdhesionEntity.getCuentasAdhesion().size()>0) {
				parameters.put(IS_ADHESION, Boolean.TRUE);
			}

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("ComprobanteGestionAdhesionDebin.pdf");
			return reporte;
		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
