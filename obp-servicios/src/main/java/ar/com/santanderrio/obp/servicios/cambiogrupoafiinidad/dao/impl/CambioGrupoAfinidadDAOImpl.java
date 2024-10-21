/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.WSGC;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadDAO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadOutEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class CambioGrupoAfinidadDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ADVANTAGE)
public class CambioGrupoAfinidadDAOImpl implements CambioGrupoAfinidadDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioGrupoAfinidadDAOImpl.class);
	
	/** Gestionar ws Alias. */
	@Autowired
	@Qualifier("solicitudProductoWS")
	private GestionarWS<WSGC> wsSolicitudClient;
	
	/** The file jasper. */
	@Value("classpath:/report/cambioGrupoAfinidad/comprobanteCambioGrupoAfinidadAadvantage.jasper")
	private Resource fileJasperAadvantage;
	
	/** The file jasper. */
	@Value("classpath:/report/cambioGrupoAfinidad/comprobanteCambioGrupoAfinidadAadvantageSinSocio.jasper")
	private Resource fileJasperAadvantageSinSocio;
	
	/** The file jasper. */
	@Value("classpath:/report/cambioGrupoAfinidad/comprobanteCambioGrupoAfinidadSuperclub.jasper")
	private Resource fileJasperSuperclub;
	
	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;
	
	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;
	
	/** The Constant LEGAL_PIE. */
	private static final String LEGAL_PIE = "LEGAL_PIE";
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadDAO#altaSolicitudAdhesion(ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadInEntity)
	 */
	@Override
	public AltaSolicitudCambioAfinidadOutEntity altaSolicitudAdhesion(AltaSolicitudCambioAfinidadInEntity inEntity) throws DAOException {
		WSGC services = null;
		AltaSolicitudCambioAfinidadOutEntity respuesta = new AltaSolicitudCambioAfinidadOutEntity();
		ResultadoAltaWS respuestaWS;
		try {
			services = wsSolicitudClient.obtenerPort();
			respuestaWS = services.altaGestion(inEntity.getCodAsociacion(), inEntity.getTipoPersona(), inEntity.getNup(), inEntity.getCodSector(), inEntity.getCodUser(), inEntity.getMedioIngreso(), inEntity.getComentario(),
					inEntity.getInfoRequerida());
			if (respuestaWS.getCodRetorno() == 0) {
				String numeroGestion = respuestaWS.getIdeGestionNro().toString();
				respuesta.setNroGestion(numeroGestion);
			}
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestion.", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (TimeOutException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestionUser en AumentoLimiteTransferenciaDAOImpl", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} finally {
			wsSolicitudClient.liberarPort(services);
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao.CambioGrupoAfinidadDAO#generarComprobanteCambioGrupoAfinidad(ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView)
	 */
	@Override
	public Reporte generarComprobanteCambioGrupoAfinidad(ComprobanteSolicitudCambioAfinidadView datosComprobante) {
		
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		Boolean esSuperClub = false;
		String legalPie;
		try {
			
			if(datosComprobante.getNroSocioAdvantage() != null && !StringUtils.EMPTY.equals(datosComprobante.getNroSocioAdvantage())) {
				jasperReport = (JasperReport) JRLoader.loadObject(fileJasperAadvantage.getInputStream());
			}else if(datosComprobante.getNroSocioAdvantage() != null && StringUtils.EMPTY.equals(datosComprobante.getNroSocioAdvantage())) {
				jasperReport = (JasperReport) JRLoader.loadObject(fileJasperAadvantageSinSocio.getInputStream());
			}else {
				jasperReport = (JasperReport) JRLoader.loadObject(fileJasperSuperclub.getInputStream());
				esSuperClub = true;
			}

			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());
			if(datosComprobante.getNroSocioAdvantage() != null) {
				parameters.put("NRO_SOCIO_ADVANTAGE", datosComprobante.getNroSocioAdvantage());
			}
			parameters.put("NRO_GESTION", datosComprobante.getNumeroGestion());
			parameters.put("FECHA_OP", datosComprobante.getFechaHora());
			if (esSuperClub == false) {
				legalPie = datosComprobante.getInfoPie().replaceAll("</br>", "<br>");
				parameters.put(LEGAL_PIE, legalPie);
			}
			
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new JRBeanCollectionDataSource(datosComprobante.getTarjetasAsociadas(), false));
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobanteCambioAfinidad.pdf");
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
