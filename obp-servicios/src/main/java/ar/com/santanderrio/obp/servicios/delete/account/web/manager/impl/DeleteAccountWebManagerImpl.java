package ar.com.santanderrio.obp.servicios.delete.account.web.manager.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.DeleteAccountResponseDTO;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.delete.account.config.FeatureBajaCuentaConfig;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountRequest;
import ar.com.santanderrio.obp.servicios.delete.account.utils.AccountUtils;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.ComprobanteBajaCuentaPDF;
import ar.com.santanderrio.obp.servicios.delete.account.web.dto.ItemDetalleRow;
import ar.com.santanderrio.obp.servicios.delete.account.web.manager.DeleteAccountWebManager;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.DeleteAccountView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.Invalidante;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.ProductoBajaView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.ProductosBajaResponseView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitarBajaCuentaView;
import ar.com.santanderrio.obp.servicios.delete.account.web.view.SolicitudDeleteAccountView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class DeleteAccountWebManagerImpl implements DeleteAccountWebManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAccountWebManagerImpl.class);

	@Autowired
	private SesionCliente sesionCliente;

	@Autowired
	private SesionParametros sesionParametros;

	@Autowired
	private CuentaBO cuentaBO;

	@Autowired
	private LegalBO legalBo;

	@Autowired
	private FeatureBajaCuentaConfig configBajaCuenta;

	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private MensajeBO mensajeBO;

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Value("classpath:/report/comprobantes/cabecera_comprobante.png")
	private Resource logoCabecera;

	@Value("classpath:/report/comprobantes/cierre de comprobante.png")
	private Resource logoPie;

	@Value("classpath:/report/bajaCuentas/DetalleInvalidantes.jasper")
	private Resource archivoJasperDetalleInvalidantesBajaProducto;

	@Value("classpath:/report/bajaCuentas/ComprobanteBajaProducto.jasper")
	private Resource archivoJasperComprobanteBajaProducto;

	// LINKS Y GOTOS
	private static final String TYC = "50036";
	private static final String CANCELLATION_REASON = "customer_decision";

	private static final String ESTADISTICA_OK = "1";
	private static final String ESTADISTICA_ERROR = "2";
	//BAJA
	private static final String ESTADISTICA_INVALIDANTES = "3";
	private static final String ESTADISTICA_SALDO_POSITIVO = "4";
	//CONFIG
	private static final String ESTADISTICA_SEGMENTO_NO_HABILITADO = "3";
	private static final String ESTADISTICA_FEATURE_DESACTIVADO = "4";
	private static final String ESTADISTICA_FUERA_HORARIO = "5";
	private static final String ESTADISTICA_NUP_NO_HABILITADO = "6";
	private static final String CONFIG_DATE_FORMAT = "HH:mm:ss";
	private static final String COMPROBANTE_DATE_FORMAT = "dd/MM/yyyy - HH:mm";

	private static final String PLACEHOLDER_PARAM = "{0}";

	@Override
	public Respuesta<DeleteAccountView> deleteCuenta(SolicitudDeleteAccountView inView) {
		final Estadistica estadisticaOperacion = new Estadistica();
		final Boolean isSimulacion = inView.getSimulate();

		Respuesta<DeleteAccountView> respuesta;
		DeleteAccountView deleteCuentaView = new DeleteAccountView();

		Cuenta cuenta = AccountUtils.getCuenta(sesionCliente.getCliente(),
				AccountUtils.getPredicateNumeroCuentaFormateado(inView.getCuenta()));

		try {
			if(cuenta == null) { throw new BusinessException("No se encontro numero de cuenta: " + inView.getCuenta()); }

			Respuesta<DeleteAccountResponseDTO> response = cuentaBO.deleteCuenta(
					buildRequest(inView, sesionCliente.getCliente(), cuenta), cuenta);

			if (response.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				estadisticaOperacion.setCodigoError(ESTADISTICA_OK);
				if(sesionParametros.getBajaCuentaWarning()) {
					deleteCuentaView.setMensajeInformativo(sesionParametros.getMensajeInformativo());
				}
				respuesta = respuestaFactory.crearRespuestaOk(DeleteAccountView.class);
			} else if (response.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				estadisticaOperacion.setCodigoError(ESTADISTICA_SALDO_POSITIVO);
				String mensajeSimulacion = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_MENSAJE_INFORMATIVO_SALDO_POSITIVO).getMensaje();
				String mensajeInformativo = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_MENSAJE_INFORMATIVO_SALDO_POSITIVO_FEEDBACK).getMensaje();
				deleteCuentaView.setMensajeInformativo(mensajeSimulacion);
				deleteCuentaView.setInvalidantes(response.getRespuesta().getInvalidantes());
				sesionParametros.setMensajeInformativo(mensajeInformativo.replace(PLACEHOLDER_PARAM, response.getRespuesta().getMensajeInformativo()));
				sesionParametros.setBajaCuentaWarning(true);
				respuesta = respuestaFactory.crearRespuestaWarning(deleteCuentaView, null,
						TipoError.INVALIDANTES_SALDO_POSITIVO, null);
			} else {
				estadisticaOperacion.setCodigoError(ESTADISTICA_INVALIDANTES);
				deleteCuentaView.setInvalidantes(response.getRespuesta().getInvalidantes());
				respuesta = respuestaFactory.crearRespuestaWarning(deleteCuentaView, null,
						response.getRespuesta().getTipoInvalidante(), null);
			}

			String codigoMensaje;
			if (!isSimulacion && inView.getCajaAhorro()) {
				deleteCuentaView.setNumeroCuenta(ISBANStringUtils.formatearNumeroCuenta(response.getRespuesta().getNewAccountNumber()));
				deleteCuentaView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_MENSAJE_AZUL).getMensaje());
				codigoMensaje = CodigoMensajeConstantes.BAJA_CUENTA_OK_BAJA_CON_CA;
			} else {
				deleteCuentaView.setNumeroCuenta(inView.getCuenta());
				codigoMensaje = CodigoMensajeConstantes.BAJA_CUENTA_OK_BAJA_SIN_CA;
			}

			deleteCuentaView.setIrASucursal(TipoError.INVALIDANTES_SUCURSAL.equals(response.getRespuesta().getTipoInvalidante()));
			deleteCuentaView.setFechaHora(new SimpleDateFormat(COMPROBANTE_DATE_FORMAT).format(new Date()));
			deleteCuentaView.setIdentificacionCliente(sesionCliente.getCliente().getNup());
			deleteCuentaView.setCuentaAEliminar(inView.getCuenta());
			deleteCuentaView.setNroControl("00");
			deleteCuentaView.setNroDeComprobante(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
			deleteCuentaView.setKeepsAccount(inView.getCajaAhorro());
			deleteCuentaView.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje());
			respuesta.setRespuesta(deleteCuentaView);

			try {
				deleteCuentaView.setTyc(legalBo.obtenerLegal(TYC));
			} catch (DAOException e) {
				LOGGER.error("Ocurrió un error al obtener los tyc.", e);
			}
			sesionParametros.setPdfData(deleteCuentaView);
		} catch (BusinessException be) {
			estadisticaOperacion.setCodigoError(ESTADISTICA_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BAJA_CUENTA, CodigoMensajeConstantes.BAJA_CUENTA_ERROR_BAJA);
		}

		estadisticaOperacion.setCodigoTransaccion(isSimulacion ? EstadisticasConstants.BAJA_CUENTA_SIMULACION :
				EstadisticasConstants.BAJA_CUENTA_CONFIRMACION);

		estadisticaManager.add(estadisticaOperacion);
		return respuesta;
	}

	private DeleteAccountRequest buildRequest(SolicitudDeleteAccountView inView, Cliente cliente, Cuenta cuenta) {
		return DeleteAccountRequest.builder()
				.cancellationReason(CANCELLATION_REASON)
				.keepsAccount(inView.getCajaAhorro().toString())
				.simulate(inView.getSimulate().toString())
				.nup(cliente.getNup())
				.formattedAccountId(cuenta.getCbu())
				.build();
	}

	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		Respuesta<ReporteView> respuesta;
		DeleteAccountView pdfData = sesionParametros.getPdfData();
		Estadistica estadistica = new Estadistica(EstadisticasConstants.BAJA_CUENTA_DESCARGA_COMPROBANTE,
				ESTADISTICA_OK);

		List<ItemDetalleRow> listDetalleOperacion = new ArrayList<ItemDetalleRow>();

		ItemDetalleRow itemRow = new ItemDetalleRow();
		itemRow.setLabel("Cerraste");
		itemRow.setDescripcionPaquete(sesionParametros.getDescCtaAEliminar());
		itemRow.setNumeroPaquete(pdfData.getCuentaAEliminar());
		listDetalleOperacion.add(itemRow);

		if(pdfData.getKeepsAccount()) {
			ItemDetalleRow itemRow2 = new ItemDetalleRow();
			itemRow2.setLabel("Mantenés sin cargo");
			itemRow2.setDescripcionPaquete("Caja de Ahorro en pesos");
			itemRow2.setNumeroPaquete(pdfData.getNumeroCuenta());
			listDetalleOperacion.add(itemRow2);
		}

		try {
			ComprobanteBajaCuentaPDF comprobanteBajaPDF = ComprobanteBajaCuentaPDF.builder()
		        .fechaHoraOperacion(new SimpleDateFormat(COMPROBANTE_DATE_FORMAT).format(new Date()))
		        .logoCabeceraPath(logoCabecera.getFile().getPath())
		        .logoPiePath(logoPie.getFile().getPath())
		        .mantieneCajaAhorro(pdfData.getKeepsAccount())
		        .tituloCuentaCerrada("Ya cerraste tu cuenta Santander")
		        .tituloSolicitudEnviada("Recibimos tu solicitud")
		        .listItemsProcesados(listDetalleOperacion)
		        .build();

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperComprobanteBajaProducto.getInputStream());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, comprobanteBajaPDF.mapParams(), new JREmptyDataSource());
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();

			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("Comprobante baja de cuenta.pdf");

			respuesta = respuestaFactory.crearRespuestaOk(ReporteView.fromReporte(reporte));
		} catch (JRException ex) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.BAJA_CUENTA_ERROR_COMPROBANTE);
		} catch (IOException e) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.BAJA_CUENTA_ERROR_COMPROBANTE);
		}
		estadisticaManager.add(estadistica);
		return respuesta;
	}

	@Override
	public Respuesta<ReporteView> generarInvalidantesPDF() {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		DeleteAccountView pdfData = sesionParametros.getPdfData();
		Estadistica estadistica = new Estadistica(EstadisticasConstants.BAJA_CUENTA_DESCARGA_DETALLE_INVALIDANTES,
				ESTADISTICA_OK);

		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasperDetalleInvalidantesBajaProducto.getInputStream());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("FECHA_HORA_OPERACION", new SimpleDateFormat(COMPROBANTE_DATE_FORMAT).format(new Date()));

			List<String> invalidantes = new ArrayList<String>();

			for (Invalidante inv : pdfData.getInvalidantes()) {
				invalidantes.add(inv.getLabel());
			}

			String mensaje = pdfData.getIrASucursal() ? mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_MENSAJE_COMPROBANTE_SUCURSAL).getMensaje()
				: mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.BAJA_CUENTA_MENSAJE_COMPROBANTE).getMensaje();
			mensaje = mensaje.replace(PLACEHOLDER_PARAM, sesionParametros.getDescCtaAEliminar() + " "
					+ sesionParametros.getPdfData().getCuentaAEliminar());
			parameters.put("MENSAJE_INFORMATIVO", mensaje);
			parameters.put("LIST_INVALIDANTES", invalidantes);
			parameters.put("LOGO_CABECERA", logoCabecera.getFile().getPath());
			parameters.put("LOGO_PIE", logoPie.getFile().getPath());

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			// se crea el archivo PDF
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

			byte[] byteArray = outStream.toByteArray();

			Reporte reporte = new Reporte();
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(byteArray);
			reporte.setNombre("Detalle pasos a seguir.pdf");

			respuesta = respuestaFactory.crearRespuestaOk(ReporteView.fromReporte(reporte));

		} catch (JRException ex) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.BAJA_CUENTA_ERROR_COMPROBANTE);
		} catch (IOException e) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.BAJA_CUENTA_ERROR_COMPROBANTE);
		}
		estadisticaManager.add(estadistica);
		return respuesta;
	}

	@Override
	public Respuesta<SolicitarBajaCuentaView> solicitarBajaCuenta() {
		Respuesta<SolicitarBajaCuentaView> moduloHabilitado = checkModuloHabilitado(sesionCliente.getCliente());
		if(!EstadoRespuesta.OK.equals(moduloHabilitado.getEstadoRespuesta())) {
			return Respuesta.copy(moduloHabilitado);
		}
		SolicitarBajaCuentaView response = new SolicitarBajaCuentaView();
		response.setMostrarRetencion(sesionCliente.getCliente().getSegmento().isSelectOnline());
		return respuestaFactory.crearRespuestaOk(SolicitarBajaCuentaView.class);
	}

	private Respuesta<SolicitarBajaCuentaView> checkModuloHabilitado(Cliente cliente) {
		Estadistica estadistica = new Estadistica(EstadisticasConstants.BAJA_CUENTA_INICIO_OPERACION,
				ESTADISTICA_OK);

		if(!configBajaCuenta.isFeatureEnabled()) {
			estadistica.setCodigoError(ESTADISTICA_FEATURE_DESACTIVADO);
			estadisticaManager.add(estadistica);
			return respuestaFactory.crearRespuestaError(null, TipoError.OPERACION_DESHABILITADA,
					CodigoMensajeConstantes.BAJA_CUENTA_OPERACION_DESHABILITADA);
		}

		try {
			String fechaActual = new SimpleDateFormat(CONFIG_DATE_FORMAT).format(new Date());
			Date fecDesde = new SimpleDateFormat(CONFIG_DATE_FORMAT).parse(configBajaCuenta.getHorarioDesde());
			Date fecHasta = new SimpleDateFormat(CONFIG_DATE_FORMAT).parse(configBajaCuenta.getHorarioHasta());

			Calendar calendarFrom = Calendar.getInstance();
		    calendarFrom.setTime(fecDesde);
		    calendarFrom.add(Calendar.DATE, 1);

		    Calendar calendarTo = Calendar.getInstance();
		    calendarTo.setTime(fecHasta);
		    calendarTo.add(Calendar.DATE, 1);

		    Date d = new SimpleDateFormat(CONFIG_DATE_FORMAT).parse(fechaActual);
		    Calendar calendarCurrent = Calendar.getInstance();
		    calendarCurrent.setTime(d);
		    calendarCurrent.add(Calendar.DATE, 1);

		    Date toCompare = calendarCurrent.getTime();
		    if (toCompare.before(calendarFrom.getTime()) || toCompare.after(calendarTo.getTime())) {
		    	estadistica.setCodigoError(ESTADISTICA_FUERA_HORARIO);
		    	return respuestaFactory.crearRespuestaError("", TipoError.FUERA_DE_HORARIO,
		    			CodigoMensajeConstantes.BAJA_CUENTA_ERROR_FUERA_HORARIO_BAJA);
		    }
		} catch (ParseException e) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			estadisticaManager.add(estadistica);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

		try {
			if(configBajaCuenta.isValidateNup() && !configBajaCuenta.getNupsHabilitados().contains(cliente.getNup())) {
				estadistica.setCodigoError(ESTADISTICA_NUP_NO_HABILITADO);
				estadisticaManager.add(estadistica);
				ItemMensajeRespuesta itemMensajeRespuesta = respuestaFactory.crearItemMensajeRespuesta("null",
						TipoError.NUP_NO_HABILITADO, null);
				return respuestaFactory.crearRespuestaError(SolicitarBajaCuentaView.class, Collections.singletonList(itemMensajeRespuesta));
			}
		} catch (DAOException e) {
			estadistica.setCodigoError(ESTADISTICA_ERROR);
			estadisticaManager.add(estadistica);
			ItemMensajeRespuesta itemMensajeRespuesta = respuestaFactory.crearItemMensajeRespuesta("null",
					TipoError.OPERACION_DESHABILITADA, null);
			return respuestaFactory.crearRespuestaError(SolicitarBajaCuentaView.class,
					Collections.singletonList(itemMensajeRespuesta));
		}


		if(configBajaCuenta.isValidateSegmentosHabilitados() && !getClienteInSegmento(cliente)) {
			estadistica.setCodigoError(ESTADISTICA_SEGMENTO_NO_HABILITADO);
			estadisticaManager.add(estadistica);
			return respuestaFactory.crearRespuestaError(null, TipoError.OPERACION_SEGMENTO_NO_HABILITADO,
					CodigoMensajeConstantes.BAJA_CUENTA_OPERACION_SEGMENTO_NO_HABILITADO);
		}

		estadisticaManager.add(estadistica);
		return respuestaFactory.crearRespuestaOk(SolicitarBajaCuentaView.class);
	}

	public boolean getClienteInSegmento(Cliente cliente) {
		for(String nombreSegmento: cliente.getSegmento().getSegmentos()) {
			if(configBajaCuenta.getSegmentosList().contains(nombreSegmento)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Respuesta<ProductosBajaResponseView> getProductosBaja() {
		Predicate productosBajaCuentaPredicate = PredicateUtils.andPredicate(
				PredicateUtils.anyPredicate(Arrays.asList(AccountUtils.CajaAhorroPredicate,
						AccountUtils.CuentaCorrientePredicate,AccountUtils.CuentaUnicaPredicate)),
				PredicateUtils.notPredicate(AccountUtils.CuentaPrivadaPredicate));

		Set<Cuenta> cuentas = AccountUtils.getCuentasByPredicate(sesionCliente.getCliente(), productosBajaCuentaPredicate);
		List<ProductoBajaView> productosBaja = new ArrayList<ProductoBajaView>();

		for(Cuenta cuenta: cuentas) {
			ProductoBajaView bajaProducto = new ProductoBajaView();
			bajaProducto.setDescripcion(cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
			bajaProducto.setCodigo(cuenta.getCodigoPaquete());
			bajaProducto.setNumero(cuenta.obtenerNroCuentaFormateado());
			bajaProducto.setCuenta(cuenta.getIndex());
			productosBaja.add(bajaProducto);
		}

		ProductosBajaResponseView productosBajaResponseView = new ProductosBajaResponseView();
		productosBajaResponseView.setProductos(productosBaja);
		return buildResponse(productosBajaResponseView);
	}

	private Respuesta<ProductosBajaResponseView> buildResponse(ProductosBajaResponseView productosBajaResponse) {
		Respuesta<ProductosBajaResponseView> response = new Respuesta<ProductosBajaResponseView>();
		response.setRespuesta(productosBajaResponse);
		response.setEstadoRespuesta(EstadoRespuesta.OK);

		if(productosBajaResponse.getProductos().isEmpty()) {
			response.setEstadoRespuesta(EstadoRespuesta.ERROR);
			response.add(respuestaFactory.crearItemMensajeRespuesta(null, TipoError.BAJA_CUENTAS_ERROR_SIN_PRODUCTOS,
					CodigoMensajeConstantes.BAJA_CUENTA_ERROR_SIN_PRODUCTOS));
		}

		estadisticaManager.add(EstadisticasConstants.BAJA_CUENTA_CONFIGURACION_PRODUCTOS,
				EstadoRespuesta.OK.equals(response.getEstadoRespuesta()) ? ESTADISTICA_OK : ESTADISTICA_ERROR);
		return response;
	}
}
