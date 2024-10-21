/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.clientes.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReporteDAOImpl.
 */
@Component("reporteDAO")
public class ReporteDAOImpl implements ReporteDAO {

	/** The Constant TIPO_COMPROBANTE_CAMBIO_USUARIO. */
	private static final String TIPO_COMPROBANTE_CAMBIO_USUARIO = "Usuario";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteDAOImpl.class);

	/** The file jasper. */
	@Value("classpath:/report/perfil/comprobanteCambioClaveUsuario.jasper")
	private Resource fileJasperCambioClaveUsuario;

	/** The file jasper. */
	@Value("classpath:/report/perfil/comprobanteCambioUsuario.jasper")
	private Resource fileJasperCambioUsuario;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.clientes.dao.ReporteDAO#
	 * descargarComprobante(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * DatosComprobante)
	 */
	@Override
	public Reporte descargarComprobante(DatosComprobante datos) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {

			if (TIPO_COMPROBANTE_CAMBIO_USUARIO.equals(datos.getTipoComprobante())) {
				// se carga el reporte
				jasperReport = (JasperReport) JRLoader.loadObject(fileJasperCambioUsuario.getInputStream());
			} else {
				// CAMBIO CLAVE Y USUARIO
				// se carga el reporte
				jasperReport = (JasperReport) JRLoader.loadObject(fileJasperCambioClaveUsuario.getInputStream());
			}
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("logo", logoCabecera.getFile().getPath());
			parameters.put("logoFooter", logoPie.getFile().getPath());
			parameters.put("nro_comprobante", datos.getNroComprobante());

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("comprobante.pdf");
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
