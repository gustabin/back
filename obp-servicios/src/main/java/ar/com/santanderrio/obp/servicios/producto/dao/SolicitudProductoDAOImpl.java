/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.WSGC;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class SolicitudProductoDAOImpl.
 */
@Component
public class SolicitudProductoDAOImpl implements SolicitudProductoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudProductoDAOImpl.class);

	/** Gestionar ws Alias. */
	@Autowired
	@Qualifier("solicitudProductoWS")
	private GestionarWS<WSGC> wsSolicitudClient;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/Logo_Visa.png")
	private Resource logoDefault;

	/** The file jasper agendar. */
	@Value("classpath:/report/bajaProducto/ComprobanteBajaProducto.jasper")
	private Resource fileJasperBajaProducto;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";
	
	private static final String ID_CUENTA_PAQUETE = "PAQUETE";
	
	private static final String  TITULO_OPERACION = "TITULO_OPERACION";
	
	private static final String  TITULO_TIPO_BAJA = "TITULO_TIPO_BAJA";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAO#
	 * altaGestion(java.lang.Integer, java.lang.String, java.lang.Integer,
	 * java.lang.String, java.lang.String, java.lang.Integer, java.lang.String,
	 * ar.com.santanderrio.obp.generated.webservices.productos.
	 * ArrayOf158770343432493182NillableInfoRequeridaWS)
	 */
	@Override
	public ResultadoAltaWS altaGestion(Integer codAsociacion, String tipoPersona, Integer nup, String codSector,
			String codUser, Integer medioIngreso, String comentario,
			ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida) throws DAOException {
		WSGC services = null;
		try {
			services = wsSolicitudClient.obtenerPort();
			return services.altaGestion(codAsociacion, tipoPersona, nup, codSector, codUser, medioIngreso, comentario,
					infoRequerida);
		} catch (RuntimeException e) {
			LOGGER.error("Hubo un error al invocar al ws de Alias para la operacion altaAlias.", e);
			throw new DAOException(e);
		} finally {
			wsSolicitudClient.liberarPort(services);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAO#
	 * generarComprobanteBaja(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Reporte generarComprobanteBaja(ComprobanteBajaProductoView comprobanteBajaProducto) {
		Reporte reporte = new Reporte();

		try {
			// se carga el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileJasperBajaProducto.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// se procesa el archivo jasper
			
			boolean esPaquete = ID_CUENTA_PAQUETE.equals(comprobanteBajaProducto.getTipoProducto());
			
			if (esPaquete) {
				parameters.put(TITULO_OPERACION, "Comprobante de solicitud de baja de producto");
				parameters.put(TITULO_TIPO_BAJA, "Solicitud de baja de producto");
			} else if ("Tarjetas".equals(comprobanteBajaProducto.getTipoOperacion().getNombre())) {
				parameters.put(TITULO_OPERACION, "Comprobante de solicitud de baja");
				parameters.put(TITULO_TIPO_BAJA, "Solicitud de baja de tarjeta");

			} else if (comprobanteBajaProducto.getArrepentimiento()) {
				parameters.put(TITULO_OPERACION, "Comprobante de solicitud de baja por arrepentimiento");
				parameters.put(TITULO_TIPO_BAJA, "Solicitud de baja por arrepentimiento de " + comprobanteBajaProducto.getTipoOperacion().getNombre());
			} else {
				parameters.put(TITULO_OPERACION, "Comprobante de solicitud de baja");
				parameters.put(TITULO_TIPO_BAJA, "Solicitud de baja de " + comprobanteBajaProducto.getTipoOperacion().getNombre());
			}

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());

			parameters.put("PRODUCTO_BAJA", comprobanteBajaProducto.getDescripcionProducto());
			parameters.put("PRODUCTO", comprobanteBajaProducto.getDescripcionProducto());
			parameters.put("NRO_CUENTA", comprobanteBajaProducto.getNumero());

			parameters.put("FECHA_BAJA", comprobanteBajaProducto.getFechaDeBaja());
			parameters.put("NRO_COMPROBANTE", comprobanteBajaProducto.getNroComprobante());

			parameters.put("FECHA_HORA_OPERACION", comprobanteBajaProducto.getFechaOperacion());
			parameters.put("LOGO_CIERRE", logoCierre.getFile().getPath());

			parameters.put("ES_PAQUETE", esPaquete);
			parameters.put("MANTIENE_CAJA_AHORRO", comprobanteBajaProducto.getMantieneCajaAhorro());

			if ("Caja de seguridad".equals(comprobanteBajaProducto.getDescripcionProducto())) {
				parameters.put("MENSAJE_LEGAL", comprobanteBajaProducto.getMensaje().replaceAll("<p>", "").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("</p>", ""));
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre(comprobanteBajaProducto.getArrepentimiento() ? "Comprobante_Arrepentimiento_Producto" : "Comprobante_Baja_Producto" + comprobanteBajaProducto.getNroComprobante() + PDF_EXTENSION);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);

		} catch (JRException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
		return reporte;
	}

}
