/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ReporteComprobantePDFDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCAfipView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCVEPView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePMCView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobantePagoTarjetaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ComprobanteOrdenVenta;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.TipoNuevoPagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * BO de reporte comprobante pdf.
 */
@Component
public class ReporteComprobantePDFBOImpl implements ReporteComprobantePDFBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReporteComprobantePDFBOImpl.class);

	/** The Constant DOLARES. */
	private static final String DOLARES = "Dólares";

	/** The pesos Y dolares. */
	private static final String PESOSYDOLARES = "Pesos y Dólares";

	/** The peso. */
	private static final String PESO = "peso";

	/** The pesos. */
	private static final String PESOS = "Pesos";

	/** The Constant LABEL_DEBITO_PESOS. */
	private static final String LABEL_DEBITO_PESOS = "Importe total debitado en pesos";

	/** The Constant LABEL_DEBITO_DOLARES. */
	private static final String LABEL_DEBITO_DOLARES = "Importe en dólares debitado";

	/** The Constant LABEL_CREDITO_PESOS. */
	private static final String LABEL_CREDITO_PESOS = "Importe en pesos acreditado";

	/** The Constant LABEL_CREDITO_DOLARES. */
	private static final String LABEL_CREDITO_DOLARES = "Importe acreditado en dólares";

	/** The Constant ALIAS. */
	private static final String ALIAS = "Alias";

	/** The ReporteComprobanteDAO. */
	@Autowired
	private ReporteComprobantePDFDAO reporteDAO;

	/** The RespuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The medios pago BO. */
	@Autowired
	private MediosPagoBO mediosPagoBO;

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.comprobantes.web
	 * .view.DetalleComprobanteView,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * TipoOperacionComprobanteEnum)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(DetalleComprobanteView detalleView) {
		try {
			Reporte reporte = reporteDAO.obtenerReporteComprobantePDF(detalleView);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (Exception e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.comprobantes.web
	 * .view.DetalleComprobanteView,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * TipoOperacionComprobanteEnum)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(TransferenciaView transferenciaView) {
		DetalleComprobanteView detalleView = obtenerDetalleViewDesdeTransferenciaView(transferenciaView);
		return obtenerComprobantePDF(detalleView);
	}

	/**
	 * Obtener detalle view desde transferencia view.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdeTransferenciaView(TransferenciaView transferenciaView) {
		DetalleComprobanteTransferenciaView detalle = new DetalleComprobanteTransferenciaView();
		if (transferenciaView.isInmediata()) {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA.getDetalle());
		} else {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_TRANSFERENCIA_PROGRAMADA.getDetalle());
			detalle.setNroOperacionProgramada(transferenciaView.getNroOperacionProgramada());
		}
		setearImporte(detalle, transferenciaView.getImporte(), transferenciaView.getMoneda());
		detalle.setNroCuentaOrigen(transferenciaView.getNroCuenta());
		detalle.setTipoCuentaOrigen(transferenciaView.getTipoCuentaDescripcion());
		setearNroYTipoCuentaDestino(detalle, transferenciaView);
		setearCUITCUILDestino(detalle, transferenciaView);
		detalle.setTipoCuit(CuitEnum.CUIT_DESTINATARIO_TRANSFERENCIAS.getDetalle());
		detalle.setBanco(transferenciaView.getBanco());
		detalle.setDestinatario(transferenciaView.getTitular());
		detalle.setFecha(transferenciaView.getFechaEjecucion().substring(0, 10));
		detalle.setPlazoAcreditacion(transferenciaView.getFechaAcreditacion());
		detalle.setConcepto(transferenciaView.getConcepto().getDescripcion());
		detalle.setDesConcepto(transferenciaView.getDescripcion());
		detalle.setAvisoTransfMail(transferenciaView.getEnviaEmail());
		detalle.setNroComprobante(transferenciaView.getNumeroComprobante());
		detalle.setMail(transferenciaView.getEmail());
		detalle.setComentarios(transferenciaView.getMensajeEmail());
		detalle.setFechaOperacion(transferenciaView.getFechaOperacion());
		setearAliasDestino(detalle, transferenciaView);
		return detalle;
	}

	/**
	 * Setear CUITCUIL destino. solo si es una Tx a OBancos.
	 *
	 * @param detalle
	 *            the detalle
	 * @param transferenciaView
	 *            the transferencia view
	 */
	private void setearCUITCUILDestino(DetalleComprobanteTransferenciaView detalle,
			TransferenciaView transferenciaView) {
		if (!transferenciaView.getIsRioRio()) {
			detalle.setCuit(transferenciaView.getCuitCuil());
		}
	}

	/**
	 * Setear alias destino.
	 *
	 * @param detalle
	 *            the detalle
	 * @param transferenciaView
	 *            the transferencia view
	 */
	private void setearAliasDestino(DetalleComprobanteTransferenciaView detalle, TransferenciaView transferenciaView) {
		if (null == transferenciaView.getAliasDestino()) {
			detalle.setCuenta(null);
		} else {
			detalle.setCuenta(transferenciaView.getNroCuentaDestino());
			detalle.setTipoCuenta(transferenciaView.getTipoCuentaDestinoDescripcion());
			detalle.setNroCuentaDestino(transferenciaView.getAliasDestino());
			detalle.setTipoCuentaDestino(ALIAS);
		}

	}

	/**
	 * Setear nro Y tipo cuenta destino.
	 *
	 * @param detalle
	 *            the detalle
	 * @param transferenciaView
	 *            the transferencia view
	 */
	private void setearNroYTipoCuentaDestino(DetalleComprobanteTransferenciaView detalle,
			TransferenciaView transferenciaView) {
		if (StringUtils.isNotBlank(transferenciaView.getCbu())) {
			detalle.setCbu(transferenciaView.getCbu());
		} else {
			detalle.setNroCuentaDestino(transferenciaView.getNroCuentaDestino());
			detalle.setTipoCuentaDestino(transferenciaView.getTipoCuentaDestinoDescripcion());
		}
	}

	/**
	 * Setear importe.
	 *
	 * @param detalle
	 *            the detalle
	 * @param importe
	 *            the importe
	 * @param moneda
	 *            the moneda
	 */
	private void setearImporte(DetalleComprobanteView detalle, String importe, String moneda) {
		if (PESO.equals(moneda)) {
			detalle.setImportePesos(importe);
		} else {
			detalle.setImporteDolares(importe);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * PagoTarjetaCreditoView,
	 * ar.com.santanderrio.obp.servicios.comprobantes.dto.
	 * TipoOperacionComprobanteEnum, java.util.List,
	 * ar.com.santanderrio.obp.servicios.tarjetas.entities.
	 * ComprobantePagoTarjeta)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(PagoTarjetaCreditoView pagoTarjetaCreditoView,
			TipoOperacionComprobanteEnum tipoOperacion, List<Cuenta> cuentas,
			ComprobantePagoTarjeta comprobantePagoTarjeta) {
		DetalleComprobanteView detalleView = obtenerDetalleViewDesdePagoTarjetaCreditoView(pagoTarjetaCreditoView,
				cuentas, comprobantePagoTarjeta);
		return obtenerComprobantePDF(detalleView);
	}

	/**
	 * Obtener detalle view desde pago tarjeta credito view.
	 *
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 * @param cuentas
	 *            the cuentas
	 * @param comprobantePagoTarjeta
	 *            the comprobante pago tarjeta
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdePagoTarjetaCreditoView(
			PagoTarjetaCreditoView pagoTarjetaCreditoView, List<Cuenta> cuentas,
			ComprobantePagoTarjeta comprobantePagoTarjeta) {
		DetalleComprobantePagoTarjetaView detalle = new DetalleComprobantePagoTarjetaView();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO_SANTANDER_RIO.getDetalle());
		setearImporte(detalle, pagoTarjetaCreditoView);
		setearNumeroDeCuenta(detalle, pagoTarjetaCreditoView);
		setearTipoCuenta(detalle, pagoTarjetaCreditoView, cuentas);
		detalle.setFechaOperacion(comprobantePagoTarjeta.getFechaHora());
		detalle.setNroComprobante(comprobantePagoTarjeta.getNroComprobante());
		detalle.setNroStopDebit(comprobantePagoTarjeta.getNroComprobanteStopDebit());
		detalle.setFecha(pagoTarjetaCreditoView.getFechaDePago());
		detalle.setDestinatario(pagoTarjetaCreditoView.getNumeroTarjeta());
		detalle.setTipoTarjeta(pagoTarjetaCreditoView.getTipoTarjeta().trim());
		return detalle;

	}

	/**
	 * Setear tipo cuenta.
	 *
	 * @param detalle
	 *            the detalle
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 * @param cuentas
	 *            the cuentas
	 */
	private void setearTipoCuenta(DetalleComprobantePagoTarjetaView detalle,
			PagoTarjetaCreditoView pagoTarjetaCreditoView, List<Cuenta> cuentas) {
		if (pagoTarjetaCreditoView.getCbuPesos() != null) {
			for (Cuenta cuenta : cuentas) {
				if (detalle.getNroCuentaOrigen().equals(cuenta.obtenerNroCuentaFormateado())) {
					detalle.setTipoCuentaOrigen(cuenta.getTipoCuentaEnum().getDescripcion());
				}
			}
		}
		if (pagoTarjetaCreditoView.getCbuDolares() != null) {
			for (Cuenta cuenta : cuentas) {
				if (detalle.getNroCuentaOrigenDolares().equals(cuenta.obtenerNroCuentaFormateado())) {
					detalle.setTipoCuentaOrigenDolares(cuenta.getTipoCuentaEnum().getDescripcion());
				}
			}
		}
	}

	/**
	 * Setear numero de cuenta.
	 *
	 * @param detalle
	 *            the detalle
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 */
	private void setearNumeroDeCuenta(DetalleComprobantePagoTarjetaView detalle,
			PagoTarjetaCreditoView pagoTarjetaCreditoView) {
		if (pagoTarjetaCreditoView.getCbuDolares() != null) {
			detalle.setNroCuentaOrigenDolares(
					ISBANStringUtils.obtenerNumeroCuentaDesdeCBU(pagoTarjetaCreditoView.getCbuDolares()));
		}
		if (pagoTarjetaCreditoView.getCbuPesos() != null) {
			detalle.setNroCuentaOrigen(
					ISBANStringUtils.obtenerNumeroCuentaDesdeCBU(pagoTarjetaCreditoView.getCbuPesos()));
		}
	}

	/**
	 * Setear importe.
	 *
	 * @param detalle
	 *            the detalle
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 */
	private void setearImporte(DetalleComprobantePagoTarjetaView detalle,
			PagoTarjetaCreditoView pagoTarjetaCreditoView) {
		setearImportePesos(detalle, pagoTarjetaCreditoView);
		setearImporteDolares(detalle, pagoTarjetaCreditoView);
	}

	/**
	 * Setear importe pesos.
	 *
	 * @param detalle
	 *            the detalle
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 */
	private void setearImportePesos(DetalleComprobantePagoTarjetaView detalle,
			PagoTarjetaCreditoView pagoTarjetaCreditoView) {
		if (PESOS.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())
				|| PESOSYDOLARES.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
			String tipoPago = pagoTarjetaCreditoView.getTipoPagoTC().trim();
			if ("0".equals(tipoPago)) {
				detalle.setImportePesos(pagoTarjetaCreditoView.getImporteMinimo());
			} else if ("1".equals(tipoPago)) {
				if (PESOS.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
					detalle.setImportePesos(pagoTarjetaCreditoView.getTotalAPagarEnPesos());
				} else {
					detalle.setImportePesos(pagoTarjetaCreditoView.getSaldoPesosTC());
				}
			} else if ("2".equals(tipoPago)) {
				detalle.setImportePesos(ISBANStringUtils
						.formatearConComaYDosDecimales(pagoTarjetaCreditoView.getImportePagoPesos().getNum()));
			} else if ("3".equals(tipoPago)) {
				if (PESOS.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
					detalle.setImportePesos(pagoTarjetaCreditoView.getSaldoAPagarConvertidoAPesos());
				} else {
					detalle.setImportePesos(pagoTarjetaCreditoView.getSaldoSinSiguienteCierrePesos());
				}
			}
		}
	}

	/**
	 * Setear importe dolares.
	 *
	 * @param detalle
	 *            the detalle
	 * @param pagoTarjetaCreditoView
	 *            the pago tarjeta credito view
	 */
	private void setearImporteDolares(DetalleComprobantePagoTarjetaView detalle,
			PagoTarjetaCreditoView pagoTarjetaCreditoView) {
		if (DOLARES.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())
				|| PESOSYDOLARES.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
			String tipoPago = pagoTarjetaCreditoView.getTipoPagoTC().trim();
			if ("0".equals(tipoPago)) {
				detalle.setImporteDolares(pagoTarjetaCreditoView.getImporteMinimo());
			} else if ("1".equals(tipoPago)) {
				if (DOLARES.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
					detalle.setImporteDolares(pagoTarjetaCreditoView.getTotalAPagarEnDolares());
				} else {
					detalle.setImporteDolares(pagoTarjetaCreditoView.getSaldoDolaresTC());
				}
			} else if ("2".equals(tipoPago)) {
				detalle.setImporteDolares(ISBANStringUtils
						.formatearConComaYDosDecimales(pagoTarjetaCreditoView.getImportePagoDolares().getNum()));
			} else if ("3".equals(tipoPago)) {
				if (DOLARES.equals(pagoTarjetaCreditoView.getMonedaSeleccionado())) {
					detalle.setImporteDolares(pagoTarjetaCreditoView.getSaldoAPagarConvertidoADolares());
				} else {
					detalle.setImporteDolares(pagoTarjetaCreditoView.getSaldoSinSiguienteCierreDolares());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.compraventa.web.
	 * view.ComprobanteCompraVentaView)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(ComprobanteCompraVentaView compraVentaView) {
		DetalleComprobanteView detalleView = obtenerDetalleViewDesdeCompraVentaView(compraVentaView);
		return obtenerComprobantePDF(detalleView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.nuevarecarga.web
	 * .view.ConfirmacionRecargaView)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(ConfirmacionRecargaView recarga) {
		DetalleComprobanteView detalleView = obtenerDetalleViewDesdeConfirmacionRecargaView(recarga);
		return obtenerComprobantePDF(detalleView);
	}

	/**
	 * Obtener detalle view desde confirmacion recarga view.
	 *
	 * @param recarga
	 *            the recarga
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdeConfirmacionRecargaView(ConfirmacionRecargaView recarga) {
		DetalleComprobantePMCView detalleView = new DetalleComprobantePMCView();
		MedioPago medioPago = mediosPagoBO.obtenerPorCodigo(recarga.getCodigoEmpresa());
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setFechaPago(StringUtils.left(recarga.getFechaHora(), 10));
		detalleView.setImportePesos(ISBANStringUtils.formatearConComaYDosDecimales(recarga.getMontoId()));
		detalleView.setEmpresa(recarga.getNombreEmpresa());
		detalleView.setIdentificacion(recarga.getIdentificacionCliente());
		detalleView.setNroCuentaOrigen(recarga.getNumeroCuenta());
		detalleView.setFecha(recarga.getFechaHora());
		detalleView.setNroControl(recarga.getNroControl());
		detalleView.setNroTransaccion(recarga.getNroDeComprobante());
		return detalleView;
	}

	/**
	 * Obtener detalle view desde compra venta view.
	 *
	 * @param compraVentaView
	 *            the compra venta view
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdeCompraVentaView(ComprobanteCompraVentaView compraVentaView) {
		DetalleCompraVentaDolarView detalle = new DetalleCompraVentaDolarView();
		detalle.setNroCuentaOrigen(compraVentaView.getNroCuentaOrigen());
		detalle.setTipoCuentaOrigen(compraVentaView.getTipoCuentaOrigen());
		detalle.setDestinatario(compraVentaView.getNroCuentaDestino());
		detalle.setTipoCuentaDestino(compraVentaView.getTipoCuentaDestino());
		detalle.setCotizacionAplicada("u$s 1 = $" + compraVentaView.getCotizacion());
		setearImportesYLabels(detalle, compraVentaView);
		detalle.setLegales(compraVentaView.getLegalesDos());
		detalle.setHora(null);
		detalle.setFecha(null);
		detalle.setNroOperacion(compraVentaView.getNumeroOperacion());
		detalle.setNroComprobante(compraVentaView.getNumeroComprobante());
		detalle.setFechaOperacion(compraVentaView.getFecha() + " - " + compraVentaView.getHora());
		detalle.setImporteImpuesto(compraVentaView.getImporteImpuesto());
        detalle.setImporteImpuesto2(compraVentaView.getImporteImpuesto2());
		detalle.setImporteBienes(compraVentaView.getImpuestoBienes());
		return detalle;
	}

	/**
	 * Setear importes Y labels.
	 *
	 * @param detalle
	 *            the detalle
	 * @param compraVentaView
	 *            the compra venta view
	 */
	private void setearImportesYLabels(DetalleCompraVentaDolarView detalle,
			ComprobanteCompraVentaView compraVentaView) {
		if (null != compraVentaView.getImporteCompraDolar() && null != compraVentaView.getImporteDebitarPesos()) {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPRA_DOLARES.getDetalle());
			detalle.setLabelCredito(LABEL_CREDITO_DOLARES);
			detalle.setLabelDebito(LABEL_DEBITO_PESOS);
			detalle.setSaldoAcreditado(compraVentaView.getImporteCompraDolar());
			detalle.setSaldoDebitado(compraVentaView.getImporteDebitarPesos());
		} else {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.VENTA_DOLARES.getDetalle());
			detalle.setLabelCredito(LABEL_CREDITO_PESOS);
			detalle.setLabelDebito(LABEL_DEBITO_DOLARES);
			detalle.setSaldoAcreditado(compraVentaView.getImporteAcreditarPesos());
			detalle.setSaldoDebitado(compraVentaView.getImporteVentaDolar());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * NuevoPago, java.lang.String)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(NuevoPago nuevoPagoView, String fechaVencimiento) {
		return obtenerComprobantePDF(obtenerDetalleViewDesdeNuevoPagoView(nuevoPagoView, fechaVencimiento));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.pagos.web.view.
	 * PagoMultipleView,
	 * ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(PagoMultipleView pagoMultipleView, PagoMultipleView pagoMultipleReq,
			String fechaHora, String cuitVep, String elementoAdicional) {
		return obtenerComprobantePDF(obtenerDetalleViewDesdePagoMultipleViewView(pagoMultipleView, pagoMultipleReq,
				fechaHora, cuitVep, elementoAdicional));
	}

	/**
	 * Obtener detalle view desde nuevo pago view.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param nuevoPagoReq
	 *            the nuevo pago req
	 * @param fechaHora
	 *            the fecha hora
	 * @param cuitVep
	 *            the cuit vep
	 * @param elementoAdicional
	 *            the elemento adicional
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdePagoMultipleViewView(PagoMultipleView nuevoPagoView,
			PagoMultipleView nuevoPagoReq, String fechaHora, String cuitVep, String elementoAdicional) {
		MedioPago medioPago = mediosPagoBO.obtenerPorCodigo(StringUtils.rightPad(nuevoPagoView.getCodigoEmpresa(), 4));
		if (medioPago == null) {
			return obtenerPagoVEP(nuevoPagoView, medioPago, nuevoPagoReq, fechaHora, cuitVep);
		}
		TipoMedioPagoBO tipoMedioDePagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);

		if (tipoMedioDePagoBO == null) {
			return null;
		}
		TipoNuevoPagoEnum enumNuevoPago = tipoMedioDePagoBO.getTipoNuevoPagoEnum();
		if (esTipoPagoConDeuda(enumNuevoPago)) {
			return obtenerPagoConDeuda(nuevoPagoView, medioPago, nuevoPagoReq, fechaHora);
		} else if (esTipoPagoSinDeuda(enumNuevoPago)) {
			return obtenerPagoSinDeuda(nuevoPagoView, medioPago, nuevoPagoReq, fechaHora);
		} else if (esTipoPagoVEP(enumNuevoPago)) {
			return obtenerPagoVEP(nuevoPagoView, medioPago, nuevoPagoReq, fechaHora, cuitVep);
		} else if (esTipoPagoAfip(enumNuevoPago)) {
			return obtenerPagoAfip(nuevoPagoView, medioPago, enumNuevoPago, fechaHora, elementoAdicional);
		}
		return null;
	}

	/**
	 * Obtener detalle view desde nuevo pago view.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerDetalleViewDesdeNuevoPagoView(NuevoPago nuevoPagoView,
			String fechaVencimiento) {
		MedioPago medioPago = mediosPagoBO.obtenerPorCodigo(nuevoPagoView.getFiid());
		if (medioPago == null) {
			return obtenerPagoVEP(nuevoPagoView, medioPago, fechaVencimiento);
		}
		TipoMedioPagoBO tipoMedioDePagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);
		if (tipoMedioDePagoBO == null) {
			return null;
		}
		TipoNuevoPagoEnum enumNuevoPago = tipoMedioDePagoBO.getTipoNuevoPagoEnum();
		if (esTipoPagoConDeuda(enumNuevoPago)) {
			return obtenerPagoConDeuda(nuevoPagoView, medioPago, fechaVencimiento);
		} else if (esTipoPagoSinDeuda(enumNuevoPago)) {
			return obtenerPagoSinDeuda(nuevoPagoView, medioPago);
		} else if (esTipoPagoVEP(enumNuevoPago)) {
			return obtenerPagoVEP(nuevoPagoView, medioPago, fechaVencimiento);
		} else if (esTipoPagoAfip(enumNuevoPago)) {
			return obtenerPagoAfip(nuevoPagoView, medioPago, enumNuevoPago);
		}
		return null;
	}

	/**
	 * Obtener pago afip.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param medioPago
	 *            the medio pago
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoAfip(NuevoPago nuevoPagoView, MedioPago medioPago,
			TipoNuevoPagoEnum enumNuevoPago) {
		DetalleComprobantePMCAfipView detalleView = new DetalleComprobantePMCAfipView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setImportePesos(obtenerMontoFormateado(nuevoPagoView.getMonto()));
		detalleView.setEmpresa(nuevoPagoView.getNombreEmpresa());
		detalleView.setFechaVencimiento("-/-/-");
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(
				ISBANStringUtils.agregarGuionesANumeroCuitCuil(nuevoPagoView.getCodigoPagoElectronico()));
		detalleView.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(nuevoPagoView.getCodigoPagoElectronico2()));
		detalleView.setTipoCuit("CUIT/CUIL del empleador");
		obtenerElementoAdicional(detalleView, nuevoPagoView, true, enumNuevoPago);
		CuentaSeleccionView cuenta = new CuentaSeleccionView(
				sesionCliente.getCliente().getCuenta(nuevoPagoView.getCuentaSeleccionada()));
		detalleView.setFechaOperacion(StringUtils.left(nuevoPagoView.getFechaHora(), 10));
		detalleView.setHora(nuevoPagoView.getFechaHora().split("-")[1].trim());
		if (cuenta.isTarjeta()) {
			detalleView.setNroCuentaOrigen(cuenta.obtenerNumeroTarjeta());
		} else {
			detalleView.setNroCuentaOrigen(cuenta.getNumero());
			detalleView.setTipoCuentaOrigen(cuenta.getDescripcionTipoCuenta());
		}
		detalleView.setFechaActual(nuevoPagoView.getFechaHora());
		detalleView.setNroControl(nuevoPagoView.getNroControl());
		detalleView.setNroTransaccion(nuevoPagoView.getNroDeComprobante());
		return detalleView;
	}

	/**
	 * Obtener pago afip.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param medioPago
	 *            the medio pago
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @param fechaHora
	 *            the fecha hora
	 * @param elementoAdicional
	 *            the elemento adicional
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoAfip(PagoMultipleView nuevoPagoView, MedioPago medioPago,
			TipoNuevoPagoEnum enumNuevoPago, String fechaHora, String elementoAdicional) {
		DetalleComprobantePMCAfipView detalleView = new DetalleComprobantePMCAfipView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setImportePesos(obtenerMontoFormateado(nuevoPagoView.getMonto()));
		detalleView.setEmpresa(nuevoPagoView.getNombreEmpresa());
		detalleView.setFechaVencimiento("-/-/-");
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(ISBANStringUtils
				.agregarGuionesANumeroCuitCuil(nuevoPagoView.getIdentificacionCliente().substring(0, 11)));
		if (!ISBANStringUtils.isEmptyOrNull(nuevoPagoView.getNumeroFactura())) {
			detalleView.setCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(nuevoPagoView.getNumeroFactura()));
			detalleView.setTipoCuit("CUIT/CUIL del empleador");
		}
		obtenerElementoAdicional(detalleView, nuevoPagoView, true, enumNuevoPago, elementoAdicional);
		detalleView.setFechaOperacion(StringUtils.left(fechaHora, 10));
		detalleView.setHora(fechaHora.split(" ")[1].trim());
		detalleView.setNroCuentaOrigen(nuevoPagoView.getMedioDePago());
		detalleView.setTipoCuentaOrigen(nuevoPagoView.getDescripcionTipoCuenta());
		detalleView.setFechaActual(fechaHora);
		detalleView.setNroControl(nuevoPagoView.getNumeroDeControl());
		detalleView.setNroTransaccion(nuevoPagoView.getComprobantePorServicio());
		return detalleView;
	}

	/**
	 * Obtener elemento adicional.
	 *
	 * @param detalleView
	 *            the detalle view
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param isDomestico
	 *            the is domestico
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @param elementoAdicional
	 *            the elemento adicional
	 */
	private void obtenerElementoAdicional(DetalleComprobantePMCAfipView detalleView, PagoMultipleView nuevoPagoView,
			boolean isDomestico, TipoNuevoPagoEnum enumNuevoPago, String elementoAdicional) {
		detalleView.setElementoAdicional(elementoAdicional);
		if (ISBANStringUtils.isNullOrBlank(elementoAdicional)) {
			detalleView.setTipoElementoAdicional("Periodo");
		} else {
			MedioPago medioPago = mediosPagoBO.obtenerPorCodigo(nuevoPagoView.getCodigoEmpresa());
			String datoAdicional = medioPago.getDatosAdicionales();
			if ("1".equals(datoAdicional)) {
				detalleView.setTipoElementoAdicional("Anticipo");
			} else if ("2".equals(datoAdicional)) {
				detalleView.setTipoElementoAdicional("Anticipo/Año");
			} else if ("3".equals(datoAdicional)) {
				detalleView.setTipoElementoAdicional("Mes/Año");
			} else if ("5".equals(datoAdicional)) {
				detalleView.setTipoElementoAdicional("Cuota");
			}
		}

	}

	/**
	 * Obtener pago VEP.
	 *
	 * @param pago
	 *            the pago
	 * @param medioPago
	 *            the medio pago
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoVEP(NuevoPago pago, MedioPago medioPago, String fechaVencimiento) {
		DetalleComprobantePMCVEPView detalleView = new DetalleComprobantePMCVEPView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setEmpresa(pago.getNombreEmpresa());
		detalleView.setImportePesos(obtenerMontoFormateado(pago.getMonto()));
		detalleView.setFechaPago(StringUtils.left(pago.getFechaHora(), 10));
		CuentaSeleccionView cuenta = new CuentaSeleccionView(
				sesionCliente.getCliente().getCuenta(pago.getCuentaSeleccionada()));
		detalleView.setFechaVencimiento(fechaVencimiento);
		detalleView.setTipoCuit(CuitEnum.CUIT_CONTRIBUYENTE.getDetalle());
		detalleView.setCuit(pago.getCodigoPagoElectronico());
		if (StringUtils.length(pago.getCodigoPagoElectronico2()) == 11) {
			detalleView.setCuit2(pago.getCodigoPagoElectronico2());
		}
		detalleView.setNroVEP(pago.getNumeroVep());
		detalleView.setPeriodo(pago.getPeriodo());
		detalleView.setAnticipo(pago.getAnticipo());
		if (cuenta.isTarjeta()) {
			detalleView.setNroCuentaOrigen(cuenta.obtenerNumeroTarjeta());
		} else {
			detalleView.setNroCuentaOrigen(cuenta.getNumero());
			detalleView.setTipoCuentaOrigen(cuenta.getDescripcionTipoCuenta());
		}
		detalleView.setFechaPago(pago.getFechaHora());
		detalleView.setNroControl(pago.getNroControl());
		detalleView.setFechaActual(pago.getFechaHora());
		detalleView.setNroTransaccion(pago.getNroDeComprobante());
		return detalleView;
	}

	/**
	 * Obtener elemento adicional.
	 *
	 * @param detalle
	 *            the detalle
	 * @param transferencia
	 *            the transferencia
	 * @param isDomestico
	 *            the is domestico
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 */
	private void obtenerElementoAdicional(DetalleComprobantePMCAfipView detalle, NuevoPago transferencia,
			boolean isDomestico, TipoNuevoPagoEnum enumNuevoPago) {

		String periodoDePago = setearCamposAdicionales(obtenerAdicional1(transferencia, enumNuevoPago),
				obtenerAdicional2(transferencia, enumNuevoPago), isDomestico);
		if (TipoNuevoPagoEnum.DOMESTICO.equals(enumNuevoPago)) {
			detalle.setTipoElementoAdicional("Mes/Año");
			detalle.setElementoAdicional(transferencia.getMes().concat(" - ").concat(transferencia.getAnio()));
		} else {
			MedioPago medioPago = mediosPagoBO.obtenerPorCodigo(transferencia.getFiid());
			String datoAdicional = medioPago.getDatosAdicionales();
			detalle.setElementoAdicional(periodoDePago);
			if ("1".equals(datoAdicional)) {
				detalle.setTipoElementoAdicional("Anticipo");
			} else if ("2".equals(datoAdicional)) {
				detalle.setTipoElementoAdicional("Anticipo/Año");
			} else if ("3".equals(datoAdicional)) {
				detalle.setTipoElementoAdicional("Mes/Año");
			} else if ("5".equals(datoAdicional)) {
				detalle.setTipoElementoAdicional("Cuota");
			}
		}
	}

	/**
	 * Obtener adicional 1.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return the string
	 */
	private String obtenerAdicional1(NuevoPago transferencia, TipoNuevoPagoEnum enumNuevoPago) {
		String res = null;
		if (TipoNuevoPagoEnum.DOMESTICO.equals(enumNuevoPago) || TipoNuevoPagoEnum.MONOTRIBUTO.equals(enumNuevoPago)) {
			res = transferencia.getMes();
		} else if (TipoNuevoPagoEnum.AFIP_CAT1.equals(enumNuevoPago)
				|| TipoNuevoPagoEnum.AFIP_CAT2.equals(enumNuevoPago)) {
			res = transferencia.getNumeroDeAnticipo();
		} else if (TipoNuevoPagoEnum.AFIP_CAT5.equals(enumNuevoPago)) {
			res = transferencia.getNumeroDeCuota();
		}
		return res;
	}

	/**
	 * Obtener adicional 2.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return the string
	 */
	private String obtenerAdicional2(NuevoPago transferencia, TipoNuevoPagoEnum enumNuevoPago) {
		String res = null;
		if (TipoNuevoPagoEnum.DOMESTICO.equals(enumNuevoPago) || TipoNuevoPagoEnum.AFIP_CAT2.equals(enumNuevoPago)
				|| TipoNuevoPagoEnum.MONOTRIBUTO.equals(enumNuevoPago)) {
			res = transferencia.getAnio();
		}
		return res;
	}

	/**
	 * Setear campos adicionales.
	 *
	 * @param datosAdicionales1
	 *            the datos adicionales 1
	 * @param datosAdicionales2
	 *            the datos adicionales 2
	 * @param isDomestico
	 *            the is domestico
	 * @return the string
	 */
	private String setearCamposAdicionales(String datosAdicionales1, String datosAdicionales2, boolean isDomestico) {
		String res = "";
		if (datosAdicionales1 != null) {
			res = res + datosAdicionales1;
			if (datosAdicionales2 != null) {
				res = res + " - " + datosAdicionales2;
			}
		}
		if (res.isEmpty()) {
			res = null;
		}
		return res;
	}

	/**
	 * Obtener pago sin deuda.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param medioPago
	 *            the medio pago
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoSinDeuda(NuevoPago nuevoPagoView, MedioPago medioPago) {
		DetalleComprobantePMCView detalleView = new DetalleComprobantePMCView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setImportePesos(obtenerMontoFormateado(nuevoPagoView.getMonto()));
		detalleView.setEmpresa(nuevoPagoView.getNombreEmpresa());
		CuentaSeleccionView cuenta = new CuentaSeleccionView(
				sesionCliente.getCliente().getCuenta(nuevoPagoView.getCuentaSeleccionada()));
		detalleView.setFechaPago(StringUtils.left(nuevoPagoView.getFechaHora(), 10));
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(nuevoPagoView.getCodigoPagoElectronico());
		if (cuenta.isTarjeta()) {
			detalleView.setNroCuentaOrigen(cuenta.obtenerNumeroTarjeta());
		} else {
			detalleView.setNroCuentaOrigen(cuenta.getNumero());
			detalleView.setTipoCuentaOrigen(cuenta.getDescripcionTipoCuenta());
		}
		detalleView.setFechaActual(nuevoPagoView.getFechaHora());
		detalleView.setNroControl(nuevoPagoView.getNroControl());
		detalleView.setNroTransaccion(nuevoPagoView.getNroDeComprobante());
		return detalleView;
	}

	/**
	 * Obtener pago con deuda.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param medioPago
	 *            the medio pago
	 * @param fechaVencimiento
	 *            the fecha vencimiento
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoConDeuda(NuevoPago nuevoPagoView, MedioPago medioPago,
			String fechaVencimiento) {
		if (nuevoPagoView.getisFromAdhesionDebitoAutomatico()) {
			DetalleComprobanteAdhesionDebitoAutomatico detalleComprobanteAdhesionDebitoAutomatico = new DetalleComprobanteAdhesionDebitoAutomatico();
			detalleComprobanteAdhesionDebitoAutomatico.setEmpresa(nuevoPagoView.getNombreEmpresa());
			detalleComprobanteAdhesionDebitoAutomatico.setNroComprobante(nuevoPagoView.getNroDeComprobante());
			detalleComprobanteAdhesionDebitoAutomatico.setIdentificacion(nuevoPagoView.getIdentificacion());

			if ("0".equals(nuevoPagoView.getImporteLimite())) {
				detalleComprobanteAdhesionDebitoAutomatico.setLimite("Sin límite");
			} else {
				BigDecimal importeLimite = new BigDecimal(nuevoPagoView.getImporteLimite());
				detalleComprobanteAdhesionDebitoAutomatico.setLimite(ISBANStringUtils.formatearSaldo(importeLimite));
			}
			detalleComprobanteAdhesionDebitoAutomatico.setFechaOperacion(nuevoPagoView.getFechaHora());
			detalleComprobanteAdhesionDebitoAutomatico.setNombrePago(nuevoPagoView.getNombreMedioDePago());
			detalleComprobanteAdhesionDebitoAutomatico.setNumeroPago(nuevoPagoView.getMedioPago());
			detalleComprobanteAdhesionDebitoAutomatico
					.setTituloComprobante("Comprobante de adhesion debito automatico");
			return detalleComprobanteAdhesionDebitoAutomatico;
		}

		DetalleComprobantePMCView detalleView = new DetalleComprobantePMCView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		if (nuevoPagoView.getMonto() != null) {
			detalleView.setImportePesos(obtenerMontoFormateado(nuevoPagoView.getMonto()));
		}

		detalleView.setEmpresa(nuevoPagoView.getNombreEmpresa());
		detalleView.setFechaPago(StringUtils.left(nuevoPagoView.getFechaHora(), 10));
		CuentaSeleccionView cuenta = new CuentaSeleccionView(
				sesionCliente.getCliente().getCuenta(nuevoPagoView.getCuentaSeleccionada()));
		detalleView.setFechaVencimiento(fechaVencimiento);
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(nuevoPagoView.getNumeroReferenciaPago());
		detalleView.setFactura(nuevoPagoView.getFacturaNumero());
		if (cuenta.isTarjeta()) {
			detalleView.setNroCuentaOrigen(cuenta.obtenerNumeroTarjeta());
		} else {
			detalleView.setNroCuentaOrigen(cuenta.getNumero());
			detalleView.setTipoCuentaOrigen(cuenta.getDescripcionTipoCuenta());
		}
		detalleView.setFechaActual(nuevoPagoView.getFechaHora());
		detalleView.setNroControl(nuevoPagoView.getNroControl());
		detalleView.setNroTransaccion(nuevoPagoView.getNroDeComprobante());
		return detalleView;
	}

	/**
	 * Obtener pago con deuda.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @param medioPago
	 *            the medio pago
	 * @param nuevoPagoReq
	 *            the nuevo pago req
	 * @param fechaHora
	 *            the fecha hora
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoConDeuda(PagoMultipleView pagoMultipleView, MedioPago medioPago,
			PagoMultipleView nuevoPagoReq, String fechaHora) {
		DetalleComprobantePMCView detalleView = new DetalleComprobantePMCView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setImportePesos(obtenerMontoFormateado(pagoMultipleView.getMonto()));
		detalleView.setEmpresa(pagoMultipleView.getNombreEmpresa());
		detalleView.setFechaPago(StringUtils.left(pagoMultipleView.getFechaHora(), 10));
		if (pagoMultipleView.getFechaVencimiento() == null) {
			detalleView.setFechaVencimiento("-/-/-");
		} else {
			detalleView.setFechaVencimiento(pagoMultipleView.getFechaVencimiento());
		}
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
		detalleView.setFactura(pagoMultipleView.getNumeroFactura());
		detalleView.setFechaPago(pagoMultipleView.getFechaPago());
		detalleView.setNroCuentaOrigen(nuevoPagoReq.getNumeroCuenta());
		detalleView.setTipoCuentaOrigen(nuevoPagoReq.getTipoCuenta());
		detalleView.setNroCuentaOrigen(pagoMultipleView.getMedioDePago());
		detalleView.setTipoCuentaOrigen(pagoMultipleView.getDescripcionTipoCuenta());
		detalleView.setFechaActual(fechaHora);
		detalleView.setNroControl(pagoMultipleView.getNumeroDeControl());
		detalleView.setNroTransaccion(pagoMultipleView.getComprobantePorServicio());
		return detalleView;
	}

	/**
	 * Obtener pago VEP.
	 *
	 * @param nuevoPagoView
	 *            the nuevo pago view
	 * @param medioPago
	 *            the medio pago
	 * @param nuevoPagoReq
	 *            the nuevo pago req
	 * @param fechaHora
	 *            the fecha hora
	 * @param cuitVep
	 *            the cuit vep
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoVEP(PagoMultipleView nuevoPagoView, MedioPago medioPago,
			PagoMultipleView nuevoPagoReq, String fechaHora, String cuitVep) {
		DetalleComprobantePMCVEPView detalle = new DetalleComprobantePMCVEPView();
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalle.setImportePesos(obtenerMontoFormateado(nuevoPagoView.getMonto()));
		detalle.setEmpresa(nuevoPagoView.getNombreEmpresa());
		detalle.setFechaVencimiento(nuevoPagoView.getFechaVencimiento());
		detalle.setTipoCuit(CuitEnum.CUIT_CONTRIBUYENTE.getDetalle());
		detalle.setCuit(nuevoPagoReq.getIdentificacionCliente());
		detalle.setFactura(nuevoPagoView.getNumeroFactura());
		detalle.setAnticipo(nuevoPagoView.getAnticipo());
		detalle.setPeriodo(nuevoPagoView.getPeriodo());
		detalle.setNroVEP(nuevoPagoView.getNumeroVep());
		detalle.setNroCuentaOrigen(nuevoPagoReq.getMedioDePago());
		detalle.setTipoCuentaOrigen(nuevoPagoReq.getDescripcionTipoCuenta());
		detalle.setFechaActual(fechaHora);
		detalle.setNroControl(nuevoPagoView.getNumeroDeControl());
		detalle.setNroTransaccion(nuevoPagoView.getComprobantePorServicio());
		detalle.setFechaPago(fechaHora);
		return detalle;
	}

	/**
	 * Obtener pago sin deuda.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @param medioPago
	 *            the medio pago
	 * @param nuevoPagoReq
	 *            the nuevo pago req
	 * @param fechaHora
	 *            the fecha hora
	 * @return the detalle comprobante view
	 */
	private DetalleComprobanteView obtenerPagoSinDeuda(PagoMultipleView pagoMultipleView, MedioPago medioPago,
			PagoMultipleView nuevoPagoReq, String fechaHora) {
		DetalleComprobantePMCView detalleView = new DetalleComprobantePMCView();
		detalleView.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalleView.setImportePesos(pagoMultipleView.getMonto());
		detalleView.setEmpresa(pagoMultipleView.getNombreEmpresa());
		detalleView.setLabelDinamico(obtenerLabelDinamico(medioPago));
		detalleView.setIdentificacion(pagoMultipleView.getIdentificacionCliente());
		detalleView.setNroCuentaOrigen(nuevoPagoReq.getMedioDePago());
		detalleView.setTipoCuentaOrigen(nuevoPagoReq.getDescripcionTipoCuenta());
		detalleView.setFechaPago(pagoMultipleView.getFechaPago());
		detalleView.setFechaActual(fechaHora);
		detalleView.setNroControl(pagoMultipleView.getNumeroDeControl());
		detalleView.setNroTransaccion(pagoMultipleView.getComprobantePorServicio());
		return detalleView;
	}

	/**
	 * Es tipo pago con deuda.
	 *
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return true, if successful
	 */
	private boolean esTipoPagoConDeuda(TipoNuevoPagoEnum enumNuevoPago) {
		return "7".equals(enumNuevoPago.getId()) || "8".equals(enumNuevoPago.getId());
	}

	/**
	 * Es tipo pago sin deuda.
	 *
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return true, if successful
	 */
	private boolean esTipoPagoSinDeuda(TipoNuevoPagoEnum enumNuevoPago) {
		return "0".equals(enumNuevoPago.getId()) || "9".equals(enumNuevoPago.getId())
				|| "10".equals(enumNuevoPago.getId());
	}

	/**
	 * Es tipo pago afip.
	 *
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return true, if successful
	 */
	private boolean esTipoPagoAfip(TipoNuevoPagoEnum enumNuevoPago) {
		boolean esAfip1 = "1".equals(enumNuevoPago.getId()) || "2".equals(enumNuevoPago.getId())
				|| "3".equals(enumNuevoPago.getId());
		return esAfip1 || "4".equals(enumNuevoPago.getId()) || "5".equals(enumNuevoPago.getId());
	}

	/**
	 * Es tipo pago VEP.
	 *
	 * @param enumNuevoPago
	 *            the enum nuevo pago
	 * @return true, if successful
	 */
	private boolean esTipoPagoVEP(TipoNuevoPagoEnum enumNuevoPago) {
		return "6".equals(enumNuevoPago.getId());
	}

	/**
	 * Obtener label dinamico.
	 *
	 * @param medioPago
	 *            the medio pago
	 * @return the string
	 */
	private String obtenerLabelDinamico(MedioPago medioPago) {
		String label = "Número de Identificación";
		if (medioPago != null) {
			if (medioPago.getPesIdentificacion().isEmpty()) {
				return label;
			} else {
				String labelDinamico = medioPago.getPesIdentificacion();
				String labelDinamicoMinuscula = StringUtils.lowerCase(labelDinamico);
				return StringUtils.capitalize(labelDinamicoMinuscula);
			}
		}
		return label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO
	 * #obtenerComprobantePDF(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.ordenventa.entities.ComprobanteOrdenVenta)
	 */
	@Override
	public Respuesta<Reporte> obtenerComprobantePDF(ComprobanteOrdenVenta comprobanteOrdenVenta) {
		DetalleComprobanteView detalleView = comprobanteOrdenVenta;
		return obtenerComprobantePDF(detalleView);
	}

	/**
	 * Obtener monto formateado.
	 *
	 * @param monto
	 *            the monto
	 * @return the string
	 */
	private String obtenerMontoFormateado(String monto) {
		if (StringUtils.indexOf(monto, '.') >= monto.length() - 3) {
			monto = StringUtils.remove(monto, ',');
		} else if (StringUtils.indexOf(monto, ',') >= monto.length() - 3) {
			monto = StringUtils.remove(monto, '.');
			monto = StringUtils.replaceChars(monto, ',', '.');
		} else {
			monto = StringUtils.remove(monto, ',');
			monto = StringUtils.remove(monto, '.');
		}
		monto = ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(monto));
		return monto;
	}

	@Override
	public Respuesta<Reporte> obtenerArchivoPDF(String ubicacion, String nombre) {
		FileInputStream file = null;
		try {
			LOGGER.info("Se busca el " + ubicacion + " a la ruta dada");
			Reporte reporte = new Reporte();
			file = new FileInputStream(filePath.getFilePath() + ubicacion);
			reporte.setBytes(IOUtils.toByteArray(file));
			reporte.setNombre(nombre);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage());
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGAR_ARCHIVO.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}
}
