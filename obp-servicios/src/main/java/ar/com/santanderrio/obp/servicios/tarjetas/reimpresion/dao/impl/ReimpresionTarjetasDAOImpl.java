/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.ReimpresionTarjetasDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaDatos;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaOut;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitada;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * The Class ReimpresionTarjetasDAOImpl.
 */
@Component
public class ReimpresionTarjetasDAOImpl implements ReimpresionTarjetasDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ReimpresionTarjetasDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The Constant LONGITUD_TIPO_CUENTA. */
	private static final int LONGITUD_MARCA = 1;

	/** The Constant LONGITUD_TIPO_CUENTA. */
	private static final int LONGITUD_TIPO_CUENTA = 1;

	/** The Constant LONGITUD_CUENTA. */
	private static final int LONGITUD_CUENTA = 5;

	/** The Constant LONGITUD_FIELDSET. */
	private static final int LONGITUD_FIELDSET = 43;

	/** The Constant LONGITUD_NOMBRE_APELLIDO. */
	private static final int LONGITUD_NOMBRE_APELLIDO = 40;

	/** The Constant LONGITUD_CODIGO_TARJETA. */
	private static final int LONGITUD_CODIGO_TARJETA = 1;

	/** The Constant LONGITUD_NUMERO_TARJETA. */
	private static final int LONGITUD_NUMERO_TARJETA = 16;

	/** The Constant LONGITUD_NUMERO_CUENTA. */
	private static final int LONGITUD_NUMERO_CUENTA = 26;

	/** The Constant LONGITUD_MOTIVO_REIMPRESION. */
	private static final int LONGITUD_MOTIVO_REIMPRESION = 2;

	/** The Constant LONGITUD_DOMICILIO. */
	private static final int LONGITUD_DOMICILIO = 3;

	/** The Constant LIMITE_ITERACIONES. */
	private static final int LIMITE_ITERACIONES = 10;

	/** The Constant OPCION. */
	private static final String OPCION = "A";

	/** The Constant CATEGORIA. */
	private static final String CATEGORIA = "T";

	/** The Constant CANTIDAD_CAMPOS_SALIDA. */
	private static final int CANTIDAD_CAMPOS_SALIDA = 3;

	/** The Constant TARJETA_INVALIDA. */
	private static final String TARJETA_INVALIDA = "0000000000000000";

	/** The Constant SOLICITUD_EN_CURSO. */
	private static final Integer SOLICITUD_EN_CURSO = 10000060;
	
	/** The Constant SOLICITUD_EN_CURSO_DESA. */
	private static final Integer SOLICITUD_EN_CURSO_DESA = 10000050;

	private static final Integer SOLICITUD_EN_CURSO_WOMEN = 10000002;
	
	/** The file jasper. */
	@Value("classpath:/report/reimpresion/comprobanteReimpresion.jasper")
	private Resource fileJasper;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cabecera_comprobante.png")
	private Resource logoCabecera;

	/** The logo. */
	@Value("classpath:/report/comprobantes/logo_cierre_comprobante.png")
	private Resource logoPie;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.
	 * ReimpresionTarjetasDAO#consultaDatosTarjetas(ar.com.santanderrio.obp.
	 * servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasIn)
	 */
	public ConsultaDatosTarjetasOut consultaDatosTarjetas(ConsultaDatosTarjetasIn in) {
		ConsultaDatosTarjetasOut out = new ConsultaDatosTarjetasOut();
		IatxRequest request = buildIatxRequest(in);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				out = buildDatosTarjeta(iatxResponse);
			} else {
				throw new IatxException("Error" + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
		return out;

	}

	/**
	 * ConsultaDatosTarjetasOut.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the consulta datos tarjetas out
	 */
	private ConsultaDatosTarjetasOut buildDatosTarjeta(IatxResponse iatxResponse) {
		ConsultaDatosTarjetasOut out = new ConsultaDatosTarjetasOut();
		List<TarjetaDatos> tarjetasOut = new ArrayList<TarjetaDatos>();
		out.setHayMasDatos(iatxResponse.getData(2));
		out.setClaveSegundoLlamado(iatxResponse.getData(1));
		int cant = Integer.parseInt(iatxResponse.getData(5));
		int next = 6;
		for (int n = 0; n < cant; ++n) {
			TarjetaDatos tarjetaOut = new TarjetaDatos();
			tarjetaOut.setNroTarjeta(iatxResponse.getData(next));
			tarjetaOut.setApliCtaRelacionada(iatxResponse.getData(next + 1));
			tarjetaOut.setSucursalCtaRelacionada(iatxResponse.getData(next + 2));
			tarjetaOut.setTipoCuenta(iatxResponse.getData(3));
			tarjetaOut.setNroCuenta(iatxResponse.getData(4));
			tarjetaOut.setEstadoTarjeta(iatxResponse.getData(next + 6));
			tarjetaOut.setApellidoNombreEmbozado(iatxResponse.getData(next + 27));
			tarjetasOut.add(tarjetaOut);
			next += LONGITUD_FIELDSET;
		}
		out.setTarjetas(tarjetasOut);
		return out;
	}

	/**
	 * buildIatxRequest.
	 *
	 * @param inTarjeta
	 *            the in tarjeta
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequest(ConsultaDatosTarjetasIn inTarjeta) {
		IatxRequest requestIatx = new IatxRequest("CNSTJCDATC", "100");
		IatxRequestData requestDataIatx = new IatxRequestData(inTarjeta.getCliente());
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getMarca(), LONGITUD_MARCA, '0'));
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getTipoCuenta(), LONGITUD_TIPO_CUENTA, '0'));
		requestDataIatx.addBodyValue(StringUtils.leftPad(inTarjeta.getNroCuenta().substring(5), LONGITUD_CUENTA, '0'));
		requestDataIatx.addBodyValue("    ");
		requestDataIatx.addBodyValue("000");
		requestDataIatx.addBodyValue("0");
		requestDataIatx.addBodyValue("0000000000000");
		requestDataIatx.addBodyValue("00");
		requestDataIatx.addBodyValue(TARJETA_INVALIDA);
		requestDataIatx.addBodyValue(OPCION);
		requestDataIatx.addBodyValue(CATEGORIA);
		requestDataIatx.addBodyValue(inTarjeta.getClaveSegundoLlamado());

		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.
	 * ReimpresionTarjetasDAO#altaReimpresionTarjetaDebito(ar.com.santanderrio.
	 * obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.
	 * TarjetaSolicitada)
	 */
	public TarjetaOut altaReimpresionTarjetaDebito(Cliente cliente, TarjetaSolicitada tarjetaSolicitada) {
		TarjetaOut out = new TarjetaOut();
		IatxRequest request = buildIatxRequestAltaReimpresionDebito(cliente, tarjetaSolicitada);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				out = buildResponseAltaReimpresionDebito(iatxResponse);
            } else if (SOLICITUD_EN_CURSO.equals(iatxResponse.getErrorCode())
                    || SOLICITUD_EN_CURSO_DESA.equals(iatxResponse.getErrorCode())) {
                out.setCodigoTarjeta(SOLICITUD_EN_CURSO_DESA.toString());
			} else {
				throw new IatxException("Error IATX: " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
		return out;
	}

	/**
	 * Builds the response alta reimpresion debito.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the tarjeta out
	 */
	private TarjetaOut buildResponseAltaReimpresionDebito(IatxResponse iatxResponse) {
		TarjetaOut tarjetaDebito = new TarjetaOut();
		tarjetaDebito.setNroTarjeta(iatxResponse.getData(1));
		tarjetaDebito.setDescripcionMensaje(iatxResponse.getData(2));
		tarjetaDebito.setFechaHoraOperacion(iatxResponse.getFechaHoraReq());
		return tarjetaDebito;
	}

	/**
	 * Builds the iatx request alta reimpresion debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param in
	 *            the in
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequestAltaReimpresionDebito(Cliente cliente, TarjetaSolicitada in) {
		IatxRequest request = new IatxRequest("ALTIMPRDEB", "110");
		IatxRequestData requestData = new IatxRequestData(cliente);
		requestData.addBodyValue(
				StringUtils.rightPad(in.getNombreApellido(), LONGITUD_NOMBRE_APELLIDO, StringUtils.EMPTY));
		requestData.addBodyValue(StringUtils.leftPad(in.getCodigoTarjeta(), LONGITUD_CODIGO_TARJETA, '0'));
		requestData.addBodyValue(StringUtils.leftPad(in.getNroTarjeta(), LONGITUD_NUMERO_TARJETA, '0'));
		requestData.addBodyValue(StringUtils.leftPad(in.getNroCuenta(), LONGITUD_NUMERO_CUENTA, '0'));
		requestData.addBodyValue(StringUtils.leftPad(in.getMotivoReimpresion(), LONGITUD_MOTIVO_REIMPRESION, '0'));
		requestData.addBodyValue(StringUtils.leftPad(in.getDomicilio(), LONGITUD_DOMICILIO, '0'));
		request.setData(requestData);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.
	 * ReimpresionTarjetasDAO#altaReimpresionTarjetaCredito(ar.com.santanderrio.
	 * obp.servicios.tarjetas.reimpresion.entities.AltaReimpresionTarjetasIn)
	 */
	public AltaReimpresionTarjetasOut altaReimpresionTarjetaCredito(AltaReimpresionTarjetasIn in) {
		AltaReimpresionTarjetasOut out = new AltaReimpresionTarjetasOut();
		IatxRequest request = buildIatxRequestAltaReimpresionCredito(in);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				out = buildDatosAltaReimpresionCredito(iatxResponse, in);
			} else if (SOLICITUD_EN_CURSO.equals(iatxResponse.getErrorCode())
                    || SOLICITUD_EN_CURSO_DESA.equals(iatxResponse.getErrorCode())) {
                out.setCodigoTarjeta(SOLICITUD_EN_CURSO_DESA.toString());
			} else if (SOLICITUD_EN_CURSO_WOMEN.equals(iatxResponse.getErrorCode())){
				out.setCodigoTarjeta(SOLICITUD_EN_CURSO_WOMEN.toString());			
			} else {
				throw new IatxException("Error IATX: " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(e.getMessage());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
		return out;
	}

	/**
	 * Builds the datos alta reimpresion credito.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param in
	 *            the in
	 * @return the alta reimpresion tarjetas out
	 */
	private AltaReimpresionTarjetasOut buildDatosAltaReimpresionCredito(IatxResponse iatxResponse,
			AltaReimpresionTarjetasIn in) {
		AltaReimpresionTarjetasOut out = new AltaReimpresionTarjetasOut();
		List<TarjetaOut> tarjetasCredito = new ArrayList<TarjetaOut>();
		for (int x = 0; x < LIMITE_ITERACIONES; x++) {

			// determina la posicion de la que deben tomarse los campos a partir
			// de la segunda iteracion
			int corrimientoCampos = x * CANTIDAD_CAMPOS_SALIDA;
			TarjetaOut tarjeta = new TarjetaOut();
			tarjeta.setCodigoTarjeta(iatxResponse.getData(1 + corrimientoCampos));
			tarjeta.setNroTarjeta(iatxResponse.getData(2 + corrimientoCampos));
			tarjeta.setDescripcionMensaje(iatxResponse.getData(3 + corrimientoCampos));
			tarjeta.setFechaHoraOperacion(iatxResponse.getFechaHoraReq());
			if (!TARJETA_INVALIDA.equals(tarjeta.getNroTarjeta())) {
				tarjetasCredito.add(tarjeta);
			}
		}
		out.setTarjetasCredito(tarjetasCredito);
		return out;
	}

	/**
	 * Builds the iatx request alta reimpresion credito.
	 *
	 * @param in
	 *            the in
	 * @return the iatx request
	 */
	private IatxRequest buildIatxRequestAltaReimpresionCredito(AltaReimpresionTarjetasIn in) {
		IatxRequest request = new IatxRequest("ALTIMPRCRE", "110");
		IatxRequestData requestData = new IatxRequestData(in.getCliente());

		requestData.addBodyValue(StringUtils.rightPad(in.getTarjetasCredito().get(0).getNombreApellido(),
				LONGITUD_NOMBRE_APELLIDO, StringUtils.EMPTY));
		for (TarjetaSolicitada tarjetaSolicitada : in.getTarjetasCredito()) {
			requestData.addBodyValue(
					StringUtils.leftPad(tarjetaSolicitada.getCodigoTarjeta(), LONGITUD_CODIGO_TARJETA, '0'));
			requestData
					.addBodyValue(StringUtils.leftPad(tarjetaSolicitada.getNroTarjeta(), LONGITUD_NUMERO_TARJETA, '0'));
			requestData.addBodyValue(
					StringUtils.leftPad(tarjetaSolicitada.getMotivoReimpresion(), LONGITUD_MOTIVO_REIMPRESION, '0'));
			requestData.addBodyValue(StringUtils.leftPad(tarjetaSolicitada.getDomicilio(), LONGITUD_DOMICILIO, '0'));
		}
		requestData = llenadoRestante(requestData, in);
		request.setData(requestData);
		return request;
	}

	/**
	 * Llenado restante.
	 *
	 * @param requestData
	 *            the request data
	 * @param in
	 *            the in
	 * @return the iatx request data
	 */
	private IatxRequestData llenadoRestante(IatxRequestData requestData, AltaReimpresionTarjetasIn in) {
		for (int x = 0; x < (LIMITE_ITERACIONES - in.getTarjetasCredito().size()); x++) {
			requestData
					.addBodyValue(StringUtils.leftPad(StringUtils.EMPTY, LONGITUD_CODIGO_TARJETA, StringUtils.EMPTY));
			requestData
					.addBodyValue(StringUtils.leftPad(StringUtils.EMPTY, LONGITUD_NUMERO_TARJETA, StringUtils.EMPTY));
			requestData.addBodyValue(
					StringUtils.leftPad(StringUtils.EMPTY, LONGITUD_MOTIVO_REIMPRESION, StringUtils.EMPTY));
			requestData.addBodyValue(StringUtils.leftPad(StringUtils.EMPTY, LONGITUD_DOMICILIO, StringUtils.EMPTY));
		}
		return requestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao.
	 * ReimpresionTarjetasDAO#descargarComprobante(ar.com.santanderrio.obp.
	 * servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante)
	 */
	@Override
	public Reporte descargarComprobante(DatosReimpresionComprobante datos) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {

			jasperReport = (JasperReport) JRLoader.loadObject(fileJasper.getInputStream());

			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			// se procesa el archivo jasper

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_CIERRE", logoPie.getFile().getPath());
			parameters.put("domicilio", datos.getDomicilios().get(0).getDomicilio());
			parameters.put("tipoDomicilio", datos.getDomicilios().get(0).getTipoDomicilio());
			parameters.put("FECHA_OP", ISBANStringUtils.formatearFecha(new Date()));

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new JRBeanCollectionDataSource(datos.getTarjetas(), false));
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
			logger.error(ex.getMessage(), ex);
			throw new ISBANRuntimeException(ex);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ISBANRuntimeException(e);
		}
	}

}
