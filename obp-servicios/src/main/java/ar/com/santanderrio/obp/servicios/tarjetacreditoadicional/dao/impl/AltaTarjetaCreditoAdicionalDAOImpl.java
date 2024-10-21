/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.AltaTarjetaCreditoAdicionalDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalOutResponseEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class AltaTarjetaCreditoAdicionalDAOImpl.
 */
@Component("altaTarjetaCreditoAdicionalDAO")
public class AltaTarjetaCreditoAdicionalDAOImpl extends IatxBaseDAO implements AltaTarjetaCreditoAdicionalDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaTarjetaCreditoAdicionalDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The servicio pag tjc. */
	@Value("${SERVICIO.PREFIJO.ALTTARJETA}")
	private String servicioAltaTarjetaCredAd;

	/** The version pag tjc. */
	@Value("${SERVICIO.VERSION.ALTTARJETA}")
	private String versionAltaTarjetaCredAd;

	/** The Constant BLANK. */
	private static final String BLANK = " ";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant TARJETA_ADICIONAL_FUNCION. */
	private static final String TARJETA_ADICIONAL_FUNCION = "FI";

	/** The Constant TARJETA_ADICIONAL_USUARIOOPERACION. */
	private static final String TARJETA_ADICIONAL_USUARIOOPERACION = "OBP1";

	/** The Constant TARJETA_ADICIONAL_ORIGENOPERACION. */
	private static final String TARJETA_ADICIONAL_ORIGENOPERACION = "OBP";

	/** The Constant TARJETA_ADICIONAL_CODIGOPRODUCTO. */
	private static final String TARJETA_ADICIONAL_CODIGOPRODUCTO = "0";

	/** The Constant CANAL. */
	private static final String CANAL = "06";

	/** The Constant TARJETA_ADICIONAL_USUARIOVTA. */
	private static final String TARJETA_ADICIONAL_USUARIOVTA = "OBP1";

	/** The Constant TARJETA_ADICIONAL_DURACIONTARJETA. */
	private static final String TARJETA_ADICIONAL_DURACIONTARJETA = "36";

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The file jasper. */
	@Value("classpath:/report/tarjetaCreditoAdicional/ComprobanteTarjCredAdic.jasper")
	private Resource reportFile;

	/** The logo cierre. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoCierre;

	/** The logo cabecera. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo default. */
	@Value("classpath:/report/comprobantes/logo_default.png")
	private Resource logoDefault;

	/** The Constant PDF_EXTENSION. */
	private static final String PDF_EXTENSION = ".pdf";

	/** The Constant PARAM_LOGO_CABECERA. */
	private static final String LOGO_CABECERA = "LOGO_CABECERA";

	/** The Constant PARAM_LOGO_DEFAULT. */
	private static final String LOGO_DEFAULT = "LOGO_DEFAULT";

	/** The Constant PARAM_SUBREPORT_DIR. */
	private static final String APE_NOM_ADIC = "PARAM_APE_NOM_ADIC";

	/** The Constant PARAM_SUBREPORT_DIR. */
	private static final String DNI_ADIC = "PARAM_DNI_ADIC";

	/** The Constant PARAM_SUBREPORT_DIR. */
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";

	/** The Constant PARAM_LOGO_CIERRE. */
	private static final String LOGO_CIERRE = "LOGO_CIERRE";

	/** The Constant PARAM_FECHA_OP. */
	private static final String FECHA_OP = "FECHA_OP";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.
	 * AltaTarjetaCreditoAdicionalDAO#altaTarjetaCreditoAdicional(ar.com.
	 * santanderrio.obp.servicios.tarjetacreditoadicional.entities.
	 * AltaTarjetaCreditoAdicionalInEntity)
	 */
	@Override
	public AltaTarjetaCreditoAdicionalOutResponseEntity altaTarjetaCreditoAdicional(
			AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity) throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioAltaTarjetaCredAd, versionAltaTarjetaCredAd);

		AltaTarjetaCreditoAdicionalOutResponseEntity altaTarjetaCreditoAdicionalOutResponseEntity = new AltaTarjetaCreditoAdicionalOutResponseEntity();

		try {
			IatxRequestData data = generateRequestDataALTTARJETA(altaTarjetaCreditoAdicionalInEntity);

			iatxRequest.setData(data);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();

			if (OK_CODIGO_RETORNO == errorCode) {
				altaTarjetaCreditoAdicionalOutResponseEntity = processTrama(iatxResponse.getTrama(),
						AltaTarjetaCreditoAdicionalOutResponseEntity.class);
			} else {
				// manejar respuesta ERROR
				return null;
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return altaTarjetaCreditoAdicionalOutResponseEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.
	 * AltaTarjetaCreditoAdicionalDAO#generarComprobante(ar.com.santanderrio.obp
	 * .servicios.tarjetacreditoadicional.web.view.
	 * ComprobanteAltaTarjetaCreditoAdicionalView)
	 */
	@Override
	public Reporte generarComprobante(ComprobanteAltaTarjCredAdicionalView view) {
		Reporte reporte = new Reporte();
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

			// Se procesa el archivo jasper
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(LOGO_CABECERA, logoCabecera.getFile().getPath());
			parameters.put(LOGO_DEFAULT, logoDefault.getFile().getPath());
			parameters.put(APE_NOM_ADIC, view.getApellido().trim() + ", " + view.getNombre().trim());
			parameters.put(DNI_ADIC, view.getDniAdicional());
			parameters.put(SUBREPORT_DIR, reportFile.getFile().getParent() + File.separator);
			parameters.put(LOGO_CIERRE, logoCierre.getFile().getPath());
			parameters.put(FECHA_OP, ISBANStringUtils.formatearFecha(new Date(), "dd/MM/yyyy HH:mm"));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new JRBeanCollectionDataSource(view.getTarjetasAdicionalesSolicitadas()));

			// Se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();
			reporte.setBytes(byteArray);
			reporte.setNombre("ComprobanteTarjCredAdic" + PDF_EXTENSION);
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

	/**
	 * Generate request data ALTTARJETA.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataALTTARJETA(AltaTarjetaCreditoAdicionalInEntity entity) {

		IatxRequestData data = new IatxRequestData(entity.getCliente());

		// datos generales
		data.addBodyValue(TARJETA_ADICIONAL_FUNCION); // 1.Funcion
		data.addBodyValue(entity.getNroCuentaTarjeta()); // 2.Nro_cuenta_tarjeta
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 3.Aplicación _
		// Cuenta
		// relacionada
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 3)); // 4.Sucursal _
		// Cuenta
		// relacionada
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 1)); // 5.Tipo cuenta -
		// Cuenta
		// relacionada
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 13)); // 6.Numero de
		// cuenta -
		// Cuenta
		// relacionada
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 2)); // 7.Firmante -
		// Cuenta
		// relacionada
		data.addBodyValue(ISBANStringUtils.fillStr(TARJETA_ADICIONAL_USUARIOOPERACION, 8)); // 8.usuario_operacion
		data.addBodyValue(TARJETA_ADICIONAL_ORIGENOPERACION); // 9.Origen_operacion
		data.addBodyValue(entity.getSucursalUsuario()); // 10.Sucursal_ usuario
		// CONFIRMAR
		data.addBodyValue(entity.getCentroDeCosto()); // 11.Centro de costo
		// CONFIRMAR
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 12.Código de
		// comercio
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 3)); // 13.Código
		// sucursal
		data.addBodyValue("0"); // 14.Código_compra

		// datos de la cuenta
		data.addBodyValue(TARJETA_ADICIONAL_CODIGOPRODUCTO); // 15.Codigo_producto
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 1)); // 16.Codigo
		// categoría
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 1)); // 17.Codigo_cartera
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 24)); // 18.Apellido y
		// nombre
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 19.Secuencia_domicilio
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 20.Secuencia_telefono
		data.addBodyValue(entity.getGrupoAfinidad()); // 21.Grupo_afinidad
		data.addBodyValue(CANAL); // 22.Canal_venta
		data.addBodyValue(ISBANStringUtils.fillStr(TARJETA_ADICIONAL_USUARIOVTA, 8)); // 23.Usuario_venta
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 2)); // 24.Forma_pago
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 25.Cuenta pago
		// - aplicación
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 26.Cuenta pago
		// sucursal
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 1)); // 27.Cuenta de
		// pago tipo de
		// cuenta
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 7)); // 28.Cuenta de
		// pago numero
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 2)); // 29.Cuenta de
		// pago Firmante
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 13)); // 30.Importe
		// limite compra
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 13)); // 31.Importe
		// limite
		// crédito
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 32.Cargo
		// renovación
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 2)); // 33.Cargo
		// renovación
		// cuotas
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 1)); // 34.Cargo MB
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 35.Tipo paquete
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 2)); // 36.Plazo pago
		// mínimo
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 37.empresa
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 38.Modelo
		// liquidación
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 39.planta
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 4)); // 40.Compañía
		// seguro
		data.addBodyValue("F"); // 41.Tipo persona
		data.addBodyValue(entity.getMarca()); // 42.marca
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 1)); // 43.Control
		// limite por
		// tarjeta
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 8)); // 44.Nup
		data.addBodyValue("01"); // 45.Cantidad_tarjetas

		// datos de la tarjeta
		String apeNomEmbozado = ISBANStringUtils.removerCaraceteresEspeciales(
				ISBANStringUtils.removerCaraceteresEspeciales(entity.getApellidoYNombre()));
		data.addBodyValue(apeNomEmbozado.length() > 25 ? apeNomEmbozado.substring(0, 24)
				: ISBANStringUtils.fillStr(apeNomEmbozado, 24));
		data.addBodyValue("1"); // 47.Tipo de autorizado
		data.addBodyValue("1"); // 48.Tipo de tarjeta
		data.addBodyValue(entity.getNup()); // 49.Nup ES EL NUP DEL ADICIONAL
		data.addBodyValue(TARJETA_ADICIONAL_DURACIONTARJETA); // 50.Duración
		// tarjeta
		data.addBodyValue(entity.getPorcentajeLimiteCompra()); // 51.Porcentaje
		// limite compra
		data.addBodyValue(entity.getPorcentajeLimiteCompra()); // 52.Porcentaje
		// limite cuotas
		data.addBodyValue(entity.getPorcentajeLimiteCompra()); // 53.Porcentaje
		// limite
		// adelanto
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 3)); // 54.Bonificación
		data.addBodyValue(ISBANStringUtils.repeat(CERO, 2)); // 55.Cuotas
		// bonificación
		data.addBodyValue("0"); // 56.cargo
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 3)); // 57.área
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 3)); // 58.Sector
		data.addBodyValue(entity.getPorcentajeLimiteCompra()); // 59.Porcentaje
		// limite
		// tarjeta
		data.addBodyValue(ISBANStringUtils.repeat(BLANK, 17)); // 60.Cuarta
		// línea

		return data;
	}
}
